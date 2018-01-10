<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): chenchuan@git.com.cn
-->
<title>合作中介客户基本信息</title>
<%@include file="/common/nui/common.jsp"%>
<script type="text/javascript" src="<%=contextPath%>/csm/js/commValidate.js"></script>
<script type="text/javascript" src="<%=contextPath%>/csm/js/csmValidate.js"></script>
<%@page import="com.bos.pub.GitUtil"%>
</head>
<body>
	<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input name="party.partyId" class="nui-hidden nui-form-input"/>
	<input name="thirdParty.partyId" class="nui-hidden nui-form-input"/>
	<fieldset>
  	<legend>
    	<span>概况</span>
    </legend>
    	<div class="nui-dynpanel" style="border:none" columns="4">
    	<label>ECIF客户编号：</label>
		<input id="party.ecifPartyNum" name="party.ecifPartyNum" required="true" readonly="true" Enabled="ture" class="nui-textbox nui-form-input" vtype="maxLength:30"/>
    	
   		<label>CRMS客户编号：</label>
		<input id="party.partyNum" name="party.partyNum" readonly="true" Enabled="ture" class="nui-text nui-form-input" vtype="maxLength:30"/>
		
		<label>合作中介类型：</label>
		<input id="thirdParty.corpCustomerTypeCd" name="thirdParty.corpCustomerTypeCd" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_KHCD7002" />
		
		<label>客户名称：</label>
		<input id="party.partyName" name="party.partyName" required="true" class="nui-textbox nui-form-input" vtype="maxLength:100"/>	
		
		<label>别名：</label>
		<input id="thirdParty.englishName" name="thirdParty.englishName" required="false" class="nui-textbox nui-form-input" vtype="maxLength:100"/>
   		</div>
    </fieldset>
    
    <fieldset>
  	<legend>
    	<span>证件信息</span>
    </legend>
    	<div class="nui-dynpanel" style="border:none" columns="4">
	    	<label>法人代表姓名：</label>
			<input id="thirdParty.legalName" name="thirdParty.legalName" required="true" class="nui-textbox nui-form-input" vtype="maxLength:100"/>
			
			<label>法人代表证件类型：</label>
			<input id="thirdParty.legalCertType" name="thirdParty.legalCertType" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CDKH0002"/>
			
			<label>法人代表证件号码：</label>
			<input id="thirdParty.legalCertificateNo" name="thirdParty.legalCertificateNo" required="true" class="nui-textbox nui-form-input" vtype="maxLength:70"onvalidation="onValidateCertificateCode"/>
			
			<label>证件到期日：</label>
			<input id="thirdParty.legalCertificateEndDate" name="thirdParty.legalCertificateEndDate" required="true" class="nui-datepicker nui-form-input" vtype="maxLength:100"/>
    		
			<label>长期</label>
			<input id="longTime" name="longTime"  onclick="setDate(this)" required="false" class="nui-checkbox"  />
    		
    		<label>营业执照：</label>
			<input id="thirdParty.registrCd" name="thirdParty.registrCd" vtype="maxLength:30"   required="true" enabled="false"  class="nui-textbox nui-form-input"/>
    	
    		<label>统一社会信用代码：</label>
			<input id="thirdParty.unifySocietyCreditNum" name="thirdParty.unifySocietyCreditNum" onblur="dataCheck"  enabled="true"   vtype="minLength:18;maxLength:18" onvalidation="checkUnifySocietyCreditNum" class="nui-textbox nui-form-input"/>
			
    		<label>注营业执照登记日期：</label>
			<input id="thirdParty.registerDate" name="thirdParty.registerDate" required="true" maxDate="<%=GitUtil.getBusiDateStr()%>" class="nui-datepicker nui-form-input" vtype="maxLength:100"/>
			
    		<label>注册资本币种：</label>
			<input id="thirdParty.registerAssetsCurrencyCd" name="thirdParty.registerAssetsCurrencyCd" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CD000001" dvalue="CNY"/>
			
    		<label>营业执照登记证到期日：</label>
			<input id="thirdParty.registerEndDate" name="thirdParty.registerEndDate" required="true" minDate="<%=GitUtil.getBusiDateStr()%>" class="nui-datepicker nui-form-input" vtype="maxLength:100"/>
			
			<label>长期</label>
			<input id="longTime1" name="longTime1"  onclick="setDate1(this)" required="false" class="nui-checkbox"  />
			
    		<label>注册资本：</label>
			<input id="thirdParty.registerAssets" name="thirdParty.registerAssets" required="true" class="nui-textbox nui-form-input" vtype="float;maxLength:20" dataType="currency"/>
			
    		<label>经营范围：</label>
			<input id="thirdParty.businessScope" name="thirdParty.businessScope" required="true" class="nui-textarea nui-form-input" vtype="maxLength:800"/>
    	
    		<label>组织机构代码：</label>
			<input id="thirdParty.orgRegisterCd" name="thirdParty.orgRegisterCd" required="true"  enabled="false"  class="nui-textbox nui-form-input"  /> 
			
			<label>组织机构代码证到期日：</label>
			<input id="thirdParty.orgRegisterEndDate" name="thirdParty.orgRegisterEndDate" allowInput="false" minDate="<%=GitUtil.getBusiDateStr()%>" class="nui-datepicker nui-form-input"  required="true" format="yyyy-MM-dd"/>
			
			<label>中征码：</label>
			<input id="thirdParty.middelCode" name="thirdParty.middelCode" required="false" class="nui-textbox nui-form-input" vtype="int;minLength:16;maxLength:16" />
			
			<label>机构信用代码：</label>
			<input id="thirdParty.orgCreditCode" name="thirdParty.orgCreditCode" vtype="int;minLength:18;maxLength:18" required="false" class="nui-textbox nui-form-input"/>
			
			<label>国税登记证号码：</label>
			<input id="thirdParty.nationalTaxNo" name="thirdParty.nationalTaxNo" onvalidation="CsmValidateobj.onValidateTax"  required="true" class="nui-textbox nui-form-input" vtype="rangeLength:15,20" />
			
			<label>地税登记证号码：</label>
			<input id="thirdParty.governmentTentNo" name="thirdParty.governmentTentNo" onvalidation="CsmValidateobj.onValidateTax"  required="true" class="nui-textbox nui-form-input" vtype="rangeLength:15,20"/>
			
			 <!-- 
			<label>状态：</label>
			<input id="thirdParty.custStatus" name="thirdParty.custStatus" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_KHCD0223" vtype="maxLength:30"/>
			-->
    	</div>
    </fieldset>
    
    <fieldset>
  	<legend>
    <span>附属信息</span>
    </legend>
    	<div class="nui-dynpanel" style="border:none" columns="4">
    	<label>经营许可证：</label>
		<input id="thirdParty.businessCd" name="thirdParty.businessCd"   required="false" class="nui-textbox nui-form-input" vtype="maxLength:30"/>
			
		<label>证件到期日：</label>
		<input id="thirdParty.businessEndDate" name="thirdParty.businessEndDate"   required="false" class="nui-datepicker nui-form-input" onbuttonclick="" vtype="maxLength:30"/>
    	
    	<label>长期</label>
			<input id="longTime2" name="longTime2"  onclick="setDate2(this)" required="false" class="nui-checkbox"  />
			
    	<label>联系地址：</label>
		<input id="thirdParty.contactAdress" name="thirdParty.contactAdress"   required="true" class="nui-textbox nui-form-input" vtype="maxLength:150"/>
			
		<label>联系电话：</label>
		<input id="thirdParty.contactPhone" name="thirdParty.contactPhone"   required="true" class="nui-textbox nui-form-input" vtype="int;maxLength:30"/>
		
		<label>合作起期：</label>
		<input id="thirdParty.teamworkStart" name="thirdParty.teamworkStart"   required="true" class="nui-datepicker nui-form-input" onbuttonclick="" maxDate="<%=GitUtil.getBusiDateStr() %>" vtype="maxLength:30"/>
		
		<label>合作止期：</label>
		<input id="thirdParty.teamworkEnd" name="thirdParty.teamworkEnd"   required="true" class="nui-datepicker nui-form-input" onbuttonclick="" minDate="<%=GitUtil.getBusiDateStr() %>" vtype="maxLength:30"/>
	</div>
    </fieldset>
    
     <fieldset>
  	<legend>
    <span>系统信息</span>
    </legend>
    	<div class="nui-dynpanel" style="border:none" columns="4">
			
			<label>登记日期：</label>
			<input id="party.createTime" name="party.createTime"  Enabled="false" class="nui-datepicker nui-form-input"   format="yyyy-MM-dd"/>
			
			<label>更新人员：</label>
			<div><input class="nui-text nui-form-input"  id="thirdParty.updateUserNum" name="thirdParty.updateUserNum" dictTypeId="user" enabled="true"/></div>
		
			<label>更新日期：</label>
			<input id="thirdParty.updateTime" name="thirdParty.updateTime" Enabled="false" class="nui-datepicker nui-form-input" format="yyyy-MM-dd"/>
    	</div>
    	
    	
    </fieldset>
	<div class="nui-toolbar" style="text-align:right;" 
	    borderStyle="border:0;">
	   	<a id="btnSaveTmp" class="nui-button"  iconCls="icon-save" onclick="update(1)">临时保存</a>
	    <a id="btnSave" class="nui-button"  iconCls="icon-save" onclick="update(2)">保存</a>
		</div>
	</div>

<script type="text/javascript">
	nui.parse();
	git.mask("form1");
	var form = new nui.Form("#form1");
	var partyId = "<%=request.getParameter("partyId") %>";
	var qote = "<%=request.getParameter("qote")%>" ;
	    var legorg = "<%=com.bos.pub.GitUtil.getLegorg()%>";
		var unifySocietyCreditNumEdit;
		var orgRegisterCdEdit;
	nui.get("btnSaveTmp").hide();
	if(qote==1){
		nui.get("btnSave").hide();
		nui.get("btnSaveTmp").hide();
		form.setEnabled(false);
	}	
	//区域类型事件
	function dataCheck(){
		var unifySocietyCreditNum = nui.get("thirdParty.unifySocietyCreditNum").getValue();//统一社会信用代码
		if(unifySocietyCreditNum!=""){//如果已录入统一社会信用代码
			nui.get("thirdParty.orgRegisterCd").setRequired(false);
			nui.get("thirdParty.registrCd").setRequired(false);
		}else{
			nui.get("thirdParty.orgRegisterCd").setRequired(true);
			nui.get("thirdParty.registrCd").setRequired(true);
		}
	}

	var json = nui.encode({"partyId":partyId});
	function init(){
	if(qote!=1){
				$.ajax({
		        url: "com.bos.csm.thirdParty.thirdParty.getThirdPartyEcif.biz.ext",//getThirdPartyEcif
		        type: 'POST',
		        data: json,
		        cache: false,
		        contentType:'text/json',
		        success: function (text) {
		        	git.unmask("form1");
		        	if(text.msg){
		        		alert(text.msg);
		        	} else {
		                var o = nui.decode(text);
		                //初始化证件到期日
		               	if(text.thirdParty.legalCertificateEndDate=="9999-12-31"){
		             	nui.get("longTime").setChecked(true);
		             	nui.get("thirdParty.legalCertificateEndDate").setEnabled(false);
		               	}else{
		               	nui.get("longTime").setChecked(false);
		               	nui.get("thirdParty.legalCertificateEndDate").setEnabled(true);
		               	}
		                //初始化证件到期日1
		               	if(text.thirdParty.businessEndDate=="9999-12-31"){
		             	nui.get("longTime1").setChecked(true);
		             	nui.get("thirdParty.registerEndDate").setEnabled(false);
		               	}else{
		               	nui.get("longTime1").setChecked(false);
		               	nui.get("thirdParty.registerEndDate").setEnabled(true);
		               	}
		               	orgRegisterCdEdit=text.map.thirdParty.orgRegisterCd;
    	                unifySocietyCreditNumEdit=text.map.thirdParty.unifySocietyCreditNum;
		                form.setData(o);
		                window.form1Data=form.getData();
		                 //字典过滤，过滤掉202的证件类型
	   					var arr = git.getDictDataUnFilter("CDKH0002",'202');
						nui.get("thirdParty.legalCertType").setData(arr);
						var unifySocietyCreditNum = nui.get("thirdParty.unifySocietyCreditNum").getValue();//统一社会信用代码
						if(unifySocietyCreditNum!=""){//如果已录入统一社会信用代码
							nui.get("thirdParty.orgRegisterCd").setRequired(false);
							nui.get("thirdParty.registrCd").setRequired(false);
						}else{
							nui.get("thirdParty.orgRegisterCd").setRequired(true);
							nui.get("thirdParty.registrCd").setRequired(true);
						}
		            }
		            		/* if("AAAAAAA"!=text.map.msg){
								 nui.alert(text.map.msgg);
								  git.unmask("form1");
								} */
		        }
	    	});
	}else{
				$.ajax({
		        url: "com.bos.csm.thirdParty.thirdParty.getThirdParty.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        contentType:'text/json',
		        success: function (text) {
		        	git.unmask("form1");
		        	if(text.msg){
		        		alert(text.msg);
		        	} else {
		                var o = nui.decode(text);
		                orgRegisterCdEdit=text.thirdParty.orgRegisterCd;
    	                unifySocietyCreditNumEdit=text.thirdParty.unifySocietyCreditNum;
		                form.setData(o);
		                window.form1Data=form.getData();
		                 //字典过滤，过滤掉202的证件类型
	   					var arr = git.getDictDataUnFilter("CDKH0002",'202');
						nui.get("thirdParty.legalCertType").setData(arr);
						var unifySocietyCreditNum = nui.get("thirdParty.unifySocietyCreditNum").getValue();//统一社会信用代码
						if(unifySocietyCreditNum!=""){//如果已录入统一社会信用代码
							nui.get("thirdParty.orgRegisterCd").setRequired(false);
							nui.get("thirdParty.registrCd").setRequired(false);
						}else{
							nui.get("thirdParty.orgRegisterCd").setRequired(true);
							nui.get("thirdParty.registrCd").setRequired(true);
						}
		            }
		        }
	    	});
	}

	}
	init();
    
    <%--保存客户时的操作 --%>
	function update(v){
		if(v==2){
			form.validate();
        	if (form.isValid()==false) {
        		alert("请完整填写信息！");
        		return;
        	}
		}
        git.mask("form1");
		var o1 = form.getData();//修改后的数据
        var json = nui.encode(o1);
        
        //shangmf
        //alert(json);
        //var tocollMap = eval(json);
        //alert(tocollMap.thirdParty);
        //var tocollJson = {"ope_flag":"0","busi_begin_date":(nui.get("thirdParty.teamworkStart").getValue()).substr(0,10),"busi_end_date":(nui.get("thirdParty.teamworkEnd").getValue()).substr(0,10),"linkman":nui.get("thirdParty.legalName").getValue(),"orgnum":nui.get("thirdParty.orgRegisterCd").getValue(),"relativetype":nui.get("thirdParty.contactPhone").getValue(),"orgid":nui.get("party.ecifPartyNum").getValue(),"orgname":nui.get("party.partyName").getValue()};
        var corpCustomerTypeCd = nui.get("thirdParty.corpCustomerTypeCd").getValue();
        //客户编号暂时用 party.partyNum(crms客户号)，后续改为party.ecifPartyNum(ecif客户号)
        var tocollJson = nui.encode({ope_flag:"0","busi_begin_date":(nui.get("thirdParty.teamworkStart").getValue()).substr(0,10),"busi_end_date":(nui.get("thirdParty.teamworkEnd").getValue()).substr(0,10),"linkman":nui.get("thirdParty.legalName").getValue(),"orgnum":nui.get("thirdParty.orgRegisterCd").getValue(),"relativetype":nui.get("thirdParty.contactPhone").getValue(),"orgid":nui.get("party.partyNum").getValue(),"orgname":nui.get("party.partyName").getValue(),"status_changedate":nui.get("thirdParty.updateTime").getValue()});
                
        $.ajax({
            url: "com.bos.csm.thirdParty.thirdParty.updateThirdPartyEcif.biz.ext",//updateThirdParty
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            				if("AAAAAAA"!=text.map.msg){
							 	 nui.alert("调用ECIF失败："+text.map.msgg); 
								  git.unmask("form1");
								  return;
								}else{
								            	git.unmask("form1");
            	if(text.msg){
            		alert(text.msg);
            		return;
            	} else {
            	
            		//add by shangmf 20170512：增加同步押品的处理	Begin:如果为评估机构则通知押品
					if(v==2){	
						if(corpCustomerTypeCd == '5'){
						$.ajax({
      						url: "com.bos.csm.thirdParty.thirdParty.updateThirdPartyByWebService.biz.ext",
      						type: 'POST',
      						data: tocollJson,
      						cache: false,
      						contentType:'text/json',
      						success: function (text) {
      								git.unmask("form1");
      								if(text.msg){
      									alert(text.msg);
      									return;
      								} else {
										alert("保存成功，同步押品系统成功！");
										init();																		
      								}
      							},
      							error: function (jqXHR, textStatus, errorThrown) {
      					    		git.unmask("form1");
      					    		nui.alert(jqXHR.responseText);
      							}
     						});
						}		
						else{
							alert("保存成功！");
							init();
						}
					
					}else{
						alert("保存成功！");
						init();
					}
					//add by shangmf 20170512：增加同步押品的处理	End		
					
            	}
								}

            },
            error: function (jqXHR, textStatus, errorThrown) {
                git.unmask("form1");
                nui.alert(jqXHR.responseText);
            }
        });
        	    unifySocietyCreditNum=nui.get("thirdParty.unifySocietyCreditNum").getValue();
				orgRegisterCd=nui.get("thirdParty.orgRegisterCd").getValue();
	}
	
	//---------------合法校验区--------------------//
	
	//校验营业执照号码是否唯一
	function checkBusinessLicenseNumUnique(e) {
		if (e.isValid) {
			var json = {
				"certno" : e.value,"partyId" : partyId
			};
			msg = exeRule("RCSM_0114", "1", json);
			if (null != msg && '' != msg) {
				e.errorText = msg;
				e.isValid = false;
			}
		}
	}

	//校验组织机构号码是否唯一
	function checkOrgRegisterCdUnique(e) {
/* 	   	isValidCompID(e);
	   	if (e.isValid) {
			var json = {
				"certno" : e.value,"partyId" : partyId
			};
			msg = exeRule("RCSM_0113", "1", json);
			if (null != msg && '' != msg) {
				e.errorText = msg;
				e.isValid = false;
			}
		} */
	}

	//校验中征码是否唯一
	function checkMiddelCodeUnique(e) {
		if (e.isValid) {
			var json = {
				"certno" : e.value,
				"partyId" : partyId
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
		var certType = nui.get("thirdParty.legalCertType").getValue();
		var certNum = nui.get("thirdParty.legalCertificateNo").getValue();
		if ('101' == certType || '102' == certType) {
			var validateMsg = CsmValidateobj.validateIDCard(certNum, null);
			if (null != validateMsg) {
				//验证身份证号码
				e.isValid = false;
				e.errorText = validateMsg;
			}
		}
	}
	
	   	//校验统一社会信用代码
	function checkUnifySocietyCreditNum(e){
		var unifySocietyCreditNum = e.value;
	 	if(null != unifySocietyCreditNum && '' != unifySocietyCreditNum){
	 	//校验统一社会信用代码,第9-17位符合组织机构代码校验
	 	e.value=unifySocietyCreditNum.substring(8,16)+"-"+unifySocietyCreditNum.substring(16,17);
	   	isValidCompID(e);
	 	if (e.isValid) {
	   	      	var json = {"certno":unifySocietyCreditNum,"partyId":partyId};
	   	      	msg = exeRule("RCSM_0116","1",json);
	   	      	if(null != msg && '' != msg){
		   	      e.errorText = msg;
	              e.isValid = false;
	   	      	}
	   	 	}
	 	}
	}
	
		//设置长期
	function setDate(e){
		if(e.checked==true){
		nui.get("thirdParty.legalCertificateEndDate").setValue("9999-12-31");
		nui.get("thirdParty.legalCertificateEndDate").setEnabled(false);
		}else{
		nui.get("thirdParty.legalCertificateEndDate").setEnabled(true);
		nui.get("thirdParty.legalCertificateEndDate").setValue("");
		}
	}
				//设置长期1
	function setDate1(e){
		if(e.checked==true){
		nui.get("thirdParty.registerEndDate").setValue("9999-12-31");
		nui.get("thirdParty.registerEndDate").setEnabled(false);
		}else{
		nui.get("thirdParty.registerEndDate").setEnabled(true);
		nui.get("thirdParty.registerEndDate").setValue("");
		}
	}
	function setDate2(e){
		if(e.checked==true){
		nui.get("thirdParty.businessEndDate").setValue("9999-12-31");
		nui.get("thirdParty.businessEndDate").setEnabled(false);
		}else{
		nui.get("thirdParty.businessEndDate").setEnabled(true);
		nui.get("thirdParty.businessEndDate").setValue("");
		}
	}
	
		 	   	//校验统一社会信用代码
	function checkUnifySocietyCreditNum(e){
		     debugger;
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
					nui.get("thirdParty.orgRegisterCd").setValue(unifySocietyCreditNum.substring(8,16)+"-"+unifySocietyCreditNum.substring(16,17));
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
</script>
</body>
</html>

