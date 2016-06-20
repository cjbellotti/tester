package com.tester.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement( name = "component")
public class Component {

	private String type;
	private String description;
	private List<Property> properties;
	private List<Component> components;
	
	public Component() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getType() {
		return type;
	}
	
	@XmlElement
	public void setType(String type) {
		this.type = type;
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
