<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): lujinbin
  - Date: 2013-10-31
  - Description:TB_SYS_PRODUCT, com.bos.pub.product.TbSysProduct
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1"class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
	<input type="hidden" name="item._entity" value="com.bos.pub.product.TbSysProduct" class="nui-hidden" />
	<input type="hidden" name="item.superiorId" id="item.superiorId" class="nui-hidden" />
	<div class="nui-dynpanel" columns="6">
		<label>授信品种代码：</label>
		<input name="item.productCd" required="false" class="nui-textbox nui-form-input" />

		<label>授信品种名称：</label>
		<input name="item.productName" required="false" class="nui-buttonEdit nui-form-input" allowInput="false" onbuttonclick="selectProduct"/>

		<label>是否显示在产品树中：</label>
		<input name="item.discountInd" required="false" class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002" emptyText="请选择"/>

		<label>授信品种级别：</label>
		<input name="item.productLevel" required="false" class="nui-dictcombobox nui-form-input" dictTypeId="XD_GGCD842" emptyText="请选择"/>
		
		<label>贴息标志：</label>
		<input name="item.txbz" required="false" class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002" emptyText="请选择"/>
	</div>
				
<div class="nui-toolbar" style="padding-right:20px;text-align:right;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
	<a class="nui-button" onclick="reset()">重置</a>
</div>
</div>
<div class="nui-toolbar" style="border-bottom:0;">
	<a class="nui-button" iconCls="icon-add" onclick="add()">增加</a>
	<a class="nui-button" iconCls="icon-edit" onclick="edit(0)">编辑</a>
	<a class="nui-button" iconCls="icon-zoomin" onclick="edit(1)">查看</a>
	<a class="nui-button" id="remove" iconCls="icon-remove" onclick="remove()">删除</a>
</div>
	    
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.pub.systechproduct.getItemList.biz.ext" dataField="items"
	allowResize="true" showReloadButton="false"allowAlternating="true"
	sizeList="[10,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="productCd" headerAlign="center" allowSort="true" >授信品种代码</div>
		<div field="productName" headerAlign="center" allowSort="true" >授信品种名称</div>
		<div field="discountInd" headerAlign="center" allowSort="true" dictTypeId="XD_0002">是否显示在产品树中</div>
		<div field="productLevel" headerAlign="center" allowSort="true" dictTypeId="XD_GGCD842" >授信品种级别</div>
		<div field="txbz" headerAlign="center" allowSort="true" dictTypeId="XD_0002">贴息标志</div>
	</div>
</div>
			
    <script type="text/javascript">
	 	nui.parse();
	    var form = new nui.Form("#form1"); 
		var grid = nui.get("grid1");
		var productId = "<%=request.getParameter("productId") %>";
		var productLevel="<%=request.getParameter("productLevel") %>";
		var productCd="<%=request.getParameter("productCd") %>";
		var c="<%=request.getParameter("c") %>";
		if(productLevel!="2"){
		nui.get("remove").hide();
		}
		
        function search() {
			if (productId) {
				nui.get("item.superiorId").setValue(productId);
			}
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
		
function selectProduct(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/product/rule/product.jsp",
            title: "选择",
            width: 200,
            height: 450,
            ondestroy: function (action) {
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.GetData();
                    data = nui.clone(data);
                    if (data) {
                        btnEdit.setValue(data.split(":")[1]);//alert(nui.encode(data));
                        btnEdit.setText(data.split(":")[1]);
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
                url: nui.context + "/pub/product/product/item_add.jsp?productId="+productId+"&productLevel="+productLevel+"&productCd="+productCd+"&c="+c,
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
                    url: nui.context + "/pub/product/product/item_tab.jsp?itemId="+row.productId+"&view="+v,
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
	                        // parent.reload();
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
	            	var json=nui.encode({"item":{"productId":
	            		row.productId,
						"_entity":"com.bos.pub.product.TbSysProduct"}});
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
	                         parent.reload();
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
