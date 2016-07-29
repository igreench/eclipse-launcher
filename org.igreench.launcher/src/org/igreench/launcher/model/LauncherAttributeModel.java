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
import java.util.List;

public class LauncherAttributeModel implements ILauncherAttributeModel {

	private int iterationsCount = 0;
	private List<String> iterationDelays = new ArrayList<String>();
	private List<String> launchNames = new ArrayList<String>();
	private List<String> launchModes = new ArrayList<String>();
	private List<String> launchIterationIndexes = new ArrayList<String>();

	public int getIterationsCount() {
		return iterationsCount;
	}

	public List<String> getIterationDelays() {
		return iterationDelays;
	}

	public void setIterationsCount(int iterationsCount) {
		this.iterationsCount = iterationsCount;
	}

	public void setIterationDelays(List<String> iterationDelays) {
		this.iterationDelays = iterationDelays;
	}

	public void addIterationDelay(String iterationDelay) {
		iterationDelays.add(iterationDelay);
	}

	public List<String> getLaunchNames() {
		return launchNames;
	}

	public void setLaunchNames(List<String> launchNames) {
		this.launchNames = launchNames;
	}

	public void addLaunchName(String launchName) {
		launchNames.add(launchName);
	}

	public List<String> getLaunchModes() {
		return launchModes;
	}

	public void setLaunchModes(List<String> launchModes) {
		this.launchModes = launchModes;
	}

	public void addLaunchMode(String launchMode) {
		launchModes.add(launchMode);
	}

	public List<String> getLaunchIterationIndexes() {
		return launchIterationIndexes;
	}

	public void setLaunchIterationIndexes(List<String> launchIterationIndexes) {
		this.launchIterationIndexes = launchIterationIndexes;
	}

	public void addLaunchIterationIndex(String launchIterationIndex) {
		launchIterationIndexes.add(launchIterationIndex);
	}
}
