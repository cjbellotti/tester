package com.tester.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.json.JSONObject;

import com.tester.components.HttpRequestComponent;
import com.tester.components.LoggerComponent;
import com.tester.context.TestContext;
import com.tester.exception.ComponentException;
import com.tester.xml.Component;
import com.tester.xml.Project;
import com.tester.xml.Property;

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
		
//		TestContext ctx = new TestContext();
//		
//		ctx.setVariable("HOST", "localhost");
//		ctx.setVariable("IP", "3000");
//		
//		HttpRequestComponent component1 = new HttpRequestComponent(ctx);
//		LoggerComponent component2 = new LoggerComponent(ctx);
//		LoggerComponent component3 = new LoggerComponent(ctx);
//		
//		component1.getProperties().put("METHOD", "POST");
//		component1.getProperties().put("URL", "http://${HOST}:${IP}/clientes");
//		component1.getProperties().put("BODY", "{ \"id\" : 25, \"nombre\" : \"Roberto\", \"apellido\" : \"Sediño\" }");
//		component1.getProperties().put("__HEADER_Content-Type", "application/json");
//		component1.addComponent(component2);
//		component1.addComponent(component3);
//		component1.init();
//		component2.init();
//		component3.init();
//		
//		component1.process(null);
//		
//		System.out.println("{ \"id\" : 25, \"nombre\" : \"Roberto\", \"apellido\" : \"Sediño\" }");
		
		//new Main().doTask();
	
		TestContext ctx = new TestContext("project.xml");
		try {
			System.out.println(ctx.process("Hola Mundo!!!"));
		} catch (ComponentException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("NOMBRE_GUARDADO: " + ctx.getVariable("NOMBRE_GUARDADO"));
		//String nombre = obj.getString("apellido.segundo");
		//System.out.println(nombre);
	}
	
	public void doTask() {

		ClassLoader classLoader = getClass().getClassLoader();
		
		try {
			File file = new File(classLoader.getResource("project2.xml").getFile());
			JAXBContext jaxbContext = JAXBContext.newInstance(Project.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			
			Project project = (Project) jaxbUnmarshaller.unmarshal(file);
			
			System.out.println(project.getComponents().get(0).getProperties().get(0).getValue());
			
//			Project project = new Project();
//			
//			project.setName("Test Project");
//			Component component = new Component();
//			component.setType("HttpRequest");
//			component.setDescription("Test clientes Rest Get");
//			component.setProperties(new ArrayList<Property>());
//			component.getProperties().add(new Property("URL", "http://${HOST}:${IP}/clientes"));
//			component.getProperties().add(new Property("METHOD", "GET"));
//			component.setComponents(new ArrayList<Component>());
//			
//			Component logComponent = new Component();
//			logComponent.setType("Logger");
//			logComponent.setDescription("Show Response");
//			component.getComponents().add(logComponent);
//			
//			Component secondComponent = new Component();
//			secondComponent.setType("Logger");
//			secondComponent.setDescription("Test second Component");
//			
//			project.setComponents(new ArrayList<Component>());
//			project.getComponents().add(component);
//			project.getComponents().add(secondComponent);
//
//			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
//			StringWriter sw = new StringWriter();
//			jaxbMarshaller.marshal(project, sw);
//			System.out.println(sw);
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
