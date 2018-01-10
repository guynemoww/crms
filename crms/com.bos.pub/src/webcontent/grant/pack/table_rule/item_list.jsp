<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-04-08
  - Description:TB_PUB_GRANT_TABLE_RULE, com.bos.pub.decision.TbPubGrantTableRule
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input id="tbPubGrantTableRule.pid" name="tbPubGrantTableRule.pid" class="nui-hidden" value="<%=request.getParameter("itemId") %>"/>
	<div class="nui-dynpanel" columns="4">
		<label>规则顺序优先级：</label>
		<input name="tbPubGrantTableRule.rno" required="false" class="nui-textbox nui-form-input" vtype="maxLength:4" />

		<label>规则标识：</label>
		<input name="tbPubGrantTableRule.rind" required="false" class="nui-textbox nui-form-input" vtype="maxLength:10" />
	</div>
</div>
				
<div class="nui-toolbar" style="padding-right:20px;text-align:right;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
	<a class="nui-button" onclick="reset()">重置</a>
	<a class="nui-button" iconCls="icon-close" onclick="CloseWindow();">关闭</a>
</div>
<div class="nui-toolbar" style="border-bottom:0;">
	<a class="nui-button" iconCls="icon-add" onclick="add()">增加</a>
	<a class="nui-button" iconCls="icon-edit" onclick="edit(0)">编辑</a>
	<a class="nui-button" iconCls="icon-edit" onclick="edit(1)">查看</a>
	<a class="nui-button" iconCls="icon-remove" onclick="remove()">删除</a>
</div>
	    
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.pub.decision.getTbPubGrantTableRuleList.biz.ext"
	dataField="tbPubGrantTableRules"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<!--<div field="rpid" headerAlign="center" allowSort="true" >授权表关联编号</div>
		<div field="pid" headerAlign="center" allowSort="true" >授权表编号</div>-->
		<div field="rind" headerAlign="center" allowSort="true" >规则标识</div>
		<div field="rno" headerAlign="center" allowSort="true" >规则顺序优先级</div>
		<div field="orgNum" headerAlign="center" allowSort="true" dictTypeId="org">创建机构</div>
		<div field="createTime" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd HH:mm:ss" >创建时间</div>
		<div field="userNum" headerAlign="center" allowSort="true" dictTypeId="user">创建人</div>
		</div>
	</div>
			
    <script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	var itemId = "<%=request.getParameter("itemId") %>"
	
    function search() {
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
    }
    search();
    
    function reset(){
		form.reset();
		nui.get("tbPubGrantTableRule.pid").setValue(itemId);
	}
	
    function add() {
        nui.open({
            url: "pub/grant/pack/table_rule/item_add.jsp?itemId="+itemId,
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
                url: "pub/grant/pack/table_rule/item_edit.jsp?rpid="+row.rpid+"&view="+v,
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
        	var count;
        	var tbPubGrantTableCols;
        	var json1 = nui.encode({"tbPubGrantTableCol":{"rind":
            		row.rind}})
        	$.ajax({
                    url: "com.bos.pub.decision.getTbPubGrantTableColList.biz.ext",
	                type: 'POST',
	                data: json1,
	                cache: false,
	                async : false, 
	                contentType:'text/json',
                    success: function (text) {
                    	if (text.msg) {
                    		nui.alert(text.msg);
                    		return;
                    	}
                    	tbPubGrantTableCols = text.tbPubGrantTableCols || [];
                    },
                    error: function () {
                    	nui.alert("操作失败！");
                    }
                });

                if(tbPubGrantTableCols.length>0){
                 	alert("不能删除,授权表表项中存在相应记录");
	             	return;
                }
        	nui.confirm("确定删除吗？","确认",function(action){
            	if(action!="ok") return;
            	var json=nui.encode({"tbPubGrantTableRule":{"rpid":
            		row.rpid,version:row.version}});
                $.ajax({
                     url: "com.bos.pub.decision.delTbPubGrantTableRule.biz.ext",
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

	</script>
</body>
</html>
