<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-07-03 10:36:40
  - Description:
-->
<head>
<title>保证金协议关联保证金信息</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="form" style="width:99.5%;height:auto;overflow:hidden; text-align:center;"  class="nui-form">
	<div class="nui-toolbar" style="border-bottom:0;text-align:left;">
		<!-- <a class="nui-button" iconCls="icon-add" onclick="add()" id="add">添加</a> -->
		<!-- <a class="nui-button" iconCls="icon-save" onclick="insert()" id="link">关联</a> -->
		<a class="nui-button" iconCls="icon-edit" onclick="edit(0)"id="edit">追加</a> 
		<a class="nui-button" iconCls="icon-zoomin" onclick="edit(1)" id="see">查看</a> 
		<!-- <a class="nui-button" iconCls="icon-remove" onclick="remove()" id="delete">删除</a> -->
	</div>
	<div id="grid" class="nui-datagrid" style="width:100%;height:auto" 
		url="com.bos.grt.conGrt.getConSubBzj.biz.ext"allowAlternating="true"
		dataField="conGrts" allowResize="true" showReloadButton="false"
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
		<div property="columns">
			<div type="checkcolumn" >选择</div>
			<div type="indexcolumn">序号</div>
			<div field="MARGIN_SORT" allowSort="true"  headerAlign="center"  dictTypeId="XD_YWDB0134">保证金类型</div>
			<div field="OPEN_BANK" headerAlign="center" allowSort="true" dictTypeId="org">开户行</div>
			<div field="ACCT_NAME" headerAlign="center" allowSort="true" >开户人</div>
			<div field="MARGIN_ACCOUNT" headerAlign="center" allowSort="true" >保证金账号</div>
			<div field="CURRENCY_CD" headerAlign="center" allowSort="true" dictTypeId="CD000001">币种</div>
			<div field="ACC_BALANCE" headerAlign="center" allowSort="true" >保证金金额</div>
<!-- 			<div field="IS_JIXI" headerAlign="center" allowSort="true" dictTypeId="XD_0002">是否计息</div> -->
			<div field="MARGIN_RATE" headerAlign="center" allowSort="true" >执行利率(%)</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	nui.parse();
	var grid = nui.get("grid");
	var subcontractId="<%=request.getParameter("subcontractId")%>";
	var applyId="<%=request.getParameter("applyId")%>";
	var subcontractType="<%=request.getParameter("subcontractType")%>";
	var partyId="<%=request.getParameter("partyId")%>";
	var xgbz="<%=request.getParameter("xgbz")%>";
	
	var contractId="<%=request.getParameter("contractId")%>";
	
	var view="<%=request.getParameter("view") %>";
	var ljurl;	
	var ljurl2;
	if (view=="1") {
	/* 	nui.get("add").hide();
		nui.get("link").hide();
		nui.get("delete").hide(); */
		nui.get("edit").hide();
	} 
	
		
	/* if (xgbz=="1") {
		nui.get("see").hide();
	 
	}  */
	search();
	//初始化查询
	function search(){
	    grid.load({"subcontractId":subcontractId,"xgbz":xgbz});
	}
	//将抵质押物引入担保合同
	function insert(){
		var totalCount = grid.getTotalCount();
		if(totalCount>0){
			alert("保证金协议只能关联一笔保证金");
			return;
		}
        nui.open({
            url: nui.context + "/crt/con_grt/selSubGrt_bzj.jsp?subcontractId="+subcontractId+"&applyId="+applyId+"&subcontractType=03"+"&xgbz="+xgbz,
            title: "关联保证金",
            width: 1100,
    		height: 450,
            ondestroy: function (action) {     
				search();
        	}
        }); 
	}
	//删除抵质押物与担保合同关联关系
	function remove(){
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一笔保证金信息");
			return false;
		}
		 
		var json = nui.encode({"relationId":row.RELATION_ID,"row":row,"xgbz":xgbz});
		
		
		//删除抵质押物与担保合同关联关系
		$.ajax({
	        url: "com.bos.grt.conGrt.delSubGrtRel.biz.ext",
	        type: 'POST',
	        data: json,
	        contentType:'text/json',
	        cache: false,
	        success: function (data) {
	        	search();
            	alert("删除关联成功");
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            alert(jqXHR.responseText);
	        }
        });	
	}
	
	function edit(v){
      	var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一笔保证金信息");
			return false;
		}
        	   nui.open({
			    	url: nui.context + "/crt/con_grt/con_bzj_add1.jsp?partyId="+partyId+"&applyId="+applyId+"&subcontractId="+subcontractId+"&suretyId="+row.SURETY_ID+"&view="+v+"&xgbz="+xgbz+"&contractId="+contractId,
					title: "保证金关联信息", 
					width: 800, 
					height: 400,
					allowResize:false,
				    allowDrag: false,
					showMaxButton: false,
					ondestroy: function (action) {
							search();
						}
					});
	}
	
	function add() {
		var totalCount = grid.getTotalCount();
		if(totalCount>0){
			alert("一个保证金协议只能关联一个保证金");
			return;
		}
		 
		var json=nui.encode({"subContract":{"subcontractId":subcontractId}});
		$.ajax({
	        url: "com.bos.grt.conGrt.getGrtConInfo.biz.ext",    
	        type: 'POST',
	        data: json,
	        cache: false,
	        contentType:'text/json',
	        success: function (text) {
					var o = nui.decode(text);
	        		if(null==o.subContract.bzjlx){
	        			alert("请先保存保证金协议基本信息");
	        			return;
	        		}else{
	        			nui.open({
			    			url: nui.context + "/crt/con_grt/con_bzj_add.jsp?partyId="+partyId+"&applyId="+applyId+"&collType=04&subcontractId="+subcontractId+"&xgbz="+xgbz,
							title: "新增保证金", 
							width: 800, 
							height: 400,
							allowResize:false,
				        	allowDrag: false,
							showMaxButton: false,
							ondestroy: function (action) {
								search();
							}
						});
	        		}
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            nui.alert(jqXHR.responseText);
	        }
		});
		}
</script>
</body>
</html>