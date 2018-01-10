<%@page pageEncoding="UTF-8"%>
<fieldset>
		<legend>
		   		<span>物业信息</span>
		</legend>
		<div>
			<div class="nui-toolbar" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px">
				<a class="nui-button" id="wy_add" iconCls="icon-add" onclick="addwy()">增加</a>
				<a class="nui-button" id="wy_edit" iconCls="icon-edit" onclick="editwy(0)">编辑</a>
				<a class="nui-button" id="wy_edit1" iconCls="icon-node" onclick="editwy(1)">查看</a>
				<a class="nui-button" id="wy_remove" iconCls="icon-remove" onclick="removewy()">删除</a>
			</div>
				    
			<div id="gridwy" class="nui-datagrid" style="width:100%;height:auto" 
				url="com.bos.bizProductDetail.bizWyxx.getWyList.biz.ext" dataField="wyxxs"
				allowResize="false" showReloadButton="false" allowCellEdit="false" 
				sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
				<div property="columns">
					<div type="checkcolumn" >选择</div>
					<div field="wymc" headerAlign="center" allowSort="true" >物业名称</div>
					<div field="wylx" headerAlign="center" allowSort="true" dictTypeId="XD_SXYW0201">物业类型</div>
					<div field="wysyqrmc" headerAlign="center" allowSort="true" >物业所有权人名称</div>
					<div field="wydd" headerAlign="center" allowSort="true" >物业地点</div>
				</div>
			</div>
		</div>
</fieldset>
	
<script type="text/javascript">	
	//添加项目信息
	function addwy(){
		var amountDetailId=nui.get("amountDetail.amountDetailId").getValue();
	    nui.open({
	        url: nui.context + "/biz/biz_product_detail/wyxx/biz_wy_add.jsp?amountDetailId="+amountDetailId,
	        title: "新增", 
	        width: 800, 
	    	height: 500,
	    	allowResize:true,
	    	showMaxButton: true,
	        ondestroy: function (action) {
	       		 if(action=="ok"){
	             	var json = nui.decode({"amountDetailId":amountDetailId});
					var gridwy = nui.get("gridwy");
					gridwy.load(json);
	           	 }
	        }
	    });
	}
	//编辑项目信息
	function editwy(v) {
		var amountDetailId=nui.get("amountDetail.amountDetailId").getValue();
		var grid = nui.get("gridwy");
	    var row = grid.getSelected();
	    if (row) {
	        nui.open({
	            url: nui.context+"/biz/biz_product_detail/wyxx/biz_wy_edit.jsp?wyId="+row.wyId+"&view="+v,
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
	                    var json = nui.decode({"amountDetailId":amountDetailId});
						var gridwy = nui.get("gridwy");
						gridwy.load(json);
	           	 	}
	            }
	        });
	    }else{
	    	alert("请选择项目信息！");
	    }
	}
	//删除关联关系
	function removewy() {
		var grid = nui.get("gridwy");
	    var rows = grid.getSelected();
		if (null == rows) {
			nui.alert("请选择项目信息！");
			return false;
		}
		var json = nui.encode({"tbBizWyxxApply":rows});
		var amountDetailId=nui.get("amountDetail.amountDetailId").getValue();
		nui.confirm("确定删除吗？","确认",function(action){
	    	if(action!="ok") return;
	        $.ajax({
	            url: "com.bos.bizProductDetail.bizWyxx.delWyxx.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            	if (text.msg) {
	            		nui.alert(text.msg);
	            		return;
	            	}
	                var json = nui.decode({"amountDetailId":amountDetailId});
					var gridwy = nui.get("gridwy");
					gridwy.load(json);
	            },
	            error: function () {
	            	nui.alert("操作失败！");
	            }
	        });
	    }); 
	}
</script>