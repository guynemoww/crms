<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): 卢金彬
  - Date: 2014-02-18
  - Description:TB_SYS_WARN_OVERDUE_RATE, com.bos.dataset.sys.TbSysWarnOverdueRate
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input type="hidden" name="item._entity" value="com.bos.dataset.sys.TbSysWarnOverdueRate" class="nui-hidden" />
	<h3>逾期率预警参数</h3>
	<div class="nui-dynpanel" columns="4">

		<label>逾期率类型：</label>
		<input name="item.overdueRateType" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" />

		<label>生效日期：</label>
		<input name="item.rateEffectDate" required="true" class="nui-datepicker nui-form-input" />


	</div>
</div>
				
<div class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;" 
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
	url="com.bos.pub.TbSysWarnOverdueRate.getItemList.biz.ext" dataField="items"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="businessKind" headerAlign="center" allowSort="true" dictTypeId="product">业务种类</div>
		<div field="orgId" headerAlign="center" allowSort="true" >机构编码</div>
		<div field="orgLevel" headerAlign="center" allowSort="true" dictTypeId="XD_GGCD6004">机构级别</div>
		<div field="overdueRateIndex" headerAlign="center" allowSort="true" >逾期率指标</div>
		<div field="overdueRateType" headerAlign="center" allowSort="true" >逾期率类型</div>
		<div field="rateEffectDate" headerAlign="center" allowSort="true" >生效日期</div>
		<div field="warnControleRule" headerAlign="center" allowSort="true" >逾期率预警控制规则</div>
		<div field="warnIndexDesc" headerAlign="center" allowSort="true" >预警指标描述</div>
		<div field="warnSignalLevel" headerAlign="center" allowSort="true" dictTypeId="WarningSignalLevel">预警信号等级</div>
		<div field="warnSignalReceiver" headerAlign="center" allowSort="true" dictTypeId="user">预警信号接收人</div>
		<div field="handlingDate" headerAlign="center" allowSort="true" >经办日期</div>
		<div field="handlingOrgId" headerAlign="center" allowSort="true" dictTypeId="org" >经办机构</div>
		<div field="handlingUserId" headerAlign="center" allowSort="true" dictTypeId="user">经办人员名称</div>
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
            url: "pub/warnOverdue/item_add.jsp",
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
                url: "pub/warnOverdue/item_edit.jsp?itemId="+row.overdueRateId+"&view="+v,
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
            	var json=nui.encode({"item":{"overdueRateId":
            		row.overdueRateId,
					"_entity":"com.bos.dataset.sys.TbSysWarnOverdueRate"}});
                $.ajax({
                     url: "com.bos.pub.TbSysWarnOverdueRate.delItem.biz.ext",
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
