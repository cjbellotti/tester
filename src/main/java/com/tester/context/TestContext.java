package com.tester.context;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tester.base.IComponent;
import com.tester.base.IContext;

public class TestContext implements IContext {

	private Properties variables;
	private IComponent components;

	public TestContext() {
		this.variables = new Properties();		
		this.components = null;
	}
	
	public TestContext(String xmlConfig) {
		this.variables = new Properties();
		this.components = buildContext(xmlConfig);
	}
	
	private IComponent buildContext(String xmlConfig) {
		return null;
	}
	
	@Override
	public String getVariable(String name) {
		return this.variables.getProperty(name);
	}

	@Override
	public void process() {

		this.components.process(null);
		
	}

	@Override
	public String processData(String data) {
		
		List<String> variables = this.getVariablesList(data);
		String result = new String(data);
		Iterator<String> it = variables.iterator();
		while(it.hasNext()) {
			
			String variableName = it.next();
			String value = this.getVariable(variableName);
			result = result.replace("${" + variableName + "}", value);

		}
		return result;

	}
	
	private List<String> getVariablesList(String input) {
		
		List<String> result = new ArrayList<String>();
		
		Pattern pattern = Pattern.compile("\\$\\{([a-zA-Z0-9]+)\\}");
		
		Matcher match = pattern.matcher(input);
		
		while(match.find()) {
			
			result.add(match.group(1));
			
		}
		return result;
		
	}

	@Override
	public void setVariable(String name, String newValue) {
		
		String value = this.variables.getProperty(name);
		
		if (value != null)
			this.variables.remove(name);
		
		this.variables.put(name, newValue);
		
	}
	
}
