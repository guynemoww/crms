package com.bos.csm.pub;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.bos.pub.GitUtil;
import com.bos.pub.entity.EntityUtil;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.system.annotation.Bizlet;
import com.primeton.tsl.ecif.S0110101000A107ServiceStub.FMT_CRMS_SVR_S0110101000A107_IN;
import com.primeton.tsl.ecif.S0110101000A107ServiceStub.FMT_CRMS_SVR_S0110101000A107_OUT_SUB;
import com.primeton.tsl.ecif.S0110101000A107ServiceStub.S0110101000A107Response;
import com.primeton.tsl.ecif.S0110101000A108ServiceStub.FMT_CRMS_SVR_S0110101000A108_IN;
import com.primeton.tsl.ecif.S0110101000A108ServiceStub.FMT_CRMS_SVR_S0110101000A108_OUT_SUB;
import com.primeton.tsl.ecif.S0110101000A108ServiceStub.S0110101000A108Response;
import com.primeton.tsl.ecif.S0110102000B110ServiceStub;
import com.primeton.tsl.ecif.S0110102000B110ServiceStub.FMT_CRMS_SVR_S0110102000B110_IN_SUB;
import com.primeton.tsl.ecif.S0110102000B110ServiceStub.FMT_CRMS_SVR_S0110102000B110_OUT_SUB;
import com.primeton.tsl.ecif.S0110102000B110ServiceStub.S0110102000B110Response;
import com.primeton.tsl.ecif.S0110102000B111ServiceStub;
import com.primeton.tsl.ecif.S0110102000B111ServiceStub.S0110102000B111Response;
import com.primeton.tsl.ecif.S0110102000B112ServiceStub;
import com.primeton.tsl.ecif.S0110102000B112ServiceStub.FMT_CRMS_SVR_S0110102000B112_IN_SUB;
import com.primeton.tsl.ecif.S0110102000B112ServiceStub.FMT_CRMS_SVR_S0110102000B112_OUT_SUB;
import com.primeton.tsl.ecif.S0110102000B112ServiceStub.S0110102000B112Response;
import com.primeton.tsl.ecif.S0110102000B113ServiceStub;
import com.primeton.tsl.ecif.S0110102000B113ServiceStub.S0110102000B113Response;
import com.primeton.tsl.ecif.port.IcustEcif;
import com.primeton.tsl.ecif.port.impl.CustEcifImpl;

import commonj.sdo.DataObject;
/**
 * 
 * @author zhouxu
 *对私客户关系
 */
@Bizlet("")
public class NaturalPersonRel {
	
	
	
	@Bizlet(value = "对私客户关系信息查询")
	public Map PsnRelQuery(String cType,String ecifCustNo,String partyId) throws Exception{
		Map map = new HashMap();
		if("person".equals(cType)){
			map=PsnRelPsnQuery(ecifCustNo,partyId);
		}else{
			map=PsnRelComQuery(ecifCustNo,partyId);
		}
		return map;
		
	}
	
	@Bizlet(value = "对私客户关系信息查询")
	public Map PsnRelQueryBmd(String cType,String ecifCustNo,String partyId) throws Exception{
		  Map map = new HashMap();
		  Map map1 = new HashMap();
		  map= PsnRelPsnQueryBmd(ecifCustNo,partyId);
		 map1=PsnRelComQueryBmd(ecifCustNo,partyId);
		  String code=(String) map.get("code");
			if(!"AAAAAAA".equals(code)){
				return map;
			}else{
			return map1;
			}
	}
	
	
	/**
	 * 
	 * @param ecifPartyNum
	 * @return
	 * @throws Exception
	 * 对私客户关系个人信息查询
	 */
	@Bizlet(value = "对私客户关系个人信息查询")
	public Map PsnRelPsnQuery(String ecifCustNo,String partyId) throws Exception{
		ArrayList<DataObject> relatedPartyarr=new ArrayList<DataObject>();
		String resultSizePsn="";
		String size ="";
		int allCount=0;
		Map map = new HashMap();
		int ferst=1;
		do{
		IcustEcif custEcif =new CustEcifImpl();
		FMT_CRMS_SVR_S0110101000A107_IN requestA107=new FMT_CRMS_SVR_S0110101000A107_IN();
		requestA107.setFIRST_NO_PSN(String.valueOf(ferst));
		requestA107.setRESULT_SIZE_PSN("30");
		requestA107.setRESOLVE_TYPE("1");
		requestA107.setECIF_CUST_NO(ecifCustNo);
//		requestA107.setECIF_CUST_NO("013000000244");
		S0110101000A107Response	responseA107=custEcif.CPsnCustRelInfoQuery(requestA107);
		String msg=responseA107.getResTranHeader().getHRetMsg();
		String code=responseA107.getResTranHeader().getHRetCode();
	    resultSizePsn=responseA107.getResponseBody().getRESULT_SIZE_PSN();
	    size = responseA107.getResponseBody().getALL_RESULT_SIZE_PSN();
	    FMT_CRMS_SVR_S0110101000A107_OUT_SUB[]  ss=responseA107.getResponseBody().getGRP_REL_PSN();
	    map.put("msg", msg);
	    map.put("code", code);
		ferst=ferst+30;
		if(!"AAAAAAA".equals(code)){
		return map;
		}
		//将从Ecif查出的数据更新到本地库里
		String date=GitUtil.getBusiDateYYYYMMDD();
		if(null !=resultSizePsn && null !=size && Integer.parseInt(resultSizePsn)>0 && Integer.parseInt(size)>0){
			for(int i=0;i<ss.length;i++){
				FMT_CRMS_SVR_S0110101000A107_OUT_SUB A107=ss[i];
				DataObject relatedParty = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmNaturalRelative");//关联信息
				DataObject	relatedParty1 = 	EntityUtil.searchEntity("com.bos.dataset.csm.TbCsmNaturalRelative", "parSeqId", A107.getPAR_SEQ_ID(),"partyId",partyId);
				if(relatedParty1!=null&&!"".equals(relatedParty1)){
					relatedParty1.set("parSeqId", A107.getPAR_SEQ_ID());//关联关系记录编号
					relatedParty1.set("relationId", A107.getRELATION_ID());//关系Id
					relatedParty1.set("partyName", A107.getREL_NAME());//关联客户名称
					relatedParty1.set("certificateTypeCd",A107.getREL_CERT_TYPE());//证件类型
					relatedParty1.set("certId", A107.getREL_CERT_NO());//证件号码
					relatedParty1.set("appellation", A107.getRELATION_TYPE());//关联关系类型
					relatedParty1.set("custRela", A107.getRELATION_TYPE());//关联关系类型
					relatedParty1.set("updateTime", date);//更新日期
					relatedParty1.set("birthday", A107.getBIRTH_DATE());//生日
					relatedParty1.set("remark",A107.getREMARK());//描述
					if(null==A107.getREL_CUST_NO()){//为空 不是本行客户
						relatedParty1.set("iscout","0");//是否我行客户 0 否 1 是
						relatedParty1.set("partnercompany", A107.getWORK_CORP());//工作单位
						relatedParty1.set("partnerphonenum", A107.getFINDTEL_NO());//联系电话
						relatedParty1.set("certBeginDate", A107.getCERT_ISSUE_DATE());//签发日期
						relatedParty1.set("certEndDate", A107.getCERT_DUE_DATE());//到期日期
						relatedParty1.set("legalContry", A107.getCERT_ORG_NAT());//国别
					}else{
//						relatedParty1.set("iscout","1");//是否我行客户 0 否 1 是
//						relatedParty1.set("relativeidPartyId", A107.getREL_CUST_NO());//关联客户号
					}
					relatedParty1.set("custType", "02");//关联客户类型
					relatedPartyarr.add(relatedParty1);
				}else{
					relatedParty.set("partyId", partyId);//关联客户
					relatedParty.set("parSeqId", A107.getPAR_SEQ_ID());//关联关系记录编号
					relatedParty.set("relationId", A107.getRELATION_ID());//关系Id
					relatedParty.set("partyName", A107.getREL_NAME());//关联客户名称
					relatedParty.set("certificateTypeCd",A107.getREL_CERT_TYPE());//证件类型
					relatedParty.set("certId", A107.getREL_CERT_NO());//证件号码
					relatedParty.set("appellation", A107.getRELATION_TYPE());//关联关系类型
					relatedParty.set("custRela", A107.getRELATION_TYPE());//关联关系类型
					relatedParty.set("updateTime", date);//更新日期
					relatedParty.set("birthday", A107.getBIRTH_DATE());//生日
					relatedParty.set("remark",A107.getREMARK());//描述
					if(null==A107.getREL_CUST_NO()){//为空 不是本行客户
						relatedParty.set("iscout","0");//是否我行客户 0 否 1 是
						relatedParty.set("partnercompany", A107.getWORK_CORP());//工作单位
						relatedParty.set("partnerphonenum", A107.getFINDTEL_NO());//联系电话
						relatedParty.set("legalContry", A107.getCERT_ORG_NAT());//国别
						relatedParty.set("certBeginDate", A107.getCERT_ISSUE_DATE());//签发日期
						relatedParty.set("certEndDate", A107.getCERT_DUE_DATE());//到期日期
					}else{
//						relatedParty.set("iscout","1");//是否我行客户 0 否 1 是
//						relatedParty.set("relativeidPartyId", A107.getREL_CUST_NO());//关联客户号
					}
					relatedParty.set("custType", "02");//关联客户类型
				relatedPartyarr.add(relatedParty);
				}
				}
			}
		}while("30".equals(resultSizePsn));
		if(null !=resultSizePsn && null !=size 
				&& Integer.parseInt(resultSizePsn)>0 && Integer.parseInt(size)>0 
				&& relatedPartyarr!=null&&!"".equals(relatedPartyarr)){
			DataObject[] datas=new DataObject[relatedPartyarr.size()];
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
			for(int j=0;j<relatedPartyarr.size();j++){
				if(null !=relatedPartyarr.get(j).getString("updateTime")){
					Date updateTime =sdf.parse(relatedPartyarr.get(j).getString("updateTime"));
					relatedPartyarr.get(j).set("updateTime", updateTime);
				}
				if(null !=relatedPartyarr.get(j).getString("certBeginDate")){
					Date certBeginDate =sdf.parse(relatedPartyarr.get(j).getString("certBeginDate"));
					relatedPartyarr.get(j).set("certBeginDate", certBeginDate);
				}
				if(null !=relatedPartyarr.get(j).getString("certEndDate")){
					Date certEndDate =sdf.parse(relatedPartyarr.get(j).getString("certEndDate"));
					relatedPartyarr.get(j).set("certEndDate", certEndDate);
				}
				if(null !=relatedPartyarr.get(j).getString("birthday")){
					Date birthday =sdf.parse(relatedPartyarr.get(j).getString("birthday"));
					relatedPartyarr.get(j).set("birthday", birthday);
				}
				datas[j]=relatedPartyarr.get(j);
			}
			DatabaseUtil.saveEntities("default", datas);
		}else{
			map.put("msg", "未查询到关系个人信息！");
		}
		return map;
	}
	
	/**
	 * 
	 * @param ecifPartyNum
	 * @return
	 * @throws Exception
	 * 对私客户关系个人信息查询
	 */
	@Bizlet(value = "对私白名单客户关系个人信息查询")
	public Map PsnRelPsnQueryBmd(String ecifCustNo,String partyId) throws Exception{
		ArrayList<DataObject> relatedPartyarr=new ArrayList<DataObject>();
		String orgNum="";
		DataObject team = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmManagementTeam");
		team=EntityUtil.searchEntity("com.bos.dataset.csm.TbCsmManagementTeam","partyId",partyId);
		if(team!=null&&!"".equals(team)){
		orgNum=team.getString("orgNum");}
		String resultSizePsn="";
		String size ="";
		int allCount=0;
		Map map = new HashMap();
		int ferst=1;
		do{
		IcustEcif custEcif =new CustEcifImpl();
		FMT_CRMS_SVR_S0110101000A107_IN requestA107=new FMT_CRMS_SVR_S0110101000A107_IN();
		requestA107.setFIRST_NO_PSN(String.valueOf(ferst));
		requestA107.setRESULT_SIZE_PSN("30");
		requestA107.setRESOLVE_TYPE("1");
		requestA107.setECIF_CUST_NO(ecifCustNo);
//		requestA107.setECIF_CUST_NO("013000000244");
		S0110101000A107Response	responseA107=custEcif.CPsnCustRelInfoQuery(requestA107,orgNum);
		String msg=responseA107.getResTranHeader().getHRetMsg();
		String code=responseA107.getResTranHeader().getHRetCode();
	    resultSizePsn=responseA107.getResponseBody().getRESULT_SIZE_PSN();
	    size = responseA107.getResponseBody().getALL_RESULT_SIZE_PSN();
	    FMT_CRMS_SVR_S0110101000A107_OUT_SUB[]  ss=responseA107.getResponseBody().getGRP_REL_PSN();
	    map.put("msg", msg);
	    map.put("code", code);
		ferst=ferst+30;
		if(!"AAAAAAA".equals(code)){
		return map;
		}
		//将从Ecif查出的数据更新到本地库里
		String date=GitUtil.getBusiDateYYYYMMDD();
		if(null !=resultSizePsn && null !=size && Integer.parseInt(resultSizePsn)>0 && Integer.parseInt(size)>0){
			for(int i=0;i<ss.length;i++){
				FMT_CRMS_SVR_S0110101000A107_OUT_SUB A107=ss[i];
				DataObject relatedParty = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmNaturalRelative");//关联信息
				DataObject	relatedParty1 = 	EntityUtil.searchEntity("com.bos.dataset.csm.TbCsmNaturalRelative", "parSeqId", A107.getPAR_SEQ_ID(),"partyId",partyId);
				if(relatedParty1!=null&&!"".equals(relatedParty1)){
					relatedParty1.set("parSeqId", A107.getPAR_SEQ_ID());//关联关系记录编号
					relatedParty1.set("relationId", A107.getRELATION_ID());//关系Id
					relatedParty1.set("partyName", A107.getREL_NAME());//关联客户名称
					relatedParty1.set("certificateTypeCd",A107.getREL_CERT_TYPE());//证件类型
					relatedParty1.set("certId", A107.getREL_CERT_NO());//证件号码
					relatedParty1.set("appellation", A107.getRELATION_TYPE());//关联关系类型
					relatedParty1.set("custRela", A107.getRELATION_TYPE());//关联关系类型
					relatedParty1.set("updateTime", date);//更新日期
					relatedParty1.set("birthday", A107.getBIRTH_DATE());//生日
					relatedParty1.set("remark",A107.getREMARK());//描述
					if(null==A107.getREL_CUST_NO()){//为空 不是本行客户
						relatedParty1.set("iscout","0");//是否我行客户 0 否 1 是
						relatedParty1.set("partnercompany", A107.getWORK_CORP());//工作单位
						relatedParty1.set("partnerphonenum", A107.getFINDTEL_NO());//联系电话
						relatedParty1.set("certBeginDate", A107.getCERT_ISSUE_DATE());//签发日期
						relatedParty1.set("certEndDate", A107.getCERT_DUE_DATE());//到期日期
						relatedParty1.set("legalContry", A107.getCERT_ORG_NAT());//国别
					}else{
//						relatedParty1.set("iscout","1");//是否我行客户 0 否 1 是
//						relatedParty1.set("relativeidPartyId", A107.getREL_CUST_NO());//关联客户号
					}
					relatedParty1.set("custType", "02");//关联客户类型
					relatedPartyarr.add(relatedParty1);
				}else{
					relatedParty.set("partyId", partyId);//关联客户
					relatedParty.set("parSeqId", A107.getPAR_SEQ_ID());//关联关系记录编号
					relatedParty.set("relationId", A107.getRELATION_ID());//关系Id
					relatedParty.set("partyName", A107.getREL_NAME());//关联客户名称
					relatedParty.set("certificateTypeCd",A107.getREL_CERT_TYPE());//证件类型
					relatedParty.set("certId", A107.getREL_CERT_NO());//证件号码
					relatedParty.set("appellation", A107.getRELATION_TYPE());//关联关系类型
					relatedParty.set("custRela", A107.getRELATION_TYPE());//关联关系类型
					relatedParty.set("updateTime", date);//更新日期
					relatedParty.set("birthday", A107.getBIRTH_DATE());//生日
					relatedParty.set("remark",A107.getREMARK());//描述
					if(null==A107.getREL_CUST_NO()){//为空 不是本行客户
						relatedParty.set("iscout","0");//是否我行客户 0 否 1 是
						relatedParty.set("partnercompany", A107.getWORK_CORP());//工作单位
						relatedParty.set("partnerphonenum", A107.getFINDTEL_NO());//联系电话
						relatedParty.set("legalContry", A107.getCERT_ORG_NAT());//国别
						relatedParty.set("certBeginDate", A107.getCERT_ISSUE_DATE());//签发日期
						relatedParty.set("certEndDate", A107.getCERT_DUE_DATE());//到期日期
					}else{
//						relatedParty.set("iscout","1");//是否我行客户 0 否 1 是
//						relatedParty.set("relativeidPartyId", A107.getREL_CUST_NO());//关联客户号
					}
					relatedParty.set("custType", "02");//关联客户类型
				relatedPartyarr.add(relatedParty);
				}
				}
			}
		}while("30".equals(resultSizePsn));
		if(null !=resultSizePsn && null !=size 
				&& Integer.parseInt(resultSizePsn)>0 && Integer.parseInt(size)>0 
				&& relatedPartyarr!=null&&!"".equals(relatedPartyarr)){
			DataObject[] datas=new DataObject[relatedPartyarr.size()];
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
			for(int j=0;j<relatedPartyarr.size();j++){
				if(null !=relatedPartyarr.get(j).getString("updateTime")){
					Date updateTime =sdf.parse(relatedPartyarr.get(j).getString("updateTime"));
					relatedPartyarr.get(j).set("updateTime", updateTime);
				}
				if(null !=relatedPartyarr.get(j).getString("certBeginDate")){
					Date certBeginDate =sdf.parse(relatedPartyarr.get(j).getString("certBeginDate"));
					relatedPartyarr.get(j).set("certBeginDate", certBeginDate);
				}
				if(null !=relatedPartyarr.get(j).getString("certEndDate")){
					Date certEndDate =sdf.parse(relatedPartyarr.get(j).getString("certEndDate"));
					relatedPartyarr.get(j).set("certEndDate", certEndDate);
				}
				if(null !=relatedPartyarr.get(j).getString("birthday")){
					Date birthday =sdf.parse(relatedPartyarr.get(j).getString("birthday"));
					relatedPartyarr.get(j).set("birthday", birthday);
				}
				datas[j]=relatedPartyarr.get(j);
			}
			DatabaseUtil.saveEntities("default", datas);
		}else{
			map.put("msg", "未查询到关系个人信息！");
		}
		return map;
	}
	
	
	
	/**
	 * 
	 * @param ecifPartyNum
	 * @return
	 * @throws Exception
	 * 对私客户关系企业信息查询
	 */
	
	@Bizlet(value = "对私客户关系企业信息查询")
	public Map PsnRelComQuery(String ecifCustNo,String partyId) throws Exception{
		ArrayList<DataObject> relatedPartyarr=new ArrayList<DataObject>();
		String resultSizePsn="";
		String size="";
		Map map = new HashMap();
		int ferst=1;
	
		do{
		IcustEcif custEcif =new CustEcifImpl();
		FMT_CRMS_SVR_S0110101000A108_IN requestA108=new FMT_CRMS_SVR_S0110101000A108_IN();
		requestA108.setFIRST_NO_COM(String.valueOf(ferst));
		requestA108.setRESULT_SIZE_COM("30");
		requestA108.setRESOLVE_TYPE("1");
		requestA108.setECIF_CUST_NO(ecifCustNo);
//		requestA108.setECIF_CUST_NO("013000000748");
		S0110101000A108Response	responseA108=custEcif.CPsnRelComQuery(requestA108);
		String msg=responseA108.getResTranHeader().getHRetMsg();
		String code=responseA108.getResTranHeader().getHRetCode();
	    resultSizePsn=responseA108.getResponseBody().getRESULT_SIZE_COM();
	    size = responseA108.getResponseBody().getALL_RESULT_SIZE_COM();
	    FMT_CRMS_SVR_S0110101000A108_OUT_SUB[]  ss=responseA108.getResponseBody().getGRP_REL_COM();
        map.put("msg", msg);
		map.put("code", code);
		ferst=ferst+30;
		if(!"AAAAAAA".equals(code)){
		return map;
		}
		//将从Ecif查出的数据更新到本地库里
		Date date=GitUtil.getBusiDate();
		if(null !=resultSizePsn && null !=size && Integer.parseInt(resultSizePsn)>0 && Integer.parseInt(size)>0){
			for(int i=0;i<ss.length;i++){
				FMT_CRMS_SVR_S0110101000A108_OUT_SUB A108=ss[i];
				DataObject relatedParty = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmNaturalRelative");//关联信息
				DataObject	relatedParty1 = 	EntityUtil.searchEntity("com.bos.dataset.csm.TbCsmNaturalRelative", "parSeqId", A108.getPAR_SEQ_ID(),"partyId",partyId);
				if(relatedParty1!=null&&!"".equals(relatedParty1)){
					relatedParty1.set("parSeqId", A108.getPAR_SEQ_ID());//关联关系记录编号
					relatedParty1.set("relationId", A108.getRELATION_ID());//关系Id
					relatedParty1.set("partyName", A108.getREL_NAME());//关联客户名称
					relatedParty1.set("certificateTypeCd",A108.getREL_CERT_TYPE());//证件类型
					relatedParty1.set("certId", A108.getREL_CERT_NO());//证件号码
					relatedParty1.set("appellation", A108.getRELATION_TYPE());//关联关系类型
					relatedParty1.set("custRela", A108.getRELATION_TYPE());//关联关系类型
					relatedParty1.set("updateTime", date);//更新日期
					relatedParty1.set("remark",A108.getREMARK());//描述
					if(null!=A108.getREL_CUST_NO()){//为空 不是本行客户
//						relatedParty1.set("iscout","1");//是否我行客户 0 否 1 是
//						relatedParty1.set("relativeidPartyId", A108.getREL_CUST_NO());//关联客户号
					}else{
						relatedParty1.set("iscout","0");//是否我行客户 0 否 1 是
						relatedParty1.set("partnercompany", A108.getADMN_ADDR());//经营地址
						relatedParty1.set("partnerphonenum", A108.getOFFICE_TEL());//联系电话
						relatedParty1.set("legalContry", A108.getCERT_ORG_NAT());//国别
						relatedParty1.set("certBeginDate", A108.getCERT_ISSUE_DATE());//签发日期
						relatedParty1.set("certEndDate", A108.getCERT_DUE_DATE());//到期日期OFFICE_TEL
					}
					relatedParty1.set("custType", "01");//关联客户类型
					relatedPartyarr.add(relatedParty1);
				}else{
					relatedParty.set("partyId", partyId);//关联客户
					relatedParty.set("parSeqId", A108.getPAR_SEQ_ID());//关联关系记录编号
					relatedParty.set("relationId", A108.getRELATION_ID());//关系Id
					relatedParty.set("partyName", A108.getREL_NAME());//关联客户名称
					relatedParty.set("certificateTypeCd",A108.getREL_CERT_TYPE());//证件类型
					relatedParty.set("certId", A108.getREL_CERT_NO());//证件号码
					relatedParty.set("appellation", A108.getRELATION_TYPE());//关联关系类型
					relatedParty.set("custRela", A108.getRELATION_TYPE());//关联关系类型
					relatedParty.set("updateTime", date);//更新日期
					
					relatedParty.set("remark",A108.getREMARK());//描述
					if(null!=A108.getREL_CUST_NO()){//为空 不是本行客户
//						relatedParty.set("iscout","1");//是否我行客户 0 否 1 是
//						relatedParty.set("relativeidPartyId", A108.getREL_CUST_NO());//关联客户号
					}else{
						relatedParty.set("iscout","0");//是否我行客户 0 否 1 是
						relatedParty.set("legalContry", A108.getCERT_ORG_NAT());//国别
						relatedParty.set("partnercompany", A108.getADMN_ADDR());//工作单位
						relatedParty.set("partnerphonenum", A108.getOFFICE_TEL());//联系电话
						relatedParty.set("certBeginDate", A108.getCERT_ISSUE_DATE());//签发日期
						relatedParty.set("certEndDate", A108.getCERT_DUE_DATE());//到期日期
					}
					relatedParty.set("custType", "01");//关联客户类型
				relatedPartyarr.add(relatedParty);
				}
				}
			}
		}while("30".equals(resultSizePsn));
		if(null !=resultSizePsn && null !=size
				&& Integer.parseInt(resultSizePsn)>0 && Integer.parseInt(size)>0
				&&relatedPartyarr!=null&&!"".equals(relatedPartyarr)){
			DataObject[] datas=new DataObject[relatedPartyarr.size()];
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
			for(int j=0;j<relatedPartyarr.size();j++){
				if(null !=relatedPartyarr.get(j).getString("updateTime")){
					Date updateTime =sdf.parse(relatedPartyarr.get(j).getString("updateTime"));
					relatedPartyarr.get(j).set("updateTime", updateTime);
				}
				if(null !=relatedPartyarr.get(j).getString("certBeginDate")){
					Date certBeginDate =sdf.parse(relatedPartyarr.get(j).getString("certBeginDate"));
					relatedPartyarr.get(j).set("certBeginDate", certBeginDate);
				}
				if(null !=relatedPartyarr.get(j).getString("certEndDate")){
					Date certEndDate =sdf.parse(relatedPartyarr.get(j).getString("certEndDate"));
					relatedPartyarr.get(j).set("certEndDate", certEndDate);
				}
				if(null !=relatedPartyarr.get(j).getString("birthday")){
					Date birthday =sdf.parse(relatedPartyarr.get(j).getString("birthday"));
					relatedPartyarr.get(j).set("birthday", birthday);
				}
				datas[j]=relatedPartyarr.get(j);
			}
			DatabaseUtil.saveEntities("default", datas);
		}else{
			map.put("msg", "未查询到关系企业信息！");
		}
		return map;
	}
	/**
	 * 
	 * @param ecifPartyNum
	 * @return
	 * @throws Exception
	 * 对私客户关系企业信息查询
	 */
	
	@Bizlet(value = "对私白名单客户关系企业信息查询")
	public Map PsnRelComQueryBmd(String ecifCustNo,String partyId) throws Exception{
		ArrayList<DataObject> relatedPartyarr=new ArrayList<DataObject>();
		String orgNum="";
		DataObject team = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmManagementTeam");
		team=EntityUtil.searchEntity("com.bos.dataset.csm.TbCsmManagementTeam","partyId",partyId);
		if(team!=null&&!"".equals(team)){
		orgNum=team.getString("orgNum");}
		String resultSizePsn="";
		String size="";
		Map map = new HashMap();
		int ferst=1;
	
		do{
		IcustEcif custEcif =new CustEcifImpl();
		FMT_CRMS_SVR_S0110101000A108_IN requestA108=new FMT_CRMS_SVR_S0110101000A108_IN();
		requestA108.setFIRST_NO_COM(String.valueOf(ferst));
		requestA108.setRESULT_SIZE_COM("30");
		requestA108.setRESOLVE_TYPE("1");
		requestA108.setECIF_CUST_NO(ecifCustNo);
//		requestA108.setECIF_CUST_NO("013000000748");
		S0110101000A108Response	responseA108=custEcif.CPsnRelComQuery(requestA108,orgNum);
		String msg=responseA108.getResTranHeader().getHRetMsg();
		String code=responseA108.getResTranHeader().getHRetCode();
	    resultSizePsn=responseA108.getResponseBody().getRESULT_SIZE_COM();
	    size = responseA108.getResponseBody().getALL_RESULT_SIZE_COM();
	    FMT_CRMS_SVR_S0110101000A108_OUT_SUB[]  ss=responseA108.getResponseBody().getGRP_REL_COM();
        map.put("msg", msg);
		map.put("code", code);
		ferst=ferst+30;
		if(!"AAAAAAA".equals(code)){
		return map;
		}
		//将从Ecif查出的数据更新到本地库里
		Date date=GitUtil.getBusiDate();
		if(null !=resultSizePsn && null !=size && Integer.parseInt(resultSizePsn)>0 && Integer.parseInt(size)>0){
			for(int i=0;i<ss.length;i++){
				FMT_CRMS_SVR_S0110101000A108_OUT_SUB A108=ss[i];
				DataObject relatedParty = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmNaturalRelative");//关联信息
				DataObject	relatedParty1 = 	EntityUtil.searchEntity("com.bos.dataset.csm.TbCsmNaturalRelative", "parSeqId", A108.getPAR_SEQ_ID(),"partyId",partyId);
				if(relatedParty1!=null&&!"".equals(relatedParty1)){
					relatedParty1.set("parSeqId", A108.getPAR_SEQ_ID());//关联关系记录编号
					relatedParty1.set("relationId", A108.getRELATION_ID());//关系Id
					relatedParty1.set("partyName", A108.getREL_NAME());//关联客户名称
					relatedParty1.set("certificateTypeCd",A108.getREL_CERT_TYPE());//证件类型
					relatedParty1.set("certId", A108.getREL_CERT_NO());//证件号码
					relatedParty1.set("appellation", A108.getRELATION_TYPE());//关联关系类型
					relatedParty1.set("custRela", A108.getRELATION_TYPE());//关联关系类型
					relatedParty1.set("updateTime", date);//更新日期
					relatedParty1.set("remark",A108.getREMARK());//描述
					if(null!=A108.getREL_CUST_NO()){//为空 不是本行客户
//						relatedParty1.set("iscout","1");//是否我行客户 0 否 1 是
//						relatedParty1.set("relativeidPartyId", A108.getREL_CUST_NO());//关联客户号
					}else{
						relatedParty1.set("iscout","0");//是否我行客户 0 否 1 是
						relatedParty1.set("partnercompany", A108.getADMN_ADDR());//经营地址
						relatedParty1.set("partnerphonenum", A108.getOFFICE_TEL());//联系电话
						relatedParty1.set("legalContry", A108.getCERT_ORG_NAT());//国别
						relatedParty1.set("certBeginDate", A108.getCERT_ISSUE_DATE());//签发日期
						relatedParty1.set("certEndDate", A108.getCERT_DUE_DATE());//到期日期OFFICE_TEL
					}
					relatedParty1.set("custType", "01");//关联客户类型
					relatedPartyarr.add(relatedParty1);
				}else{
					relatedParty.set("partyId", partyId);//关联客户
					relatedParty.set("parSeqId", A108.getPAR_SEQ_ID());//关联关系记录编号
					relatedParty.set("relationId", A108.getRELATION_ID());//关系Id
					relatedParty.set("partyName", A108.getREL_NAME());//关联客户名称
					relatedParty.set("certificateTypeCd",A108.getREL_CERT_TYPE());//证件类型
					relatedParty.set("certId", A108.getREL_CERT_NO());//证件号码
					relatedParty.set("appellation", A108.getRELATION_TYPE());//关联关系类型
					relatedParty.set("custRela", A108.getRELATION_TYPE());//关联关系类型
					relatedParty.set("updateTime", date);//更新日期
					
					relatedParty.set("remark",A108.getREMARK());//描述
					if(null!=A108.getREL_CUST_NO()){//为空 不是本行客户
//						relatedParty.set("iscout","1");//是否我行客户 0 否 1 是
//						relatedParty.set("relativeidPartyId", A108.getREL_CUST_NO());//关联客户号
					}else{
						relatedParty.set("iscout","0");//是否我行客户 0 否 1 是
						relatedParty.set("legalContry", A108.getCERT_ORG_NAT());//国别
						relatedParty.set("partnercompany", A108.getADMN_ADDR());//工作单位
						relatedParty.set("partnerphonenum", A108.getOFFICE_TEL());//联系电话
						relatedParty.set("certBeginDate", A108.getCERT_ISSUE_DATE());//签发日期
						relatedParty.set("certEndDate", A108.getCERT_DUE_DATE());//到期日期
					}
					relatedParty.set("custType", "01");//关联客户类型
				relatedPartyarr.add(relatedParty);
				}
				}
			}
		}while("30".equals(resultSizePsn));
		if(null !=resultSizePsn && null !=size
				&& Integer.parseInt(resultSizePsn)>0 && Integer.parseInt(size)>0
				&&relatedPartyarr!=null&&!"".equals(relatedPartyarr)){
			DataObject[] datas=new DataObject[relatedPartyarr.size()];
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
			for(int j=0;j<relatedPartyarr.size();j++){
				if(null !=relatedPartyarr.get(j).getString("updateTime")){
					Date updateTime =sdf.parse(relatedPartyarr.get(j).getString("updateTime"));
					relatedPartyarr.get(j).set("updateTime", updateTime);
				}
				if(null !=relatedPartyarr.get(j).getString("certBeginDate")){
					Date certBeginDate =sdf.parse(relatedPartyarr.get(j).getString("certBeginDate"));
					relatedPartyarr.get(j).set("certBeginDate", certBeginDate);
				}
				if(null !=relatedPartyarr.get(j).getString("certEndDate")){
					Date certEndDate =sdf.parse(relatedPartyarr.get(j).getString("certEndDate"));
					relatedPartyarr.get(j).set("certEndDate", certEndDate);
				}
				if(null !=relatedPartyarr.get(j).getString("birthday")){
					Date birthday =sdf.parse(relatedPartyarr.get(j).getString("birthday"));
					relatedPartyarr.get(j).set("birthday", birthday);
				}
				datas[j]=relatedPartyarr.get(j);
			}
			DatabaseUtil.saveEntities("default", datas);
		}else{
			map.put("msg", "未查询到关系企业信息！");
		}
		return map;
	}

	@Bizlet(value = "维护 与创建 对私客户关系信息")
	public Map PsnRelMaint(DataObject item) throws Exception{
		Map map= new HashMap();
		String custType =item.getString("custType");//客户类型
		if("01".equals(custType)){
			map=PsnRelOrgMaint(item);
		}else{
			map=PsnRelPsnMaint(item);
		}
		
		return map;
		
	}
	
	@Bizlet(value = "维护 与创建 对私客户关系个人信息")
	public Map PsnRelPsnMaint(DataObject item) throws Exception{
		Map map= new HashMap();
		DataObject party = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
		party.set("partyId", item.getString("partyId"));
		DatabaseUtil.expandEntity("default", party);
		String iscout =item.getString("iscout");//是否我行客户
		IcustEcif custEcif=new  CustEcifImpl();
		S0110102000B110ServiceStub.FMT_CRMS_SVR_S0110102000B110_IN B110IN=new S0110102000B110ServiceStub.FMT_CRMS_SVR_S0110102000B110_IN();
		
		B110IN.setRESOLVE_TYPE("1");
     	B110IN.setECIF_CUST_NO(party.getString("ecifPartyNum"));
		//B110IN.setECIF_CUST_NO("013000000202");
		
		FMT_CRMS_SVR_S0110102000B110_IN_SUB sub =new FMT_CRMS_SVR_S0110102000B110_IN_SUB();
		//关联客户类型
		sub.setREL_NAME(item.getString("partyName"));//客户名称
		String certType = item.getString("certificateTypeCd");//证件类型
		sub.setREL_CERT_TYPE(certType);//证件类型
		sub.setREL_CERT_NO(item.getString("certId"));//证件号码
//		sub.setREL_CERT_NO("510623199010202318");//证件号码
		sub.setRELATION_TYPE(item.getString("appellation"));//关联关系类型
		sub.setWORK_CORP(item.getString("partnercompany"));
		sub.setFINDTEL_NO(item.getString("partnerphonenum"));
		sub.setREMARK(item.getString("remark"));
		sub.setNAT_CODE(item.getString("legalContry"));
		if(null!=item.getString("certBeginDate")){
			sub.setCERT_ISSUE_DATE(item.getString("certBeginDate").replaceAll("-", "").substring(0,8));
		}
		//勾选长期后ECIF29991231  信贷99991231 不一致 注释
		/*if(null!=item.getString("certEndDate")){
			sub.setCERT_DUE_DATE(item.getString("certEndDate").replaceAll("-", "").substring(0,8));
		}*/
		FMT_CRMS_SVR_S0110102000B110_IN_SUB ss [] =new FMT_CRMS_SVR_S0110102000B110_IN_SUB[1];
		ss[0]=sub;
		B110IN.setGRP_REL_PSN(ss);
		S0110102000B110Response  resB110=custEcif.CPsnRelPsnMaint(B110IN);
		String msg=resB110.getResTranHeader().getHRetMsg();
		String code=resB110.getResTranHeader().getHRetCode();
		map.put("msg", msg);
		map.put("code", code);
		if(!"AAAAAAA".equals(code)){
			return map;
		}
//		String ecifPartyNum = resB110.getResponseBody().getECIF_CUST_NO();
		FMT_CRMS_SVR_S0110102000B110_OUT_SUB array [] =resB110.getResponseBody().getGRP_REL_PSN();
		
		for (int i = 0; i < array.length; i++) {
			map.put("parSeqId", array[i].getPAR_SEQ_ID());//关联关系记录编号
			map.put("relationId", array[i].getRELATION_ID());//关系Id
		}
	
//		map.put("ecifPartyNum", ecifPartyNum);
		return map;
		
	}
	
	@Bizlet(value = "维护 与创建 对私客户关系企业信息")
	public Map PsnRelOrgMaint(DataObject item) throws Exception{
 		Map map= new HashMap();
		DataObject party = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
		party.set("partyId", item.getString("partyId"));
		DatabaseUtil.expandEntity("default", party);
		String iscout =item.getString("iscout");//是否我行客户custType
		
		IcustEcif custEcif=new  CustEcifImpl();
		S0110102000B112ServiceStub.FMT_CRMS_SVR_S0110102000B112_IN B112IN=new S0110102000B112ServiceStub.FMT_CRMS_SVR_S0110102000B112_IN();
		
		B112IN.setRESOLVE_TYPE("1");
		B112IN.setECIF_CUST_NO(party.getString("ecifPartyNum"));
	//	B112IN.setECIF_CUST_NO("013000000057");
		
		FMT_CRMS_SVR_S0110102000B112_IN_SUB sub =new FMT_CRMS_SVR_S0110102000B112_IN_SUB();
		//关联客户类型
		sub.setREL_NAME(item.getString("partyName"));//客户名称
		String certType = item.getString("certificateTypeCd");//证件类型
		/**
		 * 目前证件类型 不一致 需要转换 待统一后 不需要转换
		 */
		if("202".equals(certType)){
			certType="21";
		}
		sub.setREL_CERT_TYPE(certType);//证件类型
//		sub.setREL_CERT_TYPE("21");//证件类型
		sub.setREL_CERT_NO(item.getString("certId"));//证件号码
		sub.setRELATION_TYPE(item.getString("appellation"));//关联关系类型
//		sub.setRELATION_TYPE("4210");//关联关系类型
		sub.setADMN_ADDR(item.getString("partnercompany"));//经营地址
		sub.setCOM_ADDR(item.getString("partnercompany"));//公司地址
		sub.setREGISTER_ADDR(item.getString("partnercompany"));//注册地址
		sub.setOFFICE_TEL(item.getString("partnerphonenum"));//联系电话
		sub.setREMARK(item.getString("remark"));
		sub.setCERT_ORG_NAT(item.getString("legalContry"));
		sub.setCERT_ISSUE_DATE(item.getString("certBeginDate").replaceAll("-", "").substring(0,8));
		//sub.setCERT_DUE_DATE(item.getString("certEndDate").replaceAll("-", "").substring(0,8));
		FMT_CRMS_SVR_S0110102000B112_IN_SUB ss [] =new FMT_CRMS_SVR_S0110102000B112_IN_SUB[1];
		ss[0]=sub;
		B112IN.setGRP_REL_COM(ss);
		S0110102000B112Response  resB112=custEcif.CPsnRelOrgMaint(B112IN);
		String msg=resB112.getResTranHeader().getHRetMsg();
		String code=resB112.getResTranHeader().getHRetCode();
		map.put("msg", msg);
		map.put("code", code);
		if(!"AAAAAAA".equals(code)){
			return map;
		}
		FMT_CRMS_SVR_S0110102000B112_OUT_SUB[] array =resB112.getResponseBody().getGRP_REL_COM();
		for (int i = 0; i < array.length; i++) {
			map.put("parSeqId", array[i].getPAR_SEQ_ID());//关联关系记录编号
			map.put("relationId", array[i].getRELATION_ID());//关系Id
		}
//		map.put("ecifPartyNum", ecifPartyNum);
		return map;
		
	}
	
	@Bizlet(value = "对私客户关系个人信息删除")
	public Map PsnRelPsnDel(DataObject item) throws Exception{
		Map map= new HashMap();
		DataObject party = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
		party.set("partyId", item.getString("partyId"));
		DatabaseUtil.expandEntity("default", party);
		
		IcustEcif custEcif=new  CustEcifImpl();
		S0110102000B111ServiceStub.FMT_CRMS_SVR_S0110102000B111_IN B111IN=new S0110102000B111ServiceStub.FMT_CRMS_SVR_S0110102000B111_IN();
		B111IN.setPAR_SEQ_ID(item.getString("parSeqId"));
		B111IN.setECIF_CUST_NO(party.getString("ecifPartyNum"));
		//B111IN.setECIF_CUST_NO("013000000202");
		S0110102000B111Response  resB111=custEcif.CPsnRelPsnDel(B111IN);
		String msg=resB111.getResTranHeader().getHRetMsg();
		String code=resB111.getResTranHeader().getHRetCode();
		resB111.getResponseBody();
		map.put("msg", msg);
		map.put("code", code);
		if(!"AAAAAAA".equals(code)){
			return map;
		}
		return map;
		
	}
	
	@Bizlet(value = "对私客户关系企业信息删除")
	public Map PsnRelOrgDel(DataObject item) throws Exception{
		Map map= new HashMap();
		DataObject party = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
		party.set("partyId", item.getString("partyId"));
		DatabaseUtil.expandEntity("default", party);
		
		IcustEcif custEcif=new  CustEcifImpl();
		S0110102000B113ServiceStub.FMT_CRMS_SVR_S0110102000B113_IN B113IN=new S0110102000B113ServiceStub.FMT_CRMS_SVR_S0110102000B113_IN();
		B113IN.setPAR_SEQ_ID(item.getString("parSeqId"));
		B113IN.setECIF_CUST_NO(party.getString("ecifPartyNum"));
		//B113IN.setECIF_CUST_NO("013000000057");
		S0110102000B113Response  resB113=custEcif.CPsnRelOrgDel(B113IN);
		String msg=resB113.getResTranHeader().getHRetMsg();
		String code=resB113.getResTranHeader().getHRetCode();
		resB113.getResponseBody();
		map.put("msg", msg);
		map.put("code", code);
		if(!"AAAAAAA".equals(code)){
			return map;
		}
		return map;
		
	}
	
	@Bizlet(value = "对私客户关系信息删除")
	public Map PsnRelDel(DataObject item) throws Exception{
		Map map= new HashMap();
		String custType =item.getString("custType");//客户类型
		if("01".equals(custType)){
			map=PsnRelOrgDel(item);
		}else{
			map=PsnRelPsnDel(item);
		}
		return map;
	}
	
	/**
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
	
	/**
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
         } else{ //组织机构代码证
        	 certType="10";
         } 
		return certType;
		
	}
	
}
