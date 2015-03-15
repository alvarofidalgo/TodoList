package todolist.rest.service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import todolist.rest.model.Response;
import todolist.rest.service.headers.HttpResponseHeadersFactory.TypeHttpCalculator;
import todolist.rest.utils.RequestExtractor;
import todolist.usecase.ModifyTodoListUseCaseFactory.TypeModifyTodoList;
import todolist.usecase.errors.ModifyTodoListErrorFactory.TypeErrorModifyTodoList;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;


public final class TaskService implements HttpHandler {
		
	private Map<String,Service> _services = new HashMap<String,Service>();
	
	public TaskService(){
		_services.put("GET", new ListTasks());
		_services.put("PUT", new TodoListModifierService(TypeModifyTodoList.INSERT_TASK,TypeErrorModifyTodoList.INSERT_TASK,TypeHttpCalculator.INSERT_TASK));
		_services.put("POST", new TodoListModifierService(TypeModifyTodoList.MODIFY_TASK,TypeErrorModifyTodoList.MODIFY_TASK,TypeHttpCalculator.MODIFY_OR_DELETE_TASK));		
		_services.put("DELETE", new TodoListModifierService(TypeModifyTodoList.DELETE_TASK,TypeErrorModifyTodoList.DELETE_TASK,TypeHttpCalculator.MODIFY_OR_DELETE_TASK));
		
	}
  		
	public void handle(HttpExchange httpExchange) throws IOException {
		RequestExtractor requestExtractor = new RequestExtractor(httpExchange);	
	    Response response = _services.get(requestExtractor.httpMethod()).execute(requestExtractor.obtainParametersValues());
	    writeResponse(httpExchange,response);
	}
	
	private void writeResponse(HttpExchange httpExchange,Response response) throws IOException{
		String jsonResponse = response.toJson();
        httpExchange.getResponseHeaders().add("Access-Control-Allow-Origin","null");       
        OutputStream os = httpExchange.getResponseBody();      
        httpExchange.sendResponseHeaders(response.getHttpResponseHeader(), jsonResponse.length());    
        os.write(jsonResponse.getBytes());
        os.close();		
	}
}
