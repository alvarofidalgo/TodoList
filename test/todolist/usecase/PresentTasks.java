package todolist.usecase;

import static org.junit.Assert.*;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import todolist.entitys.Task;
import todto.TaskDto;
import fixtures.PresentTaskFixture;
import fixtures.QueryTasks;

public class PresentTasks {
	
	private PresentTaskFixture fixture = new PresentTaskFixture();
	private SetupTest setUpTest = new SetupTest();
	private QueryTasks queryTasks = setUpTest.getQueryTasks();
		
	@Before
	public void setUp(){
		setUpTest.setUp();
	}
	
	@After
	public void shutDown(){
		fixture.deleteDb();	
	}
	
	
	//Given : tasks  []
	//When : init 
	//Then : show  []
	@Test
	public void whenNoTasks(){
		fixture.deleteDb();
		assertTrue(fixture.tasksShow().isEmpty());
	}

	//Given : tasks [|'task1','pending'|,|'task2,'terminated'|,|'task3','pending'|,|'task4','terminated'|]
	//When : init
	//Then : show  [|'task1','pending'|,|'task2,'terminated'|,|'task3','pending'|,|'task4','terminated'|]
	@Test
	public void whenThereTasks(){			
		List<Task> tasks = fixture.tasksShow();
		assertEquals(new TaskDto().toDto(tasks),queryTasks.query());
		
	}
	
	//Given : tasks [|'task1','pending'|,|'task2,'terminated'|,|'task3','pending'|,|'task4','terminated'|]
	//When : Order task with terminated first condition
	//Then :Show [|task2,'terminated'|,|'task4',terminated|,|'task1','pending'|,|'task3','pending'|]
	@Test
	public void whenOrderTaskWithOrderTerminatedFisrtCondition(){
		List<Task> tasks = fixture.showFirstTerminatedTasks();
		assertEquals(new TaskDto().toDto(tasks),queryTasks.queryWithTerminatedTaskFirst());		
	}
}
