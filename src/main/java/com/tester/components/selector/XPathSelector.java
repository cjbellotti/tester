package com.tester.components.selector;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.xml.sax.InputSource;

import com.tester.base.IContext;
import com.tester.components.BaseComponent;
import com.tester.exception.ComponentException;

public class XPathSelector extends BaseComponent {

	DocumentBuilder builder;
	XPath xpath;

	public XPathSelector(IContext ctx) {
		super(ctx);
	}

	@Override
	public String task(String data) throws ComponentException {
		String xPath = this.getProperties().getProperty("PATH");
		InputSource is = new InputSource(new StringReader(data));
		String result = "";
		try {
			result = xpath.compile(xPath).evaluate(is);
		} catch (XPathExpressionException e) {
			throw new ComponentException("XPathComponent: XpathExpressionException: " + e.getMessage());
		}
		return result;
	}

	@Override
	public void init() {

		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

		try {
			builder = builderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		this.xpath = XPathFactory.newInstance().newXPath();

	}

}
