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

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public abstract class Dialog {

	private static final String BUTTON_OK_TEXT = "Ok"; //$NON-NLS-1$

	private static final String BUTTON_CANCEL_TEXT = "Cancel"; //$NON-NLS-1$

	private Display display;
	private Shell shell;

	private Label label;
	private Button buttonOk;
	private Button buttonCancel;

	public Dialog(Display display, String shellText, String labelText) {
		this.display = display;
		initShell(shellText);
		initHeader(labelText);
		initBody();
		initFooter();
	}

	protected Shell getShell() {
		return shell;
	}

	protected void initShell(String shellText) {
		shell = new Shell(display, SWT.APPLICATION_MODAL | SWT.SHELL_TRIM | SWT.CENTER);
		shell.setText(shellText);
		shell.setLayout(LauncherUIWidgetsUtilities.getTreeDialogGridLayout());
	}

	protected void initHeader(String labelText) {
		label = new Label(shell, SWT.NONE);
		label.setText(labelText);
		label.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER, true, false));
	}

	protected abstract void initBody();

	protected void initFooter() {
		Composite footerComposite = new Composite(shell, SWT.NONE);
		footerComposite.setLayoutData(new GridData(GridData.END, GridData.CENTER, false, false));

		GridLayout footerGridLayout = new GridLayout(2, true);
		footerComposite.setLayout(footerGridLayout);

		buttonOk = new Button(footerComposite, SWT.NONE);
		buttonOk.setText(BUTTON_OK_TEXT);
		buttonOk.setLayoutData(LauncherUIWidgetsUtilities.getButtonGridData());

		buttonCancel = new Button(footerComposite, SWT.NONE);
		buttonCancel.setText(BUTTON_CANCEL_TEXT);
		buttonCancel.setLayoutData(LauncherUIWidgetsUtilities.getButtonGridData());
		buttonCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				close();
			}
		});
	}

	protected void setOkButtonEnabled(boolean enabled) {
		buttonOk.setEnabled(enabled);
	}

	public void addOkListener(SelectionAdapter listener) {
		buttonOk.addSelectionListener(listener);
	}

	public void setSize(int width, int height) {
		shell.setSize(width, height);
	}

	public void setImage(Image image) {
		shell.setImage(image);
	}

	public void setFont(Font font) {
		shell.setFont(font);
	}

	public void setCenterLocation() {
		LauncherUIWidgetsUtilities.setCenterLocation(display, shell);
	}

	public void open() {
		shell.open();
	}

	public void close() {
		shell.close();
	}
}