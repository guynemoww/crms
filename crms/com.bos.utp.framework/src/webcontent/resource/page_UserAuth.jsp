<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<%@page import="com.eos.foundation.eoscommon.ResourcesMessageUtil"%>
<head>
	<title>页面权限配置</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<%@include file="/common/nui/common.jsp" %>
	<style>
		  tr {
    		margin-top:1px;
    	  }
    	  td {
    		margin-top:1px;
    		}
	</style>
</head>
<body>
	
	<div  style="margin-top:0px;" id="form1">
		<table style="width:100%;height:70%;" class="nui-form-table">
			<tr>
				<td height="400" style="width:50%;" valign="top">
					 <div id="UserMainTabs" class="nui-tabs bg-toolbar" activeIndex="0" style="width:100%;height:100%;" onactivechanged="onUserActiveTab">
						<div name="show" title="[显示状态]所拥有人员" url="<%=request.getContextPath()%>/utp/framework/resource/show_UserList.jsp?resouceid=<%=request.getParameter("resouceid") %>">
								        
						</div>
						<div name="hidden" title="[隐藏状态]所拥有人员" url="<%=request.getContextPath()%>/utp/framework/resource/hidden_userList.jsp?resouceid=<%=request.getParameter("resouceid")%>">
						        
						</div>
					</div>
				</td>
			</tr>
			<tr>
		</tr>
		</table>
	</div>
	
	<script type="text/javascript">		
		var form1 = new nui.Form("#form1");
		//当选中员工面板的时候，把tab选项重新加载，已达到刷新的目的
		function onUserActiveTab(e){
			var tabs = nui.get("UserMainTabs");
            var tab = tabs.getActiveTab();
            if (tab) {
                tabs.reloadTab(tab);
            }
		}
		
		<%-- 关闭窗口 --%>
		function CloseWindow(action){
			if(action=="close" && form1.isChanged()){
				if(confirm("数据已改变,是否先保存?")){
					return false;
				}
			}else if(window.CloseOwnerWindow){
				return window.CloseOwnerWindow(action);
			}else{
				return window.close();
			}
		}
		
		function formCancel(){
			CloseWindow("cancel");
		}
			
	</script>
</body>
</html>
