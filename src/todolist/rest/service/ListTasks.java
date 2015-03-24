package todolist.rest.service;

import todolist.rest.model.Response;
import todolist.rest.model.TaskRequest;
import todolist.usecase.PresentTasksUseCase;

public final class ListTasks implements Service {
	
	public Response execute(TaskRequest taskRequest) {
        Response response = new Response();
        response.setTasks(new PresentTasksUseCase().tasksShow(taskRequest.getOrder()));
		return response;
	}
	
}
