package todolist.rest.service.headers;

import todolist.rest.model.Response;

public final class HttpResponserHeaderCalculator {
	
	private int _httpResponseHeaderCorrect, _httpResponseHeaderFail;
	
	public HttpResponserHeaderCalculator(int httpResponseHeaderCorrect,int httpResponseHeaderFail){
		_httpResponseHeaderCorrect = httpResponseHeaderCorrect;
		_httpResponseHeaderFail = httpResponseHeaderFail;
	}

	public int calculateResponseHeader(Response response) {
		return response.getError().equals("")?_httpResponseHeaderCorrect:_httpResponseHeaderFail;
	}

}
