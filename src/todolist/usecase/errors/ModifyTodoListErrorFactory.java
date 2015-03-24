package todolist.usecase.errors;

import java.util.HashMap;
import java.util.Map;

public final class ModifyTodoListErrorFactory {
	
	public enum TypeErrorModifyTodoList{INSERT_TASK,MODIFY_TASK,DELETE_TASK};
	
	public Map<TypeErrorModifyTodoList,ModifyTodoListError> buiders= new HashMap<TypeErrorModifyTodoList,ModifyTodoListError>();
	
	public ModifyTodoListErrorFactory(){
		buiders.put(TypeErrorModifyTodoList.INSERT_TASK, new InsertTaskError());
		buiders.put(TypeErrorModifyTodoList.MODIFY_TASK, new ModifyTaskError());
		buiders.put(TypeErrorModifyTodoList.DELETE_TASK, new DeleteTaskError());
	}
	
	public ModifyTodoListError getUseCaseError(TypeErrorModifyTodoList type){
		return buiders.get(type);
	}
}
