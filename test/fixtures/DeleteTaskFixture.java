package fixtures;

import todolist.usecase.DeleteTaskUseCase;

public class DeleteTaskFixture {
	
	private DeleteTaskUseCase useCase = new DeleteTaskUseCase();

	public void delete(String taskDescription) {
		useCase.setTaskDescription(taskDescription);
		useCase.deleteTask();
	}

	public String showError() {
		return useCase.errorIs();
	}
}
