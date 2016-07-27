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
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate;
import org.eclipse.debug.core.ILaunchesListener2;

public class LauncherDelegate implements ILaunchConfigurationDelegate {

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
	public void launch(ILaunchConfiguration configuration, String mode, ILaunch launch, IProgressMonitor monitor)
			throws CoreException {

		ILauncherAttributeModel launcherAttributeModel = LauncherAttributeUtilities.getAttributes(configuration);

		List<List<String>> launches = new ArrayList<List<String>>();
		List<List<String>> modes = new ArrayList<List<String>>();

		for (int i = 0; i < launcherAttributeModel.getIterationsCount(); i++) {
			launches.add(new ArrayList<String>());
			modes.add(new ArrayList<String>());
		}

		for (int i = 0; i < launcherAttributeModel.getLaunchNames().size(); i++) {

			int iterationIndex = JavaUtilities.strToInt(launcherAttributeModel.getLaunchIterationIndexes().get(i), 0);

			launches.get(iterationIndex).add(launcherAttributeModel.getLaunchNames().get(i));
			modes.get(iterationIndex).add(launcherAttributeModel.getLaunchModes().get(i));
		}

		try {
			for (int i = 0; i < launcherAttributeModel.getIterationsCount(); i++) {
				int value = JavaUtilities.strToInt(launcherAttributeModel.getIterationDelays().get(i), -1);

				if (-1 == value) {
					launch(launches.get(i), modes.get(i), launch, monitor);
				} else {
					delay(value);
				}
			}
		} catch (CoreException e) {
			LauncherPlugin.log(e);
		}
	}

	/**
	 * Launch group of launches. Launches in one iteration will be wait
	 * terminating previous launches.
	 */
	private void launch(List<String> launchNames, List<String> modes, ILaunch launch, IProgressMonitor monitor)
			throws CoreException {

		if (launchNames.size() < 1) {
			return;
		}

		List<ILaunchConfiguration> configurations = new ArrayList<ILaunchConfiguration>();

		for (String launchName : launchNames) {
			configurations.add(LauncherPlugin.getDefault().getLauncherModel().getLaunchConfiguration(launchName));
		}

		ILaunch currentLaunch = configurations.get(0).launch(modes.get(0), monitor);

		LaunchAdapter launchAdapter = new LaunchAdapter(currentLaunch, configurations, modes, 1, monitor);

		DebugPlugin.getDefault().getLaunchManager().addLaunchListener(launchAdapter);
	}

	private static void delay(int value) {
		try {
			Thread.sleep(value);
		} catch (InterruptedException e) {
			LauncherPlugin.log(e);
		}
	}
}

class LaunchAdapter implements ILaunchesListener2 {

	private ILaunch currentLaunch;

	private List<ILaunchConfiguration> configurations;

	private List<String> modes;

	private IProgressMonitor monitor;

	private int nextLaunchIndex;

	protected LaunchAdapter(ILaunch currentLaunch, List<ILaunchConfiguration> configurations, List<String> modes,
			int nextLaunchIndex, IProgressMonitor monitor) {
		this.currentLaunch = currentLaunch;
		this.configurations = configurations;
		this.modes = modes;
		this.monitor = monitor;
		this.nextLaunchIndex = nextLaunchIndex;
	}

	@Override
	public void launchesRemoved(ILaunch[] launches) {
	}

	@Override
	public void launchesAdded(ILaunch[] launches) {
	}

	@Override
	public void launchesChanged(ILaunch[] launches) {
	}

	@Override
	public void launchesTerminated(ILaunch[] launches) {
		if (Arrays.asList(launches).contains(currentLaunch)) {

			DebugPlugin.getDefault().getLaunchManager().removeLaunchListener(this);

			if (null == configurations.get(nextLaunchIndex) || null == modes.get(nextLaunchIndex)) {
				return;
			}

			try {
				ILaunch nextLaunch = configurations.get(nextLaunchIndex).launch(modes.get(nextLaunchIndex), monitor);
				if (nextLaunchIndex < configurations.size() - 1) {
					DebugPlugin.getDefault().getLaunchManager().addLaunchListener(
							new LaunchAdapter(nextLaunch, configurations, modes, nextLaunchIndex + 1, monitor));
				}
			} catch (CoreException e) {
				LauncherPlugin.log(e);
			}
		}
	}
}
