<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): chenchuan
  - Date: 2013-11-18 15:26:25
  - Description:
-->
<head>
<title>同业客户关键信息</title>
<%@include file="/common/nui/common.jsp"%>
<script type="text/javascript" src="<%=contextPath%>/csm/js/commValidate.js"></script>
</head>
<body>
	<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<fieldset>
		<legend>
		   <span>概况</span>
		</legend>
		
	    <input id="financialInstitution.partyId" class="nui-hidden nui-form-input" name="financialInstitution.partyId" value="<%=request.getParameter("partyId")%>">
		<div class="nui-dynpanel" columns="4">
			<label>客户编号：</label>
			<input id="financialInstitution.partyNum" class="nui-text nui-form-input" name="financialInstitution.partyNum"/>

		    <label>客户名称：</label>
		    <input id="financialInstitution.partyName" class="nui-textbox nui-form-input" name="financialInstitution.partyName" required="true"/>

			<label>区域类型：</label>
   	    	<input id="financialInstitution.areaType" required="true" name="financialInstitution.areaType" onvaluechanged="dataCheck()" class="nui-dictcombobox nui-form-input" 
   	    	 dictTypeId="XD_KHCD0210">
   	    	 
   	    	<label>金融机构类型：</label>
		 	<input  id="financialInstitution.financeEnterpriseType" required="true" name="financialInstitution.financeEnterpriseType"  class="nui-dictcombobox nui-form-input" 
		 			allowInput="false"  dictTypeId="ECIF_JRJGLX01" />
		 	
		 	<label>统一社会信用代码：</label>
			<input id="financialInstitution.unifySocietyCreditNum" name="financialInstitution.unifySocietyCreditNum"class="nui-textbox nui-form-input" onblur="dataCheck()" onvalidation="checkUnifySocietyCreditNum" />
		 			
		 	<label>营业执照:</label>
   	    	<input id="financialInstitution.registerCode" name="financialInstitution.registerCode"  class="nui-textbox nui-form-input" onvalidation="checkBusinessLicenseNumUnique" vtype="maxLength:32"/> 
   	    	
   	  		<label>组织机构代码：</label>
   	        <input id="financialInstitution.orgRegisterCd"  name="financialInstitution.orgRegisterCd"  required="true"class="nui-textbox nui-form-input" onvalidation="checkOrgRegisterCdUnique" />
   	    			
		   	<label>swift BIC码：</label>
		    <input id="financialInstitution.swiftBicNum" class="nui-textbox nui-form-input" name="financialInstitution.swiftBicNum" onvalidation="checkSwiftCdUnique" />
		</div>
		</fieldset>
	</div>	
	<div class="nui-toolbar" style="text-align:right;" borderStyle="border:0;">
	    <a id="btnSave" class="nui-button" iconCls="icon-save" onclick="update()">保存</a>
	    <a id = "btnClose" class="nui-button" iconCls="icon-close"  onclick="CloseWindow('ok')">关闭</a>
	</div>

	<script type="text/javascript">
		nui.parse();
		git.mask("form1");
		var form = new nui.Form("form1");
		var qote = "<%=request.getParameter("qote")%>" ;
		
		//查看进入
		if(qote==1){
		   form.setEnabled(false);
		   nui.get("btnSave").hide();
		}
		 formInit();
	    //初始化表单数据
	    function formInit(){
	    	var partyId = nui.get("financialInstitution.partyId").getValue();
	    	var param = {"fit":{"partyId":partyId}};
	    	var json = nui.encode(param);
	    	if(partyId!=""){
	    		$.ajax({
	    			url:"com.bos.csm.financialinstitution.financialinstitutioninfo.queryTbCsmFinancialInstitution.biz.ext",
	    			type:"POST",
	    			data:json,
	    			contentType:'text/json',
	    			success:function(data){
	    				git.unmask("form1");
	    				form.setData(data);
                		window.form1Data=form.getData();
	    			},
	    			error:function(jqXHR, textStatus, errorThrown){
	       	   	  	   git.unmask("form1");
	       	   	  	   nui.alert(jqXHR.responseText);
	       	   		}});
	       }
	    }
	function dataCheck(){
		var area = nui.get("financialInstitution.areaType").getValue();//境内外标识
		var unifySocietyCreditNum = nui.get("financialInstitution.unifySocietyCreditNum").getValue();//统一社会信用代码
		if(unifySocietyCreditNum!=""){//如果已录入统一社会信用代码
			nui.get("financialInstitution.registerCode").setRequired(false);
			nui.get("financialInstitution.orgRegisterCd").setRequired(false);
		}else{
			nui.get("financialInstitution.registerCode").setRequired(true);
			if(area=="1"){//境内
				nui.get("financialInstitution.orgRegisterCd").setRequired(true);
			}else{//境外
				nui.get("financialInstitution.orgRegisterCd").setRequired(false);
			}
		}
		if(area=="1"){//境内
			nui.get("financialInstitution.swiftBicNum").setRequired(false);
		}else{
			nui.get("financialInstitution.swiftBicNum").setRequired(true);
		}
	}    
	
      function update(){
	       //校验
	       form.validate();
	       if(form.isValid()==false) {
	       		alert("请完整填写信息!");
	       		return;
	       }
	       git.mask("form1");
	       var o = form.getData();
	       o.co_flag="1";
	       o.financialInstitution.partyName=nui.get("financialInstitution.partyName").getValue().trim();
	       var json = nui.encode(o);
	       $.ajax({
	       	   url:"com.bos.csm.financialinstitution.financialinstitutioninfo.updateTbCsmFinancialInstitution.biz.ext",
	       	   type:'POST',
	       	   data:json,
	       	   contentType:'text/json',
	       	   success:function(data){
	       	   		git.unmask("form1");
	       	   		if(data.msg){
	       	   		 	alert(data.msg);
	       	   		 	return;
	       	   		}else{
	       	   			alert("保存成功");
	       	   			CloseWindow();
	       	   		}
	       	   },
	       	   error:function(jqXHR, textStatus, errorThrown){
	       	   	  git.unmask("form1");
	       	   	  nui.alert(jqXHR.responseText);
	       	   }}); 
	    }
      
      
      //金融机构类型
      function selectFinancialType(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/dict/code_tree.jsp?dicttypeid=CDKH0023",
            title: "选择字典项",
            width: 800,
            height: 450,
            ondestroy: function (action) {    
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.currentNode;
                    data = nui.clone(data);
                    if (data) {
                    	nui.get("financialInstitution.financeEnterpriseType").setValue(data.dictid);
                        nui.get("financialInstitution.financeEnterpriseType").setText(data.dictname);
                        if(data.dictid=="230000"){
                        	nui.get("financialInstitution.swiftBicNum").setRequired(true);
                        }else{
                        	nui.get("financialInstitution.swiftBicNum").setRequired(false);
                        }
                    }
                }
                if (action == "clear") { //清空选择的内容
                	btnEdit.setValue("");
                	btnEdit.setText("");
            	}
        	}
        });            
	}
      
		
   	//校验营业执照号码是否唯一
	function checkBusinessLicenseNumUnique(e){
	 	var registerCode = nui.get("financialInstitution.registerCode").getValue();
	 	var areaType = nui.get("financialInstitution.areaType").getValue();
	 	if (e.isValid) {
	 			var so = window.form1Data;
	 			var s_registerCode;
	 			if(null == so){
			 		s_registerCode=e.value;
			 	}else{
			 		s_registerCode =so.financialInstitution.registerCode;
			 	}
			 	if(s_registerCode != registerCode){
			 		var json = {"certno":registerCode};
		   	      	msg = exeRule("RCSM_0002","1",json);
		   	      	if(null != msg && '' != msg){
			   	      e.errorText = msg;
		              e.isValid = false;
		   	      	}
			 	}
	   	      	
	   	 }
   	  }
   	//校验组织机构号码是否唯一
	function checkOrgRegisterCdUnique(e){
		var orgRegisterCd = e.value;
	 	//校验组织机构代码有效
	   	isValidCompID(e);
	   	//如果有效，接着校验是否唯一
	 	if (e.isValid) {
	 		var so = window.form1Data;
	 		var s_registerCode;
	 		if(null == so){
			 	s_orgRegisterCd=e.value;
			 }else{
			 	s_orgRegisterCd =so.financialInstitution.orgRegisterCd;
			 }
			 if(s_orgRegisterCd != orgRegisterCd){
			 	var json = {"certno":orgRegisterCd};
	   	      	msg = exeRule("RCSM_00021","1",json);
	   	      	if(null != msg && '' != msg){
		   	      e.errorText = msg;
	              e.isValid = false;
	   	      	}
			 }
	   	 }
	}
	//校验中征码是否唯一
	function checkMiddleCodeCdUnique(e){
		var middleCode = e.value;
	   	//如果有效，接着校验是否唯一
	 	if (e.isValid) {
	 		var so = window.form1Data;
	 		var s_middleCode;
	 		if(null == so){
			 	s_middleCode=e.value;
			 }else{
			 	s_middleCode =so.financialInstitution.middleCode;
			 }
			 if(s_middleCode != middleCode){
			 	var json = {"certno":middleCode};
	   	      	msg = exeRule("RCSM_00022","1",json);
	   	      	if(null != msg && '' != msg){
		   	      e.errorText = msg;
	              e.isValid = false;
	   	      	}
			 }
	   	 }
	}
	
	//校验swift码是否唯一
	function checkSwiftCdUnique(e){
		var swiftBicNum = e.value;
	   	//如果有效，接着校验是否唯一
	 	if (e.isValid) {
	 		var so = window.form1Data;
	 		var s_swiftBicNum;
	 		if(null == so){
			 	s_swiftBicNum=e.value;
			 }else{
			 	s_swiftBicNum =so.financialInstitution.swiftBicNum;
			 }
			 if(s_swiftBicNum != swiftBicNum){
			 	var json = {"certno":swiftBicNum};
	   	      	msg = exeRule("RCSM_00023","1",json);
	   	      	if(null != msg && '' != msg){
		   	      e.errorText = msg;
	              e.isValid = false;
	   	      	}
			 }
	   	 }
	}
	
	
	//校验统一社会信用代码
	function checkUnifySocietyCreditNum(e) {
		var unifySocietyCreditNum = e.value;
		if(null != unifySocietyCreditNum && '' != unifySocietyCreditNum){
	 	//校验统一社会信用代码,第9-17位符合组织机构代码校验
	 	e.value=unifySocietyCreditNum.substring(8,16)+"-"+unifySocietyCreditNum.substring(16,17);
	   	isValidCompID(e);
		//判断值是否改变,如改变，则做唯一校验，否则不校验
		if (e.isValid) {
			var so = window.form1Data;
			var s_unifySocietyCreditNum;
			if (null == so) {
				s_unifySocietyCreditNum = unifySocietyCreditNum;
			} else {
				s_unifySocietyCreditNum = so.financialInstitution.unifySocietyCreditNum;
			}
			if (s_unifySocietyCreditNum != unifySocietyCreditNum) {
				var json = {"certno" : unifySocietyCreditNum};
				msg = exeRule("RCSM_unifySocietyCreditNum", "1", json);
				if (null != msg && '' != msg) {
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