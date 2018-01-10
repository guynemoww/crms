<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2013-10-14 10:38:33
  - Description:参数列表
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;height:115%;">
<div title="指标列表" >
	<div id="form1" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;" >
		<input type="hidden" class="nui-hidden" name="criteria/_expr[1]/_op" value="like"/>
		<input type="hidden" class="nui-hidden" name="criteria/_expr[1]/_likeRule" value="all"/>
		<input type="hidden" name="criteria/_entity" value="com.bos.pub.model.TbPubIndexBase" class="nui-hidden" />
		<input type="hidden" class="nui-hidden" name="criteria/_expr[2]/_op" value="="/>
	
		<div class="nui-dynpanel" columns="6">						
			<label>指标名称：</label>
			<input name="criteria/_expr[1]/indexName" class="nui-textbox"/>
			<label>指标状态：</label>
			<input class="nui-dictcombobox" name="criteria/_expr[2]/indexStatus" emptyText="请选择"
			showNullItem="true" nullItemText="请选择" dictTypeId="model_index_status"/>
		</div>
		
<div class="nui-toolbar" style="text-align:right;border:none">
    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
	<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
</div>
</div>
<div class="nui-toolbar" style="border-bottom:0;">
	<a class="nui-button" iconCls="icon-add" onclick="add()">增加</a>
	<a class="nui-button" iconCls="icon-edit" onclick="edit()" id="edit_btn">编辑</a>
	<a class="nui-button" iconCls="icon-zoomin" onclick="view()">查看</a>
	<a class="nui-button" iconCls="icon-remove" onclick="remove()">删除</a>
	<a class="nui-button" onclick="setNormal()">启用</a>
	<a class="nui-button" onclick="setAbNormal()">停用</a>
	<a class="nui-button" onclick="querySql()">查询</a>
</div>
	    
		   <div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
		    url="com.bos.pub.model.index.getIndexList.biz.ext" dataField="indexes"
		    onselectionchanged="selectionChanged" idField="indexId" allowResize="false" showReloadButton="false"
		    sizeList="[10,15,20,50,100]" multiSelect="false" onrowdblclick="edit()" pageSize="15" sortMode="client">
			    <div property="columns">
			        <div type="checkcolumn" ></div>
			        <div field="indexName" headerAlign="center" allowSort="true">指标名称</div>    
			        <div field="indexInd" headerAlign="center" allowSort="true">指标标识</div>
			        <div field="indexStatus" headerAlign="center" allowSort="true" renderer="onStatus">指标状态</div>
			        <div field="indexType" headerAlign="center" allowSort="true" renderer="onType">指标类型</div>
			        <div field="indexDesc" headerAlign="center" allowSort="true">指标描述</div>
			    </div>
			</div>
	</div></div>		
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
        
        function add() {
            nui.open({
                url: "<%=request.getContextPath() %>/pub/model/baseindex/index_add.jsp",
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
            	if (row.indexStatus != '0') {
            		nui.alert("只有未启用状态时可编辑");
            		return;
            	}
                nui.open({
                    url: "<%=request.getContextPath() %>/pub/model/baseindex/index_edit.jsp?indexId="+row.indexId,
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
                    url: "<%=request.getContextPath() %>/pub/model/baseindex/index_edit.jsp?view=1&indexId="+row.indexId,
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
            	if (row.indexStatus != '0') {
            		nui.alert("只有未启用状态时可删除");
            		return;
            	}
            	nui.confirm("确定删除吗？","确认",function(action){
	            	if(action!="ok") return;
	            	
	            	//指标删除时，删除规则表记录
	            	var json = nui.encode({"base":{"indexId":row.indexId}});//nui.alert(json);return;
	                $.ajax({
	                     url: "com.bos.pub.model.index.delIndex.biz.ext",
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
            	if (row.indexStatus != '0') {
            		nui.alert("只有未启用状态时可启用");
            		return;
            	}
            	
            	nui.confirm("启用后不可修改，确定启用吗？","确认",function(action){
	            	if(action!="ok") return;
	            	var data=nui.clone(row);
	            	data.indexStatus='1';
	            	var json = nui.encode({"base":data});//nui.alert(json);return;
	                $.ajax({
	                     url: "com.bos.pub.model.index.updateIndex.biz.ext",
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
            	if (row.indexStatus != '1') {
            		nui.alert("只有正常状态时可停用");
            		return;
            	}
            	
            	nui.confirm("停用后不可修改或删除，确定停用吗？","确认",function(action){
	            	if(action!="ok") return;
	            	var data=nui.clone(row);
	            	data.indexStatus='2';
	            	var json = nui.encode({"base":data});//nui.alert(json);return;
	                $.ajax({
	                     url: "com.bos.pub.model.index.updateIndex.biz.ext",
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
			return nui.getDictText("model_index_status", e.row.indexStatus);
        }
		
		function onType(e) {
			return nui.getDictText("model_index_type", e.row.indexType);
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
		
		
		  function querySql() {
            nui.open({
                url: "<%=request.getContextPath() %>/pub/log/query_sql.jsp",
                title: "SQL查询", 
                width: 1000, 
            	height: 600,
            	allowResize:false,
            	showMaxButton: true,
                ondestroy: function (action) {
                    if(action=="ok"){
                        grid.reload();
                    }
                }
            });
        }
	</script>
</body>
</html>