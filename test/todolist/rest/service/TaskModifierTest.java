package todolist.rest.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import todolist.constanst.StringConstants;
import todolist.entitys.Task;
import todolist.rest.model.Response;
import todolist.rest.model.TaskRequest;
import todolist.usecase.SetupTest;
import todolist.usecase.ModifyTodoListUseCaseFactory.TypeModifyTodoList;
import todolist.usecase.errors.ModifyTodoListErrorFactory.TypeErrorModifyTodoList;


public class TaskModifierTest {
	
	private SetupTest setup  = new SetupTest();
	private Service service = new TodoListModifierService(TypeModifyTodoList.MODIFY_TASK,TypeErrorModifyTodoList.MODIFY_TASK);
	private TaskRequest taskRequest;
	
	@Before public void setUp(){
		setup.setUp();
		taskRequest = new TaskRequest();
	}
		
	@Test public void whenModifyIsCorrect(){	
		taskRequest.setOrder("no_order");
		taskRequest.setTaskdescription("task1");
		taskRequest.setStatus("terminated");
		Response response =service.execute(taskRequest);	
		
		assertEquals(response.getTasks().get(0).getStatus(),Task.Status.TERMINATED);			
		assertEquals(response.getError(),"");
	}
	
	@Test public void whenModifyIsNotCorrect(){
		taskRequest.setOrder("terminated_first");
		taskRequest.setTaskdescription("task2");
		taskRequest.setStatus("pending");
		Response response =service.execute(taskRequest);		
		assertEquals(response.getTasks().get(1).getStatus(),Task.Status.TERMINATED);	
		assertEquals(response.getError(),StringConstants.ERROR_MODIFY_TASK_TERMINATED);
	}
}
