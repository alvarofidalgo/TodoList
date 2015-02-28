package todolist.gateways;

import todolist.entitys.Task;

public interface TaskOperations {

	void insert(Task task);

	Task getTaskWith(String taskDescription);

	void modifyStatus(Task task);

	void delete(Task task);

}
