<%@page pageEncoding="UTF-8"%>

<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<script type="text/javascript" src="<%=contextPath%>/grt/grtJS/grt.js"></script>
<body>

	<div class="nui-toolbar" style="text-align:left;padding-top:5px;"borderStyle="border:0;">
    	<a class="nui-button" iconCls="icon-add" id="biz_gs_dy_add" onclick="add()">增加</a>
    	<a class="nui-button" iconCls="icon-edit" id="biz_gs_dy_edit" onclick="edit(0)">编辑</a>
		<a class="nui-button" iconCls="icon-remove" id="biz_gs_dy_remove" onclick="remove()">删除</a>
	</div>
   <div id="datagrid1" class="nui-datagrid" style="width:100%;height:auto;"  sortMode="client"
	    url="com.bos.grt.grtMainManage.grtOuter.getOuterGrtList.biz.ext" dataField="items" allowAlternating="true"
	    sizeList="[10,20,50,100]" pageSize="10">
		<div property="columns">
	       <div type="checkcolumn">选择</div>
	        <div type="indexcolumn">序号</div>
	        <div field="ORG_NUM" allowSort="true" headerAlign="center" dictTypeId="org">机构名称</div>
	        <div field="PARTY_NAME" allowSort="true" headerAlign="center">抵质押人名称</div>
	        <div field="COLL_TYPE" allowSort="true" dictTypeId="XD_YWDB0131" headerAlign="center" >抵质押类型</div>
	        <div field="SORT_TYPE" allowSort="true" dictTypeId="XD_YPZL01" headerAlign="center" >抵质押物类型</div>
	        <div field="SURETY_NO" allowSort="true" dictTypeId="XD_YWDB02" headerAlign="center" >抵质押物编号</div>
	        <div field="ASSESS_VALUE" allowSort="true"  headerAlign="center" dataType="currency">评估价值(元)</div>
	     </div>
	</div>

	<script type="text/javascript">
		nui.parse();
	 	var partyId="<%=request.getParameter("partyId")%>";
	 	var sortType="<%=request.getParameter("sortType")%>";
	 	var collType="<%=request.getParameter("collType")%>";
	 	
	 	var grid = nui.get("datagrid1");
	 	
	 	initPage();
	 	
	 	function initPage(){
	 		var json =({"partyId":partyId,"sortType":sortType<%--,"collType":collType--%>});
	 		grid.load(json);
	 	}
	 	
	 	
	 	
	 	function add(){
	 		//var suretyNum = getSuretyNum(sortType);
    		var json = nui.encode({"tbGrtGuarantybasic":{"partyId":partyId,"sortType":sortType,"collType":collType}});
    		$.ajax({
				url: "com.bos.grt.grtMainManage.grtOuter.addOuterGrt.biz.ext",
				type:'POST',
				data: json,
				cache: false,
				contentType:'text/json',
				success: function (text) {
					var suretyId=text.tbGrtGuarantybasic.suretyId;
					var collType=text.tbGrtGuarantybasic.collType;
					 nui.open({
			            url: nui.context + "/com.bos.grt.grtMainManage.getGrtDetail.flow?suretyId="+suretyId+"&collType="+collType+"&sortType="+sortType+"&isManage=1",
			            showMaxButton: true,
			            title: "新增押品信息",
			            width: 800,
			            height: 500,
			            state:"max",
			            ondestroy: function(e) {
			            	initPage();
			            }
			        });
					
					git.unmask();
				},
				error: function () {
					nui.alert("操作失败！");
				}
			});
	 	}
	 	
	 	function edit(v){
	 		var row = grid.getSelected();
	        var title1;
	        if(v == "0"){
	        	title1 = "编辑";
	        }else if(v == "1"){
	        	title1 = "查看";
	        }
	        if (row) {			   
	        	nui.open({
		            url: nui.context + "/com.bos.grt.grtMainManage.getGrtDetail.flow?suretyId="+row.SURETY_ID+"&view="+v+"&collType="+row.COLL_TYPE,
		            showMaxButton: true,
		            title: title1+"押品信息",
		            width: 800,
		            height: 500,
		            state:"max",
		            ondestroy: function(e) {
		            	initPage();
		            }
		        });
	        } else {
	            alert("请选中一条记录");
	        }
	 	}
	 	
	 	
	 	function remove() {
	        var row = grid.getSelected();
	         if (row) {
	    		nui.confirm("确定删除吗？删除后系统中将无法找回该押品记录!","确认",function(action){
		        	if(action!="ok") return;
		        	var json=nui.encode({"grtMortgageBasic":{"suretyId":row.SURETY_ID,"sortType":row.SORT_TYPE}});
		            $.ajax({
		                 url: "com.bos.grt.grtMainManage.grtOuter.deleteOuterGrt.biz.ext",
		                type: 'POST',
		                data: json,
		                cache: false,
		                contentType:'text/json',
		                success: function (text) {
		                   nui.alert(text.msg);
		                   initPage();
		                },
		                error: function () {
		                	nui.alert("操作失败！");
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