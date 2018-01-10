<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): chenchuan
  - Date: 2015-11-11 12:42:24
  - Description:维护实际控制人信息
-->
<head>
<title>维护实际控制人信息</title>
<%@include file="/common/nui/common.jsp"%>
<script type="text/javascript" src="<%=contextPath%>/csm/js/csmValidate.js"></script>

</head>
<body>
<div id="form1" style="width:99.5%;height:auto;overflow:hidden;">
	<input name="ctl.id" id="ctl.id" class="nui-hidden" />
	<input name="ctl.partyId" id="ctl.partyId"  value="<%=request.getParameter("partyId") %>"class="nui-hidden"/>
	<input name="ctl.controlId" id="ctl.controlId" class="nui-hidden"/>
	<div class="nui-dynpanel" columns="4">
	  	<label>实际控制人类型：</label>
		<input id="ctl.actualControllerType" name="ctl.actualControllerType" 
			required="true" class="nui-dictcombobox nui-form-input" dictTypeId="CDKH0034" 
			/>
			
	  	<label>实际控制人名称：</label>
		<input id="ctl.partyName"  name="ctl.partyName" required="true" allowInput="false" 
			class="nui-buttonEdit" onbuttonclick="selectCust" />
		<!-- 
		<label>组织机构代码：</label>
		<input id="ctl.orgRegisterCd" name="ctl.orgRegisterCd" allowInput="false"  
			class="nui-textbox nui-form-input" enabled="false"/>
	  	 -->
	  	<label>证件类型：</label>
		<input id="ctl.cerType"  name="ctl.cerType" 
			class="nui-dictcombobox" dictTypeId="CDKH0002"/>
		
		<label>证件号码：</label>
		<input id="ctl.certNum"  name="ctl.certNum" 
			class="nui-textbox" />

		<label>控制方式：</label>
		<input id="ctl.controlMethod" name="ctl.controlMethod" required="true" 
			class="nui-dictcombobox" dictTypeId="XD_SJCZR000"  style="width:400px;"/>
	</div>
	<div class="nui-toolbar" style="text-align:right;padding-right:20px;border:0;" >
	    <a id="btnSave" class="nui-button"  iconCls="icon-save" onclick="save">保存</a>
	    <a class="nui-button" iconCls="icon-close" onclick="CloseWindow()">关闭</a>
	    <span style="display:inline-block;width:25px;"></span>
	</div>
</div>


<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form1");

	//参与者ID
	var partyId = "<%=request.getParameter("partyId") %>";
	//事件类型
	var qote = "<%=request.getParameter("qote") %>";
	//控制人类型
	var ctlType = "<%=request.getParameter("ctlType") %>";
	//控制人ID
	var id = "<%=request.getParameter("id") %>";
	//如果id没取到值，则置为null,而不是字符串null;
	if('null' == id){
		id = null;
	}
	
// 	if (partyId) {
// 		nui.get("ctl.partyId").setValue(partyId); 
// 	}
	if(qote == '1'){
	
		form.setEnabled(false);
		nui.get("btnSave").hide();
	}
	
	$(document).ready(function(){
		var json=nui.encode({"id":id});
		$.ajax({
            url: "com.bos.csm.corporation.TbCsmControlInfo.getTbCsmControlInfo.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	git.unmask("form1");
        		var o = nui.decode(text);
        		form.setData(o);
        		nui.get("ctl.partyName").setText(text.ctl.partyName);
        		nui.get("ctl.partyId").setValue(partyId); 
            },
            error: function (jqXHR, textStatus, errorThrown) {
				git.unmask("form1");		               
                nui.alert(jqXHR.responseText);
            }
		});
	});
	
	
	function save() {
		form.validate();
		if (form.isValid() == false) {
			alert("请将信息填写完整");
			return;
		}
		
// 		var json1 = {
// 			"controlId" : nui.get("ctl.controlId").getValue(),
// 			"partyId" : nui.get("ctl.partyId").getValue()
// 		};
// 		msg = exeRule("CUS_CONTROL", "1", json1);
// 		if (null != msg && '' != msg) {
// 			nui.alert(msg);
// 			return;
// 		}
		
		git.mask("form1");
		var o=form.getData();
		var json=nui.encode(o);
		
		$.ajax({
	            url: "com.bos.csm.corporation.TbCsmControlInfo.saveTbCsmControlInfo.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            	git.unmask("form1");
	            	if(text.msg){
	            		alert(text.msg);
	            		return;
	            	} else {
	            //		alert("保存成功!");
	            		CloseWindow("ok");
	            	}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                git.ummask("form1");
	                nui.alert(jqXHR.responseText);
	            }
		});
	}
	
	function clearControlInfo(){
		var actualControllerType= nui.get("ctl.actualControllerType").getValue();
  		if(actualControllerType=="2"||actualControllerType=="3"||actualControllerType=="4"||actualControllerType=="5"){//如类型选择其他，股东名称允许“手工输入”
  			nui.get("ctl.cerType").setEnabled(true);
  			nui.get("ctl.certNum").setEnabled(true);
  			nui.get("ctl.partyName").setAllowInput(true);
  		}else{
  		  	nui.get("ctl.cerType").setEnabled(false);
  		  	nui.get("ctl.certNum").setEnabled(false);
  		  	nui.get("ctl.partyName").setAllowInput(false);
  		}	
		nui.get("ctl.partyName").setValue("");
		nui.get("ctl.partyName").setText("");
		//nui.get("ctl.orgRegisterCd").setValue("");
		nui.get("ctl.cerType").setValue("");
		nui.get("ctl.certNum").setValue("");
	
	}
	//实际控制人选择	
	function selectCust(e) {
		// 01:法人；02：自然人
		var actualControllerType = nui.get("ctl.actualControllerType").getValue();
		if(null == actualControllerType || ''==actualControllerType){
		
			nui.alert("请先选择实际控制人类型！");
			return;
		}
		nui.open({
	        url: nui.context + "/csm/pub/queryCorpAndNartual.jsp?stockholderTypeCd=" + actualControllerType,
	        showMaxButton: true,
	        title: "选择企业或者自然人",
	        width: 1000,
	        height: 500,
	        ondestroy: function (action) {            
	            if (action == "ok") {
	                var iframe = this.getIFrameEl();
	                var data = iframe.contentWindow.getData();
	                data = nui.clone(data);
	                if (data) {
	                	if(data.partyTypeCd == '01'){
	                		nui.get("ctl.cerType").setValue('202');
                    		nui.get("ctl.certNum").setValue(data.orgRegisterCd);
	                	}else{
	                		nui.get("ctl.cerType").setValue(data.certType);
                    		nui.get("ctl.certNum").setValue(data.certNum);
	                	}
                    	nui.get("ctl.controlId").setValue(data.partyId);
	                    nui.get("ctl.partyName").setText(data.partyName);
	                    nui.get("ctl.partyName").setValue(data.partyName);
	                }
	            }
	        }
	    }); 
    }
    
    
    
 	//证件验证
	function onValidateCertificateCode(e){
		var certificateTypeCdStr = nui.get("ctl.cerType").getValue();
		var certificateCodeStr = nui.get("ctl.certNum").getValue();
		if('101'==certificateTypeCdStr){
			var validateMsg=CsmValidateobj.validateIDCard(certificateCodeStr,null);
			if(null!=validateMsg){
			    //验证身份证号码
				e.isValid = false;
				e.errorText = validateMsg;
			}
		}else if('102'==certificateTypeCdStr){
			//临时身份证
			var validateMsg=CsmValidateobj.validateIDCard(certificateCodeStr,null);
			if(null!=validateMsg){
			    //验证身份证号码
				e.isValid = false;
				e.errorText = validateMsg;
			}
		}else if('202'==certificateTypeCdStr){
		    var validateMsg=CsmValidateobj.validCompID(certificateCodeStr);
			if(null!=validateMsg){
			    //验证组织机构代码证,规则验证
				e.isValid = false;
				e.errorText = validateMsg;
			}else{
				//验证组织机构代码证,唯一性验证
				validateMsg=CsmValidateobj.checkOrgNumUnique(e,certificateTypeCdStr,certificateCodeStr);
				
			}
		}else if('20100'==certificateTypeCdStr){
		    //验证企业营业执照,规则验证
		    var validateMsg=CsmValidateobj.checLicenseNo(certificateCodeStr);
			if(null!=validateMsg){
				e.isValid = false;
				e.errorText = validateMsg;
			}else{
				//验证企业营业执照,唯一性验证
				validateMsg=CsmValidateobj.checkOrgNumUnique(e,certificateTypeCdStr,certificateCodeStr);
				
			}
		}
	}
    
</script>
</body>
</html>