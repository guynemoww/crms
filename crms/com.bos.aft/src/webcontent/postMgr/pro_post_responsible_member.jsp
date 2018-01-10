<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2014-07-18 16:37:03
  - Description:
-->
<head>
<title>岗位责任管理体系</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<center>
<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
		<div id="panel4" class="nui-panel" title="成员信息" expanded="true"
			style="width:99.5%;height:auto;" showToolbar="false"
			showCollapseButton="true" showFooter="false" allowResize="false">
			<div>
				<div id="grid" class="nui-datagrid" style="width:100%;height:auto" 
					 url="com.bos.aft.choosePost.getPost.biz.ext" dataField="posts" allowResize="false" 
					 showReloadButton="false" multiSelect="true" pageSize="200" sortMode="client" 
					 showPager="false" showFooter="false" virtualScroll="true">
					<div property="columns">
						<div type="checkcolumn">选择</div>
						<div field="responsiblePersonId" headerAlign="center" allowSort="false">工号</div>
						<div field="empname" headerAlign="center" allowSort="false">姓名</div>
						<div field="posiname" headerAlign="center" allowSort="false">岗位名称</div>
						<div field="orgname" headerAlign="center" allowSort="false">机构名称</div>
					</div>
				</div>
			</div>
			<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
		    	<a class="nui-button" id="biz_meeting_add" iconCls="icon-add" onclick="add">添加</a>
		    	<a class="nui-button" id="biz_meeting_remove" iconCls="icon-add" onclick="del">删除</a>
			</div>
		</div>
		
		<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
		    <a class="nui-button" id="biz_meeting_save" iconCls="icon-add" onclick="close">确定</a>
		</div>
</div>
</center>
<script type="text/javascript">
	nui.parse();
	var grid = nui.get("grid");
	var bizId = "<%=request.getParameter("bizId") %>";
	init();
	//初始化
	function init(){
		
	    grid.load({"responsiblePersonType":"8","bizId":bizId});
	       
    }
	//添加成员
	function add(){
		var infourl = nui.context+"/aft/postMgr/pro_post_responsible_selectUser.jsp?bizId="+bizId+"&responsiblePersonType=8";
		nui.open({
            url: infourl,
            showMaxButton: true,
            title: "提示：可点击最大化按钮放大此窗口",
            width: 950,
            height: 500,
            ondestroy: function(e) {
            	init();
            }
        })
	}
	//删除成员
	function del(){
		var rows = grid.getSelecteds();
		if (null == rows) {
			nui.alert("请选择要删除的成员");
			return false;
		}
		var json = nui.encode({"posts":rows});
        $.ajax({
            url: "com.bos.aft.choosePost.delPost.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (mydata) {
                alert("删除成功！");
                init();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
        });

	}
	//保存会议信息
	function close(){
		CloseWindow("ok");
	}
</script>
</body>
</html>