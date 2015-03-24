package todolist.rest.service.headers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.net.HttpURLConnection;

import todolist.rest.model.Response;

public class HttpResponserHeaderCalculatorTest {
	
	private HttpResponserHeaderCalculator calculator;
	
	@Before public void setUp(){
		calculator=new HttpResponserHeaderCalculator(HttpURLConnection.HTTP_CREATED,HttpURLConnection.HTTP_NOT_MODIFIED);
	}
	
	@Test public void whenIsCorrect(){

		assertEquals(calculator.calculateResponseHeader(new Response()),HttpURLConnection.HTTP_CREATED);
	}
	
	@Test public void whenIsNotCorrect(){
		
		Response response = new Response();
		response.setError("aaaa");
		assertEquals(calculator.calculateResponseHeader(response),HttpURLConnection.HTTP_NOT_MODIFIED);
	}

}
