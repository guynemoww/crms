<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2014-12-24 14:16:34
  - Description:
-->
<head>
<title>资产处置申请信息</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<div id="form" style="width:99.5%;height:auto;overflow:hidden;">  
		<div class="nui-dynpanel" columns="4" id="table1">
			<label id="partyNum"  class="nui-form-label">客户编号：</label>
			<input id="tbNplDispositionScheme.partyNum" name="tbNplDispositionScheme.partyNum" 
			class="nui-text nui-form-input" required="true"/>
	  
			<label id="partyName" class="nui-form-label">客户名称：</label>
			<input id="tbNplDispositionScheme.partyName" name="tbNplDispositionScheme.partyName"
			class="nui-text nui-form-input" required="true"/>
	  
			<label id="partyType" class="nui-form-label">客户类型：</label>
			<input id="tbNplDispositionScheme.partyType" name="tbNplDispositionScheme.partyType"
			class="nui-text nui-form-input" required="true"/>
	  
			<label id="schemeName" class="nui-form-label">方案名称：</label>
			<input id="tbNplDispositionScheme.schemeName" name="tbNplDispositionScheme.schemeName" 
			class="nui-textbox nui-form-input" required="true" />
	  
			<label id="schemeNum" class="nui-form-label">方案编号：</label>
			<input id="tbNplDispositionScheme.schemeNum" name="tbNplDispositionScheme.schemeNum"
			class="nui-textbox nui-form-input" required="true"/>
	  
			<label class="nui-form-label" id="orgNum">申报行：</label>
			<input id="tbNplDispositionScheme.orgNum" name="tbNplDispositionScheme.orgNum"
			class="nui-text nui-form-input" required="true" />
	  
			<label class="nui-form-label" id="declareDate">申报日期：</label>
			<input id="tbNplDispositionScheme.declareDate" name="tbNplDispositionScheme.declareDate" 
			class="nui-text nui-form-input" required="true" />
			<input id="tbNplDispositionScheme.schemeId" name="tbNplDispositionScheme.schemeId" 
			class="nui-hidden nui-form-input" required="true"/>
		</div>
		<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
	    	<a class="nui-button" id="save" iconCls="icon-save" onclick="create">保存</a>
		</div>
	</div>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	query();
	//页面初始化
	function query(){
		var form = new nui.Form("#form");
		var json = nui.encode({"schemeId":"<%=request.getParameter("schemeId")%>"});
		$.ajax({
	        url: "com.bos.npl.assets.AssetsDispositionScheme.getDispositionScheme.biz.ext",
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
	//保存
	function create(){
		form.validate();
        if (form.isValid()==false){
        	nui.alert("请按规则填写信息");
        	return;
        }
        var o = form.getData();
        var json = nui.encode(o);
        $.ajax({
	        url: "com.bos.npl.assets.AssetsDispositionScheme.createDispositionScheme.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
	        contentType:'text/json',
	        success: function (mydata) {
	        	alert("保存成功！");
	            query();
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            nui.alert(jqXHR.responseText);
	        }
	    });
	}
</script>
</body>
</html>