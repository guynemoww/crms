<%@page pageEncoding="UTF-8"%>

<html>
<!-- 
  - Author(s): zengfang
  - Date: 2014-02-28 16:34:13
  - Description:批量检查平台客户的成员检查情况补充
-->
<head>
<%@include file="/common/nui/common.jsp"%>
<title>检查意见</title>
</head>  
<strong style="text-align: center;">在提交保存前请确认本次填写的信息有无遗漏！</strong>
<body style="margin-left:10px;">
	    <div class=""  id="saveBtn" style="border-bottom:0;text-align:right;margin-top: 20px;">
		     <a class="nui-button" iconCls="icon-save" onclick="btnSave()">确认提交本次填写的指标信息</a>
	    </div>

	<script type="text/javascript">
	nui.parse();
 function btnSave(){   
   var alcInfoId = "<%=request.getParameter("alcInfoId")%>";
   var json = nui.encode({alcInfoId:alcInfoId});
    $.ajax({
            url: "com.bos.aft.indexMgr.updateIndexInfo.biz.ext",
            type: 'POST',
            data: json,
            contentType:'text/json',
            cache: false,
            async:false,
            success: function (mydata) {
            	if(mydata.msg){
            	  alert(mydata.msg);
            	   git.gohome();
            	}
            	
            },
            error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
            }
        });
        }
	</script>
</body>
</html>