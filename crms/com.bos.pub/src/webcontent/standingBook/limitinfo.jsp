<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 金磊
  - Date: 2014-05-09 10:55:07
  - Description:
-->
<head>
<title>额度使用台账查询</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;height:auto;">
<div title="额度使用台账查询" >
<center>
<div id="form1" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;" >
	<div class="nui-dynpanel" columns="6">
		<label>客户编号：</label>
		<input name="limitinfo.partyNum" required="false" class="nui-textbox nui-form-input"  />
		<label>客户名称：</label>
		<input name="limitinfo.partyName" required="false" class="nui-textbox nui-form-input"  />
		<label>额度编号：</label>
		<input name="limitinfo.limitNum" required="false" class="nui-textbox nui-form-input"  />
		<label>组织机构代码：</label>
		<input name="limitinfo.certificateCode" required="false" class="nui-textbox nui-form-input"  />
	</div>

	<div class="nui-toolbar" style="text-align:right;padding-top:5px;padding-bottom:5px;" borderStyle="border:0;">
    	<a class="nui-button"  iconCls="icon-search" onclick="search()">查询</a>
		<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
	</div>
</div>
	
	<div id="grid1" class="nui-datagrid" style="width:99.65%;height:auto;margin-top:7px" sortMode="client" 
		url="com.bos.pub.standingbook.limitinfo.limitinfolist.biz.ext" dataField="limitinfos"
		allowAlternating="true" multiSelect="false" showEmptyText="true" showPager="true"
	    emptyText="没有查到数据" showReloadButton="false" showColumnsMenu="true"
	    onrowdblclick="" allowCellEdit="true" allowCellSelect="true"onselectionchanged="onSelectionChanged"
	    sizeList="[10,20,50,100]" pageSize="10" >
		<div property="columns">
			<div type="indexcolumn" >序号</div>
			<div field="limitNum" headerAlign="center" allowSort="true" >额度编号</div>
			<div field="partyNum" headerAlign="center" allowSort="true" >客户编号</div>
			<div field="partyName" headerAlign="center" allowSort="true" >客户名称</div>
			<div field="certificateCode" headerAlign="center" allowSort="true" >组织机构代码</div>
			<div field="productType" headerAlign="center" allowSort="true" >额度产品</div>
			<div field="currencyCd" headerAlign="center" allowSort="true" dictTypeId="CD000001" >币别</div>
			<div field="creditAmt" headerAlign="center" allowSort="true" dataType="currency">敞口额度金额</div>
			<div field="aoe" headerAlign="center" allowSort="true" dataType="currency">敞口已用额度</div>
			<div field="aae" headerAlign="center" allowSort="true" dataType="currency">敞口可用额度</div>
			<div field="boe" headerAlign="center" allowSort="true" dataType="currency">分项已用额度</div>
			<div field="bae" headerAlign="center" allowSort="true" dataType="currency">分项可用额度</div>
			<div field="createTime" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">起始日期</div>
			<div field="endDate" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">到期日期</div>
		</div>
	</div>
<!--	<div class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;" borderStyle="border:0;">
    	<a class="nui-button" onclick="clickproduct1">查看额度产品信息</a>
	</div>-->
	
</center>
</div>
</div>	
<script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	//nui.get("editCust").hide();
	
       grid.on("preload",function(e){
   		if (!e.data || e.data.length < 1)
   			return;
   		for (var i=0; i<e.data.length; i++){//nui.alert(nui.encode(e.data[i]));
   			e.data[i]['productType']='<a href="#" onclick="clickproduct(\''
   				+ e.data[i].partyNum+'\',\''+ e.data[i].productType
   				+'\');return false;" value="'
   				+ e.data[i].partyNum+','+ e.data[i].productType
   				+ '">'+git.getProductName(e.data[i]['productType'])+'</a>';
   		}
   });
    	
	function clickproduct(partyNum,productType){
		var url = nui.context + "/pub/standingBook/limitproductinfo.jsp?partyNum=" + partyNum
		+"&productType=" + productType ;
	    git.go(url);
	}
	
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