package com.gather.jsfcommons.servlets.filter;

import javax.servlet.*;
import java.io.IOException;

public class CharacterEncodingFilter implements Filter {

    public void doFilter(ServletRequest req,
                         ServletResponse resp,
                         FilterChain chain) throws
                                            IOException,
                                            ServletException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        chain.doFilter(req,
                       resp);
    }

    public void init(FilterConfig filterConfig) throws
                                                ServletException {

    }

    public void destroy() {

    }
}