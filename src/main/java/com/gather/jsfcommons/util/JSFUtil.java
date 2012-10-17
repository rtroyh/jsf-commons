package com.gather.jsfcommons.util;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import java.util.Map;

public final class JSFUtil {

    public static String getDirectoryPath(FacesContext fc) {
        ServletContext context = (ServletContext) fc.getExternalContext().getContext();

        return context.getRealPath("/");
    }

    public static String getParameterValue(FacesContext fc,
                                           String parameterName) {
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();

        return params.get(parameterName);
    }

}
