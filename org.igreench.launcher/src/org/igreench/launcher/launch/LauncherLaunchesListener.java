package org.igreench.launcher.launch;

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchesListener2;

/**
 * Launcher launches listener class. It provides access to event of termination
 * launches of launcher
 * 
 */
class LauncherLaunchesListener implements ILaunchesListener2 {

	private LauncherLaunch commonLaunch;

	private ILaunch currentLaunch;

	private List<ILaunchConfiguration> configurations;

	private List<String> modes;

	private IProgressMonitor monitor;

	private int nextLaunchIndex;

	protected LauncherLaunchesListener(LauncherLaunch commonLaunch, ILaunch currentLaunch,
			List<ILaunchConfiguration> configurations, List<String> modes, int nextLaunchIndex,
			IProgressMonitor monitor) {
		this.commonLaunch = commonLaunch;
		this.currentLaunch = currentLaunch;
		this.configurations = configurations;
		this.modes = modes;
		this.monitor = monitor;
		this.nextLaunchIndex = nextLaunchIndex;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.debug.core.ILaunchesListener#launchesRemoved(org.eclipse.
	 * debug.core.ILaunch[])
	 */
	@Override
	public void launchesRemoved(ILaunch[] launches) {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.debug.core.ILaunchesListener#launchesAdded(org.eclipse.debug.
	 * core.ILaunch[])
	 */
	@Override
	public void launchesAdded(ILaunch[] launches) {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.debug.core.ILaunchesListener#launchesChanged(org.eclipse.
	 * debug.core.ILaunch[])
	 */
	@Override
	public void launchesChanged(ILaunch[] launches) {
		// do nothing
	}

	@Override
	public void launchesTerminated(ILaunch[] launches) {
		if (Arrays.asList(launches).contains(currentLaunch)) {

			DebugPlugin.getDefault().getLaunchManager().removeLaunchListener(this);

			commonLaunch.oneLaunchTerminated();

			if (nextLaunchIndex < configurations.size()) {
				LauncherLaunchUtilities.launchNext(commonLaunch, currentLaunch, configurations, modes, nextLaunchIndex,
						monitor);
			}
		}
	}
}
