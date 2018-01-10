<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn 请在此写上作者
  - Date: 2014-06-06
  - Description:TB_BATCH_SP_LOG, com.bos.dataset.batch.TbBatchSpLog
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;">
<div title="批量存储过程日志" >
<center>
<form id="form1" action="" method="post" enctype="multipart/form-data" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
	<div class="nui-dynpanel" columns="6">
		<label>过程或函数名：</label>
		<input name="map/spName" required="false" class="nui-textbox nui-form-input"/>
	    <label>过程开始时间起：</label>
		<input name="map/begTime" required="false" class="nui-datepicker" format="yyyy-MM-dd HH:mm" showtime="true"/>
		<label>过程开始时间止：</label>
		<input name="map/endTime" required="false" class="nui-datepicker" format="yyyy-MM-dd HH:mm" showtime="true"/>
	   	<label>执行标识：</label>
		<input name="map/execResult" required="false" class="nui-dictcombobox nui-form-input"
		data="data" valueField="dictID" textField="dictName" dictTypeId="XD_RZCD0001"/>
	</div>
	<div class="nui-toolbar" style="text-align:right;border:none">
	    <a class="nui-button" style="margin-right:5px;height:21px;" iconCls="icon-search" onclick="search()">查询</a>
		<a class="nui-button" style="margin-right:5px;height:21px" iconCls="icon-reset" onclick="reset()">重置</a>
		<a class="nui-button" style="margin-right:20px;height:21px" onclick="exportEmp" type="submit" >导出Excel表格(F2)</a>
	</div>
</form>
<div style="width:99.5%">				
	<div class="nui-toolbar" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px">
		<a class="nui-button" style="margin-left:5px" iconCls="icon-node" onclick="edit(1)">查看</a>
		<a class="nui-button" style="margin-left:5px" iconCls="icon-node" onclick="tarLogs()">打包日志</a>
		<a class="nui-button" style="margin-left:5px" iconCls="icon-node" href="com.bos.batch.datalistAttachment.downloadInventory.biz.ext2?filename=CRMSlogs.tar.gz&datalistpath=/crms/tarlogs/CRMSlogs.tar.gz" >下载日志</a>
		<!-- 
		<a class="nui-button" iconCls="icon-remove" onclick="remove()">删除</a>
		 -->
	</div>
</div>	    
<div id="grid1" class="nui-datagrid" style="width:99.5%;height:auto;"
	url="com.bos.batch.queryLog.getTbBatchSpLogList.biz.ext"
	dataField="tbBatchSpLogs" allowAlternating="true" 
	allowResize="true" showReloadButton="false" 
	sizeList="[10,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="spName" headerAlign="center" allowSort="true" >过程或函数名</div>
	    <div field="execResult" headerAlign="center" allowSort="true"  dictTypeId="XD_RZCD0001">执行标识</div>
	    <div field="inPara1" headerAlign="center" allowSort="true" >入参1</div>
	    <div field="inPara2" headerAlign="center" allowSort="true" >入参2</div>
	    <div field="inPara3" headerAlign="center" allowSort="true" >入参3</div>
	    <div field="inPara4" headerAlign="center" allowSort="true" >入参4</div>
	    <div field="inPara5" headerAlign="center" allowSort="true" >入参5</div>
	    <div field="outPara1" headerAlign="center" allowSort="true" >出参1</div>
	    <div field="outPara2" headerAlign="center" allowSort="true" >出参2</div>
	    <div field="outPara3" headerAlign="center" allowSort="true" >出参3</div>
	    <div field="outPara4" headerAlign="center" allowSort="true" >出参4</div>
	    <div field="outPara5" headerAlign="center" allowSort="true" >出参5</div>
		<div field="begTime" headerAlign="center" allowSort="true"  dateformat="yyyy-MM-dd HH:mm:ss">过程开始时间</div>
		<div field="endTime" headerAlign="center" allowSort="true"  dateformat="yyyy-MM-dd HH:mm:ss">过程结束时间</div>
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
	
    function search() {
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
    }
    search();
    
    function reset(){
		form.reset();
	}
	
    function add() {
        nui.open({
            url: "item_add.jsp",
            title: "新增", 
            width: 800, 
        	height: 500,
        	allowResize:true,
        	showMaxButton: true,
            ondestroy: function (action) {
                if(action=="ok"){
                    search();
                }
            }
        });
    }
    
    function edit(v) {
        var row = grid.getSelected();
        if (row) {
            nui.open({
                url: "batch/log/item_edit.jsp?logId="+row.logId+"&view="+v,
                title: "查看", 
                width: 800,
        		height: 500,
                allowResize:true,
        		showMaxButton: true,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    var data = row;
                    //iframe.contentWindow.SetData(data);
                },
                ondestroy: function (action) {
                    if(action=="ok"){
                        search();
               	 	}
                }
            });
        } else {
            alert("请选中一条记录");
        }
        
    }
    
    /**导出Excel表格，注意：导出之前必须查询，除非全部导出*/
    function exportEmp() {
    	var rows = grid.findRows(function(row){
   	 		if(row.logId != null) return true;
		});
		
		if(rows != null && rows.length > 0) {
			//有要导出的记录
			var forms = document.getElementById("form1");
			forms.action = "com.primeton.example.excel.empManager.flow?_eosFlowAction=exportFile&importCd=26";
			forms.submit();
		} else {
			//没有要导出的记录
			alert('没有要导出的记录');
		}
    }
    //删除操作
    <%--function remove() {
        var row = grid.getSelected();
        
        if (row) {
        	nui.confirm("确定删除吗？","确认",function(action){
            	if(action!="ok") return;
            	var json=nui.encode({"tbBatchSpLog":{"logId":
            		row.logId,version:row.version}});
                $.ajax({
                     url: "com.bos.pub.crud.delTbBatchSpLog.biz.ext",
	                type: 'POST',
	                data: json,
	                cache: false,
	                contentType:'text/json',
                    success: function (text) {
                    	if (text.msg) {
                    		nui.alert(text.msg);
                    		return;
                    	}
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
    }--%>
	
    //日志打包
    function tarLogs() {
        	nui.confirm("确定要打包最新的日志吗？","确认",function(action){
            	if(action!="ok") return;
            	grid.loading("处理中......");
                $.ajax({
                    url: "com.bos.batch.commonutil.tarLogs.biz.ext",
	                type: 'POST',
	                data: null,
	                cache: false,
	                contentType:'text/json',
                    success: function (text) {
                    	if (text.msg) {
                    		nui.alert(text.msg);
                    		return;
                    	}
                    	grid.unmask();
                    },
                    error: function () {
                        grid.unmask();
                    	nui.alert("打包失败！");
                    }
                });
            }); 
        } 
        
     function downloadFile() {
        ifrm.src="com.bos.batch.datalistAttachment.downloadInventory.biz.ext2?filename="+row.filename+"&datalistpath="+row.datalistpath;
    }
	</script>
</body>
</html>
