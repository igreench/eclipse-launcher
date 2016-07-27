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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.debug.ui.IDebugModelPresentation;
import org.eclipse.swt.graphics.Image;

class LauncherUIModel implements ILauncherUIModel {

	private List<String> selectedLaunchNames = new ArrayList<String>();
	
	private IDebugModelPresentation debugModelPresentation = DebugUITools.newDebugModelPresentation();
	
	protected Map<String, Image> launchTypeIcons = new HashMap<String, Image>();
	
	private List<String> launchTypes = new ArrayList<String>();
	
	private ILauncherModel launcherModel;

	public LauncherUIModel(ILauncherModel launcherModel) {
		this.launcherModel = launcherModel;
		launcherModel.addListener(new ILauncherModelListener() {
			@Override
			public void launcherModelChanged() {
				updateLaunchConfigurations();
			}
		});
		updateLaunchConfigurations();
	}

	public void updateLaunchConfigurations() {
		try {
			for (ILaunchConfiguration launchConfiguration : launcherModel.getLaunchConfigurations()) {
				launchTypeIcons.put(launchConfiguration.getType().getName(), getLaunchConfigurationImage(launchConfiguration));

				if (-1 == launchTypes.indexOf(launchConfiguration.getType().getName())) {
					launchTypes.add(launchConfiguration.getType().getName());
				}
			}
		} catch (CoreException e) {
			LauncherUIPlugin.log(e);
		}
	}

	public List<String> getLaunchTypes() {
		return launchTypes;
	}

	public Image getLaunchConfigurationIcon(String launchName) {
		try {
			return launchTypeIcons.get(launcherModel.getLaunchConfigurationType(launchName));
		} catch (CoreException e) {
			return null;
		}
	}

	public Image getLaunchTypeIcon(String launchType) {
		return launchTypeIcons.get(launchType);
	}

	public Image getLaunchConfigurationImage(ILaunchConfiguration configuration) {
		return debugModelPresentation.getImage(configuration);
	}

	public void clear() {
		launcherModel.clear();
		selectedLaunchNames.clear();
	}
}
