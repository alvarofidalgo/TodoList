package todolist.rest;

import todolist.rest.service.TaskService;
import todolist.usecase.SetupTest;

public final class MainRestServer {

	public static void main(String[] args) {
		new SetupTest().setUp();// this line will removed
		RestServer server = new RestServer(8000);	
		server.addServiceInContext("task", new TaskService());
		server.start();	
	}
}
