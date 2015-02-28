package todolist.entitys;

public final class Task {

	public enum Status {TERMINATED,PENDING};
		
    private String _taskDescription  ;
    private Status _status;

	public Task(String taskDescription,Status status) {
		_taskDescription = taskDescription;
		_status = status;
	}

	public String getTaskDescription() {
		return _taskDescription;
	}
	
	public Status getStatus(){
		return _status ;
	}
}
