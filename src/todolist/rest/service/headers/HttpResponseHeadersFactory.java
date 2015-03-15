package todolist.rest.service.headers;

import java.util.HashMap;
import java.util.Map;

import sun.net.www.protocol.http.HttpURLConnection;

public final class HttpResponseHeadersFactory {
	
	public enum TypeHttpCalculator{INSERT_TASK,MODIFY_OR_DELETE_TASK}
	
	private Map<TypeHttpCalculator,HttpResponserHeaderCalculator> _calculators = new HashMap<TypeHttpCalculator,HttpResponserHeaderCalculator>();
	
	public HttpResponseHeadersFactory(){
		_calculators.put(TypeHttpCalculator.INSERT_TASK, new HttpResponserHeaderCalculator(HttpURLConnection.HTTP_CREATED,HttpURLConnection.HTTP_NOT_MODIFIED));
		_calculators.put(TypeHttpCalculator.MODIFY_OR_DELETE_TASK, new HttpResponserHeaderCalculator(HttpURLConnection.HTTP_OK,HttpURLConnection.HTTP_NOT_MODIFIED));
	}
	
	public HttpResponserHeaderCalculator getCalculator(TypeHttpCalculator type){
		return _calculators.get(type);
	}

}
