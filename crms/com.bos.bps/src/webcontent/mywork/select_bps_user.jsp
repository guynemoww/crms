<%@page pageEncoding="UTF-8" import="com.bos.bps.util.CommonUtil,com.eos.data.datacontext.IUserObject"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<!-- 
  - Author(s): YANGZHOU
  - Date: 2013-03-01 17:43:27
  - Description:
-->
<%@include file="/common/nui/common.jsp" %>
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/utp/tools/icons/icon.css"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>组织机构树</title>
</head>
<body>
<style>
	#tree .mini-grid-viewport{
		background-color:transparent !important;
	}
	#tree  .mini-panel-viewport{
		background-color:transparent !important;
	}
	#orgTabs .mini-tabs-bodys{
		padding:0px;
	}
</style>
<%
IUserObject user = CommonUtil.getIUserObject();
String orgdegree =(String)user.getAttributes().get("orgdegree");
 %>
<div id="layout1" class="nui-layout" style="width:100%;height:88%;">
	<div title="机构管理" allowResize="false">
		<ul id="tree1" class="nui-tree" style="width:100%;padding:5px;" 
		    showTreeIcon="true" showExpandButtons="false" expandOnLoad="true" textField="nodeName" idField="nodeId"
		    >
		</ul>
	</div>
	
</div>
 <div class="nui-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" 
        borderStyle="border-left:0;border-bottom:0;border-right:0;">
        <a class="nui-button" style="width:60px;" iconCls="icon-ok" onclick="onOk()">确定</a>
        <span style="display:inline-block;width:25px;"></span>
        <a class="nui-button" style="width:60px;" iconCls="icon-cancel" onclick="onCancel()">取消</a>
</div>


 
 
<script type="text/javascript">
	nui.parse();
	//flag=2 表示代办人员选择（单选）
	var flag = '<%=request.getParameter("flag") %>';
	
	function getInit() {
		var tree = nui.get("tree1");
		//tree.load("com.bos.bps.flow.queryBpsUserSelectTree.biz.ext");
		//tree.loadData([{id:317301, text:"赖成", orgcode:"BS001", orgname:"绵阳银行总行"}]);
		var json = '{"map":'+nui.getParams()["map"]+'}';//nui.alert(json);return;
        $.ajax({
            url: "com.bos.bps.op.WorkFlowManager.queryBpsUserSelectTree.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
                var o = nui.decode(mydata);//alert(nui.encode(o));
                tree.loadData(mydata.treeNodes);
                //一般机构单选，小贷多选
                if('2'==flag){
                
                	tree.showCheckBox=false;
                }else{
                
	                if('1'==<%=orgdegree%>){
	                	tree.showCheckBox=false;
	                }else{
	                	tree.showCheckBox=true;
	                }
                }
            }
        });
	}
	getInit();

    function GetData() {
		var tree = nui.get("tree1");
		if('1'==<%=orgdegree%> || '2'==flag){
		
			return tree.getSelectedNode();
		}else{
		
			return tree.getCheckedNodes(false);
		}
        
    }
    
	function onOk() {
		var d = GetData();
		if(null !=d && '' != d){
			
			if('1'==<%=orgdegree %> || '2'==flag){
			
				if(!d.nodeType || d.nodeType != "OrgEmployee"){
				
					nui.alert("请选择用户");
					return;
				}
			}
		}else{
			nui.alert("请选择用户");
			return;
		}
		
        CloseWindow("ok");
    }
    
    function onCancel() {
        CloseWindow("cancel");
    }
    
	function CloseWindow(action) {
        if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
        else window.close();
    }
</script>

</body>
</html>