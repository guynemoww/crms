<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): lujinbin
  - Date: 2013-10-31

  - Description:TB_SYS_TECH_PRODUCT, com.bos.pub.product.TbSysTechProduct-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input type="hidden" name="item" class="nui-hidden" />
	<input type="hidden" name="item._entity" value="com.bos.pub.product.TbSysTechProduct" class="nui-hidden" />
	<input type="hidden" name="control" class="nui-hidden" />
	<input type="hidden" name="rate" class="nui-hidden" />
	<input type="hidden" name="contract" class="nui-hidden" />
	<input name="contract.rateParamId" required="false" class="nui-hidden" vtype="maxLength:32" 
			enabled="false"/>
	<div class="nui-dynpanel" columns="4">
		<label>授信产品控制规则代码：</label>
		<input name="item.productRuleCd" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" enabled="false"/>

		<label>授信产品控制规则名称：</label>
		<input name="item.productRuleName" required="true" class="nui-buttonEdit nui-form-input" vtype="maxLength:100" enabled="false" dictTypeId="product"/>


	</div>
	
	<br/>
	<div class="nui-dynpanel" columns="4">
		<label>是否允许展期：</label>
		<input id="postponementInd" name="contract.postponementInd" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:1" dictTypeId="XD_0002" emptyText="请选择"/>
		<label>展期期限单位：</label>
		<input id="postponementTermUnitCd" name="contract.postponementTermUnitCd" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:1" dictTypeId="BIZ_TERMUNITCDAPPR" emptyText="请选择"/>
		<label>展期次数：</label>
		<input id="postponementMaxTime" name="contract.postponementMaxTime" required="false" class="nui-textbox nui-form-input" vtype="maxLength:10;int" />

		<label>累计展期最长期限：</label>
		<input id="postponementMaxTerm" name="contract.postponementMaxTerm" required="false" class="nui-textbox nui-form-input" vtype="maxLength:10;int" />

		
		<label>是否允许缩期：</label>
		<input id="isSystolicPeriod" name="contract.isSystolicPeriod" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:1" dictTypeId="XD_0002" emptyText="请选择"/>

		<label>缩期次数：</label>
		<input id="systolicPeriodNum" name="contract.systolicPeriodNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:10;int" />

		<label>累积缩期最长数：</label>
		<input id="cumulativeSystolicPeriod" name="contract.cumulativeSystolicPeriod" required="false" class="nui-textbox nui-form-input" vtype="maxLength:10;int" />

		<label>缩期期限单位：</label>
		<input  id="systolicPeriodUnit" name="contract.systolicPeriodUnit" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:1"  dictTypeId="BIZ_TERMUNITCDAPPR" emptyText="请选择"/>

		<label>是否允许提前还本：</label>
		<input id="isReturn" name="contract.isReturn" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:1" dictTypeId="XD_0002" emptyText="请选择"/>

		<label>最低提前还本金额：</label>
		<input id="minReturnAdvanceAmount" name="contract.minReturnAdvanceAmount" required="false" class="nui-textbox nui-form-input" vtype="float" />

		<label>最短提前还本期限：</label>
		<input id="minReturnAdvancePeriod" name="contract.minReturnAdvancePeriod" required="false" class="nui-textbox nui-form-input" vtype="maxLength:10;int" />

		<label>提前还本期限单位：</label>
		<input id="advanceReturnUnit" name="contract.advanceReturnUnit" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:1" dictTypeId="BIZ_TERMUNITCDAPPR" emptyText="请选择"/>

		<label>是否收取提前还本违约金：</label>
		<input id="isPenaltyCharge" name="contract.isPenaltyCharge" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:1" dictTypeId="XD_0002" emptyText="请选择" />
		
		<label>违约金收取方式：</label>
		<input id="penaltyChargeMode" name="contract.penaltyChargeMode" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:1" dictTypeId="" emptyText="请选择"/>
		
		<label>违约金收取比例（%）：</label>
		<input id="penaltyChargeProportion" name="contract.penaltyChargeProportion" required="false" class="nui-textbox nui-form-input" vtype="float" />
		
		<label>单笔最低违约金金额：</label>
		<input id="minPenalty" name="contract.minPenalty" required="false" class="nui-textbox nui-form-input" vtype="float" />
		<label>合同变更状态：</label>
		<input name="contract.statusCd" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_GGCD2012" emptyText="请选择"/>
		
		
		
		<label>生效日期：</label>
		<input id="okTime" name="contract.validDate" required="true" class="nui-datepicker nui-form-input" vtype="maxLength:11" />
		<label>经办日期：</label>
		<input name="contract.handlingDate" required="true" class="nui-datepicker nui-form-input" enabled="false"/>

		<label>经办机构：</label>
		<input name="contract.orgNum" required="false" class="nui-buttonEdit"  vtype="maxLength:9" enabled="false" dictTypeId="org" />

		<label>经办人员：</label>
		<input name="contract.userNum" required="false" class="nui-buttonEdit"  vtype="maxLength:10" enabled="false" dictTypeId="user" />	
	
		

	</div>
</div>
				
<div class="nui-toolbar" style="border-bottom:0;padding-right:20px;text-align:right;">
	<a class="nui-button" iconCls="icon-save" onclick="save()" id="btnSave">保存</a>
</div>
	    
			
    <script type="text/javascript">
	 	nui.parse();
	    var form = new nui.Form("#form1");
		if ("<%=request.getParameter("view") %>"=="1") {
			form.setEnabled(false);
			nui.get("btnSave").hide();
		}
	 //比较时间大小
    function toDate(str){
     var sd=str.split("-");
    return new Date(sd[0],sd[1],sd[2]);
      }		
	function initForm() {
		var json=nui.encode({"item":{"productRuleCd":"<%=request.getParameter("itemId") %>",
			"_entity":"com.bos.pub.product.TbSysTechProduct"}});
		$.ajax({
	            url: "com.bos.pub.product.getRule.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            	if(text.msg){
	            		alert(text.msg);
	            	} else {
	            		form.setData(text);
	            	}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                nui.alert(jqXHR.responseText);
	            }
		});

	}
	initForm();
	var postponementInd, postponementTermUnitCd,//是否允许展期,展期期限单位
	postponementMaxTime ,postponementMaxTerm,//展期次数,累计展期最长期限
	isSystolicPeriod,systolicPeriodNum,//是否允许缩期,缩期次数
	cumulativeSystolicPeriod,systolicPeriodUnit,//累积缩期最长数,缩期期限单位
	isReturn,minReturnAdvanceAmount,//是否允许提前还本,最低提前还本金额
	minReturnAdvancePeriod,advanceReturnUnit,//最短提前还本期限,提前还本期限单位
	isPenaltyCharge,penaltyChargeMode,//是否收取提前还本违约金,违约金收取方式
	penaltyChargeProportion,minPenalty;//违约金收取方式,违约金收取比例（%）
	
	function save() {
	postponementInd=nui.get("postponementInd").getValue();//是否允许展期
	isSystolicPeriod=nui.get("isSystolicPeriod").getValue();//是否允许缩期
	isReturn=nui.get("isReturn").getValue();//是否允许提前还本
	isPenaltyCharge=nui.get("isPenaltyCharge").getValue();//是否收取提前还本违约金
	
		if(postponementInd=="1"){
			nui.get("postponementTermUnitCd").setRequired(true);
			nui.get("postponementMaxTime").setRequired(true);
			nui.get("postponementMaxTerm").setRequired(true);
		}else{
			nui.get("postponementTermUnitCd").setRequired(false);
			nui.get("postponementMaxTime").setRequired(false);
			nui.get("postponementMaxTerm").setRequired(false);
		}
		if(isSystolicPeriod=="1"){
			nui.get("systolicPeriodNum").setRequired(true);
			nui.get("cumulativeSystolicPeriod").setRequired(true);
			nui.get("systolicPeriodUnit").setRequired(true);
		}else{
			nui.get("systolicPeriodNum").setRequired(false);
			nui.get("cumulativeSystolicPeriod").setRequired(false);
			nui.get("systolicPeriodUnit").setRequired(false);
		}
		if(isReturn=="1"){
			nui.get("minReturnAdvanceAmount").setRequired(true);
			nui.get("minReturnAdvancePeriod").setRequired(true);
			nui.get("advanceReturnUnit").setRequired(true);
		} else{
			nui.get("minReturnAdvanceAmount").setRequired(false);
			nui.get("minReturnAdvancePeriod").setRequired(false);
			nui.get("advanceReturnUnit").setRequired(false);
		}
		if(isPenaltyCharge=="1"){
			nui.get("penaltyChargeMode").setRequired(true);
			nui.get("penaltyChargeProportion").setRequired(true);
			nui.get("minPenalty").setRequired(true);
		}else{
			nui.get("penaltyChargeMode").setRequired(false);
			nui.get("penaltyChargeProportion").setRequired(false);
			nui.get("minPenalty").setRequired(false);	
		}
		form.validate();
		if (form.isValid() == false) {
			nui.alert("请将信息填写完整");
			return;
		}
		 	var time;
       		var a=new Date();
	        //当前时间
	        var time1=a.getFullYear() + "-" + (a.getMonth() + 1) + "-" + a.getDate();
	        //生效时间
	        time=nui.get("okTime").getValue();
	        var time2=time.split(" ");
	        var d1=toDate(time2[0]);
			var d2=toDate(time1);
		if(d1>d2){
              var o=form.getData();
			var jsons=nui.encode(o);
			//nui.alert(json);return;
			$.ajax({
		            url: "com.bos.pub.product.saveRule.biz.ext",
		            type: 'POST',
		            data: jsons,
		            cache: false,
		            contentType:'text/json',
		            success: function (text) {	
		            	if(text.msg){
		            	    alert(text.msg);
		            	} else {
		            		initForm();
		            		alert("保存成功");
		            	}
		            },
		            error: function (jqXHR, textStatus, errorThrown) {
		                nui.alert(jqXHR.responseText);
		            }
			});
		}else{
		   alert("生效时间应该晚于当前时间");
		   return;
		}


		
		
	}
	</script>
</body>
</html>
