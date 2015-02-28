package todolist.usecase;

import static org.junit.Assert.*;
import static utils.ResulstCreator.resultList;

import org.junit.Before;
import org.junit.Test;

import todolist.entitys.Task;
import fixtures.InsertTaskFixture;
import fixtures.QueryTasks;

public class InsertTask {
	
	private InsertTaskFixture fixture = new InsertTaskFixture();
	private SetupTest setUpTest = new SetupTest();
	private QueryTasks queryTasks = setUpTest.getQueryTasks();
		
	@Before
	public void setUp(){
		setUpTest.setUp();
	}

	@Test
	public void whenInsertTaskThatNotExistInListTasks(){
		fixture.insertTask("task5");
		assertEquals(resultList(new Task("task1",Task.Status.PENDING),new Task("task2",Task.Status.TERMINATED),
				                new Task("task3",Task.Status.PENDING),new Task("task4",Task.Status.TERMINATED),
				                new Task("task5",Task.Status.PENDING)),queryTasks.query());
	}

	@Test
	public void whenInsertTaskWithSameDescriptionThatOtherPendingTask(){
		fixture.insertTask("task1");
		assertEquals(fixture.showError(),"Error : there is one task with same description");
		assertEquals(resultList(new Task("task1",Task.Status.PENDING),new Task("task2",Task.Status.TERMINATED),
                                new Task("task3",Task.Status.PENDING),new Task("task4",Task.Status.TERMINATED)),
                     queryTasks.query());
	}
	
	@Test
	public void whenInsertTaskWithSameDescriptionTatOtherTerminatedTask(){
		fixture.insertTask("task2");
		assertEquals(fixture.showError(),"");
		assertEquals(resultList(new Task("task1",Task.Status.PENDING),new Task("task2",Task.Status.PENDING),
                                new Task("task3",Task.Status.PENDING),new Task("task4",Task.Status.TERMINATED)),queryTasks.query());
	}
}
