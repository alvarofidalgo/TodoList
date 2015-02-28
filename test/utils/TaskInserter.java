package utils;

import java.util.List;

import todolist.entitys.Task;

public final class TaskInserter {
	
	public void insertTaskInListIfSameStatus(List<Task> taskList,Task task,String status){
		if (task.getStatus().equals(status))
			taskList.add(task);
	}
	
	public List<Task> appendListInOrder(List<Task> firstList,List<Task> secondList){
		firstList.addAll(secondList);
		return firstList;
	}

}
