package todolist.rest.service;

import todolist.rest.model.Response;
import todolist.rest.model.TaskRequest;

public interface Service {

	Response execute(TaskRequest taskRequest);

}