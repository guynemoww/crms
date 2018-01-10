<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-05-28
  - Description:TB_CON_LOAN_SUMMARY, com.bos.dataset.pay.TbConLoanSummary
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;height:auto;overflow:auto;">
<div title="责任人管理信息查询" >
<center>
 <form id="form1" action="" class="nui-form"method="post" style="width:99.5%;height:auto;overflow:hidden;margin-bottom:5px;"enctype="multipart/form-data" >
<div  style="width:100%;height:auto;overflow:hidden;">

	<div class="nui-dynpanel" columns="6">

		<label>客户名称：</label>
		<input name="map/partyName" required="false" class="nui-textbox nui-form-input" vtype="maxLength:20" />


		<label>授信金额：</label>
		<input name="map/amt" required="false" class="nui-textbox nui-form-input" vtype="float" />

		<label>经办行：</label>
		<input name="map/orgNum" required="false" class="nui-buttonEdit" onbuttonclick="selectEmpOrg" />


		<label>用户编号：</label>
		<input name="map/userNum" required="false" class="nui-textbox nui-form-input"   />

		<label>合同编号：</label>
		<input name="map/contractNum" required="false" class="nui-textbox nui-form-input"   />

	</div>
</div>
</form>			
<div class="nui-toolbar" style="padding-right:20px;text-align:right;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
	<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
  <%--  <a class="nui-button"  onclick="exportEmp" type="submit" />导出信息</a>--%>
</div>

	    
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.pub.responsibilityAccounting.getResponAccount.biz.ext"
	dataField="pesAcc"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="PARTY_NAME" headerAlign="center" allowSort="true" >客户名称</div>
		<div field="amt" headerAlign="center" allowSort="true" >授信金额</div>
		<div field="CONTRACT_NUM" headerAlign="center" allowSort="true" >合同编号</div>
		<div field="USER_NUM" headerAlign="center" allowSort="true" dictTypeId="user">经办用户</div>
		<div field="ORG_NUM" headerAlign="center" allowSort="true" dictTypeId="org">经办行</div>
		<div field="USER_NAME1" headerAlign="center" allowSort="true"   >经营主责任人</div>
		<div field="USER_NAME2" headerAlign="center" allowSort="true" >审批主责任人</div>
		<div field="USER_NAME3" headerAlign="center" allowSort="true" >再管理主责任人（审核）</div>
		<div field="USER_NAME4" headerAlign="center" allowSort="true" >受理调查</div>
		<div field="USER_NAME5" headerAlign="center" allowSort="true" >评审审批</div>
		<div field="USER_NAME6" headerAlign="center" allowSort="true" >授信审核</div>
		<div field="USER_NAME7" headerAlign="center" allowSort="true" >合同订立、发放、支付</div>
		<div field="USER_NAME8" headerAlign="center" allowSort="true" >贷后管理</div>
		<div field="USER_NAME9" headerAlign="center" allowSort="true" >分类</div>
		<div field="USER_NAME10" headerAlign="center" allowSort="true" >资产保全</div>
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
    	git.mask("tabs1");
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data,function(){
        git.unmask("tabs1");
        });
    }
    //search();
    
    function reset(){
		form.reset();
	}
	
    
    function selectEmpOrg(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/sys/select_org_tree.jsp",
            showMaxButton: true,
            title: "选择机构",
            width: 350,
            height: 450,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.GetData();
                    data = nui.clone(data);
                    if (data) {
                        btnEdit.setValue(data.orgid);
                        btnEdit.setText(data.orgname);
                    }
                }
            }
        });            
    }

  
//导出
    function exportEmp()
    {
        var rows = grid.findRows(function(row){
   	 	if(row.PARTY_NAME != null) return true;
	});
		if(rows != null && rows.length > 0) {
	     var form = document.getElementById("form1");
			     form.action = "com.bos.pub.standingbook.accountManager.flow?_eosFlowAction=exportFile&importCd=230";
			     form.submit();
	    }
	    else{
	    nui.alert("没有要导出的记录");
	    }
    }
	</script>
</body>
</html>
