<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-04-21 10:35:42
  - Description:客户进入页面
-->
<head>
<title>业务信息</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>

<div id="tabs" class="nui-tabs  bg-toolbar" activeIndex="0" tabAlign="left"
	style="width:100%;height:100%;border:0;" refreshOnClick="true">
</div>
<script type="text/javascript">
	nui.parse();
	var json = nui.encode({"partyId":"<%=request.getParameter("corpid")%>"});
        $.ajax({
            url: "com.bos.bizApply.bizApply.getPartyInfoByPartyId.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	var o = nui.decode(mydata);
				var tabs = nui.get("tabs");
				//公司客户
				if(o.party.partyTypeCd=="01"){
					tabs.setTabs([
						{title:"生效批复", url:nui.context+"/biz/biz_apply/biz_apply_ls.jsp?partyId="+o.party.partyId+"&bizHappenType=00",refreshOnClick:true},
						{title:"正常", url:nui.context+"/biz/biz_apply/biz_apply_zc.jsp?partyId="+o.party.partyId+"&bizHappenType=01&partyTypeCd="+o.party.partyTypeCd+"&certType="+o.party.certType+"&certNum="+o.party.certNum+"&bizHappenNature="+o.party.bizHappenNature,refreshOnClick:true},
						{title:"调整", url:nui.context+"/biz/biz_apply/biz_apply_tz.jsp?partyId="+o.party.partyId+"&bizHappenType=04&partyTypeCd="+o.party.partyTypeCd,refreshOnClick:true},
						{title:"循环通/续接贷", url:nui.context+"/biz/biz_apply/biz_apply_jxhj.jsp?partyId="+o.party.partyId+"&bizHappenType=06",refreshOnClick:true},
						{title:"复议", url:nui.context+"/biz/biz_apply/biz_apply_fy.jsp?partyId="+o.party.partyId+"&bizHappenType=02&partyTypeCd="+o.party.partyTypeCd,refreshOnClick:true},
						{title:"批复重新启用", url:nui.context+"/biz/biz_apply/biz_apply_qy.jsp?partyId="+o.party.partyId+"&bizHappenType=09",refreshOnClick:true},
						{title:"冻结与解冻", url:nui.context+"/biz/biz_apply/biz_apply_djjd.jsp?partyId="+o.party.partyId+"&bizHappenType=07",refreshOnClick:true},
						{title:"批复失效", url:nui.context+"/biz/biz_apply/biz_apply_sx.jsp?partyId="+o.party.partyId+"&bizHappenType=08",refreshOnClick:true}
					]);
				}else if(o.party.partyTypeCd=="06"){
					tabs.setTabs([
						{title:"生效批复", url:nui.context+"/biz/biz_apply/biz_apply_ls.jsp?partyId="+o.party.partyId+"&bizHappenType=00",refreshOnClick:true},
						{title:"正常", url:nui.context+"/biz/biz_apply/biz_apply_zc.jsp?partyId="+o.party.partyId+"&bizHappenType=01&partyTypeCd="+o.party.partyTypeCd,refreshOnClick:true},
						{title:"调整", url:nui.context+"/biz/biz_apply/biz_apply_tz.jsp?partyId="+o.party.partyId+"&bizHappenType=04&partyTypeCd="+o.party.partyTypeCd,refreshOnClick:true},
						{title:"复议", url:nui.context+"/biz/biz_apply/biz_apply_fy.jsp?partyId="+o.party.partyId+"&bizHappenType=02&partyTypeCd="+o.party.partyTypeCd,refreshOnClick:true}
					]);
				}else{
					tabs.setTabs([
						{title:"生效批复", url:nui.context+"/biz/biz_apply/biz_apply_ls.jsp?partyId="+o.party.partyId+"&bizHappenType=00",refreshOnClick:true},
						{title:"正常", url:nui.context+"/biz/biz_apply/biz_apply_zc.jsp?partyId="+o.party.partyId+"&bizHappenType=01&partyTypeCd="+o.party.partyTypeCd+"&certType="+o.party.certType+"&certNum="+o.party.certNum+"&bizHappenNature="+o.party.bizHappenNature,refreshOnClick:true},
						{title:"调整", url:nui.context+"/biz/biz_apply/biz_apply_tz.jsp?partyId="+o.party.partyId+"&bizHappenType=04&partyTypeCd="+o.party.partyTypeCd,refreshOnClick:true},
						{title:"循环通/续接贷", url:nui.context+"/biz/biz_apply/biz_apply_jxhj.jsp?partyId="+o.party.partyId+"&bizHappenType=06",refreshOnClick:true},
						{title:"复议", url:nui.context+"/biz/biz_apply/biz_apply_fy.jsp?partyId="+o.party.partyId+"&bizHappenType=02&partyTypeCd="+o.party.partyTypeCd,refreshOnClick:true},
						{title:"批复重新启用", url:nui.context+"/biz/biz_apply/biz_apply_qy.jsp?partyId="+o.party.partyId+"&bizHappenType=09",refreshOnClick:true},
						{title:"冻结与解冻", url:nui.context+"/biz/biz_apply/biz_apply_djjd.jsp?partyId="+o.party.partyId+"&bizHappenType=07",refreshOnClick:true},
						{title:"批复失效", url:nui.context+"/biz/biz_apply/biz_apply_sx.jsp?partyId="+o.party.partyId+"&bizHappenType=08",refreshOnClick:true}
					]);
				}
				$("#tabs").show();
			}
        });
</script>
</body>
</html>