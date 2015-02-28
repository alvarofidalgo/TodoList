package todolist.usecase.checkers;

import java.util.ArrayList;
import java.util.List;

import todolist.entitys.Task;

public final class ChecksLauncher {
	
	public enum LauncherType{INSERT,MODIFY,DELETE}
	
	private List<Checker> _checkers = new ArrayList<Checker>();
	private LauncherType _typeModify;
	
	public ChecksLauncher(LauncherType typeModify){
		_typeModify = typeModify;
	}
	
	public LauncherType typeModify(){
		return _typeModify;
	}
	
	public void addChecker(Checker checker){
		_checkers.add(checker);
	}
	
	public boolean isModificableTaskTo(Task task) {	
		boolean isValid = true;
		for (int index = 0;index<_checkers.size() && isValid;index++)
			isValid = isValid && _checkers.get(index).isValid(task);
		return isValid;
	}
}
