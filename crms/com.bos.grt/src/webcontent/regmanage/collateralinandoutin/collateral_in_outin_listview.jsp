<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): ZhuYongLun
  - Date: 2014-04-03 09:20:03
  - Description:
-->
<head>
	<%@include file="/common/nui/common.jsp" %>
	<title>押品入库信息</title>
</head>
<body>
	<div id="form1" style="width:100%;height:auto;overflow:hidden;">
		<input type="hidden" name="incoolinfo" class="nui-hidden" />
	</div>

	<div id="grid1" class="nui-datagrid" style="width:100%;height:90%" 
		url="com.bos.grt.regmanage.collateralinandoutin.queryInCoolInfoList.biz.ext"
		dataField="incoolinfos" allowResize="false" showReloadButton="false" 
		sizeList="[10,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
		<div property="columns">
			<div type="indexcolumn" id="chooise" ></div>
			<div field="partyName" headerAlign="center" allowSort="true" >抵质押人名称</div>
			<div field="sortType" headerAlign="center" allowSort="true" dictTypeId="XD_DBCD4002">押品类别</div>
			<div field="cardType" headerAlign="center" allowSort="true" dictTypeId="YP_GLCD0200">权证类型</div>
			<div field="registerCertino" headerAlign="center" allowSort="true">权利证明号</div>
			<div field="cardState" headerAlign="center" allowSort="true" dictTypeId="YP_GLCD0008">权证状态</div>
			<div field="mailerNum" headerAlign="center" allowSort="true" width="180px">信封编号</div>
        </div>
	</div>	
	
	<%if(request.getParameter("activityDefId")!=null && (request.getParameter("activityDefId").equals("P1001")||request.getParameter("activityDefId").equals("P2001")||request.getParameter("activityDefId").equals("P3001")||
	     request.getParameter("activityDefId").equals("P4001")||request.getParameter("activityDefId").equals("P5001"))){%>
		<div class="nui-toolbar" style="border-bottom:0;text-align:center;">
			<a class="nui-button" iconCls="icon-ok" onclick="RuKuCancel()">撤销</a>
		</div>
	<%}%>
	
<script type="text/javascript">
	nui.parse();
	var grid = nui.get("grid1");
	var form = nui.get("#form1");
	var bizId = "<%=request.getParameter("bizId")%>";
		
	function search(){
		var data  = {"incoolinfo":{"laidUpId":bizId}};
		var json = nui.decode(data);
		grid.load(json);
	}
	search();
	    
	/**
	 * 打印
	 */
	function grtprint(){
		//?contractId="+contractId
		nui.open({
			url: nui.context+"/grt/grtprint/grtprint_print.jsp?bizId="+bizId,
			title: "已提交入库申请的押品信息", 
			width: 1100,
       		height: 500,
			allowResize:false,
			allowDrag: false,
			showCloseButton: true,
       		showMaxButton: false,
			ondestroy: function (action) {
				if(action=="ok"){
					grid.reload();
				}
			}
		});
	}
	
	//入库撤销
	function RuKuCancel(){
	   var laidUpId = "<%=request.getParameter("bizId")%>";
		nui.confirm("确定入库撤销吗？","确认",function(action){
			if(action!="ok") return;
			var json=nui.encode({"laidUpId":laidUpId});
			$.ajax({
				url: "com.bos.grt.regmanage.collateralinandoutin.cancleRuKuFlow.biz.ext",
				type: 'POST',
				data: json,
				cache: false,
				contentType:'text/json',
				success: function (text) {
				   nui.alert(text.msg,"提示",function(action){
    					var w=self.parent ? self.parent : self;
    					w.location.replace("<%=contextPath %>/csm/workdesk/mywork.jsp");
  	 			  }); 
				},
				error: function (jqXHR, textStatus, errorThrown) {
						nui.alert(jqXHR.responseText);
				 }
			});
		});
	}
</script>
</body>
</html>