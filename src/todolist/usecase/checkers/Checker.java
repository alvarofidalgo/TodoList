package todolist.usecase.checkers;

import todolist.entitys.Task;

public interface Checker {

	public abstract boolean isValid(Task task);

}