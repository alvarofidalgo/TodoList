package todolist.usecase.checkers;

import todolist.entitys.Task;

public final class TaskDescriptionChecker implements Checker{
	
	private String _description ;
	
	public TaskDescriptionChecker(String description){
		_description = description;
	}
	
	public boolean isValid(Task task) {
		return _description.equals(task.getTaskDescription());
	}
}
