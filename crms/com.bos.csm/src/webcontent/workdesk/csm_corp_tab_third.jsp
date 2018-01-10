<%@page pageEncoding="UTF-8"%>
<html>
<head>
<title>客户信息</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div class="nui-fit" style="padding:5px;">
<div id="tabs" class="nui-tabs  bg-toolbar" activeIndex="0" tabAlign="right"
	style="width:100%;height:100%;border:0;" refreshOnClick="true"></div>
</div>
<script type="text/javascript">
	nui.parse();
	var partyId = '<%=request.getParameter("corpid")%>';
	var json=nui.encode({"partyId":partyId});
	$.ajax({
            url: "com.bos.pub.common.getCustType.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            async: false,
            contentType:'text/json',
            success: function (text) {
            	if(null != text.item){
            		var url;
            		var partyTypeCd = text.item.partyTypeCd;
            		var partyNum = text.item.partyNum;
            		var custType  = text.item.custType;
            		var naturalType = text.item.naturalType;
            		var thirdPartyTypeCd = text.item.thirdCustTypeCd
            		
            		//对公客户
            		if("01"==partyTypeCd){
            			
            			url = nui.context + "/csm/corporation/csm_corporation_tree.jsp?partyId="+partyId+"&qote=1&cusType=="+custType+"&partyNum="+partyNum;
            		}else if("02"==partyTypeCd){
            			//自然人
            			url = nui.context +  "/csm/natural/natural_tree.jsp?partyId="+partyId+"&qote=1&partyNum="+partyNum;
            		}else if("04"==partyTypeCd){
            			
            			url= nui.context + "/csm/guar/guarGroup_tree.jsp?partyId="+partyId + "&qote=1&partyNum=" + partyNum;

            		}else if("05"==partyTypeCd){
            			
            			url = nui.context + "/csm/financialinstitution/csm_financialinstitution_tree.jsp?partyId="+ partyId+"&qote=1&partyNum="+partyNum;

            		}else if("06"==partyTypeCd){
            			
            			url = nui.context + "/csm/company/groupCompany_tree.jsp?partyId="+partyId + "&qote=1&partyNum=" + partyNum;

            		}else if("07"==partyTypeCd){
            			
            			url = nui.context+ "/csm/thirdParty/csm_thirdParty_tree.jsp?partyId="+partyId + "&qote=1";
            		}
					var tabs = nui.get("tabs");
					if(thirdPartyTypeCd =='1'){
						tabs.setTabs([
							{title:"客户信息", url:url, showCloseButton:false},
							{title:"评级信息", url:nui.context+"/irm/irm_apply/irm_apply.jsp?corpid="+partyId,refreshOnClick:true},
							{title:"额度信息", url:nui.context+"/crd/crd_apply/crd_apply_third.jsp?corpid="+partyId,refreshOnClick:true},
							{title:"合同签署", url:nui.context+"/crt/con_apply/con_apply_dbht.jsp?corpid="+partyId,refreshOnClick:true},
							{title:"客户预警", url:nui.context+"/ews/warnDetail/ews_warn_main.jsp?corpid="+partyId+"&type=07",refreshOnClick:true}
						]);
					}else if(thirdPartyTypeCd =='4'){//委托方（合作项目额度需求变更）
						if("01"==partyTypeCd){//公司客户才有额度申请
							tabs.setTabs([
								{title:"客户信息", url:url, showCloseButton:false},
								{title:"评级信息", url:nui.context+"/irm/irm_apply/irm_apply.jsp?corpid="+partyId,refreshOnClick:true},
								{title:"额度信息", url:nui.context+"/crd/crd_apply/crd_apply_third.jsp?corpid="+partyId,refreshOnClick:true},
								{title:"客户预警", url:nui.context+"/ews/warnDetail/ews_warn_main.jsp?corpid="+partyId+"&type=07",refreshOnClick:true}
							]);
						}else{
							tabs.setTabs([
								{title:"客户信息", url:url, showCloseButton:false},
								{title:"评级信息", url:nui.context+"/irm/irm_apply/irm_apply.jsp?corpid="+partyId,refreshOnClick:true},
								{title:"客户预警", url:nui.context+"/ews/warnDetail/ews_warn_main.jsp?corpid="+partyId+"&type=07",refreshOnClick:true}
							]);
						}
					}else{
						tabs.setTabs([
							{title:"客户信息", url:url, showCloseButton:false},
							{title:"评级信息", url:nui.context+"/irm/irm_apply/irm_apply.jsp?corpid="+partyId,refreshOnClick:true},
							{title:"额度信息", url:nui.context+"/crd/crd_apply/crd_apply_third.jsp?corpid="+partyId,refreshOnClick:true},
							{title:"客户预警", url:nui.context+"/ews/warnDetail/ews_warn_main.jsp?corpid="+partyId+"&type=07",refreshOnClick:true}
						]);
					}
					
					$("#tabs").show();
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
	});
</script>
</body>
</html>
