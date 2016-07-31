package org.igreench.launcher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
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

public class TestLaunchConfigurationWorkingCopy implements ILaunchConfigurationWorkingCopy {

	private TestLaunchConfiguration parent;

	private Map<String, Object> attributes = new HashMap<String, Object>();

	public TestLaunchConfigurationWorkingCopy(TestLaunchConfiguration parent) {
		this.parent = parent;
	}

	public void update() {
		parent.update(attributes);
	}

	@Override
	public ILaunchConfiguration doSave() throws CoreException {
		parent.update(attributes);
		return parent;
	}

	@Override
	public void addModes(Set<String> modes) {
		// todo
	}

	@Override
	public boolean hasAttribute(String attributeName) throws CoreException {
		return attributes.containsKey(attributeName);
	}

	@Override
	public Object removeAttribute(String attributeName) {
		return attributes.remove(attributeName);
	}

	@Override
	public ILaunchConfiguration getOriginal() {
		return parent;
	}

	@Override
	public ILaunchConfigurationWorkingCopy getParent() {
		return null;
	}

	@Override
	public boolean isDirty() {
		return true;
	}

	@Override
	public void removeModes(Set<String> modes) {
		// do nothing
	}

	@Override
	public void rename(String name) {
		// do nothing
	}

	@Override
	public void setAttribute(String attributeName, int value) {
		attributes.put(attributeName, value);
	}

	@Override
	public void setAttribute(String attributeName, String value) {
		attributes.put(attributeName, value);
	}

	@Override
	public void setAttribute(String attributeName, List<String> value) {
		attributes.put(attributeName, value);
	}

	@Override
	public void setAttribute(String attributeName, Map<String, String> value) {
		attributes.put(attributeName, value);
	}

	@Override
	public void setAttribute(String attributeName, boolean value) {
		attributes.put(attributeName, value);
	}

	@Override
	public void setAttributes(Map<String, ? extends Object> attributes) {
		this.attributes.putAll(attributes);
	}

	@Override
	public void setContainer(IContainer container) {
		// do nothing
	}

	@Override
	public void setMappedResources(IResource[] resources) {
		// do nothing
	}

	@Override
	public void setModes(Set<String> modes) {
		// todo
	}

	@Override
	public void setPreferredLaunchDelegate(Set<String> modes, String delegateId) {
		// do nothing
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
		// do nothing
	}

	@Override
	public boolean exists() {
		return true;
	}

	@Override
	public boolean getAttribute(String attributeName, boolean defaultValue) throws CoreException {
		return parent.getAttribute(attributeName, defaultValue);
	}

	@Override
	public int getAttribute(String attributeName, int defaultValue) throws CoreException {
		return parent.getAttribute(attributeName, defaultValue);
	}

	@Override
	public List<String> getAttribute(String attributeName, List<String> defaultValue) throws CoreException {
		return parent.getAttribute(attributeName, defaultValue);
	}

	@Override
	public Set<String> getAttribute(String attributeName, Set<String> defaultValue) throws CoreException {
		return parent.getAttribute(attributeName, defaultValue);
	}

	@Override
	public Map<String, String> getAttribute(String attributeName, Map<String, String> defaultValue) throws CoreException {
		return parent.getAttribute(attributeName, defaultValue);
	}

	@Override
	public String getAttribute(String attributeName, String defaultValue) throws CoreException {
		return parent.getAttribute(attributeName, defaultValue);
	}

	@Override
	public Map<String, Object> getAttributes() throws CoreException {
		return attributes;
	}

	@Override
	public void setAttribute(String attributeName, Set<String> value) {
		attributes.put(attributeName, value);
	}

	@Override
	public String getCategory() throws CoreException {
		return parent.getCategory();
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
		return parent.getMemento();
	}

	@Override
	public Set<String> getModes() throws CoreException {
		return parent.getModes();
	}

	@Override
	public String getName() {
		return parent.getName();
	}

	@Override
	public ILaunchDelegate getPreferredDelegate(Set<String> modes) throws CoreException {
		return parent.getPreferredDelegate(modes);
	}

	@Override
	public ILaunchConfigurationType getType() throws CoreException {
		return parent.getType();
	}

	@Override
	public ILaunchConfigurationWorkingCopy getWorkingCopy() throws CoreException {
		return null;
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
	public ILaunch launch(String mode, IProgressMonitor monitor) throws CoreException {
		return null;
	}

	@Override
	public ILaunch launch(String mode, IProgressMonitor monitor, boolean build) throws CoreException {
		return null;
	}

	@Override
	public ILaunch launch(String mode, IProgressMonitor monitor, boolean build, boolean register) throws CoreException {
		return null;
	}

	@Override
	public void migrate() throws CoreException {
		// do nothing
	}

	@Override
	public boolean supportsMode(String mode) throws CoreException {
		return false;
	}

	@Override
	public <T> T getAdapter(Class<T> adapter) {
		return null;
	}

}