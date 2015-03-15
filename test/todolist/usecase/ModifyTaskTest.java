package todolist.usecase;

import static org.junit.Assert.*;
import static utils.ResulstCreator.resultList;
import fixtures.QueryTasks;
import org.junit.Before;
import org.junit.Test;
import todolist.entitys.Task;
import todolist.usecase.ModifyTodoListUseCaseFactory.TypeModifyTodoList;
import todto.TaskDto;


public class ModifyTaskTest {
	
	private ModifyTodoListUseCase useCase = new ModifyTodoListUseCaseFactory().getTypeUseCase(TypeModifyTodoList.MODIFY_TASK);

	private SetupTest setUpTest = new SetupTest();
	private QueryTasks queryTasks = setUpTest.getQueryTasks();
		
	@Before
	public void setUp(){
		setUpTest.setUp();
	}
	
	@Test
	public void whenModifyTaskWithPendingStatus(){

		useCase.executeModificationsInTodoList(new Task("task3",Task.Status.TERMINATED));
		assertEquals(resultList(new Task("task1",Task.Status.PENDING),new Task("task2",Task.Status.TERMINATED),
                                new Task("task3",Task.Status.TERMINATED),new Task("task4",Task.Status.TERMINATED)),new TaskDto().toDto(queryTasks.query()));		
	}
	
	@Test
	public void whenModifyTaskWithTerminatedStatus(){
		useCase.executeModificationsInTodoList(new Task("task2",Task.Status.PENDING));	
		assertEquals(resultList(new Task("task1",Task.Status.PENDING),new Task("task2",Task.Status.TERMINATED),
                                new Task("task3",Task.Status.PENDING),new Task("task4",Task.Status.TERMINATED)),new TaskDto().toDto(queryTasks.query()));		
	}
}
