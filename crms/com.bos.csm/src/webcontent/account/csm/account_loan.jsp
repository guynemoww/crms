<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 甘泉
  - Date: 2015-6-3 8:26:27
  - Description:
-->
<head>
<title>小贷中心客户查询</title>
<%@include file="/common/nui/common.jsp"%>
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
</head>
<body>
	<iframe name="exportFrame" id="exportFrame" src="" style="display:none;"></iframe>
	<div id="tabs1" class="nui-tabs" activeIndex="0"
		style="width: 100%; height: auto;">
		<div title="小贷中心客户查询">
			<center>
				<div id="form1" class="nui-form"
					style="width: 99.5%; height: auto; overflow: hidden;">
					<input name="sqlName" class="nui-hidden nui-form-input" value="com.bos.account.csm.loan" />
					<div class="nui-dynpanel" columns="8">
						
						<label class="nui-form-label">机构名称：</label>
						<input id="item.orgId" name="item.orgId" text="<%=((UserObject)session.getAttribute("userObject")).getUserOrgName()%>" value="<%=((UserObject)session.getAttribute("userObject")).getUserOrgId()%>" allowInput="false" required="true" class="nui-buttonEdit" onbuttonclick="selectOrg"/>
						<label>客户名称：</label>
						<input name="item.partyName" class="nui-textbox nui-form-input"/>
						<label>证件类型：</label>
						<input id="item.certType" name="item.certType" class="nui-dictcombobox nui-form-input"  dictTypeId="CDKH0002"  allowInput="false" />
						<label>证件号码：</label>			
						<input id="item.certNum"  class="nui-textbox nui-form-input" name="item.certNum" onvalidation="onValidateCertificateCode"  />
						<label>中征码：</label>
						<input id="item.middelCode" name="item.middelCode" required="false" class="nui-textbox nui-form-input" vtype="int;minLength:16;maxLength:16"/>
						<label>是否农户：</label>
						<input id="item.isFarmer" name="item.isFarmer" required="false" class="nui-dictcombobox nui-form-input" dictTypeId="YesOrNo" />	
						<label>行业类别：</label>
						<input id="item.industry" name="item.industry" required="false" allowInput="false"  
						class="nui-buttonEdit nui-form-input" onbuttonclick="selectIndustryType" dictTypeId="CDKH0095"/>
			
			
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
						<a class="nui-button" iconCls="icon-download" onclick="dc()">导出EXCEL</a>	
					</div>
				</div>
				<div id="datagrid1" class="nui-datagrid"
					style="width: 99.5%; height: auto"
					url="com.bos.csm.pub.ibatis.getItem.biz.ext" dataField="items"
					allowResize="true" showReloadButton="false"
					sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" onselectionchanged="" sortMode="client"  allowAlternating="true">
					<div property="columns">
					    <div type="indexcolumn">序号</div>
						<div field="orgname" allowSort="true" width="" headerAlign="center"
							autoEscape="false">机构名称</div>
						<div field="partyName" allowSort="true" width="" headerAlign="center"
							dictTypeId="product">客户名称</div>
						<div field="certType" allowSort="true" width=""
							headerAlign="center" dictTypeId="CDKH0002">证件类型</div>
						<div field="certNum" allowSort="true" width="" headerAlign="center"
							dictTypeId="">证件号码</div>
						<div field="middelCode" allowSort="true" width="" headerAlign="center"
							dictTypeId="">中征码</div>
						<div field="industry" allowSort="true" width="" headerAlign="center"
							dictTypeId="CDKH0095">行业类别</div>
						<div field="isFarmer" allowSort="true" width="" headerAlign="center"
							dictTypeId="YesOrNo">是否农户</div>
						<div field="generalAdjustRatingCd" allowSort="true" width="" headerAlign="center"
							dictTypeId="">信用等级</div>
						<div field="userNum" allowSort="true" width="" headerAlign="center"
							dictTypeId="user">客户经理</div>
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
	                        btnEdit.setValue(data.orgid);
	                        btnEdit.setText(data.orgname);
	                    }
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
	    
	    //登记注册类型
		function selectIndustryType(e) {
			var btnEdit = this;
			nui.open({
				url : nui.context + "/pub/dict/code_tree.jsp?dicttypeid=CDKH0095",
				title : "选择字典项",
				width : 800,
				height : 450,
				ondestroy : function(action) {
					if (action == "ok") {
						var iframe = this.getIFrameEl();
						var data = iframe.contentWindow.currentNode;
						data = nui.clone(data);
						if (data) {
							btnEdit.setValue(data.dictid);
							btnEdit.setText(data.dictname);
						}
					}
					if (action == "clear") { //清空选择的内容
						btnEdit.setValue("");
						btnEdit.setText("");
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
			}
		});
		
			function dc(){
				git.mask();
				var ifrm = document.getElementById("exportFrame");
				var o = form.getData();//逻辑流必须返回total
				var json = nui.encode(o);
		 
 			$.ajax({
	            url: "com.bos.csm.pub.ibatis.downloadEXCELXDZX.biz.ext",
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