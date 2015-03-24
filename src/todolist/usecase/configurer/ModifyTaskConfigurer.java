package todolist.usecase.configurer;

import todolist.entitys.Task;
import todolist.usecase.checkers.TaskStatusChecker;
import todolist.usecase.listmodifiers.ModifyTodoListExecutor;
import todolist.usecase.listmodifiers.TaskModifier;

public final class ModifyTaskConfigurer implements ExecutorConfigurer {

	
	public ModifyTodoListExecutor configureExecutorWithTask(ModifyTodoListExecutor executor,Task task){
		executor.addListModifierWithCheckers(new TaskModifier(),new TaskStatusChecker(Task.Status.TERMINATED));		
		executor.setTaskToInsert(task);
		return executor;
	}
}
