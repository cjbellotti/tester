package com.tester.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.tester.base.IComponent;
import com.tester.base.IContext;
import com.tester.components.selector.JSONSelector;
import com.tester.context.TestContext;
import com.tester.exception.ComponentException;

public class JSONSelectorTestSuccessful {

	private static IContext context;
	private static IComponent component;
	
	@BeforeClass
	public static void initTestContextTest() {
		context = new TestContext("test.xml");
		component = new JSONSelector(context);
		component.getProperties().put("PATH", "address.street");
	}
	
	@Test
	public void TestJSONSelector() {
		
		try {
			String result = component.process("{ \"name\" : \"Roger\", \"address\" : { \"street\" : \"Siempre Viva\", \"number\" : 123 }}");
			assertEquals("Siempre Viva", result);
		} catch (ComponentException e) {
			e.printStackTrace();
		}
	}

}
