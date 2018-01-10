<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-04-23
  - Description:TB_REW_AUTO_WARNING_RULE, com.bos.dataset.irm.TbRewAutoWarningRule
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	
	<div class="nui-dynpanel" columns="6">
		<label>行业代码：</label>
		<input name="tbRewAutoWarningRule.industrialTypeCd" class="nui-buttonEdit nui-form-input"  allowInput="false" dictTypeId="XD_KHCD0092" onbuttonclick="selectIndustrial" required="false"  vtype="maxLength:32" />

		<label>规则SQL语句：</label>
		<input name="tbRewAutoWarningRule.ruleSql" required="false" class="nui-textbox nui-form-input" vtype="maxLength:1000" />

		<label>参数1：</label>
		<input name="tbRewAutoWarningRule.parmA" required="false" class="nui-textbox nui-form-input" vtype="maxLength:20;int" />

		<label>参数2：</label>
		<input name="tbRewAutoWarningRule.parmB" required="false" class="nui-textbox nui-form-input" vtype="maxLength:20;int" />

		<label>参数3：</label>
		<input name="tbRewAutoWarningRule.parmC" required="false" class="nui-textbox nui-form-input" vtype="maxLength:20;int" />
	
		<label>参数4：</label>
		<input name="tbRewAutoWarningRule.paramD" required="false" class="nui-textbox nui-form-input" vtype="maxLength:20;int" />

		<label>参数5：</label>
		<input name="tbRewAutoWarningRule.paramE" required="false" class="nui-textbox nui-form-input" vtype="maxLength:20;int" />

		<label>预警信号代码：</label>
		<input name="tbRewAutoWarningRule.signalCode"  class="nui-buttonEdit nui-form-input" allowInput="false" required="false" onbuttonclick="selectSignal"  />

	</div>
</div>
				
<div class="nui-toolbar" style="padding-right:20px;text-align:right;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
	<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
</div>
<div class="nui-toolbar" style="border-bottom:0;">
	<a class="nui-button" iconCls="icon-add" onclick="add()">增加</a>
	<a class="nui-button" iconCls="icon-edit" onclick="edit(0)">编辑</a>
	<a class="nui-button" iconCls="icon-edit" onclick="edit(1)">查看</a>
	<a class="nui-button" iconCls="icon-remove" onclick="remove()">删除</a>
</div>
	    
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.pub.autoWarningRule.getTbRewAutoWarningRuleList.biz.ext"
	dataField="tbRewAutoWarningRules"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="industrialTypeCd" headerAlign="center" allowSort="true" dictTypeId="XD_KHCD0092">行业代码</div>
		<div field="ruleSql" headerAlign="center" allowSort="true" >规则SQL语句</div>
		<div field="parmA" headerAlign="center" allowSort="true" >参数1</div>
		<div field="parmB" headerAlign="center" allowSort="true" >参数2</div>
		<div field="parmC" headerAlign="center" allowSort="true" >参数3</div>
		<div field="parmD" headerAlign="center" allowSort="true" >参数4</div>
		<div field="parmE" headerAlign="center" allowSort="true" >参数5</div>
		<div field="signalCode" headerAlign="center" allowSort="true"  dictTypeId="XD_KH2D0098" >预警信号代码</div>
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
            url: "<%=request.getContextPath() %>/pub/autoWarningRule/item_add.jsp",
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
                url: "<%=request.getContextPath() %>/pub/autoWarningRule/item_edit.jsp?autoWarningId="+row.autoWarningId+"&view="+v,
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
            	var json=nui.encode({"tbRewAutoWarningRule":{"autoWarningId":
            		row.autoWarningId,version:row.version}});
                $.ajax({
                     url: "com.bos.pub.autoWarningRule.delTbRewAutoWarningRule.biz.ext",
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
    
    function selectSignal(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/dict/code_tree.jsp?dicttypeid=XD_KH2D0098",
            title: "选择字典项",
            width: 800,
            height: 450,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.currentNode;
                    data = nui.clone(data);
                    if (data) {
                        btnEdit.setValue(data.dictid);
                        btnEdit.setText(data.dictid);
                    }
                }else if (action == "clear") { //清空选择的内容
                	btnEdit.setValue("");
                	btnEdit.setText("");
            	}
        	}
        });            
	}
	
	function selectIndustrial(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/dict/code_tree.jsp?dicttypeid=XD_KHCD0092",
            title: "选择字典项",
            width: 800,
            height: 450,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.currentNode;
                    data = nui.clone(data);
                    if (data) {
                        btnEdit.setValue(data.dictid);
                    }
                }else if (action == "clear") { //清空选择的内容
                	btnEdit.setValue("");
                	btnEdit.setText("");
            	}
        	}
        });            
	}

	</script>
</body>
</html>
