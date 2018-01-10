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
			<input name="partyName" required="false" class="nui-text nui-form-input"/>
			
			<label>客户编号：</label>
			<input name="partyNum" required="false" class="nui-text nui-form-input"/>
			
			<label>提示类型代码：</label>
			<input name="infoTypeCd" required="false" dictTypeId="XD_DHCD0015" class="nui-text nui-form-input"/>
			
			<label>提醒日期：</label>
			<input name="infoDt" required="false" class="nui-text nui-form-input"/>
			
			<label>提醒信息：</label>
			<div colspan="3">
				<input id="infoComment" name="infoComment" required="false" class="nui-textarea nui-form-input" />
			</div>
			
			<label>前次检查日期：</label>
			<input name="priorCheckDt" required="false" class="nui-text nui-form-input"/>
			
			<label>本次检查截止日期：</label>
			<input name="nextCheckDt" required="false" class="nui-text nui-form-input"/>
					
			<label>经办机构：</label>
			<input name="orgNum" required="false" dictTypeId="org" class="nui-text nui-form-input"/>
			
			<label>经办人：</label>
			<input id="userNum" name="userNum" required="false" dictTypeId="user" class="nui-text nui-form-input"/>
		</div>
		<div class="nui-toolbar" style="border-bottom:0;text-align:right;padding-top:5px;padding-bottom:5px;">
			<a class="nui-button" style="margin-right:5px;height:21px;" onclick="add()"> 确 认 </a>
			<a class="nui-button" style="margin-right:5px;height:21px;" iconCls="icon-remove" onclick="remove()">删除</a>
		</div>
	</div>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form1");
	var afterLoanInfoId = "<%=request.getParameter("afterLoanInfoId") %>";
	init();
	function init(){
		var json = nui.encode({"afterLoanInfoId":afterLoanInfoId});
		nui.ajax({
	        url: "com.bos.pub.loanAfter.queryAfterLoanInfo.biz.ext",
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
	                form.setData(o.out);
	            }
	        }
	    });
	    nui.get("infoComment").setEnabled(false);
	}
	
	function remove(){
		nui.confirm("该提示信息将被删除，确定？","删除确认",function(action){
			if(action!="ok"){
				return;
			}else{
				var json = nui.encode({"afterLoanInfoId":afterLoanInfoId});
				$.ajax({
		            url: "com.bos.pub.loanAfter.deleteAfterLoanInfo.biz.ext",
			        type: 'POST',
			        data: json,
			        cache: false,
			        contentType:'text/json',
		            success: function (text) {
		                if (text.msg) {
		                    nui.alert(text.msg);
		                    return;
		                }
		             },
		            error: function () {
		                nui.alert("操作失败！");
		            }
		      	});
		      	CloseWindow("cancel");
			}
		});
	}
	
	function add(){
		var json = nui.encode({"afterLoanInfoId":afterLoanInfoId});
		$.ajax({
            url: "com.bos.pub.loanAfter.updateAfterLoanInfo.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
	        contentType:'text/json',
            success: function (text) {
                if (text.msg) {
                    nui.alert(text.msg);
                    return;
                }
             },
            error: function () {
                nui.alert("操作失败！");
            }
      	});
		CloseWindow("cancel");
	}

</script>
</body>
</html>