package com.clement.magichome;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import javax.annotation.Resource;

import org.apache.catalina.connector.Connector;
import org.apache.catalina.valves.RemoteIpValve;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.annotation.JsonAppend.Prop;

/**
 * This is the main class that launch the spring application.
 * 
 * @author Clement_Soullard
 *
 */
@SpringBootApplication
public class SchedulerApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SchedulerApplication.class, args);
	}

	@Bean
	@SuppressWarnings("static-method")
	public EmbeddedServletContainerFactory servletContainer() {
		TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
		// tomcat.addAdditionalTomcatConnectors(createConnector());
		// tomcat.addContextValves(createRemoteIpValves());
		// tomcat.setContextPath("/tvscheduler");
		// tomcat.getAdditionalTomcatConnectors();
		return tomcat;
	}

	// private static RemoteIpValve createRemoteIpValves() {
	// RemoteIpValve remoteIpValve = new RemoteIpValve();
	// remoteIpValve.setRemoteIpHeader("x-forwarded-for");
	// remoteIpValve.setProtocolHeader("x-forwarded-proto");
	// return remoteIpValve;
	// }

	private static Connector createConnector() {
		Connector connector = new Connector("AJP/1.3");
		connector.setPort(8009);
		return connector;
	}
}