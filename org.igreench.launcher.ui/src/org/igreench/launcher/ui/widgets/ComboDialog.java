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
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;

/**
 * Combo dialog class
 *
 */
public class ComboDialog extends Dialog {

	private static final int COMBO_DIALOG_WIDTH = 300;
	private static final int COMBO_DIALOG_HEIGHT = 140;

	protected Combo combo;
	protected String value;

	public ComboDialog(Display display, String shellText, String labelText) {
		super(display, shellText, labelText);
		setSize(COMBO_DIALOG_WIDTH, COMBO_DIALOG_HEIGHT);
		setOkButtonEnabled(false);
	}

	@Override
	protected void initBody() {
		combo = new Combo(getShell(), SWT.READ_ONLY);
		combo.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
		combo.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				setOkButtonEnabled(true);
				value = combo.getText();
			}
		});
	}

	public void setItems(String... items) {
		combo.setItems(items);
	}

	public String getValue() {
		return value;
	}

}
