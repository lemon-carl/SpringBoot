package com.example.demo.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @ClassName : AyUserFilter
 * Created with IDEA
 * @author:CarlLing
 * @CreateDate : 2018-09-25 11:50
 * @Description :
 */
@WebFilter(filterName = "ayUserFilter",urlPatterns = "/*")
public class AyUserFilter implements Filter{

     private static final Logger logger = LoggerFactory.getLogger(AyUserFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
            logger.info("------------>> init ");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            logger.info("------------>> doFilter");
            filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
            logger.info("------------>> destroy");
    }
}
