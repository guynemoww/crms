<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 陈川
  - Date: 2016-8-26 16:17:00
  - Description:股东和对外股权投资选择自然人和公司页面
-->
<head>
<title>查询客户</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="form1" class="nui-form"style="width:99.5%;height:auto;overflow:hidden;">
	<input name="sqlName" id="sqlName" class="nui-hidden" value="com.bos.csm.pub.getNaturalAndCorp.getPartyList" />
	<div class="nui-dynpanel" id="table1" columns="4">
		<label>银行行号：</label>
		<input id="FBHHHH"name="FBHHHH" class="nui-textbox nui-form-input" />
		<label>银行名称：</label>
		<input id=KEHYWM" name="KEHYWM" class="nui-textbox nui-form-input"/>
	</div>
	<div class="nui-toolbar" style="text-align:right;border:none;padding-top: 10px;" >
	    <a class="nui-button" iconCls="icon-save" iconCls="icon-search" onclick="query()">查询</a>
		<a class="nui-button" iconCls="icon-save" iconCls="icon-reset" onclick="reset()">重置</a>
	</div>
</div>
<div id="datagrid1" class="nui-datagrid" style="width:100%;height:auto;"  sortMode="client"
	url="com.bos.bizApply.bizApply.queryPZFHH.biz.ext" dataField="retuMap"
	allowAlternating="true" multiSelect="false"  showReloadButton="false"
	sizeList="[10,20,50,100]" pageSize="10">
	<div property="columns">
		<div type="checkcolumn">选择</div>
		<div field="FBHHHH" allowSort="false"  headerAlign="center" >银行行号</div> 
		<div field="KEHYWM" allowSort="false"  headerAlign="center" >银行名称</div>
		<!--<div field="FBHQSH" allowSort="false"  headerAlign="center" >所属法人行号</div>-->
		<div field="DIHDIG" allowSort="false"  headerAlign="center" >联系电话</div>
	</div>
</div>

<div id="tools" class="nui-toolbar" style="text-align:right;padding-right:20px;border:0;">
	<a id = "btnSave" class="nui-button" style="margin-right:5px;"  iconCls="icon-save" onclick="selected">选中</a>
	<!-- <a id = "btnClose" class="nui-button" iconCls="icon-close"  onclick="CloseWindow('ok')">关闭</a> -->
</div>

<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form1");
	var grid = nui.get("datagrid1");
	query();
	
    function query(){
		form.validate();
		var o = form.getData();
		grid.load(o);
		nui.get("datagrid1").show();
		nui.get("tools").show();
    }
    
    function selected() {
      var row = grid.getSelected();
        if (row) {
            CloseWindow("ok");
        } else {
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
    
    function reset(){
		form.reset();
		query();
	}
</script>
</body>
</html>