package com.laboki.eclipse.plugin.responsiveness.main;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.jobs.IJobManager;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.text.ITextOperationTarget;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPartService;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

public enum EditorContext {
	INSTANCE;

	public static final String PLUGIN_NAME = "com.laboki.eclipse.plugin.responsiveness";
	private static final IWorkbench WORKBENCH = PlatformUI.getWorkbench();
	public static final Display DISPLAY = EditorContext.WORKBENCH.getDisplay();
	public static final IJobManager JOB_MANAGER = Job.getJobManager();

	public static void flushEvents() {
		while (EditorContext.DISPLAY.readAndDispatch())
			EditorContext.DISPLAY.update();
	}

	public static void asyncExec(final Runnable runnable) {
		if (EditorContext.displayIsDisposed()) return;
		EditorContext.DISPLAY.asyncExec(runnable);
	}

	public static void syncExec(final Runnable runnable) {
		if (EditorContext.displayIsDisposed()) return;
		EditorContext.DISPLAY.syncExec(runnable);
	}

	private static boolean displayIsDisposed() {
		return (EditorContext.DISPLAY == null) || EditorContext.DISPLAY.isDisposed();
	}

	public static IPartService getPartService() {
		return (IPartService) EditorContext.WORKBENCH.getActiveWorkbenchWindow().getService(IPartService.class);
	}

	public static IEditorPart getEditor() {
		return EditorContext.WORKBENCH.getActiveWorkbenchWindow().getActivePage().getActiveEditor();
	}

	public static Control getControl(final IEditorPart editor) {
		return (Control) editor.getAdapter(Control.class);
	}

	public static StyledText getBuffer(final IEditorPart editor) {
		return (StyledText) EditorContext.getControl(editor);
	}

	public static SourceViewer getView(final IEditorPart editor) {
		return (SourceViewer) editor.getAdapter(ITextOperationTarget.class);
	}

	public static IFile getFile(final IEditorPart editor) {
		return ((FileEditorInput) editor.getEditorInput()).getFile();
	}

	public static void cancelJobsBelongingTo(final String jobName) {
		EditorContext.JOB_MANAGER.cancel(jobName);
	}

	public static boolean taskDoesNotExist(final String... names) {
		for (final String name : names)
			if (EditorContext.taskExists(name)) return false;
		return true;
	}

	private static boolean taskExists(final String name) {
		return EditorContext.JOB_MANAGER.find(name).length > 0;
	}
}
