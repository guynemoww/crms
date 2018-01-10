<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): js1688
  - Date: 2014-09-23 09:37:23
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp" %>
<title>流程授权参数</title>
</head>
<body>
	<div class="nui-toolbar" style="border-bottom:0;">
		<a class="nui-button" iconCls="icon-search" onclick="exctRule()">计算规则</a>
		<a class="nui-button" iconCls="icon-search" onclick="query()">查询</a>
		<a class="nui-button" iconCls="icon-search" onclick="queryParam()">查看详细</a>
	</div>
	<div id="datagrid3" class="nui-datagrid" style="width:100%;height:auto;"  sortMode="client"
	    url="com.bos.pub.decision.processLogQuery.biz.ext" dataField="rules" 
	    allowAlternating="true" multiSelect="false" showEmptyText="true"
	    emptyText="没有查到数据" showReloadButton="false" showColumnsMenu="true"
	    onrowdblclick="" allowCellEdit="true" allowCellSelect="true" allowCellWrap="true"
	    sizeList="[10,20,50,100]" pageSize="10">
	    <div property="columns">
	    	<div type="checkcolumn">选择</div>
	        <div field="createdate" dateFormat="yyyy-MM-dd HH:mm:ss" >执行时间</div>
	        <div field="org">授权判断终批机构</div>
	     </div>
	</div>
 <h3>规则判断列表</h3>
    <div showCollapseButton="false" style="padding:5px;">
       <div id="datagrid2" class="nui-datagrid" 
       style="width:100%;height:auto" url="com.bos.bps.op.WorkFlowManager.getExceptionProcess.biz.ext"
        dataField="retitems"
		multiSelect="true"
		allowResize="true" showReloadButton="false" allowCellWrap="true"
		sizeList="[10]"  pageSize="10" sortMode="client">
		<div property="columns">			
			<div field="key" headerAlign="center" width="25%" allowSort="true" >规则名称</div>
			<div field="logName" headerAlign="center" allowSort="true" renderer="logName">规则判断依据</div> 
			<div field="value" headerAlign="center" allowSort="true" width="100">规则结果</div>
		</div>
		</div>
    </div>  
    	<h3>规则参数详细展示</h3>
	 <div showCollapseButton="false" style="padding:5px;">
       <div id="datagrid1" class="nui-datagrid" 
       style="width:100%;height:auto" url="com.bos.bps.op.WorkFlowManager.getExceptionProcess.biz.ext"
        dataField="retitems"
		multiSelect="true"
		allowResize="true" showReloadButton="false"
		sizeList="[10]"  pageSize="10" sortMode="client">
		<div property="columns">
			<div field="key" headerAlign="center" width="35%" allowSort="true" >规则名称</div>
			<div field="value" headerAlign="center" width="35%" allowSort="true" >规则值</div> 
		</div>
		</div>
    </div> 
    
    <div id="detailGrid_Form" style="display:none;">
        <div id="employee_grid" class="nui-datagrid" style="width:100%;height:150px;" >
            
            <div property="columns">
                <div field="loginname" width="120" headerAlign="center" allowSort="true">
                </div>                       
            </div>
        </div>    
    </div>
    
</body>
</html>
<script type="text/javascript">
	nui.parse();
	var grid = nui.get("datagrid3");
	grid.load({processInstId:'<%=request.getParameter("processInstId") %>'});
	var employee_grid = nui.get("employee_grid");
	var detailGrid_Form = document.getElementById("detailGrid_Form");
	
	function logName(e){
		if(e.row.logName == "" || e.row.logName == null) return;
		var str = e.row.logName.split(",");
		var strLog = "";
        
		for(var i=0;i<str.length;i++){
			strLog += i+1+"、"+str[i]+"<br>";
			strLog=strLog.replace("：false",":不通过");
		 	strLog=strLog.replace("：true",":通过");
		 	
		}
		return strLog;
	}
	
	function query(){
		grid.load({processInstId:'<%=request.getParameter("processInstId") %>'});
	}
	
	//计算规则
	function exctRule(){
	
		var ruleID= '<%=request.getParameter("ruleID")%>';
		if(null==ruleID || ''==ruleID || 'null'==ruleID){
		
			nui.alert("此环节不能计算规则！");
			return;
		}
		var json=nui.encode({'processInstId':'<%=request.getParameter("processInstId")%>','ruleId':ruleID});
    	$.ajax({
            url: "com.bos.bps.op.WorkFlowManager.excuteRule.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            async:false,
            contentType:'text/json',
            success: function (text) {
            
            	nui.alert(text.msg);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
        });
	
	}
	
	
	function splitStr(name){
		var jsons=[];
		var log = name.split("rule=");
		var logi=log[0].split(" ");
		
		var str = nui.encode(log[1]);
		var strs = str.split(";");
		var len=0;
		for(var i=0;i<logi.length;i++){
			var logy=logi[i].split("=");
			if(logy[0]!=""&&logy[0]!=null&&logy[1]!=""&&logy[1]!=null){
				json={};
				json["key"]=logy[0];
				json["value"]=logy[1];
				jsons[len]=json;
				len++;
			}else{
				len--;
			}
		}
		return jsons;
	}
	function splitStrs(name,rule){
		var jsons=[];
		var log = rule.split("rule=");
		var logi=name.split(" ");
		
		var str = nui.encode(log[1]);
		var strs = str.split(";");
		strs[0] = strs[0].substring(1);
		//alert(strs[0].split("=")[0].trim());
		var len=0;
		for(var i=0;i<logi.length;i++){
			var logy=logi[i].split("=");
			if(logy[0]!=""&&logy[0]!=null&&logy[1]!=""&&logy[1]!=null){
				json={};
				json["key"]=logy[0];
				for(var j=0;j<strs.length;j++){
					if(strs[j].split("=")[0].trim()==logy[0].trim()){
						json["logName"]=strs[j].split("=")[1];
					}
				}

				json["value"]=logy[1];
				if(json["key"] == "上级审批"){
					json["value"]="是";
					if(json["logName"] != null && json["logName"] != ""){
						json["logName"] = json["logName"].replace("：false","：true");
					}
				}
				jsons[len]=json;
				len++;
			}else{
				len--;
			}
		}
		return jsons;
	}
	//弹出框展示详细参数列表
	function queryParam(){
	 var row = grid.getSelected();
	 //alert(nui.encode(row));
        if (row){
       	 	var logname = row.logname;
       	 	var rule = row.rule;
			var grid1 = nui.get("datagrid1");
			var grid2 = nui.get("datagrid2");
			
			var map1=splitStr(logname);
			var map2=splitStrs(rule,logname);
			
				grid1.load({"gettotal":map1.length,"items":map1});
				grid2.load({"gettotal":map2.length,"items":map2});
        } else {
            nui.alert("请选中一条记录");
        }
	}
</script>