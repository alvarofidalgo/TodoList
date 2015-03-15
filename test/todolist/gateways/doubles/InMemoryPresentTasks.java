package todolist.gateways.doubles;

import java.util.List;

import todolist.entitys.Task;
import todolist.gateways.PresentTasksGateway;

public final class InMemoryPresentTasks implements PresentTasksGateway {

	private MemoryDb _db;
	
	public InMemoryPresentTasks(MemoryDb db){
		_db = db;
	}
		
	public List<Task> allTasks() {		
		return _db.getTasks();
	}
}
