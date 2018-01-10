package com.bos.pub;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.system.annotation.Bizlet;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.mgrcore.FXD011;
import com.primeton.mgrcore.OXD011_AccoutingReq;
import com.primeton.mgrcore.OXD012_AccoutingRes;
import com.primeton.mgrcore.client.CrmsMgrCallCoreImpl;
import com.primeton.mgrcore.client.CrmsMgrCallCoreProxy;
import com.primeton.plus.PayVerifBackControlRq;
import com.primeton.plus.RepayControlCancel;
import com.primeton.plus.VerificationBackRq;
import com.primeton.plus.VerificationRq;
import com.primeton.plusclient.CrmsCallPlusImpl;
import com.primeton.plusclient.CrmsCallPlusProxy;
import com.primeton.tsl.BaseVO;
import com.primeton.tsl.TransferDataUtil;

import commonj.sdo.DataObject;

/**
 * 核销收回
 * @author CHENPAN
 *
 */
@Bizlet("核销收回")
public class VerifyBack {

	@Bizlet("根据通知书编号和借据编号核销收回")
	public String transVerifyBack(PayVerifBackControlRq reqControl){
		String result="";
		try {
			Map map = new HashMap();
			map.put("summaryNum", reqControl.getDueNum());
			Object[] relativeInfo = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.aft.conLoanChange.queryLoanInfo", map);
			Map maps = (Map) relativeInfo[0];
			String loanOrg = (String) maps.get("LOAN_ORG");
			String summaryId = (String) maps.get("SUMMARY_ID");
			CrmsCallPlusProxy proxy = new CrmsCallPlusImpl();
			BaseVO bvo = new BaseVO();
			bvo.setTranCod("T1208");//交易代码
			bvo.setOpr(GitUtil.getCurrentUserId());//操作员
			bvo.setAut(GitUtil.getCurrentUserId());//授权员
			bvo.setAcsMethStan(Long.valueOf(BizNumGenerator.getLcsStan()));//接入系统流水号
			bvo.setRcnStan(Long.valueOf(BizNumGenerator.getLcsStan()));//对账流水号
			bvo.setTrnDep(loanOrg);//交易机构，会校验
			bvo.setTranFrom("47");
			bvo.setTranTimes("2");//交易次数标志 1次交易后填2，二次交易后填3
			bvo.setToCoreSys("0");//交易是否转发核心系统标志 0=不转发；1=向核心系统转发
			bvo.setOpnDep(loanOrg);//贷款开户机构
			bvo.setAccSysDate(GitUtil.getBusiDateYYYYMMDD());//营业日期 检查该机构在机构表中是否存在
			bvo.setTranDate(GitUtil.getBusiDateYYYYMMDD());
			bvo.setOrigFrom("11000");
			bvo.setLegPerCod("9999");
			reqControl.setBaseVO(bvo);
			PayVerifBackControlRq rs = proxy.executeT1208(reqControl);
			if(!"00000".equals(rs.getBaseVO().getErrCod())){
				result = rs.getBaseVO().getErrMsg();
				throw new EOSException(rs.getBaseVO().getErrMsg());
			}
			VerificationBackRq rq1 = new VerificationBackRq();
			rq1.setDueNum(reqControl.getDueNum());
			rq1.setTelNo(reqControl.getTelNo());
			BaseVO vo = reqControl.getBaseVO();
			vo.setTranCod("T1108");
			vo.setTranTimes("1");
			rq1.setBaseVO(vo);
			VerificationBackRq rs1 = proxy.executeT1108(rq1);
			if(!"00000".equals(rs1.getBaseVO().getErrCod())){
				result = rs1.getBaseVO().getErrMsg();
				throw new EOSException(rs1.getBaseVO().getErrMsg());
			}
			VerificationBackRq rs2 = proxy.executeT1108(rs1);
			if(!"00000".equals(rs2.getBaseVO().getErrCod())){
				result = rs2.getBaseVO().getErrMsg();
				throw new EOSException(rs2.getBaseVO().getErrMsg());
			}
			CrmsMgrCallCoreProxy coreProxy = new CrmsMgrCallCoreImpl();
			OXD011_AccoutingReq coreReq = new OXD011_AccoutingReq();
			TransferDataUtil util = new TransferDataUtil();
			FXD011[] fxd011s = util.plusToHx(rs2.getAccJson());
			coreReq.setChargeSeq(String.valueOf(rs2.getBaseVO().getRcnStan()));
			coreReq.setOutSystemDate(rs2.getBaseVO().getAccSysDate());
			coreReq.setBusiType1("XDHS");
			coreReq.setThridTransCode("XD01");
			coreReq.setRecNum(new BigInteger(String.valueOf(fxd011s.length)));
			coreReq.setFxd011(fxd011s);
			coreReq.setRemarkInfo("B");//核销收回标志
			coreReq.setHxorg(loanOrg);
			coreReq.setSummaryCode("B00158");//摘要代码
			coreReq.setSummaryDescription("贷款核销收回");//摘要描述
			OXD012_AccoutingRes resp = coreProxy.executeXD01(coreReq);
			if(!"AAAAAAA".equals(resp.getResTranHeader().getHRetCode())){
				result = resp.getResTranHeader().getHRetMsg();
				RepayControlCancel cancel = new RepayControlCancel();
				cancel.setDueNum(reqControl.getDueNum());
				cancel.setTelNo(reqControl.getTelNo());
				BaseVO vos = rs2.getBaseVO();
				vos.setTranCod("B1102");
				vos.setTranTimes("1");
				vos.setToCoreSys("0");
				cancel.setBaseVO(vos);
				RepayControlCancel back = proxy.executeB1102(cancel);
				if(!"00000".equals(back.getBaseVO().getErrCod())){
					throw new EOSException("调用核算撤销接口失败"); 
				}
			}else{
				VerificationBackRq rs3 = proxy.executeT1108(rs2);
				if(!"00000".equals(rs3.getBaseVO().getErrCod())){
					throw new EOSException(rs3.getBaseVO().getErrMsg());
				}
				Object[] res = DatabaseExt.queryByNamedSql("aplus", "com.bos.batch.dealAccount.queryLoanInfo", reqControl.getDueNum());
				DataObject loanInfo = (DataObject) res[0];
				DataObject summary = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanSummary");
				summary.set("summaryId", summaryId);
				summary.set("jjye", loanInfo.getBigDecimal("V_JJYE")); // 借据余额
				BigDecimal ye = new BigDecimal("0").setScale(2, BigDecimal.ROUND_HALF_DOWN);
				BigDecimal yes = loanInfo.getBigDecimal("V_JJYE").setScale(2, BigDecimal.ROUND_HALF_DOWN);
				if(ye.equals(yes)){
					summary.set("summaryStatusCd", "04");
				}
				DatabaseUtil.updateEntity("default", summary);
				result="1";
			}
		} catch (Exception e) {
			throw new EOSException("核销收回调用接口异常！");
		}
		return result;
	}
}
