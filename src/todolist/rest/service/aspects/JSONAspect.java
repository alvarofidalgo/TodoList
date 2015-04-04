package todolist.rest.service.aspects;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Around;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

import todolist.rest.model.Response;

@Aspect
public class JSONAspect {

	   @Around("within(todolist.rest.controller.TaskService)")
	   public String createJSON(ProceedingJoinPoint procedJoinPoint) throws Throwable{
		   ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		   Response responseBody  =(Response) procedJoinPoint.proceed();
		   return  ow.writeValueAsString(responseBody);
	   }
}
