package org.igreench.launcher;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;

import org.igreench.launcher.LauncherPlugin.LauncherAttributeModelException;
import org.igreench.launcher.model.ILauncherAttributeModel;
import org.igreench.launcher.model.ILauncherModel;
import org.junit.Test;

public class LauncherPluginValidateTest {

	private Exception exception;

	@Test
	public void validateTest() {
		try {
			ILaunchConfigurationType type1 = new TestLaunchConfigurationType("type1");
			ILaunchConfiguration configuration1 = new TestLaunchConfig("configuration1", type1);

			ILaunchConfigurationType type2 = new TestLaunchConfigurationType("type2");
			ILaunchConfiguration configuration2 = new TestLaunchConfig("configuration2", type2);

			ILauncherAttributeModel attributeModel1 = LauncherAttributeUtilities.getAttributes(configuration1);
			ILauncherModel launcherModel = new TestLauncherModel(new ILaunchConfiguration[] { configuration2 });
			
			LauncherPlugin.validate(configuration1, attributeModel1, launcherModel);
			
		} catch (Exception e) {
			exception = e;
		}
		assertEquals(null, exception);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void empty() {
		new ArrayList<Object>().get(0);
	}

	@Test(expected = LauncherPlugin.CycledLauncherAttributeModelException.class)
	public void cycleTest() throws CoreException, LauncherAttributeModelException {
		ILaunchConfigurationType type1 = new TestLaunchConfigurationType("type1");
		ILaunchConfiguration configuration1 = new TestLaunchConfig("configuration1", type1);

		ILaunchConfigurationType type2 = new TestLaunchConfigurationType("type2");
		ILaunchConfiguration configuration2 = new TestLaunchConfig("configuration2", type2);

		ILauncherAttributeModel attributeModel1 = LauncherAttributeUtilities.getAttributes(configuration1);
		ILauncherModel launcherModel = new TestLauncherModel(new ILaunchConfiguration[] { configuration2, configuration1 });
		
		LauncherPlugin.validate(configuration1, attributeModel1, launcherModel);
	}

	@Test(expected = LauncherPlugin.DeletedLaunchLauncherAttributeModelException.class)
	public void deletedLaunchTest() throws CoreException, LauncherAttributeModelException {
		ILaunchConfigurationType type1 = new TestLaunchConfigurationType("type1");
		ILaunchConfiguration configuration1 = new TestLaunchConfig("configuration1", type1);

		ILaunchConfigurationType type2 = new TestLaunchConfigurationType("type2");
		ILaunchConfiguration configuration2 = new TestLaunchConfig("configuration2", type2);

		ILauncherAttributeModel attributeModel1 = LauncherAttributeUtilities.getAttributes(configuration1);
		ILauncherModel launcherModel = new TestLauncherModel(new ILaunchConfiguration[] { configuration2 });
		
		LauncherPlugin.validate(configuration1, attributeModel1, launcherModel);
	}
}
