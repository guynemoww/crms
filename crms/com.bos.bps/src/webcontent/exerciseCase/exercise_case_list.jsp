<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-03-25
  - Description:TB_WFM_WORKITEMMAPPING, com.bos.bps.dataset.bps.TbWfmProcessmapping
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input type="hidden" name="item._entity" value="com.bos.bps.dataset.bps.TbWfmWorkitemmapping" class="nui-hidden" />
	<input name="item.processMappingId"type="hidden" value="<%=request.getParameter("id") %>" required="false" class="nui-hidden nui-form-input" vtype="maxLength:32" />
		<div class="nui-dynpanel" columns="4">

		<label>活动名称：</label>
		<input name="item.activityName" required="false" class="nui-textbox nui-form-input" vtype="maxLength:50" />
		<label>活动id：</label>
		<input name="item.activityDefId" required="false" class="nui-textbox nui-form-input" vtype="maxLength:10" />

	</div>
</div>
<div class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-search" onclick="search()">查询(Enter)</a>
	<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
</div>
<div class="nui-toolbar" style="border-bottom:0;">
	<a class="nui-button" iconCls="icon-add" onclick="add()">增加</a>
	<a class="nui-button" iconCls="icon-edit" onclick="edit(0)">编辑</a>
	<a class="nui-button" iconCls="icon-edit" onclick="edit(1)">查看</a>
	<a class="nui-button" iconCls="icon-remove" onclick="remove()">删除</a>
	<a class="nui-button" iconCls="icon-add" onclick="demo()">操作子表</a>
	<a class="nui-button" iconCls="icon-add" onclick="ret()">返回主表(Esc)</a>
	
</div>
	    
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.bps.util.TbWfmWorkItemMapping.getTbWfmWorkitemmappingList.biz.ext"
	dataField="items"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		 <div type="indexcolumn">序号</div>     
		<div field="activityDefId" headerAlign="center" allowSort="true" >活动定义id</div>
		<div field="activityName" headerAlign="center" allowSort="true" >活动定义名称</div>
		<div field="doUrl" headerAlign="center" allowSort="true" >处理路径</div>
		<div field="ruleId" headerAlign="center" allowSort="true" >规则Id</div>
		<div field="viewUrl" headerAlign="center" allowSort="true" >保存路径</div>
		<div field="PROCESSMAPPINGID" headerAlign="center" allowSort="true" >流程id(主键)</div>
		<div field="workitemMappingId" headerAlign="center" allowSort="true" >活动id(主键)</div>
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
	}
	
    function add() {
        nui.open({
            url: nui.context+"/bps/exerciseCase/exercise_case_add.jsp?processMappingId=<%=request.getParameter("id") %>",
            title: "新增", 
            width: 800, 
        	height: 500,
        	allowResize:true,
        	showMaxButton: true,
            ondestroy: function (action) {
                if(action=="ok"){
                    search();
                }
            }
        });
    }
    
    function edit(v) {
        var row = grid.getSelected();
        if (row) {
            nui.open({
                url: nui.context+"/bps/exerciseCase/exercise_case_edit.jsp?workitemMappingId="+row.workitemMappingId+"&processMappingId="+row.PROCESSMAPPINGID+"&view="+v,
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
                        search();
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
            	var json=nui.encode({"item":{"workitemMappingId":
            		row.workitemMappingId,"processMappingId":row.PROCESSMAPPINGID,"_entity":"com.bos.bps.dataset.bps.TbWfmWorkitemmapping"}});
                $.ajax({
                     url: "com.bos.bps.util.TbWfmProcessMapping.delTbWfmProcessMapping.biz.ext",
	                type: 'POST',
	                data: json,
	                cache: false,
	                contentType:'text/json',
                    success: function (text) {
                    	if (text.msg) {
                    		nui.alert(text.msg);
                    		return;
                    	}
                        search();
                    },
                    error: function () {
                    	nui.alert("操作失败！");
                    }
                });
            }); 
        } else {
            alert("请选中一条记录");
        }
    }
function ret (){
git.go(nui.context+"/bps/flowModel/flow_model_list.jsp");
}
function demo(){
 var row = grid.getSelected();
        
  if (row) {
	git.go(nui.context+"/bps/busiparameter/tbWfmBusiparameter_list.jsp?id="+row.workitemMappingId+"&exerciseId="+row.PROCESSMAPPINGID);
 } else {
            alert("请选中一条记录");
        }
}
	</script>
</body>
</html>
<script type="text/javascript" language=JavaScript charset="UTF-8">
      document.onkeydown=function(event){
            var e = event || window.event || arguments.callee.caller.arguments[0];
            if(e && e.keyCode==27){ // 按 Esc 
                //要做的事情
                ret();
              }
            if(e && e.keyCode==113){ // 按 F2 
                 //要做的事情
                
               }     
            if(e && e.keyCode==46){ // 按 Delete
                 //要做的事情
                remove();
               }          
             if(e && e.keyCode==13){ // enter 键
                 //要做的事情
                search();
            }
        }; 
</script>