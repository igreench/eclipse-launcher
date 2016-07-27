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
import org.eclipse.swt.widgets.Display;

public class TreeDialog extends Dialog {

	private static final int TREE_DIALOG_WIDTH = 600;
	private static final int TREE_DIALOG_HEIGHT = 400;

	protected TwoLevelTreeWidget treeWidget;

	public TreeDialog(Display display, String shellText, String labelText) {
		super(display, shellText, labelText);
		setSize(TREE_DIALOG_WIDTH, TREE_DIALOG_HEIGHT);
	}

	@Override
	protected void initBody() {
		treeWidget = new TwoLevelTreeWidget(getShell(), SWT.CHECK | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);		
		treeWidget.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));
		treeWidget.addSelectionListener(new SelectionAdapter() {
			@Override			
			public void widgetSelected(SelectionEvent event) {
				if (treeWidget.getSelectedNodeNames().isEmpty()) {
					setOkButtonEnabled(false);
				} else {
					setOkButtonEnabled(true);
				}
			}
		});
	}

	public TwoLevelTreeWidget getTreeWidget() {
		return treeWidget;
	}
}
