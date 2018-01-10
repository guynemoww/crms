package com.bos.inter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import com.bos.pub.BizNumGenerator;
import com.bos.pub.GitUtil;
import com.eos.foundation.common.utils.DateUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.system.logging.Logger;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.ecds.client.ECDSClient;
import com.primeton.mgrcore.FXD011;
import com.primeton.mgrcore.OXD011_AccoutingReq;
import com.primeton.mgrcore.OXD012_AccoutingRes;
import com.primeton.mgrcore.OXD071_AccControlReq;
import com.primeton.mgrcore.OXD072_AccControlRes;
import com.primeton.mgrcore.OXD081_CustAccInfoQryReq;
import com.primeton.mgrcore.OXD082_CustAccInfoQryRes;
import com.primeton.mgrcore.client.CrmsMgrCallCoreImpl;
import com.primeton.mgrcore.client.CrmsMgrCallCoreProxy;
import com.primeton.plusclient.CrmsCallPlusImpl;
import com.primeton.plusclient.CrmsCallPlusProxy;

import commonj.sdo.DataObject;

public class InterScheduler {

	public void interChose(DataObject loanInfo) throws EOSException {
		try {
			String productType = (String) loanInfo.get("productType");
			/*
			 * if("01007007".equals(productType)){//进口信用证
			 * try {
			 * //LoanToGj lt = new LoanToGj();
			 * //lt.jkxyz(loanInfo);
			 * } catch (Throwable e) {
			 * e.printStackTrace();
			 * throw new EOSException(e.getMessage());
			 * }
			 * }else if("01007008".equals(productType)){//进口保函
			 * try {
			 * //LoanToGj lt = new LoanToGj();
			 * //lt.jkbh(loanInfo);
			 * } catch (Throwable e) {
			 * e.printStackTrace();
			 * throw new EOSException(e.getMessage());
			 * }
			 * }else if("01007001".equals(productType)){//出口信用证押汇
			 * try {
			 * //LoanToGj lt = new LoanToGj();
			 * //lt.ckxyzyh(loanInfo);
			 * } catch (Throwable e) {
			 * e.printStackTrace();
			 * throw new EOSException(e.getMessage());
			 * }
			 * }else if("01007002".equals(productType)){//出口TT押汇
			 * try {
			 * //LoanToGj lt = new LoanToGj();
			 * //lt.ckttyh(loanInfo);
			 * } catch (Throwable e) {
			 * e.printStackTrace();
			 * throw new EOSException(e.getMessage());
			 * }
			 * }else if("01007003".equals(productType)){//出口托收押汇
			 * try {
			 * //LoanToGj lt = new LoanToGj();
			 * //lt.cktsyh(loanInfo);
			 * } catch (Throwable e) {
			 * e.printStackTrace();
			 * throw new EOSException(e.getMessage());
			 * }
			 * }else if("01007004".equals(productType)){//进口信用证押汇
			 * try {
			 * //LoanToGj lt = new LoanToGj();
			 * //lt.jkxyzyh(loanInfo);
			 * } catch (Throwable e) {
			 * e.printStackTrace();
			 * throw new EOSException(e.getMessage());
			 * }
			 * }else if("01007005".equals(productType)){//进口TT押汇
			 * try {
			 * //LoanToGj lt = new LoanToGj();
			 * //lt.jkttyh(loanInfo);
			 * } catch (Throwable e) {
			 * e.printStackTrace();
			 * throw new EOSException(e.getMessage());
			 * }
			 * }else if("01007006".equals(productType)){//进口托收押汇
			 * try {
			 * //LoanToGj lt = new LoanToGj();
			 * //lt.jkdsyh(loanInfo);
			 * } catch (Throwable e) {
			 * e.printStackTrace();
			 * throw new EOSException(e.getMessage());
			 * }
			 * }else if("01008001".equals(productType)){//银承
			 * try {
			 * //LoanToHx lt = new LoanToHx();
			 * //lt.cdhp(loanInfo);
			 * } catch (Throwable e) {
			 * e.printStackTrace();
			 * throw new EOSException(e.getMessage());
			 * }
			 * }else if("01004001".equals(productType)||"01009001".equals(productType)||"01006001".equals(productType)
			 * ||"01009002".equals(productType)||"01010001".equals(productType)||
			 * "01011001".equals(productType)||"01012001".equals(productType)){//国内保理国内信用证、保函、信贷证明、项目贷款承诺函、贴现
			 * try {
			 * //LoanToHx lt = new LoanToHx();
			 * //lt.gnbl(loanInfo);
			 * } catch (Throwable e) {
			 * e.printStackTrace();
			 * throw new EOSException(e.getMessage());
			 * }
			 * }else{
			 * String loanId = loanInfo.getString("loanId");
			 * LoanToLcs ll = new LoanToLcs();
			 * ll.loanToLcs2(loanId);
			 * }
			 */

			/*
			 * 表内融资业务放款交易---调用国结接口
			 * 出口信用证押汇
			 * 出口托收押汇
			 * 进口信用证押汇
			 * 进口代付
			 * 国际福费廷
			 * 国际信用证打包贷款
			 * 进口代收押汇
			 * 进口T/T押汇
			 */
			if (ProductConstant.CKXYZYH.equals(productType) || ProductConstant.CKTSYH.equals(productType) || ProductConstant.JKXYZYH.equals(productType) || ProductConstant.JKDF.equals(productType)
					|| ProductConstant.GJFFT.equals(productType) || ProductConstant.GJXYZDBDK.equals(productType) || ProductConstant.JKDSYH.equals(productType)
					|| ProductConstant.JKTTYH.equals(productType)) {
				LoanToGj lt = new LoanToGj();
				lt.bnrzywfk(loanInfo);
			} else if (ProductConstant.GJXYZKZ.equals(productType)) {// 国际信用证开证
				LoanToGj lt = new LoanToGj();
				lt.gjxyzkz(loanInfo);
			} else if (ProductConstant.GJBH.equals(productType)) {// 国际保函
				LoanToGj lt = new LoanToGj();
				lt.gjbh(loanInfo);
			} else if (ProductConstant.THDB.equals(productType)) {// 提货担保
				LoanToGj lt = new LoanToGj();
				lt.thdb(loanInfo);
			} else if ("01006".equals(productType.substring(0, 5))) {// 贴现
				Logger log = GitUtil.getLogger("ECDSClient.S01001010021003");
				try {
					log.info("调用接口【银承签发】服务码【S01001010021003】开始");
					ECDSClient cleint = new ECDSClient();
					cleint.S01001010021003(loanInfo);
					log.info("调用接口【银承签发】服务码【S01001010021003】成功");
				} catch (Throwable e) {
					e.printStackTrace();
					log.info("调用接口【银承签发】服务码【S01001010021003】失败");
					throw new EOSException(e.getMessage());
				}
			} else if ("01008".equals(productType.substring(0, 5))) {// 银行承兑汇票
				Logger log = GitUtil.getLogger("ECDSClient.S01001010021001");
				try {
					log.info("调用接口【银承签发】服务码【S01001010021001】开始");
					ECDSClient cleint = new ECDSClient();
					cleint.S01001010021001(loanInfo);
					log.info("调用接口【银承签发】服务码【S01001010021001】成功");
				} catch (Exception e) {
					log.info("调用接口【银承签发】服务码【S01001010021001】失败");
					throw new EOSException(e.getMessage());
				}
			} else if("01009".equals(productType.substring(0, 5))){//国内保函，融资性保函，非融资性保函
				Map map = new HashMap();
				map.put("loanId", loanInfo.getString("loanId"));
				//Object[] accountInfo = (Object[]) DatabaseExt.queryByNamedSql("default", "com.bos.payInfo.queryForHx.queryAccountInfo", map);
				Object[] accountInfo = (Object[]) DatabaseExt.queryByNamedSql("default", "com.bos.payInfo.queryForHx.queryBzjAccountInfo", map);
				//获取保证金金额
				Object[] accountJeInfo = (Object[]) DatabaseExt.queryByNamedSql("default", "com.bos.payInfo.queryForHx.queryBzjJeInfo", map);
				//String accountPay = "";//放款账号
				//String accountRepay = "";//还款账号
				//if(accountInfo.length >= 2){
//					for(int i=0; i<accountInfo.length; i++){
//						DataObject data = (DataObject)accountInfo[i];
//						String zhlx = data.getString("ZHLX");
//						if("0".equals(zhlx)){//放款账号
//							accountPay = data.getString("ZH");
//						}
//						if("1".equals(zhlx)){//还款账号
//							accountRepay = data.getString("ZH");
//						}
//					}
//					String currencyCd = loanInfo.getString("currencyCd");//货币
//					CrmsCallPlusProxy proxy = new CrmsCallPlusImpl();
//					OXD011_AccoutingReq req = new OXD011_AccoutingReq();
//					req.setHxorg(loanInfo.getString("loanOrg"));
//					req.setAmount(loanInfo.getString("loanAmt"));
//					req.setRecNum(new BigInteger("2"));
//					req.setSummaryCode("B00152");//摘要代码
//					req.setSummaryDescription("国内保函");//摘要描述
//					FXD011[] msg = new FXD011[2];
//					FXD011 msgenty = new FXD011();
//					FXD011 msgenty1 = new FXD011();
//					req.setChargeSeq(String.valueOf(BizNumGenerator.getLcsStan()));
//					req.setOutSystemDate(GitUtil.getBusiDateYYYYMMDD());
//					req.setRecNum(new BigInteger("2"));
//					//req.setRemarkInfo("M");//类型 核心说不需要传此类型，通过账号判断表内或表外
//					msgenty.setDealType("0");
//					msgenty.setDrCrFlag("0");//借贷标志--0-借/收  1-贷/付
//					msgenty.setAcct(accountPay);
//					if("CNY".equals(currencyCd)){
//						msgenty.setCurrCode("01");
//						msgenty.setCashFlag("0");
//					}else{
//						if(currencyCd.equals("HKD")){//港币
//							msgenty.setCurrCode("13");
//						}else if(currencyCd.equals("JPY")){//日元
//							msgenty.setCurrCode("27");
//						}else if(currencyCd.equals("MOP")){//澳门元
//							msgenty.setCurrCode("81");
//						}else if(currencyCd.equals("AUD")){//澳洲元
//							msgenty.setCurrCode("29");
//						}else if(currencyCd.equals("SGD")){//新加坡元
//							msgenty.setCurrCode("32");
//						}else if(currencyCd.equals("CHF")){//瑞士法郎
//							msgenty.setCurrCode("15");
//						}else if(currencyCd.equals("GBP")){//英镑
//							msgenty.setCurrCode("12");
//						}else if(currencyCd.equals("USD")){//美元
//							msgenty.setCurrCode("14");
//						}else if(currencyCd.equals("EUR")){//欧元
//							msgenty.setCurrCode("38");
//						}else if(currencyCd.equals("CAD")){//加拿大元
//							msgenty.setCurrCode("28");
//						}else{
//							throw new EOSException("不支持的币种");
//						}
//						msgenty.setCashFlag("1");
//					}
//					msgenty.setTransAmt(loanInfo.getString("loanAmt"));
//					msgenty.setAcctFromGo("0");
//					msgenty.setPwdKind("00");
//					msgenty.setSignPassFlag("1");
//					msgenty.setVertLastboxSignFlag("0");
//					msgenty.setFeePayType("0");
//					msg[0]=msgenty;
//					msgenty1.setDealType("0");
//					msgenty1.setDrCrFlag("1");//借贷标志--0-借/收  1-贷/付
//					msgenty1.setAcct(accountRepay);
//					if("CNY".equals(currencyCd)){
//						msgenty1.setCurrCode("01");
//						msgenty1.setCashFlag("0");
//					}else{
//						if(currencyCd.equals("HKD")){//港币
//							msgenty1.setCurrCode("13");
//						}else if(currencyCd.equals("JPY")){//日元
//							msgenty1.setCurrCode("27");
//						}else if(currencyCd.equals("MOP")){//澳门元
//							msgenty1.setCurrCode("81");
//						}else if(currencyCd.equals("AUD")){//澳洲元
//							msgenty1.setCurrCode("29");
//						}else if(currencyCd.equals("SGD")){//新加坡元
//							msgenty1.setCurrCode("32");
//						}else if(currencyCd.equals("CHF")){//瑞士法郎
//							msgenty1.setCurrCode("15");
//						}else if(currencyCd.equals("GBP")){//英镑
//							msgenty1.setCurrCode("12");
//						}else if(currencyCd.equals("USD")){//美元
//							msgenty1.setCurrCode("14");
//						}else if(currencyCd.equals("EUR")){//欧元
//							msgenty1.setCurrCode("38");
//						}else if(currencyCd.equals("CAD")){//加拿大元
//							msgenty1.setCurrCode("28");
//						}else{
//							throw new EOSException("不支持的币种");
//						}
//						msgenty1.setCashFlag("1");
//					}
//					msgenty1.setTransAmt(loanInfo.getString("loanAmt"));
//					msgenty1.setAcctFromGo("0");
//					msgenty1.setPwdKind("00");
//					msgenty1.setSignPassFlag("1");
//					msgenty1.setVertLastboxSignFlag("0");
//					msgenty1.setFeePayType("0");
//					msg[1]=msgenty1;
				if(accountInfo.length >0 ){
				DataObject data = (DataObject)accountInfo[0];
				String bzjzh = data.getString("MARGIN_ACCOUNT");//保证金账号
				DataObject dataAmt = (DataObject)accountJeInfo[0];
				BigDecimal accAmt = dataAmt.getBigDecimal("BZJJE");//保证金金额
				CrmsMgrCallCoreProxy hxProxy = new CrmsMgrCallCoreImpl();
				OXD081_CustAccInfoQryReq cusReq = new OXD081_CustAccInfoQryReq();
				cusReq.setCustNo(bzjzh);//客户账号
				cusReq.setOrgNum(loanInfo.getString("loanOrg"));
				OXD082_CustAccInfoQryRes cusRes = hxProxy.executeXD08(cusReq);
				if("AAAAAAA".equals(cusRes.getResTranHeader().getHRetCode())){
					String accountName = cusRes.getOxd082ResBody().getCustAcctName();//客户账户名称
					String subAcctSeri = cusRes.getOxd082ResBody().getSubAcctSeri();//子账户序号
					String currCode = cusRes.getOxd082ResBody().getCurrCode();//货币代码
					String cashFlag = cusRes.getOxd082ResBody().getCashFlag();//钞汇标志
					String labtAcctNum = cusRes.getOxd082ResBody().getLabtAcctNum();//负债账号
					String vchKind = cusRes.getOxd082ResBody().getVchKind();//凭证种类
					String vchBatNo = cusRes.getOxd082ResBody().getVchBatNo();//凭证批号
					String vchSerialNo = cusRes.getOxd082ResBody().getVchSerialNo();//凭证序号
					OXD071_AccControlReq request = new OXD071_AccControlReq();
					request.setFreezeOperFlag("5");//5-控制 6-解控
					request.setFreezeType("32");//冻结种类
					request.setCustNo(bzjzh);//客户账号
					request.setAcctname(accountName);//账户名称
					request.setSubAcctSeri(subAcctSeri);//子账户序号
					request.setCurrCode(currCode);//货币代号
					request.setCashFlag(cashFlag);//钞汇标志
					request.setLabtAcctNum(labtAcctNum);//负债账号
					request.setFreezeAmt(accAmt.toString());//需冻结金额
					request.setFrzCase("国内保函保证金冻结");//冻结原因
					request.setVchKind(vchKind);//凭证种类
					request.setVchBatNo(vchBatNo);//凭证批号
					request.setVchSerialNo(vchSerialNo);//凭证序号
					request.setFreezeEndDate(DateUtil.format(loanInfo.getDate("endDate"), "yyyyMMdd"));//冻结终止日期
					request.setOrgNum(loanInfo.getString("loanOrg"));//机构
					OXD072_AccControlRes response = hxProxy.executeXD07(request);
					if("AAAAAAA".equals(response.getResTranHeader().getHRetCode())){
						//账号：XXXX（机构号）+9+XX（币种）+0+3656+00001
						String account =loanInfo.getString("loanOrg")+"9"+currCode+"0365600001";
						String currencyCd = loanInfo.getString("currencyCd");//货币
						CrmsCallPlusProxy proxy = new CrmsCallPlusImpl();
						OXD011_AccoutingReq req = new OXD011_AccoutingReq();
						req.setHxorg(loanInfo.getString("loanOrg"));
						req.setAmount(loanInfo.getString("loanAmt"));
						req.setSummaryCode("B00152");//摘要代码
						req.setSummaryDescription("国内保函");//摘要描述
						FXD011[] msg = new FXD011[1];
						FXD011 msgenty = new FXD011();
						req.setChargeSeq(String.valueOf(BizNumGenerator.getLcsStan()));
						req.setOutSystemDate(GitUtil.getBusiDateYYYYMMDD());
						req.setRecNum(new BigInteger("1"));
						msgenty.setDealType("0");
						msgenty.setDrCrFlag("0");//借贷标志--0-借/收  1-贷/付
						msgenty.setAcct(account);//账号信息
						if("CNY".equals(currencyCd)){
							msgenty.setCurrCode("01");
							msgenty.setCashFlag("0");
						}else{
							if(currencyCd.equals("HKD")){//港币
								msgenty.setCurrCode("13");
							}else if(currencyCd.equals("JPY")){//日元
								msgenty.setCurrCode("27");
							}else if(currencyCd.equals("MOP")){//澳门元
								msgenty.setCurrCode("81");
							}else if(currencyCd.equals("AUD")){//澳洲元
								msgenty.setCurrCode("29");
							}else if(currencyCd.equals("SGD")){//新加坡元
								msgenty.setCurrCode("32");
							}else if(currencyCd.equals("CHF")){//瑞士法郎
								msgenty.setCurrCode("15");
							}else if(currencyCd.equals("GBP")){//英镑
								msgenty.setCurrCode("12");
							}else if(currencyCd.equals("USD")){//美元
								msgenty.setCurrCode("14");
							}else if(currencyCd.equals("EUR")){//欧元
								msgenty.setCurrCode("38");
							}else if(currencyCd.equals("CAD")){//加拿大元
								msgenty.setCurrCode("28");
							}else{
								throw new EOSException("不支持的币种");
							}
							msgenty.setCashFlag("1");
						}
						msgenty.setTransAmt(loanInfo.getString("loanAmt"));
						msgenty.setAcctFromGo("0");
						msgenty.setPwdKind("00");
						msgenty.setSignPassFlag("1");
						msgenty.setVertLastboxSignFlag("0");
						msgenty.setFeePayType("0");
						msg[0]=msgenty;
						req.setFxd011(msg);
						OXD012_AccoutingRes rs = proxy.executeXD01(req);
						if(!"AAAAAAA".equals(rs.getResTranHeader().getHRetCode())){
							throw new EOSException(rs.getResTranHeader().getHRetMsg());
						}
						Map valMaps = new HashMap();
						valMaps.put("LOAN_ID", loanInfo.getString("loanId"));
						valMaps.put("CUST_ACCOUNT", response.getOxd072ResBody().getCustNo());
						valMaps.put("ACCOUNT_NAME",  response.getOxd072ResBody().getAcctname());
						valMaps.put("FRE_AMT",  response.getOxd072ResBody().getFreezeAmt());
						valMaps.put("FRE_RESION",  response.getOxd072ResBody().getFrzCase());
						valMaps.put("FRE_TYPE",  response.getOxd072ResBody().getFreezeEnsureFileType());
						valMaps.put("FRE_NO",  response.getOxd072ResBody().getFreezeNotifyNo());
						valMaps.put("EXAMNU",  response.getOxd072ResBody().getApprover());
						valMaps.put("FRE_NUM",  response.getOxd072ResBody().getFrzNum());
						valMaps.put("ACCOUNT_AMT",  response.getOxd072ResBody().getAccrrestAmt());
						valMaps.put("OUT_ACCOUNT", account);
						DatabaseExt.executeNamedSql("default", "com.bos.payInfo.queryForHx.insertGuanteeInfo", valMaps);
					}else{
						throw new EOSException(response.getResTranHeader().getHRetMsg());
					}
				}else{
					throw new EOSException(cusRes.getResTranHeader().getHRetMsg());
				}
				}else{
					throw new EOSException("请添加保证金账户信息");
				}
			} else {
				String loanId = loanInfo.getString("loanId");
				LoanToLcs ll = new LoanToLcs();
				ll.loanToLcs2(loanId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		}
	}
}
