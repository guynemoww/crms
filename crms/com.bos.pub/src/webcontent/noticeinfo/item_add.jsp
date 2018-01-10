<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): 
  - Date: 2013-11-21
  - Description:TB_PUB_MARKET_INFO, com.bos.pub.sys.TbPubMarketInfo
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>

<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<!--<input type="hidden" name="item" class="nui-hidden" />
	<input type="hidden" name="item._entity" value="com.bos.pub.sys.TbPubNotice" class="nui-hidden" />  -->
	<input name="item.infoId"   class="nui-hidden" id="item.infoId"/>
	
	<div class="nui-dynpanel" columns="4" id="grid">
		<label>公告标题：</label>
		<input name="item.infoTitle" id="item.infoTitle" required="true" class="nui-textbox nui-form-input" vtype="maxLength:200" />

		<label>公告适用范围：</label>
		<input name="item.infoRangeCd" id="item.infoRangeCd" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="pub_market_info_range" emptyText="请选择"/>

		<label>公告类型：</label>
		<input colspan="3" name="item.infoType" id="item.infoType" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="pub_notice_info_type" emptyText="请选择"/>

		<label>公告内容：</label>
		<input colspan="3" name="item.infoContent" id="item.infoContent" required="true" class="nui-textarea nui-form-input" vtype="maxLength:4000" 
			style="width:400px;height:200px;" />
	</div>
</div>

<form id="uploadFileForm" action="com.bos.pub.grantManage.grantManage.flow" enctype="multipart/form-data" method="post" >
	<input class="nui-hidden" id="viewUrl"  name="viewUrl" value="/pub/noticeinfo/item_add.jsp" />
	<input id="bizNum" class="nui-hidden" name="bizNum" />
	<input type="hidden" name="bizPhase" value="notice" />
	<input type="hidden" name="docType" value="notice" />	
	<input type="hidden" name="docNote" value="备注或描述信息" />
	<input type="hidden" name="redirectPage" id="redirectPage" value="/pub/noticeinfo/item_add.jsp?itemId="/>
	
	<div style="padding-top:5px;padding-bottom:2px;padding-left:165px" borderStyle="border:0;">
	<input class="nui-htmlfile" id="file" name="file" limitType="*.*"/>
	<a class="nui-button" iconCls="icon-upload" onclick="uploadFile" id="biz_uploan_save"/>上传附件</a>
	<a class="nui-button" iconCls="icon-download" onclick="downLoad"  id="biz_uploan_down">下载附件</a>
<%--	<a class="nui-button" iconCls="icon-remove" onclick="delFile"  id="biz_uploan_del">删除</a>--%>
	</div>


<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
		 url="com.bos.pub.noticeinfo.pubDoc.biz.ext" dataField="tbPubDocs" allowResize="false" 
		 showReloadButton="false" sizeList="[10,20]" multiSelect="false" pageSize="5" sortMode="client">
		<div property="columns">
			<div type="checkcolumn">选择</div>
			<%--<div field="BIZNUM" headerAlign="center" allowSort="true" >编号</div>--%>
			<div field="DOCNAME" headerAlign="center" allowSort="true" >文档名称</div>
			<div field="USERNUM" headerAlign="center" allowSort="true" dictTypeId="user">创建者</div>
			<div field="CREATETIME" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">创建时间</div>
		</div>
</div>
	<iframe name="downloadFileFrame" id="downloadFileFrame" src="" style="display:none;"></iframe>
	
</form>
<div class="nui-toolbar" style="border-bottom:0;padding-right:20px;text-align:right;margin-top:15px;">
	<a class="nui-button" iconCls="icon-save" onclick="save()">保存</a>
	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
</div>		    
			
 <script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1");
    var form2=new nui.Form("#uploadFileForm");
    var grid= nui.get("grid1");
    //显示上传附件的信息
	function search() {
			var data = form2.getData(); //获取表单多个控件的数据
			var jsonMap = {"map":{"bizNum":"<%=request.getAttribute("itemId")%>","bizPhase":"notice","docType":"notice"}}
		    var json = nui.encode(jsonMap);
		    grid.load(jsonMap);
		}

    function save() {
    
		form.validate();
		if (form.isValid() == false) {
			nui.alert("请将信息填写完整");
			return;
		}
		var o=form.getData();
		o.item.orgNum=nui.userOrgId;
		o.item.userNum=nui.userId;
		o.item.infoStatus="0";
		var json=nui.encode(o);
		$.ajax({
	            url: "com.bos.pub.noticeinfo.addItem.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	                nui.encode(text);
	                nui.get("item.infoId").setValue(text.notice.infoId);
	                 nui.get("bizNum").setValue(text.notice.infoId);
	            	if(text.msg){
	            		nui.alert(text.msg); 
	            	}else{
	            		CloseWindow("ok");
	            	}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                nui.alert(jqXHR.responseText);
	            }
		});
	}
	//刷新页面之后，加载数据
     function initForm() {
	 var json=nui.encode({"item":{"infoId":"<%=request.getAttribute("itemId") %>",
		"_entity":"com.bos.pub.sys.TbPubNotice"}});
	 $.ajax({
            url: "com.bos.pub.noticeinfo.getItem.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
             nui.get("bizNum").setValue("<%=request.getAttribute("itemId") %>");
            	if(text.msg){
            		nui.alert(text.msg);
            	} else {
            		form.setData(text);
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
	});
}
   if("<%=request.getAttribute("itemId")%>"!="null"&&"<%=request.getAttribute("itemId") %>"!=""){
      initForm();
      search();
   }
     

       //附件上传
 		function uploadFile() {
			
			if(!nui.get("file").getValue()){
				nui.alert("请选择文件");
				return;
			}
			
			var infoId = nui.get("item.infoId").getValue();
			if(null !=infoId && ''!=infoId){
			
				var frm = document.getElementById("uploadFileForm");
				git.mask();
				frm.action = "com.bos.sys.noticeUpload.flow?_eosFlowAction=upload";
				frm.submit();
			}else{
				nui.alert("请先点击保存!");
			}
		}
		 //附件下载
		function downLoad(){
			var row=nui.get("grid1").getSelected();
			if (null == row) {
				nui.alert("请选择一条记录");
				return false;
			}
			var frm = document.getElementById("uploadFileForm");
			frm.action = "com.bos.sys.noticeUpload.flow?_eosFlowAction=down&docId="+row.DOCID;
			frm.submit();
			//var ifrm = document.getElementById("downloadFileFrame");
			//ifrm.src="com.bos.pub.document.updown.download.biz.ext?docId="+row.DOCID;
		}
		

		function CloseWindow(action) {            
			window.CloseOwnerWindow("ok");
	    }
	</script>
</body>
</html>
