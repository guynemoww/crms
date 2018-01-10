<%@page pageEncoding="UTF-8"%>
<html>
<%-- 
  - Author(s): zengfang
  - Date: 2014-02-13 10:16:54
  - Description:
--%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>日常检查</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<div id="form1" style="width:100%;height:auto;overflow:hidden;">
		<div class="nui-dynpanel" columns="4">
			<label>客户名称：</label>
			<input name="partyName"  class="nui-text nui-form-input" />
			<label>客户编码：</label>
			<input name="partyNum"  class="nui-text nui-form-input"/>
			<label>ECIF客户编号：</label>
			<input name="ecifPartyNum"  class="nui-text nui-form-input"/>
			<label>客户类型：</label>
			<input name="partyTypeCd"  class="nui-text nui-form-input" dictTypeId="XD_KHCD0219"/>
		</div>
	</div>
	<div class="nui-toolbar" style="text-align:right;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
  	  <a class="nui-button" iconCls="icon-addnew" onclick="add">创建贷后检查流程</a>
	</div>

<script type="text/javascript">
	nui.parse();
	var partyId="<%=request.getParameter("corpid") %>";
	var form = new nui.Form("#form1");
	var msg;
	var party;
	function init(){/* 数据加载 */
		git.mask();
		var json = nui.encode({"param":{"partyId":partyId}});
		nui.ajax({
            url: "com.bos.aft.dailyInspect.queryCorpInfo.biz.ext",
            data:json,
            type:"POST",
            contentType:'text/json',
            success: function (text) {
              	form.setData(text.corpInfo);
              	party = text.corpInfo;
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR.responseText);
            }
        }); 
        git.unmask();     			
	}
	init();
	//创建一个新的检查
	function add(){
		var json=nui.encode({"partyId":partyId,"partyNum":party.partyNum,"partyName":party.partyName});
		nui.ajax({
			url: "com.bos.aft.aft_warrant.createGuaranteeFlow.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	msg=mydata.msg;
            	node=mydata.node;
            	var bizId=mydata.bizId
            	if(msg==1){
            		var url=nui.context+"/aft/aft_warrant/aft_dailyIns_tree_show.jsp?partyId="+partyId+"&node="+nui.encode(node)+"&bizId="+bizId;
            		git.go(url);
            	}else if(msg==2){
            		nui.alert("该客户贷后检查流程还未结束!");
            		return;
            	}else if(msg==3){
            		nui.alert("出现异常!");
            		return;
            	}else if(msg==4){
            		nui.alert("创建失败！");
            		return;
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR.responseText);
            }
		});
	}
</script>
</body>
</html>