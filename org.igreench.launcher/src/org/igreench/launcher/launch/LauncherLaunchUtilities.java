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
package org.igreench.launcher.launch;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.igreench.launcher.LauncherPlugin;

final class LauncherLaunchUtilities {
	
	/**
	 * Launch group of launches. Launches in one group will be wait terminating
	 * previous launches.
	 */
	public static void launch(List<String> launchNames, List<String> modes, LauncherLaunch commonLaunch,
			IProgressMonitor monitor) throws CoreException {

		if (launchNames.size() < 1) {
			return;
		}

		List<ILaunchConfiguration> configurations = new ArrayList<ILaunchConfiguration>();

		for (String launchName : launchNames) {
			configurations.add(LauncherPlugin.getDefault().getLauncherModel().getLaunchConfiguration(launchName));
		}

		ILaunch currentLaunch = launch(configurations.get(0), modes.get(0), monitor);

		LauncherLaunchesListener launcherListener = new LauncherLaunchesListener(commonLaunch, currentLaunch, configurations,
				modes, 1, monitor);

		DebugPlugin.getDefault().getLaunchManager().addLaunchListener(launcherListener);
	}

	/**
	 * Delay method
	 * @param value - the length of time to sleep in milliseconds
	 */
	public static void delay(int value) {
		try {
			Thread.sleep(value);
		} catch (InterruptedException e) {
			LauncherPlugin.log(e);
		}
	}

	/**
	 * Launch new launch and return it.
	 * 
	 * @param configuration - launch configuration
	 * @param mode - launch mode
	 * @param monitor
	 * @return new launch
	 * @throws CoreException
	 */
	public static ILaunch launch(ILaunchConfiguration configuration, String mode, IProgressMonitor monitor)
			throws CoreException {
		ILaunch nextLaunch = configuration.launch(mode, monitor);
		return nextLaunch;
	}
	
	/**
	 * 
	 * @param commonLaunch
	 * @param currentLaunch
	 * @param configurations
	 * @param modes
	 * @param nextLaunchIndex
	 * @param monitor
	 */

	public static void launchNext(LauncherLaunch commonLaunch, ILaunch currentLaunch,
			List<ILaunchConfiguration> configurations, List<String> modes, int nextLaunchIndex,
			IProgressMonitor monitor) {

		if (nextLaunchIndex >= configurations.size() || nextLaunchIndex >= modes.size()) {
			return;
		}

		try {

			ILaunch nextLaunch = launch(configurations.get(nextLaunchIndex), modes.get(nextLaunchIndex), monitor);

			if (nextLaunch instanceof LauncherLaunch) {
				((LauncherLaunch) nextLaunch).addListener(new ILauncherLaunchListener() {
					@Override
					public void allLaunchesTerminated() {
						commonLaunch.oneLaunchTerminated();

						LauncherLaunchUtilities.launchNext(commonLaunch, nextLaunch, configurations, modes,
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
	
	private LauncherLaunchUtilities() {
		// Not instantiable
	}
}
