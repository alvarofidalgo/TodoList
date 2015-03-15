package todolist.usecase.errors;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import todolist.constanst.StringConstants;
import todolist.entitys.Task;
import todolist.usecase.SetupTest;
import todolist.usecase.errors.ModifyTodoListErrorFactory.TypeErrorModifyTodoList;


public class ModifyTaskErrorTest {
	
	private ModifyTodoListError useCase = new ModifyTodoListErrorFactory().getUseCaseError(TypeErrorModifyTodoList.MODIFY_TASK);
	private SetupTest setUpTest = new SetupTest();
		
	@Before
	public void setUp(){
		setUpTest.setUp();
	}
	
	@Test
	public void whenModifyTaskWithPendingStatus(){

		assertEquals(useCase.errorInTaskIs(new Task("task3",Task.Status.TERMINATED)),"");	
		
	}
	
	@Test
	public void whenModifyTaskWithTerminatedStatus(){
		assertEquals(useCase.errorInTaskIs(new Task("task2",Task.Status.PENDING)),StringConstants.ERROR_MODIFY_TASK_TERMINATED);	
	}
}
