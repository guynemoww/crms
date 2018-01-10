<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2013-10-14 10:38:33
  - Description:参数列表
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
<table style="width:100%;height:auto;table-layout:fixed;" class="nui-form-table">
						<tr>
							<td >模型名称：</td>
							<td>
							  <input type="hidden" name="criteria/_entity" value="com.bos.pub.model.TbPubModel" class="nui-hidden" />
							  <input name="criteria/_expr[1]/modelName" class="nui-textbox"/>
				              <input type="hidden" class="nui-hidden" name="criteria/_expr[1]/_op" value="like"/>
				              <input type="hidden" class="nui-hidden" name="criteria/_expr[1]/_likeRule" value="all"/>
					        </td>
							<td >模型状态：</td>
							<td>
								<input class="nui-dictcombobox" name="criteria/_expr[2]/modelStatus" emptyText="请选择"
									showNullItem="true" nullItemText="请选择" dictTypeId="XD_PJCD0012"/>
								<input type="hidden" class="nui-hidden" name="criteria/_expr[2]/_op" value="="/>
					        </td>
					        <td class="btn-wrap"></td>
						</tr>
					</table>
				</div>
				
<div class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
	<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
</div>
<div class="nui-toolbar" style="border-bottom:0;">
	<a class="nui-button" iconCls="icon-add" onclick="add()">增加</a>
	<a class="nui-button" iconCls="icon-edit" onclick="edit()" id="edit_btn">编辑</a>
	<a class="nui-button" iconCls="icon-edit" onclick="view()">查看</a>
	<a class="nui-button" iconCls="icon-remove" onclick="remove()">删除</a>
	&nbsp;
	&nbsp;
	<a class="nui-button" onclick="setNormal()">启用</a>
	<a class="nui-button" onclick="setAbNormal()">停用</a>
	<a class="nui-button" onclick="modelIndex(0)">编辑模型指标</a>
	<a class="nui-button" onclick="modelIndexView(0)">测试模型（树结构）</a>
	<a class="nui-button" onclick="modelIndexView(1)">查看模型（树结构）</a>
</div>
	    
		    <div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
		    url="com.bos.pub.model.model.getModelList.biz.ext" dataField="items"
		    onselectionchanged="selectionChanged" idField="indexId" allowResize="false" showReloadButton="false"
		    sizeList="[10,15,20,50,100]" multiSelect="false" onrowdblclick="edit()" pageSize="15" sortMode="client">
			    <div property="columns">
			        <div type="checkcolumn" ></div>
			        <div field="modelName" headerAlign="center" allowSort="true">模型名称</div>    
			        <div field="modelDesc" headerAlign="center" allowSort="true">模型描述</div>
			        <div field="modelStatus" headerAlign="center" allowSort="true" renderer="onStatus">模型状态</div>
			        <div field="gradeType" headerAlign="center" allowSort="true" renderer="onType">总分计算方式</div>
			    </div>
			</div>
			
    <script type="text/javascript">
	 	nui.parse();
	    var form = new nui.Form("#form1"); 
		var grid = nui.get("grid1");
		var cnt=0;
		
        function search() {
			var data = form.getData(false,false); //获取表单多个控件的数据
            grid.load(data);
        }
        search();

		function modelIndex(v) {
			var row = grid.getSelected();
            if (row) {
            	if (row.modelStatus != '1' && v!=1) {
            		nui.alert("只有未启用状态时可编辑");
            		return;
            	}
                nui.open({
                    url: "<%=request.getContextPath() %>/pub/model/model/model_index.jsp?itemId="
                    	+row.modelId+"&view="+v,
                    title: "模型指标", 
                    onload: function () {
                        var iframe = this.getIFrameEl();
                        var data = row;
                        //iframe.contentWindow.SetData(data);
                        //this.max();//最大化窗口
                    },
                    ondestroy: function (action) {
                        if(action=="ok"){
	                        //grid.reload();
                   	 	}
                    }
                });
            } else {
                alert("请选中一条记录");
                return;
            }
		}
		
		function modelIndexView(v) {
			var row = grid.getSelected();
            if (!row) {
                alert("请选中一条记录");
                return;
            }

                nui.open({
                    url: "<%=request.getContextPath() %>/pub/model/model/model_index_tree.jsp?itemId="
                    	+row.modelId+"&view="+v,
                    title: "模型树", 
                    width: 800,
            		height: 500,
                    allowResize:false,
            		showMaxButton: true,
                    onload: function () {
                        var iframe = this.getIFrameEl();
                        var data = row;
                        //iframe.contentWindow.SetData(data);
                        //this.max();//最大化窗口
                    },
                    ondestroy: function (action) {
                        if(action=="ok"){
	                        //grid.reload();
                   	 	}
                    }
                });
		}

        function add() {
            nui.open({
                url: "<%=request.getContextPath() %>/pub/model/model/model_add.jsp",
                title: "新增", 
                width: 800, 
            	height: 500,
            	allowResize:false,
            	showMaxButton: true,
                ondestroy: function (action) {
                    if(action=="ok"){
                        grid.reload();
                    }
                }
            });
        }
        
        function edit() {
            var row = grid.getSelected();
            if (row) {
            	if (row.modelStatus != '1') {
            		nui.alert("只有未启用状态时可编辑");
            		return;
            	}
                nui.open({
                    url: "<%=request.getContextPath() %>/pub/model/model/model_edit.jsp?itemId="+row.modelId,
                    title: "编辑", 
                    width: 800,
            		height: 500,
                    allowResize:false,
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
        
        function view() {
            var row = grid.getSelected();
            if (row) {
                nui.open({
                    url: "<%=request.getContextPath() %>/pub/model/model/model_edit.jsp?view=1&itemId="+row.modelId,
                    title: "查看", 
                    width: 800,
            		height: 500,
                    allowResize:false,
            		showMaxButton: true,
                    onload: function () {
                        var iframe = this.getIFrameEl();
                        var data = row;
                        //iframe.contentWindow.SetData(data);
                    },
                    ondestroy: function (action) {
                        if(action=="ok"){
	                        //grid.reload();
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
            	if (row.modelStatus != '1') {
            		nui.alert("只有未启用状态时可删除");
            		return;
            	}
            	nui.confirm("确定删除吗？","确认",function(action){
	            	if(action!="ok") return;
	            	var json = nui.encode({"item":{"modelId":row.modelId}});//nui.alert(json);return;
	                $.ajax({
	                     url: "com.bos.pub.model.model.delModel.biz.ext",
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
        
        function setNormal() {//启用
            var row = grid.getSelected();
            
            if (row) {
            	if (row.modelStatus != '1') {
            		nui.alert("只有未启用状态时可启用");
            		return;
            	}
            	
            	nui.confirm("启用后不可修改，确定启用吗？","确认",function(action){
	            	if(action!="ok") return;
	            	var data=nui.clone(row);
	            	data.modelStatus='2';
	            	var json = nui.encode({"item":data});//nui.alert(json);return;
	                $.ajax({
	                     url: "com.bos.pub.model.model.updateModel.biz.ext",
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
        
        function setAbNormal() {//停用
            var row = grid.getSelected();
            
            if (row) {
            	if (row.modelStatus != '2') {
            		nui.alert("只有正常状态时可停用");
            		return;
            	}
            	
            	nui.confirm("停用后不可修改或删除，确定停用吗？","确认",function(action){
	            	if(action!="ok") return;
	            	var data=nui.clone(row);
	            	data.modelStatus='3';
	            	var json = nui.encode({"item":data});//nui.alert(json);return;
	                $.ajax({
	                     url: "com.bos.pub.model.model.updateModel.biz.ext",
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
        
        function reset(){
			form.reset();
			search();
		}
		
		function onStatus(e) {
			return nui.getDictText("model_model_status", e.row.modelStatus);
        }
		
		function onType(e) {
			return nui.getDictText("model_model_grade", e.row.gradeType);
        }
        
        function selectionChanged(){
			var rows = grid.getSelecteds();
			if(rows.length>1){
				//disable edit button
				nui.get("edit_btn").disable();
			}else{
				nui.get("edit_btn").enable();
			}
		}
	</script>
</body>
</html>