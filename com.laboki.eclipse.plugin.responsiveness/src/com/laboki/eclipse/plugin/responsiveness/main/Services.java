package com.laboki.eclipse.plugin.responsiveness.main;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.laboki.eclipse.plugin.responsiveness.instance.Instance;
import com.laboki.eclipse.plugin.responsiveness.listeners.AnnotationsListener;
import com.laboki.eclipse.plugin.responsiveness.listeners.KeyEventListener;
import com.laboki.eclipse.plugin.responsiveness.listeners.VerifyEventListener;

public final class Services implements Instance {

	private final List<Instance> instances = Lists.newArrayList();
	private final EventBus eventBus = new EventBus();

	@Override
	public Instance begin() {
		this.startServices();
		return this;
	}

	private void startServices() {
		this.startService(new VerifyEventListener(this.eventBus));
		this.startService(new AnnotationsListener(this.eventBus));
		this.startService(new KeyEventListener(this.eventBus));
	}

	private void startService(final Instance instance) {
		instance.begin();
		this.instances.add(instance);
	}

	@Override
	public Instance end() {
		this.stopServices();
		this.instances.clear();
		return this;
	}

	private void stopServices() {
		for (final Instance instance : ImmutableList.copyOf(this.instances))
			this.stopService(instance);
	}

	private void stopService(final Instance instance) {
		this.instances.remove(instance);
		instance.end();
	}
}
