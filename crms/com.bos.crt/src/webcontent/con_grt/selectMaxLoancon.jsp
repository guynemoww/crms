<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<!-- 

-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input type="hidden" id="map.guarantyType" name="map.guarantyType" value="<%=request.getParameter("guarantyType") %>" class="nui-hidden" />
	<input type="hidden" id="map.partyId" name="map.partyId" value="<%=request.getParameter("partyId") %>" class="nui-hidden" />
	<input type="hidden" id="map.applyId" name="map.applyId" value="<%=request.getParameter("applyId") %>" class="nui-hidden" />
	<input type="hidden" id="map.contractId" name="map.contractId" value="<%=request.getParameter("contractId") %>" class="nui-hidden" />

<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.bizInfo.bizGrt.getMaxLoanCon.biz.ext" dataField="maxLoanCons"
	allowResize="true" showReloadButton="false"allowAlternating="true"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
	<div property="columns">
		<div type="checkcolumn">选择</div>
	    <div type="indexcolumn">序号</div>
	    <div field="PARTY_NAME" headerAlign="center" allowSort="true">担保人名称</div>
		<div field="SUBCONTRACT_NUM" headerAlign="center" allowSort="true" >担保合同编号</div>
		<div field="PAPER_CON_NUM" headerAlign="center" allowSort="true">担保合同纸质编号</div>
		<div field="BZ" headerAlign="center" allowSort="true" dictTypeId="CD000001" >币种</div>
		<div field="ZGBJXE" headerAlign="center" allowSort="true" dataType="currency">担保合同金额</div>
		<div field="AVI_AMT" headerAlign="center" allowSort="true" dataType="currency">可用担保金额</div>
		<div field="END_DATE" headerAlign="center"  allowSort="true" >担保合同止期</div>
	</div>
</div>
<div class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;" 
    	borderStyle="border:0;">
    	<a class="nui-button" iconCls="icon-add" id="add" onclick="add">确认</a>
    	<a class="nui-button" iconCls="icon-close" id="close" onclick="CloseWindow('ok')">关闭</a>
</div>
		
<script type="text/javascript">
 	nui.parse();
 	
 	var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	var contractId = "<%=request.getParameter("contractId") %>";
	var guarantyType = "<%=request.getParameter("guarantyType") %>";
	var partyId = "<%=request.getParameter("partyId") %>";
	var applyId = "<%=request.getParameter("applyId") %>";
	var contractType = "<%=request.getParameter("contractType") %>";
	search();
    function search() {
<%--        var partyName = nui.get("map.partyName").getValue().trim();
        var subcontractNum = nui.get("map.subcontractNum").getValue().trim();
        var subcontractManualNum = nui.get("map.subcontractManualNum").getValue().trim();
        if(partyName=="" && subcontractNum=="" && subcontractManualNum==""){
           nui.alert("请至少填写一个查询条件!");
           return;
        }--%>
        git.mask();
    	var data = form.getData(false, true); //获取表单多个控件的数据
        grid.load(data);
        git.unmask();
    }
    
    function reset() {
		nui.get("partyName").setValue("");
		nui.get("subcontractNum").setValue("");
		nui.get("subcontractManualNum").setValue("");
	}
    
    function add() {
        var row = grid.getSelected();
        if(row){
        	 if("02" == contractType){
 		    	 nui.open({
			         url: nui.context + "/crt/con_grt/saveConSubRel.jsp?subcontractId="+row.SUBCONTRACT_ID,
			         title: "输入本次担保金额",
			         width: 400,
			    	 height: 200,
			         ondestroy: function (action){
			               if (action == "ok") {
				                 var iframe = this.getIFrameEl();
				                 var data = iframe.contentWindow.GetData();
				                 data = nui.clone(data);
				                 if (data) {
									//将担保物关联到抵质押合同
						        	var json=nui.encode({"item":{"contractId":contractId,"subcontractId":row.SUBCONTRACT_ID,"suretyAmt":data.suretyAmt,"reType":"<%=request.getParameter("guarantyType") %>"}});
						        	$.ajax({
					                   url: "com.bos.grt.conGrt.addMaxloancon.biz.ext",
						               type: 'POST',
						               data: json,
						               cache: false,
						               contentType:'text/json',
					                   success: function (text) {
					                    	if (text.msg) {
					                    		nui.alert(text.msg);
					                    		return;
					                    	}else{
					                    		nui.alert("引入成功");
					                    		CloseWindow("ok");
					                    	}
					                    },
					                    error: function () {
					                    	nui.alert("操作失败！");
					                    }
						            });
				             	 }
				           }
			         }
			     });
        	 }else{
	        	 var json=nui.encode({"item":{"contractId":contractId,"subcontractId":row.SUBCONTRACT_ID,"reType":"<%=request.getParameter("guarantyType") %>"}});
	        	 $.ajax({
	                    url: "com.bos.grt.conGrt.addMaxloancon.biz.ext",
		                type: 'POST',
		                data: json,
		                cache: false,
		                contentType:'text/json',
	                    success: function (text) {
	                    	if (text.msg) {
	                    		nui.alert(text.msg);
	                    		return;
	                    	}else{
	                    		nui.alert("引入成功");
	                    		CloseWindow("ok");
	                    	}
	                    },
	                    error: function () {
	                    	nui.alert("操作失败！");
	                    }
	                });
             }  
        }else{
        	nui.alert("请选择一条最高额担保合同后在进行此操作");
        	return;
        }
    }
        

</script>
</body>
</html>
