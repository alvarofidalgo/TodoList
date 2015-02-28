package todolist.usecase;

import todolist.entitys.Task;
import todolist.gateways.Context;
import todolist.usecase.checkers.ChecksLauncher;
import todolist.usecase.checkers.TaskStatusChecker;
import todolist.usecase.errors.Errors;

public final class DeleteTaskUseCase {
	
	private ModificableTodoListUseCase _modificableuseCase = new ModificableTodoListUseCase();
	
	public DeleteTaskUseCase(){
		_modificableuseCase.addCheckerToTypeModification(ChecksLauncher.LauncherType.DELETE, new TaskStatusChecker(Task.Status.TERMINATED));
	}

	public void deleteTask() {
		if (_modificableuseCase.isTaskModificableToTypes(ChecksLauncher.LauncherType.DELETE))
		   Context.taskOperations.delete(_modificableuseCase.getTask(ModificableTodoListUseCase.TypeTask.TASK_PERSIST));
	}

	public String errorIs() {
		 return _modificableuseCase.errorTypeToTypeModificationIs(
				                  Errors.TypeErrors.DELETE_PENDING_TASK, 
				                          ChecksLauncher.LauncherType.DELETE);
	}
	
	public void setTaskDescription(String taskDescription) {
		_modificableuseCase.setTask(ModificableTodoListUseCase.TypeTask.TASK_PERSIST, new Task(taskDescription,Task.Status.TERMINATED));
		_modificableuseCase.setTask(ModificableTodoListUseCase.TypeTask.TASK_TO_REPLACE, Context.taskOperations.getTaskWith(taskDescription));
	}
}
