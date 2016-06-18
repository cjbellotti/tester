package com.tester.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class Main {

	public static void main(String[] args) {

		Properties properties = new Properties();
		
		properties.put("PROPIEDAD_1", "10");
		
		
		try {
			URL url = new URL("http://www.thomas-bayer.com/sqlrest/CUSTOMER/");
			
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			String line;
			while((line = br.readLine()) != null){
				System.out.println(line);
			}
			br.close();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
