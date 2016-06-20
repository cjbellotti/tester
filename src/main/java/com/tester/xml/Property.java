package com.tester.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement ( name = "property")
public class Property {

	private String name;
	private String value;
	public Property() {
		super();
	}
	public Property(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}
	public String getName() {
		return name;
	}
	
	@XmlAttribute
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	
	@XmlAttribute
	public void setValue(String value) {
		this.value = value;
	}
	
}
