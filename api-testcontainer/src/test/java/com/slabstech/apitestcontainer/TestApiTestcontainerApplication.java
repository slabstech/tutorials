package com.slabstech.apitestcontainer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestApiTestcontainerApplication {

	public static void main(String[] args) {
		SpringApplication.from(ApiTestcontainerApplication::main).with(TestApiTestcontainerApplication.class).run(args);
	}

}
