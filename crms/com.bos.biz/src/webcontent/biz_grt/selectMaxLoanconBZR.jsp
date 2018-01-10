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

<div class="nui-toolbar" style="text-align:left;padding-top:2px;padding-bottom:2px;" 
    	borderStyle="border:0;">
    	<a class="nui-button" iconCls="icon-add" id="add" onclick="add">确认</a>
</div>

<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.bizInfo.bizGrt.getMaxLoanCon.biz.ext" dataField="maxLoanCons"
	allowResize="true" showReloadButton="false"allowAlternating="true"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
	<div property="columns">
		<div type="checkcolumn">选择</div>
	    <div field="PARTY_NAME" headerAlign="center" allowSort="true">担保人名称</div>
	    <div field="SUBCONTRACT_TYPE" headerAlign="center" allowSort="true" dictTypeId="XD_YWDB0131">担保方式</div>
		<div field="SUBCONTRACT_NUM" headerAlign="center" allowSort="true" >担保合同编号</div>
		<div field="ZGBJXE" headerAlign="center" allowSort="true" dataType="currency">担保合同金额</div>
		<div field="AVI_AMT" headerAlign="center" allowSort="true" dataType="currency">可用担保金额</div>
	    <div field="BEGIN_DATE" allowSort="true" headerAlign="center">担保额度起期</div>
	    <div field="END_DATE" allowSort="true"   headerAlign="center" >担保额度止期</div>		
		<!-- <div field="GUARANTEE_RIGHT_MONEY" headerAlign="center" allowSort="true" dataType="currency">本次申请担保金额</div> -->
	</div>
</div>
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
    
    grid.on("preload", function(e) {
			if (!e.data || e.data.length < 1) {
				return;
			}
			for (var i = 0; i < e.data.length; i++) {//nui.alert(nui.encode(e.data[i]));
				e.data[i]['SUBCONTRACT_NUM']='<a href="#" onclick="editMaxLoan(\''+ e.data[i].SUBCONTRACT_ID+ '\');">'+e.data[i]['SUBCONTRACT_NUM']+'</a>';
			}
		});
    
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
      function editMaxLoan(SUBCONTRACT_ID) {
        var row = grid.getSelected();
        if (row) {
            nui.open({
                url: nui.context + "/crt/con_grt/con_bzr_tab.jsp?applyId="+applyId+"&subcontractId="+row.SUBCONTRACT_ID+"&view=1",
                title: "查看", 
                width: 800,
        		height: 500,
                allowResize:true,
        		showMaxButton: true,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    var data = row;
                    //iframe.contentWindow.SetData(data);
                },
                ondestroy: function (action) {
                }
            });
        } else {
            alert("请选中一条记录");
        }
        
    }
    
    
</script>
</body>
</html>
