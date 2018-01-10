<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2014-05-28 10:41:26
  - Description:
-->
<head>
<title>模板节点树</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input id="tbBizAmountApprove.amountId" class="nui-hidden nui-form-input" name="tbBizAmountApprove.amountId"/>
			<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
				 url="com.bos.biz.pro.ProMod.getDocumentInfo.biz.ext" dataField="documentInfos" allowResize="false" 
				 showReloadButton="false" sizeList="[10,20]" multiSelect="false" pageSize="5" sortMode="client">
				<div property="columns">
					<div type="checkcolumn">选择</div>
					<div field="documentName" headerAlign="center" allowSort="true" >模板名称</div>
				</div>
			</div>
	</div>
	<div class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;"  borderStyle="border:0;">
    	<%--<a class="nui-button" iconCls="icon-upload" onclick="uploadFile"/>上传</a>--%>
		<a class="nui-button" iconCls="icon-download" onclick="downLoad">下载</a>
	</div>
	<iframe name="downloadFileFrame" id="downloadFileFrame" src="" style="display:none;"></iframe>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form1");
	var grid1 = nui.get("grid1");
	grid1.load({"applyId":"<%=request.getParameter("applyId")%>","documentType":"<%=request.getParameter("documentType")%>"});
	function downLoad(){
		var row=nui.get("grid1").getSelected();
		if (null == row) {
			nui.alert("请选择一条记录");
			return false;
		}
		var json1 = nui.encode({"applyId":"<%=request.getParameter("applyId")%>","reportType":row.documentNum,"custFlag":row.custFlag});
		$.ajax({
		          url: "com.bos.biz.report.reportCalls.findReport.biz.ext",
		          type: 'POST',
		          data: json1,
		          cache: false,
		          contentType:'text/json',
		          cache: false,
		          success: function (mydata) {
		              if(mydata.msg !=null &&mydata.msg !=""){
		              	nui.alert(mydata.msg);
		              }else{
		              	var ifrm = document.getElementById("downloadFileFrame");
						ifrm.src="com.bos.biz.report.reportCalls.report.biz.ext2?applyId=<%=request.getParameter("applyId")%>&reportType="+row.documentNum+"&documentType=<%=request.getParameter("documentType")%>";
		              }
		           },
		           error: function (jqXHR, textStatus, errorThrown) {
		           nui.alert("提交失败");
		          }
		 });
		
	}
</script>
</body>
</html>