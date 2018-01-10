<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<!-- 
  - Author(s): yangyong
  - Date: 2013-02-28 10:14:50
  - Description:
-->
<head>
<title>下级人员</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="padding:5px;">
	<table style="width:100%;table-layout:fixed;" id="table1" class="table" >
		<tr>
			<!--<td class="tit">机构代码：</td>-->
			<td class="tit nui-form-label">工号：</td>
			<td>
				<input class="nui-textbox" name="criteria._expr[2].empcode" style="width:95%" vtype="maxLength:32"/>
				<input class="nui-hidden" name="criteria._expr[2]._op" value="like"/>
				<input class="nui-hidden" name="criteria._expr[2]._likeRule" value="all"/>
			</td>
			<td class="tit nui-form-label">姓名：</td>
			<td>
				<input class="nui-textbox" name="criteria._expr[3].empname" style="width:95%" vtype="maxLength:64"/>
				<input class="nui-hidden" name="criteria._expr[3]._op" value="like"/>
				<input class="nui-hidden" name="criteria._expr[3]._likeRule" value="all"/>
				
				<input class="nui-hidden" id="orgid" name="criteria._expr[1].inorgid" style="width:95%" vtype="maxLength:64"/>
				<input class="nui-hidden" name="criteria._expr[1]._op" value="="/>
			</td>
			 
			<td class="tit nui-form-label">角色名称：</td>
			<td>
				<input class="nui-textbox" name="criteria._expr[4].rolename" style="width:95%" vtype="maxLength:64"/>
				<input class="nui-hidden" name="criteria._expr[4]._op" value="like"/>
				<input class="nui-hidden" name="criteria._expr[4]._likeRule" value="all"/>
			</td>
			 
			<td class="btn-wrap">
				<input class="nui-button" text="查询" iconCls="icon-search" onclick="search"/>
			</td>
		</tr>
	</table>
</div>
<div id="datagrid1" class="nui-datagrid" style="width:100%; "  sortMode="client" showReloadButton="false"
url="com.bos.utp.org.employee.queryEmpUsers.biz.ext" dataField="treeNodes"  　idField="id" allowResize="true" sizeList="[10,20,50,100]" pageSize="10">
    <div property="columns">
        <div type="checkcolumn">选择</div>
        <div field="empcode" allowSort="true" width="10%" headerAlign="center" >工号</div>  
        <div field="empname" allowSort="true" width="15%" headerAlign="center" >姓名</div> 
        <div field="status" allowSort="true" width="10%" headerAlign="center" renderer="renderStatus" >操作状态</div>       
        <div field="inorgname" allowSort="true" width="20%" headerAlign="center" >所属机构</div>       
        <!--<div field="rolename" allowSort="true" width="20%" headerAlign="center" >所属角色</div>    
        --><div field="lastlogin" allowSort="true" width="25%" headerAlign="center" dateFormat="yyyy-MM-dd HH:mm:ss" >最后签到时间</div>    
    </div>
</div>
 
<div class="nui-toolbar" style="border-bottom:0;text-align:center;">
	<a class="nui-button" iconCls="icon-save" onclick="save()">保存</a>
	<a class="nui-button"  iconCls="icon-close" onclick="window.parent.CloseWindow('close')">关闭</a>
</div>


<script type="text/javascript">
	nui.parse();
	
	(function(){
		if(window.parent.getCurrentNode){
			var node = window.parent.getCurrentNode();
			var parentNode = node;
			window['parentNode'] = parentNode;
		}
	})();
	nui.get("orgid").setValue(window.parentNode.orgid);
	var form = new nui.Form("#form1");
	var grid = nui.get("datagrid1");
	var formData = form.getData(false, true);
    grid.load(formData);
	grid.sortBy("empcode", "desc");
	function search(){
		//校验
		form.validate();
        if (form.isValid()==false) return;
        
		var formData = form.getData(false, true);
		
		//var json = nui.encode(formData);
        grid.load(formData);
	}
	
	var bootPath = "<%=request.getContextPath() %>/";

	function renderGender(e){
		return nui.getDictText("ABF_GENDER",e.row.gender);
	}
	
	function renderEmpStatus(e){
		return nui.getDictText("ABF_EMPSTATUS",e.row.empstatus);
	}
	
	function renderStatus(e){
		return nui.getDictText("ABF_OPERSTATUS",e.row.status);
	}
	//保存方法执行
	function save(){
	 var row = grid.getSelected();
        
        if (row) {
			window.parent.CloseWindow({'result':'ok','data':row});
		 } else {
            alert("请选中一条记录");
        }
	}
</script>

</body>
</html>