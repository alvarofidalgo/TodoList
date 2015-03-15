package todolist.usecase.errors;

import todolist.entitys.Task;
import todolist.usecase.checkers.Checker;
import todolist.usecase.checkers.CheckersLauncher;

public final class Errors {
	
	private String _errorMessage;
	private CheckersLauncher _checksErrors = new CheckersLauncher();
	
	public Errors(String errorMessage,Checker... checkers){
		_errorMessage = errorMessage;
		_checksErrors.addCheckers(checkers);
	}

	public String errorInTaskIs(Task task) {
		if (_checksErrors.taskPassAllChecks(task))
			_errorMessage = "";
		return _errorMessage;
	}
}
