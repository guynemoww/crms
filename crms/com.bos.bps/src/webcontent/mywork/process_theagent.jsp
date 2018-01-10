<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): js1688
  - Date: 2014-05-29 10:25:22
  - Description:
-->
<head>
<title>指定流程代办</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<div id="form1" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
		<div class="nui-dynpanel" columns="6">
			<label class="nui-form-label">客户名称：</label>
			<input id="wi.cusName" name="wi.cusName"  class="nui-textbox nui-form-input" />
			<label class="nui-form-label">发起机构：</label>
			<input id="wi.orgid" name="wi.orgid"  allowInput="false" class="nui-buttonEdit" onbuttonclick="selectOrg"/>
			<label class="nui-form-label">发起人：</label>
			<input id="wi.createUserName" name="wi.createUserName" class="nui-textbox nui-form-input" />
			<label class="nui-form-label">流程编号：</label>
			<input id="wi.processId" name="wi.processId"  class="nui-textbox nui-form-input" />
			<label class="nui-form-label">业务类型：</label>
			<input id="wi.processDefName" name="wi.processDefName" data="data" valueField="dictID" textField="dictName" dictTypeId="XD_WFCD0001" class="nui-dictcombobox" />
			<label class="nui-form-label">创建时间：</label>
			<div>
			<input id="wi.startDate" name="wi.startDate" required="false" style="width:100px;"  class="nui-datepicker nui-form-input" />-
			<input id="wi.endDate" name="wi.endDate" required="false" style="width:100px;"  class="nui-datepicker nui-form-input" />
			</div>
		</div>
		<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
		    <a class="nui-button" iconCls="icon-search" onclick="query()">查询</a>
			<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
		</div>
	</div>	

	<div style="width:99.5%">
		<div class="nui-toolbar" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px">
			<a class="nui-button"   onclick="view()">处理</a>
		</div>
	</div>
	<div id="datagrid1" class="nui-datagrid" style="width:99.5%;height:auto;"  sortMode="client"
	    url="com.bos.bps.util.TbWfmProcessInstance.queryProcessScopByOrg.biz.ext" dataField="pis"
	    allowAlternating="true" multiSelect="false" showEmptyText="true"
	    emptyText="没有查到数据" showReloadButton="false" showColumnsMenu="true"
	    onrowdblclick="" allowCellEdit="true" allowCellSelect="true"
	    sizeList="[10,20,50,100]" pageSize="10">
	    <div property="columns">
	        <%--<div type="indexcolumn">序号</div>--%>
	        <div type="checkcolumn"></div>
	        <div field="processId" allowSort="true" width="" headerAlign="center">流程编号</div>
	        <div field="cusName" allowSort="true" width="" headerAlign="center">客户名称</div>
	        <div field="processinstancename" allowSort="true" width="" headerAlign="center">流程实例名称</div>   
	        <div field="createOrgName" allowSort="true" width="" headerAlign="center">发起机构</div>
	        <div field="createUserName" allowSort="true" width="" headerAlign="center">发起人</div> 
	        <div field="createTime" allowSort="true" width="" dateFormat="yyyy-MM-dd HH:mm:ss" headerAlign="center">创建时间</div>
	        <div field="lastupdatetime" allowSort="true" width="" dateFormat="yyyy-MM-dd HH:mm:ss" headerAlign="center">最近更新时间</div>
	        <div field="processStatus" allowSort="true" width="" renderer="onStatus"  headerAlign="center">流程状态</div>
	     </div>
	</div>
</body>
</html>
<script type="text/javascript">
	nui.parse();
	var grid = nui.get("datagrid1");
	var form = new nui.Form("#form1");
	function query(){
	   var o = form.getData();
       grid.load(o);
    }   
	query();
	//重置
	function reset(){
		form.reset();
	}
	
	function view(){
	
		var row = grid.getSelected();
		if(row){
		
			nui.open({
            url: nui.context + "/bps/mywork/workitem_reassign_list.jsp?processInstId="+row.processId,
            showMaxButton: false,
            title: "工作列表",
            width: 1000,
            height: 300,
            ondestroy: function (action) {
               
            }    
        	});
		
		}else{
		
			nui.alert("请选中一条记录！");
		}
	}
	
	//机构选择
	function selectOrg(){
	
		var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/sys/select_org_tree.jsp",
            showMaxButton: true,
            title: "选择机构",
            width: 350,
            height: 450,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.GetData();
                    data = nui.clone(data);
                    if (data) {
                    	self.orglevel=data.orglevel;
                        btnEdit.setValue(data.orgid);
                        btnEdit.setText(data.orgname);
                    }
                }
            }
        });      
	}
	
	function onStatus(e){
    	return nui.getDictText("XD_WFCD0003",e.value);
    }
</script>