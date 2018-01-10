<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 陈川
  - Date: 2016-7-20 8:26:27
  - Description:
-->
<head>
<title>名单制管理</title>
<%@include file="/common/nui/common.jsp"%>
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
</head>
<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;height:115%;">
		<div title="黑名单客户管理">
				<form id="form2"class="nui-form" style="width:99.5%;height:auto;overflow:hidden;" method="post" action="com.primeton.example.excel.empManager.flow?_eosFlowAction=exportFile">
					<input id="entity" name="item/entity" value="com.bos.lst.lst.TbLstStock" class="nui-hidden" />
					<input name="importCd" value='102' id="importCd" class="nui-hidden" />
				</form>
				<div id="form1" class="nui-form"style="width: 99.5%; height: auto; overflow: hidden;">
					<input name="sqlName" class="nui-hidden nui-form-input" value="com.bos.lst.lst.blackList" />
					<div class="nui-dynpanel" columns="6">
					<!-- 
						<label>机构名称：</label> 
						<input name="item.recordStatus" class="nui-dictcombobox nui-form-input" dictTypeId="XD_KHCD3202"/>
					 -->
					 	<label class="nui-form-label">机构名称：</label>
						<input id="item.orgId" name="item.orgId" text="<%=((UserObject)session.getAttribute("userObject")).getUserOrgName()%>" value="<%=((UserObject)session.getAttribute("userObject")).getUserOrgId()%>" allowInput="false" class="nui-buttonEdit" required="true" onbuttonclick="selectOrg"/>
						<label>客户名称：</label> 
						<input name="item.partyName" class="nui-textbox nui-form-input" />
						<!-- 
						<label>营业执照：</label> 
						<input name="item.businessType" class="nui-textbox nui-form-input" /> 
						<label>组织机构代码：</label> 
						<input name="item.mainCustManager" class="nui-textbox nui-form-input" />
						 -->
						<label>证件类型：</label> 
						<input name="item.certType" id="item.certType" class="nui-dictcombobox nui-form-input" dictTypeId="CDKH0002"/>
						<label>证件号码：</label> 
						<input name="item.certNum" id="item.certNum" class="nui-textbox nui-form-input" />
						<!-- 
						<label>中征码：</label> 
						
						<input name="item.mainCustManager" class="nui-textbox nui-form-input" vtype="int;minLength:16;maxLength:16"/>
						 -->
						<label>监控名单类型：</label> 
						<input name="listStatus" class="nui-textbox nui-form-input" value="黑名单" allowInput="false" enabled="false"/>
						
						
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
					</div>
					</div>
				
			<div class="nui-toolbar" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px">
					    <a class="nui-button" iconCls="icon-zoomin" onclick="view()">查看</a>
					<% 
						manage = "";
						
						if (null != roles && roles.length > 0) {
							for (int i=0; i<roles.length; i++) {
									DataObject role = roles[i];
									if ("R2002".equals(role.get("roleid"))||"R1010".equals(role.get("roleid"))){//信息维护员,支行行长
										manage="true";
									}else{
										continue;
									}
								}	        			
						}
						if(manage.equals("true")){
					%>
						<a class="nui-button" iconCls="icon-upload" onclick="impExcel()">上传</a>
					<% 
						}else{
					%>
						<a class="nui-button" iconCls="icon-add"  onclick="remove()">转出</a>
					<%
						}
					 %>
					</div>
				<div id="datagrid1" class="nui-datagrid"
					style="width:99.5%;height:auto;"sortMode="client"
					url="com.bos.csm.pub.ibatis.getItem.biz.ext" dataField="items"
					allowResize="false" showReloadButton="false" allowAlternating="true" 
					sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" onselectionchanged=""sortMode="client">
					<div property="columns">
						<div type="checkcolumn">选择</div>
						<div field="orgNum" allowSort="true" headerAlign="center" autoEscape="false" dictTypeId="org">机构名称</div>
						<div field="partyName" allowSort="true" headerAlign="center" >客户名称</div>
						<div field=certType allowSort="true" headerAlign="center" dictTypeId="CDKH0002">证件类型</div>
						<div field="certNum" allowSort="true" headerAlign="center" >证件号码</div>
						<!-- 
						<div field="middleCode" allowSort="true" headerAlign="center">中征码</div>
						 -->
						<div field="clsResult" allowSort="true" headerAlign="center" dictTypeId="XD_FLCD0001">分类</div>
						<div field="listStatus" allowSort="true" headerAlign="center" dictTypeId="XD_MDCD0001">监控名单类型</div>
						<div field="pdYj" allowSort="true" headerAlign="center" >判断依据</div>
						<div field="createDate" allowSort="true" headerAlign="center" dateformat="yyyy-MM-dd">加入日期</div>
						<div field="operUserid" allowSort="true"  headerAlign="center" dictTypeId="user">经办人</div>
					</div>
				</div>
				<!-- 
				<fieldset>
					<legend>
					  <span>历史记录</span>
					</legend>
					<div id="datagrid2" class="nui-datagrid"
						style="width: 99.5%; height: 200"
						url="com.bos.csm.pub.ibatis.getItem.biz.ext" dataField="items"
						allowResize="false" showReloadButton="false"
						sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10"
						sortMode="client">
						<div property="columns">
							<div field="changeDate" allowSort="true" width="" headerAlign="center"
								autoEscape="false">变更日期</div>
							<div field="changeBeforStatus" allowSort="true" width="" headerAlign="center"
								dictTypeId="XD_MDCD0001">变更前状态</div>
							<div field="changeAfterStatus" allowSort="true" width="20%"
								headerAlign="center" dictTypeId="XD_MDCD0001">变更后状态</div>
							<div field="changeReson" allowSort="true" width="" headerAlign="center" >变更原因</div>
							<div field="operUserid" allowSort="true" width="" headerAlign="center"
								dictTypeId="user">经办人</div>
						</div>
					</div>
				</fieldset>
				 -->
		</div>
	</div>
	<script type="text/javascript">
		nui.parse();
		git.mask();
		var form = new nui.Form("#form1");
		var grid = nui.get("datagrid1");
		
		function queryInit() {
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
		function view(){
			var row = grid.getSelected();
			if(row){
				nui.open({
					url : nui.context + "/pub/lst/lst_info_view.jsp?partyId="+row.partyId,
					showMaxButton : true,
					title : "查看",
					width : 800,
					height : 400,
					onload : function(e) {
						var iframe = this.getIFrameEl();
						var text = iframe.contentWindow.document.body.innerText;
					},
					ondestroy : function(action) {
						queryInit();
					}
				});
			}else{
				nui.alert("请选择查看记录");
			}
		}

//		function selectPo(){
//			var row = grid.getSelected();
//			grid2.load({"item":row,"sqlName":"com.bos.lst.lst.changeList"});
//		}
		
		function impExcel(){
			//打开上传面
			nui.open({
				url : nui.context + "/com.bos.lst.lstManage.flow",
				showMaxButton : true,
				title : "上传文件",
				width : 800,
				height : 300,
				onload : function(e) {
					var iframe = this.getIFrameEl();
					var text = iframe.contentWindow.document.body.innerText;
				},
				ondestroy : function(action) {
					queryInit();
				}
			});
		}
		//将黑名单客户转出
		function remove(){
			var row = grid.getSelected();
			if(!row){
				return alert("请选择一条记录");
			}
	        //判断批复下是在途的调整
		  	var json1 = {"partyId":row.partyId};
		   	msg = exeRule("RCSM_1004","1",json1);
		   	if(null != msg && '' != msg){
		   	   nui.alert(msg);
		   	   return;//?????
		   }
		   //alert(nui.get(item.));
	        var json = nui.encode({"partyId":row.partyId});
	         $.ajax({
		            url: "com.bos.pub.lst.lst.adjustInfo.biz.ext",
		            type: 'POST',
		            data: json,
		            cache: false,
		            contentType:'text/json',
		            success: function (text) {
		            	git.unmask("form1");
		            	if(text.msg){
		            		nui.alert(text.msg);
		            	} else {
		                    if(text.response.bizId!=""&&null!=text.response.bizId){    	
			            		var url=nui.context + "/pub/lst/lst_black_confirm_tree.jsp?bizId="+ text.response.bizId+"&processInstId="+text.response.processInstId+"&qote=0&isSrc=2";            			
			            		nui.open({
						            url:url,
						            showMaxButton: true,
						            title: "审批意见",
						            state:"max",
						            onload: function(e){
						            	var iframe = this.getIFrameEl();
						            	var text = iframe.contentWindow.document.body.innerText;
						            	//alert(text);
						            },
						            ondestroy: function (action) {
						            	CloseWindow('ok');
						            }
						        });
			               }else{
			            	    nui.alert("未知异常!");
			               }
		            	}
		            },
		            error: function (jqXHR, textStatus, errorThrown) {
		    			git.unmask("form1");
		                nui.alert(jqXHR.responseText);
		            }
		        });
		}
		
		//弹出审批意见页面
		function openSubmitView(response){
			var url = nui.context + "/csm/company/groupCompany_tree.jsp?bizId="
						+ response.bizId + "&qote=2&processInstId="+response.processInstId+"&isSrc=2";
			nui.open({
		               url: url,
		               title: "提交审批", 
		               width: 550, 
		               height: 260,
		               state:"max",
		               onload: function () {
		               },
		               ondestroy: function (action) {
		               	  queryInit();
		               }
		           });
		
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
		function exportEmp() {
			var frm = document.getElementById("form2");
			frm.action = "com.primeton.example.excel.empManager.flow?_eosFlowAction=exportFile";
			frm.submit();
		}
	</script>
</body>
</html>