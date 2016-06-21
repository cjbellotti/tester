package com.tester.components;

import java.util.Iterator;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.tester.base.IComponent;
import com.tester.base.IContext;
import com.tester.exception.ComponentException;

public class WhileComponent extends BaseComponent {

	private ScriptEngine engine;

	public WhileComponent(IContext ctx) {
		super(ctx);
	}

	@Override
	public String process(String data) throws ComponentException {
		
		String expression = this.getCtx().processData(this.getProperties().getProperty("EXPRESSION"));
		try {
			while (engine.eval(expression).toString().equals("true")) {

				Iterator<IComponent> it = this.getComponents().iterator();
				
				while (it.hasNext()) {
					it.next().process(null);
				}
			}
		} catch (ScriptException e) {
			e.printStackTrace();
		}		
		
		return "";

	}

	@Override
	public String task(String data) throws ComponentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init() {
		
		ScriptEngineManager factory = new ScriptEngineManager();
		engine = factory.getEngineByName("JavaScript");
		engine.put("ctx", this.getCtx());

	}

}
