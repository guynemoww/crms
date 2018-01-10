<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): chenchuan
  - Date: 2016-5-5 13:16:44
  - Description:
-->
<head>
<title>本行融资情况-批复</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="form1" style="width:99.5%;height:auto;overflow:hidden;">
	<input name="sqlName" value="com.bos.csm.myBank.myBank.financingList" class="nui-hidden" />
	<input name="item.partyId" value="<%=request.getParameter("partyId") %>" class="nui-hidden" />
</div>
<div id="datagrid1" class="nui-datagrid" style="width:99.5%;height:auto;"  sortMode="client"
	    url="com.bos.csm.pub.ibatis.getItem.biz.ext" dataField="items"
	    allowAlternating="true" multiSelect="false" showReloadButton="false"
	    sizeList="[10,20,50,100]"  pageSize="10">
	    <div property="columns">
	        <div type="indexcolumn">序号</div>
	        <div field="approvalNum" allowSort="true" >批复编号</div>                
	        <div field="bizType" dictTypeId="XD_SXYW0002" allowSort="true" >业务性质</div> 
	        <div field="creditAmt" allowSort="true" dataType="currency" >批复金额</div> 
	        <div field="availableAmt" allowSort="true" dataType="currency" >批复已用金额</div> 
	        <div field="startDate" allowSort="true" dateFormat="yyyy-MM-dd" >批复起期</div> 
	        <div field="endDate" allowSort="true" dateFormat="yyyy-MM-dd" >批复止期</div> 
	        <div field="orgNum" allowSort="true" dictTypeId="org" >经办机构</div>
	        <div field="userNum" allowSort="true" dictTypeId="user" >经办人</div> 
	     </div>
	</div>
<!-- 		<div class="nui-toolbar" style="text-align:right;border:none" > -->
<!-- 		    <label>批复金额合计：</label> -->
<!-- 			<input id="creditAmtSum"style="width:200px" enabled="false"dataType="currency" class="nui-textbox nui-form-input"  />	 -->
<!-- 		    <label>批复已用金额合计：</label> -->
<!-- 			<input id="availableAmtSum"style="width:200px" enabled="false"dataType="currency" class="nui-textbox nui-form-input"  />	 -->
<!-- 		</div> -->
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form1"); 
	var grid = nui.get("datagrid1");
	var partyId = "<%=request.getParameter("partyId") %>";
	
	function init() {
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
        
//         //合计计算
//         var json = nui.encode({"item":{"partyId" : partyId},"sqlName":"com.bos.csm.myBank.myBank.financingListSum"});
// 			$.ajax({
// 				url : "com.bos.csm.pub.ibatis.getItemDataObject.biz.ext",
// 				type : 'POST',
// 				data : json,
// 				cache : false,
// 				contentType : 'text/json',
// 				success : function(text) {
// 					nui.get("creditAmtSum").setValue(text.items[0].CREDIT_AMT_SUM);
// 					nui.get("availableAmtSum").setValue(text.items[0].AVAILABLE_AMT_SUM);
// 				}
// 			});
    }
    init();
    
    
    grid.on("preload", function(e) {
			if (!e.data || e.data.length < 1) {
				return;
			}
			for (var i = 0; i < e.data.length; i++) {//nui.alert(nui.encode(e.data[i]));
				e.data[i]['approvalNum']='<a href="#" onclick="bizView3231(\''+ e.data[i].approvalNum+ '\');">'+e.data[i]['approvalNum']+'</a>';
			}
		});
</script>
	
	

</body>
</html>