package todolist.usecase.checkers;

import java.util.ArrayList;
import java.util.List;

import todolist.entitys.Task;

public final class CheckersLauncher {
		
	private List<Checker> _checkers = new ArrayList<Checker>();
	
	public void addCheckers(Checker... checkers) {
	       for (int index = 0;index<checkers.length;_checkers.add(checkers[index++]));    
		}

	public boolean taskPassAllChecks(Task task) {
		boolean resultChecks = true;
        for (int index = 0;index<_checkers.size() && resultChecks;index++)
        	resultChecks = _checkers.get(index).isValid(task);
		return resultChecks;
	}
}
