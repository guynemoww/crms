<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): hanshuo
  - Date: 2014-12-25 16:18:26
  - Description:
-->
<head>
<title>待移交资产明细</title>
</head>
<body>
<div id="form" style="width:99.5%;height:auto;overflow:hidden;">  
	<div class="nui-dynpanel" columns="4" id="table1">
		<label id="partyName" class="nui-form-label" >客户名称：</label>
		<input id="assetsPerforming.partyName" class="nui-textbox nui-form-input" name="assetsPerforming.partyName" required="true"/>
		
		<label id="partyType" class="nui-form-label" >客户类型：</label>
		<input id="assetsPerforming.partyType" class="nui-textbox nui-form-input" name="assetsPerforming.partyType" required="true"/>
		
		<label id="partyNum" class="nui-form-label" >客户编号：</label>
		<input id="assetsPerforming.partyNum" class="nui-textbox nui-form-input" name="assetsPerforming.partyNum" required="true"/>
		
		<label id="assetsStatus" class="nui-form-label" >资产状态：</label>
		<input id="assetsPerforming.assetsStatus" class="nui-textbox nui-form-input" name="assetsPerforming.assetsStatus" required="true"/>
  
		<label id="intoDate" class="nui-form-label" >转入保全时间：</label>
		<input id="assetsPerforming.intoDate" class="nui-textbox nui-form-input" name="assetsPerforming.intoDate" required="true" />
  
  		<label id="responsibleUserNum">保全责任人：</label>
  		<input id="assetsPerforming.responsibleUserNum" name="assetsPerforming.responsibleUserNum" valueField="dictID" 
  		class="nui-textbox nui-form-input" required="true" onvaluechanged="bankTeamChange"/>
  
		<label id="responsibleOrgNum" class="nui-form-label" >保全责任机构：</label>
		<input id="assetsPerforming.responsibleOrgNum" name="assetsPerforming.responsibleOrgNum" valueField="dictID" 
		class="nui-textbox nui-form-input" required="true" />
  
  		<label id="creditAmt" class="nui-form-label" >授信额度：</label>
  		<input id="assetsPerforming.creditAmt" name="assetsPerforming.creditAmt" class="nui-textbox nui-form-input"
   		required="true" />
  
  		<label id="balanceAmt" class="nui-form-label" >授信余额：</label>
  		<input id="assetsPerforming.balanceAmt" name="assetsPerforming.balanceAmt" class="nui-textbox nui-form-input"
   		required="true" />
  
		<label id="overdueInterest" class="nui-form-label" >表内外应收未收利息：</label>
		<input id="assetsPerforming.overdueInterest" name="assetsPerforming.overdueInterest" valueField="dictID" 
		class="nui-textbox nui-form-input" required="true"/>
		
		<label id="overdueDays" class="nui-form-label" >逾期或垫款天数：</label>
		<input id="assetsPerforming.overdueDays" name="assetsPerforming.overdueDays" valueField="dictID" 
		class="nui-textbox nui-form-input" required="true"/>
		
		<label id="riskClassify" class="nui-form-label" >五级分类：</label>
		<input id="assetsPerforming.riskClassify" name="assetsPerforming.riskClassify" valueField="dictID" 
		class="nui-textbox nui-form-input" required="true"/>
		
		<label id="clearDate" class="nui-form-label" >结清日期：</label>
		<input id="assetsPerforming.clearDate" name="assetsPerforming.clearDate" valueField="dictID" 
		class="nui-textbox nui-form-input" required="true"/>
  
		<label id="userNum">经办人：</label>
		<input id="assetsPerforming.userNum" name="assetsPerforming.userNum" valueField="dictID" 
 		class="nui-textbox nui-form-input" required="true" onvaluechanged="lowchange"/>
  
		<label for="isteam$text" id="OrgNum">经办机构：</label>
		<input id="assetsPerforming.orgNum" name="assetsPerforming.orgNum" valueField="dictID" 
		class="nui-textbox nui-form-input" required="true" onvaluechanged="lowchange"/>
  
		<label id="handingDate">经办日期：</label>
		<input id="assetsPerforming.handingDate" name="assetsPerforming.handingDate" valueField="dictID" 
		class="nui-textbox nui-form-input" required="true" onvaluechanged="lowchange"/>
  
		<input id="assetsPerforming.performingId" name="assetsPerforming.performingId" 
		class="nui-hidden nui-form-input" required="true"/>
	</div>
	<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
		<a class="nui-button" id="" iconCls="icon-close" onclick="back">关闭</a>
	</div>
</div>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	query();
	//查询
	function query(){
		//var json = nui.encode({"assetsTransfer":{"transferId":"<%=request.getParameter("transferId")%>"}});
		var json = nui.encode({"performingId":"<%=request.getParameter("performingId")%>"});
		$.ajax({
	        url: "com.bos.npl.assets.AssetsPerforming.getAssetsPerformingDetail.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
	        contentType:'text/json',
	        success: function (mydata) {
	        	var o = nui.decode(mydata);
	        	form.setData(o);
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	        	nui.alert(jqXHR.responseText);
	        }
     	});
	}
	
	//关闭
	function back() {
 		CloseWindow("ok");
 	}
</script>
</body>
</html>