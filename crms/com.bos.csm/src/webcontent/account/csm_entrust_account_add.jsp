<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<!-- 
  - Author(s): chenchuan@git.com.cn
  - Date: 2016-5-17
  - Description:TB_CSM_IMPORNANT_EVENT, com.bos.dataset.csm.TbCsmImpornantEvent
-->
<head>
<%@include file="/common/nui/common.jsp"%>
<script type="text/javascript" src="<%=contextPath%>/csm/js/commValidate.js"></script>
</head>
<body>
<div id="form1" style="width:99.5%;height:auto;overflow:hidden;">
	<fieldset>
	  <legend>
	    <span>委托方账户信息</span>
	   </legend>
		<input type="hidden" name="item._entity" value="com.bos.dataset.csm.TbCsmEntrustAccount" class="nui-hidden" />
		<input name="item.partyId" id="item.partyId" class="nui-hidden" />
		<div class="nui-dynpanel" columns="4">
	
			<label>账户名称：</label> 
			<input id="item.accName" name="item.accName" required="true" class="nui-textbox nui-form-input"  enabled="false"/>
			
			<label>委托项目名称：</label> 
			<input name="item.entrustProjectName" id="item.entrustProjectName" vtype="maxLength:150" class="nui-textbox nui-form-input" /> 
			
			<label>委托贷款类型：</label> 
			<input id="item.entrustType" name="item.entrustType" required="true" enabled="true" onvaluechanged="changeType" class="nui-dictcombobox nui-form-input" dictTypeId="XD_WTDK0001" />
			
			<label id="entrustAcc_id">委托存款账号：</label> 
			<input name="item.entrustAcc" id="item.entrustAcc" required="true" class="nui-textbox nui-form-input"  vtype="maxLength:32"/>
						
			<!-- <label>委托贷款基金账号：</label>
			<input name="item.entrustLoanAcc" id="item.entrustLoanAcc" required="true" class="nui-textbox nui-form-input" />  -->
						
			<!-- <label id="entrustReturnAcc_id">委托贷款收息账号：</label> 
			<input name="item.entrustReturnAcc" id="item.entrustReturnAcc"  required="true" class="nui-textbox nui-form-input"  vtype="maxLength:32"/>  -->
						
			<label id="entrustReturnPrincipalAcc_id">委托人收本账号：</label> 
			<input name="item.entrustReturnPrincipalAcc" id="item.entrustReturnPrincipalAcc"  required="true" class="nui-textbox nui-form-input"  vtype="maxLength:32"/> 
						
			<label id="entrustReturnInterestAcc_id">委托人收息账号：</label> 
			<input name="item.entrustReturnInterestAcc"  id="item.entrustReturnInterestAcc" required="true" class="nui-textbox nui-form-input"  vtype="maxLength:32"/>
		
		</div>
	</fieldset>
	<div class="nui-toolbar" style="border:0;text-align:right;padding-right:20px">
		<a class="nui-button" iconCls="icon-save" onclick="save()">保存</a>
		<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
	</div>
</div>
<script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1");
	var partyId = "<%=request.getParameter("partyId") %>";
	var length="<%=request.getParameter("length") %>";//记录条数
	
	if (partyId) {
		nui.get("item.partyId").setValue(partyId); 
	}
	if(length>0){
		nui.get("item.entrustProjectName").setRequired(true);
	}else{
		nui.get("item.entrustProjectName").setRequired(false);
	}
	
	function initForm() {
		var json=nui.encode({"partyId":"<%=request.getParameter("partyId")%>"});
			$.ajax({
						url : "com.bos.csm.corporation.corporation.getEntrustAccount.biz.ext",
						type : 'POST',
						data : json,
						cache : false,
						contentType : 'text/json',
						success : function(text) {
							git.unmask("form1");
							if (text.msg) {
								nui.alert(text.msg);
							} else {
								nui.get("item.accName").setValue(text.item.accName);
							}
						},
						error : function(jqXHR, textStatus, errorThrown) {
							git.unmask("form1");
							nui.alert(jqXHR.responseText);
						}
					});
		}
		initForm();
	
	function save() {
		var accName = nui.get('item.accName').getValue();
		form.validate();
		if (form.isValid() == false) {
			nui.alert("请将信息填写完整");
			return;
		}
		
		var entrustAcc=nui.get("item.entrustAcc").getValue();
		//var entrustLoanAcc=nui.get("item.entrustLoanAcc").getValue();
		//var entrustReturnAcc=nui.get("item.entrustReturnAcc").getValue();
		var entrustReturnPrincipalAcc=nui.get("item.entrustReturnPrincipalAcc").getValue();
		var entrustReturnInterestAcc=nui.get("item.entrustReturnInterestAcc").getValue();
		entrustAcc = entrustAcc.trim();
		entrustReturnPrincipalAcc = entrustReturnPrincipalAcc.trim();
		entrustReturnInterestAcc = entrustReturnInterestAcc.trim();
		var flag;
		if(entrustAcc){
		var subentrustAcc = entrustAcc.substring(4,5);
			if(subentrustAcc == '8' || subentrustAcc == '9'){
	    	 var json2=nui.encode({"acctInd":entrustAcc});
			  $.ajax({
		        url: "com.bos.accInfo.accInfo.queryAcc2.biz.ext",
		        type: 'POST',
		        data: json2,
		        cache: false,
		        async : false,
		        contentType:'text/json',
		        success: function (text) {
		        	var message = text.msg;
		        	var code = text.code;
		        	if(code != 'AAAAAAA'){
		        		flag = "1";
		        		nui.alert(message);
		        		return;
		        	}
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		            nui.alert(jqXHR.responseText);
		        }
			});
	    	 }else{
	    	 var json=nui.encode({"acctInd":entrustAcc});
			  $.ajax({
		        url: "com.bos.accInfo.accInfo.queryAcc1.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        async : false,
		        contentType:'text/json',
		        success: function (text) {
		        	var message = text.msg;
		        	var code = text.code;
		        	if(code != 'AAAAAAA'){
		        	flag = "1";
		        		nui.alert(message);
		        		return;
		        	}
		        	
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		            nui.alert(jqXHR.responseText);
		        }
			});
	    	 }
		}
		if(flag == "1"){
			return false;
		}
		if(entrustReturnPrincipalAcc){
			var subentrustReturnPrincipalAcc = entrustReturnPrincipalAcc.substring(4,5);
			if(subentrustReturnPrincipalAcc == '8' || subentrustReturnPrincipalAcc == '9'){
	    	 var json2=nui.encode({"acctInd":entrustReturnPrincipalAcc});
			  $.ajax({
		        url: "com.bos.accInfo.accInfo.queryAcc2.biz.ext",
		        type: 'POST',
		        data: json2,
		        cache: false,
		        async : false,
		        contentType:'text/json',
		        success: function (text) {
		        	var message = text.msg;
		        	var code = text.code;
		        	if(code != 'AAAAAAA'){
		        	flag = "1";
		        		nui.alert(message);
		        		return;
		        	}
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		            nui.alert(jqXHR.responseText);
		        }
			});
	    	 }else{
	    	 var json=nui.encode({"acctInd":entrustReturnPrincipalAcc});
			  $.ajax({
		        url: "com.bos.accInfo.accInfo.queryAcc1.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        async : false,
		        contentType:'text/json',
		        success: function (text) {
		        	var message = text.msg;
		        	var code = text.code;
		        	if(code != 'AAAAAAA'){
		        	flag = "1";
		        		nui.alert(message);
		        		return;
		        	}
		        	
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		            nui.alert(jqXHR.responseText);
		        }
			});
	    	 }
		}
		if(flag == "1"){
			return false;
		}
		if(entrustReturnInterestAcc){
		var subentrustReturnInterestAcc = entrustReturnInterestAcc.substring(4,5);
			if(subentrustReturnInterestAcc == '8' || subentrustReturnInterestAcc == '9'){
	    	 var json2=nui.encode({"acctInd":entrustReturnInterestAcc});
			  $.ajax({
		        url: "com.bos.accInfo.accInfo.queryAcc2.biz.ext",
		        type: 'POST',
		        data: json2,
		        cache: false,
		        async : false,
		        contentType:'text/json',
		        success: function (text) {
		        	var message = text.msg;
		        	var code = text.code;
		        	if(code != 'AAAAAAA'){
		        	flag = "1";
		        		nui.alert(message);
		        		return;
		        	}
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		            nui.alert(jqXHR.responseText);
		        }
			});
	    	 }else{
	    	 var json=nui.encode({"acctInd":entrustReturnInterestAcc});
			  $.ajax({
		        url: "com.bos.accInfo.accInfo.queryAcc1.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        async : false,
		        contentType:'text/json',
		        success: function (text) {
		        	var message = text.msg;
		        	var code = text.code;
		        	if(code != 'AAAAAAA'){
		        	flag = "1";
		        		nui.alert(message);
		        		return;
		        	}
		        	
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		            nui.alert(jqXHR.responseText);
		        }
			});
	    	 }
		}
		if(flag == "1"){
			return false;
		}
		var o=form.getData();
		var json=nui.encode(o);
		$.ajax({
	            url: "com.bos.csm.pub.crudCustInfo.insertItem.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            async : false,
	            contentType:'text/json',
	            success: function (text) {
	            	git.unmask("form1");
	            	if(text.msg){
	            		nui.alert(text.msg);
	            	} else {
	            		CloseWindow("ok");
	            	}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	       			git.unmask("form1");
	                nui.alert(jqXHR.responseText);
	            }
		});
	}
	function changeType(e){
		if(e.value == '02'){
			$("#entrustAcc_id").css("display","none");
			//$("#entrustReturnAcc_id").css("display","none");
			$("#entrustReturnPrincipalAcc_id").css("display","none");
			$("#entrustReturnInterestAcc_id").css("display","none");
			nui.get("item.entrustAcc").hide();
			//nui.get("item.entrustReturnAcc").hide();
			nui.get("item.entrustReturnPrincipalAcc").hide();
			nui.get("item.entrustReturnInterestAcc").hide();
			nui.get("item.entrustAcc").setValue("");
			//nui.get("item.entrustReturnAcc").setValue("");
			nui.get("item.entrustReturnPrincipalAcc").setValue("");
			nui.get("item.entrustReturnInterestAcc").setValue("");
		}else{
			nui.get("item.entrustAcc").show();
			//nui.get("item.entrustReturnAcc").show();
			nui.get("item.entrustReturnPrincipalAcc").show();
			nui.get("item.entrustReturnInterestAcc").show();
			$("#entrustAcc_id").css("display","block");
			//$("#entrustReturnAcc_id").css("display","block");
			$("#entrustReturnPrincipalAcc_id").css("display","block");
			$("#entrustReturnInterestAcc_id").css("display","block");
		}
	}
</script>
</body>
</html>
