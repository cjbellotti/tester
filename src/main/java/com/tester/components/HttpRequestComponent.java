package com.tester.components;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Properties;

import com.tester.base.IContext;
import com.tester.exception.ComponentException;

public class HttpRequestComponent extends BaseComponent {

	private URL url;
	private Properties header;
	
	public HttpRequestComponent(IContext ctx) {
		super(ctx);
		this.header = new Properties();
	}

	@Override
	public String task(String data) throws ComponentException {

		String response = "";
		
		HttpURLConnection connection;
		try {
			
			String method = this.getProperties().getProperty("METHOD");
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(method);
			
			Iterator<Object> it = this.header.keySet().iterator();
			
			while(it.hasNext()) {
			
				String name = (String) it.next();
				connection.setRequestProperty(name, this.header.getProperty(name));
			}
			
			if (method.equals("POST") || method.equals("PUT")) {
			
				connection.setDoOutput(true);
				String body = this.getCtx().processData(this.getProperties().getProperty("BODY"));
				
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
				
				bw.write(body);
				
				bw.flush();
				
				bw.close();
				
			}
			
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			String line;
			while((line = br.readLine()) != null){
				response += line;
			}
			br.close();
			
		} catch (IOException e) {
			throw new ComponentException("ERROR in request : " + e.getMessage());
		}

		return response;
	}

	@Override
	public void init() {

		String url = this.getCtx().processData(this.getProperties().getProperty("URL"));
		try {
			this.url = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		Iterator<Object> it = this.getProperties().keySet().iterator();
		
		while(it.hasNext()) {
			
			String name = (String) it.next();
			
			if (name.length() > 9) 
				if (name.substring(0, 9).equals("__HEADER_"))
					this.header.put(name.substring(9), this.getProperties().get(name));
			
		}
		
	}

}
