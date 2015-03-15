package todolist.usecase.errors;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import todolist.constanst.StringConstants;
import todolist.entitys.Task;
import todolist.usecase.SetupTest;
import todolist.usecase.errors.ModifyTodoListErrorFactory.TypeErrorModifyTodoList;



public class InsertTaskErrorTest {
	
	private ModifyTodoListError error = new ModifyTodoListErrorFactory().getUseCaseError(TypeErrorModifyTodoList.INSERT_TASK);
	private SetupTest setUpTest = new SetupTest();
		
	@Before
	public void setUp(){
		setUpTest.setUp();
	}

	@Test
	public void whenInsertTaskThatNotExistInListTasks(){
				
		assertEquals(error.errorInTaskIs(new Task("task5",Task.Status.PENDING)),"");

	}

	@Test
	public void whenInsertTaskWithSameDescriptionThatOtherPendingTask(){
		assertEquals(error.errorInTaskIs(new Task("task1",Task.Status.PENDING)),StringConstants.ERROR_TASK_WITH_SAME_DESCRIPTION);
	}
	
	@Test
	public void whenInsertTaskWithSameDescriptionThatOtherTerminatedTask(){
		assertEquals(error.errorInTaskIs(new Task("task2",Task.Status.PENDING)),"");
	}
}
