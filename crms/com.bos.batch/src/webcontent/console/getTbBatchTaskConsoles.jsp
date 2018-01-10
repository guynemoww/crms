<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-04-23
  - Description:AC_BATCH, com.bos.intf.monitor.AcBatch
-->
<head>
<%@include file="/common/nui/common.jsp" %>
<title>修改任务执行状态</title>
</head>

<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;">
<div title="修改任务执行状态" >
<center>
<div id="form1" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
	<div class="nui-dynpanel" columns="6">
		<label>批量名称：</label>
		<input name="tbBatchTaskConsole.batchName" required="false" class="nui-textbox nui-form-input" vtype="maxLength:64" />
		
		<label>批量组：</label>
		<input name="tbBatchTaskConsole.batchGroup" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:10"
		data="data" valueField="dictID" textField="dictName" dictTypeId="nightGroup"/>
		
		<label>批量状态：</label>
		<input name="tbBatchTaskConsole.status" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:4"
		data="data" valueField="dictID" textField="dictName" dictTypeId="XD_RZCD0006"/>
		
		<label>批量日期：</label> 
		<input name="tbBatchTaskConsole.batchDate" required="false" class="nui-datepicker"
		format="yyyy-MM-dd"  showtime="false" />
		
	</div>
	<div class="nui-toolbar" style="text-align:right;border:none">
	    <a class="nui-button" style="margin-right:5px;height:21px;" iconCls="icon-search" onclick="search()">查询</a>
		<a class="nui-button" style="margin-right:23px;height:21px" iconCls="icon-reset" onclick="reset()">重置</a>
	</div>
</div>
<div style="width:99.5%">				
	<div class="nui-toolbar" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px">
		<a class="nui-button" iconCls="icon-edit" onclick="edit(0)">编辑</a>
		<!-- 
		<a class="nui-button"  iconCls="icon-node" onclick="edit(1)">查看</a>
		<a class="nui-button" style="margin-left:5px" class="nui-button" iconCls="icon-add" onclick="add()">新增</a>
		<a class="nui-button" iconCls="icon-remove" onclick="remove()">删除</a>
		 -->
	</div>
</div>	    
<div id="grid1" class="nui-datagrid" style="width:99.5%;height:auto;"
	url="com.bos.batch.updateBatchTaskStatus.getTbBatchTaskConsoles.biz.ext"
	dataField="tbBatchTaskConsoles" allowAlternating="true" 
	allowResize="true" showReloadButton="false" 
	sizeList="[10,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="batchCode" headerAlign="center" allowSort="true"  width="150" >批量代码</div>
		<div field="batchName" headerAlign="center" allowSort="true"  width="200" >批量名称</div>
		<div field="batchGroup" headerAlign="center" allowSort="true" width="100" dictTypeId="nightGroup">批量组</div>
		<div field="batchOrder" headerAlign="center" allowSort="true" width="80" >批量组内排序</div>
		<div field="status" headerAlign="center" allowSort="true" width="80" dictTypeId="XD_RZCD0006">任务状态</div>
		<div field="batchDate" headerAlign="center" allowSort="true" width="200">批量日期</div>
		<div field="starttime" headerAlign="center" allowSort="true" width="200">开始时间</div>
		<div field="endtime" headerAlign="center" allowSort="true" width="200">结束时间</div>
		</div>
	</div>
	</center>
	</div>
	</div>		
    <script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	var editTitle = '编辑';
	
    function search() {
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
    }
    search();
    
    function reset(){
		form.reset();
	}
	
    function edit(v) {
        var row = grid.getSelected();
        
        if(v == 1) {
        	editTitle = '查看';
        } else {
        	editTitle = '编辑';
        }
        if (row) {
            nui.open({
                url: nui.context + "/batch/console/updateStatus.jsp?consoleId="+row.consoleId+"&view="+v,
                title: editTitle, 
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
    
   

	</script>
</body>
</html>
