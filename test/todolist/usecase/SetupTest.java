package todolist.usecase;

import todolist.entitys.Task;
import todolist.gateways.Context;
import todolist.usecase.selector.PresentTaskGatewaySelector;
import doubles.InMemoryPresentFirstTerminatedTasks;
import doubles.InMemoryPresentTasks;
import doubles.InMemoryTaskOperations;
import doubles.MemoryDb;
import fixtures.GivenTasks;
import fixtures.QueryTasks;


public final class SetupTest {
	
	private MemoryDb db= new MemoryDb();
	private QueryTasks queryTasks = new QueryTasks(db);
	private GivenTasks givenTasks = new GivenTasks(db);
	
	private Object[][] tasks =  {new Object[]{"task1",Task.Status.PENDING},
			                     new Object[]{"task2",Task.Status.TERMINATED},
			                     new Object[]{"task3",Task.Status.PENDING},
			                     new Object[]{"task4",Task.Status.TERMINATED}};
	
	public void setUp(){
		PresentTaskGatewaySelector selector = new PresentTaskGatewaySelector();
		selector.addOrderCondition(PresentTaskGatewaySelector.TypeOrder.NO_ORDER, new InMemoryPresentTasks(db));
		selector.addOrderCondition(PresentTaskGatewaySelector.TypeOrder.TERMINATED_FIRST, new InMemoryPresentFirstTerminatedTasks(db));		
		
		Context.presentGatewaySelector = selector;		
		Context.taskOperations = new InMemoryTaskOperations(db);
		
	
		for (int index=0;index<tasks.length;index++){
			givenTasks.setTaskDescription((String)tasks[index][0]);
			givenTasks.setStatus((Task.Status)tasks[index][1]);
			givenTasks.execute();
		}
	}
		
	public QueryTasks getQueryTasks() {
		return queryTasks;
	}
}
