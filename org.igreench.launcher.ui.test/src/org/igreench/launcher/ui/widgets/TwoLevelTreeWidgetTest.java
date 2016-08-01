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
package org.igreench.launcher.ui.widgets;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.junit.Before;
import org.junit.Test;

/**
 * Testing class TwoLevelTreeWidget
 *
 */
public class TwoLevelTreeWidgetTest {

	private ITwoLevelTreeWidget widget;

	@Before
	public void before() {
		Shell shell = new Shell(Display.getDefault());
		widget = new TwoLevelTreeWidget(shell, 0);
	}

	@Test
	public void addRootTest1() {
		widget.addRoot("root1");

		assertEquals("root1", widget.getRootNames().get(0).get(0));

		assertNotEquals("root2", widget.getRootNames().get(0).get(0));
	}

	@Test
	public void addRootTest2() {

		widget.setRootColumns(new String[] { "column1", "column2" });

		widget.addRoot(new String[] { "root1", "value1" });
		widget.addRoot(new String[] { "root2", "value2" });

		assertEquals(Arrays.asList(new String[] { "root1", "value1" }), widget.getRootNames().get(0));

		assertEquals(Arrays.asList(new String[] { "root2", "value2" }), widget.getRootNames().get(1));

		assertNotEquals(Arrays.asList(new String[] { "root2", "value2" }), widget.getRootNames().get(0));

		assertNotEquals(Arrays.asList(new String[] { "root1", "value1" }), widget.getRootNames().get(1));
	}

	@Test
	public void addNodeTest1() {
		widget.addRoot("root1");
		widget.addNode(0, new String[] { "node1" });

		assertEquals(Arrays.asList(new String[] { "node1" }), widget.getNodeNames(0).get(0));

		assertNotEquals(Arrays.asList(new String[] { "node2" }), widget.getNodeNames(0).get(0));
	}

	@Test
	public void addNodeTest2() {

		widget.setRootColumns(new String[] { "column1", "column2" });

		widget.addRoot(new String[] { "root1", "value1" });
		widget.addNode(0, new String[] { "node1", "value2" });
		widget.addNode(0, new String[] { "node2" });
		widget.addNode(0, new String[] { "node2", "value", "value" });

		assertEquals(Arrays.asList(new String[] { "node1", "value2" }), widget.getNodeNames(0).get(0));

		assertNotEquals(Arrays.asList(new String[] { "node2", "value2" }), widget.getNodeNames(0).get(0));
	}

	@Test
	public void clearTest() {
		widget.addRoot(new String[] { "root1", "value1" });
		widget.addNode(0, new String[] { "node1", "value2" });
		widget.addNode(0, new String[] { "node2" });
		widget.addNode(0, new String[] { "node2", "value", "value" });
		widget.clear();

		assertEquals(new ArrayList<String>(), widget.getNodeNames(0));
	}
}
