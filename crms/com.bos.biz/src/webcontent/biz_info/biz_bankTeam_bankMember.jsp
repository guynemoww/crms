<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2014-06-27 10:36:21
  - Description:
-->
<head>
<title>添加外部银团成员</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<center>
<div id="panel4" class="nui-panel" title="" expanded="true" style="width:99.5%;height:auto;" showToolbar="false"
		showCollapseButton="true" showFooter="false" allowResize="false">
		<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
				<div class="nui-dynpanel" columns="4">
					<label>成员行名称：</label>
					<input name="bankname" class="nui-textbox nui-form-input"/>
					
					<label>成员行编号：</label>
					<input name="bankcode" class="nui-textbox nui-form-input"/>
				</div>
		</div>
		<div class="nui-toolbar" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px">
		    <a class="nui-button"  iconCls="icon-search" onclick="query">查询</a>
		    <a class="nui-button" iconCls="icon-reset" onclick="reset">重置</a>
		</div>
		<div id="grid" class="nui-datagrid" style="width:100%;height:auto" sortMode="client"
		  	url="com.bos.bizInfo.bizInfo.getApproveBankMember2.biz.ext" dataField="members" multiSelect="false" 
			sizeList="[10,20,50,100]" pageSize="10"> 
			<div property="columns">
				<div type="checkcolumn">选择</div>
				<div field="bankname" headerAlign="center" allowSort="false">成员名称</div>
				<div field="bankcode" headerAlign="center" allowSort="false">成员编号</div>
			</div>
		</div>
		<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
	    	<a class="nui-button" id="btnCreate" iconCls="icon-add" onclick="add">添加</a>
		</div>
</div>
</center>
<script type="text/javascript">
	nui.parse();
	var grid = nui.get("grid");
	var form = new nui.Form("#form");
	//查询
	function query(){
       
		var o = form.getData();
		if((o.bankname==""||o.bankname==null)&&(o.bankcode==""||o.bankcode==null)){
			alert("请输入查询条件");
			return;
		}
      	grid.load(o);
	}
	var data;
	function add(){
    	CloseWindow("ok");
	}
	function getData(){
    	var row = grid.getSelected();
      	if (row) {
            return row;
        } else {
            return null;
        }
    }
	function reset(){
		form.reset();
	}
</script>
</body>
</html>