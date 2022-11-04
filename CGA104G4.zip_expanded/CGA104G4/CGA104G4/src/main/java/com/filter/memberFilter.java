package com.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebFilter
public class memberFilter implements Filter {

    private FilterConfig config;

    public void init(FilterConfig config) throws ServletException {
        this.config = config;
    }

    public void destroy() {
        config = null;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession();

        Object memId = session.getAttribute("memId");
        if (memId == null) {
            session.setAttribute("location", req.getRequestURI());
            res.sendRedirect(req.getContextPath() + "/front-end/memberlogin.html");
            return;
        } else {
            chain.doFilter(request, response);
        }
    }
}
