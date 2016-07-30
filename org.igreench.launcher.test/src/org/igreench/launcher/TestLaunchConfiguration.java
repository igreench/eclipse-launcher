package org.igreench.launcher;

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

public class TestLaunchConfiguration implements ILaunchConfiguration {
	
	private String name;
	
	private ILaunchConfigurationType type;
	
	private Map<String, Integer> intAttributes;
	private Map<String, String> stringAttributes;
	private Map<String, List<String>> listAttributes;
	private Map<String, Set<String>> setAttributes;
	private Map<String, Map<String, String>> mapAttributes;
	
	public TestLaunchConfiguration(String name, ILaunchConfigurationType type) {
		this.name = name;
		this.type = type;
	}

	@Override
	public <T> T getAdapter(Class<T> adapter) {
		return null;
	}

	@Override
	public boolean contentsEqual(ILaunchConfiguration configuration) {
		return false;
	}

	@Override
	public ILaunchConfigurationWorkingCopy copy(String name) throws CoreException {
		return null;
	}

	@Override
	public void delete() throws CoreException {		
	}

	@Override
	public boolean exists() {
		return true;
	}

	@Override
	public boolean getAttribute(String attributeName, boolean defaultValue) throws CoreException {
		return false;
	}

	@Override
	public int getAttribute(String attributeName, int defaultValue) throws CoreException {
		if (!intAttributes.containsKey(attributeName)) {
			return defaultValue;
		}
		return intAttributes.get(attributeName);
	}

	@Override
	public List<String> getAttribute(String attributeName, List<String> defaultValue) throws CoreException {
		if (!listAttributes.containsKey(attributeName)) {
			return defaultValue;
		}
		return listAttributes.get(attributeName);
	}

	@Override
	public Set<String> getAttribute(String attributeName, Set<String> defaultValue) throws CoreException {
		if (!setAttributes.containsKey(attributeName)) {
			return defaultValue;
		}
		return setAttributes.get(attributeName);
	}

	@Override
	public Map<String, String> getAttribute(String attributeName, Map<String, String> defaultValue)
			throws CoreException {
		if (!mapAttributes.containsKey(attributeName)) {
			return defaultValue;
		}
		return mapAttributes.get(attributeName);
	}

	@Override
	public String getAttribute(String attributeName, String defaultValue) throws CoreException {
		if (!stringAttributes.containsKey(attributeName)) {
			return defaultValue;
		}
		return stringAttributes.get(attributeName);
	}

	@Override
	public Map<String, Object> getAttributes() throws CoreException {
		// TODO Auto-generated method stub
		return null;
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
	public String getName() {
		return name;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Set<String> getModes() throws CoreException {
		return type.getSupportedModes();
	}

	@Override
	public ILaunchDelegate getPreferredDelegate(Set<String> modes) throws CoreException {
		return null;
	}

	@Override
	public ILaunchConfigurationType getType() throws CoreException {
		return type;
	}

	@Override
	public ILaunchConfigurationWorkingCopy getWorkingCopy() throws CoreException {
		return null;
	}

	@Override
	public boolean hasAttribute(String attributeName) throws CoreException {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isLocal() {
		return true;
	}

	@Override
	public boolean isMigrationCandidate() throws CoreException {
		return true;
	}

	@Override
	public boolean isWorkingCopy() {
		return true;
	}

	@Override
	public ILaunch launch(String mode, IProgressMonitor monitor) throws CoreException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ILaunch launch(String mode, IProgressMonitor monitor, boolean build) throws CoreException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ILaunch launch(String mode, IProgressMonitor monitor, boolean build, boolean register) throws CoreException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void migrate() throws CoreException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean supportsMode(String mode) throws CoreException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isReadOnly() {
		// TODO Auto-generated method stub
		return false;
	}

}
