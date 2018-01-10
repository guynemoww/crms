<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>当日委托贷款到期借据清单台账查询</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;height:auto;overflow:auto;">
<div title="当日委托贷款到期借据清单台账查询" >
<center>
<form id="form1" action="" method="post" enctype="multipart/form-data" class="nui-form" style="height:auto;overflow:hidden;margin-bottom:5px;width:99.3%;">
	<div class="nui-dynpanel" columns="6">
			<label>借据编号：</label>
			<input name="map/loanNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />
			<label>借款人：</label>
			<input name="map/partyName" required="false" id="laidstart" class="nui-textbox nui-form-input" vtype="maxLength:32"/>
			<label>委托人：</label>
			<input name="map/wpartyName" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32"/>
			<label>放款核准单号：</label>
			<input name="map/loanApplyNo" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32"/>
			<!-- 
			<label>日期：</label>
			<input name="map/endDate"  required="false" class="nui-datepicker nui-form-input" dateFormat="yyyy-MM-dd"/>
			 -->
	</div>
	<div class="nui-toolbar" style="padding-right:15px;text-align:right;height:auto;padding-right:85px;" borderStyle="border:0;">
	    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
		<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
		<%--<a class="nui-button"  iconCls="icon-download" onclick="downloadExcel()" type="submit">导出</a>--%>
	</div>
</form>

<div id="grid1" class="nui-datagrid" style="width:99.3%;height:auto" 
	url="com.bos.pub.standingbook.entrustedloan.GetEntrustedloanObjectList.biz.ext"
	dataField="items"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	
	<div property="columns" >
		<!-- <div type="checkcolumn" >选择</div> -->
		<div field="loanNum" headerAlign="center" allowSort="true" >借据编号</div>
		<div field="loanApplyNo" headerAlign="center" allowSort="true">放款核准单号</div>	
		<div field="partyName" headerAlign="center" allowSort="true">借款人</div>
		<div field="wpartyName" headerAlign="center" allowSort="true">委托人</div>	
		<div field="currencyCd" headerAlign="center" allowSort="true" dictTypeId="CD000001">币种</div>	
		<div field="loanBalance" headerAlign="center" allowSort="true" dataType="currency">应还款金额</div>	
		<div field="openingBankName" headerAlign="center" allowSort="true" >出账机构</div>
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
     
    
	//选择经办机构
	function selectCodeList(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/sys/select_org_tree.jsp",
            showMaxButton: true,
            title: "选择经办机构",
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
    
        
//选择人员（无权限）
function selectEmp(e) {
    var btnEdit = this;
    nui.open({
        url: nui.context + "/pub/standingBook/select_employee.jsp",
        showMaxButton: true,
        title: "选择经办人",
        width: 850,
        height: 450,
        ondestroy: function (action) {            
            if (action == "ok") {
                var iframe = this.getIFrameEl();
                var data = iframe.contentWindow.GetData();
                data = nui.clone(data);
                if (data) {
                    btnEdit.setValue(data.empcode);
                    btnEdit.setText(data.empname);
                }
            }
        }
    });            
}
		//导出
	    function downloadExcel() {
	    	var rows = grid.findRows(function(row){
	   	 		if(row.loanNum != null) return true;
			});
			
			if(rows != null && rows.length > 0) {//有要导出的记录
				var forms = document.getElementById("form1");
				forms.action = "com.bos.pub.standingbook.accountManager.flow?_eosFlowAction=exportFile&importCd=210";
				forms.submit();
			} else {
				alert('没有要导出的记录');
			}
	    }
	
	</script>
</body>
</html>
