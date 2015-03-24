package todolist.usecase.listmodifiers;

import todolist.entitys.Task;
import todolist.gateways.Context;

public final class TaskInserter implements TodoListModifier {
	
	public void modifiTodoListWithTask(Task task){
		Context.taskOperations.insert(task);
	}

}
