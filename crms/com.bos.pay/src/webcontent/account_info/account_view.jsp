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
	<input id="id" name="tbLoanZh.id"  class="nui-hidden nui-form-input" value=""/>
	<div class="nui-dynpanel" columns="4">
				
				<label>账户类型：</label>
				<input id="zhlx" name="tbLoanZh.zhlx" required="true"  class="nui-dictcombobox nui-form-input" dictTypeId="XD_ZHLX10001"/>
				
				<label>账户名称：</label>
				<input id="zhmc" name="tbLoanZh.zhmc" class="nui-textbox nui-form-input" vtype="maxLength:200" required="true" />
		
				<label>账号：</label>
				<input id="zh" name="tbLoanZh.zh" style="float: left;"   required="true" class="nui-textbox nui-form-input"/>
				
				<label>卡折标志：</label>
				<input id="kzbs" name="tbLoanZh.kzbs" required="true"  class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXYW0220"/>
				
				<label>开户行：</label>
				<input id="zhkhjg"  name="tbLoanZh.zhkhjg" required="true"  class="nui-text nui-form-input" dictTypeId="org"/>
		
			</div>
</div>
				
<div class="nui-toolbar" style="border-bottom:0;text-align:center;">
	<a class="nui-button" iconCls="icon-save" onclick="save()" id="btnSave">保存</a>
	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
</div>
	    
			
    <script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1");
	if ("<%=request.getParameter("view") %>"=="1") {
		form.setEnabled(false);
		nui.get("btnSave").hide();
	}

//初始化页面，获取页面信息
function initForm() {
	var json=nui.encode({"id":"<%=request.getParameter("id") %>"});
	$.ajax({
        url: "com.bos.accInfo.accInfo.getLoanAccInfo.biz.ext",
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
