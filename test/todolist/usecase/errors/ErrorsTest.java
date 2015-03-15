package todolist.usecase.errors;

import static org.junit.Assert.*;

import org.junit.Test;

import todolist.entitys.Task;
import todolist.usecase.checkers.TaskStatusChecker;

public class ErrorsTest {
	
	private Errors errors = new Errors("Error",new TaskStatusChecker(Task.Status.PENDING)); 
	
	
	@Test public void whenTaskIsPendingAndIsCorrect(){
		assertEquals(errors.errorInTaskIs(new Task("",Task.Status.PENDING)),"");	
	}
	
	@Test public void whenTaskIsPendingAndIsIncorrect(){
		assertEquals(errors.errorInTaskIs(new Task("",Task.Status.TERMINATED)),"Error");	
	}
}
