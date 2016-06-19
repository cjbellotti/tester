package com.tester.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import com.tester.components.HttpRequestComponent;
import com.tester.components.LoggerComponent;
import com.tester.context.TestContext;

public class Main {

	public static void main(String[] args) {
		
//		try {
//			URL url = new URL("http://www.thomas-bayer.com/sqlrest/CUSTOMER/");
//			
//			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//			connection.setRequestMethod("GET");
//			
//			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//			
//			String line;
//			while((line = br.readLine()) != null){
//				System.out.println(line);
//			}
//			br.close();
//			
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		TestContext ctx = new TestContext();
		
		ctx.setVariable("HOST", "localhost");
		ctx.setVariable("IP", "3000");
		
		HttpRequestComponent component1 = new HttpRequestComponent(ctx);
		LoggerComponent component2 = new LoggerComponent(ctx);
		LoggerComponent component3 = new LoggerComponent(ctx);
		
		component1.getProperties().put("METHOD", "POST");
		component1.getProperties().put("URL", "http://${HOST}:${IP}/clientes");
		component1.getProperties().put("BODY", "{ \"id\" : 25, \"nombre\" : \"Roberto\", \"apellido\" : \"Sediño\" }");
		component1.getProperties().put("__HEADER_Content-Type", "application/json");
		component1.addComponent(component2);
		component1.addComponent(component3);
		component1.init();
		component2.init();
		component3.init();
		
		component1.process(null);
		
		System.out.println("{ \"id\" : 25, \"nombre\" : \"Roberto\", \"apellido\" : \"Sediño\" }");
		
	
	}

}
