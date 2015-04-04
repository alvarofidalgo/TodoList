package todolist.rest;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import todolist.rest.service.aspects.JSONAspect;


@Configuration
@EnableWebMvc
@ComponentScan({ "todolist.rest.controller" })
public class AppConfig {

	@Bean
	public JSONAspect getAspect(){
	  return new JSONAspect();
	}
}
