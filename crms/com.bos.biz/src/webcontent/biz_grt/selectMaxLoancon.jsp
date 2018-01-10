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

<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.bizInfo.bizGrt.getMaxLoanCon.biz.ext" dataField="maxLoanCons"
	allowResize="true" showReloadButton="false"allowAlternating="true"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
	<div property="columns">
		<div type="checkcolumn">选择</div>
	    <div field="PARTY_NAME" headerAlign="center" allowSort="true">担保人名称</div>
	    <div field="CERT_TYPE" headerAlign="center" allowSort="true" dictTypeId="CDKH0002">证件类型</div>
	    <div field="CERT_NUM" headerAlign="center" allowSort="true">证件号码</div>
	    <div field="SUBCONTRACT_TYPE" headerAlign="center" allowSort="true" dictTypeId="XD_YWDB0131">担保方式</div>
		<div field="SUBCONTRACT_NUM" headerAlign="center" allowSort="true" >担保合同编号</div>
		<div field="ZGBJXE" headerAlign="center" allowSort="true" dataType="currency">担保合同金额</div>
		<div field="AVI_AMT" headerAlign="center" allowSort="true" dataType="currency">可用担保金额</div>
		<!-- <div field="GUARANTEE_RIGHT_MONEY" headerAlign="center" allowSort="true" dataType="currency">本次申请担保金额</div> -->
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
	var applyId = "<%=request.getParameter("applyId") %>";
	var guarantyType = "<%=request.getParameter("guarantyType") %>";
	search();
    function search() {
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
        	 var json=nui.encode({"item":{"applyId":applyId,"subcontractId":row.SUBCONTRACT_ID,"reType":"<%=request.getParameter("guarantyType") %>"}});
        	 $.ajax({
                    url: "com.bos.bizInfo.bizGrt.addMaxloancon.biz.ext",
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
        }else{
        	nui.alert("请选择一条最高额担保合同后在进行此操作");
        	return;
        }
        git.unmask();
    }
</script>
</body>
</html>
