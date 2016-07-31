package org.igreench.launcher;

import static org.junit.Assert.*;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;

import org.igreench.launcher.LauncherPlugin.LauncherAttributeModelException;
import org.igreench.launcher.model.ILauncherAttributeModel;
import org.igreench.launcher.model.ILauncherModel;
import org.igreench.launcher.model.LauncherAttributeModel;
import org.junit.Test;

public class LauncherPluginTest {
	
	@Test
	public void validateTest() {
		ILaunchConfigurationType type1 = new TestLaunchConfigurationType("type1");
		ILaunchConfiguration configuration1 = new TestLaunchConfig("configuration1", type1);
		ILauncherAttributeModel currentLauncherAttributeModel = new LauncherAttributeModel();
		// TODO
		ILauncherModel launcherModel = new TestLauncherModel(new ILaunchConfiguration[]{ configuration1 }); 
		// TODO
		try {
			LauncherPlugin.validate(configuration1, currentLauncherAttributeModel, launcherModel);
		} catch (LauncherAttributeModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
