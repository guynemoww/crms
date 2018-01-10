<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2014-12-30 10:07:49
  - Description:
-->
<head>
<title>以物抵债处置备案</title>
</head>
<body>
<center>
	<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
		<div class="nui-dynpanel" columns="4" id="table1">	
			<label id="bondedMaterialNum" class="nui-form-label">抵债物编号</label>	
			<input id="mortgageRecord.bondedMaterialNum" name="mortgageRecord.bondedMaterialNum" class="nui-textbox nui-form-input" />
				
			<label id="debtHolder" class="nui-form-label">抵债人</label>
			<input id="mortgageRecord.debtHolder"  name="mortgageRecord.debtHolder" class="nui-textbox nui-form-input"/>

			<label id="debtAssetName" class="nui-form-label">抵债资产名称</label>
			<input id="mortgageRecord.debtAssetName"  name="mortgageRecord.debtAssetName" class="nui-textbox nui-form-input"/>
			
			<label id="originalDebtAssetHolder" class="nui-form-label">抵债资产原权利人</label>
			<input id="mortgageRecord.originalDebtAssetHolder"  name="mortgageRecord.originalDebtAssetHolder" class="nui-textbox nui-form-input"/>

			<label id="dispostionType" class="nui-form-label">处置方式</label>
			<input id="mortgageRecord.dispostionType"  name="mortgageRecord.dispostionType" class="nui-textbox nui-form-input"/>
			
			<label id="originalBorrower" class="nui-form-label">原借款人</label>
			<input id="mortgageRecord.originalBorrower"  name="mortgageRecord.originalBorrower" class="nui-textbox nui-form-input"/>

			<label id="debtAmount" class="nui-form-label">抵债金额</label>
			<input id="mortgageRecord.debtAmount"  name="mortgageRecord.debtAmount" class="nui-textbox nui-form-input"/>
			
			<label id="entryValue" class="nui-form-label">入账价值</label>
			<input id="mortgageRecord.entryValue"  name="mortgageRecord.entryValue" class="nui-textbox nui-form-input"/>

			<label id="dispostionAmount" class="nui-form-label">处置价格</label>
			<input id="mortgageRecord.dispostionAmount"  name="mortgageRecord.dispostionAmount" class="nui-textbox nui-form-input"/>
			
			<label id="entryDate" class="nui-form-label">入账日期</label>
			<input id="mortgageRecord.entryDate"  name="mortgageRecord.entryDate" class="nui-datepicker nui-form-input" 
						format="yyyy-MM-dd"/>

			<label id="dispostionCost" class="nui-form-label">处置费用</label>
			<input id="mortgageRecord.dispostionCost"  name="mortgageRecord.dispostionCost" class="nui-textbox nui-form-input"/>
			
			<label id="dispostionDate" class="nui-form-label">处置日期</label>
			<input id="mortgageRecord.dispostionDate"  name="mortgageRecord.dispostionDate" class="nui-datepicker nui-form-input" 
						format="yyyy-MM-dd"/>

			<label id="dispostionIncome" class="nui-form-label">处置损益</label>
			<input id="mortgageRecord.dispostionIncome"  name="mortgageRecord.dispostionIncome" class="nui-textbox nui-form-input"/>
			
			<label id="afterDebtAssetHolder" class="nui-form-label">抵债后资产权利人</label>
			<input id="mortgageRecord.afterDebtAssetHolder"  name="mortgageRecord.afterDebtAssetHolder" class="nui-textbox nui-form-input"/>
			
			<label id="contacts" class="nui-form-label">联系人</label>
			<input id="mortgageRecord.contacts"  name="mortgageRecord.contacts" class="nui-textbox nui-form-input"/>

			<label id="contactsPhone" class="nui-form-label">联系电话</label>
			<input id="mortgageRecord.contactsPhone"  name="mortgageRecord.contactsPhone" class="nui-textbox nui-form-input"/>
			
			<label id="dispostionExecutiveCondition" class="nui-form-label" >处置方案执行情况</label>
			<input id="mortgageRecord.dispostionExecutiveCondition" name="mortgageRecord.dispostionExecutiveCondition"  vtype="maxLength:1500" class="nui-textarea nui-form-input" required="false" />
			
			<label id="" class="nui-hidden" ></label>													
			<input id="" name=""   class="nui-hidden" required="false" />
			
			<label id="dispostionDisposeCondition" class="nui-form-label" >处置帐务处理情况</label>
			<input id="mortgageRecord.dispostionDisposeCondition" name="mortgageRecord.dispostionDisposeCondition"  vtype="maxLength:1500" class="nui-textarea nui-form-input" required="false" />
			
			<label id="" class="nui-hidden" ></label>													
			<input id="mortgageRecord.remark" name="mortgageRecord.remark"   class="nui-hidden" required="false" />
			
			<label id="remark" class="nui-form-label">备注</label>
			<input id="mortgageRecord.remark" name="mortgageRecord.remark"  vtype="maxLength:1500" class="nui-textarea nui-form-input" required="false" />		
		</div>
		
		<div class="nui-toolbar" style="text-align:right;padding-right:70px;margin-top:3px;border:0">
				<a class="nui-button" iconCls="icon-save" onclick="save">保存</a>
				<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
		</div>
	</div>
</center>
</body>
<script type="text/javascript">
		nui.parse();
		 var form = new nui.Form("#form");
		
		function save() {
		form.validate();
		if (form.isValid() == false) {
			nui.alert("请将信息填写完整");
			return;
		}
		git.mask("form");
		var o=form.getData();
		var json=nui.encode(o);
		// nui.alert(json);
		// return;
		$.ajax({
	            url: "com.bos.npl.book.AssetsBook.insertMortgageRecordBook.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            git.unmask("form");
	            	if(text.msg){
	            		nui.alert(text.msg);
	            	} else {
	            		alert("保存成功!");
	            	}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                git.unmask("form");
	                nui.alert(jqXHR.responseText);
	            }
		});
		}
		</script>
</html>