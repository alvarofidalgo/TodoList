package todolist.rest;

import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
//@EnableAutoConfiguration
public class RestServerSpring {
	
	private ConfigurableApplicationContext context;

    public  void start(String [] args) {
    	context = SpringApplication.run(RestServerSpring.class,args );
    }
    
    public void stop(){
    	context.close();
    	context.stop(); 
    }
    
    public void close(){
    	context.close();
    }
}


