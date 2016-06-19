package com.tester.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.tester.base.IContext;
import com.tester.context.TestContext;

public class TestContextTestSuccessful {

	private static IContext context;
	
	@BeforeClass
	public static void initTestContextTest() {
		context = new TestContext();
	}
	
	@Test
	public void testVariableSetter() {
		
		context.setVariable("VARIABLE", "variableValue");
		
		assertEquals("variableValue", context.getVariable("VARIABLE"));

		context.setVariable("VARIABLE", "newVariableValue");
		
		assertEquals("newVariableValue", context.getVariable("VARIABLE"));

	}
	
	@Test
	public void testProcessData() {
		
		context.setVariable("HOST", "site.com");
		context.setVariable("IP", "8888");
		
		String string = "http://${HOST}:${IP}/someroute";
		
		assertEquals("http://site.com:8888/someroute", context.processData(string));
	}

}
