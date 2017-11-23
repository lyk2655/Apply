package com.linyk3.apply.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.linyk3.apply.server.ApplyServer;


public class Main {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
		ApplyServer server = context.getBean(ApplyServer.class);
		server.startServer();
	}
}
