package com.tester.components;

import com.tester.base.IContext;
import com.tester.context.TestContext;
import com.tester.exception.ComponentException;

public class RunProjectComponent extends BaseComponent {

	private IContext context;
	
	public RunProjectComponent(IContext ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String task(String data) throws ComponentException {
		return this.context.process(data);
	}

	@Override
	public void init() {

		String projectFile = this.getProperties().getProperty("PROJECT_FILE");
		this.context = new TestContext(projectFile);
		this.context.setVariables(this.getCtx().getVariables());
		
	}

}
