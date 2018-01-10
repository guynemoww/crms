<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): lujinbin
  - Date: 2013-10-31
  - Description:TB_SYS_PRODUCT, com.bos.pub.product.TbSysProduct
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input type="hidden" name="item._entity" value="com.bos.dataset.pub.TbPubImageType" class="nui-hidden" />
	<input type="hidden" name="item.superiorId" id="item.superiorId" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">
		<label>影像类型代码：</label>
		<input name="item.imageTypeId" required="false" class="nui-textbox nui-form-input" vtype="maxLength:6" />

		<label>影像类型名称：</label>
		<input name="item.imageTypeName" required="false" class="nui-textbox nui-form-input" allowInput="false" />
			
	</div>
</div>
				
<div class="nui-toolbar" style="padding-right:20px;text-align:right;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
	<a class="nui-button" onclick="reset()">重置</a>
</div>
<div class="nui-toolbar" style="border-bottom:0;">
	<a class="nui-button" iconCls="icon-add" onclick="add()">增加</a>
	<a class="nui-button" iconCls="icon-edit" onclick="edit(0)">编辑</a>
	<a class="nui-button" iconCls="icon-edit" onclick="edit(1)">查看</a>
	<a class="nui-button" id="remove" iconCls="icon-remove" onclick="remove()">删除</a>
</div>
	    
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.pub.image.getImageTypeList1.biz.ext" dataField="items"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="imageTypeId" headerAlign="center" allowSort="true" >影像类型代码</div>
		<div field="imageTypeName" headerAlign="center" allowSort="true" >影像类型名称</div>
		<div field="imageModelType" headerAlign="center" allowSort="true"  dictTypeId="XD_GGCD9998">影像模板类型</div>
		<%--<div field="imageControlType" headerAlign="center" allowSort="true" >适用岗位</div>
		<div field="imageRequireStatus" headerAlign="center" allowSort="true" dictTypeId="YesOrNo">是否必传</div>--%>
		<div field="flowModuleType" headerAlign="center" allowSort="true" dictTypeId="XD_GG20099">业务阶段</div>
		</div>
	</div>
			
    <script type="text/javascript">
	 	nui.parse();
	    var form = new nui.Form("#form1"); 
		var grid = nui.get("grid1");
		var imageTypeId = "<%=request.getParameter("imageTypeId") %>";
		var imageModelType = "<%=request.getParameter("imageModelType") %>";
	
		
        function search() {
			if (null != imageTypeId && ''!=imageTypeId && 'null'!=imageTypeId) {
				nui.get("item.superiorId").setValue(imageTypeId);
				var data = form.getData(); //获取表单多个控件的数据
            	grid.load(data);
			}
        }
        search();
        
        function reset(){
			form.reset();
			search();
		}
	

		
        function add() {
            nui.open({
                url: nui.context + "/pub/imagePlatform/imageType/item_add.jsp?superiorId="+imageTypeId+"&imageModelType="+imageModelType,
                title: "新增", 
                width: 800, 
            	height: 300,
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
                    url: nui.context + "/pub/imagePlatform/imageType/item_edit.jsp?itemId="+row.imageTypeId+"&view="+v,
                    title: "编辑", 
                    width: 800,
            		height: 300,
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
	                        parent.reload();
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
            	if('2'==row.isSrc){
            	
            		nui.alert("自定义目录，非模板默认目录，不可以删除！");
            		return;
            	}
            	var isleaf = row.imageRequireStatus;
            	if('0'== isleaf){
            		var json = {"imageTypeId":row.imageTypeId};
		   	      	var msg = exeRule("RIMG_0002","1",json);
		   	      	if(null != msg && '' != msg){
			   	      nui.alert(msg+",不能删除！");
			   	      return;
		   	      	}
            	}else{
            	
            		var json = {"imageTypeId":row.imageTypeId};
		   	      	var msg = exeRule("RIMG_0001","1",json);
		   	      	if(null != msg && '' != msg){
			   	      nui.alert(msg+",不能删除！");
			   	      return;
		   	      	}
            	
            	}
            	
            	nui.confirm("确定删除吗？","确认",function(action){
	            	if(action!="ok") return;
	            	var json=nui.encode({item:row});
	                $.ajax({
	                     url: "com.bos.pub.image.delItem.biz.ext",
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
