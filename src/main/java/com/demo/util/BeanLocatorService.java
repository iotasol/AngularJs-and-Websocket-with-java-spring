package com.demo.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class BeanLocatorService implements ApplicationContextAware {
	private static ApplicationContext context;

	public static Object getBean(String beanName, Object... objects) {
		return (context.getBean(beanName, objects));
	}

	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		BeanLocatorService.context = context;
	}
}