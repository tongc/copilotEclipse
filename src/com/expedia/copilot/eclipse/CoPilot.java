package com.expedia.copilot.eclipse;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.expedia.copilot.domain.Editing;
import com.expedia.copilot.service.MessagePublishingService;

/**
 * Our sample action implements workbench action delegate.
 * The action proxy will be created by the workbench and
 * shown in the UI. When the user tries to use the action,
 * this delegate will be created and execution will be
 * delegated to it.
 * @see IWorkbenchWindowActionDelegate
 */
public class CoPilot implements IWorkbenchWindowActionDelegate {
	private IWorkbenchWindow window;
	/**
	 * The constructor.
	 */
	public CoPilot() {
	}

	/**
	 * The action has been activated. The argument of the
	 * method represents the 'real' action sitting
	 * in the workbench UI.
	 * @see IWorkbenchWindowActionDelegate#run
	 */
	@Override
	public void run(IAction action) {
		ApplicationContext context = new ClassPathXmlApplicationContext("copilotEclipse.xml");
		MessagePublishingService service = context.getBean(MessagePublishingService.class);
		EditingMessageHandler handler = context.getBean(EditingMessageHandler.class);
		MessageBox box = new MessageBox(window.getShell(),SWT.ICON_INFORMATION);
		EditingCallBackHandle callbackHandle = new EditingCallBackHandle(box);
		handler.setCallbackHandle(callbackHandle);
		InputDialog dialog = new InputDialog(window.getShell(),"Lets try!",
      			"Please enter your message","",null);
	      if( dialog.open()== IStatus.OK){
	          String value = dialog.getValue();
	          service.send("copilotMessage", "", new Editing.Builder().withValue(value).build());
	      }
	}

	/**s
	 * Selection in the workbench has been changed. We
	 * can change the state of the 'real' action here
	 * if we want, but this can only happen after
	 * the delegate has been created.
	 * @see IWorkbenchWindowActionDelegate#selectionChanged
	 */
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
	}

	/**
	 * We can use this method to dispose of any system
	 * resources we previously allocated.
	 * @see IWorkbenchWindowActionDelegate#dispose
	 */
	@Override
	public void dispose() {
	}

	/**
	 * We will cache window object in order to
	 * be able to provide parent shell for the message dialog.
	 * @see IWorkbenchWindowActionDelegate#init
	 */
	@Override
	public void init(IWorkbenchWindow window) {
		this.window = window;
	}
}
