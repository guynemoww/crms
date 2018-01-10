<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<html>
  <head>
    <title>手动批量执行 </title>
    <%@include file="/common/nui/common.jsp" %>
  </head>
  <body>
	<center>
	    <div id="form1" class="nui-form" style="width:99%;height:auto;overflow:hidden;">
			<div class="nui-dynpanel" columns="6">
				<!-- 
				<label>批量组：</label>
				<input name="batchGroup" id="batchGroup" class="nui-dictcombobox nui-form-input" vtype="maxLength:10"
				data="data" valueField="dictID" textField="dictName" dictTypeId="nightGroup" onvaluechanged="checkBatchDate" />
				 -->
				<label>执行日期：</label>
				<input name="item.batchDate" id="item.batchDate" Enabled="false" required="true" class="nui-datepicker" vtype="maxLength:64" format="yyyy-MM-dd"  showtime="false" value="<%=GitUtil.getBusiDateStr()%>" />
				
				<label>任务状态：</label>
				<input id="item.taskStatus" name="item.taskStatus" required="true"  class="nui-dictcombobox nui-form-input"  textField="dictname" valueField="dictid"  allowInput="false"/>
				
				<!-- <label>是否执行全部：</label>
				<input name="item.fullFlag" id="item.fullFlag" required="true" class="nui-dictradiogroup nui-form-input"
				data="data" valueField="dictID" textField="dictName" dictTypeId="XD_RZCD0005" value="2" />
				 -->
			</div>
			<div class="nui-toolbar" style="text-align:right;border:none">
				<a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
			    <a class="nui-button" style="margin-right:23px;height:21px" iconCls="icon-upload" onclick="javascript:runBatch();" name="run" id="run">开始执行</a>
			</div>
		</div>
		<div id="grid1" class="nui-datagrid" style="width:99%;height:auto;"
			url="com.bos.batch.console.queryTaskList.biz.ext"
			dataField="taskList" allowAlternating="true" 
			allowResize="true" showReloadButton="false" 
			multiSelect="true" sortMode="client" pageSize="50" sizeList="[10,20,50,100]" >
			<div property="columns">
				<div type="checkcolumn" ></div>
				<div field="batchId" headerAlign="center"  width="150" >任务编号</div>
				<div field="batchCode" headerAlign="center"  width="150" >任务代码</div>
				<div field="batchName" headerAlign="center"  width="200" >任务名称</div>
				<div field="batchOrder" headerAlign="center" width="80" >任务排序</div>
				<div field="batchBizname" headerAlign="center"  width="200">执行逻辑名</div>
			</div>
		</div>
	</center>
    <script>
    
    nui.parse();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
    
    init();
    search();
    
    function init(){
		var taskStatus = [
        	{"dictname":"全部","dictid":"0"},
        	{"dictname":"已完成","dictid":"1"},
        	{"dictname":"未完成","dictid":"2"}
        ];
        nui.get("item.taskStatus").setData(taskStatus);
        nui.get("item.taskStatus").setValue("0");
	}
    
    function search() {
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
    }
    
    function runBatch(){
   		var exeUrl = "com.bos.batch.console.runBatch.biz.ext";
    	var rows = grid.getSelecteds();
    	var json = nui.encode({"taskList":rows});
    	if(rows.length == 0){
    		alert("请选择要执行的批量任务！");
    		return false;
    	}
    	if(!confirm("准备执行【"+rows.length+"】个批量任务，确认开始？")){
    		return false;
    	}
		 nui.ajax({
			    url: exeUrl,
			    type: "post",
			    contentType: "text/json",
			    data:json,
			    success: function (text) {
		            if(text.resCode){
			        	nui.alert(text.resMsg);
			        	return false;
		        	}else{
		        	   //跳转到监控页面
			           git.go(nui.context+"/batch/monitor/monitor_list.jsp")
		        	}
	            },
	            error: function () {
	            	nui.alert("批量执行失败！");
	            }
			}
		 );
		
    }
    
    
    function checkBatchDate(){
		 var form = new nui.Form("#form1");
		 var o=form.getData();
		 var json=nui.encode(o);
		 nui.ajax({
			    url: "com.bos.batch.acbatchmonitorbiz.checkBatchDate.biz.ext",
			    type: "post",
			    contentType: "text/json",
			    data:json,
			    success: function (text) {
		            if(text.batchDate){
		        		nui.get("item.batchDate").setValue(text.batchDate);
		        	} else {
		        		CloseWindow("ok");
		        	}
	            },
	            error: function () {
	            	nui.alert("获取批量日期失败");
	            }
			});
			
    }
    
    //发起批量时执行
    function checkRunBatch(){
   		var form = new nui.Form("#form1");
    	form.validate();
        if (form.isValid()==false){
          alert("填写信息不完整，不能发起批量！"); 
          return 
        }
		var o=form.getData();
		var json=nui.encode(o);
	 	nui.ajax({
		    url: "com.bos.batch.acbatchconsolebiz.checkBatchStatus.biz.ext",
		    type: "post",
		    contentType: "text/json",
		    data:json,
		    success: function (text) {
	            if(text.count == 0){
	            	runBatch();
	        	} else {
	        		var batchDate = nui.formatDate ( nui.parseDate(nui.get("batchDate").value), 'yyyy-MM-dd' );
	        		var batchGroup = nui.getDictText("nightGroup",nui.get("batchGroup").value);
	        		nui.alert("批量组 ["+ batchGroup +"] 在 ["+  batchDate +"] 批量日正在运行！");
	        		var url = nui.context+"/batch/monitor/monitor_list.jsp";
					git.go(url);
	        		return false;
	        	}
            },
            error: function () {
            	nui.alert("批量任务状态检查失败");
            	var url = nui.context+"/batch/monitor/monitor_list.jsp";
				git.go(url);
            	return false;
            }
		});
		      
    }
  
    </script>
  </body>
</html>
