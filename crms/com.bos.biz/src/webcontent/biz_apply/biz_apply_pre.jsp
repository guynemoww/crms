<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s):
  - Date: 2016-03-14 
  - Description: 押品维护进入主页面
-->
<head>
<h:css href="/css/style1/style-custom.css"/>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>

	<div id="form1" style="width:100%;height:auto;overflow:hidden;">
		<input name="partyId" id="partyId" class="nui-hidden" value="<%=request.getParameter("partyId")%>"/>
		<input name="bussinessType" id="bussinessType" class="nui-hidden" value="biz"/>
	</div>
	
	<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	   url="com.bos.bizApply.bizApply.queryBizPre.biz.ext" dataField="busApply"
	   allowResize="true" showReloadButton="false" allowAlternating="true"
	   sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
	   <div property="columns">
		  <div type="checkcolumn">选择</div>
		  <div field="USER_NUM" headerAlign="center" align="center" dictTypeId="user">客户经理</div>
		  <div field="ORG_NUM" headerAlign="center" align="center" dictTypeId="org">机构</div>
		  <div field="PARTY_NAME" headerAlign="center" align="center">客户名称</div>
		  <div field="APPLY_NUM" headerAlign="center" align="center">业务申请编号</div>
		  <div field="BIZ_TYPE" headerAlign="center" align="center" dictTypeId="XD_SXYW0002">业务性质</div>
		  <div field="CREATE_TIME" headerAlign="center" align="center" >创建时间</div>
	   </div>
	 </div>
	<div class="nui-toolbar" style="text-align:right;padding-top:5px;padding-bottom:5px;" borderStyle="border:0;">
      	<a class="nui-button" iconCls="icon-ok" onclick="add()">确定</a>
    </div>
  <script>
		nui.parse();
		var grid = nui.get("grid1");
		var form = new nui.Form("#form1");
		
		search();//查询
		
		function search() {
    	    git.mask();
        	var data = form.getData(); //获取表单多个控件的数据
        	grid.load(data);
        	git.unmask();
    	}
    	
    	function add(){
    		var row = grid.getSelected();
    		if(row){
	    		CloseWindow("ok");
    		}else{
    			alert("请选择一条记录再执行新增预申请操作!");
    		}
    	}
    	
    	function getData(){
    		return grid.getSelected();
    	}
    </script>
</body>
</html>
