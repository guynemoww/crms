<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 王世春
  - Date: 2013-08-18 12:42:24
  - Description:
-->
<head>
<title>查询客户</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<fieldset>
<legend>
    	<span>单一客户查询</span>
    </legend>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
		<div class="nui-dynpanel" columns="4" style="text-align:center;">
		    <div style="text-align:center;">客户名称</div>
		    <input name="partyName" required="false"  class="nui-textBox nui-form-input" style="width:100%;" />
		    <div style="text-align:center;">客户编号</div>
		    <input name="partyNum" required="false"  class="nui-textBox nui-form-input" style="width:100%;" />
		</div>
</div>
<div class="nui-toolbar" style="border-bottom:0;text-align:right;">
	    <a class="nui-button" iconCls="icon-add" onclick="query()" id="btnAdd">查询</a>
</div>
</fieldset>

<fieldset>
<legend>
    	<span>单一客户列表</span>
    </legend>
	<div id="datagrid1" class="nui-datagrid" style="width:100%;height:auto;"  sortMode="client"
	    url="com.bos.ews.monitor.querySingleCsm.biz.ext" dataField="csmWarnings"
	    allowAlternating="true" multiSelect="false"
	    sizeList="[10,20,50,100]">
	    <div property="columns">
	        <div type="checkcolumn">选择</div>
	        <div field="partyName" allowSort="true" width="20%" headerAlign="center" >客户名称</div> 
	        <div field="partyNum" allowSort="true" width="" headerAlign="center">客户编号</div>
	        <div field="orgNum" allowSort="true" width="20%" headerAlign="center" dictTypeId="org" >所属经办机构</div>
	        <div field="userNum" allowSort="true" width="20%" headerAlign="center" dictTypeId="user" >所属经办人</div>       
	    </div>
	</div>
</fieldset>
<div class="nui-toolbar" style="text-align:right;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button" onclick="view">预警管理</a>
</div>

<script type="text/javascript">
	nui.parse();
    var form = new nui.Form("#form1");
	var grid = nui.get("datagrid1");
	
	function query(){
	   git.mask();                                                     //表单遮罩
       var o = form.getData();                                         //逻辑流必须返回total
       grid.load({corp:o});                                            //加载客户数据
       git.unmask();                                                   //加载数据完成取消遮罩
	}
    query();

//给选中客户信息增加预警信号
	function view(){
	    git.mask();                                                    //表单遮罩
		var row=grid.getSelected();                                    //取得所选客户
		if (null == row) {
			nui.alert("请选择一条记录");
			git.unmask();
			return;
		}
		//alert(row.partyId);   
		var url=nui.context + "/ews/warnDetail/warnMgr/tree/ews_warnMgr_tree_add.jsp?corpid="+row.partyId+"&type=01";
		git.go(url);                                                   //跳转到新增预警信号页面              
	}
</script>
</body>
</html>