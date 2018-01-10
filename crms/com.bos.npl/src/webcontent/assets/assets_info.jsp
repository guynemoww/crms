<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): g_Yiy
  - Date: 2015-01-05 14:20:42
  - Description:
-->
<head>
<title>处置方案</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<fieldset>
  	<legend>
    	<span>基本信息</span>
    </legend>
	<div id="form" style="width:100%;height:auto;overflow:hidden;">
		<div class="nui-dynpanel" columns="4">
			<label class="nui-form-label">客户编号：</label>
			<input name="tbCsmParty.partyNum" required="false" setEnabled="flase" class="nui-text nui-form-input" vtype="maxLength:100" />
	
			<label class="nui-form-label">客户名称：</label>
			<input name="tbCsmParty.partyName" required="false" class="nui-text nui-form-input" vtype="maxLength:100" />
	
			<label class="nui-form-label">所在国家（地区）：</label>
			<input name="tbCsmParty.contryRegionCd" required="false" dictTypeId="CD000003" class="nui-text nui-form-input" vtype="maxLength:20" />
	
			<label class="nui-form-label">客户类型：</label>
			<input name="tbCsmParty.partyTypeCd"  required="false" class="nui-text nui-form-input"  dictTypeId="XD_PJCD0015"/>
		</div>
	</div>
	</fieldset>
	<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
	    	<a class="nui-button" id="btnCreate" iconCls="icon-add" onclick="cre">处置方案发起</a>
		</div>
</body>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	initPage();
	function initPage(){
			var json = nui.encode({"partyId":"<%=request.getParameter("corpid")%>"});
			$.ajax({
		        url: "com.bos.npl.assets.AssetsPerforming.getPatryInfoByPartyId.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        contentType:'text/json',
		        success: function (mydata) {
		        	var o = nui.decode(mydata);
		            form.setData(o);
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		            nui.alert(jqXHR.responseText);
		        }
	    });
	}
	
	function cre(){
		var json = nui.encode({"partyId":"<%=request.getParameter("corpid")%>"});
		$.ajax({
            url: "com.bos.npl.assets.AssetsPerforming.createDispositionScheme.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	//跳往资产保全树页面
				git.go(nui.context+"/npl/assets/assets_tree.jsp?schemeId="+mydata.tbNplDispositionScheme.schemeId+"&processInstId="+mydata.processInstId+"&flag=1",parent);
            }
        });
        
	}
</script>
</html>