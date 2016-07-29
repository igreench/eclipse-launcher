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
package org.igreench.launcher.model;

import java.util.List;

import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.swt.graphics.Image;

public interface ILauncherUIModel {

	public void updateLaunchConfigurations();

	public List<String> getLaunchTypes();
	
	public Image getLaunchConfigurationIcon(String launchName);

	public Image getLaunchTypeIcon(String launchType);

	public Image getLaunchConfigurationImage(ILaunchConfiguration configuration);

	public void clear();
	
	public void addListener(ILauncherUIModelListener listener);
	
	public void removeListener(ILauncherUIModelListener listener);
}
