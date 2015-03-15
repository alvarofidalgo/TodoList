package todolist.rest.service;

import todolist.rest.model.Response;

public interface Service {

	Response execute(String[] valueParameters);

}