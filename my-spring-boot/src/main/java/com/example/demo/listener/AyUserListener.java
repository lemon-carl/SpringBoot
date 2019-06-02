package com.example.demo.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @ClassName : AyUserListener
 * Created with IDEA
 * @author:CarlLing
 * @CreateDate : 2018-09-25 12:23
 * @Description :
 */
@WebListener
public class AyUserListener implements ServletContextListener{

    private static final Logger logger = LoggerFactory.getLogger(AyUserListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        logger.info("ServletContext 上下文初始化");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        logger.info("ServletContext 上下文被销毁");
    }
}
