<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 甘泉
  - Date: 2015-5-5 13:16:44
  - Description:
-->
<head>
<title>被我行客户担保信息</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<center>
<div id="form1" style="width:99.5%;height:auto;overflow:hidden;">
	<input name="sqlName" value="com.bos.csm.myBank.myBank.byGuaranteeListDY" class="nui-hidden" />
	<input name="item.partyId" value="<%=request.getParameter("partyId") %>" class="nui-hidden" />
</div>
<div id="datagrid1" class="nui-datagrid" style="width:99.5%;height:auto;"  sortMode="client"
	    url="com.bos.csm.pub.ibatis.getItem.biz.ext" dataField="items"
	    allowAlternating="true" multiSelect="false" showReloadButton="false"
	    sizeList="[10,20,50,100]"  pageSize="20">
	    <div property="columns">
	        <div type="indexcolumn">序号</div>
	        <!-- 
	        <div field=guarantyType allowSort="true" dictTypeId="CDZC0005">担保方式</div>   
	         -->             
	        <div field="productType" allowSort="true" dictTypeId="product">业务品种</div> 
	        <div field="contractNum" allowSort="true" >借款合同编号</div> 
	        <div field="subcontractNum" allowSort="true" >抵/质押合同编号</div> 
	        <div field="collType" allowSort="true" dictTypeId="XD_YWDB0131" >抵/质押类型</div> 
	        <div field="partyName" allowSort="true" >抵/质押人名称</div> 
	        <div field="sortType" allowSort="true" dictTypeId="XD_YWDB02" >抵/质押物类型</div> 
	        <div field="subcontractAmt" allowSort="true"	dataType="currency" >抵/质押担保金额</div> 
	        <div field="beginDate" allowSort="true" >抵/质押合同起期</div> 
	       	<div field="endDate" allowSort="true" >抵/质押合同止期</div> 
	        <div field="status" allowSort="true" dictTypeId="" >抵/质押物出入库状态</div> 
	        <div field="orgNum" allowSort="true" dictTypeId="org" >经办机构</div> 
	        <div field="userNum" allowSort="true" dictTypeId="user" >经办人</div> 
	     </div>
	</div>
</center>

<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form1"); 
	var grid = nui.get("datagrid1");
	var partyId = "<%=request.getParameter("partyId") %>";
	
	function init() {
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
    }
    init();
    
     grid.on("preload", function(e) {
			if (!e.data || e.data.length < 1) {
				return;
			}
			for (var i = 0; i < e.data.length; i++) {//nui.alert(nui.encode(e.data[i]));
				e.data[i]['contractNum']='<a href="#" onclick="bizView3231(\''+ e.data[i].contractNum+ '\');">'+e.data[i]['contractNum']+'</a>';
				e.data[i]['partyName']='<a href="#" onclick="toGoCustDetail(\''+ e.data[i].partyId+ '\');">'+e.data[i]['partyName']+'</a>';
				e.data[i]['subcontractNum']='<a href="#" onclick="bizView3231(\''+ e.data[i].subcontractNum+ '\');">'+e.data[i]['subcontractNum']+'</a>';
			}
		});
</script>
	
	

</body>
</html>