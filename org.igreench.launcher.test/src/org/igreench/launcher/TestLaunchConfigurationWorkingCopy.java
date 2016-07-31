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
	
	private String name;
	
	private ILaunchConfigurationType type;
	
	private Map<String, Boolean> booleanAttributes = new HashMap<>();
	private Map<String, Integer> intAttributes = new HashMap<>();
	private Map<String, String> stringAttributes = new HashMap<>();
	private Map<String, List<String>> listAttributes = new HashMap<>();
	private Map<String, Set<String>> setAttributes = new HashMap<>();
	private Map<String, Map<String, String>> mapAttributes = new HashMap<>();
	
	public TestLaunchConfigurationWorkingCopy(String name, ILaunchConfigurationType type) {
		this.name = name;
		this.type = type;
	}

	@Override
	public boolean contentsEqual(ILaunchConfiguration configuration) {
		// TODO Auto-generated method stub
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
		if (!booleanAttributes.containsKey(attributeName)) {
			return defaultValue;
		}
		return booleanAttributes.get(attributeName);
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
		Map<String, Object> map = new HashMap<>();
		
		for (String attribute : booleanAttributes.keySet()) {
			map.put(attribute, booleanAttributes.get(attribute));
		}
		
		for (String attribute : intAttributes.keySet()) {
			map.put(attribute, intAttributes.get(attribute));
		}
		
		for (String attribute : stringAttributes.keySet()) {
			map.put(attribute, stringAttributes.get(attribute));
		}
		
		for (String attribute : listAttributes.keySet()) {
			map.put(attribute, listAttributes.get(attribute));
		}
		
		for (String attribute : setAttributes.keySet()) {
			map.put(attribute, setAttributes.get(attribute));
		}
		
		for (String attribute : mapAttributes.keySet()) {
			map.put(attribute, mapAttributes.get(attribute));
		}
		
		return map;
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
		return this;
	}

	@Override
	public boolean hasAttribute(String attributeName) throws CoreException {
		return getAttributes().containsKey(attributeName);
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
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean supportsMode(String mode) throws CoreException {
		return type.getSupportedModes().contains(mode);
	}

	@Override
	public boolean isReadOnly() {
		return false;
	}

	@Override
	public <T> T getAdapter(Class<T> adapter) {
		return null;
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public ILaunchConfiguration doSave() throws CoreException {
		return null;
	}

	@Override
	public void setAttribute(String attributeName, int value) {
		intAttributes.put(attributeName, value);		
	}

	@Override
	public void setAttribute(String attributeName, String value) {
		stringAttributes.put(attributeName, value);	
	}

	@Override
	public void setAttribute(String attributeName, List<String> value) {
		listAttributes.put(attributeName, value);	
	}

	@Override
	public void setAttribute(String attributeName, Map<String, String> value) {
		mapAttributes.put(attributeName, value);	
	}

	@Override
	public void setAttribute(String attributeName, Set<String> value) {
		setAttributes.put(attributeName, value);	
	}

	@Override
	public void setAttribute(String attributeName, boolean value) {
		booleanAttributes.put(attributeName, value);	
	}

	@Override
	public ILaunchConfiguration getOriginal() {
		return null;
	}

	@Override
	public void rename(String name) {
		this.name = name;
	}

	@Override
	public void setContainer(IContainer container) {
	}

	@Override
	public void setAttributes(Map<String, ? extends Object> attributes) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void setMappedResources(IResource[] resources) {
	}

	@Override
	public void setModes(Set<String> modes) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void setPreferredLaunchDelegate(Set<String> modes, String delegateId) {
	}

	@Override
	public void addModes(Set<String> modes) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void removeModes(Set<String> modes) {
		// TODO Auto-generated method stub		
	}

	@Override
	public Object removeAttribute(String attributeName) {
		if (booleanAttributes.containsKey(attributeName)) {
			return booleanAttributes.remove(attributeName);
		}
		if (intAttributes.containsKey(attributeName)) {
			return intAttributes.remove(attributeName);
		}
		if (stringAttributes.containsKey(attributeName)) {
			return stringAttributes.remove(attributeName);
		}
		if (listAttributes.containsKey(attributeName)) {
			return listAttributes.remove(attributeName);
		}
		if (setAttributes.containsKey(attributeName)) {
			return setAttributes.remove(attributeName);
		}
		if (mapAttributes.containsKey(attributeName)) {
			return mapAttributes.remove(attributeName);
		}
		return null;
	}

	@Override
	public ILaunchConfigurationWorkingCopy getParent() {
		return null;
	}

}
