package fixtures;

import java.util.List;

import todolist.entitys.Task;
import todolist.gateways.Context;
import todolist.usecase.PresentTasksUseCase;
import todolist.usecase.selector.PresentTaskGatewaySelector;

public final class PresentTaskFixture {

    private PresentTasksUseCase useCase = new PresentTasksUseCase();
        
	public List<Task> tasksShow() {
		return useCase.tasksShow(PresentTaskGatewaySelector.TypeOrder.NO_ORDER);
	}

	public List<Task> showFirstTerminatedTasks() {
		return useCase.tasksShow(PresentTaskGatewaySelector.TypeOrder.TERMINATED_FIRST);
	}
	
	public void deleteDb() {
		List<Task> dbTasks =  Context.presentGatewaySelector.getAllTask(PresentTaskGatewaySelector.TypeOrder.NO_ORDER); 
		while (!dbTasks.isEmpty())
			dbTasks.remove(0);
	}

}
