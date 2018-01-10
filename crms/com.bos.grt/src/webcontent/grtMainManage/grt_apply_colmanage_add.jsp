<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): 钟辉
  - Date: 2015-07-03 
-->
<head>
	<%@include file="/common/nui/common.jsp" %>
	<%
		String jspName ="/"+(String)request.getAttribute("jspName");
		System.out.println(jspName);
	%>
	<title>新增业务申请下的抵质押品</title>
</head>
<script type="text/javascript" src="<%=contextPath%>/grt/grtJS/grt.js"></script>
<body>
	<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;height:110%;" onbeforeactivechanged="tabChange">
		<div title="押品基本信息" id="basicTab" name="basicTab" style="width:90%;height:auto;" >
			<div class="nui-dynpanel" columns="1" id="table2"  style="width:99.5%;">
				<fieldset>
					<div id="formInfo" style="width:100%;height:auto;overflow:hidden;">
						<input name="party.partyId" id="party.partyId" class="nui-hidden" />
						<input name="grtMortgageBasic.suretyId" id="grtMortgageBasic.suretyId" class="nui-hidden" />
						<input name="grtMortgageBasic.partyId" id="grtMortgageBasic.partyId" class="nui-hidden" />
						<div id="panel1" class="nui-dynpanel" columns="4">
							<label>抵质押物编号：</label>
							<input name="grtMortgageBasic.suretyNo" class="nui-textbox nui-form-input" id="grtMortgageBasic.suretyNo" enabled="false" />
										
							<label>抵质押人：</label>
							<input name="party.partyName" class="nui-textbox nui-form-input" id="party.partyName" allowinput="false" enabled="false"/>
							
							<label>抵质押类型：</label>
							<input name="grtMortgageBasic.collType" required="true" class="nui-dictcombobox nui-form-input"required="true" enabled="false"id="grtMortgageBasic.collType" onvaluechanged="changeCollType" dictTypeId="XD_YWDB0131" />
							
							<label>抵质押物类型：</label>
							<input name="grtMortgageBasic.sortType"class="nui-dictcombobox nui-form-input"  id="grtMortgageBasic.sortType"  dictTypeId="XD_YWDB02" enabled="false"/>
							
							<label>投保情况：</label>
							<input name="grtMortgageBasic.insuranceCondition" required="true"class="nui-dictcombobox nui-form-input"  id="grtMortgageBasic.sortType"  dictTypeId="XD_YWDB22"onvaluechanged="insuranceCondition" />
							
							<label>保险公司：</label>
							<input name="grtMortgageBasic.insuranceCompany"  class="nui-textbox nui-form-input"  id="grtMortgageBasic.insuranceCompany"  vtype="maxLength:100;"/>
							
							<label>保单号码：</label>
							<input name="grtMortgageBasic.insuranceNum"  class="nui-textbox nui-form-input"  id="grtMortgageBasic.insuranceNum" vtype="maxLength:80;" />
							
							<label>权利价值(元)：</label>
							<input name="grtMortgageBasic.mortgageValue" class="nui-textbox nui-form-input"  id="grtMortgageBasic.mortgageValue" vtype="float;maxLength:20;range:1"  dataType="currency"/>
							
							<a href="javascript:yingxiang()" >影像信息</a>
							
							<%-- <label>经办机构：</label>
							<input name="grtMortgageBasic.orgNum" enabled="false" class="nui-text nui-form-input" value="<%=com.bos.pub.GitUtil.getCurrentOrgCd()%>" dictTypeId="org" />
									
							<label>经办人：</label>
							<input name="grtMortgageBasic.userNum" enabled="false" class="nui-text nui-form-input" value="<%=com.bos.pub.GitUtil.getCurrentUserId()%>" dictTypeId="user"/>
							
							<label>创建日期：</label>
							<input name="grtMortgageBasic.createTime" class="nui-textbox nui-form-input" id="grtMortgageBasic.createTime" value="<%=com.bos.pub.GitUtil.getBusiDate() %>"  enabled="false" />
					
							<label>更新日期：</label>
							<input name="grtMortgageBasic.updateTime" class="nui-textbox nui-form-input"  id="grtMortgageBasic.updateTime" value="<%=com.bos.pub.GitUtil.getBusiDate()%>"  enabled="false" /> --%>
						</div>
					</div>
					<jsp:include page="<%=jspName %>"/>
				<div class="nui-toolbar" style="border-bottom:0;padding-right:20px;text-align:center;">
					<a class="nui-button" iconCls="icon-save" id="btnsave" onclick="save()">保存</a>
					<a class="nui-button" iconCls="icon-close" onclick="close()" id="btnClose">关闭</a>
				</div>
				</fieldset>
			</div>
		</div>
	</div>
		    
	<script type="text/javascript">
	 	nui.parse();
	 	
		var form = new nui.Form("#formInfo");
		//押品和业务关联表ID
	    var relationId="<%=request.getAttribute("relationId")%>";
		//主键ID
		var suretyId ="<%=request.getAttribute("suretyId")%>";
		//押品类型
		var collType ="<%=request.getAttribute("collType")%>";
		//押品分类
		var sortType="<%=request.getAttribute("sortType")%>";
		//编辑查看入口
		var view="<%=request.getAttribute("view") %>";
		//是否从押品管理进入的
		var isManage="<%=request.getAttribute("isManage") %>";
		
		if(sortType.substr(0,2)=="06"||sortType=="040100"||sortType=="040200"){//初始化方法
			init();
		}
		
		if (view=="1"||view=="2") {
			form.setEnabled(false);
			form1.setEnabled(false);
			nui.get("btnsave").hide();
		}
		basicInfoInit();//初始化押品主信息
		sortTypeInit();	//初始化押品明细
		function basicInfoInit(){
			var json=nui.encode({"suretyId":suretyId,"relationId":relationId});
			git.mask(); 
			$.ajax({
	        	url: "com.bos.grt.grtMainManage.grtApply.getApplyTbGrtGuarantybasic.biz.ext",
	        	type: 'POST',
	        	data: json,
	        	async: false,
	        	cache: false,
	        	contentType:'text/json',
	        	success: function (text) {
	        		var o=nui.decode(text);
					form.setData(o);
					if(o.grtMortgageBasic.collType=='00'){//既可抵押也可质押的押品
						nui.get("grtMortgageBasic.collType").setEnabled(true);
						nui.get("grtMortgageBasic.collType").setData(getDictData("XD_YWDB0131","str","01,02"));
					}
	        		git.unmask();
	        	},
	        	error: function (jqXHR, textStatus, errorThrown) {
	            	nui.alert(jqXHR.responseText);
	        	}
			});
			addTab2();
			addTab3();
		}
		
		function sortTypeInit(){
			//查看   屏蔽按钮和可编辑状态
			var json=nui.encode({"item":{"suretyId":suretyId,"sortType":sortType},"partyName":nui.get("party.partyName").getValue()});
			git.mask();
			$.ajax({
	        	url: "com.bos.grt.grtManage.mortgageCURD.getMortgage.biz.ext",
	        	type: 'POST',
	        	data: json,
	        	cache: false,
	        	contentType:'text/json',
	        	success: function (text) {
	        		var o=nui.decode(text);
					form1.setData(o);
	        		git.unmask();
	        	},
	        	error: function (jqXHR, textStatus, errorThrown) {
	            	nui.alert(jqXHR.responseText);
	        	}
			});
		}

		
		/**
		 * 保存
		 */
		function save() {
			form1.validate();
			form.validate();
			if (form1.isValid() == false||form.isValid() == false) {
				nui.alert("请将信息填写完整");
				return;
			}
			//押品管理入口进入的
			var o=form1.getData();//押品子信息
			var o1=form.getData();//押品主信息，从主页面获取
			o.item.suretyId=suretyId;
			o.item.sortType=sortType;
			o.grtMortgageBasic=o1.grtMortgageBasic;
			var json=nui.encode(o);
			add(json);
		}
		function add(json){
			git.mask();
			 $.ajax({
		        url: "com.bos.grt.grtManage.mortgageCURD.saveOrUpdateMortgage.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        contentType:'text/json',
		        success: function (text) {
		        	nui.alert(text.msg);
		        	nui.get("suretyKeyId").setValue(text.item.suretyKeyId);
		        	nui.get("grtMortgageBasic.suretyNo").setValue(text.grtMortgageBasic.suretyNo);
		        	git.unmask();
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		            nui.alert(jqXHR.responseText);
		        }
			}); 
		}
		function close(){
			CloseWindow("ok");
		}
		
		/**
		 * 登记权证信息页签
		 */
		function addTab4() {
			var tabs = nui.get("tabs1");
			var tab = {title: "登记权证信息",name: "regInfo",url: nui.context+"/grt/manage/TbGrtRegcardinfo/TbGrtRegcardinfo_info.jsp"};
			tab = tabs.addTab(tab);            
		}
		
		/**
		 * 合同信息页签
		 */
		function addTab5() {
			var tabs = nui.get("tabs1");
			var tab = {title: "关联合同信息",name: "loanInfo",url: nui.context+"/grt/manage/loanInfo/loan_list.jsp"};
			tab = tabs.addTab(tab);            
		}
		
		//共有人
		function addTab3() {
			var tabs = nui.get("tabs1");
			var tab = {title: "共有人信息",name: "common",url: nui.context+"/grt/manage/partOwner/partyOwner_list.jsp"};
			tab = tabs.addTab(tab);            
		}
		
		//评估信息
		function addTab2() {
			var tabs = nui.get("tabs1");
			var tab = {title: "评估信息",name: "assess",url: nui.context+"/grt/manage/assess/assessList.jsp"};
			tab = tabs.addTab(tab);            
		}
		
		/**
		 * 初始化
		 */
	 	/**
	 	 * 切换tab时
	 	 */
	 	
		function tabChange(e) {
			var partyId=nui.get("party.partyId").getValue();
			if(e.name=="common"||e.name=="regInfo"||e.name=="assess"||e.name=="loanInfo"){
				var tabs = nui.get("tabs1");
				var tabName = tabs.getTab(e.name);
				var tabUrl = tabName.url.split("?")[0];
				tabUrl = tabUrl + "?suretyId=" + suretyId+ "&sortType=" + sortType + "&collType=" + collType+"&view="+view+"&partyId="+partyId;
				tabName.url = tabUrl;
				tabs.reloadTab(tabName);//更新tab标签页
			}
		}


		if(isManage=='1'&&view=='1'){
	 		 addTab4();
	 		 addTab5();
	 	}
	 	
	 	function yingxiang(){
	 		//查询客户num
			var json = nui.encode({"suretyId":suretyId});
		    $.ajax({
		        url: "com.bos.grt.grtManage.mortgageCURD.expendMortgage.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        contentType:'text/json',
		        cache: false,
		        success: function (mydata) {
		        	o = nui.decode(mydata);
		        	
		        	var suretyNo=o.mortgageBasic.suretyNo;
			 		if(suretyNo==null){
			 			alert("请先保存押品相关信息!");
			 			return;
			 		}
		        	var collType=o.mortgageBasic.collType;
		        	
		        	var flowModuleType="";
		        	if(collType == "01") {				//抵押   13
				    	flowModuleType = "13";
				    }else if(collType == "02") {		//质押14
				    	flowModuleType = "14";
				    }
		        	//跳往信息平台页面
		        	var ecmurl =nui.context + '/pub/imagePlatform/item_tree.jsp?businessNumber='+o.mortgageBasic.suretyNo+'&csmNum='+o.tbCsmParty.partyNum+'&partyTypeCd=01&ismove='+o.mortgageBasic.ifDataMove+"&view=1&flowModuleType="+flowModuleType;
					if(view=="0"){
						var ecmurl =nui.context + '/pub/imagePlatform/item_tree.jsp?businessNumber='+o.mortgageBasic.suretyNo+'&csmNum='+o.tbCsmParty.partyNum+'&partyTypeCd=01&ismove='+o.mortgageBasic.ifDataMove+"&view=2&flowModuleType="+flowModuleType;
					}
		        	nui.open({
		                url:ecmurl,
		                title: "影像信息", 
		                width: 1200,
		        		height: 600,
		        		state:"max",
		                allowResize:true,
		        		showMaxButton: true,
		                onload: function () {
		                },
		                ondestroy: function (action) {
		                }
	            	})
		        }
					
			})
			return;
	 	}
	 	//投保情况
		function insuranceCondition(e){
			if(e.value=="1"){//已投保
				nui.get("grtMortgageBasic.insuranceCompany").setRequired(true);			
				nui.get("grtMortgageBasic.insuranceNum").setRequired(true);			
			}else{//未投保
				nui.get("grtMortgageBasic.insuranceCompany").setRequired(false);			
				nui.get("grtMortgageBasic.insuranceNum").setRequired(false);
			}
		}
		function changeCollType(){
			if(sortType.substr(0,2)=="06"){
			if(nui.get("grtMortgageBasic.collType").getValue()=="02"){//质押
				form1=new nui.Form("#form2");
				$("#form1").css("display","none");
				$("#form2").css("display","block");
			}else{
				form1=new nui.Form("#form1");
				$("#form2").css("display","none");
				$("#form1").css("display","block");
			}
			}
		}
	
	</script>
</body>
</html>
