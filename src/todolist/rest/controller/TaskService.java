package todolist.rest.controller;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import todolist.rest.model.Response;
import todolist.rest.model.TaskRequest;
import todolist.rest.service.ListTasks;
import todolist.rest.service.TodoListModifierService;
import todolist.usecase.ModifyTodoListUseCaseFactory.TypeModifyTodoList;
import todolist.usecase.errors.ModifyTodoListErrorFactory.TypeErrorModifyTodoList;

@RestController(value="/task")
public class TaskService {
	
	private TaskRequest _request;
	
	@ModelAttribute("TaskRequest")
	public void decodeQueryHeader( TaskRequest request){		
		_request= request; 
	}
		
	@RequestMapping(method=RequestMethod.GET)	
	@ResponseStatus (value = HttpStatus.OK)		
	public @ResponseBody Response getTaskListWithorder()  {		
		return  new ListTasks().execute(_request);
	}
		
	@RequestMapping(value="/task",method=RequestMethod.PUT)	
	public @ResponseBody Response  inserTask() {		
		return new TodoListModifierService(TypeModifyTodoList.INSERT_TASK,TypeErrorModifyTodoList.INSERT_TASK).execute(_request);
	}
	
	@RequestMapping(value="/task",method=RequestMethod.POST)
	public  @ResponseBody Response  modifyTask() {
		return new TodoListModifierService(TypeModifyTodoList.MODIFY_TASK,TypeErrorModifyTodoList.MODIFY_TASK).execute(_request);
	}
	
	@RequestMapping(value="/task",method=RequestMethod.DELETE)
	public @ResponseBody Response  deleteTask() throws JsonGenerationException, JsonMappingException, IOException{
		return new TodoListModifierService(TypeModifyTodoList.DELETE_TASK,TypeErrorModifyTodoList.DELETE_TASK).execute(_request);
	}
}
