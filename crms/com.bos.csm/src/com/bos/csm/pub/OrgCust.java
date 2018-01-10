/**
 * 
 */
package com.bos.csm.pub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.bos.pub.BizNumGenerator;
import com.bos.pub.GitUtil;
import com.bos.pub.entity.EntityUtil;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.system.annotation.Bizlet;
import com.informix.util.dateUtil;
import com.primeton.tsl.ecif.S0110101000A011ServiceStub.FMT_CRMS_SVR_S0110101000A011_IN;
import com.primeton.tsl.ecif.S0110101000A011ServiceStub.FMT_CRMS_SVR_S0110101000A011_OUT;
import com.primeton.tsl.ecif.S0110101000A011ServiceStub.FMT_CRMS_SVR_S0110101000A011_OUT_SUB;
import com.primeton.tsl.ecif.S0110101000A011ServiceStub.FMT_CRMS_SVR_S0110101000A011_OUT_SUB1;
import com.primeton.tsl.ecif.S0110101000A011ServiceStub.FMT_CRMS_SVR_S0110101000A011_OUT_SUB2;
import com.primeton.tsl.ecif.S0110101000A011ServiceStub.S0110101000A011Response;
import com.primeton.tsl.ecif.S0110101000A202ServiceStub.FMT_CRMS_SVR_S0110101000A202_IN;
import com.primeton.tsl.ecif.S0110101000A202ServiceStub.FMT_CRMS_SVR_S0110101000A202_OUT;
import com.primeton.tsl.ecif.S0110101000A202ServiceStub.S0110101000A202Response;
import com.primeton.tsl.ecif.S0110101000A221ServiceStub.FMT_CRMS_SVR_S0110101000A221_IN;
import com.primeton.tsl.ecif.S0110101000A221ServiceStub.FMT_CRMS_SVR_S0110101000A221_OUT;
import com.primeton.tsl.ecif.S0110101000A221ServiceStub.FMT_CRMS_SVR_S0110101000A221_OUT_SUB;
import com.primeton.tsl.ecif.S0110101000A221ServiceStub.S0110101000A221Response;
import com.primeton.tsl.ecif.S0110101000A222ServiceStub.FMT_CRMS_SVR_S0110101000A222_IN;
import com.primeton.tsl.ecif.S0110101000A222ServiceStub.FMT_CRMS_SVR_S0110101000A222_OUT_SUB;
import com.primeton.tsl.ecif.S0110101000A222ServiceStub.S0110101000A222Response;
import com.primeton.tsl.ecif.S0110102000B011ServiceStub.FMT_CRMS_SVR_S0110102000B011_IN;
import com.primeton.tsl.ecif.S0110102000B011ServiceStub.FMT_CRMS_SVR_S0110102000B011_IN_SUB;
import com.primeton.tsl.ecif.S0110102000B011ServiceStub.FMT_CRMS_SVR_S0110102000B011_IN_SUB1;
import com.primeton.tsl.ecif.S0110102000B011ServiceStub.FMT_CRMS_SVR_S0110102000B011_IN_SUB2;
import com.primeton.tsl.ecif.S0110102000B011ServiceStub.S0110102000B011Response;
import com.primeton.tsl.ecif.S0110102000B201ServiceStub.FMT_CRMS_SVR_S0110102000B201_IN;
import com.primeton.tsl.ecif.S0110102000B201ServiceStub.FMT_CRMS_SVR_S0110102000B201_OUT;
import com.primeton.tsl.ecif.S0110102000B201ServiceStub.S0110102000B201Response;
import com.primeton.tsl.ecif.S0110102000B221ServiceStub.FMT_CRMS_SVR_S0110102000B221_IN;
import com.primeton.tsl.ecif.S0110102000B221ServiceStub.FMT_CRMS_SVR_S0110102000B221_OUT;
import com.primeton.tsl.ecif.S0110102000B221ServiceStub.S0110102000B221Response;
import com.primeton.tsl.ecif.S0110102000B222ServiceStub.FMT_CRMS_SVR_S0110102000B222_IN;
import com.primeton.tsl.ecif.S0110102000B222ServiceStub.FMT_CRMS_SVR_S0110102000B222_IN_SUB;
import com.primeton.tsl.ecif.S0110102000B222ServiceStub.FMT_CRMS_SVR_S0110102000B222_OUT;
import com.primeton.tsl.ecif.S0110102000B222ServiceStub.S0110102000B222Response;
import com.primeton.tsl.ecif.S0110102000B223ServiceStub.FMT_CRMS_SVR_S0110102000B223_IN;
import com.primeton.tsl.ecif.S0110102000B223ServiceStub.FMT_CRMS_SVR_S0110102000B223_OUT;
import com.primeton.tsl.ecif.S0110102000B223ServiceStub.S0110102000B223Response;
import com.primeton.tsl.ecif.S0110102000B224ServiceStub.FMT_CRMS_SVR_S0110102000B224_IN;
import com.primeton.tsl.ecif.S0110102000B224ServiceStub.FMT_CRMS_SVR_S0110102000B224_OUT;
import com.primeton.tsl.ecif.S0110102000B224ServiceStub.S0110102000B224Response;
import com.primeton.tsl.ecif.port.IcustEcif;
import com.primeton.tsl.ecif.port.OrgCustEcif;
import com.primeton.tsl.ecif.port.impl.CustEcifImpl;
import com.primeton.tsl.ecif.port.impl.OrgCustEcifImpl;

import commonj.sdo.DataObject;

/**
 * @author zx
 * @date 2017-07-03 19:44:12
 *对公客户相关操作
 */
@Bizlet("")
public class OrgCust {
	/*
	 * 对公客户基本信息查询   OrgCustBaseQuery
	 */
	/**===================================================客服查询开始=======================================
	 */
		@Bizlet("对公客户基本信息查询")
		public Map OrgCustBaseQuery(DataObject party,DataObject item) throws Exception{
			
			/**
			 * 查询本地的对公客户基本信息
			 * 
			 */
			DataObject item1 = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmCorporation");
			item1.set("partyId", item.getString("partyId"));
			DatabaseUtil.expandEntity("default", item1);
			
			DataObject party1 = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
			party1.set("partyId", party.getString("partyId"));
			DatabaseUtil.expandEntity("default", party1);
			/**
			 * 调ECIF接口
			 */
			String ecifPartyNum=party1.getString("ecifPartyNum");
			OrgCustEcif orgEcif =new OrgCustEcifImpl();
			FMT_CRMS_SVR_S0110101000A202_IN requestA202=new FMT_CRMS_SVR_S0110101000A202_IN();
			requestA202.setRESOLVE_TYPE("1");
			requestA202.setECIF_CUST_NO(ecifPartyNum);
			S0110101000A202Response	responseA202=orgEcif.COrgCustBaseQuery(requestA202);
			FMT_CRMS_SVR_S0110101000A202_OUT  resBody = responseA202.getResponseBody();
			//ECIF客户编号
			 //CRMS客户编号
			//客户类型 原来的
			//客户名称 
			String partyName=resBody.getPARTY_NAME();
			if(!"".equals(partyName)&&partyName!=null){
				party1.set("partyName", resBody.getPARTY_NAME());
			}
			//是否授信客户 
			//别名 
			if(!"".equals(resBody.getCUST_ENNAME())||resBody.getCUST_ENNAME()!=null){
			item1.set("englishName", resBody.getCUST_ENNAME());
			}
			  //区域类型  原来的
			//国家或地区 
			if(!"".equals(resBody.getCERT_ORG_AREA())||resBody.getCERT_ORG_AREA()!=null){
				item1.set("contryRegionCd", resBody.getCERT_ORG_AREA());
			}
			if(!"".equals(resBody.getCERT_ORG_NAT())||resBody.getCERT_ORG_NAT()!=null){
			item1.set("contryRegionCd", resBody.getCERT_ORG_NAT());
			}
			//是否第三方客户 原来的
			//第三方客户类型  原来的
			//单一法人客户标志 原来的
			//法人代表姓名 原来的
			//法人代表证件类型原来的
			//法人代表证件号码 原来的
			//证件到期日 
			if(resBody.getCERT_DUE_DATE()!=null&&!"".equals(resBody.getCERT_DUE_DATE())){
		//	item1.set("legalCertificateEndDate", resBody.getCERT_DUE_DATE());
			}
				//登记注册类型 
			if(resBody.getREG_TYPE()!=null&&!"".equals(resBody.getREG_TYPE())){
			item1.set("registrationType", resBody.getREG_TYPE());
			}
				//企业出资人经济成分 原来的
				//统一社会信用代码 
			if("2h".equals(resBody.getCERT_TYPE())){
				if(resBody.getCERT_NO()!=null&&!"".equals(resBody.getCERT_NO())){
			item1.set("unifySocietyCreditNum", resBody.getCERT_NO());
			//国税登记证号码 原来的
		item1.set("nationalTaxNo", resBody.getCERT_NO());
			//地税登记证号码 原来的
		item1.set("governmentTentNo", resBody.getCERT_NO());
				}
			}
			if("21".equals(resBody.getCERT_TYPE())){
				if(resBody.getCERT_NO()!=null&&!"".equals(resBody.getCERT_NO())){
			item1.set("orgRegisterCd", resBody.getCERT_NO());
				}
			}
			if("22".equals(resBody.getCERT_TYPE())){
				if(resBody.getCERT_NO()!=null&&!"".equals(resBody.getCERT_NO())){
			item1.set("registrCd", resBody.getCERT_NO());
				}
			}
				//注册登记号码 
			
			if(resBody.getGOVN_CERT_NO()!=null&&!"".equals(resBody.getGOVN_CERT_NO())){
		item1.set("registrCd", resBody.getGOVN_CERT_NO());
			}
				//注册登记日期 
		String 	registerDate= resBody.getREG_DATE();
		String 	register="";
	
			 if(registerDate!=null&&registerDate.length()==8){
				 register =registerDate.substring(0, 4)+"-"+registerDate.substring(4, 6)+"-"+registerDate.substring(6, 8);
			 }
				if(register!=null&&!"".equals(register)){
				item1.set("registerDate", register);
				}
				//注册登记证到期日 
				String 	registerEndDate= resBody.getREG_CHECK_DATE();
				String 	registere="";
			
					 if(registerEndDate!=null&&registerEndDate.length()==8){
						 registere =registerEndDate.substring(0, 4)+"-"+registerEndDate.substring(4, 6)+"-"+registerEndDate.substring(6, 8);
					 }
					 if(registere!=null&&!"".equals(registere)){
	//		item1.set("registerEndDate", registere);
					 }
				//经营范围 原来的
					 if(resBody.getADMN_TYPE()!=null&&!"".equals(resBody.getADMN_TYPE())){
						 item1.set("businessScope", resBody.getADMN_TYPE());
						 } 
				//注册资本币种 
						if(resBody.getREG_CPTL_CURR()!=null&&!"".equals(resBody.getREG_CPTL_CURR())){
			//item1.set("registerAssetsCurrencyCd", resBody.getREG_CPTL_CURR());
						}
				//注册资本 
						if(resBody.getREG_CPTL()!=null&&!"".equals(resBody.getREG_CPTL())){
			item1.set("registerAssets", resBody.getREG_CPTL());
						}
			    //组织机构代码 
			//item1.set("orgRegisterCd", resBody.getORG_CODE());
						 if(resBody.getORG_CODE()!=null&&!"".equals(resBody.getORG_CODE())){
							 item1.set("orgRegisterCd", resBody.getORG_CODE());
							 }
				//组织机构代码证到期日 
			//item1.set("orgRegisterEndDate", resBody.getORG_CODE_DUE_DATE());
				//中征码 原来的
				//机构信用代码 
					//	if(resBody.getUNIT_CREDIT_CODE()!=null&&!"".equals(resBody.getUNIT_CREDIT_CODE())){
			//item1.set("orgCreditCode", resBody.getUNIT_CREDIT_CODE());
				//		}
			//行业门类 原来的
			//行业大类 原来的
			//行业中类 原来的
			//行业小类 原来的
		    // 从业人数（人） 原来的
			// 工信部企业规模 原来的
			//  统计口径企业规模 原来的
			//是否上市公司 
		if(resBody.getLISTED_FLAG()!=null&&!"".equals(resBody.getLISTED_FLAG())){
			item1.set("listingCorporation", resBody.getLISTED_FLAG());
		}
			//进出口权标志 
		if(resBody.getIMEX_MANA_IND()!=null&&!"".equals(resBody.getIMEX_MANA_IND())){
			item1.set("whetherImpExp", resBody.getIMEX_MANA_IND());
		}
			//家族企业标志 原来的
			//融资平台标志 原来的
			//重点客户标志 原来的
			//是否从事房地产开发 原来的
			//农村企业标志 原来的
			//是否我行关联方 
/*		if(resBody.getREL_UNIT_FLAG()!=null&&!"".equals(resBody.getREL_UNIT_FLAG())){
			item1.set("isBasebankRelaCust", resBody.getREL_UNIT_FLAG());
		}*/
			//我行股东标志 
		if(resBody.getSHAREHOLDER_FLAG()!=null&&!"".equals(resBody.getSHAREHOLDER_FLAG())){
			item1.set("stockholderOfBank", resBody.getSHAREHOLDER_FLAG());
		}
			//联保小组标志 原来的
			//是否统一授信成员 
//			item1.set("isGroupCust", item11.getString("isGroupCust"));
			//所属统一授信客户名称 原来的
			//黑名单标志 原来的
			//关停企业标志 原来的
			//与我行建立信贷关系日期 原来的
/*	        CCustAddrInfo(ecifPartyNum,"113","","",item);//注册地址
		    CCustAddrInfo(ecifPartyNum,"114","","",item);//经营地址
		    CCustAddrInfo(ecifPartyNum,"","103","",item);//联系人电话
		    CCustAddrInfo(ecifPartyNum,"","102","",item);//联系人电话
		    CCustAddrInfo(ecifPartyNum,"","","107",item);//微信号码
		    CCustAddrInfo(ecifPartyNum,"","","101",item);//网址
*/			
			String msgg=responseA202.getResTranHeader().getHRetMsg();
			String msg=responseA202.getResTranHeader().getHRetCode();
			Map map = new HashMap();
	
			map.put("msgg", msgg);
			map.put("msg", msg);
			map.put("party", party1);
			map.put("item", item1);
			return map;
 }
		
	
		/*
		 * 同业客户基本信息查询
		 */
		@Bizlet("同业客户基本信息查询")
		public Map SameCustBaseQuery(Map item) throws Exception{
			
			/**
			 * 查询本地的对公客户基本信息
			 * 
			 */
			HashMap<String, Object> financeIds = new HashMap<String, Object>();
			Object[] items=DatabaseExt.queryByNamedSql("default", "com.bos.csm.financialinstitution.financialsql.query_init_fin_id", item);
			/**
			 * 调ECIF接口
			 */
			DataObject financialInstitution=(DataObject) items[0];
			String ecifPartyNum=(String) financialInstitution.get("ecifPartyNum");
			OrgCustEcif orgEcif =new OrgCustEcifImpl();
			FMT_CRMS_SVR_S0110101000A202_IN requestA202=new FMT_CRMS_SVR_S0110101000A202_IN();
			requestA202.setRESOLVE_TYPE("1");
			requestA202.setECIF_CUST_NO(ecifPartyNum);
			S0110101000A202Response	responseA202=orgEcif.COrgCustBaseQuery(requestA202);
			FMT_CRMS_SVR_S0110101000A202_OUT  resBody = responseA202.getResponseBody();
			//客户名称 
			if(resBody.getPARTY_NAME()!=null&&!"".equals(resBody.getPARTY_NAME())){
			financialInstitution.set("partyName", resBody.getPARTY_NAME());
			}
			
			//别名 
			if(resBody.getCUST_ENNAME()!=null&&!"".equals(resBody.getCUST_ENNAME())){
			financialInstitution.set("englishName", resBody.getCUST_ENNAME());
			}
			/**
			 * 调用ECIF 公用地址信息
			 */
			//国家或地区 
			if(resBody.getCERT_ORG_AREA()!=null&&!"".equals(resBody.getCERT_ORG_AREA())){
				financialInstitution.set("contryRegionCd", resBody.getCERT_ORG_AREA());
			}
			if(resBody.getCERT_ORG_NAT()!=null&&!"".equals(resBody.getCERT_ORG_NAT())){
			financialInstitution.set("contryRegionCd", resBody.getCERT_ORG_NAT());
			}
			//金融机构类型
			if(resBody.getFIN_ORG_TYPE()!=null&&!"".equals(resBody.getFIN_ORG_TYPE())){
			financialInstitution.set("financeEnterpriseType", resBody.getFIN_ORG_TYPE());
			}
			//金融许可证机构编码
			if(resBody.getFIN_LIC_NO()!=null&&!"".equals(resBody.getFIN_LIC_NO())){
			financialInstitution.set("financialPermitNum", resBody.getFIN_LIC_NO());
			}
			//swift 码
			if(resBody.getSWIFT_NO()!=null&&!"".equals(resBody.getSWIFT_NO())){
			financialInstitution.set("swiftBicNum", resBody.getSWIFT_NO());
			}
			//统一社会信用代码 
			if("2h".equals(resBody.getCERT_TYPE())){
				if(resBody.getCERT_NO()!=null&&!"".equals(resBody.getCERT_NO())){
				financialInstitution.set("unifySocietyCreditNum", resBody.getCERT_NO());
				}
			}

				//注册登记号码 
			if(resBody.getGOVN_CERT_NO()!=null&&!"".equals(resBody.getGOVN_CERT_NO())){
			financialInstitution.set("registerCode", resBody.getGOVN_CERT_NO());
			}
				//注册登记日期 
		String 	registerDate= resBody.getREG_DATE();
		String 	register="";
	
			 if(registerDate!=null&&registerDate.length()==8){
				 register =registerDate.substring(0, 4)+"-"+registerDate.substring(4, 6)+"-"+registerDate.substring(6, 8);
			 }
			 financialInstitution.set("registerDate", register);
				//注册登记证到期日 
				String 	registerEndDate= resBody.getREG_CHECK_DATE();
				String 	registere="";
			
					 if(registerEndDate!=null&&registerEndDate.length()==8){
						 registere =registerEndDate.substring(0, 4)+"-"+registerEndDate.substring(4, 6)+"-"+registerEndDate.substring(6, 8);
					 }
					 if(registere!=null&&!"".equals(registere)){
					 financialInstitution.set("registerEndDate", registere);
					 }
				//注册资本币种 
					 if(resBody.getREG_CPTL_CURR()!=null&&!"".equals(resBody.getREG_CPTL_CURR())){
					 financialInstitution.set("registerAssetsCurrencyCd", resBody.getREG_CPTL_CURR());
					 }
				//注册资本 
					 if(resBody.getREG_CPTL()!=null&&!"".equals(resBody.getREG_CPTL())){
					 financialInstitution.set("registerAssets", resBody.getREG_CPTL());
					 }
			    //组织机构代码 
					 if(resBody.getORG_CODE()!=null&&!"".equals(resBody.getORG_CODE())){
					 financialInstitution.set("orgRegisterCd", resBody.getORG_CODE());
					 }
			
	        CCustAddrInfo(ecifPartyNum,"113","","",financialInstitution);//注册地址
		    CCustAddrInfo(ecifPartyNum,"114","","",financialInstitution);//经营地址
	
		    //	item.set("addressValue", item.getString("jyAddress"));
/*		    CCustAddrInfo(ecifPartyNum,"","103","",item);//联系人电话
		    CCustAddrInfo(ecifPartyNum,"","102","",item);//联系人电话
		    CCustAddrInfo(ecifPartyNum,"","","107",item);//微信号码
		    CCustAddrInfo(ecifPartyNum,"","","101",item);//网址
*/			
			String msgg=responseA202.getResTranHeader().getHRetMsg();
			String msg=responseA202.getResTranHeader().getHRetCode();
	      
			Map map = new HashMap();
			
			map.put("msgg", msgg);
			map.put("msg", msg);
			map.put("financialInstitution", financialInstitution);
			return map;
 }
		
		/*
		 * 合作中介客户基本信息查询
		 */
		@Bizlet("合作中介客户基本信息查询")
		public Map thirdCustBaseQuery(String partyId) throws Exception{
			
			/**
			 * 查询本地的对公客户基本信息
			 * 
			 */
			DataObject thirdParty = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmThirdParty");
			thirdParty.set("partyId", partyId);
			DatabaseUtil.expandEntity("default", thirdParty);
			
			DataObject party = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
			party.set("partyId", partyId);
			DatabaseUtil.expandEntity("default", party);
			/**
			 * 调ECIF接口
			 */
			String ecifPartyNum=party.getString("ecifPartyNum");
			OrgCustEcif orgEcif =new OrgCustEcifImpl();
			FMT_CRMS_SVR_S0110101000A202_IN requestA202=new FMT_CRMS_SVR_S0110101000A202_IN();
			requestA202.setRESOLVE_TYPE("1");
			requestA202.setECIF_CUST_NO(ecifPartyNum);
			S0110101000A202Response	responseA202=orgEcif.COrgCustBaseQuery(requestA202);
			FMT_CRMS_SVR_S0110101000A202_OUT  resBody = responseA202.getResponseBody();
			//客户名称 
			 if(resBody.getPARTY_NAME()!=null&&!"".equals(resBody.getPARTY_NAME())){
			party.set("partyName", resBody.getPARTY_NAME());
			 }
			
			//别名 
			 if(resBody.getCUST_ENNAME()!=null&&!"".equals(resBody.getCUST_ENNAME())){
			thirdParty.set("englishName", resBody.getCUST_ENNAME());
			 }
			/**
			 * 调用ECIF 公用地址信息
			 */
			//证件到期日 
			String 	legalCertificateEndDate=resBody.getCERT_DUE_DATE();
			String 	legalCertificate="";
		
				 if(legalCertificateEndDate!=null&&legalCertificateEndDate.length()==8){
					 legalCertificate =legalCertificateEndDate.substring(0, 4)+"-"+legalCertificateEndDate.substring(4, 6)+"-"+legalCertificateEndDate.substring(6, 8);
				 }
				 if(legalCertificate!=null&&!"".equals(legalCertificate)){	 
		// thirdParty.set("legalCertificateEndDate", legalCertificate);
				 }


				//注册登记号码 
				 if(resBody.getGOVN_CERT_NO()!=null&&!"".equals(resBody.getGOVN_CERT_NO())){	  
			thirdParty.set("registrCd", resBody.getGOVN_CERT_NO());
				 }
				//注册登记日期 
		String 	registerDate= resBody.getREG_DATE();
		String 	register="";
	
			 if(registerDate!=null&&registerDate.length()==8){
				 register =registerDate.substring(0, 4)+"-"+registerDate.substring(4, 6)+"-"+registerDate.substring(6, 8);
			 }
			 if(register!=null&&!"".equals(register)){
			 thirdParty.set("registerDate", register);
			 }
				//注册登记证到期日 
				String 	registerEndDate= resBody.getREG_CHECK_DATE();
				String 	registere="";
			
					 if(registerEndDate!=null&&registerEndDate.length()==8){
						 registere =registerEndDate.substring(0, 4)+"-"+registerEndDate.substring(4, 6)+"-"+registerEndDate.substring(6, 8);
					 }
					 if(registere!=null&&!"".equals(registere)){
					 thirdParty.set("registerEndDate", registere);
					 }
						//经营范围 
					 if(resBody.getADMN_TYPE()!=null&&!"".equals(resBody.getADMN_TYPE())){
					 thirdParty.set("businessScope", resBody.getADMN_TYPE());
					 }
						//统一社会信用代码 
						if("2h".equals(resBody.getCERT_TYPE())){
							 if(resBody.getCERT_NO()!=null&&!"".equals(resBody.getCERT_NO())){
							thirdParty.set("unifySocietyCreditNum", resBody.getCERT_NO());
							 }
						}
				//注册资本币种 
						 if(resBody.getREG_CPTL_CURR()!=null&&!"".equals(resBody.getREG_CPTL_CURR())){
					 thirdParty.set("registerAssetsCurrencyCd", resBody.getREG_CPTL_CURR());
						 }
				//注册资本 
						 if(resBody.getREG_CPTL()!=null&&!"".equals(resBody.getREG_CPTL())){
					 thirdParty.set("registerAssets", resBody.getREG_CPTL());
						 }
			    //组织机构代码 
						 if(resBody.getORG_CODE()!=null&&!"".equals(resBody.getORG_CODE())){
					 thirdParty.set("orgRegisterCd", resBody.getORG_CODE());
						 }
						//组织机构代码证到期日 
						String 	orgRegisterEndDate= resBody.getREG_DATE();
						String 	orgRegister="";
					
							 if(orgRegisterEndDate!=null&&orgRegisterEndDate.length()==8){
								 orgRegister =orgRegisterEndDate.substring(0, 4)+"-"+orgRegisterEndDate.substring(4, 6)+"-"+orgRegisterEndDate.substring(6, 8);
							 }
							 if(orgRegister!=null&&!"".equals(orgRegister)){
					 thirdParty.set("orgRegisterEndDate", orgRegister);
							 }
							//机构信用代码 
						//	 if(resBody.getUNIT_CREDIT_CODE()!=null&&!"".equals(resBody.getUNIT_CREDIT_CODE())){
					// thirdParty.set("orgCreditCode", resBody.getUNIT_CREDIT_CODE());
						//	 }
						//国税登记证号码原来的
					if(resBody.getTAX_REG_NO()!=null&&!"".equals(resBody.getTAX_REG_NO())){
						thirdParty.set("nationalTaxNo", resBody.getTAX_REG_NO());
					}
					 
						//地税登记证号码 原来的
					if(resBody.getTAX_AREA_NO()!=null&&!"".equals(resBody.getTAX_AREA_NO())){
						thirdParty.set("governmentTentNo", resBody.getTAX_AREA_NO());
					}
						//经营许可证： 
							 if(resBody.getBUSI_LIC_NO()!=null&&!"".equals(resBody.getBUSI_LIC_NO())){
					 thirdParty.set("businessCd", resBody.getBUSI_LIC_NO());
							 }
						//证件到期日 
						String 	businessEndDate= resBody.getREG_DATE();
						String 	business="";
					
							 if(businessEndDate!=null&&businessEndDate.length()==8){
								 business =businessEndDate.substring(0, 4)+"-"+businessEndDate.substring(4, 6)+"-"+businessEndDate.substring(6, 8);
							 }
							 if(business!=null&&!"".equals(business)){
					 thirdParty.set("businessEndDate", business);
							 }
					 
	        CCustAddrInfo(ecifPartyNum,"103","","",thirdParty);//联系地址
	        CCustAddrInfo(ecifPartyNum,"","103","",thirdParty);//联系人电话
		
			String msgg=responseA202.getResTranHeader().getHRetMsg();
			String msg=responseA202.getResTranHeader().getHRetCode();
	      
			Map map = new HashMap();
			
			map.put("msgg", msgg);
			map.put("msg", msg);
			map.put("party", party);
			map.put("thirdParty", thirdParty);
			return map;
 }
		
		/*
		 * 
		 */
		@Bizlet("对公客户基本地址信息查询")
		public Map OrgCustAdderQuery(String partyId) throws Exception{
			/**
			 * 查询本地的对公客户基本信息
			 * 
			 */
			DataObject items = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmCorporation");
			items.set("partyId", partyId);
			DatabaseUtil.expandEntity("default", items);
			DataObject party = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
			party.set("partyId", partyId);
			DatabaseUtil.expandEntity("default", party);
			DataObject	item = 	EntityUtil.searchEntity("com.bos.dataset.csm.TbCsmAttachedInfo", "partyId",partyId);
			Map map = new HashMap();
			String msgg="成功";
			String msg="AAAAAAA";
			if(item!=null&&!"".equals(item)){
				
	
			/**
			 * 调ECIF接口
			 */
			String ecifPartyNum=party.getString("ecifPartyNum");
			Map mapZC = new HashMap();
			mapZC=CCustAddrInfo(ecifPartyNum,"113","","",item);//注册地址
			Map mapJY = new HashMap();
			mapJY= CCustAddrInfo(ecifPartyNum,"114","","",item);//经营地址
			Map mapLXR = new HashMap();
			mapLXR= CCustAddrInfo(ecifPartyNum,"","103","",item);//联系人电话
			Map mapYD = new HashMap();
			mapYD=CCustAddrInfo(ecifPartyNum,"","102","",item);//移动电话
			Map mapWX = new HashMap();
			mapWX= CCustAddrInfo(ecifPartyNum,"","","107",item);//微信号码
			Map mapWZ = new HashMap();
			mapWZ=CCustAddrInfo(ecifPartyNum,"","","101",item);//网址
             if(!"AAAAAAA".equals((String)mapZC.get("msg"))){
            	 msgg=(String) mapZC.get("msgg");
            	 msg=(String) mapZC.get("msg");
             }
             if(!"AAAAAAA".equals((String)mapJY.get("msg"))){
            	 msgg=(String) mapJY.get("msgg");
            	 msg=(String) mapJY.get("msg");
             }
             if(!"AAAAAAA".equals((String)mapLXR.get("msg"))){
            	 msgg=(String) mapLXR.get("msgg");
            	 msg=(String) mapLXR.get("msg");
             }
             if(!"AAAAAAA".equals((String)mapYD.get("msg"))){
            	 msgg=(String) mapYD.get("msgg");
            	 msg=(String) mapYD.get("msg");
             }
             if(!"AAAAAAA".equals((String)mapWX.get("msg"))){
            	 msgg=(String) mapWX.get("msgg");
            	 msg=(String) mapWX.get("msg");
             }
             if(!"AAAAAAA".equals((String)mapWZ.get("msg"))){
            	 msgg=(String) mapWZ.get("msgg");
            	 msg=(String) mapWZ.get("msg");
             }
			}
            map.put("msgg", msgg);
			map.put("msg", msg);
			map.put("party", party);
			map.put("item", item);
			return map;
		}
		@Bizlet(value = "集团客户查询")
		public Map COrgGrpCustBaseMaintgroup(DataObject party) throws Exception {
			
			DataObject party1 = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
			party1.set("partyId", party.getString("partyId"));
			DatabaseUtil.expandEntity("default", party1);
			
			DataObject csmGroupCompany= DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmGroupCompany");
			csmGroupCompany.set("partyId", party.getString("partyId"));
			DatabaseUtil.expandEntity("default", csmGroupCompany);
			IcustEcif orgEcif =new CustEcifImpl();
			FMT_CRMS_SVR_S0110101000A221_IN requestA221=new FMT_CRMS_SVR_S0110101000A221_IN();
			requestA221.setFIRST_NO("1");
			requestA221.setRESULT_SIZE("1");
			requestA221.setRESOLVE_TYPE("1");
			requestA221.setGROUP_CUST_NO(party1.getString("ecifPartyNum"));
			S0110101000A221Response	responseA221=orgEcif.OrgGrpBaseQuery(requestA221);
			FMT_CRMS_SVR_S0110101000A221_OUT resBody = responseA221.getResponseBody();
			FMT_CRMS_SVR_S0110101000A221_OUT_SUB[] ress=resBody.getGRP_BASE();
			FMT_CRMS_SVR_S0110101000A221_OUT_SUB res=new FMT_CRMS_SVR_S0110101000A221_OUT_SUB();
			if(ress!=null&&ress.length>0){
				res=ress[0];
			}
			String msgg=responseA221.getResTranHeader().getHRetMsg();
			String msg=responseA221.getResTranHeader().getHRetCode();
			csmGroupCompany.set("groupMemberNum",res.getUNION_MEM_COUNT() );
	/*		party1.set("partyName", res.getGROUP_NAME());*/
			Map map = new HashMap();
            map.put("msgg", msgg);
			map.put("msg", msg);
			map.put("party", party1);
			map.put("memberNum",res.getUNION_MEM_COUNT() );
			map.put("csmGroupCompany", csmGroupCompany);
			return map;
		}
		@Bizlet(value = "联保客户查询")
		public Map GuarCustBaseMaint(DataObject party) throws Exception {
			DataObject party1 = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
			party1.set("partyId", party.getString("partyId"));
			DatabaseUtil.expandEntity("default", party1);
			
			DataObject csmGuarGroup= DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmGuarGroup");
			csmGuarGroup.set("partyId", party.getString("partyId"));
			DatabaseUtil.expandEntity("default", csmGuarGroup);
			IcustEcif orgEcif =new CustEcifImpl();
			FMT_CRMS_SVR_S0110101000A221_IN requestA221=new FMT_CRMS_SVR_S0110101000A221_IN();
			requestA221.setFIRST_NO("1");
			requestA221.setRESULT_SIZE("1");
			requestA221.setRESOLVE_TYPE("1");
			requestA221.setGROUP_CUST_NO(party1.getString("ecifPartyNum"));
			S0110101000A221Response	responseA221=orgEcif.OrgGrpBaseQuery(requestA221);
			FMT_CRMS_SVR_S0110101000A221_OUT resBody = responseA221.getResponseBody();
			FMT_CRMS_SVR_S0110101000A221_OUT_SUB[] ress=resBody.getGRP_BASE();
			FMT_CRMS_SVR_S0110101000A221_OUT_SUB res=new FMT_CRMS_SVR_S0110101000A221_OUT_SUB();
			if(ress!=null&&ress.length>0){
				res=ress[0];
			}
			String msgg=responseA221.getResTranHeader().getHRetMsg();
			String msg=responseA221.getResTranHeader().getHRetCode();
/*			party1.set("partyName", res.getGROUP_NAME());*/
			Map map = new HashMap();
            map.put("msgg", msgg);
			map.put("msg", msg);
			map.put("party", party1);
			map.put("memberNum",res.getUNION_MEM_COUNT() );
			map.put("csmGuarGroup", csmGuarGroup);
			return map;
		}
		@Bizlet(value = "集团客户或者联保客户添加")
		public Map COrgGrpCustBaseMaintgroup(String partyName,String groupType) throws Exception {
			IcustEcif orgEcif =new CustEcifImpl();
			FMT_CRMS_SVR_S0110102000B221_IN requestB221=new FMT_CRMS_SVR_S0110102000B221_IN();
			requestB221.setGROUP_TYPE(groupType);//群体客户编号
			requestB221.setGROUP_NAME(partyName);
			S0110102000B221Response	responseB221=orgEcif.OrgGrpCustBaseMaint(requestB221);
			String msgg=responseB221.getResTranHeader().getHRetMsg();
			String msg=responseB221.getResTranHeader().getHRetCode();
			Map map = new HashMap();
            map.put("msgg", msgg);
			map.put("msg", msg);
			map.put("groupCustNo", responseB221.getResponseBody().getGROUP_CUST_NO());
			return map;
		}
		@Bizlet(value = "群体客户成员关系查询")
		public Map OrgGrpMemRelQuery(String partyId,DataObject pageCond,String groupType) throws Exception {
			DataObject party = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
			party.set("partyId", partyId);
			DatabaseUtil.expandEntity("default", party);//
			
			String firstNo=pageCond.getString("begin");//RESULT_SIZE
			int first=0;
			if(firstNo!=null&&!"".equals(firstNo)){
				first=Integer.parseInt(firstNo)+1;
				
			}else{
				first=1;
			}
			firstNo=String.valueOf(first);
			String resultSize=pageCond.getString("length");
			IcustEcif orgEcif =new CustEcifImpl();
			FMT_CRMS_SVR_S0110101000A222_IN requestA222=new FMT_CRMS_SVR_S0110101000A222_IN();
			requestA222.setFIRST_NO(firstNo);
			requestA222.setRESULT_SIZE(resultSize);
			requestA222.setRESOLVE_TYPE("2");
			requestA222.setPARTY_NAME(party.getString("partyName"));
			requestA222.setGROUP_TYPE(groupType);
//			requestA222.setGROUP_CUST_NO(party.getString("ecifPartyNum"));
			S0110101000A222Response	responseA222=orgEcif.COrgGrpMemRelQuery(requestA222);
			String msgg=responseA222.getResTranHeader().getHRetMsg();
			String msg=responseA222.getResTranHeader().getHRetCode();
			String count =responseA222.getResponseBody().getALL_RESULT_SIZE();
			FMT_CRMS_SVR_S0110101000A222_OUT_SUB[] subs=responseA222.getResponseBody().getGRP_MEM_REL();
			int len=0;
			if(count!=null&&!"".equals(count)){
				pageCond.set("count", count);
			}
			if(subs!=null&&!"".equals(subs)){
				len=subs.length;
				
			}
			Map[] items=new Map[len];
			Map map = new HashMap();
			if("AAAAAAA".equals(msg)){
				if(subs!=null&&!"".equals(subs)){
					for(int i=0;i<subs.length;i++){
						String ecifPartyNum1=subs[i].getECIF_CUST_NO();
						String legorg =	GitUtil.getLegorg();//绵商行为9999，北川富民村镇银行为5099，平武富民“4099”
						DataObject	patry1  = 	EntityUtil.searchEntity("com.bos.dataset.csm.TbCsmParty", "ecifPartyNum", ecifPartyNum1,"legOrg",legorg);
						DataObject	 groupMemeber  = 	DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmGroupMember");
						DataObject	 mGuarMemeber  = 	DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmGuarMemeber");
						DataObject	eamin  = 	DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmManagementTeam");
						DataObject	patryin  = 	DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
						DataObject	csmCorporationin  = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmCorporation");
						DataObject	TbCsmGuarMemeberin  = 	DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmGuarMemeber");
						DataObject	smGuarMemeberin  = 	DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmGroupMember");
						DataObject	eam  = 	DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmManagementTeam");
						if(patry1!=null&&!"".equals(patry1)){
								 groupMemeber  = 	EntityUtil.searchEntity("com.bos.dataset.csm.TbCsmGroupMember", "corporationPartyId", patry1.getString("partyId"));
								 mGuarMemeber  = 	EntityUtil.searchEntity("com.bos.dataset.csm.TbCsmGuarMemeber", "relatedCustPartyId", patry1.getString("partyId"));
								eamin  = 	EntityUtil.searchEntity("com.bos.dataset.csm.TbCsmManagementTeam", "partyId", patry1.getString("partyId"));
							patry1.set("partyName", subs[i].getPARTY_NAME());
							DatabaseUtil.updateEntity("default", patry1);
						}else{
		
							String partyNum = BizNumGenerator.genCustomerNum();
							patryin.set("ecifPartyNum", ecifPartyNum1);//BizNumGenerator.genCustomerNum
							patryin.set("partyName", subs[i].getPARTY_NAME());
							patryin.set("partyNum", partyNum);
							patryin.set("partyTypeCd","01");
							DatabaseUtil.insertEntity("default", patryin);

							csmCorporationin.set("partyId", patryin.get("partyId"));
							csmCorporationin.set("ecifPartyNum", ecifPartyNum1);
							csmCorporationin.set("corpCustomerTypeCd", "1");
							DatabaseUtil.insertEntity("default", csmCorporationin);
							//tb_csm_management_team
						}
						if(groupMemeber!=null&&!"".equals(groupMemeber)){
							groupMemeber.set("groupRelTypeCd", subs[i].getRELATION_TYPE());
						    DatabaseUtil.updateEntity("default", groupMemeber);
						}else{
							if("512".equals(groupType)){
								
								
							}else{

							smGuarMemeberin.set("corporationPartyId", patry1.get("partyId"));
							smGuarMemeberin.set("groupPartyId", partyId);
							smGuarMemeberin.set("groupRelTypeCd", subs[i].getRELATION_TYPE());
							smGuarMemeberin.set("status", "3");
							DatabaseUtil.insertEntity("default", smGuarMemeberin);
							
							if(eamin!=null&&!"".equals(eamin)){
								
							}else{
								eam.set("partyId", patry1.get("partyId"));
								eam.set("userPlacingCd", "01");
								DatabaseUtil.insertEntity("default", eam);
							}
		
							}
							
						}
						if(mGuarMemeber!=null&&!"".equals(mGuarMemeber)){

						}else{
							if("512".equals(groupType)){
								
								TbCsmGuarMemeberin.set("partyId", partyId);
								TbCsmGuarMemeberin.set("relatedCustPartyId", patry1.get("partyId"));
								TbCsmGuarMemeberin.set("status", "01");
								DatabaseUtil.insertEntity("default", TbCsmGuarMemeberin);
								
							}
		
							}
							
						}
						
				     }
					}
	

            map.put("msgg", msgg);
			map.put("msg", msg);
			map.put("items", items);
			map.put("pageCond", pageCond);

			return map;
		}
		/**===================================================客服查询结束=======================================
		 */
		/**===================================================客服信息修改开始=======================================
		 */
		
		/**
		 * @author
		 * @date 2017-07-03 19:44:12
		 *对公客户修改
		 */
				@Bizlet("对公客户修改")
				public Map OrgCustBaseMaint(DataObject party,DataObject item) throws Exception{
					
					/**
					 * 查询本地的对公客户基本信息
					 * 
					 */
					DataObject item1 = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmCorporation");
					item1.set("partyId", item.getString("partyId"));
					DatabaseUtil.expandEntity("default", item1);
					
					DataObject party1 = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
					party1.set("partyId", party.getString("partyId"));
					DatabaseUtil.expandEntity("default", party1);
					/**
					 * 调ECIF接口 S0110102000B201Response
					 */
					String ecifPartyNum=party1.getString("ecifPartyNum");
					OrgCustEcif orgEcif =new OrgCustEcifImpl();
					FMT_CRMS_SVR_S0110102000B201_IN resBody=new FMT_CRMS_SVR_S0110102000B201_IN();
					//ECIF客户编号 
					resBody.setECIF_CUST_NO(ecifPartyNum);
				String unifySocietyCreditNum=item.getString("unifySocietyCreditNum");
				String registrCd=item.getString("registrCd");
				String orgRegisterCd=item.getString("orgRegisterCd");
				String certType="";
				String certNum="";
				if(""!=unifySocietyCreditNum&&unifySocietyCreditNum!=null){
					certType="2h";
					certNum=unifySocietyCreditNum;
					//证件类型
				resBody.setCERT_TYPE(certType);
					//证件号码
				resBody.setCERT_NO(certNum);
			    //组织机构代码 
				resBody.setORG_CODE(item.getString("orgRegisterCd"));
				resBody.setPARTY_NAME(party.getString("partyName"));
				}else if (""!=orgRegisterCd&&orgRegisterCd!=null){
					certType="21";
					certNum=orgRegisterCd;
				}else{
					certType="22";
					certNum=registrCd;
				}
				   //客户类型
				    resBody.setCUST_TYPE("2");
					//证件类型
				//resBody.setCERT_TYPE(certType);
					//证件号码
				//	resBody.setCERT_NO(certNum);
					//客户名称 
				//	resBody.setPARTY_NAME(party.getString("partyName"));
					//别名 
				//	resBody.setCUST_ENNAME(item.getString("englishName"));
					/**
					 * 调用ECIF 公用地址信息
					 */
					//国家或地区 
/*					if( null == resBody.getCERT_ORG_NAT() || "".equals(resBody.getCERT_ORG_NAT())){
						item.set("contryRegionCd", resBody.getCERT_ORG_AREA());
					}*/
//					resBody.setCERT_ORG_AREA(item.getString("contryRegionCd"));
/*					item.set("contryRegionCd", resBody.getCERT_ORG_NAT());*/
						//证件到期日 
					String curdate=item.getString("legalCertificateEndDate");
					if(curdate!=null&&curdate.length()>10){
						
						curdate=curdate.substring(0, 10);
						curdate=curdate.replace("-", "");
					}
				//	resBody.setCERT_DUE_DATE(curdate);
						//登记注册类型 
					resBody.setREG_TYPE(item.getString("registrationType"));
//						//企业出资人经济成分 
//					item.set("economicSectorCode", item1.getString("economicSectorCode"));
//						//统一社会信用代码 
//					item.set("unifySocietyCreditNum", item1.getString("unifySocietyCreditNum"));
//						//注册登记号码 
//					item.set("registrCd", item1.getString("registrCd"));
						//注册登记日期 
					String registerDate=item.getString("registerDate");
					if(registerDate!=null&&registerDate.length()>10){
						
						registerDate=registerDate.substring(0, 10);
						registerDate=registerDate.replace("-", "");
					}
					resBody.setREG_DATE(registerDate);
						//注册登记证到期日 
					String registerEndDate=item.getString("registerEndDate");
					if(registerEndDate!=null&&registerEndDate.length()>10){
						
						registerEndDate=registerEndDate.substring(0, 10);
						registerEndDate=registerEndDate.replace("-", "");
					}
			//		resBody.setREG_CHECK_DATE(registerEndDate);
						//经营范围 
//					item.set("businessScope", item1.getString("businessScope"));
					resBody.setADMN_TYPE(item.getString("businessScope"));
						//注册资本币种 
					resBody.setREG_CPTL_CURR(item.getString("registerAssetsCurrencyCd"));
						//注册资本 
					resBody.setREG_CPTL(item.getString("registerAssets"));
					    //组织机构代码 
				//	resBody.setORG_CODE(item.getString("orgRegisterCd"));
						//组织机构代码证到期日 

					String orgRegisterEndDate=item.getString("orgRegisterEndDate");
					if(orgRegisterEndDate!=null&&orgRegisterEndDate.length()>10){
						
						orgRegisterEndDate=orgRegisterEndDate.substring(0, 10);
						orgRegisterEndDate=orgRegisterEndDate.replace("-", "");
					}
					resBody.setORG_CODE_DUE_DATE(orgRegisterEndDate);
						//中征码 
//					item.set("middelCode", item1.getString("middelCode"));
						//机构信用代码 
					resBody.setUNIT_CREDIT_CODE(item.getString("orgCreditCode"));
						//国税登记证号码 
					resBody.setTAX_REG_NO(item.getString("nationalTaxNo"));
//						//地税登记证号码 
					resBody.setTAX_AREA_NO(item.getString("governmentTentNo"));
//					//行业门类 
//					item.set("industrialTypeCd", item1.getString("industrialTypeCd"));
//					//行业大类 
//					item.set("industrialTypeBigCd", item1.getString("industrialTypeBigCd"));
//					//行业中类 
//					item.set("industrialTypeMidCd", item1.getString("industrialTypeMidCd"));
//					//行业小类 
//					item.set("industrialTypeSamllCd", item1.getString("industrialTypeSamllCd"));
//						    // 从业人数（人） 
//					item.set("employeesNumber", item1.getString("employeesNumber"));
//						    // 工信部企业规模 
//					item.set("enterpriseScaleGx", item1.getString("enterpriseScaleGx"));
//						   //  统计口径企业规模 
//					item.set("countBoreEnterScale", item1.getString("countBoreEnterScale"));
					//是否上市公司 
					resBody.setLISTED_FLAG(item.getString("listingCorporation"));
					//进出口权标志 
					resBody.setIMEX_MANA_IND(item.getString("whetherImpExp"));
					//家族企业标志 
//					item.set("familyEnterprise", item1.getString("familyEnterprise"));
					//融资平台标志 
//					item.set("whetherGovernmentFinanceCor", item1.getString("whetherGovernmentFinanceCor"));
//					//重点客户标志 
//					item.set("focusCustomer", item1.getString("focusCustomer"));
//					//是否从事房地产开发 
//					item.set("isRealEstateDev", item1.getString("isRealEstateDev"));
//					//农村企业标志 
//					item.set("countrysideEnterprise", item1.getString("countrysideEnterprise"));
					//是否我行关联方 
					resBody.setREL_UNIT_FLAG(item.getString("REL_UNIT_FLAG"));
					//我行股东标志 
					resBody.setSHAREHOLDER_FLAG(item.getString("stockholderOfBank"));
//					//联保小组标志 
//					item.set("jointGuarantee", item1.getString("jointGuarantee"));
//					//是否统一授信成员 
//					item.set("isGroupCust", item1.getString("isGroupCust"));
//					//所属统一授信客户名称 
//					item.set("attachGroupName", item1.getString("attachGroupName"));
//					//黑名单标志 
//					item.set("whetherBlackList", item1.getString("whetherBlackList"));
//					//关停企业标志 
//					item.set("stopCorpInd", item1.getString("stopCorpInd"));
//					//与我行建立信贷关系日期 
//					item.set("creditRelationshipTime", item1.getString("creditRelationshipTime"));
					S0110102000B201Response	responseB201=orgEcif.COrgCustBaseMaint(resBody);
					String msgg=responseB201.getResTranHeader().getHRetMsg();
					String msg=responseB201.getResTranHeader().getHRetCode();
					Map map = new HashMap();
			
						map.put("msgg", msgg);
						map.put("msg", msg);
					return map;
		 }
				/**
				 * @author
				 * @date 2017-07-03 19:44:12
				 *同业客户修改
				 */
						@Bizlet("同业客户修改")
						public Map SameOrgCustBaseMaint(DataObject item) throws Exception{

							/**
							 * 调ECIF接口 S0110102000B201Response
							 */
							String ecifPartyNum=item.getString("ecifPartyNum");
							OrgCustEcif orgEcif =new OrgCustEcifImpl();
							FMT_CRMS_SVR_S0110102000B201_IN resBody=new FMT_CRMS_SVR_S0110102000B201_IN();
							//ECIF客户编号 
							resBody.setECIF_CUST_NO(ecifPartyNum);
						String unifySocietyCreditNum=item.getString("unifySocietyCreditNum");
						String registrCd=item.getString("registrCd");
						String orgRegisterCd=item.getString("orgRegisterCd");
						String certType="";
						String certNum="";
						if(""!=unifySocietyCreditNum&&unifySocietyCreditNum!=null){
							certType="2h";
							certNum=unifySocietyCreditNum;
							//证件类型
							resBody.setCERT_TYPE(certType);
								//证件号码
							resBody.setCERT_NO(certNum);
						    //组织机构代码 
							resBody.setORG_CODE(item.getString("orgRegisterCd"));
							resBody.setPARTY_NAME(item.getString("partyName"));
						}else if (""!=orgRegisterCd&&orgRegisterCd!=null){
							certType="21";
							certNum=orgRegisterCd;
						}else{
							certType="22";
							certNum=registrCd;
						}
						   //客户类型
						    resBody.setCUST_TYPE("3");
							//证件类型
						//	resBody.setCERT_TYPE(certType);
							
							//证件号码
						//	resBody.setCERT_NO(certNum);
							//客户名称 
						//	resBody.setPARTY_NAME(item.getString("partyName"));
							//别名 
							resBody.setCUST_ENNAME(item.getString("englishName"));
				

								//注册登记日期 
							String registerDate=item.getString("registerDate");
							if(registerDate!=null&&registerDate.length()>10){
								
								registerDate=registerDate.substring(0, 10);
								registerDate=registerDate.replace("-", "");
							}
							resBody.setREG_DATE(registerDate);
								//注册登记证到期日 
							String registerEndDate=item.getString("registerEndDate");
							if(registerEndDate!=null&&registerEndDate.length()>10){
								
								registerEndDate=registerEndDate.substring(0, 10);
								registerEndDate=registerEndDate.replace("-", "");
							}
							resBody.setREG_CHECK_DATE(registerEndDate);
							//金融机构类型
							resBody.setFIN_ORG_TYPE(item.getString("financeEnterpriseType"));
							//金融许可证机构编码
							resBody.setFIN_LIC_NO(item.getString("financialPermitNum"));
							//swift 码
							resBody.setSWIFT_NO(item.getString("swiftBicNum"));
							//统一社会信用代码 
						//	resBody.setUNIT_CREDIT_CODE(item.getString("unifySocietyCreditNum"));

								//注册登记号码 
						//	resBody.setGOVN_CERT_NO(item.getString("registrCd"));
							
								//注册资本币种 
							resBody.setREG_CPTL_CURR(item.getString("registerAssetsCurrencyCd"));
								//注册资本 
							resBody.setREG_CPTL(item.getString("registerAssets"));
							    //组织机构代码 
						//	resBody.setORG_CODE(item.getString("orgRegisterCd"));
							S0110102000B201Response	responseB201=orgEcif.COrgCustBaseMaint(resBody);
							String msgg=responseB201.getResTranHeader().getHRetMsg();
							String msg=responseB201.getResTranHeader().getHRetCode();
//							item.set("addressValue", item.getString("jyAddress"));
							CCustAddrInfoMaint(item,item);
							Map map = new HashMap();
					
								map.put("msgg", msgg);
								map.put("msg", msg);
							return map;
				 }
						
						/**
						 * @author
						 * @date 2017-07-03 19:44:12
						 *合作中介客户修改
						 */
								@Bizlet("合作中介客户修改")
								public Map ThirdOrgCustBaseMaint(DataObject party,DataObject thirdParty) throws Exception{

									/**
									 * 调ECIF接口 S0110102000B201Response
									 */
									String ecifPartyNum=party.getString("ecifPartyNum");
									OrgCustEcif orgEcif =new OrgCustEcifImpl();
									FMT_CRMS_SVR_S0110102000B201_IN resBody=new FMT_CRMS_SVR_S0110102000B201_IN();
									//ECIF客户编号 
									resBody.setECIF_CUST_NO(ecifPartyNum);
								String unifySocietyCreditNum=thirdParty.getString("unifySocietyCreditNum");
								String registrCd=thirdParty.getString("registrCd");
								String orgRegisterCd=thirdParty.getString("orgRegisterCd");
								String certType="";
								String certNum="";
								if(""!=unifySocietyCreditNum&&unifySocietyCreditNum!=null){
									certType="2h";
									certNum=unifySocietyCreditNum;
									//证件类型
									resBody.setCERT_TYPE(certType);
										//证件号码
									resBody.setCERT_NO(certNum);
								    //组织机构代码 
									resBody.setORG_CODE(thirdParty.getString("orgRegisterCd"));
									resBody.setPARTY_NAME(party.getString("partyName"));
								}else if (""!=orgRegisterCd&&orgRegisterCd!=null){
									certType="21";
									certNum=orgRegisterCd;
								}else{
									certType="22";
									certNum=registrCd;
								}
								   //客户类型
								    resBody.setCUST_TYPE("2");//2-对公、3-同业
									//证件类型
								//	resBody.setCERT_TYPE(certType);
									//证件号码
								//	resBody.setCERT_NO(certNum);
									//客户名称 
								//	resBody.setPARTY_NAME(party.getString("partyName"));
									
									//别名 
									resBody.setCUST_ENNAME(thirdParty.getString("englishName"));
									//证件到期日 
									String legalCertificateEndDate=thirdParty.getString("legalCertificateEndDate");
									if(legalCertificateEndDate!=null&&legalCertificateEndDate.length()>10){
										
										legalCertificateEndDate=legalCertificateEndDate.substring(0, 10);
										legalCertificateEndDate=legalCertificateEndDate.replace("-", "");
										resBody.setCERT_DUE_DATE(legalCertificateEndDate);
									}
									


										//注册登记号码 
								//	resBody.setGOVN_CERT_NO(thirdParty.getString("registrCd"));
										//注册登记日期 
							
										String registerDate=thirdParty.getString("registerDate");
										if(registerDate!=null&&registerDate.length()>10){
											
											registerDate=registerDate.substring(0, 10);
											registerDate=registerDate.replace("-", "");
										}
										resBody.setREG_DATE(registerDate);
										//注册登记证到期日 
										String registerEndDate=thirdParty.getString("registerEndDate");
										if(registerEndDate!=null&&registerEndDate.length()>10){
											
											registerEndDate=registerEndDate.substring(0, 10);
											registerEndDate=registerEndDate.replace("-", "");
										}
									//	resBody.setREG_CHECK_DATE(registerEndDate);
					
												//经营范围 
										     resBody.setADMN_TYPE(thirdParty.getString("businessScope"));
												//统一社会信用代码 
										     resBody.setUNIT_CREDIT_CODE(thirdParty.getString("unifySocietyCreditNum"));
										//注册资本币种 
										     resBody.setREG_CPTL_CURR(thirdParty.getString("registerAssetsCurrencyCd"));
										//注册资本 
										     resBody.setREG_CPTL(thirdParty.getString("registerAssets"));
										
									    //组织机构代码 
									//	     resBody.setORG_CODE(thirdParty.getString("orgRegisterCd"));
												//组织机构代码证到期日 
												String orgRegisterEndDate=thirdParty.getString("orgRegisterEndDate");
												if(orgRegisterEndDate!=null&&orgRegisterEndDate.length()>10){
													
													orgRegisterEndDate=orgRegisterEndDate.substring(0, 10);
													orgRegisterEndDate=orgRegisterEndDate.replace("-", "");
												}
												resBody.setREG_DATE(registerDate);
											 
													//机构信用代码 
												 resBody.setUNIT_CREDIT_CODE(thirdParty.getString("orgCreditCode"));
												//国税登记证号码
											 resBody.setTAX_REG_NO(thirdParty.getString("nationalTaxNo"));
												//地税登记证号码 
											 resBody.setTAX_AREA_NO(thirdParty.getString("governmentTentNo"));
												//经营许可证： 
											 resBody.setBUSI_LIC_NO(thirdParty.getString("businessCd"));
												//证件到期日 
												String businessEndDate=thirdParty.getString("businessEndDate");
												if(businessEndDate!=null&&businessEndDate.length()>10){
													
													businessEndDate=businessEndDate.substring(0, 10);
													businessEndDate=businessEndDate.replace("-", "");
												}
												resBody.setREG_DATE(businessEndDate);
												S0110102000B201Response	responseB201=orgEcif.COrgCustBaseMaint(resBody);
												String msgg=responseB201.getResTranHeader().getHRetMsg();
												String msg=responseB201.getResTranHeader().getHRetCode();
											 
							        CCustAddrInfo(ecifPartyNum,"103","","",thirdParty);//联系地址
							        CCustAddrInfo(ecifPartyNum,"","103","",thirdParty);//联系人电话

									CCustAddrInfoMaint(party,thirdParty);
									Map map = new HashMap();
							
										map.put("msgg", msgg);
										map.put("msg", msg);
									return map;
						 }
				
						/*
						 * 对公客户基本地址信息
						 */
						@Bizlet("对公客户基本地址信息 修改")
						public Map OrgCustAdderUpdate(DataObject item1) throws Exception{
							/**
							 * 查询本地的对公客户基本信息
							 * 
							 */
							String partyId=item1.getString("partyId");
							DataObject	item = 	EntityUtil.searchEntity("com.bos.dataset.csm.TbCsmAttachedInfo", "partyId",partyId);						
							DataObject party = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
							party.set("partyId", partyId);
							DatabaseUtil.expandEntity("default", party);
							/**
							 * 调ECIF接口
							 */
							String ecifPartyNum=party.getString("ecifPartyNum");
							Map map = new HashMap();
							map=CCustAddrInfoMaint(party,item);
							return map;
						}	
						@Bizlet(value = "集团客户维护")
						public Map COrgGrpCustBaseMaintgroupDpdate(DataObject party,DataObject TbCsmGroupCompany,String memberNum) throws Exception {
							
							DataObject party1 = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
							party1.set("partyId", TbCsmGroupCompany.getString("partyId"));
							DatabaseUtil.expandEntity("default", party1);
							
							DataObject csmGroupCompany1= DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmGroupCompany");
							csmGroupCompany1.set("partyId", TbCsmGroupCompany.getString("partyId"));
							DatabaseUtil.expandEntity("default", csmGroupCompany1);
							String userId=GitUtil.getCurrentUserId();
							String omEmployeeName="";
							
							DataObject	omEmployee = 	EntityUtil.searchEntity("com.bos.utp.dataset.organization.OmEmployee", "userid", userId);
						
							omEmployeeName=omEmployee.getString("empname");
							IcustEcif orgEcif =new CustEcifImpl();
							FMT_CRMS_SVR_S0110102000B221_IN requestB221=new FMT_CRMS_SVR_S0110102000B221_IN();
							requestB221.setGROUP_CUST_NO(party1.getString("ecifPartyNum"));
	/*						requestB221.setCUST_MANAGER_NO(userId);
							requestB221.setCUST_MNG_NAME(omEmployeeName);*/
							requestB221.setGROUP_TYPE("511");//群体客户编号
							requestB221.setGROUP_NAME(party.getString("partyName"));
							requestB221.setUNION_MEM_COUNT(memberNum);
							S0110102000B221Response	responseB221=orgEcif.OrgGrpCustBaseMaint(requestB221);
							FMT_CRMS_SVR_S0110102000B221_OUT resBody = responseB221.getResponseBody();
							String msgg=responseB221.getResTranHeader().getHRetMsg();
							String msg=responseB221.getResTranHeader().getHRetCode();
							Map map = new HashMap();
				            map.put("msgg", msgg);
							map.put("msg", msg);
							return map;
						}
						@Bizlet(value = "集团客户维护意见")
						public Map COrgGrpCustBaseMaintgroupDpdateYj(String partyId,String groupType) throws Exception {
							Map map = new HashMap();
							DataObject party1 = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
							party1.set("partyId", partyId);
							DatabaseUtil.expandEntity("default", party1);
							if(party1.getString("ecifPartyNum")!=null&&!"".equals(party1.getString("ecifPartyNum"))){
						         map.put("msgg", "");
									map.put("msg", "AAAAAAA");
									return map;
							}
							DataObject csmGroupCompany= DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmGroupCompany");
							csmGroupCompany.set("partyId", partyId);
							DatabaseUtil.expandEntity("default", csmGroupCompany);
							DataObject csmGuarGroup= DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmGuarGroup");
							csmGuarGroup.set("partyId", partyId);
							DatabaseUtil.expandEntity("default", csmGuarGroup);
							
							String status="";
							if("511".equals(groupType)){//511集团客户  
								if(csmGroupCompany!=null&&!"".equals(csmGroupCompany)){
									status=csmGroupCompany.getString("status");
								}
								
							}else{
								if(csmGuarGroup!=null&&!"".equals(csmGuarGroup)){
									status=csmGuarGroup.getString("jointGuaranteeStatus");
								}
								
							}
							if(!"01".equals(status)){
					                        map.put("msgg", "");
											map.put("msg", "AAAAAAA");
											return map;
							}else{
							
							String ecifPartyNum="";
							DataObject smGuarMemeber= DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmGuarMemeber");
							smGuarMemeber.set("partyId", partyId);
							DataObject[] smGuarMemebers=DatabaseUtil.queryEntitiesByTemplate("default", smGuarMemeber);
							
							DataObject smGroupMember= DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmGroupMember");
							smGroupMember.set("groupPartyId", partyId);
							DataObject[] smGroupMembers=DatabaseUtil.queryEntitiesByTemplate("default", smGroupMember);
							String userId=GitUtil.getCurrentUserId();
							String omEmployeeName="";
							
							DataObject	omEmployee = 	EntityUtil.searchEntity("com.bos.utp.dataset.organization.OmEmployee", "userid", userId);
						     String memberNum="0";
						     if(smGuarMemebers!=null&&!"".equals(smGuarMemebers)&&"512".equals(groupType)){
						    	 memberNum=String.valueOf(smGuarMemebers.length);
						    	 if(smGuarMemebers.length>0){
						    		 String partyId1=smGuarMemebers[0].getString("relatedCustPartyId");
										DataObject party1u = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
										party1u.set("partyId", partyId1);
										DatabaseUtil.expandEntity("default", party1u);
										ecifPartyNum=party1u.getString("ecifPartyNum");
						    	 }
						     }
						     if(smGroupMembers!=null&&!"".equals(smGroupMembers)&&"511".equals(groupType)){
						    	 memberNum=String.valueOf(smGroupMembers.length);
						       	 if(smGroupMembers.length>0){
						       		 for(int i=0;i<smGroupMembers.length;i++){
						       			String memberTypeCd=smGroupMembers[i].getString("memberTypeCd");
											if("01".equals(memberTypeCd)){
									    		 String partyId1=smGroupMembers[0].getString("corporationPartyId");
									    			DataObject party1u = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
													party1u.set("partyId", partyId1);
													DatabaseUtil.expandEntity("default", party1u);
											        ecifPartyNum=party1u.getString("ecifPartyNum");
											}
						       			 
						       		 }
						    	 }
						     }
		                    
						     if(memberNum.equals("0")){
						            map.put("msgg", "请添加成员信息");
									map.put("msg", "");
									return map;
						     }
						     if(("".equals(ecifPartyNum)||ecifPartyNum==null)&&"511".equals(groupType)){
						            map.put("msgg", "请添加母公司信息");
									map.put("msg", "");
									return map;
						     }
							omEmployeeName=omEmployee.getString("empname");
							IcustEcif orgEcif =new CustEcifImpl();
							FMT_CRMS_SVR_S0110102000B221_IN requestB221=new FMT_CRMS_SVR_S0110102000B221_IN();
							requestB221.setGROUP_CUST_NO(party1.getString("ecifPartyNum"));
	/*						requestB221.setCUST_MANAGER_NO(userId);
							requestB221.setCUST_MNG_NAME(omEmployeeName);*/
							requestB221.setGROUP_TYPE(groupType);//群体客户编号
							if("512".equals(groupType)){
								requestB221.setRELATION_TYPE("5120");
							}else{
								requestB221.setRELATION_TYPE("5111");
							}
						
							requestB221.setGROUP_NAME(party1.getString("partyName"));
							requestB221.setUNION_MEM_COUNT(memberNum);
							requestB221.setECIF_CUST_NO(ecifPartyNum);
							S0110102000B221Response	responseB221=orgEcif.OrgGrpCustBaseMaint(requestB221);
							FMT_CRMS_SVR_S0110102000B221_OUT resBody = responseB221.getResponseBody();
							String msgg=responseB221.getResTranHeader().getHRetMsg();
							String msg=responseB221.getResTranHeader().getHRetCode();
							if("AAAAAAA".equals(msg)){
								party1.set("ecifPartyNum", resBody.getGROUP_CUST_NO());
								DatabaseUtil.saveEntity("default", party1);
							}
				            map.put("msgg", msgg);
							map.put("msg", msg);
							return map;
							}
						}
						@Bizlet(value = "集团客户维护只维护名称")
						public Map COrgGrpCustBaseMaintgroupDpdate1(DataObject party) throws Exception {
							DataObject party1 = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
							party1.set("partyId", party.getString("partyId"));
							DatabaseUtil.expandEntity("default", party1);
							IcustEcif orgEcif =new CustEcifImpl();
							FMT_CRMS_SVR_S0110102000B221_IN requestB221=new FMT_CRMS_SVR_S0110102000B221_IN();
							requestB221.setGROUP_CUST_NO(party1.getString("ecifPartyNum"));
							requestB221.setGROUP_TYPE("511");//群体客户编号
							requestB221.setGROUP_NAME(party1.getString("partyName"));
							S0110102000B221Response	responseB221=orgEcif.OrgGrpCustBaseMaint(requestB221);
							FMT_CRMS_SVR_S0110102000B221_OUT resBody = responseB221.getResponseBody();
							String msgg=responseB221.getResTranHeader().getHRetMsg();
							String msg=responseB221.getResTranHeader().getHRetCode();
							Map map = new HashMap();
				            map.put("msgg", msgg);
							map.put("msg", msg);
							return map;
						}
						@Bizlet(value = "联保客户维护")
						public Map GuarCustBaseMaintUpdate(DataObject party,DataObject TbCsmGroupCompany,String memberNum) throws Exception {
							
							DataObject party1 = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
							party1.set("partyId", TbCsmGroupCompany.getString("partyId"));
							System.out.println(party1);
							DatabaseUtil.expandEntity("default", party1);
							
							
							DataObject csmGuarGroup1= DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmGuarGroup");
							csmGuarGroup1.set("partyId", TbCsmGroupCompany.getString("partyId"));
							DatabaseUtil.expandEntity("default", csmGuarGroup1);
							String userId=GitUtil.getCurrentUserId();
							String omEmployeeName="";
							
							DataObject	omEmployee = 	EntityUtil.searchEntity("com.bos.utp.dataset.organization.OmEmployee", "userid", userId);
							
							omEmployeeName=omEmployee.getString("empname");
							IcustEcif orgEcif =new CustEcifImpl();
							FMT_CRMS_SVR_S0110102000B221_IN requestB221=new FMT_CRMS_SVR_S0110102000B221_IN();
							requestB221.setGROUP_CUST_NO(party1.getString("ecifPartyNum"));
/*							requestB221.setCUST_MANAGER_NO(userId);
							requestB221.setCUST_MNG_NAME(omEmployeeName);*/
							requestB221.setGROUP_TYPE("512");//联保
							requestB221.setGROUP_NAME(party1.getString("partyName"));
							requestB221.setUNION_MEM_COUNT(memberNum);
							S0110102000B221Response	responseB221=orgEcif.OrgGrpCustBaseMaint(requestB221);
							FMT_CRMS_SVR_S0110102000B221_OUT resBody = responseB221.getResponseBody();
							String msgg=responseB221.getResTranHeader().getHRetMsg();
							String msg=responseB221.getResTranHeader().getHRetCode();
							Map map = new HashMap();
				            map.put("msgg", msgg);
							map.put("msg", msg);
							return map;
						}
						@Bizlet(value = "联保客户解散，群体客户全行级注销")
						public Map OrgGrpCustLogout(DataObject party,DataObject TbCsmGroupCompany,String memberNum) throws Exception {
							
							DataObject party1 = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
							party1.set("partyId", TbCsmGroupCompany.getString("partyId"));
							System.out.println(party1);
							DatabaseUtil.expandEntity("default", party1);
							IcustEcif orgEcif =new CustEcifImpl();
							FMT_CRMS_SVR_S0110102000B224_IN requestB224=new FMT_CRMS_SVR_S0110102000B224_IN();
							requestB224.setGROUP_CUST_NO(party1.getString("ecifPartyNum"));
							S0110102000B224Response	responseB224=orgEcif.OrgGrpCustLogout(requestB224);
							FMT_CRMS_SVR_S0110102000B224_OUT resBody = responseB224.getResponseBody();
							String msgg=responseB224.getResTranHeader().getHRetMsg();
							String msg=responseB224.getResTranHeader().getHRetCode();
							Map map = new HashMap();
				            map.put("msgg", msgg);
							map.put("msg", msg);
							return map;
						}
						@Bizlet(value = "联保客户解散，群体客户全行级注销")
						public Map OrgGrpCustLogoutYj(String partyId,String groupType) throws Exception {
								DataObject csmGroupCompany= DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmGroupCompany");
							csmGroupCompany.set("partyId", partyId);
							DatabaseUtil.expandEntity("default", csmGroupCompany);
							DataObject csmGuarGroup= DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmGuarGroup");
							csmGuarGroup.set("partyId", partyId);
							DatabaseUtil.expandEntity("default", csmGuarGroup);
							DataObject party1 = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
							party1.set("partyId", partyId);
							DatabaseUtil.expandEntity("default", party1);
							IcustEcif orgEcif =new CustEcifImpl();
							FMT_CRMS_SVR_S0110102000B224_IN requestB224=new FMT_CRMS_SVR_S0110102000B224_IN();
							Map map = new HashMap();
							String status="";
							if("511".equals(groupType)){//511集团客户  
								if(csmGroupCompany!=null&&!"".equals(csmGroupCompany)){
									status=csmGroupCompany.getString("status");
								}
								
							}else{
								if(csmGuarGroup!=null&&!"".equals(csmGuarGroup)){
									status=csmGuarGroup.getString("jointGuaranteeStatus");
								}
								
							}
						String	ecifPartyNum=party1.getString("ecifPartyNum");
							//party1.getString("ecifPartyNum")如果为空表示还没与ecif同步
						if(ecifPartyNum==null||"".equals(ecifPartyNum)){
							if("511".equals(groupType)){//511集团客户  
								if(csmGroupCompany!=null&&!"".equals(csmGroupCompany)){
									csmGroupCompany.set("status", "04");
									DatabaseUtil.saveEntity("default", csmGroupCompany);
									
								}
								
							}else{
								if(csmGuarGroup!=null&&!"".equals(csmGuarGroup)){
									csmGuarGroup.set("jointGuaranteeStatus","04");
									DatabaseUtil.saveEntity("default", csmGuarGroup);
								}
								
							}
							map.put("msg", "AAAAAAA");
							map.put("msgg", "成功");
							return map;
						}else{
							if(!"04".equals(status)){
								requestB224.setGROUP_CUST_NO(ecifPartyNum);
								S0110102000B224Response	responseB224=orgEcif.OrgGrpCustLogout(requestB224);
								FMT_CRMS_SVR_S0110102000B224_OUT resBody = responseB224.getResponseBody();
								String msgg=responseB224.getResTranHeader().getHRetMsg();
								String msg=responseB224.getResTranHeader().getHRetCode();
								if("AAAAAAA".equals(msg)){
								if("511".equals(groupType)){//511集团客户  
									if(csmGroupCompany!=null&&!"".equals(csmGroupCompany)){
										csmGroupCompany.set("status", "04");
										DatabaseUtil.saveEntity("default", csmGroupCompany);
										
									}
									
								}else{
									if(csmGuarGroup!=null&&!"".equals(csmGuarGroup)){
										csmGuarGroup.set("jointGuaranteeStatus","04");
										DatabaseUtil.saveEntity("default", csmGuarGroup);
									}
									
								}
								}
					            map.put("msgg", msgg);
								map.put("msg", msg);
								return map;
							}else{
								map.put("msg", "AAAAAAA");
								map.put("msgg", "成功");
								return map;
							}
						}
						
						}
						
						@Bizlet(value = "群体客户成员关系创建")
						public Map COrgGrpMemRelMaint(DataObject csmGuarGroup,String groupType) throws Exception {
							DataObject party1 = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
							DataObject party2 = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
							if("511".equals(groupType)){
								party1.set("partyId", csmGuarGroup.getString("groupPartyId"));
								DatabaseUtil.expandEntity("default", party1);

								party2.set("partyId", csmGuarGroup.getString("corporationPartyId"));
								DatabaseUtil.expandEntity("default", party2);
							}else{
								party1.set("partyId", csmGuarGroup.getString("partyId"));
								DatabaseUtil.expandEntity("default", party1);

								party2.set("partyId", csmGuarGroup.getString("relatedCustPartyId"));
								DatabaseUtil.expandEntity("default", party2);
							}

							IcustEcif orgEcif =new CustEcifImpl();
							FMT_CRMS_SVR_S0110102000B222_IN requestB222=new FMT_CRMS_SVR_S0110102000B222_IN();
							FMT_CRMS_SVR_S0110102000B222_IN_SUB[] ress=new FMT_CRMS_SVR_S0110102000B222_IN_SUB[1];
							FMT_CRMS_SVR_S0110102000B222_IN_SUB res=new FMT_CRMS_SVR_S0110102000B222_IN_SUB();
							requestB222.setGROUP_CUST_NO(party1.getString("ecifPartyNum"));
							requestB222.setGROUP_TYPE(groupType);//联保
							res.setECIF_CUST_NO(party2.getString("ecifPartyNum"));
							if("511".equals(groupType)){
							res.setRELATION_TYPE(csmGuarGroup.getString("groupRelTypeCd"));
							}else{
							res.setRELATION_TYPE("5120");	
							}
							ress[0]=res;
							requestB222.setGRP_MEM_REL(ress);
							S0110102000B222Response	responseB222=orgEcif.OrgGrpMemRelMaint(requestB222);
							FMT_CRMS_SVR_S0110102000B222_OUT resBody = responseB222.getResponseBody();
							String msgg=responseB222.getResTranHeader().getHRetMsg();
							String msg=responseB222.getResTranHeader().getHRetCode();
							Map map = new HashMap();
				            map.put("msgg", msgg);
							map.put("msg", msg);
							return map;
						}
						@Bizlet(value = "群体客户成员关系创建")
						public Map COrgGrpMemRelMaintYj(String partyId,String groupType) throws Exception {
							DataObject party1 = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
							party1.set("partyId", partyId);
							DatabaseUtil.expandEntity("default", party1);
							Map map = new HashMap();
							DataObject smGuarMemeber= DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmGuarMemeber");
							smGuarMemeber.set("partyId", partyId);
							DataObject[] smGuarMemebers=DatabaseUtil.queryEntitiesByTemplate("default", smGuarMemeber);
							
							DataObject smGroupMember= DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmGroupMember");
							smGroupMember.set("groupPartyId", partyId);
							DataObject[] smGroupMembers=DatabaseUtil.queryEntitiesByTemplate("default", smGroupMember);

							IcustEcif orgEcif =new CustEcifImpl();
							FMT_CRMS_SVR_S0110102000B222_IN requestB222=new FMT_CRMS_SVR_S0110102000B222_IN();
							requestB222.setGROUP_CUST_NO(party1.getString("ecifPartyNum"));
							requestB222.setGROUP_TYPE(groupType);//联保
					        int lengthgr=0;
					        int lengthgu=0;
							if(smGroupMembers!=null&&!"".equals(smGroupMembers)&&"511".equals(groupType)){
								lengthgr=smGroupMembers.length;
							}	
							if(smGuarMemebers!=null&&!"".equals(smGuarMemebers)&&"512".equals(groupType)){
								lengthgu=smGuarMemebers.length;	
							}
							
							if(lengthgr>0){
							FMT_CRMS_SVR_S0110102000B222_IN_SUB[] ress=new FMT_CRMS_SVR_S0110102000B222_IN_SUB[lengthgr];
							for(int i=0;i<lengthgr;i++){
								FMT_CRMS_SVR_S0110102000B222_IN_SUB res=new FMT_CRMS_SVR_S0110102000B222_IN_SUB();
								DataObject party2 = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
								party2.set("partyId", smGroupMembers[i].getString("corporationPartyId"));
								DatabaseUtil.expandEntity("default", party2);
								res.setECIF_CUST_NO(party2.getString("ecifPartyNum"));
								String memberTypeCd=smGroupMembers[i].getString("memberTypeCd");
			                       if(!"01".equals(memberTypeCd)){
		/*	                    	   if("02".equals(memberTypeCd)){
			                    	   memberTypeCd="5112";//集团子公司
			                    	   }*/
			                      		res.setRELATION_TYPE("5112");
										ress[i]=res;
			                       }
		             
							}
							requestB222.setGRP_MEM_REL(ress);
							}
							if(lengthgu>0){
							FMT_CRMS_SVR_S0110102000B222_IN_SUB[] ress=new FMT_CRMS_SVR_S0110102000B222_IN_SUB[lengthgu];
							for(int i=0;i<lengthgu;i++){
								FMT_CRMS_SVR_S0110102000B222_IN_SUB res=new FMT_CRMS_SVR_S0110102000B222_IN_SUB();
								DataObject party2 = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
								party2.set("partyId", smGuarMemebers[i].getString("relatedCustPartyId"));
								DatabaseUtil.expandEntity("default", party2);
								res.setECIF_CUST_NO(party2.getString("ecifPartyNum"));
								res.setRELATION_TYPE("5120");
								ress[i]=res;
							}
							requestB222.setGRP_MEM_REL(ress);
							}
						
							
							S0110102000B222Response	responseB222=orgEcif.OrgGrpMemRelMaint(requestB222);
							FMT_CRMS_SVR_S0110102000B222_OUT resBody = responseB222.getResponseBody();
							String msgg=responseB222.getResTranHeader().getHRetMsg();
							String msg=responseB222.getResTranHeader().getHRetCode();
				            map.put("msgg", msgg);
							map.put("msg", msg);
							return map;
						}
						@Bizlet(value = "群体客户成员关系删除")
						public Map OrgGrpMemRelDel(DataObject csmGuarGroup,String groupType) throws Exception {
							DataObject csmGuarGroup11 = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmGroupMember");
							DataObject csmGuarGroup12 = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmGuarMemeber");
							DataObject party1 = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
							DataObject party2 = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
							if("511".equals(groupType)){
								String groupMemberId=csmGuarGroup.getString("groupMemberId");
								csmGuarGroup11.set("groupMemberId", groupMemberId);
								DatabaseUtil.expandEntity("default", csmGuarGroup11);
								party1.set("partyId", csmGuarGroup11.getString("groupPartyId"));
								DatabaseUtil.expandEntity("default", party1);
								party2.set("partyId", csmGuarGroup11.getString("corporationPartyId"));
								DatabaseUtil.expandEntity("default", party2);
							}else{
								String id=csmGuarGroup.getString("id");
								csmGuarGroup12.set("id", id);
								DatabaseUtil.expandEntity("default", csmGuarGroup12);
								party1.set("partyId", csmGuarGroup12.getString("partyId"));
								DatabaseUtil.expandEntity("default", party1);
								party2.set("partyId", csmGuarGroup12.getString("relatedCustPartyId"));
								DatabaseUtil.expandEntity("default", party2);
							}
							IcustEcif orgEcif =new CustEcifImpl();
							FMT_CRMS_SVR_S0110102000B223_IN requestB223=new FMT_CRMS_SVR_S0110102000B223_IN();
							requestB223.setGROUP_CUST_NO(party1.getString("ecifPartyNum"));
							requestB223.setECIF_CUST_NO(party2.getString("ecifPartyNum"));
							S0110102000B223Response	responseB223=orgEcif.OrgGrpMemRelDel(requestB223);
							FMT_CRMS_SVR_S0110102000B223_OUT resBody = responseB223.getResponseBody();
							String msgg=responseB223.getResTranHeader().getHRetMsg();
							String msg=responseB223.getResTranHeader().getHRetCode();
							Map map = new HashMap();
				            map.put("msgg", msgg);
							map.put("msg", msg);
							return map;
						}
			/**===================================================客服信息修改结束=======================================
						 */
		@Bizlet("对公客户获取ECIF客户号ecifPartyNum")
		public Map GetecifPartyNum(DataObject corp,String partyName,String custType) throws Exception{
			
			/**
			 * 调ECIF接口
			 */
			String certType="";
			String certSubType="";
			String certNum="";
			String unifySocietyCreditNum=corp.getString("unifySocietyCreditNum");//统一社会信用代码
			String registrCd=corp.getString("registrCd");//注册登记号码  营业执照 corpCustomerTypeCd
			if("".equals(registrCd)){
			 registrCd=corp.getString("registerCode");//注册登记号码  营业执照 corpCustomerTypeCd
			}
			String orgRegisterCd=corp.getString("orgRegisterCd");//组织机构代码
			if(""!=unifySocietyCreditNum&&unifySocietyCreditNum!=null){
				certType="2h";
				certNum=unifySocietyCreditNum;
			}else if (""!=orgRegisterCd&&orgRegisterCd!=null){
				certType="21";
				certNum=orgRegisterCd;
			}else{
				certType="22";
				certNum=registrCd;
				if(!"".equals(registrCd)&&null!=registrCd){
					certSubType="221";
					String corpCustomerTypeCd=corp.getString("corpCustomerTypeCd");
					if(!"".equals(corpCustomerTypeCd)&&null!=corpCustomerTypeCd){
						if("1".equals(corpCustomerTypeCd)){
							certSubType="221";
						}else if("2".equals(corpCustomerTypeCd)){
							certSubType="222";
						}else if("3".equals(corpCustomerTypeCd)){
							certSubType="223";	
						}else{
							certSubType="221";
						}
					}
				}
			}
			OrgCustEcif orgEcif =new OrgCustEcifImpl();
			FMT_CRMS_SVR_S0110102000B201_IN requestB201=new FMT_CRMS_SVR_S0110102000B201_IN();
			requestB201.setCERT_TYPE(certType);
			requestB201.setCERT_SUB_TYPE(certSubType);
			requestB201.setPARTY_NAME(partyName);
			requestB201.setCERT_NO(certNum);
			requestB201.setCUST_TYPE(custType);//2对公 3合作
			requestB201.setGOVN_CERT_NO(registrCd);//营业执照号码  我们系统叫注册登记码
			requestB201.setORG_CODE(orgRegisterCd);//组织机构代码
			S0110102000B201Response	responseB201=orgEcif.COrgCustBaseMaint(requestB201);
			FMT_CRMS_SVR_S0110102000B201_OUT  resBody = responseB201.getResponseBody();
			String ecifPartyNum=responseB201.getResponseBody().getECIF_CUST_NO();
			String msg=responseB201.getResTranHeader().getHRetCode();
			String msgg=responseB201.getResTranHeader().getHRetMsg();
/*			OrgCustEcif orgEcif =new OrgCustEcifImpl();
			FMT_CRMS_SVR_S0110101000A202_IN requestA202=new FMT_CRMS_SVR_S0110101000A202_IN();
			requestA202.setRESOLVE_TYPE("2");
			requestA202.setCERT_TYPE(certType);
			requestA202.setCERT_NO(certNum);
			S0110101000A202Response	responseA202=orgEcif.COrgCustBaseQuery(requestA202);
			String ecifPartyNum=responseA202.getResponseBody().getECIF_CUST_NO();
			String msg=responseA202.getResTranHeader().getHRetCode();
			String msgg=responseA202.getResTranHeader().getHRetMsg();*/
			Map map = new HashMap();
			map.put("ecifPartyNum", ecifPartyNum);
			map.put("msg", msg);
			map.put("msgg", msgg);
			return map;
 }
		
		@Bizlet(value = "维护ECIF地址信息")
		public Map CCustAddrInfoMaint(DataObject patry,DataObject item) throws Exception {
			IcustEcif custEcif=new  CustEcifImpl();
			FMT_CRMS_SVR_S0110102000B011_IN requestB011=new FMT_CRMS_SVR_S0110102000B011_IN();
			requestB011.setECIF_CUST_NO(patry.getString("ecifPartyNum"));//ECIF客户编号
			requestB011.setRESOLVE_TYPE("1");
			ArrayList<FMT_CRMS_SVR_S0110102000B011_IN_SUB> grpAddrArry=new ArrayList<FMT_CRMS_SVR_S0110102000B011_IN_SUB>();
			String addressValue=item.getString("addressValue");//经营地址 addressValue  jyAddress
			if(addressValue==null||"".equals(addressValue)){
				addressValue=item.getString("jyAddress");
			}
		    String regAdministrativeDivisions=item.getString("regAdministrativeDivisions");//注册地行政区划
		    if(!"".equals(addressValue)&&addressValue!=null){
		    FMT_CRMS_SVR_S0110102000B011_IN_SUB grpAddrA0=new FMT_CRMS_SVR_S0110102000B011_IN_SUB();
		    grpAddrA0.setADDR_TYPE("114");
		    grpAddrA0.setADDR_LINE(addressValue);//经营地址
		    grpAddrArry.add(grpAddrA0);
		    FMT_CRMS_SVR_S0110102000B011_IN_SUB grpAddrA2=new FMT_CRMS_SVR_S0110102000B011_IN_SUB();
		    grpAddrA2.setADDR_TYPE("103");
		    grpAddrA2.setADDR_LINE(addressValue);//联系地址
		    grpAddrArry.add(grpAddrA2);
		    }
		    if(!"".equals(regAdministrativeDivisions)&&regAdministrativeDivisions!=null){
			FMT_CRMS_SVR_S0110102000B011_IN_SUB grpAddrA1=new FMT_CRMS_SVR_S0110102000B011_IN_SUB();
		    grpAddrA1.setADDR_TYPE("113");
		    grpAddrA1.setADDR_LINE(addressValue); //详细地址
		    grpAddrA1.setNATION_CODE(item.getString("nationalityCd"));//国家
		    grpAddrA1.setPROVINCE_CODE(item.getString("provinceCd"));//省、直辖市、自治区
			grpAddrA1.setCITY_CODE(item.getString("cityCd"));//市/自治州
			grpAddrA1.setCOUNTY_CODE(item.getString("district"));//区/县
			grpAddrA1.setSTREET_AREA(item.getString("streetAddress"));//街道
		    grpAddrArry.add(grpAddrA1);
		    }
			ArrayList<FMT_CRMS_SVR_S0110102000B011_IN_SUB1> grpAddrBrry=new ArrayList<FMT_CRMS_SVR_S0110102000B011_IN_SUB1>();
			String accountContactsPhone=item.getString("accountContactsPhone");//联系人电话
		    String telephone=item.getString("telephone");//固定电话
		    telephone="";
		    if(!"".equals(accountContactsPhone)&&accountContactsPhone!=null){
			 FMT_CRMS_SVR_S0110102000B011_IN_SUB1 grpAddrB0=new FMT_CRMS_SVR_S0110102000B011_IN_SUB1();
			 grpAddrB0.setTELE_TYPE("103");
			 grpAddrB0.setPHONE_NO(accountContactsPhone);//联系人电话
			 grpAddrBrry.add(grpAddrB0);
		    }
		    if(!"".equals(telephone)&&telephone!=null){
			 FMT_CRMS_SVR_S0110102000B011_IN_SUB1 grpAddrB1=new FMT_CRMS_SVR_S0110102000B011_IN_SUB1();
			 grpAddrB1.setTELE_TYPE("102");
			 grpAddrB1.setPHONE_NO(telephone);//固定电话
			 grpAddrBrry.add(grpAddrB1);
		    }
			ArrayList<FMT_CRMS_SVR_S0110102000B011_IN_SUB2> grpAddrCrry=new ArrayList<FMT_CRMS_SVR_S0110102000B011_IN_SUB2>();
			String website=item.getString("website");
		    String email=item.getString("email");
		    if(!"".equals(website)&&website!=null){
			 FMT_CRMS_SVR_S0110102000B011_IN_SUB2 grpAddrC0=new FMT_CRMS_SVR_S0110102000B011_IN_SUB2();
			 grpAddrC0.setINTER_TYPE("107");
			 grpAddrC0.setINTERNET_ADDR(website);//网址
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
		
		
		@Bizlet(value = "ECIF客户地址信息查询输入MAP")
		public Map CCustAddrInfo(String ecifPartyNum,String addrType,String teleType,String interType,Map item) throws Exception {
			IcustEcif custEcif=new  CustEcifImpl();
			FMT_CRMS_SVR_S0110101000A011_IN requestA011=new FMT_CRMS_SVR_S0110101000A011_IN();
			requestA011.setRESOLVE_TYPE("1");
			requestA011.setECIF_CUST_NO(ecifPartyNum);
			requestA011.setADDR_TYPE(addrType);
			requestA011.setTELE_TYPE(teleType);
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
			if("113".equals(addrType)){
			String regAdministrativeDivisions=grpAddrA1.getADDR_LINE();//注册地址
			String nationalityCd=grpAddrA1.getNATION_CODE();//国家
			String provinceCd=grpAddrA1.getPROVINCE_CODE();//省、直辖市、自治区
			String cityCd=grpAddrA1.getCITY_CODE();//市/自治州
			String district=grpAddrA1.getCOUNTY_CODE();//区/县
			String streetAddress=grpAddrA1.getSTREET_AREA();//街道
			item.put("regAdministrativeDivisions", regAdministrativeDivisions);
			item.put("nationalityCd", nationalityCd);
			item.put("provinceCd", provinceCd);
		//	item.put("provinceCd", "11");
			item.put("cityCd", cityCd);
			item.put("district", district);
			item.put("streetAddress", streetAddress);
			
			}
			if("114".equals(addrType)){
			String addressValue=grpAddrA1.getADDR_LINE();//经营地址
			item.put("addressValue", addressValue);
			}
			}
			if(grpAddrB!=null&&grpAddrB.length>0){
				FMT_CRMS_SVR_S0110101000A011_OUT_SUB1 grpAddrB1 =	grpAddrB[0];
			if("103".equals(teleType)){
			String accountContactsPhone=grpAddrB1.getPHONE_NO();//联系人电话
			item.put("accountContactsPhone", accountContactsPhone);
			}
			if("102".equals(teleType)){
			String telephone=grpAddrB1.getPHONE_NO();//固定电话
			//item.put("telephone", telephone);
			}

			}
			if(grpAddrC!=null&&grpAddrC.length>0){
				FMT_CRMS_SVR_S0110101000A011_OUT_SUB2 grpAddrC1 =	grpAddrC[0];
			if("107".equals(interType)){
			String website=grpAddrC1.getINTERNET_ADDR();//网址
			item.put("website", website);
			}
			if("101".equals(interType)){
			String email=grpAddrC1.getINTERNET_ADDR();//邮箱
			item.put("email", email);
			}
			}
			String msgg=responseA011.getResTranHeader().getHRetMsg();
			String msg=responseA011.getResTranHeader().getHRetCode();
			return item;
			
		}
		@Bizlet(value = "ECIF客户地址信息查询")
		public Map CCustAddrInfo(String ecifPartyNum,String addrType,String teleType,String interType,DataObject item) throws Exception {
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
			if("113".equals(addrType)){
			String regAdministrativeDivisions=grpAddrA1.getADDR_LINE();//注册地址
			String nationalityCd=grpAddrA1.getNATION_CODE();//国家
			String provinceCd=grpAddrA1.getPROVINCE_CODE();//省、直辖市、自治区
			String cityCd=grpAddrA1.getCITY_CODE();//市/自治州
			String district=grpAddrA1.getCOUNTY_CODE();//区/县
			String streetAddress=grpAddrA1.getSTREET_AREA();//街道
			if(regAdministrativeDivisions!=null&&!"".equals(regAdministrativeDivisions)){
				item.set("regAdministrativeDivisions", regAdministrativeDivisions);
			}
			if(nationalityCd!=null&&!"".equals(nationalityCd)){
				item.set("nationalityCd", nationalityCd);
			}
			if(provinceCd!=null&&!"".equals(provinceCd)){
				item.set("provinceCd", provinceCd);
			}
			if(cityCd!=null&&!"".equals(cityCd)){
				item.set("cityCd", cityCd);
			}
			if(district!=null&&!"".equals(district)){
				item.set("district", district);
			}
			if(district!=null&&!"".equals(district)){
				item.set("district", district);
			}
			
			}
			if("114".equals(addrType)){
			String addressValue=grpAddrA1.getADDR_LINE();//经营地址
			if(addressValue!=null&&!"".equals(addressValue)){
				item.set("addressValue", addressValue);//jyAddress
				item.set("jyAddress", addressValue);//同业客户
			}
			}
/*			if("103".equals(addrType)){
			String contactAdress=grpAddrA1.getADDR_LINE();//联系地址
			item.set("contactAdress", contactAdress);
			}*/
			if("103".equals(addrType)){
			String addressValue=grpAddrA1.getADDR_LINE();//联系地址
			if(addressValue!=null&&!"".equals(addressValue)){
				item.set("addressValue", addressValue);
			}
			}
			}
			if(grpAddrB!=null&&grpAddrB.length>0){
				FMT_CRMS_SVR_S0110101000A011_OUT_SUB1 grpAddrB1 =	grpAddrB[0];
			if("103".equals(teleType)){
			String accountContactsPhone=grpAddrB1.getPHONE_NO();//联系人电话
			if(accountContactsPhone!=null&&!"".equals(accountContactsPhone)){
				item.set("accountContactsPhone", accountContactsPhone);
			}
			}
			if("102".equals(teleType)){
			String telephone=grpAddrB1.getPHONE_NO();//固定电话
			if(telephone!=null&&!"".equals(telephone)){
				item.set("telephone", telephone);
			}

			}

			}
			if(grpAddrC!=null&&grpAddrC.length>0){
				FMT_CRMS_SVR_S0110101000A011_OUT_SUB2 grpAddrC1 =	grpAddrC[0];
			if("107".equals(interType)){
			String website=grpAddrC1.getINTERNET_ADDR();//网址
			if(website!=null&&!"".equals(website)){
				item.set("website", website);
			}
			}
			if("101".equals(interType)){
			String email=grpAddrC1.getINTERNET_ADDR();//邮箱
			if(email!=null&&!"".equals(email)){
				item.set("email", email);
			}
			}
			}
			String msgg=responseA011.getResTranHeader().getHRetMsg();
			String msg=responseA011.getResTranHeader().getHRetCode();
			Map map = new HashMap();
			map.put("msgg", msgg);
			map.put("msg", msg);
			return map;
			
		}
		@Bizlet(value = "客户业务开通情况登记")
		public Map CCustBussOpenMaint(String ecifPartyNum) throws Exception {
			IcustEcif custEcif=new  CustEcifImpl();
			FMT_CRMS_SVR_S0110101000A011_IN requestA011=new FMT_CRMS_SVR_S0110101000A011_IN();
			requestA011.setRESOLVE_TYPE("1");
			requestA011.setECIF_CUST_NO(ecifPartyNum);
//			requestA011.setADDR_TYPE(addrType);
//			requestA011.setTELE_TYPE(teleType);
//			requestA011.setFIRST_NO_A("1");
//			requestA011.setFIRST_NO_B("1");
//			requestA011.setFIRST_NO_C("1");
//			requestA011.setRESULT_SIZE_A("1");
//			requestA011.setRESULT_SIZE_B("1");
//			requestA011.setRESULT_SIZE_C("1");
//			S0110101000A011Response	responseA011=custEcif.CCustAddrInfoQuery(requestA011);
//			FMT_CRMS_SVR_S0110101000A011_OUT out=responseA011.getResponseBody();
//			FMT_CRMS_SVR_S0110101000A011_OUT_SUB[]  grpAddrA=out.getGRP_ADDR_A();
//			FMT_CRMS_SVR_S0110101000A011_OUT_SUB1[]  grpAddrB=out.getGRP_ADDR_B();
//			FMT_CRMS_SVR_S0110101000A011_OUT_SUB2[]  grpAddrC=out.getGRP_ADDR_C();
//			if(grpAddrA!=null&&grpAddrA.length>0){
//				FMT_CRMS_SVR_S0110101000A011_OUT_SUB grpAddrA1 =	grpAddrA[0];
//			if("113".equals(addrType)){
//			String regAdministrativeDivisions=grpAddrA1.getADDR_LINE();//注册地址
//			String nationalityCd=grpAddrA1.getNATION_CODE();//国家
//			String provinceCd=grpAddrA1.getPROVINCE_CODE();//省、直辖市、自治区
//			String cityCd=grpAddrA1.getCITY_CODE();//市/自治州
//			String district=grpAddrA1.getCOUNTY_CODE();//区/县
//			String streetAddress=grpAddrA1.getSTREET_AREA();//街道
//			item.set("regAdministrativeDivisions", regAdministrativeDivisions);
//			item.set("nationalityCd", nationalityCd);
//			item.set("provinceCd", provinceCd);
//			item.set("cityCd", cityCd);
//			item.set("district", district);
//			item.set("streetAddress", streetAddress);
//			
//			}
//			if("114".equals(addrType)){
//			String addressValue=grpAddrA1.getADDR_LINE();//经营地址
//			item.set("addressValue", addressValue);
//			}
//			if("103".equals(addrType)){
//			String contactAdress=grpAddrA1.getADDR_LINE();//联系地址
//			item.set("contactAdress", contactAdress);
//			}
//			}
//			if(grpAddrB!=null&&grpAddrB.length>0){
//				FMT_CRMS_SVR_S0110101000A011_OUT_SUB1 grpAddrB1 =	grpAddrB[0];
//			if("103".equals(teleType)){
//			String accountContactsPhone=grpAddrB1.getPHONE_NO();//联系人电话
//			item.set("accountContactsPhone", accountContactsPhone);
//			}
//			if("102".equals(teleType)){
//			String telephone=grpAddrB1.getPHONE_NO();//固定电话
//			item.set("telephone", telephone);
//			}
//
//			}
//			if(grpAddrC!=null&&grpAddrC.length>0){
//				FMT_CRMS_SVR_S0110101000A011_OUT_SUB2 grpAddrC1 =	grpAddrC[0];
//			if("107".equals(interType)){
//			String website=grpAddrC1.getINTERNET_ADDR();//网址
//			item.set("website", website);
//			}
//			if("101".equals(interType)){
//			String email=grpAddrC1.getINTERNET_ADDR();//邮箱
//			item.set("email", email);
//			}
//			}
//			String msgg=responseA011.getResTranHeader().getHRetMsg();
//			String msg=responseA011.getResTranHeader().getHRetCode();
//			Map map = new HashMap();
//			map.put("msgg", msgg);
//			map.put("msg", msg);
//			return map;
			return null;
		}
		
		

}
