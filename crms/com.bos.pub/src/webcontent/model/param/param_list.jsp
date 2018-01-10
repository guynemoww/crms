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
							<td align="right">动态参数名称：</td>
							<td>
							  <input type="hidden" name="criteria/_entity" value="com.bos.pub.model.TbPubParam" class="nui-hidden" />
							  <input name="criteria/_expr[1]/paramName" class="nui-textbox"/>
				              <input type="hidden" class="nui-hidden" name="criteria/_expr[1]/_op" value="like"/>
				              <input type="hidden" class="nui-hidden" name="criteria/_expr[1]/_likeRule" value="all"/>
					        </td>
							<td align="right">参数类型：</td>
							<td>
								<input class="nui-dictcombobox" name="criteria/_expr[2]/paramType" valueField="dictID" emptyText="请选择"
									textField="dictName" showNullItem="true" nullItemText="请选择" dictTypeId="model_param_type"/>
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
	<a class="nui-button" iconCls="icon-edit" onclick="edit(0)" id="edit_btn">编辑</a>
	<a class="nui-button" iconCls="icon-edit" onclick="edit(1)">查看</a>
	<a class="nui-button" iconCls="icon-remove" onclick="remove()">删除</a>
	&nbsp;
	&nbsp;
	<a class="nui-button" onclick="setNormal()">启用</a>
	<a class="nui-button" onclick="setAbNormal()">停用</a>
</div>
	    
		    <div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
		    url="com.bos.pub.model.param.getParamList.biz.ext" dataField="params"
		    onselectionchanged="selectionChanged" idField="paramId" allowResize="false" showReloadButton="false"
		    sizeList="[10,15,20,50,100]" multiSelect="false" onrowdblclick="edit()" pageSize="15" sortMode="client">
			    <div property="columns">
			        <div type="checkcolumn" ></div>
			        <div field="paramName" headerAlign="center" allowSort="true">动态参数名称</div>    
			        <!--<div field="paramInd" headerAlign="center" allowSort="true">参数标识</div>-->
			        <div field="paramStatus" headerAlign="center" allowSort="true" renderer="onStatus">参数状态</div>
			        <!--<div field="paramClass" headerAlign="center" allowSort="true" renderer="onClass">参数分类</div>-->
			        <div field="paramType" headerAlign="center" allowSort="true" renderer="onType">参数类型</div>
			        <div field="paramExpr" headerAlign="center" allowSort="false" autoEscape="false">参数表达式</div>
<!--			        <div field="paramExprLink" headerAlign="center" allowSort="false" autoEscape="false">参数表达式</div>-->
			        <div field="financeVar" headerAlign="center" allowSort="true">财报变量</div>
			    </div>
			</div>
			
    <script type="text/javascript">
	 	nui.parse();
	    var form = new nui.Form("#form1"); 
		var grid = nui.get("grid1");
		var cnt=0;
		
        function search() {
			grid.on("preload",function(e){
       			if (!e.data || e.data.length < 1)
       				return;
       			for (var i=0; i<e.data.length; i++){
       				cnt++;
       				e.data[i]['paramExprLink']='&nbsp;<a href="#" onclick="viewExpr(\''
       					+ e.data[i].paramId
       					+ '\','
       					+ cnt
       					+ ');return false;">查看</a>&nbsp;&nbsp;<span id="v'+cnt+'" ></span>';
       			}
       		});
			var data = form.getData(false,false); //获取表单多个控件的数据
            grid.load(data);
        }
        search();
        
        function viewExpr(paramId, divId) {
        	if (document.getElementById("v"+divId).innerHTML.length > 0) {
        		document.getElementById("v"+divId).innerHTML="";
        		return;
        	}
        	
        	var o={"param":{"paramId": paramId}};
        	var json=nui.encode(o);
        	$.ajax({
            	url: "com.bos.pub.model.param.getParamExpr.biz.ext",
            	type: 'POST',
            	data: json,
            	cache: false,
            	contentType:'text/json',
           		success: function (text) {
            		if(text.msg){
            			nui.alert(text.msg);
            		} else { 
            			document.getElementById("v"+divId).innerHTML="<pre>"+text.expr+"</pre>";
            		}
            	},
            	error: function (jqXHR, textStatus, errorThrown) {
                	nui.alert(jqXHR.responseText);
            	}
			});
        }
        
        function add() {
            nui.open({
                url: "<%=request.getContextPath() %>/pub/model/param/param_add.jsp",
                title: "新增参数", 
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
        
        function edit(v) {
            var row = grid.getSelected();
            if (row) {
            	if (v!=1 && row.paramStatus != '0') {
            		nui.alert("只有未启用状态的参数可编辑");
            		return;
            	}
                nui.open({
                    url: "<%=request.getContextPath() %>/pub/model/param/param_edit.jsp?paramId="+row.paramId+"&v="+v,
                    title: "参数", 
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
        
        function remove() {
            var row = grid.getSelected();
            
            if (row) {
            	if (row.paramStatus != '0') {
            		nui.alert("只有未启用状态的参数可删除");
            		return;
            	}
            	nui.confirm("确定删除吗？","确认",function(action){
	            	if(action!="ok") return;
	            	var json = nui.encode({"param":{"paramId":row.paramId}});//nui.alert(json);return;
	                $.ajax({
	                     url: "com.bos.pub.model.param.delParam.biz.ext",
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
            	if (row.paramStatus != '0') {
            		nui.alert("只有未启用状态的参数可启用");
            		return;
            	}
            	
            	nui.confirm("启用后不可修改，确定启用吗？","确认",function(action){
	            	if(action!="ok") return;
	            	var data=nui.clone(row);
	            	data.paramStatus='1';
	            	var json = nui.encode({"param":data});//nui.alert(json);return;
	                $.ajax({
	                     url: "com.bos.pub.model.param.updateParam.biz.ext",
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
            	if (row.paramStatus != '1') {
            		nui.alert("只有正常状态的参数可停用");
            		return;
            	}
            	
            	nui.confirm("停用后不可修改或删除，确定停用吗？","确认",function(action){
	            	if(action!="ok") return;
	            	var data=nui.clone(row);
	            	data.paramStatus='2';
	            	var json = nui.encode({"param":data});//nui.alert(json);return;
	                $.ajax({
	                     url: "com.bos.pub.model.param.updateParam.biz.ext",
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
			return nui.getDictText("model_param_status", e.row.paramStatus);
        }
        
        function onType(e) {
            return nui.getDictText("model_param_type", e.row.paramType);
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