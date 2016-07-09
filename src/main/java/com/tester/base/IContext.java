package com.tester.base;

import java.util.Properties;

import com.tester.exception.ComponentException;

public interface IContext {

	public void setVariable (String name, String newValue);
	public String getVariable(String name);
	public void process() throws ComponentException;
	public String process(String data) throws ComponentException;
	public String processData(String data);
	public IComponent getComponents();
	public Properties getVariables();
	public void setVariables(Properties variables);
	
}
