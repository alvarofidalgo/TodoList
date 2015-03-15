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
	
	@Test
	public void whenNoTasks(){
		fixture.deleteDb();
		assertTrue(fixture.tasksShow().isEmpty());
	}

	@Test
	public void whenThereTasks(){			
		List<Task> tasks = fixture.tasksShow();
		assertEquals(new TaskDto().toDto(tasks),new TaskDto().toDto(queryTasks.query()));
		
	}

	@Test
	public void whenOrderTaskWithOrderTerminatedFisrtCondition(){
		List<Task> tasks = fixture.showFirstTerminatedTasks();
		assertEquals(new TaskDto().toDto(tasks),new TaskDto().toDto(queryTasks.queryWithTerminatedTaskFirst()));		
	}
}
