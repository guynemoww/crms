package com.bos.gjService;

import com.eos.engine.component.ILogicComponent;
import com.primeton.ext.engine.component.LogicComponentFactory;

public class WdtoLoanServiceImpl implements WdtoLoanService{
	
	//网贷wd001白名单查询
	public D001Response executeD001(D001Request d001Request) {
		ILogicComponent logicComponent = LogicComponentFactory.create("com.bos.gjService.GjtoLoanLogic");
		Object[] objs = null;
		try {
			objs = logicComponent.invoke("exeD001", new Object[]{d001Request});
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return (D001Response)objs[0];
	}
	
	//网贷wd002业务受理
	public D002Response executeD002(D002Request d001Request) {
		ILogicComponent logicComponent = LogicComponentFactory.create("com.bos.gjService.GjtoLoanLogic");
		Object[] objs = null;
		try {
			objs = logicComponent.invoke("exeD002", new Object[]{d001Request});
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return (D002Response)objs[0];
	}
	
	//wd003网银放款接口
	public D003Response executeD003(D003Request request) {
		ILogicComponent logicComponent = LogicComponentFactory.create("com.bos.gjService.GjtoLoanLogic");
		Object[] objs = null;
		try {
			objs = logicComponent.invoke("exeD003", new Object[] { request });
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return (D003Response)objs[0];
	}
	//wd004网银还款接口 
	public D004Response executeD004(D004Request request) {
		ILogicComponent logicComponent = LogicComponentFactory.create("com.bos.gjService.GjtoLoanLogic");
		Object[] objs = null;
		try {
			objs = logicComponent.invoke("exeD004", new Object[] { request });
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return (D004Response)objs[0];
	}
	//wd005查询额度信息
	public D005Response executeD005(D005Request request) {
		ILogicComponent logicComponent = LogicComponentFactory.create("com.bos.gjService.GjtoLoanLogic");
		Object[] objs = null;
		try {
			objs = logicComponent.invoke("exeD005", new Object[] { request });
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return (D005Response)objs[0];
	}

	//wd006贷款本息查询 
	public D006Response executeD006(D006Request request) {
		ILogicComponent logicComponent = LogicComponentFactory.create("com.bos.gjService.GjtoLoanLogic");
		Object[] objs = null;
		try {
			objs = logicComponent.invoke("exeD006", new Object[] { request });
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return (D006Response)objs[0];
	}

	//wd007放款流水查询 
	public D007Response executeD007(D007Request request) {
		ILogicComponent logicComponent = LogicComponentFactory.create("com.bos.gjService.GjtoLoanLogic");
		Object[] objs = null;
		try {
			objs = logicComponent.invoke("exeD007", new Object[] { request });
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return (D007Response)objs[0];
	}

	//wd008还款流水
	public D008Response executeD008(D008Request request) {
		ILogicComponent logicComponent = LogicComponentFactory.create("com.bos.gjService.GjtoLoanLogic");
		Object[] objs = null;
		try {
			objs = logicComponent.invoke("exeD008", new Object[] { request });
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return (D008Response)objs[0];
	}
	//wd009查询借据信息 
	public D009Response executeD009(D009Request request) {
		ILogicComponent logicComponent = LogicComponentFactory.create("com.bos.gjService.GjtoLoanLogic");
		Object[] objs = null;
		try {
			objs = logicComponent.invoke("exeD009", new Object[] { request });
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return (D009Response)objs[0];
	}

	//wd010客户申请状态查询
	public D010Response executeD010(D010Request d010Request) {
		ILogicComponent logicComponent = LogicComponentFactory.create("com.bos.gjService.GjtoLoanLogic");
		Object[] objs = null;
		try {
			objs = logicComponent.invoke("exeD010", new Object[]{d010Request});
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		return (D010Response)objs[0];
	}

	//wd011还款账号变更
	public D011Response executeD011(D011Request d011Request) {
		ILogicComponent logicComponent = LogicComponentFactory.create("com.bos.gjService.GjtoLoanLogic");
		Object[] objs = null;
		try{
			objs = logicComponent.invoke("exeD011", new Object[]{d011Request});
		} catch(Throwable e){
			e.printStackTrace();
		}
		
		return (D011Response)objs[0];
	}
}




