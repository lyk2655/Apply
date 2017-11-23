package com.ipin.service.rest.tasks;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;

public abstract class Task {

	@Value("#{configProperties['schedule.cache.isstartinit']}")  
	private boolean isInit;
	
	/**
	 * 启动时执行一次
	 */
	@PostConstruct
	public void firstTask() {
		if(isInit) {
			doTask();
		}
	}
	
	public abstract void doTask();
	
}
