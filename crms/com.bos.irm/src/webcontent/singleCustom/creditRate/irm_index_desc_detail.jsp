<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): miaolf
  - Date: 2014-04-14 09:31:26
  - Description:
-->
<head>
<title>非财务信息</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>

	<div style="width:90%;height:10px;"></div>
	<div id="form1" style="width:90%;height:85%; margin-left:20px;">
		<input id='indexDesc' class='nui-textarea nui-form-input' name='indexDesc' style='text-align:left;width:100%;height:90%;'/>
	</div>
	<div  class="nui-toolbar" style="text-align:right;padding-top:0px;padding-right:20px;" 
    borderStyle="border:0;">
	    <a class="nui-button" id="btnCancel"  onclick="close()">关闭</a>
	</div>

	<div style="width:90%;height:10px;"></div>
<script type="text/javascript">
	nui.parse();
	
    var form1 = new nui.Form("#form1"); 
	
	var applyId = '<%=request.getParameter("applyId")%>';
    var indexId ='<%=request.getParameter("indexId")%>';
    <%String indexId = request.getParameter("indexId"); %>
    var indexType = '02';//非财务
    function initFrom(){
		var json = nui.encode({"applyId":applyId,"indexId":indexId,"indexType":indexType});
	    $.ajax({
	        url: "com.bos.irm.queryRatingResult.queryNonFinIndexDesc.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
	        contentType:'text/json',
	        cache: false,
	        success: function (text) {
	        	var o = nui.decode(text);
	     		//form1.setData(o);
	     		nui.get("indexDesc").setValue(text.nonFinIndexDesc.indexDesc);
	     		nui.get("indexDesc").setReadOnly(true);
	     		//form1.setEnabled(false);
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            nui.alert(jqXHR.responseText);
	            git.unmask();
	        }
	   });
	}
	initFrom();
	
	function close(){
		CloseWindow();
	}

</script>
</body>
</html>
