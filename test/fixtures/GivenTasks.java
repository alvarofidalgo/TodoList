package fixtures;

import todolist.entitys.Task;
import todolist.gateways.doubles.MemoryDb;

public final class GivenTasks {
		
    private String _taskDescription  ;
    private Task.Status _status;
    private MemoryDb _db;
    
    public GivenTasks(MemoryDb db){
    	_db = db;
    }
    
    public void setTaskDescription(String taskDescription){   	
    	_taskDescription = taskDescription;
    }
    
	public void setStatus(Task.Status status) {
		_status = status;
	}
	
	public void execute(){	
		Task task = new Task(_taskDescription,_status);
		_db.getTasks().add(task);		
	}
}
