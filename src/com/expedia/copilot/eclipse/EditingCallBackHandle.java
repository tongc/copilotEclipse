package com.expedia.copilot.eclipse;

import org.eclipse.swt.widgets.MessageBox;

import com.expedia.copilot.domain.Editing;

public class EditingCallBackHandle implements CallBackHandle<Editing> {
	private MessageBox box;

	public EditingCallBackHandle(MessageBox box) {
		this.box = box;
	}

	@Override
	public void handle(Editing editing) {
		box.setMessage(editing.getValue());
		box.open();
	}
}
