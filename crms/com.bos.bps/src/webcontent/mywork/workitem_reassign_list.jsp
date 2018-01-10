<%@page pageEncoding="UTF-8" %>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): lijf
  - Date: 2014-08-28 17:28:11
  - Description:代办管理页面。分配给流程管理员
-->
<head>
<title>代办管理-工作项列表页面</title>
</head>
<body>
	<div style="width:99.5%">
		<div class="nui-toolbar" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px">
			<a class="nui-button"   onclick="reassignWorkItem()">代办</a>
		</div>
	</div>
	<div id="datagrid1" class="nui-datagrid" style="width:99.5%;height:80%;"  sortMode="client"
	    url="com.bos.bps.op.WorkFlowManager.queryCurrentWorkitemsByProcessId.biz.ext" dataField="list"
	    allowAlternating="true" multiSelect="false" showEmptyText="true"
	    emptyText="没有查到数据"  showPager="false" showReloadButton="false" showColumnsMenu="true"
	    sizeList="[10,20,50,100]" pageSize="10">
	    <div property="columns">
	        <%--<div type="indexcolumn">序号</div>--%>
	        <div type="checkcolumn">选择</div>
	        <div field="processInstName" allowSort="true" width="" headerAlign="center">流程实例名称</div>   
	        <div field="workItemId" allowSort="true" width="" headerAlign="center">工作项ID</div>
	        <div field="activityInstName" allowSort="true" width="" headerAlign="center">工作项名称</div>
	        <div field="participant" allowSort="true" width="" headerAlign="center">参与者ID</div>
	        <div field="partiName" allowSort="true" width="" headerAlign="center">参与者名称</div> 
	        <div field="createTime" allowSort="true" width="" dateFormat="yyyy-MM-dd HH:mm:ss" headerAlign="center">开始时间</div>
	     </div>
	</div>
</body>
<script type="text/javascript">
	nui.parse();
	
	var grid = nui.get("datagrid1");
	function query(){//对公单一客户查询
       grid.load({"processInstId":'<%=request.getParameter("processInstId")%>'});
    }   
	query();
	
	//改派参与人
	function reassignWorkItem(){
	
		var row = grid.getSelected();
        if (row) {
            
            var json = nui.encode({"row":row});
            $.ajax({
                url: "com.bos.bps.op.WorkFlowManager.initReassginWorkItemMap.biz.ext",
                type: 'POST',
                data: json,
                cache: false,
                contentType:'text/json',
                success: function (data) {
                	window['bps_attr_map'] = data.map;
                	//选择人员，进行认领
                	selectEmpOrg(row.processInstId,row.workItemId,row.participant,row.partiName);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    nui.alert(jqXHR.responseText);
                }
            });
            
        }else{
        
        	nui.alert("请选中一条记录！");
        }
	}
	
	//选择机构
    function selectEmpOrg(processInstId,workItemId,participant,participantName) {
        nui.open({
            url: nui.context + "/bps/mywork/select_bps_user.jsp?flag=2&map="+escape(nui.encode(window['bps_attr_map'])),
            showMaxButton: false,
            title: "选择人员",
            width: 350,
            height: 450,
            ondestroy: function (action) {
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.GetData();
                    data = nui.clone(data);
                    if (data) {
                    	if(participant == data.USERID){
                    	
                    		nui.alert("指定代办的参与者与当前的参与者相同，不需要操作代办！");
                    	}else{
                    	
                    		var json = nui.encode({"workitemId":workItemId,"personId":data.USERID,"processId":processInstId,"reassUserId":data.USERID,"reassUserName":data.OPERATORNAME,"reassUserOrgCode":data.ORGCODE,"reassUserOrgName":data.ORGNAME});
				            $.ajax({
				                url: "com.bos.bps.op.WorkFlowManager.reassignWorkItem.biz.ext",
				                type: 'POST',
				                data: json,
				                cache: false,
				                contentType:'text/json',
				                success: function (text) {
				                	
				                	if (null!=text.map && 'E00001'==text.map.errCode) {
				                		nui.alert("代办失败，错误信息:"+text.map.errInfo);
				                	}else{
				                		//插入变更参与人日志
				                		saveParticipantLog('<%=request.getParameter("processInstId")%>',workItemId,data,'3',participant,participantName);
				                		nui.alert("代办成功！");
				                		grid.reload();
				                	}
				                },
				                error: function (jqXHR, textStatus, errorThrown) {
				                    nui.alert(jqXHR.responseText);
				                }
				            });
                    	}
                    }
                }
            }
        });
    }
    
    //保存参与人代办日志
    function saveParticipantLog(processId,workItemId,data,status,participant,participantName){
    
    	var ret =null;
    	var json = nui.encode({"processId":processId,"workitemId":workItemId,"personId":data.USERID,"personName":data.OPERATORNAME,"status":status,"srcUserNum":participant,"srcUserName":participantName});
  		$.ajax({
            url: "com.bos.bps.util.TbWfmParticipantlog.saveTbWfmParticipantlogByMgr.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            async: false,
            contentType:'text/json',
            success: function (data) {
            	ret = data.ret;
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
		});
		return ret;
    }
	
</script>	
</html>