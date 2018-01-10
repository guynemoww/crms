<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): ZhuYongLun
  - Date: 2014-07-14 20:22:17
  - Description:
-->
<head>
	<%@include file="/common/nui/common.jsp" %>
	<title>押品封包入库和借出归还</title>
</head>
<body>
	<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	    <div class="nui-dynpanel" columns="4">
			<label class="nui-form-label">经办机构：</label>
			<input name="collConGrtRelationInAndOutIn.orgNum" required="false" class="nui-text" 
				id="collConGrtRelationInAndOutIn.orgNum" dictTypeId="org" />
			<label class="nui-form-label">经办人：</label>
			<input name="collConGrtRelationInAndOutIn.userNum" required="false" class="nui-text" 
				id="collConGrtRelationInAndOutIn.userNum" dictTypeId="user" />
			<label class="nui-form-label">借款人：</label>
			<input name="collConGrtRelationInAndOutIn.partyName1" required="false" class="nui-text" 
				id="collConGrtRelationInAndOutIn.partyName1" />
	    	<label class="nui-form-label">入库原因：</label>
			<input name="laidUpReason" required="true" class="nui-textarea nui-form-input" vtype="maxLength:200"
				id="laidUpReason" dictTypeId="user" />
		</div>
	</div>
	<div class="nui-toolbar" style="border-bottom:0;">
		<a class="nui-button" iconCls="icon-edit" onclick="shengchengall()" id="shengchengall">生成信封编号</a>
		<a class="nui-button" iconCls="icon-edit" onclick="xuanzeall()" id="xuanzeall">选择信封编号</a>
	</div>
	<div id="grid1" class="nui-datagrid" style="width:100%;height:378px;"
		dataField="collConGrtRelationInAndOutIns" allowResize="false" showReloadButton="false" 
		sizeList="[100]" multiSelect="true" pageSize="100" sortMode="client">
		<div property="columns">
			<div type="checkcolumn" >选择</div>
			<div field="subcontractNum" allowSort="true" >担保合同号</div>
			<div field="currencyCd" allowSort="true" dictTypeId="CD000001" >担保币种</div>
			<div field="guaranteeRightMoney" allowSort="true" >担保合同金额</div>
			<div field="partyName2" allowSort="true" >担保人名称</div>
			<div field="subcontractStatusCd" allowSort="true" dictTypeId="XD_SXCD1219" >担保合同状态</div>
			<div field="sortType" allowSort="true" dictTypeId="XD_DBCD4002" >押品类型</div>
			<div field="cardType" allowSort="true" dictTypeId="YP_GLCD0200" >权证类型</div>
			<div field="registerCertino" width="120">权利证明号</div>
			<div field="surelyOrg" allowSort="true" name="surelyOrg" dictTypeId="org" >保管机构</div>
			<div field="cardState" allowSort="true" dictTypeId="YP_GLCD0008" >权证状态</div>
			<div field="mailerNum" headerAlign="center" allowSort="true" width="180px" >信封编号</div>
		</div>
   	</div>
   	
   	<div class="nui-toolbar" style="border-bottom:0;text-align:center;">
		<a class="nui-button" iconCls="icon-ok" onclick="incool()" id="incool">入库提交</a>
		<%--<a class="nui-button" iconCls="icon-undo" onclick="incoolcancel()" id="incoolcancel">入库撤销</a>--%>
		<a class="nui-button" iconCls="icon-close" onclick="closeok()">关闭</a>
	</div>	
	
	<script type="text/javascript">
		nui.parse();
	    var form = new nui.Form("#form1");
		var grid = nui.get("grid1");
		
        function SetData(data) {
			//data = nui.clone(data);
			grid.addRows(data.collConGrtRelationInAndOutIns,0);
			grid.setTotalCount(data.collConGrtRelationInAndOutIns.length);
			nui.get("collConGrtRelationInAndOutIn.orgNum").setValue(data.collConGrtRelationInAndOutIns[0].orgNum);
			nui.get("collConGrtRelationInAndOutIn.userNum").setValue(data.collConGrtRelationInAndOutIns[0].userNum);
			nui.get("collConGrtRelationInAndOutIn.partyName1").setValue(data.collConGrtRelationInAndOutIns[0].partyName1);
        }
        
        /**
		 * 点击生成信封编号按钮
		 */
		function shengchengall(){
			var rows = grid.getSelecteds();
			//信封编号
			var mailerNum="";
			var thisTime="<%=com.bos.pub.GitUtil.getBusiTimestamp()%>";
			var thisOrgCd="<%=com.bos.pub.GitUtil.getCurrentOrgCd()%>";
			var thisUserId="<%=com.bos.pub.GitUtil.getCurrentUserId()%>";
			thisTime = thisTime.replace(/-/g,"");
			thisTime = thisTime.replace(/:/g,"");
			thisTime = thisTime.replace(/ /g,"");
			thisTime = thisTime.substr(0,14);
			mailerNum = thisOrgCd +thisUserId + thisTime;
			for(var i = 0;i < rows.length;i++){
				rows[i].mailerNum=mailerNum;
				grid.beginEditRow(rows[i]);
			}
		}
		
		/**
	     * 点击选择信封编号按钮
	     */
		function xuanzeall(){
			var rows = grid.getSelecteds();
			nui.open({
				url: nui.context+"/grt/regmanage/collateralinandoutin/collateral_in_outin_chooisepackage.jsp",
				title: "选择押品信封编号", 
				width: 800,
				height: 600,
	           	allowResize:false,
	           	allowDrag: false,
	           	showCloseButton: true,
	     		showMaxButton: false,
	           	ondestroy: function (action) {
	           		if(action=="ok"){
						var iframe = this.getIFrameEl();
						var mailnum = iframe.contentWindow.mnum;
						for(var i = 0;i < rows.length;i++){
							rows[i].mailerNum=mailnum;
							grid.beginEditRow(rows[i]);
						}
					}
				}
	       	});
		}
		
		/**
		 * 点击入库按钮进行入库操作
		 */
		function incool() {
			grid.selectAll();
			//获取到所有选中的行
			var rows = grid.getSelecteds();
			//判断信封编号是否为空，
			for(var i = 0;i < rows.length;i++){
				//为空则不往下执行
				if(rows[i].mailerNum==null){
					nui.alert("信封编号不能为空！");
					return;
				}
			}
			form.validate();
			if (form.isValid() == false) {
				nui.alert("请将信息填写完整");
				return;
			}
			var json1=nui.encode({"tbGrtGuarantybasic":rows});
			//获取到DG中的所有数据
			var data = {tbGrtRegcardinfo:rows};
			//入库原因
			var laidUpReason = nui.get("laidUpReason").getValue();
			data.laidUpReason = laidUpReason;
			//借款人名称
		    var partyName = nui.get("collConGrtRelationInAndOutIn.partyName1").getValue();
			data.partyName = partyName;
			//合同ID
			var contractId = rows[0].contractId;
			data.contractId = contractId;
			//将获取到的数据转换格式
			var json = nui.encode(data);
			git.mask();
			$.ajax({
				url: "com.bos.grt.regmanage.collateralinandoutin.addGrtRanklaidupmain.biz.ext",
				type: 'POST',
				data: json,
				cache: false,
				contentType:'text/json',
				success: function (text) {
				    if(text.msg == "保存成功！"){
					  nui.get("incool").setEnabled(false);
					  nui.get("laidUpReason").setEnabled(false);
					  nui.get("shengchengall").setEnabled(false);
					  nui.get("xuanzeall").setEnabled(false);
					  //流程选择下级岗位提交页面
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
		 * 入库撤销操作
		 */
		function incoolcancel(){
			grid.selectAll();
			//获取到所有选中的行
			var rows = grid.getSelecteds();
			var data = {tbGrtRegcardinfo:rows};
			//将获取到的数据转换格式
			var json = nui.encode(data)+nui.encode({"operateType":"01"});
			git.mask();
			$.ajax({
				url: "com.bos.grtpublic.getInterResult.getCRMSCollInBackRig.biz.ext",
				type: 'POST',
				data: json,
				cache: false,
				contentType:'text/json',
				success: function (text) {
					nui.alert(text.msg);
					<%--if(text.msg == "撤销成功！"){
						//禁用入库撤销按钮
						nui.get("incoolcancel").setEnabled(false);
					}--%>
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