<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): lujinbin
  - Date: 2013-10-31
  - Description:TB_SYS_TECH_PRODUCT, com.bos.pub.product.TbSysTechProduct
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input type="hidden" name="item._entity" value="com.bos.pub.product.TbSysTechProduct" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">
		<label>授信产品控制规则代码：</label>
		<input name="item.productRuleCd" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />

		<label>授信产品控制规则名称：</label>
		<input name="item.productRuleName" required="false" class="nui-textbox nui-form-input" vtype="maxLength:100" />

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
	url="com.bos.pub.systechproduct.getItemList.biz.ext" dataField="items"
	allowResize="true" showReloadButton="false" onrowdblclick="save"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="productRuleCd" headerAlign="center" allowSort="true" >授信产品控制规则代码</div>
		<div field="productRuleName" headerAlign="center" allowSort="true" dictTypeId="product">授信产品控制规则名称</div>
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
                    url: nui.context + "/pub/product/rule/eval_rule_tab.jsp?itemId="+row.productRuleCd+"&view="+v,
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
