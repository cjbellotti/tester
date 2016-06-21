package com.tester.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name = "project")
public class Project {

	private String name;
	private String description;
	private List<Component> components;
	private List<Property> properties; 
	
	public Project() {
		super();
	}
	
	public Project(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}
	
	@XmlElement
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	
	@XmlElement
	public void setDescription(String description) {
		this.description = description;
	}

	public List<Component> getComponents() {
		return components;
	}

	@XmlElementWrapper (name = "components")
	@XmlElement (name = "component")
	public void setComponents(List<Component> components) {
		this.components = components;
	}

	public List<Property> getProperties() {
		return properties;
	}

	@XmlElementWrapper (name = "properties")
	@XmlElement (name = "property")
	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}


}
