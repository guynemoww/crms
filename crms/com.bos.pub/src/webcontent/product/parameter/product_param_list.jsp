<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): ljf
  - Date: 2016-03-10
  - Description：产品控制参数维护
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1"class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
	<input  name="item._entity" value="com.bos.pub.product.TbSysProductParam" class="nui-hidden" />
	<input  id="item.productId" name="item.productId" value="<%=request.getParameter("itemId") %>" class="nui-hidden" />
	<div class="nui-dynpanel" columns="6">
		<label>授权机构：</label>
		<input name="item.authOrgNum" required="false" class="nui-buttonEdit nui-form-input" allowInput="false" onbuttonclick="selectOrg" />

		<label>参数类型：</label>
		<input id="item.paraType" name="item.paraType"  class="nui-combobox" textField="text" valueField="id"  emptyText="--请选择--"/>

		<label>参数字段名称：</label>
		<input name="item.paraColunmName" required="false" class="nui-textbox nui-form-input"/>

		<label>参数状态：</label>
		<input name="item.paraStatus" required="false" class="nui-dictcombobox nui-form-input" dictTypeId="XD_FLCD0015" emptyText="请选择"/>
		
	</div>
				
<div class="nui-toolbar" style="padding-right:20px;text-align:right;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
	<a class="nui-button" onclick="reset()">重置</a>
</div>
</div>
<div class="nui-toolbar" style="border-bottom:0;">
	<a id="add" class="nui-button" iconCls="icon-add" onclick="add()">增加</a>
	<a id="edit" class="nui-button" iconCls="icon-edit" onclick="edit(0)">编辑</a>
	<a id="view" class="nui-button" iconCls="icon-zoomin" onclick="edit(1)">查看</a>
	<a id="remove" class="nui-button" id="remove" iconCls="icon-remove" onclick="remove()">删除</a>
</div>
	    
<div id="grid1" class="nui-datagrid" style="width:100%;height:68%" 
	url="com.bos.pub.productParam.queryProductPara.biz.ext" dataField="items"
	allowResize="true" showReloadButton="false"allowAlternating="true"
	sizeList="[10,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="productId" headerAlign="center" allowSort="true" dictTypeId="product">授信品种</div>
		<div field="authOrgNum" headerAlign="center" allowSort="true" dictTypeId="org">授权机构</div>
		<div field="paraType" headerAlign="center" allowSort="true" renderer="onParaTypeRenderer" >参数类型</div>
		<div field="paraColumn" headerAlign="center" allowSort="true" >参数字段</div>
		<div field="paraColunmName" headerAlign="center" allowSort="true" >参数字段名称</div>
		<div field="paraCountSign" headerAlign="center" allowSort="true" renderer="onSignRenderer">参数运算符号</div>
		<div field="paraContrlLeftval" headerAlign="center" allowSort="true" >控制参数一</div>
		<div field="paraContrlRigthval" headerAlign="center" allowSort="true" >控制参数二</div>
		<div field="paraStatus" headerAlign="center" allowSort="true" dictTypeId="XD_FLCD0015" >参数状态</div>
	</div>
</div>
			
    <script type="text/javascript">
	 	nui.parse();
	    var form = new nui.Form("#form1"); 
		var grid = nui.get("grid1");
		var v = "<%=request.getParameter("view") %>";
		var productId = nui.get("item.productId").getValue();
		//页面初始区
		var paraTypes =[
		{"id":"","text":"--请选择--"},
		{"id":"1","text":"业务申请"},
		{"id":"2","text":"合同签订"},
		{"id":"3","text":"出账放款"}
		];			
		nui.get("item.paraType").setData(paraTypes);
		
		//如果是查看，隐藏所有编辑按钮
		if('1' === v){
		
			nui.get("add").hide();
			nui.get("edit").hide();
			nui.get("remove").hide();
		}
		
		//翻译参数类型
		function onParaTypeRenderer(e){
  		
	  		for (var i = 0, l = paraTypes.length; i < l; i++) {
	            var g = paraTypes[i];
	            if (g.id == e.value) return g.text;
	        }
	        return "";
	  	}
	  	//翻译运算符号
	  	var paraCountSigns =[
		{"id":"","text":"--请选择--"},
		{"id":"=","text":"="},
		{"id":">","text":">"},
		{"id":"<","text":"<"},
		{"id":">=","text":">="},
		{"id":"<=","text":"<="},
		{"id":"between","text":"between"},
		{"id":"include","text":"包含"},
		{"id":"unclude","text":"不包含"}
		];
	  	function onSignRenderer(e){
	  	
	  		for (var i = 0, l = paraCountSigns.length; i < l; i++) {
	            var g = paraCountSigns[i];
	            if (g.id == e.value) return g.text;
	        }
	        return "";
	  	
	  	}
		
		/*
		*控件事件区
		*/
		//机构选择
		function selectOrg(){
		
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
	                        btnEdit.setValue(data.orgcode);
	                        btnEdit.setText(data.orgname);
	                    }
	                }
	            }
	        });      
		}
		/**
		*按扭事件区
		*/
		//查询
        function search() {
			var data = form.getData(); //获取表单多个控件的数据
            grid.load(data);
        }
        search();
        
        //重置
        function reset(){
			form.reset();
		}
		//新增
        function add() {
            nui.open({
                url: nui.context + "/pub/product/parameter/product_param_edit.jsp?productId="+productId,
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
        
        //编辑
        function edit(v) {
            var row = grid.getSelected();
            
            var title = "编辑";
            if('1' == v){
            	title = "查看";
            }
            
            if (row) {
                nui.open({
                    url: nui.context + "/pub/product/parameter/product_param_edit.jsp?pId="+row.pId+"&view="+v+"&productId="+productId,
                    title: title, 
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
        //删除
        function remove() {
            var row = grid.getSelected();
            if (row) {
            	nui.confirm("确定删除吗？","确认",function(action){
	            	if(action!="ok") return;
	            	var json=nui.encode({"item":row});
	                $.ajax({
	                    url: "com.bos.pub.productParam.deleteProductPara.biz.ext",
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
