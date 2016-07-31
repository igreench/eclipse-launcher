package org.igreench.launcher;

import static org.junit.Assert.*;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;

import org.junit.Before;
import org.junit.Test;

import org.igreench.launcher.model.ILauncherAttributeModel;

public class LauncherAttributeUtilitiesTest {

	private ILaunchConfiguration configuration;

	@Before
	public void beforeTest() {
		configuration = new TestLaunchConfig();		
	}

	@Test
	public void getAttributesTest1() {
		try {
			configuration.getWorkingCopy().setAttribute(LauncherAttributes.ATTRIBUTE_ITERATIONS_COUNT, 5);
			ILauncherAttributeModel attributeModel = LauncherAttributeUtilities.getAttributes(configuration);
			assertEquals(5, attributeModel.getIterationsCount());
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getAttributesTest2() {
		try {
			ILauncherAttributeModel attributeModel = LauncherAttributeUtilities.getAttributes(configuration);
			assertEquals(0, attributeModel.getIterationsCount());
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getAttributesTest3() {
		try {
			configuration.getWorkingCopy().setAttribute(LauncherAttributes.ATTRIBUTE_ITERATIONS_COUNT, "other");
			ILauncherAttributeModel attributeModel = LauncherAttributeUtilities.getAttributes(configuration);
			assertEquals(0, attributeModel.getIterationsCount());
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getAttributesTest4() {
		try {
			configuration.getWorkingCopy().setAttribute(LauncherAttributes.ATTRIBUTE_ITERATIONS_COUNT, "5");
			ILauncherAttributeModel attributeModel = LauncherAttributeUtilities.getAttributes(configuration);
			assertEquals(4, attributeModel.getIterationsCount());
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}
}

/*
 * test ui treeutilitest
 * 
 * twoleveltreewidget
 * 
 * launcherwidgetutilities
 */