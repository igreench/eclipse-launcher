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
package org.igreench.launcher.ui;

import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.graphics.Image;

import org.igreench.launcher.ILauncherModel;
import org.igreench.launcher.LauncherPlugin;
import org.igreench.launcher.LauncherUIPlugin;
import org.igreench.launcher.ui.widgets.ITwoLevelTreeWidget;

public final class LauncherUIUtilities {

	public static void addLaunches(ITwoLevelTreeWidget launchWidget, List<String> addinglaunchNames,
			ILauncherModel defaultModel) throws CoreException {
		for (String launchName : addinglaunchNames) {

			Image image = LauncherUIPlugin.getDefault().getLauncherUIModel().getLaunchConfigurationIcon(launchName);

			launchWidget.addRoot(LauncherUIUtilities.getIterationName(),
					LauncherUIPlugin.getDefault().getIterationImage(), true, true);

			String[] modes = defaultModel.getLaunchModes(launchName);
			String mode = null;
			if (modes.length > 0) {
				mode = modes[0];
			}
			launchWidget.addNode(launchWidget.getRootCount() - 1, new String[] { launchName, mode }, image, true,
					false);
		}
	}

	public static void addIteration(ITwoLevelTreeWidget treeWidget) {
		treeWidget.addRoot(LauncherUIStrings.ITERATION_LABEL, LauncherUIPlugin.getDefault().getIterationImage(), true,
				true);
	}

	public static void addDelay(ITwoLevelTreeWidget treeWidget, int value) {
		treeWidget.addRoot(new String[] { LauncherUIStrings.DELAY_LABEL, String.valueOf(value) },
				LauncherUIPlugin.getDefault().getDelayImage(), true, true);
	}

	public static void updateLaunchesWidget(ITwoLevelTreeWidget launcherWidget) throws CoreException {
		launcherWidget.clear();

		List<String> launchTypes = LauncherUIPlugin.getDefault().getLauncherUIModel().getLaunchTypes();

		for (String launchType : launchTypes) {

			Image image = LauncherUIPlugin.getDefault().getLauncherUIModel().getLaunchTypeIcon(launchType);

			launcherWidget.addRoot(launchType, image, true, true);
		}

		for (String launchName : LauncherPlugin.getDefault().getLauncherModel().getLaunchNames()) {

			Image image = LauncherUIPlugin.getDefault().getLauncherUIModel().getLaunchConfigurationIcon(launchName);

			launcherWidget.addNode(
					launchTypes.indexOf(
							LauncherPlugin.getDefault().getLauncherModel().getLaunchConfigurationType(launchName)),
					new String[] { launchName }, image, true, false);
		}
	}

	public static void updateConfigurationWidget(ITwoLevelTreeWidget configWidget) throws CoreException {
		configWidget.clear();

		for (String launchName : LauncherPlugin.getDefault().getLauncherModel().getLaunchNames()) {

			Map<String, Object> launchAttributes = LauncherPlugin.getDefault().getLauncherModel()
					.getLaunchAttribute(launchName);

			configWidget.addRoot(launchName,
					LauncherUIPlugin.getDefault().getLauncherUIModel().getLaunchConfigurationIcon(launchName));

			for (String launchNamesAttribute : launchAttributes.keySet()) {
				configWidget.addNode(configWidget.getRootCount() - 1,
						new String[] { launchNamesAttribute, launchAttributes.get(launchNamesAttribute).toString() },
						null);
			}
		}
	}

	public static String getIterationName() {
		return LauncherUIStrings.ITERATION_LABEL;
	}

	public static String[] getDelayName(int value) {
		return new String[] { LauncherUIStrings.DELAY_LABEL, String.valueOf(value) };
	}

	private LauncherUIUtilities() {
		// Not instantiable
	}
}
