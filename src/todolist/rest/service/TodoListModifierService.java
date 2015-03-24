package todolist.rest.service;

import todolist.entitys.Task;
import todolist.rest.model.Response;
import todolist.rest.model.TaskRequest;
import todolist.usecase.ModifyTodoListUseCase;
import todolist.usecase.ModifyTodoListUseCaseFactory;
import todolist.usecase.PresentTasksUseCase;
import todolist.usecase.ModifyTodoListUseCaseFactory.TypeModifyTodoList;
import todolist.usecase.errors.ModifyTodoListError;
import todolist.usecase.errors.ModifyTodoListErrorFactory;
import todolist.usecase.errors.ModifyTodoListErrorFactory.TypeErrorModifyTodoList;


public final class TodoListModifierService  implements Service{
	
	private ModifyTodoListUseCase _useCase ;
	private ModifyTodoListError _useCaseError;
	
	public TodoListModifierService(TypeModifyTodoList typeModify,TypeErrorModifyTodoList typeError){
		_useCase = new ModifyTodoListUseCaseFactory().getTypeUseCase(typeModify);
		_useCaseError = new ModifyTodoListErrorFactory().getUseCaseError(typeError);
		
	}
	
	public Response execute(TaskRequest taskRequest) {
		Task task = taskRequest.buildTask(); 	
		Response response =new Response ();
		response.setError(_useCaseError.errorInTaskIs(task));
		_useCase.executeModificationsInTodoList(task);	
		response.setTasks(new PresentTasksUseCase().tasksShow(taskRequest.getOrder()));
		return response;		
	}
}
