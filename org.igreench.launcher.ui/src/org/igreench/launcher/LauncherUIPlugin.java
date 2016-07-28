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

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.osgi.framework.BundleContext;

import org.igreench.launcher.LauncherPlugin;
import org.igreench.launcher.ui.LauncherUIUtilities;
import org.igreench.launcher.ui.widgets.ITwoLevelTreeWidget;

/**
 * The activator class controls the plug-in life cycle
 */
public class LauncherUIPlugin extends AbstractLauncherUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.igreench.launcher"; //$NON-NLS-1$

	// The shared instance
	private static volatile LauncherUIPlugin launcherPlugin = null;

	private ILauncherUIModel launcherUIModel;

	/**
	 * The constructor
	 */
	public LauncherUIPlugin() {
		super();
		setDefault(this);
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
	public static LauncherUIPlugin getDefault() {
		LauncherUIPlugin localInstance = launcherPlugin;
		if (localInstance == null) {
			synchronized (LauncherUIPlugin.class) {
				localInstance = launcherPlugin;
				if (localInstance == null) {
					launcherPlugin = localInstance = new LauncherUIPlugin();
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
	private static void setDefault(LauncherUIPlugin plugin) {
		launcherPlugin = plugin;
	}

	/**
	 * Returns the launcher model.
	 *
	 * @return the launcher model
	 * @see LauncherModel
	 */
	public synchronized ILauncherModel getLauncherModel() {
		return LauncherPlugin.getDefault().getLauncherModel();
	}

	/**
	 * Returns the launcher UI model.
	 *
	 * @return the launcher UI model
	 * @see LauncherUIModel
	 */
	public synchronized ILauncherUIModel getLauncherUIModel() {
		if (launcherUIModel == null) {
			launcherUIModel = new LauncherUIModel(LauncherPlugin.getDefault().getLauncherModel());
		}
		return launcherUIModel;
	}

	/*
	 * Images
	 */

	public Image getLauncherImage() {
		return LauncherUIPlugin.getDefault().getImageDescriptor(LauncherUIImages.LAUNCHER_ICON).createImage();
	}

	public Image getConfigImage() {
		return LauncherUIPlugin.getDefault().getImageDescriptor(LauncherUIImages.CONFIG_ICON).createImage();
	}

	public Image getEclipseImage() {
		return LauncherUIPlugin.getDefault().getImageDescriptor(LauncherUIImages.ECLIPSE_ICON).createImage();
	}

	public Image getIterationImage() {
		return LauncherUIPlugin.getDefault().getImageDescriptor(LauncherUIImages.ITERATION_ICON).createImage();
	}

	public Image getDelayImage() {
		return LauncherUIPlugin.getDefault().getImageDescriptor(LauncherUIImages.DELAY_ICON).createImage();
	}

	@Override
	protected void initializeImageRegistry(ImageRegistry reg) {
		super.initializeImageRegistry(reg);

		reg.put(LauncherUIImages.LAUNCHER_ICON, imageDescriptorFromPath("icons/launcher.png")); //$NON-NLS-1$

		reg.put(LauncherUIImages.ECLIPSE_ICON, imageDescriptorFromPath("icons/sample.gif")); //$NON-NLS-1$

		reg.put(LauncherUIImages.ITERATION_ICON, imageDescriptorFromPath("icons/iteration.png")); //$NON-NLS-1$

		reg.put(LauncherUIImages.DELAY_ICON, imageDescriptorFromPath("icons/delay.png")); //$NON-NLS-1$

		reg.put(LauncherUIImages.CONFIG_ICON, imageDescriptorFromPath("icons/config.png")); //$NON-NLS-1$
	}

	public static void getAttributes(ILaunchConfiguration configuration, ITwoLevelTreeWidget launcherWidget) {
		getAttributes(configuration, getDefault().getLauncherUIModel(), launcherWidget);
	}

	public static void setAttributes(ILaunchConfigurationWorkingCopy configuration,
			ITwoLevelTreeWidget launcherWidget) {
		LauncherAttributeUtilities.setAttributes(configuration,
				getLauncherAttributeModel(getDefault().getLauncherUIModel(), launcherWidget));
	}

	public static void getAttributes(ILaunchConfiguration configuration, ILauncherUIModel launcherUIModel,
			ITwoLevelTreeWidget launcherWidget) {
		try {

			ILauncherAttributeModel launcherAttributeModel = LauncherAttributeUtilities.getAttributes(configuration);

			for (int i = 0; i < launcherAttributeModel.getIterationsCount(); i++) {
				int value = JavaUtilities.strToInt(launcherAttributeModel.getIterationDelays().get(i), -1);

				// (-1 == value) -> add iteration;
				// (-1 != value) -> add delay.
				
				if (-1 == value) {
					launcherWidget.addRoot(LauncherUIUtilities.getIterationName(),
							LauncherUIPlugin.getDefault().getIterationImage(), true, true);
					
				} else {
					
					launcherWidget.addRoot(LauncherUIUtilities.getDelayName(value),
							LauncherUIPlugin.getDefault().getDelayImage(), true, false);
				}
			}

			List<String> launchNames = launcherAttributeModel.getLaunchNames();

			for (int i = 0; i < launchNames.size(); i++) {

				Image image = launcherUIModel.getLaunchConfigurationIcon(launchNames.get(i));

				launcherWidget.addNode(
						JavaUtilities.strToInt(launcherAttributeModel.getLaunchIterationIndexes().get(i), 0),
						new String[] { launchNames.get(i), launcherAttributeModel.getLaunchModes().get(i) }, image,
						true, false);
			}
		} catch (CoreException e) {
			LauncherUIPlugin.log(e);
		}
	}

	public static ILauncherAttributeModel getLauncherAttributeModel(ILauncherUIModel launcherUIModel,
			ITwoLevelTreeWidget launcherWidget) {

		ILauncherAttributeModel launcherAttributeModel = new LauncherAttributeModel();

		launcherAttributeModel.setIterationsCount(launcherWidget.getRootCount());

		List<List<String>> iterationNames = launcherWidget.getRootNames();

		for (int i = 0; i < launcherAttributeModel.getIterationsCount(); i++) {
			launcherAttributeModel.addIterationDelay(iterationNames.get(i).get(1));
			for (List<String> launchName : launcherWidget.getNodeNames(i)) {
				launcherAttributeModel.addLaunchName(launchName.get(0));
				launcherAttributeModel.addLaunchMode(launchName.get(1));
				launcherAttributeModel.addLaunchIterationIndex(String.valueOf(i));
			}
		}

		return launcherAttributeModel;
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
