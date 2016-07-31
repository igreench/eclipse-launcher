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

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.igreench.launcher.launch.LaunchConfigurationListener;
import org.igreench.launcher.model.ILauncherAttributeModel;
import org.igreench.launcher.model.ILauncherModel;
import org.igreench.launcher.model.LauncherModel;
import org.osgi.framework.BundleContext;

/**
 * There is one instance of the launcher plug-in available from
 * <code>Launcher.getDefault()</code>. The launcher plug-in provides:
 * <ul>
 * <li>access to the launcher model</li>
 * <li>validation method</li>
 * <li>validation exceptions</li>
 * <li>status handlers</li>
 * </ul>
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 */
public class LauncherPlugin extends Plugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.igreench.launcher"; //$NON-NLS-1$

	// The shared instance
	private static volatile LauncherPlugin launcherPlugin = null;

	private ILauncherModel launcherModel;

	/**
	 * The constructor <code>LauncherPlugin()</code>
	 */
	public LauncherPlugin() {
		super();
		setDefault(this);
		DebugPlugin.getDefault().getLaunchManager().addLaunchConfigurationListener(new LaunchConfigurationListener());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.
	 * BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.
	 * BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		setDefault(null);
		super.stop(context);
	}

	/**
	 * Returns the singleton instance of the launcher plug-in.
	 *
	 * @return the launcher plug-in
	 */
	public static LauncherPlugin getDefault() {
		LauncherPlugin localInstance = launcherPlugin;
		if (null == localInstance) {
			synchronized (LauncherPlugin.class) {
				localInstance = launcherPlugin;
				if (null == localInstance) {
					launcherPlugin = localInstance = new LauncherPlugin();
				}
			}
		}
		return localInstance;
	}

	/**
	 * Sets the singleton instance of the launcher plug-in.
	 *
	 * @param plugin
	 *            the launcher plug-in, or <code>null</code> when shutting down
	 */
	private static void setDefault(LauncherPlugin plugin) {
		launcherPlugin = plugin;
	}

	/**
	 * Returns the launcher model.
	 *
	 * @return the launcher model
	 * @see LauncherModel
	 */
	public synchronized ILauncherModel getLauncherModel() {
		if (null == launcherModel) {
			launcherModel = new LauncherModel();
		}
		return launcherModel;
	}

	public static class LauncherAttributeModelException extends Exception {

		/**
		 * serialVersionUID
		 */
		private static final long serialVersionUID = -205095149255645770L;
	}

	public static class CycledLauncherAttributeModelException extends LauncherAttributeModelException {

		public CycledLauncherAttributeModelException() {
		}

		/**
		 * serialVersionUID
		 */
		private static final long serialVersionUID = -2995855990107279611L;
	}

	public static class DeletedLaunchLauncherAttributeModelException extends LauncherAttributeModelException {

		/**
		 * serialVersionUID
		 */
		private static final long serialVersionUID = 5438395328507690588L;

	}
	
	private boolean isValid = false;
	
	public boolean isValid() {
		return isValid;
	}

	/**
	 * Validation ILauncherAttributeModel
	 */
	public void validate(ILaunchConfiguration currentLaunchConfiguration,
			ILauncherAttributeModel currentLauncherAttributeModel)
			throws LauncherAttributeModelException, CoreException {

		isValid = true;
		try {
			validate(currentLaunchConfiguration, currentLauncherAttributeModel, getDefault().getLauncherModel());
		} catch (Throwable e) {
			isValid = false;
			throw e;
		}
	}

	protected static Set<String> launcherLaunchNames = new HashSet<String>();

	/**
	 * Validation ILauncherAttributeModel
	 */
	public static void validate(ILaunchConfiguration currentLaunchConfiguration,
			ILauncherAttributeModel currentLauncherAttributeModel, ILauncherModel launcherModel)
			throws LauncherAttributeModelException, CoreException {

		try {
			launcherLaunchNames.add(currentLaunchConfiguration.getName());
			validate2(currentLaunchConfiguration, currentLauncherAttributeModel, getDefault().getLauncherModel());
		} finally {
			launcherLaunchNames.clear();
		}
	}

	/**
	 * Validation ILauncherAttributeModel
	 */
	protected static void validate2(ILaunchConfiguration currentLaunchConfiguration,
			ILauncherAttributeModel currentLauncherAttributeModel, ILauncherModel launcherModel)
			throws LauncherAttributeModelException, CoreException {

		Set<String> launchNames = new HashSet<String>(currentLauncherAttributeModel.getLaunchNames());

		for (String launchName : launchNames) {

			if (launcherLaunchNames.contains(launchName)) {
				throw new CycledLauncherAttributeModelException();
			}

			if (!launcherModel.containsLaunchConfiguration(launchName)) {
				throw new DeletedLaunchLauncherAttributeModelException();
			}

			if (launcherModel.getLaunchConfiguration(launchName).getType().getName()
					.equals(currentLaunchConfiguration.getType().getName())) {

				launcherLaunchNames.add(launchName);

				ILauncherAttributeModel launcherAttributeModel = LauncherAttributeUtilities
						.getAttributes(launcherModel.getLaunchConfiguration(launchName));

				validate(currentLaunchConfiguration, launcherAttributeModel, launcherModel);
			}
		}
	}

	/*
	 * Logging
	 */

	public static void log(Throwable t) {
		log(t.getMessage(), t);
	}

	public static void log(String message, Throwable t) {
		getDefault().getLog().log(createStatus(message, t));
	}

	public static IStatus createStatus(String message) {
		return createStatus(message, null);
	}

	public static IStatus createStatus(Throwable t) {
		return createStatus(t.getMessage(), t);
	}

	public static IStatus createStatus(String message, Throwable t) {
		return new Status(Status.ERROR, PLUGIN_ID, message, t);
	}

}
