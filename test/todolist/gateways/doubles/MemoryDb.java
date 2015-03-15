package todolist.gateways.doubles;

import java.util.ArrayList;
import java.util.List;

import todolist.entitys.Task;

public final class MemoryDb  {
	
	private List<Task> db = new ArrayList<Task>();

	public  List<Task> getTasks() {
		return db;
	}
}
