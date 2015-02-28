package todolist.usecase.selector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import todolist.entitys.Task;
import todolist.gateways.PresentTasksGateway;

public final class PresentTaskGatewaySelector {
	
	public enum TypeOrder {NO_ORDER,TERMINATED_FIRST}
	private Map<TypeOrder,PresentTasksGateway> presentsGateway = new HashMap<TypeOrder,PresentTasksGateway>();
		
	public void addOrderCondition(TypeOrder typeOrder ,PresentTasksGateway presentGateway) {
		presentsGateway.put(typeOrder, presentGateway);
	}

	public List<Task> getAllTask(TypeOrder typeOrder) {	
		PresentTasksGateway execGateway = presentsGateway.get(typeOrder);
		return execGateway.allTasks();
	}
}
