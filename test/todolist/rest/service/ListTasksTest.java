package todolist.rest.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import sun.net.www.protocol.http.HttpURLConnection;
import todolist.rest.model.Response;
import todolist.usecase.SetupTest;
import todto.TaskDto;

public class ListTasksTest {
	
	private Service service = new ListTasks();
	private SetupTest setup  = new SetupTest();
	
	@Before public void setUp(){
		setup.setUp();
	}
	
	
	@Test public void wheCallWithoutOrder(){		
		String [] paramValue = new String[]{"no_order"};
		Response response = service.execute(paramValue);
		assertEquals(new TaskDto().toDto(response.getTasks()),new TaskDto().toDto(setup.getQueryTasks().query()));	
		assertEquals(response.getHttpResponseHeader(),HttpURLConnection.HTTP_OK);
		
	}
	
	@Test public void whenCallWithOrder(){
		String [] paramValue = new String[]{"terminated_first"};
		Response response = service.execute(paramValue);
		assertEquals(new TaskDto().toDto(response.getTasks()),new TaskDto().toDto(setup.getQueryTasks().queryWithTerminatedTaskFirst()));	
		assertEquals(response.getHttpResponseHeader(),HttpURLConnection.HTTP_OK);
	}

}
