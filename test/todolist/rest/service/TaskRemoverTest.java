package todolist.rest.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import todolist.constanst.StringConstants;
import todolist.entitys.Task;
import todolist.rest.model.Response;
import todolist.rest.model.TaskRequest;
import todolist.usecase.SetupTest;
import todolist.usecase.ModifyTodoListUseCaseFactory.TypeModifyTodoList;
import todolist.usecase.errors.ModifyTodoListErrorFactory.TypeErrorModifyTodoList;
import todto.TaskDto;

public class TaskRemoverTest {
	
	private Service service =  new TodoListModifierService(TypeModifyTodoList.DELETE_TASK,TypeErrorModifyTodoList.DELETE_TASK);
	private SetupTest setUp ;
	private int lastTasksSize;;
	private TaskRequest taskRequest;
	
	
	@Before public void setUp(){
		setUp = new SetupTest();
		setUp.setUp();
		lastTasksSize  =setUp.getQueryTasks().query().size();
		taskRequest = new TaskRequest();
	}
	
	
	@Test public void whenDeleteIsCorrect(){
		
		taskRequest.setOrder("no_order");
		taskRequest.setTaskdescription("task2");
		taskRequest.setStatus("terminated");
		Response response =service.execute(taskRequest);

		List<Task> actualTasks = setUp.getQueryTasks().query();
		assertEquals(actualTasks.size(),lastTasksSize-1);
		assertEquals(new TaskDto().toDto(response.getTasks()),new TaskDto().toDto(actualTasks));	
		assertEquals(response.getError(),"");

	}
	
	@Test public void whenDeleteIsNotCorrect(){
		taskRequest.setOrder("terminated_first");
		taskRequest.setTaskdescription("task1");
		taskRequest.setStatus("pending");
		Response response =service.execute(taskRequest);
			
		List<Task> actualTasks = setUp.getQueryTasks().queryWithTerminatedTaskFirst();
		assertEquals(actualTasks.size(),lastTasksSize);
		assertEquals(new TaskDto().toDto(response.getTasks()),new TaskDto().toDto(actualTasks));	
		assertEquals(response.getError(),StringConstants.ERROR_DELETE_PENDING_TASK);
	}
}
