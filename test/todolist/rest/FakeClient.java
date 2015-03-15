package todolist.rest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import sun.net.www.protocol.http.HttpURLConnection;

public final class FakeClient {
	
	
    private int _port;
    private String _collection="NO_COLLECTION";
    
	public FakeClient(int port){
		_port = port;
		
	}
	
	public void addCollection(String collection){
		_collection = collection;
	}
	
	public HttpURLConnection createConnectionWithMethodAndService(String method,String service) throws MalformedURLException, IOException{
		
		HttpURLConnection	connection =  (HttpURLConnection) new URL("http://localhost:"+_port+"/"+_collection+service).openConnection();		  
		connection.setRequestMethod(method);
		connection.addRequestProperty("Access-control-request-method", method);		
		connection.connect();	
		return connection;
	}
}
