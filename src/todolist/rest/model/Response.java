package todolist.rest.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import todolist.entitys.Task;

public final class Response implements Serializable{

	private static final long serialVersionUID = 1L;

	private String _error="";
	private List<Task> _tasks= new ArrayList<Task>();
	
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
}
