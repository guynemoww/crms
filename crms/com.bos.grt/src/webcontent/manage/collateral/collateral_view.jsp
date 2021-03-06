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
	<div id="grid4" class="nui-datagrid" style="width:auto;height:200px;" 
		url="com.bos.grt.grtMainManage.grtOuter.getLinkCardList.biz.ext"
		dataField="arrays"allowAlternating="true"
		allowResize="false" showReloadButton="false" allowCellEdit="true" allowCellSelect="true"
		sizeList="[10,15,20,50,100]" multiSelect="true" pageSize="15" sortMode="client">
		<div property="columns">
			<div field="CONTRACT_NUM" allowSort="true" headerAlign="center">业务合同编号</div>
			<div field="SUBCONTRACT_NUM" allowSort="true" headerAlign="center">担保合同编号</div>
	        <div field="PARTY_NAME" allowSort="true" headerAlign="center">抵质押人名称</div>
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
 	
 	//权证主键ID
	var suretyKeyId ="<%=request.getParameter("suretyKeyId")%>";
	
	var partyId="<%=request.getParameter("partyId")%>";
		
	var grid = nui.get("grid4");
	
	var v="<%=request.getParameter("view") %>";
	if ("1" == v){
		nui.get("add").hide();
		nui.get("edit0").hide();
		nui.get("remove").hide();
	}
	
    search();
    
    function search() {
		var json=({"suretyKeyId":suretyKeyId});
	    grid.load(json);
    }

	
    function link() {
    	if(suretyKeyId==""||suretyKeyId=="null"){
    		alert("请先录入权证基本信息!");
    		return;
    	}
    	
    	var contractNum;
    	var collType;
    	var rows = nui.get("grid4").getData();
    	for(var i=0;i<rows.length;i++){
    		contractNum=rows[i].CONTRACT_NUM;
    		collType=rows[i].COLL_TYPE;
    		break;
    	}
        nui.open({																				
            url: nui.context+"/grt/manage/collateral/addAbleCollateral.jsp?partyId="+partyId+"&suretyKeyId="+suretyKeyId+"&contractNum="+contractNum+"&collType="+collType,
            title: "选择关联押品", 
            width: 800, 
        	height: 400,
        	allowResize: false,
        	showMaxButton: true,
            ondestroy: function (action) {
                if(action=="ok"){
                    search();
                }
            }
        });
    }

	function remove(){
    	var rows = grid.getSelecteds();
    	var row = grid.getSelected();
        if (row) {
        	nui.confirm("确定删除吗？","确认",function(action){
            	if(action!="ok") return;
            	var arrays=new Array();
		    	for(var i=0;i<rows.length;i++){
		    		arrays.push(rows[i].REL_ID);
		    	}
		    	var json=nui.encode({"rels":arrays});
                $.ajax({
                    url: "com.bos.grt.grtMainManage.grtOuter.deleteLinkCard.biz.ext",
	                type: 'POST',
	                data: json,
	                cache: false,
	                contentType:'text/json',
                    success: function (text) {
                    	nui.alert(text.msg);
                    	search();
                    },
                    error: function () {
                    	nui.alert("操作失败！");
                    }
                });
            }); 
	      }else{
	        alert("请选中一条记录");
	      }
    }
	</script>
</body>
</html>
