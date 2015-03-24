package todolist.usecase.listmodifiers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import todolist.entitys.Task;
import todolist.usecase.SetupTest;
import todolist.usecase.checkers.TaskStatusChecker;

public class ListModifierWithCheckersTest {
	
	private SetupTest setUp = new SetupTest();
	private int lengthAfterInsert;
	private Task taskToInsert = new Task("testTask",Task.Status.TERMINATED);
	
	@Before public void setUp(){
		setUp.setUp();	
		lengthAfterInsert = setUp.getQueryTasks().query().size();
		
	}

	
	@Test public void whenTaskToInsertAndTaskToCheckAreDiferent(){			
		ListModifierWithCheckers  modifier = new ListModifierWithCheckers (new TaskInserter(),new TaskStatusChecker(Task.Status.PENDING));
		modifier.setTaskToInsertAndTaskToCheck(taskToInsert);
		modifier.changeTaskToCheck(new Task("",Task.Status.PENDING));
		modifier.modifyTodoListWithTask();
		assertEquals(setUp.getQueryTasks().query().get(lengthAfterInsert).getTaskDescription(),"testTask");		
	}
	
	@Test public void whenTaskToInsertAndTaskToCheckAreTheSame(){
		ListModifierWithCheckers modifier = new ListModifierWithCheckers (new TaskInserter(),new TaskStatusChecker(Task.Status.TERMINATED));
		modifier.setTaskToInsertAndTaskToCheck(taskToInsert);
		modifier.modifyTodoListWithTask();
		assertEquals(setUp.getQueryTasks().query().get(lengthAfterInsert).getTaskDescription(),"testTask");	
	}
}
