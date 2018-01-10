<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 甘泉
  - Date: 2015-6-3 8:26:27
  - Description:
-->
<head>
<title>历史流水查询</title>
<%@include file="/common/nui/common.jsp"%>
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
</head>
<body>
	<iframe name="exportFrame" id="exportFrame" src="" style="display:none;"></iframe>

			<center>
				<div id="form1" class="nui-form"
					style="width: 99.5%; height: auto; overflow: hidden;">
					<input name="sqlName" class="nui-hidden nui-form-input" value="com.bos.account.loanPerson.hkls" />
					<div class="nui-dynpanel" columns="4">
						<label class="nui-form-label">机构名称：</label>
						<input id="item.orgcode" name="item.orgcode" text="<%=((UserObject)session.getAttribute("userObject")).getUserOrgName()%>" value="<%=((UserObject)session.getAttribute("userObject")).getAttributes().get("orgcode")%>" allowInput="false" required="true" class="nui-buttonEdit" onbuttonclick="selectOrg"/>
						<!--  
						<label>客户编号：</label>
						<input id="item.partyNum" name="item.partyNum" required="false" class="nui-textbox nui-form-input" />
						-->
						<label>客户名称：</label>
						<input id="item.partyName" name="item.partyName" required="false" class="nui-textbox nui-form-input" />
						
						<label>证件类型：</label>
						<input id="item.certType" name="item.certType" class="nui-dictcombobox nui-form-input"  dictTypeId="CDKH0002"  allowInput="false" />
						<label>证件号码：</label>			
						<input id="item.certNum"  class="nui-textbox nui-form-input" name="item.certNum" onvalidation=""  />
						
						<label>合同编号：</label>
						<input id="item.contractNum" name="item.contractNum" required="false" class="nui-textbox nui-form-input" />
						<label>借据编号：</label>
						<input id="item.summaryNum" name="item.summaryNum" required="false" class="nui-textbox nui-form-input" />
						
						<label  class="nui-form-label">还款日期：</label>
						<div colspan="1">
						<input id="item.beginDate1" name="item.beginDate1" required="false" style="width:100px;" class="nui-datepicker nui-form-input"/>-<input id="item.beginDate2" name="item.beginDate2" required="false" style="width:100px;" class="nui-datepicker nui-form-input"/>
						</div>
						<input  id="item.datafinal" name="item.datafinal" class="nui-hidden" />
						<input  id="item.datafinal1" name="item.datafinal1" class="nui-hidden" />
						<input  id="item.datafinal2" name="item.datafinal2" class="nui-hidden" />
						<!--  
						<label>经办人：</label> 
						-->
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
						<input id="item.userNum"  class="nui-hidden nui-form-input" name="item.userNum" text="<%=((UserObject)session.getAttribute("userObject")).getUserName()%>"  value="<%=((UserObject)session.getAttribute("userObject")).getUserId()%>" readonly> />
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
					style="width: 99%; "
					url="com.bos.csm.pub.ibatis.getItem.biz.ext" dataField="items"
					allowResize="true" showReloadButton="false" allowAlternating="true"
					sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" onselectionchanged="" sortMode="client">
					<div property="columns">
						<div type="indexcolumn">序号</div>
						<div field="orgNum" allowSort="true" width="" headerAlign="center"
							autoEscape="false" dictTypeId="org">机构名称</div>
						<!--  
						<div field="partyNum" allowSort="true" width="" headerAlign="center"
							dictTypeId="">客户编号</div>
						-->
						<div field="partyName" allowSort="true" width="" headerAlign="center"
							dictTypeId="">客户名称</div>
							
						<div field="certType" allowSort="true" width=""
							headerAlign="center" dictTypeId="CDKH0002">证件类型</div>
						<div field="certNum" allowSort="true" width="" headerAlign="center"
							dictTypeId="">证件号码</div>
							
						<div field="contractNum" allowSort="true" width="" headerAlign="center"
							dictTypeId="">合同编号</div>
						<div field="summaryNum" allowSort="true" width="" headerAlign="center"
							dictTypeId="">借据编号</div>
						<div field="rcvDate" allowSort="true" width="" headerAlign="center"
							dictTypeId="" >还款日期</div>
						<div field="hbje" allowSort="true" width="" headerAlign="center"
							dictTypeId="">归还本金</div>
						<div field="sum1" allowSort="true" width="" headerAlign="center"
							dictTypeId="">归还利息</div>
						<div field="sum2" allowSort="true" width="" headerAlign="center"
							dictTypeId="">归还罚息</div>
						<div field="sum3" allowSort="true" width="" headerAlign="center"
							dictTypeId="">归还本息合计</div>
						<!--  
						<div field="fljg" allowSort="true" width="" headerAlign="center"
							dictTypeId="XD_FLCD0001">五级分类状况</div>
						-->
						<div field="userNum" allowSort="true" width="" headerAlign="center"
							autoEscape="false" dictTypeId="user">经办人</div>
					</div>
				</div>
			</center>
	<script type="text/javascript">
		nui.parse();
		git.mask();
		var da1 = "";
		var da2 = "";
		var form = new nui.Form("#form1");
		var grid = nui.get("datagrid1");
		function queryInit() {
			if (form.isValid()==false) {
	        	nui.alert("请输入必填项。");
	        	return;   
	        }
	        
	        date1 = nui.get("item.beginDate1").getValue();
	        date2 = nui.get("item.beginDate2").getValue();
	        
			if(date1=="" && date2==""){
				nui.get("item.datafinal").setValue("X");
			}else{
				nui.get("item.datafinal").setValue("");
			}
			
			var json = nui.encode({});
			
			$.ajax({
	            url: "com.bos.csm.pub.ibatis.xwhklsdate.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            async: false,
	            contentType:'text/json',
	            success: function (text) {
	            	da1 = text.date1;
	            	da2 = text.date2;
	            	
	            	nui.get("item.datafinal1").setValue(da1);
	            	nui.get("item.datafinal2").setValue(da2);
	            	
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                nui.alert(jqXHR.responseText);
	            }
			});
		
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
	
	grid.on("preload", function(e) {
			if (!e.data || e.data.length < 1) {
				return;
			}
			for (var i = 0; i < e.data.length; i++) {//nui.alert(nui.encode(e.data[i]));
				e.data[i]['partyName']='<a href="#" onclick="toGoCustDetail(\''+ e.data[i].partyId+ '\');">'+e.data[i]['partyName']+'</a>';
				e.data[i]['contractNum']='<a href="#" onclick="bizView3231(\''+ e.data[i].contractNum+ '\');">'+e.data[i]['contractNum']+'</a>';
				e.data[i]['summaryNum']='<a href="#" onclick="bizView3231(\''+ e.data[i].summaryNum+ '\');">'+e.data[i]['summaryNum']+'</a>';
			}
		});
		function dc(){
				var ifrm = document.getElementById("exportFrame");
				git.mask();
		 var o = form.getData();//逻辑流必须返回total
  		 
	
		 	 	var json = nui.encode(o);
		 
 		$.ajax({
	            url: "com.bos.csm.pub.ibatis.xwhklsDownloadEXCEL.biz.ext",
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