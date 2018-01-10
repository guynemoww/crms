<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 李伟
  - Date: 2013-11-11 13:26:27
  - Description:
-->
<head>
<title>接口日志查询</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;height:115%;">
<div title="接口日志" >
<center>
<div id="form1" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
	<div class="nui-dynpanel" columns="6">
		<label>请求流水号：</label>
		<input name="item.rquid" class="nui-textbox nui-form-input"/>

		<label>创建时间：</label>
		<input name="item.createtime" required="false" class="nui-datepicker" format="yyyy-MM-dd"/>

		<label>更新时间：</label>
		<input name="item.upatetime" required="false" class="nui-datepicker" format="yyyy-MM-dd" />		
	</div>
	<div class="nui-toolbar" style="text-align:right;border:0;padding-right:20px;"> 
	    <a class="nui-button"  iconCls="icon-search" onclick="queryInit()">查询</a>
	    <a class="nui-button" onclick="reset()" iconCls="icon-reset">重置</a>
	</div>
</div>

<div style="width:99.5%">
	<div class="nui-toolbar" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px;">
	    <a id = "query" class="nui-button" iconCls="icon-node" onclick="query()">查看</a> 
	</div>
</div>

<div id="datagrid1" class="nui-datagrid" style="width:99.5%;height:auto" 
	url="com.bos.comm.biz.CrudItem.queryTbPubTradeInfo.biz.ext" dataField="items"
	allowResize="true" showReloadButton="false"  <%--onselectionchanged="onSelectionChanged" 撤销管户权控制--%>
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
	    <div property="columns">
	        <div type="checkcolumn">选择</div>               
	        <div field="rquid" allowSort="true" width="20%" headerAlign="center" >请求流水号</div> 
	        <div field="spname" allowSort="true" width="20%" headerAlign="center" >服务名称</div> 
	        <div field="direction" allowSort="true" width="" headerAlign="center" autoEscape="false">交易方向</div>
	        <div field="createtime" allowSort="true" width="20%" headerAlign="center">创建时间</div>       
	        <div field="updatetime" allowSort="true" width="20%" headerAlign="center">更新时间</div>
	     </div>
</div>

</center>
</div>
</div>
<script type="text/javascript">
	nui.parse();
	git.mask();	
	var form = new nui.Form("#form1");
	var grid = nui.get("datagrid1");	
	


	
	function queryInit(){
       var o = form.getData();//逻辑流必须返回total
       grid.load(o);
	   git.unmask();
	}
	queryInit();	
	
	function reset(){
		form.reset();
		queryInit();
		}

	
	function query(){
		var row=grid.getSelected();
		if (null == row) {
			nui.alert("请选择一条记录");
			return;
		}
		var url=nui.context + "/comm/pub/interface_bw.jsp?tradId=" + row.tradId;		           			
		//查看
			nui.open({
	            url: url,
	            showMaxButton: true,
	            title: "查看客户信息",
	            width: 1024,
	            height: 768,
	            state:"max",
	            onload: function(e){
	            	var iframe = this.getIFrameEl();
	            	var text = iframe.contentWindow.document.body.innerText;
	            	//alert(text);
	            },
	            ondestroy: function (action) {
	                //queryInit();
	            }
      	 	 });
	}
	

	

</script>
</body>
</html>