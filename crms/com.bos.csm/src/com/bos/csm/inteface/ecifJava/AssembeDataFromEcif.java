/**
 * 
 */
package com.bos.csm.inteface.ecifJava;


import java.sql.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.eos.foundation.data.DataObjectUtil;
import com.eos.system.annotation.Bizlet;
import com.eos.system.exception.EOSException;
import commonj.sdo.DataObject;

/**
 * @author git
 * @date 2014-06-09 17:49:05
 *
 */
@Bizlet("组装数据（数据从ECIF查询得到）")
public class AssembeDataFromEcif {

	
	 @Bizlet(value="组装数据(party)")
	   public DataObject handleDataParty(EcifCustRs ecifCustRs) throws EOSException{
		 DataObject obj = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
		 if(ecifCustRs.Customer!=null){
			 obj.set("ecifPartyNum",ecifCustRs.Customer);//客户号   
		 }
		 if(ecifCustRs.SPName!=null){
			 obj.set("partyName",ecifCustRs.SPName);                //客户名称 
		 }                            
		                
		 //obj.set("contryRegionCd",ecifCustRs.Nationality);           //国家或地区  
		 if(ecifCustRs.Nationality!=null){
			 ecifCustRs.ResidenceCountryCd = ecifCustRs.Nationality;
		 }
		 if(ecifCustRs.ResidenceCountryCd!=null){
			 obj.set("contryRegionCd",ecifCustRs.ResidenceCountryCd);    //所在国家（地区）
		 }
		 if(ecifCustRs.AdminDiv!=null){
			 obj.set("administrativeDivisionsCd",ecifCustRs.AdminDiv);              //行政区划
		 } 
		 if(ecifCustRs.GoldCustId!=null){
			 obj.set("goldExchangeCustCode",ecifCustRs.GoldCustId);            //金交所客户代码
		 }
		 
		 
		 return obj;
	 }
	 
	 @Bizlet(value="组装数据(corp)")
	   public DataObject handleDataCorp(EcifCustRs ecifCustRs,String corpType) throws EOSException, ParseException{
		 DataObject obj = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmCorporation");
		 if(ecifCustRs.GBShortName!=null){
			 obj.set("englishName",ecifCustRs.GBShortName);           //客户英文名              
		 }
//		 if(ecifCustRs.LoanCardNo!=null){
//			 obj.set("loanCardNum",ecifCustRs.LoanCardNo);            //贷款卡号
//		 }
		 if(ecifCustRs.ApplyType!=null){
			 obj.set("corpCustomerTypeCd",ecifCustRs.ApplyType == null ? corpType:ecifCustRs.ApplyType);             //商业客户类型
		 }
		 if(ecifCustRs.EcgncSctCode!=null){
			 obj.set("economicSectorCode",ecifCustRs.EcgncSctCode);          //企业经济成分
		 }
		 if(ecifCustRs.EcgncSctCode!=null){
			 obj.set("economicCategoriesCode",ecifCustRs.EcgncCtgCode);          //企业经济类型
		 }            
		 if(ecifCustRs.IndustrialTypeCd!=null){
			 obj.set("moneyLaunderingResultsCd",ecifCustRs.IndustrialTypeCd);      //反洗钱评级结果
		 }          
		 /*if(ecifCustRs.Mertype!=null){
			 obj.set("industrialTypeCd",ecifCustRs.Mertype);               //行业类型
		 }*/
		 if(ecifCustRs.ListedCmp!=null){                
			 obj.set("listingCorporation",ecifCustRs.ListedCmp);             //是否上市公司        
	 	 }
//		 if(ecifCustRs.EstabDate!=null){
//			 obj.set("dateOfEstablishment",new SimpleDateFormat("yyyyMMdd").parse(ecifCustRs.EstabDate));             //企业成立日期
//		 }
//		 if(ecifCustRs.CapReg!=null){
//			 obj.set("registerAssets",ecifCustRs.CapReg);                //注册资本
//		 }
//		 if(ecifCustRs.Currency!=null){
//			 obj.set("registerAssetsCurrencyCd",ecifCustRs.Currency);              //注册资本币种
//		 }                
		 if(ecifCustRs.Number!=null){
			 obj.set("employeesNumber",ecifCustRs.Number);                //从业人数
		 }            
		 if(ecifCustRs.InputAmt!=null){
			 obj.set("businessIncome",ecifCustRs.InputAmt);              //营业收入
		 }         
		 if(ecifCustRs.TranTotalAmt!=null){
			 obj.set("totalAssets",ecifCustRs.TranTotalAmt);          //资产总额
		 }                
//		 if(ecifCustRs.BusiTypeName!=null){
//			 obj.set("operatingBusiness",ecifCustRs.BusiTypeName);          //主营业务
//		 }
		 if(ecifCustRs.AnnualRst!=null){
			 obj.set("annualInspectionCd",ecifCustRs.AnnualRst);             //企业年检结果
		 }
		 if(ecifCustRs.BeginDt!=null){
			 obj.set("loanCardOpenDate",new SimpleDateFormat("yyyyMMdd").parse(ecifCustRs.BeginDt));               //贷款卡首次申领日期
		 }
		 if(ecifCustRs.IndexNo!=null){
			 obj.set("annualInspectionIndexCd",ecifCustRs.IndexNo);               //贷款卡年检标识
		 }   
		 if(ecifCustRs.TranTime!=null){
			 obj.set("annualInspectionDate",new SimpleDateFormat("yyyyMMdd").parse(ecifCustRs.TranTime));              //贷款卡年检时间
			 
		 }
		 if(ecifCustRs.Email!=null){
			 obj.set("corporationEMail",ecifCustRs.Email);                 //公司E-Mail 
		 }              
		 if(ecifCustRs.CompanyUrl!=null){
			 obj.set("corporationUrl",ecifCustRs.CompanyUrl);            //公司网址
		 }
//		 if(ecifCustRs.Telephone!=null){
//			 obj.set("contactTelNum",ecifCustRs.Telephone);             //联系电话
//		 }               
//		 if(ecifCustRs.FaxPhone!=null){
//			 obj.set("faxPhone",ecifCustRs.FaxPhone);              //传真电话
//		 }
		 if(ecifCustRs.MobilePhone!=null){
			 obj.set("financeContactPhone",ecifCustRs.MobilePhone);           //财务部联系电话
		 }                
//		 if(ecifCustRs.CntyRegId!=null){
//			 obj.set("nationalTaxNo",ecifCustRs.CntyRegId);             //税务登记号（国税）
//		 }          
//		 if(ecifCustRs.CityRegId!=null){
//			 obj.set("governmentTentNo",ecifCustRs.CityRegId);             //税务登记证号（地税） 
//		 }      
		    
		 if(ecifCustRs.AppTime!=null){
			 obj.set("creditRelationshipTime",new SimpleDateFormat("yyyyMMdd").parse(ecifCustRs.AppTime));               //与我行建立信贷关系时间
		 }
		 if(ecifCustRs.ImpExpFlg!=null){
			 obj.set("whetherImpExp",ecifCustRs.ImpExpFlg);             //有无进出口经营权
		 }        
		 if(ecifCustRs.CorpFlg!=null){
			 obj.set("whetherScienceCorp",ecifCustRs.CorpFlg);               //是否科技型企业
		 }
		 if(ecifCustRs.CorpArea!=null){
			 obj.set("operatingArea",ecifCustRs.CorpArea);              //经营场地面积（平方米）
		 }  
		 if(ecifCustRs.CorpAreaOwn!=null){
			 obj.set("operatingAreaOwnershipCd",ecifCustRs.CorpAreaOwn);           //经营场地所有权
		 }
		 if(ecifCustRs.CorpHoldType!=null){
			 obj.set("corpHoldingTypeCd",ecifCustRs.CorpHoldType);          //企业控股类型 
		 }            
		 if(ecifCustRs.CorpCloseFlg!=null){
			 obj.set("whetherCloseCorp",ecifCustRs.CorpCloseFlg);          //是否关停企业
		 }            
		 if(ecifCustRs.CorpDespSizeFlg!=null){
			 obj.set("whetherAboveDesignatedSize",ecifCustRs.CorpDespSizeFlg);       //是否规模以上企业
		 }
		 if(ecifCustRs.CorpLmtSizeFlg!=null){
			 obj.set("whetherAboveLimitSize",ecifCustRs.CorpLmtSizeFlg);        //是否限额以上企业
		 }        
		 if(ecifCustRs.CorpBnkImptFlg!=null){
			 obj.set("whetherBankImportantCorp",ecifCustRs.CorpBnkImptFlg);        //是否全行及重点目标客户
		 }        
//		 if(ecifCustRs.CorpPassPeatFlg!=null){
//			 obj.set("whetherPassPeanuts",ecifCustRs.CorpPassPeatFlg);       //是否小企业认定通过
//		 }      
		 if(ecifCustRs.CorpCreditFlg!=null){
			 obj.set("creditCustomers",ecifCustRs.CorpCreditFlg);         //是否授信客户
		 }         
		 if(ecifCustRs.CorpSpclFlg!=null){
			 obj.set("whetherSpecialCorp",ecifCustRs.CorpSpclFlg);           //是否专项客户
		 }
		 if(ecifCustRs.AgentName!=null){
			 obj.set("agentName",ecifCustRs.AgentName);             //代理商名称
		 }              
		 if(ecifCustRs.CorpImportFlg!=null){
			 obj.set("focusCustomer",ecifCustRs.CorpImportFlg);         //是否重点客户
		 }           
		 if(ecifCustRs.CorpVipFlg!=null){
			 obj.set("whetherVipCorp",ecifCustRs.CorpVipFlg);            //是否Vip客户
		 }
		            
//		 if(ecifCustRs.CM_sEdDay!=null){ 
//			 obj.set("registerDate",new SimpleDateFormat("yyyyMMdd").parse(ecifCustRs.CM_sEdDay)); //注册日期    
//		 }
		                          
		 //obj.set("registerAssetsRate",ecifCustRs.RegtAssRate);           //注册资本到位率          
		 if(ecifCustRs.IdNo!=null){
			 obj.set("legalCertificateNo",ecifCustRs.IdNo);                  //法人证书号码
		 }            
		 if(ecifCustRs.MainDept!=null){
			 obj.set("competentDepartment",ecifCustRs.MainDept);              //主管部门
		 }                
		 if(ecifCustRs.InitAmt!=null){
			 obj.set("initialFund",ecifCustRs.InitAmt);               //开办资金
		 }              
		 if(ecifCustRs.FinaResources!=null){
			 obj.set("financialResourcesCd",ecifCustRs.FinaResources);         //经费来源
		 }
		 if(ecifCustRs.CompanyName!=null){
			 obj.set("hostUnit",ecifCustRs.CompanyName);           //举办单位
		 }                
		 if(ecifCustRs.ContactPerson!=null){
			 obj.set("corpLinkman",ecifCustRs.ContactPerson);         //客户方联系人
		 }               
		 if(ecifCustRs.ContactPhone!=null){
			 obj.set("financeLinkman",ecifCustRs.ContactPhone);          //财务部联系人
		 }            
		 if(ecifCustRs.LegalCrtftEnDt!=null){
			 obj.set("legalCertificateEndDate",new SimpleDateFormat("yyyyMMdd").parse(ecifCustRs.LegalCrtftEnDt));        //法人证书有效期 
		 }
		 if(ecifCustRs.PurposeScope!=null){
			 obj.set("purposeBizScope",ecifCustRs.PurposeScope);          //宗旨和业务范围
		 }          
		 if(ecifCustRs.RegOrg!=null){
			 obj.set("registerOrg",ecifCustRs.RegOrg);                //登记机关 
		 }                
		 if(ecifCustRs.ReMark!=null){
			 obj.set("manageLevelState",ecifCustRs.ReMark);                //客户历史沿革管理水平简介
		 }
		 if(ecifCustRs.Usage!=null){
			 obj.set("operateState",ecifCustRs.Usage);                 //经营状况说明
		 }            
		 if(ecifCustRs.ProdtRemark!=null){
			 obj.set("majorProductState",ecifCustRs.ProdtRemark);           //主要产品情况
		 }            
		 if(ecifCustRs.OpenAcctNo!=null){
			 obj.set("openAcctApprovalNo",ecifCustRs.OpenAcctNo);            //开户核准号
		 }              
		 if(ecifCustRs.ClrAcctFlg!=null){
			 obj.set("whetherSettlementAccount",ecifCustRs.ClrAcctFlg);            //是否在我行有结算帐户
		 }    
		 if(ecifCustRs.DrawBackAcctFlg!=null){
			 obj.set("whetherExpDrawbackAccount",ecifCustRs.DrawBackAcctFlg);       //是否有出口退税帐户
		 }      
		 if(ecifCustRs.PerfAcctFlg!=null){
			 obj.set("haveNotPreferentialPolicy",ecifCustRs.PerfAcctFlg);           //有无优惠政策
		 }            
		 if(ecifCustRs.EnterpFlg!=null){
			 obj.set("whetherAttentionEnterprise",ecifCustRs.EnterpFlg);             //是否出口收汇关注企业
		 }    
		 if(ecifCustRs.FurzEnterpSize!=null){
			 obj.set("fourzEnterpriseSizeCd",ecifCustRs.FurzEnterpSize);        //四部委企业规模-自动计算 
		 } 
//		 if(ecifCustRs.FurrEnterpSize!=null){
//			 obj.set("fourrEnterpriseSizeCd",ecifCustRs.FurrEnterpSize);        //四部委企业规模-人工输入
//		 } 
		 if(ecifCustRs.BankType!=null){
			 obj.set("financeEnterpriseType",ecifCustRs.BankType);              //金融机构类型 
		 }            
		 if(ecifCustRs.BlackLstFlg!=null){
			 obj.set("whetherBlackList",ecifCustRs.BlackLstFlg);           //是否黑名单客户
		 }          
		 if(ecifCustRs.BlklstRson!=null){
			 obj.set("blackListReasonCd",ecifCustRs.BlklstRson);            //黑名单进入原因
		 }          
		 if(ecifCustRs.GrpRelCutFlg!=null){
			 obj.set("groupCustomer",ecifCustRs.GrpRelCutFlg);          //是否集团客户
		 }            
//		 if(ecifCustRs.GrpRelCutCode!=null){
//			 obj.set("groupRelTypeCd",ecifCustRs.GrpRelCutCode);         //集团成员关系种类代码
//		 }    
		 if(ecifCustRs.OpenCurrecy!=null){
			 obj.set("initialFundCurrecyCd",ecifCustRs.OpenCurrecy);           //开办资金币种
		 }            
		 if(ecifCustRs.RegType!=null){
			 obj.set("registrationType",ecifCustRs.RegType);               //登记注册类型
		 }            
//		 if(ecifCustRs.FtaCsmFlg!=null){
//			 obj.set("ifFtaCsm",ecifCustRs.FtaCsmFlg);             //是否自贸区客户
//		 }          
		 if(ecifCustRs.InputDt!=null){
			 obj.set("orgRegisterDate",new SimpleDateFormat("yyyyMMdd").parse(ecifCustRs.InputDt));               //组织机构登记日期
		 }
		 if(ecifCustRs.CommiStatus!=null){
			 obj.set("whetherLoanValidate",ecifCustRs.CommiStatus);           //贷款卡是否有效
		 }        
		 if(ecifCustRs.EstateFlag!=null){
			 obj.set("whetherEstateDev",ecifCustRs.EstateFlag);            //是否从事房地产开发
		 }
		 if(ecifCustRs.TechCompFlag!=null){
			 obj.set("whetherHightechCorp",ecifCustRs.TechCompFlag);          //是否高新技术企业
		 }      
		         
		                  
		 return obj;
	 }
	 
	 @Bizlet(value="组装数据(financial)")
	   public DataObject handleDataFinancial(EcifCustRs ecifCustRs) throws EOSException, ParseException{
		 DataObject obj = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmFinancialInstitution");
		 if(ecifCustRs.FinanceInstType!=null){
			 obj.set("financialInstitutionTypeCd",ecifCustRs.FinanceInstType);       //同业类型
		 }
		 if(ecifCustRs.LoanCardNo!=null){
			 obj.set("loanCardNum",ecifCustRs.LoanCardNo);            //贷款卡号
		 }  
		 if(ecifCustRs.SwiftBicCode!=null){
			 obj.set("swiftBicNum",ecifCustRs.SwiftBicCode);          //swift BIC码
		 }
		 if(ecifCustRs.GBShortName!=null){
			 obj.set("englishCustomerName",ecifCustRs.GBShortName);           //客户英文名
		 }
		 if(ecifCustRs.TranTime!=null){
			 obj.set("annualInspectionDate",new SimpleDateFormat("yyyyMMdd").parse(ecifCustRs.TranTime));              //贷款卡年检时间
		 }
		 if(ecifCustRs.EstabDate!=null){
			 obj.set("dateOfEstablishment",new SimpleDateFormat("yyyyMMdd").parse(ecifCustRs.EstabDate));             //企业成立日期
		 }
		 if(ecifCustRs.SubjectType!=null){
			 obj.set("subjectTypeCd",ecifCustRs.SubjectType);           //客户主体类型
		 }
//		 if(ecifCustRs.InspectionDate!=null){
//			 obj.set("industryAnnualInspectionDat",new SimpleDateFormat("yyyyMMdd").parse(ecifCustRs.InspectionDate));        //工商年检日期
//		 }
		 if(ecifCustRs.CM_sEdDay!=null){
			 obj.set("registerDate",new SimpleDateFormat("yyyyMMdd").parse(ecifCustRs.CM_sEdDay));             //注册日期
		 }
//		 if(ecifCustRs.CollectDepCode!=null){
//			 obj.set("financialPermitNum",ecifCustRs.CollectDepCode);        //金融许可证机构编码
//		 }      
		 if(ecifCustRs.CapReg!=null){
			 obj.set("registerAssets",ecifCustRs.CapReg);                //注册资本
		 }
		 if(ecifCustRs.Currency!=null){
			 obj.set("registerAssetsCurrencyCd",ecifCustRs.Currency);              //注册资本币种
		 } 
		 if(ecifCustRs.CntyRegId!=null){
			 obj.set("nationalTaxNo",ecifCustRs.CntyRegId);             //税务登记号（国税）
		 }      
		 if(ecifCustRs.CityRegId!=null){
			 obj.set("governmentTentNo",ecifCustRs.CityRegId);             //税务登记证号（地税）
		 }    
		 if(ecifCustRs.Number!=null){
			 obj.set("employeesNumber",ecifCustRs.Number);                //从业人数
		 }
		 if(ecifCustRs.InputAmt!=null){
			 obj.set("businessIncome",ecifCustRs.InputAmt);              //营业收入
		 }                
		 if(ecifCustRs.TranTotalAmt!=null){
			 obj.set("totalAssets",ecifCustRs.TranTotalAmt);          //资产总额
		 }                
		 if(ecifCustRs.CreditPolicy!=null){
			 obj.set("customerCreditPolicyCd",ecifCustRs.CreditPolicy);          //客户信贷政策
		 }
		 if(ecifCustRs.ListedCmp!=null){
			 obj.set("listingCorporation",ecifCustRs.ListedCmp);             //是否上市公司
		 }
		 if(ecifCustRs.GrpRelCutFlg!=null){
			 obj.set("groupCustomer",ecifCustRs.GrpRelCutFlg);          //是否集团客户
		 }
		 if(ecifCustRs.FtaCsmFlg!=null){
			 obj.set("ifFtaCsm",ecifCustRs.FtaCsmFlg);             //是否自贸区客户
		 }
		 if(ecifCustRs.TotalCurry!=null){
			 obj.set("assetsTotalAmtCcy",ecifCustRs.TotalCurry);            //资产总额币种
		 }            
		 if(ecifCustRs.AreaType!=null){
			 obj.set("areaType",ecifCustRs.AreaType);              //区域类型-境内外
		 }         
		 if(ecifCustRs.BankType!=null){
			 obj.set("financeEnterpriseType",ecifCustRs.BankType);              //金融机构类型 
		 } 
		 if(ecifCustRs.FurzEnterpSize!=null){
			 obj.set("fourzEnterpriseSizeCd",ecifCustRs.FurzEnterpSize);        //四部委企业规模-自动计算
		 } 
		 if(ecifCustRs.FurrEnterpSize!=null){
			 obj.set("fourrEnterpriseSizeCd",ecifCustRs.FurrEnterpSize);        //四部委企业规模-人工输入
		 } 
		 //obj.set("chinaInvestFlag",ecifCustRs.CountryFinFlg);         //中资标志                
		 if(ecifCustRs.Remark_1!=null){
			 obj.set("remarkOne",ecifCustRs.Remark_1);              //备注1
		 }                   
		 if(ecifCustRs.Remark_2!=null){
			 obj.set("remarkTwo",ecifCustRs.Remark_2);              //备注2
		 }                   
		 if(ecifCustRs.Remark_3!=null){
			 obj.set("remarkThree",ecifCustRs.Remark_3);              //备注3
		 }          
		 
		 return obj;
	 }
	 
	 @Bizlet(value="组装数据(address)")
	   public ArrayList handleDataAdds(EcifCustRs ecifCustRs) throws EOSException{
		 ArrayList objs = new ArrayList();
		 List<AddressInfoForECIF> adrs = ecifCustRs.adds;
		 if(null != adrs && adrs.size()>0){
			 for(int i=0;i<adrs.size();i++){
				 DataObject obj = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmAddress");
				 AddressInfoForECIF adr = adrs.get(i);
				 if(adr.AddrUsageType!=null){
					 obj.set("addressTypeCd", adr.AddrUsageType);//传统地址类型
				 }
				 if(adr.Continent!=null){
					 obj.set("deltaType",adr.Continent);//州别
				 }
				 if(adr.AddCountry!=null){
					 obj.set("nationalityCd", adr.AddCountry); // 国家代码
				 }
				 if(adr.Province!=null){
					 obj.set("provinceCd", adr.Province); //省份代码
				 }
				 if(adr.Dist!=null){
					 obj.set("district", adr.Dist); // 区县
				 }
				 if(adr.City!=null){
					 obj.set("cityCd", adr.City);  //城市代码
				 }
				 if(adr.Strent!=null){
					 obj.set("townStreet",adr.Strent);//街道
				 }
				 if(adr.StrentDoor!=null){
					 obj.set("streetAddress", adr.StrentDoor); // 街道地址，具体到门牌号码
				 }
				 if(adr.TePhone!=null){
					 obj.set("telephone", adr.TePhone); // 电话号码
				 }
				 if(adr.FaxPhone!=null){
					 obj.set("fax", adr.FaxPhone); //	传真号码
				 }
				 if(adr.AddressType!=null){
					 obj.set("addressType",adr.AddressType);//地址类型
				 }
				// obj.set("website", adr.AgtPossCode); //电子地址
				// obj.set("mobilePhone", adr.AgtMobile); //	手机
				// obj.set("email", adr.FareEmail); // 电子邮件
				// obj.set("linkmanName", adr.ContactPerson); //联系人
				 objs.add(obj);
			 }
		 }
		 return objs;
	 }
	 
	 @Bizlet(value="组装数据(address2)")
	   public ArrayList handleDataAdds2(EcifCustRs ecifCustRs) throws EOSException{
		 ArrayList objs = new ArrayList();
		 List<AddressInfo2> adrs2 = ecifCustRs.adds2;
		 if(null != adrs2 && adrs2.size()>0){
			 for(int i=0;i<adrs2.size();i++){
				 DataObject obj = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmAddress");
				 AddressInfo2 adr = adrs2.get(i);
				// obj.set("telephone", adr.Phone); // 电话号码
				// obj.set("fax", adr.faxAgtFax); //	传真号码
				// obj.set("website", adr.AgtPossCode); //电子地址
				// obj.set("mobilePhone", adr.AgtMobile); //	手机
				// obj.set("email", adr.FareEmail); // 电子邮件
				// obj.set("linkmanName", adr.ContactPerson); //联系人
				   //电话地址
				   if(adrs2.get(i).NetAddrNo=="102"){
					   obj.set("mobilePhone",adr.NetAddr);
				   }
				   //传真地址
				   else if(adrs2.get(i).NetAddrNo=="103"){
					   obj.set("fax",adr.NetAddr);
				   }
				   //网络地址
				   else if(adrs2.get(i).NetAddrNo=="201"){
					   obj.set("website",adr.NetAddr);
				   }
				   //电子邮件地址
				   else if(adrs2.get(i).NetAddrNo=="202"){
					   obj.set("email",adr.NetAddr);
				   }
				   //即时通讯工具地址
				   else if(adrs2.get(i).NetAddrNo=="203"){
					   obj.set("addressValue",adr.NetAddr);
				   }
				   //其他地址
				   else if(adrs2.get(i).NetAddrNo=="900"){
					   obj.set("addressValue",adr.NetAddr);
				   }
			
				 objs.add(obj);
			 }
		 }
		 return objs;
	 }
	 
	 @Bizlet(value="组装数据(cert)")
	   public ArrayList  handleDataCerts(EcifCustRs ecifCustRs) throws EOSException, ParseException{
		 ArrayList objs = new ArrayList();
		 List<CertInfo> certs = ecifCustRs.certs;
		 if(null != certs && certs.size()>0){
			 for(int i=0;i<certs.size();i++){
				 DataObject obj = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmCertificateInfo");
				 CertInfo cert = certs.get(i);
				 obj.set("certificateTypeCd", cert.LegalEntTyp);//证件类型
				 if(cert.LegalEntTyp.equals("20001")){//组织机构代码格式转换
					 
					 char[] ltp = cert.LegalIdNo.toCharArray();
					 String certNo = "";
					 for(int j = 0;j<ltp.length;j++){
						 if(j==8){
							 certNo += "-"+ltp[j];
						 }else{
							 certNo += ltp[j]; 
						 }
						 
					 }
					 obj.set("certificateCode", certNo); // 证件编号
				 }else{
					 obj.set("certificateCode", cert.LegalIdNo); // 证件编号
				 }
				 
				 if(cert.BeginEffDt!=null){
					 obj.set("signDate", new SimpleDateFormat("yyyyMMdd").parse(cert.BeginEffDt)); //签发日期
				 }
				 if(cert.EndEffDt!=null){
					 obj.set("endDate", new SimpleDateFormat("yyyyMMdd").parse(cert.EndEffDt)); // 到期日期
				 }
				 
				 objs.add(obj);
			 }
		 }
		 return objs;
	 }
	 
	 
}
