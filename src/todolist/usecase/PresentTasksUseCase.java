package todolist.usecase;

import java.util.List;

import todolist.entitys.Task;
import todolist.gateways.Context;
import todolist.usecase.selector.PresentTaskGatewaySelector.TypeOrder;

public final class PresentTasksUseCase {
		
	public List<Task> tasksShow(TypeOrder order) {
		return Context.presentGatewaySelector.getAllTask(order);
	}
}
