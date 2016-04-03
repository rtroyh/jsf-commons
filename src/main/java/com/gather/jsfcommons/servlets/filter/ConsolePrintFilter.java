package com.gather.jsfcommons.servlets.filter;

import javax.servlet.*;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: rodrigotroy
 * Date: 7/3/12
 * Time: 3:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConsolePrintFilter implements Filter {

    public void doFilter(ServletRequest req,
                         ServletResponse resp,
                         FilterChain chain) throws
                                            IOException,
                                            ServletException {
        System.out.println("");
        System.out.println("getContentType: " + req.getContentType());

        System.out.println("Attribute Names:");
        Enumeration enums = req.getAttributeNames();
        while (enums.hasMoreElements()) {
            System.out.println("    Attribute name: " + enums.nextElement());
        }

        System.out.println("Parameter Map:");
        for (Map.Entry entry : req.getParameterMap().entrySet()) {
            System.out.println("    " + entry.getKey() + "= " + entry.getValue());
        }

        chain.doFilter(req,
                       resp);
    }

    public void init(FilterConfig filterConfig) throws
                                                ServletException {
        System.out.println("INIT");
    }

    public void destroy() {
        System.out.println("DESTROY");
    }
}
