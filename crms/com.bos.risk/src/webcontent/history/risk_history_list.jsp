<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<!--
		- 分类历史调整记录
	-->
	<head>
		<%@include file="/common/nui/common.jsp" %>
	</head>
	<body>
		<center>
			<div id="form1" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;margin-top:5px;" >
				<input name="nameSqlId" id="nameSqlId"  class="nui-hidden" value="com.bos.risk.history.queryRiskHistoryList"/>
				<div id="menuField" class="nui-dynpanel" columns="4">
					<label>客户名称</label>
					<input id="item.partyName" name="item.partyName" required="false" class="nui-textbox nui-form-input" />
			
					<label>证件类型</label>
					<input id="item.certType" name="item.certType" required="false"  class="nui-dictcombobox nui-form-input" dictTypeId="CDKH0002"  allowInput="false" />
					
					<label>证件号码</label>
					<input id="item.certNum" name="item.certNum" required="false" class="nui-textbox nui-form-input" />
					
					<label>合同编号</label>
					<input id="item.contractNum" name="item.contractNum" required="false" class="nui-textbox nui-form-input" />
				</div>
				<div class="nui-toolbar" style="text-align:right;border:none" >
					<a class="nui-button" iconCls="icon-search" onclick="query()">查询</a>
	  				<a class="nui-button" iconCls="icon-reset" onclick="reset()">重置</a>
				</div>
			</div>
			<!-- 分类历史展示 -->
			<div id="datagrid" 
				class="nui-datagrid" 
				style="width:99.5%;height:auto"
				sortMode="client" 
				url="com.bos.risk.common.queryByNamedSqlWithPage.biz.ext" 
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
					<div field="partyName" headerAlign="center" allowSort="true">客户名称</div>
					<div field="certType" headerAlign="center" allowSort="true" dictTypeId="CDKH0002">证件类型</div>
					<div field="certNum" headerAlign="center" allowSort="true">证件号码</div>
					<div field="contractNum" headerAlign="center" allowSort="true">合同编号</div>
					<div field="productType" headerAlign="center" allowSort="true" dictTypeId="product">授信品种</div>
					<div field="currencyCd" headerAlign="center" allowSort="true" dictTypeId="CD000001">币种 </div>
					<div field="contractAmt" headerAlign="center" allowSort="true" dataType="currency">合同金额</div>
					<div field="conYuE" headerAlign="center" allowSort="true" dataType="currency">合同已用金额</div>
					<!-- <div field="conBalance" headerAlign="center" allowSort="true" dataType="currency">合同已用金额</div> -->
					<div field="beginDate" headerAlign="center" allowSort="true" dataType="date">合同起期</div>
					<div field="endDate" headerAlign="center" allowSort="true" dataType="date">合同止期</div>
					<div field="clsResult" headerAlign="center" allowSort="true" dictTypeId="XD_FLCD0001">分类结果</div>
				</div>
			</div>
			<div style="margin-top:10px;">
				<input class="nui-button" style="margin-right:5px;" text="查看" iconCls="icon-node" onclick="selectHistoryInfo()"/>
			</div>
		</center>

		<script type="text/javascript">
			nui.parse();
			git.mask();
			var grid = nui.get("datagrid");
			var form = new nui.Form("#form1"); 
			
			//init();
			query();
			
			function reset(){
	  			var menuField = new nui.Form("#menuField");
	  			menuField.reset();
				query();
			}
			
			function query() {
				var data = form.getData();
		        grid.load(data);
		        git.unmask();
	    	}
			
	    	function selectHistoryInfo(){
				var row = grid.getSelected();
				if(row){
			  		nui.open({
			            url: nui.context + "/risk/history/risk_history_list_detail.jsp?contractNum="+row.contractNum2,
			            title: "调整记录表", 
			            width: 800, 
		        		height: 400,
			        	allowResize:true,
			        	showMaxButton: true,
			            ondestroy: function (action) {
			                if(action=="ok"){
			                    
			                }
			            }
			        });
		        }else{
		        	alert("请选中一条记录");
		        }
	  		}
	    	
	    	function init(){
		 		git.mask();
			    var json = nui.encode({parentId:"10000"});
			     $.ajax({
			        url: "com.bos.csm.pub.getDict.getCertTypeDict.biz.ext",
			        type: 'POST',
			        data: json,
			        cache: false,
			        contentType:'text/json',
			        success: function (text) {
			            git.unmask();
			            var zuzhi = {dictid:"202",dictname:"组织机构代码证"};
			            text.levels.add(zuzhi);
			            nui.get("item.certType").setData(text.levels);
			        },
			        error: function (jqXHR, textStatus, errorThrown) {
			            git.unmask();
			            nui.alert(jqXHR.responseText);
			        }
			     });
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
