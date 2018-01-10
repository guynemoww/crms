<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<!-- 
  - Author(s): YANGZHOU
  - Date: 2013-03-01 17:43:27
  - Description:
-->
<style>
#table1 .tit{
	height: 10px;
    line-height: 10px;
}
#table1 td{
	height: 10px;
    line-height: 10px;
}
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@include file="/common/nui/common.jsp" %>
<link id="css_icon" rel="stylesheet" type="text/css" href="<%=contextPath%>/utp/org/css/org.css"/>
<title>机构列表</title>
<body>
<div class="search-condition">
   <div class="list">
	 <div id="form1" >
		<table style="width:100%;table-layout:fixed;" id="table1" class="table" >
			<tr>
				<td class="tit nui-form-label">工号：</td>
				<td>
					<input class="nui-textbox" name="temp/empcode" style="width:95%" vtype="maxLength:32"/>
				</td>
				<td class="tit nui-form-label">姓名：</td>
				<td>
					<input class="nui-textbox" name="temp/empname" style="width:95%" vtype="maxLength:64"/>
				</td>
				<td class="tit nui-form-label">机构名称：</td>
				<td>
					<input class="nui-textbox" name="temp/orgname" style="width:95%" vtype="maxLength:64"/>
				</td>
				<td colspan="2" class="btn-wrap">
					<input class="nui-button" text="查询" iconCls="icon-search" onclick="search"/>
				</td>
			</tr>
			 					
		</table>
	</div>
  </div>
 </div>
<div class="nui-fit" style="padding:5px;">
	<div id="datagrid1" class="nui-datagrid" style="width:100%;height:auto;"  sortMode="client"
	    url="com.bos.utp.org.employee.queryEmployeeByPage.biz.ext" dataField="treeNodes" sizeList="[10,20,50,100]">
	    <div property="columns">
	        <div type="indexcolumn"></div>                
	        <div field="empcode" allowSort="true" width="120" headerAlign="center" >工号</div>  
	        <div field="empname" allowSort="true" width="120" headerAlign="center" >姓名</div> 
	        <div field="status" allowSort="true" width="120" headerAlign="center" renderer="renderStatus" >操作状态</div>       
	        <div field="inorgname" allowSort="true" width="120" headerAlign="center" >所属机构</div>       
	        <!--<div field="rolename" allowSort="true" width="120" headerAlign="center" >所属角色</div>    
	        --><div field="lastlogin" allowSort="true" width="120" headerAlign="center" dateFormat="yyyy-MM-dd HH:mm:ss"  >最后签到时间</div>   
	     </div>
	</div>
</div>
<div class="nui-toolbar" style="border-bottom:0;text-align:center;">
	<a class="nui-button" iconCls="icon-save" onclick="save()">保存</a>
	<a class="nui-button"  iconCls="icon-close" onclick="window.parent.CloseWindow('close')">关闭</a>
</div>
<script type="text/javascript">
	nui.parse();
	
	var form = new nui.Form("#form1");
	var grid = nui.get("datagrid1");
    
    grid.load();
	grid.sortBy("empcode", "desc");
	function search(){
		//校验
		form.validate();
        if (form.isValid()==false) return;
        
		var formData = form.getData(false, true);
		
		//var json = nui.encode(formData);
        grid.load(formData);
	}
	
	function resetForm(){
		form.reset();
	}
	var bootPath = "<%=request.getContextPath() %>";
	
	 //选择机构
    function selectOrg(e) {
        var btnEdit = this;
        nui.open({
            url: bootPath + "/pub/sys/select_org_tree.jsp",
            showMaxButton: false,
            title: "选择机构",
            width: 350,
            height: 350,
            ondestroy: function(action){
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.GetData();
                    data = nui.clone(data);
                    if (data) {
                        btnEdit.setValue(data.orgid);
                        btnEdit.setText(data.orgname);
                    }
                }
            }
        });            
    }
	
	function renderGender(e){
		return nui.getDictText("ABF_GENDER",e.row.gender);
	}
	
	function renderStatus(e){
		return nui.getDictText("ABF_OPERSTATUS",e.row.status);
	}
	
	function reset() {
		form.reset();
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