<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): chenchuan
  - Date: 2016-05-05 15:43:13
  - Description:修改了传入参数，使用此页面需要传入2个参数：1：业务编号；2：业务类型（例如：csm,biz,con...）
-->
<head>
<title>上传文件页面</title>
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<form id="uploadFileForm" action="com.bos.pub.document.updown.upload.biz.ext" enctype="multipart/form-data" method="post">
	<input id="bizNum" class="nui-hidden" name="bizNum" value="<%=request.getParameter("bizNum")%>"/>
	<input id="bizPhase"class="nui-hidden"  name="bizPhase"  value="<%=request.getParameter("bizType")%>"/>
	<input id="docType"class="nui-hidden"  name="docType" value="<%=request.getParameter("bizType")%>"/>

<%--	<input type="hidden" name="docName" value="业务申请文件" />--%>
	<input class="nui-hidden" name="docNote" value="备注或描述信息" />
	<input type="hidden" name="redirectPage" value="biz/biz_info/pro_biz_upload.jsp?bizNum=<%=request.getParameter("bizNum")%>&bizType=<%=request.getParameter("bizType")%>&proFlag=<%=request.getParameter("proFlag")%>"/>
	
	<div class="nui-toolbar"style="border-top: 1px solid #8ba0bc; border-bottom: none; text-align: left; margin-top: 7px">
			<input class="nui-htmlfile" id="file" name="file" limitType=""/>
			<a class="nui-button" iconCls="icon-upload" onclick="uploadFile" id="biz_uploan_save">上传</a>
			<a class="nui-button" iconCls="icon-download" onclick="downLoad"  id="biz_uploan_down">下载</a>
			<a class="nui-button" iconCls="icon-remove" onclick="delFile"  id="biz_uploan_del">删除</a>
	</div>
	
	<div id="grid1" class="nui-datagrid" style="width:100%;height:auto"  allowAlternating="true"
		 url="com.bos.bizInfo.adviceAndFile.getProDownFiles.biz.ext" dataField="tbPubDocs" allowResize="false" 
		 showReloadButton="false" sizeList="[10,20]" multiSelect="false" pageSize="8" sortMode="client">
		<div property="columns">
			<div type="checkcolumn">选择</div>
			<div field="docName" headerAlign="center" allowSort="true" >文档名称</div>
			<div field="userNum" headerAlign="center" allowSort="true" dictTypeId="user">创建者</div>
			<div field="createTime" headerAlign="center" allowSort="true"dateFormat="yyyy-MM-dd HH:mm:ss">创建时间</div>
		</div>
	</div>
	<br/>
	<iframe name="downloadFileFrame" id="downloadFileFrame" src="" style="display:none;"></iframe>
</form>

</body>			
<script type="text/javascript">
 	nui.parse();
	 	var form = new nui.Form("#uploadFileForm"); 
	 	var grid = nui.get("grid1");
	 	var bizNum="<%=request.getParameter("bizNum")%>";
	 	var bizType="<%=request.getParameter("bizType")%>";
	 	var proFlag=<%=request.getParameter("proFlag")%>;
	  	if(bizType=="csm"){//1:查看，2：编辑
	  		if("1"==proFlag){
	 			nui.get("biz_uploan_save").hide();
	 			nui.get("biz_uploan_del").hide();
	 			$("#file").css("display","none");
	 		} 
	  	}else if(bizType=="biz"){//1:编辑，其他是查看
	  		if("-1"==proFlag){
	 			nui.get("biz_uploan_save").hide();
	 			nui.get("biz_uploan_del").hide();
	 			$("#file").css("display","none");
	 		} 
	  	}
	  	
 		search();
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
			var row=nui.get("grid1").getSelected();
			if (null == row) {
				nui.alert("请选择一条记录");
				return false;
			}
			var ifrm = document.getElementById("downloadFileFrame");
			//ifrm.src="com.bos.pub.document.updown.download.biz.ext?docId="+row.docId;
			ifrm.src="com.bos.risk.FileTransfer.flow?docId="+row.docId;
		}

		function delFile(){
			var row = grid.getSelected();
			var json = nui.encode({"docId":row.docId});
			if (row) {
				    $.ajax({
	                    url: "com.bos.bizInfo.adviceAndFile.delDownLoadFile.biz.ext",
		                type: 'POST',
		                data: json,
		                cache: false,
		                contentType:'text/json',
	                    success: function (text) {
	                    	if (text.delFlag=="0") {
	                    		nui.alert("非上传者不能删除!");
	                    		return;
	                    	}else{
	                    		nui.alert("删除成功!");
	                    		search();
	                    	}
	                    },
	                    error: function () {
	                    	nui.alert("操作失败！");
	                    }
                	});
        	} else {
            	nui.alert("请选中一条记录");
        	}
		}

</script>
</html>