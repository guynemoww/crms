<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): 钟辉
  - Date: 2015-08-24
-->
<head>
	<%@include file="/common/nui/common.jsp" %>
	<title>录入权证信息的时候展示的押品列表页面</title>
</head>
<body>
	<div class="nui-toolbar" style="border-bottom:0;width:100%;text-align: left;">
		<a class="nui-button" iconCls="icon-add" onclick="link()" id="link">选中</a>
	</div>
	<div id="form1" class="nui-form" style="width:100%;height:auto;overflow:hidden;">
	    	<div class="nui-dynpanel" columns="4">
	    		<label>抵质押人名称：</label>
				<input name="partyName"  class="nui-textbox nui-form-input" />
		    </div>
		<div class="nui-toolbar" style="text-align:right;padding-top:5px;padding-bottom:5px;" 
   			 borderStyle="border:0;">
    		<a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
			<a class="nui-button" iconCls="icon-reset" onclick="reset()">重置</a>
		</div>
	</div>
	<div id="grid4" class="nui-datagrid" style="width:auto;height:200px;" 
		url="com.bos.grt.grtMainManage.grtOuter.getLinkList.biz.ext"
		dataField="arrays"allowAlternating="true"
		allowResize="false" showReloadButton="false" allowCellEdit="true" allowCellSelect="true"
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
		<div property="columns">
			<div type="checkcolumn" >选择</div>
	        <div field="CONTRACT_NUM" allowSort="true" headerAlign="center">业务合同编号</div>
	        <div field="SUBCONTRACT_NUM" allowSort="true" headerAlign="center">担保合同编号</div>
	        <div field="DIPARTYNAME" allowSort="true" headerAlign="center">抵质押人名称</div>
	        <div field="COLL_TYPE" allowSort="true" dictTypeId="XD_YWDB0131" headerAlign="center" >抵质押类型</div>
	        <div field="SORT_TYPE" allowSort="true" dictTypeId="XD_YPZL01" headerAlign="center" >抵质押物类型</div>
	        <div field="SURETY_NO" allowSort="true" dictTypeId="XD_YWDB02" headerAlign="center" >抵质押物编号</div>
	        <div field="ASSESS_VALUE" allowSort="true"  headerAlign="center" dataType="currency">评估价值(元)</div>  
	        <div field="MORTGAGE_VALUE" allowSort="true"  headerAlign="center" dataType="currency">权利价值(元)</div>  
			<div field="ORG_NUM" allowSort="true" headerAlign="center" dictTypeId="org">机构名称</div>
		</div>
	</div>

	<script type="text/javascript">
 	nui.parse();
	
	var partyId ="<%=request.getParameter("partyId")%>";
	
	var suretyKeyId ="<%=request.getParameter("suretyKeyId")%>";
	
	var contractNum ="<%=request.getParameter("contractNum")%>";
	
	var collType ="<%=request.getParameter("collType")%>";

	var grid = nui.get("grid4");
	
    
    //add by shangmf
    var form = new nui.Form("#form1");
    function search() {
		//var json=({"partyId":partyId});
		var json = form.getData();
	    grid.load(json);
    }
	search();
    function link() {
    		var row = grid.getSelected();
    	if(contractNum!="undefined"){
    		if(row.CONTRACT_NUM==contractNum){
    			if(row.COLL_TYPE==collType){
    				addLink();
    			}else{
    				alert("权证的抵质押类型必须一致!");
    			    return;
    			}
    		}else{
    			alert("选中的业务合同编号必须一致才可以关联在同一权证下!");
    			return;
    		}
    	}else{
    		var flag = "1";
    		//如果选择的是综合授信协议，查询其下有无普通业务合同，如果存在普通业务合同。则提示“授信协议下存在普通合同，请选择该普通合同关联押品”
    		var conNo = row.CONTRACT_NUM;
    		var consubNo = row.SUBCONTRACT_NUM;
    		if(conNo.substring(0,2)=="XY"){
	    		var json=nui.encode({"subcontractNum":consubNo});
		    	 $.ajax({
			        url: "com.bos.grt.grtMainManage.grtOuter.checkCreditCount.biz.ext",
			        type: 'POST',
			        data: json,
			        cache: false,
			        async : false,
			        contentType:'text/json',
			        success: function (text) {
			        	if(text.msg == "true"){
							alert("授信协议下存在普通合同，请选择该普通合同关联押品");	
							flag = "0";	        	
			        	}
			        },
			        error: function () {
			        	nui.alert("操作失败！");
			        	flag = "0";	 
			        }
			    }); 
    		}
    		if(flag == "1"){
				addLink();
    		}
    	}
    }
    
    function addLink(){
    	var rows = grid.getSelecteds();
    	if(rows.length>0){
    		var json=nui.encode({"suretyKeyId":suretyKeyId,"rows":rows});
	    	 $.ajax({
		        url: "com.bos.grt.grtMainManage.grtOuter.addLinkCard.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        contentType:'text/json',
		        success: function (text) {
		        	alert(text.msg);
		        	CloseWindow("ok");
		        },
		        error: function () {
		        	nui.alert("操作失败！");
		        }
		    }); 
    	}else{
    		alert("请选中关联的押品信息!");
    	}
    }
    
    function reset(){
			form.reset();
	}
	</script>
</body>
</html>
