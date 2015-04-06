package todolist.rest.aspects;

import java.net.HttpURLConnection;

import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import todolist.rest.model.Response;

@Aspect
public class StatusAspect {
	
	@Autowired private HttpServletResponse responseInScopeFilter;
	
	
	@Before("execution (* todolist.rest.controller.TaskService.inserTask() )")
	public void initStatusCreated(){
		responseInScopeFilter.setStatus(HttpURLConnection.HTTP_CREATED);	
	}
	
	@Before ("execution (* todolist.rest.controller.TaskService.modifyTask()) && "
			+ "execution (* todolist.rest.controller.TaskService.deleteTask())")
	public void initStatusOK(){
		responseInScopeFilter.setStatus(HttpURLConnection.HTTP_OK);	
	}
	
	
	@AfterReturning(value="execution(* todolist.rest.controller.TaskService.*())",returning ="response")
	public void statucChanger(Response response){		
		String error = response.getError();
		if (!error.equals(""))
			responseInScopeFilter.setStatus(HttpURLConnection.HTTP_NOT_MODIFIED);
	}

}
