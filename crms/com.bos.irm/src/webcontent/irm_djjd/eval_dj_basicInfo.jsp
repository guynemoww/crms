<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): miaolf
  - Date: 2014-04-08 18:58:16
  - Description:
-->
<head>
<title>冻结</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input id="item" class="nui-hidden nui-form-input" name="item"/>
	<fieldset>
  	<legend>
    	<span>基本信息</span>
    </legend>
	<div class="nui-dynpanel" columns="4">
		<label class="nui-form-label">客户编号：</label>
		<input id="party.partyNum" name="party.partyNum" required="false" setEnabled="flase" class="nui-text nui-form-input" vtype="maxLength:100" />

		<label class="nui-form-label">客户名称：</label>
		<input id ='party.partyName' name="party.partyName" required="false" enabled='false' class="nui-text nui-form-input" vtype="maxLength:100" />
		
		<label class="nui-form-label">客户类型：</label>
		<input id="party.partyTypeCd" name="party.partyTypeCd" required="false" valueField="dictID" dictTypeId="XD_KHCD1001" class="nui-text nui-form-input"  vtype="maxLength:100" />	
		
		<label class="nui-form-label">业务性质：</label>
		<input id ='approve.bizType' name="approve.bizType" required="false" dictTypeId="XD_SXYW0002" enabled='false' class="nui-text nui-form-input" vtype="maxLength:100" />
		
		<label class="nui-form-label">批复编号：</label>
		<input id="approve.approvalNum" name="approve.approvalNum" required="false" setEnabled="flase" class="nui-text nui-form-input" vtype="maxLength:100" />
		
		<label class="nui-form-label">批复状态：</label>
		<input id="amount.statusCd" name="amount.statusCd" required="false" dictTypeId="XD_SXCD8003" class="nui-text nui-form-input"  vtype="maxLength:100" />	
		
		<label class="nui-form-label">批复起期：</label>
		<input id="approve.validDate" name="approve.validDate" required="false" setEnabled="flase" class="nui-text nui-form-input" vtype="maxLength:100" />

		<label class="nui-form-label">批复止期：</label>
		<input id ='approve.endDate' name="approve.endDate" required="false" enabled='false' class="nui-text nui-form-input" vtype="maxLength:100" />
		
		<label class="nui-form-label">币种：</label>
		<input id="amount.currencyCd" name="amount.currencyCd" required="false" dictTypeId="CD000001" class="nui-text nui-form-input"  vtype="maxLength:100" />	
		
		<label class="nui-form-label">批复金额：</label>
		<input id ='amount.creditAmount' name="amount.creditAmount" required="false" enabled='false' class="nui-text nui-form-input" vtype="maxLength:100" />
		
		<label class="nui-form-label">已用金额：</label>
		<input id ='amount.creditUsed' name="amount.creditUsed" required="false" enabled='false' class="nui-text nui-form-input" vtype="maxLength:100" />
		
		<label class="nui-form-label">可用金额：</label>
		<input id ='amount.creditAvi' name="amount.creditAvi" required="false" enabled='false' class="nui-text nui-form-input" vtype="maxLength:100" />
		
		<label class="nui-form-label">经办机构：</label>
		<input id ='approve.orgNum' name="approve.orgNum" dictTypeId="org" required="false" enabled='false' class="nui-text nui-form-input" vtype="maxLength:100" />
		
		<label class="nui-form-label">经办人：</label>
		<input id ='approve.userNum' name="approve.userNum" dictTypeId="user" required="false" enabled='false' class="nui-text nui-form-input" vtype="maxLength:100" />
		
	</div>
	</fieldset>
</div>						
<script type="text/javascript">
    nui.parse();
	var form = new nui.Form("#form1");
	var amountId = "<%=request.getParameter("amountId") %>";
	var grid = nui.get("grid1");	
	var projectId;
    init();
    function init(){
	    var json = nui.encode({"amountId":amountId});  	
   	    nui.ajax({//获取客户基本信息
	        url: "com.bos.irm.queryInfo.queryCustInfodj.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
		    async:false,        
	        contentType:'text/json',
	        success: function (text) {
	        	if(text.msg){
	        		alert(text.msg);
	        	} else {
	                var o = nui.decode(text);
	                form.setData(o);
	            }
	        }
	    });
    }
 
</script>
</body>
</html>