package todolist.usecase.checkers;

import todolist.entitys.Task;

public final class TaskStatusChecker implements Checker {
	
	private Task.Status _status;
	
	public TaskStatusChecker(Task.Status status){
		_status = status;
	}

	public boolean isValid(Task task){
		return _status.equals(task.getStatus());
	}

}
