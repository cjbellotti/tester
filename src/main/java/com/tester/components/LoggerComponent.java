package com.tester.components;

import com.tester.base.IContext;

public class LoggerComponent extends BaseComponent {

	public LoggerComponent(IContext ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String task(String data) {
		
		System.out.println(">>>>>>>>" + data);
		return data;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

}
