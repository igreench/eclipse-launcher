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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate2;

import org.igreench.launcher.LauncherAttributeUtilities;
import org.igreench.launcher.LauncherPlugin;
import org.igreench.launcher.model.ILauncherAttributeModel;
import org.igreench.launcher.LauncherPlugin.CycledLauncherAttributeModelException;
import org.igreench.launcher.LauncherPlugin.DeletedLaunchLauncherAttributeModelException;
import org.igreench.launcher.LauncherPlugin.LauncherAttributeModelException;

public class LauncherDelegate implements ILaunchConfigurationDelegate2 {

	private LauncherLaunch launcherLaunch = null;

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

		if (!commonLaunch.equals(launcherLaunch)) {
			throw new CoreException(new Status(IStatus.ERROR, DebugPlugin.getUniqueIdentifier(),
					DebugPlugin.INTERNAL_ERROR, "Received wrong launch object", null)); //$NON-NLS-1$
		}

		if (!(commonLaunch instanceof LauncherLaunch)) {
			throw new CoreException(new Status(IStatus.ERROR, DebugPlugin.getUniqueIdentifier(),
					DebugPlugin.INTERNAL_ERROR, "Received not Launcher launch object", null)); //$NON-NLS-1$
		}

		try {
			ILauncherAttributeModel launcherAttributeModel = LauncherAttributeUtilities.getAttributes(configuration);

			LauncherPlugin.getDefault().validate(configuration, launcherAttributeModel);

		} catch (DeletedLaunchLauncherAttributeModelException e) {
			throw new CoreException(new Status(IStatus.ERROR, DebugPlugin.getUniqueIdentifier(),
					DebugPlugin.INTERNAL_ERROR, "Found deleted launch", null)); //$NON-NLS-1$

		} catch (CycledLauncherAttributeModelException e) {
			throw new CoreException(new Status(IStatus.ERROR, DebugPlugin.getUniqueIdentifier(),
					DebugPlugin.INTERNAL_ERROR, "Launcher's launch cycled", null)); //$NON-NLS-1$

		} catch (LauncherAttributeModelException e) {
			LauncherPlugin.log(e);

		} catch (CoreException e) {
			LauncherPlugin.log(e);
		}

		launcherLaunch = (LauncherLaunch) commonLaunch;

		try {
			for (int i = 0; i < launcherLaunch.getIterationsCount(); i++) {
				int value = launcherLaunch.getIterationDelay(i);

				if (-1 == value) {
					LauncherLaunchUtilities.launch(launcherLaunch.getLaunches().get(i),
							launcherLaunch.getModes().get(i), launcherLaunch, monitor);
				} else {
					LauncherLaunchUtilities.delay(value);
				}
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
		launcherLaunch = new LauncherLaunch(configuration, mode, null);
		return launcherLaunch;
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