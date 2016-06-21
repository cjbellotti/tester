package com.tester.components.selector;

import org.json.JSONObject;

import com.tester.base.IContext;
import com.tester.base.ISelector;
import com.tester.components.BaseComponent;
import com.tester.exception.ComponentException;

public class JSONSelector extends BaseComponent implements ISelector{

	public JSONSelector(IContext ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String task(String data) throws ComponentException {
		
		JSONObject obj = new JSONObject(data);
		
		String[] prop = this.getProperties().getProperty("PATH").split("\\.");
				
		int i = 0;
		for (i = 0; i < prop.length - 1; i++) {
			obj = (JSONObject) obj.get(prop[i]);
		}

		return obj.getString(prop[i]);
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

}
