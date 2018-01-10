<%@page pageEncoding="UTF-8"%>
<%@include file="/common.jsp"%>
<%
	//获取标签中使用的国际化资源信息
	String title_type   = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("DictManager_l_title_type");   //业务字典类型
	String title_item   = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("DictManager_l_dict_item");    //数据字典项
	String add_type     = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("DictManager_l_add_type");     //增加字典类型
	String refresh      = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("DictManager_l_refresh");      //刷新
	String update_type  = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("DictManager_l_update_type");  //修改字典类型  
	String delete       = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("DictManager_l_delete");       //删除
	String query        = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("DictManager_l_query");        //查询
	String reset        = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("DictManager_l_reset");        //重置
	String dicttypeid   = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("EosDictType.dicttypeid");     //类型代码
	String dicttypename = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("EosDictType.dicttypename");   //类型名称
	String operate      = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("DictManager_l_operate");      //操作
	String item         = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("DictManager_l_item");         //项
	String refresh_slow = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("DictManager_l_refresh_slow"); //刷新缓存
	String export       = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("DictManager_l_export");       //导出
	String dictid       = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("EosDictEntry.dictid");        //类型项代码
	String dictname     = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("EosDictEntry.dictname");      //类型项名称
	String sortno       = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("EosDictEntry.sortno");        //排序
	String status       = com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("EosDictEntry.status");        //是否封存
%>

<html>
<head>
<title><b:message key="DictManager_l_title_main"/></title><!-- 业务字典管理 -->
</head>
<body topmargin="0" leftmargin="0">
	<TABLE height="100%" cellSpacing=0 cellPadding=0 width="100%" border=0>
		<TR>
			<TD width="36%" height="100%" align="left" valign="top">
				<!-- 业务字典类型 -->
				<table align="center"  border="0" width="100%" height="100%" cellSpacing=0 cellPadding=0>
				    <tr>
				      <td>
				    <table align="center" class="form_table" border="0" width="100%" height="100%" cellSpacing=0 cellPadding=0>
		            <h:form id="queryForm">
					
					<tr height="5%">
						<td class="eos-panel-title" colspan="4">&nbsp;&nbsp;<%=title_type %></td>
					</tr>
		            <tr>
			            <td class="form_label" align="center" nowrap="nowrap">
			               <b:message key="EosDictType.dicttypeid"/><b:message key="DictManager_l_colon"/><!-- 类型代码： -->
			            </td>
			            <td align="left">
			                 <h:text property="dictType/_expr[2]/dicttypeid" size="20"/>
			            </td>
			       	</tr>
			       	<tr>
			            <td class="form_label" align="center" nowrap="nowrap">
			               <b:message key="EosDictType.dicttypename"/><b:message key="DictManager_l_colon"/><!-- 类型名称： -->
			            </td>
			            <td align="left">
			                 <h:text property="dictType/_expr[3]/dicttypename" size="20"/>
			            </td>
			            <input type="hidden" name="dictType/_expr[1]/parentid" value="">
			            <input type="hidden" name="dictType/_expr[1]/_processNullValue" value="true">
			            <input type="hidden" name="dictType/_expr[2]/_op" value="like">
			            <input type="hidden" name="dictType/_expr[2]/_likeRule" value="all">
			            <input type="hidden" name="dictType/_expr[3]/_op" value="like">
			            <input type="hidden" name="dictType/_expr[3]/_likeRule" value="all">
		            </tr>
		            <tr>
		            	<td colSpan="4" align="center">
 			            	 <input type="button" class="button" value="<%=refresh_slow%>" onclick="onRefreshSlow();"/><!-- 刷新缓存 -->
			                 <input type="button" class="button" value="<%=export%>" onclick="exportDict();"/><!-- 导出 -->
			                 <input type="button" class="button" value="<%=query%>" onclick="queryDictType();"/><!-- 查询 -->
			                 <input type="button" class="button" value="<%=reset%>" onclick="onResetType();"/><!-- 重置 -->
			            </td>
		            </tr>
		            </h:form>
		            </table>
		            </td>
		            </tr>
			        <TR>
						<TD width="100%" align="left" valign="top" colspan="2" height="100%">
							<table border="0" width="100%" height="100%" cellpadding="0" cellspacing="0">
								<tr height="5%">
									<td class="eos-panel-title">&nbsp;&nbsp;<%=title_type %></td>
								</tr>
								<tr height="91%">
									<td width="30%" height="100%" valign="top" class="eos-panel-body">
										<r:datacell entityType="com.bos.utp.dataset.dict.EosDictType" 
													height="100%" width="100%" id="dictTypeID" pageSize="10" xpath="dictTypeLists"
													queryAction="com.bos.utp.tools.DictManager.queryDictTypePage.biz"
													submitAction="com.bos.utp.tools.DictManager.saveDictType.biz" paramFormId="queryForm">
										  <r:toolbar location="bottom" tools="nav:first prev next last goto,edit:add del save reload"/>
										  <r:field fieldName="dicttypeid" align="left" label="<%=dicttypeid%>" editId="dicttypeid" width="110" allowModify="false"><!-- 类型代码 -->
										    <h:text id="dicttypeid" validateAttr="maxLength=128;allowNull=false;"/>
										  </r:field>
										  <r:field fieldName="dicttypename" align="left" label="<%=dicttypename%>" editId="dicttypename" width="110"><!-- 类型名称 -->
										    <h:text id="dicttypename" validateAttr="maxLength=255;allowNull=false;"/>
										  </r:field>
										  <r:field fieldName="operate" align="center" label="<%=operate%>" width="45" onRefreshFunc="showOperate"><!-- 操作 -->
										  </r:field>
										</r:datacell>
									</td>
								</tr>
							</table>
						<td>
					<tr>
				</table>
			</TD>
			<TD id="entryInfo" height="100%" vAlign="top" width="64%">
				<TABLE height="100%" cellSpacing=0 cellPadding=0 width="100%" border=0>
					<tr height="28">
						<td class="eos-panel-title" colspan="4">&nbsp;&nbsp;<%=title_item %></td>
					</tr>
					<TR>
						<TD width="100%" align="left" valign="top" height="40%">
							<!-- 业务字典类型项 -->
							<r:rtree height="100%" id="dictTrees" width="100%">
							  <r:treeRoot action="com.bos.utp.tools.DictManager.queryParentDictEntry.biz" childEntities="dictEntryList"
							  onClickFunc="queryNodeDictEntry" icon='<%=SkinUtil.getStyleFileForTag("images/abf/folder_page.png",request)%>' display="" initParamFunc="initParam">
							  	<r:treeMenu>
							      <r:treeMenuItem display="<%=refresh%>" onClickFunc="onRefreshRoot"/><!-- 刷新 -->
							      <r:treeMenuItem display="<%=add_type + item%>" onClickFunc="addDictEnrey"/><!-- 增加字典类型项 -->
							    </r:treeMenu>
							  </r:treeRoot>
							  <r:treeNode nodeType="dictEntryList" action="com.bos.utp.tools.DictManager.queryChildDictEntry.biz" onClickFunc="queryNodeDictEntry"
							  	childEntities="dictEntryList" submitXpath="eosDictEntry" showField="dictname" onDblclickFunc="dbClickReload" 
							  	icon='<%=SkinUtil.getStyleFileForTag("images/abf/tree_folderopen.gif",request)+","+SkinUtil.getStyleFileForTag("images/abf/tree_folder.gif",request)%>' preload="true">
								<r:treeMenu>
							      <r:treeMenuItem display="<%=refresh%>" onClickFunc="onRefresh"/><!-- 刷新 -->
							      <r:treeMenuItem display="<%=add_type + item%>" onClickFunc="addDictEnrey"/><!-- 增加字典类型项 -->
							      <r:treeMenuItem display="<%=update_type + item%>" onClickFunc="updateDictEntry"/><!-- 修改字典类型项 -->
							      <r:treeMenuItem display="<%=delete%>" onClickFunc="deleteDictEntry"/><!-- 删除 -->
							    </r:treeMenu>    
							  </r:treeNode>
							</r:rtree>
						</TD>
					</TR>
					<TR>
						<TD width="100%" align="left" valign="top">
							<!-- 业务字典类型项 -->
							<table align="center" border="0" cellpadding="0" cellspacing="0" width="100%" class="form_table">
					            <h:form id="queryEntryForm">
						            <input type="hidden" name="dictEntry/_expr[3]/dictid" value="">
						            <input type="hidden" name="dictEntry/_expr[4]/dictname" value="">
						            <input type="hidden" name="dictEntry/_expr[1]/eosDictType.dicttypeid" value="">
						            <input type="hidden" name="dictEntry/_expr[2]/parentid" value="">
						            <input type="hidden" name="dictEntry/_expr[2]/_processNullValue" value="true">
						            <input type="hidden" name="dictEntry/_expr[3]/_op" value="like">
						            <input type="hidden" name="dictEntry/_expr[3]/_likeRule" value="all">
						            <input type="hidden" name="dictEntry/_expr[4]/_op" value="like">
						            <input type="hidden" name="dictEntry/_expr[4]/_likeRule" value="all">
						            <input type="hidden" name="isParent" value="">
					            </h:form>
					        </table>
					        <table border="0" width="100%" height="100%" cellpadding="0" cellspacing="0">
							<tr height="9%">
								<td class="eos-panel-title">&nbsp;&nbsp;<%=title_item %></td>
							</tr>
							<tr>
								<td width="30%" height="100%" valign="top" class="eos-panel-body">
								<r:datacell entityType="com.bos.utp.dataset.dict.EosDictEntry" 
											height="100%" width="100%" id="dictEntryID" pageSize="10" xpath="dictEntryLists"
											queryAction="com.bos.utp.tools.DictManager.queryDictEntryPage.biz"
											submitAction="com.bos.utp.tools.DictManager.saveDictEntry.biz" paramFormId="queryEntryForm">
								  	<r:toolbar location="bottom" tools="nav:first prev next last goto,edit:add del save reload"/>
								  	<r:field fieldName="dictid" align="center" label="<%=dictid%>" editId="dictid" width="80" allowModify="false"><!-- 类型项代码 -->
								    	<h:text id="dictid" validateAttr="maxLength=128;allowNull=false;"/>
								  	</r:field>
								  	<r:field fieldName="dictname" align="center" label="<%=dictname%>" editId="dictname" width="100"><!-- 类型项名称 -->
								    	<h:text id="dictname" validateAttr="maxLength=255;allowNull=false;"/>
								  	</r:field>
								  	<r:field fieldName="sortno" align="center" label="<%=sortno%>" editId="sortno" width="50"><!-- 排序 -->
								    	<h:text id="sortno" validateAttr="maxLength=10;type=number;"/>
								  	</r:field>
								  		<r:field fieldName="status" align="center" label="<%=status%>" defaultValue="1" width="80" onRefreshFunc="showStatus"><!-- 是否封存 -->
								  	</r:field>
								</r:datacell>
						</TD>
					</TR>
				</TABLE>
			</TD>			
		</TR>
	</TABLE>
</body>
<script language="javascript">

	var treeEntry     = $id("dictTrees");     //业务字典类型项树
	var datacellType  = $id("dictTypeID");    //业务字典类型查询结果(Datacell)
	var datacellEntry = $id("dictEntryID");   //业务字典类型项查询结果(Datacell)
	
	var dicttypeid;    //业务字典类型ID
	var dicttypename;  //业务字典类型名称
	
	/*
	 * 功能：重新加载业务字典类型记录(Datacell)
	 * param 父ID
	 * return 
	 */
	function reloadDatacellType(_parentid)
	{
		//第一层节点父节点字段(parentid)为空
      	$name("dictType/_expr[1]/parentid").value = _parentid;
      	
      	//重新加载Datacell数据
		datacellType.reload();
	}
	
	/*
	 * 功能：重新加载业务字典类型项记录(Datacell)
	 * param _dicttypeid,_parentid,_processNullValue,_isParent 类型ID,父ID,isnull查询参数,是否根节点
	 * return 
	 */
	function reloadDatacellEntry(_dicttypeid,_parentid,_processNullValue,_isParent)
	{
		
		//类型字典类型ID赋值
		$name("dictEntry/_expr[1]/eosDictType.dicttypeid").value = _dicttypeid;
		
		//查询当前节点的子节点(当前节点赋值父节点)
		$name("dictEntry/_expr[2]/parentid").value = _parentid;
		
		//业务字典类型项父节点ID为空查询条件(is null)
		$name("dictEntry/_expr[2]/_processNullValue").value = _processNullValue;
		
		//是否根节点(0为子节点,1为根节点)
		$name("isParent").value = _isParent;
		
		//业务字典类型项Datacell重新加载数据
		datacellEntry.reload();
	}
	
	/*
	 * 功能：单击业务字典类型树节点查询
	 * param node
	 * return
	 */
	function queryNodeDictType(node)
	{	
		//父节点(根节点父节点为空)
		var _parentid = "";
		
		//判断是否根节点
		if(node.isRootNode())
		{
			//重新加载业务字典类型记录(Datacell)
			reloadDatacellType(_parentid);
		}else{
			//查询当前节点的子节点(当前节点赋值父节点)
			_parentid = node.getProperty("dicttypeid");
			
			//重新加载业务字典类型记录(Datacell)
			reloadDatacellType(_parentid);
			
			//获取业务字典类型ID
      		dicttypeid   = node.getProperty("dicttypeid");
      	
      		//获取业务字典类型名称
      		dicttypename = node.getProperty("dicttypename");
		}
		
		//重新加载业务字典类型项树子节点
      	treeEntry.getRootNode().reloadChild();	
	}
	
	/*
	 * 功能：业务字典类型查询(查询按钮触发)
	 *
	 * return 
	 */
	function queryDictType()
	{
		//重新加载Datacell数据
      	datacellType.reload();
	}
	
	/*
	 * 功能：选中数据行之后调用
	 *
	 * return 
	 */
	datacellType.afterSelectRow = function()
	{
		//复制对应行对象(Row)的entity对象
		var entity = datacellType.copyRow();
		
		if(entity != null)
		{
			//获取业务字典类型ID和业务字典类型名称
		    dicttypeid   = entity.getProperty('dicttypeid');
		    dicttypename = entity.getProperty('dicttypename');
		}
		
        return true;
    }
    
    /*
	 * 功能：单击业务字典类型项节点查询
	 * param node
	 * return 
	 */
    function queryNodeDictEntry(node)
    {	
    	//获取根节点entity
    	var entity = treeEntry.getRootNode().getEntity();
    	
    	var	_dicttypeid       = entity.getProperty("dicttypeid"); //类型字典类型ID赋值
    	var _parentid         = "";         //父节点ID(根节点ID为空)
    	var _processNullValue = "true";     //业务字典类型项父节点ID为空查询条件(is null)
    	var _isParent         = 1;          //初始化是否根节点为(根节点)
    	
		if(node.isRootNode())
		{
			//重新加载业务字典类型项记录(Datacell)
			reloadDatacellEntry(_dicttypeid,_parentid,_processNullValue,_isParent);
		}else{
			//变量赋值
			_parentid = node.getProperty("dictid");
			_isParent = 0;
			_processNullValue = "false";
			
			//重新加载业务字典类型项记录(Datacell)
		 	reloadDatacellEntry(_dicttypeid,_parentid,_processNullValue,_isParent);
		}   
    }
    
	/*
	 * 功能：业务字典类型项查询(查询按钮触发)
	 *
	 * return 
	 */
	function queryDictEntry()
	{
		//业务字典类型项Datacell重新加载数据
		datacellEntry.reload();
	}
	
	/*
	 * 功能：业务字典类型项树初始化
	 * 
	 * return 
	 */
	function initParam()
    {
    	//字典类型项树根节点设置初始值
    	var entity = treeEntry.getRootNode().getEntity();
    	
    	//业务字典类型ID
    	entity.setProperty("dicttypeid",dicttypeid);
    	
    	//业务字典类型名称
    	entity.setProperty("dicttypename",dicttypename);
    	
    	//根节点初始化参数
    	var _parentid         = "";
    	var _processNullValue = "true";
    	var _isParent         = 1;
    	
    	//业务字典类型项Datacell加载
    	reloadDatacellEntry(dicttypeid,_parentid,_processNullValue,_isParent)
    	
    	
    	//设置业务字典类型项树根节点显示名称
    	treeEntry.getRootNode().setText(dicttypename);
    	
    	//业务字典类型ID封装String型
    	var str = "<dicttypeid>" + dicttypeid + "</dicttypeid>";
    	return str;
    }
    
    /* 
	 * 功能：单击“类型项”按钮加载字典类型项树和Datacell
	 *
	 * return 
	 */
	function reloadDictEntry()
	{
		datacellType.afterSelectRow = function(){
			//复制对应行对象(Row)的entity对象
			var entity = datacellType.copyRow();
			if(entity != null)
			{
				//获取业务字典类型ID和业务字典类型名称
			    dicttypeid   = entity.getProperty('dicttypeid');
			    dicttypename = entity.getProperty('dicttypename');
			}
			if(dicttypename != "" && dicttypeid != "")
			{
		    	
			    //设置业务字典类型项树根节点显示名称
			    treeEntry.getRootNode().setText(dicttypename);
				//重新加载业务字典类型项树子节点
			    treeEntry.getRootNode().reloadChild();
      		}
      	}
      	
      	//新增数据后提交
		if(datacellType.isModefied)
		{
			datacellType.submit();
		}
	}
	
	/*
	 * 功能：双击节点展开、关闭当前节点的下级子节点
	 * param node
	 * return
	 */
    function dbClickReload(node)
    {
    	if(node.isExpanded())
    	{
    		//关闭当前节点
        	node.collapseNode();
        }else{
            //展开当前节点的下级子节点
        	node.expandNode();
        }
    }
    
    /*
	 * 功能：树节点刷新
	 * param node
	 * return 
	 */
    function onRefresh(node)
    {
    	//从服务端重新加载当前节点的子节点(刷新下级节点)
    	node.reloadChild();
    }
    
    /*
	 * 功能：刷新字典类型项根节点
	 *
	 * return 
	 */
    function onRefreshRoot()
	{
		//获取根节点entity
		var entity = treeEntry.getRootNode().getEntity();
		
		//设置业务字典类型项树根节点显示名称
    	treeEntry.getRootNode().setText(entity.getProperty("dicttypename"));
		
		//重新加载业务字典类型项树子节点
      	treeEntry.getRootNode().reloadChild();
	}
	
     /*
	  * 功能：新增业务字典类型
	  * param node
	  * return
	  */
    function addDictType(node)
    {
    	var url = "com.bos.utp.tools.DictManager.flow";
    	url = url + "?_eosFlowAction=insertDictType";
    	url = url + "&eosDictType/parentid=";
    	
    	//父类型代码
    	var parentid;
    	
    	//如果是根节点父类型代码为空
    	if(!node.isRootNode())
    	{
    		parentid = node.getProperty("dicttypeid");
    	}else{
    		parentid = "";
    	}
    	url = url + parentid;
    	url += '&_ts='+(new Date()).getTime();   //防止IE缓存，在每次打开时加个时间差的参数
    	
    	//弹出新增业务字典类型模态框
    	showModalCenter(url, "", callBackTypeAdd, 300, 145, '<b:message key="DictManager_l_add_type"/>');//增加字典类型
    }
    
    /*
	 * 功能：新增业务字典类型项
	 * param node
	 * return
	 */
    function addDictEnrey(node)
    {
    	var url = "com.bos.utp.tools.DictManager.flow";
    	url = url + "?_eosFlowAction=insertDictEntry";
    	
    	//获取业务字典类型代码
    	url = url + "&eosDictEntry/eosDictType/dicttypeid=";
    	var entity = $id("dictTrees").getRootNode().getEntity();
    	url = url + entity.getProperty('dicttypeid');
    	
    	//父业务字典类型项代码
    	url = url + "&eosDictEntry/parentid=";
    	var parentid;
    	if(!node.isRootNode())
    	{
    		parentid = node.getProperty("dictid");
    	}else{
    		parentid = "";
    	}
    	url = url + parentid;
    	url += '&_ts='+(new Date()).getTime();   //防止IE缓存，在每次打开时加个时间差的参数
    	
    	//弹出新增业务字典类型项模态框
    	showModalCenter(url, "", callBackEntryAdd, 300, 200, '<b:message key="DictManager_l_add_type"/>'+'<b:message key="DictManager_l_item"/>');//增加字典类型项
    }
    
    /*
	 * 功能：增加字典类型回调函数
	 * 
	 * return 
	 */
    function callBackTypeAdd()
    {
    	//新增成功刷新树
    	onRefresh(treeType.getSelectNode());
    	
    	//更新字典类型Datacell、字典类型项树、Datacell
    	queryNodeDictType(treeType.getSelectNode());
    }
    
    /*
	 * 功能：增加字典类型项回调函数
	 *
	 * return
	 */
    function callBackEntryAdd()
    {
    	if(treeEntry.getSelectNode().isRootNode())
    	{
    		//根节点刷新
    		onRefreshRoot();
    	}else{
    		//成功新增刷新树
    		onRefresh(treeEntry.getSelectNode());
    		
    		//更新Datacell
    		queryNodeDictEntry(treeEntry.getSelectNode());
    	}
    }
    
    /*
	 * 功能：修改业务字典类型
	 * param node
	 * return
	 */
    function updateDictType(node)
    {
    	var url = "com.bos.utp.tools.DictManager.flow";
    	url = url + "?_eosFlowAction=updateDictType";
    	url = url + "&eosDictType/dicttypeid=";
    	
    	var dicttypeid;
    	if(!node.isRootNode())
    	{
    		dicttypeid = node.getProperty("dicttypeid");
    	}
    	url = url + dicttypeid;
    	url += '&_ts='+(new Date()).getTime();   //防止IE缓存，在每次打开时加个时间差的参数
    	
    	showModalCenter(url, "", callBackTypeUpdate, 300, 145, '<b:message key="DictManager_l_update_type"/>');//修改字典类型
    }
    
    /*
	 * 功能：修改业务字典类型项
	 * param node
	 * return
	 */
    function updateDictEntry(node)
    {
    	var url = "com.bos.utp.tools.DictManager.flow";
    	url = url + "?_eosFlowAction=updateDictEntry";
    	
    	//获取业务字典类型代码
    	url = url + "&eosDictEntry/eosDictType/dicttypeid=";
    	var entity = $id("dictTrees").getRootNode().getEntity();
    	url = url + entity.getProperty('dicttypeid');
    	
    	//业务字典类型项代码
    	url = url + "&eosDictEntry/dictid=";
    	var parentid;
    	if(!node.isRootNode())
    	{
    		parentid = node.getProperty("dictid");
    	}else{
    		parentid = "";
    	}
    	url = url + parentid;
    	url += '&_ts='+(new Date()).getTime();   //防止IE缓存，在每次打开时加个时间差的参数
    	
    	showModalCenter(url, "", callBackEntryUpdate, 300, 200, '<b:message key="DictManager_l_update_type"/>'+'<b:message key="DictManager_l_item"/>');//修改字典类型
    }
    
    /*
	 * 功能：修改字典类型回调函数
	 *
	 * return
	 */
    function callBackTypeUpdate()
    {
    	//更新字典类型Datacell、字典类型项树、Datacell
    	queryNodeDictType(treeType.getSelectNode().getParent());
    	
    	//成功修改刷新树
    	onRefresh(treeType.getSelectNode().getParent());
    	
    }
    
    /*
	 * 功能：修改字典类型项回调函数
	 *
	 * return
	 */
    function callBackEntryUpdate()
    {
    	//更新Datacell
    	queryNodeDictEntry(treeEntry.getSelectNode().getParent());
    	
    	//成功修改刷新树
    	onRefresh(treeEntry.getSelectNode().getParent());
    }
    
    /*
	 * 功能：删除业务字典类型
	 * param node
	 * return
	 */
    function deleteDictType(node)
    {
    	var refresh = confirm('<b:message key="DictManager_m_delete_type_confirm1"/>'+'\n'+'<b:message key="DictManager_m_delete_type_confirm2"/>');//您确认删除当前选中类型吗？\n[注意]：删除选中类型时，会删除当前类型下的所有下级类型。
    	var reCode;
		if (refresh)
		{  
			//调用逻辑流
	    	var myAjax = new Ajax("com.bos.utp.tools.DictManager.deleteDictType.biz");
	    	
	    	//增加参数
	    	myAjax.addParam("dicttypeid",node.getProperty("dicttypeid"));
	    	
	    	//开始调用
	    	myAjax.submit();
	    	
	    	//取得调用后的结果(xml对象)
	    	var returnNode =myAjax.getResponseXMLDom();
	    	if(returnNode)
	    	{
	    		//获取指定的节点值
	    		reCode = myAjax.getValue("/root/data/reCode");
	    	}
		} else { 
			return false;
		}
		//判断刷新业务字典信息成败
    	if(reCode == 1)
    	{
    		alert('<b:message key="l_m_delete_success"/>');   //删除成功
    		
    		//更新字典类型Datacell、字典类型项树、Datacell
	    	queryNodeDictType(treeType.getSelectNode().getParent());
	    	
	    	//成功删除刷新树
	    	onRefresh(treeType.getSelectNode().getParent());
    	}else{
    		alert('<b:message key="DictManager_m_delete_message_type"/>');//删除类型失败。
    	}
    }
    
    /*
	 * 功能：删除业务字典类型项
	 * param node
	 * return
	 */
    function deleteDictEntry(node)
    {
    	var refresh = confirm('<b:message key="DictManager_m_delete_entry_confirm1"/>'+'\n'+'<b:message key="DictManager_m_delete_entry_confirm2"/>');//您确认删除当前选中类型项吗？\n[注意]：删除选中类型项时，会删除当前类型项下的所有下级类型项。
    	var reCode;
		if (refresh)
		{  
			//调用逻辑流
	    	var myAjax = new Ajax("com.bos.utp.tools.DictManager.deleteDictEntry.biz");
	    	
	    	//增加参数
	    	var entity = node.getEntity();
	    	myAjax.addParam("dictid",entity.getProperty("dictid"));
	    	myAjax.addParam("dicttypeid",entity.getProperty("dicttypeid"));
	    	//alert(entity.getProperty("eosDictType/dicttypeid"));
	    	myAjax.addParam("dicttypeid",dicttypeid);
	  
	    	
	    	//开始调用
	    	myAjax.submit();
	    	
	    	//取得调用后的结果(xml对象)
	    	var returnNode =myAjax.getResponseXMLDom();
	    	if(returnNode)
	    	{
	    		//获取指定的节点值
	    		reCode = myAjax.getValue("/root/data/reCode");
	    	}
		} else { 
			return false;
		}
		
		//判断刷新业务字典信息成败
    	if(reCode == 1)
    	{
    		alert('<b:message key="l_m_delete_success"/>');   //删除成功
    		
    		//更新Datacell
    		queryNodeDictEntry(treeEntry.getSelectNode().getParent());
    	
    		//成功删除刷新树
    		onRefresh(treeEntry.getSelectNode().getParent());
    	}else{
    		alert('<b:message key="DictManager_m_delete_message_entry"/>');//删除类型项失败。
    	}
    }
    
	/*
	 * 功能：加载“类型项”按钮
	 *
	 * return
	 */
	function showOperate()
	{
		var title = '<b:message key="DictManager_l_type_item"/>';//类型项(国际化)
		return "<img src='"+'<%=SkinUtil.getStyleFile("images/abf/table_multiple.png",request)%>'+"' title='" + title + "' onclick='reloadDictEntry()'>";
	}
	
	/*
	 * 功能：加载“是否封存”checkbox控件
	 * param value,entity,rowNo,cellNo,datacell 值,datacell实体,行,列,datacell对象
	 * return
	 */
	function showStatus(value,entity,rowNo,cellNo,datacell)
	{
		return checkCell(value,entity,rowNo,cellNo,datacell,"0","1");
	}
	
	/*
	 * 功能：加载checkbox控件
	 * param value,entity,rn,cn,datacell ,checkedValue,uncheckedValue 值,datacell实体,行,列,datacell对象,选中值,不选中值
	 * return
	 */
	function checkCell(value,entity,rn,cn,datacell ,checkedValue,uncheckedValue)
	{
	 	var checkFlag = '',_cvalue = uncheckedValue;
	 	if(value == checkedValue)
	 	{
		  	checkFlag = "checked";
		  	_cvalue = checkedValue;
	 	}
	 	
		 _cvalue = _cvalue||_cvalue === 0?_cvalue:'';
		 var funcname = 'fieldFunc_' + datacell.id + '_' + cn;
	 
		Datacell[funcname]=function(checkObj)
	 	{
		 	var cell = checkObj.parentNode
		    while(cell.tagName.toLowerCase() != "td")
		    {
		    	cell = cell.parentNode;
		    }
		  	if (checkObj.checked)
		  	{
		   		datacell.setCellValue(cell,checkedValue)
		  	}else{
		   		datacell.setCellValue(cell,uncheckedValue);
		  	}
	        datacell.refreshRow(cell.parentNode);
	        datacell.getEntity(cell.parentNode).status = Entity.STATUS_MODIFIED;
	        datacell.isModefied=true;
	 	}
	 	
	 	return "<input type=\"checkbox\" " + checkFlag + " value=\""+(''+_cvalue).replace(/\"/igm,"\\\"" ).replace(/\n/igm,"\\n" )+"\" "
	   		 + " onclick=\"Datacell['" + funcname + "'](this)\"  />";
	}
	
    /*
	 * 功能：业务字典类型重置
	 *
	 * return
	 */
    function onResetType()
    {
    	$name("dictType/_expr[2]/dicttypeid").value   = "";
    	$name("dictType/_expr[3]/dicttypename").value = "";
    }
    
    /*
	 * 功能：业务字典类型项重置
	 *
	 * return
	 */
    function onResetEntry()
    {
    	$name("dictEntry/_expr[3]/dictid").value   = "";
    	$name("dictEntry/_expr[4]/dictname").value = "";
    }
    
    /*
	 * 功能：刷新缓存中业务字典信息
	 *
	 * return
	 */
    function onRefreshSlow()
    {
    	var refresh = confirm('<b:message key="DictManager_m_refresh_slow_confirm"/>');//您确认需要刷新缓存中的业务字典信息吗？
    	var reCode;
		if (refresh)
		{  
			//调用逻辑流
	    	var myAjax = new Ajax("com.bos.utp.tools.DictManager.reloadDictType.biz");
	    	//开始调用
	    	myAjax.submit();
	    	//取得调用后的结果(xml对象)
	    	var returnNode =myAjax.getResponseXMLDom();
	    	if(returnNode)
	    	{
	    		//获取指定的节点值
	    		reCode = myAjax.getValue("/root/data/reCode");
	    	}
		} else { 
			return false;
		}
		//判断刷新业务字典信息成败
    	if(reCode == 1)
    	{
    		alert('<b:message key="DictManager_m_refresh_slow_succeed"/>');//刷新业务字典信息成功。
    	}else{
    		alert('<b:message key="DictManager_m_refresh_slow_failed"/>');//刷新业务字典信息失败。
    	}
    }
    
	/*
	 * 功能：Datacell提交后校验是否提交成功，成功刷新树
	 *
	 * return
	 */
	datacellEntry.afterSubmit = function(ajax)
	{
		var reCode = ajax.getValue("root/data/reCode");
		if(reCode == 1)
		{
			var node = $id("dictTrees").getSelectNode();
			if(node == null)
			{
				node = $id("dictTrees").getRootNode();
			}
			node.reloadChild();
		}else{
			alert('<b:message key="DictManager_m_datacell_entry_message"/>');//保存字典类型项数据异常。
		}
	}
	
	/*
	 * 功能：导出业务字典数据到Excel
	 *
	 * return
	 */
    function exportDict()
    {
    	var frm = $id("queryForm");
    	frm.action = "com.bos.utp.tools.DictManager.flow?_eosFlowAction=dictExport";
    	frm.submit();
    }
    
</script>
</html>

 
 