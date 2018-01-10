<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="/common/nui/common.jsp" %>
<%@page import="com.eos.foundation.data.DataContextUtil"%>
<html>
<!-- 
  - Author(s): 王世春
  - Date: 2014-02-19
  - Description: 岗位管理页面
-->
<head>
	<title>岗位查询</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
</head>
<body style="width:100%;height:100%;">
<div class="search-condition">
	<div class="list">
		<div id="form1">
		<div class="nui-dynpanel" columns="2">
		 <label> 岗位名称： </label>
						<input class="nui-textbox" name="criteria._expr[0].posiname" />
						<input class="nui-hidden" name="criteria._expr[0]._op" value="like" />
						<input class="nui-hidden" name="criteria._expr[0]._likeRule" value="all" />
			</div>
		</div>
	</div>
</div>
<div class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;"  borderStyle="border:0;">
    							<input class="nui-button" iconCls="icon-search" text="查询" onclick="search" />
						<input class="nui-button" text="重置" onclick="form1.reset();" />
						 
        </div>
<div class="nui-toolbar" style="border-bottom:0;padding-right:20px;text-align:right;">
	<a class="nui-button" onclick="selectPosition">确定</a>
	<a class="nui-button" onclick="CloseWindow('cancel');">关闭</a>
</div>
<div id="roleGrid" class="nui-datagrid" style="width:100%;height:auto;" url="com.bos.utp.rights.positionManager.queryPosition.biz.ext"
	idField="roleid" multiSelect="false" allowAlternating="true" showPager="true" sizeList="[15,20,30]" pageSize="20" 
	selectOnLoad="true" onselectionchanged="" sortMode="client">
	<div property="columns">
		<div type="checkcolumn"></div>
		<div field="posicode" width="20%" headerAlign="center" allowSort="true">岗位编码</div>
		<div field="posiname" width="30%" headerAlign="center" allowSort="true">岗位名称</div>
	</div>
</div>
</body>
</html>
<script type="text/javascript">
	var posilevelData=$.grep(nui.getDictData('ABF_ROLETYPE'),function(value,index){
		if (parseInt(value.dictID)<parseInt("<%=DataContextUtil.getString("m:userObject/attributes/orglevel") %>"))
			return false;
		
		return true;
	});
	nui.parse();
	//nui.get("posilevel").setData(posilevelData);
	
	var form1 = new nui.Form("#form1");
	var roleGrid = nui.get("roleGrid");
	//roleGrid.load();
    
	function selectPosition(){
		var rows = roleGrid.getSelecteds();
		if(rows == null || rows.length != 1){
			nui.alert("请选中一个岗位！");
			return false;
		}
		self.data=rows[0];
		
		CloseWindow("ok");
	}

	function search(){
		var form1Data = form1.getData(false, true);
        roleGrid.load(form1Data);
	}
	search();
</script>