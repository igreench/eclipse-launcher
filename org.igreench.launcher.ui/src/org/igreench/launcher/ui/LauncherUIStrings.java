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
package org.igreench.launcher.ui;

import org.eclipse.osgi.util.NLS;

public final class LauncherUIStrings extends NLS { 

	private static final String BUNDLE_NAME = "org.igreench.launcher.ui.LauncherUIStrings"; //$NON-NLS-1$

	public static String LAUNCHER_TAB_NAME;
	public static String CONFIG_TAB_NAME;
	
	public static String ITERATION_LABEL;
	public static String DELAY_LABEL;

	public static String LAUNCHER_TAB_TEXT;
	public static String LAUNCHER_TAB_HINT;
	
	public static String CONFIG_TAB_WIDGET_COLUMN_1;
	public static String CONFIG_TAB_WIDGET_COLUMN_2;
	
	public static String LAUNCHER_TAB_WIDGET_COLUMN_1;
	public static String LAUNCHER_TAB_WIDGET_COLUMN_2;
	
	public static String LAUNCHES_DIALOG_SHELL_TEXT;
	public static String LAUNCHES_DIALOG_LABEL_TEXT;
	public static String LAUNCHES_DIALOG_COLUMN_1;
	
	public static String DELAY_DIALOG_SHELL_TEXT;
	public static String DELAY_DIALOG_LABEL_TEXT;
	
	public static String DELAY_DIALOG_EDIT_SHELL_TEXT;
	public static String DELAY_DIALOG_EDIT_LABEL_TEXT;
	
	public static String MODE_DIALOG_SHELL_TEXT;
	public static String MODE_DIALOG_LABEL_TEXT;
	
	public static String MODE_DIALOG_EDIT_SHELL_TEXT;
	public static String MODE_DIALOG_EDIT_LABEL_TEXT;
	
	public static String BUTTON_ADD_LAUNCHES_TEXT;
	public static String BUTTON_ADD_ITERATION_TEXT;
	public static String BUTTON_ADD_DELAY_TEXT;
	public static String BUTTON_EDIT_TEXT;
	public static String BUTTON_DELETE_TEXT;
	
	static {
		NLS.initializeMessages(BUNDLE_NAME, LauncherUIStrings.class);
	}
	
	private LauncherUIStrings() {
		// Not instantiable
	}
}
