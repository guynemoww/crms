<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 陈川
  - Date: 2016-5-5 13:16:44
  - Description:
-->
<head>
<title>本行融资情况-业务信息</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="form1" style="width:99.5%;height:auto;overflow:hidden;">
	<input name="sqlName" value="com.bos.csm.myBank.myBank.financingListYW" class="nui-hidden" />
	<input name="item.partyId" value="<%=request.getParameter("partyId") %>" class="nui-hidden" />
</div>
<div id="datagrid1" class="nui-datagrid" style="width:99.5%;height:auto;"  sortMode="client"
	    url="com.bos.csm.pub.ibatis.getItem.biz.ext" dataField="items"
	    allowAlternating="true" multiSelect="false" showReloadButton="false"
	    sizeList="[10,20,50,100]"  pageSize="10">
	    <div property="columns">
	        <div type="indexcolumn">序号</div>
	        <div field="contractNum" allowSort="true" >合同编号</div>      
	        <div field="productType" dictTypeId="product" allowSort="true" >业务品种</div>
	       	<div field="currencyCd" allowSort="true" dictTypeId="CD000001" >币种</div> 
	        <div field="contractAmt" allowSort="true" dataType="currency">合同金额</div> 
	       	<div field="conYuE" allowSort="true" dataType="currency">合同已用金额</div> 
	        <div field="beginDate" allowSort="true" dateFormat="yyyy-MM-dd" >合同起期</div> 
	        <div field="endDate" allowSort="true" dateFormat="yyyy-MM-dd" >合同止期</div> 
	       	<div field="bzjje"name="bzjje" allowSort="true" dataType="currency">保证金余额</div> 
	       	<div field="yqts" allowSort="true" >逾期天数</div> 
	        <div field="jjyqbj" allowSort="true" dataType="currency">逾期本金</div> 
	       	<div field="arrearItr" allowSort="true" dataType="currency">逾期利息</div> 
	        <div field="clsResult" allowSort="true" dictTypeId="XD_FLCD0001" >分类</div> 
	        <div field="orgNum" allowSort="true" dictTypeId="org" >经办机构</div> 
	        <div field="userNum" allowSort="true" dictTypeId="user" >经办人</div> 
	     </div>
	</div>
<!-- 		<div class="nui-toolbar" style="text-align:right;border:none" > -->
<!-- 		    <label>合同金额合计：</label> -->
<!-- 			<input id="contractAmtSum"style="width:200px" enabled="false"dataType="currency" class="nui-textbox nui-form-input"  />	 -->
<!-- 		    <label>合同已用金额合计：</label> -->
<!-- 			<input id="conYuESum"style="width:200px" enabled="false"dataType="currency" class="nui-textbox nui-form-input"  />	 -->
<!-- 		    <label id="bzjjeSumlab">保证金余额合计：</label> -->
<!-- 			<input id="bzjjeSum"style="width:200px" enabled="false"dataType="currency" class="nui-textbox nui-form-input"  />	 -->
<!-- 		</div> -->
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form1"); 
	var grid = nui.get("datagrid1");
	var partyId = "<%=request.getParameter("partyId") %>";
	var partyTypeCd = "<%=request.getParameter("partyTypeCd") %>";
	
	function init() {
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
        if(partyTypeCd=="01"){//公司客户
        	grid.showColumn(grid.getColumn("bzjje"));
        }else{//自然人客户没有保证金余额和合计
            grid.hideColumn(grid.getColumn("bzjje"));
            //$("#bzjjeSumlab").css("display","none");
            //nui.get("bzjjeSum").hide();
        }
//           //合计计算
//         var json = nui.encode({"item":{"partyId" : partyId},"sqlName":"com.bos.csm.myBank.myBank.financingListYWSum"});
// 			$.ajax({
// 				url : "com.bos.csm.pub.ibatis.getItemDataObject.biz.ext",
// 				type : 'POST',
// 				data : json,
// 				cache : false,
// 				contentType : 'text/json',
// 				success : function(text) {
// 					nui.get("contractAmtSum").setValue(text.items[0].CONTRACT_AMT_SUM);
// 					nui.get("conYuESum").setValue(text.items[0].CON_YU_E_SUM);
// 					nui.get("bzjjeSum").setValue(text.items[0].BZJJE_SUM);
// 				}
// 			});
    }
    init();
    
    grid.on("preload", function(e) {
			if (!e.data || e.data.length < 1) {
				return;
			}
			for (var i = 0; i < e.data.length; i++) {//nui.alert(nui.encode(e.data[i]));
				e.data[i]['contractNum']='<a href="#" onclick="bizView3231(\''+ e.data[i].contractNum+ '\');">'+e.data[i]['contractNum']+'</a>';
			}
		});
</script>

</body>
</html>