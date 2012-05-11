package com.gather.jsfcommons.util;

import javax.el.MethodExpression;
import javax.faces.component.UIComponent;
import javax.faces.event.FacesEvent;
import javax.faces.event.FacesListener;

/**
 * Created with IntelliJ IDEA.
 * User: rodrigotroy
 * Date: 5/11/12
 * Time: 11:19 AM
 * To change this template use File | Settings | File Templates.
 */
public class MethodEvent extends FacesEvent {

    private MethodExpression method;
    private Object[] args;

    public MethodEvent(UIComponent source,
                       MethodExpression method,
                       Object... args) {
        super(source);
        this.method = method;
        this.args = args;
    }

    @Override
    public boolean isAppropriateListener(FacesListener listener) {
        return listener instanceof MethodListener;
    }

    @Override
    public void processListener(FacesListener listener) {
        ((MethodListener) listener).process(method,
                                            args);
    }
}