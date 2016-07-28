package org.igreench.launcher.ui;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LauncherUIUtilitiesTest {
	@SuppressWarnings("deprecation")
	@Test
	public void getDelayName(int value) {
		assertEquals(LauncherUIUtilities.getDelayName(-1), new String[] { "Delay", "-1"});
	}
}
