package org.igreench.launcher;

import static org.junit.Assert.assertEquals;

import org.igreench.launcher.ui.LauncherUIUtilities;
import org.junit.Test;

public class LauncherUIUtilitiesTest {
	@SuppressWarnings("deprecation")
	@Test
	public void getDelayName(int value) {
		assertEquals(LauncherUIUtilities.getDelayName(-1), new String[] { "Delay", "-1"});
	}
}
