<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 王世春
  - Date: 2013-08-18 12:42:24
  - Description:
-->
<head>
<title>黑名单客户</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>

<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;height:100%;">
<div title="黑名单客户-名单内" url="../../csm/blacklist/csm_blackList_list.jsp"  refreshOnClick=true></div>
<div title="黑名单客户-名单外" url="../../csm/blacklist/csm_blackList_out.jsp" refreshOnClick=true></div>
<div title="黑名单客户导入" url="../../csm/blacklist/black_import.jsp" refreshOnClick=true></div>
</div> 



</body>
</html>