package com.bos.gjService;

import com.bos.gjService.ZX001Request;
import com.bos.gjService.ZX001Response;
import com.eos.engine.component.ILogicComponent;
import com.eos.system.annotation.Bizlet;
import com.primeton.ext.engine.component.LogicComponentFactory;

@Bizlet("网贷")
public class ZxtoLoanServiceImpl implements IzxtoLoan {
	/**
	 * @param request 
	 * 征信调信贷
	 */
	@Override
	public ZX001Response executeX001(ZX001Request request)
			throws Exception {
		ILogicComponent logicComponent = LogicComponentFactory
				.create("com.bos.gjService.GjtoLoanLogic");
		Object[] objs = null;
		try {
			objs = logicComponent.invoke("ExeZX001", new Object[] { request });
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return (ZX001Response) objs[0];
	}

}
