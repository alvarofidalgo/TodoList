package todolist.rest.service;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import todolist.entitys.Task;
import todolist.rest.FakeClient;
import todolist.rest.RestServer;
import todolist.rest.model.Response;
import todolist.usecase.SetupTest;


public class TaskServiceTest {
	

	private RestServer server;
	private String context ="task";
	private FakeClient client;
	private SetupTest setup = new SetupTest();
	private Response response;
	
	@Before public void setUp(){
		System.setProperty( "sun.net.http.allowRestrictedHeaders", "true");
		response = new Response();
		setup.setUp();
		int port =8000;
		client = new FakeClient(port);
		server = new RestServer(port);	
		client.addCollection(context);	
		server.addServiceInContext(context, new TaskService());
		server.start();			
	}
	
	@After public void shutDown(){
		server.stop();
	}
	
	@Test public void whenCallGetMethod() throws IOException{
		response.setTasks(setup.getQueryTasks().query());
		assertEquals(transformInputToString(client.createConnectionWithMethodAndService("GET","?order='no_order'").getInputStream()),response.toJson());
		
	}
	
	@Test public void whenCallPutMethod() throws MalformedURLException, IOException{
		response.setTasks(setup.getQueryTasks().query());
		assertEquals(transformInputToString(client.createConnectionWithMethodAndService("PUT","?order='no_order'&taskdescription='task5'&status='pending'").getInputStream()),
				                                   response.toJson());
		assertEquals(setup.getQueryTasks().query().get(4).getStatus(),Task.Status.PENDING);
	}
	
	@Test public void whenCallPostMethod() throws MalformedURLException, IOException{
		response.setTasks(setup.getQueryTasks().query());
		assertEquals(transformInputToString(client.createConnectionWithMethodAndService("POST","?order='no_order'&taskdescription='task1'&status='terminated'").getInputStream()),
				response.toJson());
		assertEquals(setup.getQueryTasks().query().get(0).getStatus(),Task.Status.TERMINATED);
	}
	
	@Test public void whenCallDeleteMethod() throws MalformedURLException, IOException{
		int length = setup.getQueryTasks().query().size();
		response.setTasks(setup.getQueryTasks().query());
		assertEquals(transformInputToString(client.createConnectionWithMethodAndService("DELETE","?order='no_order'&taskdescription='task2'&status='terminated'").getInputStream()),
				     response.toJson());
		assertEquals(length-1,setup.getQueryTasks().query().size());
	}
	
	
	private String transformInputToString(InputStream stream) throws IOException{
		
		StringBuilder sb = new StringBuilder();
		String line ;
	    BufferedReader br = new BufferedReader(new InputStreamReader(stream));
		while ((line = br.readLine()) != null) 
				sb.append(line);
		return sb.toString();
	}

}
