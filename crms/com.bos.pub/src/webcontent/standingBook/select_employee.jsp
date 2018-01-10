<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<!-- 
  - Author(s): 王世春
  - Description:
-->
<head>
<title>人员查询(无权限)</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="padding:5px;">
	<table style="width:100%;table-layout:fixed;" id="table1" class="table" >
		<tr>
			<td class="tit nui-form-label">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			工号：</td>
			<td>
				<input class="nui-hidden" id="orgid" name="criteria._expr[1].inorgid" style="width:95%" vtype="maxLength:64"/>
				<input class="nui-hidden" name="criteria._expr[1]._op" value="="/>
				
				<input class="nui-textbox" name="criteria._expr[2].empcode" style="width:95%" vtype="maxLength:32"/>
				<input class="nui-hidden" name="criteria._expr[2]._op" value="like"/>
				<input class="nui-hidden" name="criteria._expr[2]._likeRule" value="all"/>
			</td>
			<td class="tit nui-form-label">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			姓名：</td>
			<td>
				<input class="nui-textbox" name="criteria._expr[3].empname" style="width:95%" vtype="maxLength:64"/>
				<input class="nui-hidden" name="criteria._expr[3]._op" value="like"/>
				<input class="nui-hidden" name="criteria._expr[3]._likeRule" value="all"/>
			</td>
			 
			<td class="btn-wrap">
				<input class="nui-button" text="查询" iconCls="icon-search" onclick="search"/>
			</td>
		</tr>
	</table>
</div>

<div id="datagrid1" class="nui-datagrid" style="width:100%; "  sortMode="client"
	url="com.bos.utp.org.employee.queryEmpUsers.biz.ext" dataField="treeNodes" onrowdblclick="onOk"
	idField="id" allowResize="true" sizeList="[10,20,50,100]" pageSize="10" multiSelect="false">
    <div property="columns">
        <div type="checkcolumn"></div>
        <div field="empcode" allowSort="true" width="10%" headerAlign="center" >工号</div>  
        <div field="empname" allowSort="true" width="15%" headerAlign="center" >姓名</div> 
        <div field="status" allowSort="true" width="10%" headerAlign="center" dictTypeId="ABF_OPERSTATUS" >操作状态</div>       
        <div field="inorgname" allowSort="true" width="20%" headerAlign="center" >所属机构</div>       
        <div field="lastlogin" allowSort="true" width="25%" headerAlign="center" dateFormat="yyyy-MM-dd HH:mm:ss" >最后签到时间</div>    
    </div>
</div>
<div class="nui-toolbar" style="border-bottom:0;text-align:center;">
    <a class="nui-button" iconCls="icon-save" onclick="onOk">确定</a>
	<a class="nui-button" onclick="onCancel">取消</a>
	<!-- <a class="nui-button" onclick="onClear">清空</a> -->
</div>
<script type="text/javascript">
	nui.parse();
	
	var form = new nui.Form("#form1");
	var grid = nui.get("datagrid1");
	var formData = form.getData();
    grid.load(formData);
	grid.sortBy("empcode", "desc");
	function search(){
		//校验
		form.validate();
        if (form.isValid()==false) return;
		var formData = form.getData();
		//var json = nui.encode(formData);
        grid.load(formData);
	}
	function GetData() {
        return grid.getSelected();
    }
	function onOk() {
		var len=grid.getSelecteds().length;
		if (len != 1) {
			alert('请选择一条记录');
			return;
		}
        CloseWindow("ok");
    }
    function onCancel() {
        CloseWindow("cancel");
    }
    function onClear() {
	    grid.select({});
        CloseWindow("ok");
    }
</script>

</body>
</html>