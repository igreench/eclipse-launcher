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
import java.util.Arrays;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.igreench.launcher.ui.widgets.dnd.DNDModel;
import org.igreench.launcher.ui.widgets.dnd.IDNDModel;
import org.igreench.launcher.ui.widgets.dnd.TreeDragListener;
import org.igreench.launcher.ui.widgets.dnd.TreeDropListener;
import org.igreench.launcher.ui.widgets.dnd.IDNDModel.IDNDListener;

public class TwoLevelTreeWidget implements ITwoLevelTreeWidget {

	private Tree tree;
	private Composite composite;
	private int style;

	private static final int TREE_COLUMN_SIZE = 200;
	private static final int TREE_ALIGN = SWT.LEFT;

	private boolean isExpanded = true;

	private DragSource dragSource;
	private DropTarget dropTarget;

	private DNDModel<TreeItem> dndAdapter = new DNDModel<TreeItem>();

	public TwoLevelTreeWidget(Composite composite, int style) {
		this.composite = composite;
		this.style = style;
		initWidget();
	}

	@Override
	public void initWidget() {
		tree = new Tree(composite, style);
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		tree.setHeaderVisible(true);

		if ((style | SWT.CHECK) == style) {
			addCheck();
		}

		Transfer[] types = new Transfer[] { TextTransfer.getInstance() };
		int operations = DND.DROP_MOVE | DND.DROP_COPY | DND.DROP_LINK;

		dragSource = new DragSource(tree, operations);
		dragSource.setTransfer(types);

		dropTarget = new DropTarget(tree, operations);
		dropTarget.setTransfer(types);
	}

	public void addSelectionListener(SelectionListener listener) {
		tree.addSelectionListener(listener);
	}

	@Override
	public void setFont(Font font) {
		composite.setFont(font);
	}

	public void setLayout(Layout layout) {
		composite.setLayout(layout);
	}

	public void setLayoutData(Object layoutData) {
		composite.setLayoutData(layoutData);
	}

	@Override
	public void addRoot(String rootName) {
		addRoot(rootName, false, false);
	}

	@Override
	public void addRoot(String rootName, Image rootImage) {
		addRoot(rootName, rootImage, false, false);
	}

	@Override
	public void addRoot(String[] rootName) {
		addRoot(rootName, false, false);
	}

	@Override
	public void addRoot(String[] rootName, Image rootImage) {
		addRoot(rootName, rootImage, false, false);
	}

	@Override
	public void addNode(int rootIndex, String[] nodeName) {
		addNode(rootIndex, nodeName, false, false);
	}

	@Override
	public void addNode(int rootIndex, String[] nodeName, Image nodeImage) {
		addNode(rootIndex, nodeName, nodeImage, false, false);
	}

	@Override
	public void addNode(TreeItem root, String[] node) {
		addNode(root, node, false, false);
	}

	@Override
	public void addNode(TreeItem root, String[] node, Image nodeImage) {
		addNode(root, node, nodeImage, false, false);
	}

	@Override
	public void addRoot(String rootName, boolean isDraggable, boolean isDroppable) {
		addRoot(rootName, null, isDraggable, isDroppable);
	}

	@Override
	public void addRoot(String rootName, Image rootImage, boolean isDraggable, boolean isDroppable) {
		addRoot(new String[] { rootName }, rootImage, isDraggable, isDroppable);
	}

	@Override
	public void addRoot(String[] rootName, boolean isDraggable, boolean isDroppable) {
		addRoot(rootName, null, isDraggable, isDroppable);
	}

	@Override
	public void addRoot(String[] rootName, Image rootImage, boolean isDraggable, boolean isDroppable) {
		TreeItem rootTreeItem = new TreeItem(tree, SWT.NONE);
		rootTreeItem.setText(rootName);
		rootTreeItem.setGrayed(true);
		rootTreeItem.setChecked(false);
		if (null != rootImage) {
			rootTreeItem.setImage(rootImage);
		}
		rootTreeItem.setExpanded(isExpanded);
		dndAdapter.addItem(rootTreeItem, 0, isDraggable, isDroppable);
	}

	@Override
	public void addNode(int rootIndex, String[] node, boolean isDraggable, boolean isDroppable) {
		addNode(rootIndex, node, null, isDraggable, isDroppable);
	}

	@Override
	public void addNode(int rootIndex, String[] node, Image nodeImage, boolean isDraggable, boolean isDroppable) {
		if (0 <= rootIndex && tree.getItemCount() > rootIndex) {
			addNode(tree.getItem(rootIndex), node, nodeImage, isDraggable, isDroppable);
		}
	}

	@Override
	public void addNode(TreeItem root, String[] node, boolean isDraggable, boolean isDroppable) {
		addNode(root, node, null, isDraggable, isDroppable);
	}

	@Override
	public void addNode(TreeItem root, String[] node, Image nodeImage, boolean isDraggable, boolean isDroppable) {
		TreeItem treeItem = new TreeItem(root, SWT.NONE);
		treeItem.setText(node);
		if (null != nodeImage) {
			treeItem.setImage(nodeImage);
		}
		dndAdapter.addItem(treeItem, 1, isDraggable, isDroppable);
	}

	@Override
	public void editNode(TreeItem node, String[] nodeName) {
		node.setText(nodeName);
	}

	@Override
	public void removeNode(TreeItem node) {
		removeDNDItem(node);
		node.dispose();
	}

	@Override
	public void clear() {
		if (0 > tree.getItems().length) {
			return;
		}
		for (TreeItem item : tree.getItems()) {
			item.dispose();
		}
		dndAdapter.clear();
	}

	@Override
	public List<TreeItem> getSelectedNodes() {
		return Arrays.asList(tree.getSelection());
	}

	@Override
	public List<String> getSelectedNodeNames() {
		List<String> names = new ArrayList<String>();
		for (TreeItem ti : tree.getItems()) {
			for (TreeItem node : ti.getItems()) {
				if (node.getChecked()) {
					names.add(node.getText());
				}
			}
		}
		return names;
	}

	public int getRootCount() {
		return tree.getItemCount();
	}

	public List<List<String>> getRootNames() {
		return TreeUtilities.getRootNames(tree);
	}

	public List<String> getNodeName(TreeItem node) {
		return TreeUtilities.getNodeName(node);
	}

	public List<List<String>> getNodeNames(int rootIndex) {
		return TreeUtilities.getNodeNames(tree, rootIndex);
	}

	public void addCheck() {
		tree.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				if (event.detail == SWT.CHECK) {
					TreeItem item = (TreeItem) event.item;
					boolean checked = item.getChecked();
					TreeUtilities.checkItems(item, checked);
					TreeUtilities.checkPath(item.getParentItem(), checked, false);
				}
			}
		});
	}

	public void setRootColumns(String[] columnNames) {
		for (String columnName : columnNames) {
			TreeColumn column = new TreeColumn(tree, TREE_ALIGN);
			column.setText(columnName);
			column.setWidth(TREE_COLUMN_SIZE);
		}
	}

	public void setFocus() {
		tree.setFocus();
	}

	public void setExpanded(boolean isExpanded) {
		this.isExpanded = isExpanded;
	}

	public void addDragAndDrop() {
		addDNDListeners(tree, composite.getDisplay(), dndAdapter);
	}

	protected void addDNDListeners(Tree tree, Display display, IDNDModel<TreeItem> dndAdapter) {
		dragSource.addDragListener(new TreeDragListener(tree, dndAdapter));
		dropTarget.addDropListener(new TreeDropListener(tree, display, dndAdapter));
	}

	protected void removeDNDItem(TreeItem item) {
		dndAdapter.removeItem(item);
	}

	public void addDNDListener(IDNDListener listener) {
		dndAdapter.addDNDListener(listener);
	}

	public void removeDNDListener(IDNDListener listener) {
		dndAdapter.removeDNDListener(listener);
	}
}
