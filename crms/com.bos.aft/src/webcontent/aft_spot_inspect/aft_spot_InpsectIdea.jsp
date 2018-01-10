<%@page pageEncoding="UTF-8"%>

<html>
<!-- 
  - Author(s): zengfang
  - Date: 2014-02-28 16:34:13
  - Description:批量检查平台客户的成员检查情况补充
-->
<head>
<%@include file="/common/nui/common.jsp"%>
<title>检查意见</title>
</head>  
<body style="margin-left:10px;">

	<div id="form1">
		<div class="nui-dynpanel" columns="5">
				<div style="text-align:left"><label>履行能力/综合评价1</label></div>                           
				<input name="pfAbility" id="pfAbility" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_DHCD0004"  colspan="4"/>
				<div style="text-align:left"><label>检查结论</label></div>
				<input name="pfConclusion" id="pfConclusion" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_DHCD0005" colspan="4" onvaluechanged="adjustment"/>
				<div style="text-align:left"><label>客户资产分类是否调整</label></div>
				<div name="pfPropertyAdjust" id="pfPropertyAdjust" class="mini-dictradiogroup nui-form-input"  dictTypeId="YesOrNo" onvaluechanged="valuechanged" id="pfPropertyAdjust" colspan="4">
				</div>
				<div style="text-align:left"><label>调整类型</label></div>	
				<input name="pfAdjustType" id="pfAdjustType" class="nui-dictcombobox nui-form-input" dictTypeId="XD_FLCD0001"  colspan="4"/>
				<div style="text-align:left"><label>调整理由</label></div>
				<input name="pfAdjustReason" width="50%" class="nui-textarea nui-form-input"  value="" id="pfAdjustReason" colspan="4"/>
	            <div style="text-align:left"><label>控制措施</label></div> 
				<input name="pfRiskcontrol" id="pfRiskcontrol" class="nui-dictcombobox nui-form-input" dictTypeId="XD_DHCD0006" colspan="4"/>
		</div>		
	</div>		

	<div class=""  id="saveBtn" style="border-bottom:0;text-align:right;margin-top: 20px;">
		 <a class="nui-button" iconCls="icon-save" onclick="btnSave()">保存</a>
		  <a href="#" onclick="clickDownload()">贷后检查报告下载</a>
	</div>

	<iframe name="x" id="x" style="display:none;"></iframe>
	<script type="text/javascript">
	 	var param=<%=request.getParameter("param") %>;
		var callback="<%=request.getParameter("callback") %>";
		var processInstId=<%=request.getParameter("processInstId") %>;
		if(callback=="y"){
			$("#saveBtn").hide();
			$(".nui-textarea").attr("allowInput","false");
			$(".nui-dictcombobox").attr("enabled","false");
			$(".mini-dictradiogroup").attr("enabled","false");
		}
		nui.parse();
		var form = new nui.Form("#form1");/* 表单 */
		function query(){
		   var json=nui.encode({"param":param});
			 nui.ajax({
                url: "com.bos.aft.aft_spot_inspect.querySpotInspect.biz.ext",
                data:json,
                type:"POST",
                contentType:'text/json',
                success: function (text) {
                	form.setData(text.spotInpsect);
					git.unmask();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                    git.unmask();
                }
            });		
		}
		query();
		
		function btnSave(){/* 保存检查信息 */
			//动态设置必输属性
			adjustment();
			//获取值并校验
			var o = form.getData();
   			form.validate();
       		if (form.isValid()==false) return;
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
		
        function valuechanged(){
			var propertyAdjust = nui.get("pfPropertyAdjust");
			var pfAdjustType = nui.get("pfAdjustType");
			var pfAdjustReason = nui.get("pfAdjustReason");
			var i = propertyAdjust.getValue();
			if(i == 1){
				pfAdjustType.setEnabled(true);
				pfAdjustReason.setEnabled(true);
			}else{
				pfAdjustType.setEnabled(false);
				pfAdjustReason.setEnabled(false);
				pfAdjustType.setValue("");
				pfAdjustReason.setValue("");
			}
		}
		
	function clickDownload(){
		document.getElementById('x').src="com.bos.aft.aft_spot_inspect.downloadSpotInspectReport.biz.ext2?sidId="+param.sidId+"&partyId="+param.partyId+"&processId="+processInstId;
		return;
	}	
	
	function adjustment(){
		var  pfConclusion = nui.get("#pfConclusion").getValue();
		//仅当检查结论为退出时，需要填写资产分类是否需要调整、控制措施等内容，否则后者非必填项
		if(null!=pfConclusion && ""!=pfConclusion && "3"==pfConclusion){
			nui.get("#pfPropertyAdjust").setRequired(true);
			nui.get("#pfAdjustType").setRequired(true);
			nui.get("#pfAdjustReason").setRequired(true);
			nui.get("#pfRiskcontrol").setRequired(true);
		}else{
			nui.get("#pfPropertyAdjust").setRequired(false);
			nui.get("#pfAdjustType").setRequired(false);
			nui.get("#pfAdjustReason").setRequired(false);
			nui.get("#pfRiskcontrol").setRequired(false);
		}
	}
	</script>
</body>
</html>