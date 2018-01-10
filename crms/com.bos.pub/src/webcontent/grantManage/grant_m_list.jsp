<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp" %>
<html>
<!-- 
  - Author(s): ljf
  - Date: 2015-07-03 09:05:14
  - Description:小贷中心授权维护
-->
<head>
<title>小贷中心授权维护</title>
</head>
<body>

<div id="form1" class="nui-form" style="overflow:hidden;" >
		<div class="nui-dynpanel" columns="4">
			<label>用户名称：</label>
			<input id="item.userName" name="item.userName" class="nui-textbox nui-form-input"  />
		</div>
		<div class="nui-toolbar" style="text-align:right;border:none" >
		    <a class="nui-button" style="margin-right:5px;height:21px;" iconCls="icon-search" onenter="onKeyEnter" onclick="search()">查询</a>
			<a class="nui-button" style="margin-right:23px;height:21px" iconCls="icon-reset" onclick="reset()">重置</a>
		</div>
</div>
<div class="nui-toolbar" style="border-bottom:0;padding:0px;">
	<a class="nui-button" iconCls="icon-add" onclick="editRow(1)" plain="true">增加</a>
	<a class="nui-button" iconCls="icon-edit" onclick="editRow(2)" plain="true">编辑</a>
    <a class="nui-button" iconCls="icon-remove" onclick="removeRow()" plain="true">删除</a>
    <span class="separator"></span>
    <a class="nui-button"  onclick="uploadExcel" plain="true">导入</a>
</div>
<div id="datagrid1" class="nui-datagrid" style="width:100%;height:auto;" 
url="com.bos.pub.grantManage.grantManage.queryGrantsM.biz.ext" idField="id"
allowResize="true" pageSize="10"  dataField="items" multiSelect="false" sizeList="[10,20,50,100]">

<div property="columns"> 
    <div type="checkcolumn">选择</div>
    <div field="userId" allowResize="false"  headerAlign="center" allowSort="true" >用户编号</div>
    <div field="userName" allowResize="false"  headerAlign="center" allowSort="true" >用户名称</div>
    <div field="maxAmt"  allowSort="true" >权限金额(万元)</div>            
</div>
</div>
</body>
<script type="text/javascript">
	nui.parse();
	var grid = nui.get("datagrid1");
	var form = new nui.Form("#form1"); 
	grid.load();
	//搜索事件
	function search() {       
        var o = form.getData();
        grid.load(o);
    }
    //回车键事件
    function onKeyEnter(e) {
        search();
    }
    function reset(){
		form.reset();
	}
	
	//新增/编辑事件
	function editRow(v){
		var title="新增";
		var gId=null;
		if('2'==v){
			title="编辑";
			var row = grid.getSelected();
			if(null==row || ''==row){
			
				nui.alert("请选择记录");
				return;
			}else{
				gId = row.gId;
			}
		}
		nui.open({
            url: nui.context + "/pub/grantManage/grant_m_edit.jsp?gId="+gId+"&v="+v,
            title: title, 
            width: 500, 
        	height: 300,
        	allowResize:true,
        	showMaxButton: true,
            ondestroy: function (action) {
                if(action=="ok"){
                    grid.reload();
                }
            }
        });
	
	}
	
	
	//删除事件
	function removeRow() {
     
     	var row = grid.getSelected();
		if(row){
			var json = {"item":row};
	        grid.loading("删除中，请稍后......");
	        nui.ajax({
	            url: "com.bos.pub.grantManage.grantManage.deleteGrantM.biz.ext",
	            type: 'POST',
	            data: json,
	            success: function (text) {
	            	if(null != text.msg && ''!=text.msg){
	            	
	            		alert(text.msg);
	            	}else{
	            	
		                grid.reload();
	            	}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                alert(jqXHR.responseText);
	            }
	        });
		}else{
			nui.alert("请选择记录");
			return;
		}
    }
    
    //
  	function uploadExcel(){
  	
  		nui.open({
            url: nui.context + "/pub/grantManage/grant_m_export.jsp",
            title: "导入", 
            width: 600, 
        	height: 200,
        	allowResize:true,
        	showMaxButton: true,
            ondestroy: function (action) {
                    grid.reload();
            }
        });
  	}
  	
</script>
</html>