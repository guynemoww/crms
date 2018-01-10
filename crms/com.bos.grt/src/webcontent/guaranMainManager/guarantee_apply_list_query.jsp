<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<%@include file="/common/nui/common.jsp" %>
<title>保证人选择</title>
</head>
<body>
	<div id="form1" style="width:80%;height:auto;overflow:hidden;" >
	<input name="item.partyTypeCd" id="item.partyTypeCd" class="nui-hidden" />
		<div class="nui-dynpanel" columns="2">
			<label>客户名称：</label>
			<input id=item.partyName" name="item.partyName" class="nui-textbox nui-form-input"/>
			
			<label>证件类型：</label>
			<input id="item.partyNum" name="item.partyNum" class="nui-textbox nui-form-input"/>
			
			<label>证件号码：</label>
			<input id="item.ecifPartyNum" name="item.ecifPartyNum" class="nui-textbox nui-form-input" />
		</div>
	</div>

	</div style="width:99.5%">
		<div class="nui-toolbar" style="text-align:right;border:none" >
		    <a class="nui-button" style="margin-right:5px;height:21px;" iconCls="icon-search" onclick="query()">查询</a>
			<a class="nui-button" style="margin-right:23px;height:21px" iconCls="icon-reset" onclick="reset()">重置</a>
		</div>
	<div>

<%--<div id="cxresult">查询结果：</div>--%>

	<div id="datagrid1" class="nui-datagrid" style="width:100%;height:auto;"  sortMode="client"
		    url=".biz.ext" dataField="items"
		    allowAlternating="true" multiSelect="false"  showReloadButton="false"
		    sizeList="[10,20,50,100]" pageSize="10">
		    <div property="columns">
		        <div type="checkcolumn">选择</div>
		        <div field="partyNum" allowSort="true" width="20%" headerAlign="center" >客户名称</div> 
		        <div field="ecifPartyNum" allowSort="true" width="20%" headerAlign="center" >证件类型</div>                
		        <div field="partyName" allowSort="true" width="" headerAlign="center" >证件号码</div> 
		        <div field="certType" allowSort="true" width="" headerAlign="center" >保证限额</div> 
		        <div field="certCode" allowSort="true" width="" headerAlign="center" >已用保证限额（元）</div> 
		        <div field="partyTypeCd" allowSort="true" >币种</div>
		     </div>
	</div>
<div style="width:99.5%">
	<div id="dataConfirm"  class="nui-toolbar" style="border:0;text-align:right;"> 
    <a class="nui-button"  onclick="selected()">选中</a>
	</div>
</div>

<script type="text/javascript">
	nui.parse();
	
	
	
	
	
	
	function selected() {
      var row = grid.getSelected();
        if (row) {
           CloseWindow("ok");
        } else {
            alert("请选中一条记录");
        }
    } 
</script>
</body>
</html>
