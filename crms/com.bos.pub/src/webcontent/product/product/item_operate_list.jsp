<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): lujinbin
  - Date: 2013-10-31
  - Description:TB_SYS_PRODUCT, com.bos.pub.product.TbSysProduct
-->
<head>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	
	<div class="nui-dynpanel" columns="2">
		

	<label>授信品种参数代码：</label>
	<input name="proOrg.productId" required="false" class="nui-textbox" allowInput="false"	 />

	<label>机构名称：</label>
	<input name="proOrg.orgId" required="false" class="nui-buttonEdit" onbuttonclick="selectEmpOrg"/>

	</div>
	</div>

	<div class="nui-toolbar" style="padding-right:20px;text-align:right;padding-top:5px;padding-bottom:5px;" borderStyle="border:0;">
		<a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
		<a class="nui-button" onclick="reset()">重置</a>
	</div>
	<div class="nui-toolbar" style="border-bottom:0;">
	
		<a class="nui-button" iconCls="icon-edit" onclick="edit(1)">查看</a>
		<a class="nui-button" iconCls="icon-remove" onclick="remove()">删除</a>

	</div>
	
	<div id="grid1" class="nui-datagrid" style="width:100%;height:auto"
		url="com.bos.pub.product.getOperate.biz.ext" dataField="items"
		allowResize="true" showReloadButton="false"
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15"
		sortMode="client">
	<div property="columns">
	<div type="checkcolumn">选择</div>
	<div field="productId" headerAlign="center" allowSort="true" >授信品种参数代码</div>
	<div field="orgNum"  headerAlign="center" allowSort="true" dictTypeId="org">机构名称</div>
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
		function renderYesorno(e) {
			return nui.getDictText('ABF_YESORNO',e.row.discountInd);
		}
		
		function selectEmpOrg(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/sys/select_org_tree.jsp",
            showMaxButton: true,
            title: "选择机构",
            width: 350,
            height: 450,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.GetData();
                    data = nui.clone(data);
                    if (data) {
                        btnEdit.setValue(data.orgid);
                        btnEdit.setText(data.orgname);
                    }
                }
            }
        });            
    }
		
		
		
	function selectProduct(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/product/product/select_product_tree.jsp",
            title: "选择",
            width: 800,
            height: 450,
            ondestroy: function (action) {
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.currentNode;
                    data = nui.clone(data);
                    if (data) {
                        btnEdit.setValue(data.productCd);//alert(nui.encode(data));
                        btnEdit.setText(data.productName);
                    }
                }
                if (action == "clear") { //清空选择的内容
                	btnEdit.setValue("");
                	btnEdit.setText("");
            	}
        	},
        	onload: function () {
                        var iframe = this.getIFrameEl();
                        //iframe.contentWindow.save();
                        //this.max();//最大化窗口
                    }
        	
	        });            
	}

		
        function add() {
            nui.open({
                url: nui.context + "/pub/product/product/item_add.jsp?productId="+productId,
                title: "新增", 
                width: 800, 
            	height: 500,
            	allowResize:true,
            	showMaxButton: true,
                ondestroy: function (action) {
                    if(action=="ok"){
                        grid.reload();
                        parent.reload();
                    }
                }
            });
        }
        
        function edit(v) {
            var row = grid.getSelected();
            if (row) {
                nui.open({
                    url: nui.context + "/pub/product/product/item_operate_edit.jsp?itemId="+row.orgOperateAuthorizationId+"&view="+v,
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
	                       //  parent.reload();
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
	            	var json=nui.encode({"item":{"orgOperateAuthorizationId":
	            		row.orgOperateAuthorizationId,
						"_entity":"com.bos.pub.product.TbSysOperateAuthorization"}});
	                $.ajax({
	                     url: "com.bos.pub.crud.delItem.biz.ext",
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
		            url: nui.context + "/pub/product/product/item_bind.jsp?itemId="+row.productId,
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

	</script>
</body>
</html>
