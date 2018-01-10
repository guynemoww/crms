package testWebService.testWSMethod;

import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;

import com.eos.engine.component.ILogicComponent;
import com.eos.system.annotation.Bizlet;
import com.eos.system.exception.EOSException;
import com.primeton.ext.engine.component.LogicComponentFactory;

import testWebService.WdtoLoanServiceImplServiceServiceStub;
import testWebService.WdtoLoanServiceImplServiceServiceStub.D001Response;
import testWebService.WdtoLoanServiceImplServiceServiceStub.D001ResponseBody;
import testWebService.WdtoLoanServiceImplServiceServiceStub.ExecuteD001;
import testWebService.WdtoLoanServiceImplServiceServiceStub.D001Request;
import testWebService.WdtoLoanServiceImplServiceServiceStub.D001RequestBody;
import testWebService.WdtoLoanServiceImplServiceServiceStub.ExecuteD001Response;
import testWebService.WdtoLoanServiceImplServiceServiceStub.ReqTranHeader;
import testWebService.WdtoLoanServiceImplServiceServiceStub.RequestHeader;

public class TestWdWebService {

	@Bizlet("")
	public void testWebService() {
		WdtoLoanServiceImplServiceServiceStub stub;
		try {
			stub = new WdtoLoanServiceImplServiceServiceStub();

			ExecuteD001 executeD001 = new ExecuteD001();
			D001Request param = new D001Request();
			RequestHeader header = new RequestHeader();
			ReqTranHeader tran = new ReqTranHeader();
			D001RequestBody paramBody = new D001RequestBody();

			header.setVersionNo("1");
			header.setReqSysCode("00201");
			header.setReqTime("20171225160120");
			tran.setHTxnCd("WD001");

			paramBody.setCerNum("510702197808086429");//证件号码
			paramBody.setEcifPartyNum("011004150467");//ecif客户编号

			param.setRequestHeader(header);
			param.setReqTranHeader(tran);
			param.setRequestBody(paramBody);
			
			executeD001.setIn0(param);
			ExecuteD001Response res = stub.executeD001(executeD001);
			D001Response d01res = res.getOut1();
			D001ResponseBody body = d01res.getResponseBody();
			System.out.println(d01res.getResponseBody().getCusName());
			System.out.println(d01res.getResTranHeader().getHRetMsg());
			System.out.println(d01res.getResponseBody().getAviLimit());
			System.out.println(body.getAviLimit());

		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

}











