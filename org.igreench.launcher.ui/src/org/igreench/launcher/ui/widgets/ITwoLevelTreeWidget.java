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

import java.util.List;

import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TreeItem;
import org.igreench.launcher.ui.widgets.dnd.IDNDModel.IDNDListener;

/**
 * Two level tree widget interface.
 * First level - roots.
 * Second level - nodes. 
 * Provides:
 * <ul>
 * <li>access to the adding roots and nodes</li>
 * <li>access to the editing roots and nodes</li>
 * <li>access to the removing roots and nodes</li>
 * <li>access to the clearing widget</li>
 * <li>access to the getting roots and nodes</li>
 * <li>access to the getting selected nodes</li>
 * <li>access to the drag and drop</li>
 * </ul>
 */
public interface ITwoLevelTreeWidget {
	
	public void initWidget();
	
	public void setFont(Font font);
	public void setLayoutData(Object layoutData);
	
	public void addRoot(String rootName);
	public void addRoot(String rootName, Image rootImage) ;
	
	public void addRoot(String[] rootName);
	public void addRoot(String[] rootName, Image rootImage) ;

	public void addNode(int rootIndex, String[] nodeName);
	public void addNode(int rootIndex, String[] nodeName, Image nodeImage);
	
	public void addNode(TreeItem root, String[] node);
	public void addNode(TreeItem root, String[] node, Image nodeImage);
	
	public void addRoot(String rootName, boolean isDraggable, boolean isDroppable);
	public void addRoot(String rootName, Image rootImage, boolean isDraggable, boolean isDroppable);
	
	public void addRoot(String[] rootName, boolean isDraggable, boolean isDroppable);
	public void addRoot(String[] rootName, Image rootImage, boolean isDraggable, boolean isDroppable);
	
	public void addNode(int rootIndex, String[] nodeName, boolean isDraggable, boolean isDroppable);
	public void addNode(int rootIndex, String[] nodeName, Image nodeImage, boolean isDraggable, boolean isDroppable);
	
	public void addNode(TreeItem root, String[] node, boolean isDraggable, boolean isDroppable);
	public void addNode(TreeItem root, String[] node, Image nodeImage, boolean isDraggable, boolean isDroppable);
	
	public void editNode(TreeItem node, String[] nodeName);
	
	public void removeNode(TreeItem node);
	
	public int getRootCount();
	
	public List<List<String>> getRootNames();
	
	public List<String> getNodeName(TreeItem node);	
	public List<List<String>> getNodeNames(int rootIndex);
	
	public List<TreeItem> getSelectedNodes();
	public List<String> getSelectedNodeNames();
	
	public void clear();
	
	public void addSelectionListener(SelectionListener listener);
	
	public void addDragAndDrop();
	
	public void addDNDListener(IDNDListener listener);
	public void removeDNDListener(IDNDListener listener);
	
	public void setRootColumns(String[] columnNames);
}
