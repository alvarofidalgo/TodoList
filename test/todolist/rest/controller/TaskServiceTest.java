package todolist.rest.controller;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import todolist.entitys.Task;
import todolist.rest.FakeClient;
import todolist.rest.RestServerSpring;
import todolist.rest.model.Response;
import todolist.usecase.SetupTest;

public class TaskServiceTest {
	
	private  Response response;
	private SetupTest setup = new SetupTest();
	private FakeClient client;
	private RestServerSpring serverSPring;
	private ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();;
	
	public TaskServiceTest(){
		serverSPring  = new RestServerSpring();
		serverSPring.start(new String[]{});
	}
	
	
	@Before public void setUp(){
		try{
		 	setup.setUp();
         
		 	client = new FakeClient(8080);
		 	client.addCollection("task");	
		 	response = new Response();
		}catch(Exception e){
		 	e.printStackTrace();
		}
	}
	
	@After public void shutDown(){
		serverSPring.stop();
	}
	
	@Test public void whenCallGetMethod() throws IOException{
		response.setTasks(setup.getQueryTasks().query());
		assertEquals(transformInputToString(client.createConnectionWithMethodAndService("GET","?order=no_order").getInputStream()),ow.writeValueAsString(response));
		assertEquals(client.responseCodeIs(),HttpURLConnection.HTTP_OK);		
	}
	
	@Test public void whenCallPutMethod() throws MalformedURLException, IOException{
		response.setTasks(setup.getQueryTasks().query());
		assertEquals(transformInputToString(client.createConnectionWithMethodAndService("PUT","?order=no_order&taskdescription=task5&status=pending").getInputStream()),
				                             ow.writeValueAsString(response));
		assertEquals(setup.getQueryTasks().query().get(4).getStatus(),Task.Status.PENDING);
		assertEquals(client.responseCodeIs(),HttpURLConnection.HTTP_CREATED);
	}
	
	@Test public void whenCallPutMethodWithError() throws MalformedURLException, IOException{
	  client.createConnectionWithMethodAndService("PUT","?order=no_order&taskdescription=task1&status=pending");
	  assertEquals(client.responseCodeIs(),HttpURLConnection.HTTP_NOT_MODIFIED);	  
              
	}
	
	@Test public void whenCallPostMethod() throws MalformedURLException, IOException{
		response.setTasks(setup.getQueryTasks().query());
		assertEquals(transformInputToString(client.createConnectionWithMethodAndService("POST","?order=no_order&taskdescription=task1&status=terminated").getInputStream()),
				ow.writeValueAsString(response));
		assertEquals(setup.getQueryTasks().query().get(0).getStatus(),Task.Status.TERMINATED);
		assertEquals(client.responseCodeIs(),HttpURLConnection.HTTP_OK);
	}
	
	
	@Test public void whenCallPostMethodWithError() throws MalformedURLException, IOException{
		client.createConnectionWithMethodAndService("POST","?order=no_order&taskdescription=task2&status=pending");		
		assertEquals(client.responseCodeIs(),HttpURLConnection.HTTP_NOT_MODIFIED);
	}
	
	
	@Test public void whenCallDeleteMethod() throws MalformedURLException, IOException{
		int length = setup.getQueryTasks().query().size();
		response.setTasks(setup.getQueryTasks().query());
		assertEquals(transformInputToString(client.createConnectionWithMethodAndService("DELETE","?order=no_order&taskdescription=task2&status=terminated").getInputStream()),
				       ow.writeValueAsString(response));
		assertEquals(length-1,setup.getQueryTasks().query().size());
		assertEquals(client.responseCodeIs(),HttpURLConnection.HTTP_OK);
	}
	
	@Test public void whenCallDeleteMethodWithError() throws MalformedURLException, IOException{
		client.createConnectionWithMethodAndService("DELETE","?order=no_order&taskdescription=task1&status=pending");
		assertEquals(client.responseCodeIs(),HttpURLConnection.HTTP_NOT_MODIFIED);
	}
	
	
	private String transformInputToString(InputStream stream) throws IOException{
		
		StringBuilder sb = new StringBuilder();
		String line ;
	    BufferedReader br = new BufferedReader(new InputStreamReader(stream));
		while ((line = br.readLine()) != null) 
				sb.append(line).append("\n");
		return sb.delete(sb.length()-1, sb.length()).toString();
	}
}
