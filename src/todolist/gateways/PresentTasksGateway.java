package todolist.gateways;

import java.util.List;

import todolist.entitys.Task;

public interface PresentTasksGateway {
	List<Task> allTasks();
}
