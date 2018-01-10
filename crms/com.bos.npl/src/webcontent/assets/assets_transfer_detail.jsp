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
		<input id="assetsTransfer.partyName" class="nui-textbox nui-form-input" name="assetsTransfer.partyName" required="true"/>
		
		<label id="partyType" class="nui-form-label" >客户类型：</label>
		<input id="assetsTransfer.partyType" class="nui-textbox nui-form-input" name="assetsTransfer.partyType" required="true"/>
		
		<label id="partyNum" class="nui-form-label" >客户编号：</label>
		<input id="assetsTransfer.partyNum" class="nui-textbox nui-form-input" name="assetsTransfer.partyNum" required="true"/>
		
		<label id="creditAmt" class="nui-form-label" >授信额度：</label>
		<input id="assetsTransfer.creditAmt" class="nui-textbox nui-form-input" name="assetsTransfer.creditAmt" required="true"/>
  
		<label id="balanceAmt" class="nui-form-label" >授信余额：</label>
		<input id="assetsTransfer.balanceAmt" class="nui-textbox nui-form-input" name="assetsTransfer.balanceAmt" required="true" />
  
  		<label id="partyCreditRating">客户评级：</label>
  		<input id="assetsTransfer.partyCreditRating" name="assetsTransfer.partyCreditRating" valueField="dictID" 
  		class="nui-textbox nui-form-input" required="true" onvaluechanged="bankTeamChange"/>
  
		<label id="warningLevelCd" class="nui-form-label" >预警级别：</label>
		<input id="assetsTransfer.warningLevelCd" name="assetsTransfer.warningLevelCd" valueField="dictID" 
		class="nui-textbox nui-form-input" required="true" />
  
  		<label id="riskClassify" class="nui-form-label" >最新分类：</label>
  		<input id="assetsTransfer.riskClassify" name="assetsTransfer.riskClassify" class="nui-textbox nui-form-input"
   		required="true" />
  
  		<label id="holdDate" class="nui-form-label" >认定时间：</label>
  		<input id="assetsTransfer.holdDate" name="assetsTransfer.holdDate" class="nui-textbox nui-form-input"
   		required="true" />
  
		<label id="status" class="nui-form-label" >状态：</label>
		<input id="assetsTransfer.status" name="assetsTransfer.status" valueField="dictID" 
		class="nui-textbox nui-form-input" required="true"/>
  
		<label id="userNum">经办人：</label>
		<input id="assetsTransfer.userNum" name="assetsTransfer.userNum" valueField="dictID" 
 		class="nui-textbox nui-form-input" required="true" onvaluechanged="lowchange"/>
  
		<label for="isteam$text" id="OrgNum">经办机构：</label>
		<input id="assetsTransfer.orgNum" name="assetsTransfer.orgNum" valueField="dictID" 
		class="nui-textbox nui-form-input" required="true" onvaluechanged="lowchange"/>
  
		<label id="handingDate">经办日期：</label>
		<input id="assetsTransfer.handingDate" name="assetsTransfer.handingDate" valueField="dictID" 
		class="nui-textbox nui-form-input" required="true" onvaluechanged="lowchange"/>
  
		<input id="assetsTransfer.transferId" name="assetsTransfer.transferId" 
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
		var json = nui.encode({"transferId":"<%=request.getParameter("transferId")%>"});
		$.ajax({
	        url: "com.bos.npl.assets.AssetsTransfer.getAssetsTransferDetail.biz.ext",
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