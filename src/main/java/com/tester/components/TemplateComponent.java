package com.tester.components;

import java.util.Iterator;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import com.tester.base.IContext;
import com.tester.exception.ComponentException;

public class TemplateComponent extends BaseComponent {

	private JtwigTemplate template;
	
	public TemplateComponent(IContext ctx) {
		super(ctx);
	}

	@Override
	public String task(String data) throws ComponentException {
		
		Iterator<Object> it = this.getCtx().getVariables().keySet().iterator();
		
		JtwigModel model = JtwigModel.newModel();
		while(it.hasNext()) {
			String variableName = (String) it.next();
			model.with(variableName, this.getCtx().getVariable(variableName));
		}
		
		return template.render(model);
		
	}

	@Override
	public void init() {
		String templateSrc = this.getCtx().processData(this.getProperties().getProperty("TEMPLATE"));
		System.out.println(templateSrc);
		template = JtwigTemplate.inlineTemplate(templateSrc);		
	}

}
