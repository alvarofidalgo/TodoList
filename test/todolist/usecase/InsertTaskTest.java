package todolist.usecase;

import static org.junit.Assert.*;
import static utils.ResulstCreator.resultList;

import org.junit.Before;
import org.junit.Test;

import todolist.entitys.Task;
import todolist.usecase.ModifyTodoListUseCaseFactory.TypeModifyTodoList;
import todto.TaskDto;
import fixtures.QueryTasks;


public class InsertTaskTest {
	
	private ModifyTodoListUseCase useCase = new ModifyTodoListUseCaseFactory().getTypeUseCase(TypeModifyTodoList.INSERT_TASK);
	private SetupTest setUpTest = new SetupTest();
	private QueryTasks queryTasks = setUpTest.getQueryTasks();
		
	@Before
	public void setUp(){
		setUpTest.setUp();
	}

	@Test
	public void whenInsertTaskThatNotExistInListTasks(){
			

		useCase.executeModificationsInTodoList(new Task("task5",Task.Status.PENDING));	
		assertEquals(resultList(new Task("task1",Task.Status.PENDING),new Task("task2",Task.Status.TERMINATED),
				                new Task("task3",Task.Status.PENDING),new Task("task4",Task.Status.TERMINATED),
				                new Task("task5",Task.Status.PENDING)),new TaskDto().toDto(queryTasks.query()));
	}

	@Test
	public void whenInsertTaskWithSameDescriptionThatOtherPendingTask(){
		
		useCase.executeModificationsInTodoList(new Task("task1",Task.Status.PENDING));	
		assertEquals(resultList(new Task("task1",Task.Status.PENDING),new Task("task2",Task.Status.TERMINATED),
                                new Task("task3",Task.Status.PENDING),new Task("task4",Task.Status.TERMINATED)),
                                new TaskDto().toDto(queryTasks.query()));
	}
	
	@Test
	public void whenInsertTaskWithSameDescriptionThatOtherTerminatedTask(){
		useCase.executeModificationsInTodoList(new Task("task2",Task.Status.PENDING));	
		assertEquals(resultList(new Task("task1",Task.Status.PENDING),new Task("task2",Task.Status.PENDING),
                                new Task("task3",Task.Status.PENDING),new Task("task4",Task.Status.TERMINATED)),
                                new TaskDto().toDto(queryTasks.query()));
	}
}
