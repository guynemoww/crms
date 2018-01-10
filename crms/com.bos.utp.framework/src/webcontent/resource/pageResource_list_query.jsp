<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="/common/nui/common.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
</head>
<body>
<div id="form1"style="width:100%;height:auto;overflow:hidden;">
	<div class="nui-dynpanel" columns="4">
				<label>按钮标识：</label>
				<input name="resource/resourceid" class="nui-textbox"/>
				<label> 按钮名称：</label>
				<input name="resource/resourcename" class="nui-textbox"/>
    </div>
      <div class="nui-toolbar" style="padding-right:20px;text-align:right;padding-top:5px;padding-bottom:5px;"  borderStyle="border:0;">
    	  				  <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>&nbsp;&nbsp;
						  <a class="nui-button" iconCls="icon-reset" onclick="reset()">重置</a>
							
    </div>
			<div class="nui-toolbar" style="padding:2px;border-bottom:0;">
			                <a class="nui-button"  iconCls="icon-add"  onclick="add()">增加</a>
			            	<a class="nui-button" iconCls="icon-edit"  onclick="edit()" id="edit_btn">编辑</a>
			            	<a class="nui-button" iconCls="icon-remove" onclick="remove()">删除</a>
		    </div>
		    <div id="messagegrid" class="nui-datagrid" 
		    url="com.bos.utp.framework.ResourceManager.queryResourceInfo.biz.ext"  idField="messagecode" allowResize="false"
		    sizeList="[10,20,50,100]" multiSelect="true" multiSelect="false"
		    dataField="resources"  allowCellSelect="false"
		    sortMode="client">
			    <div property="columns">
			        <div type="checkcolumn" ></div>
			        <div field="resourceid" width="100" align="left" headerAlign="center" allowSort="true">按钮标识</div>
			        <div field="resourcename" width="100" align="left" headerAlign="center" allowSort="true">按钮名称</div>  
			        <div field="resourcedesc" width="100" align="left" headerAlign="center" allowSort="true">按钮描述</div>
			        <%--<div field="funccode" width="100" align="left" headerAlign="center" allowSort="true">功能代码</div>--%>
			        <div field="funcname" width="100" align="left" headerAlign="center" allowSort="true">关联菜单名称</div>
			    </div>
			</div>
		</div>

    <script type="text/javascript">
   		nui.parse();
		var grid = nui.get("messagegrid");
		var form = new nui.Form("#form1"); 
        function search() {
			var data = form.getData(false,false);      //获取表单多个控件的数据
            grid.load(data);
        }
        grid.load();
        
        //onselectionchanged="selectionChanged" onrowdblclick="edit()" 
          
        function reset(){//重置
			form.reset();
		}

		
		//增加页面资源信息
		function add() {
            nui.open({
			url:"<%=request.getContextPath() %>/utp/framework/resource/pageResource_add.jsp",
			title:'按钮资源添加',
			width:700,
			height:200,
			onload:function(){
			},
			ondestroy:function(action){
			  if(action =='saveSuccess'){
			    nui.get("messagegrid").reload();
			  }
			}
		   });   
        }
        
        
        //编辑、修改应用域
        function edit() {
            var rows = grid.getSelecteds();
               var row = grid.getSelected();
            if (row) {
            if(rows.length>1){
              alert("请选中一条记录");
            return;
            }
                nui.open({
                    url: "<%=request.getContextPath() %>/utp/framework/resource/pageResource_update.jsp",
                    title: "按钮资源修改",
                    width: 700,
                	height:200,
                    onload: function () {
                        var iframe = this.getIFrameEl();
                        var data = row;
                        iframe.contentWindow.SetData(data);
                    },
                    ondestroy: function (action) {
                    	if(action=='saveSuccess'){
	                        grid.reload();
	                    }
                    }
                });
                
            } else {
                nui.alert("请选中一条页面资源记录！","系统提示");
            }
            
        }
        
        
        //删除信息
        function remove(){
			var rows = grid.getSelecteds();
			if(rows == null || rows.length == 0){
				nui.alert("请至少选中一页面资源记录！");
				return false;
			} else {
				nui.confirm("确定删除选中页面资源记录？", "系统提示", function(action){
					if(action=="ok"){
						var sendData = nui.encode({resources:rows});
						grid.loading("正在删除中,请稍等...");
						$.ajax({
							url:"com.bos.utp.framework.ResourceManager.removeResourceManager.biz.ext",
							type:'POST',
							data:sendData,
							cache: false,
							contentType:'text/json',
							success:function(text){
								var returnJson = nui.decode(text.returnCode);
								if(returnJson=='1'){
									nui.alert("页面资源删除成功！", "系统提示", function(action){
										grid.reload();
									});
								}else{
									nui.alert("页面资源删除失败！", "系统提示");
									grid.unmask();
								}
							}
						});
					}
				});
			}
		}
		
		
		
		
	
        
        //页面权限设置
         /*function setAuth() {
            var row = grid.getSelected();
            if (row) {
                nui.open({
                   url:"/utp/framework/resource/page_Auth.jsp?resouceid="+row.resourceid,
                    title: "页面资源权限设置",
                    width: 700,
                	height:440,
                    onload: function () {
                        var iframe = this.getIFrameEl();
                        var data = row;
                        iframe.contentWindow.SetData(data);
                    },
                    ondestroy: function (action) {
                    	if(action=='saveSuccess'){
	                        grid.reload();
	                    }
                    }
                });
                
            } else {
                nui.alert("请选中一条页面资源记录！","系统提示");
            }
            
        }
        
    function setRoleAuth() {
            var row = grid.getSelected();
            if (row) {
                nui.open({
                   url:"<%=request.getContextPath() %>/utp/framework/resource/page_RoleAuth.jsp?resouceid="+row.resourceid,
                    title: "页面资源角色权限设置",
                    width: 700,
                    allowResize:false,
                	height:440,
                    onload: function () {
                        var iframe = this.getIFrameEl();
                        var data = row;
                        iframe.contentWindow.SetData(data);
                    },
                    ondestroy: function (action) {
                    	if(action=='saveSuccess'){
	                        grid.reload();
	                    }
                    }
                });
                
            } else {
                nui.alert("请选中一条页面资源记录！","系统提示");
            }
            
        }
       
        
        
         //页面资源人员权限设置
         function setUserAuth() {
            var row = grid.getSelected();
            if (row) {
                nui.open({
                   url:"<%=request.getContextPath() %>/utp/framework/resource/page_UserAuth.jsp?resouceid="+row.resourceid,
                    title: "资源人员权限设置",
                    width: 700,
                    allowResize:false,
                	height:440,
                    onload: function () {
                        var iframe = this.getIFrameEl();
                        var data = row;
                        iframe.contentWindow.SetData(data);
                    },
                    ondestroy: function (action) {
                    	if(action=='saveSuccess'){
	                        grid.reload();
	                    }
                    }
                });
                
            } else {
                nui.alert("请选中一条页面资源记录！","系统提示");
            }
            
        }
        */
        						     
	</script>
</body>
</html>