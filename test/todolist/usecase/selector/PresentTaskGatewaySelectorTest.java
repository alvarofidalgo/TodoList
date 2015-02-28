package todolist.usecase.selector;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import todolist.usecase.selector.PresentTaskGatewaySelector;

public class PresentTaskGatewaySelectorTest {
		
	private PresentTaskGatewaySelector selector;
	
	@Before
	public void setUp(){
		selector = new PresentTaskGatewaySelector();
		selector.addOrderCondition(PresentTaskGatewaySelector.TypeOrder.NO_ORDER,new PresentNoOrderTasksGatewayMock());
		selector.addOrderCondition(PresentTaskGatewaySelector.TypeOrder.TERMINATED_FIRST,new PresentTerminatedTasksFirstGatewayMock());
	}
	
	@Test
	public void whenNoOrderCondition(){				
		assertTrue(selector.getAllTask(PresentTaskGatewaySelector.TypeOrder.NO_ORDER).isEmpty());		
	}
	
	@Test
	public void whenFirstTerminatedOrderConidition(){
		assertTrue(selector.getAllTask(PresentTaskGatewaySelector.TypeOrder.TERMINATED_FIRST).size() == 1);	
	}
}
