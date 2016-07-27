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

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;

public final class LauncherUIWidgetsUtilities {
	
	public static final int BUTTON_WIDTH_HINT = 90;
	
	public static final int TREE_DIALOG_MARGIN_LEFT = 5;
	public static final int TREE_DIALOG_MARGIN_TOP = 10;
	public static final int TREE_DIALOG_MARGIN_RIGHT = 5;
	public static final int TREE_DIALOG_MARGIN_BOTTOM = 5;
	
	public static void setCenterLocation(Display display, Shell shell) {
		Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shell.getBounds();

		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;

		shell.setLocation(x, y);
	}
	
	public static GridLayout getTreeDialogGridLayout() {
		GridLayout gridLayout = new GridLayout(1, true);
		gridLayout.marginLeft = TREE_DIALOG_MARGIN_LEFT;
		gridLayout.marginTop = TREE_DIALOG_MARGIN_TOP;
		gridLayout.marginRight = TREE_DIALOG_MARGIN_RIGHT;
		gridLayout.marginBottom = TREE_DIALOG_MARGIN_BOTTOM;
		return gridLayout;
	}

	public static GridData getButtonGridData() {
		GridData gridData = new GridData(GridData.FILL, GridData.CENTER, false, false);
		gridData.widthHint = BUTTON_WIDTH_HINT;
		return gridData;
	}
	
	private LauncherUIWidgetsUtilities() {
		// Not instantiable
	}
}
