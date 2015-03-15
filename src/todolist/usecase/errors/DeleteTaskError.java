package todolist.usecase.errors;

import todolist.constanst.StringConstants;
import todolist.entitys.Task;
import todolist.usecase.checkers.TaskStatusChecker;

public final class DeleteTaskError implements ModifyTodoListError {
	
	public String errorInTaskIs(Task task) {
		return new Errors(StringConstants.ERROR_DELETE_PENDING_TASK,
                    new TaskStatusChecker(Task.Status.TERMINATED)).errorInTaskIs(task);
	}
}
