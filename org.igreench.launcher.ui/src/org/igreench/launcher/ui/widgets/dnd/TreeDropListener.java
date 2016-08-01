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
package org.igreench.launcher.ui.widgets.dnd;

import java.util.Arrays;

import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.igreench.launcher.ui.widgets.TreeUtilities;

/**
 * Tree drop listener class
 *
 */
public class TreeDropListener extends DropTargetAdapter {

	private Tree tree;
	private IDNDModel<TreeItem> dndAdapter;

	private Display display;

	public TreeDropListener(Tree tree, Display display, IDNDModel<TreeItem> dndAdapter) {
		this.tree = tree;
		this.display = display;
		this.dndAdapter = dndAdapter;
	}

	@Override
	public void dragOver(DropTargetEvent event) {

		event.feedback = DND.FEEDBACK_EXPAND | DND.FEEDBACK_SCROLL;
		if (null != event.item) {
			TreeItem item = (TreeItem) event.item;

			if (tree != item.getParent()) {
				return;
			}

			if (null == item.getParentItem() && dndAdapter.isRootItem(dndAdapter.getDraggingData())) {
				Point pt = display.map(null, tree, event.x, event.y);
				Rectangle bounds = item.getBounds();
				if (pt.y < bounds.y + bounds.height / 2) {
					event.feedback |= DND.FEEDBACK_INSERT_BEFORE;
				} else {
					event.feedback |= DND.FEEDBACK_INSERT_AFTER;
				}		
				return;
			}
			
			if (dndAdapter.isDroppableNode(item)) {
				Point pt = display.map(null, tree, event.x, event.y);
				Rectangle bounds = item.getBounds();
				if (pt.y < bounds.y + bounds.height / 2) {
					event.feedback |= DND.FEEDBACK_INSERT_BEFORE;
				} else {
					event.feedback |= DND.FEEDBACK_INSERT_AFTER;
				}	
				return;
			}		

			if (dndAdapter.isDroppable(item) && dndAdapter.isDroppableRoot(item)) {
				event.feedback |= DND.FEEDBACK_SELECT;
			}
		}
	}

	@Override
	public void drop(DropTargetEvent event) {
		if (event.data == null) {
			event.detail = DND.DROP_NONE;
			return;
		}
		
		TreeItem item = (TreeItem) event.item;
		
		Point pt = display.map(null, tree, event.x, event.y);
		Rectangle bounds = item.getBounds();
		TreeItem parent = item.getParentItem();
		TreeItem draggingNode = dndAdapter.getDraggingData();

		// Is this the root node of the tree?
		if (null == parent && dndAdapter.isRootItem(draggingNode)) {
			TreeItem[] items = tree.getItems();
			int index = Arrays.asList(items).indexOf(item);
			if (pt.y < bounds.y + bounds.height / 2) {
				addItem(tree, SWT.NONE, index, draggingNode);
			} else {
				addItem(tree, SWT.NONE, index + 1, draggingNode);
			}
			dndAdapter.update();
			return;
		}

		// Is this the parent-level node of the dragged node?
		if (dndAdapter.isDroppable(item) && dndAdapter.isDroppableRoot(item)) {
			addItem(item, SWT.NONE, -1, draggingNode);
			dndAdapter.update();
			return;
		}

		if (dndAdapter.isDroppableNode(item)) {			
			TreeItem[] items = parent.getItems();
			int index = Arrays.asList(items).indexOf(item);
			if (pt.y < bounds.y + bounds.height / 2) {
				addItem(parent, SWT.NONE, index, draggingNode);
			} else {
				addItem(parent, SWT.NONE, index + 1, draggingNode);
			}
			dndAdapter.update();
			return;
		}
		
		event.detail = DND.DROP_NONE;

	}

	private void addItem(TreeItem parent, int style, int index, TreeItem node) {
		TreeItem newItem = null;
		if (-1 == index) {
			newItem = new TreeItem(parent, style);			
		} else {
			newItem = new TreeItem(parent, style, index);
		}

		newItem.setText(TreeUtilities.getNodeNameAsArray(node));
		newItem.setImage(node.getImage());
		dndAdapter.replace(node, newItem);

		if (node.getItems().length > 0) {
			for (TreeItem item : node.getItems()) {
				addItem(newItem, SWT.NONE, -1, item);
			}
		}
	}

	private void addItem(Tree tree, int style, int index, TreeItem node) {
		TreeItem newItem = null;
		if (-1 == index) {
			newItem = new TreeItem(tree, style);			
		} else {
			newItem = new TreeItem(tree, style, index);
		}

		newItem.setText(TreeUtilities.getNodeNameAsArray(node));
		newItem.setImage(node.getImage());
		dndAdapter.replace(node, newItem);

		if (node.getItems().length > 0) {
			for (TreeItem item : node.getItems()) {
				addItem(newItem, SWT.NONE, -1, item);
			}
		}
	}
}