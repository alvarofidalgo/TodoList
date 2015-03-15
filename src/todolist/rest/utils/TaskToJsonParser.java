package todolist.rest.utils;

import todolist.entitys.Task;

public final class TaskToJsonParser {
	
	public String parser(Task task){
		return "{taskDescription:'"+task.getTaskDescription()+"',status:"+ task.getStatus().toString().toLowerCase()+"}";
	}

}
