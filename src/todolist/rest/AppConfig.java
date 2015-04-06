package todolist.rest;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import todolist.rest.aspects.StatusAspect;


@Configuration
@EnableWebMvc
@ComponentScan({ "todolist.rest.controller" })
public class AppConfig {
	
		
	@Bean
    public MappingJackson2HttpMessageConverter jacksonMessageConverter(){
        return new MappingJackson2HttpMessageConverter();
    }
	
	@Bean
	public StatusAspect getStatusAspect(){
		return new StatusAspect();
	}
}
