<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-05-28
  - Description:TB_CON_SUBCONTRACT, com.bos.dataset.crt.TbConSubcontract
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input id="contractId" name="tbConSubcontract.contractId" required="false" class="nui-hidden"  />
	
	<!-- 
	<div class="nui-dynpanel" columns="6">

		<label>币种：</label>
		<input name="tbConSubcontract.currencyCd" required="false" class="nui-textbox nui-form-input" vtype="maxLength:3" />
		<label>担保额度模式编号：</label>
		<input name="tbConSubcontract.guaranteeAmountModelNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />

		<label>保证形式：</label>
		<input name="tbConSubcontract.guaranteeKindCd" required="false" class="nui-textbox nui-form-input" vtype="maxLength:6" />

		<label>本次抵押品占用价值合计：</label>
		<input name="tbConSubcontract.guaranteeLimitSumMoney" required="false" class="nui-textbox nui-form-input" vtype="maxLength:26" />

		<label>担保债权金额：</label>
		<input name="tbConSubcontract.guaranteeRightMoney" required="false" class="nui-textbox nui-form-input" vtype="maxLength:26" />

		<label>担保方式：</label>
		<input name="tbConSubcontract.loanType" required="false" class="nui-textbox nui-form-input" vtype="maxLength:6" />


		<label>从合同唯一标识：</label>
		<input name="tbConSubcontract.subcontractId" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />

		<label>纸质担保合同编号：</label>
		<input name="tbConSubcontract.subcontractManualNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:30" />

		<label>从合同唯一标识：</label>
		<input name="tbConSubcontract.subcontractNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:30" />

		<label>从合同状态：</label>
		<input name="tbConSubcontract.subcontractStatusCd" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_SXCD1106"/>

		<label>从合同类型：</label>
		<input name="tbConSubcontract.subcontractTypeCd" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" />

		<label>签约日期：</label>
		<input name="tbConSubcontract.subContractSignDate" required="true" class="nui-datepicker nui-form-input" />

	</div>
	<div class="nui-toolbar" style="padding-right:20px;text-align:right;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
	<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
	</div>
 -->
</div>
				
<!-- 
<div class="nui-toolbar" style="border-bottom:0;">
	<a class="nui-button" iconCls="icon-edit" onclick="edit(1)">查看</a>
</div>
 -->	    
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.pub.ledgerMsg.getTbConSubcontractList.biz.ext"
	dataField="tbConSubcontracts"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="currencyCd" headerAlign="center" allowSort="true" dictTypeId="CD000001">币种</div>
		<div field="expirationDate" headerAlign="center" allowSort="true"  dateFormat="yyyy-MM-dd" >到期日期</div>
		<div field="guaranteeAmountModelNum" headerAlign="center" allowSort="true" >担保额度模式编号</div>
		<div field="guaranteeKindCd" headerAlign="center" allowSort="true" >保证形式</div>
		<div field="guaranteeRightMoney" headerAlign="center" allowSort="true" >担保债权金额</div>
		<div field="guatorTypeCd" headerAlign="center" allowSort="true" >保证方式</div>
		<div field="ifDifferentPlace" headerAlign="center" allowSort="true" dictTypeId="XD_0002">是否异地担保</div>
		<div field="ifOccupyGuaranteeAmount" headerAlign="center" allowSort="true" dictTypeId="XD_0002">是否占用担保额度</div>
		<div field="loanType" headerAlign="center" allowSort="true" dictTypeId="YP_GLCD0152">担保方式</div>
		<div field="orgNum" headerAlign="center" allowSort="true" dictTypeId="org">经办机构</div>
		<div field="startDate" headerAlign="center" allowSort="true"  dateFormat="yyyy-MM-dd" >起始日期</div>
		<div field="subcontractId" headerAlign="center" allowSort="true" >从合同唯一标识</div>
		<div field="subcontractManualNum" headerAlign="center" allowSort="true" >纸质担保合同编号</div>
		<div field="subcontractNum" headerAlign="center" allowSort="true" >从合同唯一标识</div>
		<div field="subcontractStatusCd" headerAlign="center" allowSort="true" dictTypeId="XD_SXCD1106">从合同状态</div>
		<div field="subcontractTypeCd" headerAlign="center" allowSort="true" >从合同类型</div>
		<div field="subContractSignDate" headerAlign="center" allowSort="true"  dateFormat="yyyy-MM-dd" >签约日期</div>
		<div field="subContractSignPlace" headerAlign="center" allowSort="true" >签约地点</div>
		</div>
	</div>
			
    <script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	nui.get("contractId").setValue("<%=request.getParameter("contractId") %>");
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
                url: nui.context+"/pub/LedgerMsg/ledgerSub.jsp?subcontractId="+row.subcontractId+"&view="+v,
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
            	var json=nui.encode({"tbConSubcontract":{"subcontractId":
            		row.subcontractId,version:row.version}});
                $.ajax({
                     url: "com.bos.pub.crud.delTbConSubcontract.biz.ext",
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
