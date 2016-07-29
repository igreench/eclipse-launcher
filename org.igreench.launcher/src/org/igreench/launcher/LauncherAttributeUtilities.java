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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;

import org.igreench.launcher.LauncherAttributes;
import org.igreench.launcher.model.ILauncherAttributeModel;
import org.igreench.launcher.model.LauncherAttributeModel;

public final class LauncherAttributeUtilities {

	public static ILauncherAttributeModel getAttributes(ILaunchConfiguration configuration)
			throws CoreException {
		
		ILauncherAttributeModel launcherAttributeModel = new LauncherAttributeModel();
		
		launcherAttributeModel.setIterationDelays(
				configuration.getAttribute(LauncherAttributes.ATTRIBUTE_ITERATIONS_DELAY, new ArrayList<String>()));

		launcherAttributeModel.setIterationsCount(
				configuration.getAttribute(LauncherAttributes.ATTRIBUTE_ITERATIONS_COUNT, 0));

		launcherAttributeModel.setLaunchNames(
				configuration.getAttribute(LauncherAttributes.ATTRIBUTE_LAUNCHES_NAME, new ArrayList<String>()));

		launcherAttributeModel.setLaunchModes(
				configuration.getAttribute(LauncherAttributes.ATTRIBUTE_LAUNCHES_MODE, new ArrayList<String>()));

		launcherAttributeModel.setLaunchIterationIndexes(
				configuration.getAttribute(LauncherAttributes.ATTRIBUTE_LAUNCHES_ITERATION_INDEX, new ArrayList<String>()));
		
		return launcherAttributeModel;
	}

	public static void setAttributes(ILaunchConfigurationWorkingCopy configuration, ILauncherAttributeModel launcherAttributeModel) {
		
		configuration.setAttribute(LauncherAttributes.ATTRIBUTE_ITERATIONS_COUNT, 
				launcherAttributeModel.getIterationsCount());
		
		configuration.setAttribute(LauncherAttributes.ATTRIBUTE_LAUNCHES_NAME, 
				launcherAttributeModel.getLaunchNames());
		
		configuration.setAttribute(LauncherAttributes.ATTRIBUTE_LAUNCHES_MODE, 
				launcherAttributeModel.getLaunchModes());
		
		configuration.setAttribute(LauncherAttributes.ATTRIBUTE_LAUNCHES_ITERATION_INDEX, 
				launcherAttributeModel.getLaunchIterationIndexes());
		
		configuration.setAttribute(LauncherAttributes.ATTRIBUTE_ITERATIONS_DELAY, 
				launcherAttributeModel.getIterationDelays());
	}

	private LauncherAttributeUtilities() {
		// Not instantiable
	}
}
