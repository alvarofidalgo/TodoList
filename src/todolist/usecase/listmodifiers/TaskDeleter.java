package todolist.usecase.listmodifiers;

import todolist.entitys.Task;
import todolist.gateways.Context;

public final class TaskDeleter implements TodoListModifier {

	public void modifiTodoListWithTask(Task task) {
		Context.taskOperations.delete(task);
	}
}
