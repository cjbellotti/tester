package com.tester.components;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import com.tester.base.IComponent;
import com.tester.base.IContext;

public abstract class BaseComponent implements IComponent {

	private Properties properties;
	private IContext ctx;
	private List<IComponent> components;
	
	public BaseComponent(IContext ctx) {
		
		this.ctx = ctx;
		this.properties = new Properties();
		this.components = new LinkedList<IComponent>();

	}
	
	@Override
	public Properties getProperties() {
		return this.properties;
	}

	@Override
	public void addComponent(IComponent component) {
		this.components.add(component);
	}

	@Override
	public void removeComponent(IComponent component) {
		this.components.remove(component);
	}

	@Override
	public void removeComponent(int index) {
		this.components.remove(index);
	}

	@Override
	public String process(String data) {
		
		String result = this.task(data);
		
		Iterator<IComponent> it = this.components.iterator();
		
		while (it.hasNext()) {
			it.next().process(result);
		}
		
		return result;

	}
	
	public abstract String task(String data);

	public IContext getCtx() {
		return ctx;
	}

	public void setCtx(IContext ctx) {
		this.ctx = ctx;
	}
	
	public abstract void init();
	
}
