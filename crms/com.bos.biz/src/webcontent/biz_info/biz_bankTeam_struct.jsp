<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): zhufaying
  - Date: 2014-03-28 15:49:37
  - Description:
-->
<head>
<title>银团结构</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<center>
<div id="form1" style="width:99.5%;height:auto;overflow:hidden;">
   <input id="bankTs.applyId" class="nui-hidden nui-form-input" name ="bankTs.applyId" value="<%=request.getParameter("applyId")%>"/>
   <input id="bankTs.syndicatedStructId" class="nui-hidden nui-form-input" name ="bankTs.syndicatedStructId" />
   <div class="nui-dynpanel" columns="4" id="table1">
		<label class="nui-form-label">银团组团对象：</label>
		<input id="bankTs.syndicatedObjectCd" name="bankTs.syndicatedObjectCd" data="data" valueField="dictID" 
		class="nui-dictcombobox nui-form-input" dictTypeId="CDXY0064" required="true" emptyText="--请选择--"/>
<%--		
		<label class="nui-form-label">银团组团方式：</label>
		<input id="bankTs.syndicatedWayCd" name="bankTs.syndicatedWayCd" data="data" valueField="dictID" 
		class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXCD1067" required="true" emptyText="--请选择--"/>
				--%>
		<label class="nui-form-label" id="lowType">银团项目名称：</label>
		<input id="bankTs.syndicatedProjectName" name="bankTs.syndicatedProjectName" vtype="maxLength:100" class="nui-textbox nui-form-input" required="true" />
	
		<label class="nui-form-label" id="creditProduct">项目所在地：</label>
		<input id="bankTs.projectAddress" name="bankTs.projectAddress" vtype="maxLength:100" class="nui-textbox nui-form-input" required="true"/>
		
<%--		<label for="nui-form-label">我行分工：</label>
		<input id="bankTs.myselfRoleCd" name="bankTs.myselfRoleCd" data="data" valueField="dictID" 
		class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXCD1065" required="true" emptyText="--请选择--"/>--%>
				
		<label for="nui-form-label">银团贷款总金额：</label>
		<input id="bankTs.syndicatedLoanAmt" name="bankTs.syndicatedLoanAmt" vtype="float;maxLength:20" class="nui-textbox nui-form-input" dataType="currency" required="true"/>
		
		<label class="nui-form-label">币种：</label>
		<input id="bankTs.currencyCd" name="bankTs.currencyCd" data="data" valueField="dictID" 
		class="nui-dictcombobox nui-form-input" dictTypeId="CD000001" value="156" required="true" emptyText="--请选择--" />

		<label class="nui-form-label" id="businessProduct">是否集团客户：</label>
		<input id="bankTs.groupCustomer"  name="bankTs.groupCustomer" data="data" valueField="dictID" 
		class="nui-text nui-form-input" dictTypeId="XD_0002" required="false"/>
		
<%--		<label  class="nui-form-label" id="bankTeam">集团客户名称：</label>
		<input id="bankTs.customerGroupName" name="bankTs.customerGroupName" class="nui-text nui-form-input" required="false"/>
		<input id="bankTs.applyId" class="nui-hidden nui-form-input" name="bankTs.applyId"/>
		<input id="bankTs.syndicatedStructId" class="nui-hidden nui-form-input" name="bankTs.syndicatedStructId"/>--%>
	</div>
	<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
	 	<a class="nui-button" id="biz_bankTeam_struce_save" iconCls="icon-ok" onclick="create">确定</a>
	</div>
</div>
</center>
</body>
<script type="text/javascript">

	nui.parse();
	var form = new nui.Form("#form1");
	var applyId ="<%=request.getParameter("applyId")%>";
	var proFlag ="<%=request.getParameter("proFlag") %>";
	if("1" != proFlag){
		nui.get("biz_bankTeam_struce_save").hide();
		form.setEnabled(false);
	}
	initPage();
	function initPage(){
		var json = nui.encode({"applyId":applyId});
        $.ajax({
            url: "com.bos.bizInfo.bizInfo.getBankTeamStruct.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	var o = nui.decode(text);
            	form.setData(o);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
        });
	}
	function create(){
		//校验
		form.validate();
        if (form.isValid()==false) return;
		//nui.get("biz_bankTeam_struce_save").setEnabled(false);
        
       	var o = form.getData();
        var json = nui.encode(o);
        $.ajax({
            url: "com.bos.bizInfo.bizInfo.saveBankTeamStruct.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	nui.alert("保存成功!");
            	initPage();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
        });
	}
	

</script>
</html>