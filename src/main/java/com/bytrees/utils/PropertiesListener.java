package com.bytrees.utils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class PropertiesListener implements ServletContextListener  {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		String log4jLogFile = System.getenv("BYTREES_LOG4J_FILE");
		if (log4jLogFile != null) {
			System.setProperty("BYTREES_LOG4J_FILE", log4jLogFile);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		System.getProperties().remove("BYTREES_LOG4J_FILE");
	}
    
}
