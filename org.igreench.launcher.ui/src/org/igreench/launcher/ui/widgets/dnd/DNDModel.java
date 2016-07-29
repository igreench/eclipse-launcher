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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DNDModel<T> implements IDNDModel<T> {
	
	private List<List<T>> items = new ArrayList<List<T>>();
	
	private T draggingData;

	private Map<T, Boolean> draggingMap = new HashMap<T, Boolean>();
	private Map<T, Boolean> droppingMap = new HashMap<T, Boolean>();
	
	private List<IDNDListener> dndListeners = new ArrayList<IDNDListener>();
	
	@Override
	public void addItem(T item, int level, boolean isDraggable, boolean isDroppable) {
		if (level >= items.size()) {
			for (int i = items.size(); i < level + 1; i++) {
				items.add(new ArrayList<T>());
			}			
		}
		items.get(level).add(item);
		draggingMap.put(item, isDraggable);
		droppingMap.put(item, isDroppable);
	}

	@Override
	public void removeItem(T item) {
		for (List<T> items : items) {
			items.remove(item);
		}
		draggingMap.remove(item);
		droppingMap.remove(item);
	}

	@Override
	public void clear() {
		items.clear();
		draggingMap.clear();
		droppingMap.clear();
	}

	@Override
	public boolean isDraggable(T item) {
		if (!draggingMap.get(item)) {
			return false;
		}
		for (List<T> levelItems : items) {
			if (-1 != levelItems.indexOf(item)) {
				return true;
			}			
		}
		return false;
	}

	@Override
	public boolean isDroppable(T item) {
		if (droppingMap.get(item)) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean isDroppableRoot(T item) {
		if (null == item) {
			return false;
		}
		for (int i = 0; i < items.size(); i++) {
			if (i > 0 && -1 != items.get(i).indexOf(draggingData) && -1 != items.get(i - 1).indexOf(item)) {
				return true;
			}	
		}
		return false;
	}
	
	@Override
	public boolean isDroppableNode(T item) {
		if (null == item) {
			return false;
		}
		for (int i = 0; i < items.size(); i++) {
			if (-1 != items.get(i).indexOf(draggingData) && -1 != items.get(i).indexOf(item)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean isRootItem(T item) {
		if (-1 != items.get(0).indexOf(item)) {
			return true;
		}
		return false;
	}
	
	@Override
	public void setDraggingData(T data) {
		this.draggingData = data;
	}

	@Override
	public T getDraggingData() {
		return draggingData;
	}

	@Override
	public void replace(T oldItem, T newItem) {
		for (List<T> levelItems : items) {
			if (-1 != levelItems.indexOf(oldItem)) {
				levelItems.remove(oldItem);
				levelItems.add(newItem);
				draggingMap.put(newItem, draggingMap.get(oldItem));
				droppingMap.put(newItem, droppingMap.get(oldItem));
				draggingMap.remove(oldItem);
				droppingMap.remove(oldItem);
			}			
		}
	}
	
	@Override
	public void addDNDListener(IDNDListener listener) {
		dndListeners.add(listener);
	}
	
	@Override
	public void removeDNDListener(IDNDListener listener) {
		dndListeners.remove(listener);
	}
	
	@Override
	public void update() {
		for (IDNDListener listener : dndListeners) {
			listener.changed();
		}
	}
	
	@Override
	public void print() {
		System.out.println("items");
		System.out.println(items);
		System.out.println("draggingData");
		System.out.println(draggingData);
		System.out.println("draggingMap");
		System.out.println(draggingMap);
		System.out.println("droppingMap");
		System.out.println(droppingMap);
	}
}
