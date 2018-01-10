<%@page import="com.bos.pub.UserUtil"%>
<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 王文昌
  - Date: 2015-9-1
  - Description:
-->
<head>
<title>个人合同信息查询</title>
<%@include file="/common/nui/common.jsp"%>
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
<%@page import="com.eos.*"%>
</head>
<body>
	<iframe name="exportFrame" id="exportFrame" src="" style="display:none;"></iframe>
	<div id="tabs1" class="nui-tabs" activeIndex="0"
		style="width: 100%; height: auto;">
		<div title="个人合同信息查询">
			<center>
				<div id="form1" class="nui-form"
					style="width: 99.5%; height: auto; overflow: hidden;">
					<input name="sqlName" class="nui-hidden nui-form-input" value="com.bos.account.credit.indvcustomerContract" />
					<div class="nui-dynpanel" columns="8">
						<label class="nui-form-label">机构名称：</label>
						<input id="item.orgcode" name="item.orgcode" text="<%=((UserObject)session.getAttribute("userObject")).getUserOrgName()%>" value="<%=((UserObject)session.getAttribute("userObject")).getAttributes().get("orgcode")%>" allowInput="false" required="true" class="nui-buttonEdit" onbuttonclick="selectOrg"/>
						<label>客户名称：</label>
						<input name="item.partyName" class="nui-textbox nui-form-input"/>
						<label>证件类型：</label>
						<input id="item.certType" name="item.certType" class="nui-dictcombobox nui-form-input"  textField="dictname" valueField="dictid" dictTypeId="CDKH0002"  allowInput="false" />
						<label>证件号码：</label>			
						<input id="item.certNum"  class="nui-textbox nui-form-input" name="item.certNum" onvalidation=""  />
						<label>贷款品种：</label>			
						<input id="item.productType"  class="nui-buttonEdit nui-form-input" allowInput="false" onbuttonclick="selectProduct"  name="item.productType" dictTypeId="product" />
						<label>合同编号：</label>			
						<input id="item.contractNum"  class="nui-textbox nui-form-input" name="item.contractNum" onvalidation=""  />
						<label>合同状态：</label>
						<input id="item.conStatus" name="item.conStatus" class="nui-dictcombobox nui-form-input"  dictTypeId="XD_SXCD8003"  />
						<label>主担保方式：</label>			
						<input id="item.mainGuarantyType"  name="item.mainGuarantyType" class="nui-dictcombobox nui-form-input" dictTypeId="CDZC0005"  />
						<label>金额范围：</label>			
						<div colspan="3" style="text-align: left;">
						<input id="item.amtType" name="item.amtType" class="nui-combobox" data="amtType" style="width: 100px;" />
						<input id="item.minAmt" name="item.minAmt" class="nui-textbox"  style="width: 100px;"/>-	
						<input id="item.maxAmt" name="item.maxAmt" class="nui-textbox"  style="width: 100px;"/>
					</div>
						<!--  <label>经办人：</label> -->
						<% if(UserUtil.isManager()){
					%>
						<input id="item.userNum"  class="nui-hidden  nui-buttonEdit" name="item.userNum" text="<%=((UserObject)session.getAttribute("userObject")).getUserName()%>"  value="<%=((UserObject)session.getAttribute("userObject")).getUserId()%>" readonly> />
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
					style="width: 100%; height: auto"
					url="com.bos.csm.pub.ibatis.getItem.biz.ext" dataField="items"
					allowResize="true" showReloadButton="false" allowAlternating="true"
					sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" onselectionchanged="" sortMode="client">
					<div property="columns">
						<!--  
						<div field="corpCustomerTypeCd" allowSort="true" width="" headerAlign="center" dictTypeId="">客户类型</div>
						-->
						<div field="orgNum" allowSort="true" width="" headerAlign="center" autoEscape="false" dictTypeId="org">机构名称</div>
						
						<div field="partyName" allowSort="true" width="" headerAlign="center" dictTypeId="">客户名称</div>
						
						<div field="contractNum" allowSort="true" width="" headerAlign="center" dictTypeId="">合同编号</div>
						
						<div field="conStatus" allowSort="true" width="" headerAlign="center" dictTypeId="XD_SXCD8003">合同状态</div>
						
						<div field="productType" allowSort="true" width="" headerAlign="center" dictTypeId="product">贷款品种</div>
						
						<div field="currencyCd" allowSort="true" width="" headerAlign="center" dictTypeId="CD000001">币种</div>
						
						<div field="contractAmt" allowSort="true" width="" headerAlign="center" dictTypeId="">合同金额</div>
						
						<div field="conYuE" allowSort="true" width="" headerAlign="center" dictTypeId="">合同已用金额</div>
						
						<div field="mainGuarantyType" allowSort="true" width="" headerAlign="center" dictTypeId="CDZC0005">主担保方式</div>
						
						<div field="beginDate" allowSort="true" width="" headerAlign="center" dictTypeId="">起始日</div>
						
						<div field="endDate" allowSort="true" width="" headerAlign="center" dictTypeId="">到期日</div>
						<!--  
						<div field="yearRate" allowSort="true" width="" headerAlign="center" dictTypeId="">贷款利率</div>
						-->
						<div field="yqts" allowSort="true" width="" headerAlign="center" dictTypeId="">逾期天数</div>
						<!--  
						<div field="dft_itr_in" allowSort="true" width="" headerAlign="center" dictTypeId="">表内欠息</div>
						
						<div field="dft_itr_out" allowSort="true" width="" headerAlign="center" dictTypeId="">表外欠息</div>
						-->
						<div field="fljg" allowSort="true" width="" headerAlign="center" dictTypeId="XD_FLCD0001">分类</div>
						
						<div field="userNum" allowSort="true" width="" headerAlign="center" dictTypeId="user">经办人</div>
						
					</div>
				</div>
			</center>
		</div>
	</div>
	<script type="text/javascript">
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
	
		function init(){
 		git.mask();
	    var json = nui.encode({parentId:"10000"});
		     $.ajax({
		        url: "com.bos.csm.pub.getDict.getCertTypeDict.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        contentType:'text/json',
		        success: function (text) {
		            git.unmask();
		            nui.get("item.certType").setData(text.levels);
		            custFlag = true;
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		            git.unmask();
		            nui.alert(jqXHR.responseText);
		        }
		     });
		}
	  init();	
	    
	 grid.on("preload", function(e) {
			if (!e.data || e.data.length < 1) {
				return;
			}
			for (var i = 0; i < e.data.length; i++) {//nui.alert(nui.encode(e.data[i]));
				e.data[i]['partyName']='<a href="#" onclick="toGoCustDetail(\''+ e.data[i].partyId+ '\');">'+e.data[i]['partyName']+'</a>';
				e.data[i]['contractNum']='<a href="#" onclick="bizView3231(\''+ e.data[i].contractNum+ '\');">'+e.data[i]['contractNum']+'</a>';
			}
		});
		
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
				var ifrm = document.getElementById("exportFrame");
				git.mask();
		 var o = form.getData();//逻辑流必须返回total
  		 
	
		 	 	var json = nui.encode(o);
		 
 		$.ajax({
	            url: "com.bos.csm.pub.ibatis.zzrHTDownloadEXCEL.biz.ext",
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