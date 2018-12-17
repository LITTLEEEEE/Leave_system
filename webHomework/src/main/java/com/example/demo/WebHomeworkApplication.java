package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
public class WebHomeworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebHomeworkApplication.class, args);
	}

	@RequestMapping(value = "hello", method = RequestMethod.GET)
	public String hello(){

//		model.addAttribute("name", name);
		return "manager/home";
	}
}

