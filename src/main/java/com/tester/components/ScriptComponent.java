package com.tester.components;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.tester.base.IContext;
import com.tester.exception.ComponentException;

public class ScriptComponent extends BaseComponent {

	private ScriptEngine engine;

	public ScriptComponent(IContext ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String task(String data) throws ComponentException {
		
		String result = "";
		try {
			
			String expresion = this.getCtx().processData(this.getProperties().getProperty("EXPRESSION"));
			engine.put("input", data);
			engine.eval(expresion);
			
		} catch (ScriptException e) {
			
			throw new ComponentException("ERROR execution expresion: " + e.getMessage());
			
		}
		
		return result;
		
	}

	@Override
	public void init() {

		ScriptEngineManager factory = new ScriptEngineManager();
		engine = factory.getEngineByName("JavaScript");
		engine.put("ctx", this.getCtx());

	}

}
