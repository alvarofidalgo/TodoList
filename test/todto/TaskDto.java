package todto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import todolist.entitys.Task;

public final class TaskDto {
	
	public List<Object> toDto(List<Task> taskList) {
	    List<Object> dto = new ArrayList<Object>();
	    for (Task task : taskList)
	    	dto.add(makeRow(task));
	    return dto;

	}
	
	private List<Object> makeRow(Task task) {
		    return list(
		      new Object[]{list("taskDescription", task.getTaskDescription()),
		    		  list("status", task.getStatus())}
		    );
    }
	
	private List<Object> list(Object... objects) {
	    return Arrays.asList(objects);
    }

}
