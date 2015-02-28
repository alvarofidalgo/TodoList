package utils;

import java.util.ArrayList;
import java.util.List;

import todolist.entitys.Task;
import todto.TaskDto;

public class ResulstCreator {
	
	static public List<Object> resultList(Task... tasks){
		List<Task> listTask = new ArrayList<Task>();	
		for (int index = 0;index<tasks.length;index++)
			listTask.add(tasks[index]);
		return new TaskDto().toDto(listTask);
	}

}
