<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): lujinbin
  - Date: 2014-03-25
  - Description:TB_SYS_DEPARTMENT, com.bos.dataset.sys.TbSysDepartment
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input id="orgid" name="tbSysDepartment.orgNum"  class="nui-hidden" />
	
	<div class="nui-dynpanel" columns="4">


		<label>部门名称：</label>
		<input name="tbSysDepartment.departmentName" required="false" class="nui-textbox nui-form-input" vtype="maxLength:100" />

		<label>部门状态：</label>
		<input name="tbSysDepartment.departmentStatus" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:2" dictTypeId="XD_GGCD877" emptyText="请选择"/>


		<label>部门属性：</label>
		<input name="tbSysDepartment.departmentType" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:10" dictTypeId="XD_GGCD293" emptyText="请选择"/>
		
	</div>
</div>
				
<div class="nui-toolbar" style="padding-right:20px;text-align:right;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
	<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
</div>
	    
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.utp.org.department.getTbSysDepartmentList.biz.ext"
	dataField="tbSysDepartments"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="departmentName" headerAlign="center" allowSort="true" >部门名称</div>
		<div field="orgNum" headerAlign="center" allowSort="true" dictTypeId="org">所属机构</div>
		<div field="departmentStatus" headerAlign="center" allowSort="true" >部门状态</div>
		<div field="departmentType" headerAlign="center" allowSort="true" >部门属性</div>
		<div field="departmentManager" headerAlign="center" allowSort="true" >部门负责人</div>
		<div field="departmentTel" headerAlign="center" allowSort="true" >联系人电话</div>
		</div>
	</div>
	</div>
		<div class="nui-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" 
        borderStyle="border-left:0;border-bottom:0;border-right:0;">
        <a class="nui-button" style="width:60px;" iconCls="icon-ok" onclick="selected()">确定</a>
        <span style="display:inline-block;width:25px;"></span>
</div>	
    <script type="text/javascript">
 	nui.parse();
	nui.get("orgid").setValue("<%=request.getParameter("orgId") %>");
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	
    function search() {
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
    }
    search();
    
    function reset(){
		form.reset();
	}
	
    function selected(){
       var row = grid.getSelected();
        if (row) {
             CloseWindow("ok");
        }else{
          alert("请选中一条记录");
        }
    
    }	
    	
    	function getData(){
    var row = grid.getSelected();
      if (row) {
            return row;
        } else {
            return null;
        }
    }

	</script>
</body>
</html>
