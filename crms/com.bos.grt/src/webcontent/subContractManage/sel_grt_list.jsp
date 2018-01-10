<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): chenchuan
  - Date: 2016-06-06 10:23:37
  - Description:
-->
<head>
<title>查询抵质押物列表</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="form" style="width:99.5%;height:auto;overflow:hidden; text-align:center;"  class="nui-form">
	<div class="nui-toolbar" style="border-bottom:0;text-align:left;">
		<a class="nui-button" iconCls="icon-add" onclick="insert()">确认</a>
		<%--<a class="nui-button" iconCls="icon-add" onclick="add()">增加</a>--%>
		 <a id="editCust" class="nui-button" iconCls="icon-zoomin"onclick="view()">查看</a>
	</div>
	<div id="grid" class="nui-datagrid" style="width:100%;height:auto" 
		url="com.bos.grt.subContractManage.subContract.findUnGrtList.biz.ext"
		dataField="grts" allowResize="true" showReloadButton="false"allowAlternating="true"
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
		<div property="columns">
			<div type="checkcolumn" >选择</div>  
			<div type="indexcolumn">序号</div>
			<div field="PARTY_NAME" headerAlign="center" allowSort="true" >抵质押人名称</div>
			<div field="SURETY_NO" headerAlign="center" allowSort="true" >抵质押物编号</div>
			<div field="SORT_TYPE" headerAlign="center" allowSort="true" dictTypeId="XD_YPZL01">抵质押物类型</div>
			<div field="CURRENCY_CD" headerAlign="center" allowSort="true" dictTypeId="CD000001">币种</div>
			<div field="ASSESS_VALUE" headerAlign="center" allowSort="true" dataType="currency" >评估价值（元）</div>
			<div field="MORTGAGE_VALUE" headerAlign="center" allowSort="true"  dataType="currency">权利价值（元）</div>
			<div field="SURETY_AMT" headerAlign="center" allowSort="true"  dataType="currency">已担保金额（元）</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	nui.parse();
	var grid = nui.get("grid");
	var subcontractId="<%=request.getParameter("subcontractId")%>";
	var subconractType ="<%=request.getParameter("subconractType")%>";
	var array="<%=request.getParameter("array")%>";
	search();
	//初始化查询
	function search(){
	    grid.load({"subcontractId":subcontractId,"subcontractType":subconractType});
	}
	//将抵质押物引入担保合同
	function insert(){
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一笔抵质押品信息");
			return false;
		}
		if(array!=""){
			var arr=array.split(",");
			for(var i=0;i<arr.length;i++){
				if(arr[i]==row.SURETY_NO){
					alert("同一笔抵质押物不能重复关联");
					return;
				}
			}
		}
		
		var sortType=row.SORT_TYPE.substring(0,7);
		if(sortType=="A010101"){
			//校验存单的 存单账号 存单号 存入日 到期日 是否为空
			
			var suretyNo=row.SURETY_NO
			
			var json = nui.encode({"suretyNo":suretyNo});
			//将担保物关联到抵质押合同
			$.ajax({
		        url: "com.bos.grt.grtMainMargin.grtContract.validateDeposit.biz.ext",
		        type: 'POST',
		        data: json,
		        contentType:'text/json',
		        cache: false,
		        success: function (data) {
	            	var acc=data.deposit.DEPOSIT_ACC
	            	var no=data.deposit.DEPOSIT_NO
	            	var beginDate=data.deposit.BEGIN_DATE
	            	var endDate=data.deposit.END_DATE
	            	debugger;
	            	if(acc==null||no==null||beginDate==null||endDate==null){
	            		alert("请完整录入存单存单账号、存单号、存入日、到期日!");
	            	}else{
	            		add(row);
	            	}
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		            alert(jqXHR.responseText);
		        }
	        });
		}else{		//非存单
			add(row);
		}
	}
	
	function add(row){
		nui.open({
	        url: nui.context + "/grt/subContractManage/grt_surety_amt.jsp?subcontractId="+subcontractId+"&suretyId="+row.SURETY_ID,
	        title: "押品本次担保金额", 
	        width: 400,
			height: 200,
	        allowResize:true,
			showMaxButton: true,
	        ondestroy: function (action) {
	            if (action == "ok") {
	                var iframe = this.getIFrameEl();
	                var data = iframe.contentWindow.GetData();
	                data = nui.clone(data);
	                if (data) {
	    				var json = nui.encode({"tbConSubTmp":{"subcontractId":subcontractId,"conSuretyId":row.SURETY_ID,"suretyAmt":data.suretyAmt}});
						//将担保物关联到抵质押合同
						$.ajax({
					        url: "com.bos.grt.subContractManage.subContract.insertTbConSubTmp.biz.ext",
					        type: 'POST',
					        data: json,
					        contentType:'text/json',
					        cache: false,
					        success: function (data) {
					        	if(data.msg){
				            		alert(data.msg);
					        	}else{
				            		alert("关联成功！");
				            		CloseWindow("ok");
				            	}
					        },
					        error: function (jqXHR, textStatus, errorThrown) {
					            alert(jqXHR.responseText);
					        }
				        });
	                }
	            }
	        }
	    });
	}
	
	
	function view(){
        var row = grid.getSelected();
		var v ="1";
        if (row) {		   
        	var relationId=row.RELATION_ID;
        	nui.open({
	            url: nui.context + "/com.bos.grt.grtMainManage.getGrtDetail.flow?suretyId="+row.SURETY_ID+"&view="+v,
	            showMaxButton: true,
	            title:"押品信息",
	            width: 800,
	            height: 500,
	            state:"max",
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