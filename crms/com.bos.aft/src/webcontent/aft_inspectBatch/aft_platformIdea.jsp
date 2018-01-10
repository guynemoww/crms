<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): zengfang
  - Date: 2014-02-28 16:34:13
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp"%>
<title>检查意见</title>
</head>
<body>
<div style="padding: 10px;">
    <div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<!-- 
		<input type="hidden" name="item._entity" value="com.bos.dataset.csm.TbCsmAptitudeInfo" class="nui-hidden" />
		<input name="item.partyId" id="item.partyId" class="nui-hidden" />
	 -->

	    <fieldset>
		      <legend>
		         <span>检查意见</span>
		      </legend>

		      <input name="pfIdea" id="pfIdea" style="width:100%;" class="nui-textarea nui-form-input" colspan="3"  value=""/>
        </fieldset>

	    <div class="" id="btnGroup" style="border-bottom:0;text-align:right;margin-top: 20px;">
		      <a class="nui-button" iconCls="icon-ok" onclick="checkSubmit">批量创建检查任务</a>
		      <a class="nui-button" iconCls="icon-save" onclick="btnSave()">临时保存</a>
	    </div>
	    <!-- <a href="#" onclick="clickDownload()">平台贷后检查报告下载</a> -->
     </div>
</div>
<iframe name="x" id="x" style="display:none;"></iframe>
	<script type="text/javascript">
		nui.parse();
		var form = new nui.Form("#form1");
		var param=<%=request.getParameter("param") %>;
		if("<%=request.getParameter("callback") %>"=="y"){
			$("#btnGroup").hide();
		}
		//nui.parse();
		git.mask();
		
		function query(){
			var json=nui.encode({"param":param});
			 nui.ajax({
                url: "com.bos.aft.aft_inspectBatch.queryPfCorpIdea.biz.ext",
                data:json,
                type:"POST",
                contentType:'text/json',
                success: function (text) {
                	form.setData(text.pfCorpIdea);
					git.unmask();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                }
            });		
		}
		query();
		
		function checkSubmit(){
		var pfIdea = nui.get("pfIdea").getValue();
		if(pfIdea){
		   btnSubmit();
		}else{
		  alert("检查意见不能为空！");
		return;
		}
		
		
		
		
		}
		function btnSubmit(){/* 批量创建检查任务 */
			git.mask();
			var jsonCreate=nui.encode({"param":param});
			//alert(jsonData);
			nui.ajax({
                url: "com.bos.aft.aft_inspectBatch.createInspectBatch.biz.ext",
                data:jsonCreate,
                type:"POST",
                contentType:'text/json',
                success: function (text) {
                	git.unmask();
                	nui.alert(text.msg);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                	git.unmask();
                    alert(jqXHR.responseText);
                }
            });	
            	
		}
		function btnSave(){/* 临时保存 */
			var jsonData=nui.encode({"param":param,"formData":form.getData()});
			//alert(jsonData);
			nui.ajax({
                url: "com.bos.aft.aft_inspectBatch.updatePfCorpIdea.biz.ext",
                data:jsonData,
                type:"POST",
                contentType:'text/json',
                success: function (text) {
                	nui.alert(text.msg);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                }
            });		
		}
		
		function clickDownload(){
		document.getElementById('x').src="com.bos.aft.aft_inspectBatch.downloadPlatformReport.biz.ext2?pfId="+param.pfId+"&partyId="+param.partyId;
		return;
	}
	</script>
</body>
</html>