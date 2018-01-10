/**
 * 
 */
package com.bos.csm.pub;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.bos.pub.BizNumGenerator;
import com.bos.pub.GitUtil;
import com.bos.pub.entity.EntityUtil;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.system.annotation.Bizlet;
import com.informix.util.dateUtil;
import com.primeton.tsl.ecif.S0110101000A207ServiceStub.FMT_CRMS_SVR_S0110101000A207_IN;
import com.primeton.tsl.ecif.S0110101000A207ServiceStub.FMT_CRMS_SVR_S0110101000A207_OUT_SUB;
import com.primeton.tsl.ecif.S0110101000A207ServiceStub.S0110101000A207Response;
import com.primeton.tsl.ecif.S0110101000A208ServiceStub.FMT_CRMS_SVR_S0110101000A208_IN;
import com.primeton.tsl.ecif.S0110101000A208ServiceStub.FMT_CRMS_SVR_S0110101000A208_OUT_SUB;
import com.primeton.tsl.ecif.S0110101000A208ServiceStub.S0110101000A208Response;
import com.primeton.tsl.ecif.S0110102000B101ServiceStub.FMT_CRMS_SVR_S0110102000B101_IN;
import com.primeton.tsl.ecif.S0110102000B101ServiceStub.S0110102000B101Response;
import com.primeton.tsl.ecif.S0110102000B210ServiceStub.FMT_CRMS_SVR_S0110102000B210_IN;
import com.primeton.tsl.ecif.S0110102000B210ServiceStub.FMT_CRMS_SVR_S0110102000B210_IN_SUB;
import com.primeton.tsl.ecif.S0110102000B210ServiceStub.FMT_CRMS_SVR_S0110102000B210_OUT_SUB;
import com.primeton.tsl.ecif.S0110102000B210ServiceStub.S0110102000B210Response;
import com.primeton.tsl.ecif.S0110102000B211ServiceStub.FMT_CRMS_SVR_S0110102000B211_IN;
import com.primeton.tsl.ecif.S0110102000B211ServiceStub.S0110102000B211Response;
import com.primeton.tsl.ecif.S0110102000B212ServiceStub.FMT_CRMS_SVR_S0110102000B212_IN;
import com.primeton.tsl.ecif.S0110102000B212ServiceStub.FMT_CRMS_SVR_S0110102000B212_IN_SUB;
import com.primeton.tsl.ecif.S0110102000B212ServiceStub.FMT_CRMS_SVR_S0110102000B212_OUT_SUB;
import com.primeton.tsl.ecif.S0110102000B212ServiceStub.S0110102000B212Response;
import com.primeton.tsl.ecif.S0110102000B213ServiceStub.FMT_CRMS_SVR_S0110102000B213_IN;
import com.primeton.tsl.ecif.S0110102000B213ServiceStub.S0110102000B213Response;
import com.primeton.tsl.ecif.port.IcustEcif;
import com.primeton.tsl.ecif.port.impl.CustEcifImpl;

import commonj.sdo.DataObject;

/**
 * @author TLQ
 * @date 2017-07-03 19:44:12
 *客户关系相关操作
 */
@Bizlet("")
public class orgCustRel {
	/**===================================================客服查询开始=======================================
	 * @throws Exception 
	 */
	/*
	 * 对公客户关系个人信息查询   COrgRelPsnQuery
	 */
	@Bizlet(value = "对公客户关系个人信息查询")
	public Map COrgRelPsnQuery(String ecifCustNo,String partyId) throws Exception{
		DataObject party1 = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
		party1.set("partyId", partyId);
		DatabaseUtil.expandEntity("default", party1);
		String ecifCustNo1=party1.getString("ecifPartyNum");
//		ecifCustNo1="023002000415";
		ArrayList<DataObject> relatedPartyarr=new ArrayList<DataObject>();
		String resultSizePsn="";
		Map map = new HashMap();
		int ferst=1;
		do{
		IcustEcif orgEcif =new CustEcifImpl();//com.bos.dataset.csm.TbCsmOtherRelatedParty
		FMT_CRMS_SVR_S0110101000A207_IN requestA207=new FMT_CRMS_SVR_S0110101000A207_IN();
		requestA207.setFIRST_NO_PSN(String.valueOf(ferst));
		requestA207.setRESULT_SIZE_PSN("10");
		requestA207.setECIF_CUST_NO(ecifCustNo1);
		S0110101000A207Response	responseA207=orgEcif.COrgRelPsnQuery(requestA207);
		String msgg=responseA207.getResTranHeader().getHRetMsg();
		String msg=responseA207.getResTranHeader().getHRetCode();
	    resultSizePsn=responseA207.getResponseBody().getRESULT_SIZE_PSN();
	    FMT_CRMS_SVR_S0110101000A207_OUT_SUB[]  ss=responseA207.getResponseBody().getGRP_REL_PSN();
        map.put("msgg", msgg);
		map.put("msg", msg);
		ferst=ferst+10;
		if(!"AAAAAAA".equals(msg)){
		return map;
		}
		//将从Ecif查出的数据更新到本地库里
		Date date=GitUtil.getBusiDate();
		if(ss!=null&&!"".equals(ss)){
			for(int i=0;i<ss.length;i++){
				FMT_CRMS_SVR_S0110101000A207_OUT_SUB A207=new FMT_CRMS_SVR_S0110101000A207_OUT_SUB();
				A207=ss[i];
				String parSeqId=A207.getPAR_SEQ_ID();
				DataObject relatedParty = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmOtherRelatedParty");//关联信息
				DataObject	relatedParty1 = 	EntityUtil.searchEntity("com.bos.dataset.csm.TbCsmOtherRelatedParty",  "parSeqId", parSeqId,"partyId",partyId);
				if(!"".equals(A207.getPAR_SEQ_ID())&&A207.getPAR_SEQ_ID()!=null){
					
				if(relatedParty1!=null&&!"".equals(relatedParty1)){
					if(!"".equals(A207.getRELATION_TYPE())&&A207.getRELATION_TYPE()!=null){
					relatedParty1.set("relatedCd", A207.getRELATION_TYPE());//关联关系类型
					}
				  //relatedParty1.set("remark",A207.getREMARK());//描述
					if(!"".equals(A207.getREL_CUST_NO())&&A207.getREL_CUST_NO()!=null){
					relatedParty1.set("relCustNo", A207.getREL_CUST_NO());//关联客户号
					}
					if(!"".equals(A207.getRELATION_ID())&&A207.getRELATION_ID()!=null){
						relatedParty1.set("relaPartyId", A207.getRELATION_ID());//关联客ID
					}
					if(!"".equals(A207.getPAR_SEQ_ID())&&A207.getPAR_SEQ_ID()!=null){
						relatedParty1.set("parSeqId", A207.getPAR_SEQ_ID());//关联关系记录编号
					}
					if(!"".equals(A207.getREL_NAME())&&A207.getREL_NAME()!=null){
						relatedParty1.set("relaPartyName", A207.getREL_NAME());//关联客户名称
					}
					if(!"".equals(A207.getREL_CERT_TYPE())&&A207.getREL_CERT_TYPE()!=null){

						relatedParty1.set("certType", A207.getREL_CERT_TYPE());//证件类型
						
					}
					if(!"".equals(A207.getREL_CERT_NO())&&A207.getREL_CERT_NO()!=null){
						relatedParty1.set("certNum", A207.getREL_CERT_NO());//证件号码
					}
						relatedParty1.set("custType", "6");//关联客户类型
						if(A207.getREL_CUST_NO()!=null){
							relatedParty.set("iscout", "1");//是否本行客户
						}else{
							relatedParty.set("iscout", "0");//是否本行客户
						}
/*					DatabaseUtil.updateEntity("default", relatedParty1);*/
					relatedPartyarr.add(relatedParty1);
				}else{
			
				relatedParty.set("partyId", partyId);//参与者
				relatedParty.set("relatedCd", A207.getRELATION_TYPE());//关联关系类型
				relatedParty.set("remark",A207.getREMARK());//描述
				relatedParty.set("createTime",date);//创建时间
				relatedParty.set("relFrom", "1");//认定类型
				relatedParty.set("custType", "6");//关联客户类型
				relatedParty.set("relCustNo", A207.getREL_CUST_NO());//关联客户号
				relatedParty.set("relaPartyId", A207.getRELATION_ID());//关联客ID
				relatedParty.set("parSeqId", A207.getPAR_SEQ_ID());//关联关系记录编号
				relatedParty.set("relaPartyName", A207.getREL_NAME());//关联客户名称
				relatedParty.set("certType", A207.getREL_CERT_TYPE());//证件类型
				relatedParty.set("certNum", A207.getREL_CERT_NO());//证件号码
				if(A207.getREL_CUST_NO()!=null){
					relatedParty.set("iscout", "1");//是否本行客户
				}else{
					relatedParty.set("iscout", "0");//是否本行客户
				}
/*				DatabaseUtil.insertEntity("default", relatedParty);*/
				relatedPartyarr.add(relatedParty);
					
				}
			}
				}
			}
		}while("10".equals(resultSizePsn));
		if(relatedPartyarr!=null&&!"".equals(relatedPartyarr)){
			DataObject[] datas=new DataObject[relatedPartyarr.size()];
			for(int j=0;j<relatedPartyarr.size();j++){
				datas[j]=relatedPartyarr.get(j);
			}
			DatabaseUtil.saveEntities("default", datas);
		}
		
		return map;
	}
	/*
	 * 对公客户关系企业信息查询  COrgRelPsnQuery
	 */
	@Bizlet(value = "对公客户关系企业信息查询")
	public Map OrgRelComQuery(String ecifCustNo,String partyId) throws Exception{
		ArrayList<DataObject> relatedPartyarr=new ArrayList<DataObject>();
		DataObject party1 = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
		party1.set("partyId", partyId);
		DatabaseUtil.expandEntity("default", party1);
		String ecifCustNo1=party1.getString("ecifPartyNum");
//		ecifCustNo1="023002003627";
		String resultSizePsn="";
		Map map = new HashMap();
		int ferst=1;
		do{
		IcustEcif orgEcif =new CustEcifImpl();
		FMT_CRMS_SVR_S0110101000A208_IN requestA208=new FMT_CRMS_SVR_S0110101000A208_IN();
		requestA208.setFIRST_NO_COM(String.valueOf(ferst));
		requestA208.setRESULT_SIZE_COM("10");
		requestA208.setECIF_CUST_NO(ecifCustNo1);
		S0110101000A208Response	responseA208=orgEcif.OrgRelComQuery(requestA208);
		String msgg=responseA208.getResTranHeader().getHRetMsg();
		String msg=responseA208.getResTranHeader().getHRetCode();
	    resultSizePsn=responseA208.getResponseBody().getRESULT_SIZE_COM();
	    FMT_CRMS_SVR_S0110101000A208_OUT_SUB[]  ss=responseA208.getResponseBody().getGRP_REL_COM();
        map.put("msgg", msgg);
		map.put("msg", msg);
		ferst=ferst+10;
		if(!"AAAAAAA".equals(msg)){
		return map;
		}
		//将从Ecif查出的数据更新到本地库里
		Date date=GitUtil.getBusiDate();
		if(ss!=null&&!"".equals(ss)){
			for(int i=0;i<ss.length;i++){
				FMT_CRMS_SVR_S0110101000A208_OUT_SUB A208=new FMT_CRMS_SVR_S0110101000A208_OUT_SUB();
				A208=ss[i];
				String parSeqId=A208.getPAR_SEQ_ID();
				DataObject relatedParty = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmOtherRelatedParty");//关联信息
				DataObject	relatedParty1 = 	EntityUtil.searchEntity("com.bos.dataset.csm.TbCsmOtherRelatedParty",  "parSeqId", parSeqId,"partyId",partyId);
				if(!"".equals(A208.getPAR_SEQ_ID())&&A208.getPAR_SEQ_ID()!=null){
				if(relatedParty1!=null&&!"".equals(relatedParty1)){
					if(!"".equals(A208.getRELATION_TYPE())&&A208.getRELATION_TYPE()!=null){
						relatedParty1.set("relatedCd", A208.getRELATION_TYPE());//关联关系类型
					}
				//	relatedParty1.set("remark",A208.getREMARK());//描述
					if(!"".equals(A208.getREL_CUST_NO())&&A208.getREL_CUST_NO()!=null){
						relatedParty1.set("relCustNo", A208.getREL_CUST_NO());//关联客户号
					}
					if(!"".equals(A208.getRELATION_ID())&&A208.getRELATION_ID()!=null){
						relatedParty1.set("relaPartyId", A208.getRELATION_ID());//关联客ID
					}
					if(!"".equals(A208.getPAR_SEQ_ID())&&A208.getPAR_SEQ_ID()!=null){
						relatedParty1.set("parSeqId", A208.getPAR_SEQ_ID());//关联关系记录编号
					}
					if(!"".equals(A208.getREL_NAME())&&A208.getREL_NAME()!=null){
						relatedParty1.set("relaPartyName", A208.getREL_NAME());//关联客户名称
					}
					if(!"".equals(A208.getREL_NAME())&&A208.getREL_NAME()!=null){
						if("21".equals(A208.getREL_CERT_TYPE())){
						relatedParty1.set("certType", "202");//证件类型
						}else{
						relatedParty1.set("certType", A208.getREL_CERT_TYPE());//证件类型
						}
					}

					relatedParty1.set("custType", "1");//关联客户类型
					if(!"".equals(A208.getREL_CERT_NO())&&A208.getREL_CERT_NO()!=null){
						relatedParty1.set("certNum", A208.getREL_CERT_NO());//证件号码
					}
					if(A208.getREL_CUST_NO()!=null){
						relatedParty1.set("iscout", "1");//是否本行客户
					}else{
						relatedParty1.set("iscout", "0");//是否本行客户
					}
			/*		DatabaseUtil.updateEntity("default", relatedParty1);*/
					relatedPartyarr.add(relatedParty1);
				}else{
				relatedParty.set("partyId", partyId);//参与者
				relatedParty.set("relatedCd", A208.getRELATION_TYPE());//关联关系类型
				relatedParty.set("remark",A208.getREMARK());//描述
				relatedParty.set("createTime",date);//创建时间
				relatedParty.set("relFrom", "1");//认定类型
				relatedParty.set("custType", "1");//关联客户类型
				relatedParty.set("relCustNo", A208.getREL_CUST_NO());//关联客户号
				relatedParty.set("relaPartyId", A208.getRELATION_ID());//关联客ID
				relatedParty.set("parSeqId", A208.getPAR_SEQ_ID());//关联关系记录编号
				relatedParty.set("relaPartyName", A208.getREL_NAME());//关联客户名称
				relatedParty.set("certType", A208.getREL_CERT_TYPE());//证件类型
				relatedParty.set("certNum", A208.getREL_CERT_NO());//证件号码
				if(A208.getREL_CUST_NO()!=null){
					relatedParty.set("iscout", "1");//是否本行客户
				}else{
					relatedParty.set("iscout", "0");//是否本行客户
				}
/*				DatabaseUtil.insertEntity("default", relatedParty);*/
				relatedPartyarr.add(relatedParty);
				}
				}
			}
			}
		}while("10".equals(resultSizePsn));
		if(relatedPartyarr!=null&&!"".equals(relatedPartyarr)){
			DataObject[] datas=new DataObject[relatedPartyarr.size()];
			for(int j=0;j<relatedPartyarr.size();j++){
				datas[j]=relatedPartyarr.get(j);
			}
			DatabaseUtil.saveEntities("default", datas);
		}
		
		return map;
	}
	/**===================================================客服查询结束=======================================
	 */
	/**===================================================客服修改开始=======================================
	 */
	/*
	 * 对公客户关系信息修改  COrgRelPsnUpdate
	 */
	@Bizlet(value = "对公客户关系企业信息修改")
	public Map OrgRelComUpdate(DataObject rela) throws Exception{
		Map map = new HashMap();
		String custType=rela.getString("custType");
		if("6".equals(custType)){
			map=OrgRelPsnMaint(rela);
		}else{
			map=OrgRelComMaint(rela);
		}

		
		return map;
	}
	/*
	 * 对公客户关系信息删除  COrgRelPsnUpdate
	 */
	@Bizlet(value = "对公客户关系信息删除")
	public Map OrgRelDel(DataObject rela) throws Exception{
		Map map = new HashMap();
		String custType=rela.getString("custType");
		if("6".equals(custType)){
			map=OrgRelPsnDel(rela);
		}else{
			map=OrgRelComDel(rela);
		}

		
		return map;
	}
	/*
	 * 对公客户关系个人信息创建与维护  OrgRelPsnMaint
	 */
	@Bizlet(value = "对公客户关系个人信息修改")
	public Map OrgRelPsnMaint(DataObject rela) throws Exception{
		String partyId=rela.getString("partyId");
		DataObject party1 = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
		party1.set("partyId", partyId);
		DatabaseUtil.expandEntity("default", party1);
		String ecifCustNo1=party1.getString("ecifPartyNum");
		String ecifCustNo11=null;
		 ecifCustNo11=rela.getString("relCustNo");
		rela.set("relCustNo", ecifCustNo11);//关系人客户号
		IcustEcif orgEcif =new CustEcifImpl();
		FMT_CRMS_SVR_S0110102000B210_IN B210= new  FMT_CRMS_SVR_S0110102000B210_IN();
		FMT_CRMS_SVR_S0110102000B210_IN_SUB[]  subs=new FMT_CRMS_SVR_S0110102000B210_IN_SUB[1];
		FMT_CRMS_SVR_S0110102000B210_IN_SUB sub=new FMT_CRMS_SVR_S0110102000B210_IN_SUB();
		B210.setECIF_CUST_NO(ecifCustNo1);//023002000415
		//sub.setPAR_SEQ_ID(rela.getString("parSeqId"));//关联关系记录编号
		sub.setRELATION_TYPE(rela.getString("relatedCd"));//关联关系类型
		sub.setREL_CUST_NO(ecifCustNo11);//关联客户号
		//sub.setRELATION_ID(rela.getString("relaPartyId"));//关联客户号
		sub.setREL_NAME(rela.getString("relaPartyName"));//关联客户名称
		sub.setREL_CERT_TYPE(rela.getString("certType"));//证件类型
	//	sub.setREL_CERT_TYPE("11");//证件类型
		sub.setREL_CERT_NO(rela.getString("certNum"));//证件号码
		subs[0]=sub;
		B210.setGRP_REL_PSN(subs);
		S0110102000B210Response responseB210=orgEcif.OrgRelPsnMaint(B210);
		String msgg=responseB210.getResTranHeader().getHRetMsg();
		String msg=responseB210.getResTranHeader().getHRetCode();
		FMT_CRMS_SVR_S0110102000B210_OUT_SUB[] out = responseB210.getResponseBody().getGRP_REL_PSN();
		if(out.length>0){
			rela.set("parSeqId", out[0].getPAR_SEQ_ID());
		}
		Map map = new HashMap();
	    map.put("msgg", msgg);
		map.put("msg", msg);

		
		return map;
	}
	/*
	 * 对公客户关系企业信息创建与维护  OrgRelComMaint
	 */
	@Bizlet(value = "对公客户关系企业信息修改")
	public Map OrgRelComMaint(DataObject rela) throws Exception{
		String partyId=rela.getString("partyId");
		DataObject party1 = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
		party1.set("partyId", partyId);
		DatabaseUtil.expandEntity("default", party1);
		String ecifCustNo1=party1.getString("ecifPartyNum");
		String ecifCustNo11=null;
		String isCout=rela.getString("isCout");
	    ecifCustNo11=rela.getString("relCustNo");
		rela.set("relCustNo", ecifCustNo11);//关系人客户号
		IcustEcif orgEcif =new CustEcifImpl();
		FMT_CRMS_SVR_S0110102000B212_IN B212= new  FMT_CRMS_SVR_S0110102000B212_IN();
		FMT_CRMS_SVR_S0110102000B212_IN_SUB[]  subs=new FMT_CRMS_SVR_S0110102000B212_IN_SUB[1];
		FMT_CRMS_SVR_S0110102000B212_IN_SUB sub=new FMT_CRMS_SVR_S0110102000B212_IN_SUB();
		B212.setECIF_CUST_NO(ecifCustNo1);
		sub.setRELATION_TYPE(rela.getString("relatedCd"));//关联关系类型
		//sub.setPAR_SEQ_ID(rela.getString("parSeqId"));//关联关系记录编号
		sub.setREL_CUST_NO(ecifCustNo11);//关联客户号
		//sub.setRELATION_ID(rela.getString("relaPartyId"));//关联客户号
		sub.setREL_NAME(rela.getString("relaPartyName"));//关联客户名称
		String certType=rela.getString("certType");
		if("202".equals(certType)){
			certType="21";
			
		}
		sub.setREL_CERT_TYPE(certType);//证件类型
		sub.setREL_CERT_NO(rela.getString("certNum"));//证件号码
		subs[0]=sub;
		B212.setGRP_REL_COM(subs);
		S0110102000B212Response responseB212= orgEcif.OrgRelComMaint(B212);
		String msgg=responseB212.getResTranHeader().getHRetMsg();
		String msg=responseB212.getResTranHeader().getHRetCode();
		FMT_CRMS_SVR_S0110102000B212_OUT_SUB[] out = responseB212.getResponseBody().getGRP_REL_COM();
		if(out.length>0){
			rela.set("parSeqId", out[0].getPAR_SEQ_ID());
		}
		Map map = new HashMap();
	    map.put("msgg", msgg);
		map.put("msg", msg);
		return map;
	}
	
	/*
	 * 对公客户关系个人信息删除  OrgRelPsnMaint
	 */
	@Bizlet(value = "对公客户关系个人信息删除")
	public Map OrgRelPsnDel(DataObject rela) throws Exception{
		String partyId=rela.getString("partyId");
		DataObject party1 = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
		party1.set("partyId", partyId);
		DatabaseUtil.expandEntity("default", party1);
		String ecifCustNo1=party1.getString("ecifPartyNum");//023002000415
		IcustEcif orgEcif =new CustEcifImpl();
		FMT_CRMS_SVR_S0110102000B211_IN B211= new  FMT_CRMS_SVR_S0110102000B211_IN();
		String parSeqId=rela.getString("parSeqId");
		Map map = new HashMap();
		if("".equals(parSeqId)||parSeqId==null){
		    map.put("msgg", "成功");
			map.put("msg", "AAAAAAA");
			return map;
		}
		B211.setPAR_SEQ_ID(rela.getString("parSeqId"));//关联关系记录编号
//		B211.setREL_CUST_NO(rela.getString("relCustNo"));//关系人客户编号
		B211.setECIF_CUST_NO(ecifCustNo1);//客户编号
//		B211.setECIF_CUST_NO("023002000415");//客户编号
//		B211.setRELATION_TYPE(rela.getString("relatedCd"));//关联关系类型
//		B211.setRELATION_ID(rela.getString("relaPartyId"));//关联客户号
		S0110102000B211Response responseB211=orgEcif.OrgRelPsnDel(B211);
		String msgg=responseB211.getResTranHeader().getHRetMsg();
		String msg=responseB211.getResTranHeader().getHRetCode();
	    map.put("msgg", msgg);
		map.put("msg", msg);

		
		return map;
	}
	/*
	 * 对公客户关系企业信息删除
	 */
	@Bizlet(value = "对公客户关系企业信息删除")
	public Map OrgRelComDel(DataObject rela) throws Exception{
		String partyId=rela.getString("partyId");
		DataObject party1 = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
		party1.set("partyId", partyId);
		DatabaseUtil.expandEntity("default", party1);
		String ecifCustNo1=party1.getString("ecifPartyNum");
		IcustEcif orgEcif =new CustEcifImpl();
		FMT_CRMS_SVR_S0110102000B213_IN B213= new  FMT_CRMS_SVR_S0110102000B213_IN();
		String parSeqId=rela.getString("parSeqId");
		Map map = new HashMap();
		if("".equals(parSeqId)||parSeqId==null){
		    map.put("msgg", "成功");
			map.put("msg", "AAAAAAA");
			return map;
		}
		B213.setPAR_SEQ_ID(rela.getString("parSeqId"));//关联关系记录编号
//		B213.setREL_CUST_NO(rela.getString("relCustNo"));//关系人客户编号
		B213.setECIF_CUST_NO(ecifCustNo1);//客户编号023002003627
//		B213.setECIF_CUST_NO("023002003627");//客户编号023002003627
//		B213.setRELATION_TYPE(rela.getString("relatedCd"));//关联关系类型
//		B213.setRELATION_ID(rela.getString("relaPartyId"));//关联客户号
		S0110102000B213Response responseB213= orgEcif.OrgRelComDel(B213);
		String msgg=responseB213.getResTranHeader().getHRetMsg();
		String msg=responseB213.getResTranHeader().getHRetCode();
	    map.put("msgg", msgg);
		map.put("msg", msg);
		return map;
	}
	/**===================================================客服修改结束=======================================
	 */
		
		
		
		

}
