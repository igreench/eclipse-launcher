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

import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchDelegate;
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate;
import org.eclipse.debug.core.sourcelookup.ISourcePathComputer;

/**
 * Testing helper class of launch configuration type
 *
 */
public class TestLaunchConfigurationType implements ILaunchConfigurationType {

	private String name;

	public TestLaunchConfigurationType(String name) {
		this.name = name;
	}

	@Override
	public <T> T getAdapter(Class<T> adapter) {
		return null;
	}

	@Override
	public String getAttribute(String attributeName) {
		return null;
	}

	@Override
	public String getCategory() {
		return null;
	}

	@Override
	public ILaunchConfigurationDelegate getDelegate() throws CoreException {
		return null;
	}

	@Override
	public ILaunchConfigurationDelegate getDelegate(String mode) throws CoreException {
		return null;
	}

	@Override
	public ILaunchDelegate[] getDelegates(Set<String> modes) throws CoreException {
		return null;
	}

	@Override
	public ILaunchDelegate getPreferredDelegate(Set<String> modes) throws CoreException {
		return null;
	}

	@Override
	public void setPreferredDelegate(Set<String> modes, ILaunchDelegate delegate) throws CoreException {
		// do nothing
	}

	@Override
	public boolean supportsModeCombination(Set<String> modes) {
		return false;
	}

	@Override
	public String getIdentifier() {
		return null;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getPluginIdentifier() {
		return null;
	}

	@Override
	public String getSourceLocatorId() {
		return null;
	}

	@Override
	public ISourcePathComputer getSourcePathComputer() {
		return null;
	}

	@Override
	public Set<String> getSupportedModes() {
		return null;
	}

	@Override
	public Set<Set<String>> getSupportedModeCombinations() {
		return null;
	}

	@Override
	public boolean isPublic() {
		return true;
	}

	@Override
	public ILaunchConfigurationWorkingCopy newInstance(IContainer container, String name) throws CoreException {
		return null;
	}

	@Override
	public boolean supportsMode(String mode) {
		return false;
	}

	@Override
	public String getContributorName() {
		return "xored candidate guy"; //$NON-NLS-1$
	}

}
