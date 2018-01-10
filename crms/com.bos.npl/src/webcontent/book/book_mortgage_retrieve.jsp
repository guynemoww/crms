<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2014-12-30 10:07:49
  - Description:
-->
<head>
<title>以物抵债收取情况</title>
</head>
<body>
<center>
	<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
		<div class="nui-dynpanel" columns="4" id="">	
			<label id="bondedMaterialNum" class="nui-form-label">抵债物编号</label>
			<div>	
				<input id="mortgageCollect.bondedMaterialNum" name="mortgageCollect.bondedMaterialNum" class="nui-textbox nui-form-input" 
						required="true" style="width:67%"/>
				<a id="" name="" class="nui-button" >查看</a>
			</div>
			
			<label class="nui-hidden" ></label>													
			<input id="" name=""   class="nui-hidden" required="false" />

			<label id="debtAssetName" class="nui-form-label">抵债资产名称</label>
			<input id="mortgageCollect.debtAssetName"  name="mortgageCollect.debtAssetName" class="nui-textbox nui-form-input"/>
			
			<label id="originalDebtAssetHolder" class="nui-form-label">抵债资产原权利人</label>
			<input id="mortgageCollect.originalDebtAssetHolder"  name="mortgageCollect.originalDebtAssetHolder" class="nui-textbox nui-form-input"/>

			<label id="debtPrincipalAmount" class="nui-form-label">(抵债金额)本金</label>
			<input id="mortgageCollect.debtPrincipalAmount"  name="mortgageCollect.debtPrincipalAmount" class="nui-textbox nui-form-input"/>
			
			<label id="effectiveDate" class="nui-form-label">抵债协议或抵债裁定生效日</label>
			<input id="mortgageCollect.effectiveDate"  name="mortgageCollect.effectiveDate" class="nui-datepicker nui-form-input" 
						format="yyyy-MM-dd" />

			<label id="debtInterestAmount" class="nui-form-label">(抵债金额)表内欠息</label>
			<input id="mortgageCollect.debtInterestAmount"  name="mortgageCollect.debtInterestAmount" class="nui-textbox nui-form-input"/>
			
			<label id="entryValue" class="nui-form-label">抵债资产入账价值</label>
			<input id="mortgageCollect.entryValue"  name="mortgageCollect.entryValue" class="nui-textbox nui-form-input"/>

			<label id="debtOutsideAmount" class="nui-form-label">(抵债金额)表外欠息</label>
			<input id="mortgageCollect.debtOutsideAmount"  name="mortgageCollect.debtOutsideAmount" class="nui-textbox nui-form-input"/>
			
			<label id="entryDate" class="nui-form-label">入账日期</label>
			<input id="mortgageCollect.entryDate"  name="mortgageCollect.entryDate" class="nui-datepicker nui-form-input" format="yyyy-MM-dd" />

			<label id="debtAmount" class="nui-form-label">(抵债金额)费用</label>
			<input id="mortgageCollect.debtAmount"  name="mortgageCollect.debtAmount" class="nui-textbox nui-form-input"/>
			
			<label id="riskClassify" class="nui-form-label">抵债资产五级分类</label>
			<input id="mortgageCollect.riskClassify"  name="mortgageCollect.riskClassify" class="nui-textbox nui-form-input"/>

			<label id="debtTotalAmount" class="nui-form-label">(抵债金额)合计</label>
			<input id="mortgageCollect.debtTotalAmount"  name="mortgageCollect.debtTotalAmount" class="nui-textbox nui-form-input"/>
			
			<label id="afterDebtAssetHolder" class="nui-form-label">抵债后资产权利人</label>
			<input id="mortgageCollect.afterDebtAssetHolder"  name="mortgageCollect.afterDebtAssetHolder" class="nui-textbox nui-form-input"/>
			
			<label id="contacts" class="nui-form-label">联系人</label>
			<input id="mortgageCollect.contacts"  name="mortgageCollect.contacts" class="nui-textbox nui-form-input"/>

			<label id="contactsPhone" class="nui-form-label">联系电话</label>
			<input id="mortgageCollect.contactsPhone"  name="mortgageCollect.contactsPhone" class="nui-textbox nui-form-input"/>
			
			<label id="ownershipChanges" class="nui-form-label" >权属变更情况</label>
			<input id="mortgageCollect.ownershipChanges" name="mortgageCollect.ownershipChanges"  vtype="maxLength:1500" 
						class="nui-textarea nui-form-input" required="false" />
			
			<label id="" class="nui-hidden" ></label>													
			<input id="" name=""   class="nui-hidden" required="false" />
			
			<label id="storageCase" class="nui-form-label" >保管情况</label>
			<input id="mortgageCollect.storageCase" name="mortgageCollect.storageCase"  vtype="maxLength:1500" class="nui-textarea nui-form-input" required="false" />
			
			<label class="nui-hidden" ></label>													
			<input id="" name=""   class="nui-hidden" required="false" />
			
			<label id="remark" class="nui-form-label">备注</label>
			<input id="mortgageCollect.remark" name="mortgageCollect.remark"  vtype="maxLength:1500" class="nui-textarea nui-form-input" required="false" />
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
	            url: "com.bos.npl.book.AssetsBook.insertMortgageCollectBook.biz.ext",
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

