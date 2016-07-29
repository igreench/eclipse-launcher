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

import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationListener;
import org.igreench.launcher.LauncherPlugin;

/**
 * Tracks changes of launch configurations to make batch configurations be
 * actual.
 */
public class LaunchConfigurationListener implements ILaunchConfigurationListener {

	/**
	 * Handles adding of launch configurations.
	 */
	public void launchConfigurationAdded(ILaunchConfiguration newConfiguration) {
		/* TODO: 
		 * LauncherPlugin.getDefault().getLauncherModel().addLaunchConfiguration(newConfiguration);
		 * LauncherPlugin.getDefault().getLauncherModel().removeLaunchConfiguration(oldConfiguration);
		 */		
		LauncherPlugin.getDefault().getLauncherModel().updateLaunchConfigurations();
	}

	/**
	 * Handles changing of launch configurations.
	 */
	public void launchConfigurationChanged(ILaunchConfiguration configuration) {
		// do nothing
	}

	/**
	 * Handles deleting of launch configurations.
	 */
	public void launchConfigurationRemoved(ILaunchConfiguration oldConfiguration) {
		/* TODO: 
		 * LauncherPlugin.getDefault().getLauncherModel().removeLaunchConfiguration(oldConfiguration);
		 */
		LauncherPlugin.getDefault().getLauncherModel().updateLaunchConfigurations();
	}
}