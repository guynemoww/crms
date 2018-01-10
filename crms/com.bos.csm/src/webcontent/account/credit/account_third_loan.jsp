<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 甘泉
  - Date: 2015-6-3 8:26:27
  - Description:
-->
<head>
<title>合作项目额度使用明细查询</title>
<%@include file="/common/nui/common.jsp"%>
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
<%@page import="com.eos.*"%>
</head>
<body>
	<iframe name="exportFrame" id="exportFrame" src="" style="display:none;"></iframe>
	<div id="tabs1" class="nui-tabs" activeIndex="0"
		style="width: 100%; height: auto;">
		<div title="合作项目额度使用明细查询">
			<center>
				<div id="form1" class="nui-form"
					style="width: 99.5%; height: auto; overflow: hidden;">
					<input name="sqlName" class="nui-hidden nui-form-input" value="com.bos.account.credit.thirdCustomerLoan" />
					<div class="nui-dynpanel" columns="8">
						<label class="nui-form-label">机构名称：</label>
						<input id="item.orgcode" name="item.orgcode" text="<%=((UserObject)session.getAttribute("userObject")).getUserOrgName()%>" value="<%=((UserObject)session.getAttribute("userObject")).getAttributes().get("orgcode")%>" allowInput="false" required="true" class="nui-buttonEdit" onbuttonclick="selectOrg"/>
						<label>第三方客户名称：</label>
						<input name="item.partyName" class="nui-textbox nui-form-input"/>
						<label>合作项目名称：</label>
						<input name="item.hzName" class="nui-textbox nui-form-input"/>
						<label>业务品种：</label>			
						<input id="item.productType"  class="nui-buttonEdit nui-form-input" allowInput="false" onbuttonclick="selectProduct"  name="item.productType" dictTypeId="product" />
						<label>借据状态：</label>			
						<input id="item.summaryStatusCd"  class="nui-dictcombobox nui-form-input" name="item.summaryStatusCd" dictTypeId="XD_SXYW0226"  />
						<!--  <label>经办人：</label> -->
						
						<% UserObject user = (UserObject)session.getAttribute("userObject");
						String manage = "";
						DataObject[] roles =  (DataObject[]) user.getAttributes().get("roles");
						if (null != roles && roles.length > 0) {
							for (int i=0; i<roles.length; i++) {
									DataObject role = roles[i];
									if ("R1002".equals(role.get("roleid"))||"R1003".equals(role.get("roleid"))||
										"R1159".equals(role.get("roleid"))||"R1153".equals(role.get("roleid"))||
										"R1147".equals(role.get("roleid"))||
									"R1006".equals(role.get("roleid"))||"R1007".equals(role.get("roleid"))){
										manage="true";
									}else{
										continue;
									}
								}	        			
						}
						if(manage.equals("true")){
					%>
						<input id="item.userNum"  class="nui-hidden nui-buttonEdit" name="item.userNum" text="<%=((UserObject)session.getAttribute("userObject")).getUserName()%>"  value="<%=((UserObject)session.getAttribute("userObject")).getUserId()%>" readonly> />
					<% 
						}
						//else{
					%>
						<!--  
						<input id="item.userNum" name="item.userNum" required="false" allowInput="false" class="nui-buttonEdit" onbuttonclick="selectCustManegers"/>
						-->
					<%
						//}
					%>
						
					</div>
					<div class="nui-toolbar"
						style="text-align: right; border: 0; padding-right: 20px;">
												
						<a class="nui-button" iconCls="icon-search" onclick="queryInit()">查询</a>
						<a class="nui-button" onclick="reset()" iconCls="icon-reset">重置</a>
						<!-- <a class="nui-button" iconCls="icon-download" onclick="dc()">导出EXCEL</a> -->
						
					</div>
				</div>
				<div id="datagrid1" class="nui-datagrid"
					style="width: 99.5%; height: auto"
					url="com.bos.csm.pub.ibatis.getItem.biz.ext" dataField="items"
					allowResize="true" showReloadButton="false" allowAlternating="true"
					sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" onselectionchanged="" sortMode="client">
					<div property="columns">
						<div field="orgNum" allowSort="true" width="" headerAlign="center" autoEscape="false" dictTypeId="org">机构名称</div>
						
						<div field="partyName2" allowSort="true" width="" headerAlign="center" dictTypeId="">第三方客户名称</div>
						
						<div field="itemName" allowSort="true" width="" headerAlign="center" dictTypeId="">合作项目名称</div>
						
						<div field="partyName" allowSort="true" width="" headerAlign="center" dictTypeId="">借款人名称</div>
						
						<div field="contractNum" allowSort="true" width="" headerAlign="center" dictTypeId="">合同编号</div>
						
						<div field="summaryNum" allowSort="true" width="" headerAlign="center" dictTypeId="">借据编号</div>
						
						<div field="productType" allowSort="true" width="" headerAlign="center" dictTypeId="product">贷款品种</div>
						
						<div field="currencyCd" allowSort="true" width="" headerAlign="center" dictTypeId="CD000001">币种</div>
						
						<div field="summaryAmt" allowSort="true" width="" headerAlign="center" dictTypeId="">借据金额</div>
						
						<div field="jjye" allowSort="true" width="" headerAlign="center" dictTypeId="">借据余额</div>
						
						<div field="beginDate" allowSort="true" width="" headerAlign="center" dictTypeId="">借据起期</div>
						
						<div field="endDate" allowSort="true" width="" headerAlign="center" dictTypeId="">借据止期</div>
						
						<div field="summaryStatusCd" allowSort="true" width="" headerAlign="center" dictTypeId="XD_SXYW0226">借据状态</div>
						
						<div field="yqts" allowSort="true" width="" headerAlign="center" dictTypeId="">逾期天数</div>
						
						<div field="jjyqbj" allowSort="true" width="" headerAlign="center" dictTypeId="">逾期本金</div>
						
						<div field="normalItr" allowSort="true" width="" headerAlign="center" dictTypeId="">正常利息</div>
						<div field="arrearItr" allowSort="true" width="" headerAlign="center" dictTypeId="">拖欠利息</div>
						<div field="punishItr" allowSort="true" width="" headerAlign="center" dictTypeId="">罚息</div>
						
						<div field="userNum" allowSort="true" width="" headerAlign="center" dictTypeId="user">经办人</div>
						<!--  
						<div field="viewLoanList" allowSort="true" width="" headerAlign="center" dictTypeId="">查看流水</div>
						-->
					</div>
				</div>
			</center>
		</div>
	</div>
	<script type="text/javascript">
		nui.parse();
		git.mask();
		var form = new nui.Form("#form1");
		var grid = nui.get("datagrid1");
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
				//e.data[i]['partyName']='<a href="#" onclick="toGoCustDetail(\''+ e.data[i].partyId+ '\');">'+e.data[i]['partyName']+'</a>';
				e.data[i]['contractNum']='<a href="#" onclick="bizView3231(\''+ e.data[i].contractNum+ '\');">'+e.data[i]['contractNum']+'</a>';
				e.data[i]['summaryNum']='<a href="#" onclick="bizView3231(\''+ e.data[i].summaryNum+ '\');">'+e.data[i]['summaryNum']+'</a>';
				//e.data[i]['viewLoanList']='<button onclick=\"viewLoan();\">查看流水</button>';
			}
		});
	  
	  function viewLoan() {
	        var row = grid.getSelected();
	        debugger;
	        if(row) {
				nui.open({
	            url: "csm/account/credit/loan_list.jsp?dueNum="+row.summaryNum2,
	            title: "借据流水", 
	            width: 1000,
	    		height: 500,
	            allowResize:true,
	    		showMaxButton: true,
	            onload: function () {
	                var iframe = this.getIFrameEl();
	                var data = row;
	            }});
				return;
	        }else {
	            alert("请选中一条记录");
	        }
	        
	    }
		
		var arr = git.getDictDataFilter("XD_SXYW0226","02,03");
		nui.get("item.summaryStatusCd").setData(arr);
		
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
	
	
		//产品选择
		function selectProduct(e) {
	        var btnEdit = this;
	        nui.open({
	            url: nui.context + "/pub/product/product/select_product_tree.jsp?partyTypeCd=02&partyId=null",
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
	
		function dc(){
				var ifrm = document.getElementById("exportFrame");
				git.mask();
		 var o = form.getData();//逻辑流必须返回total
  		 
	
		 	 	var json = nui.encode(o);
		 
 		$.ajax({
	            url: "com.bos.csm.pub.ibatis.hzxmDownloadEXCEL.biz.ext",
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