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

/**
 * Launcher attribute model interface.
 *
 */
public interface ILauncherAttributeModel {
	
	public int getIterationsCount();	
	public void setIterationsCount(int iterationsCount);

	public List<String> getIterationDelays();	
	public void setIterationDelays(List<String> iterationDelays);
	public void addIterationDelay(String iterationDelay);
	
	public List<String> getLaunchNames();	
	public void setLaunchNames(List<String> launchNames);	
	public void addLaunchName(String launchName);
	
	public List<String> getLaunchModes();	
	public void setLaunchModes(List<String> launchModes);
	public void addLaunchMode(String launchMode);
	
	public List<String> getLaunchIterationIndexes();	
	public void setLaunchIterationIndexes(List<String> launchIterationIndexes);
	public void addLaunchIterationIndex(String launchIterationIndex);
	
}
