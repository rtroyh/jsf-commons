package com.gather.jsfcommons.util;

import javax.el.MethodExpression;
import javax.faces.context.FacesContext;
import javax.faces.event.FacesListener;

/**
 * Created with IntelliJ IDEA.
 * User: rodrigotroy
 * Date: 5/11/12
 * Time: 11:19 AM
 * To change this template use File | Settings | File Templates.
 */
public class MethodListener implements FacesListener {

    void process(MethodExpression method,
                 Object[] args) {
        method.invoke(FacesContext.getCurrentInstance().getELContext(),
                      args);
    }
}
