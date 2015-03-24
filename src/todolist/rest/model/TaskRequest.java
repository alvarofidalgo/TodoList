package todolist.rest.model;

import todolist.entitys.Task;
import todolist.usecase.selector.PresentTaskGatewaySelector.TypeOrder;

public class TaskRequest {
	
	
	private String _order="no_order";
	private String _taskDescription="";
	private String _status="terminated";
		
	public TypeOrder getOrder(){
		return   TypeOrder.valueOf(_order.toUpperCase());
	}
	
	public Task buildTask(){
		return new Task(_taskDescription,Task.Status.valueOf(_status.toUpperCase()));
	}
	
	public void setOrder(String order){
		_order = order;
	}

	public void setTaskdescription( String taskdescription){
		 _taskDescription = taskdescription;
	}
	
    public void setStatus(String status){
    	_status = status;
    }

}
