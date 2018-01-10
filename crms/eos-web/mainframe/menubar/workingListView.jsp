<%@page pageEncoding="UTF-8" import="com.bos.bps.util.CommonUtil,com.eos.data.datacontext.IUserObject"%>
<html>
<!-- 
  - Author(s): 吕健豪
  - Date: 2014-6-27 12:42:24
  - Description:展示待执行工作项，以及待领取工作项
-->
<head>
<title>待办任务/工作列表 - 我的工作</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<%
		IUserObject user = CommonUtil.getIUserObject();
		String userid = user.getUserId();
	%>
	<div id ="tool" class="nui-toolbar" style="border-bottom:0;">
		<label style="float:left;">流程类型:</label>
		<a  id="flowTypeCd" style="float:left;margin-top: 3px;" onvaluechanged="changebox" repeatItems="1000" textField="text" multiSelect="false" valueField="id"  class="nui-checkboxlist"></a >
		<!-- <a class="nui-button" style="float: left;" iconCls="icon-ok" onclick="reassignWorkItem()">代办</a> -->
	</div>
	<div id="datagrid1" class="nui-datagrid" style="width:100%;height:100%;"  sortMode="client"
	    url="com.bos.bps.op.WorkFlowManager.queryWorkingList.biz.ext" dataField="list"
	    allowAlternating="true" multiSelect="false" showEmptyText="true"
	    emptyText="没有查到数据" showReloadButton="false" showColumnsMenu="true"
	    onrowdblclick="" allowCellEdit="true" allowCellSelect="true"
	    sizeList="[10,20,50,100]" pageSize="6">
	    <div property="columns">
	    	<%--<div type="checkcolumn"></div>--%>
	        <%--<div type="indexcolumn">序号</div>--%>
	        <div field="processInstId" allowSort="true" width="" headerAlign="center">流程编号</div> 
	        <div field="cusName" allowSort="true" width="" headerAlign="center">客户名称</div> 
	        <div field="processInstName" allowSort="true" width="15%" headerAlign="center">流程实例名称</div>   
	        <div field="wfCreateUserName" allowSort="true" width="100px" headerAlign="center">发起人</div> 
	        <div field="wfCreateOrgName" allowSort="true" width="" headerAlign="center">发起机构</div>
	        <div field="createTime" allowSort="true" width="" headerAlign="center" dateFormat="yyyy-MM-dd HH:mm:ss" >创建时间</div>
	        <div field="activityInstName" allowSort="true" width="" headerAlign="center" >当前活动</div>
	        <div field="wfReadStatus" renderer="onReadStatusRenderer" allowSort="true" width="100px" headerAlign="center" >阅读标志</div>      
	        <div field="op" autoEscape="false" headerAlign="center">操作</div>
	     </div>
	</div>
<script type="text/javascript">
	nui.parse();
	//待办列表
	nui.get("tool").hide();
	var grid = nui.get("datagrid1");
	function query(flowTypeCdValue){
	   //var form = new nui.Form("#form1");
	   //var o = form.getData();
	   var flowTypeCd=flowTypeCdValue==''||flowTypeCdValue==""||flowTypeCdValue==null?'<%=request.getParameter("flowTypeCd") %>':flowTypeCdValue;
       grid.load({"module":flowTypeCd});
	}
	grid.on("preload",function(e){
       		if (!e.data || e.data.length < 1)
       			return;
       		for (var i=0; i<e.data.length; i++){
       			if(null !=e.data[i].cusName && ''!=e.data[i].cusName){
       			
       				e.data[i]['cusName']='<a href="#" onclick="toGoCustDetail(\''+ e.data[i].custId+ '\');">'+e.data[i].cusName+'</a>';
       			}
       			var state = e.data[i].currentState;
       			if('10' == state){
       				e.data[i]['op']='<a href="#" onclick="getWorItem({bizId:\''
       				+e.data[i].bizId
       				+'\'},'
       				+i
       				+');">执行</a>';
       			}else if('4' == state){
       				e.data[i]['op']='<a href="#" onclick="updateWorkItem({bizId:\''
       				+e.data[i].bizId
       				+'\'},'
       				+i
       				+');">认领执行</a>';
       			}
       			
       		}
       });
	query();
	//流程类型改变
	function changebox(){
		query(nui.get("flowTypeCd").getValue());
	}
	
	
	//执行工作项事件，进行审批
	function getWorItem(e, idx) {
		var w=self.parent ? self.parent : self;
		var it = grid.getRow(idx);
		//var it = grid.getSelected();
		var processDefName = it.processDefName;
		var activityDefId = it.activityDefId;
		var processInstId = it.processInstId;
		if (!!it) {
			var jsonstr=nui.encode({'processid':processInstId,'workitemId':it.workItemId});
		$.ajax({
                url: "com.bos.bps.util.TbWfmProcessInstance.chackProcessStatus.biz.ext",
                type: 'POST',
                data: jsonstr,
                cache: false,
                contentType:'text/json',
                success: function (data) {//alert(nui.encode(data.map));return;
                	if(null!=data.map && "waiting"==data.map.status){
                		nui.alert("当前流程已被挂起:挂起意见["+data.map.opinion+"]","提示");
                		return;
                	}
                	if(null!=data.map && "claiming"==data.map.status){
                		nui.alert("该待办工作项，已经发送变更申请，正在等待["+data.map.opinion+"]确认,不能点击执行！","提示");
                		return;
                	}
                	if(null!=data.map && "exception"==data.map.status){
                		nui.alert("数据异常，原因是：["+data.map.opinion+"]，请联系管理员处理！","提示");
                		return;
                	}
                	
					var json = nui.encode({"processDefName":processDefName,"activityDefId":activityDefId,"processInstId":processInstId,"it":it});
		            $.ajax({
		                url: "com.bos.bps.util.TbWfmWorkItemInstance.insertTbWfmWorkitemInstance.biz.ext",
		                type: 'POST',
		                data: json,
		                cache: false,
		                contentType:'text/json',
		                success: function (data) {//alert(nui.encode(data.map));return;
		                	if(null!=data.workiteminstanceQuery && null!=data.workiteminstanceQuery.userNum && '<%=userid %>'!=data.workiteminstanceQuery.userNum){
		                		data.itemInfo=null;
		                		nui.alert("当前这条待办["+git.getUserName(data.workiteminstanceQuery.userNum)+"]正在操作");
		                	}
		                	if (null != data.itemInfo) {
		                	//w.location.replace
		                	var urlobj=nui.context+"/bps/mywork/work_flow_top.jsp?bizId="+e.bizId+"&processInstId="+processInstId
		                		+"&workItemId="+it.workItemId+"&viewUrl="+data.itemInfo.viewUrl+"&conclusion="+data.itemInfo.finalJudge
		                		+"&ruleID="+data.itemInfo.ruleId+"&activityDefId="+activityDefId+"&activityInstId="+it.activityInstId
		                		+"&processDefName="+processDefName+"&activityInstName="+it.activityInstName+"&selectType="+data.itemInfo.selectType
		                		+"&orgLvCd="+data.itemInfo.orgLvCd+"&bizType="+it.bizType+"&doUrl="+data.itemInfo.doUrl
		                		+"&workitemMappingId="+data.itemInfo.workitemMappingId+"&approveType="+it.approveType
		                		+"&templateVersion="+data.itemInfo.templateVersion+"&userVariable="+data.itemInfo.userVariable
		                		+"&startTime="+nui.formatDate(it.startTime,"yyyy-MM-dd HH:mm:ss");
		                		w.location.replace(urlobj);
		                	}
		                },
		                error: function (jqXHR, textStatus, errorThrown) {
		                    nui.alert(jqXHR.responseText);
		                }
		            });
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    nui.alert(jqXHR.responseText);
                }
            });

            
		}
		
		//w.location.href=nui.context+'/csm/corp/eval/eval_corp_tree.jsp?bizId='+e.bizId;
	}
	
	//认领工作项事件，更新审批人
	function updateWorkItem(e, idx){
	
		var w=self.parent ? self.parent : self;
		var it = grid.getRow(idx);
		//var it = grid.getSelected();
		if (null != it) {
		
			var json = nui.encode({"it":it});
            $.ajax({
                url: "com.bos.bps.op.WorkFlowManager.assignWorkItemToSelf.biz.ext",
                type: 'POST',
                data: json,
                cache: false,
                contentType:'text/json',
                success: function (data) {
                	if ("1"==data.retflag) {
                		//w.location.reload();
                		getWorItem({bizId:e.bizId},idx);
                	}else{
                		 nui.alert("认领工作项失败！");
                	}
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    nui.alert(jqXHR.responseText);
                }
            });
		
		}
	
	}
	
    var ReadStatus = [{ id: 1, text: '未读' }, { id: 2, text: '已读'}];
    function onReadStatusRenderer(e) {
        for (var i = 0, l = ReadStatus.length; i < l; i++) {
            var g = ReadStatus[i];
            if (g.id == e.value) return g.text;
        }
        return "";
    }
	
	function renderType(e) {
		return nui.getDictText("flow_type_cd",e.row.wfBizType);
	}
	function reset() {
		var form = new nui.Form("#form1");
		form.setData({});
	}
</script>
</body>
</html>