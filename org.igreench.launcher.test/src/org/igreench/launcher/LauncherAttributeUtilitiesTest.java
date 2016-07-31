package org.igreench.launcher;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;

import org.junit.Before;
import org.junit.Test;

import org.igreench.launcher.model.ILauncherAttributeModel;
import org.igreench.launcher.model.LauncherAttributeModel;

public class LauncherAttributeUtilitiesTest {

	private ILaunchConfiguration configuration;
	private ILauncherAttributeModel attributeModel;

	@Before
	public void beforeTest() {
		configuration = new TestLaunchConfiguration();
	}

	@Test
	public void getAttributesIterationsCountTest() throws CoreException {
		attributeModel = LauncherAttributeUtilities.getAttributes(configuration);

		assertEquals(0, attributeModel.getIterationsCount());

		configuration.getWorkingCopy().setAttribute(LauncherAttributes.ATTRIBUTE_ITERATIONS_COUNT, 5);
		((TestLaunchConfigurationWorkingCopy) configuration.getWorkingCopy()).update();
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
		((TestLaunchConfigurationWorkingCopy) configuration.getWorkingCopy()).update();
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
		((TestLaunchConfigurationWorkingCopy) configuration.getWorkingCopy()).update();
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
		((TestLaunchConfigurationWorkingCopy) configuration.getWorkingCopy()).update();
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
		((TestLaunchConfigurationWorkingCopy) configuration.getWorkingCopy()).update();
		attributeModel = LauncherAttributeUtilities.getAttributes(configuration);
		
		List<String> indexes2 = new ArrayList<>();
		indexes2.add("3");
		indexes2.add("1");
		
		assertEquals(indexes2, attributeModel.getLaunchIterationIndexes());

		indexes2.add("1");

		assertNotEquals(indexes2, attributeModel.getLaunchIterationIndexes());
	}

	@Test
	public void setAttributesIterationsCountTest() throws CoreException {
		
		assertEquals(-5, configuration.getAttribute(LauncherAttributes.ATTRIBUTE_ITERATIONS_COUNT, -5));
		
		ILauncherAttributeModel attributeModel = new LauncherAttributeModel();
		attributeModel.setIterationsCount(5);
		
		LauncherAttributeUtilities.setAttributes(configuration.getWorkingCopy(), attributeModel);
		((TestLaunchConfigurationWorkingCopy) configuration.getWorkingCopy()).update();

		assertEquals(5, configuration.getAttribute(LauncherAttributes.ATTRIBUTE_ITERATIONS_COUNT, 0));
		
		attributeModel.setIterationsCount(3);
		
		LauncherAttributeUtilities.setAttributes(configuration.getWorkingCopy(), attributeModel);
		((TestLaunchConfigurationWorkingCopy) configuration.getWorkingCopy()).update();
		
		assertNotEquals(5, configuration.getAttribute(LauncherAttributes.ATTRIBUTE_ITERATIONS_COUNT, 0));
	}

	@Test
	public void setAttributesIterationDelaysTest() throws CoreException {
		
		List<String> iterationDelays = new ArrayList<>();
		iterationDelays.add("50000");
		
		assertEquals(iterationDelays, configuration.getAttribute(LauncherAttributes.ATTRIBUTE_ITERATIONS_DELAY, iterationDelays));
		
		ILauncherAttributeModel attributeModel = new LauncherAttributeModel();
		attributeModel.setIterationDelays(iterationDelays);
		
		LauncherAttributeUtilities.setAttributes(configuration.getWorkingCopy(), attributeModel);
		((TestLaunchConfigurationWorkingCopy) configuration.getWorkingCopy()).update();
		
		List<String> iterationDelays2 = new ArrayList<>();
		iterationDelays2.add("50000");

		assertEquals(iterationDelays2, configuration.getAttribute(LauncherAttributes.ATTRIBUTE_ITERATIONS_DELAY, new ArrayList<>()));
		
		iterationDelays.add("20000");
		attributeModel.setIterationDelays(iterationDelays);
		
		LauncherAttributeUtilities.setAttributes(configuration.getWorkingCopy(), attributeModel);
		((TestLaunchConfigurationWorkingCopy) configuration.getWorkingCopy()).update();
		
		assertNotEquals(iterationDelays2, configuration.getAttribute(LauncherAttributes.ATTRIBUTE_ITERATIONS_DELAY, new ArrayList<>()));
	}

	@Test
	public void setAttributesLaunchNamesTest() throws CoreException {
		
		List<String> launches = new ArrayList<>();
		launches.add("launch1");
		
		assertEquals(launches, configuration.getAttribute(LauncherAttributes.ATTRIBUTE_LAUNCHES_NAME, launches));
		
		ILauncherAttributeModel attributeModel = new LauncherAttributeModel();
		attributeModel.setLaunchNames(launches);
		
		LauncherAttributeUtilities.setAttributes(configuration.getWorkingCopy(), attributeModel);
		((TestLaunchConfigurationWorkingCopy) configuration.getWorkingCopy()).update();
		
		List<String> launches2 = new ArrayList<>();
		launches2.add("launch1");

		assertEquals(launches2, configuration.getAttribute(LauncherAttributes.ATTRIBUTE_LAUNCHES_NAME, new ArrayList<>()));
		
		launches = new ArrayList<>();
		launches.add("launch2");
		attributeModel.setLaunchNames(launches);
		
		LauncherAttributeUtilities.setAttributes(configuration.getWorkingCopy(), attributeModel);
		((TestLaunchConfigurationWorkingCopy) configuration.getWorkingCopy()).update();
		
		assertNotEquals(launches2, configuration.getAttribute(LauncherAttributes.ATTRIBUTE_LAUNCHES_NAME, new ArrayList<>()));
	}

	@Test
	public void setAttributesLaunchModesTest() throws CoreException {
		
		List<String> modes = new ArrayList<>();
		modes.add("mode1");
		
		assertEquals(modes, configuration.getAttribute(LauncherAttributes.ATTRIBUTE_LAUNCHES_MODE, modes));
		
		ILauncherAttributeModel attributeModel = new LauncherAttributeModel();
		attributeModel.setLaunchModes(modes);
		
		LauncherAttributeUtilities.setAttributes(configuration.getWorkingCopy(), attributeModel);
		((TestLaunchConfigurationWorkingCopy) configuration.getWorkingCopy()).update();
		
		List<String> modes2 = new ArrayList<>();
		modes2.add("mode1");

		assertEquals(modes2, configuration.getAttribute(LauncherAttributes.ATTRIBUTE_LAUNCHES_MODE, new ArrayList<>()));
		
		modes = new ArrayList<>();
		modes.add("mode2");
		attributeModel.setLaunchModes(modes);
		
		LauncherAttributeUtilities.setAttributes(configuration.getWorkingCopy(), attributeModel);
		((TestLaunchConfigurationWorkingCopy) configuration.getWorkingCopy()).update();
		
		assertNotEquals(modes2, configuration.getAttribute(LauncherAttributes.ATTRIBUTE_LAUNCHES_MODE, new ArrayList<>()));
	}

	@Test
	public void setAttributesLaunchIterationIndexesTest() throws CoreException {
		
		List<String> indexes = new ArrayList<>();
		indexes.add("4");
		
		assertEquals(indexes, configuration.getAttribute(LauncherAttributes.ATTRIBUTE_LAUNCHES_ITERATION_INDEX, indexes));
		
		ILauncherAttributeModel attributeModel = new LauncherAttributeModel();
		attributeModel.setLaunchIterationIndexes(indexes);
		
		LauncherAttributeUtilities.setAttributes(configuration.getWorkingCopy(), attributeModel);
		((TestLaunchConfigurationWorkingCopy) configuration.getWorkingCopy()).update();
		
		List<String> indexes2 = new ArrayList<>();
		indexes2.add("4");

		assertEquals(indexes2, configuration.getAttribute(LauncherAttributes.ATTRIBUTE_LAUNCHES_ITERATION_INDEX, new ArrayList<>()));
		
		indexes.add("1");
		attributeModel.setLaunchIterationIndexes(indexes);
		
		LauncherAttributeUtilities.setAttributes(configuration.getWorkingCopy(), attributeModel);
		((TestLaunchConfigurationWorkingCopy) configuration.getWorkingCopy()).update();
		
		assertNotEquals(indexes2, configuration.getAttribute(LauncherAttributes.ATTRIBUTE_LAUNCHES_ITERATION_INDEX, new ArrayList<>()));
	}
}

/*
 * test ui treeutilitest
 * 
 * twoleveltreewidget
 * 
 * launcherwidgetutilities
 */