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
<title>批量配置</title>
</head>

<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;">
<div title="批量配置" >
<center>
<div id="form1" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
	<div class="nui-dynpanel" columns="6">
		<label>批量代码：</label>
		<input name="acBatch.batchCode" required="false" class="nui-textbox nui-form-input" vtype="maxLength:64" />
		
		<label>批量名称：</label>
		<input name="acBatch.batchName" required="false" class="nui-textbox nui-form-input" vtype="maxLength:64" />
		
	<!-- 	<label>批量组：</label>
		<input name="acBatch.batchGroup" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:10"
		data="data" valueField="dictID" textField="dictName" dictTypeId="nightGroup"/> -->
		
		<label>批量启用状态：</label>
		<input name="acBatch.batchStatus" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:4"
		data="data" valueField="dictID" textField="dictName" dictTypeId="XD_RZCD0004"/>
		
	</div>
	<div class="nui-toolbar" style="text-align:right;border:none">
	    <a class="nui-button" style="margin-right:5px;height:32px;" iconCls="icon-search" onclick="search()">查询</a>
		<a class="nui-button" style="margin-right:23px;height:32px" iconCls="icon-reset" onclick="reset()">重置</a>
	</div>
</div>
<div style="width:99.5%">				
	<div class="nui-toolbar" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px">
		<a class="nui-button" style="margin-left:5px" class="nui-button" iconCls="icon-add" onclick="add()">新增</a>
		<a class="nui-button"  iconCls="icon-node" onclick="edit(1)">查看</a>
		<a class="nui-button" iconCls="icon-edit" onclick="edit(0)">编辑</a>
		<a class="nui-button" iconCls="icon-remove" onclick="remove()">删除</a>
	</div>
</div>	    
<div id="grid1" class="nui-datagrid" style="width:99.5%;height:auto;"
	url="com.bos.batch.batchquery.getAcBatchList.biz.ext"
	dataField="acBatchs" allowAlternating="true" 
	allowResize="true" showReloadButton="false" 
	sizeList="[10,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="batchCode" headerAlign="center" allowSort="true"  width="150" >任务代码</div>
		<div field="batchName" headerAlign="center" allowSort="true"  width="200" >任务名称</div>
		<!-- <div field="batchGroup" headerAlign="center" allowSort="true" width="100" dictTypeId="nightGroup">批量组</div> -->
		<div field="batchOrder" headerAlign="center" allowSort="true" width="80" >执行顺序</div>
		<div field="batchStatus" headerAlign="center" allowSort="true" width="80" dictTypeId="XD_RZCD0004">启用状态</div>
		<div field="batchBizname" headerAlign="center" allowSort="true" width="200">调用逻辑名</div>
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
	
    function add() {
        nui.open({
            url: "batch/batchquery/batchquery_add.jsp",
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
        if(v == 1) {
        	editTitle = '查看';
        } else {
        	editTitle = '编辑';
        }
        
        if (row) {
            nui.open({
                url: "batch/batchquery/batchquery_edit.jsp?batchId="+row.batchId+"&view="+v,
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
    
    function remove() {
        var row = grid.getSelected();
        
        if (row) {
        	nui.confirm("确定删除吗？","确认",function(action){
            	if(action!="ok") return;
            	var json=nui.encode({"acBatch":{"batchId":
            		row.batchId,version:row.version}});
                $.ajax({
                     url: "com.bos.batch.batchquery.delAcBatch.biz.ext",
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
    }

	</script>
</body>
</html>
