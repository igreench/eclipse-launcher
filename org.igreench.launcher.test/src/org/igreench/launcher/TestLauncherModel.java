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
