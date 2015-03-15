package todolist.rest.service;

import todolist.entitys.Task;
import todolist.rest.model.Response;
import todolist.rest.service.headers.HttpResponseHeadersFactory;
import todolist.rest.service.headers.HttpResponseHeadersFactory.TypeHttpCalculator;
import todolist.rest.service.headers.HttpResponserHeaderCalculator;
import todolist.usecase.ModifyTodoListUseCase;
import todolist.usecase.ModifyTodoListUseCaseFactory;
import todolist.usecase.PresentTasksUseCase;
import todolist.usecase.ModifyTodoListUseCaseFactory.TypeModifyTodoList;
import todolist.usecase.errors.ModifyTodoListError;
import todolist.usecase.errors.ModifyTodoListErrorFactory;
import todolist.usecase.errors.ModifyTodoListErrorFactory.TypeErrorModifyTodoList;
import todolist.usecase.selector.PresentTaskGatewaySelector.TypeOrder;

public final class TodoListModifierService  implements Service{
	
	private ModifyTodoListUseCase _useCase ;
	private ModifyTodoListError _useCaseError;
	private HttpResponserHeaderCalculator _calculator;
	
	public TodoListModifierService(TypeModifyTodoList typeModify,TypeErrorModifyTodoList typeError, TypeHttpCalculator typeCalculator){
		_useCase = new ModifyTodoListUseCaseFactory().getTypeUseCase(typeModify);
		_useCaseError = new ModifyTodoListErrorFactory().getUseCaseError(typeError);
		_calculator = new HttpResponseHeadersFactory().getCalculator(typeCalculator);
		
	}
	
	public Response execute(String [] paramValues) {
		Task task = new Task(paramValues[1],Task.Status.valueOf(paramValues[2].toUpperCase())); 	
		Response response =new Response ();
		response.setError(_useCaseError.errorInTaskIs(task));
		_useCase.executeModificationsInTodoList(task);	
		response.setTasks(new PresentTasksUseCase().tasksShow(TypeOrder.valueOf(paramValues[0].toUpperCase())));
		response.setHttpResponseHeader(_calculator.calculateResponseHeader(response));
		return response;		
	}
	
}
