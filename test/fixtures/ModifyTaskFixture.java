package fixtures;

import todolist.entitys.Task;
import todolist.usecase.ModifyTaskUseCase;

public final class ModifyTaskFixture {

	private ModifyTaskUseCase useCase = new ModifyTaskUseCase();
	
	public void modifyTask(String taskDescription,Task.Status status) {
		useCase.setTask(new Task(taskDescription,status));
		useCase.modify();
	}

	public String showError() {
		return useCase.errorIs();
	}
}
