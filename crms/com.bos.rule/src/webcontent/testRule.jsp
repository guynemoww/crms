<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/nui/common.jsp" %>
<html>
<%-- 
  - Author(s): ljf
  - Date: 2015-04-24 18:07:58
  - Description:
--%>
<head>
<title>测试规则</title>
</head>
<body>
	<div class="nui-toolbar" style="border:0;text-align:right;padding-right:20px;">
	<a class="nui-button" iconCls="icon-save" onclick="test()" id="btnSave">保存</a>
	</div>
</body>
<script type="text/javascript">
nui.parse();

function test(){

   var tjson={"certno":"0001"};
   var msg = exeRule("RCSM_0001","1",tjson);
   if(null != msg && '' != msg){
   		 nui.alert(msg);
   }else{
   		nui.alert("检验通过！");
   }
  
}


</script>
</html>