package todolist.rest;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public final class RestServer {

	private HttpServer server;
	
	public RestServer(int port){
		try{
		   server = HttpServer.create(new InetSocketAddress(port),0);
		   server.setExecutor(java.util.concurrent.Executors.newCachedThreadPool());
		
		}catch(IOException e){
			throw new RuntimeException("Server not create");
		}
	}
	
	public void addServiceInContext(String context,HttpHandler restService){
		server.createContext("/"+context, restService);		
		
	}
	
	public void start() {
		 server.start();	
	}

	public void stop() {
		server.stop(0);	
	}
}
