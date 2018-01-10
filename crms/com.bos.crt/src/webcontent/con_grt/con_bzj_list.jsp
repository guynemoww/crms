<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-07-05 16:16:18
  - Description:
-->
<head>
<title>保证金合同</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form" style="width:99.5%;height:auto;overflow:hidden; text-align:center;"  class="nui-form">
	<div class="nui-toolbar" style="border-bottom:0;text-align:left;">
		<a class="nui-button" iconCls="icon-add" onclick="add()" id="addBtn">增加</a>
		<a class="nui-button" iconCls="icon-zoomin" onclick="edit(1)">查看</a>
		<a class="nui-button" iconCls="icon-edit"  onclick="edit(0)" id="editBtn">编辑</a>
		<a class="nui-button" iconCls="icon-remove" onclick="remove()" id="deleteBtn">删除</a>
	</div>
	<div id="grid" class="nui-datagrid" style="width:100%;height:auto" 
		url="com.bos.grt.conGrt.getConGRTBZJList.biz.ext"allowAlternating="true"
		dataField="conGrts" allowResize="true" showReloadButton="false"
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
		<div property="columns">
			<div type="checkcolumn" >选择</div>
			<div field="SUBCONTRACT_NUM" headerAlign="center" allowSort="true" >保证金协议编号</div>
			<div field="PARTY_NAME" headerAlign="center" allowSort="true" >保证金所有权人</div>
			<div field="BZ" allowSort="true"  headerAlign="center"  dictTypeId="CD000001">币种</div>
			<div field="BZJJE"  headerAlign="center" dataType="currency">担保合同金额</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	nui.parse();
	var grid = nui.get("grid");
	var contractId="<%=request.getParameter("contractId")%>";
	var subcontractType="<%=request.getParameter("subcontractTypeCd")%>";
	
	var partyId="<%=request.getParameter("partyId")%>";
	var applyId="<%=request.getParameter("applyId")%>";
	
	var proFlag="<%=request.getParameter("proFlag")%>";
	var proFlag_s="<%=request.getParameter("proFlag_s")%>";
	var xgbz="<%=request.getParameter("xgbz")%>";
 	
	if(proFlag!='1'){
		nui.get("addBtn").hide();
		nui.get("editBtn").hide();
		nui.get("deleteBtn").hide();
	}

	
	search();
	//初始化查询
	function search(){
	    grid.load({"contractId":contractId,"subcontractType":subcontractType,"partyId":partyId,"applyId":applyId,"xgbz":xgbz});
	}
	//添加担保合同
	function add(){
		//添加担保合同信息
		 
		var json = nui.encode({"subContract":{"subcontractType":subcontractType,"partyId":partyId}});
		$.ajax({
            url: "com.bos.grt.conGrt.saveGrtCon.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	var o = nui.decode(mydata);
				//添加担保合同与业务合同关联关系
				var json1 = nui.encode({"conSubcontractRel":{"contractId":contractId,"subcontractId":o.subContract.subcontractId,"ifEffective":"1"}});
				$.ajax({
		            url: "com.bos.grt.conGrt.insertGrtConRel.biz.ext",
		            type: 'POST',
		            data: json1,
		            cache: false,
		            contentType:'text/json',
		            cache: false,
		            success: function (mydata) {
		            	var o = nui.decode(mydata);
		            	//跳往担保合同信息页面（已生成担保合同ID）
	            		nui.open({
				            url: nui.context + "/crt/con_grt/con_bzj_tab.jsp?subcontractId="+o.conSubcontractRel.subcontractId+"&contractId="+contractId+"&partyId="+partyId+"&applyId="+applyId+"&xgbz="+xgbz,
				            showMaxButton: true,
				            title: "保证金明细信息",
				            width: 800,
				            height: 500,
				            state:"max",
				            ondestroy: function(e) {
				            	search();
				            }
				        });
					}
		        })
			}
        });
	}




    function edit(v){     
        var row = grid.getSelected();
        if(xgbz=="1"){
   
        if("01" == row.OPERATION_TYPE&&v=='0'){
        	alert("已存在保证金不允许编辑");
        	return;
        } 
        }
        if(row){
	        nui.open({
	            url: nui.context + "/crt/con_grt/con_bzj_tab.jsp?subcontractId="+row.SUBCONTRACT_ID+"&contractId="+contractId+"&view="+v+"&applyId="+applyId+"&partyId="+partyId+"&xgbz="+xgbz,
	            title: "担保合同信息", 
	            width: 800,
	    		height: 500,
	    		state:"max",
	            allowResize:true,
	    		showMaxButton: true,
	            onload: function () {
	                var iframe = this.getIFrameEl();
	                //var data = row;
	                search();
	            },
	            ondestroy: function (action) {
	            	search();
	            }
	        });        
        }else{
        	nui.alert("请选择一条记录后再操作");
        	return;
        }
    }

    function remove(){
    	var row = grid.getSelected();
        if (row) {    
	    	 nui.confirm("确定删除吗？","确认",function(action){
	        	if(action!="ok") return;
	        	var conSubconId=row.CON_SUBCON_ID;
	        	var subcontractId=row.SUBCONTRACT_ID; 
				var json = nui.encode({"bizRel":{"relationId":""},"conSubRel":{"conSubconId":conSubconId},"conSub":{"subcontractId":subcontractId}});
				$.ajax({
		            url: "com.bos.grt.conGrt.delConBZRRel.biz.ext",
		            type: 'POST',
		            data: json,
		            cache: false,
		            contentType:'text/json',
		            cache: false,
		            success: function (mydata) {
		            	var o = nui.decode(mydata);
						search();
					}
		        });
	        });
        } else {
            nui.alert("请选中一条记录");
        }
    }
</script>
</body>
</html>