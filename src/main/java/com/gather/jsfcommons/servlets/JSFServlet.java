package com.gather.jsfcommons.servlets;

import javax.faces.FactoryFinder;
import javax.faces.context.FacesContext;
import javax.faces.context.FacesContextFactory;
import javax.faces.lifecycle.Lifecycle;
import javax.faces.lifecycle.LifecycleFactory;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@SuppressWarnings("serial")
public abstract class JSFServlet extends HttpServlet {

    protected FacesContext getFacesContext(ServletRequest request,
                                           ServletResponse response) {

        FacesContextFactory contextFactory = (FacesContextFactory) FactoryFinder.getFactory(FactoryFinder.FACES_CONTEXT_FACTORY);
        LifecycleFactory lifecycleFactory = (LifecycleFactory) FactoryFinder.getFactory(FactoryFinder.LIFECYCLE_FACTORY);
        Lifecycle lifecycle = lifecycleFactory.getLifecycle(LifecycleFactory.DEFAULT_LIFECYCLE);
        ServletContext servletContext = ((HttpServletRequest) request).getSession().getServletContext();
        FacesContext facesContext = contextFactory.getFacesContext(servletContext,
                                                                   request,
                                                                   response,
                                                                   lifecycle);
        return facesContext;
    }
}
