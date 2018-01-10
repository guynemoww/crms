<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s):lujinbin
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
		<input name="item.productRuleName" required="false" class="nui-buttonEdit nui-form-input" vtype="maxLength:100" dictTypeId="product" onbuttonclick="selectProduct"/>

	</div>
</div>
				
<div class="nui-toolbar" style="padding-right:20px;text-align:right;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
	<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
</div>
<div class="nui-toolbar" style="border-bottom:0;">
	<a class="nui-button" iconCls="icon-add" onclick="add()">增加</a>
	<a class="nui-button" iconCls="icon-edit" onclick="xuanze(0)">编辑</a>
	<a class="nui-button" iconCls="icon-edit" onclick="xuanze(1)">查看</a>
	<a class="nui-button" iconCls="icon-remove" onclick="remove()">删除</a>
	<a class="nui-button" id="btnBind" iconCls="icon-edit" onclick="bind()">关联机构</a>
</div>
	    
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.pub.systechproduct.getItemList.biz.ext" dataField="items"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="productRuleCd" headerAlign="center" allowSort="true" >授信产品控制规则代码</div>
		<div field="productRuleName" headerAlign="center" allowSort="true" dictTypeId="product" >授信产品控制规则名称</div>
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
                url: nui.context + "/pub/product/rule/item_add.jsp",
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
        // 选择业务品种参数
         function xuanze(v) {
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
                       //  var iframe = this.getIFrameEl();
                      //   var data = row;
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
        
       <%--  
        function edit(v) {
            var row = grid.getSelected();
            if (row) {
                nui.open({
                    url: nui.context + "/pub/product/rule/item_edit.jsp?itemId="+row.productRuleCd+"&view="+v,
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
        --%> 
        function remove() {
            var row = grid.getSelected();
            
            if (row) {
            	nui.confirm("确定删除吗？","确认",function(action){
	            	if(action!="ok") return;
	            	var json=nui.encode({"item":{"productRuleCd":
	            		row.productRuleCd,
						"_entity":"com.bos.pub.product.TbSysTechProduct"}});
	                $.ajax({
	                     url: "com.bos.pub.product.delRule.biz.ext",
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
function bind(){
        	var row = grid.getSelected();
            if (row) {
				nui.open({
		            url: nui.context + "/pub/product/product/item_bind.jsp?itemId="+row.productRuleCd+"&productCd="+row.productRuleName,
		            title: "添加品种机构", width: 350, height: 250,
		            onload:function(){
		            },
		            ondestroy: function (action) {
		               if(action=="ok"){
			                grid.reload();
		               }
		            }
		        });
               }   
           	else {
                alert("请选中一条记录");
            }    
        }
        
        function selectProduct(){

		 var btnEdit = this;
		        nui.open({
		            url: nui.context + "/pub/product/product/select_product_tree.jsp",
		            showMaxButton: true,
		            title: "选择产品",
		            width: 250,
		            height: 450,
		            ondestroy: function (action) {            
		                if (action == "ok") {
		                    var iframe = this.getIFrameEl();
		                    var data = iframe.contentWindow.currentNode;
		                    data = nui.clone(data);
		                    if (data) {
		                       btnEdit.setValue(data.productCd);
		                    }
		                }
		            }
		        });            
}
	</script>
</body>
</html>
