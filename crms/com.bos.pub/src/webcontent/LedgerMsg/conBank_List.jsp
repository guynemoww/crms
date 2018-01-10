<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-05-06
  - Description:TB_AFT_AFTER_LOAN_INFO, com.bos.dataset.aft.TbAftAfterLoanInfo
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;height:auto;overflow:auto">
<div title="银团成员客户台账" >
<center>
 <form id="form1" class="nui-form"action="com.primeton.example.excel.empManager.flow" method="post" style="width:99.5%;height:auto;overflow:hidden;margin-bottom:5px;"enctype="multipart/form-data" >
	<div class="nui-dynpanel" columns="6" style="width:100%;height:auto">
		<label>客户名称：</label>
		<input name="map/partyName" required="false" class="nui-textbox nui-form-input"  />
		<label>合同编号：</label>
		<input name="map/contractNum" required="false" class="nui-textbox nui-form-input" />
		<label>集团名称：</label>
		<input name="map/groupCustomer" required="false" class="nui-textbox nui-form-input" />
		<label>机构名称：</label>
		<input name="map/orgNum" required="false"  class="nui-buttonEdit" onbuttonclick="selectEmpOrg" dictTypeId="org" />
		
	</div>
	<div class="nui-toolbar"style="text-align:right;padding-top:5px;padding-bottom:5px;padding-right:35px" borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
	<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
 	 <%--<a class="nui-button"  onclick="exportEmp" type="submit" />导出信息</a> --%>
</div>
 </form>
				

<div id="grid1" class="nui-datagrid" style="width:99.5%;height:auto"  
	url="com.bos.pub.ledgerMsg.getConBank.biz.ext"
	dataField="conBank"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="ecifPartyNum" headerAlign="center" allowSort="true" >T24客户编号1</div>
		<div field="partyNum" headerAlign="center" allowSort="true"  >客户编号</div>
		<div field="contractNum" headerAlign="center" allowSort="true"  >合同编号</div>
		<div field="groupCustomer" headerAlign="center" allowSort="true"  >集团名称</div>
		<div field="partyName" headerAlign="center" allowSort="true" >客户名称</div>
		<div field="syndicatedWayCd" headerAlign="center" allowSort="true" dictTypeId="XD_SXCD1067">组团方式</div>
		<div field="syndicatedObjectCd" headerAlign="center" allowSort="true" dictTypeId="XD_SXCD1066">组团对象</div>
		<div field="syndicatedLoanAmt" headerAlign="center" allowSort="true" >银团贷款总金额</div>
		<div field="productType" headerAlign="center" allowSort="true">业务品种</div>
		<div field="promiseShare" headerAlign="center" allowSort="true">我行承贷份额</div>
		<div field="startDate" headerAlign="center" allowSort="true" > 合同起始日期</div>
		<div field="expirationDate" headerAlign="center" allowSort="true" >合同终止日期</div>
		 <div field="orgNum" headerAlign="center" allowSort="true" dictTypeId="org">经办机构</div>
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
	
  //选择机构
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
     var form = document.getElementById("form1");
		     form.action = "com.primeton.example.excel.empManager.flow?_eosFlowAction=exportFile&importCd=17";
		     form.submit();
    }
    
  
	</script>
</body>
</html>
