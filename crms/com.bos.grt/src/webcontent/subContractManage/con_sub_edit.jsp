<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-07-06 18:52:09
  - Description:
-->
<head>
<title>修改担保合同与业务合同关联信息</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
	<div class="nui-dynpanel" columns="2" id="table">
	<label class="nui-form-label">合同金额（元）：</label>
		<input id="contractAmt" name="suretyAmt" value="<%=request.getParameter("contractAmt") %>"  vtype="float;maxLength:20;range:1" required="true" enabled="false" class="nui-textbox nui-form-input" dataType="currency"/>
		<label class="nui-form-label">合同已用金额（元）：</label>
		<input id="conYuE" name="suretyAmt" value="<%=request.getParameter("conYuE") %>"  vtype="float;maxLength:20;range:1" required="true" enabled="false"class="nui-textbox nui-form-input" dataType="currency"/>
		<label class="nui-form-label">本次担保金额（元）：</label>
		<input id="suretyAmt" name="suretyAmt" value="<%=request.getParameter("suretyAmt") %>"  vtype="float;maxLength:20;range:1" required="true" class="nui-textbox nui-form-input" dataType="currency"/>
	</div>
	<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
		<a class="nui-button" id="saveSubGrtRel_save" iconCls="icon-save" onclick="save">确定</a>
	</div>
</div>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var id="<%=request.getParameter("id") %>";
	var suretyAmt="<%=request.getParameter("suretyAmt") %>";
	var contractAmt="<%=request.getParameter("contractAmt") %>";
	var conYuE="<%=request.getParameter("conYuE") %>";
	
	function save(){
	
		form.validate();
        if (form.isValid()==false){
        	nui.alert("请按规则填写信息");
        	return;
        }
		var json = nui.encode({"id":id,"suretyAmt":nui.get("suretyAmt").getValue()});
		$.ajax({
	        url: "com.bos.grt.subContractManage.subContract.updateConSub.biz.ext",
	        type: 'POST',
	        data: json,
	        contentType:'text/json',
	        cache: false,
	        success: function (data) {
	        
	        	if(data.msg){
	        		alert(data.msg);
	        	}else{
	        		alert("修改成功！");
	        		CloseWindow("ok");
	        	}
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            alert(jqXHR.responseText);
	        }
        });	
	
	
	}
</script>
</body>
</html>