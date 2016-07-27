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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import org.igreench.launcher.LauncherUIPlugin;
import org.igreench.launcher.ui.widgets.TwoLevelTreeWidget;

public class ConfigTab extends AbstractLaunchConfigurationTab {

	private TwoLevelTreeWidget configWidget;

	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.BORDER);
		setControl(composite);

		GridLayout gridLayout = new GridLayout(1, false);
		composite.setLayout(gridLayout);

		Label label = new Label(composite, SWT.NONE);
		label.setText("Launches attributes:");

		configWidget = new TwoLevelTreeWidget(composite, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		configWidget.setRootColumns(new String[] { LauncherUIStrings.CONFIG_TAB_WIDGET_COLUMN_1,
				LauncherUIStrings.CONFIG_TAB_WIDGET_COLUMN_2 });
		configWidget.setExpanded(false);

		try {
			LauncherUIUtilities.updateConfigurationWidget(configWidget);
		} catch (CoreException e) {
			LauncherUIPlugin.log(e);
		}
	}

	@Override
	public void setDefaults(ILaunchConfigurationWorkingCopy configuration) {
	}

	@Override
	public void initializeFrom(ILaunchConfiguration configuration) {
	}

	@Override
	public void performApply(ILaunchConfigurationWorkingCopy configuration) {
	}

	@Override
	public String getName() {
		return LauncherUIStrings.CONFIG_TAB_NAME;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#getImage()
	 */
	@Override
	public Image getImage() {
		return LauncherUIPlugin.getDefault().getConfigImage();
	}

}