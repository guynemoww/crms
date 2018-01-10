package com.bos.gjService;

import com.eos.engine.component.ILogicComponent;
import com.primeton.ext.engine.component.LogicComponentFactory;

public class PjtoLoanServiceImpl implements PjtoLoanService{

	public P001Response executeP001(P001Request request) {
		ILogicComponent logicComponent = LogicComponentFactory.create("com.bos.gjService.GjtoLoanLogic");
		Object[] objs = null;
		try {
			objs = logicComponent.invoke("exeP001", new Object[] { request });
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return (P001Response)objs[0];
	}

}
