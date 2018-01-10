<%@page pageEncoding="UTF-8"%>
<%@include file="/common.jsp"%>
<html>
<head>
<title></title>
<%
	//个人配置树
    String operConfigTree = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("personalsettingManager_l_operConfig_tree");
    //首选项
    String preference = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("personalsettingManager_l_preference");
%>

<script type="text/javascript" language="javascript">
	/*
	 * 根据节点的值判断执行那个action
	 */
	function clickNode(node)
	{
		var dictid=node.getProperty("dictID");
		var dictName=node.getProperty("dictName");
		$name('detail').src='com.bos.utp.auth.application.PersonalsettingManager.flow?_eosFlowAction=querysetting&operconfig/configname='+dictid+"&operconfig/configvalue="+dictName;
	}
</script>
</head>
<body topmargin="0" leftmargin="0">
<TABLE border="0" width="100%" height="100%" cellpadding="0" cellspacing="0">
	<TR>
	    <TD width="25%" height="100%" valign="top">
		  	<table border="0" width="100%" height="100%" cellpadding="0" cellspacing="0">
				<tr height="4%">
					<td class="eos-panel-title">&nbsp;&nbsp;<%=preference%></td> <!--首选项 -->
				</tr>
				<tr>
					<td width="30%" height="100%" valign="top" class="eos-panel-body">
						<w:tree  id="configTree" width="100%" height="497">
						   <w:treeRoot display="<%=operConfigTree %>" icon='<%=SkinUtil.getStyleFileForTag("images/abf/application_home.png",request)%>' >
						   </w:treeRoot>
					         <w:treeNode nodeType="dictEntry" showField="dictName" xpath="pSettings" onClickFunc="clickNode" icon='<%=SkinUtil.getStyleFileForTag("images/abf/user_setting.png",request)%>'>
					         <w:treeRelation parentNodeType="root" field="level" value="1" />
					       </w:treeNode>
				        </w:tree>
			     	</td>
				</tr>
			</table>
        </td>
	  <td valign="top" width="75%" height="100%">
	       <iframe  name="detail" style="width:100%;height:100%" frameBorder="0" scrolling="auto" src="com.bos.utp.auth.application.PersonalsettingManager.flow?_eosFlowAction=querysetting"></iframe>
	  </td>
  	</tr>
</table>
</body>
</html>
