package todolist.rest.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import todolist.rest.model.Response;
import todolist.rest.model.TaskRequest;
import todolist.usecase.SetupTest;
import todto.TaskDto;

public class ListTasksTest {
	
	private Service service = new ListTasks();
	private SetupTest setup  = new SetupTest();
	private TaskRequest taskRequest;
	
	@Before public void setUp(){
		setup.setUp();
		taskRequest = new TaskRequest();
	}
	
	
	@Test public void wheCallWithoutOrder(){	
		taskRequest.setOrder("no_order");
		Response response = service.execute(taskRequest);
		assertEquals(new TaskDto().toDto(response.getTasks()),new TaskDto().toDto(setup.getQueryTasks().query()));			
	}
	
	@Test public void whenCallWithOrder(){
		taskRequest.setOrder("terminated_first");
		Response response = service.execute(taskRequest);
		assertEquals(new TaskDto().toDto(response.getTasks()),new TaskDto().toDto(setup.getQueryTasks().queryWithTerminatedTaskFirst()));	
	}
}
