<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): sdl
  - Date: 2017-03-29 10:34:01
  - Description:选择机构
-->
<head>
<title>选择机构</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>

	
<div id="form1" class="nui-form"style="width:100%;height:auto;overflow:hidden;">
		<!-- 数据实体的名称 -->
    	<input class="nui-hidden" name="criteria/_entity" value="com.bos.utp.dataset.organization.OmOrganization">
    	<!-- 查询条件 -->
    	<input class="nui-hidden" name="criteria._expr[0]._op" value="="/>
		<input class="nui-hidden" name="criteria._expr[0]._likeRule" value="all"/>
		<input class="nui-hidden" name="criteria._expr[1]._op" value="like"/>
		<input class="nui-hidden" name="criteria._expr[1]._likeRule" value="all"/>
		<!-- 排序字段 -->
        <input class="nui-hidden" name="criteria._orderby[0]._property" value="orgcode">
        <input class="nui-hidden" name="criteria._orderby[0]._sort" value="asc">
		<div class="nui-dynpanel" columns="4">
			<label>机构行号：</label>
			<input name="criteria._expr[0].orgcode" required="false" class="nui-textbox nui-form-input"  />
			<label>机构名称：</label>
			<input name="criteria._expr[1].orgname" class="nui-textbox nui-form-input" required="false"/>
		</div>
		<div class="nui-toolbar" style="text-align:right;border:none" >
    		<a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
			<a class="nui-button" iconCls="icon-reset" onclick="reset()">重置</a>
		</div>
</div>
	
<div id="datagrid1" class="nui-datagrid" style="width:100%;height:auto;"sortMode="client"
		 url="com.bos.utp.org.organization.getOrgList.biz.ext" dataField="omorganizations"
		allowResize="true" showReloadButton="false"    allowAlternating="true"
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
		<div property="columns">
			<div type="checkcolumn" >选择</div>
			<div field="orgcode" headerAlign="center" allowSort="true" autoEscape="false" width="10%">机构行号</div>
			<div field="orgname" headerAlign="center" allowSort="true"align="center" width="20%">机构名称</div>
			<div field="status" headerAlign="center" align="center"allowSort="true" dictTypeId="CDZZ0004" width="10%">机构状态</div>
			<div field="orglevel" headerAlign="center" align="center"allowSort="true" dictTypeId="XD_GGCD6004" width="10%">机构级别</div>
			<div field="linktel" headerAlign="center" align="center"allowSort="true" width="25%">联系电话</div>
			<div field="orgaddr" headerAlign="center" align="center"allowSort="true" width="25%">联系地址</div>
		</div>
</div>

<!-- 选中 关闭 -->
<div id="tools" class="nui-toolbar" style="text-align:right;padding-right:20px;border:0;">
	<a id = "btnSave" class="nui-button" style="margin-right:5px;"  iconCls="icon-save" onclick="selected()">选中</a>
	<a id = "btnClose" class="nui-button" iconCls="icon-close"  onclick="CloseWindow('ok')">关闭</a>
</div>
<script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("datagrid1");
	//查询
	function search() {
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
    }
    search();
    //重置
    function reset(){
		form.reset();
	}
	//选中 
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
    //关闭
	function CloseWindow(action) {
		if (action == "close") {
		} else if (window.CloseOwnerWindow) {
			return window.CloseOwnerWindow(action);
		} else {
			return window.close();
		}
	}
</script>
</body>
</html>