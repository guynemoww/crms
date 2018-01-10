<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/nui/common.jsp"%>
<script type="text/javascript" src="<%=contextPath%>/csm/js/commValidate.js"></script>
<script type="text/javascript" src="<%=contextPath%>/csm/js/csmValidate.js"></script>
<!-- 
  - Author(s): cc
  - Date: 2016-04-27

  - Description:企业客户基本信息维护
-->
<head>
<title>企业客户基本信息</title>
</head>
<body>
<div id="form1"  style="width:100%;height:auto;overflow:hidden; text-align:left">
	<input name="item.partyId" class="nui-hidden nui-form-input"/>
	<input id="party.examineState" name="party.examineState" class="nui-hidden nui-form-input"/>
	<fieldset>
  	<legend>
    	<span>概况</span>
    </legend>
    	<div class="nui-dynpanel" style="border:none" columns="4">
			
    	<label>ECIF客户编号：</label>
		<input id="party.ecifPartyNum" name="party.ecifPartyNum"   enabled="false"  class="nui-textbox nui-form-input"/>
    	
   		<label>CRMS客户编号：</label>
		<input id="party.partyNum" name="party.partyNum"  enabled="false" class="nui-textbox nui-form-input"/>
		
		<label>客户性质：</label>
		<input id="item.corpCustomerTypeCd" name="item.corpCustomerTypeCd" required="true" enabled="true"
			class="nui-dictcombobox nui-form-input" dictTypeId="XD_KHCD0252" />
		
		<!-- <label>客户主体类型：</label> 
		<input id="item.mainCustTypeCd" name="item.mainCustTypeCd" enabled="false" class="nui-dictcombobox nui-form-input" dictTypeId="ECIF_KHZTLX0001" />	 -->
		
		<label>客户名称：</label>
		<input id="party.partyName" name="party.partyName" required="true" enabled="false" class="nui-textbox nui-form-input" vtype="maxLength:100"/>	
		
		<label>外文名称：</label>
		<input id="item.englishName" name="item.englishName" required="false" class="nui-textbox nui-form-input" vtype="maxLength:100"/>
   		
   		<label>国家或地区：</label>
		<input id="item.contryRegionCd" name="item.contryRegionCd" required="true" class="nui-dictcombobox nui-form-input" 
				dictTypeId="CD000003"   dValue="CHN" enabled="false"/>
				
   		<label>区域类型：</label> 
		<input id="item.areaType" name="item.areaType"enabled="false"
			class="nui-dictcombobox nui-form-input" dictTypeId="XD_KHCD0210"  required="true" />
				
		<label>是否信贷客户：</label>
		<input id="party.isPotentialCust" name="party.isPotentialCust" required="true"  onvaluechanged="dataCheck"
			class="nui-dictcombobox nui-form-input" dictTypeId="YesOrNo" />	
			
		<label>是否第三方客户：</label>
		<input id="item.isThirdCust"  name="item.isThirdCust" required="true" 
				class="nui-dictcombobox nui-form-input" onvaluechanged="selectThirdCust" dictTypeId="YesOrNo" />
		<label>第三方客户类型：</label> 
		<input id="item.thirdCustTypeCd" name="item.thirdCustTypeCd" required="false" class="nui-dictcombobox nui-form-input" dictTypeId="XD_KHCD7001" />	
		
		<label>成立日期：</label> 
		<input id="item.registerDate1" name="item.registerDate1" enabled="false" class="nui-text nui-form-input"  />
		
		<label>机构状态：</label>
		<input id="item.orgStatus" name="item.orgStatus" required="true" valueField="dictID" textField="dictName" class="nui-dictcombobox nui-form-input" dictTypeId="CDZZ0004" emptyText="请选择" /> 
		
		</div>
    </fieldset>
    
    <fieldset>
  	<legend>
    	<span>证件信息</span>
    </legend>
    	<div class="nui-dynpanel" style="border:none" columns="4">
			<label>法人代表国籍：</label>
			<input id="item.legalContry" name="item.legalContry" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CD000003" dValue="CHN"/>
			
	    	<label>法人代表姓名：</label>
			<input id="item.legalName" name="item.legalName" required="true" class="nui-textbox nui-form-input" vtype="maxLength:100"/>
			
			<label>法人代表联系电话：</label>
			<input id="item.legalPhoneNumber" name="item.legalPhoneNumber" required="true" class="nui-textbox nui-form-input" vtype="int;minLength:11;maxLength:11"/>
		
			<label>法人代表证件类型：</label>
			<input id="item.legalCertType" name="item.legalCertType" required="true"
				class="nui-dictcombobox nui-form-input"  dictTypeId="CDKH0002"/>
			
			<label>法人代表证件号码：</label>
			<input id="item.legalCertificateNo" name="item.legalCertificateNo" required="true" onvalidation="onValidateCertificateCode"
				class="nui-textbox nui-form-input" vtype="maxLength:100"/>
				
			<label>证件签发日期：</label>
			<input id="item.legalRegistDate" name="item.legalRegistDate" required="true" allowInput="false" 
				class="nui-datepicker nui-form-input" maxDate="<%=GitUtil.getBusiDateStr()%>" format="yyyy-MM-dd"/>
				
			<label>证件到期日：</label>
			<input id="item.legalCertificateEndDate" name="item.legalCertificateEndDate" allowInput="false" minDate="<%=GitUtil.getBusiDateStr()%>"  required="true" 
			class="nui-datepicker nui-form-input"  format="yyyy-MM-dd"/>
			
			<label>长期</label>
			<input id="longTime" name="longTime"  onclick="setDate(this)" required="false" class="nui-checkbox"  />

			<label>登记注册类型：</label>
			<input id="item.registrationType" name="item.registrationType" required="true" allowInput="false"  
			class="nui-buttonEdit nui-form-input" onbuttonclick="selectRegistType" dictTypeId="CDKH0024"/>
			
			<label>企业出资人经济成分：</label>
			<input id="item.economicSectorCode" name="item.economicSectorCode" required="true"  allowInput="false"
				class="nui-buttonEdit nui-form-input" onbuttonclick="selectEconomicSectorCode" dictTypeId="CDKH0028"/>
			
			<label>统一社会信用代码：</label>
			<input id="item.unifySocietyCreditNum" name="item.unifySocietyCreditNum" enabled="true" onblur="dataCheck" onvalidation="checkUnifySocietyCreditNum"  class="nui-textbox nui-form-input"/>
			
			<label>营业执照：</label>
			<input id="item.registrCd" name="item.registrCd"  enabled="true" vtype="maxLength:30"  class="nui-textbox nui-form-input"/>
			
			<label>营业执照登记日期：</label>
			<input id="item.registerDate" name="item.registerDate"  allowInput="false" 
				class="nui-datepicker nui-form-input" maxDate="<%=GitUtil.getBusiDateStr()%>" format="yyyy-MM-dd"/>
				
			<label>注册资本币种：</label>
			<input id="item.registerAssetsCurrencyCd" name="item.registerAssetsCurrencyCd" required="true" 
				class="nui-dictcombobox nui-form-input" dictTypeId="CD000001" dValue="CNY" />
				
			<label>营业执照到期日：</label>
			<input id="item.registerEndDate" name="item.registerEndDate"  allowInput="false" minDate="<%=GitUtil.getBusiDateStr()%>"   
			class="nui-datepicker nui-form-input" format="yyyy-MM-dd"/>
			
			<label>长期</label>
			<input id="longTime1" name="longTime1"  onclick="setDate1(this)" required="false" class="nui-checkbox"  />
			
			<label>注册资本：</label>
			<input id="item.registerAssets"  name="item.registerAssets" required="true"  
				class="nui-textbox nui-form-input" vtype="float;range:1,999999999999;maxLength:15" dataType="currency"/>
				
			<label>经营范围：</label>
			<input id="item.businessScope" name="item.businessScope" required="true" 
				class="nui-textarea nui-form-input" vtype="maxLength:800"/>
<!-- 		    <label>经营场地面积(平方米)：</label>
			<input id="item.area"  name="item.area" 
				class="nui-textbox nui-form-input" vtype="float;range:0,999999999999;maxLength:15"/>
			<label>经营场地所有权：</label>
			<input id="item.owner"           name="item.owner"           required="true"  class="nui-dictcombobox nui-form-input" dictTypeId="CDJYSYQ00"/> -->
			
    		<label>组织机构代码：</label>
			<input id="item.orgRegisterCd" name="item.orgRegisterCd" enabled="false"  required="true" 
				class="nui-textbox nui-form-input"/>
			
			<label>组织机构代码证到期日：</label>
			<input id="item.orgRegisterEndDate" name="item.orgRegisterEndDate" allowInput="false"  required="true" 
				class="nui-datepicker nui-form-input" format="yyyy-MM-dd" minDate="<%=GitUtil.getBusiDateStr()%>"/>
			
			<label>中征码：</label>
			<input id="item.middelCode" name="item.middelCode" 
				class="nui-textbox nui-form-input" vtype="int;minLength:16;maxLength:16"/>
			
			<label>机构信用代码：</label>
			<input id="item.orgCreditCode" name="item.orgCreditCode" required="false" 
				class="nui-textbox nui-form-input" vtype="minLength:18;maxLength:18"/>
			
			<label>国税登记证号码：</label>
			<input id="item.nationalTaxNo" name="item.nationalTaxNo" vtype="minLength:15;maxLength:25" 
				class="nui-textbox nui-form-input" />
		
			<label>地税登记证号码：</label>
			<input id="item.governmentTentNo" name="item.governmentTentNo" vtype="minLength:15;maxLength:25" 
				class="nui-textbox nui-form-input" />
    	</div>
    </fieldset>
    
    <fieldset>
  	<legend>
   		<span>分类信息</span>
    </legend>
    	<div class="nui-dynpanel" style="border:none" columns="4">
    	
		<label>行业门类：</label>
		<input id="item.industrialTypeCd" name="item.industrialTypeCd" required="true" 
			class="nui-combobox nui-form-input" onitemclick="selectTrade" valueField="dictId" textField="dictName" />
		
		<label>行业大类：</label>
		<input id="item.industrialTypeBigCd" name="item.industrialTypeBigCd" required="true" 
			class="nui-combobox nui-form-input" onitemclick="selectTrade2" valueField="dictId" textField="dictName" />
		
		<label>行业中类：</label>
		<input id="item.industrialTypeMidCd" name="item.industrialTypeMidCd" required="true" 
			class="nui-combobox nui-form-input" onitemclick="selectTrade3" valueField="dictId" textField="dictName" />	
			
    	<label>行业小类：</label>
		<input id="item.industrialTypeSamllCd" name="item.industrialTypeSamllCd" required="true" 
			class="nui-combobox nui-form-input" valueField="dictId" textField="dictName" onitemclick="selectCode"/>
    	
    	<label>行业代码：</label>
    	<input id="industrialCode" name="industrialCode" required="false" class="nui-textbox nui-form-input" enabled="false" />
    	</div>
    </fieldset>
    
    <fieldset>
  	<legend>
   		<span>企业规模</span>
    </legend>
    	<div class="nui-dynpanel" style="border:none" columns="4">
		
	    
	    <label>从业人数（人）：</label>
		<input id="item.employeesNumber" name="item.employeesNumber" required="true" 
			class="nui-textbox nui-form-input" vtype="int;maxLength:8;range:1,99999"/>
	    
	    <label>工信部企业规模：</label>
		<input id="item.enterpriseScaleGx" name="item.enterpriseScaleGx" 
			class="nui-dictcombobox nui-form-input" dictTypeId="CDKH0025" required="true" enabled="false" />
	    
	    <label>统计口径企业规模：</label>
		<input id="item.countBoreEnterScale" name="item.countBoreEnterScale" required="true" enabled="false"
			class="nui-dictcombobox nui-form-input" dictTypeId="CDKH0025"  />
		
		<label>银行认定企业规模：</label>
		<input id="item.bankScaleIdentify" name="item.bankScaleIdentify" required="true" enabled="false"
			class="nui-dictcombobox nui-form-input" dictTypeId="CDKH0025"  />
		
    	</div>
    </fieldset>
    
    <fieldset>
  	<legend>
   		<span>重要标志</span>
    </legend>
    	<div class="nui-dynpanel" style="border:none" columns="4">
    	<label>单一法人客户标志：</label>
		<input id="item.singleLpCustInd"  name="item.singleLpCustInd" required="true" 
				class="nui-dictcombobox nui-form-input" dictTypeId="YesOrNo" />
    	
    	<label>是否上市公司：</label>
		<input id="item.listingCorporation" name="item.listingCorporation" required="true" 
			class="nui-dictcombobox nui-form-input" dictTypeId="YesOrNo" dValue="0"/>
    	
    	<label>是否科技型企业：</label>
    	<input id="item.whetherTechnic"  name="item.whetherTechnic" required="true" 
			class="nui-dictcombobox nui-form-input" dictTypeId="YesOrNo" dValue="0"/>
			
    	<label>是否绿色信贷企业：</label>
    	<input id="item.whetherGreenGroup"  name="item.whetherGreenGroup" required="true" 
			class="nui-dictcombobox nui-form-input" dictTypeId="YesOrNo" dValue="0"/>
    	
    	<label>是否军民融合企业：</label>
    	<input id="item.whetherJmrh"  name="item.whetherJmrh" required="true" 
			class="nui-dictcombobox nui-form-input" dictTypeId="YesOrNo" dValue="0"/>
    	
    	<label>是否涉农企业：</label>
    	<input id="item.whetherArgRelated"  name="item.whetherArgRelated" required="true" 
			class="nui-dictcombobox nui-form-input" dictTypeId="YesOrNo" dValue="0"/>
			
    	<label>是否在我行开立基本账户：</label>
    	<input id="item.whetherOpenAcct"  name="item.whetherOpenAcct" required="true" 
			class="nui-dictcombobox nui-form-input" dictTypeId="YesOrNo" dValue="0"/>
    	
    	<label>是否扶贫企业：</label>
    	<input id="item.whetherPovertyAdd"  name="item.whetherPovertyAdd" required="true" 
			class="nui-dictcombobox nui-form-input" dictTypeId="YesOrNo" dValue="0"/>
    	
    	<label>进出口权标志：</label>
		<input id="item.whetherImpExp"  name="item.whetherImpExp" required="true" 
			class="nui-dictcombobox nui-form-input" dictTypeId="YesOrNo" dValue="0"/>
			
		<label>家族企业标志：</label>
		<input id="item.familyEnterprise" name="item.familyEnterprise" required="true" 
			class="nui-dictcombobox nui-form-input" dictTypeId="YesOrNo" dValue="0"/>
		
		<label>融资平台标志：</label>
		<input id="item.whetherGovernmentFinanceCor" name="item.whetherGovernmentFinanceCor" required="true"
			class="nui-dictcombobox nui-form-input" dictTypeId="XD_KHCD0243"/>
			
		<label>重点客户标志：</label>
		<input id="item.focusCustomer" name="item.focusCustomer" required="true"
			class="nui-dictcombobox nui-form-input" dictTypeId="YesOrNo" dValue="0"/>
		
		<label>是否从事房地产开发：</label>
		<input id="item.isRealEstateDev" name="item.isRealEstateDev" required="true"
			class="nui-dictcombobox nui-form-input" dictTypeId="YesOrNo" dValue="0"/>
			
		<label>农村企业标志：</label>
		<input id="item.countrysideEnterprise" name="item.countrysideEnterprise" required="true"
			class="nui-dictcombobox nui-form-input" dictTypeId="YesOrNo" dValue="0"  />
			
		<label>是否我行关联方：</label>
		<input id="item.isBasebankRelaCust"  name="item.isBasebankRelaCust" required="true" 
			class="nui-dictcombobox nui-form-input" dictTypeId="YesOrNo" enabled="false" dValue="0"/>		
			
		<label>我行股东标志：</label>
		<input id="item.stockholderOfBank" name="item.stockholderOfBank"  enabled="false"
			class="nui-dictcombobox nui-form-input" dictTypeId="YesOrNo"/>	
			
		<label>联保小组标志：</label>
		<input id="item.jointGuarantee" name="item.jointGuarantee"  enabled="false"
			class="nui-dictcombobox nui-form-input" dictTypeId="YesOrNo"/>
			
    	<label>是否集团成员：</label>
		<input id="item.isGroupCust" name="item.isGroupCust"  enabled="false"
			class="nui-dictcombobox nui-form-input" dictTypeId="YesOrNo"/>
    	
    	<label>所属集团客户名称：</label>
		<input id="item.attachGroupName" name="item.attachGroupName"  enabled="false"
			class="nui-text nui-form-input" />
    	
		<label>黑名单标志：</label>
		<input id="item.whetherBlackList" name="item.whetherBlackList"  enabled="false"
			class="nui-dictcombobox nui-form-input" dictTypeId="YesOrNo" />
		
		<label>关停企业标志：</label>
		<input id="item.stopCorpInd" name="item.stopCorpInd" required="true" 
				class="nui-dictcombobox nui-form-input" dictTypeId="YesOrNo"/>
				
		<label>与我行建立信贷关系日期：</label>
		<input id="item.creditRelationshipTime" name="item.creditRelationshipTime"  enabled="false"
			class="nui-datepicker nui-form-input" format="yyyy-MM-dd"/>
			
			<label>国民经济部门类型：</label> 
			<input id="item.nationalEconomyType" name="item.nationalEconomyType" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="ECIF_GMJJBMLX01" />	
		
			<label>供应链客户标志：</label>
			<input id="item.chainSign" name="item.chainSign" required="false" 
				class="nui-dictcombobox nui-form-input" dictTypeId="YesOrNo" dValue="0"/>
    	</div>
    	
    </fieldset>
    	
	<div class="nui-toolbar" style="text-align:right;" borderStyle="border:0;">
	    <a id="btnSave" class="nui-button" iconCls="icon-save" onclick="update(1)">保存</a>
	    <a id="btnTest" class="nui-button" iconCls="icon-save" onclick="save(2)">临时保存</a>
	    <a id="btnVali" class="nui-button" iconCls="icon-save" onclick="validationAll">校验完整性</a>
	</div>
</div>

<script type="text/javascript">
	nui.parse();
	
	git.mask("form1");
	var form = new nui.Form("#form1");
	var pi = "<%=request.getParameter("partyId")%>";
	var qote = "<%=request.getParameter("qote")%>";
	var ecifPartyNum = "<%=request.getParameter("ecifPartyNum")%>" ;
		var partyId = "<%=request.getParameter("partyId")%>" ;
		var legorg = "<%=com.bos.pub.GitUtil.getLegorg()%>";
		var unifySocietyCreditNumEdit;
		var orgRegisterCdEdit;
	if(qote==1){
	   form.setEnabled(false);
	   nui.get("btnSave").hide();
	   nui.get("btnTest").hide();
	   nui.get("btnVali").hide();
	} 

	//初始化页面
    $(document).ready(function(){
        	   	  var arr = git.getDictDataFilter("XD_KHCD0252","2,3,4");
		           nui.get("item.corpCustomerTypeCd").setData(arr);
    	var json2 = nui.encode({parentId:"",typeId:"CDKH0095"});
	     $.ajax({
	        url: "com.bos.csm.pub.getDict.getDict.biz.ext",
	        type: 'POST',
	        data: json2,
	  	    cache: true,
	       	async:false,
	        contentType:'text/json',
	        success: function (text) {
	            git.unmask();
	            nui.get("item.industrialTypeCd").setData(text.levels);
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            git.unmask();
	            nui.alert(jqXHR.responseText);
	        }
	     });
		if(qote!=1){
		var json = nui.encode({"partyId":pi});
		$.ajax({
	        url: "com.bos.csm.corporation.corporation.getCorpration1.biz.ext",//getCorpration1
	        type: 'POST',
	        data: json,
	  	    cache: true,
	       	async:false,
	        contentType:'text/json',
	        success: function (text) {
	        	git.unmask("form1");
	        	if(text.map.msg!="AAAAAAA"){
	        	alert(text.map.msgg);
	        	return;
	        	} 
	        	    	 orgRegisterCdEdit=text.item.orgRegisterCd;
    	unifySocietyCreditNumEdit=text.item.unifySocietyCreditNum;
	                form.setData(text);
	                //初始化成立日期
               	nui.get("item.registerDate1").setValue(text.item.registerDate);
               	//初始化证件到期日
               	if(text.item.legalCertificateEndDate=="9999-12-31"){
             	nui.get("longTime").setChecked(true);
             	nui.get("item.legalCertificateEndDate").setEnabled(false);
               	}else{
               	nui.get("longTime").setChecked(false);
               	nui.get("item.legalCertificateEndDate").setEnabled(true);
               	}
               	//初始化注册登记证到期日
               	if(text.item.registerEndDate=="9999-12-31"){
             	nui.get("longTime1").setChecked(true);
             	nui.get("item.registerEndDate").setEnabled(false);
               	}else{
               	nui.get("longTime1").setChecked(false);
               	nui.get("item.registerEndDate").setEnabled(true);
               	}
	             	//初始化行业类型
	            	initIndustry("item.industrialTypeCd","item.industrialTypeBigCd");
	           		initIndustry("item.industrialTypeBigCd","item.industrialTypeMidCd");
	           		initIndustry("item.industrialTypeMidCd","item.industrialTypeSamllCd");
	           		nui.get("industrialCode").setValue(nui.get("item.industrialTypeSamllCd").getValue());
	                //备份数据
	               	window.form1Data=form.getData();
					if(text.party.isPotentialCust=='1'){
						nui.get('party.isPotentialCust').setEnabled(false);
                		if(text.item.areaType=="1"){//境内
                	   		nui.get("item.middelCode").setRequired(true);
                		}	            
                	}                
                	if(text.party.examineState=='3'){
	                	nui.get('btnTest').setEnabled(false);
	                 }
	                if(text.item.unifySocietyCreditNum!=null&&text.item.unifySocietyCreditNum!=""){//如已录入“统一社会信用代码”，则国税、地税登记证号码非必输
		              	nui.get("item.registrCd").setRequired(false);//注册登记号
		              	nui.get("item.orgRegisterCd").setRequired(false);//组织机构代码
		              	nui.get("item.orgRegisterEndDate").setRequired(false);//组织机构代码证到期日
		            }else{
		              	//nui.get("item.registrCd").setRequired(true);//注册登记号
		              	if(text.item.areaType=="1"){//境内
		              		nui.get("item.orgRegisterCd").setRequired(true);//组织机构代码
		              		nui.get("item.orgRegisterEndDate").setRequired(true);//组织机构代码证到期日
		              	}else{//境外
	              			nui.get("item.orgRegisterEndDate").setRequired(false);//组织机构代码证到期日
	              		}
		            }
		            form.validate();
	                 //字典过滤，过滤掉202的证件类型
	   				var arr = git.getDictDataUnFilter("CDKH0002","202");
					nui.get("item.legalCertType").setData(arr);
	                //通过规则初始化关联情况
			 		var para = {"partyId":'<%=request.getParameter("partyId")%>'};
			   	    msg = exeRule("RCSM_1003","1",para);
			   	    if(null != msg && '' != msg){
				   	     nui.get('item.whetherBlackList').setValue(0);
			   	    }else{
			   	    	 nui.get('item.whetherBlackList').setValue(1);
			   	    }
			   	    //我行股东标识
			   	    para = {"certType":'202',"certNum":text.map.item.orgRegisterCd};
	                msg = exeRule("RCSM_1001","1",para);
			   	    if(null != msg && '' != msg){
				   	     nui.get('item.stockholderOfBank').setValue(0);
			   	    }else{
			   	    	 nui.get('item.stockholderOfBank').setValue(1);
			   	    } 
			   	    //是否我行关联方
			   	    msg = exeRule("RCSM_1002","1",para);
			   	    if(null != msg && '' != msg){
				   	     nui.get('item.isBasebankRelaCust').setValue(0);
			   	    }else{
			   	    	 nui.get('item.isBasebankRelaCust').setValue(1);
			   	    }
			   	    //alert(nui.get("item.singleLpCustInd").getValue());
	        }
    	});}
    	else{
    		var jsont = nui.encode({"partyId":pi});
    	$.ajax({
	        url: "com.bos.csm.corporation.corporation.getCorpration.biz.ext",
	        type: 'POST',
	        data: jsont,
	  	    cache: true,
	       	async:false,
	        contentType:'text/json',
	        success: function (text) {
	        	git.unmask("form1");
	        	    	 orgRegisterCdEdit=text.item.orgRegisterCd;
    	unifySocietyCreditNumEdit=text.item.unifySocietyCreditNum;
               	form.setData(text);
               	 //初始化成立日期
               	nui.get("item.registerDate1").setValue(text.item.registerDate);
               	//初始化行业类型
	            initIndustry("item.industrialTypeCd","item.industrialTypeBigCd");
	           	initIndustry("item.industrialTypeBigCd","item.industrialTypeMidCd");
	           	initIndustry("item.industrialTypeMidCd","item.industrialTypeSamllCd");
	           	nui.get("industrialCode").setValue(nui.get("item.industrialTypeSamllCd").getValue());
				//备份数据
                window.form1Data=form.getData();
                if(text.party.isPotentialCust=='1'){//授信客户不能修改为非授信
                	nui.get('party.isPotentialCust').setEnabled(false);
                	if(text.item.areaType=="1"){//境内
                	    nui.get("item.middelCode").setRequired(true);
                	}
                }
                if(text.party.examineState=='3'){//通过完整性校验的客户不能点击临时保存
	                nui.get('btnTest').setEnabled(false);
	            }
	            if(text.item.unifySocietyCreditNum!=null&&text.item.unifySocietyCreditNum!=""){//如已录入“统一社会信用代码”，则国税、地税登记证号码非必输
	               	nui.get("item.nationalTaxNo").setRequired(false);//国税登记号
	              	nui.get("item.governmentTentNo").setRequired(false);//地税登记号
	              	nui.get("item.registrCd").setRequired(false);//注册登记号
	              	nui.get("item.orgRegisterCd").setRequired(false);//组织机构代码
	              	nui.get("item.orgRegisterEndDate").setRequired(false);//组织机构代码证到期日
	            }else{
	            	nui.get("item.nationalTaxNo").setRequired(true);//国税登记号
	              	nui.get("item.governmentTentNo").setRequired(true);//地税登记号
	              	nui.get("item.registrCd").setRequired(true);//注册登记号
	              	if(text.item.areaType=="1"){//境内
	              		nui.get("item.orgRegisterCd").setRequired(true);//组织机构代码
	              		nui.get("item.orgRegisterEndDate").setRequired(true);//组织机构代码证到期日
	              	}else{//境外
	              		nui.get("item.orgRegisterEndDate").setRequired(false);//组织机构代码证到期日
	              	}
	            }
	           	form.validate();
	            //字典过滤，过滤掉202的证件类型
	   			var arr = git.getDictDataUnFilter("CDKH0002",'202');
				nui.get("item.legalCertType").setData(arr);
                 //是否黑名单
			 		var para = {"partyId":'<%=request.getParameter("partyId")%>'};
			   	    msg = exeRule("RCSM_1003","1",para);
			   	    if(null != msg && '' != msg){
				   	     nui.get('item.whetherBlackList').setValue(0);
			   	    }else{
			   	    	 nui.get('item.whetherBlackList').setValue(1);
			   	    }
			   	    //我行股东标识
			   	    para = {"certType":'202',"certNum":text.item.orgRegisterCd};
	                msg = exeRule("RCSM_1001","1",para);
			   	    if(null != msg && '' != msg){
				   	     nui.get('item.stockholderOfBank').setValue(0);
			   	    }else{
			   	    	 nui.get('item.stockholderOfBank').setValue(1);
			   	    } 
			   	    //是否我行关联方
			   	    msg = exeRule("RCSM_1002","1",para);
			   	    if(null != msg && '' != msg){
				   	     nui.get('item.isBasebankRelaCust').setValue(0);
			   	    }else{
			   	    	 nui.get('item.isBasebankRelaCust').setValue(1);
			   	    }
			   	    //alert(nui.get("item.singleLpCustInd").getValue());
	        }
    	});
    	}
	});
  
	//区域类型事件
	function dataCheck(){
		var area = nui.get("item.areaType").getValue();//境内外标识
		var isPotentialCust = nui.get("party.isPotentialCust").getValue();//是否授信客户
		var unifySocietyCreditNum = nui.get("item.unifySocietyCreditNum").getValue();//统一社会信用代码
		 var ecifPartyNum = "<%=request.getParameter("ecifPartyNum")%>" ;
		if('1'==isPotentialCust&&area=="1"){//境内，授信客户为必输
			nui.get("item.middelCode").setRequired(true);
		}else{
			nui.get("item.middelCode").setRequired(false);
		}
	}
	
	
 	//----------------------事件处理区--------------------//
 	//校验客户完整性
 	function validationAll(){
 		form.validate();
        if (form.isValid()==false) {
        	nui.alert("请完整填写信息！");
        	return;
        }
 		//校验是否保存附属信息
 		var json = {"partyId":pi};
   	    msg = exeRule("RCSM_0003","1",json);
   	    if(null != msg && '' != msg){
	   	     nui.alert(msg);
	   	     return;
   	     }
   	    //校验是否保存注册资本信息
 		var json = {"partyId":pi};
   	    msg = exeRule("RCSM_0007","1",json);
   	    if(null != msg && '' != msg){
	   	     nui.alert(msg);
	   	     return;
   	     }
 		
 		//公积金委托贷款 不校验财报
 		msgjj = exeRule("RCSM_1009","1",json);	
 		//校验是否保存财务信息（个体工商户不校验）
   	    msg = exeRule("RCSM_0004","1",json);
   	    debugger;
   	    if(null != msgjj && '' != msgjj){
	   	      	}else{
	   	    if(null != msg && '' != msg){
	   	     nui.alert(msg);
	   	     return;
   	     }
	   	      	}
   	     
   	     //校验是否保存信用信息
 		var json = {"partyId":pi};
   	    msg = exeRule("RCSM_0008","1",json);
   	    if(null != msg && '' != msg){
	   	     nui.alert(msg);
	   	     return;
   	     }
 		
 		//校验是否保存实际控制人信息
   	    msg = exeRule("RCSM_0005","1",json);
   	    if(null != msg && '' != msg){
	   	     nui.alert(msg);
	   	     return;
   	     }
 		//校验通过后，修改客户状态标志，改为3：有效
 		save(3);
 	
 	}
 	   	//校验统一社会信用代码
	function checkUnifySocietyCreditNum(e){
		var unifySocietyCreditNum = e.value;
	 	if(null != unifySocietyCreditNum && '' != unifySocietyCreditNum){
	 	//校验统一社会信用代码,第9-17位符合组织机构代码校验
	 	e.value=unifySocietyCreditNum.substring(8,16)+"-"+unifySocietyCreditNum.substring(16,17);
	 	var registerCode=unifySocietyCreditNum.substring(8,16)+"-"+unifySocietyCreditNum.substring(16,17);
	   	isValidCompID(e);
	   	//isValidUnifyNum(e);
	 	if (e.isValid) {
	 	   if(unifySocietyCreditNum!=unifySocietyCreditNumEdit){
	   	      	var json = {"certno":unifySocietyCreditNum,"legorg":legorg};
	   	      	msg = exeRule("RCSM_unifySocietyCreditNum","1",json);
	   	      	if(null != msg && '' != msg){
		   	      e.errorText = msg;
	              e.isValid = false;
	   	      	}else{
					nui.get("item.orgRegisterCd").setValue(unifySocietyCreditNum.substring(8,16)+"-"+unifySocietyCreditNum.substring(16,17));
	   	      	}
	   	      	}
	   	      	if(registerCode!=orgRegisterCdEdit){
	   	      	var json1z = {"certno":registerCode,"legorg":legorg};
	   	      	msg = exeRule("RCSM_0011","1",json1z);
	   	      	if(null != msg && '' != msg){
		   	      e.errorText = msg;
	              e.isValid = false;
	   	      	}
	   	      	}
	   	 	}
	 	}
	 	
	}
    <%--保存客户时的操作 --%>
	function update(v){
		form.validate();
		
        if (form.isValid()==false) {
        	nui.alert("请完整填写信息！");
        	return;
        }
        //校验通过，保存数据
        save(v);
	}
	//不检验，直接保存数据
	function save(v){

/* 		var middleCode=nui.get("item.middelCode").getValue();
		var json = {"certno" : middleCode,"partyId":pi};
		msg = exeRule("RCSM_middleCode", "1", json);
			if (null != msg && '' != msg) {
			   nui.alert(msg);
				return;
		} */

		var examineState = nui.get("party.examineState").getValue();
		//设置客户状态，1表示正常保存，2表示临时保存
		if (examineState == '3') {//如果客户已经通过完整性校验
			nui.get("party.examineState").setValue(3);
		} else {
			nui.get("party.examineState").setValue(v);
		}
		git.mask("form1");
		var o = form.getData();
		var json = nui.encode(o);
		$.ajax({
			url : "com.bos.csm.corporation.corporation.UpdateCorpInfoEcif.biz.ext",   //UpdateCorpInfoEcif
			type : 'POST',
			data : json,
			cache : false,
			contentType : 'text/json',
			success : function(text) {
				git.unmask("form1");
 				if("AAAAAAA"!=text.ecifMsg.msg){
		             	nui.alert("调用ECIF接口失败:"+text.ecifMsg.msgg);
		             
		             	   return ;
            	}
				if (text.msg) {
					alert(text.msg);
					return;
				} else {
					if ('3' == v) {
						nui.alert("完整性校验通过！");
						//调用押品接口传数据
						json = json+nui.encode({"customertype":"01"});
			      	    $.ajax({
				        	url: "com.bos.csm.pub.custSyn.custSynForCorp.biz.ext",
				        	type: 'POST',
				        	data: json,
				        	cache: false,
				        	contentType:'text/json',
				        	success: function (text) {
				        	}
						});
						
					} else {
		
						nui.alert("保存成功！");
						
					}
					//设置工信部规模
					var scale = text.scale;
					nui.get("item.enterpriseScaleGx").setValue(scale);
					nui.get("item.countBoreEnterScale").setValue(scale);
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				git.unmask("form1");
				nui.alert(jqXHR.responseText);
			}
		});
					unifySocietyCreditNum=nui.get("item.unifySocietyCreditNum").getValue();
				orgRegisterCd=nui.get("item.orgRegisterCd").getValue();
	}
	//企业出资人经济成分
	function selectEconomicSectorCode(e) {
		var btnEdit = this;
		nui.open({
			url : nui.context + "/pub/dict/code_tree.jsp?dicttypeid=CDKH0028",
			title : "选择字典项",
			width : 800,
			height : 450,
			ondestroy : function(action) {
				if (action == "ok") {
					var iframe = this.getIFrameEl();
					var data = iframe.contentWindow.currentNode;
					data = nui.clone(data);
					if (data) {
						btnEdit.setValue(data.dictid);
						btnEdit.setText(data.dictname);
					}
				}
				if (action == "clear") { //清空选择的内容
					btnEdit.setValue("");
					btnEdit.setText("");
				}
			}
		});
	}

	
	//登记注册类型
	function selectRegistType(e) {
		var btnEdit = this;
		nui.open({
			url : nui.context + "/pub/dict/code_tree.jsp?dicttypeid=CDKH0024",
			title : "选择字典项",
			width : 800,
			height : 450,
			ondestroy : function(action) {
				if (action == "ok") {
					var iframe = this.getIFrameEl();
					var data = iframe.contentWindow.currentNode;
					data = nui.clone(data);
					if (data) {
						btnEdit.setValue(data.dictid);
						btnEdit.setText(data.dictname);
					}
				}
				if (action == "clear") { //清空选择的内容
					btnEdit.setValue("");
					btnEdit.setText("");
				}
			}
		});
	}

	
		<%--选择行业分类 --%>
	function selectTrade(e) {
		var json = nui.encode({parentId:nui.get("item.industrialTypeCd").getValue(),typeId:"CDKH0095"});
	     $.ajax({
	        url: "com.bos.csm.pub.getDict.getDict.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: true,
	        contentType:'text/json',
	        success: function (text) {
	            git.unmask();
	            nui.get("item.industrialTypeBigCd").setData(text.levels);
	            nui.get("item.industrialTypeBigCd").setValue(null);
	            nui.get("item.industrialTypeMidCd").setData(null);
	            nui.get("item.industrialTypeMidCd").setValue(null);
	            nui.get("item.industrialTypeSamllCd").setData(null);
	            nui.get("item.industrialTypeSamllCd").setValue(null);
	            nui.get("industrialCode").setValue(null);
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            git.unmask();
	            nui.alert(jqXHR.responseText);
	        }
	     });
	}
	
	function selectTrade2(e) {
		var json = nui.encode({parentId:nui.get("item.industrialTypeBigCd").getValue(),typeId:"CDKH0095"});
	     $.ajax({
	        url: "com.bos.csm.pub.getDict.getDict.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: true,
	        contentType:'text/json',
	        success: function (text) {
	            git.unmask();
	            nui.get("item.industrialTypeMidCd").setData(text.levels);
	          //  nui.get("item.industrialTypeMidCd").setValue(fromdata2);
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            git.unmask();
	            nui.alert(jqXHR.responseText);
	        }
	     });
	}
	function selectTrade3(e) {
		var json = nui.encode({parentId:nui.get("item.industrialTypeMidCd").getValue(),typeId:"CDKH0095"});
	     $.ajax({
	        url: "com.bos.csm.pub.getDict.getDict.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: true,
	        contentType:'text/json',
	        success: function (text) {
	            git.unmask();
	            nui.get("item.industrialTypeSamllCd").setData(text.levels);
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            git.unmask();
	            nui.alert(jqXHR.responseText);
	        }
	     });
	}
	//选择了行业小类后 返现行业代码字段
	function selectCode(){
		var industrialCode = nui.get("item.industrialTypeSamllCd").getValue();
		if(industrialCode){
			nui.get("industrialCode").setValue(industrialCode);
		}
	}
	
	function initIndustry(parentId,childId) {
		var json = nui.encode({parentId:nui.get(parentId).getValue(),typeId:"CDKH0095"});
	     $.ajax({
	        url: "com.bos.csm.pub.getDict.getDict.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: true,
	        contentType:'text/json',
	        success: function (text) {
	            git.unmask();
	            nui.get(childId).setData(text.levels);
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            git.unmask();
	            nui.alert(jqXHR.responseText);
	        }
	     });
	}

	//选择是否是第三客户
	function selectThirdCust() {
		if (qote != "1") {
			var istc = nui.get("item.isThirdCust").getValue();
			nui.get("item.thirdCustTypeCd").setValue("");
			if (istc == "1") {
				nui.get("item.thirdCustTypeCd").setRequired(true);
				nui.get("item.thirdCustTypeCd").setEnabled(true);
			} else {
				nui.get("item.thirdCustTypeCd").setRequired(false);
				nui.get("item.thirdCustTypeCd").setEnabled(false);
			}
			form.validate();
		}
	}

	//校验中征码是否唯一
	function checkMiddelCodeUnique(e) {
		var registerCode = e.value;
		if (e.isValid) {
			var json = {
				"certno" : registerCode,
				"partyId" : pi
			};
			msg = exeRule("RCSM_0112", "1", json);
			if (null != msg && '' != msg) {
				e.errorText = msg;
				e.isValid = false;
			}
		}
	}

	//法人证件验证
	function onValidateCertificateCode(e) {
		var certType = nui.get("item.legalCertType").getValue();
		var certNum = nui.get("item.legalCertificateNo").getValue();
		if ('101' == certType || '102' == certType) {
			var validateMsg = CsmValidateobj.validateIDCard(certNum, null);
			if (null != validateMsg) {
				//验证身份证号码
				e.isValid = false;
				e.errorText = validateMsg;
			}
		}
	}
	
		//设置证件到期日长期
	function setDate(e){
		if(e.checked==true){
		nui.get("item.legalCertificateEndDate").setValue("9999-12-31");
		nui.get("item.legalCertificateEndDate").setEnabled(false);
		}else{
		nui.get("item.legalCertificateEndDate").setEnabled(true);
		nui.get("item.legalCertificateEndDate").setValue("");
		}
	}
	//设置注册登记证到期日长期
	function setDate1(e){
		if(e.checked==true){
		nui.get("item.registerEndDate").setValue("9999-12-31");
		nui.get("item.registerEndDate").setEnabled(false);
		}else{
		nui.get("item.registerEndDate").setEnabled(true);
		nui.get("item.registerEndDate").setValue("");
		}
	}
	
	
</script>
</body>
</html>