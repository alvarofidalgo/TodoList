package todolist.usecase.checkers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import todolist.entitys.Task;
import todolist.usecase.checkers.ChecksLauncher;
import todolist.usecase.checkers.TaskDescriptionChecker;
import todolist.usecase.checkers.TaskStatusChecker;
import todolist.usecase.checkers.TypesInsertLauncher;

public class TypesInsertLauncherTest {
	
	private TypesInsertLauncher launcher;
	
	@Before public void setUp(){
		boolean mandatoryEmpty = true;
		launcher =  new TypesInsertLauncher();		
		launcher.addCheckerIn(ChecksLauncher.LauncherType.INSERT, new TaskDescriptionChecker(mandatoryEmpty));
		launcher.addCheckerIn(ChecksLauncher.LauncherType.MODIFY, new TaskStatusChecker(Task.Status.TERMINATED));
	}
		
	@Test public void whenTypeInsertAndEmptyTaskDescription(){
		Task task = new Task("",Task.Status.PENDING);
		assertTrue(launcher.isModificableTaskToType(task,ChecksLauncher.LauncherType.INSERT));		
	}
	
	@Test public void whenTypeInsertAndNotEmptyTaskDescription(){
		Task task = new Task("task1",Task.Status.TERMINATED);
		assertFalse(launcher.isModificableTaskToType(task,ChecksLauncher.LauncherType.INSERT));		
	}	
    
	@Test public void whenTypeModifyAndTerminetedStatus(){
		Task task = new Task("task",Task.Status.TERMINATED);
		assertTrue(launcher.isModificableTaskToType(task,ChecksLauncher.LauncherType.MODIFY));
    }
    
    
	@Test public void whenTypeModifyAndPendingStatus(){
    	Task task = new Task("task",Task.Status.PENDING);
    	assertFalse(launcher.isModificableTaskToType(task,ChecksLauncher.LauncherType.MODIFY));
    }
	
	
	@Test public void whenTypeModifyAndTypeInsertAndTerminatedStatus(){
		Task task = new Task("task",Task.Status.TERMINATED);
		assertTrue(launcher.isModificableTaskToType(task,ChecksLauncher.LauncherType.MODIFY,ChecksLauncher.LauncherType.INSERT));
	}
}
