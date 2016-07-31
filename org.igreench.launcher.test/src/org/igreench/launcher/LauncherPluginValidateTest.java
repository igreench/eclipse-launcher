package org.igreench.launcher;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;

import org.igreench.launcher.LauncherPlugin.LauncherAttributeModelException;
import org.igreench.launcher.model.ILauncherAttributeModel;
import org.igreench.launcher.model.ILauncherModel;
import org.igreench.launcher.model.LauncherAttributeModel;
import org.junit.Before;
import org.junit.Test;

public class LauncherPluginValidateTest {

	private Exception exception;

	private ILaunchConfiguration configuration1;
	private ILaunchConfiguration configuration2;
	private ILaunchConfiguration configuration3;

	private ILaunchConfigurationType type1;
	private ILaunchConfigurationType type2;

	private ILauncherAttributeModel attributeModel1;

	private ILauncherModel launcherModel;

	@Before
	public void beforeTest() {

		type1 = new TestLaunchConfigurationType("type1");
		configuration1 = new TestLaunchConfiguration("launcher1", type1);

		type2 = new TestLaunchConfigurationType("type2");
		configuration2 = new TestLaunchConfiguration("launch1", type2);

		configuration3 = new TestLaunchConfiguration("launcher1", type1);

		launcherModel = new TestLauncherModel(
				new ILaunchConfiguration[] { configuration1, configuration2, configuration3 });

		attributeModel1 = new LauncherAttributeModel();

		List<String> iterationDelays = new ArrayList<>();
		iterationDelays.add("5000");
		iterationDelays.add("");
		attributeModel1.setIterationDelays(iterationDelays);

		List<String> launchNames = new ArrayList<>();
		launchNames.add("launch1");
		launchNames.add("launch1");
		attributeModel1.setLaunchNames(launchNames);

		List<String> launchModes = new ArrayList<>();
		launchModes.add("mode1");
		launchModes.add("mode2");
		attributeModel1.setLaunchModes(launchModes);

		List<String> launchIterationIndexes = new ArrayList<>();
		launchIterationIndexes.add("1");
		launchIterationIndexes.add("1");
		attributeModel1.setLaunchIterationIndexes(launchIterationIndexes);
	}

	@Test
	public void validateTest() {
		try {

			LauncherAttributeUtilities.setAttributes(configuration1.getWorkingCopy(), attributeModel1);
			((TestLaunchConfigurationWorkingCopy) configuration1.getWorkingCopy()).update();

			LauncherPlugin.validate(configuration1, attributeModel1, launcherModel);

		} catch (Exception e) {
			exception = e;
		}
		assertEquals(null, exception);
	}

	@Test(expected = LauncherPlugin.CycledLauncherAttributeModelException.class)
	public void validateCycleTest1() throws CoreException, LauncherAttributeModelException {

		attributeModel1.setIterationsCount(2);

		List<String> iterationDelays = new ArrayList<>();
		iterationDelays.add("5000");
		iterationDelays.add("");
		attributeModel1.setIterationDelays(iterationDelays);

		List<String> launchNames = new ArrayList<>();
		launchNames.add("launch1");
		launchNames.add("launcher1");
		attributeModel1.setLaunchNames(launchNames);

		List<String> launchModes = new ArrayList<>();
		launchModes.add("mode1");
		launchModes.add("mode1");
		attributeModel1.setLaunchModes(launchModes);

		List<String> launchIterationIndexes = new ArrayList<>();
		launchIterationIndexes.add("1");
		launchIterationIndexes.add("1");
		attributeModel1.setLaunchIterationIndexes(launchIterationIndexes);

		LauncherAttributeUtilities.setAttributes(configuration1.getWorkingCopy(), attributeModel1);
		((TestLaunchConfigurationWorkingCopy) configuration1.getWorkingCopy()).update();

		LauncherPlugin.validate(configuration1, attributeModel1, launcherModel);
	}

	@Test(expected = LauncherPlugin.CycledLauncherAttributeModelException.class)
	public void validateCycleTest2() throws CoreException, LauncherAttributeModelException {

		attributeModel1.setIterationsCount(2);

		List<String> iterationDelays = new ArrayList<>();
		iterationDelays.add("5000");
		iterationDelays.add("");
		attributeModel1.setIterationDelays(iterationDelays);

		List<String> launchNames = new ArrayList<>();
		launchNames.add("launch1");
		launchNames.add("launcher2");
		attributeModel1.setLaunchNames(launchNames);

		List<String> launchModes = new ArrayList<>();
		launchModes.add("mode1");
		launchModes.add("mode1");
		attributeModel1.setLaunchModes(launchModes);

		List<String> launchIterationIndexes = new ArrayList<>();
		launchIterationIndexes.add("1");
		launchIterationIndexes.add("1");
		attributeModel1.setLaunchIterationIndexes(launchIterationIndexes);

		LauncherAttributeUtilities.setAttributes(configuration1.getWorkingCopy(), attributeModel1);
		((TestLaunchConfigurationWorkingCopy) configuration1.getWorkingCopy()).update();

		attributeModel1.setIterationsCount(2);

		iterationDelays = new ArrayList<>();
		iterationDelays.add("5000");
		iterationDelays.add("");
		attributeModel1.setIterationDelays(iterationDelays);

		launchNames = new ArrayList<>();
		launchNames.add("launch1");
		launchNames.add("launcher2");
		attributeModel1.setLaunchNames(launchNames);

		launchModes = new ArrayList<>();
		launchModes.add("mode1");
		launchModes.add("mode1");
		attributeModel1.setLaunchModes(launchModes);

		launchIterationIndexes = new ArrayList<>();
		launchIterationIndexes.add("1");
		launchIterationIndexes.add("1");
		attributeModel1.setLaunchIterationIndexes(launchIterationIndexes);

		ILaunchConfiguration configuration4 = new TestLaunchConfiguration("launcher2", type1);

		launcherModel = new TestLauncherModel(new ILaunchConfiguration[] { configuration4 });

		LauncherAttributeUtilities.setAttributes(configuration4.getWorkingCopy(), attributeModel1);
		((TestLaunchConfigurationWorkingCopy) configuration4.getWorkingCopy()).update();

		LauncherPlugin.validate(configuration1, attributeModel1, launcherModel);
	}

	@Test(expected = LauncherPlugin.DeletedLaunchLauncherAttributeModelException.class)
	public void validateDeletedLaunchTest() throws CoreException, LauncherAttributeModelException {

		LauncherAttributeUtilities.setAttributes(configuration1.getWorkingCopy(), attributeModel1);
		((TestLaunchConfigurationWorkingCopy) configuration1.getWorkingCopy()).update();

		launcherModel = new TestLauncherModel(new ILaunchConfiguration[] { configuration1 });

		LauncherPlugin.validate(configuration1, attributeModel1, launcherModel);
	}
}
