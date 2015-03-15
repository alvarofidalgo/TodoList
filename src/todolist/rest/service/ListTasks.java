package todolist.rest.service;

import sun.net.www.protocol.http.HttpURLConnection;
import todolist.rest.model.Response;
import todolist.usecase.PresentTasksUseCase;
import todolist.usecase.selector.PresentTaskGatewaySelector.TypeOrder;

public final class ListTasks implements Service {
	
	public Response execute(String [] valueParameters) {
        Response response = new Response();
        response.setTasks(new PresentTasksUseCase().tasksShow(TypeOrder.valueOf(valueParameters[0].toUpperCase())));
        response.setHttpResponseHeader(HttpURLConnection.HTTP_OK);
		return response;
	}
}
