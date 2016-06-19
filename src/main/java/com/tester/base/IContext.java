package com.tester.base;

public interface IContext {

	public void setVariable (String name, String newValue);
	public String getVariable(String name);
	public void process();
	public String processData(String data);
	
}
