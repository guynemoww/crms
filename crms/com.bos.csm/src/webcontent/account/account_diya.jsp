<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 甘泉
  - Date: 2015-6-3 8:26:27
  - Description:
-->
<head>
<title>抵质押查询</title>
<%@include file="/common/nui/common.jsp"%>
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
<%@page import="com.eos.foundation.eoscommon.ConfigurationUtil" %>
</head>
<body>
<%
String module = "CollUrlConfig";
String group = "coll_url_server";
String ip = "ip";
String port = "port";
String ipStr = ConfigurationUtil.getUserConfigSingleValue(module, group, ip);
String portStr = ConfigurationUtil.getUserConfigSingleValue(module, group, port);
 %>
	<iframe name="exportFrame" id="exportFrame" src="" style="display:none;"></iframe>
	<div id="tabs1" class="nui-tabs" activeIndex="0"
		style="width: 100%; height: auto;">
		<div title="抵质押查询">
			<center>
				<div id="form1" class="nui-form"
					style="width: 99.5%; height: auto; overflow: hidden;">
					<input name="sqlName" class="nui-hidden nui-form-input" value="com.bos.account.pj.dy" />
					<div class="nui-dynpanel" columns="8">
						<label class="nui-form-label">机构名称：</label>
						<input id="item.orgId" name="item.orgId" text="<%=((UserObject)session.getAttribute("userObject")).getUserOrgName()%>" value="<%=((UserObject)session.getAttribute("userObject")).getUserOrgId()%>" required="true" allowInput="false" class="nui-buttonEdit" onbuttonclick="selectOrg"/>
						<label>抵质押人名称：</label>
						<input id="item.suretyPartyName" name="item.suretyPartyName" required="false" class="nui-textbox nui-form-input" vtype=""/>
						<label>证件类型：</label>
						<input id="item.certType" name="item.certType" class="nui-dictcombobox nui-form-input"   dictTypeId="CDKH0002"  allowInput="false" />
						<label>证件号码：</label>
						<input id="item.certNum" name="item.certNum" required="false" class="nui-textbox nui-form-input"/>
						<label>抵质押物编号：</label>
						<input id="item.suretyNo" name="item.suretyNo" required="false" class="nui-textbox nui-form-input"/>
						<label>出入库状态：</label>
						<input id="item.mortgageStatus" name="item.mortgageStatus" class="nui-dictcombobox nui-form-input" data="rukuStatus" required="false"/>
					
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
						<input id="item.userNum"  class="nui-hidden nui-form-input" name="item.userNum"  value="<%=((UserObject)session.getAttribute("userObject")).getUserId()%>" />
					<% 
						}
					%>
					
					</div>
					<div class="nui-toolbar"
						style="text-align: right; border: 0; padding-right: 20px;">
						
						<a class="nui-button" iconCls="icon-search" onclick="queryInit()">查询</a>
						<a class="nui-button" onclick="reset()" iconCls="icon-reset">重置</a>
						<!-- <a class="nui-button" iconCls="icon-download" onclick="dc()">导出EXCEL</a> -->
						<a class="nui-button" iconCls="icon-print" onclick="printInStorage()">打印入库通知书</a>
						<a class="nui-button" iconCls="icon-print" onclick="printOutStorage()">打印出库通知书</a>
						
					</div>
				</div>
				<div id="datagrid1" class="nui-datagrid"
					style="width: 99.5%; height: auto"
					url="com.bos.csm.pub.ibatis.getItem.biz.ext" dataField="items"
					allowResize="true" showReloadButton="false"
					sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" onselectionchanged="" sortMode="client">
					<div property="columns">
						<div type="checkcolumn">选择</div>
						<div field="orgNum" allowSort="true" width="" headerAlign="center"
							autoEscape="false" dictTypeId="org">机构名称</div>
						<div field="partyName" allowSort="true" width=""
							headerAlign="center" dictTypeId="">抵质押人名称</div>
						<div field="sortType" allowSort="true" width="" headerAlign="center"
							dictTypeId="XD_YPZL01">抵质押物类型</div>
						<div field="suretyNo" allowSort="true" width="" headerAlign="center"
							dictTypeId="">抵质押物编号</div>
						<div field="assessValue" allowSort="true" width="" headerAlign="center"
							>评估价值</div>
						<div field="mortgageValue" allowSort="true" width="" headerAlign="center"
							dictTypeId="">权利价值</div>
						<div field="totalAmt" allowSort="true" width="" headerAlign="center"
							dictTypeId="">已担保金额</div>
						<div field="cardRegDate" allowSort="true" width="" headerAlign="center"
							dictTypeId="">登记生效日期</div>
						<div field="regDueDate" allowSort="true" width="" headerAlign="center"
							dictTypeId="">登记到期日期</div>
						<div field="mortgageStatus" allowSort="true" width="" headerAlign="center">出入库状态</div>
						<div field="saveOrg" allowSort="true" width="" headerAlign="center"
							dictTypeId="org">凭证保管地</div>
						<div field="userNum" allowSort="true" width="" headerAlign="center"
							dictTypeId="user">入库经办人</div>
						<div field="mansNum" allowSort="true" width="" headerAlign="center"
							dictTypeId="user">管户经理</div>
					</div>
				</div>
			</center>
		</div>
	</div>
	<script type="text/javascript">
		var rukuStatus = [{ id: '03', text: '未入库' }, { id: '09', text: '已入库'},{ id: '04', text: '已出库'}];
		nui.parse();
		git.mask();
		var form = new nui.Form("#form1");
		var grid = nui.get("datagrid1");
		function queryInit() {
			//校验
			form.validate();
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
	                        btnEdit.setValue(data.orgid);
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
				e.data[i]['suretyPartyName']='<a href="#" onclick="toGoCustDetail(\''+ e.data[i].suretyPartyId+ '\');">'+e.data[i]['suretyPartyName']+'</a>';
				
				if(nui.encode(e.data[0].suretyNo)=='null'){}
				else{
					e.data[i]['suretyNo']='<a href="#" onclick="showCollInfo(\''+ e.data[i].suretyNo+ '\');">'+e.data[i]['suretyNo']+'</a>';
				}
				
			}
		});
		
		function printInStorage(){
		var row = grid.getSelected();
		if(row){  
		var json = nui.encode({"suretyKeyId":row.SURETY_KEY_ID});
			if(row.mortgageStatus=="已入库"){
				$.ajax({
			        url: "com.bos.grt.grtprint.queryPrintInfo.printInStore.biz.ext",
			        type: 'POST',
			        data: json,
			        contentType:'text/json',
			        cache: false,
			        success: function (mydata) {
			        	if(mydata.swfPath){
			        		nui.open({
								url:nui.context +"/biz/biz_report/contract_view.jsp?filePath="+mydata.swfPath,
								title: "入库信息预览", width: 1000, height: 600,
					            onload: function () {
					            },
					            ondestroy: function (action) {
					                  grid.reload();
					            }
					
							});
			        	}
			        	if(mydata.msg){
			        		alert(mydata.msg);
			        	}
			        },
			        error: function (jqXHR, textStatus, errorThrown) {
			            alert(jqXHR.responseText);
			            git.unmask();
			        }
			     });	
			}else{
				alert("请选择已入库的数据打印!");
			}
			
		}else{
			alert("请选中需要打印入库的的信息");
		}
	}
	
	
	function printOutStorage(){
		var row = grid.getSelected();
		if(row){  
		var json = nui.encode({"suretyKeyId":row.SURETY_KEY_ID});
			if(row.mortgageStatus=="已出库"){
				$.ajax({
			        url: "com.bos.grt.grtprint.queryPrintInfo.printOutStore.biz.ext",
			        type: 'POST',
			        data: json,
			        contentType:'text/json',
			        cache: false,
			        success: function (mydata) {
			        	if(mydata.swfPath){
			        		nui.open({
								url:nui.context +"/biz/biz_report/contract_view.jsp?filePath="+mydata.swfPath,
								title: "出库信息预览", width: 1000, height: 600,
					            onload: function () {
					            },
					            ondestroy: function (action) {
					                  grid.reload();
					            }
					
							});
			        	}
			        	if(mydata.msg){
			        		alert(mydata.msg);
			        	}
			        },
			        error: function (jqXHR, textStatus, errorThrown) {
			            alert(jqXHR.responseText);
			            git.unmask();
			        }
			     });
			}else{
				alert("请选择已出库的数据打印!");
			}
				
		}else{
			alert("请选中需要打印出库的的信息");
		}
	}	
	function dc(){
				var ifrm = document.getElementById("exportFrame");
				git.mask();
		 var o = form.getData();//逻辑流必须返回total
  		 
	
		 	 	var json = nui.encode(o);
		 
 		$.ajax({
	            url: "com.bos.csm.pub.ibatis.dzyDownloadEXCEL.biz.ext",
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
		
		
		function showCollInfo(v){
		 	var rows = grid.getSelecteds();
		    var row = grid.getSelected();
		    if(row){
			    var cltNo = v;
				var url = "http://"+"<%=ipStr%>"+":"+"<%=portStr%>"+"/default/com.bob.bcms.collateralmgr.ViewCollFlowForCredit.flow?creditFlag=1&orgId=<%=((UserObject)session.getAttribute("userObject")).getUserOrgId()%>&userId=<%=((UserObject)session.getAttribute("userObject")).getUserId()%>&cltNo="+cltNo+"&sceneCode=1";			
		    	window.open(url);
				return;
		    }
		}
	</script>
</body>
</html>