package todolist.usecase.errors;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import todolist.constanst.StringConstants;
import todolist.entitys.Task;
import todolist.usecase.SetupTest;
import todolist.usecase.errors.ModifyTodoListErrorFactory.TypeErrorModifyTodoList;


public class DeleteTaskErrorTest {
	
	private ModifyTodoListError useCaseError =  new ModifyTodoListErrorFactory().getUseCaseError(TypeErrorModifyTodoList.DELETE_TASK);
	private SetupTest setUpTest = new SetupTest();

	
	@Before public void setUp(){
		setUpTest.setUp();
	}

	@Test public void whenDeleteTerminatedTask(){
		assertEquals(useCaseError.errorInTaskIs(new Task("task2",Task.Status.TERMINATED)),"");
	}
	
	@Test public void whenDeletePendingTask(){
		assertEquals(useCaseError.errorInTaskIs(new Task("task1",Task.Status.PENDING)),StringConstants.ERROR_DELETE_PENDING_TASK);
	}
}
