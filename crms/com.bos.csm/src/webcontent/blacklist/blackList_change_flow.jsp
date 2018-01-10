<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): 王世春
  - Date: 2013-08-17 00:02:17
  - Description:
-->
<head>
<title>我的工作</title>
</head>
<body>

<script type="text/javascript">

    nui.parse();

	var bizId = "<%=request.getParameter("bizId") %>";
	var bizType = "<%=request.getParameter("bizType") %>";
	<%--bizType=1 追加 bizType=2 退出--%>
	if(!bizId){
		alert("未获取到唯一主键");
		return;
	}
	function init(){
		var url= nui.context ;
		if(bizType=="1"){
			url += "csm/blacklist/csm_blackList_add.jsp?partyId="+bizId;
		}else{
			url += "csm/blacklist/csm_blackList_list.jsp?partyId="+bizId;
		}
		git.go(url);
            
	}
	init();
	
</script>
</body>
</html>
