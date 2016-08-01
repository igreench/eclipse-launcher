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

/**
 * Launcher ui errors class of natural language strings.
 *
 */
public final class LauncherUIErrors extends NLS { 
	
	private static final String BUNDLE_NAME = "org.igreench.launcher.ui.LauncherUIErrors"; //$NON-NLS-1$

	public static String LAUNCHER_ERROR_MASSAGE_CYCLED;
	public static String LAUNCHER_ERROR_MASSAGE_DELETED_LAUNCH;
	
	static {
		NLS.initializeMessages(BUNDLE_NAME, LauncherUIErrors.class);
	}
	
	private LauncherUIErrors() {
		// Not instantiable
	}
}
