<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): Administrator
  - Date: 2015-07-15 22:38:00
  - Description:
-->
<head>
<title>查询小贷客户</title>
</head>
<body>
<div id="form1" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
		<input id="item.thirdCustTypeCd" name="item.thirdCustTypeCd" class="nui-hidden">
		<input id="item.partyId" name="item.partyId" class="nui-hidden">
		<div class="nui-dynpanel" columns="6">
			<label>客户名称：</label>
			<input id="item.partyName" name="item.partyName"  class="nui-textbox nui-form-input"  />
	
			<label>证件类型：</label>
			<input id="item.certType" name="item.certType"  class="nui-dictcombobox nui-form-input"  textField="dictname" valueField="dictid" dictTypeId="CDKH0002"  allowInput="false" />
			
			<label>证件号码：</label>
			<input id="item.certNum" name="item.certNum"  class="nui-textbox nui-form-input"/>
			
		</div>
		<div class="nui-toolbar" style="text-align:right;padding-right:20px;border:0;">
		    <a class="nui-button"  iconCls="icon-search" onclick="query">查询</a>
		   <%-- <a class="nui-button" onclick="exportCust">导出</a>--%>
		    <a class="nui-button" onclick="reset">重置</a>
		</div>
</div>
<div id="datagrid1" class="nui-datagrid" style="width:99.6%;height:auto;margin-top:10px;"  sortMode="client"
	    url="com.bos.csm.natural.loan.queryPrivateCustForBusi.biz.ext" dataField="items"
	    allowAlternating="true" multiSelect="false" showEmptyText="true"
	    emptyText="没有查到数据" showReloadButton="false"
	    sizeList="[10,20,50,100]" pageSize="10">
	    <div property="columns">
	     	<div type="indexcolumn">序号</div> 
	     	<div field="partyNum" headerAlign="center" allowSort="true">客户编号</div>    
			<div field="partyName" headerAlign="center" allowSort="true">客户名称</div>
			<div field="certType" headerAlign="center" allowSort="true" dictTypeId="CDKH0002" >证件类型</div>
			<div field="certNum" headerAlign="center" allowSort="true">证件号码</div>
	     </div>
	</div>
	 <div class="nui-toolbar" style="text-align:center;padding-top:8px;padding-bottom:0px;" 
        borderStyle="border-left:0;border-bottom:0;border-right:0;">
        <a class="nui-button" iconCls="icon-ok" onclick="onOk()">确定</a>
        <span style="display:inline-block;width:25px;"></span>
        <a class="nui-button" iconCls="icon-cancel" onclick="onCancel()">取消</a>
</div>
</body>
<script type="text/javascript">
	nui.parse();
	
	var form = new nui.Form("#form1");
	var grid = nui.get("datagrid1");
	nui.get("item.partyId").setValue('<%=request.getParameter("partyId")%>');
	
	//初始化自然人证件类型
	function init(){
 		git.mask();
	    var json = nui.encode({parentId:"10000"});
	     $.ajax({
	        url: "com.bos.csm.pub.getDict.getCertTypeDict.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
	        contentType:'text/json',
	        success: function (text) {
	            git.unmask();
	            nui.get("item.certType").setData(text.levels);
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            git.unmask();
	            nui.alert(jqXHR.responseText);
	        }
	     });
	}
	init();	
	function getData(){
    	var row = grid.getSelected();
      	if (row) {
            return row;
        } else {
            return null;
        }
    }
	function query(){
       var o = form.getData(false, true);//逻辑流必须返回total
       grid.load(o);
	}
	query();
	 function onOk() {
        CloseWindow("ok");
    }
    
    function onCancel() {
        CloseWindow("cancel");
    }
	
</script>	
</html>