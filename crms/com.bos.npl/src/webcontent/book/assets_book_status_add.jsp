<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2014-12-30 09:52:28
  - Description:
-->
<head>
<title>增加状态</title>
</head>
<body>
<center>
<div id="form1" class="nui-form" style="width:70%;height:auto;overflow:hidden;margin-top:20px" >
	<div class="nui-dynpanel"  columns="2">
		
		<label>处置状态</label>
		<input id="" name="" required="false"  class="nui-dictcombobox nui-form-input"  />

		<label>更新时间</label>
		<input id="" name="" required="false" class="nui-datepicker nui-form-input"   format="yyyy-MM-dd"  />
	
	</div>
	<div class="nui-toolbar" style="text-align:right;border:none" >
	    <a id="" class="nui-button" style="margin-right:55px;" iconCls="icon-save" onclick="">保存</a>
		<a class="nui-button" style="margin-right:23px;height:21px" iconCls="icon-reset" onclick="reset()">返回</a>
	</div>
</div>
</body>
</html>