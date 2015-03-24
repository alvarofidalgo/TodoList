package todolist.rest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import sun.net.www.protocol.http.HttpURLConnection;

public final class FakeClient {
	
	
    private int _port;
    private String _collection="NO_COLLECTION";
    private HttpURLConnection _connection;
    
	public FakeClient(int port){
		_port = port;
		
	}
	
	public void addCollection(String collection){
		_collection = collection;
	}
	
	public HttpURLConnection createConnectionWithMethodAndService(String method,String service) throws MalformedURLException, IOException{
		
		_connection =  (HttpURLConnection) new URL("http://localhost:"+_port+"/"+_collection+service).openConnection();		  
		_connection.setRequestMethod(method);
		_connection.addRequestProperty("Access-control-request-method", method);		
		_connection.connect();	
		return _connection;
	}
	
	public int responseCodeIs(){
		try {
			return _connection.getResponseCode();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
}
