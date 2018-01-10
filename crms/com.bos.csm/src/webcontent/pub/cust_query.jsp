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
    git.mask();
	var partyId = "<%=request.getParameter("partyId") %>";
	var bizId = "<%=request.getParameter("bizId") %>";
	
	if(bizId!="null"){
		partyId = bizId ;
	}

	 
	function init(){
		if (!partyId) {
				git.unmask();
				alert("未获取partyId");
					return;
			}        
        var json=nui.encode({"partyId":partyId});
        $.ajax({
            url: "com.bos.csm.pub.crudCustInfo.getCustByPartyId.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	git.unmask();
            	if(text.msg){
            		nui.alert(text.msg);
            		return;
            	} else {
            		var url = nui.context + text.url+"&bizId="+bizId ;
            		//_alert(url);
            		git.go(url);
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                git.unmask();
                nui.alert(jqXHR.responseText);
            }
        });
	}
	init();
	
</script>
</body>
</html>
