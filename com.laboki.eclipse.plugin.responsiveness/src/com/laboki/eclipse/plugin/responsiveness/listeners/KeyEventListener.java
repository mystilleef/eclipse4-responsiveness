package com.laboki.eclipse.plugin.responsiveness.listeners;

import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.widgets.Control;

import com.laboki.eclipse.plugin.responsiveness.listeners.abstraction.AbstractListener;
import com.laboki.eclipse.plugin.responsiveness.main.EditorContext;
import com.laboki.eclipse.plugin.responsiveness.main.EventBus;

public final class KeyEventListener extends AbstractListener implements KeyListener {

	private final Control control = EditorContext.getControl(EditorContext.getEditor());

	public KeyEventListener(final EventBus eventbus) {
		super(eventbus);
	}

	@Override
	public void add() {
		this.control.addKeyListener(this);
	}

	@Override
	public void remove() {
		this.control.removeKeyListener(this);
	}

	@Override
	public void keyPressed(final KeyEvent event) {
		this.flushEvents();
	}

	@Override
	public void keyReleased(final KeyEvent event) {
		this.flushEvents();
	}
}
