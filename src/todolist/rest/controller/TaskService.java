package todolist.rest.controller;

import java.io.IOException;
import java.net.HttpURLConnection;

import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import todolist.rest.model.Response;
import todolist.rest.model.TaskRequest;
import todolist.rest.service.ListTasks;
import todolist.rest.service.TodoListModifierService;
import todolist.rest.service.headers.HttpResponserHeaderCalculator;
import todolist.usecase.ModifyTodoListUseCaseFactory.TypeModifyTodoList;
import todolist.usecase.errors.ModifyTodoListErrorFactory.TypeErrorModifyTodoList;


@RestController(value="/task")
public class TaskService {
	
	@Autowired private HttpServletResponse responseInScopeFilter;
	private TaskRequest _request;
	
	@ModelAttribute("TaskRequest")
	public void decodeQueryHeader( TaskRequest request){
		_request= request; 
	}
		
	@RequestMapping(method=RequestMethod.GET)	
	@ResponseStatus (value = HttpStatus.OK)		
	public Object getTaskListWithorder()  {		
		return  new ListTasks().execute(_request);
	}
		
	@RequestMapping(value="/task",method=RequestMethod.PUT)	
	public Object inserTask() {		
		Response responseBody = new TodoListModifierService(TypeModifyTodoList.INSERT_TASK,TypeErrorModifyTodoList.INSERT_TASK).execute(_request);
		responseInScopeFilter.setStatus(new HttpResponserHeaderCalculator(HttpURLConnection.HTTP_CREATED,HttpURLConnection.HTTP_NOT_MODIFIED).calculateResponseHeader(responseBody));
		return responseBody;
	}
	
	@RequestMapping(value="/task",method=RequestMethod.POST)
	public Object modifyTask() {
		Response responseBody = new TodoListModifierService(TypeModifyTodoList.MODIFY_TASK,TypeErrorModifyTodoList.MODIFY_TASK).execute(_request);
		responseInScopeFilter.setStatus(new HttpResponserHeaderCalculator(HttpURLConnection.HTTP_OK,HttpURLConnection.HTTP_NOT_MODIFIED).calculateResponseHeader(responseBody));
		return responseBody;
	}
	
	@RequestMapping(value="/task",method=RequestMethod.DELETE)
	public Object deleteTask() throws JsonGenerationException, JsonMappingException, IOException{
		Response responseBody = new TodoListModifierService(TypeModifyTodoList.DELETE_TASK,TypeErrorModifyTodoList.DELETE_TASK).execute(_request);
		responseInScopeFilter.setStatus(new HttpResponserHeaderCalculator(HttpURLConnection.HTTP_OK,HttpURLConnection.HTTP_NOT_MODIFIED).calculateResponseHeader(responseBody));
		return responseBody;
	}
}
