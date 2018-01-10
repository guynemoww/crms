<%@page import="com.bos.pub.UserUtil"%>
<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 甘泉
  - Date: 2015-6-3 8:26:27
  - Description:
-->
<head>
<title>公司合同信息查询</title>
<%@include file="/common/nui/common.jsp"%>
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
<%@page import="com.eos.*"%>

</head>
<body>
	<iframe name="exportFrame" id="exportFrame" src="" style="display:none;"></iframe>
	<div id="tabs1" class="nui-tabs" activeIndex="0" style="width: 100%; height: 100%;">
		<div title="公司合同信息查询">
			<div id="form1" class="nui-form">
				<input name="sqlName" class="nui-hidden nui-form-input" value="com.bos.account.credit.corporationContract" />
				<div class="nui-dynpanel" columns="8">
					<label class="nui-form-label">机构名称：</label>
					<input id="item.orgcode" name="item.orgcode" text="<%=((UserObject)session.getAttribute("userObject")).getUserOrgName()%>" value="<%=((UserObject)session.getAttribute("userObject")).getAttributes().get("orgcode")%>" allowInput="false" required="true" class="nui-buttonEdit" onbuttonclick="selectOrg"/>
					<label>客户名称：</label>
					<input name="item.partyName" class="nui-textbox nui-form-input"/>
					<label>客户性质：</label>
					<input id="item.corpCustomerTypeCd" name="item.corpCustomerTypeCd" class="nui-dictcombobox nui-form-input" dictTypeId="XD_KHCD0252" allowInput="false" />
					<label>综合授信协议编号：</label>			
					<input id="item.xyContractNum"  class="nui-textbox nui-form-input" name="item.xyContractNum" onvalidation=""  />
					<label>合同编号：</label>			
					<input id="item.contractNum"  class="nui-textbox nui-form-input" name="item.contractNum" onvalidation=""  />
					<label>贷款品种：</label>			
					<input id="item.productType"  class="nui-buttonEdit nui-form-input" allowInput="false" onbuttonclick="selectProduct"  name="item.productType" dictTypeId="product" />
					<label>主担保方式：</label>			
					<input id="item.mainGuarantyType"  class="nui-dictcombobox nui-form-input" name="item.mainGuarantyType" dictTypeId="CDZC0005"  />
					<label>合同状态：</label>			
					<input id="item.conStatus"  class="nui-dictcombobox nui-form-input" name="item.conStatus" dictTypeId="XD_SXCD8003"  />
					<label>金额范围：</label>			
						<div colspan="3" style="text-align: left;">
						<input id="item.amtType" name="item.amtType" class="nui-combobox" data="amtType" style="width: 100px;" />
						<input id="item.minAmt" name="item.minAmt" class="nui-textbox"  style="width: 100px;"/>-	
						<input id="item.maxAmt" name="item.maxAmt" class="nui-textbox"  style="width: 100px;"/>
					</div>
					<label>经办人：</label> 
				<% 
					if(UserUtil.isManager()){
				%>
					<input id="item.userNum"  class="nui-buttonEdit" name="item.userNum" text="<%=((UserObject)session.getAttribute("userObject")).getUserName()%>"  value="<%=((UserObject)session.getAttribute("userObject")).getUserId()%>" readonly> />
				<% 
					}else{
				%>
					<input id="item.userNum" name="item.userNum" required="false" allowInput="false" class="nui-buttonEdit" onbuttonclick="selectCustManegers"/>
				<%	
					}
				%>
				</div>
				<div class="nui-toolbar" style="text-align: right; border: 0; padding-right: 20px;">
					<a class="nui-button" iconCls="icon-search" onclick="queryInit()">查询</a>
					<a class="nui-button" onclick="reset()" iconCls="icon-reset">重置</a>
					<!-- <a class="nui-button" iconCls="icon-download" onclick="dc()">导出EXCEL</a> -->
				</div>
			</div>
			<div class="nui-fit">
				<div id="datagrid1" style="height:100%" class="nui-datagrid" url="com.bos.csm.pub.ibatis.getItem.biz.ext" dataField="items" multiSelect="false"  sortMode="client" allowAlternating="true">
					<div property="columns">
						<div field="corpCustomerTypeCd" allowSort="true" width="60" headerAlign="center"
							dictTypeId="XD_KHCD0252">客户性质</div>
						<div field="orgNum" allowSort="true" width="100" headerAlign="center"
							autoEscape="false" dictTypeId="org">机构名称</div>
						<div field="partyName" allowSort="true" width="120" headerAlign="center"
							dictTypeId="">客户名称</div>
						<div field="xyContractNum" allowSort="true" width="130"
							headerAlign="center">综合授信协议编号</div>
						<div field="contractNum" allowSort="true" width="130" headerAlign="center"
							dictTypeId="">合同编号</div>
						<div field="conStatus" allowSort="true" width="70" headerAlign="center"
							dictTypeId="XD_SXCD8003">合同状态</div>
						<div field="productType" allowSort="true" width="120" headerAlign="center"
							dictTypeId="product">贷款品种</div>
						<div field="currencyCd" allowSort="true" width="60" headerAlign="center"
							dictTypeId="CD000001">币种</div>
						<div field="contractAmt" allowSort="true" width="80" headerAlign="center" align="right"
							dictTypeId="">合同金额</div>
						<div field="conYuE" allowSort="true" width="80" headerAlign="center" align="right"
							dictTypeId="">合同已用金额</div>
						<div field="mainGuarantyType" allowSort="true" width="60" headerAlign="center"
							dictTypeId="CDZC0005">主担保方式</div>
						<div field="beginDate" allowSort="true" width="90" headerAlign="center"
							dictTypeId="">起始日</div>
						<div field="endDate" allowSort="true" width="90" headerAlign="center"
							dictTypeId="">到期日</div>
						<div field="yqts" allowSort="true" width="50" headerAlign="center" align="right"
							dictTypeId="">逾期天数</div>
						<div field="dft_itr_in" allowSort="true" width="70" headerAlign="center" align="right"
							dictTypeId="">表内欠息</div>
						<div field="dft_itr_out" allowSort="true" width="70" headerAlign="center" align="right"
							dictTypeId="">表外欠息</div>
						<div field="fljg" allowSort="true" width="60" headerAlign="center"
							dictTypeId="XD_FLCD0001">分类</div>
						<div field="userNum" allowSort="true" width="80" headerAlign="center"
							dictTypeId="user">经办人</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" >
		var amtType = [{"id":"CONTRACT_AMT","text":"合同金额"},{"id":"CON_YU_E","text":"合同已用金额"}];
		nui.parse();
		git.mask();
		var form = new nui.Form("#form1");
		var grid = nui.get("datagrid1");
		
		nui.get("item.amtType").setValue(amtType[0].id);
		
		function queryInit() {
			if (form.isValid()==false) {
	        	nui.alert("请输入必填项。");
	        	return;   
	        } 
			var o = form.getData();//逻辑流必须返回total
			grid.load(o);
			git.unmask();
		}
		queryInit();

		function reset() {
			form.reset();
			queryInit();
		}
		
		//机构选择
		function selectOrg(){
		
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
	                    	self.orglevel=data.orglevel;
	                        btnEdit.setValue(data.orgcode);
	                        btnEdit.setText(data.orgname);
	                    }
	                }
	            }
	        });      
		}
		
		grid.on("preload", function(e) {
			if (!e.data || e.data.length < 1) {
				return;
			}
			for (var i = 0; i < e.data.length; i++) {//nui.alert(nui.encode(e.data[i]));
				e.data[i]['partyName']='<a href="#" onclick="toGoCustDetail(\''+ e.data[i].partyId+ '\');">'+e.data[i]['partyName']+'</a>';
				e.data[i]['xyContractNum']='<a href="#" onclick="bizView3231(\''+ e.data[i].xyContractNum+ '\');">'+e.data[i]['xyContractNum']+'</a>';
				e.data[i]['contractNum']='<a href="#" onclick="bizView3231(\''+ e.data[i].contractNum+ '\');">'+e.data[i]['contractNum']+'</a>';
			}
		});
		
		//产品树
	function selectProduct(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/product/product/select_product_tree.jsp?partyTypeCd=01&partyId=null",
            title: "选择",
            width: 800,
            height: 450,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.currentNode;
                    data = nui.clone(data);
                    if (data) {
                        btnEdit.setValue(data.productCd);
                        btnEdit.setText(data.productName);
                    }
                }
                if (action == "clear") { //清空选择的内容
                	btnEdit.setValue("");
                	btnEdit.setText("");
            	}
        	}
        });            
	}
	var arr = git.getDictDataFilter("CDZC0005","01,02,03,04");
	nui.get("item.mainGuarantyType").setData(arr);
	
	var arr1 = git.getDictDataFilter("XD_SXCD8003","03,05");
	nui.get("item.conStatus").setData(arr1);
	
	// 经办人
	function selectCustManegers(e) {
		var newOrgNum;
		newOrgNum = nui.get("item.orgcode").getValue();
		
		if (newOrgNum == "") {
			alert("请先选择机构");
			return;
		}else {
			var orgIds;
			orgIds = nui.get("item.orgcode").getValue();
			var btnEdit = this;
			nui
					.open({
						url : nui.context
								+ "/pub/orgDemolition/creditMove/userManage.jsp?oldOrgNum="
								+ orgIds,
						showMaxButton : true,
						title : "选择客户经理",
						width : 800,
						height : 500,
						ondestroy : function(action) {
							if (action == "ok") {
								var iframe = this.getIFrameEl();
								var data = iframe.contentWindow.getData();
								data = nui.clone(data);
								if (data) {
									//  alert(nui.encode(data));
									btnEdit.setValue(data.userId);
									btnEdit.setText(data.empName);
								}
							}
						}
					});
		}

	}
			function dc(){
			git.mask();
				var ifrm = document.getElementById("exportFrame");
		
		 var o = form.getData();//逻辑流必须返回total
  		 
	
		 	 	var json = nui.encode(o);
		 
 		$.ajax({
	            url: "com.bos.csm.pub.ibatis.dgHTDownloadEXCEL.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            	git.unmask("form1");
	            	if(text.msg){
					git.unmask();
           ifrm.src=nui.context +"/pub/io/file/download.jsp?deleteFile=true";
	            	
	            	}else{
	            	git.unmask();
	            	 nui.alert("下载数据有误！");
	            	}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	       			git.unmask("form1");
	                nui.alert(jqXHR.responseText);
	            }
		});
		}
	</script>
</body>
</html>