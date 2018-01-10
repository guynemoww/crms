<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-03-31

  - Description:TB_CON_LOAN_ACCOUNT_INFO, com.bos.dataset.pay.TbConLoanAccountInfo-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:96%;height:auto;overflow:hidden; text-align:center;margin: 10px;"  class="nui-form">
	<div>
		<label>报文：</label>
		<input id="item.msginfo" name="item.msginfo" class="nui-textarea nui-form-input"  style="width:500px;height:300px;overflow:hidden; text-align:center;margin: 10px;" />
	</div>
</div>
				
<div class="nui-toolbar" style="border-bottom:0;text-align:center;">
	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
</div>
	    
			
 <script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1");


//初始化页面，获取页面信息
function initForm() {
	var json=nui.encode({"item":
		{"tradId":
		"<%=request.getParameter("tradId") %>"}});
	$.ajax({
        url: "com.bos.comm.biz.CrudItem.tradinfo_bw.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.msg){
        		nui.alert(text.msg);
        	} else {
        		form.setData(text);
        	}
        },
        error: function (jqXHR, textStatus, errorThrown) {
            nui.alert(jqXHR.responseText);
        }
	});
}
initForm();

	</script>
</body>
</html>
