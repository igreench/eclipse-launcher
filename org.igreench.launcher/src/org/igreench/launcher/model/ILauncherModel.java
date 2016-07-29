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

import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;

public interface ILauncherModel {
	
	public void clear();

	public void updateLaunchConfigurations();
	
	public ILaunchConfiguration getLaunchConfiguration(String launchName);
	
	public ILaunchConfiguration[] getLaunchConfigurations() throws CoreException;

	public String getLaunchConfigurationType(String launchName) throws CoreException;

	public Map<String, Object> getLaunchAttribute(String launchName) throws CoreException;

	public String[] getLaunchModes(String launchName) throws CoreException;
	
	public Set<String> getLaunchNames();
	
	public boolean containsLaunchConfiguration(String launchName);
	
	public void addListener(ILauncherModelListener listener);
	
	public void removeListener(ILauncherModelListener listener);

}
