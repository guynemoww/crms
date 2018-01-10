<%@page pageEncoding="UTF-8"%>

<html>
<!-- 
  - Author(s): zengfang
  - Date: 2014-02-13 17:44:04
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp"%>
<title>检查意见</title>
</head>
<body>
<div>
	<div>检查意见</div>
	<input class="nui-textarea nui-form-input" name="item.gradeExpr" required="false" style="width:100%;height:70px;"/>
	
	<div class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
	    <a class="nui-button" id="btnCreate" onclick="upd">提交</a>
	    <a class="nui-button" id="btnCreate" onclick="tempAdd">临时保存</a>
	    <a class="nui-button" id="btnCreate" onclick="del">撤销</a>
	</div>
</div>
<script type="text/javascript">
	nui.parse();
	
	//提交检查意见   未从表中找到检查意见信息。。
	function upd(){
	
	}
	
	//临时保存检查意见
	function tempAdd(){
	
	}
	
	//撤销检查意见
	function del(){
	
	}

</script>
</body>
</html>