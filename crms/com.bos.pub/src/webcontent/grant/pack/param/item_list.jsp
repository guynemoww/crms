<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2013-12-16
  - Description:TB_PUB_GRANT_PARAM, com.bos.pub.decision.TbPubGrantParam
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input type="hidden" name="item._entity" value="com.bos.pub.decision.TbPubGrantParam" class="nui-hidden" />
	<div class="nui-dynpanel" columns="6">

		<label>参数名称：</label>
		<input name="item.paramname" required="false" class="nui-textbox nui-form-input" vtype="maxLength:200" />

		<label>英文名称：</label>
		<input name="item.enname" required="false" class="nui-textbox nui-form-input" vtype="maxLength:200" />

		<label>参数类型：</label>
		<input name="item.paramtype" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="pub_grant_param" emptyText="--请选择--"/>

		<!--<label>参数业务字典代码：</label>
		<input name="item.paramdicttype" required="false" class="nui-textbox nui-form-input" vtype="maxLength:200" />-->

	</div>
</div>
				
<div class="nui-toolbar" style="padding-right:20px;text-align:right;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
	<a class="nui-button" onclick="reset()">重置</a>
	<a class="nui-button" onclick="CloseWindow()">关闭</a>
</div>
<div class="nui-toolbar" style="border-bottom:0;">
	<a class="nui-button" iconCls="icon-add" onclick="add()">增加</a>
	<a class="nui-button" iconCls="icon-edit" onclick="edit(0)">编辑</a>
	<a class="nui-button" iconCls="icon-edit" onclick="edit(1)">查看</a>
	<a class="nui-button" iconCls="icon-remove" onclick="remove()">删除</a>
</div>
	    
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.pub.decision.getParamList.biz.ext" dataField="items"
	allowResize="true" showReloadButton="false" showPager="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="paramid" headerAlign="center" allowSort="true" >参数编号</div>
		<div field="paramname" headerAlign="center" allowSort="true" >参数名称</div>
		<div field="enname" headerAlign="center" allowSort="true" >英文名称</div>
		<div field="paramtype" headerAlign="center" allowSort="true"  dictTypeId="pub_grant_param" >参数类型</div>
		<!--<div field="paramdicttype" headerAlign="center" allowSort="true" >参数业务字典代码</div>-->
	</div>
</div>
			
    <script type="text/javascript">
	 	nui.parse();
	    var form = new nui.Form("#form1"); 
		var grid = nui.get("grid1");
		var packageid="<%=request.getParameter("itemId") %>";
		
        function search() {
			var data = form.getData(); //获取表单多个控件的数据
			//设置规则分类编号（外键）
			data.item.tbPubGrantPackage={pid:packageid};
			data.item.pid=packageid;
            grid.load(data);
        }
        search();
        
        function reset(){
			form.reset();
			search();
		}
		
        function add() {
            nui.open({
                url: nui.context+"/pub/grant/pack/param/item_add.jsp?pid="+packageid,
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
        
        function edit(v) {
            var row = grid.getSelected();
            if (row) {
                nui.open({
                    url: nui.context+"/pub/grant/pack/param/item_edit.jsp?pid="+packageid+"&itemId="+row.paramid+"&view="+v,
                    title: (v==1 ? "查看" : "编辑"), 
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
            	nui.confirm("确定删除吗？","确认",function(action){
	            	if(action!="ok") return;
	            	var json=nui.encode({"item":{"paramid":
	            		row.paramid}});
	                $.ajax({
	                     url: "com.bos.pub.decision.delParam.biz.ext",
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
