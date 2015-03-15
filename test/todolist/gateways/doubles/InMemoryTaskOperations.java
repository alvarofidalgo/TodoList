package todolist.gateways.doubles;

import java.util.List;

import todolist.entitys.Task;
import todolist.gateways.TaskOperations;

public final class InMemoryTaskOperations implements TaskOperations{
	
	private List<Task> _taskList;
	
	public InMemoryTaskOperations(MemoryDb db){
		_taskList = db.getTasks();
	}
	
	public void insert(Task task) {
		_taskList.add(task);		
	}

	public Task getTaskWith(String taskDescription) {
		Task taskReturn = new Task("",Task.Status.TERMINATED);
		for (int index = 0;index<_taskList.size();index++){
        	Task taskToTest = _taskList.get(index);
        	if (taskDescription.equals(taskToTest.getTaskDescription()))
        		taskReturn = new Task(taskToTest.getTaskDescription(),taskToTest.getStatus());
        }
        return taskReturn;	
	}

	
	public void modifyStatus(Task task) {
		for (int index = 0;index<_taskList.size();index++){
			Task taskToTest = _taskList.get(index);
			if (task.getTaskDescription().equals(taskToTest.getTaskDescription()))
					_taskList.set(index, task);
		}
         
	}


	public void  delete(Task task) {
		boolean deleted = false;
		for (int index = 0;index<_taskList.size() && !deleted;index++){
        	Task taskToTest = _taskList.get(index);
        	if (task.getTaskDescription().equals(taskToTest.getTaskDescription())){
        		_taskList.remove(index);
        		deleted = true;
        	}
        }
	
	}
}
	
