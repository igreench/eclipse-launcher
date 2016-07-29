package org.igreench.launcher.launch;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.Launch;
import org.eclipse.debug.core.model.ISourceLocator;
import org.igreench.launcher.JavaUtilities;
import org.igreench.launcher.LauncherAttributeUtilities;
import org.igreench.launcher.model.ILauncherAttributeModel;

/**
 * Launcher launch class. Launcher launch provides:
 * <ul>
 * <li>access to launcherAttributeModel</li>
 * <li>access to events oneLaunchTerminated() and allLaunchesTerminated()</li>
 * </ul>
 */
class LauncherLaunch extends Launch {

	private int launchCount;

	private List<List<String>> launches;

	private List<List<String>> modes;

	private List<ILauncherLaunchListener> listeners = new ArrayList<ILauncherLaunchListener>();

	private ILaunchConfiguration configuration;

	private ILauncherAttributeModel launcherAttributeModel;

	public LauncherLaunch(ILaunchConfiguration configuration, String mode, ISourceLocator locator)
			throws CoreException {
		super(configuration, mode, locator);
		this.configuration = configuration;
		init();
	}

	protected void init() throws CoreException {
		launcherAttributeModel = LauncherAttributeUtilities.getAttributes(configuration);

		launches = new ArrayList<List<String>>();
		modes = new ArrayList<List<String>>();

		launchCount = launcherAttributeModel.getLaunchNames().size();

		for (int i = 0; i < launcherAttributeModel.getIterationsCount(); i++) {
			launches.add(new ArrayList<String>());
			modes.add(new ArrayList<String>());
		}

		for (int i = 0; i < launcherAttributeModel.getLaunchNames().size(); i++) {

			int iterationIndex = JavaUtilities.strToInt(launcherAttributeModel.getLaunchIterationIndexes().get(i), 0);

			launches.get(iterationIndex).add(launcherAttributeModel.getLaunchNames().get(i));
			modes.get(iterationIndex).add(launcherAttributeModel.getLaunchModes().get(i));
		}
	}

	public List<List<String>> getLaunches() {
		return launches;
	}

	public List<List<String>> getModes() {
		return modes;
	}

	public int getIterationsCount() {
		return launcherAttributeModel.getIterationsCount();
	}

	public int getIterationDelay(int index) {
		return JavaUtilities.strToInt(launcherAttributeModel.getIterationDelays().get(index), -1);
	}

	public void addListener(ILauncherLaunchListener listener) {
		listeners.add(listener);
	}

	public void removeListener(ILauncherLaunchListener listener) {
		listeners.remove(listener);
	}

	public void allLaunchesTerminated() {
		for (ILauncherLaunchListener listener : listeners) {
			listener.allLaunchesTerminated();
		}
	}

	public void oneLaunchTerminated() {
		if (0 < launchCount) {
			launchCount--;
		}
		if (0 == launchCount) {
			allLaunchesTerminated();
		}
	}

}
