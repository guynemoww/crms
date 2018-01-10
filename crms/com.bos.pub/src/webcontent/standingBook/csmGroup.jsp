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
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;height:auto;overflow:auto;">
<div title="集团客户台账" >
<center>
 <form id="form1" action="com.primeton.example.excel.empManager.flow" method="post" enctype="multipart/form-data" >
 	<div class="nui-dynpanel" columns="6" style="width:100%;height:auto">
		<label>集团名称：</label>
		<input name="map/partyName" required="false" class="nui-textbox nui-form-input"  />
		<label>企业集团登记证编号：</label>
		<input name="map/registerNum" required="false" class="nui-textbox nui-form-input" />
		<label>国别代码：</label>
		<input name="map/nationality" required="false" class="nui-dictcombobox nui-form-input" dictTypeId="XD_KHCD0087"/>
		
		<div>
		<label>资产总额：</label>
		</div>
		<div>
		<input name="map/indexValueDataTypeMin" required="false" class="nui-textbox nui-form-input"  style="width: 36%;"/>-
		<input name="map/indexValueDataTypeMax" required="false" class="nui-textbox nui-form-input"  style="width: 38%;"/>
		</div>
		
		<label>报表类型：</label>
		<input name="map/financeType" required="false" class="nui-dictcombobox nui-form-input" dictTypeId="XD_ACCCD0001"/>
		
		<label>所属机构：</label>
		<input name="map/orgNum" required="false" class="nui-buttonEdit nui-form-input"  allowInput="false" onbuttonclick="selectCodeList" vtype="maxLength:200" />
		
	</div>
 </form>
<div class="nui-toolbar" style="padding-right:20px;text-align:right;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
     <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
	 <a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
 	 <%--<a class="nui-button"  onclick="exportEmp" type="submit" />导出信息</a> --%>
</div>				
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.pub.standBook.getCsmGroupInfoList.biz.ext"
	dataField="items"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="partyName" headerAlign="center" allowSort="true"   >集团名称</div>
		<div field="registerNum" headerAlign="center" allowSort="true"  >企业集团登记证编号</div>
		<div field="financeDeadline" headerAlign="center" allowSort="true"  >资产负债表日期</div>
		<div field="indexValueDataType" headerAlign="center" allowSort="true"  >资产总额</div>
		<div field="financeTypeCd" headerAlign="center" allowSort="true" dictTypeId="XD_ACCCD0001">报表类型</div>
		<div field="nationalityCd" headerAlign="center" allowSort="true" dictTypeId="XD_KHCD0087">国别代码</div>
		<div field="streetAddress" headerAlign="center" allowSort="true" >母公司注册地址</div>
		<div field="mareaCode" headerAlign="center" allowSort="true" >母公司注册地行政区划代码</div>
		<div field="updateTime" headerAlign="center" allowSort="true" >母公司更新注册信息日期</div>
		<div field="streetAddress" headerAlign="center" allowSort="true" >国内办公地址</div>
		<div field="areaCode" headerAlign="center" allowSort="true" >国内办公地址行政区划代码</div>
	    <div field="orgname" headerAlign="center" allowSort="true" >所属机构</div>
		<div field="empname" headerAlign="center" allowSort="true" >创建人</div>		
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
        var rows = grid.findRows(function(row){
   	 	if(row.partyName != null) return true;
	});
		if(rows != null && rows.length > 0) {
	     var form = document.getElementById("form1");
		     form.action = "com.primeton.example.excel.empManager.flow?_eosFlowAction=exportFile&importCd=18";
			     form.submit();
	    }
	    else{
	    nui.alert("没有要导出的记录");
	    }
    }    
  	function selectCodeList(e) {
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
	</script>
</body>
</html>
