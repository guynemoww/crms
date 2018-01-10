<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s):曹喆
  - Date: 2014-05-22 15:43:13
  - Description:
-->
<head>
<title>上传文件页面</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<form id="uploadFileForm" action="com.bos.pub.document.updown.upload.biz.ext2" enctype="multipart/form-data" method="post">
	<input id="bizNum" class="nui-hidden nui-form-input" name="bizNum"/>
	<input type="hidden" name="bizPhase" value="biz" />
	<input type="hidden" name="docType" value="biz" />
    <input type="hidden" name="docNote" value="备注或描述信息" />
	<input type="hidden" name="redirectPage" value="/biz/biz_pro_detail_info/pro_biz_upload.jsp?partyId=<%=request.getParameter("partyId")%>" />
	<%--<input type="hidden" name="docName" value="业务申请文件" />--%>	
	
	<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
		 url="com.bos.biz.pro.ProMod.getProDownFiles.biz.ext" dataField="tbPubDocs" allowResize="false" 
		 showReloadButton="false" sizeList="[10,20]" multiSelect="false" pageSize="5" sortMode="client">
		<div property="columns">
			<div type="checkcolumn">选择</div>
			<div field="bizNum" headerAlign="center" allowSort="true" >业务编号</div>
			<div field="docName" headerAlign="center" allowSort="true" >文档名称</div>
			<div field="createUser" headerAlign="center" allowSort="true">创建者</div>
			<div field="createTime" headerAlign="center" allowSort="true" >创建时间</div>
		</div>
	</div>
	
	<input class="nui-htmlfile" id="file" name="file" limitType=""/>
	<a class="nui-button" iconCls="icon-upload" onclick="uploadFile"/>上传</a>
	<a class="nui-button" iconCls="icon-download" onclick="downLoad">下载</a>
	<br/>
	<iframe name="downloadFileFrame" id="downloadFileFrame" src="" style="display:none;"></iframe>
</form>

</body>			
<script type="text/javascript">
 	nui.parse();
	 	var form = new nui.Form("#uploadFileForm"); 
	 	var grid = nui.get("grid1");
	 	nui.get("bizNum").setValue("<%=request.getParameter("partyId")%>");
	 	
	    function search() {
			var data = form.getData(); //获取表单多个控件的数据
		    grid.load({map:data});
		}
 		function uploadFile() {
			if(!nui.get("file").getValue()){
				nui.alert("请选择文件");
				return;
			}
			//if(/\.xls?$/g.test(nui.get("file").getValue()) == false){
			//	nui.alert("请选择.xls文件");
			//	return;
			//}
			var frm = document.getElementById("uploadFileForm");
			git.mask();
			frm.submit();
		}
		function downLoad(){
			var row=nui.get("grid1").getSelected();
			if (null == row) {
				nui.alert("请选择一条记录");
				return false;
			}
			var ifrm = document.getElementById("downloadFileFrame");
			ifrm.src="com.bos.pub.document.updown.download.biz.ext2?docId="+row.docId;
		}

</script>
</html>