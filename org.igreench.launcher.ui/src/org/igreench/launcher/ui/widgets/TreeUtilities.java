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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

/**
 * Tree utilities class.
 *
 */
public final class TreeUtilities {

	public static List<List<String>> getRootNames(Tree tree) {
		List<List<String>> nodeNames = new ArrayList<List<String>>();
		for (TreeItem node : tree.getItems()) {
			nodeNames.add(getNodeName(node));
		}
		return nodeNames;
	}
	
	public static List<String> getNodeName(TreeItem node) {
		List<String> nodeName = new ArrayList<String>();
		if (0 == node.getParent().getColumnCount()) {
			nodeName.add(node.getText());
			return nodeName;
		}
		for (int i = 0; i < node.getParent().getColumnCount(); i++) {
			nodeName.add(node.getText(i));
		}
		return nodeName;
	}
	
	public static String[] getNodeNameAsArray(TreeItem node) {
		return TreeUtilities.getNodeName(node).stream().toArray(String[]::new);
		// For Java without lambda
		// return TreeUtilities.getNodeName(node).toArray(new String[node.getParent().getColumnCount()]);
	}

	public static List<List<String>> getNodeNames(Tree tree, int rootIndex) {
		List<List<String>> nodeNames = new ArrayList<List<String>>();
		if (rootIndex < tree.getItemCount()) {
			if (tree.getItem(rootIndex) != null) {
				for (TreeItem node : tree.getItem(rootIndex).getItems()) {
					nodeNames.add(getNodeName(node));
				}
			}
		}
		return nodeNames;
	}

	public static void checkPath(TreeItem item, boolean checked, boolean grayed) {
		if (item == null)
			return;
		if (grayed) {
			checked = true;
		} else {
			int index = 0;
			TreeItem[] items = item.getItems();
			while (index < items.length) {
				TreeItem child = items[index];
				if (child.getGrayed() || checked != child.getChecked()) {
					checked = grayed = true;
					break;
				}
				index++;
			}
		}
		item.setChecked(checked);
		item.setGrayed(grayed);
		checkPath(item.getParentItem(), checked, grayed);
	}

	public static void checkItems(TreeItem item, boolean checked) {
		item.setGrayed(false);
		item.setChecked(checked);
		TreeItem[] items = item.getItems();
		for (int i = 0; i < items.length; i++) {
			checkItems(items[i], checked);
		}
	}

	private TreeUtilities() {
		// Not instantiable
	}
}
