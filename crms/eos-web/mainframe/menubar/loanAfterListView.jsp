<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): lvjianhao
  - Date: 2014-06-27
  - Description:TB_AFT_AFTER_LOAN_INFO, com.bos.dataset.aft.TbAftAfterLoanInfo
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="grid1" class="nui-datagrid" style="width:100%;height:100%" 
	url="com.bos.pub.loanAfter.getTbAftAfterLoanInfoList.biz.ext"
	dataField="tbAftAfterLoanInfos"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="6" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		
		<div field="infoDt" headerAlign="center" allowSort="true"  dateFormat="yyyy-MM-dd" >提示日期</div>
		<div field="partyId" headerAlign="center" allowSort="true"  dateFormat="yyyy-MM-dd" >参与人ID</div>
		<div field="partyName" headerAlign="center" allowSort="true"  >客户名称</div>
		<div field="contractId" headerAlign="center" allowSort="true"  dateFormat="yyyy-MM-dd" >合同ID</div>
		<div field="infoTypeCd" headerAlign="center" allowSort="true" dictTypeId="XD_DHCD0015">提示类型代码</div>
		<div field="infoComment" headerAlign="center" allowSort="true" >提示信息</div>
		<div field="priorCheckDt" headerAlign="center" allowSort="true"  dateFormat="yyyy-MM-dd" >前次检查日期</div>
		<div field="nextCheckDt" headerAlign="center" allowSort="true"  dateFormat="yyyy-MM-dd" >本次检查截止日期</div>
		<div field="infoStatus" headerAlign="center" allowSort="true" dictTypeId="XD_DHCD0014">提示信息状态</div>
		<!-- 
		<div field="orgnName" headerAlign="center" allowSort="true" dictTypeId="org">经办行</div>
		<div field="userId" headerAlign="center" allowSort="true" dictTypeId="user">客户经理</div>
		 -->
		</div>
	</div>
			
    <script type="text/javascript">
 	nui.parse();
	var grid = nui.get("grid1");
	
    function search() {
        grid.load();
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
                url: "item_edit.jsp?afterLoanInfoId="+row.afterLoanInfoId+"&view="+v,
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
            	var json=nui.encode({"tbAftAfterLoanInfo":{"afterLoanInfoId":
            		row.afterLoanInfoId,version:row.version}});
                $.ajax({
                     url: "com.bos.pub.crud.delTbAftAfterLoanInfo.biz.ext",
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
