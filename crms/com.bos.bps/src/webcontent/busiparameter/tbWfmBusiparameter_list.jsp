<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-04-23
  - Description:TB_WFM_BUSIPARAMETER, com.bos.bps.dataset.bps.TbWfmBusiparameter
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>

<div class="nui-toolbar" style="border-bottom:0;">
	<a class="nui-button" iconCls="icon-add" onclick="add()">增加</a>
	<a class="nui-button" iconCls="icon-edit" onclick="edit(0)">编辑</a>
	<a class="nui-button" iconCls="icon-edit" onclick="edit(1)">查看</a>
	<a class="nui-button" iconCls="icon-remove" onclick="remove()">删除</a>
		<a class="nui-button" iconCls="icon-add" onclick="ret()">返回主表(Esc)</a>
</div>
	    
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.bps.util.TbWfmBusiparameters.queryTbWfmBusiparameters.biz.ext"
	dataField="tbWfmBusiparameters"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="isMust" headerAlign="center" allowSort="true" >是否必选</div>
		<div field="isShow" headerAlign="center" allowSort="true" >是否显示</div>
		<div field="porpertyDictName" headerAlign="center" allowSort="true" >字典项名称</div>
		<div field="porpertyName" headerAlign="center" allowSort="true" >字段名称</div>
		<div field="porpertyNum" headerAlign="center" allowSort="true" >字段名</div>
		<div field="showType" headerAlign="center" allowSort="true" >显示类型</div>
		</div>
	</div>
			
    <script type="text/javascript">
 	nui.parse();
	var grid = nui.get("grid1");
	
    function search() {
        grid.load({'workitemMappingId':'<%=request.getParameter("id") %>'});
    }
    search();

	
    function add() {
        nui.open({
            url: nui.context+"/bps/busiparameter/tbWfmBusiparameter_add.jsp?workitemMappingId=<%=request.getParameter("id") %>",
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
                url: nui.context+"/bps/busiparameter/tbWfmBusiparameter_edit.jsp?workitemMappingId="+row.workitemMappingId+"&view="+v+"&paraId="+row.paraId,
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
            	var json=nui.encode({"TbWfmBusiparameter":{"paraId":
            		row.paraId}});
                $.ajax({
                     url: "com.bos.bps.util.TbWfmBusiparameters.deleteTbWfmBusiparameters.biz.ext",
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
    //返回主表
function ret (){
git.go(nui.context+"/bps/exerciseCase/exercise_case_list.jsp?id=<%=request.getParameter("exerciseId") %>");
}
	</script>
</body>
</html>
<script type="text/javascript" language=JavaScript charset="UTF-8">
      document.onkeydown=function(event){
            var e = event || window.event || arguments.callee.caller.arguments[0];
            if(e && e.keyCode==27){ // 按 Esc 
                //要做的事情
                ret();
              }
            if(e && e.keyCode==113){ // 按 F2 
                 //要做的事情
                
               }     
            if(e && e.keyCode==46){ // 按 Delete
                 //要做的事情
                remove();
               }          
             if(e && e.keyCode==13){ // enter 键
                 //要做的事情
               
            }
        }; 
</script>