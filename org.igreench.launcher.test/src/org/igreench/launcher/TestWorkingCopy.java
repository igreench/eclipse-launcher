package org.igreench.launcher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import junit.framework.Assert;

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

@SuppressWarnings({ "rawtypes", "deprecation", "unchecked" })
public class TestWorkingCopy implements ILaunchConfigurationWorkingCopy {

    private TestLaunchConfig parent;

    private Map<String, Object> attributes = new HashMap<String, Object>();

    public TestWorkingCopy(TestLaunchConfig parent) {
        this.parent = parent;
    }
    
    public void update() {        
        parent.update(attributes);
    }

    public ILaunchConfiguration doSave() throws CoreException {        
        parent.update(attributes);
        return parent;
    }

    public void addModes(Set modes) {
    }

    public boolean hasAttribute(String attributeName) throws CoreException {
        Assert.fail(TestMessages.RefactoringTest_method_not_tested);
        return false;
    }

    public Object removeAttribute(String attributeName) {
        Assert.fail(TestMessages.RefactoringTest_method_not_tested);
        return null;
    }

    public ILaunchConfiguration getOriginal() {
        return parent;
    }

    public ILaunchConfigurationWorkingCopy getParent() {
        return null;
    }

    public boolean isDirty() {
        return true;
    }

    public void removeModes(Set modes) {
    }

    public void rename(String name) {
    }

    public void setAttribute(String attributeName, int value) {
    	attributes.put(attributeName, value);
    }

    public void setAttribute(String attributeName, String value) {
        attributes.put(attributeName, value);
    }

    public void setAttribute(String attributeName, List value) {
        attributes.put(attributeName, value);
    }

    public void setAttribute(String attributeName, Map value) {
        attributes.put(attributeName, value);
    }

    public void setAttribute(String attributeName, boolean value) {
        attributes.put(attributeName, value);
    }
    
	public void setAttributes(Map attribs) {
        attributes.putAll(attribs);
    }

    public void setContainer(IContainer container) {
        Assert.fail(TestMessages.RefactoringTest_method_not_tested);
    }

    public void setMappedResources(IResource[] resources) {
        Assert.fail(TestMessages.RefactoringTest_method_not_tested);
    }

    public void setModes(Set modes) {
        Assert.fail(TestMessages.RefactoringTest_method_not_tested);
    }

    public void setPreferredLaunchDelegate(Set modes, String delegateId) {
        Assert.fail(TestMessages.RefactoringTest_method_not_tested);
    }

    public boolean contentsEqual(ILaunchConfiguration configuration) {
        return false;
    }

    public ILaunchConfigurationWorkingCopy copy(String name) throws CoreException {
        Assert.fail(TestMessages.RefactoringTest_method_not_tested);
        return null;
    }

    public void delete() throws CoreException {
    }

    public boolean exists() {
        return false;
    }

    public boolean getAttribute(String attributeName, boolean defaultValue) throws CoreException {
        return parent.getAttribute(attributeName, defaultValue);
    }

    public int getAttribute(String attributeName, int defaultValue) throws CoreException {
    	return parent.getAttribute(attributeName, defaultValue);
    }

    public List getAttribute(String attributeName, List defaultValue) throws CoreException {
        return parent.getAttribute(attributeName, defaultValue);
    }

    public Set getAttribute(String attributeName, Set defaultValue) throws CoreException {
    	return parent.getAttribute(attributeName, defaultValue);
    }

    public Map getAttribute(String attributeName, Map defaultValue) throws CoreException {
        return parent.getAttribute(attributeName, defaultValue);
    }

    public String getAttribute(String attributeName, String defaultValue) throws CoreException {
        return parent.getAttribute(attributeName, defaultValue);
    }

    public Map getAttributes() throws CoreException {
        return attributes;
    }

    public String getCategory() throws CoreException {
        return parent.getCategory();
    }

    public IFile getFile() {
        return null;
    }

    public IPath getLocation() {
        return null;
    }

    public IResource[] getMappedResources() throws CoreException {
        return null;
    }

    public String getMemento() throws CoreException {
        return parent.getMemento();
    }

    public Set getModes() throws CoreException {
        return parent.getModes();
    }

    public String getName() {
        return parent.getName();
    }

    public ILaunchDelegate getPreferredDelegate(Set modes) throws CoreException {
        return parent.getPreferredDelegate(modes);
    }

    public ILaunchConfigurationType getType() throws CoreException {
        return parent.getType();
    }

    public ILaunchConfigurationWorkingCopy getWorkingCopy() throws CoreException {
        return null;
    }

    public boolean isLocal() {
        return false;
    }

    public boolean isMigrationCandidate() throws CoreException {
        return false;
    }

    public boolean isReadOnly() {
        return false;
    }

    public boolean isWorkingCopy() {
        return false;
    }

    public ILaunch launch(String mode, IProgressMonitor monitor) throws CoreException {
        return null;
    }

    public ILaunch launch(String mode, IProgressMonitor monitor, boolean build)
            throws CoreException {
        return null;
    }

    public ILaunch launch(String mode, IProgressMonitor monitor, boolean build, boolean register)
            throws CoreException {
        return null;
    }

    public void migrate() throws CoreException {
    }

    public boolean supportsMode(String mode) throws CoreException {
        return false;
    }

    public Object getAdapter(Class adapter) {
        return null;
    }

    public void setAttribute(String attributeName, Set value) {
        attributes.put(attributeName, value);
    }

}