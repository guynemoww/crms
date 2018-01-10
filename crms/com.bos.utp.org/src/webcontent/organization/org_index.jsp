<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<!-- 
  - Author(s): YANGZHOU
  - Date: 2013-03-01 17:43:27
  - Description:
-->
<%@page import="com.eos.foundation.eoscommon.ResourcesMessageUtil"%>
<%
	// 机构条件查询
	String orgConditionQuery = ResourcesMessageUtil.getI18nResourceMessage("orgConditionQuery"); 
	// 机构代码
	String orgCode = ResourcesMessageUtil.getI18nResourceMessage("orgCode"); 
	// 机构名称
	String orgName = ResourcesMessageUtil.getI18nResourceMessage("orgName"); 
	// 机构类型
	String orgType = ResourcesMessageUtil.getI18nResourceMessage("orgType"); 
 	// 机构等级
	String orgLevel = ResourcesMessageUtil.getI18nResourceMessage("orgLevel"); 
 	// 机构层级
	String orgDegree = ResourcesMessageUtil.getI18nResourceMessage("orgDegree"); 
 	// 机构状态
	String orgStatus = ResourcesMessageUtil.getI18nResourceMessage("orgStatus"); 
%>
<style>
#table1 .tit{
	height: 10px;
    line-height: 10px;
}
#table1 td{
	height: 10px;
    line-height: 10px;
}
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@include file="/common/nui/common.jsp" %>
<link id="css_icon" rel="stylesheet" type="text/css" href="<%=contextPath%>/utp/org/css/org.css"/>
<title>机构列表</title>
<body>
 <div class="search-condition">
   <div class="list">
	 <div id="form1" style="padding:5px;">
		<table style="width:100%;table-layout:fixed;" id="table1" class="table" >
			<tr>
				<!--<td class="tit">机构代码：</td>-->
				<td class="tit nui-form-label">机构行号：</td>
				<td>
					<input class="nui-textbox" name="criteria._expr[1].orgcode" style="width:95%" vtype="maxLength:32"/>
					<input class="nui-hidden" name="criteria._expr[1]._op" value="like"/>
					<input class="nui-hidden" name="criteria._expr[1]._likeRule" value="all"/>
				</td>
				<td class="tit nui-form-label">机构名称：</td>
				<td>
					<input class="nui-textbox" name="criteria._expr[2].orgname" style="width:95%" vtype="maxLength:64"/>
					<input class="nui-hidden" name="criteria._expr[2]._op" value="like"/>
					<input class="nui-hidden" name="criteria._expr[2]._likeRule" value="all"/>
				</td>
				<!--
				<td class="tit">机构状态：</td>
				<td>
					<input name="criteria._expr[5].status" data="data" valueField="dictID" textField="dictName" 
						class="nui-dictcombobox" emptyText="全部" dictTypeId="COF_ORGSTATUS" style="width:95%"
						showNullItem="true" nullItemText="全部"/>
					<input class="nui-hidden" name="criteria._expr[5]._op" value="="/>
				</td>
				 -->
				<td rowspan="2" class="btn-wrap">
					<input class="nui-button" text="查询" iconCls="icon-search" onclick="search"/>
				</td>
			</tr>
			<!-- 					
			<tr>
				<td class="tit">机构等级：</td>
				<td>
					<input name="criteria._expr[3].orgdegree" data="data"  valueField="dictID" textField="dictName" 
						class="nui-dictcombobox" emptyText="全部" dictTypeId="COF_ORGDEGREE" style="width:95%"
						showNullItem="true" nullItemText="全部"/>
					<input class="nui-hidden" name="criteria._expr[3]._op" value="="/>
				</td>
				<td class="tit">机构类型：</td>
				<td>
					<input name="criteria._expr[4].orgtype" data="data"  valueField="dictID" textField="dictName" 
						class="nui-dictcombobox" emptyText="全部" nullItemText="全部" dictTypeId="COF_ORGTYPE" style="width:95%"
						showNullItem="true" nullItemText="全部"/>
					<input class="nui-hidden" name="criteria._expr[4]._op" value="="/>
				</td>
				<td style="border:0px" class="tit"></td>
			</tr>
			 -->									
		</table>
	</div>
  </div>
 </div>

	<div class="nui-fit" style="padding:5px;">
		<div id="datagrid1" class="nui-datagrid" style="width:100%;height:100%;parding:5px;" sortMode="client"	allowAlternating="true"
		    url="com.bos.utp.org.organization.queryOrg.biz.ext" dataField="treeNodes" sizeList="[10,20,50,100]">
		    <div property="columns">
		        <div type="indexcolumn"></div>                
		        <!--
		        <div field="orgcode" width="120" headerAlign="center" >机构代码</div>
		        <div field="orgname" width="120" headerAlign="center" >名称</div>
		         -->
		        <div field="orgcode" width="120" allowSort="true" headerAlign="center"  >机构行号</div>
		        <div field="orgname" width="120" allowSort="true" headerAlign="center" >机构名称</div>
		        <%--<div field="buno" width="120" allowSort="true" headerAlign="center" >BU号</div>--%>
		        <div field="linktel" width="120" allowSort="true" headerAlign="center" >联系电话</div>		  
		        <%--<div field="linkman" width="120" allowSort="true" headerAlign="center" >联系人</div>	--%>	        
		        <!--<div field="orglevel" width="120" headerAlign="center" >等级</div>    
		        <div field="orgtype" width="120" headerAlign="center" renderer="renderOrgtype">机构类型</div>    
		        <div field="startdate" width="120" headerAlign="center" >生效日期</div>    
		        <div field="enddate" width="120" headerAlign="center" >失效日期</div>-->    
		    </div>
		</div>
	</div>

<script type="text/javascript">
	nui.parse();
	
	var form = new nui.Form("#form1");
	var grid = nui.get("datagrid1");
    grid.load();
    grid.sortBy("orgcode", "desc");
	
	function search(){
		//校验
		form.validate();
        if (form.isValid()==false) return;
        
		var formData = form.getData(false, true);
		
		//var json = nui.encode(formData);
        grid.load(formData);
	}

    function renderOrgtype(e) {
		return nui.getDictText("COF_ORGTYPE",e.row.orgtype);
	}
</script>

</body>
</html>