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

public interface IDNDModel<T> {
	
	public void addItem(T item, int level, boolean isDraggable, boolean isDroppable);
	public void removeItem(T item);

	public void clear();

	public boolean isDraggable(T item);
	
	public boolean isDroppable(T item);
	
	public boolean isDroppableRoot(T item);
	public boolean isDroppableNode(T item);
	
	public boolean isRootItem(T item);
	
	public void setDraggingData(T data);
	public T getDraggingData();
	
	public void replace(T oldItem, T newItem);
	
	public interface IDNDListener {
		public void changed();
	}
	
	public void addDNDListener(IDNDListener listener);
	public void removeDNDListener(IDNDListener listener);
	
	public void update();
	
	public void print();
}
