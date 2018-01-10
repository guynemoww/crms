<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): zengfang
  - Date: 2014-02-28 16:34:13
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp"%>
<title>检查报告</title>
</head>
<body>
<div style="padding: 10px;">
	<div id="form1" style="width:100%;height:auto;overflow:hidden;">
		<div class="nui-dynpanel" columns="4">
			<label>客户名称</label>
			<input name="partyName" id="partyName" width="100%" class="nui-text nui-form-input"/>
			<label>客户编号</label>
			<input name="partyNum" id="partyNum" width="100%" class="nui-text nui-form-input"/>
		</div>
	</div>
	<div class="" id="btnGroup" style="border-bottom:0;text-align:left;margin-top:20px;margin-left:20px;">
		<label>请选择模板:</label>
		<div class="nui-combobox" id="reportTemplate" textField="text" valueField="id" data="norBusiReport" style="margin-right:60px;"></div>    
		 <a class="nui-button" onclick="clickDownload()">一般企业贷后检查报告下载</a>
	</div>
</div>
<iframe name="x" id="x" style="display:none;"></iframe>
	<script type="text/javascript">
		var norBusiReport=[{ id: '1', text: '绵阳银行公司客户贷后管理评价报告' }, { id: '2', text: '绵阳银行公司客户贷后检查报告（适用于一般授信业务）'}];
		nui.parse();
		var form = new nui.Form("#form1");
		var param=<%=request.getParameter("param") %>;
		var partyId = param.corpid;
		git.mask();
		
		function query(){
			var json=nui.encode({"param":param});
			 nui.ajax({
                url: "com.bos.aft.dailyInspect.queryCorpInfo.biz.ext",
                data:json,
                type:"POST",
                contentType:'text/json',
                success: function (text) {
                	form.setData(text.corpInfo);
					git.unmask();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                    git.unmask();
                }
            });		
		}
		query();
		
		function clickDownload(){
			var value=nui.get("reportTemplate").getValue();
			if(value==""){
				alert("请选择模板!");
				return;
			}
			document.getElementById('x').src="com.bos.aft.dailyInspect.downloadDailyInpsectReport.biz.ext2?template="+value+"&partyId="+partyId;
		return;
		}
	</script>
</body>
</html>