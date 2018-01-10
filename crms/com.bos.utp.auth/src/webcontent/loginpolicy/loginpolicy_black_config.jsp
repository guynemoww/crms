<%@page pageEncoding="UTF-8"%>
<%@include file="/common.jsp"%>

<html>
<head>
<title></title>

<%
	//登录限制管理
    String blackQuery = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("loginPolicyManager_l_title_blackQuery");
    //请选择
    String pleaseSelect = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("l_pleaseSelect");
    //限制名称
    String policyname = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("loginPolicyManager_AtLoginPolicy.policyname");
    //登录名限制
    String loginNameLimit = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("loginPolicyManager_AtLoginPolicy.loginnamelimit");
    //登录名限制类型
    String loginNameLImitType = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("loginPolicyManager_AtLoginPolicy.loginnamelimittype");
    //登录Ip限制
    String loginIpLimit = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("loginPolicyManager_AtLoginPolicy.loginiplimit");
    //登录Ip限制类型
    String loginIpLimitType = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("loginPolicyManager_AtLoginPolicy.loginIPlimittype");
    //开始登录时间
    String starttime = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("loginPolicyManager_AtLoginPolicy.starttimelimit");
    //截止登录时间
    String endtitme = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("loginPolicyManager_AtLoginPolicy.endtimelimit");
    //适用范围
    String policytype = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("loginPolicyManager_AtLoginPolicy.policytype");
    //是否启用
    String isenabled = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("loginPolicyManager_AtLoginPolicy.isenabled");
%>
<script>
	    var datacell = null;
	    /*
	     * 更新记录后的确认信息
	     */
	    function initAfterSubmit(){
	    	datacell.afterSubmit = function(ajax) {
				var retCode = ajax.getValue("root/data/retCode");
				if( retCode == "1" ) {
				    alert( '<b:message key="l_m_save_success"/>' );  <!--  保存成功 -->
				} else {
				    alert( '<b:message key="l_m_save_fail"/>' );         <!-- 保存失败 -->
				}
	    	}
	    }

	    /*
         * 查询时加载datacell
         */
	    function querySubmit() {
	        datacell.reload();
	    }
	    /*
         * 自定义初始化按钮样式
         */
	    function custInit(){
	    	datacell = $id("loginlimitConfig");
		    initAfterSubmit();
	    }
	</script>
</head>
<body leftmargin="0" topmargin="0">
    <w:panel id="panel1" width="765" title="<%=blackQuery %>">
        <table align="center" border="0" width="765" class="form_table">
            <h:form id="queryForm" name="queryForm">
            <tr>
	            <td class="form_label">   <!-- 策略名称 -->
	               <b:message key="loginPolicyManager_AtLoginPolicy.policyname"></b:message><b:message key="l_colon"></b:message>
	            </td>
	            <td>
	                 <h:text property="criteria/_expr[1]/policyname"/>
	                 <h:hidden name="criteria/_expr[1]/_op" value="like"/>
	                 <h:hidden name="criteria/_expr[1]/_likeRule" value="all"/>
	            </td>
	            <td class="form_label">   <!-- 登陆名限制 -->
	               <b:message key="loginPolicyManager_AtLoginPolicy.loginnamelimit"></b:message><b:message key="l_colon"></b:message>
	            </td>
	            <td>
	                 <h:text property="criteria/_expr[2]/lognamelimit"/>
	                 <h:hidden name="criteria/_expr[2]/_op" value="like"/>
	                 <h:hidden name="criteria/_expr[2]/_likeRule" value="all"/>
	            </td>
	            <td class="form_label">   <!-- 登陆IP限制 -->
	               <b:message key="loginPolicyManager_AtLoginPolicy.loginiplimit"></b:message><b:message key="l_colon"></b:message>
	            </td>
	            <td>
	                 <h:text property="criteria/_expr[3]/logiplimit"/>
	                 <h:hidden name="criteria/_expr[3]/_op" value="like"/>
	                 <h:hidden name="criteria/_expr[3]/_likeRule" value="all"/>
	            </td>
	            <td rowspan="2" align="center">
	                 <input type="button" class="button" value='<b:message key="l_query"></b:message>' onclick="javascript:querySubmit();"/>
	            </td>
            </tr>
            <tr>
                <td class="form_label">   <!-- 是否启用 -->
	               <b:message key="loginPolicyManager_AtLoginPolicy.isenabled"></b:message><b:message key="l_colon"></b:message>
	            </td>
	            <td>
	                 <d:select dictTypeId="XD_0002" style="width:133" nullLabel="<%=pleaseSelect %>" name="criteria/_expr[4]/isenabled"></d:select>
	            </td>
	            <td class="form_label">   <!-- 开始登陆时间 -->
	               <b:message key="loginPolicyManager_AtLoginPolicy.starttimelimit"></b:message><b:message key="l_colon"></b:message>
	            </td>
	            <td>
	                 <w:date property="criteria/_expr[5]/starttimelimit" defaultNull="true" format ="yyyy-MM-dd HH:mm:ss"/>
	                 <h:hidden property="criteria/_expr[5]/_op" value=">=" />
		             <h:hidden property="criteria/_expr[5]/_pattern" value="yyyy-MM-dd HH:mm:ss"/>
	            </td>
	            <td class="form_label">   <!-- 截止登陆时间 -->
	               <b:message key="loginPolicyManager_AtLoginPolicy.endtimelimit"></b:message><b:message key="l_colon"></b:message>
	            </td>
	            <td>
	                 <w:date property="criteria/_expr[6]/endtimelimit" defaultNull="true" format ="yyyy-MM-dd HH:mm:ss"/>
	                 <h:hidden property="criteria/_expr[6]/_op" value="&amp;lt;=" />
		             <h:hidden property="criteria/_expr[6]/_pattern" value="yyyy-MM-dd HH:mm:ss"/>

					 <!-- 默认查询的是黑名单 -->
	        		 <h:hidden property="criteria/_expr[7]/policytype" value="0"/>
	                 <h:hidden property="criteria/_expr[7]/_op" value="=" />
	            </td>
            </tr>
            </h:form>
        </table>
    </w:panel>
    <r:datacell id="loginlimitConfig"
	    queryAction="com.bos.utp.auth.LoginPolicyManager.queryAllLoginPolicy.biz"
	    xpath="loginPolicys"
	    width="765"
	    height="330"
	    pageSize="10"
	    pageSizeList="10,20,30" submitAction="com.bos.utp.auth.LoginPolicyManager.saveLoginPolicyItem.biz" paramFormId="queryForm">
	    <r:field fieldName="policyname" editId="text1" label="<%=policyname %>">
	        <h:text id="text1" validateAttr="allowNull=false;"/>
	    </r:field>
	    <r:field fieldName="lognamelimit" editId="text2" label="<%=loginNameLimit %>" >
	        <h:text id="text2" />
	    </r:field>
	    <r:field fieldName="lognamelmttype" label="<%=loginNameLImitType %>" editId="lognametype">
	        <d:select dictTypeId="ABF_LTDTYPE" property="lognamelmttype" nullLabel="<%=pleaseSelect %>" id="lognametype"> </d:select>
	    </r:field>
	     <r:field fieldName="logiplimit" editId="text3" label="<%=loginIpLimit %>">
	        <h:text id="text3"/>
	    </r:field>
	    <r:field fieldName="logiplmttype" label="<%=loginIpLimitType %>">
	        <d:select dictTypeId="ABF_LTDTYPE" property="logiplmttype" nullLabel="<%=pleaseSelect %>"> </d:select>
	    </r:field>
	     <r:field fieldName="starttimelimit" editId="date1" label="<%=starttime %>" width="130">
	        <w:date id="date1" format ="yyyy-MM-dd HH:mm:ss"/>
	    </r:field>
	    <r:field fieldName="endtimelimit" editId="date2" label="<%=endtitme %>" width="130">
	        <w:date id="date2" format ="yyyy-MM-dd HH:mm:ss"/>
	    </r:field>

		<!-- 不显示当前的登录限制类型 -->
	    <r:field fieldName="policytype" width="0" defaultValue="0" label="<%=policytype %>" >
	    </r:field>

	    <r:field fieldName="isenabled" label="<%=isenabled %>" >
	        <d:select property="isenabled" dictTypeId="XD_0002" nullLabel="<%=pleaseSelect %>"></d:select>
	    </r:field>
	    <r:toolbar tools="nav,pagesize,edit" location="bottom" />
	</r:datacell>

</body>
</html>

<script language="javascript">
    //初始化页面按钮样式
    eventManager.add(window,"load",custInit);
</script>