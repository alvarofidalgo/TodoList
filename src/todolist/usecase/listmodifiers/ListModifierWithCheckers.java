package todolist.usecase.listmodifiers;

import todolist.entitys.Task;
import todolist.usecase.checkers.Checker;
import todolist.usecase.checkers.CheckersLauncher;

public final class ListModifierWithCheckers  {
	
	private Task _taskToCheck;
	private Task _task;
	private TodoListModifier _modifier;
	private CheckersLauncher _checksLauncher = new CheckersLauncher();
	
	public ListModifierWithCheckers(TodoListModifier modifier,Checker... _checkers){	
		_modifier = modifier;
		_checksLauncher.addCheckers(_checkers);
	}

	public void changeTaskToCheck(Task taskToCheck){
		_taskToCheck = taskToCheck;
	}
	
	public void modifyTodoListWithTask(){
        if (_checksLauncher.taskPassAllChecks(_taskToCheck))		
        	_modifier.modifiTodoListWithTask(_task);
	}
	
	public void setTaskToInsertAndTaskToCheck(Task task) {
		_task = _taskToCheck = task;
	}
}
