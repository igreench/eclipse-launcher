package org.igreench.launcher;

import static org.junit.Assert.*;

import org.junit.Test;

public class JavaUtilitiesTest {
	
	@Test
	public void strToIntTest1() {
		assertEquals(42, JavaUtilities.strToInt("42", 0));
	}
	
	@Test
	public void strToIntTest2() {
		assertEquals(-1, JavaUtilities.strToInt(null, -1));
	}
}
