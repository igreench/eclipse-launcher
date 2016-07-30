package org.igreench.launcher;

import static org.junit.Assert.*;

import org.eclipse.debug.core.ILaunchConfiguration;
import org.junit.Before;
import org.junit.Test;

public class LauncherAttributeUtilitiesTest {

	@Before
	public void beforeTest() {
		ILaunchConfiguration configuration = new TestLaunchConfiguration("lc1", new TestLaunchConfigurationType("lct1"));
		//LauncherAttributeUtilities.getAttributes(ILaunchConfiguration configuration) throws CoreException
	}

	@Test
	public void strToInt() {
		assertEquals(42, JavaUtilities.strToInt("42", 0));
	}
}


/* test ui
treeutilitest

twoleveltreewidget

launcherwidgetutilities
*/