package com.gather.jsfcommons.util;

import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.faces.context.FacesContext;
import javax.faces.event.MethodExpressionActionListener;
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

    public static MethodExpression createMethodExpression(String valueExpression,
                                                          Class<?> expectedReturnType,
                                                          Class<?>[] expectedParamTypes) {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExpressionFactory factory = fc.getApplication().getExpressionFactory();

        return factory.createMethodExpression(fc.getELContext(),
                                              valueExpression,
                                              expectedReturnType,
                                              expectedParamTypes);
    }

    public static MethodExpressionActionListener createMethodActionListener(String valueExpression,
                                                                            Class<?> expectedReturnType,
                                                                            Class<?>[] expectedParamTypes) {
        return new MethodExpressionActionListener(createMethodExpression(valueExpression,
                                                                         expectedReturnType,
                                                                         expectedParamTypes));
    }
}
