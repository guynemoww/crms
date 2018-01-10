<%@page pageEncoding="UTF-8"%> 
<html>
<!-- 
  - Author(s): 叶奔
  - Date: 2014-04-04 15:43:13
  - Description:
-->
<head>
<title>上传文件页面</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<form id="uploadFileForm" action="com.bos.pub.document.updown.upload.biz.ext2" enctype="multipart/form-data" method="post">
	<input id="bizNum" class="nui-hidden nui-form-input" name="bizNum"/>
<%--<input type="hidden" name="bizPhase" value="biz" /> --%>
	<input type="hidden" name="docType" value="06" />
<%--<input type="hidden" name="docName" value="业务申请文件" />	
	<input type="hidden" name="docNote" value="备注或描述信息" />--%>
	<input type="hidden" name="redirectPage" value="/aft/file/relevantFile.jsp?applyId=<%=request.getParameter("applyId")%>" />

<div id="form3" style="width:100%;height:auto;overflow:hidden;margin-top:23px;">
  <!--   <div style="text-align:left;font-weight: bolder;">预警事项描述（包括预警信息、来源渠道、程度等）</div> -->
  <a style="text-align: center;font-weight: bolder;">认定预警级别时，需要填写预警报告</a>
  <fieldset>
  	 <legend>
    	<span>预警事项描述（包括预警信息、来源渠道、程度等）</span>
     </legend>
     <div sytle="width:100%;margin-top:7px;"> 
         <input id="matter" class="nui-textarea nui-form-input" required="true" name="matterState" style="width:100%;" />
     </div>
   </fieldset>
   
   <fieldset>
  	 <legend>
    	<span>拟采取的控制措施及建议</span>
     </legend>
     <div sytle="width:100%;margin-top:7px;"> 
         <input id="suggestState" class="nui-textarea nui-form-input" required="true" name="suggestState" style="width:100%;" />
     </div>
   </fieldset>
</div>

<div id="toolbar" class="nui-toolbar" style="margin-top:20px;border-bottom:0;text-align:center;">
     <!-- <a class="nui-button" iconCls="icon-download" onclick="downLoadReport()"  id="downReport" style="float: right;">生成预警报告</a>	 -->
     <a class="nui-button" iconCls="icon-save" onclick="startFlow()" id="btnSave" style="float: right;">保存</a>
</div>


<!-- 	<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
		 url="com.bos.aft.common.getProDownFiles.biz.ext" dataField="tbPubDocs" allowResize="false" 
		 showReloadButton="false" sizeList="[10,20]" multiSelect="false" pageSize="5" sortMode="client">
		<div property="columns">
			<div type="checkcolumn">选择</div>
			<div field="bizNum" headerAlign="center" allowSort="true" >业务编号</div>
			<div field="docName" headerAlign="center" allowSort="true" >文档名称</div>
			<div field="createUser" headerAlign="center" allowSort="true">创建者</div>
			<div field="createTime" headerAlign="center" allowSort="true" >创建时间</div>
		</div>
	</div>
	</br>
	<input class="nui-htmlfile" id="file" name="file" limitType="" style="float: right;"/>
	<a class="nui-button" iconCls="icon-upload" onclick="uploadFile" id="biz_uploan_save" style="float: right;"/>上传</a>
	<a class="nui-button" iconCls="icon-download" onclick="downLoad"  id="biz_uploan_down" style="float: right;">下载</a>
    <a class="nui-button" iconCls="icon-remove" onclick="delData"  id="biz_uploan_del" style="float: right;">删除</a>
	<br/> -->
	 
	<iframe name="downloadFileFrame" id="downloadFileFrame" src="" style="display:none;"></iframe>
</form>

</body>			
<script type="text/javascript">
 	nui.parse();
	 	var form = new nui.Form("#uploadFileForm"); 
	 	var form3 = new nui.Form("#form3");//预警报告表单
	 	//var grid = nui.get("grid1");
	 	//var json = nui.encode({"applyId":"<%=request.getParameter("applyId")%>"});
	 	var viewFlag=<%=request.getParameter("viewFlag")%>;
	 	var button = "<%=request.getParameter("button") %>";
	 	var corpType = <%=request.getParameter("corpType") %>;//客户种类 1：小企业 2：大中企业 
	 	var party = <%=request.getParameter("party") %>;
	 	var bizId = "<%=request.getParameter("bizId") %>";
	 	var node = <%=request.getParameter("node") %>;
	 	var noEditLevel = "<%=request.getParameter("noEditLevel") %>";
	 	if(viewFlag==0){
	 		nui.get("downLoadDocument").hide();
	 	}   
	 	if(noEditLevel==1){
	 	  nui.get("toolbar").hide();
	 	  form3.setEnabled(false);
	 	}
	    
	    
	   function init(){
	   var json = nui.encode({bizId:bizId});
	   //alert(proFlag);
	    $.ajax({
            url: "com.bos.ews.util.getParty.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            async: true, //异步处理
            success: function (text) {
               var adjust = text.adjust;
	           form3.setData(adjust);
	        },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
           });
           
	    }
	    init();
	    
	    function check(){
	        var startHold = nui.get("startHold").value;
	        if(startHold==1){
	           form3.setEnabled(true);
	        }else{
	           form3.setEnabled(false);
	        }
	    }
	    
	 	function startFlow(){
	 	       nui.get("btnSave").setEnabled(false);
	 	       var result2;
	           form3.validate();
	 	       if(form3.isValid()==false){
                    alert("请完成预警报告的填写！");
                    nui.get("btnSave").setEnabled(true);
                    return;
                 }else{
                    result2 = form3.getData();                                  //获取表单数据
                 }
               var json=nui.encode({"corpid":party.partyId,"otherData":result2,"bizId":bizId});
                $.ajax({                                                         
                      url: "com.bos.ews.warnInfo.saveReport.biz.ext",
                      type: 'POST',
                      data: json,
                      cache: false,
                      contentType:'text/json',
                      success: function (text) {
                               var msg = text.msg;
                               var error = text.errorMsg;
                                   if(error){
                                      alert(error);
                                   }else{
                                      alert(msg);
                                   }
                                    
                      },
                      error: function (text) {
                      //alert("111");
                               nui.alert(text.msg);
                               git.unmask();
                             }
	                  });
	                  nui.get("btnSave").setEnabled(true);
	 	}

	            nui.get("bizNum").setValue("<%=request.getParameter("applyId")%>");
	            search();

 		function uploadFile() {
			if(!nui.get("file").getValue()){
				nui.alert("请选择文件");
				return;
			}

			var frm = document.getElementById("uploadFileForm");
			git.mask();
			frm.submit();
		}
		
        function downLoadReport(){
         nui.get("downReport").setEnabled(false);
	           form3.validate();
	 	       if(form3.isValid()==false){
                    alert("请完成预警报告的填写！");
                    nui.get("downReport").setEnabled(true);
                    return;
                 }
         var ifrm = document.getElementById("downloadFileFrame");
		 ifrm.src="com.bos.ews.warnReport.downLoadReport.biz.ext2?partyId="+party.partyId+"&bizId="+bizId;
		 alert("生成报告后，如需上传至服务器请在左侧的文档管理处上传报告！");
         nui.get("downReport").setEnabled(true);
    }
        
</script>
</html>