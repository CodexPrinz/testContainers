package com.projects.testContainers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestTestContainersApplication {

	public static void main(String[] args) {
		SpringApplication.from(TestContainersApplication::main).with(TestTestContainersApplication.class).run(args);
	}

}
