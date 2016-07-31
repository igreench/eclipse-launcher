package org.igreench.launcher;

import static org.junit.Assert.*;

import org.igreench.launcher.ui.LauncherUIUtilities;
import org.junit.Test;

public class LauncherUIUtilitiesTest {

	@Test
	public void getDelayName(int value) {
		assertArrayEquals(new String[] { "Delay", "-1"}, LauncherUIUtilities.getDelayName(-1));
	}
}
