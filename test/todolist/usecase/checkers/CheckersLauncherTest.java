package todolist.usecase.checkers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import todolist.entitys.Task;

public class CheckersLauncherTest {
	
	
	private CheckersLauncher launcher;
	
	@Before public void setUp(){
		launcher = new CheckersLauncher();
		launcher.addCheckers(new TaskDescriptionChecker("aaaa"),new TaskStatusChecker(Task.Status.TERMINATED));
	}
	
	
	@Test public void whenNotPassAnyCheck(){
		Task task = new Task("",Task.Status.TERMINATED);
		assertFalse(launcher.taskPassAllChecks(task));
	}
	
	@Test public void whenPassAllCheckers(){
		Task task = new Task("aaaa",Task.Status.TERMINATED);
		assertTrue(launcher.taskPassAllChecks(task));
	}

}
