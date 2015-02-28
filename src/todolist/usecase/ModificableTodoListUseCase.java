package todolist.usecase;

import java.util.HashMap;
import java.util.Map;

import todolist.entitys.Task;
import todolist.usecase.checkers.Checker;
import todolist.usecase.checkers.ChecksLauncher;
import todolist.usecase.checkers.TypesInsertLauncher;
import todolist.usecase.errors.Errors;

public final class ModificableTodoListUseCase {

	public enum TypeTask{TASK_PERSIST,TASK_TO_REPLACE}
	
	private TypesInsertLauncher _launcher = new TypesInsertLauncher();
	private Errors _errors = new Errors();
	private Map<TypeTask,Task> typeTasks = new HashMap<TypeTask,Task>();

		
	public void addCheckerToTypeModification(ChecksLauncher.LauncherType typeModification,Checker checker){
		_launcher.addCheckerIn(typeModification, checker);
	}
		
	public String errorTypeToTypeModificationIs(Errors.TypeErrors typeError,ChecksLauncher.LauncherType... types){
		return _errors.showExistErrorTo(typeError, !isTaskModificableToTypes(types));
	}
	
	public Task getTask(TypeTask typetask){
		Task task = typeTasks.get(typetask);
		if (task==null)
			task = new Task("",Task.Status.TERMINATED);
		return task;		
	}

	
	public boolean isTaskModificableToTypes(ChecksLauncher.LauncherType... typeModification){
		return _launcher.isModificableTaskToType( getTask(TypeTask.TASK_TO_REPLACE),typeModification);
	}
	
	public void setTask(TypeTask typetask,Task task){
		typeTasks.put(typetask, task);
	}
	

}
