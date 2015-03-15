package todolist.usecase.listmodifiers;

import java.util.ArrayList;
import java.util.List;

import todolist.entitys.Task;
import todolist.usecase.checkers.Checker;

public final class ModifyTodoListExecutor {
	
	private List<ListModifierWithCheckers> _modifiers = new ArrayList<ListModifierWithCheckers>();
		
	public void addListModifierWithCheckers(TodoListModifier modifier,Checker... checkers){
		_modifiers.add(new ListModifierWithCheckers(modifier,checkers));
	}
	
	public void setTaskToCheck(Task task){
		for (int index=0;index<_modifiers.size();_modifiers.get(index++).changeTaskToCheck(task));
	}
	
	public void setTaskToInsert(Task task){
		for (int index=0;index<_modifiers.size();_modifiers.get(index++).setTaskToInsertAndTaskToCheck(task));
	}
	
	public void executeModificationsInTodoList(){
		for (int index=0;index<_modifiers.size();_modifiers.get(index++).modifyTodoListWithTask());
	}

}
