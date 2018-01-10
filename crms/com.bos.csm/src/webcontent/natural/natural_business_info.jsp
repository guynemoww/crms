<%@page import="com.bos.pub.GitUtil"%>
<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): chenchuan
  - Date: 2016-05-06 11:31:35
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
		<div id="form1" clsss="nui-form"style="width: 99.5%; height: auto; overflow: hidden;">
				<input name="business.businessId" id="business.businessId" value="<%=request.getParameter("businessid")%>" class="nui-hidden" />
		        <input name="business.partyId" id="business.partyId"  value="<%=request.getParameter("partyId")%>" class="nui-hidden" />
		
				<div id="djk"class="nui-dynpanel" columns="4">
					<label>经营项目：</label> 
					<input id="business.dealproj" name="business.dealproj" required="ture" class="nui-textbox nui-form-input" vtype="string;maxLength:200"/> 
					
					<label>经营字号：</label> 
					<input id="business.dealbrand" name="business.dealbrand" required="ture" class="nui-textbox nui-form-input" vtype="string;maxLength:200"/> 
					<label>注册类型：</label> 
					<input id="business.licetype" name="business.licetype" required="ture" class="nui-dictcombobox nui-form-input" dictTypeId="CDKH0024"/> 
					<label>成立日期：</label> 
					<input id="business.comedate" name="business.comedate" required="ture" class="nui-datepicker nui-form-input"/> 
					<label>营业执照号：</label> 
					<input id="business.liceid" name="business.liceid" required="ture" class="nui-textbox nui-form-input" vtype="string;maxLength:200"/> 
					
					<label>注册地址：</label> 
					<input id="business.liceaddr" name="business.liceaddr" required="ture" class="nui-textbox nui-form-input" vtype="string;maxLength:200"/> 
					
					<label>经营范围：</label> 
					<input id="business.dealscope" name="business.dealscope" required="ture" class="nui-textbox nui-form-input" vtype="string;maxLength:200"/>
					<label>经营场所：</label> 
					<input id="business.dealaddr" name="business.dealaddr" required="ture" class="nui-textbox nui-form-input" vtype="string;maxLength:200"/> 
					
					<label>经营场所性质：</label> 
					<input id="business.dealaddrkind" name="business.dealaddrkind" required="ture" class="nui-dictcombobox nui-form-input" vtype="string;maxLength:20" dictTypeId="XD_JYCSXZ0001"/> 
					<label>年营业额：</label> 
					<input id="business.yearamt" name="business.yearamt" required="ture" class="nui-textbox nui-form-input" vtype="float;maxLength:60"/>
					
					<label>雇员人数：</label> 
					<input id="business.empnum" name="business.empnum" required="ture" class="nui-textbox nui-form-input" vtype="int;maxLength:10"/> 
					
					
					<label>月均销售额：</label> 
					<input id="business.monthavesell" name="business.monthavesell" required="ture" class="nui-textbox nui-form-input" vtype="float;maxLength:32"/> 
					<label>月均经营费用：</label> 
					<input id="business.monthavecost" name="business.monthavecost" required="ture" class="nui-textbox nui-form-input" vtype="float;maxLength:32"/>
					<label id="zhxgr">最后修改人：</label> 
					<input id="business.lastchanperson" name="business.lastchanperson" dictTypeId="user" required="ture" enabled="false" class="nui-text nui-form-input"  vtype="string;maxLength:32"/>
					<label id="zhxgsj">最后修改日期：</label> 
					<input id="business.lastchandate" name="business.lastchandate" enabled="false" required="ture" class="nui-datepicker nui-form-input" /> 
				</div>
		
			<div class="nui-toolbar"
				style="border: 0; text-align: right; padding-right:">
				<a id="save" class="nui-button" iconCls="icon-save" onclick="save()">保存</a> <a
				id="close"	class="nui-button" iconCls="icon-close" onclick="CloseWindow()">关闭</a>
			</div>
		</div>


	<script type="text/javascript">
		nui.parse();
		var form = new nui.Form("#form1");
		var qote = "<%=request.getParameter("qote") %>";
		
		  
	    if(qote=="2"){
		nui.get("save").hide();
		nui.get("close").hide();
		form.setEnabled(false);
	    }
	     if(qote=="0"){
		$("#zhxgsj").hide();
		$("#zhxgr").hide();
		nui.get("business.lastchandate").hide();
		nui.get("business.lastchanperson").hide();
	    }else{
	     initForm();
	    }
		function initForm() {
			var json = nui.encode({
				"businessId" : "<%=request.getParameter("businessId")%>"});
			$.ajax({
				url : "com.bos.csm.natural.natural.getNaturalBusiness.biz.ext",
				type : 'POST',
				data : json,
				cache : false,
				contentType : 'text/json',
				success : function(mydata) {
					git.unmask("form1");
					var o = nui.decode(mydata);
					form.setData(o);
					oldData = form.getData();
				},
				error : function(jqXHR, textStatus, errorThrown) {
					git.unmask("form1");
					nui.alert(jqXHR.responseText);
				}
			});
		}


		function save() {
			form.validate();
			if (form.isValid() == false) {
				alert("请将信息填写完整");
				return;
			}
			git.mask("form1");
			var o = form.getData();
			var json = nui.encode(o);
			if(qote!="1"){
			$.ajax({
				url : "com.bos.csm.natural.natural.addNaturalBusiness.biz.ext",
				type : 'POST',
				data : json,
				cache : false,
				contentType : 'text/json',
				success : function(text) {
					git.unmask("form1");
					if (text.msg) {
						alert(text.msg);
					} else {
						CloseWindow("ok");
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					git.unmask("form1");
					nui.alert(jqXHR.responseText);
				}
			});
			}else{
			$.ajax({
				url : "com.bos.csm.natural.natural.updateNaturalBusiness.biz.ext",
				type : 'POST',
				data : json,
				cache : false,
				contentType : 'text/json',
				success : function(text) {
					git.unmask("form1");
					if (text.msg) {
						alert(text.msg);
					} else {
						CloseWindow("ok");
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					git.unmask("form1");
					nui.alert(jqXHR.responseText);
				}
			});
			}
		}
	</script>

</body>
</html>