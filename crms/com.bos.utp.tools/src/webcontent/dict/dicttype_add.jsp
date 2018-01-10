<%@page pageEncoding="UTF-8"%>
<%@include file="/common.jsp"%>
<%
	//获取标签中使用的国际化资源信息
	String save   = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("DictManager_l_save");    //保存
	String close  = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("DictManager_l_close");   //关闭
%>
<html>
<head>
<title><b:message key="DictManager_l_add_type"/></title><!-- 增加字典类型 -->
<script language="javascript">
	
	/*
	 * 功能：保存业务字典类型
	 *
	 * return 保存成败标志
	 */
	function saveDictType()
	{
		var frm = $name("data_form");
        
        //表单验证
        if( !checkForm(frm) ) {
            return;
        }
        
	    var myAjax = null;
	    var message;
	    
	    //判断修改还是新增
		if($name("_eosFlowAction").value == "insertSubmit")
		{
			//调用增加逻辑流
		    myAjax = new Ajax("com.bos.utp.tools.DictManager.insertDictType.biz");
		    message = '<b:message key="DictManager_l_add_dict_type"/>' + '<b:message key="DictManager_l_failed"/>';//增加业务字典类型失败。
		}else{
			myAjax = new Ajax("com.bos.utp.tools.DictManager.updateDictType.biz");
			myAjax.addParam("eosDictType/rank",$name("eosDictType/rank").value);
			myAjax.addParam("eosDictType/seqno",$name("eosDictType/seqno").value);
			message = '<b:message key="DictManager_l_update_dict_type"/>'+'<b:message key="DictManager_l_failed"/>';//修改业务字典类型失败。
		}
	    
	    //增加参数
	    myAjax.addParam("eosDictType/parentid",$name("eosDictType/parentid").value);
	    myAjax.addParam("eosDictType/dicttypeid",$name("eosDictType/dicttypeid").value);
	    myAjax.addParam("eosDictType/dicttypename",$name("eosDictType/dicttypename").value);
	    
	    //开始调用
	    myAjax.submit();
	    
	    //取得调用后的结果(xml对象)
	    var returnNode = myAjax.getResponseXMLDom();
	    
	    var reCode;
	    if(returnNode)
	    {
	    	//获取指定的节点值
	    	reCode = myAjax.getValue("/root/data/reCode");
	    }
	    
	    //判断刷新业务字典信息成败
    	if(reCode == 1)
	    {
	    	alert('<b:message key="l_m_save_success"></b:message>'); //保存成功。
    		window.close();
	    }else{
    		alert(message);
    		return false;
    	}
    	
    	window.close();
	}
	
	/*
	  * 功能：初始化页面
	  *
	  *
	  */
     function custInit()
     {
     	//初始化焦点
     	$id("dicttypeid").focus()
     }
</script>
</head>
<body class="eos-panel-table">
	<h:form name="data_form" action="com.bos.utp.tools.DictManager.flow" method="post" onsubmit="return checkForm(this);">
		<l:equal property="_eosLastAccessAction" targetValue="updateDictType">
        	<input type="hidden" name="_eosFlowAction" value="updateSubmit" >
        	<h:hidden property="eosDictType/rank"/>
        	<h:hidden property="eosDictType/seqno"/>
        </l:equal>
        <l:equal property="_eosLastAccessAction" targetValue="insertDictType">
        	<input type="hidden" name="_eosFlowAction" value="insertSubmit" >
        </l:equal>
 		<table align="center" border="1" width="296" class="form_table">
          <tr>
            <td class="form_label" align="left">
              &nbsp;<b:message key="DictManager_l_superior"/><b:message key="EosDictType.dicttypeid"/><b:message key="DictManager_l_colon"/><!-- 上级类型代码： -->
            </td>
            <td colspan="1">
            	<h:text property="eosDictType/parentid" size="25" readonly="true"/>
            </td>
          </tr>
          <tr>
            <td class="form_label" align="left">
              &nbsp;<b:message key="DictManager_l_dict"/><b:message key="EosDictType.dicttypeid"/><b:message key="DictManager_l_colon"/><!-- 字典类型代码： -->
            </td>
            <td colspan="1">
            <l:equal property="_eosLastAccessAction" targetValue="insertDictType">
            	<h:text property="eosDictType/dicttypeid" size="25" id="dicttypeid" validateAttr="maxLength=128;allowNull=false;"/><font style="color:red">*</font>
            </l:equal>
            <l:equal property="_eosLastAccessAction" targetValue="updateDictType">
            	<h:text property="eosDictType/dicttypeid" size="25" readonly="true"/>
            </l:equal>
            </td>
          </tr>
          <tr>
            <td class="form_label" align="left">
              &nbsp;<b:message key="DictManager_l_dict"/><b:message key="EosDictType.dicttypename"/><b:message key="DictManager_l_colon"/><!-- 字典类型名称： -->
            </td>
            <td colspan="1">
            <l:equal property="_eosLastAccessAction" targetValue="insertDictType">
              	<h:text property="eosDictType/dicttypename" size="25" validateAttr="maxLength=255;allowNull=false;"/><font style="color:red">*</font>
            </l:equal>
            <l:equal property="_eosLastAccessAction" targetValue="updateDictType">
            	<h:text property="eosDictType/dicttypename" size="25" validateAttr="maxLength=255;allowNull=false;" id="dicttypeid"/><font style="color:red">*</font>
            </l:equal>
            </td>
          </tr>
          <tr class="form_bottom">
            <td colspan="6">
              <input type="button" class="button" value="<%=save%>" onclick="saveDictType()"><!-- 保存 -->
              <input type="button" class="button" value="<%=close%>" onclick="javascript:window.close();"><!-- 关闭 -->
            </td>
          </tr>
        </table>
    </h:form>
</body>
</html>
<script language="javascript">
    eventManager.add(window,"load",custInit);
</script>