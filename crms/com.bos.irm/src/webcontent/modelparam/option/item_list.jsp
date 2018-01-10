<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-04-14
  - Description:TB_IRM_ADJUST_OPTION, com.bos.dataset.irm.TbIrmAdjustOption
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	
	<div class="nui-dynpanel" columns="4">
		<label>选项类别：</label>
		<input name="tbIrmAdjustOption.adjustTypeCd" required="false" class="nui-dictcombobox nui-form-input" dictTypeId="XD_PJCD0014" vtype="maxLength:6" />		
		

	</div>
</div>
				
<div class="nui-toolbar" style="text-align:right;padding-right:20px;border:0;" 
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
	url="com.bos.irm.param.getTbIrmAdjustOptionList.biz.ext"
	dataField="tbIrmAdjustOptions"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="adjustTypeCd" headerAlign="center" allowSort="true" dictTypeId="XD_PJCD0014" >选项类别</div>
		<div field="adjustOptionDescription" headerAlign="center" allowSort="true" >调整选项描述</div>
		<div field="optionTypeCd" headerAlign="center" allowSort="true" dictTypeId="XD_PJCD0010">调整类型</div>
		<div field="adjustSeries" headerAlign="center" allowSort="true" dictTypeId="XD_ADJUSTSERIES">调整级数</div>
		<div field="minValue" headerAlign="center" allowSort="true" >最小分值</div>
		<div field="maxValue" headerAlign="center" allowSort="true" >最大分值</div>
		<div field="adjustOrder" headerAlign="center" allowSort="true" >调整顺序</div>
		<div field="modelNumber" headerAlign="center" allowSort="true" >适用模型编号</div>
		</div>
	</div>
			
    <script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	var modelId="<%=request.getParameter("modelId") %>";
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
            url: "<%=request.getContextPath() %>/irm/modelparam/option/item_add.jsp",
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
                url: "<%=request.getContextPath() %>/irm/modelparam/option/item_edit.jsp?aoId="+row.aoId+"&view="+v,
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
            	var json=nui.encode({"tbIrmAdjustOption":{"aoId":
            		row.aoId,version:row.version}});
            	
                $.ajax({
                     url: "com.bos.irm.model.delTbIrmAdjustOption.biz.ext",
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
    
     function selectModel(e) {
    	var btnEdit = this;
        nui.open({
            url: nui.context + "/irm/modelparam/option/select_managed_model.jsp",
            showMaxButton: false,
            title: "选择调整选项适用模型",
            width: 400,
            height: 450,
            onload:function(){
                var ids = btnEdit.getValue();
                var texts = btnEdit.getText();
                var data = {
                   parentNode: window['parentNode'],
                   ids:ids,
                   texts:texts
                };
                var iframe = this.getIFrameEl();
                iframe.contentWindow.SetData(data);
            },
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.GetData();
                    data = nui.clone(data);
                    if (data) {
                        btnEdit.setValue(data.id);
                        btnEdit.setText(data.text);
                    }
                }
            }
        });            
    }	
	


	</script>
</body>
</html>
