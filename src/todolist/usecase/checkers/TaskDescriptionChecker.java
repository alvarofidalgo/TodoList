package todolist.usecase.checkers;

import todolist.entitys.Task;

public final class TaskDescriptionChecker implements Checker{
	
	private boolean _mandatoryEmpty;
	
	public TaskDescriptionChecker(boolean mandatoryEmpty){
		_mandatoryEmpty = mandatoryEmpty;
	}
	
	public boolean isValid(Task task) {
		boolean valid = !task.getTaskDescription().isEmpty();
		if (_mandatoryEmpty)
			valid =  task.getTaskDescription().isEmpty();
		return valid;
	}
}
