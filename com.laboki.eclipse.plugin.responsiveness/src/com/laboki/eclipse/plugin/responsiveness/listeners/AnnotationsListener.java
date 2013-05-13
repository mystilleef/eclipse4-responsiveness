package com.laboki.eclipse.plugin.responsiveness.listeners;

import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.text.source.IAnnotationModelListener;

import com.laboki.eclipse.plugin.responsiveness.listeners.abstraction.AbstractListener;
import com.laboki.eclipse.plugin.responsiveness.main.EditorContext;
import com.laboki.eclipse.plugin.responsiveness.main.EventBus;

public class AnnotationsListener extends AbstractListener implements IAnnotationModelListener {

	private final IAnnotationModel annotationModel = AnnotationsListener.getAnnotationModel();

	public AnnotationsListener(final EventBus eventbus) {
		super(eventbus);
	}

	@Override
	public void add() {
		this.annotationModel.addAnnotationModelListener(this);
	}

	@Override
	public void remove() {
		this.annotationModel.removeAnnotationModelListener(this);
	}

	@Override
	public void modelChanged(final IAnnotationModel model) {
		this.flushEvents();
	}

	private static IAnnotationModel getAnnotationModel() {
		try {
			return EditorContext.getView(EditorContext.getEditor()).getAnnotationModel();
		} catch (final Exception e) {
			return null;
		}
	}
}
