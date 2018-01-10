<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): ZhuYongLun
  - Date: 2014-04-03 09:20:03
  - Description:
-->
<head>
	<%@include file="/common/nui/common.jsp" %>
	<title>押品可出库信息</title>
</head>
<script type="text/javascript" src="<%=contextPath%>/grt/grtJS/grt.js"></script>
<body>
	<div id="form1" style="width:100%;height:auto;overflow:hidden;">
		<input type="hidden" name="collTbConContractEntitys" class="nui-hidden" />
		<div class="nui-dynpanel" columns="4">
			<label class="nui-form-label">经办机构：</label>
			<input name="collConGrtRelationOut.orgNum" required="false" class="nui-text" 
				id="collConGrtRelationOut.orgNum" dictTypeId="org" />
			<label class="nui-form-label">经办人：</label>
			<input name="collConGrtRelationOut.userNum" required="false" class="nui-text" 
				id="collConGrtRelationOut.userNum" dictTypeId="user" />
			<label class="nui-form-label">借款人：</label>
			<input name="collConGrtRelationOut.partyName1" required="false" class="nui-text" 
				id="collConGrtRelationOut.partyName1" />
			<label>借出人：</label>
			<input name="checkOutName" required="true" class="nui-textbox nui-form-input" vtype="maxLength:60"
				id="checkOutName" />
			<label>出库原因：</label>
			<input name="stockRemovalReason" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:2"
				id="stockRemovalReason" dictTypeId="YP_GLCD0007" onvaluechanged="chooisereason" />
			<label id="cstt">出库状态：</label>
			<input name="cardState" required="true" vtype="maxLength:4" class="nui-dictcombobox nui-form-input"
				id="cardState" dictTypeId="YP_GLCD0143" enabled="false" />
			<label>备注：</label>
			<input name="laidUpReason" required="false" class="nui-textarea nui-form-input" vtype="maxLength:200"
				id="laidUpReason" colspan="1" />
    	</div>
	</div>
	<!-- onload="initSurelyOrg()"
	onrowclick="hangdianji" -->
	<div id="grid1" class="nui-datagrid" style="width:100%;height:378px;" allowCellEdit="true" allowCellSelect="true"
		 dataField="collConGrtRelationOuts" allowResize="false" showReloadButton="false" 
		 sizeList="[1000]" multiSelect="true" pageSize="1000" sortMode="client" showPager="false">
		<div property="columns">
			<div type="indexcolumn" >序号</div>
			<div field="subcontractNum" allowSort="true" width="80px" >担保合同号</div>
			<div field="currencyCd" allowSort="true" dictTypeId="CD000001" width="80px" >担保币种</div>
			<div field="guaranteeRightMoney" allowSort="true" width="80px" >担保合同金额</div>
			<div field="partyName2" allowSort="true" width="80px" >担保人名称</div>
			<div field="subcontractStatusCd" allowSort="true" dictTypeId="XD_SXCD1219" name="subcontractStatusCd" width="80px" >担保合同状态</div>
			<div field="sortType" allowSort="true" dictTypeId="XD_DBCD4002" width="80px" >押品类型</div>
			<div field="cardType" allowSort="true" dictTypeId="YP_GLCD0200" width="80px" >权证类型</div>
			<div field="registerCertino" width="80px">权利证明号</div>
			<div field="surelyOrg" allowSort="true" name="surelyOrg" width="80px" dictTypeId="org" >保管机构</div>
			<div field="cardState" allowSort="true" dictTypeId="YP_GLCD0008" width="80px" >权证状态</div>
			<div field="cardInRevertDate" headerAlign="center" allowSort="true" name="cardInRevertDate" 
				dateFormat="yyyy-MM-dd" width="100px">
				借出预归还时间
				<input property="editor" required="true" class="nui-datepicker" style="width:100%;"/>
			</div>
			<div field="mailerNum" headerAlign="center" allowSort="true" name="mailerNum" >信封编号</div>
        </div>
	</div>	
	
	<div class="nui-toolbar" style="border-bottom:0;text-align:center;">
		<a class="nui-button" iconCls="icon-ok" onclick="outcool()" id="outcool">出库提交</a>
		<a class="nui-button" iconCls="icon-close" onclick="closeok()">关闭</a>
	</div>	
		
	<script type="text/javascript">
		nui.parse();
		var grid = nui.get("grid1");
		var form = nui.get("#form1");
		//隐藏信封编号
		grid.hideColumn(grid.getColumn("mailerNum"));
		grid.hideColumn(grid.getColumn("cardInRevertDate"));
		
		function SetData(data) {
			data = nui.clone(data);
			grid.addRows(data.collConGrtRelationOuts,0);
			grid.setTotalCount(data.collConGrtRelationOuts.length);
			nui.get("collConGrtRelationOut.orgNum").setValue(data.collConGrtRelationOuts[0].orgNum);
			nui.get("collConGrtRelationOut.userNum").setValue(data.collConGrtRelationOuts[0].userNum);
			nui.get("collConGrtRelationOut.partyName1").setValue(data.collConGrtRelationOuts[0].partyName1);
        }
		
		
		/**
		 * 选择出库原因！控制出库状态！
		 */	
		function chooisereason(){
			grid.selectAll();
			var rows = grid.getSelecteds();
			if(nui.get("stockRemovalReason").getValue()==""){
				nui.get("cardState").setData(getDictData('YP_GLCD0143','str','0301,0302,0303'));
				nui.get("cardState").setValue("");
				nui.get("cardState").setEnabled(false);
				grid.hideColumn(grid.getColumn("cardInRevertDate"));
				for(var i = 0;i < rows.length;i++){
					rows[i].cardInRevertDate="";
					grid.beginEditRow(rows[i]);
				}
			}else if(nui.get("stockRemovalReason").getValue()!="01"&&nui.get("stockRemovalReason").getValue()!="02"){
				nui.get("cardState").setData(getDictData('YP_GLCD0143','str','0301,0302'));
				nui.get("cardState").setValue("--请选择--");
				nui.get("cardState").setEnabled(true);
				grid.showColumn(grid.getColumn("cardInRevertDate"));
				for(var i = 0;i < rows.length;i++){
	      			grid.beginEditRow(rows[i]);
	      		}
			}else if(nui.get("stockRemovalReason").getValue()=="01"||nui.get("stockRemovalReason").getValue()=="02"){
				nui.get("cardState").setData(getDictData('YP_GLCD0143','str','0301,0302,0303'));
				nui.get("cardState").setValue("0303");
				nui.get("cardState").setEnabled(false);
				grid.hideColumn(grid.getColumn("cardInRevertDate"));
				for(var i = 0;i < rows.length;i++){
					rows[i].cardInRevertDate="";
					grid.beginEditRow(rows[i]);
				}
			}else{
				nui.get("cardState").setData(getDictData('YP_GLCD0143','str','0301,0302,0303'));
				nui.get("cardState").setValue("");
				nui.get("cardState").setEnabled(true);
				for(var i = 0;i < rows.length;i++){
					rows[i].cardInRevertDate="";
					grid.beginEditRow(rows[i]);
				}
			}
		}
		
		/**
		 * 点击出库按钮进行出库操作
		 */
		function outcool() {
			grid.selectAll();
			var rows = grid.getSelecteds();
			var cardState = nui.get("cardState").getValue();
			var stockRemovalReason = nui.get("stockRemovalReason").getValue();
			//获取到所有选中的行
			var rows = grid.getSelecteds();
			//获取到DG中的所有数据
			var data = {tbGrtRegcardinfo:rows};
			//贷款合同号主键
			data.contractId = rows[0].contractId;
			//借款人名称
			var partyName = nui.get("collConGrtRelationOut.partyName1").getValue();
			data.partyName = partyName;
			//借出人
			var checkOutName = nui.get("checkOutName").getValue();
			data.checkOutName = checkOutName;
			//出库原因
			var stockRemovalReason = nui.get("stockRemovalReason").getValue();
			data.stockRemovalReason = stockRemovalReason;
			//出库状态
			var cardState = nui.get("cardState").getValue();
			data.cardState = cardState;
			if(rows == 0 || stockRemovalReason == "" || stockRemovalReason==null||cardState==""||cardState==null){
				nui.alert("请将信息填写完整！");
				return;
			}
			//判断借出归还时间是否为空
			if("0301" == cardState || "0302" == cardState){
				for(var i = 0;i < rows.length; i++){
					if(rows[i].cardInRevertDate == "" || rows[i].cardInRevertDate == null){
						nui.alert("借出预归还时间不能为空！");
						return;
					}
				}
			}
			//将获取到的数据转换格式
			var json = nui.encode(data);
			git.mask();
			$.ajax({
	       		url: "com.bos.grt.regmanage.collateralout.addCollateralOutmain.biz.ext",
	       		type: 'POST',
	       		data: json,
	       		cache: false,
	       		contentType:'text/json',
	       		success: function (text) {
	       			if(text.msg == "保存成功！"){
	       				//出库按钮
	    	   			nui.get("outcool").setEnabled(false);
		       			//备注
		       			nui.get("laidUpReason").setEnabled(false);
	       				//借出人
	       				nui.get("checkOutName").setEnabled(false);
	       				//出库原因
	    	   			nui.get("stockRemovalReason").setEnabled(false);
		       			//出库状态
		       			nui.get("cardState").setEnabled(false);
		       			//流程选择下级岗位提交页面
		       			//alert(text.node);
						openSubmitView(text.node);
	       			}else{
	       				nui.alert(text.msg);
	       			}
	       			git.unmask();
	       		},
	       		error: function (jqXHR, textStatus, errorThrown) {
					nui.alert(jqXHR.responseText);
	       		}
			});
		}
		
		/**
		 * 出库撤销
		 */
		function outcoolcancel(){
			var rows = grid.getData();
			//获取到DG中的所有数据
			var data = {tbGrtRegcardinfo:rows};
			//将获取到的数据转换格式
			var json = nui.encode(data)+nui.encode({"operateType":"02"});
			git.mask();
			$.ajax({
	       		url: "com.bos.grtpublic.getInterResult.getCRMSCollInBackRig.biz.ext",
	       		type: 'POST',
	       		data: json,
	       		cache: false,
	       		contentType:'text/json',
	       		success: function (text) {
	       			nui.alert(text.msg);
	       			if(text.msg == "撤销成功！"){
		       			//禁用出库撤销按钮
		       			nui.get("outcoolcancel").setEnabled(false);
	       			}
	       			git.unmask();
	       		},
	       		error: function (jqXHR, textStatus, errorThrown) {
					nui.alert(jqXHR.responseText);
	       		}
			});
		}
	
		/**
		 * 关闭
		 */
		function CloseWindow(action) {            
	   		 window.CloseOwnerWindow("ok");
		}
		
		/**
		 * 点击关闭按钮，关闭窗口	
		 */
		function closeok(){
			CloseWindow("ok");
		}
		
	</script>
</body>
</html>