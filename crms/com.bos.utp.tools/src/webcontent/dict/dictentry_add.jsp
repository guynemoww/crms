<%@page pageEncoding="UTF-8"%>
<%@include file="/common.jsp"%>
<%
	//获取标签中使用的国际化资源信息
	String save  = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("DictManager_l_save");                 //保存
	String close = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("DictManager_l_close");                //关闭
	String estop = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("EosDictEntry.status.value_estop");    //保存
	String open  = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("EosDictEntry.status.value_open");     //关闭
%>
<html>
<head>
<title><b:message key="DictManager_l_add_type"/><b:message key="DictManager_l_item"/></title><!-- 增加字典类型项 -->
<script language="javascript">
	
	/*
	 * 功能：保存业务字典类型项
	 *
	 * return 保存成败标志
	 */
	function saveDictEntry()
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
		    myAjax = new Ajax("com.bos.utp.tools.DictManager.insertDictEntry.biz");
		    message = '<b:message key="DictManager_l_add_dict_type"/>' + '<b:message key="DictManager_l_item"/>' + '<b:message key="DictManager_l_failed"/>';//增加业务字典类型项失败。
		}else{
			myAjax = new Ajax("com.bos.utp.tools.DictManager.updateDictEntry.biz");
			myAjax.addParam("eosDictEntry/rank",$name("eosDictEntry/rank").value);
			myAjax.addParam("eosDictEntry/seqno",$name("eosDictEntry/seqno").value);
			message = '<b:message key="DictManager_l_update_dict_type"/>' + '<b:message key="DictManager_l_item"/>' + '<b:message key="DictManager_l_failed"/>';//修改业务字典类型项失败。
		}
	    
	    //增加参数
	    myAjax.addParam("eosDictEntry/eosDictType/dicttypeid",$name("eosDictEntry/eosDictType/dicttypeid").value);
	    myAjax.addParam("eosDictEntry/parentid",$name("eosDictEntry/parentid").value);
	    myAjax.addParam("eosDictEntry/dictid",$name("eosDictEntry/dictid").value);
	    myAjax.addParam("eosDictEntry/dictname",$name("eosDictEntry/dictname").value);
	    myAjax.addParam("eosDictEntry/sortno",$name("eosDictEntry/sortno").value);
	    myAjax.addParam("eosDictEntry/status",$id("eosDictEntry/status").value);
	    
	    //开始调用
	    myAjax.submit();
	    
	    //取得调用后的结果(xml对象)
	    var returnNode =myAjax.getResponseXMLDom();
	    
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
     	$id("dictid").focus();
     }
</script>
</head>
<body class="eos-panel-table">
	<h:form name="data_form" action="com.bos.utp.tools.DictManager.flow" method="post" onsubmit="return checkForm(this);">
		<l:equal property="_eosLastAccessAction" targetValue="updateDictEntry">
        	<input type="hidden" name="_eosFlowAction" value="updateSubmit" >
        	<h:hidden property="eosDictEntry/rank"/>
        	<h:hidden property="eosDictEntry/seqno"/>
        </l:equal>
        <l:equal property="_eosLastAccessAction" targetValue="insertDictEntry">
        	<input type="hidden" name="_eosFlowAction" value="insertSubmit" >
        </l:equal>
        <h:hidden property="eosDictEntry/eosDictType/dicttypeid"/>
 		<table align="center" border="1" width="296" class="form_table">
          <tr>
            <td class="form_label" align="left">
              &nbsp;<b:message key="DictManager_l_superior"/><b:message key="EosDictEntry.dictid"/><b:message key="DictManager_l_colon"/><!-- 上级类型项代码： -->
            </td>
            <td colspan="1">
            	<h:text property="eosDictEntry/parentid" size="20" readonly="true"/>
            </td>
          </tr>
          <tr>
            <td class="form_label" align="left">
              &nbsp;<b:message key="DictManager_l_dict"/><b:message key="EosDictEntry.dictid"/><b:message key="DictManager_l_colon"/><!-- 字典类型项代码： -->
            </td>
            <td colspan="1">
            <l:equal property="_eosLastAccessAction" targetValue="updateDictEntry">
            	<h:text property="eosDictEntry/dictid" size="20" readonly="true"/>
            </l:equal>
            <l:equal property="_eosLastAccessAction" targetValue="insertDictEntry">
            	<h:text property="eosDictEntry/dictid" size="20" id="dictid" validateAttr="maxLength=128;allowNull=false;"/><font style="color:red">*</font>
            </l:equal>
            </td>
          </tr>
          <tr>
             <td class="form_label" align="left">
              &nbsp;<b:message key="DictManager_l_dict"/><b:message key="EosDictEntry.dictname"/><b:message key="DictManager_l_colon"/><!-- 字典类型项名称： -->
            </td>
            <td colspan="1">
            <l:equal property="_eosLastAccessAction" targetValue="updateDictEntry">
            	<h:text property="eosDictEntry/dictname" size="20" validateAttr="maxLength=255;allowNull=false;" id="dictid"/><font style="color:red">*</font>
            </l:equal>
            <l:equal property="_eosLastAccessAction" targetValue="insertDictEntry">
            	<h:text property="eosDictEntry/dictname" size="20" validateAttr="maxLength=255;allowNull=false;"/><font style="color:red">*</font>
            </l:equal>
            </td>
          </tr>
          <tr>
             <td class="form_label" align="left">
              &nbsp;<b:message key="EosDictEntry.sortnotemp"/><b:message key="DictManager_l_colon"/><!-- 显示排序： -->
            </td>
            <td colspan="1">
              <h:text property="eosDictEntry/sortno" size="20" validateAttr="maxLength=10;type=number;"/>
            </td>
          </tr>
          <tr>
             <td class="form_label" align="left">
              &nbsp;<b:message key="EosDictEntry.status"/><b:message key="DictManager_l_colon"/><!-- 是否封存： -->
            </td>
            <td colspan="1">
              	<h:select id="eosDictEntry/status" value="1" style="width=132" property="eosDictEntry/status">
					<h:option label="<%=estop%>" value="0"/><!-- 禁止 -->
					<h:option label="<%=open%>" value="1"/><!-- 启用 -->
				</h:select>
            </td>
          </tr>
          <tr class="form_bottom">
            <td colspan="6">
              <input type="button" class="button" value="<%=save%>" onclick="saveDictEntry();"><!-- 保存 -->
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