package todolist.usecase.configurer;

import todolist.entitys.Task;
import todolist.gateways.Context;
import todolist.usecase.checkers.TaskDescriptionChecker;
import todolist.usecase.checkers.TaskStatusChecker;
import todolist.usecase.listmodifiers.ModifyTodoListExecutor;
import todolist.usecase.listmodifiers.TaskInserter;
import todolist.usecase.listmodifiers.TaskModifier;

public final class InsertTaskConfigurer implements ExecutorConfigurer{
	
	public ModifyTodoListExecutor configureExecutorWithTask(ModifyTodoListExecutor executor, Task task) {
		 executor.addListModifierWithCheckers(new TaskInserter(),new TaskDescriptionChecker(""));
		 executor.addListModifierWithCheckers(new TaskModifier(),new TaskStatusChecker(Task.Status.TERMINATED),
                                                      new TaskDescriptionChecker(task.getTaskDescription()));		
		 executor.setTaskToInsert(task);
		 executor.setTaskToCheck(Context.taskOperations.getTaskWith(task.getTaskDescription()));
		 return executor;
	}
}
