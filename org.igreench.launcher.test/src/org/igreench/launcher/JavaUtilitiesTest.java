/*******************************************************************************
 * Copyright (c) 2016 Lipkin Evgenii.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Lipkin Evgenii
 *******************************************************************************/
package org.igreench.launcher;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Testing class JavaUtilities
 *
 */
public class JavaUtilitiesTest {

	@Test
	public void strToIntTest() {
		assertEquals(42, JavaUtilities.strToInt("42", 0));

		assertEquals(-1, JavaUtilities.strToInt(null, -1));
	}
}
