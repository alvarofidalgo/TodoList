package todolist.rest.utils;

import static org.junit.Assert.*;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;
import org.mockito.Mockito;

import com.sun.net.httpserver.HttpExchange;



public class RequestExtractorTest {
	
	private HttpExchange createHttpExchange(String query) throws URISyntaxException{
		HttpExchange httpExchangeMock = Mockito.mock(HttpExchange.class);
		URI uri = URI.create("http://localhost:8000/task"+query);
		Mockito.when(httpExchangeMock.getRequestURI()).thenReturn(uri);
		return httpExchangeMock;
	}
	
	@Test public void whenQueryNotHaveParameters() throws URISyntaxException{
	
		RequestExtractor requestPreparator = new RequestExtractor(createHttpExchange(""));
		String [] params = requestPreparator.obtainParametersValues();
		String [] expectedParams = new String[0];
		assertEquals(expectedParams.length,params.length);		
	}
	
	@Test public void whenQueryHaveOneParameter() throws URISyntaxException{

		RequestExtractor requestPreparator = new RequestExtractor(createHttpExchange("?firstParam='first'"));
		String [] params = requestPreparator.obtainParametersValues();
		String [] expectedParams = new String[]{"first"};
		assertEquals(expectedParams.length,params.length);	
		assertEquals(expectedParams[0],params[0]);
	}
	
	@Test public void whenQueryHaveTwoParameters() throws URISyntaxException{
		RequestExtractor requestPreparator = new RequestExtractor(createHttpExchange("?firstParam='first'&secondParam='second'"));
		String [] params = requestPreparator.obtainParametersValues();
		String [] expectedParams = new String[]{"first","second"};
		assertEquals(expectedParams.length,params.length);	
		assertEquals(expectedParams[0],params[0]);
		assertEquals(expectedParams[1],params[1]);
	}
	
	@Test public void whenValuesNotHaveQuotes() throws URISyntaxException{
		RequestExtractor requestPreparator = new RequestExtractor(createHttpExchange("?firstParam=first"));
		String [] params = requestPreparator.obtainParametersValues();
		String [] expectedParams = new String[]{"first"};
		assertEquals(expectedParams.length,params.length);	
		assertEquals(expectedParams[0],params[0]);
	}
	

}
