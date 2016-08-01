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
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;

/**
 * Numeric text dialog class
 *
 */
public class NumericTextDialog extends Dialog {

	private static final int NUMERIC_TEXT_DIALOG_WIDTH = 300;
	private static final int NUMERIC_TEXT_DIALOG_HEIGHT = 140;

	protected Text text;
	protected int value;

	public NumericTextDialog(Display display, String shellText, String labelText) {
		super(display, shellText, labelText);
		setSize(NUMERIC_TEXT_DIALOG_WIDTH, NUMERIC_TEXT_DIALOG_HEIGHT);
		setOkButtonEnabled(false);
	}

	@Override
	protected void initBody() {
		text = new Text(getShell(), SWT.BORDER);
		text.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
		text.addVerifyListener(new VerifyListener() {
			@Override
			public void verifyText(VerifyEvent event) {
				final String oldS = ((Text) event.getSource()).getText();
				final String newS = oldS.substring(0, event.start) + event.text + oldS.substring(event.end);
				
				if (newS.isEmpty()) {
					setOkButtonEnabled(false);
					return;
				}

				try {
					value = Integer.parseInt(newS);
					setOkButtonEnabled(true);
				} catch (final NumberFormatException numberFormatException) {
					event.doit = false;
				}
			}
		});		
	}

	public int getValue() {
		return value;
	}

}
