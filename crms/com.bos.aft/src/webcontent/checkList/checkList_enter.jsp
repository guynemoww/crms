<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): xiaoxia
  - Date: 2015-07-20
  - Description:
-->
<head>
<title>授信后检查名单</title>
<%@include file="/common/nui/common.jsp"%>
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
<%@page import="com.eos.*"%>
</head>
<body>
	<iframe name="exportFrame" id="exportFrame" src="" style="display:none;"></iframe>
<center>
<fieldset>
<legend> <span>本期需要进行贷后检查客户名单</span> </legend>
	<div id="form" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px"  class="nui-form">

		<input id="sqlName" class="nui-hidden nui-form-input nui-textbox"  required="false"  name="sqlName"   value="com.bos.aft.checkList.findCheckList" />
		
		<div class="nui-dynpanel" columns="4">
		
			<!-- <label class="nui-form-label">所属机构</label>
			<input id="map.orgid" name="map.orgid"  allowInput="false" class="nui-buttonEdit" onbuttonclick="selectOrg"/> -->
			
			<label class="nui-form-label">所属机构：</label>
			<input id="map.orgcode" name="map.orgcode" text="<%=((UserObject)session.getAttribute("userObject")).getUserOrgName()%>" value="<%=((UserObject)session.getAttribute("userObject")).getAttributes().get("orgcode")%>" allowInput="false"  class="nui-buttonEdit" onbuttonclick="selectOrg"/>
			
			<label class="nui-form-label">客户名称：</label>
			<input name="map.partyName" id="map.partyName" required="false" class="nui-textbox nui-form-input"  />
			
			<label class="nui-form-label">证件类型：</label>
			<input id="map.certType" name="map.certType"  class="nui-dictcombobox nui-form-input"   
				dictTypeId="CDKH0002"  allowInput="false" />
			
			<label class="nui-form-label">证件号码：</label>
			<input id="map.certNum" name="map.certNum" required="false" class="nui-textbox nui-form-input"  onblur="checkCertCode"/> 
			
				<label class="nui-form-label">检查类型：</label>
			<input id="map.checkType" name="map.checkType"  class="nui-dictcombobox nui-form-input" dictTypeId="XD_JCLX0001" />  
			
			<label class="nui-form-label">检查截止日期：</label>
			<div>
				<input id="map.stDate" name="map.stDate"  style="width:100px;"  class="nui-datepicker nui-form-input" />
				<!--  <input id="map.enDate" name="map.enDate"  style="width:100px;"  class="nui-datepicker nui-form-input" />-->
			</div>
				
				<label class="nui-form-label">完成情况：</label>
			<input id="map.remindStatus" name="map.remindStatus"  class="nui-dictcombobox nui-form-input"  dictTypeId="XD_0002"   />
			
		 
	 
			<label>经办人：</label> 
						
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
				<input id="map.userNum"  class="nui-buttonEdit" name="map.userNum" text="<%=((UserObject)session.getAttribute("userObject")).getUserName()%>"  value="<%=((UserObject)session.getAttribute("userObject")).getUserId()%>" readonly> />
			<% 
				}else{
			%>
				<input id="map.userNum" name="map.userNum" required="false" allowInput="false" class="nui-buttonEdit" onbuttonclick="selectCustManegers"/>
			<%}%>
			
			
		</div>
			
		
	<!--  	<div class="nui-dynpanel" columns="4" id="div3">
			<label class="nui-form-label">检查类型：</label>
			<input id="map.checkType2" name="map.checkType2"  class="nui-dictcombobox nui-form-input" dictTypeId="XD_JCLX0002" />
		</div> 
	 -->
		
		
		<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;" borderStyle="border:0;">
		 	<a class="nui-button" iconCls="icon-search" onclick="query()">查询</a>
			<a class="nui-button" iconCls="icon-reset" onclick="reset()">重置</a>
		  	<a class="nui-button" iconCls="icon-download" onclick="dc()">导出EXCEL</a>					
			
		</div>
		</div>
 	
	<div id="grid" class="nui-datagrid" sortMode="client"
		url="com.bos.aft.checkList.findCheckList.biz.ext" dataField="items"
	    allowAlternating="true" multiSelect="false" showEmptyText="true" allowResize="true"
	    emptyText="没有查到数据" showReloadButton="false"
	    onrowdblclick="" allowCellEdit="true" allowCellSelect="true"
	    sizeList="[10,20,50,100]" pageSize="10" sortMode="client">
	    <div property="columns">
	        <!-- <div type="checkcolumn">选择</div> -->
	        <div type="indexcolumn" width="50px;">序号</div>
	        <div name="ORG_NUM" field="ORG_NUM" headerAlign="center" align="center" dictTypeId="org" allowSort="true">所属机构</div>
			<div name="PARTY_NAME" field="PARTY_NAME" headerAlign="center" align="center"allowSort="true">客户名称</div>
			<div name="CERT_TYPE" field="CERT_TYPE" headerAlign="center" align="center" dictTypeId="CDKH0002"allowSort="true">证件类型</div>
	        <div name="CERT_NUM" field="CERT_NUM" align="center" headerAlign="center" allowSort="true">证件号码</div>  
	        <div name="PARTY_TYPE_CD" field="PARTY_TYPE_CD" headerAlign="center" align="center" dictTypeId="XD_KHCD1001"allowSort="true">客户类型</div>
	       <!--  <div name="RE_NUM" field="RE_NUM" headerAlign="center" align="center">借据/合同编号</div> --> 
	        <div name="WARNING_LEVEL_CD" field="WARNING_LEVEL_CD" headerAlign="center" align="center" dictTypeId="XD_YJJB0001"allowSort="true">预警级别</div>
	        <div name="REMIND" field="REMIND" headerAlign="center" align="center" dictTypeId="XD_DHCD0015"allowSort="true">检查类型</div>
	        <div name="LAST_DATE" field="LAST_DATE" headerAlign="center" align="center" dateFormat="yyyy-MM-dd"allowSort="true">检查截至日期</div>
	        <div name="REMIND_STATUS" field="REMIND_STATUS" headerAlign="center" align="center" dictTypeId="XD_0002"  allowSort="true">完成情况</div>
	        <div name="USER_NUM" field="USER_NUM" headerAlign="center" align="center" dictTypeId="user">经办人</div>
	        
	    </div>
	</div>
	
</fieldset>
</center>

<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var grid = nui.get("grid");  //客户列表
	
//	$("#div1").css("display","none");
//	$("#div2").css("display","none");
//	$("#div3").css("display","none");
	query();
	function query(){
		var o = form.getData();
 		grid.load(o);
		//alert(o.map.partyName);
	}
	
  
    
    function query1(){
	       var json = nui.encode({});
			$.ajax({
	            url: "com.bos.aft.aft_manage.queryRateListInit.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (mydata) {
	            	orgDegree = mydata.orgDegree;
	            	//alert(orgDegree);
	            	
	            	
	           // 		$("#div1").css("display","block");
	            //		$("#div2").css("display","none");
	            //		$("#div3").css("display","block");
	            		//grid.hideColumn(grid.getColumn(7)); 
	            //		grid.hideColumn(grid.getColumn("WARNING_LEVEL_CD")); 
	            	
	            		$("#div1").css("display","block");
	            		$("#div2").css("display","block");
  	            //		grid.hideColumn(grid.getColumn("USER_NUM")); 
	            	
	            	
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
                        btnEdit.setValue(data.orgcode);
                        btnEdit.setText(data.orgname);
                    }
                }
            }
        });      
	}
    
    //重置
	function reset(){
		form.reset();
	}
	
	// 经办人
	function selectCustManegers(e) {
		var newOrgNum;
		newOrgNum = nui.get("map.orgcode").getValue();
		//alert(newOrgNum);
		if (newOrgNum == "") {
			alert("请先选择机构");
			return;
		}else {
			var orgIds;
			orgIds = nui.get("map.orgcode").getValue();
		//	alert(orgIds);
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

				var  o = form.getData(); 
				 var orgcode=o.map.orgcode;
				//alert(orgcode);
				 if(orgcode=="08001"){
				 alert("下载时机构不能选总行！");
				 git.unmask();
				 
				 return;
				 }
		 	 	var json = nui.encode(o);
				git.mask();
 		 
 		$.ajax({
	            url: "com.bos.csm.pub.ibatis.DHJCDownloadEXCEL.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            	git.unmask("form");
	            	if(text.msg){
					git.unmask();
           ifrm.src=nui.context +"/pub/io/file/download.jsp?deleteFile=true";
	            	
	            	}else{
	            	git.unmask();
	            	 nui.alert("下载数据有误！");
	            	}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	       			git.unmask("form");
	                nui.alert(jqXHR.responseText);
	            }
		});
	
		}
	//onvaluechanged="onselectSettle"
	/* function onselectSettle(e){
		var checkType= nui.get("map.checkType").getValue();
		if(checkType=="2"){
			grid.hideColumn(grid.getColumn("RE_NUM")); 
		}else{
			grid.showColumn(grid.getColumn("RE_NUM")); 
		}
	} */  
		
</script>
</body>
</html>