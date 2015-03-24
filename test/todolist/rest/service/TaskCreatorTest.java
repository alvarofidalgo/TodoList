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


public class TaskCreatorTest {
		
	private SetupTest setup  = new SetupTest();
	private Service service = new TodoListModifierService(TypeModifyTodoList.INSERT_TASK,TypeErrorModifyTodoList.INSERT_TASK);
	private int lastTasksSize;
	private TaskRequest taskRequest;
	
	
	@Before public void setUp(){
		setup.setUp();
		lastTasksSize = setup.getQueryTasks().query().size();
		taskRequest = new TaskRequest();
	}
	
	@Test public void whenInsertIsCorrect(){	
		taskRequest.setOrder("no_order");
		taskRequest.setTaskdescription("task5");
		taskRequest.setStatus("pending");
		Response response =service.execute(taskRequest);		
		List<Task> actualTasks = setup.getQueryTasks().query();
		assertEquals(actualTasks.size(),lastTasksSize+1);
		assertEquals(new TaskDto().toDto(response.getTasks()),new TaskDto().toDto(actualTasks));	
		assertEquals(response.getError(),"");
	}
	
	@Test public void whenInsertIsNotCorrect(){
		taskRequest.setOrder("terminated_first");
		taskRequest.setTaskdescription("task1");
		taskRequest.setStatus("pending");
		Response response =service.execute(taskRequest);			
		List<Task> actualTasks = setup.getQueryTasks().queryWithTerminatedTaskFirst();
		assertEquals(actualTasks.size(),lastTasksSize);
		assertEquals(new TaskDto().toDto(response.getTasks()),new TaskDto().toDto(actualTasks));	
		assertEquals(response.getError(),StringConstants.ERROR_TASK_WITH_SAME_DESCRIPTION);
	}

}
