package todolist.rest.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import sun.net.www.protocol.http.HttpURLConnection;
import todolist.constanst.StringConstants;
import todolist.entitys.Task;
import todolist.rest.model.Response;
import todolist.rest.service.headers.HttpResponseHeadersFactory.TypeHttpCalculator;
import todolist.usecase.SetupTest;
import todolist.usecase.ModifyTodoListUseCaseFactory.TypeModifyTodoList;
import todolist.usecase.errors.ModifyTodoListErrorFactory.TypeErrorModifyTodoList;
import todto.TaskDto;


public class TaskCreatorTest {
		
	private SetupTest setup  = new SetupTest();
	private Service service = new TodoListModifierService(TypeModifyTodoList.INSERT_TASK,TypeErrorModifyTodoList.INSERT_TASK,TypeHttpCalculator.INSERT_TASK);
	private int lastTasksSize;
	
	
	@Before public void setUp(){
		setup.setUp();
		lastTasksSize = setup.getQueryTasks().query().size();
	}
	
	@Test public void whenInsertIsCorrect(){	
        
		Response response =service.execute(new String[]{"no_order","task5","pending"});		
		List<Task> actualTasks = setup.getQueryTasks().query();
		assertEquals(actualTasks.size(),lastTasksSize+1);
		assertEquals(new TaskDto().toDto(response.getTasks()),new TaskDto().toDto(actualTasks));	
		assertEquals(response.getHttpResponseHeader(),HttpURLConnection.HTTP_CREATED);
		assertEquals(response.getError(),"");
	}
	
	@Test public void whenInsertIsNotCorrect(){
		Response response =service.execute(new String[]{"terminated_first","task1","pending"});		
		List<Task> actualTasks = setup.getQueryTasks().queryWithTerminatedTaskFirst();
		assertEquals(actualTasks.size(),lastTasksSize);
		assertEquals(new TaskDto().toDto(response.getTasks()),new TaskDto().toDto(actualTasks));	
		assertEquals(response.getHttpResponseHeader(),HttpURLConnection.HTTP_NOT_MODIFIED);
		assertEquals(response.getError(),StringConstants.ERROR_TASK_WITH_SAME_DESCRIPTION);
	}

}
