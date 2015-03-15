package todolist.rest.service;

import static org.junit.Assert.*;

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


public class TaskModifierTest {
	
	private SetupTest setup  = new SetupTest();
	private Service service = new TodoListModifierService(TypeModifyTodoList.MODIFY_TASK,TypeErrorModifyTodoList.MODIFY_TASK,TypeHttpCalculator.MODIFY_OR_DELETE_TASK);
	
	@Before public void setUp(){
		setup.setUp();
	}
		
	@Test public void whenModifyIsCorrect(){		
		Response response =service.execute(new String[]{"no_order","task1","terminated"});		
		assertEquals(response.getTasks().get(0).getStatus(),Task.Status.TERMINATED);	
		assertEquals(response.getHttpResponseHeader(),HttpURLConnection.HTTP_OK);		
		assertEquals(response.getError(),"");
	}
	
	@Test public void whenModifyIsNotCorrect(){
		Response response =service.execute(new String[]{"terminated_first","task2","pending"});		
		assertEquals(response.getTasks().get(1).getStatus(),Task.Status.TERMINATED);	
		assertEquals(response.getHttpResponseHeader(),HttpURLConnection.HTTP_NOT_MODIFIED);
		assertEquals(response.getError(),StringConstants.ERROR_MODIFY_TASK_TERMINATED);
	}
}
