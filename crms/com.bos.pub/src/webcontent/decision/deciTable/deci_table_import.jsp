<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-05-22
  - Description:TB_PUB_DECI_TABLE, com.bos.pub.decision.TbPubDeciTable
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<form id="form1" style="width:100%;height:auto;overflow:hidden;"
	action="" enctype="multipart/form-data" method="post">
	<div class="nui-dynpanel" columns="4">
		<label>决策表名称：</label>
		<input colspan="3" name="tbPubDeciTable.tname" required="true" class="nui-textbox nui-form-input" vtype="maxLength:100" />
		
		<label>决策表内容：</label>
		<input class="nui-htmlfile" colspan="3" required="true" id="file" name="file" limitType="*.xls"/>
	</div>
</form>
				
<div class="nui-toolbar" style="border-bottom:0;padding-right:20px;text-align:right;">
	<a class="nui-button" iconCls="icon-save" onclick="save()">导入</a>
	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
</div>
	    
			
    <script type="text/javascript">
	 	nui.parse();
	    var form = new nui.Form("#form1");
	    var tid="<%=request.getParameter("tid") %>";
	    var msg="<%=request.getParameter("msg") %>";
	    if (tid && tid != 'null') {
	    	_alert('导入成功！');
	    	git.go("/pub/decision/deciTable/deci_table_detail_edit.jsp?tid="+tid+"&view=0");
	    }
	    
	    
function save() {
	form.validate();
	var o=form.getData();
	if (form.isValid() == false) {
		if (!o.tbPubDeciTable || !o.tbPubDeciTable.tname) {
			alert('请填写决策表名称');
		} else {
			alert('请选择一个后缀为xls的Excel文件');
		}
		return;
	}
	$('#form1')[0].action='com.bos.pub.deciTable.importTbPubDeciTable.biz.ext2?tbPubDeciTable/tname='+git.toUrlParam(o.tbPubDeciTable.tname);
	$('#form1')[0].submit();
}
	</script>
</body>
</html>
