/**
 * 
 */
package com.bos.grt.cdzykh;

import com.bos.pub.BizNumGenerator;
import com.bos.pub.GitUtil;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.system.annotation.Bizlet;
import com.primeton.plus.CrePayQueryRq;
import com.primeton.plusclient.CrmsCallPlusImpl;
import com.primeton.plusclient.CrmsCallPlusProxy;
import com.primeton.tsl.BaseVO;
import commonj.sdo.DataObject;

/**
 * @author lenovo
 * @date 2017-11-28 15:53:44
 *
 */
@Bizlet("")
public class CdzykhBxcx {

	@Bizlet("本息查询 ")
	public CrePayQueryRq bxcx(String summaryNum) throws Exception {
		CrmsCallPlusProxy proxy = new CrmsCallPlusImpl();
		CrePayQueryRq rq = new CrePayQueryRq();
		// 查询借据信息
		DataObject loanSummary = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanSummary");
		loanSummary.set("summaryNum", summaryNum);
		DatabaseUtil.expandEntityByTemplate("default", loanSummary, loanSummary);
		String bus_date = GitUtil.getBusiDateYYYYMMDD();//当前营业日期
		String loanId = loanSummary.getString("loanId");// 放款ID
		DataObject loanInfo = DataObjectUtil
				.createDataObject("com.bos.dataset.pay.TbLoanInfo");
		loanInfo.set("loanId", loanId);
		DatabaseUtil.expandEntity("default", loanInfo);
		String loanorg = loanInfo.getString("loanOrg");
		
		BaseVO baseVO = new BaseVO();
		baseVO.setTranCod("T1410");//交易代码
		baseVO.setTranDate(bus_date);
		baseVO.setAccSysDate(bus_date);//营业日期 检查该机构在机构表中是否存在
		baseVO.setTrnDep(loanorg);//交易机构，会校验
		baseVO.setOpnDep(loanorg);//贷款开户机构
		baseVO.setTranTimes("1");//交易次数标志 1次交易后填2，二次交易后填3
		baseVO.setToCoreSys("0");//交易是否转发核心系统标志 0=不转发；1=向核心系统转发
		baseVO.setTranFrom("47");
		baseVO.setOrigFrom("11000");
		baseVO.setLegPerCod("9999");
		baseVO.setOpr(GitUtil.getCurrentUserId());//操作员
		baseVO.setAut(GitUtil.getCurrentUserId());//授权员
		baseVO.setAcsMethStan(Long.valueOf(BizNumGenerator.getLcsStan()));//接入系统流水号
		baseVO.setRcnStan(Long.valueOf(BizNumGenerator.getLcsStan()));//对账流水号
		rq.setBaseVO(baseVO);
		rq.setDueNum(summaryNum);
		rq = proxy.executeT1410(rq);
		return rq;
	}
}
