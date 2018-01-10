<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<!-- 
  - Author(s): cp
  - Date: 2017-07-15 16:23:04
  - Description:
-->
<head>
<title>借据信息列表</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
	<div id="tabs1" class="nui-tabs" activeIndex="0" style="width: 100%; height: auto;">
		<div title="借据信息查询">
			<div id="form1" class="nui-form" style="width: 99.5%; height: auto; overflow: hidden;">
				<div class="nui-dynpanel" columns="6">
					<label>客户名称：</label>
					<input id="item.partyName" name="item.partyName" required="false" class="nui-textbox nui-form-input"/>
					<label>借据编号：</label>
					<input id="item.summaryNum" name="item.summaryNum" required="false" class="nui-textbox nui-form-input"/>
					<label>合同编号：</label>
					<input id="item.contractNum" name="item.contractNum" required="false" class="nui-textbox nui-form-input"/>
				</div>
				<div class="nui-toolbar" style="text-align: right; border: none">
					<a class="nui-button" iconCls="icon-search" onclick="initPage()">查询</a>
					<a class="nui-button" onclick="reset()" iconCls="icon-reset">重置</a>
				</div>
			</div>
			<div style="width: 99.5%">
				<div class="nui-toolbar" style="text-align: right; border: 0; padding-right: 0px;">
						<a class="nui-button" iconCls="icon-search" onclick="addcust()">期供</a>
						<a class="nui-button" iconCls="icon-search" onclick="allCust()">统计信息</a>
						<a class="nui-button" iconCls="icon-search" onclick="accCust()">会计分录</a>
						<a class="nui-button" iconCls="icon-search" onclick="detailCust()">交易明细</a>
						<a class="nui-button" id="printDeatil" onclick="printDeatil">对账单打印</a>
						<a class="nui-button" id="HKQD2" iconCls="icon-search" onclick="print(this)">还款清单打印</a>
						<a class="nui-button" iconCls="icon-search" onclick="printDebt()">不良贷款打印</a>
					</div>
			</div>
			<div id="datagrid1" class="nui-datagrid" style="width: 99.5%; height: auto;" sortMode="client" url="com.bos.csm.pub.ibatis.querySummaryInfo.biz.ext" dataField="items" allowResize="true" showReloadButton="false" 
			allowAlternating="true" sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
				<div property="columns">
					<div type="checkcolumn">选择</div>
					<div field="PARTY_NAME" headerAlign="center" allowSort="true" align="center" width="11%" >客户名称</div>
					<div field="CONTRACT_NUM" headerAlign="center" allowSort="true" align="center" width="11%">合同编号</div>
					<div field="SUMMARY_NUM" headerAlign="center" align="center" allowSort="true" width="11%">借据编号</div>
					<div field="BEGIN_DATE" headerAlign="center" align="center" allowSort="true" width="11%">开始日期</div>
					<div field="END_DATE" headerAlign="center" align="center" allowSort="true" width="11%">结束时间</div>
					<div field="SUMMARY_AMT" headerAlign="center" align="center" allowSort="true" width="11%">借据金额</div>
					<div field="JJYE" headerAlign="center" align="center" allowSort="true" width="11%">借据余额</div>
					<div field="USER_NUM" headerAlign="center" align="center" allowSort="true" width="11%" dictTypeId="user">经办人</div>
					<div field="ORG_NUM" headerAlign="center" align="center" allowSort="true" width="11%" dictTypeId="org">经办机构</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
    	nui.parse();
    	 var form = new nui.Form("#form1"); 
		 var grid = nui.get("datagrid1");
		 initPage();
	     function initPage(){
	    		var data = form.getData();
	       		 grid.load(data);
	    	}
    	function addcust() {
	    	var row = grid.getSelected();
	    	if(row){
		    		nui.open({
		            url: nui.context+"/csm/account/period_info.jsp?summaryNum="+row.SUMMARY_NUM,
		            showMaxButton: true,
		            title: "借据期供信息",
		            width: 1024,
		            height: 400,
		            //state:"max",
		            onload: function(e){
		            	var iframe = this.getIFrameEl();
		            	var text = iframe.contentWindow.document.body.innerText;
		            },
		            ondestroy: function (action) {
		            }
	  	 	 });
	    	}else{
	    		nui.alert("请选中一条记录");
	    	}
		}
		function reset() {
			form.reset();
			initPage();
		}
		function allCust() {
	    	var row = grid.getSelected();
	    	if(row){
		    		nui.open({
		            url: nui.context+"/csm/account/all_period_info.jsp?summaryNum="+row.SUMMARY_NUM,
		            showMaxButton: true,
		            title: "借据统计信息",
		            width: 1024,
		            height: 400,
		            //state:"max",
		            onload: function(e){
		            	var iframe = this.getIFrameEl();
		            	var text = iframe.contentWindow.document.body.innerText;
		            },
		            ondestroy: function (action) {
		            }
	  	 	 });
	    	}else{
		    	nui.alert("请选中一条记录");
	    	}
		}
		function accCust() {
	    	var row = grid.getSelected();
	    	if(row){
		    		nui.open({
		            url: nui.context+"/csm/account/acc_period_info.jsp?summaryNum="+row.SUMMARY_NUM,
		            showMaxButton: true,
		            title: "会计分录信息",
		            width: 1024,
		            height: 400,
		            //state:"max",
		            onload: function(e){
		            	var iframe = this.getIFrameEl();
		            	var text = iframe.contentWindow.document.body.innerText;
		            },
		            ondestroy: function (action) {
		            }
	  	 	 });
	    	}else{
		    	nui.alert("请选中一条记录");
	    	}
		}
		function detailCust() {
	    	var row = grid.getSelected();
	    	if(row){
		    		nui.open({
		            url: nui.context+"/csm/account/detail_period_info.jsp?summaryNum="+row.SUMMARY_NUM,
		            showMaxButton: true,
		            title: "交易明细信息",
		            width: 1024,
		            height: 400,
		            //state:"max",
		            onload: function(e){
		            	var iframe = this.getIFrameEl();
		            	var text = iframe.contentWindow.document.body.innerText;
		            },
		            ondestroy: function (action) {
		            }
	  	 	 });
	    	}else{
		    	nui.alert("请选中一条记录");
	    	}
		}
		function printDeatil(){
			var row = grid.getSelected();
			if (null == row) {
				nui.alert("请选择一笔借据");
				return false;
			}
			var json = nui.encode({"printType":"HDDY","dueNum":row.SUMMARY_NUM,"loanId":row.LOAN_ID});
	        $.ajax({
	            url: "com.bos.pay.LoanSummary.PrintCommon.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	        		if(text.swfPath){
						nui.open({
							url:nui.context +"/biz/biz_report/contract_view.jsp?filePath="+text.swfPath,
							title: "回单联和通知联预览", width: 1000, height: 500,
				            onload: function () {
				            },
				            ondestroy: function (action) {
				                 grid.reload();
				            }
						});
					}else{
						alert("无打印信息!");
					}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                nui.alert(jqXHR.responseText);
	            }
	        });
					
			}
		function printDebt(){
			var row = grid.getSelected();
			if(row){
					var json = nui.encode({"printType":"YQDK","dueNum":row.SUMMARY_NUM,"loanId":row.LOAN_ID});
        $.ajax({
            url: "com.bos.pay.LoanSummary.PrintCommon.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
        		if(text.swfPath){
					nui.open({
						url:nui.context +"/biz/biz_report/contract_view.jsp?filePath="+text.swfPath,
						title: "借款凭证信息预览", width: 1000, height: 500,
			            onload: function () {
			            },
			            ondestroy: function (action) {
			                 grid.reload();
			            }
					});
				}else{
					alert("无打印信息!");
				}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
        });
			}else{
				nui.alert("请选中一条记录");
			}
		}
		
	// 公共打印方法
	function print(printType) {
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一笔借据");
			return false;
		}
		row = nui.clone(row);
		row.PRINT_TYPE = printType.id;
		nui.open({
			url : nui.context
					+ "/csm/account/selectDate.jsp",
			title : "时间选择",
			width : 400,
			height : 340,
			showModal: true,
			onload: function () {
		       var iframe = this.getIFrameEl();
		       if(iframe&&iframe.contentWindow){
		       debugger;
		           iframe.contentWindow.setData(row);
		       }
   			 }
		});
	}
    </script>
</body>
</html>