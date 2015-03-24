package todolist.usecase.errors;

import todolist.constanst.StringConstants;
import todolist.entitys.Task;
import todolist.usecase.checkers.TaskStatusChecker;

public final class ModifyTaskError implements ModifyTodoListError {
		
	public String errorInTaskIs(Task task) {
		return new Errors(StringConstants.ERROR_MODIFY_TASK_TERMINATED, new TaskStatusChecker(Task.Status.TERMINATED)).errorInTaskIs(task);
	}
}
