package org.igreench.launcher;

import static org.junit.Assert.*;

import org.junit.Test;

public class JavaUtilitiesTest {
	
	@Test
	public void strToInt() {
		assertEquals(-1, JavaUtilities.strToInt(null, -1));
	}
}
