<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn 请在此写上作者
  - Date: 2014-09-10

  - Description:TB_BATCH_TASK_DATE, com.bos.dataset.batch.TbBatchTaskDate-->
<head>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;">
	<div title="重跑标识设置" >
	<center>
<div id="form1" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
<input type="hidden" name="tbBatchTaskDate" class="nui-hidden" />
	<div class="nui-dynpanel" columns="6">
		<label>批量日期：</label>
		 <input name="tbBatchTaskDate.batchDate" required="true" class="nui-datepicker nui-form-input" vtype="maxLength:5"
				id="tbGrtFund.createTime" format="yyyy-MM-dd" enabled="false" />
        <label>重跑标识：</label>
		<input name="tbBatchTaskDate.batchTemp1" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:5"
				dictTypeId="XD_0002"/>
				
	</div>
	<div class="nui-toolbar" style="text-align:right;border:none">
		<a class="nui-button" style="margin-right:23px;height:21px" iconCls="icon-save" onclick="save()" id="btnSave">保存</a>
	</div>
</div>
				
</center>
	</div>
	</div>	    
			
    <script type="text/javascript">
   nui.parse();
    var form = new nui.Form("#form1");
	if ("<%=request.getParameter("view") %>"=="1") {
		form.setEnabled(false);
		nui.get("btnSave").hide();
	}

function initForm() {
	var json=nui.encode({"tbBatchTaskDate":
		{"batchDateid":
		"<%=request.getParameter("batchDateid") %>"}});
	$.ajax({
        url: "com.bos.batch.againFlag.getTbBatchTaskDate.biz.ext",
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

function save() {
	form.validate();
	if (form.isValid() == false) {
		nui.alert("请将信息填写完整");
		return;
	}
	var o=form.getData();
	var json=nui.encode(o);
	//nui.alert(json);return;
	$.ajax({
        url: "com.bos.batch.againFlag.updateTbBatchTaskDate.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	nui.alert(" 保存成功");
        },
        error: function (jqXHR, textStatus, errorThrown) {
            nui.alert(jqXHR.responseText);
        }
	});

}
	</script>
</body>
</html>
