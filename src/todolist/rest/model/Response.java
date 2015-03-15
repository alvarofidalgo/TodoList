package todolist.rest.model;

import java.util.ArrayList;
import java.util.List;

import todolist.entitys.Task;
import todolist.rest.utils.JSONParser;

public final class Response {
	
	private String _error="";
	private List<Task> _tasks= new ArrayList<Task>();
	private int _httpResponse = 0;
	
	public String getError() {
		return _error;
	}
	public void setError(String error) {
		_error = error;
	}
	public List<Task> getTasks() {
		return _tasks;
	}
	public void setTasks(List<Task> tasks) {
		_tasks = tasks;
	}
	
	public void setHttpResponseHeader(int httpResponse) {
		_httpResponse = httpResponse;
	}
	
	public int getHttpResponseHeader(){
		return _httpResponse;
	}
	
	public String toJson(){
		return "{tasks:"+new JSONParser().toStringJSON(_tasks)+","+
	           "{"+_error+"}";
				
	}
}
