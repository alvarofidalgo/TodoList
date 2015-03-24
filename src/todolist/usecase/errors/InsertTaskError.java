package todolist.usecase.errors;

import todolist.constanst.StringConstants;
import todolist.entitys.Task;
import todolist.gateways.Context;
import todolist.usecase.checkers.TaskStatusChecker;

public final class InsertTaskError implements ModifyTodoListError {
	
	public String errorInTaskIs(Task task){
		return new Errors(StringConstants.ERROR_TASK_WITH_SAME_DESCRIPTION,
                   new TaskStatusChecker(Task.Status.TERMINATED))
		               .errorInTaskIs(Context.taskOperations.getTaskWith(task.getTaskDescription()));
	}
}
