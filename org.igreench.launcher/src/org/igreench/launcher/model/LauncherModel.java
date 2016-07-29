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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.igreench.launcher.LauncherPlugin;

public class LauncherModel implements ILauncherModel {

	/**
	 * Map of launch configurations by their names. TreeMap uses for sorting
	 * launch configurations by the name.
	 */
	protected Map<String, ILaunchConfiguration> launchConfigurationsMap = new TreeMap<String, ILaunchConfiguration>();

	private List<ILauncherModelListener> listeners = new ArrayList<ILauncherModelListener>();

	public LauncherModel() {
		updateLaunchConfigurations();
	}

	public void addListener(ILauncherModelListener listener) {
		listeners.add(listener);
	}

	public void removeListener(ILauncherModelListener listener) {
		listeners.remove(listener);
	}

	public void clear() {
		launchConfigurationsMap.clear();
	}

	public void updateLaunchConfigurations() {
		clear();
		try {
			ILaunchConfiguration[] launchConfigurations = DebugPlugin.getDefault().getLaunchManager()
					.getLaunchConfigurations();
			for (ILaunchConfiguration launchConfiguration : launchConfigurations) {
				launchConfigurationsMap.put(launchConfiguration.getName(), launchConfiguration);
			}
			for (ILauncherModelListener listener : listeners) {
				listener.launcherModelChanged();
			}
		} catch (CoreException e) {
			LauncherPlugin.log(e);
		}
	}

	public ILaunchConfiguration getLaunchConfiguration(String launchName) {
		return launchConfigurationsMap.get(launchName);
	}

	public ILaunchConfiguration[] getLaunchConfigurations() throws CoreException {
		return DebugPlugin.getDefault().getLaunchManager().getLaunchConfigurations();
	}

	public String getLaunchConfigurationType(String launchName) throws CoreException {
		if (launchConfigurationsMap.containsKey(launchName)) {
			return launchConfigurationsMap.get(launchName).getType().getName();
		}
		return null;
	}

	public Map<String, Object> getLaunchAttribute(String launchName) throws CoreException {
		return launchConfigurationsMap.get(launchName).getAttributes();
	}

	public String[] getLaunchModes(String launchName) throws CoreException {
		Set<String> launchModes = new HashSet<String>();
		if (!containsLaunchConfiguration(launchName)) {
			return (String[]) launchModes.toArray();
		}
		for (Set<String> supportedModeCombinations : getLaunchConfiguration(launchName).getType()
				.getSupportedModeCombinations()) {
			for (String mode : supportedModeCombinations) {
				launchModes.add(mode);
			}
		}
		return launchModes.toArray(new String[launchModes.size()]);
	}

	public Set<String> getLaunchNames() {
		return launchConfigurationsMap.keySet();
	}

	public boolean containsLaunchConfiguration(String launchName) {
		return launchConfigurationsMap.containsKey(launchName);
	}
}
