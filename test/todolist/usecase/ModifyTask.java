package todolist.usecase;

import static org.junit.Assert.*;
import static utils.ResulstCreator.resultList;


import fixtures.ModifyTaskFixture;
import fixtures.QueryTasks;

import org.junit.Before;
import org.junit.Test;

import todolist.entitys.Task;


public class ModifyTask {
	
	private ModifyTaskFixture fixture = new ModifyTaskFixture();
	private SetupTest setUpTest = new SetupTest();
	private QueryTasks queryTasks = setUpTest.getQueryTasks();
		
	@Before
	public void setUp(){
		setUpTest.setUp();
	}
	
	//Given : tasks [|'task1','pending'|,|'task2,'terminated'|,|'task3','pending'|,|'task4','terminated'|]
	//When  : modify |'task3'|
	//Then  : Show [|'task1','pending'|,|'task2,'terminated'|,|'task3','terminated'|,|'task4','terminated'|]	
	@Test
	public void whenModifyTaskWithPendingStatus(){
		fixture.modifyTask("task3",Task.Status.TERMINATED);
		assertEquals(fixture.showError(),"");	
		assertEquals(resultList(new Task("task1",Task.Status.PENDING),new Task("task2",Task.Status.TERMINATED),
                                new Task("task3",Task.Status.TERMINATED),new Task("task4",Task.Status.TERMINATED)),queryTasks.query());		
	}
	
	//Given : tasks [|'task1','pending'|,|'task2,'terminated'|,|'task3','pending'|,|'task4','terminated'|]
	//When  : modify |'task2'|
	//Then  : Show 'Error : Tasks in terminated state canÂ´t modify'
	//And   : show  tasks [|'task1','pending'|,|'task2,'terminated'|,|'task3','pending'|,|'task4','terminated'|]
	@Test
	public void whenModifyTaskWithTerminatedStatus(){
		fixture.modifyTask("task2",Task.Status.PENDING);
		assertEquals(fixture.showError(),"Error : Tasks in terminated state can´t modify");	
		assertEquals(resultList(new Task("task1",Task.Status.PENDING),new Task("task2",Task.Status.TERMINATED),
                                new Task("task3",Task.Status.PENDING),new Task("task4",Task.Status.TERMINATED)),queryTasks.query());			
	}
}
