<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2014-05-07 17:29:57
  - Description:
-->
<head>
<title>选择协办客户经理</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<center>
<div id="panel4" class="nui-panel" title="选择协办客户经理" expanded="true"
			style="width:99.5%;height:auto;" showToolbar="false"
			showCollapseButton="true" showFooter="false" allowResize="false">
		<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
				<div class="nui-dynpanel" columns="6">
					<label>工号：</label>
					<input name="userNum" class="nui-textbox nui-form-input"/>
					<label>用户姓名：</label>
					<input name="userName" class="nui-textbox nui-form-input"/>
					<label>机构名称：</label>
					<input name="orgName" class="nui-textbox nui-form-input"/>
				</div>
		</div>
		<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
		    <a class="nui-button"  iconCls="icon-search" onclick="query">查询</a>
		    <%--<a class="nui-button" iconCls="icon-reset" onclick="reset">重置</a>--%>
		</div>
		<div id="grid" class="nui-datagrid" style="width:99.5%;height:auto" 
			 url="com.bos.bizInfo.adviceAndFile.getSurveyreportSugMember.biz.ext" dataField="users" allowResize="false" 
			 showReloadButton="false" multiSelect="false" pageSize="10" sortMode="client" 
			 showPager="true" showFooter="false" virtualScroll="true">
			<div property="columns">
				<div type="checkcolumn">选择</div>
				<div field="EMPNAME" headerAlign="center" allowSort="false">成员名称</div>
				<div field="USERID" headerAlign="center" allowSort="false">成员编号</div>
				<div field="ORGNAME" headerAlign="center" allowSort="false">机构名称</div>
			</div>
		</div>
	<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
    	<a class="nui-button" id="btnCreate" iconCls="icon-add" onclick="add">确认</a>
	</div>
</div>
</center> 
<script type="text/javascript">
	nui.parse();
	var grid = nui.get("grid");
	var selectType = '<%=request.getParameter("selectType")%>';//1-协办  2-第一责任人
	var data;
	//查询
	function query(){
       	var form = new nui.Form("#form");
		var o = form.getData();
		//var json=nui.encode(o);
      	grid.load(o);
	}
	query();
	//子页面调用方法
	function GetData(){
		var row = grid.getSelected();
		data = row;
		return data;
	}
	
	function add(){
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一条记录");
			return false;
		}
    	var json = nui.encode({"sugReport":{"bizId":"<%=request.getParameter("bizId")%>","xbNum":row.USERID,"xbName":row.EMPNAME,"xbOrgNum":row.ORGCODE,"xbOrgName":row.ORGNAME}});
		if('2' == selectType){
			json = nui.encode({"sugReport":{"bizId":"<%=request.getParameter("bizId")%>","firstResponseNum":row.USERID,"firstResponseName":row.EMPNAME}});
		}
		$.ajax({
            url: "com.bos.bizInfo.adviceAndFile.saveSurveyreportSug.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	if("0"!=mydata.msg){
            		alert(mydata.msg);
            		return;
            	}
            	CloseWindow("ok"); 
            }
        });
		
		return;
	}
</script>
</body>
</html>
