<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): 钟辉
  - Date: 2015-07-29 16:07:56
  - Description:
-->
<head>
<title>批复通知书</title>
</head>
<body>
	<iframe name="x" id="x" style="display:none;"></iframe>
	<div  style="width:100%;height:auto;overflow:hidden;">
		<div class="nui-toolbar" style="border-bottom:0;text-align:center;">
		<!-- <a class="nui-button" iconCls="icon-save" onclick="clickDownload()">下载</a> -->
		<a class="nui-button" iconCls="icon-search" onclick="clickDownload()">预览</a>
	</div>
	<!-- <div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	 url="com.bos.conApply.conApply.getApproveBizInfo.biz.ext" dataField="bizInfos" allowResize="true"  
	 multiSelect="false" pageSize="5" sortMode="client">	
		<div property="columns">
		<div type="checkcolumn">选择</div>
		<div type="indexcolumn">序号</div>
		<div field="contractNum" headerAlign="center"   >业务编号</div>
		<div field="partyName" headerAlign="center"  >业务性质</div>
	    <div field="bizType"  align="center" dictTypeId="XD_SXCD1038">申报模式</div>
	    <div field="bizHappenType"  align="center"  dictTypeId="XD_SXCD1039">业务产品</div> 
		<div field="productType" headerAlign="center" dictTypeId="product"  renderer="productType">业务品种</div>
		<div field="contractStatusCd" dictTypeId="XD_SXCD1106"  >申请日期</div>
	</div> -->
<script type="text/javascript">
     nui.parse();
     
     var partyId="<%=request.getParameter("partyId")%>";
     
     var bizNum="<%=request.getParameter("bizNum")%>";
     
     var applyId="<%=request.getParameter("applyId")%>";
     
     var processInstId ="<%=request.getParameter("processInstId") %>"; //流程ID
     
     var grid = nui.get("grid1");
     
     load();
     
     function load(){
     	grid.load({"partyId":partyId,"bizNum":bizNum});
     }
     
     
     //合同模板下载
	function clickDownload(){
		var json = nui.encode({"map":{"sqlName":"com.bos.bizApprove.bizApprove.getApproveInfo","applyId":applyId,"reportName":'/approve/XDZXApproveNotice.docx',"processInstId":processInstId}});
		//var json = nui.encode({"productCd":"approve","approveId":"ff8080814e48e3ec014e4917e981005e","reportName":"/approve/approve.docx","isDownload":"true"});
		$.ajax({
            url: "com.bos.biz.print.printApproveXw.biz.ext",
            type: 'POST',
            data: json,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	if(mydata.swfPath){
            		nui.open({
						url:nui.context +"/biz/biz_report/contract_view.jsp?filePath="+mydata.swfPath,
						title: "合同信息预览", width: 1000, height: 600,
			            onload: function () {
			            },
			            ondestroy: function (action) {
			                  grid.reload();
			            }
			
					});
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR.responseText);
                git.unmask();
            }
       	});	
	}
	
	function viewContract(){
		/* var contractId = nui.get("contractId").getValue();
		var conDetailId = nui.get("conDetailId").getValue();
		var productCd = nui.get("productCd").getValue();
		var json = nui.encode({"contractCheckInfo/contractId":contractId}); */
		$.ajax({
	            url: "com.bos.crt.downloanContractMain.downloanContractMain.biz.ext",
	            type: 'POST',
	            data: json,
	            contentType:'text/json',
	            cache: false,
	            success: function (mydata) {
	            	//alert("filePath:"+mydata.swfPath);
	                if(mydata.swfPath){
	            		nui.open({
							url:nui.context +"/crt/view/contract_view.jsp?filePath="+mydata.swfPath,
							//url:nui.context +"/documnetView.jsp",
							title: "批复通知书预览", width: 1000, height: 600,
				            onload: function () {
				            },
				            ondestroy: function (action) {
				                  grid.reload();
				            }
				
						});
	            	}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                alert(jqXHR.responseText);
	                git.unmask();
	            }
       	});	
	}
 </script>
</body>
</html>