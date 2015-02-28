package todolist.usecase.selector;

import java.util.ArrayList;
import java.util.List;

import todolist.entitys.Task;
import todolist.gateways.PresentTasksGateway;

public final class PresentNoOrderTasksGatewayMock implements PresentTasksGateway{


	public List<Task> allTasks() {
	    List<Task> emptyTask = new ArrayList<Task>();
		return emptyTask;
	}

}
