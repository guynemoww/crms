/**
 * 
 */
package com.bos.csm.inteface.ecifJava;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import com.bos.pub.BizNumGenerator;
import com.bos.pub.GitUtil;
import com.eos.system.annotation.Bizlet;
import com.ibm.mq.MQException;
import commonj.sdo.DataObject;

/**
 * @author windows
 * @date 2014-06-06 17:15:35
 * 
 */
@Bizlet("")
public class SetEcifUpdateRqData {

	@Bizlet(value="对公客户请求数据组装")
	public EcifCustUpdateRq SetEcifUpdateRqData(DataObject party,DataObject corp, DataObject financial, DataObject[] adds,
			DataObject[] certs) throws JAXBException, MQException {
		EcifCustUpdateRq ecifCustUpdateRq = new EcifCustUpdateRq();
		List<AddressInfo> addInfo = new ArrayList<AddressInfo>();
		List<AddressInfo2> addInfo2 = new ArrayList<AddressInfo2>();
		List<CertInfo> certInfo = new ArrayList<CertInfo>();
		String TransSeqNo = BizNumGenerator.nowString();
		//组装流水号信息
		ecifCustUpdateRq.setTransSeqNo(TransSeqNo);          //流水号 
		
		//组装地址信息
		if(null!=adds && adds.length>0){
			   for(int i=0;i<adds.length;i++){
				   //传统地址
				   if(adds[i].getString("addressType")=="101"){
					   AddressInfo addrs=new AddressInfo();
					   addrs.setAddrType(adds[i].getString("deltaType")); //	州别
					   addrs.setAddrType(adds[i].getString("townStreet")); //	街道
					   
					   addrs.setAddrType(adds[i].getString("addressTypeCd")); //	地址类型代码
					   //addrs.setAgtMobile(adds[i].getString("mobilePhone"));//	手机
					   addrs.setAgtNation(adds[i].getString("streetAddress"));// 街道地址，具体到门牌号码
					   //addrs.setAgtPossCode(adds[i].getString("website"));//电子地址
					   //addrs.setContactPerson(adds[i].getString("linkmanName"));//	联系人
					   addrs.setCustProvince(adds[i].getString("provinceCd"));//省份代码
					   addrs.setDistNo(adds[i].getString("district"));// 区县
					   //addrs.setFareEmail(adds[i].getString("email"));// 电子邮件
					   addrs.setFaxPhone(adds[i].getString("fax"));	//	传真号码
					   addrs.setNationalCode(adds[i].getString("nationalityCd"));// 国家代码
					   addrs.setPayerPostCode(adds[i].getString("zipNum"));//	邮政编码
					   addrs.setTePhone(adds[i].getString("telephone"));// 电话号码
					   addrs.setProvinceCode(adds[i].getString("cityCd"));//城市代码
					   
					   addInfo.add(addrs);
				   }else{
					   AddressInfo2 addrs2=new AddressInfo2();
					   addrs2.setOpTypeAddn(adds[i].getString("crud"));//操作类型（逻辑流中赋值，无值则默认U）
					   addrs2.setNetType(null);//物理编号，暂为空
					   //电话地址
					   if(adds[i].getString("addressType")=="102"){
						   addrs2.setNetAddr(adds[i].getString("telephone"));
					   }
					   //传真地址
					   else if(adds[i].getString("addressType")=="103"){
						   addrs2.setNetAddr(adds[i].getString("fax"));
					   }
					   //网络地址
					   else if(adds[i].getString("addressType")=="201"){
						   addrs2.setNetAddr(adds[i].getString("website"));
					   }
					   //电子邮件地址
					   else if(adds[i].getString("addressType")=="202"){
						   addrs2.setNetAddr(adds[i].getString("email"));
					   }
					   //即时通讯工具地址
					   else if(adds[i].getString("addressType")=="203"){
						   addrs2.setNetAddr(null);//暂空
					   }
					   //其他地址
					   else if(adds[i].getString("addressType")=="900"){
						   addrs2.setNetAddr(null);//暂空
					   }
					   addInfo2.add(addrs2); 
				   }
				   
				   
			   }
			   ecifCustUpdateRq.setAddressList(addInfo);
			   ecifCustUpdateRq.setAddressList2(addInfo2);
		   }
		
		//组装证件信息
		if(null!=certs && certs.length>0){
			   for(int i=0;i<certs.length;i++){
				   CertInfo cert=new CertInfo();
				   
				   cert.setLegalEntTyp(certs[i].getString("certificateTypeCd"));//证件类型
				   if(certs[i].getString("certificateTypeCd").equals("20001")){
					   cert.setLegalIdNo(certs[i].getString("certificateCode").replace("-", ""));//组织机构代码去掉“-”
				   }else{
					   cert.setLegalIdNo(certs[i].getString("certificateCode"));// 证件编号
				   }
				   if(certs[i].getDate("signDate")!=null){
					   cert.setBeginEffDt(new SimpleDateFormat("yyyyMMdd").format(certs[i].getDate("signDate")));//签发日期
				   }
				   if(certs[i].getDate("endDate")!=null){
					   cert.setEndEffDt(new SimpleDateFormat("yyyyMMdd").format(certs[i].getDate("endDate")));// 到期日期
				   }
				   certInfo.add(cert);
			   }
			   ecifCustUpdateRq.setCertInfoList(certInfo);
		   }
		
		//组装参与人表
		if(null!=party){
			 ecifCustUpdateRq.setCustomer(party.getString("ecifPartyNum"));//客户号
			 ecifCustUpdateRq.setCNShortName(party.getString("partyName"));//客户名称
			 //ecifCustUpdateRq.setNationality(party.getString("contryRegionCd"));//国家或地区
			 ecifCustUpdateRq.setResidenceCountryCd(party.getString("contryRegionCd"));//所在国家（地区）
			 //ecifCustUpdateRq.setAdminDiv(party.getString("administrativeDivisionsCd"));//行政区划
			 //ecifCustUpdateRq.setGoldCustId(party.getString("goldExchangeCustCode"));//金交所客户代码
		}
		
		//组装公司客户表
		if(null!=corp){
			 ecifCustUpdateRq.setGBShortName(corp.getString("englishName"));          //客户英文名                
			 ecifCustUpdateRq.setLoanCardNo(corp.getString("loanCardNum"));           //贷款卡号                  
			 ecifCustUpdateRq.setApplyType(corp.getString("corpCustomerTypeCd"));            //商业客户类型                        
			 ecifCustUpdateRq.setEcgncSctCode(corp.getString("economicSectorCode"));         //企业经济成分              
			 //ecifCustUpdateRq.setEcgncCtgCode(corp.getString("economicCategoriesCode"));         //企业经济类型              
			 ecifCustUpdateRq.setIndustrialTypeCd(corp.getString("moneyLaunderingResultsCd"));     //反洗钱评级结果            
			 ecifCustUpdateRq.setMertype(corp.getString("industrialTypeCd"));              //行业类型                  
			 ecifCustUpdateRq.setListedCmp(corp.getString("listingCorporation"));            //是否上市公司   
			 if(corp.getDate("dateOfEstablishment")!=null){
				 ecifCustUpdateRq.setEstabDate(new SimpleDateFormat("yyyyMMdd").format(corp.getDate("dateOfEstablishment")));            //企业成立日期
			 }
			 ecifCustUpdateRq.setCapReg(corp.getString("registerAssets"));               //注册资本                  
			 ecifCustUpdateRq.setCurrency(corp.getString("registerAssetsCurrencyCd"));             //注册资本币种              
			 ecifCustUpdateRq.setNumber(corp.getString("employeesNumber"));               //从业人数                  
			 ecifCustUpdateRq.setInputAmt(corp.getString("businessIncome"));             //营业收入                  
			 ecifCustUpdateRq.setTranTotalAmt(corp.getString("totalAssets"));         //资产总额                  
			 ecifCustUpdateRq.setBusiTypeName(corp.getString("operatingBusiness"));         //主营业务                  
			 ecifCustUpdateRq.setAnnualRst(corp.getString("annualInspectionCd")); 
			 if(corp.getDate("loanCardOpenDate")!=null){
				 ecifCustUpdateRq.setBeginDt(new SimpleDateFormat("yyyyMMdd").format(corp.getDate("loanCardOpenDate")));              //贷款卡首次申领日期
			 }//企业年检结果              
			         
			 ecifCustUpdateRq.setIndexNo(corp.getString("annualInspectionIndexCd"));              //贷款卡年检标识  
			 if(corp.getDate("annualInspectionDate")!=null){
				 ecifCustUpdateRq.setTranTime(new SimpleDateFormat("yyyyMMdd").format(corp.getDate("annualInspectionDate")));   
			 }//贷款卡年检时间            
			 ecifCustUpdateRq.setEmail(corp.getString("corporationEMail"));                //公司E-Mail                
			 ecifCustUpdateRq.setCompanyUrl(corp.getString("corporationUrl"));           //公司网址                  
			 ecifCustUpdateRq.setTelephone(corp.getString("contactTelNum"));            //联系电话                  
			 ecifCustUpdateRq.setFaxPhone(corp.getString("faxPhone"));             //传真电话                  
			 ecifCustUpdateRq.setMobilePhone(corp.getString("financeContactPhone"));          //财务部联系电话            
			 ecifCustUpdateRq.setCntyRegId(corp.getString("nationalTaxNo"));            //税务登记号（国税）        
			 ecifCustUpdateRq.setCityRegId(corp.getString("governmentTentNo"));            //税务登记证号（地税）      
			 if(corp.getDate("annualInspectionDate")!=null){
				 ecifCustUpdateRq.setAppTime(new SimpleDateFormat("yyyyMMdd").format(corp.getDate("annualInspectionDate")));   
			 }//与我行建立信贷关系时间    
			 ecifCustUpdateRq.setImpExpFlg(corp.getString("whetherImpExp"));            //有无进出口经营权          
			 ecifCustUpdateRq.setCorpFlg(corp.getString("whetherScienceCorp"));              //是否科技型企业            
			 ecifCustUpdateRq.setCorpArea(corp.getString("operatingArea"));             //经营场地面积（平方米）    
			 ecifCustUpdateRq.setCorpAreaOwn(corp.getString("operatingAreaOwnershipCd"));          //经营场地所有权            
			 ecifCustUpdateRq.setCorpHoldType(corp.getString("corpHoldingTypeCd"));         //企业控股类型              
			 ecifCustUpdateRq.setCorpCloseFlg(corp.getString("whetherCloseCorp"));         //是否关停企业              
			 ecifCustUpdateRq.setCorpDespSizeFlg(corp.getString("whetherAboveDesignatedSize"));      //是否规模以上企业          
			 ecifCustUpdateRq.setCorpLmtSizeFlg(corp.getString("whetherAboveLimitSize"));       //是否限额以上企业          
			 ecifCustUpdateRq.setCorpBnkImptFlg(corp.getString("whetherBankImportantCorp"));       //是否全行及重点目标客户    
			 ecifCustUpdateRq.setCorpPassPeatFlg(corp.getString("whetherPassPeanuts"));      //是否小企业认定通过        
			 ecifCustUpdateRq.setCorpCreditFlg(corp.getString("creditCustomers"));        //是否授信客户              
			 ecifCustUpdateRq.setCorpSpclFlg(corp.getString("whetherSpecialCorp"));          //是否专项客户              
			 ecifCustUpdateRq.setAgentName(corp.getString("agentName"));            //代理商名称                
			 ecifCustUpdateRq.setCorpImportFlg(corp.getString("focusCustomer"));        //是否重点客户              
			 ecifCustUpdateRq.setCorpVipFlg(corp.getString("whetherVipCorp"));           //是否Vip客户         
			 if(corp.getDate("registerDate")!=null){
				 ecifCustUpdateRq.setCM_sEdDay(new SimpleDateFormat("yyyyMMdd").format(corp.getDate("registerDate")));        
			 }//注册日期                  
			 //ecifCustUpdateRq.setRegtAssRate(corp.getString("registerAssetsRate"));          //注册资本到位率            
			 ecifCustUpdateRq.setIdNo(corp.getString("legalCertificateNo"));                 //法人证书号码              
			 ecifCustUpdateRq.setMainDept(corp.getString("competentDepartment"));             //主管部门                  
			 ecifCustUpdateRq.setInitAmt(corp.getString("initialFund"));              //开办资金                  
			 ecifCustUpdateRq.setFinaResources(corp.getString("financialResourcesCd"));        //经费来源                  
			 ecifCustUpdateRq.setCompanyName(corp.getString("hostUnit"));          //举办单位                  
			 ecifCustUpdateRq.setContactPerson(corp.getString("corpLinkman"));        //客户方联系人              
			 ecifCustUpdateRq.setContactPhone(corp.getString("financeLinkman"));         //财务部联系人              
			// ecifCustUpdateRq.setLegalCrtftEnDt(new SimpleDateFormat("yyyyMMdd").format(corp.getDate("legalCertificateEndDate")));       //法人证书有效期            
			 ecifCustUpdateRq.setPurposeScope(corp.getString("purposeBizScope"));         //宗旨和业务范围            
			 ecifCustUpdateRq.setRegOrg(corp.getString("registerOrg"));               //登记机关                  
			 ecifCustUpdateRq.setRemark(corp.getString("manageLevelState"));               //客户历史沿革管理水平简介  
			 ecifCustUpdateRq.setUsage(corp.getString("operateState"));                //经营状况说明              
			 ecifCustUpdateRq.setProdtRemark(corp.getString("majorProductState"));          //主要产品情况              
			 ecifCustUpdateRq.setOpenAcctNo(corp.getString("openAcctApprovalNo"));           //开户核准号                
			 ecifCustUpdateRq.setClrAcctFlg(corp.getString("whetherSettlementAccount"));           //是否在我行有结算帐户      
			 ecifCustUpdateRq.setDrawBackAcctFlg(corp.getString("whetherExpDrawbackAccount"));      //是否有出口退税帐户        
			 ecifCustUpdateRq.setPerfAcctFlg(corp.getString("haveNotPreferentialPolicy"));          //有无优惠政策              
			 ecifCustUpdateRq.setEnterpFlg(corp.getString("whetherAttentionEnterprise"));            //是否出口收汇关注企业      
			 ecifCustUpdateRq.setFurzEnterpSize(corp.getString("fourzEnterpriseSizeCd"));       //四部委企业规模-自动计算   
			 ecifCustUpdateRq.setFurrEnterpSize(corp.getString("fourrEnterpriseSizeCd"));       //四部委企业规模-人工输入   
			 ecifCustUpdateRq.setBankType(corp.getString("financeEnterpriseType"));             //金融机构类型              
			 ecifCustUpdateRq.setBlackLstFlg(corp.getString("whetherBlackList"));          //是否黑名单客户            
			 ecifCustUpdateRq.setBlklstRson(corp.getString("blackListReasonCd"));           //黑名单进入原因            
			 ecifCustUpdateRq.setGrpRelCutFlg(corp.getString("groupCustomer"));         //是否集团客户              
			 ecifCustUpdateRq.setGrpRelCutCode(corp.getString("groupRelTypeCd"));        //集团成员关系种类代码      
			 ecifCustUpdateRq.setOpenCurrecy(corp.getString("initialFundCurrecyCd"));          //开办资金币种              
			 ecifCustUpdateRq.setRegType(corp.getString("registrationType"));              //登记注册类型              
			 ecifCustUpdateRq.setFtaCsmFlg(corp.getString("ifFtaCsm"));            //是否自贸区客户        
			 if(corp.getDate("orgRegisterDate")!=null){
				 ecifCustUpdateRq.setInputDt(new SimpleDateFormat("yyyyMMdd").format(corp.getDate("orgRegisterDate")));              //组织机构登记日期
			 }
			 
			 ecifCustUpdateRq.setCommiStatus(corp.getString("whetherLoanValidate"));          //贷款卡是否有效            
			 ecifCustUpdateRq.setEstateFlag(corp.getString("whetherEstateDev"));           //是否从事房地产开发        
			 ecifCustUpdateRq.setTechCompFlag(corp.getString("whetherHightechCorp"));         //是否高新技术企业   
		}
		
		//组装同业客户表
		if(null!=financial){
			ecifCustUpdateRq.setFinanceInstType(financial.getString("financialInstitutionTypeCd"));      //同业类型 
			ecifCustUpdateRq.setLoanCardNo(financial.getString("loanCardNum"));           //贷款卡号 
			ecifCustUpdateRq.setSwiftBicCode(financial.getString("swiftBicNum"));         //swift BIC码 
			ecifCustUpdateRq.setGBShortName(financial.getString("englishCustomerName"));          //客户英文名 
			if(financial.getDate("annualInspectionDate")!=null){
				ecifCustUpdateRq.setTranTime(new SimpleDateFormat("yyyyMMdd").format(financial.getDate("annualInspectionDate")));
			}//贷款卡年检时间
			if(financial.getDate("dateOfEstablishment")!=null){
				ecifCustUpdateRq.setEstabDate(new SimpleDateFormat("yyyyMMdd").format(financial.getDate("dateOfEstablishment")));   
			}//企业成立日期 
			ecifCustUpdateRq.setSubjectType(financial.getString("subjectTypeCd"));          //客户主体类型
			if(financial.getDate("orgRegisterDate")!=null){
			ecifCustUpdateRq.setInspectionDate(new SimpleDateFormat("yyyyMMdd").format(financial.getDate("industryAnnualInspectionDat")));
			}//工商年检日期 
			if(financial.getDate("registerDate")!=null){
				ecifCustUpdateRq.setCM_sEdDay(new SimpleDateFormat("yyyyMMdd").format(financial.getDate("registerDate")));
			}//注册日期    
			ecifCustUpdateRq.setCollectDepCode(financial.getString("financialPermitNum"));       //金融许可证机构编码 
			ecifCustUpdateRq.setCapReg(financial.getString("registerAssets"));               //注册资本                  
			ecifCustUpdateRq.setCurrency(financial.getString("registerAssetsCurrencyCd"));             //注册资本币种  
			ecifCustUpdateRq.setCntyRegId(financial.getString("nationalTaxNo"));            //税务登记号（国税）        
			ecifCustUpdateRq.setCityRegId(financial.getString("governmentTentNo"));            //税务登记证号（地税）             
			ecifCustUpdateRq.setNumber(financial.getString("employeesNumber"));               //从业人数                  
			ecifCustUpdateRq.setInputAmt(financial.getString("businessIncome"));             //营业收入                  
			ecifCustUpdateRq.setTranTotalAmt(financial.getString("totalAssets"));         //资产总额 
			ecifCustUpdateRq.setCreditPolicy(financial.getString("customerCreditPolicyCd"));         //客户信贷政策  
			ecifCustUpdateRq.setListedCmp(financial.getString("listingCorporation"));            //是否上市公司
			ecifCustUpdateRq.setGrpRelCutFlg(financial.getString("groupCustomer"));         //是否集团客户 
			ecifCustUpdateRq.setFtaCsmFlg(financial.getString("ifFtaCsm"));            //是否自贸区客户
			ecifCustUpdateRq.setTotalCurry(financial.getString("assetsTotalAmtCcy"));           //资产总额币种             
			ecifCustUpdateRq.setAreaType(financial.getString("areaType"));             //区域类型-境内外 
			ecifCustUpdateRq.setBankType(financial.getString("financeEnterpriseType"));             //金融机构类型 
			ecifCustUpdateRq.setFurzEnterpSize(financial.getString("fourzEnterpriseSizeCd"));       //四部委企业规模-自动计算   
			ecifCustUpdateRq.setFurrEnterpSize(financial.getString("fourrEnterpriseSizeCd"));       //四部委企业规模-人工输入            
			//ecifCustUpdateRq.setCountryFinFlg(financial.getString("chinaInvestFlag"));        //中资标志                  
			ecifCustUpdateRq.setRemark_1(financial.getString("remarkOne"));             //备注1                     
			ecifCustUpdateRq.setRemark_2(financial.getString("remarkTwo"));             //备注2                     
			ecifCustUpdateRq.setRemark_3(financial.getString("remarkThree"));             //备注3  

		}

		return ecifCustUpdateRq;
	}

	
	
	@Bizlet(value="自然人客户请求数据组装(单纯自然人维护)")
	public EcifNaturalUpdateRq SetEcifNaturalUpdateRqData(DataObject party,
			DataObject nat,DataObject[] certs) throws JAXBException, MQException {
		
		EcifNaturalUpdateRq ecifNaturalUpdatRq = new EcifNaturalUpdateRq();
		List<CertInfo> certInfo = new ArrayList<CertInfo>();
		
		//组装证件信息
		if(null!=certs && certs.length>0){
			   for(int i=0;i<certs.length;i++){
				   CertInfo cert=new CertInfo();
				   cert.setLegalEntTyp(certs[i].getString("certificateTypeCd"));//证件类型
				   cert.setLegalIdNo(certs[i].getString("certificateCode"));// 证件编号
				   if(certs[i].getDate("signDate")!=null){
					   cert.setBeginEffDt(new SimpleDateFormat("yyyyMMdd").format(certs[i].getDate("signDate")));//签发日期
				   }
				   if(certs[i].getDate("signDate")!=null){
					   cert.setEndEffDt(new SimpleDateFormat("yyyyMMdd").format(certs[i].getDate("endDate")));// 到期日期
				   }
				   certInfo.add(cert);
			   }
			   ecifNaturalUpdatRq.setCertInfoList(certInfo);
		   }
		
		//组装自然人的基本信息
		if(null!=party && null!=nat){
			ecifNaturalUpdatRq.setCustomer(party.getString("ecifPartyNum"));//客户号
			ecifNaturalUpdatRq.setSPName(party.getString("partyName"));//参与人名称
			ecifNaturalUpdatRq.setNationality(party.getString("contryRegionCd"));//国籍代码
			ecifNaturalUpdatRq.setSex(nat.getString("genderCd"));//性别代码
			ecifNaturalUpdatRq.setBirthday(nat.getString("birthday"));//出生日期
			ecifNaturalUpdatRq.setMarriage(nat.getString("marriageCd"));//婚姻状况
			ecifNaturalUpdatRq.setDegreeCode(nat.getString("degreeCd"));//学位代码
			ecifNaturalUpdatRq.setDegreeCode_1(nat.getString("educationBackgroudCd"));//学历代码
			ecifNaturalUpdatRq.setEthnicGp(nat.getString("nation"));//民族
			ecifNaturalUpdatRq.setSpouseName(nat.getString("spouseName"));//配偶姓名
		}
		
		return ecifNaturalUpdatRq;
	}
	
	@Bizlet(value="自然人客户请求数据组装(建立与对公客户关系)")
	public EcifNaturalUpdateRq SetEcifNaturalUpdateRqData(DataObject party,
			DataObject nat,DataObject[] certs,String CRUD,String Type) throws JAXBException, MQException {
		
		EcifNaturalUpdateRq ecifNaturalUpdatRq = new EcifNaturalUpdateRq();
		List<CertInfo> certInfo = new ArrayList<CertInfo>();
		
		//建立的关系
		
		//当前建立关系的客户号
		
		
		//组装证件信息
		if(null!=certs && certs.length>0){
			   for(int i=0;i<certs.length;i++){
				   CertInfo cert=new CertInfo();
				   
				   cert.setLegalEntTyp(certs[i].getString("certificateTypeCd"));//证件类型
				   cert.setLegalIdNo(certs[i].getString("certificateCode"));// 证件编号
				   if(certs[i].getDate("signDate")!=null){
					   cert.setBeginEffDt(new SimpleDateFormat("yyyyMMdd").format(certs[i].getDate("signDate")));//签发日期
				   }
				   if(certs[i].getDate("signDate")!=null){
					   cert.setEndEffDt(new SimpleDateFormat("yyyyMMdd").format(certs[i].getDate("endDate")));// 到期日期
				   }
				   
				   certInfo.add(cert);
			   }
			   ecifNaturalUpdatRq.setCertInfoList(certInfo);
		   }
		
		//组装自然人的基本信息
		if(null!=party && null!=nat){
			ecifNaturalUpdatRq.setCustomer(party.getString("ecifPartyNum"));//客户号
			ecifNaturalUpdatRq.setSPName(party.getString("partyName"));//参与人名称
			ecifNaturalUpdatRq.setNationality(party.getString("contryRegionCd"));//国籍代码
			ecifNaturalUpdatRq.setSex(nat.getString("genderCd"));//性别代码
			ecifNaturalUpdatRq.setBirthday(nat.getString("birthday"));//出生日期
			ecifNaturalUpdatRq.setMarriage(nat.getString("marriageCd"));//婚姻状况
			ecifNaturalUpdatRq.setDegreeCode(nat.getString("degreeCd"));//学位代码
			ecifNaturalUpdatRq.setDegreeCode_1(nat.getString("educationBackgroudCd"));//学历代码
			ecifNaturalUpdatRq.setEthnicGp(nat.getString("nation"));//民族
			ecifNaturalUpdatRq.setSpouseName(nat.getString("spouseName"));//配偶姓名
			ecifNaturalUpdatRq.setReLtTyCd(Type);//关系类型
		}
		
		
		return ecifNaturalUpdatRq;
	}
}
