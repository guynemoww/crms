package com.bos.grt.collService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class testCollService {

	public static void main(String[] args) {
		try{
			
//		CollServiceImplServiceServiceStub stub = new CollServiceImplServiceServiceStub();
//		CollServiceImplServiceServiceStub.CollServiceCommInter ser = new CollServiceImplServiceServiceStub.CollServiceCommInter();
//
//		ObjectMapper mapper = new ObjectMapper();
//		Map ypxxMap = new HashMap();
//		ypxxMap.put("clt_no","2017050500000018");
//		ypxxMap.put("beforeevalvalue",999);
//		ypxxMap.put("scene_id","0");//贷前
//		ypxxMap.put("trans_code","1103");//贷前
//		// Convert object to JSON string  
//		String ypxxJsonStr = null;
//		ypxxJsonStr = mapper.writeValueAsString(ypxxMap);
//		ser.setIn0(ypxxJsonStr);
//		String flag = stub.collServiceCommInter(ser).getOut1();
//		System.out.println(flag);
			
			
			CollServiceImplServiceServiceStub stub = new CollServiceImplServiceServiceStub();
			CollServiceImplServiceServiceStub.CollServiceCommInter ser = new CollServiceImplServiceServiceStub.CollServiceCommInter();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			ObjectMapper mapper = new ObjectMapper();
			Map ypxxMap = new HashMap();
			ypxxMap.put("ope_flag", "2");//出库
			ypxxMap.put("in_out_flag", "01");//出库类型 TB_GRT_OUT
			ypxxMap.put("in_out_result", "222");//出库原因 TB_GRT_OUT_DETAIL
			ypxxMap.put("clt_no", "2017050400000003");//押品编号 tb_grt_card_mortagage  tb_grt_mortgage_basic
			ypxxMap.put("guarantyright_nm","222");//权证名称 TB_GRT_REG_CARD
			ypxxMap.put("signeename","222");//押品所有权人名称 tb_csm_party
			ypxxMap.put("guarantyright_id", "222");//登记权证编号 TB_GRT_REG_CARD 
			ypxxMap.put("warrant_management_org_name","222");//登记机构名称TB_GRT_REG_CARD 
			ypxxMap.put("warrant_amt", 3.3);//登记金额 TB_GRT_REG_CARD
			ypxxMap.put("warrant_info_force_dt", sdf.format(new Date()));//登记生效日期 TB_GRT_REG_CARD
			ypxxMap.put("warrant_efficacy_dt",  sdf.format(new Date()));//登记到期日期TB_GRT_REG_CARD
			ypxxMap.put("warrant_management_org_no", "222");//保管机构TB_GRT_REG_CARD 
			ypxxMap.put("roll_out_dt",  sdf.format(new Date()));//出库时间 TB_GRT_OUT
			ypxxMap.put("storage_id", "111");//出入库申请流水号 TB_GRT_OUT
			ypxxMap.put("storage_issue_nm", "111");//发起人名称 om_employee
			ypxxMap.put("storage_issue_org", "111");//发起机构 TB_GRT_OUT
			ypxxMap.put("tally_dt",  sdf.format(new Date()));//记账日期 TB_GRT_OUT
			ypxxMap.put("storage_issue_id", "111");//发起人ID  TB_GRT_OUT
			ypxxMap.put("trans_code","1105");//价值同步接口交易码
			// Convert object to JSON string  
			String ypxxJsonStr = null;
			ypxxJsonStr = mapper.writeValueAsString(ypxxMap);
			ser.setIn0(ypxxJsonStr);
			String flag = stub.collServiceCommInter(ser).getOut1();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
