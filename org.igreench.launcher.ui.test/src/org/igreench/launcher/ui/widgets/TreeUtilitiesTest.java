package org.igreench.launcher.ui.widgets;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.junit.Before;
import org.junit.Test;

public class TreeUtilitiesTest {

	private Tree tree;

	@Before
	public void before() {
		Shell shell = new Shell(Display.getDefault());
		tree = new Tree(shell, 0);
	}

	@Test
	public void getNodeNameTest1() {
		TreeItem rootItem = new TreeItem(tree, 0);
		rootItem.setText("root1");

		TreeItem nodeItem = new TreeItem(rootItem, 0);
		nodeItem.setText("node1");

		assertEquals("node1", TreeUtilities.getNodeName(nodeItem).get(0));

		assertNotEquals("node2", TreeUtilities.getNodeName(nodeItem).get(0));
	}

	@SuppressWarnings("unused")
	@Test
	public void getNodeNameTest2() {
		
		TreeColumn column = new TreeColumn(tree, 0);
		column = new TreeColumn(tree, 0);
		
		TreeItem rootItem = new TreeItem(tree, 0);
		rootItem.setText(new String[] { "root1", "value1" });

		TreeItem nodeItem = new TreeItem(rootItem, 0);
		nodeItem.setText(new String[] { "node1", "value2" });

		assertEquals(Arrays.asList(new String[] { "node1", "value2" }), TreeUtilities.getNodeName(nodeItem));
		
		assertNotEquals(Arrays.asList(new String[] { "node1", "value1" }), TreeUtilities.getNodeName(nodeItem));
	}

	@Test
	public void getNodeNamesTest1() {
		
		TreeItem rootItem = new TreeItem(tree, 0);
		rootItem.setText("root1");

		TreeItem nodeItem = new TreeItem(rootItem, 0);
		nodeItem.setText("node1");
		
		assertEquals(Arrays.asList(new String[] { "node1" }), TreeUtilities.getNodeNames(tree, 0).get(0));
		
		assertNotEquals(Arrays.asList(new String[] { "root1" }), TreeUtilities.getNodeNames(tree, 0).get(0));
	}

	@SuppressWarnings("unused")
	@Test
	public void getNodeNamesTest2() {
		
		TreeColumn column = new TreeColumn(tree, 0);
		column = new TreeColumn(tree, 0);
		
		TreeItem rootItem = new TreeItem(tree, 0);
		rootItem.setText(new String[] { "root1", "value1" });

		TreeItem nodeItem = new TreeItem(rootItem, 0);
		nodeItem.setText(new String[] { "node1", "value2" });
		
		assertEquals(Arrays.asList(new String[] { "node1", "value2" }), TreeUtilities.getNodeNames(tree, 0).get(0));
		
		assertNotEquals(Arrays.asList(new String[] { "root1", "value1" }), TreeUtilities.getNodeNames(tree, 0).get(0));
	}

	@Test
	public void getRootNamesTest1() {

		TreeItem rootItem = new TreeItem(tree, 0);
		rootItem.setText("root1");

		rootItem = new TreeItem(tree, 0);
		rootItem.setText("root2");

		rootItem = new TreeItem(tree, 0);
		rootItem.setText("root3");

		rootItem = new TreeItem(tree, 0);
		rootItem.setText("root4");

		List<List<String>> roots = new ArrayList<List<String>>();
		List<String> root = new ArrayList<String>();
		root.add("root1");
		roots.add(root);
		root = new ArrayList<String>();
		root.add("root2");
		roots.add(root);
		root = new ArrayList<String>();
		root.add("root3");
		roots.add(root);
		root = new ArrayList<String>();
		root.add("root4");
		roots.add(root);

		assertEquals(roots, TreeUtilities.getRootNames(tree));

		root = new ArrayList<String>();
		root.add("root1");
		roots.add(root);

		assertNotEquals(roots, TreeUtilities.getRootNames(tree));
	}

	@SuppressWarnings("unused")
	@Test
	public void getRootNamesTest2() {

		TreeColumn column = new TreeColumn(tree, 0);
		column = new TreeColumn(tree, 0);

		TreeItem rootItem = new TreeItem(tree, 0);
		rootItem.setText(new String[] { "root1", "value1" });

		rootItem = new TreeItem(tree, 0);
		rootItem.setText(new String[] { "root2", "value2" });

		rootItem = new TreeItem(tree, 0);
		rootItem.setText(new String[] { "root3", "value3" });

		rootItem = new TreeItem(tree, 0);
		rootItem.setText(new String[] { "root4", "value4" });

		List<List<String>> roots = new ArrayList<List<String>>();
		List<String> root = new ArrayList<String>();
		root.add("root1");
		root.add("value1");
		roots.add(root);
		root = new ArrayList<String>();
		root.add("root2");
		root.add("value2");
		roots.add(root);
		root = new ArrayList<String>();
		root.add("root3");
		root.add("value3");
		roots.add(root);
		root = new ArrayList<String>();
		root.add("root4");
		root.add("value4");
		roots.add(root);

		assertEquals(roots, TreeUtilities.getRootNames(tree));

		root = new ArrayList<String>();
		root.add("root1");
		root.add("value1");
		roots.add(root);

		assertNotEquals(roots, TreeUtilities.getRootNames(tree));
	}
}
