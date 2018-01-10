<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-06-26
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<ul id="workCount">
</ul>
			
    <script type="text/javascript">
	 	nui.parse();
	 	
	function initWorkCount() {
   		git.mask();
   		var json = nui.encode({o:1});
        $.ajax({
            url: "com.bos.bps.op.WorkFlowManager.workinglistCount.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            async: true, //异步处理
            success: function (text) {
            	git.unmask();
            	if(text.flows){
            		var flows=text.flows;
            		//nui.alert(nui.encode(flows));
            		var html="";
            		for (var i=0; i<flows.length; i++) {
            			var t=nui.getDictText("XD_WFCD0001",flows[i].flowTypeCd);
            			if(null == t || '' == t){
            				t = "其它";
            			}
            			html +='<li><a href="#" onclick="git.go(\'<%=request.getContextPath() %>/csm/workdesk/mywork.jsp?flowTypeCd='
            				+ flows[i].flowTypeCd
            				+ '\');return false;">'
            				+ t
            				+ '&nbsp;('
            				+ flows[i].cnt
            				+ ')</a></li>';
            		}
            		document.getElementById("workCount").innerHTML=html;
    		//<li><a href="<%=request.getContextPath() %>/csm/workdesk/mywork.jsp" onclick="">评级申请 2</a></li>
            	} else {
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                git.unmask();
                nui.alert(jqXHR.responseText);
            }
        });
   	}
   	initWorkCount();
	</script>
</body>
</html>
