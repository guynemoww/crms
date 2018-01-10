package testWebService.testWSMethod;

import com.eos.engine.component.ILogicComponent;
import com.primeton.ext.engine.component.LogicComponentFactory;

public class TestWebServiceImpl {

	public void testWebServiceImpl(){
		///测试  
		ILogicComponent logicComponent = LogicComponentFactory.create("testWebService.testWSMethod.testWS");
		Object[] objs3 = new Object[1];
		Object[] params = new Object[1];
		String acc ="1";
		params[0] = acc;
		try {
			objs3 = logicComponent.invoke("exewd001", params);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		////测试代码
	}
	public static void main(String[] args) {
		new TestWebServiceImpl().testWebServiceImpl();
	}
}
