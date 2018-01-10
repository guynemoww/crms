<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn 请在此写上作者
  - Date: 2014-09-18
  - Description:TB_BATCH_BUSINESS_DUEBILL, com.bos.dataset.batch.TbBatchBusinessDuebill
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;">
<div title="数仓异常额度调整" >
<center>
<div id="form1" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
	
	<div class="nui-dynpanel" columns="6">
		<label>借据号：</label>
		<input name="tbBatchBusinessDuebill.serialno" required="false" class="nui-textbox nui-form-input" vtype="maxLength:40" />
		<label>入账机构号：</label>
		<input name="tbBatchBusinessDuebill.orgNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:9" />

	</div>
	<div class="nui-toolbar" style="text-align:right;border:none">
	    <a class="nui-button" style="margin-right:5px;height:21px;" iconCls="icon-search" onclick="search()">查询</a>
		<a class="nui-button" style="margin-right:23px;height:21px" iconCls="icon-reset" onclick="reset()">重置</a>
	</div>
</div>
<div style="width:99.5%">		
	<div class="nui-toolbar" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px">
		<a class="nui-button" iconCls="icon-edit" onclick="edit(0)">编辑</a>
		<!-- 
	<a class="nui-button" iconCls="icon-add" onclick="add()">增加</a>
	<a class="nui-button" iconCls="icon-edit" onclick="edit(1)">查看</a>
	<a class="nui-button" iconCls="icon-remove" onclick="remove()">删除</a>
	 -->
	</div>
</div>
	    
<div id="grid1" class="nui-datagrid" style="width:99.5%;height:auto;"
	url="com.bos.batch.updateTbBatchBusinessDuebill.getTbBatchBusinessDuebillList.biz.ext"
	dataField="tbBatchBusinessDuebills" allowAlternating="true"
	allowResize="true" showReloadButton="false"
	sizeList="[10,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="serialno" headerAlign="center" allowSort="true" >借据号</div>
		<div field="orgNum" headerAlign="center" allowSort="true" >入账机构号</div>
		<div field="currency" headerAlign="center" allowSort="true" dictTypeId="CD000001">币种</div>
		<div field="businesssum" headerAlign="center" allowSort="true" dataType="currency">发放金额</div>
		<div field="balance" headerAlign="center" allowSort="true" dataType="currency">本金总余额</div>
		<div field="normalbalance" headerAlign="center" allowSort="true" dataType="currency">正常余额</div>
		<div field="overduebalance1" headerAlign="center" allowSort="true" dataType="currency">三个月以内逾期余额</div>
		<div field="overduebalance2" headerAlign="center" allowSort="true" dataType="currency">三个月以上逾期余额</div>
		<div field="dullbalance" headerAlign="center" allowSort="true" dataType="currency">呆滞余额</div>
		<div field="badbalance" headerAlign="center" allowSort="true" dataType="currency">呆账余额</div>
		<div field="interestbalance1" headerAlign="center" allowSort="true" dataType="currency">表内欠息余额</div>
		<div field="interestbalance2" headerAlign="center" allowSort="true" dataType="currency">表外欠息余额</div>
		</div>
	</div>
	</center>
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
            url: "item_add.jsp",
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
                url: nui.context + "/batch/updateTbBatchBusinessDuebill/tbBatchBusinessDuebillEdit.jsp?uuid="+row.uuid+"&view="+v,
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
                        alert("保存成功");
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
            	var json=nui.encode({"tbBatchBusinessDuebill":{"uuid":
            		row.uuid,version:row.version}});
                $.ajax({
                     url: "com.bos.pub.crud.delTbBatchBusinessDuebill.biz.ext",
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
