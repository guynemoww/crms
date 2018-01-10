/**
 * 
 */
package com.bos.csm.natural;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.bos.batch.DateUtil;
import com.bos.pub.entity.EntityUtil;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.system.annotation.Bizlet;
import com.eos.system.exception.EOSException;
import com.primeton.p2p.P2pCreditImpl;
import com.primeton.tsl.ecif.S00601120005491ServiceStub;
import com.primeton.tsl.ecif.S00601120005491ServiceStub.FMT_CRMS_SVR_S00601120005491_IN;
import com.primeton.tsl.ecif.S00601120005491ServiceStub.FMT_CRMS_SVR_S00601120005491_IN_SUB_F54910;
import com.primeton.tsl.ecif.S00601120005491ServiceStub.FMT_CRMS_SVR_S00601120005491_OUT_SUB_F54911;
import com.primeton.tsl.ecif.S00601120005491ServiceStub.S00601120005491Response;
import com.primeton.tsl.ecif.S0110101000A011ServiceStub.FMT_CRMS_SVR_S0110101000A011_IN;
import com.primeton.tsl.ecif.S0110101000A011ServiceStub.FMT_CRMS_SVR_S0110101000A011_OUT;
import com.primeton.tsl.ecif.S0110101000A011ServiceStub.FMT_CRMS_SVR_S0110101000A011_OUT_SUB;
import com.primeton.tsl.ecif.S0110101000A011ServiceStub.FMT_CRMS_SVR_S0110101000A011_OUT_SUB1;
import com.primeton.tsl.ecif.S0110101000A011ServiceStub.FMT_CRMS_SVR_S0110101000A011_OUT_SUB2;
import com.primeton.tsl.ecif.S0110101000A011ServiceStub.S0110101000A011Response;
import com.primeton.tsl.ecif.S0110101000A102ServiceStub.FMT_CRMS_SVR_S0110101000A102_IN;
import com.primeton.tsl.ecif.S0110101000A102ServiceStub.FMT_CRMS_SVR_S0110101000A102_OUT;
import com.primeton.tsl.ecif.S0110101000A102ServiceStub.S0110101000A102Response;
import com.primeton.tsl.ecif.S0110101000A107ServiceStub.FMT_CRMS_SVR_S0110101000A107_IN;
import com.primeton.tsl.ecif.S0110101000A107ServiceStub.FMT_CRMS_SVR_S0110101000A107_OUT_SUB;
import com.primeton.tsl.ecif.S0110101000A107ServiceStub.S0110101000A107Response;
import com.primeton.tsl.ecif.S0110102000B011ServiceStub.FMT_CRMS_SVR_S0110102000B011_IN;
import com.primeton.tsl.ecif.S0110102000B011ServiceStub.FMT_CRMS_SVR_S0110102000B011_IN_SUB;
import com.primeton.tsl.ecif.S0110102000B011ServiceStub.FMT_CRMS_SVR_S0110102000B011_IN_SUB1;
import com.primeton.tsl.ecif.S0110102000B011ServiceStub.FMT_CRMS_SVR_S0110102000B011_IN_SUB2;
import com.primeton.tsl.ecif.S0110102000B011ServiceStub.S0110102000B011Response;
import com.primeton.tsl.ecif.S0110102000B101ServiceStub.FMT_CRMS_SVR_S0110102000B101_IN;
import com.primeton.tsl.ecif.S0110102000B101ServiceStub.S0110102000B101Response;
import com.primeton.tsl.ecif.port.IcustEcif;
import com.primeton.tsl.ecif.port.impl.CustEcifImpl;
import com.primeton.tsl.reqest.ReqestA107;
import com.primeton.tsl.response.ResponseA107;

import commonj.sdo.DataObject;

/**
 * @author ganquan
 * @date 2015-05-19 15:54:20
 * 
 */
@Bizlet("")
public class WhitefNaturalPerson {
	@Bizlet(value = "提交判断")
	public String whiteCommit(String batchNumber) throws EOSException {
		String msg="1";
		if(batchNumber!=null&&!"".equals(batchNumber)){
			DataObject white = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmWhiteCustomer");
			white.set("batchNumber", batchNumber);
			DataObject[] whites= DatabaseUtil.queryEntitiesByTemplate("default", white);
			if(whites!=null){
				for (DataObject dataObject : whites) {
					String cusStatus=dataObject.getString("cusStatus");
					if(cusStatus!=null&&!"".equals(cusStatus)&&"01".equals(cusStatus)){//01表示准入
						msg=dataObject.getString("ecifPartyNum");
					}else{
					}
				
				}
			}
			
		}
		return msg;
	}
	
}