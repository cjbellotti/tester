package com.tester.components;

import com.tester.base.IContext;

public class ContainerComponent extends BaseComponent {

	public ContainerComponent(IContext ctx) {
		super(ctx);
	}

	@Override
	public String task(String data) {
		return data;
	}

	@Override
	public void init() {

	}

}
