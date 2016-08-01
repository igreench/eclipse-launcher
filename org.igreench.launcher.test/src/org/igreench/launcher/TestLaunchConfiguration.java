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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;

import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchDelegate;

/**
 * Testing helper class of launch configuration
 *
 */
public class TestLaunchConfiguration implements ILaunchConfiguration {

	private Map<String, Object> attributes = new HashMap<String, Object>();

	private String name;

	private ILaunchConfigurationType type;

	private TestLaunchConfigurationWorkingCopy workingCopy;

	public TestLaunchConfiguration() {
	}

	public TestLaunchConfiguration(String name, ILaunchConfigurationType type) {
		this.name = name;
		this.type = type;
	}

	public void update(Map<String, Object> attributes) {
		this.attributes.putAll(attributes);
	}

	@Override
	public boolean contentsEqual(final ILaunchConfiguration configuration) {
		return false;
	}

	@Override
	public ILaunchConfigurationWorkingCopy copy(final String name) throws CoreException {
		return null;
	}

	@Override
	public void delete() throws CoreException {

	}

	@Override
	public boolean exists() {
		return false;
	}

	@Override
	public boolean getAttribute(final String attributeName, final boolean defaultValue) throws CoreException {
		if (attributes.containsKey(attributeName)) {
			return (Boolean) attributes.get(attributeName);
		}
		return defaultValue;
	}

	@Override
	public boolean hasAttribute(final String attributeName) throws CoreException {
		return attributes.containsKey(attributeName);
	}

	@Override
	public int getAttribute(final String attributeName, final int defaultValue) throws CoreException {
		if (attributes.containsKey(attributeName)) {
			return (Integer) attributes.get(attributeName);
		}
		return defaultValue;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAttribute(final String attributeName, final List<String> defaultValue) throws CoreException {
		if (attributes.containsKey(attributeName)) {
			return (List<String>) attributes.get(attributeName);
		}
		return defaultValue;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Set<String> getAttribute(final String attributeName, final Set<String> defaultValue) throws CoreException {
		if (attributes.containsKey(attributeName)) {
			return (Set<String>) attributes.get(attributeName);
		}
		return defaultValue;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> getAttribute(final String attributeName, final Map<String, String> defaultValue)
			throws CoreException {
		if (attributes.containsKey(attributeName)) {
			return (Map<String, String>) attributes.get(attributeName);
		}
		return defaultValue;
	}

	@Override
	public String getAttribute(final String attributeName, final String defaultValue) throws CoreException {
		if (attributes.containsKey(attributeName)) {
			return (String) attributes.get(attributeName);
		}
		return defaultValue;
	}

	@Override
	public Map<String, Object> getAttributes() throws CoreException {
		return attributes;
	}

	@Override
	public String getCategory() throws CoreException {
		return null;
	}

	@Override
	public IFile getFile() {
		return null;
	}

	@Override
	public IPath getLocation() {
		return null;
	}

	@Override
	public IResource[] getMappedResources() throws CoreException {
		return null;
	}

	@Override
	public String getMemento() throws CoreException {
		return null;
	}

	@Override
	public Set<String> getModes() throws CoreException {
		return null;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public ILaunchDelegate getPreferredDelegate(final Set<String> modes) throws CoreException {
		return null;
	}

	@Override
	public ILaunchConfigurationType getType() throws CoreException {
		return type;
	}

	@Override
	public ILaunchConfigurationWorkingCopy getWorkingCopy() throws CoreException {
		if (null == workingCopy) {
			workingCopy = new TestLaunchConfigurationWorkingCopy(this);
		}
		return workingCopy;
	}

	@Override
	public boolean isLocal() {
		return false;
	}

	@Override
	public boolean isMigrationCandidate() throws CoreException {
		return false;
	}

	@Override
	public boolean isReadOnly() {
		return false;
	}

	@Override
	public boolean isWorkingCopy() {
		return false;
	}

	@Override
	public ILaunch launch(final String mode, final IProgressMonitor monitor) throws CoreException {
		return null;
	}

	@Override
	public ILaunch launch(final String mode, final IProgressMonitor monitor, final boolean build) throws CoreException {
		return null;
	}

	@Override
	public ILaunch launch(final String mode, final IProgressMonitor monitor, final boolean build,
			final boolean register) throws CoreException {
		return null;
	}

	@Override
	public void migrate() throws CoreException {

	}

	@Override
	public boolean supportsMode(final String mode) throws CoreException {
		return false;
	}

	@Override
	public <T> T getAdapter(Class<T> adapter) {
		return null;
	}
};
