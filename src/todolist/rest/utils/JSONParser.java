package todolist.rest.utils;

import java.util.List;

import todolist.entitys.Task;

public final class JSONParser {

	public String toStringJSON(List<Task> tasks) {
		return includeExternalBraces(deleteLastCommaIfExist(createJSONOBjects(tasks))).toString();
	}
	
	private StringBuffer includeExternalBraces(StringBuffer buffer){
		return buffer.insert(0, "{").append("}");
	}
	
	private StringBuffer deleteLastCommaIfExist(StringBuffer buffer){
		int length = buffer.length(),
		 postionDelete = length -1;
		if (postionDelete>=0)
			buffer.delete(postionDelete, length);
		return buffer;
	}
	
	private StringBuffer createJSONOBjects(List<Task> tasks){
		StringBuffer buffer = new StringBuffer();
		for (int index=0;index<tasks.size();index++)
			buffer.append(toJSON(tasks.get(index)));	
		return buffer;
	}
	
	private String toJSON(Task task){
		return new TaskToJsonParser().parser(task)+",";
	}
}
