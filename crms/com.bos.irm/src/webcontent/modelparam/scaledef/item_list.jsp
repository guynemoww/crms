<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-04-15
  - Description:TB_IRM_SCALE_DEF, com.bos.dataset.irm.TbIrmScaleDef
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	
	<div class="nui-dynpanel" columns="4">
		
		
		<label>是否专业贷款：</label>
		<input name="tbIrmScaleDef.isProfessionalLoan" required="false" class="nui-dictcombobox nui-form-input" dictTypeId="YesOrNo" vtype="maxLength:16" />
		
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
	url="com.bos.irm.param.getTbIrmScaleDefList.biz.ext"
	dataField="tbIrmScaleDefs"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		
		<div field="orderNo" headerAlign="center" allowSort="true" >顺序号</div>
		<div field="creditRatingCd" headerAlign="center" allowSort="true" >信用等级</div>
		<div field="creditRatingDef" headerAlign="center" allowSort="true" >信用等级定义</div>
		<div field="creditRatingDisplay" headerAlign="center" allowSort="true" >等级显示</div>
		<div field="smallLtdDisplay" headerAlign="center" allowSort="true" >小企业等级显示</div>
		<div field="minPd" headerAlign="center" allowSort="true" >最小PD</div>
		<div field="maxPd" headerAlign="center" allowSort="true" >最大PD</div>
		<div field="avagPd" headerAlign="center" allowSort="true" >均值PD</div>
		<div field="isProfessionalLoan" headerAlign="center" allowSort="true" dictTypeId="YesOrNo">是否专业贷款</div>
		</div>
	</div>
			
    <script type="text/javascript">
 	nui.parse();
 	git.mask();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	
    function search() {
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
		git.unmask();
    }
    search();
    
    function reset(){
		form.reset();
	}
	
    function add() {
        nui.open({
            url: "<%=request.getContextPath() %>/irm/modelparam/scaledef/item_add.jsp",
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
                url: "<%=request.getContextPath() %>/irm/modelparam/scaledef/item_edit.jsp?scaleDefId="+row.scaleDefId+"&view="+v,
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
            	git.mask();
            	var json=nui.encode({"tbIrmScaleDef":{"scaleDefId":
            		row.scaleDefId,version:row.version}});
                $.ajax({
                     url: "com.bos.irm.param.delTbIrmScaleDef.biz.ext",
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
                        git.unmask();
                    },
                    error: function () {
                    	nui.alert("操作失败！");
                    	
                    	 git.unmask();
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
