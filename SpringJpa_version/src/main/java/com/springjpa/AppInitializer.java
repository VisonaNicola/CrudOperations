package com.springjpa;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	//indica quale classe deve essere usata per accedere a tutte le configurazioni web
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] {WebConfig.class};
	}

	//indica quale path deve intercettare la DispatcherServlet
	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}

	@Override
	protected FrameworkServlet createDispatcherServlet(WebApplicationContext servletAppContext) {
		DispatcherServlet ds = new DispatcherServlet(servletAppContext);
		ds.setThrowExceptionIfNoHandlerFound(true);
		return ds;
	}
}
