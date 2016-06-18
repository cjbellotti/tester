package com.tester.components;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.tester.base.IContext;

public class HttpRequestComponent extends BaseComponent {

	private URL url;
	
	public HttpRequestComponent(IContext ctx) {
		super(ctx);
	}

	@Override
	public String task(String data) {

		String response = "";
		
		HttpURLConnection connection;
		try {
			
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			String line;
			while((line = br.readLine()) != null){
				response += line;
			}
			br.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		
	}

}
