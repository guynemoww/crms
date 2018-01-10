<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): chenchuan
  - Date: 2016-05-20 10:36:40
  - Description:
-->
<head>
<title>保证合同关联保证人信息</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="form" style="width:99.5%;height:auto;overflow:hidden; text-align:center;"  class="nui-form">
	<div class="nui-toolbar" style="border-bottom:0;text-align:left;">
		<!-- <a class="nui-button" iconCls="icon-add" onclick="add()" id="add">添加</a> -->
		<a class="nui-button" iconCls="icon-save" onclick="insert()" id="link">关联</a>
		<a class="nui-button" iconCls="icon-remove" onclick="remove()" id="delete">删除</a>
		<a class="nui-button" iconCls="icon-zoomin" onclick="view">查看</a>
	</div>
	<div id="grid" class="nui-datagrid" style="width:100%;height:auto" 
		url="com.bos.grt.conGrt.getConSubBzr.biz.ext"
		dataField="conGrts" allowResize="true" showReloadButton="false"
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
		<div property="columns">
			<div type="checkcolumn" >选择</div>
			<div type="indexcolumn">序号</div>
			<div field="PARTY_NAME" headerAlign="center" allowSort="true" >保证人名称</div>
			<div field="CERT_TYPE" allowSort="true"  headerAlign="center"  dictTypeId="CDKH0002">证件类型</div>
			<div field="CERT_NUM" allowSort="true"  headerAlign="center"  >证件号码</div>
			<div field="TOTAL_AMT" allowSort="true"  headerAlign="center"  dataType="currency">申请担保金额（元）</div>
			<div field="USED_AMT" allowSort="true"  headerAlign="center"  dataType="currency">已担保金额（元）</div>
			<div field="SURETY_AMT" allowSort="true"  headerAlign="center" dataType="currency" >担保确认金额（元）</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	nui.parse();
	var grid = nui.get("grid");
	var subcontractId="<%=request.getParameter("subcontractId")%>";
	var applyId="<%=request.getParameter("applyId")%>";
	var subcontractType="<%=request.getParameter("subcontractType")%>";
	var contractId;
	if("<%=request.getParameter("contractId")%>"!="null"){
		var contractId="<%=request.getParameter("contractId")%>";
	}
	
	var view="<%=request.getParameter("view") %>";
	if (view=="1") {
		nui.get("delete").hide();
		nui.get("link").hide();
	} 
	
	search();
	//初始化查询
	function search(){
	    grid.load({"subcontractId":subcontractId,"contractId":contractId});
	}
	//将保证人引入担保合同
	function insert(){
		var json = nui.encode({"subcontractId":subcontractId});
		$.ajax({
	        url: "com.bos.grt.grtManage.mortgageSort.getSubCon.biz.ext",
	        type: 'POST',
	        data: json,
	        contentType:'text/json',
	        cache: false,
	        success: function (data) {
	        	var o = nui.decode(data);
	        	if("" == o.conSub.ifTopSubcon || null == o.conSub.ifTopSubcon){
	        		alert("请先保存合同基本信息！");
	        		return;
	        	}
	        	
	        	var totalCount = grid.getTotalCount();
				if(totalCount>0){
					alert("一个保证合同只能关联一个保证人");
					return;
				} 
	        	
		        nui.open({
		            url: nui.context + "/crt/con_grt/selSubGrt_bzr.jsp?subcontractId="+subcontractId+"&applyId="+applyId+"&subcontractType="+subcontractType,
		            title: "关联保证人",
		            width: 900,
		    		height: 400,
		            ondestroy: function (action) {     
						search();
		        	}
		        });
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            alert(jqXHR.responseText);
	        }
        });	
	}
	//删除抵质押物与担保合同关联关系
	function remove(){
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一笔抵押物信息");
			return false;
		}
		var json = nui.encode({"relationId":row.RELATION_ID});
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
	
	function view(){
        var row = grid.getSelected();
		var v ="1";
        if (row) {		   
        	var relationId=row.RELATION_ID;
        	nui.open({
        		url: nui.context+"/grt/guaranMainManager/guarantee_apply_list_guaranteer_add.jsp?suretyId="+row.SURETY_ID+"&view="+v,
	            showMaxButton: true,
	            title:"保证人信息",
	            width: 800,
	            height: 500,
	            ondestroy: function(e) {
	            	search();
	            }
	        });
        } else {
            alert("请选中一条记录");
        } 
	}
	
</script>
</body>
</html>