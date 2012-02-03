package com.gather.jsfcommons.handler;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

public class ViewExpiredExceptionExceptionHandlerFactory extends ExceptionHandlerFactory {

	private ExceptionHandlerFactory parent;

	public ViewExpiredExceptionExceptionHandlerFactory(ExceptionHandlerFactory parent) {
		this.parent = parent;
	}

	@Override
	public ExceptionHandler getExceptionHandler() {
		ExceptionHandler parentHandler = parent.getExceptionHandler();
		return new ViewExpiredExceptionExceptionHandler(parentHandler);
	}

}
