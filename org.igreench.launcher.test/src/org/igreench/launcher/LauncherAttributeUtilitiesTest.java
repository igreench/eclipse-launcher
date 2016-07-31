package org.igreench.launcher;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;

import org.junit.Before;
import org.junit.Test;

import org.igreench.launcher.model.ILauncherAttributeModel;

public class LauncherAttributeUtilitiesTest {

	private ILaunchConfiguration configuration;
	private ILauncherAttributeModel attributeModel;

	@Before
	public void beforeTest() {
		configuration = new TestLaunchConfig();
	}

	@Test
	public void getAttributesIterationsCountTest() throws CoreException {
		attributeModel = LauncherAttributeUtilities.getAttributes(configuration);

		assertEquals(0, attributeModel.getIterationsCount());

		configuration.getWorkingCopy().setAttribute(LauncherAttributes.ATTRIBUTE_ITERATIONS_COUNT, 5);
		((TestWorkingCopy) configuration.getWorkingCopy()).update();
		attributeModel = LauncherAttributeUtilities.getAttributes(configuration);

		assertEquals(5, attributeModel.getIterationsCount());
		
		assertNotEquals(0, attributeModel.getIterationsCount());
	}

	@Test
	public void getAttributesIterationDelaysTest() throws CoreException {
		attributeModel = LauncherAttributeUtilities.getAttributes(configuration);

		assertEquals(new ArrayList<String>(), attributeModel.getIterationDelays());

		List<String> delays = new ArrayList<>();
		delays.add("500");
		delays.add("10000");

		configuration.getWorkingCopy().setAttribute(LauncherAttributes.ATTRIBUTE_ITERATIONS_DELAY, delays);
		((TestWorkingCopy) configuration.getWorkingCopy()).update();
		attributeModel = LauncherAttributeUtilities.getAttributes(configuration);
		
		List<String> delays2 = new ArrayList<>();
		delays2.add("500");
		delays2.add("10000");		

		assertEquals(delays2, attributeModel.getIterationDelays());
		
		delays.add("10000");		

		assertNotEquals(delays2, attributeModel.getIterationDelays());
	}

	@Test
	public void getAttributesLaunchNamesTest() throws CoreException {
		attributeModel = LauncherAttributeUtilities.getAttributes(configuration);

		assertEquals(new ArrayList<String>(), attributeModel.getLaunchNames());

		List<String> launches = new ArrayList<>();
		launches.add("launch 1");
		launches.add("launch 2");

		configuration.getWorkingCopy().setAttribute(LauncherAttributes.ATTRIBUTE_LAUNCHES_NAME, launches);
		((TestWorkingCopy) configuration.getWorkingCopy()).update();
		ILauncherAttributeModel attributeModel = LauncherAttributeUtilities.getAttributes(configuration);
		
		List<String> launches2 = new ArrayList<>();
		launches2.add("launch 1");
		launches2.add("launch 2");

		assertEquals(launches2, attributeModel.getLaunchNames());
		
		launches.add("launch 3");

		assertNotEquals(launches2, attributeModel.getLaunchNames());
	}

	@Test
	public void getAttributesLaunchModesTest() throws CoreException {
		attributeModel = LauncherAttributeUtilities.getAttributes(configuration);

		assertEquals(new ArrayList<String>(), attributeModel.getLaunchModes());

		List<String> modes = new ArrayList<>();
		modes.add("mode 1");
		modes.add("mode 2");

		configuration.getWorkingCopy().setAttribute(LauncherAttributes.ATTRIBUTE_LAUNCHES_MODE, modes);
		((TestWorkingCopy) configuration.getWorkingCopy()).update();
		attributeModel = LauncherAttributeUtilities.getAttributes(configuration);
		
		List<String> modes2 = new ArrayList<>();
		modes2.add("mode 1");
		modes2.add("mode 2");

		assertEquals(modes2, attributeModel.getLaunchModes());
		
		modes2.add("mode 3");
		
		assertNotEquals(modes2, attributeModel.getLaunchModes());
	}

	@Test
	public void getAttributesLaunchIterationIndexesTest() throws CoreException {
		attributeModel = LauncherAttributeUtilities.getAttributes(configuration);

		assertEquals(new ArrayList<String>(), attributeModel.getLaunchIterationIndexes());

		List<String> indexes = new ArrayList<>();
		indexes.add("3");
		indexes.add("1");

		configuration.getWorkingCopy().setAttribute(LauncherAttributes.ATTRIBUTE_LAUNCHES_ITERATION_INDEX, indexes);
		((TestWorkingCopy) configuration.getWorkingCopy()).update();
		attributeModel = LauncherAttributeUtilities.getAttributes(configuration);
		
		List<String> indexes2 = new ArrayList<>();
		indexes2.add("3");
		indexes2.add("1");
		
		assertEquals(indexes2, attributeModel.getLaunchIterationIndexes());

		indexes2.add("1");

		assertNotEquals(indexes2, attributeModel.getLaunchIterationIndexes());
	}
}

/*
 * test ui treeutilitest
 * 
 * twoleveltreewidget
 * 
 * launcherwidgetutilities
 */