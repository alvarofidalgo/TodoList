package fixtures;

import java.util.ArrayList;
import java.util.List;

import doubles.MemoryDb;
import todolist.entitys.Task;
import todto.TaskDto;
import utils.TaskInserter;

public final class QueryTasks {
	
	private MemoryDb _db;
	
	public QueryTasks(MemoryDb db){
		_db = db;
	}
	public List<Object> query(){
		return new TaskDto().toDto(_db.getTasks());
	}
	
	
	public List<Object> queryWithTerminatedTaskFirst() {
		TaskInserter taskInerter = new TaskInserter();
		List<Task> tasks = _db.getTasks();
		List<Task> terminatedTask = new ArrayList<Task>();
		List<Task> pendingTasks = new ArrayList<Task>();
		for (int index=0;index<tasks.size();index++){
			Task task = tasks.get(index);
			taskInerter.insertTaskInListIfSameStatus(terminatedTask,task,"terminated");
			taskInerter.insertTaskInListIfSameStatus(pendingTasks,task,"pending");	
		}

		return new TaskDto().toDto(taskInerter.appendListInOrder(terminatedTask, pendingTasks));
	}
}
