package com.tester.context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.tester.base.IComponent;
import com.tester.base.IContext;
import com.tester.components.ContainerComponent;
import com.tester.exception.ComponentException;
import com.tester.xml.Component;
import com.tester.xml.Project;
import com.tester.xml.Property;

public class TestContext implements IContext {

	private Properties variables;
	private IComponent components;
	private Properties componentsDefinition;

	public TestContext() {
		this.variables = new Properties();
		this.components = null;
		this.loadComponentDefinition();
	}

	public TestContext(String xmlConfig) {
		this.variables = new Properties();
		this.loadComponentDefinition();
		this.components = buildContext(xmlConfig);
	}

	private void loadComponentDefinition() {
		this.componentsDefinition = new Properties();

		FileInputStream fileComponentDefinition;
		try {
			fileComponentDefinition = new FileInputStream(
					getClass().getClassLoader().getResource("components.properties").getFile());
			this.componentsDefinition.load(fileComponentDefinition);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private IComponent buildContext(String xmlConfig) {

		ContainerComponent container = new ContainerComponent(this);
		try {

			File file = new File(getClass().getClassLoader().getResource(xmlConfig).getFile());
			JAXBContext jaxbContext = JAXBContext.newInstance(Project.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Project project = (Project) jaxbUnmarshaller.unmarshal(file);

			if (project.getProperties() != null) {
				Iterator<Property> itProp = project.getProperties().iterator();

				while (itProp.hasNext()) {

					Property prop = itProp.next();
					this.variables.put(prop.getName(), prop.getValue());

				}

			}
			
			Iterator<Component> it = project.getComponents().iterator();

			while (it.hasNext()) {
				this.processComponentConfig(container, it.next());
			}

		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return container;
	}

	private void processComponentConfig(IComponent parent, Component component) {

		IComponent newComponent = null;

		try {

			@SuppressWarnings("rawtypes")
			Constructor componentConstructor = Class.forName(this.componentsDefinition.getProperty(component.getType()))
					.getConstructor(IContext.class);

			newComponent = (IComponent) componentConstructor.newInstance(this);

			if (component.getProperties() != null) {
				Iterator<Property> it = component.getProperties().iterator();
				while (it.hasNext()) {
					Property prop = it.next();
					newComponent.getProperties().put(prop.getName(), prop.getValue());
				}
			}
			parent.addComponent(newComponent);
			newComponent.init();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		if (component.getComponents() != null) {
			Iterator<Component> it = component.getComponents().iterator();

			while (it.hasNext()) {

				Component tmpComponent = it.next();
				this.processComponentConfig(newComponent, tmpComponent);

			}

		}
	}

	@Override
	public String getVariable(String name) {
		return this.variables.getProperty(name);
	}

	@Override
	public void process() throws ComponentException {

		this.components.process(null);

	}

	@Override
	public String processData(String data) {

		List<String> variables = this.getVariablesList(data);
		String result = new String(data);
		Iterator<String> it = variables.iterator();
		while (it.hasNext()) {

			String variableName = it.next();
			String value = this.getVariable(variableName);
			result = result.replace("${" + variableName + "}", value);

		}
		return result;

	}

	private List<String> getVariablesList(String input) {

		List<String> result = new ArrayList<String>();

		Pattern pattern = Pattern.compile("\\$\\{([_a-zA-Z0-9]+)\\}");

		Matcher match = pattern.matcher(input);

		while (match.find()) {

			result.add(match.group(1));

		}
		return result;

	}

	@Override
	public void setVariable(String name, String newValue) {

		String value = this.variables.getProperty(name);

		if (value != null)
			this.variables.remove(name);

		this.variables.put(name, newValue);

	}

	@Override
	public IComponent getComponents() {
		return this.components;
	}

}
