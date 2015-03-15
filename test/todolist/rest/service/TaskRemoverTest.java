package todolist.rest.service;

import static org.junit.Assert.*;

import java.net.HttpURLConnection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import todolist.constanst.StringConstants;
import todolist.entitys.Task;
import todolist.rest.model.Response;
import todolist.rest.service.headers.HttpResponseHeadersFactory.TypeHttpCalculator;
import todolist.usecase.SetupTest;
import todolist.usecase.ModifyTodoListUseCaseFactory.TypeModifyTodoList;
import todolist.usecase.errors.ModifyTodoListErrorFactory.TypeErrorModifyTodoList;
import todto.TaskDto;

public class TaskRemoverTest {
	
	private Service service =  new TodoListModifierService(TypeModifyTodoList.DELETE_TASK,TypeErrorModifyTodoList.DELETE_TASK,TypeHttpCalculator.MODIFY_OR_DELETE_TASK);
	private SetupTest setUp ;
	private int lastTasksSize;;
	
	
	@Before public void setUp(){
		setUp = new SetupTest();
		setUp.setUp();
		lastTasksSize  =setUp.getQueryTasks().query().size();
	}
	
	
	@Test public void whenDeleteIsCorrect(){

		Response response =service.execute(new String[]{"no_order","task2","terminated"});		
		List<Task> actualTasks = setUp.getQueryTasks().query();
		assertEquals(actualTasks.size(),lastTasksSize-1);
		assertEquals(new TaskDto().toDto(response.getTasks()),new TaskDto().toDto(actualTasks));	
		assertEquals(response.getHttpResponseHeader(),HttpURLConnection.HTTP_OK);
		assertEquals(response.getError(),"");

	}
	
	@Test public void whenDeleteIsNotCorrect(){
		
		Response response =service.execute(new String[]{"terminated_first","task1","pending"});		
		List<Task> actualTasks = setUp.getQueryTasks().queryWithTerminatedTaskFirst();
		assertEquals(actualTasks.size(),lastTasksSize);
		assertEquals(new TaskDto().toDto(response.getTasks()),new TaskDto().toDto(actualTasks));	
		assertEquals(response.getHttpResponseHeader(),HttpURLConnection.HTTP_NOT_MODIFIED);
		assertEquals(response.getError(),StringConstants.ERROR_DELETE_PENDING_TASK);
	}
}
