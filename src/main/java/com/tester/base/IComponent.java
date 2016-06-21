package com.tester.base;

import java.util.List;
import java.util.Properties;

import com.tester.exception.ComponentException;

public interface IComponent {

	public Properties getProperties();
	public void addComponent(IComponent component);
	public void removeComponent(IComponent component);
	public void removeComponent(int index);
	public String process(String data) throws ComponentException;
	public void init();
	public List<IComponent> getComponents();
	
}
