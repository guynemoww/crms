<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2014-07-18 16:26:53
  - Description:
-->
<head>
<title>岗位责任人选择人员信息</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<center>
<div id="panel4" class="nui-panel" title="添加成员信息" expanded="true"
			style="width:99.5%;height:auto;" showToolbar="false"
			showCollapseButton="true" showFooter="false" allowResize="false">
		<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
			<div class="nui-dynpanel" columns="4">
				<label>岗位名称：</label>
				<input name="posiname" class="nui-textbox nui-form-input"/>
				<label>机构名称：</label>
				<input name="orgName" class="nui-textbox nui-form-input"/>
				<label>工号：</label>
				<input name="userNum" class="nui-textbox nui-form-input"/>
				<label>用户姓名：</label>
				<input name="userName" class="nui-textbox nui-form-input"/>
			</div>
		</div>
		<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
		    <a class="nui-button"  iconCls="icon-search" onclick="query">查询</a>
		    <a class="nui-button" onclick="reset">重置</a>
		</div>
		<div id="grid" class="nui-datagrid" style="width:99.5%;height:auto" 
			 url="com.bos.aft.choosePost.getPostUsers.biz.ext" dataField="members" allowResize="false" 
			 showReloadButton="false" multiSelect="true" pageSize="20" sortMode="client" 
			 showPager="true" showFooter="false" virtualScroll="true">
			<div property="columns">
				<div type="checkcolumn">选择</div>
				<div field="empname" headerAlign="center" allowSort="false">成员名称</div>
				<div field="userid" headerAlign="center" allowSort="false">成员编号</div>
				<div field="posiname" headerAlign="center" allowSort="false">成员编号</div>
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
	var o = form.getData();
	grid.load(o);
	//查询
	function query(){
       	var form = new nui.Form("#form");
		var o = form.getData();
		//var json=nui.encode(o);
      	grid.load(o);
	}
	function add(){
		var rows = grid.getSelecteds();
		if (null == rows) {
			nui.alert("请选择一条记录");
			return false;
		}
		var json = nui.encode({"members":rows,"bizId":"<%=request.getParameter("bizId")%>","responsiblePersonType":"8"});
            grid.loading("保存中，请稍后......");
            nui.ajax({
                url: "com.bos.aft.choosePost.savePostUsers.biz.ext",
                type: 'POST',
                data: json,
                success: function (text) {
                	nui.alert("添加成功!");
                	CloseWindow("ok");
                }
               
            });
	}
</script>
</body>
</html>