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
public class NaturalPerson {
	@Bizlet(value = "通过身份证号码,返回生日性别.")
	public Map<String, String> certNum(String certNum) throws EOSException {
		if(certNum.length()==18){
			Map<String, String> map = new HashMap<String, String>();
			String birthday = certNum.substring(6, 14);
			String gender = certNum.substring(14, 17);
			if (Integer.parseInt(gender) % 2 == 0) {
				gender = "2";
			} else {
				gender = "1";
			}
			map.put("birthday", birthday);
			map.put("gender", gender);
			return map;
		}else{
			Map<String, String> map = new HashMap<String, String>();
			String birthday = certNum.substring(6, 12);
			String gender = certNum.substring(12, 15);
			if (Integer.parseInt(gender) % 2 == 0) {
				gender = "2";
			} else {
				gender = "1";
			}
			map.put("birthday",DateUtil.convertDate2String(DateUtil.convertString2Date(birthday, "yyMMdd"),"yyyyMMdd"));
			map.put("gender", gender);
			return map;
		}
		
	}
	@Bizlet(value = "对私客户基本信息创建与维护 从ECIF获取ECIF客户号")
	public Map CPsnCustListQuery(DataObject party,DataObject natural) throws Exception {
		IcustEcif custEcif=new  CustEcifImpl();
		String partyName=party.getString("partyName");
		String certType=natural.getString("certType");
		String certNum=natural.getString("certNum");
		FMT_CRMS_SVR_S0110102000B101_IN requestB101=new FMT_CRMS_SVR_S0110102000B101_IN();
		requestB101.setCERT_NO(certNum);//证件号码
		requestB101.setPARTY_NAME(partyName);//客户名称
		if("121".equals(certType) || "122".equals(certType)){
			requestB101.setCERT_TYPE("12");//证件类型
			requestB101.setCERT_SUB_TYPE(certType);//证件细分类型
		}else{
			requestB101.setCERT_TYPE(certType);//证件类型
		}
/*		requestA102.setCERT_TYPE("12");//证件类型
*/	     //requestB101.setRESOLVE_TYPE("2");//客户识别方式
		S0110102000B101Response	responseB101=custEcif.CPsnCustBaseInfoMaint(requestB101);
		String msgg=responseB101.getResTranHeader().getHRetMsg();
		String msg=responseB101.getResTranHeader().getHRetCode();
		String ecifPartyNum=responseB101.getResponseBody().getECIF_CUST_NO();
		Map map = new HashMap();
		map.put("ecifPartyNum", ecifPartyNum);
		map.put("msg", msg);
		map.put("msgg", msgg);
		return map;
	}
	
	
	@Bizlet(value = "查询对私客户基本信息")
	public Map PsnCustBaseQuery(DataObject party,DataObject natural) throws Exception {
/*		P2pCreditImpl p2p =new P2pCreditImpl();
		p2p.p2pCreditReport("50bf5f006fa642c892fd5633b0ad9a51", "011005275976", "00AAAFTDAAEAABLIdAAz");*/
		Map map = new HashMap();
		IcustEcif custEcif=new  CustEcifImpl();
		DataObject patry1 = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
		DataObject natural1 = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmNaturalPerson");
		patry1.set("partyId", natural.getString("partyId"));
		DatabaseUtil.expandEntity("default", patry1);
		natural1.set("partyId", natural.getString("partyId"));
		DatabaseUtil.expandEntity("default", natural1);
		String partyName=patry1.getString("partyName");
		String certType=natural1.getString("certType");
		String certNo=natural1.getString("certNum");
		String custNo=patry1.getString("ecifPartyNum");
		FMT_CRMS_SVR_S0110101000A102_IN requestA102=new FMT_CRMS_SVR_S0110101000A102_IN();
		requestA102.setRESOLVE_TYPE("1");
		/**
		 * 写死 先 013000000803
		 */
		requestA102.setECIF_CUST_NO(custNo);
//		requestA102.setPARTY_NAME(partyName);
//		requestA102.setCERT_TYPE(certType);
//		requestA102.setCERT_NO(certNo);
		S0110101000A102Response	responseA102=custEcif.CPsnCustBaseInfoQuery(requestA102);
		String msgg=responseA102.getResTranHeader().getHRetMsg();
		String msg=responseA102.getResTranHeader().getHRetCode();
		FMT_CRMS_SVR_S0110101000A102_OUT resBody =responseA102.getResponseBody();
		if("AAAAAAA".equals(msg)){
			map =getnaturalAndpatry(resBody,party,natural1);
		}
		map.put("msgg", msgg);
		map.put("msg", msg);
		return map;
	}
	@Bizlet(value = "查询对私客户基本信息白名单客户使用")
	public Map PsnCustBaseQuery(DataObject party,DataObject natural,String orgNum) throws Exception {
		Map map = new HashMap();
		IcustEcif custEcif=new  CustEcifImpl();
		DataObject patry1 = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
		DataObject natural1 = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmNaturalPerson");
		DataObject team = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmManagementTeam");
		patry1.set("partyId", natural.getString("partyId"));
		DatabaseUtil.expandEntity("default", patry1);
		natural1.set("partyId", natural.getString("partyId"));
		DatabaseUtil.expandEntity("default", natural1);
		team=EntityUtil.searchEntity("com.bos.dataset.csm.TbCsmManagementTeam","partyId",natural.getString("partyId"));
		if(team!=null&&!"".equals(team)){
		orgNum=team.getString("orgNum");}
		String custNo=patry1.getString("ecifPartyNum");
		FMT_CRMS_SVR_S0110101000A102_IN requestA102=new FMT_CRMS_SVR_S0110101000A102_IN();
		requestA102.setRESOLVE_TYPE("1");
		requestA102.setECIF_CUST_NO(custNo);
		
		S0110101000A102Response	responseA102=custEcif.CPsnCustBaseInfoQuery(requestA102,orgNum);
		String msgg=responseA102.getResTranHeader().getHRetMsg();
		String msg=responseA102.getResTranHeader().getHRetCode();
		FMT_CRMS_SVR_S0110101000A102_OUT resBody =responseA102.getResponseBody();
		if("AAAAAAA".equals(msg)){
			map =getnaturalAndpatry(resBody,party,natural1,orgNum);
		}
		map.put("msgg", msgg);
		map.put("msg", msg);
		return map;
	}
	@Bizlet(value = "维护ECIF信息")
	public Map UpdateCPsnCustListQuery(DataObject patry,DataObject natural) throws Exception {
/*		ReqestB101 reqB101 = setReqestB101toEcif(patry,natural);*/
		IcustEcif custEcif=new  CustEcifImpl();
//		String custName=patry.getString("partyName");
//		String certKind=natural.getString("certType");
//		String certNo=natural.getString("certNum");
//		ReqestB101 reqest=new ReqestB101();
//		reqest.setCustName(custName);
//		reqest.setCertKind(certKind);
//		reqest.setCertNo(certNo);
		FMT_CRMS_SVR_S0110102000B101_IN requestB101=new FMT_CRMS_SVR_S0110102000B101_IN();
		requestB101.setECIF_CUST_NO(patry.getString("ecifPartyNum"));//ECIF客户编号
     	//requestB101.setECIF_CUST_NO("013000000803");//ECIF客户编号
        requestB101.setPARTY_NAME(patry.getString("partyName"));//客户名称
	    String certType=natural.getString("certType");
//	    certType=certTypeCrmsToEcif(certType);
	    requestB101.setCERT_TYPE(certType);//证件类型
	    requestB101.setCERT_NO(natural.getString("certNum"));//证件号码
		requestB101.setRSDT_TYPE(natural.getString("resdntCharCd"));//居民性质
		requestB101.setGENDER_CODE(natural.getString("genderCd"));//性别
		String curdate=natural.getString("birthday");
		if(curdate!=null&&curdate.length()>10){
			
			curdate=curdate.substring(0, 10);
			curdate=curdate.replace("-", "");
		}
		requestB101.setBIRTH_DATE(curdate);//出生日期
		
		requestB101.setNAT_CODE(natural.getString("countrySign"));//国籍
	//	requestB101.setPEOPLE(natural.getString("nation"));//民族
		requestB101.setMARITAL_STAT(natural.getString("marriageCd"));//婚姻状况
		requestB101.setFARMER_FLAG(natural.getString("isFarmer"));//是否农户
		requestB101.setRGSTER_AREA(natural.getString("hukouRegisted"));//户籍所在地
		requestB101.setHIGHEST_DEGREE(natural.getString("degreeCd"));//最高学位
		requestB101.setPROFESSION_CODE(natural.getString("profession"));//职业
		requestB101.setTECH_TITLE(natural.getString("professionalTitle"));//职称
		requestB101.setUNIT_POSITION(natural.getString("accountingAssistant"));//职务
		//requestB101.setWORK_START_DATE(natural.getString("workYears"));//目前工作持续年限
	//	requestB101.setFAM_MEMB_TOTAL(natural.getString("familyNumber"));//家庭人口
	/*	requestB101.setNUM_DEPEND(natural.getString("provideForNumber"));//供养人口
*/		//requestB101.setRSDT_TYPE(natural.getString("houseProperty"));//住宅性质
		requestB101.setINDUSTRY_TYPE(natural.getString("industry"));//行业
		requestB101.setUNIT_NAME(natural.getString("workUnit"));//工作单位
		requestB101.setYEAR_SALARY(natural.getString("annualsalary"));//年收入
		requestB101.setSHAREHOLDER_FLAG(natural.getString("stockholderOfBank"));//我行股东标志：
		requestB101.setBANK_REL_CODE(natural.getString("isBasebankRelaCust"));//是否我行关联方：
		S0110102000B101Response	responseB101=custEcif.CPsnCustBaseInfoMaint(requestB101);
		String msgg=responseB101.getResTranHeader().getHRetMsg();
		String msg=responseB101.getResTranHeader().getHRetCode();
		Map map = new HashMap();
		Map mapAdd = new HashMap();
		mapAdd=CCustAddrInfoMaint(patry,natural);
		String msggA=(String) mapAdd.get("msgg");
		String msgA=(String) mapAdd.get("msg");
		if("AAAAAAA".equals(msg)&&"AAAAAAA".equals(msgA)){
			map.put("msgg", msgg);
			map.put("msg", msg);
		}else{
			if(!"AAAAAAA".equals(msgA)){
				if(msggA.contains("地址")){
					map.put("msgg", "[地址]内容未通过校验!");
					map.put("msg", msgA);
				}else{
				map.put("msgg", msggA);
				map.put("msg", msgA);
				}
			}else{
				map.put("msgg", msgg);
				map.put("msg", msg);
			}
		}

		return map;
	}
	
	@Bizlet(value = "维护ECIF地址信息")
	public Map CCustAddrInfoMaint(DataObject patry,DataObject natural) throws Exception {
		IcustEcif custEcif=new  CustEcifImpl();
		FMT_CRMS_SVR_S0110102000B011_IN requestB011=new FMT_CRMS_SVR_S0110102000B011_IN();
		requestB011.setECIF_CUST_NO(patry.getString("ecifPartyNum"));//ECIF客户编号
		requestB011.setRESOLVE_TYPE("1");
		ArrayList<FMT_CRMS_SVR_S0110102000B011_IN_SUB> grpAddrArry=new ArrayList<FMT_CRMS_SVR_S0110102000B011_IN_SUB>();
		String familyAddress=natural.getString("familyAddress");
	    String communicationAddress=natural.getString("communicationAddress");
	    String unitAdress=natural.getString("unitAdress");
	    if(!"".equals(familyAddress)&&familyAddress!=null){
	    FMT_CRMS_SVR_S0110102000B011_IN_SUB grpAddrA0=new FMT_CRMS_SVR_S0110102000B011_IN_SUB();
	    grpAddrA0.setADDR_TYPE("104");
	    grpAddrA0.setADDR_LINE(familyAddress);//家庭住址
	    grpAddrArry.add(grpAddrA0);
	    }
	    if(!"".equals(communicationAddress)&&communicationAddress!=null){
		FMT_CRMS_SVR_S0110102000B011_IN_SUB grpAddrA1=new FMT_CRMS_SVR_S0110102000B011_IN_SUB();
	    grpAddrA1.setADDR_TYPE("109");
	    grpAddrA1.setADDR_LINE(communicationAddress);//通讯地址
	    grpAddrArry.add(grpAddrA1);
	    }
	    if(!"".equals(unitAdress)&&unitAdress!=null){
		 FMT_CRMS_SVR_S0110102000B011_IN_SUB grpAddrA2=new FMT_CRMS_SVR_S0110102000B011_IN_SUB();
	    grpAddrA2.setADDR_TYPE("112");
	    grpAddrA2.setADDR_LINE(unitAdress);//单位地址
	    grpAddrArry.add(grpAddrA2);
	    }
		ArrayList<FMT_CRMS_SVR_S0110102000B011_IN_SUB1> grpAddrBrry=new ArrayList<FMT_CRMS_SVR_S0110102000B011_IN_SUB1>();
		String phoneNumber=natural.getString("phoneNumber");
	    String unitPhone=natural.getString("unitPhone");
	    String familyPhone=natural.getString("familyPhone");
	    if(!"".equals(phoneNumber)&&phoneNumber!=null){
		 FMT_CRMS_SVR_S0110102000B011_IN_SUB1 grpAddrB0=new FMT_CRMS_SVR_S0110102000B011_IN_SUB1();
		 grpAddrB0.setTELE_TYPE("102");
		 grpAddrB0.setPHONE_NO(phoneNumber);//手机号码
		 grpAddrBrry.add(grpAddrB0);
	    }
	    if(!"".equals(unitPhone)&&unitPhone!=null){
		 FMT_CRMS_SVR_S0110102000B011_IN_SUB1 grpAddrB1=new FMT_CRMS_SVR_S0110102000B011_IN_SUB1();
		 grpAddrB1.setTELE_TYPE("108");
		 grpAddrB1.setPHONE_NO(unitPhone);//单位电话
		 grpAddrBrry.add(grpAddrB1);
	    }
	    if(!"".equals(familyPhone)&&familyPhone!=null){
		 FMT_CRMS_SVR_S0110102000B011_IN_SUB1 grpAddrB2=new FMT_CRMS_SVR_S0110102000B011_IN_SUB1();
		 grpAddrB2.setTELE_TYPE("104");
		 grpAddrB2.setPHONE_NO(familyPhone);//家庭电话
		 grpAddrBrry.add(grpAddrB2);
	    }
		ArrayList<FMT_CRMS_SVR_S0110102000B011_IN_SUB2> grpAddrCrry=new ArrayList<FMT_CRMS_SVR_S0110102000B011_IN_SUB2>();
		String weixinNum=natural.getString("weixinNum");
	    String email=natural.getString("email");
	    if(!"".equals(weixinNum)&&weixinNum!=null){
		 FMT_CRMS_SVR_S0110102000B011_IN_SUB2 grpAddrC0=new FMT_CRMS_SVR_S0110102000B011_IN_SUB2();
		 grpAddrC0.setINTER_TYPE("104");
		 grpAddrC0.setINTERNET_ADDR(weixinNum);//微信号码
		 grpAddrCrry.add(grpAddrC0);
	    }
	    if(!"".equals(email)&&email!=null){
	   FMT_CRMS_SVR_S0110102000B011_IN_SUB2 grpAddrC1=new FMT_CRMS_SVR_S0110102000B011_IN_SUB2();
	   grpAddrC1.setINTER_TYPE("101");
	   grpAddrC1.setINTERNET_ADDR(email);//邮箱
		 grpAddrCrry.add(grpAddrC1);
	    }
		
	    /*
	     * 组装地址信息输入报文
	     */
	    
	    FMT_CRMS_SVR_S0110102000B011_IN_SUB[] A=new FMT_CRMS_SVR_S0110102000B011_IN_SUB[grpAddrArry.size()];
	    FMT_CRMS_SVR_S0110102000B011_IN_SUB1[] B=new FMT_CRMS_SVR_S0110102000B011_IN_SUB1[grpAddrBrry.size()];
	    FMT_CRMS_SVR_S0110102000B011_IN_SUB2[] C=new FMT_CRMS_SVR_S0110102000B011_IN_SUB2[grpAddrCrry.size()];
	    for(int i=0;i<grpAddrArry.size();i++){
	    	A[i]=grpAddrArry.get(i);
	    }
	    for(int i=0;i<grpAddrBrry.size();i++){
	    	B[i]=grpAddrBrry.get(i);
	    }
	    for(int i=0;i<grpAddrCrry.size();i++){
	    	C[i]=grpAddrCrry.get(i);
	    }
		Map map = new HashMap();
		if(!"".equals(A)&&A!=null){
			requestB011.setGRP_ADDR_A(A);
		}
		if(!"".equals(B)&&B!=null){
			requestB011.setGRP_ADDR_B(B);
		}
		if(!"".equals(C)&&C!=null){
			requestB011.setGRP_ADDR_C(C);
		}
		S0110102000B011Response	responseB011=custEcif.CCustAddrInfoMaint(requestB011);
		String msgg=responseB011.getResTranHeader().getHRetMsg();
		String msg=responseB011.getResTranHeader().getHRetCode();
		map.put("msgg", msgg);
		map.put("msg", msg);
		return map;
	}
  /*
   * 将ecif的信息查询出来  结合CRMS本地的数据传给前台
   */
	public Map getnaturalAndpatry(FMT_CRMS_SVR_S0110101000A102_OUT resBody,DataObject party,DataObject natural) throws Exception{
		DataObject patry1 = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
		DataObject natural1 = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmNaturalPerson");
		patry1.set("partyId", natural.getString("partyId"));
		DatabaseUtil.expandEntity("default", patry1);
		natural1.set("partyId", natural.getString("partyId"));
		DatabaseUtil.expandEntity("default", natural1);
		party.set("partyNum", patry1.getString("partyNum"));//客户编号  本地
		party.set("ecifPartyNum", patry1.getString("ecifPartyNum"));//ECIF客户编号
		if(!"".equals(resBody.getPARTY_NAME())&& resBody.getPARTY_NAME()!=null){
		party.set("partyName", resBody.getPARTY_NAME());//客户名称
		}
	    String certType=natural1.getString("certType");
	/*    certType=certTypeEcifToCrms(certType);*/
		party.set("isPotentialCust", patry1.getString("isPotentialCust"));//是否授信客户 本地
		natural.set("certType", certType);//证件类型
		if(!"".equals(resBody.getCERT_NO())&& resBody.getCERT_NO()!=null){
		natural.set("certNum", resBody.getCERT_NO());//证件号码
		}
		natural.set("middleCode", natural1.getString("middleCode"));//中征码信客户 本地
		natural.set("isThirdCust", natural1.getString("isThirdCust"));//是否第三方客户信客户 本地
		natural.set("thirdCustTypeCd", natural1.getString("thirdCustTypeCd"));//第三方客户类型信客户 本地
		natural.set("naturalPersonTypeCd", natural1.getString("naturalPersonTypeCd"));//自然人类型 本地
		if(!"".equals(resBody.getRSDT_TYPE())&& resBody.getRSDT_TYPE()!=null){
		natural.set("resdntCharCd", resBody.getRSDT_TYPE());//居民性质
		}
		if(!"".equals(resBody.getGENDER_CODE())&& resBody.getGENDER_CODE()!=null){
		natural.set("genderCd", resBody.getGENDER_CODE());//性别
		}
		if(!"".equals(resBody.getBIRTH_DATE())&& resBody.getBIRTH_DATE()!=null){
			String birthday=resBody.getBIRTH_DATE();
			String birthdayh=natural.getString("birthday");
			if(birthday.length()==8){
				String yy=birthday.substring(0, 4);
				String mm=birthday.substring(4, 6);
				String dd=birthday.substring(6, 8);
				birthdayh=yy+"-"+mm+"-"+dd;
				
			}
		natural.set("birthday", birthdayh);//出生日期
		}
		if(!"".equals(resBody.getBIRTH_DATE())&& resBody.getBIRTH_DATE()!=null){
		natural.set("countrySign", resBody.getNAT_CODE());//国籍
		}
	//	natural.set("nation", resBody.getPEOPLE_CODE());//民族
		if(!"".equals(resBody.getMARITAL_STAT())&& resBody.getMARITAL_STAT()!=null){
		natural.set("marriageCd", resBody.getMARITAL_STAT());//婚姻状况
		}
		if(!"".equals(resBody.getFARMER_FLAG()) && resBody.getFARMER_FLAG()!=null){
		natural.set("isFarmer", resBody.getFARMER_FLAG());//是否农户
		}
		if(!"".equals(resBody.getRGSTER_AREA())&& resBody.getRGSTER_AREA()!=null){
		natural.set("hukouRegisted", resBody.getRGSTER_AREA());//户籍所在地
		}
/*		natural.set("hukouProperty", "");//户籍性质 本地
		natural.set("streetPoliceStation", "");//所属街道派出所 本地
		natural.set("healthState", "");//健康状况 本地
*/		natural.set("educationBackgroudCd", natural1.getString("educationBackgroudCd"));//最高学历信客户 本地
		if(!"".equals(resBody.getHIGHEST_DEGREE())&&resBody.getHIGHEST_DEGREE()!=null){
        natural.set("degreeCd", resBody.getHIGHEST_DEGREE());//最高学位
		}
		//natural.set("profession", resBody.getPROFESSION_CODE());//职业
		//natural.set("professionalTitle", resBody.getTECH_TITLE());//职称
		//natural.set("accountingAssistant", resBody.getUNIT_POSITION());//职务
		//natural.set("positionProperty", natural1.getString("positionProperty"));//岗位性质信客户 本地
		//natural.set("workYears", resBody.getWORK_START_DATE());//目前工作开始时间
		natural.set("workYears", natural1.getString("workYears"));//目前工作持续年限 本地
	   //natural.set("familyNumber", resBody.getFAM_MEMB_TOTAL());//家庭人口
		if(!"".equals(resBody.getNUM_DEPEND())&&resBody.getNUM_DEPEND()!=null){
		natural.set("provideForNumber", resBody.getNUM_DEPEND());//供养人口
		}
		natural.set("familyAddress", natural1.getString("familyAddress"));//家庭住址客户 先用本地（以后调接口）
/*		if(!"".equals(resBody.getRSDT_TYPE())||resBody.getRSDT_TYPE()!=null){
		natural.set("houseProperty", resBody.getRSDT_TYPE());//住宅性质
		}*/
	//	natural.set("familyPhone", natural1.getString("familyPhone"));//家庭电话 先用本地（以后调接口）
		natural.set("communicationAddress", natural1.getString("communicationAddress"));//通讯地址 先用本地（以后调接口）
	//	natural.set("communicationAddressCode", natural1.getString("communicationAddressCode"));//通讯地址邮编 先用本地（以后调接口）
		natural.set("liveAddressCode", natural1.getString("liveAddressCode"));//居住地址邮编 先用本地（以后调接口）
	//	natural.set("phoneNumber", natural1.getString("phoneNumber"));//手机号码 先用本地（以后调接口）
		if(!"".equals(resBody.getINDUSTRY_TYPE())&&resBody.getINDUSTRY_TYPE()!=null){
		natural.set("industry", resBody.getINDUSTRY_TYPE());//行业
		}
	//	natural.set("weixinNum", natural1.getString("weixinNum"));//微信号 先用本地（以后调接口）
	//	natural.set("email", natural1.getString("email"));//电子邮箱 本地
		if(!"".equals(resBody.getUNIT_NAME())&&resBody.getUNIT_NAME()!=null){
		natural.set("workUnit", resBody.getUNIT_NAME());//工作单位
		}
		natural.set("unitAdress", natural1.getString("unitAdress"));//单位地址 先用本地（以后调接口）
		if(!"".equals(resBody.getYEAR_SALARY())&&resBody.getYEAR_SALARY()!=null){
		natural.set("annualsalary", resBody.getYEAR_SALARY());//年收入
		}
	//	natural.set("unitPhone", natural1.getString("unitPhone"));//单位电话： 先用本地（以后调接口）
		natural.set("unitAddressCode", natural1.getString("unitAddressCode"));//单位邮编： 先用本地（以后调接口）
		natural.set("industryDesc", natural1.getString("industryDesc"));//行业具体描述： 本地
		natural.set("jointGuarantee", natural1.getString("jointGuarantee"));//联保小组标志： 本地
		if(!"".equals(resBody.getSHAREHOLDER_FLAG())&&resBody.getSHAREHOLDER_FLAG()!=null){
		natural.set("stockholderOfBank", resBody.getSHAREHOLDER_FLAG());//我行股东标志：
		}
		if(!"".equals(resBody.getBANK_REL_CODE())&&resBody.getBANK_REL_CODE()!=null){
		natural.set("isBasebankRelaCust", resBody.getBANK_REL_CODE());//是否我行关联方：
		}
		natural.set("isGroupCust", natural1.getString("isGroupCust"));//是否统一授信成员：本地
		natural.set("attachGroupName", natural1.getString("attachGroupName"));//所属统一授信客户名称： 本地
		natural.set("whetherBlackList", natural1.getString("whetherBlackList"));//黑名单标志  本地
		natural.set("blackListReason", natural1.getString("blackListReason"));//加入黑名单原因：本地
		natural.set("isBankEmployee", natural1.getString("isBankEmployee"));//是否本行员工：本地
		String  custNo =patry1.getString("ecifPartyNum");
        CCustAddrInfo(custNo,"104","","",natural);//家庭住址
	    CCustAddrInfo(custNo,"109","","",natural);//通讯地址
	    CCustAddrInfo(custNo,"112","","",natural);//单位地址
	    CCustAddrInfo(custNo,"","102","",natural);//手机号码
	    CCustAddrInfo(custNo,"","108","",natural);//单位电话
	    CCustAddrInfo(custNo,"","104","",natural);//家庭电话
	    CCustAddrInfo(custNo,"","","104",natural);//微信号码
	    CCustAddrInfo(custNo,"","","101",natural);//邮箱
		Map map = new HashMap();
		map.put("natural", natural);
		map.put("party", party);
		return map;
		
	}
	/*
	   * 将ecif的信息查询出来给白名单客户使用 
	   */
		public Map getnaturalAndpatry(FMT_CRMS_SVR_S0110101000A102_OUT resBody,DataObject party,DataObject natural,String orgNum) throws Exception{
			DataObject patry1 = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
			DataObject natural1 = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmNaturalPerson");
			patry1.set("partyId", natural.getString("partyId"));
			DatabaseUtil.expandEntity("default", patry1);
			natural1.set("partyId", natural.getString("partyId"));
			DatabaseUtil.expandEntity("default", natural1);
			party.set("partyNum", patry1.getString("partyNum"));//客户编号  本地
			party.set("ecifPartyNum", patry1.getString("ecifPartyNum"));//ECIF客户编号
			party.set("partyName", patry1.getString("partyName"));//客户名称
		    String certType=natural1.getString("certType");
		/*    certType=certTypeEcifToCrms(certType);*/
			party.set("isPotentialCust", patry1.getString("isPotentialCust"));//是否授信客户 本地
			natural.set("certType", certType);//证件类型
			natural.set("certNum", natural1.getString("certNum"));//证件号码
			natural.set("middleCode", natural1.getString("middleCode"));//中征码信客户 本地
			natural.set("isThirdCust", natural1.getString("isThirdCust"));//是否第三方客户信客户 本地
			natural.set("thirdCustTypeCd", natural1.getString("thirdCustTypeCd"));//第三方客户类型信客户 本地
			natural.set("naturalPersonTypeCd", natural1.getString("naturalPersonTypeCd"));//自然人类型 本地
			if(!"".equals(resBody.getRSDT_TYPE())&& resBody.getRSDT_TYPE()!=null){
			natural.set("resdntCharCd", resBody.getRSDT_TYPE());//居民性质
			}
			if(!"".equals(resBody.getGENDER_CODE())&& resBody.getGENDER_CODE()!=null){
			natural.set("genderCd", resBody.getGENDER_CODE());//性别
			}
			natural.set("genderCd", natural1.getString("genderCd"));//性别
			if(!"".equals(resBody.getBIRTH_DATE())&& resBody.getBIRTH_DATE()!=null){
				String birthday=resBody.getBIRTH_DATE();
				String birthdayh=natural.getString("birthday");
				if(birthday.length()==8){
					String yy=birthday.substring(0, 4);
					String mm=birthday.substring(4, 6);
					String dd=birthday.substring(6, 8);
					birthdayh=yy+"-"+mm+"-"+dd;
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd");
					Date date=sdf.parse(birthdayh);
					natural.setDate("birthday", date);
				}

			}
			if(!"".equals(resBody.getBIRTH_DATE())&& resBody.getBIRTH_DATE()!=null){
			natural.set("countrySign", resBody.getNAT_CODE());//国籍
			}
		//	natural.set("nation", resBody.getPEOPLE_CODE());//民族
			if(!"".equals(resBody.getMARITAL_STAT())&& resBody.getMARITAL_STAT()!=null){
			natural.set("marriageCd", resBody.getMARITAL_STAT());//婚姻状况
			}
			if(!"".equals(resBody.getFARMER_FLAG()) && resBody.getFARMER_FLAG()!=null){
			natural.set("isFarmer", resBody.getFARMER_FLAG());//是否农户
			}
			if(!"".equals(resBody.getRGSTER_AREA())&& resBody.getRGSTER_AREA()!=null){
			natural.set("hukouRegisted", resBody.getRGSTER_AREA());//户籍所在地
			}
	/*		natural.set("hukouProperty", "");//户籍性质 本地
			natural.set("streetPoliceStation", "");//所属街道派出所 本地
			natural.set("healthState", "");//健康状况 本地
	*/		natural.set("educationBackgroudCd", natural1.getString("educationBackgroudCd"));//最高学历信客户 本地
			if(!"".equals(resBody.getHIGHEST_DEGREE())&&resBody.getHIGHEST_DEGREE()!=null){
	        natural.set("degreeCd", resBody.getHIGHEST_DEGREE());//最高学位
			}
			//natural.set("profession", resBody.getPROFESSION_CODE());//职业
			//natural.set("professionalTitle", resBody.getTECH_TITLE());//职称
			//natural.set("accountingAssistant", resBody.getUNIT_POSITION());//职务
			//natural.set("positionProperty", natural1.getString("positionProperty"));//岗位性质信客户 本地
			//natural.set("workYears", resBody.getWORK_START_DATE());//目前工作开始时间
			natural.set("workYears", natural1.getString("workYears"));//目前工作持续年限 本地
		   //natural.set("familyNumber", resBody.getFAM_MEMB_TOTAL());//家庭人口
			if(!"".equals(resBody.getNUM_DEPEND())&&resBody.getNUM_DEPEND()!=null){
			natural.set("provideForNumber", resBody.getNUM_DEPEND());//供养人口
			}
			natural.set("familyAddress", natural1.getString("familyAddress"));//家庭住址客户 先用本地（以后调接口）
	/*		if(!"".equals(resBody.getRSDT_TYPE())||resBody.getRSDT_TYPE()!=null){
			natural.set("houseProperty", resBody.getRSDT_TYPE());//住宅性质
			}*/
			natural.set("familyPhone", natural1.getString("familyPhone"));//家庭电话 先用本地（以后调接口）
			natural.set("communicationAddress", natural1.getString("communicationAddress"));//通讯地址 先用本地（以后调接口）
			natural.set("communicationAddressCode", natural1.getString("communicationAddressCode"));//通讯地址邮编 先用本地（以后调接口）
			natural.set("liveAddressCode", natural1.getString("liveAddressCode"));//居住地址邮编 先用本地（以后调接口）
			natural.set("phoneNumber", natural1.getString("phoneNumber"));//手机号码 先用本地（以后调接口）
			if(!"".equals(resBody.getINDUSTRY_TYPE())&&resBody.getINDUSTRY_TYPE()!=null){
			natural.set("industry", resBody.getINDUSTRY_TYPE());//行业
			}
			natural.set("weixinNum", natural1.getString("weixinNum"));//微信号 先用本地（以后调接口）
			natural.set("email", natural1.getString("email"));//电子邮箱 本地
			if(!"".equals(resBody.getUNIT_NAME())&&resBody.getUNIT_NAME()!=null){
			natural.set("workUnit", resBody.getUNIT_NAME());//工作单位
			}
			natural.set("unitAdress", natural1.getString("unitAdress"));//单位地址 先用本地（以后调接口）
			if(!"".equals(resBody.getYEAR_SALARY())&&resBody.getYEAR_SALARY()!=null){
			natural.set("annualsalary", resBody.getYEAR_SALARY());//年收入
			}
			natural.set("unitPhone", natural1.getString("unitPhone"));//单位电话： 先用本地（以后调接口）
			natural.set("unitAddressCode", natural1.getString("unitAddressCode"));//单位邮编： 先用本地（以后调接口）
			natural.set("industryDesc", natural1.getString("industryDesc"));//行业具体描述： 本地
			natural.set("jointGuarantee", natural1.getString("jointGuarantee"));//联保小组标志： 本地
			if(!"".equals(resBody.getSHAREHOLDER_FLAG())&&resBody.getSHAREHOLDER_FLAG()!=null){
			natural.set("stockholderOfBank", resBody.getSHAREHOLDER_FLAG());//我行股东标志：
			}
			if(!"".equals(resBody.getBANK_REL_CODE())&&resBody.getBANK_REL_CODE()!=null){
			natural.set("isBasebankRelaCust", resBody.getBANK_REL_CODE());//是否我行关联方：
			}
			natural.set("isGroupCust", natural1.getString("isGroupCust"));//是否统一授信成员：本地
			natural.set("attachGroupName", natural1.getString("attachGroupName"));//所属统一授信客户名称： 本地
			natural.set("whetherBlackList", natural1.getString("whetherBlackList"));//黑名单标志  本地
			natural.set("blackListReason", natural1.getString("blackListReason"));//加入黑名单原因：本地
			natural.set("isBankEmployee", natural1.getString("isBankEmployee"));//是否本行员工：本地
			String  custNo =patry1.getString("ecifPartyNum");
	        CCustAddrInfo(custNo,"104","","",natural,orgNum);//家庭住址
		    CCustAddrInfo(custNo,"109","","",natural,orgNum);//通讯地址
		    CCustAddrInfo(custNo,"112","","",natural,orgNum);//单位地址
		    CCustAddrInfo(custNo,"","102","",natural,orgNum);//手机号码
		    CCustAddrInfo(custNo,"","108","",natural,orgNum);//单位电话
		    CCustAddrInfo(custNo,"","104","",natural,orgNum);//家庭电话
		    CCustAddrInfo(custNo,"","","104",natural,orgNum);//微信号码
		    CCustAddrInfo(custNo,"","","101",natural,orgNum);//邮箱
			Map map = new HashMap();
			map.put("natural", natural);
			map.put("party", party);
			return map;
			
		}
	@Bizlet(value = "ECIF客户地址信息查询")
	public DataObject CCustAddrInfo(String ecifPartyNum,String addrType,String teleType,String interType,DataObject natural) throws Exception {
		IcustEcif custEcif=new  CustEcifImpl();
		FMT_CRMS_SVR_S0110101000A011_IN requestA011=new FMT_CRMS_SVR_S0110101000A011_IN();
		requestA011.setRESOLVE_TYPE("1");
		requestA011.setECIF_CUST_NO(ecifPartyNum);
		requestA011.setADDR_TYPE(addrType);
		requestA011.setTELE_TYPE(teleType);
		requestA011.setINTER_TYPE(interType);
		requestA011.setFIRST_NO_A("1");
		requestA011.setFIRST_NO_B("1");
		requestA011.setFIRST_NO_C("1");
		requestA011.setRESULT_SIZE_A("1");
		requestA011.setRESULT_SIZE_B("1");
		requestA011.setRESULT_SIZE_C("1");
		S0110101000A011Response	responseA011=custEcif.CCustAddrInfoQuery(requestA011);
		FMT_CRMS_SVR_S0110101000A011_OUT out=responseA011.getResponseBody();
		FMT_CRMS_SVR_S0110101000A011_OUT_SUB[]  grpAddrA=out.getGRP_ADDR_A();
		FMT_CRMS_SVR_S0110101000A011_OUT_SUB1[]  grpAddrB=out.getGRP_ADDR_B();
		FMT_CRMS_SVR_S0110101000A011_OUT_SUB2[]  grpAddrC=out.getGRP_ADDR_C();
		if(grpAddrA!=null&&grpAddrA.length>0){
			FMT_CRMS_SVR_S0110101000A011_OUT_SUB grpAddrA1 =	grpAddrA[0];
		if("104".equals(addrType)){
		String familyAddress=grpAddrA1.getADDR_LINE();//家庭住址
		if(familyAddress!=null&&!"".equals(familyAddress)){
		natural.set("familyAddress", familyAddress);
		}
		}
		if("109".equals(addrType)){
		String communicationAddress=grpAddrA1.getADDR_LINE();//通讯地址
		if(communicationAddress!=null&&!"".equals(communicationAddress)){
		natural.set("communicationAddress", communicationAddress);
		}
		}
		if("112".equals(addrType)){
		String unitAdress=grpAddrA1.getADDR_LINE();//单位地址
		if(unitAdress!=null&&!"".equals(unitAdress)){
		natural.set("unitAdress", unitAdress);
		}
		}
		}
		if(grpAddrB!=null&&grpAddrB.length>0){
			FMT_CRMS_SVR_S0110101000A011_OUT_SUB1 grpAddrB1 =	grpAddrB[0];
		if("102".equals(teleType)){
		String phoneNumber=grpAddrB1.getPHONE_NO();//手机号码
		if(phoneNumber!=null&&!"".equals(phoneNumber)){
		natural.set("phoneNumber", phoneNumber);
		}
		}
		if("108".equals(teleType)){
		String unitPhone=grpAddrB1.getPHONE_NO();//单位电话
		if(unitPhone!=null&&!"".equals(unitPhone)){
		natural.set("unitPhone", unitPhone);
		}
		}
		if("104".equals(teleType)){
		String familyPhone=grpAddrB1.getPHONE_NO();//家庭电话
		if(familyPhone!=null&&!"".equals(familyPhone)){
		natural.set("familyPhone", familyPhone);
		}
		}
		}
		if(grpAddrC!=null&&grpAddrC.length>0){
			FMT_CRMS_SVR_S0110101000A011_OUT_SUB2 grpAddrC1 =	grpAddrC[0];
		if("104".equals(interType)){
		String weixinNum=grpAddrC1.getINTERNET_ADDR();//微信号码
		if(weixinNum!=null&&!"".equals(weixinNum)){
		natural.set("weixinNum", weixinNum);
		}
		}
		if("101".equals(interType)){
		String email=grpAddrC1.getINTERNET_ADDR();//邮箱
		if(email!=null&&!"".equals(email)){
		natural.set("email", email);
		}
		}
		}
		return natural;
		
	}
	@Bizlet(value = "ECIF客户地址信息查询给白名单客户使用")
	public DataObject CCustAddrInfo(String ecifPartyNum,String addrType,String teleType,String interType,DataObject natural,String orgNum) throws Exception {
		IcustEcif custEcif=new  CustEcifImpl();
		FMT_CRMS_SVR_S0110101000A011_IN requestA011=new FMT_CRMS_SVR_S0110101000A011_IN();
		requestA011.setRESOLVE_TYPE("1");
		requestA011.setECIF_CUST_NO(ecifPartyNum);
		requestA011.setADDR_TYPE(addrType);
		requestA011.setTELE_TYPE(teleType);
		requestA011.setINTER_TYPE(interType);
		requestA011.setFIRST_NO_A("1");
		requestA011.setFIRST_NO_B("1");
		requestA011.setFIRST_NO_C("1");
		requestA011.setRESULT_SIZE_A("1");
		requestA011.setRESULT_SIZE_B("1");
		requestA011.setRESULT_SIZE_C("1");
		S0110101000A011Response	responseA011=custEcif.CCustAddrInfoQuery(requestA011,orgNum);
		FMT_CRMS_SVR_S0110101000A011_OUT out=responseA011.getResponseBody();
		FMT_CRMS_SVR_S0110101000A011_OUT_SUB[]  grpAddrA=out.getGRP_ADDR_A();
		FMT_CRMS_SVR_S0110101000A011_OUT_SUB1[]  grpAddrB=out.getGRP_ADDR_B();
		FMT_CRMS_SVR_S0110101000A011_OUT_SUB2[]  grpAddrC=out.getGRP_ADDR_C();
		if(grpAddrA!=null&&grpAddrA.length>0){
			FMT_CRMS_SVR_S0110101000A011_OUT_SUB grpAddrA1 =	grpAddrA[0];
		if("104".equals(addrType)){
		String familyAddress=grpAddrA1.getADDR_LINE();//家庭住址
		if(familyAddress!=null&&!"".equals(familyAddress)){
		natural.set("familyAddress", familyAddress);
		}
		}
		if("109".equals(addrType)){
		String communicationAddress=grpAddrA1.getADDR_LINE();//通讯地址
		if(communicationAddress!=null&&!"".equals(communicationAddress)){
		natural.set("communicationAddress", communicationAddress);
		}
		}
		if("112".equals(addrType)){
		String unitAdress=grpAddrA1.getADDR_LINE();//单位地址
		if(unitAdress!=null&&!"".equals(unitAdress)){
		natural.set("unitAdress", unitAdress);
		}
		}
		}
		if(grpAddrB!=null&&grpAddrB.length>0){
			FMT_CRMS_SVR_S0110101000A011_OUT_SUB1 grpAddrB1 =	grpAddrB[0];
		if("102".equals(teleType)){
		String phoneNumber=grpAddrB1.getPHONE_NO();//手机号码
		if(phoneNumber!=null&&!"".equals(phoneNumber)){
		natural.set("phoneNumber", phoneNumber);
		}
		}
		if("108".equals(teleType)){
		String unitPhone=grpAddrB1.getPHONE_NO();//单位电话
		if(unitPhone!=null&&!"".equals(unitPhone)){
		natural.set("unitPhone", unitPhone);
		}
		}
		if("104".equals(teleType)){
		String familyPhone=grpAddrB1.getPHONE_NO();//家庭电话
		if(familyPhone!=null&&!"".equals(familyPhone)){
		natural.set("familyPhone", familyPhone);
		}
		}
		}
		if(grpAddrC!=null&&grpAddrC.length>0){
			FMT_CRMS_SVR_S0110101000A011_OUT_SUB2 grpAddrC1 =	grpAddrC[0];
		if("104".equals(interType)){
		String weixinNum=grpAddrC1.getINTERNET_ADDR();//微信号码
		if(weixinNum!=null&&!"".equals(weixinNum)){
		natural.set("weixinNum", weixinNum);
		}
		}
		if("101".equals(interType)){
		String email=grpAddrC1.getINTERNET_ADDR();//邮箱
		if(email!=null&&!"".equals(email)){
		natural.set("email", email);
		}
		}
		}
		return natural;
		
	}
	@Bizlet(value = "身份核查")
	public Map checkIdentity(String certNum,String certType,String partyName) throws Exception{
		IcustEcif custEcif=new  CustEcifImpl();
		Map map = new HashMap();
		
		FMT_CRMS_SVR_S00601120005491_IN IN5491 =new FMT_CRMS_SVR_S00601120005491_IN();
		S00601120005491ServiceStub.FMT_CRMS_SVR_S00601120005491_IN_SUB_F54910 [] sub=new FMT_CRMS_SVR_S00601120005491_IN_SUB_F54910[1];
		S00601120005491ServiceStub.FMT_CRMS_SVR_S00601120005491_IN_SUB_F54910 ss=new FMT_CRMS_SVR_S00601120005491_IN_SUB_F54910();
		/**
		 * 交易码 5491 核查方式0-自动处理，1-人工处理  
		 */
		IN5491.setTransCode("5491");
		IN5491.setInspectWay("0");//核查方式0-自动处理，1-人工处理
		IN5491.setInspectKind("05");//业务种类05
		ss.setCertificateName(partyName);
		ss.setCertNo(certNum);
		if(certType!=null&&!"".equals(certType)&&certType.length()==3){
			certType=certType.substring(0, 2);
		}
		ss.setCertType(certType);
		sub[0]=ss;
		IN5491.setRecMsg(sub);
		S00601120005491Response res=custEcif.CCheckIdentity(IN5491);
		String msg=res.getResTranHeader().getHRetMsg();
		String code=res.getResTranHeader().getHRetCode();
		map.put("msg", msg);
		map.put("code", code);
		FMT_CRMS_SVR_S00601120005491_OUT_SUB_F54911[] outSub= res.getResponseBody().getRecMsg();
		if(null!=outSub){
			String result=outSub[0].getInspectResult();
			outSub[0].getCertificateName();
			outSub[0].getFilePath();
			outSub[0].getIssueInstitution();
			outSub[0].getPhoneRoute();
			outSub[0].getPlateFlow();
			map.put("result", result);
		}
		
		return map;
		
	}

	/*
	 * 将CRMS的证件类型转换成ECIF
	 */
	public String certTypeCrmsToEcif(String certType){
         if("101".equals(certType)){
        	 certType="10";//身份证
         } else if("102".equals(certType)){//临时身份证
        	 certType="17";
         } else if("110".equals(certType)){//户口簿
        	 certType="11";
         } else if("121".equals(certType)){//士兵证
        	 certType="14";
         } else if("122".equals(certType)){//军官证
        	 certType="13";
         } else if("123".equals(certType)){//解放军文职干部证
        	 certType="1b";
         } else if("132".equals(certType)){//警官证
        	 certType="19";
         } else if("140".equals(certType)){//护照
        	 certType="12";
         } else if("150".equals(certType)){//港澳居民来往内地通行证
        	 certType="15";
         } else if("151".equals(certType)){//台湾同胞来往内地通行证
        	 certType="16";
         } else if("153".equals(certType)){//外国人居留证
        	 certType="18";
         } else if("199".equals(certType)){//其他证件
        	 certType="1X";
         } else{ //组织机构代码证  202
        	 certType="1X";
         } 
		return certType;
		
	}
	
	/*
	 * 将ECIF的证件类型转换成CRMS
	 */
	public String certTypeEcifToCrms(String certType){

        if("10".equals(certType)){
       	 certType="101";//身份证
        }else if("17".equals(certType)){//户口簿
          	 certType="102";//临时身份证
        }else if("11".equals(certType)){//户口簿
       	 certType="110";
        } else if("14".equals(certType)){//士兵证
       	 certType="121";
        } else if("13".equals(certType)){//军官证
       	 certType="122";
        } else if("1b".equals(certType)){//解放军文职干部证
       	 certType="123";
        } else if("19".equals(certType)){//警官证
       	 certType="132";
        } else if("12".equals(certType)){//护照
       	 certType="140";
        } else if("15".equals(certType)||"1h".equals(certType)){//港澳居民来往内地通行证
       	 certType="150";
        } else if("16".equals(certType)){//台湾同胞来往内地通行证
       	 certType="151";
        } else if("18".equals(certType)){//外国人居留证
       	 certType="153";
        } else {//其他证件
       	 certType="199";
        } 
		return certType;
		
	}
	
}