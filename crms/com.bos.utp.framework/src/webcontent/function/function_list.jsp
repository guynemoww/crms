<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="/common/nui/common.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): fangwl (mailto:fangwl@primeton.com)
  - Date: 2013-03-01 10:35:26
  - Description:功能列表，tab页中使用
-->
<head>
</head>
<body>
	<div style="padding:5px 5px 0px 5px;">
		 <div class="nui-toolbar" style="border-bottom:0;">
	        <table style="width:100%;">
	            <tr>
		            <td style="width:100%;">
		                <a class="nui-button" iconCls="icon-add" onclick="add()">增加</a>
		            	<a class="nui-button" iconCls="icon-edit" onclick="edit()" id="edit_btn">编辑</a>
		            	<a class="nui-button" iconCls="icon-remove" onclick="remove()">删除</a>
		            </td>
	            </tr>
	        </table>
	    </div>
	</div>

	<div class="nui-fit" style="padding:0px 5px 5px 5px;">
		<input id="parentgroupid" name="parentgroupid" class="nui-hidden" />
	    <div id="functiongrid" class="nui-datagrid" style="width:100%;height:100%;" 
	    url="com.bos.utp.framework.FunctionManager.queryFunction.biz.ext" onselectionchanged="selectionChanged" idField="funccode" allowResize="false"
	    sizeList="[10,20,50,100]" multiSelect="true" onrowdblclick="edit()" sortMode="client">
		    <div property="columns">
		        <div type="checkcolumn" ></div>
		        <div field="funcname" width="100" headerAlign="center" allowSort="true">功能名称</div>    
		        <div field="functype" width="100" headerAlign="center" allowSort="true" renderer="onResTypeRenderer">功能类型</div>                            
		        <div field="ismenu" width="100" headerAlign="center" allowSort="true" renderer="onIsMenuRenderer">是否定义为菜单</div>
		        <div field="acFuncgroup.funcgroupname" width="100" headerAlign="center" allowSort="true">所属功能组</div>
		    </div>
		</div>
	</div>
    <script type="text/javascript">
	 	nui.parse();
		var grid = nui.get("functiongrid");
        var parentgroupid = <%=request.getParameter("realId") %>;
		document.getElementById("parentgroupid").value = parentgroupid;
		var data = {funcgroupid:parentgroupid};
        grid.load(data);
		
        function add() {
            nui.open({
                url: "<%=request.getContextPath() %>/utp/framework/function/function_add.jsp",
                title: "新增功能", 
                width: 850, 
            	height: 355,
                onload: function () {
                	var id = document.getElementById("parentgroupid").value;
                	var data = {parentgroupid:id};
                	var iframe = this.getIFrameEl();
                    iframe.contentWindow.SetData(data);
                },
                ondestroy: function (action) {
                	if(action=="ok"){
                    	parent.refresh();
                        grid.reload();
                    }
                }
            });
        }
        
        function edit() {
            var row = grid.getSelected();
            if (row) {
                nui.open({
                    url: "<%=request.getContextPath() %>/utp/framework/function/function_edit.jsp",
                    title: "编辑功能", 
                    width: 850, 
           			height: 355,
                    onload: function () {
                        var iframe = this.getIFrameEl();
                        var data = row;
                        iframe.contentWindow.SetData(data);
                    },
                    ondestroy: function (action) {
                    	if(action=="ok"){
	                    	parent.refresh();
	                        grid.reload();
                        }
                    }
                });
                
            } else {
                alert("请选中一条记录");
            }
            
        }
        
        function remove() {
            var rows = grid.getSelecteds();
            var nodes = [];
            
            for(var i=0; i<rows.length;i++){
            	nodes.push({realId:rows[i].funccode,type:"function"});
            }
            if (rows.length > 0) {
                nui.confirm("确定删除选中记录？","删除确认",function(action){
	            	if(action!="ok") return;
                    var json = nui.encode({nodes:nodes});
                    $.ajax({
                         url: "com.bos.utp.framework.ApplicationManager.deleteApplications.biz.ext",
		                type: 'POST',
		                data: json,
		                cache: false,
		                contentType:'text/json',
                        success: function (text) {
                            grid.reload();
                            parent.refresh();
                        },
                        error: function () {
                        }
                    });
                }); 
            } else {
                alert("请选中一条记录");
            }
        }
        
        function onResTypeRenderer(e) {
            if (e.value == "flow") return "页面流";
            if (e.value == "page") return "页面";
            if (e.value == "form") return "表单";
            if (e.value == "view") return "视图";
            if (e.value == "startprocess") return "启动流程";
            else return "其他";
        }
        
        function onIsMenuRenderer(e) {
            return nui.getDictText("XD_0002", e.row.ismenu);
        }
        
        function selectionChanged(){
			var rows = grid.getSelecteds();
			if(rows.length>1){
				//disable edit button
				nui.get("edit_btn").disable();
			}else{
				nui.get("edit_btn").enable();
			}
		}
	</script>
</body>
</html>