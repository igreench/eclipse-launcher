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

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * Launcher ui plug-in abstract class <It provides:
 * <ul>
 * <li>access to image and image descriptor</li>
 * <li>access to standard display</li>
 * <li>access to plug-in id</li>
 * </ul>
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 */
public class AbstractLauncherUIPlugin extends AbstractUIPlugin {

	private boolean imageRegistryCreated = false;

	public Image getImage(String imageId) {
		return getImageRegistry().get(imageId);
	}

	public ImageDescriptor getImageDescriptor(String imageId) {
		return getImageRegistry().getDescriptor(imageId);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		if (imageRegistryCreated) {
			getImageRegistry().dispose();
		}

		super.stop(context);
	}

	protected ImageDescriptor imageDescriptorFromPath(String imageFilePath) {
		return imageDescriptorFromPlugin(getPluginID(), imageFilePath);
	}

	/**
	 * Subclasses that override this method must call
	 * super.initializeImageRegistry as well.
	 */
	@Override
	protected void initializeImageRegistry(ImageRegistry reg) {
		imageRegistryCreated = true;
	}

	private String getPluginID() {
		return getBundle().getSymbolicName();
	}

	/**
	 * Returns the standard display to be used. The method first checks, if the
	 * thread calling this method has an associated display. If so, this display
	 * is returned. Otherwise the method returns the default display.
	 */
	public static Display getStandardDisplay() {
		Display display = Display.getCurrent();
		if (null == display) {
			display = Display.getDefault();
		}
		return display;
	}

}
