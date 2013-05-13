package com.laboki.eclipse.plugin.responsiveness.listeners.abstraction;

import com.laboki.eclipse.plugin.responsiveness.instance.AbstractEventBusInstance;
import com.laboki.eclipse.plugin.responsiveness.instance.Instance;
import com.laboki.eclipse.plugin.responsiveness.main.EditorContext;
import com.laboki.eclipse.plugin.responsiveness.main.EventBus;
import com.laboki.eclipse.plugin.responsiveness.task.AsyncTask;

public abstract class AbstractListener extends AbstractEventBusInstance implements IListener {

	public AbstractListener(final EventBus eventbus) {
		super(eventbus);
	}

	@Override
	public Instance begin() {
		this.tryToAdd();
		return super.begin();
	}

	private void tryToAdd() {
		try {
			this.add();
		} catch (final Exception e) {}
	}

	@Override
	public void add() {}

	@Override
	public void remove() {}

	@Override
	public Instance end() {
		this.tryToRemove();
		return super.end();
	}

	private void tryToRemove() {
		try {
			this.remove();
		} catch (final Exception e) {}
	}

	protected static void flushEvents() {
		new AsyncTask() {

			@Override
			public void asyncExecute() {
				EditorContext.flushEvents();
			}
		}.begin();
	}
}
