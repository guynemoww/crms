package com.bos.gjService;

import com.eos.engine.component.ILogicComponent;
import com.primeton.ext.engine.component.LogicComponentFactory;
/**
 * 国结调用信贷接口实现类
 */
public class GjtoLoanServiceImpl implements GjtoLoanService {

	public G001Response executeG001(G001Request g001Request) {
		ILogicComponent logicComponent = LogicComponentFactory.create("com.bos.gjService.GjtoLoanLogic");
		Object[] objs = null;
		try {
			objs = logicComponent.invoke("exeG001", new Object[] { g001Request });
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return (G001Response)objs[0];
	}

	public G002Response executeG002(G002Request g002Request) {
		ILogicComponent logicComponent = LogicComponentFactory.create("com.bos.gjService.GjtoLoanLogic");
		Object[] objs = null;
		try {
			objs = logicComponent.invoke("exeG002", new Object[] { g002Request });
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return (G002Response)objs[0];
	}

	public G003Response executeG003(G003Request g003Request) {
		ILogicComponent logicComponent = LogicComponentFactory.create("com.bos.gjService.GjtoLoanLogic");
		Object[] objs = null;
		try {
			objs = logicComponent.invoke("exeG003", new Object[] { g003Request });
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return (G003Response)objs[0];
	}

	public G004Response executeG004(G004Request g004Request) {
		ILogicComponent logicComponent = LogicComponentFactory.create("com.bos.gjService.GjtoLoanLogic");
		Object[] objs = null;
		try {
			objs = logicComponent.invoke("exeG004", new Object[] { g004Request });
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return (G004Response)objs[0];
	}

	public G005Response executeG005(G005Request g005Request) {
		ILogicComponent logicComponent = LogicComponentFactory.create("com.bos.gjService.GjtoLoanLogic");
		Object[] objs = null;
		try {
			objs = logicComponent.invoke("exeG005", new Object[] { g005Request });
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return (G005Response)objs[0];
	}

	public G006Response executeG006(G006Request g006Resquest) {
		ILogicComponent logicComponent = LogicComponentFactory.create("com.bos.gjService.GjtoLoanLogic");
		Object[] objs = null;
		try {
			objs = logicComponent.invoke("exeG006", new Object[] { g006Resquest });
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return (G006Response)objs[0];
	}
}
