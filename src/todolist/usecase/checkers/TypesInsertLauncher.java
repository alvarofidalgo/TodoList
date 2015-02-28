package todolist.usecase.checkers;

import java.util.HashMap;
import java.util.Map;

import todolist.entitys.Task;

public final class TypesInsertLauncher {
			
	public  Map<ChecksLauncher.LauncherType,ChecksLauncher> _launchers = new HashMap<ChecksLauncher.LauncherType,ChecksLauncher>();
	
	public TypesInsertLauncher(){
		ChecksLauncher.LauncherType [] types = ChecksLauncher.LauncherType.values();
		for (int index=0;index<types.length;_launchers.put(types[index],new ChecksLauncher(types[index++])));
	}
	
	public void addCheckerIn(ChecksLauncher.LauncherType typeModification,Checker checker){		
		ChecksLauncher launcher = _launchers.get(typeModification);
		launcher.addChecker(checker);	  
	}
	
	public boolean isModificableTaskToType(Task task,ChecksLauncher.LauncherType... typeModification) {	
		boolean isModificable = false;
		for (int index=0;index<typeModification.length;index++){
		    ChecksLauncher launcher = _launchers.get(typeModification[index]);
		    isModificable = isModificable||launcher.isModificableTaskTo(task);
		}
		return isModificable;
	}
}
