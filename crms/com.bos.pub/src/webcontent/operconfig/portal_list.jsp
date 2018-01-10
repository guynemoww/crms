<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn 请在此写上作者
  - Date: 2014-06-26
  - Description:AC_OPERCONFIG, com.bos.pub.meta.AcOperconfig
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div class="nui-toolbar" style="border-bottom:0;">
	<a class="nui-button" iconCls="icon-add" onclick="add()">增加</a>
	<a class="nui-button" iconCls="icon-remove" onclick="remove()">删除</a>
</div>
	    
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.pub.operConfig.getAcOperconfigList.biz.ext"
	dataField="acOperconfigs"
	allowResize="true" showReloadButton="false" showPager="false"
	multiSelect="false" pageSize="150" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="configname" headerAlign="center" allowSort="true" renderer="renderResourceName">资源名称</div>
		<div field="configvalue" headerAlign="center" allowSort="true" >顺序号</div>
		</div>
	</div>
			
<script type="text/javascript" src="portals_config.js"></script>
    <script type="text/javascript">
 	nui.parse();
	var grid = nui.get("grid1");
	
    grid.load();
    
    function renderResourceName(e) {
    	for (var i=0,len=portals.length; i<len; i++) {
    		if (portals[i].id == e.row.configname) {
    			return portals[i].text;
    		}
    	}
    }
    function add() {
        nui.open({
            url: nui.context+"/pub/operconfig/portal_add.jsp",
            title: "新增", 
            width: 800, 
        	height: 500,
        	allowResize:true,
        	showMaxButton: true,
            ondestroy: function (action) {
                if(action=="ok"){
                    grid.load();
                }
            }
        });
    }
    
    function remove() {
        var row = grid.getSelected();
        
        if (row) {
        	nui.confirm("确定删除吗？","确认",function(action){
            	if(action!="ok") return;
            	var json=nui.encode({"acOperconfig":{"operatorid":
            		row.operatorid,configtype:row.configtype,configname:row.configname}});
                $.ajax({
                     url: "com.bos.pub.operConfig.delAcOperconfig.biz.ext",
	                type: 'POST',
	                data: json,
	                cache: false,
	                contentType:'text/json',
                    success: function (text) {
                    	if (text.msg) {
                    		nui.alert(text.msg);
                    		return;
                    	}
                        grid.load();
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
