package todolist.rest.utils;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.Headers;

public final class RequestExtractor {

	private String _query = "";
	private Headers _header;
	
	public RequestExtractor(HttpExchange httpExchange) {
		   _header = httpExchange.getRequestHeaders();
		   _query = httpExchange.getRequestURI().getQuery()==null?"":httpExchange.getRequestURI().getQuery();
	}

	public String[] obtainParametersValues() {    
        String [] paramsAndValues = extractParametersAndValues();
        String [] params = new String[paramsAndValues.length];
        for (int index = 0;index<paramsAndValues.length;index++ )
        	params[index] = getParamValue(paramsAndValues[index]);
        return params;
	}
	
	private String [] extractParametersAndValues(){
		String [] paramsAndValues = _query.split("&");
		if (paramsAndValues[0].equals(""))
			paramsAndValues = new String[0];
		return paramsAndValues;
	}
	
	private String getParamValue(String paramAndValue){
		String [] order = paramAndValue.split("=");
		int initSubtring = order[1].startsWith("'")?1:0;
		int endSubString = order[1].endsWith("'")?order[1].length()-1:order[1].length();
		return order[1].substring(initSubtring,endSubString);
	}
	
	public String httpMethod(){
		return _header.get("Access-control-request-method").get(0);
	}
}
