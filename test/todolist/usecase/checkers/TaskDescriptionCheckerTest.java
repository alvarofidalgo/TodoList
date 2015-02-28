package todolist.usecase.checkers;

import static org.junit.Assert.*;

import org.junit.Test;

import todolist.entitys.Task;
import todolist.usecase.checkers.Checker;
import todolist.usecase.checkers.TaskDescriptionChecker;

public class TaskDescriptionCheckerTest {
	
	
	@Test public void whenDescriptionHaveBeenEmptyButNotempty(){
		   boolean mandatoryempty = true;
		   Checker checker = new TaskDescriptionChecker(mandatoryempty);
		   Task task = new Task("task1",Task.Status.PENDING);
		   assertFalse(checker.isValid(task));
	}
	
	@Test public void whenDescriptionHaveBeenEmptyAndEmpty(){
		   boolean mandatoryempty = true;
		   Checker checker = new TaskDescriptionChecker(mandatoryempty);
		   Task task = new Task("",Task.Status.PENDING);
		   assertTrue(checker.isValid(task));
	}
	
	@Test public void whenDescriptionHavenNotBeenEmptyAndEmpty(){
		   boolean mandatoryempty = false;
		   Checker checker = new TaskDescriptionChecker(mandatoryempty);
		   Task task = new Task("",Task.Status.PENDING);
		   assertFalse(checker.isValid(task));
	}
	
	@Test public void whenDescriptionHaveNotBeenEmptyAndNotempty(){
		   boolean mandatoryempty = false;
		   Checker checker = new TaskDescriptionChecker(mandatoryempty);
		   Task task = new Task("Task1",Task.Status.PENDING);
		   assertTrue(checker.isValid(task));
	}

}
