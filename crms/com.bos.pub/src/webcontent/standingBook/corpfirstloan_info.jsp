<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 金磊
  - Date: 2014-05-09 10:55:07
  - Description:
-->
<head>
<title>对公客户首次用信查询</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;height:auto;">
<div title="对公客户首次用信查询" >
<center>
<div id="form1" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;" >
	<div class="nui-dynpanel" columns="6">
		<label>客户名称：</label>
		<input name="item.partyName" required="false" class="nui-textbox nui-form-input"  />
		<label>组织机构代码：</label>
		<input name="item.orgnNum" required="false" class="nui-textbox nui-form-input"  />
	</div>

	<div class="nui-toolbar" style="text-align:right;padding-top:5px;padding-bottom:5px;" borderStyle="border:0;">
    	<a class="nui-button"  iconCls="icon-search" onclick="search()">查询</a>
		<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
	</div>
</div>

	<div id="grid1" class="nui-datagrid" style="width:99.65%;height:auto;margin-top:7px"
		url="com.bos.pub.standingbook.corpfirstloaninfo.querycorpfirstloaninfo.biz.ext"
		dataField="items"
		allowResize="true" showReloadButton="false" allowAlternating="true"
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
		<div property="columns">
			<div type="indexcolumn" >序号</div>
			<div field="partyName" headerAlign="center" allowSort="true" >客户名称</div>
			<div field="orgnNum" headerAlign="center" allowSort="true" >客户组织机构代码</div>
			<div field="productType" headerAlign="center" allowSort="true" dictTypeId="product" >产品</div>
			<div field="contractSignDate" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd" >首次用信时间</div>
		</div>
	</div>
</center>
</div>
</div>	
    <script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	
	//初始化页面
    function search() {
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
        //var duebillserialno = "<%=request.getParameter("duebillserialno") %>";
		//var json = {"tbBatchWastebook/duebillserialno":duebillserialno};		
        //grid.load(json);
    }
    search();
    
    function reset(){
		form.reset();
		search();
	}
	

	</script>
</body>
</html>