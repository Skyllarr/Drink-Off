package com.violetbutterfly.drinkoff.web.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class WebAppInitializer implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sev) {
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
