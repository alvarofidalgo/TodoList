package doubles;

import java.util.ArrayList;
import java.util.List;

import todolist.entitys.Task;
import todolist.gateways.PresentTasksGateway;
import utils.TaskInserter;

public final class InMemoryPresentFirstTerminatedTasks implements PresentTasksGateway {

	private MemoryDb _db;
	
	public InMemoryPresentFirstTerminatedTasks(MemoryDb db){
		_db = db;
	}
	
	
	public List<Task> allTasks() {
		TaskInserter taskInerter = new TaskInserter();
		List<Task> tasks = _db.getTasks();
		List<Task> terminatedTask = new ArrayList<Task>();
		List<Task> pendingTasks = new ArrayList<Task>();
		for (int index=0;index<tasks.size();index++){
			Task task = tasks.get(index);
			taskInerter.insertTaskInListIfSameStatus(terminatedTask,task,"terminated");
			taskInerter.insertTaskInListIfSameStatus(pendingTasks,task,"pending");	
		}
		return taskInerter.appendListInOrder(terminatedTask, pendingTasks);
	}
}
