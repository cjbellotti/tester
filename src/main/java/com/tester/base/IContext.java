package com.tester.base;

public interface IContext {

	public void addVariable (String name, String value);
	public String getVariable(String name);
	public void process();
	public String processData(String data);
	
}
