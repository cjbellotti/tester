package com.tester.base;

import com.tester.exception.ComponentException;

public interface IContext {

	public void setVariable (String name, String newValue);
	public String getVariable(String name);
	public void process() throws ComponentException;
	public String processData(String data);
	public IComponent getComponents();
	
}
