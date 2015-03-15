package todolist.usecase.configurer;

import todolist.entitys.Task;
import todolist.usecase.listmodifiers.ModifyTodoListExecutor;

public interface ExecutorConfigurer {

	ModifyTodoListExecutor configureExecutorWithTask(ModifyTodoListExecutor executor, Task task);

}