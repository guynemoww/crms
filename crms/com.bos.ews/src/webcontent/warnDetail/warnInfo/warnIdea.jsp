<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): zengfang
  - Date: 2014-02-13 17:44:04
  - Description:
-->
<head>
<title>流程提交</title>
</head>
<body>
<a style="font-weight: bolder">此页面用于流程提交，在完善以上信息后可以在此页面提交流程至下一岗位审核</a>
<a class="nui-button" iconCls="icon-upload" onclick="submit" id="biz_uploan_save" style="float: right;"/>流程提交</a>
<script type="text/javascript">
	nui.parse();
	var node =<%=request.getParameter("node") %>;
    //openSubmitView(node);
    function submit(){
       openSubmitView(node);
    }
</script>
</body>
</html>