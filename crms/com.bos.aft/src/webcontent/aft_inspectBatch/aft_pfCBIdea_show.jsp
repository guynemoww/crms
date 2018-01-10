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
		<div class="nui-dynpanel" columns="1">
			<label>检查意见</label>
			<input name="pfIdea" id="pfIdea" width="100%" class="nui-textarea nui-form-input" colspan="3"  value=""/>
		</div>
	
</div>
	<script type="text/javascript">
		nui.parse();
		var form = new nui.Form("#form1");
		var param=<%=request.getParameter("param") %>;
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
/*		
function modifyStatus(){
	var json=nui.encode({"param":param});
	 $.ajax({
                url: "com.bos.aft.aft_inspectBatch.modifyPfFlowStaus.biz.ext",
                data:json,
                type:"POST",
                contentType:'text/json',
                async:false,
                success: function (text) {
                    alert("success");
					
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                }
            });	
 }
 */ 
	</script>
</body>
</html>