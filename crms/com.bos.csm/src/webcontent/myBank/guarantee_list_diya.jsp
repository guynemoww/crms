<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 陈川
  - Date: 2016-5-5 13:16:44
  - Description:
-->
<head>
<title>为我行客户担保情况</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="form1" style="width:99.5%;height:auto;overflow:hidden;">
	<input name="sqlName" value="com.bos.csm.myBank.myBank.guaranteeListDY" class="nui-hidden" />
	<input name="item.partyId" value="<%=request.getParameter("partyId") %>" class="nui-hidden" />
</div>
<div id="datagrid1" class="nui-datagrid" style="width:99.5%;height:auto;"  sortMode="client"
	    url="com.bos.csm.pub.ibatis.getItem.biz.ext" dataField="items"
	    allowAlternating="true" multiSelect="false" showReloadButton="false"
	    sizeList="[10,20,50,100]"  pageSize="20">
	    <div property="columns">
	        <div type="indexcolumn">序号</div>
	        <div field="partyName" headerAlign="center" allowSort="true" >借款人名称</div>                
	        <div field="productType" headerAlign="center" allowSort="true" dictTypeId="product">业务品种</div> 
	        <div field="contractNum"  headerAlign="center"allowSort="true" >借款合同编号</div> 
	        <div field="subcontractNum"  headerAlign="center"allowSort="true" >抵/质押合同编号</div> 
	        <div field="collType"  headerAlign="center"allowSort="true" dictTypeId="XD_YWDB0131">抵/质押类型</div> 
	        <div field="sortType"  headerAlign="center"allowSort="true" dictTypeId="XD_YWDB02">抵/质押物类型</div> 
	       	<div field="currencyCd" headerAlign="center" allowSort="true" dataType="CD000001">币种</div> 
	        <div field="conYuE"  headerAlign="center"allowSort="true" dataType="currency">借款余额</div>	
	        <div field="subcontractAmt"  headerAlign="center"allowSort="true" dataType="currency">担保金额</div> 
	        <div field="beginDate"  headerAlign="center"allowSort="true" >担保合同起期</div> 
	        <div field="endDate"  headerAlign="center"allowSort="true" >担保合同止期</div> 
	        <div field="status" headerAlign="center" allowSort="true" >抵/质押物出入库状态</div> 
<!-- 	       	<div field="subcontractSum" allowSort="true" dataType="currency">抵/质押担保合计</div>  -->
	     </div>
	</div>
	
		<div class="nui-toolbar" style="text-align:right;border:none" >
		    <label>抵/质押担保合计：</label>
			<input id="grtSum"style="width:200px" enabled="false"dataType="currency" class="nui-textbox nui-form-input"  />	
		</div>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form1"); 
	var grid = nui.get("datagrid1");
	var partyId = "<%=request.getParameter("partyId") %>";
	
	function init() {
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
         var json = nui.encode({"item":{"partyId" : partyId},"sqlName":"com.bos.csm.myBank.myBank.myBankGrtSum"});
			$.ajax({
				url : "com.bos.csm.pub.ibatis.getItemDataObject.biz.ext",
				type : 'POST',
				data : json,
				cache : false,
				contentType : 'text/json',
				success : function(text) {
					nui.get("grtSum").setValue(text.items[0].SUM);
				}
			});
    }
    init();
    
     grid.on("preload", function(e) {
     		var count = 0;
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