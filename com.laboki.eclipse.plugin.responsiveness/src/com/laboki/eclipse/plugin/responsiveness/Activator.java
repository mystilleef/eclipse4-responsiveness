package com.laboki.eclipse.plugin.responsiveness;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class Activator extends AbstractUIPlugin {

	private static Activator plugin;

	public Activator() {}

	@Override
	public void start(final BundleContext context) throws Exception {
		super.start(context);
		Activator.plugin = this;
		Plugin.INSTANCE.begin();
	}

	@Override
	public void stop(final BundleContext context) throws Exception {
		Activator.plugin = null;
		Plugin.INSTANCE.end();
		super.stop(context);
	}

	public static Activator getInstance() {
		return Activator.plugin;
	}
}
