<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2013-10-31
  - Description:TB_SYS_TECH_PRODUCT, com.bos.pub.product.TbSysTechProduct
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">

	<input type="hidden" name="item._entity" value="com.bos.pub.decision.TbPubGrantParam" class="nui-hidden" />
	<div class="nui-dynpanel" columns="6">
		<label>规则类别编号：</label>
		<input name="item.pid" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" value="<%=request.getParameter("temp") %>"/>
		
	
		<label>参数编号：</label>
		<input name="item.paramid" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />

	
		<label>参数名称：</label>
		<input name="item.paramname" required="false" class="nui-textbox nui-form-input" vtype="maxLength:200" />

		<label>参数类型：</label>
		<input name="item.paramtype" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="pub_grant_param" emptyText="--请选择--"/>


	</div>
</div>
				
<div class="nui-toolbar" style="padding-right:20px;text-align:right;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
	<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
</div>
<div class="nui-toolbar" style="border-bottom:0;">
	<a class="nui-button" iconCls="icon-edit" onclick="edit(1)">查看</a>
    <a class="nui-button"  iconCls="icon-save" onclick="save">确定</a>
    <a class="nui-button" id="cancelBtn_01" iconCls="icon-cancel" onclick="CloseWindow()">关闭</a>
    <a class="nui-button" id="cancelBtn_01" iconCls="icon-cancel" onclick="CloseWindow('clear')">清空</a>
</div>
	    
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.pub.decision.getParamList.biz.ext" dataField="items"
	allowResize="true" showReloadButton="false" onrowdblclick="save"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="paramid" headerAlign="center" allowSort="true" >参数编号</div>
		<div field="paramname" headerAlign="center" allowSort="true" >参数名称</div>
		<div field="paramtype" headerAlign="center" allowSort="true"  dictTypeId="pub_grant_param" >参数类型</div>
		<div field="paramdicttype" headerAlign="center" allowSort="true" >参数业务字典代码</div>
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
		
		function save(){
			var row = grid.getSelected();
			if (!row) {
				nui.alert("请选中一条记录");
				return;
			}
        	CloseWindow("ok");
		}
		
        function edit(v) {
            var row = grid.getSelected();
            if (row) {
                nui.open({
                    url: nui.context+"/pub/grant/pack/param/item_edit.jsp?itemId="+row.paramid+"&view="+v,
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
        
      
	</script>
</body>
</html>
