package todolist.usecase.selector;

import java.util.ArrayList;
import java.util.List;

import todolist.entitys.Task;
import todolist.gateways.PresentTasksGateway;

public final class PresentTerminatedTasksFirstGatewayMock  implements PresentTasksGateway{


	public List<Task> allTasks() {
		List<Task> tasks = new ArrayList<Task>();
		tasks.add(new Task("",Task.Status.PENDING));
		return tasks;
	}
}
