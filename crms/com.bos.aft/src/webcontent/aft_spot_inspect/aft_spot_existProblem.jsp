<%@page pageEncoding="UTF-8"%>

<html>
<!-- 
  - Author(s): zengfang
  - Date: 2014-02-28 16:34:13
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp"%>
<title>存在的主要问题</title>
</head>
<body>
<div style="padding: 10px;">
	<div id="form1" style="width:100%;height:auto;overflow:hidden;">
		<div class="nui-dynpanel" columns="2">
			<div colspan="2" style="padding:2px;"><label><span >存在的主要问题:</span></label></div>
			<div colspan="2" style="margin-top:2px"><input class="nui-textarea  nui-form-input" name="exitMainProblem"  id="exitMainProblem" width="100%" height="50px"/></div>
		</div>	
		
	<div class="" id="saveBtn" style="border-bottom:0;text-align:center;margin-top: 20px;">
		 <a class="nui-button" iconCls="icon-save" onclick="btnSave()">保存</a>
	</div>
</div>
	<script type="text/javascript">
		var param=<%=request.getParameter("param") %>;
		var callback="<%=request.getParameter("callback") %>";
		if(callback=="y"){
			$("#saveBtn").hide();
			$(".nui-textarea").attr("allowInput","false");
		}
		nui.parse();
		var form = new nui.Form("#form1");
		git.mask("form1");
		
		function query(){
			var json=nui.encode({"param":param});
			 nui.ajax({
                url: "com.bos.aft.aft_spot_inspect.querySpotInspect.biz.ext",
                data:json,
                type:"POST",
                contentType:'text/json',
                success: function (text) {
                	form.setData(text.spotInpsect);
					git.unmask("form1");
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                    git.unmask("form1");
                }
            });		
		}
		query();
		function btnSave(){/* 保存 */
			var jsonData=nui.encode({"param":param,"localeInspect":form.getData()});
			//alert(jsonData);
			nui.ajax({
                url: "com.bos.aft.aft_spot_inspect.updateLocaleInspect.biz.ext",
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
		
	</script>
</body>
</html>