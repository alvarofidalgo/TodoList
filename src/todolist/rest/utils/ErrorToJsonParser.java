package todolist.rest.utils;

public final class ErrorToJsonParser {
	
	public String parser(String error){
		if (!error.equals(""))
			error = "{"+error+"}";
		return error;	
	}
}
