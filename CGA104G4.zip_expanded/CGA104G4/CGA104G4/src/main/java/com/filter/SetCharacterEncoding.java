package com.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

@WebFilter
public class SetCharacterEncoding implements Filter {
    protected  String encoding = null;
    protected FilterConfig filterConfig =null;
    protected boolean ignore = true;

    public void init(FilterConfig config) throws ServletException {
        this.filterConfig = config;
        this.encoding = config.getInitParameter("encoding");
        String value = config.getInitParameter("ignore");
        if(value == null)
            this.ignore = true;
        else if (value.equalsIgnoreCase("true"))
            this.ignore = true;
        else if (value.equalsIgnoreCase("yes"))
            this.ignore =true;
        else
            this.ignore =false;
    }

    public void destroy() {
        this.encoding = null;
        this.filterConfig = null;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException,
            IOException {

        if (ignore||(request.getCharacterEncoding() == null)){
            String characterEncoding = selectEncoding(request);
            if(characterEncoding !=null){
                request.setCharacterEncoding(characterEncoding);
            }
        }

        chain.doFilter(request, response);
    }

    private String selectEncoding(ServletRequest request) {
        return (this.encoding);
    }
}
