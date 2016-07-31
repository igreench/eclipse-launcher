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

	private ILaunchConfigurationWorkingCopy workingCopy;

	public TestLaunchConfiguration(String name, ILaunchConfigurationType type) {
		workingCopy = new TestLaunchConfigurationWorkingCopy(name, type);
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
		return workingCopy.exists();
	}

	@Override
	public boolean getAttribute(String attributeName, boolean defaultValue) throws CoreException {
		return workingCopy.getAttribute(attributeName, defaultValue);
	}

	@Override
	public int getAttribute(String attributeName, int defaultValue) throws CoreException {
		return workingCopy.getAttribute(attributeName, defaultValue);
	}

	@Override
	public List<String> getAttribute(String attributeName, List<String> defaultValue) throws CoreException {
		return workingCopy.getAttribute(attributeName, defaultValue);
	}

	@Override
	public Set<String> getAttribute(String attributeName, Set<String> defaultValue) throws CoreException {
		return workingCopy.getAttribute(attributeName, defaultValue);
	}

	@Override
	public Map<String, String> getAttribute(String attributeName, Map<String, String> defaultValue)
			throws CoreException {
		return workingCopy.getAttribute(attributeName, defaultValue);
	}

	@Override
	public String getAttribute(String attributeName, String defaultValue) throws CoreException {
		return workingCopy.getAttribute(attributeName, defaultValue);
	}

	@Override
	public Map<String, Object> getAttributes() throws CoreException {
		return workingCopy.getAttributes();
	}

	@Override
	public String getCategory() throws CoreException {
		return workingCopy.getCategory();
	}

	@Override
	public IFile getFile() {
		return workingCopy.getFile();
	}


	@Override
	public IPath getLocation() {
		return null;
	}

	@Override
	public IResource[] getMappedResources() throws CoreException {
		return workingCopy.getMappedResources();
	}

	@Override
	public String getMemento() throws CoreException {
		return workingCopy.getMemento();
	}

	@Override
	public String getName() {
		return workingCopy.getName();
	}

	@SuppressWarnings("deprecation")
	@Override
	public Set<String> getModes() throws CoreException {
		return workingCopy.getType().getSupportedModes();
	}

	@Override
	public ILaunchDelegate getPreferredDelegate(Set<String> modes) throws CoreException {
		return workingCopy.getPreferredDelegate(modes);
	}

	@Override
	public ILaunchConfigurationType getType() throws CoreException {
		return workingCopy.getType();
	}

	@Override
	public ILaunchConfigurationWorkingCopy getWorkingCopy() throws CoreException {
		return workingCopy;
	}

	@Override
	public boolean hasAttribute(String attributeName) throws CoreException {
		return workingCopy.hasAttribute(attributeName);
	}

	@Override
	public boolean isLocal() {
		return workingCopy.isLocal();
	}

	@Override
	public boolean isMigrationCandidate() throws CoreException {
		return workingCopy.isMigrationCandidate();
	}

	@Override
	public boolean isWorkingCopy() {
		return workingCopy.isWorkingCopy();
	}

	@Override
	public ILaunch launch(String mode, IProgressMonitor monitor) throws CoreException {
		return workingCopy.launch(mode, monitor);
	}

	@Override
	public ILaunch launch(String mode, IProgressMonitor monitor, boolean build) throws CoreException {
		return workingCopy.launch(mode, monitor, build);
	}

	@Override
	public ILaunch launch(String mode, IProgressMonitor monitor, boolean build, boolean register) throws CoreException {
		return workingCopy.launch(mode, monitor, build, register);
	}

	@Override
	public void migrate() throws CoreException {
		workingCopy.migrate();
	}

	@Override
	public boolean supportsMode(String mode) throws CoreException {
		return workingCopy.supportsMode(mode);
	}

	@Override
	public boolean isReadOnly() {
		return workingCopy.isReadOnly();
	}

}
