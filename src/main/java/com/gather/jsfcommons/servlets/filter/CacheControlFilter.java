package com.gather.jsfcommons.servlets.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * $ Project: jsf-commons
 * User: rodrigotroy
 * Date: 03-04-16
 * Time: 16:12
 */
public class CacheControlFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws
                                                ServletException {

    }

    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws
                                            IOException,
                                            ServletException {

        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setHeader("Expires",
                       "Tue, 03 Jul 2001 06:00:00 GMT");
        resp.setDateHeader("Last-Modified",
                           new Date().getTime());
        resp.setHeader("Cache-Control",
                       "no-store, no-cache, must-revalidate, max-age=0, post-check=0, pre-check=0");
        resp.setHeader("Pragma",
                       "no-cache");

        chain.doFilter(request,
                       response);
    }

    @Override
    public void destroy() {

    }

}