<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-05-22
  - Description:TB_PUB_DECI_TABLE, com.bos.pub.decision.TbPubDeciTable
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	
	<div class="nui-dynpanel" columns="4">
		<label>决策表名称：</label>
		<input name="tbPubDeciTable.tname" required="false" class="nui-textbox nui-form-input" vtype="maxLength:100" />

	</div>
</div>
				
<div class="nui-toolbar" style="padding-right:20px;text-align:right;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
	<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
</div>
<div class="nui-toolbar" style="border-bottom:0;">
	<a class="nui-button" iconCls="icon-folderopen" onclick="importTable">导入</a>
	<!--<a class="nui-button" iconCls="icon-add" onclick="add()">增加</a>
	<a class="nui-button" iconCls="icon-edit" onclick="edit(0)">编辑</a>
	<a class="nui-button" iconCls="icon-edit" onclick="edit(1)">查看</a>-->
	<a class="nui-button" iconCls="icon-edit" onclick="editTable(0)">编辑</a>
	<a class="nui-button" iconCls="icon-edit" onclick="editTable(1)">查看</a>
	<a class="nui-button" iconCls="icon-remove" onclick="remove()">删除</a>
</div>
	    
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.pub.deciTable.getTbPubDeciTableList.biz.ext"
	dataField="tbPubDeciTables"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="tid" headerAlign="center" allowSort="true" >决策表编号</div>
		<div field="tname" headerAlign="center" allowSort="true" >决策表名称</div>
		<div field="createTime" headerAlign="center" allowSort="true" >创建时间</div>
		</div>
	</div>
			
    <script type="text/javascript">
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
            url: nui.context+"/pub/decision/deciTable/deci_table_add.jsp",
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
    function importTable() {
        nui.open({
            url: nui.context+"/pub/decision/deciTable/deci_table_import.jsp",
            title: "导入", 
            width: 1000, 
        	height: 500,
        	allowResize:true,
        	showMaxButton: true,
            ondestroy: function (action) {
                //if(action=="ok"){
                    search();
                //}
            }
        });
    }
    
    function edit(v) {
        var row = grid.getSelected();
        if (row) {
            nui.open({
                url: nui.context+"/pub/decision/deciTable/deci_table_edit.jsp?tid="+row.tid+"&view="+v,
                title: "编辑", 
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
            	var json=nui.encode({"tbPubDeciTable":{"tid":
            		row.tid,version:row.version}});
                $.ajax({
                     url: "com.bos.pub.deciTable.delTbPubDeciTable.biz.ext",
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
    
    function editTable(v) {
        var row = grid.getSelected();
        if (!row) {
            alert("请选中一条记录");
            return;
        }
        nui.open({
            url: nui.context+"/pub/decision/deciTable/deci_table_detail_edit.jsp?tid="+row.tid+"&view="+v,
            title: "编辑", 
            width: 1000,
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
    }

	</script>
</body>
</html>
