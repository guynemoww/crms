<%@page pageEncoding="UTF-8" import="commonj.sdo.DataObject"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-04-23 17:55:38
  - Description:
-->
<head>
<title>解冻</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<script type="text/javascript" src="<%=contextPath%>/biz/biz_js/biz.js"></script>

<body>
<center>
	<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
	<div class="nui-dynpanel" columns="4">
				<label>冻结种类：</label>
				<div style="width:100%">
				<input id="frzType" name="frzType" enabled="false" class="nui-dictcombobox nui-form-input" dictTypeId="CD100003"  />
				</div>
				
				<label>冻结金额：</label>
				<input id="frzAmt"  name="frzAmt"   class="nui-text nui-form-input" enabled="false"/>
				
				<label>冻结原因：</label>
				<input id="frzReason"  name="frzReason"   class="nui-textarea nui-form-input" enabled="false"/>
				
				<label>是否到期自动解冻：</label>
				<input id="operFlag"  name="operFlag"   enabled="false"  class="nui-dictcombobox nui-form-input" dictTypeId="CDGY0001"/>
				
				<label>解冻原因：</label>
				<input id="cFrzReason"  name="cFrzReason"   required="true" class="nui-textarea nui-form-input" />
		</div>
	<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
		<!--<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow('ok')">关闭</a>-->
	    <a class="nui-button" id="btnCreate" iconCls="icon-save" onclick="save">解冻</a>
	</div>
</center>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var applyId = "<%=request.getParameter("applyId") %>";//1：可修改。0：不可修改.-1:过程查看，不可修改且不展示提交按钮
	var amountId = "<%=request.getParameter("amountId") %>";
	var approveId = "<%=request.getParameter("approveId") %>";
	var frz_num = "<%=request.getParameter("frz_num") %>";//冻结流水编号
	
	function initPage(){
		var json = nui.encode({"frz_num":frz_num});
		$.ajax({
			url : "com.bos.bizInfo.bizInfo.getDjInfo.biz.ext",
			type : 'POST',
			data : json,
			cache : false,
			contentType : 'text/json',
			cache : false,
			success : function(mydata) {
				nui.get("frzType").setValue(mydata.tbBizJdDjFlow.frzType);
				nui.get("frzAmt").setValue(mydata.tbBizJdDjFlow.frzAmt);
				nui.get("frzReason").setValue(mydata.tbBizJdDjFlow.frzReason);
				nui.get("operFlag").setValue(mydata.tbBizJdDjFlow.operFlag);
			}
		});
	}
	initPage();
	function save(){
		var FrzType  = nui.get("frzType").getValue();//冻结类型
    	var FrzAmt = nui.get("frzAmt").getValue();//冻结金额
    	var OperFlag = nui.get("operFlag").getValue();//是否到期自动解冻
    	var cFrzReason = nui.get("cFrzReason").getValue();//解冻原因
		
		nui.get("btnCreate").setEnabled(false);
		var json = nui.encode({"amountId":amountId ,"approveId":approveId, "frzType": FrzType , "frzAmt" :FrzAmt, "frzReason":cFrzReason, "operFlag":OperFlag,"frz_num":frz_num});
		$.ajax({
			url : "com.bos.bizInfo.bizInfo.saveBizJdInfo.biz.ext",
			type : 'POST',
			data : json,
			cache : false,
			contentType : 'text/json',
			cache : false,
			success : function(mydata) {
				nui.alert(mydata.msg);
				CloseWindow("ok");
			}
		});
		nui.get("btnCreate").setEnabled(true);
	}
</script>
</body>
</html>