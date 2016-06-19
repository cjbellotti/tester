package com.tester.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.tester.base.IComponent;
import com.tester.base.IContext;
import com.tester.components.HttpRequestComponent;
import com.tester.context.TestContext;

public class HttpRequestComponentTestSuccessful {

	private static IContext context;
	private static IComponent component;
	
	private HttpServer server;
	
	@BeforeClass
	public static void initTestContextTest() {
		context = new TestContext();
		component = new HttpRequestComponent(context);
		component.getProperties().put("METHOD", "GET");
		component.getProperties().put("URL", "http://localhost:8472/data");
		component.init();
	}
	
	@Before
	public void InitServer() {
		
		try {
			server = HttpServer.create(new InetSocketAddress(8472), 0);
			
			server.createContext("/data", new TestRequestHandler());
			
			server.setExecutor(null);
			
			server.start();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static class TestRequestHandler implements HttpHandler {

		@Override
		public void handle(HttpExchange t) throws IOException {
			String response = "<response><data>1234</data></response>";
			t.sendResponseHeaders(200, response.length());
			OutputStream os = t.getResponseBody();
			os.write(response.getBytes());
			os.close();
		}
		
	}
	
	@After
	public void stopServer() {
		server.stop(0);
	}
	
	@Test
	public void testGetRequest() {
		
		String result = component.process(null);
		assertEquals("<response><data>1234</data></response>", result);
		
	}

}
