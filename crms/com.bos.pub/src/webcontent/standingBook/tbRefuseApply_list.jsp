<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>审批退回业务查询</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;height:auto;">
<div title="审批退回业务查询" >
<center>
<form id="form1" action="" method="post" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;margin-bottom:5px">
	<div class="nui-dynpanel" columns="6">
			<label>客户名称：</label>
			<input name="tbRefuseApply.partyName" required="false" class="nui-textbox nui-form-input"  vtype="maxLength:32" />
			<label>客户编号：</label>
			<input name="tbRefuseApply.partyNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />
			<label>客户经理：</label>
			<input name="tbRefuseApply.userNum" allowInput="false" class="nui-buttonEdit" onbuttonclick="selectEmp"/>
			<label>经办机构：</label>
			<input name="tbRefuseApply.orgNum" required="false" class="nui-buttonEdit nui-form-input" allowInput="false" onbuttonclick="selectCodeList" vtype="maxLength:32" />
			<label>退回人：</label>
			<input name="tbRefuseApply.buserNum" allowInput="false" class="nui-buttonEdit nui-form-input" onbuttonclick="selectEmp"/>
			<label>退回人机构：</label>
			<input name="tbRefuseApply.borgNum" required="false" class="nui-buttonEdit nui-form-input" allowInput="false" onbuttonclick="selectCodeList" vtype="maxLength:32" />
			<label>退回接收人：</label>
			<input name="tbRefuseApply.nextUsersNum" allowInput="false" class="nui-buttonEdit nui-form-input" onbuttonclick="selectEmp"/>
			<label>退回接收人机构：</label>
			<input name="tbRefuseApply.nextOrgNum" required="false" class="nui-buttonEdit nui-form-input" allowInput="false" onbuttonclick="selectCodeList" vtype="maxLength:32" />
			<label></label>
			<input name="" required="false" class="nui-hidden" vtype="maxLength:32" />
			<label>退回开始时间：</label>
			<input name="tbRefuseApply.backTimeStart" required="false" id="laidstart" class="nui-datepicker nui-form-input" vtype="maxLength:32" onvaluechanged="onregDueDate"/>~
			<label>退回截止日期：</label>
			<input name="tbRefuseApply.backTimeEnd" required="false" id="laidend" class="nui-datepicker nui-form-input" vtype="maxLength:32" onvaluechanged="onDueDate"/>
			
	</div>
	<div class="nui-toolbar" style="text-align:right;padding-top:5px;padding-bottom:5px;padding-right:49px" borderStyle="border:0;">
	    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
		<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
		<%--<a class="nui-button"  onclick="exportEmp" type="submit" />导出信息</a>--%>
	</div>
</form>

<div id="grid1" class="nui-datagrid" style="width:99.5%;height:auto" 
	url="com.bos.pub.standingbook.guarantyaccout.TbRefuseApplyList.biz.ext"
	dataField="items"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns" >
		<div type="checkcolumn" >选择</div>
		<div field="partyNum" headerAlign="center" allowSort="true" >客户编号</div>
		<div field="partyTypeCd" headerAlign="center" allowSort="true" dictTypeId="XD_KHCD0219">客户类型</div>		
		<div field="partyName" headerAlign="center" allowSort="true" >客户名称</div>
		<div field="userNum" headerAlign="center" allowSort="true" dictTypeId="user">客户经理</div>		
		<div field="orgNum" headerAlign="center" allowSort="true" dictTypeId="org">机构名称</div>	
		<div field="applyDate" headerAlign="center" allowSort="true"  dateFormat="yyyy-MM-dd">申请日期</div><!-- 申请日期/预受理日期 -->	
		<div field="bizType" headerAlign="center" allowSort="true" dictTypeId="XD_SXCD1038">业务性质</div>					
		<div field="bizHappenType" headerAlign="center" allowSort="true" dictTypeId="XD_SXCD1039">业务发生性质</div>			
		<div field="totalAmount" headerAlign="center" allowSort="true" dataType="currency">申请总金额</div>			
		<div field="creditTotalExposure" headerAlign="center" allowSort="true" dataType="currency">申请敞口金额</div>		
		<div field="currencyCd" headerAlign="center" allowSort="true" dictTypeId="CD000001">币种</div>		
		<div field="buserNum" headerAlign="center" allowSort="true" dictTypeId="user">退回人</div>	
		<div field="borgNum" headerAlign="center" allowSort="true" dictTypeId="org">退回人机构</div>	
		<div field="performtime" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">退回时间</div>							
		<div field="nextUsersNum" headerAlign="center" allowSort="true" dictTypeId="user">退回接收人</div>	
		<div field="nextOrgNum" headerAlign="center" allowSort="true" dictTypeId="org">退回接收人机构</div>	
		<div field="opinion" headerAlign="center" allowSort="true" >退回原因</div>	
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
    	
	// 日期判断
	function onregDueDate(){
		var laidstart = nui.get("laidstart").getValue();//起始日期
		var laidend = nui.get("laidend").getValue();//截止日期
			if(laidend==""){//截止日期为空
			nui.get("laidend").setValue(laidstart);
			}
		if(laidstart!=""&&laidend!=""){
			if(nui.parseDate(laidstart)-nui.parseDate(laidend)>0){
				nui.alert("起始日期应小于截止日期");
				nui.get("laidstart").setValue("");
				return false;
			}
		}else{
	
			return true;
		}
	}
	
	function onDueDate(){
		var laidstart = nui.get("laidstart").getValue();//起始日期
		var laidend = nui.get("laidend").getValue();//截止日期
	
		if(laidstart!=""&&laidend!=""){
			if(nui.parseDate(laidstart)-nui.parseDate(laidend)>0){
				nui.alert("起始日期应小于截止日期");
				nui.get("laidend").setValue(laidstart);
				return false;
			}
		}else{
	
			return true;
		}
	}
////////////////////日期判断结束
		//导出
    function exportEmp()
    {
        var rows = grid.findRows(function(row){
   	 	if(row.partyNum != null) return true;
	});
		if(rows != null && rows.length > 0) {
	     var form = document.getElementById("form1");
			     form.action = "com.bos.pub.standingbook.accountManager.flow?_eosFlowAction=exportFile&importCd=229";
			     form.submit();
	    }
	    else{
	    nui.alert("没有要导出的记录");
	    }
    }
	</script>
</body>
</html>
