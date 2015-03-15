package todolist.usecase;

import todolist.entitys.Task;
import todolist.usecase.configurer.ExecutorConfigurer;
import todolist.usecase.listmodifiers.ModifyTodoListExecutor;

public final class ModifyTodoListUseCase {
		
	private ExecutorConfigurer _configurer;
	
	public ModifyTodoListUseCase(ExecutorConfigurer configurer){
		_configurer = configurer;
	}
		
	public void executeModificationsInTodoList(Task task){		
		 ModifyTodoListExecutor executor =  _configurer.configureExecutorWithTask(new ModifyTodoListExecutor(),task);
		 executor.executeModificationsInTodoList();
	}
}
