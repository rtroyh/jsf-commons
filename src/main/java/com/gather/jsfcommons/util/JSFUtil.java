package com.gather.jsfcommons.util;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

public final class JSFUtil {

    public static String getDirectoryPath() {
        FacesContext aFacesContext = FacesContext.getCurrentInstance();
        ServletContext context = (ServletContext) aFacesContext.getExternalContext().getContext();
        String rootpath = context.getRealPath("/");

        return rootpath;
    }

}
