<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<script type="text/javascript" src="<%=contextPath%>/csm/js/commValidate.js"></script>
<html>
<!-- 
  - Author(s): cp
  - Date: 2017-07-16 10:23:12
  - Description:
-->
<head>
<title>修改机构下的公积金</title>
</head>
<body>
<div id="form1" style="width:99.5%;height:99.5%;overflow:hidden;">
	<fieldset>
  	<legend>
    	<span>公积金账号信息</span>
    </legend>
		<div class="nui-dynpanel" id="table1" columns="4" >
			<label>机构名称：</label>
			<input id="orgEntrustAccount.orgCode" name="orgEntrustAccount.orgCode" required="true" enabled="false" class="nui-buttonEdit" allowInput="false" onbuttonclick="selectEmpOrg" dictTypeId="org"/>
		
			<label>委托存款账号：</label>
			<input id="orgEntrustAccount.entrustAcc" name="orgEntrustAccount.entrustAcc" required="true"  class="nui-textbox nui-form-input" vtype="maxLength:32"  />
		
			<!-- <label>委托贷款收息账号：</label>
			<input id="orgEntrustAccount.entrustReturnAcc" name="orgEntrustAccount.entrustReturnAcc" vtype="maxLength:100" required="false"  class="nui-textbox nui-form-input"  /> -->
			
			<label>委托人收本账号：</label>
			<input id="orgEntrustAccount.entrustReturnPrincipalAcc" name="orgEntrustAccount.entrustReturnPrincipalAcc" required="true" class="nui-textbox nui-form-input" />
				
			<label>委托人收息账号：</label>
			<input id="orgEntrustAccount.entrustReturnInterestAcc" name="orgEntrustAccount.entrustReturnInterestAcc" required="true"  class="nui-textbox nui-form-input"/>
		</div>
	</fieldset>
	<div class="nui-toolbar" style="border:0;text-align:right;padding-right:20px;">
	    <a id = "btnSave" class="nui-button" style="margin-right:5px;"  iconCls="icon-save" onclick="add">保存</a>
    	<a id = "btnClose" class="nui-button" iconCls="icon-close"  onclick="CloseWindow('ok')">关闭</a>
	</div>
</div>
	<script type="text/javascript">
		nui.parse();
		var orgCode = "<%=request.getParameter("orgCode") %>";
		nui.get("orgEntrustAccount.orgCode").setValue(orgCode);
    	var form = new nui.Form("#form1");
    	init();
    	function init(){
    		var jsons = nui.encode({"orgCode":orgCode});
    		 $.ajax({
	            url: "com.bos.utp.rights.funds.fundQuery.biz.ext",
	            type: 'POST',
	            data: jsons,
	            cache: false,
	            async: false,
	            contentType:'text/json',
	            success: function (text) {
	           		form.setData(text);
	           	 }
	            });
    	}
    	function add(){
	    	if (form.isValid()==false){
	         	nui.alert("请按规则填写信息");
	         	return false;
	        }
	        var o = form.getData();
    		var json = nui.encode({"orgEntrustAccount":o.orgEntrustAccount});
	        $.ajax({
	            url: "com.bos.utp.rights.funds.fundupdate.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            async: false,
	            contentType:'text/json',
	            success: function (text) {
	            if(text.msg !='111111'){
	            		nui.alert("修改成功!");
	            		CloseWindow('ok')
	            	}else{
						nui.alert("修改失败!");	            	
	            	}
	            }
	            });
    	}
    </script>
</body>
</html>