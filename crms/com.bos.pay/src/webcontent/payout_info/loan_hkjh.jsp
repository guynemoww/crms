<%@page pageEncoding="UTF-8" import="commonj.sdo.DataObject"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-04-23 17:55:38
  - Description:
-->
<head>
<title>不规则还款计划</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<script type="text/javascript" src="<%=contextPath%>/biz/biz_js/biz.js"></script>
<body>
	<div id="form" style="width:99.4%;height:auto;overflow:hidden;">
		<div class="nui-dynpanel" columns="4" id="cs"></div>
		
		<!-- <div class="nui-toolbar" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px">
	    	<a class="nui-button" iconCls="icon-print" id="print" onclick="print">打印还款计划表</a>
		</div> -->
		
		<div id="hkjh" class="nui-datagrid" style="width:100%;height:450px" 
			url="com.bos.payInfo.PayInfo.getLoanHkjh.biz.ext" dataField="hkjhs"
			allowResize="true" showReloadButton="false" allowCellEdit="true" 
		    allowCellSelect="true" showPager="false"
			sizeList="[10,20,30,50,100]" multiSelect="false" pageSize="100" sortMode="client">
			<div property="columns">
				<div type="indexcolumn" headerAlign="center">期次 </div>
				<div field="forwProvDate" headerAlign="center">本期起始日期
					<input property="editor" class="nui-text" />
				</div>
				<div field="nextProvDate" headerAlign="center">本期终止日期
					<input property="editor" class="nui-text" />
				</div>
				<div field="DCurPrin" headerAlign="center">本期本金
					<input property="editor" class="nui-text" />
				</div>
				<div field="DCurItr" headerAlign="center">本期利息
					<input property="editor" class="nui-text" />
				</div>
				<div field="DTotalAmt" headerAlign="center">本期本息合计
					<input property="editor" class="nui-text" />
				</div>
			</div>
		</div>	
	</div>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var summaryNum =  "<%=request.getParameter("summaryNum") %>";
	var loanId =  "<%=request.getParameter("loanId") %>";
	loadformhkjh();
	function loadformhkjh(){
		var json = nui.decode({"summaryNum":summaryNum,"loanId":loanId});
		var grid = nui.get("hkjh");
		grid.load(json);
	}
	
	
	function print(){
		var json = nui.encode({"summaryNum":summaryNum,"loanId":loanId});
        $.ajax({
            url: "com.bos.pay.LoanSummary.printHkjhs.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	if(text.swfPath){
	        		nui.open({
						url:nui.context +"/biz/biz_report/contract_view.jsp?filePath="+text.swfPath,
						title: "还款计划表信息预览", width: 1000, height: 500,
			            onload: function () {
			            },
			            ondestroy: function (action) {
			                  grid.reload();
			            }
			
					});
	        	}else{
	        		alert(text.msg);
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