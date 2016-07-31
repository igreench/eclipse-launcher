package org.igreench.launcher.ui;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.igreench.launcher.LauncherPlugin;
import org.igreench.launcher.LauncherUIPlugin;
import org.igreench.launcher.ui.LauncherUIUtilities;
import org.igreench.launcher.ui.widgets.ITwoLevelTreeWidget;
import org.igreench.launcher.ui.widgets.TwoLevelTreeWidget;
import org.junit.Before;
import org.junit.Test;

/**
 * Testing class LauncherUIUtilities
 *
 */

public class LauncherUIUtilitiesTest {

	private ITwoLevelTreeWidget widget;

	@Before
	public void before() {
		Shell shell = new Shell(Display.getDefault());
		widget = new TwoLevelTreeWidget(shell, 0);
	}

	@Test
	public void updateConfigWidgetTest() throws CoreException {

		widget.setRootColumns(new String[] { LauncherUIStrings.CONFIG_TAB_WIDGET_COLUMN_1,
				LauncherUIStrings.CONFIG_TAB_WIDGET_COLUMN_2 });

		LauncherUIUtilities.updateConfigurationWidget(widget);

		List<String> rootNames = new ArrayList<String>();

		ILaunchConfiguration[] launchConfigurations = LauncherUIPlugin.getDefault().getLauncherModel()
				.getLaunchConfigurations();

		int index = 0;
		for (ILaunchConfiguration launchConfiguration : launchConfigurations) {

			rootNames.add(launchConfiguration.getName());

			List<List<String>> nodeNames = new ArrayList<List<String>>();

			for (String nodeName : launchConfiguration.getAttributes().keySet()) {

				List<String> node = new ArrayList<String>();
				node.add(nodeName);
				node.add(launchConfiguration.getAttributes().get(nodeName).toString());
				nodeNames.add(node);
			}

			assertEquals(nodeNames, widget.getNodeNames(index));
			index++;
		}

		List<String> widgetRootNames = new ArrayList<String>();
		for (List<String> rootName : widget.getRootNames()) {
			rootName.add(rootName.get(0));
		}

		assertEquals(rootNames, widgetRootNames);

	}

	@Test
	public void updateLaunchesWidgetTest() throws CoreException {

		widget.setRootColumns(new String[] { LauncherUIStrings.LAUNCHES_DIALOG_COLUMN_1 });

		LauncherUIUtilities.updateLaunchesWidget(widget);

		List<String> launchTypes = LauncherUIPlugin.getDefault().getLauncherUIModel().getLaunchTypes();
		Set<String> launchNames = LauncherPlugin.getDefault().getLauncherModel().getLaunchNames();

		for (int index = 0; index < launchTypes.size(); index++) {

			List<List<String>> nodeNames = new ArrayList<List<String>>();

			for (String launchName : launchNames) {
				if (index == launchTypes.indexOf(
						LauncherPlugin.getDefault().getLauncherModel().getLaunchConfigurationType(launchName))) {
					List<String> node = new ArrayList<String>();
					node.add(launchName);
					nodeNames.add(node);
				}
			}

			assertEquals(nodeNames, widget.getNodeNames(index));
		}

		List<String> widgetRootNames = new ArrayList<String>();
		for (List<String> rootName : widget.getRootNames()) {
			rootName.add(rootName.get(0));
		}

		assertEquals(launchTypes, widgetRootNames);

	}
}
