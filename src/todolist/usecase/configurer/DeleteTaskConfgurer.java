package todolist.usecase.configurer;

import todolist.entitys.Task;
import todolist.usecase.checkers.TaskStatusChecker;
import todolist.usecase.listmodifiers.ModifyTodoListExecutor;
import todolist.usecase.listmodifiers.TaskDeleter;

public final class DeleteTaskConfgurer implements ExecutorConfigurer{
	
	public ModifyTodoListExecutor configureExecutorWithTask(ModifyTodoListExecutor executor, Task task) {
		executor.addListModifierWithCheckers(new TaskDeleter(), new TaskStatusChecker(Task.Status.TERMINATED));
		executor.setTaskToInsert(task);
		return executor;
	}
}
