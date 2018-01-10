<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-03-25

  - Description:TB_SYS_DEPARTMENT, com.bos.dataset.sys.TbSysDepartment-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<%--   --%><input type="hidden" name="tbSysDepartment" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">
		
		<label>部门编号：</label>
		<input name="tbSysDepartment.departmentId" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" enabled="false"/>

		<label>部门负责人：</label>
		<input name="tbSysDepartment.departmentManager" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />

		<label>部门名称：</label>
		<input name="tbSysDepartment.departmentName" required="false" class="nui-textbox nui-form-input" vtype="maxLength:100" />

		<label>部门状态：</label>
		<input name="tbSysDepartment.departmentStatus" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:2" dictTypeId="XD_GGCD877" emptyText="请选择"/>

		<label>部门属性：</label>
		<input name="tbSysDepartment.departmentType" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:10" dictTypeId="XD_GGCD293" emptyText="请选择"/>
		<label>所属机构：</label>
		<input name="tbSysDepartment.orgNum" required="true" class="nui-buttonEdit" vtype="maxLength:32"  dictTypeId="org"  enabled="false"/>
		<label>联系人电话：</label>
		<input name="tbSysDepartment.departmentTel" required="false" class="nui-textbox nui-form-input" vtype="maxLength:20" />
		
		<label>备注：</label>
		<input name="tbSysDepartment.comment" required="false" class="nui-TextArea" vtype="maxLength:500" />
	
	</div>
</div>
				
<div class="nui-toolbar" style="border-bottom:0;padding-right:20px;text-align:right;">
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

function initForm() {
	var json=nui.encode({"tbSysDepartment":
		{"departmentId":
		"<%=request.getParameter("departmentId") %>"}});
	$.ajax({
        url: "com.bos.utp.org.department.getTbSysDepartment.biz.ext",
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
        url: "com.bos.utp.org.department.updateTbSysDepartment.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.msg){
        		nui.alert(text.msg);
        	} else {
        		CloseWindow("ok");
        	}
        },
        error: function (jqXHR, textStatus, errorThrown) {
            nui.alert(jqXHR.responseText);
        }
	});
}
	</script>
</body>
</html>
