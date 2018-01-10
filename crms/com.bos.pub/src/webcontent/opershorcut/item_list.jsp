<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2013-10-31
  - Description:TB_SYS_PRODUCT, com.bos.pub.product.TbSysProduct
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div class="nui-toolbar" style="border-bottom:0;">
	<a class="nui-button" iconCls="icon-add" onclick="add()">增加</a>
	<a class="nui-button" iconCls="icon-remove" onclick="remove()">删除</a>
	<a class="nui-button"  iconCls="icon-save" onclick="saveItem">保存</a>
	<a class="nui-button" onclick="search">刷新</a>
</div>
	    
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.pub.sys.getOperShortcut.biz.ext" dataField="items"
	allowResize="true" showReloadButton="false" 
	multiSelect="false" sizeList="[100]" pageSize="100" sortMode="client"
	allowCellEdit="true" allowCellSelect="true" editNextOnEnterKey="true"
	oncellmousedown="onCellBeginEdit">
	<div property="columns">
		<div type="checkcolumn">选择</div>
		<div field="orderno" headerAlign="center" allowSort="true" >顺序号
			<input property="editor" class="nui-textbox" style="width:100%;" />
		</div>
		<div field="funcname" headerAlign="center" allowSort="true" >功能名称
			<input class="nui-buttonedit" allowInput="false" onbuttonclick="onButtonEdit" property="editor" style="width:100%;"/>
		</div>
		</div>
	</div>
			
    <script type="text/javascript">
	 	nui.parse();
		var grid = nui.get("grid1");
		
        function search() {
            grid.load({});
        }
        search();
        
        function reset(){
			form.reset();
			search();
		}
		
function add() {
    var newRow = { name: "New Row" };
	grid.addRow(newRow, 0);
}
function remove() {
	var rows = grid.getSelecteds();
	if (rows.length > 0) {
		grid.removeRows(rows, true);
	}
}

function onCellBeginEdit(e) {
	var editor = grid.getCellEditor(e.column, e.record);
	//var row = grid.getEditorOwnerRow(editor);
	//alert(nui.encode(e.record));
	if (e.field == 'funcname' && e.record.name != 'New Row') {
		editor.disable();
		return;
	}
	editor.enable();
}

function onButtonEdit(){
	   		var btnEdit = this;
	    	nui.open({
                url: "<%=request.getContextPath() %>/utp/framework/menu/menu_function_select.jsp",
                title: "选择功能调用入口",
                width: 800, 
                height: 480,
                allowResize:false,
                ondestroy: function (action) {
                   if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.getData();
                        data = nui.clone(data); //必须
                        if (data) {
                            btnEdit.setValue(data.funcname);
                            btnEdit.setText(data.funcname);
                            grid.updateRow(grid.getSelected(),{"funccode":data.funccode,
                        		"funcname":data.funcname
                        		});
                        }
                    } 
                }
            });
}

function saveItem() {
			var g=grid;
            var data = {items:g.getChanges()};
            for(var i=0;i<data.items.length;i++) {
            	var funcname=data.items[i].funcname;
            	var orderno=data.items[i].orderno;
            	var reg = '^[0-9]{1,}$';
            	if(data.items[i].orderno.match(reg) == null){
            		nui.alert("顺序号请填写数字格式！");
            		return;
            	}
            	if(data.items[i].orderno.length > 18){
            		nui.alert("顺序号超长！");
            		return;
            	}
            	if (!orderno
            		|| !funcname) {
            		nui.alert("请将信息填写完整！");
            		//nui.alert(nui.encode(data.items[i]));
            		return;
            	}
            }
            
            var json = nui.encode(data);
            g.loading("保存中，请稍后......");
            nui.ajax({
                url: "com.bos.pub.sys.saveOperShortcuts.biz.ext",
                type: 'POST',
                data: json,
                success: function (text) {
                	g.reload();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                }
            });
}

	</script>
</body>
</html>
