<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2013-12-16
  - Description:TB_PUB_GRANT_PACKAGE, com.bos.pub.decision.TbPubGrantPackage
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<div class="nui-dynpanel" columns="6">
		<label>规则分类编号：</label>
		<input name="item.pid" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />

		<label>规则分类名称：</label>
		<input name="item.pname" required="false" class="nui-textbox nui-form-input" vtype="maxLength:200" />

		<label>规则分类类型：</label>
		<input name="item.ptype" required="false" class="nui-dictcombobox nui-form-input" dictTypeId="pub_dec_pack_type" />

		<label>适用范围说明：</label>
		<input name="item.pnote" required="false" class="nui-textbox nui-form-input" vtype="maxLength:4000" />
	</div>
</div>
				
<div class="nui-toolbar" style="padding-right:20px;text-align:right;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
	<a class="nui-button" onclick="reset()">重置</a>
</div>
<div class="nui-toolbar" style="border-bottom:0;">
	<a class="nui-button" iconCls="icon-add" onclick="add()">增加</a>
	<!--<a class="nui-button" iconCls="icon-edit" onclick="edit(0)">编辑</a>-->
	<a class="nui-button" iconCls="icon-edit" onclick="edit(1)">查看</a>
	<a class="nui-button" iconCls="icon-remove" onclick="remove()">删除</a>
	<span class="separator"></span>
	<a class="nui-button" onclick="editParam()">编辑规则分类参数列表</a>
</div>
	    
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.pub.TbPubGrantRule.getItemList.biz.ext" dataField="items"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="pid" headerAlign="center" allowSort="true" >规则分类编号</div>
		<div field="pname" headerAlign="center" allowSort="true" >规则分类名称</div>
		<div field="ptype" headerAlign="center" allowSort="true"  dictTypeId="pub_dec_pack_type" >规则分类类型</div>
		<div field="pnote" headerAlign="center" allowSort="true" >适用范围说明</div>
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
			search();
		}
		
        function add() {
            nui.open({
                url: nui.context+"/pub/grant/pack/item_add.jsp",
                title: "新增", 
                width: 800, 
            	height: 500,
            	allowResize:true,
            	showMaxButton: true,
                ondestroy: function (action) {
                    if(action=="ok"){
                        grid.reload();
                    }
                }
            });
        }
        
        function editParam() {
            var row = grid.getSelected();
            if (!row) {
            	alert("请选中一条记录");
            	return;
            }
            nui.open({
                url: nui.context+"/pub/grant/pack/param/item_list.jsp?itemId="+row.pid,
                title: "参数列表", 
                width: 800,
        		height: 500,
                allowResize:true,
        		showMaxButton: true,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    var data = row;
                },
                ondestroy: function (action) {
                    if(action=="ok"){
                        //grid.reload();
                        //参数编辑完成后无需任何操作
               	 	}
                }
            });
        }
        
        function edit(v) {
            var row = grid.getSelected();
            if (row) {
                nui.open({
                    url: nui.context+"/pub/grant/pack/item_edit.jsp?itemId="+row.pid+"&view="+v,
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
                        	if (v==1)
                        		return;//查看
	                        grid.reload();
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
            	if (row.ptype == '02') {//02-用于模型指标
            		alert('该规则分类不能删除');
            		return;
            	}
            	nui.confirm("确定删除吗？","确认",function(action){
	            	if(action!="ok") return;
	            	var json=nui.encode({"item":{"pid":
	            		row.pid}});
	                $.ajax({
	                     url: "com.bos.pub.decision.delItem.biz.ext",
		                type: 'POST',
		                data: json,
		                cache: false,
		                contentType:'text/json',
	                    success: function (text) {
	                    	if (text.msg) {
	                    		nui.alert(text.msg);
	                    		return;
	                    	}
	                        grid.reload();
	                    },
	                    error: function () {
	                    	nui.alert("操作失败！");
	                    }
	                });
                }); 
            } else {
                nui.alert("请选中一条记录");
            }
        }

	</script>
</body>
</html>
