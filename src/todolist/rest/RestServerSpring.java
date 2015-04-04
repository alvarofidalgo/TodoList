package todolist.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class RestServerSpring {
	
	private ConfigurableApplicationContext context;

    public  void start(String [] args) {

    	SpringApplication aplication = new SpringApplication(RestServerSpring.class);  
    	context = aplication.run(args);    	
    }
    
    public void stop(){
    	context.close();
    	context.stop(); 
    }
    
    public void close(){
    	context.close();
    }
}


