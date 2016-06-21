package com.tester.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import com.tester.base.IComponent;
import com.tester.base.IContext;
import com.tester.components.ScriptComponent;
import com.tester.components.WhileComponent;
import com.tester.context.TestContext;
import com.tester.exception.ComponentException;

public class WhileComponentTestSuccessful {

	private static IContext context;
	private static IComponent component;
	
	@BeforeClass
	public static void initTestContextTest() {
		context = new TestContext("test.xml");
		component = new WhileComponent(context);
		component.getProperties().put("EXPRESSION", "parseInt(ctx.getVariable('CONTADOR')) < 10");
		component.init();
		
		IComponent incremento = new ScriptComponent(context);
		incremento.getProperties().put("EXPRESSION", "ctx.setVariable('CONTADOR', (parseInt(ctx.getVariable('CONTADOR')) + 1).toString())");
		incremento.init();
		component.addComponent(incremento);
		
	}
	
	@Test
	public void TestWhileComponent() {
		
		try {
			context.setVariable("CONTADOR", "0");
			component.process(null);
			assertEquals("10", context.getVariable("CONTADOR"));
		} catch (ComponentException e) {
			e.printStackTrace();
		}
	}

}
