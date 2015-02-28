package todolist.usecase;

import todolist.entitys.Task;
import todolist.gateways.Context;
import todolist.usecase.checkers.ChecksLauncher;
import todolist.usecase.checkers.TaskStatusChecker;
import todolist.usecase.errors.Errors;

public final class ModifyTaskUseCase {
	
	private ModificableTodoListUseCase _modificableuseCase = new ModificableTodoListUseCase();
	
	public ModifyTaskUseCase(){
		_modificableuseCase.addCheckerToTypeModification(ChecksLauncher.LauncherType.MODIFY, new TaskStatusChecker(Task.Status.PENDING));
	}

	public String errorIs() {
		return _modificableuseCase.errorTypeToTypeModificationIs(
				              Errors.TypeErrors.MODIFY_TASK_TERMINATED, 
				                           ChecksLauncher.LauncherType.MODIFY);
	}
	
	public void modify() {
		if (_modificableuseCase.isTaskModificableToTypes(ChecksLauncher.LauncherType.MODIFY))
		     Context.taskOperations.modifyStatus(_modificableuseCase.getTask(ModificableTodoListUseCase.TypeTask.TASK_PERSIST));	
	}
	
	public void setTask(Task task) {
		_modificableuseCase.setTask(ModificableTodoListUseCase.TypeTask.TASK_TO_REPLACE,Context.taskOperations.getTaskWith(task.getTaskDescription()));
		_modificableuseCase.setTask(ModificableTodoListUseCase.TypeTask.TASK_PERSIST,task);      	
	}
}
