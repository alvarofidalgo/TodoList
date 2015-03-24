package todolist.rest.service;

import java.io.IOException;
import java.net.HttpURLConnection;

import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;



import todolist.rest.model.Response;
import todolist.rest.model.TaskRequest;
import todolist.rest.service.headers.HttpResponserHeaderCalculator;
import todolist.usecase.ModifyTodoListUseCaseFactory.TypeModifyTodoList;
import todolist.usecase.errors.ModifyTodoListErrorFactory.TypeErrorModifyTodoList;


@RestController(value="/task")
public final class TaskService {
	
	@Autowired private  HttpServletResponse responseInScopeFilter;
	private TaskRequest _request;
	
	@ModelAttribute("TaskRequest")
	public void decodeQueryHeader(TaskRequest request){
		_request= request; 
	}
		
	@RequestMapping(method=RequestMethod.GET)	
	@ResponseStatus (value = HttpStatus.OK)	
	public String getTaskListWithorder() throws JsonGenerationException, JsonMappingException, IOException {		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		Response responseBody = new ListTasks().execute(_request);
		return ow.writeValueAsString(responseBody);	
	}
		
	@RequestMapping(method=RequestMethod.PUT)	
	public String inserTask() throws JsonGenerationException, JsonMappingException, IOException{		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		Response responseBody = new TodoListModifierService(TypeModifyTodoList.INSERT_TASK,TypeErrorModifyTodoList.INSERT_TASK).execute(_request);
		responseInScopeFilter.setStatus(new HttpResponserHeaderCalculator(HttpURLConnection.HTTP_CREATED,HttpURLConnection.HTTP_NOT_MODIFIED).calculateResponseHeader(responseBody));
		return ow.writeValueAsString(responseBody);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String modifyTask() throws JsonGenerationException, JsonMappingException, IOException{
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		Response responseBody = new TodoListModifierService(TypeModifyTodoList.MODIFY_TASK,TypeErrorModifyTodoList.MODIFY_TASK).execute(_request);
		responseInScopeFilter.setStatus(new HttpResponserHeaderCalculator(HttpURLConnection.HTTP_OK,HttpURLConnection.HTTP_NOT_MODIFIED).calculateResponseHeader(responseBody));
	    return ow.writeValueAsString(responseBody);
	}
	
	@RequestMapping(method=RequestMethod.DELETE)
	public String deleteTask() throws JsonGenerationException, JsonMappingException, IOException{
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		Response responseBody = new TodoListModifierService(TypeModifyTodoList.DELETE_TASK,TypeErrorModifyTodoList.DELETE_TASK).execute(_request);
		responseInScopeFilter.setStatus(new HttpResponserHeaderCalculator(HttpURLConnection.HTTP_OK,HttpURLConnection.HTTP_NOT_MODIFIED).calculateResponseHeader(responseBody));
		return ow.writeValueAsString(responseBody);	
	}
}
