<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;">
<div title="批量监控" >
<center>
<form id="form1" action="" method="post" enctype="multipart/form-data" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
	<div class="nui-dynpanel" columns="6">

		<label>任务名称：</label>
		<input name="map/jobName" required="false" class="nui-textbox nui-form-input" />

		<label>任务状态：</label>
		<input name="map/jobStatus" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:20"
		data="data" valueField="dictID" textField="dictName" dictTypeId="XD_RZCD0006" />

		<!-- <label>批量组名：</label>
		<input name="map/jobBatchgroup" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:20"
		data="data" valueField="dictID" textField="dictName" dictTypeId="nightGroup" /> -->
		
		<!-- 
		<label>刷新间隔时间(0值不刷新，单位：秒)：</label>
		<input id="time" required="false" class="nui-textbox"  value="60" style="width:30px;"/>
		 -->
		
		<label>批量日期：</label> 
		<input name="map/jobBatchdate" required="false" class="nui-datepicker" format="yyyy-MM-dd"  showtime="false" value="<%=GitUtil.getBusiDateStr()%>"/>
		
		<label>任务开始时间起：</label> 
		<input name="map/minJobStarttime" required="false" class="nui-datepicker" format="yyyy-MM-dd HH:mm"  showtime="true" />
		
       	<label>任务开始时间止：</label> 
       	<input name="map/maxJobStarttime" required="false" class="nui-datepicker" format="yyyy-MM-dd HH:mm" showtime="true"/>	
       	<input name="map.jobCode" id ="map.jobCode" required="false" class="nui-hidden nui-form-input"/>	
       	 
	</div>
	<div class="nui-toolbar" style="text-align:right;border:none">
	    <a class="nui-button" style="margin-right:5px;height:21px;" iconCls="icon-search" onclick="search()">查询</a>
		<a class="nui-button" style="margin-right:5px;height:21px" iconCls="icon-reset" onclick="reset()">重置</a>
	</div>
</form>


<div style="width:99.5%">				
	<div class="nui-toolbar" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px">
		<a class="nui-button"  iconCls="icon-node" onclick="edit(1)" id="batch">查看日志</a>
		<a class="nui-button"  iconCls="icon-node" onclick="breakTask()" id="breakTask">中断运行</a>
		<!-- <a class="nui-button"  iconCls="icon-node" onclick="edit(0)" id="batchMonitor">查看监控日志</a>
		<a class="nui-button"  iconCls="icon-node" onclick="edit(4)" id="crd">查看额度日志</a>
		<a class="nui-button"  iconCls="icon-node" onclick="edit(2)" id="loadData">查看导数日志</a>
		<a class="nui-button"  iconCls="icon-node" onclick="edit(3)" id="dataDrawOut">查看卸数日志</a> -->
	</div>
</div>

	    
<div id="grid1" class="nui-datagrid" style="width:99.5%;height:auto;" 
	url="com.bos.batch.acbatchmonitorbiz.getAcBatchmonitorList.biz.ext"
	dataField="acBatchmonitors" allowAlternating="true"
	allowResize="true" showReloadButton="false" onrowclick=""
	sizeList="[10,20,50,100]" multiSelect="false" pageSize="50" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div type="indexcolumn">序号</div>
		<div field="jobCode" headerAlign="center" allowSort="true" >任务代码</div>
		<div field="jobName" headerAlign="center" allowSort="true" >任务名称</div>
		<!-- <div field="jobBatchgroup" headerAlign="center" allowSort="true" dictTypeId="nightGroup">任务组别</div> -->
		<div field="jobBatchorder" headerAlign="center" allowSort="true">任务排序</div>
		<div field="jobStatus" headerAlign="center" allowSort="true" dictTypeId="XD_RZCD0006">任务状态</div>
		<div field="jobBatchdate" headerAlign="center" allowSort="true" >批量日期</div>
		<div field="jobStarttime" headerAlign="center" allowSort="true" dateformat="yyyy-MM-dd HH:mm:ss">开始时间</div>
		<div field="jobEndtime" headerAlign="center" allowSort="true" dateformat="yyyy-MM-dd HH:mm:ss">结束时间</div>
		<div field="jobTemp1" headerAlign="center" allowSort="true">耗时（分钟）</div>
		<!-- <div field="jobLogfilepath" headerAlign="center" allowSort="true" >日志链接</div> -->
	</div>
</div>

<div id="logWindow" class="nui-window" title="Window" style="width:650px;"
    showModal="true" allowResize="true" allowDrag="true" >
    <div id="logform" class="nui-form" >
        <input class="nui-hidden" id="jobId" name="acbatchmonitor.jobId"/>
        <div class="nui-dynpanel" columns="2">
			<label>任务名称：</label>
			<input name="acbatchmonitor.jobName" class="nui-form-input" disabled="true"/>
			<label>任务描述：</label>
			<input name="acbatchmonitor.jobDes" class="nui-form-input" />
			<label>日志描述：</label>
			<input name="acbatchmonitor.fileString"  class="nui-textArea" />
        </div> 
    </div>
    <div class="nui-toolbar" style="border-bottom:0;text-align:center;">
		<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
	</div>
</div>

</center>
	</div>
	</div>
			
    <script type="text/javascript">
    
    
    /**快捷键设置*/
    document.onkeydown=function(event){
            var e = event || window.event || arguments.callee.caller.arguments[0];
            if(e && e.keyCode==27){ // 按 Esc 
                //要做的事情
                
              }
            if(e && e.keyCode==113){ // 按 F2 
                 //导出Excel表格(F2)
                exportEmp();
               }    
            if(e && e.keyCode==46){ // 按 Delete
                 //要做的事情
               }           
             if(e && e.keyCode==13){ // enter 键
                 //要做的事情
            }
        };
    
 	nui.parse();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	var jobCode = "<%=request.getParameter("jobCode")%>";
    var srcFlag = "<%=request.getParameter("srcFlag")%>";
    
    if(srcFlag=="0"){
    	nui.get("map.jobCode").setValue(jobCode);
    }
    
	grid.on("drawcell", function (e) {
	    var record = e.record,
	        column = e.column,
	        field = e.field,
	        value = e.value;
	    
	    //格式化日期
	    if (field == "jobBatchdate") {
	        if (nui.isDate(value)) e.cellHtml = nui.formatDate(value, "yyyy-MM-dd");
	    }
	    
	    //action列，超连接操作按钮
	    if (field == "jobLogfilepath") {
	        e.cellStyle = "text-align:center";
	        e.cellHtml = '<button onclick=\"view();\">查看日志</button>';
	    }
	
	});

	function edit(v) {
        var row = grid.getSelected();
        v=nui.encode(v);
        if(v == 1) {
        	editTitle = '查看逻辑流日志';
        } else if(v == 2) {
        	editTitle = '查看导数日志';
        } else if(v == 3) {
        	editTitle = '查看卸数日志';
        } else if(v == 0) {
        	editTitle = '查看监控日志';
        } else if(v == 4) {
        	editTitle = '查看额度日志';
        }
        
        if(v=='0' || v=='4') {
			nui.open({
            url: "batch/monitor/showLog.jsp?v="+v,
            title: editTitle, 
            width: 800,
    		height: 500,
            allowResize:true,
    		showMaxButton: true,
            onload: function () {
                var iframe = this.getIFrameEl();
                var data = row;
            }});
			return;
        }
        
        if (row) {
           nui.open({
            url: "batch/monitor/showLog.jsp?jobId="+row.jobId+"&v=null",
            title: editTitle, 
            width: 800,
    		height: 500,
            allowResize:true,
    		showMaxButton: true,
            onload: function () {
                var iframe = this.getIFrameEl();
                var data = row;
            }});
        } else {
            alert("请选中一条记录");
        }
        
    }
    //查看日志按钮
	function view(jobId) {
		//查看逻辑流日志
		edit(1);
<%--        var row = grid.getSelected();
        
        nui.open({
            url: "batch/monitor/showLog.jsp?jobId="+row.jobId,
            title: "查看日志", 
            width: 800,
    		height: 500,
            allowResize:true,
    		showMaxButton: true,
            onload: function () {
                var iframe = this.getIFrameEl();
                var data = row;
            }
        });--%>
   }
     
	function showDetailLog(jobId) {
		var logWindow = nui.get("logWindow");
		logWindow.show();
		nui.get("jobId").setValue(jobId);
		var form = new nui.Form("#logform");
		var o=form.getData();
		var json=nui.encode(o);
		form.loading();
		nui.ajax({
		    url: "com.bos.batch.acbatchmonitorbiz.getDetailLog.biz.ext" ,
		    contentType:'text/json',
		    type: "post",
		    data:json,
		    success: function (text) {
		        var o = nui.decode(text);
		        form.setData(o);
		        form.unmask();
		        
		    },
		    error: function () {
		        alert("表单加载错误");
		    }
		});
    }
	
    function search() {
		var data = form.getData();					    //获取表单多个控件的数据
        grid.load(data);
        
    	<%--
    	//根据行方邮件要求，取消自动刷新功能。
    	var temptime = null;
        var time = (nui.get("time").value)*1000;				//获取页面中的设置值
		if(time>0){ 
			//clearTimeout(temptime);
			temptime = setTimeout("search();",time);		//每隔time秒自动刷新一次页面	
		}else{
		    clearTimeout(temptime);//当页面设置为“0”时，不再自动刷新
		}
		--%>
    }
    search();
    
    function reset(){
		form.reset();
	}
	/**导出Excel表格，注意：导出之前必须查询，除非全部导出*/
    function exportEmp() {
    	var rows = grid.findRows(function(row){
   	 		if(row.jobId != null) return true;
		});
		
		if(rows != null && rows.length > 0) {
			//有要导出的记录
			var forms = document.getElementById("form1");
			forms.action = "com.primeton.example.excel.empManager.flow?_eosFlowAction=exportFile&importCd=22";
			forms.submit();
		} else {
			//没有要导出的记录
			alert('没有要导出的记录');
		}
    }
    
    //行点击事件
	function rowselect(e){
		//alert(e.record.jobLogfilepath);return;
		if(e.record.jobLogfilepath.indexOf("batch")>0){
			nui.get("batch").setVisible(true);
			nui.get("loadData").setVisible(false);
			nui.get("dataDrawOut").setVisible(false);
		}else if(e.record.jobLogfilepath.indexOf("loadData")>0) {
			nui.get("batch").setVisible(false);
			nui.get("loadData").setVisible(true);
			nui.get("dataDrawOut").setVisible(false);
		}else if(e.record.jobLogfilepath.indexOf("dataDrawOut")>0) {
			nui.get("batch").setVisible(false);
			nui.get("loadData").setVisible(false);
			nui.get("dataDrawOut").setVisible(true);
		}else{
			nui.get("batch").setVisible(false);
			nui.get("loadData").setVisible(false);
			nui.get("dataDrawOut").setVisible(false);
		}
	}
    
	function breakTask(){
    	var rows = grid.getSelected();
    	debugger;
    	if(rows == null){
    		alert("请选择正在运行的任务！");
    		return false;
    	}
    	if(rows.jobStatus != "0"){
    		alert("当前任务并未运行！");
    		return false;
    	}
    	
    	if(!confirm("准备中断批量任务，确认？")){
    		return false;
    	}
    	var exeUrl = "com.bos.batch.console.breakTask.biz.ext";
    	var json = nui.encode({"item":rows});
		 nui.ajax({
			    url: exeUrl,
			    type: "post",
			    contentType: "text/json",
			    data:json,
			    success: function (text) {
			    	if(text.status == "1"){
		        		nui.alert("操作成功！");
			    	}else if(text.status == "2"){
		            	nui.alert("操作失败！");
			    	}
	            },
	            error: function () {
	            	nui.alert("操作失败！");
	            }
		});
    }
	</script>
</body>
</html>
