package com.tester.context;

import java.util.Properties;

import com.tester.base.IComponent;
import com.tester.base.IContext;

public class TestContext implements IContext {

	private Properties variables;
	private IComponent components;

	public TestContext(String xmlConfig) {
		this.variables = new Properties();
		this.components = buildContext(xmlConfig);
	}
	
	private IComponent buildContext(String xmlConfig) {
		return null;
	}
	
	@Override
	public void addVariable(String name, String value) {
		this.variables.put(name, value);
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
		return data;
	}
	
}
