package com.anupam.app.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class ServletInitializer implements WebApplicationInitializer {

	public void onStartup(ServletContext container) throws ServletException {
		// Context
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(SpringAppConfig.class, SpringMVCConfig.class,MQConfig.class);

		// Manage the life cycle of the root application context
		container.addListener(new ContextLoaderListener(ctx));

		// Register and map the dispatcher servlet.
		ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher", new DispatcherServlet(ctx));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/rest/*");
		dispatcher.addMapping("/msg/*");
	}

}
