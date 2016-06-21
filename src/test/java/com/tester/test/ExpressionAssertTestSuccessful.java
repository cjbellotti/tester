package com.tester.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import com.tester.base.IComponent;
import com.tester.base.IContext;
import com.tester.components.asserts.ExpressionAssert;
import com.tester.components.selector.JSONSelector;
import com.tester.context.TestContext;
import com.tester.exception.ComponentException;

public class ExpressionAssertTestSuccessful {

	private static IContext context;
	private static IComponent component;
	
	@BeforeClass
	public static void initTestContextTest() {
		context = new TestContext("test.xml");
		component = new ExpressionAssert(context);
		component.getProperties().put("EXPRESSION", "input > 'AAA' && input < 'CCC'" );
		component.init();
	}
	
	@Test
	public void testExpressionAssert() {
		
		try {
			String result = component.process("BBB");
			assertEquals("true", result);
		} catch (ComponentException e) {
			e.printStackTrace();
		}
	}

}
