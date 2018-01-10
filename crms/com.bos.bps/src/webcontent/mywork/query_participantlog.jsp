<%@page pageEncoding="UTF-8" import="com.bos.bps.util.CommonUtil,com.eos.data.datacontext.IUserObject,java.util.Map"%>
<html>
<!-- 
  - Author(s): js1688
  - Date: 2014-05-28 17:51:50
  - Description:
-->
<head>
<title>代办查询</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<%
		IUserObject user = CommonUtil.getIUserObject();
		String userid = user.getUserId();
		String orgName = user.getUserOrgName();
		Map map = user.getAttributes();
		String orgcode = (String)map.get("orgcode");
	%>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	
	<div class="nui-dynpanel" columns="4">
		<label>委托人名称：</label>
		<input name="tbWfmParticipantlog.srcUserName" required="false" class="nui-textbox nui-form-input" vtype="maxLength:200" />
		<label>代办人名称：</label>
		<input name="tbWfmParticipantlog.targerUserName" required="false" class="nui-textbox nui-form-input" vtype="maxLength:200" />
	</div>
</div>
				
<div class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
	<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
</div>
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.bps.util.TbWfmParticipantlog.queryTbWfmParticipantlog.biz.ext"
	dataField="tbWfmParticipantlogs"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="processId" headerAlign="center" allowSort="true" >流程编号</div>
		<div field="cusName" headerAlign="center" allowSort="true" >客户名称</div>
		<div field="bizType" headerAlign="center" renderer="onBizTypeRenderer" allowSort="true" >流程类型</div>
		<div field="srcUserName" headerAlign="center" allowSort="true" >委托人名称</div>
		<div field="targerUserName" headerAlign="center" allowSort="true" >代办人名称</div>
		<div field="operTime" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd HH:mm:ss">操作时间</div>
		<div field="status" headerAlign="center" allowSort="true" >操作</div>
		</div>
	</div>
</body>
  <script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	
    function search() {
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
        	grid.on("preload",function(e){
       		if (!e.data || e.data.length < 1)
       			return;
       		for (var i=0; i<e.data.length; i++){
       			
       			var status = e.data[i].status;
       			var operator=e.data[i].operator;
       			
				if('1' == status){		
       				e.data[i]['status']=operator=='<%=userid%>' ?'<a href="#" onclick="updateTarger('+status+');">发送</a>&nbsp<a href="#" onclick="updateTarger(4);">取消发送</a>':'待发送';
       			}else if('2' == status){
       				e.data[i]['status']=operator=='<%=userid%>' ?'<a href="#" onclick="updateTarger('+status+');">认领</a>&nbsp<a href="#" onclick="updateTarger(4);">取消认领</a>':'待认领';
       			}else if('3' == status){
       			    e.data[i]['status']='已认领';
       			}else if('4' == status){
       				e.data[i]['status']='已取消';
       			}
       		}
       });
    }
    function updateTarger(stat){
		var row=grid.getSelected();
			var name=stat=='1'?"发送":stat=='4'?"取消":"认领";
			nui.confirm("确定"+name+"吗?","确认",function(action){
	      	  if(action!="ok") return;
	      	  		if(stat=='4'){
	      	  			row.status=stat;
	      	  		}else{
						row.operator=stat=='1'?row.targerUserNum:row.operator;
						row.status=stat=='1'?2:3;
					}
					var json=nui.encode({'partlog':row});
						//项目数据库
		                $.ajax({
			                    url: "com.bos.bps.util.TbWfmParticipantlog.updateTbWfmParticipantlog.biz.ext",
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
		                var json1=nui.encode({'workitemId':row.workInstanceId,'personId':row.targerUserNum,"processId":row.processId,"reassUserId":row.targerUserNum,"reassUserName":row.targerUserName,"reassUserOrgCode":'<%=orgcode%>',"reassUserOrgName":'<%=orgName%>'});
		                $.ajax({
			                    url: "com.bos.bps.op.WorkFlowManager.reassignWorkItem.biz.ext",
				                type: 'POST',
				                data: json1,
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
    search();
    
    function onBizTypeRenderer(e){
    
    	return nui.getDictText("XD_WFCD0001",e.value);
    }
    
    function reset(){
		form.reset();
	}
	</script>
</html>