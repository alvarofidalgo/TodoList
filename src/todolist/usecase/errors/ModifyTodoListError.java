package todolist.usecase.errors;

import todolist.entitys.Task;

public interface ModifyTodoListError {

	public String errorInTaskIs(Task task);

}