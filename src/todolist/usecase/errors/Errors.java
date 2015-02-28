package todolist.usecase.errors;

import java.util.HashMap;
import java.util.Map;

public final class Errors {
		
	public enum TypeErrors {TASK_WITH_SAME_DESCRIPTION,MODIFY_TASK_TERMINATED,DELETE_PENDING_TASK};
	
	private Map<TypeErrors,String> errors = new HashMap<TypeErrors,String>();
	
	public Errors(){
		errors.put(TypeErrors.TASK_WITH_SAME_DESCRIPTION, "Error : there is one task with same description");
		errors.put(TypeErrors.MODIFY_TASK_TERMINATED, "Error : Tasks in terminated state can´t modify");
		errors.put(TypeErrors.DELETE_PENDING_TASK, "Error:You can´t delete pending task");
	}
	
	public String showExistErrorTo(TypeErrors typeError,boolean existError){
		return existError?errors.get(typeError):"";		
	}
}
