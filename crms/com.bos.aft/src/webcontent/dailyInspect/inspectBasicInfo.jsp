<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): fengjiahui
  - Date: 2014-02-13 16:27:30
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp"%>
<title>客户情况</title>
</head>
<body style="margin-left:10px;">
	<fieldset>
  	<legend>
   		<span>客户相关信息</span>
    </legend>    
	<div id="queryform" class="nui-dynpanel" columns="4"  >
		<label>客户全称：</label>
		<input name="partyName" id="partyName" class="nui-text nui-form-input" /> 
		<label>企业性质：</label>
		<input name="registrationType" id="enterpriseNatureCd" class="nui-text nui-form-input" dictTypeId="CDKH0024"/> 
		<label>最近一期分类结果：</label>
		<input name="clsResult" id="clsResult" class="nui-text nui-form-input" dictTypeId="XD_FLCD0001"/> 
		<label>授信金额：</label>
		<input name="creditExposure" class="nui-text nui-form-input" >
		<label>授信余额：</label>
		<input name="occupiedExposur" class="nui-text nui-form-input" >
		<label>敞口余额：</label>
		<input name="availableExposur" class="nui-text nui-form-input" >
		<label>到期日（最后一笔授信业务到期日）：</label>
		<input class="nui-text nui-form-input" >
		<label>担保方式：</label>
		<input name="guarantyType" class="nui-text nui-form-input" dictTypeId="XD_SXCD1020" >
		
	</div>
	</fieldset>
</body>
<script type="text/javascript">
	
	nui.parse();
	var queryform = new nui.Form("#queryform");
	var partyId ="<%=request.getParameter("partyId") %>";
	var json = nui.encode({partyId:partyId});
	nui.ajax({
			url: "com.bos.aft.aft_inspect_report.getInspectBasicInfo.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
                   queryform.setData(mydata.basicInfo);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                    git.unmask();
            }
		
		});
</script>
</html>