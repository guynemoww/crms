<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@include file="/common/nui/common.jsp" %>
	</head>
	<body>
		<center>
			<div id="form1" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;margin-top:5px;" >
				<input name="item.partyId" id="item.partyId" class="nui-hidden" />
				<input name="item.acApplyId" id="item.acApplyId" class="nui-hidden" />
				<div class="nui-dynpanel" columns="6">
					<label>客户名称</label>
					<input id="item.partyName" name="item.partyName" class="nui-textbox nui-form-input" />
			
					<label>合同编号</label>
					<input id="item.contractNum" name="item.contractNum" class="nui-textbox nui-form-input" style="width:200px"/>
					
					<label>分类结果</label>
					<input name="item.clsResult" id="item.clsResult" class="nui-dictcombobox nui-form-input" dictTypeId="XD_FLCD0001" allowInput="false" />
					
				</div>
				<div class="nui-toolbar" style="text-align:right;border:none" >
					<a class="nui-button" iconCls="icon-search" onclick="query()">查询</a>
	  				<a class="nui-button" iconCls="icon-reset" onclick="reset()">重置</a>
				</div>
			</div>
			<div id="datagrid" 
				class="nui-datagrid" 
				style="width:99.5%;height:auto;"
				sortMode="client" 
				url="com.bos.risk.sort.queryAdjustList.biz.ext" 
				dataField="items" 
				allowAlternating="true" 
				multiSelect="false" 
				showEmptyText="true" 
				showPager="true" 
				emptyText="没有查到数据" 
				showReloadButton="false" 
				showColumnsMenu="true" 
				onrowdblclick="" 
				allowCellEdit="true" 
				allowCellSelect="true"
				sizeList="[10,20,50,100]" 
				pageSize="10">
				<div property="columns">
					<div type="checkcolumn" allowSort="true" headerAlign="center" width="5%">选择</div>
				 	<div field="partyName" allowSort="true" headerAlign="center" autoEscape="false" align="center">客户名称</div>
				 	<div field="contractNum" allowSort="true" headerAlign="center" autoEscape="false" align="center">合同编号</div>
	       			<div field="productType" allowSort="true" headerAlign="center" dictTypeId="product" align="center">授信品种</div>       
	        		<div field="currencyCd" allowSort="true" headerAlign="center" dictTypeId="CD000001" align="center">币种</div>
					<div field="contractAmt" headerAlign="center" allowSort="true" dataType="currency" align="center">合同金额</div>
					<!-- <div field="conBalance" headerAlign="center" allowSort="true" dataType="currency" align="center">合同已用金额</div> -->
					<div field="conYuE" headerAlign="center" allowSort="true" dataType="currency" align="center">合同已用金额</div>
					<div field="beginDate" headerAlign="center" allowSort="true" align="center">合同起期</div>
					<div field="endDate" headerAlign="center" allowSort="true" align="center">合同止期</div>
					<div field="overdueCapital" headerAlign="center" allowSort="true" dataType="currency" align="center">逾期本金</div>
					<div field="inOverdueInterest" headerAlign="center" allowSort="true" dataType="currency" align="center">表内逾期利息</div>
					<div field="outOverdueInterest" headerAlign="center" allowSort="true" dataType="currency" align="center">表外逾期利息</div>
					<div field="lastClsResult" headerAlign="center" allowSort="true" dictTypeId="XD_FLCD0001" align="center">当前分类结果</div>
					<!-- <div field="clsResult" headerAlign="center" allowSort="true" dictTypeId="XD_FLCD0001" align="center">当前分类结果</div> -->
					<div field="approveResult" headerAlign="center" allowSort="true" dictTypeId="XD_FLCD0001" align="center">审核结果</div>
				</div>
			</div>
			<div style="margin-top:10px;">
				<input id="updateBtn" class="nui-button" style="margin-right:5px;" text="调整" iconCls="icon-edit" onclick="adjust_edit()"/>
				<input id="viewBtn" class="nui-button" style="margin-right:5px;" text="查看" iconCls="icon-node" onclick="adjust_history()"/>
			</div>
			</center>
		<script type="text/javascript">
			nui.parse();
			git.mask();
			var grid = nui.get("datagrid");
	  		var form = new nui.Form("#form1");
	  		var acApplyId = "<%=request.getParameter("acApplyId")%>";
	  		var partyId = "<%=request.getParameter("partyId")%>";
			var claMethod = "<%=request.getParameter("claMethod")%>";
			var workItemId = "<%=request.getParameter("workItemId")%>";
			var wflow = "<%=request.getParameter("wflow")%>";
			var operation = "<%=request.getParameter("operation")%>";
			
			if("view" == operation){
				nui.get("updateBtn").hide();
			}else{
				//$("#form1").css("display","none"); 
				nui.get("viewBtn").hide();
			}
			if("1" == wflow){
		 		nui.get("updateBtn").hide();
		 	}
			function reset(){
				form.reset();
			}
			function query() {
				//分割partyIds数组，重新组织查询条件
				var partyId_arr = partyId.split(",");
				var temp_partyId = "";
				for(var i = 0 ; i < partyId_arr.length ; i++){
					if(i == partyId_arr.length - 1){
						temp_partyId = temp_partyId + "'" + partyId_arr[i] + "'";
					}else{
						temp_partyId = temp_partyId + "'" + partyId_arr[i] + "',";
					}
				}
				partyId = temp_partyId;
				if (partyId) {
					nui.get("item.partyId").setValue(partyId);
				}
				nui.get("item.acApplyId").setValue(acApplyId);
				var data = form.getData(); 
		        grid.load(data);
		        git.unmask();
	    	}
	    	query();
			
			
			function adjust_edit(){
				var row = grid.getSelected();
				if(row){
			  		nui.open({
			            url: nui.context + "/risk/sort/adjust/sort_adjust_edit.jsp?cdInfoId="+row.cdInfoId+"&partyName2="+git.toUrlParam(row.partyName2)+"&clsResult="+row.clsResult+"&workItemId="+workItemId+"&lastClaMethod="+row.lastClaMethod+"&lastClsResult="+row.lastClsResult,
			            title: "调整分类", 
			            width: 800, 
		        		height: 400,
			        	allowResize:true,
			        	showMaxButton: true,
			            ondestroy: function (action) {
			                query();
			            }
			        });
		        }else{
		        	alert("请选中一条记录");
		        }
	  		}
	  		
			function adjust_history(){
				var row = grid.getSelected();
				if(row){
			  		nui.open({
			            url: nui.context + "/risk/sort/adjust/sort_adjust_history_list.jsp?cdInfoId="+row.cdInfoId,
			            title: "查看分类调整历史", 
			            width: 850, 
		        		height: 300,
			        	allowResize:true,
			        	showMaxButton: true,
			            ondestroy: function (action) {
			            }
			        });
		        }else{
		        	alert("请选中一条记录");
		        }
	  		}
	  		
		   	grid.on("preload",function(e){
		   		if (!e.data || e.data.length < 1)
		   			return;
		   		for (var i=0; i<e.data.length; i++){
		   			if(e.data[i].corpCustomerTypeCd != null){
			   			e.data[i]['partyName']='<a href="#" onclick="clickCorpCust(\''
			   				+ e.data[i].partyId+","+e.data[i].partyNum
			   				+ '\',\''+e.data[i].corpCustomerTypeCd+'\');return false;" value="'
			   				+ e.data[i].partyId
			   				+ '">'+e.data[i]['partyName']+'</a>';
		   			}else{
		   				e.data[i]['partyName'] = '<a href="#" onclick="clickPersCust(\''
							+ e.data[i].partyId + "," + e.data[i].partyNum
							+ '\');return false;" value="' + e.data[i].partyId + '">'
							+ e.data[i]['partyName'] + '</a>';
		   			}
		   			e.data[i]['contractNum']='<a href="#" onclick="goToLoan();return false;" value="'
	       				+ e.data[i].contractId
	       				+ '">'+e.data[i]['contractNum']+'</a>';
		   		}
		   	});
		   	
			function clickPersCust(partyId, partyNum) {
				var ps = partyId.split(",");
				partyId = ps[0];
				partyNum = ps[1];
				var url = nui.context + "/csm/natural/natural_tree.jsp?partyId="
						+ partyId + "&qote=1&partyNum=" + partyNum;
				//查看
				nui.open({
					url : url,
					showMaxButton : true,
					title : "查看客户信息",
					width : 1024,
					height : 768,
					state : "max",
					onload : function(e) {
						var iframe = this.getIFrameEl();
						var text = iframe.contentWindow.document.body.innerText;
						//alert(text);
					},
					ondestroy : function(action) {
						//queryInit();
					}
				});
			}
			
			function clickCorpCust(partyId,corpCustomerTypeCd,partyNum){
				var ps = partyId.split(",");
				partyId = ps[0];
				partyNum = ps[1];
				var url = nui.context + "/csm/corporation/csm_corporation_tree.jsp?partyId=" 
					+ partyId+"&qote=1&partyNum="+partyNum;

		        //客户类型为企业客户
		       	url += "&cusType=" + corpCustomerTypeCd;
			    nui.open({
			            url:url,
			            showMaxButton: true,
			            title: "查看客户信息",
			            width: 1024,
			            height: 768,
			            state:"max",
			            onload: function(e){
			            	var iframe = this.getIFrameEl();
			            	var text = iframe.contentWindow.document.body.innerText;
			            	//alert(text);
			            },
			            ondestroy: function (action) {
			                //search();
			            }
		      	  });	
			}
			
			//合同信息
			function goToLoan(e){
				var row=grid.getSelected();
				nui.open({
		            url:nui.context +"/pay/payout_apply/pay_tab.jsp?contractId="+row.contractId+"&partyId="+row.partyId,
		            showMaxButton: true,
		            title: "",
		            width: 1024,
		            height: 768,
		            state:"max",
		            onload: function(e){
		            	var iframe = this.getIFrameEl();
		            }
		  	 	 });	
			}
		</script>
	</body>
</html>
