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
package org.igreench.launcher;

/**
 * Launcher attributes class of constants
 */
public final class LauncherAttributes {

	public static final String ATTRIBUTE_LAUNCHES_NAME = LauncherPlugin.PLUGIN_ID + ".launches.name"; //$NON-NLS-1$
	public static final String ATTRIBUTE_LAUNCHES_MODE = LauncherPlugin.PLUGIN_ID + ".launches.mode"; //$NON-NLS-1$
	public static final String ATTRIBUTE_LAUNCHES_ITERATION_INDEX = LauncherPlugin.PLUGIN_ID + ".launches.iteration.id"; //$NON-NLS-1$

	public static final String ATTRIBUTE_ITERATIONS_DELAY = LauncherPlugin.PLUGIN_ID + ".iterations.delay"; //$NON-NLS-1$
	public static final String ATTRIBUTE_ITERATIONS_COUNT = LauncherPlugin.PLUGIN_ID + ".iterations.count"; //$NON-NLS-1$

	private LauncherAttributes() {
		// Not instantiable
	}
}
