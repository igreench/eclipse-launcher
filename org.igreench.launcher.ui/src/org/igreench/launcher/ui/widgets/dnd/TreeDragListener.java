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

import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

/**
 * Tree drag listener class
 *
 */
public class TreeDragListener implements DragSourceListener {
	
	private Tree tree;

	private TreeItem[] dragSourceItem = new TreeItem[1];

	private IDNDModel<TreeItem> dndAdapter;

	public TreeDragListener(Tree tree, IDNDModel<TreeItem> dndAdapter) {
		this.tree = tree;
		this.dndAdapter = dndAdapter;
	}

	@Override
	public void dragStart(DragSourceEvent event) {
		TreeItem[] selection = tree.getSelection();
		if (selection.length > 0 && dndAdapter.isDraggable(selection[0])) {
			event.doit = true;
			dragSourceItem[0] = selection[0];
			dndAdapter.setDraggingData(selection[0]);
		} else { 
			event.doit = false;
		}
	};

	@Override
	public void dragSetData(DragSourceEvent event) {
		event.data = dragSourceItem[0].getText();
		event.image = dragSourceItem[0].getImage();
	}

	@Override
	public void dragFinished(DragSourceEvent event) {
		if (event.detail == DND.DROP_MOVE) {
			dragSourceItem[0].dispose();
		}
		dragSourceItem[0] = null;
	}
}
