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

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.PlatformUI;

import org.igreench.launcher.ILauncherAttributeModel;
import org.igreench.launcher.LauncherPlugin;
import org.igreench.launcher.LauncherPlugin.CycledLauncherAttributeModelException;
import org.igreench.launcher.LauncherPlugin.DeletedLaunchLauncherAttributeModelException;
import org.igreench.launcher.LauncherPlugin.LauncherAttributeModelException;
import org.igreench.launcher.LauncherUIPlugin;
import org.igreench.launcher.ui.widgets.TreeDialog;
import org.igreench.launcher.ui.widgets.ComboDialog;
import org.igreench.launcher.ui.widgets.NumericTextDialog;
import org.igreench.launcher.ui.widgets.TwoLevelTreeWidget;
import org.igreench.launcher.ui.widgets.ITwoLevelTreeWidget;
import org.igreench.launcher.ui.widgets.IDNDModel.IDNDListener;
import org.igreench.launcher.ui.widgets.LauncherUIWidgetsUtilities;

public class LauncherTab extends AbstractLaunchConfigurationTab {

	private ITwoLevelTreeWidget launcherWidget;

	private TreeDialog launchesDialog;
	private NumericTextDialog delayDialog;
	private ComboDialog modeDialog;

	private Button buttonAddLaunches;
	private Button buttonAddIteration;
	private Button buttonAddDelay;
	private Button buttonEdit;
	private Button buttonDelete;

	public LauncherTab() {
		super();
	}

	@Override
	public void createControl(Composite parent) {
		// Create main composite
		Composite mainComposite = new Composite(parent, SWT.NONE);
		setControl(mainComposite);
		PlatformUI.getWorkbench().getHelpSystem().setHelp(getControl(), getHelpContextId());
		mainComposite.setLayout(new GridLayout(2, false));
		mainComposite.setFont(parent.getFont());

		createLabels(mainComposite);
		createLaunchWidget(mainComposite);
		createWidgetButtons(mainComposite);
	}

	protected void createLabels(Composite parent) {
		GridData gridData = new GridData();
		gridData.horizontalSpan = 2;
		Label labelText = new Label(parent, SWT.NONE);
		labelText.setText(LauncherUIStrings.LAUNCHER_TAB_TEXT);
		labelText.setLayoutData(gridData);

		gridData = new GridData();
		gridData.horizontalSpan = 2;
		Label labelHint = new Label(parent, SWT.NONE);
		labelHint.setText(LauncherUIStrings.LAUNCHER_TAB_HINT);
		labelHint.setLayoutData(gridData);
	}

	protected void createLaunchWidget(Composite parent) {
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.BEGINNING;

		launcherWidget = new TwoLevelTreeWidget(parent, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);

		launcherWidget.setFont(parent.getFont());

		launcherWidget.setRootColumns(new String[] { LauncherUIStrings.LAUNCHER_TAB_WIDGET_COLUMN_1,
				LauncherUIStrings.LAUNCHER_TAB_WIDGET_COLUMN_2 });

		launcherWidget.addDragAndDrop();

		launcherWidget.addDNDListener(new IDNDListener() {
			@Override
			public void changed() {
				updateLaunchConfigurationDialog();
			}
		});

		launcherWidget.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				buttonDelete.setEnabled(true);
				List<TreeItem> selection = launcherWidget.getSelectedNodes();
				if (selection.size() > 0) {
					if (null == selection.get(0).getParentItem()) {
						// Dirty
						if (LauncherUIStrings.DELAY_LABEL == selection.get(0).getText()) {
							buttonEdit.setEnabled(true);
						} else {
							buttonEdit.setEnabled(false);
						}
					} else {
						buttonEdit.setEnabled(true);
					}
				}
			}
		});
		launcherWidget.setLayoutData(gridData);
	}

	protected void createWidgetButtons(Composite parent) {
		// Create buttons composite
		Composite buttonComposite = new Composite(parent, SWT.NONE);
		buttonComposite.setLayoutData(new GridData(GridData.FILL, GridData.FILL, false, true));
		buttonComposite.setLayout(new GridLayout(1, false));

		// Create buttons
		buttonAddLaunches = createPushButton(buttonComposite, LauncherUIStrings.BUTTON_ADD_LAUNCHES_TEXT, null);
		buttonAddLaunches.setLayoutData(LauncherUIWidgetsUtilities.getButtonGridData());
		buttonAddLaunches.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				handleAddButtonSelected(parent);
			}
		});

		buttonAddIteration = createPushButton(buttonComposite, LauncherUIStrings.BUTTON_ADD_ITERATION_TEXT, null);
		buttonAddIteration.setLayoutData(LauncherUIWidgetsUtilities.getButtonGridData());
		buttonAddIteration.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				handleAddIterationButtonSelected();
			}
		});

		buttonAddDelay = createPushButton(buttonComposite, LauncherUIStrings.BUTTON_ADD_DELAY_TEXT, null);
		buttonAddDelay.setLayoutData(LauncherUIWidgetsUtilities.getButtonGridData());
		buttonAddDelay.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				handleAddDelayButtonSelected(parent);
			}
		});

		buttonEdit = createPushButton(buttonComposite, LauncherUIStrings.BUTTON_EDIT_TEXT, null);
		buttonEdit.setLayoutData(LauncherUIWidgetsUtilities.getButtonGridData());
		buttonEdit.setEnabled(false);
		buttonEdit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				handleEditButtonSelected(parent);
			}
		});

		buttonDelete = createPushButton(buttonComposite, LauncherUIStrings.BUTTON_DELETE_TEXT, null);
		buttonDelete.setLayoutData(LauncherUIWidgetsUtilities.getButtonGridData());
		buttonDelete.setEnabled(false);
		buttonDelete.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				handleDeleteButtonSelected();
			}
		});
	}

	protected void handleAddButtonSelected(Composite parent) {
		launchesDialog = new TreeDialog(LauncherUIPlugin.getStandardDisplay(),
				LauncherUIStrings.LAUNCHES_DIALOG_SHELL_TEXT, LauncherUIStrings.LAUNCHES_DIALOG_LABEL_TEXT);
		launchesDialog.setImage(LauncherUIPlugin.getDefault().getEclipseImage());
		launchesDialog.setFont(parent.getFont());
		launchesDialog.setCenterLocation();
		launchesDialog.getTreeWidget().setRootColumns(new String[] { LauncherUIStrings.LAUNCHES_DIALOG_COLUMN_1 });
		try {
			LauncherUIUtilities.updateLaunchesWidget(launchesDialog.getTreeWidget());
		} catch (CoreException e) {
			LauncherUIPlugin.log(e);
		}
		launchesDialog.addOkListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				handleAddLaunchesDialogOkButtonSelected();
			}
		});
		launchesDialog.open();
	}

	protected void handleAddIterationButtonSelected() {
		LauncherUIUtilities.addIteration(launcherWidget);
		updateLaunchConfigurationDialog();
	}

	protected void handleAddDelayButtonSelected(Composite parent) {
		delayDialog = new NumericTextDialog(LauncherUIPlugin.getStandardDisplay(),
				LauncherUIStrings.DELAY_DIALOG_SHELL_TEXT, LauncherUIStrings.DELAY_DIALOG_LABEL_TEXT);
		delayDialog.setImage(LauncherUIPlugin.getDefault().getEclipseImage());
		delayDialog.setFont(parent.getFont());
		delayDialog.setCenterLocation();
		delayDialog.addOkListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				LauncherUIUtilities.addDelay(launcherWidget, delayDialog.getValue());				
				delayDialog.close();
				updateLaunchConfigurationDialog();
			}
		});
		delayDialog.open();
	}

	protected void handleEditButtonSelected(Composite parent) {
		List<TreeItem> selection = launcherWidget.getSelectedNodes();
		if (selection.size() > 0) {
			if (null == selection.get(0).getParentItem()) {
				// Dirty code
				if (LauncherUIStrings.DELAY_LABEL != selection.get(0).getText()) {
					return;
				}
				handleEditDelay(selection.get(0), parent);
			} else {
				handleEditLaunchMode(selection.get(0), parent);
			}
		}
	}

	protected void handleEditDelay(TreeItem selection, Composite parent) {
		delayDialog = new NumericTextDialog(LauncherUIPlugin.getStandardDisplay(),
				LauncherUIStrings.DELAY_DIALOG_EDIT_SHELL_TEXT, LauncherUIStrings.DELAY_DIALOG_EDIT_LABEL_TEXT);
		delayDialog.setImage(LauncherUIPlugin.getDefault().getEclipseImage());
		delayDialog.setFont(parent.getFont());
		delayDialog.setCenterLocation();
		delayDialog.addOkListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				launcherWidget.editNode(selection,
						new String[] { selection.getText(), String.valueOf(delayDialog.getValue()) });
				delayDialog.close();
				updateLaunchConfigurationDialog();
			}
		});
		delayDialog.open();
	}

	protected void handleEditLaunchMode(TreeItem selection, Composite parent) {
		modeDialog = new ComboDialog(LauncherUIPlugin.getStandardDisplay(),
				LauncherUIStrings.MODE_DIALOG_EDIT_SHELL_TEXT, LauncherUIStrings.MODE_DIALOG_EDIT_LABEL_TEXT);
		modeDialog.setImage(LauncherUIPlugin.getDefault().getEclipseImage());
		modeDialog.setFont(parent.getFont());
		modeDialog.setCenterLocation();
		String[] items = null;
		try {
			items = LauncherPlugin.getDefault().getLauncherModel().getLaunchModes(selection.getText());
		} catch (CoreException e) {
			LauncherUIPlugin.log(e);
		}
		modeDialog.setItems(items);
		modeDialog.addOkListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				launcherWidget.editNode(selection,
						new String[] { selection.getText(), String.valueOf(modeDialog.getValue()) });
				modeDialog.close();
				updateLaunchConfigurationDialog();
			}
		});
		modeDialog.open();
	}

	protected void handleDeleteButtonSelected() {
		List<TreeItem> selection = launcherWidget.getSelectedNodes();
		if (selection.size() > 0) {
			launcherWidget.removeNode(selection.get(0));
			updateLaunchConfigurationDialog();
		}
		buttonDelete.setEnabled(false);
	}

	private void handleAddLaunchesDialogOkButtonSelected() {
		try {
			LauncherUIUtilities.addLaunches(launcherWidget, launchesDialog.getTreeWidget().getSelectedNodeNames(),
					LauncherPlugin.getDefault().getLauncherModel());
		} catch (CoreException e) {
			LauncherUIPlugin.log(e);
		}
		updateLaunchConfigurationDialog();
		launchesDialog.close();
	}

	@Override
	public void setDefaults(ILaunchConfigurationWorkingCopy configuration) {
		try {
			for (String attribute : configuration.getAttributes().keySet()) {
				configuration.removeAttribute(attribute);
			}
		} catch (CoreException e) {
			LauncherUIPlugin.log(e);
		}
	}

	@Override
	public void initializeFrom(ILaunchConfiguration configuration) {
		buttonDelete.setEnabled(false);
		buttonEdit.setEnabled(false);
		launcherWidget.clear();
		LauncherUIPlugin.getAttributes(configuration, launcherWidget);
	}

	@Override
	public void performApply(ILaunchConfigurationWorkingCopy configuration) {
		LauncherUIPlugin.setAttributes(configuration, launcherWidget);
	}

	@Override
	public boolean isValid(ILaunchConfiguration configuration) {
		ILauncherAttributeModel launcherAttributeModel = LauncherUIPlugin
				.getLauncherAttributeModel(LauncherUIPlugin.getDefault().getLauncherUIModel(), launcherWidget);

		try {
			LauncherPlugin.getDefault().validate(configuration, launcherAttributeModel);
			setErrorMessage(null);
			return true;

		} catch (DeletedLaunchLauncherAttributeModelException e) {
			setErrorMessage(LauncherUIErrors.LAUNCHER_ERROR_MASSAGE_DELETED_LAUNCH);
			return false;

		} catch (CycledLauncherAttributeModelException e) {
			setErrorMessage(LauncherUIErrors.LAUNCHER_ERROR_MASSAGE_CYCLED);
			return false;

		} catch (LauncherAttributeModelException e) {
			LauncherUIPlugin.log(e);
			return false;

		} catch (CoreException e) {
			LauncherUIPlugin.log(e);
			return false;
		}
	}

	@Override
	public String getName() {
		return LauncherUIStrings.LAUNCHER_TAB_NAME;
	}

	@Override
	public Image getImage() {
		return LauncherUIPlugin.getDefault().getLauncherImage();
	}

}