<%@page pageEncoding="UTF-8" import="com.bos.bps.util.CommonUtil,com.eos.data.datacontext.IUserObject"%>
<%@include file="/common/nui/common.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>选择人员</title>
</head>
<body>
<%
IUserObject user = CommonUtil.getIUserObject();
String orgdegree = (String)user.getAttributes().get("orgdegree");
 %>
<div class="nui-toolbar" style="text-align:center;line-height:30px;" borderStyle="border:0;">
          <label >岗位名称：</label>
          <input id="activity" name="activityDefineId" textField="activityDefineName"  class="nui-combobox" style="width:60%;" 
				    	valueField="activityDefineId"  required="true" allowInput="false" 
						showNullItem="true" nullItemText="请选择.." emptyText="请选择..." onvaluechanged="queryOpertor()"/> 
</div>

<div id="datagrid1" class="nui-datagrid" style="width:100%;height:auto;"  sortMode="client"
	    url="com.bos.bps.op.WorkFlowManager.queryBpsUserByPosition.biz.ext" dataField="users"
	    allowAlternating="true" multiSelect="true" showEmptyText="true"
	    emptyText="没有查到数据" showReloadButton="false" showColumnsMenu="true"
	     allowCellEdit="true" allowCellSelect="true" 
	    sizeList="[10,20,50,100]" pageSize="10" showFooter="false" showPager="false"
	    >
        <div property="columns">
        	<div type="checkcolumn">选择</div>
            <div type="indexcolumn" >序号</div>
            <div field="ORGNAME" width="" headerAlign="center"  allowSort="true">机构名称</div> 
            <div field="USERID" width="" headerAlign="center" allowSort="true">操作员编号</div>    
            <div field="OPERATORNAME" width="" headerAlign="center" allowSort="true">操作员名称</div>                                            
            <%--<div field="DEPARTNAME" width="" headerAlign="center"  allowSort="true">部门名称</div> --%>
        </div>
</div>
<div class="nui-toolbar">
    <a class="nui-button"  iconCls="icon-ok" onclick="onOk()">确定</a>
    <span style="display:inline-block;width:25px;"></span>
    <a class="nui-button"  iconCls="icon-cancel" onclick="onCancel()">取消</a>
</div>
 
<script type="text/javascript">
	nui.parse();
	var grid = nui.get("datagrid1");
	//初始化岗位列表
	function getInit(){
		nui.get("activity").setData(nui.decode(nui.getParams()["map"]).WFActivityDefineId);
	}
	getInit();
	
	//查询操作人员
	function queryOpertor(){
	
		var activity = nui.get("activity").getValue();
		//var json = ;//nui.alert(json);return;
		grid.load({"map":nui.decode(nui.getParams()["map"]),"activityDefineId":activity});
	}
	
	function GetData() {
        var rows = grid.getSelecteds();
        rows[0].activityDefId= nui.get("activity").getValue();
        return rows;
    }
    
	function onRowDblClick(e) {
        onOk();
    }
   
    function CloseWindow(action) {
        if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
        else window.close();
    }

    function onOk() {
   		var rows = grid.getSelecteds();
   		//如果是小贷中心机构
   		if('2'=='<%=orgdegree %>'){
   			var activity = nui.get("activity").getValue();
   			var orglevel = activity.substr(1,1);
   			//是小贷中心，并且是总中心
   			if('2'==orglevel){
   				
   				//判断已经有几个人审批过了。如果没有，则要求选择两个，有则要求至少选择一个
   				//校验是否保存附属信息
   				var processId=nui.decode(nui.getParams()["map"]).processInstId;
		 		var json = {"processId":processId};
		   	    msg = exeRule("RBPS_0001","1",json);
		   	    if(null != msg && '' != msg){
			   	    if(rows.length<2){
						nui.alert("请至少选择两个用户！");
						return;
					}
		   	     }
   			}
   		}
   		
   		//其它情况或机构
   		if(rows.length<1){
		
			nui.alert("请选择一个用户！");
			return;
		}
        CloseWindow("ok");
    }
    function onCancel() {
        CloseWindow("cancel");
    }    

</script>

</body>
</html>