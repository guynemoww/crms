<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>现金等价物金额修改复核</title>
<%@include file="/common/nui/common.jsp" %>
</head> 
<body> 
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;height:auto;">
<div title="现金等价物金额修改复核" >
<center>
<div id="form1" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
	<div class="nui-dynpanel" columns="6">
		<label>借据号：</label>
		<input name="cashDealAcc.loanNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />
		
		<label>账号：</label>
		<input name="cashDealAcc.ratioNo" required="false" class="nui-textbox nui-form-input" vtype="maxLength:18" />
		
		<label>客户编号：</label>
		<input name="cashDealAcc.partyNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />
		
		<label>客户名称：</label>
		<input name="cashDealAcc.partyName" required="false" class="nui-textbox nui-form-input" vtype="maxLength:5" />
		
		<label>发放日期：</label>
		<input name="cashDealAcc.loanActualPaymentDate" required="false" id="cardRegDate" class="nui-datepicker nui-form-input" vtype="maxLength:10" ondrawdate="oncardRegDate" onvaluechanged="oncardRegDate"/>
		
		<label>到期日期：</label>
		<input name="cashDealAcc.loanActualMaturity" required="false" id="regDueDate" class="nui-datepicker nui-form-input" vtype="maxLength:10" ondrawdate="onregDueDate" onvaluechanged="onregDueDate"/>
		
		<label>经办人：</label>
		<input name="cashDealAcc.userNum" allowInput="false" class="nui-buttonEdit" onbuttonclick="selectEmp"/>

		<label>经办机构：</label>
		<input name="cashDealAcc.orgNum" required="false" class="nui-buttonEdit nui-form-input"  allowInput="false" onbuttonclick="selectCodeList" vtype="maxLength:200" />
	</div>
	<div class="nui-toolbar" style="text-align:right;padding-top:5px;padding-bottom:5px;" 
	    borderStyle="border:0;">
	    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
		<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
		<a class="nui-button"  iconCls="icon-edit" onclick="edit()" >复核</a>
		<a class="nui-button"  iconCls="icon-reset" onclick="detail()">额度详情</a>
	</div>
</div>
				
<div id="grid1" class="nui-datagrid" style="width:99.6%;height:auto" 
	url="com.bos.pub.standingbook.guarantyaccout.GetCheckCashGrtList.biz.ext"
	dataField="cashDealAccs"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="contractNum" headerAlign="center" allowSort="true" >合同编号</div>
		<div field="loanNum" headerAlign="center" allowSort="true" >借据号</div>
		<div field="currencyCd" headerAlign="center" allowSort="true" dictTypeId="CD000001">借据币种</div>
		<div field="loanAmt" headerAlign="center" allowSort="true" dataType="currency">借据金额</div>
		<div field="loanBalance" headerAlign="center" allowSort="true" dataType="currency">借据余额</div>
		<div field="partyName" headerAlign="center" allowSort="true" >客户名称</div>
		<div field="partyNum" headerAlign="center" allowSort="true" >客户编号</div>
		<div field="productCd" headerAlign="center" allowSort="true" dictTypeId="product">授信品种</div>
		<div field="guarantyTypeCd" headerAlign="center" allowSort="true" dictTypeId="XD_DBCD4002">现金等价物类型</div>
		<div field="ratioNo" headerAlign="center" allowSort="true" >账号</div>
		<div field="currency" headerAlign="center" allowSort="true" dictTypeId="CD000001">现金等价物币种</div>
		<div field="guarantyLimitMoney" headerAlign="center" allowSort="true" dataType="currency">现金等价物金额</div>
		<div field="guarantyOccupyMoney" headerAlign="center" allowSort="true" dataType="currency">现金等价物占用金额</div>
		<div field="loanActualPaymentDate" headerAlign="center" allowSort="true" >发放日期</div>
		<div field="loanActualMaturity" headerAlign="center" allowSort="true" >到期日期</div>
		 <div field="orgNum" headerAlign="center" allowSort="true" dictTypeId="org">经办机构</div>
		<div field="userNum" headerAlign="center" allowSort="true" dictTypeId="user">经办人</div>
		<div field="loanStatus" headerAlign="center" allowSort="true" dictTypeId="XD_FHCD1008">借据状态</div>
		<!-- -->
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

    function edit(v) {
        var row = grid.getSelected();
        var applyUserNum = row.applyUserNum;
        var curUserNum = "<%=userObject.getUserId() %>";
		if(curUserNum==applyUserNum){
			nui.alert("您不能对自己录入的调整信息进行复核");
			return;
		}
        var cType=row.cType;
        var toUrl = nui.context + "/pub/standingBook/cashDealAccount_check.jsp?relationId="+row.relationId+"&guarantyLimitMoney="+row.guarantyLimitMoney+"&ratioNo="+row.ratioNo+"&guarantyTypeCd="+row.guarantyTypeCd+"&loanAmt="+row.loanAmt+"&natureCd="+row.contractNatureCd+"&changeAmt="+row.changeAmt;
        if("add"==cType){
        	toUrl=nui.context + "/pub/standingBook/cashDealAccount_addCheck.jsp?loanDetailId="+row.loanDetailId+"&contractNum="+row.contractNum;
        }
        if (row) {
            nui.open({
                url: toUrl,
                title: "现金等价物金额修改复核", 
                width: 850,
        		height: 450,
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

// 日期判断
	function oncardRegDate(){
		var cardRegDate = nui.get("cardRegDate").getValue();//发放日期
		var regDueDate = nui.get("regDueDate").getValue();//到期日
		if(cardRegDate!=""&&regDueDate!=""){
			if(nui.parseDate(cardRegDate)-nui.parseDate(regDueDate)>0){
				//到期日小于发放日期
				nui.alert("到期日应该大于发放日期");
				nui.get("cardRegDate").setValue("");
				return false;
			}
		}else{
			return true;
		}	
	}
	
	// 日期判断
	function onregDueDate(){
		var cardRegDate = nui.get("cardRegDate").getValue();//发放日期
		var regDueDate = nui.get("regDueDate").getValue();//到期日
		if(cardRegDate!=""&&regDueDate!=""){
			if(nui.parseDate(cardRegDate)-nui.parseDate(regDueDate)>0){
				//到期日小于发放日期
				nui.alert("发放日期应该大于到期日");
				nui.get("regDueDate").setValue("");
				return false;
			}
		}else{
			return true;
		}
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
	</script>
</body>
</html>
