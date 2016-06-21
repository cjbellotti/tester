package com.tester.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import com.tester.base.IComponent;
import com.tester.base.IContext;
import com.tester.components.StoreComponent;
import com.tester.context.TestContext;
import com.tester.exception.ComponentException;

public class StoreComponentTestSuccessful {

	private static IContext context;
	private static IComponent component;
	
	@BeforeClass
	public static void initTestContextTest() {
		context = new TestContext("test.xml");
		component = new StoreComponent(context);
		component.getProperties().put("VARIABLE_NAME", "VARIABLE");
		component.init();
	}
	
	@Test
	public void TestStoreComponent() {
		
		try {
			component.process("XXXX");
			assertEquals("XXXX", context.getVariable("VARIABLE"));
		} catch (ComponentException e) {
			e.printStackTrace();
		}
	}

}
