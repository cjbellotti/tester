package com.tester.components;

import com.tester.base.IContext;
import com.tester.exception.ComponentException;

public class ReturnComponent extends BaseComponent {

	public ReturnComponent(IContext ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String task(String data) throws ComponentException {
		
		String variable = this.getProperties().getProperty("VARIABLE_NAME");
		this.getCtx().setVariable("__RETURN", this.getCtx().getVariable(variable));
		return data;
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

}
