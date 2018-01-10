<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): fengjiahui
  - Date: 2014-02-13 16:27:30
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp"%>
<title>客户债项情况</title>
</head>
<script type="text/javascript" src="<%=contextPath%>/biz/biz_js/biz.js"></script>
<body>
<div>
	<div id="queryform" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;" >
		<div class="nui-dynpanel"  columns="6">
		<label>申报模式：</label>
		<input name="applyModeType"  class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXCD1040" />

		<label>授信品种：</label>
		<input id="productType" name="productType" class="nui-buttonEdit nui-form-input" allowInput="false" onbuttonclick="selectProduct"/>
		
		<label>是否完成：</label>
		<div  id="completeStatus" name="completeStatus" class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" data onvaluechanged="valuechanged"></div>	
		
		</div>
		<div class="nui-toolbar" style="text-align:right;border:none">
		    <a class="nui-button" style="margin-right:5px;height:21px;" iconCls="icon-search" onclick="search()">查询</a>
			<a class="nui-button" style="margin-right:23px;height:21px" iconCls="icon-reset" onclick="reset()">重置</a>
		</div>
	</div>
	<div style="width:99.5%">
	<div class="nui-toolbar" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;;margin-top:7px">
		<!-- 
		<a id="addCust" style="margin-left:5px" class="nui-button"  iconCls="icon-add" onclick="add()">新增</a>
		<a id = "query" class="nui-button" iconCls="icon-node" onclick="query()">查看</a>
		<a id="editCust" class="nui-button" iconCls="icon-edit" onclick="edit()">编辑</a>
		<a id="smallCorp" class="nui-button" iconCls="icon-upload" onclick="smallCorpIdentify(0)">发起小企业认定</a>
		 -->
	</div>
</div>
	 <a class="nui-button" style="float:left;" onclick="open()">填写债项级分类信息</a>
	 <div id="grid" class="nui-datagrid" style="width:99.5%;height:auto;"
	 url="com.bos.aft.dailyInspect.getDebtList.biz.ext" dataField="debtDatas" sizeList="[5,10,20,50]" 
	  showReloadButton="false" pageSize="5" multiSelect="true">
		<div property="columns">
			<div type="checkcolumn"></div>
			<div type="indexcolumn">序号</div>
			<div field="contractNum" headerAlign="center" allowSort="true">合同编号</div>
			<div field="businessNum" headerAlign="center" allowSort="true">支用编号/汇票编号</div>
			<div field="applyModeType" headerAlign="center" allowSort="true" dictTypeId="XD_SXCD1040" >申报模式</div>
			<div field="bizType" headerAlign="center" allowSort="true" dictTypeId="XD_SXCD1038">业务性质</div>
			<div field="productType" headerAlign="center" allowSort="true" dictTypeId="product">授信品种</div>
			<div field="mainSuretyMode" headerAlign="center" allowSort="true" dictTypeId="CDZC0005">担保方式</div>
			<div field="loanBalance" headerAlign="center" allowSort="true" >借据余额</div>
			<div field="interestBalance1" headerAlign="center" allowSort="true" >表内欠息余额</div>
			<div field="interestBalance2" headerAlign="center" allowSort="true" >表外欠息余额</div>
			<div field="currencyCd" headerAlign="center" allowSort="true" dictTypeId="CD000001">币种</div>
			<div field="startDate" headerAlign="center" allowSort="true" >合同开始日期</div>
			<div field="expirationDate" headerAlign="center" allowSort="true" >合同到期日期</div>
			<div field="contractStatusCd" headerAlign="center" allowSort="true" dictTypeId="XD_SXCD1106">完成状态</div>
		</div>
	 </div>
	 	
</div>

	<script type="text/javascript">
		nui.parse();
		var param=<%=request.getParameter("param") %>;
		//alert(nui.encode(param));
		var grid = nui.get("grid");
		var form=new nui.Form("#queryform");
		var o={"formData":form.getData(),"param":param};
		var goEdit = "<%=request.getParameter("goEdit") %>";
		grid.load(o);
		
		function open(){
			var rows = grid.getSelecteds();
			var rowList = [];
			var row;
			if(null==rows||typeof(rows)=="undefined"){
				alert("请选择一条数据");
				return;
			}
			for(var i=0;i<rows.length;i++){
			   rowList[i] = {aldInfoId:rows[i].aldInfoId,alcInfoId:rows[i].alcInfoId};
			}
			row=rows[0];
			/*
			if(param.lastAlcInfoId==""||param.lastAlcInfoId==null||param.lastAlcInfoId==undefined){
					var url=nui.context+"/aft/dailyInspect/firstDebtDataInfo.jsp?param="+nui.encode(param)+"&aldInfoId="+row.aldInfoId;
					nui.open({
			            url: url,
			            title: "信贷资产首次检查债项级信息检查", 
			            width: 750,  
			        	height: 400,
			        	allowResize:true,
			        	showMaxButton: true,
			            ondestroy: function (action) {
			                    grid.reload();
			            }
	       		 	});
			}else{*/
				//alert(nui.encode(param)+';'+row.aldInfoId+';'+row.loanDirection);
					var url=nui.context+"/aft/dailyInspect/debtDataInfo.jsp?loanSummaryId="+row.loanSummaryId+"&param="+nui.encode(param)+"&partyId="+row.partyId+"&aldInfoId="+row.aldInfoId+"&loanDirection="+row.loanDirection+"&goEdit="+goEdit+"&rows="+nui.encode(rowList);
					nui.open({
			            url: url,
			            title: "信贷资产检查债项级信息检查", 
			            width: 800, 
			        	height: 500,
			        	allowResize:true,
			        	showMaxButton: true,
			            ondestroy: function (action) {
			                    //grid.reload();
			            }
	        		});
			//}
			
		}
		function search(){
			var o={"formData":form.getData(),"param":param};
			//alert(nui.encode(form.getData()));
			grid.load(o);
		}
		
		function reset(){
			form.clear();
		}
	</script>
</body>
</html>