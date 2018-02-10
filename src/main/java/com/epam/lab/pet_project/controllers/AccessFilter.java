package com.epam.lab.pet_project.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AccessFilter implements Filter {
    private static final Logger LOG = LoggerFactory.getLogger(AccessFilter.class);

    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOG.debug("AccessFilter init");
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpSession session = req.getSession();
        StringBuffer url = req.getRequestURL();
        String role = (String) session.getAttribute("role");
        if(role != null){
            int index = url.indexOf("/admin");
            if ((role.equals("USER") && index > 0) ||
                    (role.equals("ADMIN") && url.indexOf("/user") > 0)  ) {
                ServletContext ctx = filterConfig.getServletContext();
                RequestDispatcher dispatcher = ctx.getRequestDispatcher("/WEB-INF/jsp/access_denied.jsp");
                dispatcher.forward(servletRequest, servletResponse);
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        LOG.debug("AccessFilter destroyed");
        filterConfig = null;
    }
}
