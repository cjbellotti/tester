package com.tester.components;

import com.tester.base.IContext;
import com.tester.exception.ComponentException;

public class StoreComponent extends BaseComponent {

	public StoreComponent(IContext ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String task(String data) throws ComponentException {
		
		String name = this.getCtx().processData(this.getProperties().getProperty("VARIABLE_NAME"));
		this.getCtx().setVariable(name, data);
		return data;
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

}
