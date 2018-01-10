package com.bos.gjService;

import com.eos.engine.component.ILogicComponent;
import com.primeton.ext.engine.component.LogicComponentFactory;

public class WytoLoanServiceImpl implements WytoLoanService{

	public WY001Response executeWY001(WY001Request request) {
		ILogicComponent logicComponent = LogicComponentFactory.create("com.bos.gjService.GjtoLoanLogic");
		Object[] objs = null;
		try {
			objs = logicComponent.invoke("exeWY001", new Object[] { request });
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return (WY001Response)objs[0];
	}
}
