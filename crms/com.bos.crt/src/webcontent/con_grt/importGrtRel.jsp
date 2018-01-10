<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-07-06 18:52:09
  - Description:
-->
<head>
<title>填写押品信息</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
	<div class="nui-dynpanel" columns="2" id="table">
		<label class="nui-form-label">评估价值（元）：</label>
		<input id="assessValue" name="grtMortgageBasic.assessValue" vtype="float;maxLength:20"  class="nui-textbox nui-form-input" dataType="currency" enabled="false"/>
		
		<label class="nui-form-label">已担保金额（元）：</label>
		<input id="grtMortgageBasic.suretyAmt" name="grtMortgageBasic.suretyAmt" vtype="float;maxLength:20"  class="nui-textbox nui-form-input" dataType="currency" enabled="false"/>
		
		<label class="nui-form-label">本次担保金额（元）：</label>
		<input id="bizGrtRel.suretyAmt" name="bizGrtRel.suretyAmt" vtype="float;maxLength:20" required="true" class="nui-textbox nui-form-input" dataType="currency"/>
	</div>
	<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
		<a class="nui-button" id="saveSubGrtRel_save" iconCls="icon-save" onclick="save">确定</a>
	</div>
</div>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	function GetData(){
		var data = form.getData();
		return data;
	}
	function save(){
		form.validate();
        if (form.isValid()==false){
        	nui.alert("请按规则填写信息");
        	return;
        }
        GetData();
		CloseWindow("ok");
		return;
	}
</script>
</body>
</html>