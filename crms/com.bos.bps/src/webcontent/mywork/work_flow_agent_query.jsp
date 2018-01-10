<%@page pageEncoding="UTF-8" import="com.bos.bps.util.CommonUtil,com.eos.data.datacontext.IUserObject"%>

<html>
<!-- 
  - Author(s): lijianfei
  - Date: 2014-05-29 10:53:41
  - Description:
-->
<head>
<title>代理列表查询</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<%
		IUserObject user = CommonUtil.getIUserObject();
		String userid = user.getUserId();
	%>
	<div class="nui-toolbar" style="border-bottom:0;">
		<a class="nui-button" iconCls="icon-add" onclick="add()">增加</a>
		<a class="nui-button" iconCls="icon-edit"  id="edit" onclick="edit(0)">修改</a>
		<a class="nui-button" iconCls="icon-search" onclick="edit(1)">查看</a>
		<a class="nui-button" iconCls="icon-remove" id="remove" onclick="remove()">删除</a>
	</div>
	<div id="datagrid1" class="nui-datagrid" style="width:100%;height:auto;"  sortMode="client"
	    url="com.bos.bps.op.WorkFlowManager.queryAgentByAgentToLocal.biz.ext" dataField="agents"
	    allowAlternating="true" multiSelect="false" showEmptyText="true" onrowclick="rowclick"
	    emptyText="没有查到数据" showReloadButton="false" showColumnsMenu="true"
	    onrowdblclick="" allowCellEdit="true" allowCellSelect="true"
	    sizeList="[10,20,50,100]" pageSize="10">
	    <div property="columns">
	    	<div type="checkcolumn">选择</div>
	        <div field="agentfrom" allowSort="true" width="" headerAlign="center" renderer="onGetAgentfromUserName">委托人</div> 
	        <div field="agentto" allowSort="true" width="" headerAlign="center" renderer="onGetAgenttoUserName">代理人</div>   
	        <div field="agenttype" allowSort="true" width="" headerAlign="center" renderer="onagenttypeRenderer" >代理方式</div>
	        <div field="starttime" allowSort="true" width="" headerAlign="center" dateFormat="yyyy-MM-dd HH:mm:ss">生效时间</div>
	        <div field="endtime" allowSort="true" width="" headerAlign="center" dateFormat="yyyy-MM-dd HH:mm:ss" >终止时间</div>     
	        <div field="operator" allowSort="true" width="" headerAlign="center" renderer="onGetOperatorUserName">当前操作员</div>
	        <div field="status" allowSort="true" width="" headerAlign="center" >状态</div>  
	     </div>
	</div>
</body>
</html>
<script type="text/javascript">
	nui.parse();
	var grid = nui.get("datagrid1");
	function onGetAgentfromUserName(e){
		return git.getUserName(e.row.agentfrom);
	}
	function onGetOperatorUserName(e){
		return git.getUserName(e.row.operator);
	}
	function onGetAgenttoUserName(e){
		return git.getUserName(e.row.agentto);
	}
    function search() {
        	grid.load();
        	grid.on("preload",function(e){
       		if (!e.data || e.data.length < 1)
       			return;
       		for (var i=0; i<e.data.length; i++){
       		
       			var status = e.data[i].status;
       			var operator=e.data[i].operator;
				if('1' == status){		
       				e.data[i]['status']=operator==<%=userid %>?'<a href="#" onclick="updateAgent('+status+');">待发送</a>&nbsp<a href="#" onclick="updateAgent(4);">取消发送</a>':'待发送';
       			}else if('2' == status){
       				e.data[i]['status']=operator==<%=userid %>?'<a href="#" onclick="updateAgent('+status+');">待认领</a>&nbsp<a href="#" onclick="updateAgent(4);">取消认领</a>':'待认领';
       			}else if('3' == status){
       			    e.data[i]['status']='已认领';
       			}else if('4' == status){
       				e.data[i]['status']='已取消';
       			}
       		}
       });
    }
    search();
    function reset(){
		form.reset();
	}
	function rowclick(e){
		if(e.record.status=="已认领"){
			nui.get("remove").hide();
			nui.get("edit").hide();
		}else{
			nui.get("remove").show();
			nui.get("edit").show();
		}
	}
	function updateAgent(stat){
		var row=grid.getSelected();
			var name=stat=='1'?"发送":stat=='4'?"取消":"认领";
			nui.confirm("确定"+name+"吗?","确认",function(action){
	      	  if(action!="ok") return;
	      	  		if(stat=='4'){
	      	  			row.status=stat;
	      	  		}else{
						row.operator=stat=='1'?row.agentto:row.operator;
						row.status=stat=='1'?2:3;
						row.starttime=nui.formatDate(row.starttime,"yyyy-MM-dd HH:mm:ss");
						row.endtime=nui.formatDate(row.endtime,"yyyy-MM-dd HH:mm:ss");
					}
					var json=nui.encode({'agent':row});
						//项目数据库
		                $.ajax({
			                    url: "com.bos.bps.op.WorkFlowManager.modifyAgentCountLocal.biz.ext",
				                type: 'POST',
				                data: json,
				                cache: false,
				                contentType:'text/json',
			                    success: function (text) {
			                      	  search();
			                    },
			                    error: function () {
			                    	nui.alert("操作失败！");
			                    }
		                	});
		                //同步流程平台
		                if(row.status==3){
		                $.ajax({
			                    url: "com.bos.bps.op.WorkFlowManager.createAgent.biz.ext",
				                type: 'POST',
				                data: json,
				                cache: false,
				                contentType:'text/json',
			                    success: function (text) {
			                      	  search();
			                    },
			                    error: function () {
			                    	nui.alert("操作失败！");
			                    }
		                	});
		                }
			});
	}
	var agenttypes = [{ id: 'ALL', text: '完全代理' }, { id: 'PART', text: '部分代理'}];        
    function onagenttypeRenderer(e) {
        for (var i = 0, l = agenttypes.length; i < l; i++) {
            var g = agenttypes[i];
            if (g.id == e.value) return g.text;
        }
        return "";
    }
	
	function add() {
        nui.open({
            url: nui.context+"/bps/mywork/work_flow_agent_add.jsp",
            title: "新增", 
            width: 800, 
        	height: 400,
        	allowResize:true,
        	showMaxButton: true,
            ondestroy: function (action) {
                if(action=="ok"){
                    search();
                }
            }
        });
    }
	//弹出选择代理人
	function selectAgent(){
		var btnEdit = this;
        nui.open({
            url: nui.context + "/bps/mywork/userogr_trre.jsp",
            title: "选择代理人",
            width: 800,
            height: 450,
            ondestroy: function (action) {   
				 if (action!="close"&&action.result == "ok") {
				 	nui.get("agentto").setValue(action.data.empcode);
				 	nui.get("agentto").setText(action.data.empname);
				 }
        	}
        });  
	}
    	//弹出选择委托人
	function agentfrom(){
		var btnEdit = this;
        nui.open({
            url: nui.context + "/bps/mywork/userogr_trre.jsp",
            title: "选择代理人",
            width: 800,
            height: 450,
            ondestroy: function (action) {   
				 if (action!="close"&&action.result == "ok") {
				 	nui.get("agentfrom").setValue(action.data.empcode);
				 	nui.get("agentfrom").setText(action.data.empname);
				 }
        	}
        });  
	}
    function edit(v){
        var row = grid.getSelected();
        if (row){
       	 	var agentid = row.agentid;
            nui.open({
                url: nui.context+"/bps/mywork/work_flow_agent_edit.jsp?agentid="+agentid+"&view="+v,
                title: "编辑查看", 
                width: 800,
        		height: 400,
                allowResize:true,
        		showMaxButton: true,
                onload: function () {
                   
                },
                ondestroy: function (action) {
                    if(action=="ok"){
                        search();
               	 	}
                }
            });
        } else {
            nui.alert("请选中一条记录");
        }
        
    }
     function remove() {
        var row = grid.getSelected();
        if (row) {
        	var agentid = row.agentid;
        	nui.confirm("确定删除吗？","确认",function(action){
            	if(action!="ok") return;
            	var json=nui.encode({'agentid':agentid});
                $.ajax({
                    url: "com.bos.bps.op.WorkFlowManager.deleteAgentBatchLocal.biz.ext",
	                type: 'POST',
	                data: json,
	                cache: false,
	                contentType:'text/json',
                    success: function (text) {
                      	  search();
                    },
                    error: function () {
                    	nui.alert("操作失败！");
                    }
                });
            }); 
        } else {
            alert("请选中一条记录");
        }
    }
    
</script>