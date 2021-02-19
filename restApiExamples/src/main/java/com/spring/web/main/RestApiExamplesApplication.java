package com.spring.web.main;

import com.spring.web.rest.config.ConfigBean;
import com.spring.web.rest.config.Configuration;
import com.spring.web.rest.config.ConfigurationDelegeta;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@ComponentScan(basePackages = {"com.spring.web"})
@EnableAsync
public class RestApiExamplesApplication {

	private static ConfigBean configBean;
	private static Configuration configuration;

	private static void setSystemProperties() {
		System.setProperty("spring.config.location", configBean.getApplicationConfigFile()+"/");
		System.setProperty("spring.main.banner-mode", "LOG");
	}

	private static void setConfigBean(String[] args) {
		configBean = new ConfigurationDelegeta().configure(args);
		configuration = configBean.getApplicationConfig();
	}

	public static void main(String[] args) {
		setConfigBean(args);
		//setSystemProperties();
		SpringApplication application = new SpringApplication(RestApiExamplesApplication.class);
		application.run(args);
	}
}
