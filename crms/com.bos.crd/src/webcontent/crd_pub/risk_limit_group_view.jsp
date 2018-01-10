<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-05-26 09:00:58
  - Description:
-->
<head>
<title>品种组添加</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<center>
<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
		<input id="group.id" class="nui-hidden nui-form-input"  name="group.id" value=<%=request.getParameter("id")%> />
		<div class="nui-dynpanel" columns="6">
			<label class="nui-form-label">组名称：</label>	
			<input name="group.groupName" id="group.groupName" required="true" 	class="nui-text nui-form-input" vtype="maxLength:32"/>
		</div>
</div>		
		<div id="panel4" class="nui-panel" title="品种组信息" expanded="true" style="width:99.5%;height:auto;" showToolbar="false"
			showCollapseButton="true" showFooter="false" allowResize="false">
			<div class="nui-toolbar" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px">
			</div>
			<div>
				<div id="grid" class="nui-datagrid" style="width:100%;height:auto" 
					 url="com.bos.crdPub.riskLimitGroup.getGroupInfo.biz.ext" dataField="groupProducts" allowResize="false" 
					 multiSelect="true" pageSize="200" sortMode="client" 
					 showPager="false" showFooter="false" virtualScroll="true"
					 allowCellEdit="true" allowCellSelect="true" multiSelect="true" editNextOnEnterKey="true">

					 <div property="columns">
					  	<div field="productCd" allowSort="true" width="" dictTypeId="product">品种名称</div> 
					 </div>
					 
				</div>
			</div>
		</div>
		
		<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
		    <a class="nui-button" id="" iconCls="icon-cancel" onclick="closeWindow">关闭</a>
		</div>
</center>
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var grid = nui.get("grid");
	initPage();
	//初始化
	function initPage(){
		var json = nui.encode({"group":{"id":"<%=request.getParameter("id")%>"}});
        $.ajax({
            url: "com.bos.crdPub.riskLimitGroup.getGroupInfoView.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (mydata) {
				var o = nui.decode(mydata);
				form.setData(o);
				grid.load({"group":{"id":"<%=request.getParameter("id")%>"}});
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
        });
    }
	function closeWindow(){
		CloseWindow('ok');
	}
</script>
</body>
</html>