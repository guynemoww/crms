<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2013-11-21

  - Description:TB_PUB_MARKET_INFO, com.bos.pub.sys.TbPubMarketInfo-->
<head>
<%@include file="/common/nui/common.jsp" %>
<title>查看</title>
</head>
<body>
<div id="form1" style="width:100%;height:auto;">
	<div class="nui-dynpanel" columns="2">
		<label>日期：</label>
		<input name="work.performtime" class="nui-text nui-form-input" dateFormat="yyyy-MM-dd HH:mm:ss" style="width:300px;"/>

		<label>审批机构：</label>
		<input name="work.orgName"  class="nui-text nui-form-input" style="width:300px;"/>

		<label>审批人：</label>
		<input name="work.userName" class="nui-text nui-form-input" style="width:300px;"/>

		<label>岗位：</label>
		<input name="work.activityName"  class="nui-text nui-form-input" style="width:300px;"/>

		<label>审批意见：</label>
		<input colspan="3" name="work.opinion" id="work.opinion" class="nui-textarea nui-form-input" vtype="maxLength:4000" 
			style="width:500px;height:200px;"/>
	</div>
	<div class="nui-toolbar" style="text-align: center; border: none">
		<a class="nui-button" iconCls="icon-search" onclick="countgb()">关闭</a>
	</div>
</div>


	<script type="text/javascript">
    	nui.parse();
    	var form = new nui.Form("#form1");
		form.setEnabled(false);
		nui.get("work.opinion").setEnabled(true);
		//初始化页面，获取页面信息
		function initForm() {
			var json=nui.encode({"workInstanceId":"<%=request.getParameter("workInstanceId") %>"});
			$.ajax({
		        url: "com.bos.accInfo.accInfo.getWorkInfo.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        contentType:'text/json',
		        success: function (text) {
		        	form.setData(text);
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		            nui.alert(jqXHR.responseText);
		        }
			});
		}
		initForm();
		function countgb(){
			CloseWindow("ok");
		}
    </script>
</body>
</html>