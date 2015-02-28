package todolist.usecase;

import todolist.entitys.Task;
import todolist.gateways.Context;
import todolist.usecase.checkers.ChecksLauncher;
import todolist.usecase.checkers.TaskDescriptionChecker;
import todolist.usecase.checkers.TaskStatusChecker;
import todolist.usecase.errors.Errors;

public final class InsertTaskUseCase {
	
	private ModificableTodoListUseCase _modificableuseCase = new ModificableTodoListUseCase();
	
	public InsertTaskUseCase(){
		 boolean mandatoryEmpty = true;
		_modificableuseCase.addCheckerToTypeModification(ChecksLauncher.LauncherType.INSERT, new TaskDescriptionChecker(mandatoryEmpty));
		_modificableuseCase.addCheckerToTypeModification(ChecksLauncher.LauncherType.MODIFY, new TaskStatusChecker(Task.Status.TERMINATED));
	}

	public String errorIs() {	
		return _modificableuseCase.errorTypeToTypeModificationIs(
				                Errors.TypeErrors.TASK_WITH_SAME_DESCRIPTION, 
				                      ChecksLauncher.LauncherType.INSERT, 
				                           ChecksLauncher.LauncherType.MODIFY);
	}
	
	public void insertTask() {	
	   Task taskTOpersis = _modificableuseCase.getTask(ModificableTodoListUseCase.TypeTask.TASK_PERSIST);
	   Context.taskOperations.modifyStatus(taskTOpersis);
	   if (_modificableuseCase.isTaskModificableToTypes(ChecksLauncher.LauncherType.INSERT))
	        Context.taskOperations.insert(taskTOpersis);
	}
	
	public void setTaskDescription(String description){
		_modificableuseCase.setTask(ModificableTodoListUseCase.TypeTask.TASK_PERSIST, new Task(description,Task.Status.PENDING));
		_modificableuseCase.setTask(ModificableTodoListUseCase.TypeTask.TASK_TO_REPLACE,Context.taskOperations.getTaskWith(description));
	}
}
