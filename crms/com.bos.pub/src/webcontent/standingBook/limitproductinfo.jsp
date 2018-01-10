<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 金磊
  - Date: 2014-05-09 10:55:07
  - Description:
-->
<head>
<title>额度产品信息</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<div class="nui-dynpanel" columns="4">
		<label>合同号：</label>
		<input name="query.contractNum" required="false" class="nui-textbox nui-form-input"  />
	</div>

	<div class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;" borderStyle="border:0;">
    	<a class="nui-button"  iconCls="icon-search" onclick="search()">查询</a>
		<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
	</div>
	<strong >额度产品信息</strong >
	<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
		url="com.bos.pub.standingbook.limitinfo.limitproductinfolist.biz.ext"
		dataField="queryLimitProductInfoLists"
		allowResize="true" showReloadButton="false" onselectionchanged="queryContract"
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
		<div property="columns">
			<div type="indexcolumn" >序号</div>
			<div field="partyName" headerAlign="center" allowSort="true" >客户</div>
			<div field="contractNum" headerAlign="center" allowSort="true" >合同号</div>
			<div field="productType" headerAlign="center" allowSort="true" dictTypeId="product" >产品</div>
			<div field="currencyCd" headerAlign="center" allowSort="true" dictTypeId="CD000001" >币种</div>
			<div field="contractTotalAmt" headerAlign="center" allowSort="true" >合同总金额(元)</div>
			<div field="availableAmt" headerAlign="center" allowSort="true" >可用金额(元)</div>
			<div field="orgNum" headerAlign="center" allowSort="true" >网点</div>
			<div field="contractSignDate" headerAlign="center" allowSort="true" >日期</div>
		</div>
	</div>
		<div class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;" borderStyle="border:0;">
    	<a class="nui-button" onclick="back()">返回</a>
	</div>
</div> 	
    <script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	
	//初始化页面
    function search() {
		//var data = form.getData(); //获取表单多个控件的数据
        //grid.load(data);
        var partyNum = "<%=request.getParameter("partyNum") %>";
        var productType = "<%=request.getParameter("productType") %>";
		var json = {"query/partyNum":partyNum,"query/productType":productType};		
        grid.load(json);
    }
    search();
    
    function reset(){
		form.reset();
		search();
	}
	
	function back(){
		var url = nui.context + "/pub/standingBook/limitinfo.jsp";
	    git.go(url);
	}	

	</script>
</body>
</html>