<%@page pageEncoding="UTF-8"%>
<%@page import="com.bos.pub.GitUtil"%>
<html>
<!-- 
  - Author(s): 陈川
  - Date: 2016-05-10 11:01:34
  - Description:
-->
<head>
<title>基本信息</title>
<%@include file="/common/nui/common.jsp"%>
<script type="text/javascript" src="<%=contextPath%>/csm/js/csmValidate.js"></script>
</head>
<body>
		<div id="form1" style="width: 99.5%; height: auto; overflow: hidden;">
			<fieldset>
				<legend>
					<span>基本信息</span>
				</legend>
				
				<input id="natural.partyId" class="nui-hidden nui-form-input" name="natural.partyId" value="<%=request.getParameter("partyId")%>" />
				<div class="nui-dynpanel" columns="4">
					<label class="nui-form-label">客户编号：</label>
					<input id="party.partyNum" class="nui-text nui-form-input" enabled="false" name="party.partyNum" /> 
					
					<label class="nui-form-label">客户名称：</label> 
					<input id="party.partyName"class="nui-textbox nui-form-input" name="party.partyName" required="true" vtype="maxLength:300"/> 
					
					<label class="nui-form-label">证件类型：</label> 
					<input id="natural.certType" name="natural.certType" class="nui-dictcombobox nui-form-input"onvalidation="onValidateCertificateCode"
						dictTypeId="CDKH0002" required="true" /> 
					
					<label class="nui-form-label">证件号码：</label> 
					<input id="natural.certNum"class="nui-textbox nui-form-input" onvalidation="onValidateCertificateCode" name="natural.certNum"
						required="true" vtype="maxLength:30" /> 
				</div>
			</fieldset>
	<div class="nui-toolbar" style="text-align:right;" borderStyle="border:0;">
		<a id="btnSave" class="nui-button" iconCls="icon-save"onclick="update">保存</a>
	    <a id="btnClose" class="nui-button" iconCls="icon-close"  onclick="CloseWindow('ok')">关闭</a>
	</div>
</div>

<script type="text/javascript">
	var oldData;
	var newData;
    nui.parse();
    git.mask("form1");
    var form = new nui.Form("#form1");
    var qote = "<%=request.getParameter("qote")%>" ;
	if(qote==1){
	   form.setEnabled(false);
	   nui.get("btnSave").hide();
	}	
    <%--  数据初始化--%>
	function initForm(){
	  var json = nui.encode({"natural":{"partyId":"<%=request.getParameter("partyId")%>"}});
	  $.ajax({
			  url: "com.bos.csm.natural.natural.getNaturalInfo.biz.ext",
			  type: 'POST',
			  data: json,
			  cache: false,
			  contentType: 'text/json',
			  success: function (mydata) {
	          		 git.unmask("form1");
	                 var o = nui.decode(mydata);
	                 form.setData(o);
	                 oldData = form.getData();
	                 //字典过滤，过滤掉202的证件类型
					 var arr = git.getDictDataUnFilter("CDKH0002", '202');
					 nui.get("natural.certType").setData(arr);
	            }, 
	            error: function (jqXHR, textStatus, errorThrown) {
	            	git.unmask("form1");
	                nui.alert(jqXHR.responseText);
	            }     
	  });
	 }
	initForm();

	function update(){
		form.validate();
		if (form.isValid() == false) {
			nui.alert("请完整填写信息！");
			return;
		}
		var o=form.getData();
		o.party.partyName=nui.get("party.partyName").getValue().trim();
		var json = nui.encode(o);
		$.ajax({
	            url: "com.bos.csm.natural.natural.updateNaturalInfo.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            	git.unmask("form1");
	            	if(text.msg){
	            		alert(text.msg);
	            	} else {
	            		alert("保存成功！"); 
	            		CloseWindow();
	            	}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	               	git.unmask("form1");
	                nui.alert(jqXHR.responseText);
	            }
	        });
	}
		
	
	
	
	//证件验证
	function onValidateCertificateCode(e){
		var certType = nui.get("natural.certType").getValue();
		var certNum = nui.get("natural.certNum").getValue();
		if('101'==certType){
			var validateMsg=CsmValidateobj.validateIDCard(certNum,null);
			if(null!=validateMsg){
			    //验证身份证号码
				e.isValid = false;
				e.errorText = validateMsg;
			}
		}else if('102'==certType){
			//临时身份证
			var validateMsg=CsmValidateobj.validateIDCard(certNum,null);
			if(null!=validateMsg){
			    //验证身份证号码
				e.isValid = false;
				e.errorText = validateMsg;
			}
		}
		//判断证件号码是否存在
		if (e.isValid) {
			var json1 = {"certType":certType,"certNum":certNum,"partyId":nui.get("natural.partyId").getValue()};			
			msg = exeRule("PUB_0018", "1", json1);
				if (null != msg && '' != msg) {
				e.errorText = msg;
				e.isValid = false;
			}
		}
	}
	 	
</script>
</body>
</html>