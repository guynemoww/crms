<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2014-12-22 12:11:34
  - Description:
-->
<head>
<title>相关文档</title>
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<%@include file="/common/nui/common.jsp"%>
</head>
<body>

<form id="uploadFileForm" action="com.bos.pub.document.updown.upload.biz.ext2" enctype="multipart/form-data" method="post">
	<input id="bizNum" class="nui-hidden nui-form-input" name="bizNum"/>
	<input id="bizPhase" type="hidden" name="bizPhase" value="npl" />
	<input id="docType" type="hidden" name="docType" value="npl" />
<%--	<input type="hidden" name="docName" value="业务申请文件" />--%>	
	<input type="hidden" name="docNote" value="备注或描述信息" />
	<input type="hidden" name="redirectPage" value="/npl/assets/documents.jsp?schemeId=<%=request.getParameter("schemeId")%>" />
	
	<input class="nui-htmlfile" id="file" name="file" limitType=""/>
	<a class="nui-button" iconCls="icon-upload" onclick="uploadFile" id="biz_uploan_save"/>上传</a>
	<a class="nui-button" iconCls="icon-download" onclick="downLoad"  id="biz_uploan_down">下载</a>
	
	<div id="grid" class="nui-datagrid" style="width:100%;height:auto" 
		 url="com.bos.npl.assets.Document.getProDownFiles.biz.ext" dataField="tbPubDocs" allowResize="false" 
		 showReloadButton="false" sizeList="[10,20]" multiSelect="false" pageSize="5" sortMode="client">
		 <div property="columns">
			<div type="checkcolumn">选择</div>
			<div field="bizNum" headerAlign="center" allowSort="true" >业务编号</div>
			<div field="docName" headerAlign="center" allowSort="true" >文档名称</div>
			<div field="userNum" headerAlign="center" allowSort="true" dictTypeId="user">创建者</div>
			<div field="createTime" headerAlign="center" allowSort="true" >创建时间</div>
		 </div>
	</div>
	</br>
	
	<br/>
	<iframe name="downloadFileFrame" id="downloadFileFrame" src="" style="display:none;"></iframe>
</form>
<script type="text/javascript">
 	nui.parse();
 	var form = new nui.Form("#uploadFileForm"); 
 	var grid = nui.get("grid");
 	query();
 	function query(){
	 	var json = nui.encode({"schemeId":"<%=request.getParameter("schemeId")%>"});
	    $.ajax({
	        url: "com.bos.npl.assets.AssetsDispositionScheme.getDispositionScheme.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
	        contentType:'text/json',
	        cache: false,
	        success: function (mydata) {
	            var o = nui.decode(mydata);
	            if(""==o.tbNplDispositionScheme.schemeNum || null==o.tbNplDispositionScheme.schemeNum){
	            	alert("请先保存方案申请信息");
	            	return;
	            }
	            nui.get("bizNum").setValue(o.tbNplDispositionScheme.schemeNum);
	            search();
	        }
	    });
    }
    function search() {
		var data = form.getData(); //获取表单多个控件的数据
	    grid.load({map:data});
	}
 	function uploadFile() {
		if(!nui.get("file").getValue()){
			nui.alert("请选择文件");
			return;
		}
		var b = nui.get("file").getValue().lastIndexOf(".")- nui.get("file").getValue().lastIndexOf("\\");
		if(b>26){
			nui.alert("上传文件名称过长！");
			return;
		}
		var frm = document.getElementById("uploadFileForm");
		git.mask();
		frm.submit();
	}
	function downLoad(){
		var row=nui.get("grid").getSelected();
		if (null == row) {
			nui.alert("请选择一条记录");
			return false;
		}
		var ifrm = document.getElementById("downloadFileFrame");
		ifrm.src="com.bos.pub.document.updown.download.biz.ext2?docId="+row.docId;
	}
</script>
</body>
</html>