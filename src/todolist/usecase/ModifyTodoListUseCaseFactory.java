package todolist.usecase;

import java.util.HashMap;
import java.util.Map;

import todolist.usecase.configurer.DeleteTaskConfgurer;
import todolist.usecase.configurer.ExecutorConfigurer;
import todolist.usecase.configurer.InsertTaskConfigurer;
import todolist.usecase.configurer.ModifyTaskConfigurer;

public final class ModifyTodoListUseCaseFactory {
	
	public enum TypeModifyTodoList{INSERT_TASK,MODIFY_TASK,DELETE_TASK};
	
	private Map<TypeModifyTodoList,ExecutorConfigurer> configurers= new HashMap<TypeModifyTodoList,ExecutorConfigurer>();
	
	public ModifyTodoListUseCaseFactory(){
		configurers.put(TypeModifyTodoList.INSERT_TASK,new InsertTaskConfigurer());
		configurers.put(TypeModifyTodoList.MODIFY_TASK,new ModifyTaskConfigurer());
		configurers.put(TypeModifyTodoList.DELETE_TASK,new DeleteTaskConfgurer());
	}
	
	public ModifyTodoListUseCase getTypeUseCase(TypeModifyTodoList type){
		return new ModifyTodoListUseCase(configurers.get(type));
	}
}
