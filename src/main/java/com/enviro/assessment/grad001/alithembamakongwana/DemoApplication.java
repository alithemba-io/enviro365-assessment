package com.enviro.assessment.grad001.alithembamakongwana;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScan(basePackages = "com.enviro.assessment.grad001.alithembamakongwana.demo")
@EntityScan(basePackages = "com.enviro.assessment.grad001.alithembamakongwana.demo")
@EnableJpaRepositories(basePackages = "com.enviro.assessment.grad001.alithembamakongwana.demo")

public class DemoApplication 
{

	public static void main(String[] args) 
	{
		SpringApplication.run(DemoApplication.class, args);
	}

}
