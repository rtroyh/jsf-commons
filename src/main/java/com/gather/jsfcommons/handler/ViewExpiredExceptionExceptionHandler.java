package com.gather.jsfcommons.handler;

import org.apache.log4j.Logger;

import javax.faces.FacesException;
import javax.faces.application.NavigationHandler;
import javax.faces.application.ViewExpiredException;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import java.util.Iterator;
import java.util.Map;

public class ViewExpiredExceptionExceptionHandler extends ExceptionHandlerWrapper {

    private ExceptionHandler wrapped;

    public ViewExpiredExceptionExceptionHandler(ExceptionHandler wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public ExceptionHandler getWrapped() {
        return this.wrapped;
    }

    @Override
    public void handle() throws
                         FacesException {
        for (Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents().iterator(); i.hasNext(); ) {
            ExceptionQueuedEvent event = i.next();
            ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();
            Throwable t = context.getException();

            FacesContext fc = FacesContext.getCurrentInstance();
            Map<String, Object> requestMap = fc.getExternalContext().getRequestMap();
            NavigationHandler nav = fc.getApplication().getNavigationHandler();

            if (t instanceof ViewExpiredException) {
                this.thisMethodIsOnlyNecessaryBecauseOfTheWayWeForceTheViewExpiredException(fc);
                try {
                    ViewExpiredException vee = (ViewExpiredException) t;

                    requestMap.put("currentViewId",
                                   vee.getViewId());

                    nav.handleNavigation(fc,
                                         null,
                                         "viewExpired");
                    fc.renderResponse();

                } finally {
                    i.remove();
                }
            } else {
                try {
                    Logger.getLogger(ViewExpiredExceptionExceptionHandler.class).info("exception:" +
                                                                                              t.getMessage());

                    nav.handleNavigation(fc,
                                         null,
                                         "exception");
                    fc.renderResponse();

                } finally {
                    i.remove();
                }
            }
        }
        // At this point, the queue will not contain any ViewExpiredEvents.
        // Therefore, let the parent handle them.
        getWrapped().handle();

    }

    private void thisMethodIsOnlyNecessaryBecauseOfTheWayWeForceTheViewExpiredException(FacesContext context) {
        UIViewRoot root = context.getApplication().getViewHandler().createView(context,
                                                                               "/validacion.xhtml");
        context.setViewRoot(root);
    }

}
