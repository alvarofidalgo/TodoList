package fixtures;

import todolist.usecase.InsertTaskUseCase;

public final class InsertTaskFixture {
	
	private InsertTaskUseCase useCase = new InsertTaskUseCase();
	
	public void insertTask(String taskDescription) {
		useCase.setTaskDescription(taskDescription);
		useCase.insertTask();		
	}

	public String showError() {
		return useCase.errorIs();
	}
}
