package com.expedia.copilot.eclipse;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.expedia.copilot.domain.Editing;
import com.expedia.copilot.handler.MessageHandler;

public class EditingMessageHandler implements MessageHandler<Editing> {
	private static Logger LOGGER = LoggerFactory.getLogger(EditingMessageHandler.class);
	private CallBackHandle<Editing> callbackHandle;

	@Override
	public void handle(Editing editing) throws IOException {
		LOGGER.debug(editing.toString());
		callbackHandle.handle(editing);
	}

	public void setCallbackHandle(CallBackHandle<Editing> callbackHandle) {
		this.callbackHandle = callbackHandle;
	}
}