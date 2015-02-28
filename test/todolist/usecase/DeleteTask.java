package todolist.usecase;


import static org.junit.Assert.*;
import static utils.ResulstCreator.resultList;

import org.junit.Before;
import org.junit.Test;

import todolist.entitys.Task;

import fixtures.DeleteTaskFixture;
import fixtures.QueryTasks;


public class DeleteTask {
	
	private DeleteTaskFixture fixture = new DeleteTaskFixture();
	private SetupTest setUpTest = new SetupTest();
	private QueryTasks queryTasks = setUpTest.getQueryTasks();
	
	
	@Before public void setUp(){
		setUpTest.setUp();
	}

	 //Given : tasks [|’task1′,’pending’|,|’task2,’terminated’|,|’task3′,’pending’|,|’task4′,’terminated’|]
	// When : delete |’task2’|
	// then : show tasks [|’task1′,’pending’|,|’task3′,’pending’|,|’task4′,’terminated’|]
	@Test public void whenDeleteTerminatedTask(){
		fixture.delete("task2");
		assertEquals(fixture.showError(),"");
		assertEquals(resultList(new Task("task1",Task.Status.PENDING),new Task("task3",Task.Status.PENDING),
				                new Task("task4",Task.Status.TERMINATED)),queryTasks.query());
		
	}

	 //  Given : tasks [|’task1′,’pending’|,|’task2,’terminated’|,|’task3′,’pending’|,|’task4′,’terminated’|]
	 //  When : delete |’task1’|
	 //  Then : Show ‘Error : You can´t delete pending task’
	 //  And : show tasks [|’task1′,’pending’|,|’task2,’terminated’|,|’task3′,’pending’|,|’task4′,’terminated’|]
	@Test public void whenDeletePendingTask(){
		fixture.delete("task1");
		assertEquals(fixture.showError(),"Error:You can´t delete pending task");
		assertEquals(resultList(new Task("task1",Task.Status.PENDING),new Task("task2",Task.Status.TERMINATED),
                                new Task("task3",Task.Status.PENDING),new Task("task4",Task.Status.TERMINATED)),queryTasks.query());
	}
}
