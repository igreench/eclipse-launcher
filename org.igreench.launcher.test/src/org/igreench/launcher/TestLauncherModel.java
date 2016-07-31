package org.igreench.launcher;

import org.eclipse.debug.core.ILaunchConfiguration;
import org.igreench.launcher.model.LauncherModel;

public class TestLauncherModel extends LauncherModel {
	public TestLauncherModel(ILaunchConfiguration[] launchConfigurations) {
		for (ILaunchConfiguration launchConfiguration : launchConfigurations) {
			launchConfigurationsMap.put(launchConfiguration.getName(), launchConfiguration);
		}
	}

	public void updateLaunchConfigurations() {
		// do nothing
	}
}
