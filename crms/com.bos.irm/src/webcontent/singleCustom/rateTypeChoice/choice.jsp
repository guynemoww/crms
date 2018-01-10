<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): miaolf
  - Date: 2014-04-01 15:35:09
  - Description:
-->
<head>
<title>评级类型选择</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<br />
<div align="center">
<div id="form1" style="width:80%;height:auto;overflow:hidden;">

	<div class="nui-dynpanel" columns="2"> 	    	
   	    	<label>评级类型</label>
   	    	<input id="rateType" property="editor" class="nui-dictcombobox" emptyText="--请选择--" textField="dictName"  valueField="dictID" dictTypeId="XCH00001"/>
	</div>
</div>
</div>
<div class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button" onclick="moveTo()">确认</a>
</div>
<br />
	
<script type="text/javascript">
 	nui.parse();
	<%String corpid = request.getParameter("corpid"); %>
    var form = new nui.Form("#form1"); 
    function moveTo() {
        var val = nui.get("rateType").value;
		if(val == '01'){
		    git.go(nui.context+"/irm/singleCustom/creditRate/eval_corp_tab.jsp?corpid=<%=corpid %>&rateType="+val);
		}
		else if(val == '02'){
			git.go(nui.context+"/irm/singleCustom/specialtyRate/eval_corp_tab2.jsp?corpid=<%=corpid %>&rateType="+val);
		}
    }
</script>
</body>
</html>