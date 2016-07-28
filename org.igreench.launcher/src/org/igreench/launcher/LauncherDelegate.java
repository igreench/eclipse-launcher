/*******************************************************************************
 * Copyright (c) 2016 Lipkin Evgenii.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Lipkin Evgenii
 *******************************************************************************/
package org.igreench.launcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchesListener2;
import org.eclipse.debug.core.Launch;
import org.eclipse.debug.core.model.ISourceLocator;
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate2;

public class LauncherDelegate implements ILaunchConfigurationDelegate2 {

	private LauncherLaunch launch = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.debug.core.model.ILaunchConfigurationDelegate#launch(org.
	 * eclipse.debug.core.ILaunchConfiguration, java.lang.String,
	 * org.eclipse.debug.core.ILaunch,
	 * org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void launch(ILaunchConfiguration configuration, String mode, ILaunch commonLaunch, IProgressMonitor monitor)
			throws CoreException {

		if (!commonLaunch.equals(launch)) {
			throw new CoreException(new Status(IStatus.ERROR, DebugPlugin.getUniqueIdentifier(),
					DebugPlugin.INTERNAL_ERROR, "Received wrong launch object", null)); //$NON-NLS-1$
		}

		if (!(commonLaunch instanceof LauncherLaunch)) {
			throw new CoreException(new Status(IStatus.ERROR, DebugPlugin.getUniqueIdentifier(),
					DebugPlugin.INTERNAL_ERROR, "Received not Launcher launch object", null)); //$NON-NLS-1$
		}

		LauncherLaunch launcherLaunch = (LauncherLaunch) commonLaunch;

		try {
			for (int i = 0; i < launcherLaunch.getIterationsCount(); i++) {
				int value = launcherLaunch.getIterationDelay(i);

				if (-1 == value) {
					launch(launcherLaunch.getLaunches().get(i), launcherLaunch.getModes().get(i), launcherLaunch,
							monitor);
				} else {
					delay(value);
				}
			}
		} catch (CoreException e) {
			LauncherPlugin.log(e);
		}

	}

	/**
	 * Launch group of launches. Launches in one group will be wait terminating
	 * previous launches.
	 */
	private void launch(List<String> launchNames, List<String> modes, LauncherLaunch commonLaunch,
			IProgressMonitor monitor) throws CoreException {

		if (launchNames.size() < 1) {
			return;
		}

		List<ILaunchConfiguration> configurations = new ArrayList<ILaunchConfiguration>();

		for (String launchName : launchNames) {
			configurations.add(LauncherPlugin.getDefault().getLauncherModel().getLaunchConfiguration(launchName));
		}

		ILaunch currentLaunch = launch(configurations.get(0), modes.get(0), monitor);

		LauncherLaunchesListener launcherListener = new LauncherLaunchesListener(launch, currentLaunch, configurations,
				modes, 1, monitor);

		DebugPlugin.getDefault().getLaunchManager().addLaunchListener(launcherListener);
	}

	private void delay(int value) {
		try {
			Thread.sleep(value);
		} catch (InterruptedException e) {
			LauncherPlugin.log(e);
		}
	}

	private static ILaunch launch(ILaunchConfiguration configuration, String mode, IProgressMonitor monitor)
			throws CoreException {
		ILaunch nextLaunch = configuration.launch(mode, monitor);
		return nextLaunch;
	}

	public static void launchNext(LauncherLaunch commonLaunch, ILaunch currentLaunch,
			List<ILaunchConfiguration> configurations, List<String> modes, int nextLaunchIndex,
			IProgressMonitor monitor) {

		if (null == configurations.get(nextLaunchIndex) || null == modes.get(nextLaunchIndex)) {
			return;
		}

		try {

			ILaunch nextLaunch = launch(configurations.get(nextLaunchIndex), modes.get(nextLaunchIndex), monitor);

			if (nextLaunch instanceof LauncherLaunch) {
				((LauncherLaunch) nextLaunch).addListener(new ILauncherLaunchListener() {
					@Override
					public void allLaunchesTerminated() {
						commonLaunch.oneLaunchTerminated();

						LauncherDelegate.launchNext(commonLaunch, nextLaunch, configurations, modes,
								nextLaunchIndex + 1, monitor);
					}
				});
				
			} else {
				DebugPlugin.getDefault().getLaunchManager().addLaunchListener(new LauncherLaunchesListener(commonLaunch,
						nextLaunch, configurations, modes, nextLaunchIndex + 1, monitor));
			}

		} catch (CoreException e) {
			LauncherPlugin.log(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.debug.core.model.ILaunchConfigurationDelegate2#getLaunch(org.
	 * eclipse.debug.core.ILaunchConfiguration, java.lang.String)
	 */
	@Override
	public ILaunch getLaunch(ILaunchConfiguration configuration, String mode) throws CoreException {
		launch = new LauncherLaunch(configuration, mode, null);
		return launch;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.debug.core.model.ILaunchConfigurationDelegate2#buildForLaunch
	 * (org.eclipse.debug.core.ILaunchConfiguration, java.lang.String,
	 * org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public boolean buildForLaunch(ILaunchConfiguration configuration, String mode, IProgressMonitor monitor)
			throws CoreException {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.model.ILaunchConfigurationDelegate2#
	 * finalLaunchCheck(org.eclipse.debug.core.ILaunchConfiguration,
	 * java.lang.String, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public boolean finalLaunchCheck(ILaunchConfiguration configuration, String mode, IProgressMonitor monitor)
			throws CoreException {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.debug.core.model.ILaunchConfigurationDelegate2#preLaunchCheck
	 * (org.eclipse.debug.core.ILaunchConfiguration, java.lang.String,
	 * org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public boolean preLaunchCheck(ILaunchConfiguration configuration, String mode, IProgressMonitor monitor)
			throws CoreException {
		return true;
	}
}

/**
 * Launcher launches listener class. It provides access to event of termination
 * launches of launcher
 * 
 */
class LauncherLaunchesListener implements ILaunchesListener2 {

	private LauncherLaunch commonLaunch;

	private ILaunch currentLaunch;

	private List<ILaunchConfiguration> configurations;

	private List<String> modes;

	private IProgressMonitor monitor;

	private int nextLaunchIndex;

	protected LauncherLaunchesListener(LauncherLaunch commonLaunch, ILaunch currentLaunch,
			List<ILaunchConfiguration> configurations, List<String> modes, int nextLaunchIndex,
			IProgressMonitor monitor) {
		this.commonLaunch = commonLaunch;
		this.currentLaunch = currentLaunch;
		this.configurations = configurations;
		this.modes = modes;
		this.monitor = monitor;
		this.nextLaunchIndex = nextLaunchIndex;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.debug.core.ILaunchesListener#launchesRemoved(org.eclipse.
	 * debug.core.ILaunch[])
	 */
	@Override
	public void launchesRemoved(ILaunch[] launches) {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.debug.core.ILaunchesListener#launchesAdded(org.eclipse.debug.
	 * core.ILaunch[])
	 */
	@Override
	public void launchesAdded(ILaunch[] launches) {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.debug.core.ILaunchesListener#launchesChanged(org.eclipse.
	 * debug.core.ILaunch[])
	 */
	@Override
	public void launchesChanged(ILaunch[] launches) {
		// do nothing
	}

	@Override
	public void launchesTerminated(ILaunch[] launches) {
		if (Arrays.asList(launches).contains(currentLaunch)) {

			DebugPlugin.getDefault().getLaunchManager().removeLaunchListener(this);

			commonLaunch.oneLaunchTerminated();

			if (nextLaunchIndex < configurations.size()) {
				LauncherDelegate.launchNext(commonLaunch, currentLaunch, configurations, modes, nextLaunchIndex,
						monitor);
			}
		}
	}
}

/**
 * Launcher launch class. Launcher launch provides:
 * <ul>
 * <li>access to launcherAttributeModel</li>
 * <li>access to events oneLaunchTerminated() and allLaunchesTerminated()</li>
 * </ul>
 */
class LauncherLaunch extends Launch {

	private int launchCount;

	private List<List<String>> launches;

	private List<List<String>> modes;

	private List<ILauncherLaunchListener> listeners = new ArrayList<ILauncherLaunchListener>();

	private ILaunchConfiguration configuration;

	private ILauncherAttributeModel launcherAttributeModel;

	public LauncherLaunch(ILaunchConfiguration configuration, String mode, ISourceLocator locator)
			throws CoreException {
		super(configuration, mode, locator);
		this.configuration = configuration;
		init();
	}

	protected void init() throws CoreException {
		launcherAttributeModel = LauncherAttributeUtilities.getAttributes(configuration);

		launches = new ArrayList<List<String>>();
		modes = new ArrayList<List<String>>();

		launchCount = launcherAttributeModel.getLaunchNames().size();

		for (int i = 0; i < launcherAttributeModel.getIterationsCount(); i++) {
			launches.add(new ArrayList<String>());
			modes.add(new ArrayList<String>());
		}

		for (int i = 0; i < launcherAttributeModel.getLaunchNames().size(); i++) {

			int iterationIndex = JavaUtilities.strToInt(launcherAttributeModel.getLaunchIterationIndexes().get(i), 0);

			launches.get(iterationIndex).add(launcherAttributeModel.getLaunchNames().get(i));
			modes.get(iterationIndex).add(launcherAttributeModel.getLaunchModes().get(i));
		}
	}

	public List<List<String>> getLaunches() {
		return launches;
	}

	public List<List<String>> getModes() {
		return modes;
	}

	public int getIterationsCount() {
		return launcherAttributeModel.getIterationsCount();
	}

	public int getIterationDelay(int index) {
		return JavaUtilities.strToInt(launcherAttributeModel.getIterationDelays().get(index), -1);
	}

	public void addListener(ILauncherLaunchListener listener) {
		listeners.add(listener);
	}

	public void removeListener(ILauncherLaunchListener listener) {
		listeners.remove(listener);
	}

	public void allLaunchesTerminated() {
		for (ILauncherLaunchListener listener : listeners) {
			listener.allLaunchesTerminated();
		}
	}

	public void oneLaunchTerminated() {
		if (0 < launchCount) {
			launchCount--;
		}
		if (0 == launchCount) {
			allLaunchesTerminated();
		}
	}

}

/**
 * Launcher launch listener interface for declaring event of termination all
 * launches
 */
interface ILauncherLaunchListener {

	public void allLaunchesTerminated();
}
