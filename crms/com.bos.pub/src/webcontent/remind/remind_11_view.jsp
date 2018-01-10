<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): miaolf
  - Date: 2014-08-25 14:12:10
  - Description:
-->
<head>
<title>查看提示信息</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
	<div id="form1" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
		<div class="nui-dynpanel" columns="4">
			<label>客户名称：</label>
			<input id="item.partyName" name="item.partyName"  class="nui-text nui-form-input"/>
			
			<label>合同编号：</label>
			<input id="item.contractNum" name="item.contractNum" class="nui-text nui-form-input"/>
			
			<label>存单号：</label>
			<input id="item.depositNum" name="item.depositNum"   class="nui-text nui-form-input"/>
			
			<label>存单金额：</label>
			<input id="item.depositAmt" name="item.depositAmt"  enabled="false" class="nui-textbox nui-form-input"  dataType="currency"/>
			
			<label>存单起始日：</label>
			<input id="item.depositStartDate" name="item.depositStartDate"  class="nui-datepicker nui-form-input" format="yyyy-MM-dd" enabled="false"/>
			
			<label>存单到期日：</label>
			<input id="item.depositEndDate" name="item.depositEndDate"  class="nui-datepicker nui-form-input" format="yyyy-MM-dd" enabled="false"/>
			
			<label>距离存单到期日：</label>
			<input id="item.toEndDateDays" name="item.toEndDateDays"  class="nui-text nui-form-input"/>
		</div>
		<div class="nui-toolbar" style="border-bottom:0;text-align:right;padding-top:5px;padding-bottom:5px;">
			<a class="nui-button" style="margin-right:5px;height:21px;" onclick="CloseWindow('ok')"> 关闭 </a>
		</div>
	</div>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form1");
	var remindId = "<%=request.getParameter("remindId") %>";
	var remindType="<%=request.getParameter("remindType") %>";
	var exsql = "com.bos.pub.remind.select_"+remindType+"_id";
	$(document).ready(function(){
		var json = nui.encode({"remindId":remindId,"exsql":exsql});
		nui.ajax({
	        url: "com.bos.pub.Remind.getRemindInfoByType.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
		    async:false,        
	        contentType:'text/json',
	        success: function (text) {
                //var o = nui.decode(text);
                form.setData(text);
	        }
	    });
	});
	
</script>
</body>
</html>