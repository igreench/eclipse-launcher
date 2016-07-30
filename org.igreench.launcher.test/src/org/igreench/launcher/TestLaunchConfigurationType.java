package org.igreench.launcher;

import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchDelegate;
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate;
import org.eclipse.debug.core.sourcelookup.ISourcePathComputer;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCategory() {
		return null;
	}

	@Override
	public ILaunchConfigurationDelegate getDelegate() throws CoreException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ILaunchConfigurationDelegate getDelegate(String mode) throws CoreException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ILaunchDelegate[] getDelegates(Set<String> modes) throws CoreException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ILaunchDelegate getPreferredDelegate(Set<String> modes) throws CoreException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPreferredDelegate(Set<String> modes, ILaunchDelegate delegate) throws CoreException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean supportsModeCombination(Set<String> modes) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Set<String>> getSupportedModeCombinations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isPublic() {
		return true;
	}

	@Override
	public ILaunchConfigurationWorkingCopy newInstance(IContainer container, String name) throws CoreException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean supportsMode(String mode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getContributorName() {
		return "xored guy"; //$NON-NLS-1$
	}

}
