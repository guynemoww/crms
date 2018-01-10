<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): ZhuYongLun
  - Date: 2014-06-24 14:53:29
  - Description:
-->
<head>
	<%@include file="/common/nui/common.jsp" %>
	<title>编辑抵质押品(抵质押合同)</title>
</head>
<script type="text/javascript" src="<%=contextPath%>/grt/grtJS/grt.js"></script>
<body>
	<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;height:94%;" onbeforeactivechanged="tabChange">
		<div title="押品概况信息" id="basicTab" name="basicTab" style="width:90%;height:550px">
			<div id="form1" style="width:100%;height:auto;overflow:hidden;">
				<input name="tbGrtGuarantybasic.parentSortType" required="true" class="nui-hidden" vtype="maxLength:30" id="tbGrtGuarantybasic.parentSortType" enabled="false" />
				<input name="tbGrtGuarantybasic.suretyId" required="true" class="nui-hidden" vtype="maxLength:50" id="tbGrtGuarantybasic.suretyId" enabled="false" />
				<input name="tbConSubGrtRelation.relationId" required="true" class="nui-hidden" vtype="maxLength:50" id="tbConSubGrtRelation.relationId" enabled="false" value="<%=request.getParameter("relationId") %>"/>
				<input name="tbGrtGuarantybasic.assessorgPartyId" required="true" class="nui-hidden" vtype="maxLength:50" id="tbGrtGuarantybasic.assessorgPartyId" enabled="false" />
				<div id="panel1" class="nui-dynpanel" columns="4">
					<label>抵质押编号：</label>
					<input name="tbGrtGuarantybasic.suretyNum" required="true" class="nui-textbox nui-form-input" vtype="maxLength:60" id="suretyNum" enabled="false" />
					
					<label>抵质押人名称：</label>
					<input name="tbGrtGuarantybasic.partyName" required="false" class="nui-buttonEdit nui-form-input" vtype="maxLength:300" id="partyName" onbuttonclick="chooiseParty" enabled="false" allowinput="false"/>
					
					<label>抵质押人客户编号：</label>
					<input name="tbGrtGuarantybasic.partyNum" required="false" class="nui-textbox nui-form-input"vtype="maxLength:20" id="partyNum" enabled="false" />
					
					<label>抵质押人证件类型：</label>
					<input name="tbGrtGuarantybasic.certificateTypeCd" required="false" class="nui-dictcombobox nui-form-input" id="certificateTypeCd" dictTypeId="CDKH0002" enabled="false" vtype="maxLength:5" />
					
					<label>抵质押人证件号码：</label>
					<input name="tbGrtGuarantybasic.certificateCode" required="false" class="nui-textbox nui-form-input" vtype="maxLength:50" id="certificateCode" enabled="false" />
					
					<label>是否存在共有人：</label>
					<input name="tbGrtGuarantybasic.ifOtherCommon" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:1" id="tbGrtGuarantybasic.ifOtherCommon" dictTypeId="XD_0002" onItemclick="publicChange" />
				
					<label>抵质押物类型：</label>
					<input name="tbGrtGuarantybasic.sortType" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:9" id="sortType" enabled="false" dictTypeId="XD_DBCD4002" />
					
					<label>抵质押类型：</label>
					<input name="tbGrtGuarantybasic.collType" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:100" id="tbGrtGuarantybasic.collType" enabled="false" dictTypeId="CDZC0005" />
					
					<label>评估方式：</label>
					<input name="tbGrtGuarantybasic.assessType" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:2" id="assessType" dictTypeId="YP_GLCD0010" onValueChanged="chooiseValuationForm" />
						
					<label>评估日期：</label>
					<input name="tbGrtGuarantybasic.assessDate" required="true" class="nui-datepicker nui-form-input" vtype="maxLength:10" id="assessDate" format="yyyy-MM-dd" />
				    
					<label>评估方法：</label>
					<input name="tbGrtGuarantybasic.valuationForm" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:4" dicttypeid="YP_GLCD0011" id="valuationForm" />
					
					<label>评估机构名称：</label>
					<input name="tbGrtGuarantybasic.assessOrg" required="false" class="nui-buttonEdit nui-form-input" vtype="maxLength:300" id="assessOrg" onbuttonclick="chooiseAssessOrg" allowinput="false"/>
					
					<label>评估到期日：</label>
					<input name="tbGrtGuarantybasic.assessEndDate" required="false" class="nui-datepicker nui-form-input" vtype="maxLength:10" id="assessEndDate" format="yyyy-MM-dd" />
					
					<label>币种（评估价值）：</label>
					<input name="tbGrtGuarantybasic.currencyCd" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:3" dictTypeId="CD000001" />
					
					<label>评估价值：</label>
					<input name="tbGrtGuarantybasic.assessCost" required="true" class="nui-textbox nui-form-input" vtype="flaot;maxLength:26" id="assessCost" dataType="currency" />
					
					<label>担保债权金额:</label>
					<input name="tbConSubGrtRelation.guaranteeRightMoney" required="true" class="nui-textbox nui-form-input" id="tbConSubGrtRelation.guaranteeRightMoney" vtype="float;maxLength:26;" dataType="currency" />
						
					<label>我行已设定担保额：</label>
					<input name="tbGrtGuarantybasic.mybankSetAmt" id="tbGrtGuarantybasic.mybankSetAmt" dataType="currency" required="true" class="nui-textbox nui-form-input" vtype="float;maxLength:26;" />	
					
					<label>他行已设定担保额：</label>
					<input name="tbGrtGuarantybasic.otherbankSetAmt" required="true" id="otherbankSetAmt" dataType="currency" class="nui-textbox nui-form-input" vtype="float;maxLength:26;" />
					
					<label>抵质押率(%)：</label>
					<input name="tbGrtGuarantybasic.mortgageRate" required="true" class="nui-textbox nui-form-input" vtype="float;range:0,100;maxLength:8" />
					
					<label colspan="2"></label>
					
					<label>创建人：</label>
					<input name="tbGrtGuarantybasic.userNum" required="false" class="nui-text nui-form-input" vtype="maxLength:50" id="tbGrtGuarantybasic.userNum" dictTypeId="user" enabled="false" />
					
					<label>更新人：</label>
					<input name="tbGrtGuarantybasic.updateUser" required="false" class="nui-text nui-form-input" vtype="maxLength:50" id="tbGrtGuarantybasic.updateUser" dictTypeId="user" enabled="false" />
					
					<label>创建机构：</label>
					<input name="tbGrtGuarantybasic.orgNum" required="false" class="nui-text nui-form-input" vtype="maxLength:20" id="tbGrtGuarantybasic.orgNum" dictTypeId="org" enabled="false" />
					
					<label>更新机构：</label>
					<input name="tbGrtGuarantybasic.updateOrg" required="false" class="nui-text nui-form-input" vtype="maxLength:10" id="tbGrtGuarantybasic.updateOrg" dictTypeId="org" enabled="false" />
					
					<label>创建日期：</label>
					<input name="tbGrtGuarantybasic.createTime" required="false" class="nui-textbox nui-form-input" vtype="maxLength:10" id="tbGrtGuarantybasic.createTime" dateformat="yyyy-MM-dd" enabled="false" />
	
					<label>更新日期：</label>
					<input name="tbGrtGuarantybasic.updateTime" required="false" class="nui-textbox nui-form-input" vtype="maxLength:10" id="tbGrtGuarantybasic.updateTime" dateformat="yyyy-MM-dd" enabled="false" />
				</div>
			</div>
			
			<div class="nui-toolbar" style="border-bottom:0;text-align:center;">
				<a class="nui-button" iconCls="icon-save" onclick="save()" id="btnSave">保存</a>
			</div>	
		</div>
		
		<div title="押品其他附属信息" id="otherInfo" name="otherInfo" style="width:90%;height:550px">
			<div id="form2" style="width:100%;height:auto;overflow:hidden;">
				<label class="nui-hidden">担保品ID：</label>
				<input name="tbGrtGuarantybasic.suretyId" required="true" class="nui-hidden" vtype="maxLength:32" value="<%=request.getParameter("suretyId") %>"/>
				<span>其他信息</span>
				<div class="nui-dynpanel" columns="4">
					<label>抵押物状态：</label>
					<input name="tbGrtGuarantybasic.guarantyStatus" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:2" id="tbGrtGuarantybasic.guarantyStatus" dictTypeId="YP_GLCD0013" />
				
					<label>法律规定禁止流通财产：</label>
					<input name="tbGrtGuarantybasic.ifLawBanAssets" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:1" dictTypeId="XD_0002" />
			
					<label>是否被查封、扣押或监管：</label>
					<input name="tbGrtGuarantybasic.ifSealSeize" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:1" dictTypeId="XD_0002" />
				
					<label>是否有强行执行条款：</label>
					<input name="tbGrtGuarantybasic.isExecute" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:1" dictTypeId="XD_0002" />
				
				    <!--<label>抵质押品变现能力：</label>
					<input name="tbGrtGuarantybasic.changeAbility" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:1" dictTypeId="YP_GLCD0144" />
			
					<label>押品价值波动性：</label>
					<input name="tbGrtGuarantybasic.valuewave" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:2" dictTypeId="YP_GLCD0146" />

					<label>押品查封便利性：</label>
					<input name="tbGrtGuarantybasic.convenient" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:2" dictTypeId="YP_GLCD0147" />-->
				</div>
				<span>合格信息</span>
				<div class="nui-dynpanel" columns="4">
				 	<label>是否权属清晰：</label>
				 	<input name="tbGrtGuarantybasic.guarantyIfFlaw" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:2" id="guarantyIfFlaw"  dictTypeId="XD_0002" />
				
				    <label>是否办理登记：</label>
					<input name="tbGrtGuarantybasic.isRegister" id="tbGrtGuarantybasic.isRegister" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:2" dictTypeId="YP_GLCD0145" />
			
					<label>是否办理足额保险：</label>
					<input name="tbGrtGuarantybasic.ifEnoughInsure" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:1" id="tbGrtGuarantybasic.ifEnoughInsure" dictTypeId="XD_0002"/>
				
					<label>我行债权是否优先受偿：</label>
					<input name="tbGrtGuarantybasic.guarantyIfMyOne" id="tbGrtGuarantybasic.guarantyIfMyOne" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:1" dictTypeId="XD_0002" />
					
                	<label>前手债权金额：</label>
					<input name="tbGrtGuarantybasic.agoHandMoney" id="tbGrtGuarantybasic.agoHandMoney" required="false" class="nui-textbox nui-form-input" vtype="float;maxLength:26;" dataType="currency" />
				
					<label>是否具有较高的市场流动性：</label>
					<input name="tbGrtGuarantybasic.guarantyMobility" id="tbGrtGuarantybasic.guarantyMobility" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:2" dictTypeId="YP_GLCD0016" />
					
					<label>与债务人之间是否存在正相关关系：</label>
					<input name="tbGrtGuarantybasic.isRelated" id="tbGrtGuarantybasic.isRelated" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:1" dictTypeId="XD_0002" />
			   </div>
			</div>
			
			<div class="nui-toolbar" style="border-bottom:0;text-align:center;">
				<a class="nui-button" iconCls="icon-save" onclick="save2()" id="btnSave2">保存</a>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		nui.parse();
		var form = new nui.Form("#form1");
		var form2 = new nui.Form("#form2");
		//设置抵质押类型的选择值
	    nui.get("tbGrtGuarantybasic.collType").setData(getDictData('CDZC0005','str','020101,020201'));
		var parentSortType = "<%=request.getParameter("parentSortType") %>";
		var partyId = "<%=request.getParameter("partyId")%>";
		if ("<%=request.getParameter("view") %>"=="1") {
			form.setEnabled(false);
			form2.setEnabled(false);
			nui.get("btnSave").hide();
			nui.get("btnSave2").hide();
		}
		
		/**
		 * 保存
		 */
		function save() {
			form.validate();
			if (form.isValid() == false) {
				nui.alert("请将信息填写完整");
				return;
			}
			var o=form.getData();
			var json=nui.encode(o)+nui.encode({"subcontractId":"<%=request.getParameter("subcontractId") %>","partyId":partyId});
			git.mask();
			$.ajax({
	        	url: "com.bos.grt.grtMainManage.grtContract.updateGrtContractDYZY.biz.ext",
	        	type: 'POST',
	        	data: json,
	        	cache: false,
	        	contentType:'text/json',
	        	success: function (text) {
	        		if("保存成功！"==text.msg){
	        			nui.alert(text.msg);
	        			var suretyId = text.tbGrtGuarantybasic.suretyId;
	        			nui.get("tbGrtGuarantybasic.suretyId").setValue(suretyId);
	        		} else {
	        			nui.alert(text.msg);
	        			CloseWindow("ok");
	        		}
	        		var tabs = nui.get("tabs1");
		    		if(nui.get("tbGrtGuarantybasic.ifOtherCommon").getValue()=="1"){
	 					//共有人信息
	 					var publicInfo = tabs.getTab("publicInfo");
	 					tabs.removeTab(publicInfo);//删除publicInfo
	 					addTab5();
	 					var publicInfo = tabs.getTab("publicInfo");
	 					var publicTypeUrl = publicInfo.url;
	 					publicTypeUrl = publicTypeUrl+"?suretyId="+suretyId +"&sortType=" + sortType;
	 					publicInfo.url = publicTypeUrl;
	 					tabs.updateTab(publicInfo);//更新tab标签页
	 				}else{
	 				    var publicInfo = tabs.getTab("publicInfo");
	 					tabs.removeTab(publicInfo);//删除publicInfo
	 				}
	        		git.unmask();
	        	},
	        	error: function (jqXHR, textStatus, errorThrown) {
	            	nui.alert(jqXHR.responseText);
	        	}
			});
		}		
		/**
		 * 初始化值
		 */
		function initForm() {
			var json=nui.encode({"tbGrtGuarantybasic":{"suretyId":"<%=request.getParameter("suretyId") %>"}})+
			nui.encode({"tbConSubGrtRelation":{"relationId":"<%=request.getParameter("relationId") %>"}});	
			git.mask();
			$.ajax({
		        url: "com.bos.grt.grtMainManage.grtContract.getGrtContractDYZY.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        contentType:'text/json',
		        success: function (text) {
		        	if(text.msg){
		        		nui.alert(text.msg);
		        	} else {
		        		form.setData(text);
		        		nui.get("partyName").setValue(text.tbGrtGuarantybasic.partyName);
		        		nui.get("partyName").setText(text.tbGrtGuarantybasic.partyName);
		        		nui.get("assessOrg").setText(text.tbGrtGuarantybasic.assessOrg);
		        		var sortType=text.tbGrtGuarantybasic.sortType;
		        		//根据押品类型显示抵押物页面
			        	var json  = nui.encode({"sortArgument":{"sortType":sortType}});
			        	git.mask();
			        	$.ajax({
			        		url: "com.bos.grt.grtMainManage.grtApply.getApplyCollUrlBySortType.biz.ext",
			        		type:'POST',
			        		data: json,
				    		cache: false,
				   		    contentType:'text/json',
				   		    success: function (text) {
				    			var typeUrl = text.typeUrl;
				    			var suretyId = "<%=request.getParameter("suretyId") %>"
				    			typeUrl = typeUrl+"?suretyId="+suretyId +"&sortType=" + sortType;
				    			addTab(typeUrl);//根据查询出的url加载tab标签
				    			initCreateValue();
				    			git.unmask();
				    		},
				            error: function () {
				            	nui.alert("操作失败！");
				            }
			        	});
		        	}
		        	git.unmask();
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		            nui.alert(jqXHR.responseText);
		        }
			});
		}
		initForm();
		
		function initCreateValue(){
			var collType = nui.get("tbGrtGuarantybasic.collType").value;
			//押品父类型编号
			nui.get("tbGrtGuarantybasic.parentSortType").setValue(parentSortType);
			var sortType=nui.get("sortType").value;
			//根据押品类型显示抵押物页面
			var json = nui.encode({"sortArgument":{"sortType":sortType}});
			git.mask();
			$.ajax({
				url: "com.bos.grt.grtMainManage.grtApply.getApplyCollUrlBySortType.biz.ext",
				type:'POST',
				data: json,
				cache: false,
				contentType:'text/json',
				success: function (text) {
		    		var typeUrl = text.typeUrl;
		    		//保管信息
					addTab3();
	 				var tabs = nui.get("tabs1");
		    		//如果共有人为否，删除共有人页签
		    		if(nui.get("tbGrtGuarantybasic.ifOtherCommon").getValue()!="1"){
	 					var publicInfo = tabs.getTab("publicInfo");
	 					tabs.removeTab(publicInfo);//删除publicInfo
	 				}else{
	 					addTab5();
	 				}
					git.unmask();
				},
				error: function () {
					nui.alert("操作失败！");
				}
			});
		}
	 
		/**
		 * 动态加载tab标签页
		 */
		function addTab(typeUrl) {
			var tabs = nui.get("tabs1");
			var tab = { title: "押品详细信息",name: "collInfo", url: nui.context+typeUrl, showCloseButton: false};
			tab.ondestroy = function (e) {
				var tabs = e.sender;
				var iframe = tabs.getTabIFrameEl(e.tab);
				//获取子页面返回数据
				var pageReturnData = iframe.contentWindow.getData ? iframe.contentWindow.getData() : "";
				//如果禁止销毁的时候，自动active一个新tab：e.autoActive = false;
			}
			tabs.addTab(tab);
		}
		
		/**
		 * 保管信息页签
		 */
		function addTab3() {
			var tabs = nui.get("tabs1");
			var tab = {title: "保管信息",name: "regInfo",url: nui.context+"/grt/manage/TbGrtRegcardinfo/TbGrtRegcardinfo_list.jsp"};
			tab = tabs.addTab(tab);            
			var el = tabs.getTabBodyEl(tab);
			//el.innerHTML = 4;
		}
		
		/**
		 * 其他信息页签
		 */
		function addTab4() {
			var tabs = nui.get("tabs1");
			var tab = {title: "其他附属信息",name: "otherInfo",url: nui.context+"/grt/grtOtherInfo/grt_other_info_edit.jsp"};
			tab = tabs.addTab(tab);            
			var el = tabs.getTabBodyEl(tab);
			//el.innerHTML = 5;
		}
		
		/**
		 * 共有人页签
		 */
		function addTab5() {
			var tabs = nui.get("tabs1");
			var tab = {title: "共有人信息",name: "publicInfo",url: nui.context+"/grt/manage/TbGrtGuarantypublic/TbGrtGuarantypublic_list.jsp"};
			tab = tabs.addTab(tab);            
			var el = tabs.getTabBodyEl(tab);
			//el.innerHTML = 5;
		}
		
	 	/**
		 * 抵质押人
		 */
		function chooiseParty(){
			nui.open({
				url: nui.context+"/grt/guaranteer/guaranteer_chooise.jsp",
				title: "选择抵质押人", 
				width: 800, 
				height: 600,
				allowResize:false,
		        allowDrag: false,
				showMaxButton: false,
				ondestroy: function (action) {
					nui.get("partyNum").setValue(action[1]);
					nui.get("certificateTypeCd").setValue(action[3]);
					nui.get("certificateCode").setValue(action[4]);
					nui.get("partyName").setValue(action[2]);
					nui.get("partyName").setText(action[2]);
				}
			}); 
		}
		
		/**
		 * 评估机构
		 */
		function chooiseAssessOrg(){
			nui.open({
				url: nui.context+"/grt/grtPublic/grtAssessCsm.jsp",
				title: "选择评估机构", 
				width: 800, 
				height: 600,
				allowResize:false,
		        allowDrag: false,
				showMaxButton: false,
				ondestroy: function (action) {
					if(action != "ok"){
						nui.get("tbGrtGuarantybasic.assessorgPartyId").setValue(action[0]);
						nui.get("assessOrg").setValue(action[2]);
						nui.get("assessOrg").setText(action[2]);
					}
				}
			});
		}
		
		/**
		 * 评估方法
		 */
		function chooiseValuationForm(){
		     //评估类型选择【评估公司】评估时,评估机构和评估到期日必须录入
		    var assessType = nui.get("assessType").getValue();
			if("0201" == assessType){
	        	nui.get("assessOrg").setRequired(true);
	        	nui.get("assessEndDate").setRequired(true);
	        }else{
	            nui.get("assessOrg").setValue("");
	            nui.get("assessOrg").setText("");
	            nui.get("assessEndDate").setValue("");
	            nui.get("assessEndDate").setText("");
	        	nui.get("assessOrg").setRequired(false);
	        	nui.get("assessEndDate").setRequired(false);
	        }
	        //评估类型选择【免评估】和【评估公司评估】时,评估方法不必须录入
	        if("0201" == assessType||"0300"==assessType){
	            nui.get("valuationForm").setValue("");
	            nui.get("valuationForm").setText("");
	        	nui.get("valuationForm").setRequired(false);
	        }else{
	            nui.get("valuationForm").setText("");
	        	nui.get("valuationForm").setRequired(true);
	        }
		}
	 	
	 	/**
	 	 * 切换tab时
	 	 */
	 	function tabChange(e){
	 		var suretyId = nui.get("tbGrtGuarantybasic.suretyId").value;
	 		var sortType = nui.get("sortType").value;
	 		if(suretyId!="" && suretyId!=null){
	 			//押品详细信息
	 			var tabs = nui.get("tabs1");
	 			var collInfo = tabs.getTab("collInfo");
	 			var typeUrl = collInfo.url.split("?")[0];
	 			typeUrl = typeUrl+"?suretyId="+suretyId+"&sortType="+sortType+"&view=<%=request.getParameter("view") %>"+"&parentSortType="+parentSortType;
	 			collInfo.url = typeUrl;
	 			tabs.updateTab(collInfo);//更新tab标签页
	 			//押品保管信息
	 			var tabs = nui.get("tabs1");
	 			var regInfo = tabs.getTab("regInfo");
	 			var regTypeUrl = regInfo.url.split("?")[0];
	 			regTypeUrl = regTypeUrl+"?suretyId="+suretyId +"&sortType=" + sortType +"&view=<%=request.getParameter("view") %>";
	 			regInfo.url = regTypeUrl;
	 			tabs.updateTab(regInfo);//更新tab标签页
	 			
	 			if(nui.get("tbGrtGuarantybasic.ifOtherCommon").getValue()=="1"){
	 				//共有人信息
	 				var tabs = nui.get("tabs1");
	 				var publicInfo = tabs.getTab("publicInfo");
	 				var publicTypeUrl = publicInfo.url.split("?")[0];
	 				publicTypeUrl = publicTypeUrl+"?suretyId="+suretyId +"&sortType=" + sortType+"&view=<%=request.getParameter("view") %>";
	 				publicInfo.url = publicTypeUrl;
	 				tabs.updateTab(publicInfo);//更新tab标签页
	 			}
	 		}else{
	 			nui.alert("请先保存基本信息");
	 			e.cancel=true;
	 		}
	 	}
	 	
		//-------------------------------------------------------其他附属信息----------------------------------------------------------
		/**
		 * 其他附属信息初始化
		 */
		function initForm2() {
			var json=nui.encode({"tbGrtGuarantybasic":{"suretyId": "<%=request.getParameter("suretyId") %>"}});
			git.mask();
			$.ajax({
	        	url: "com.bos.grt.manage.TbGrtGuarantybasic.getTbGrtGuarantybasic.biz.ext",
	        	type: 'POST',
	        	data: json,
	        	cache: false,
	        	contentType:'text/json',
	        	success: function (text) {
	        		if(text.msg){
	        			nui.alert(text.msg);
	        		} else {
	        			form2.setData(text);
	        			setQualifiedObjectEnabled();
	        		}
	        		git.unmask();
	        	},
	        	error: function (jqXHR, textStatus, errorThrown) {
	            	nui.alert(jqXHR.responseText);
	        	}
			});
		}
		initForm2();
		
		/**
		 * 保存其他附属信息
		 */
		function save2() {
			var suretyId = nui.get("tbGrtGuarantybasic.suretyId").getValue();
			form2.validate();
			if (form2.isValid() == false) {	
				nui.alert("请将信息填写完整");
				return;	
			}
			var o=form2.getData();
			var json=nui.encode(o);	
			git.mask();
			$.ajax({
		        url: "com.bos.grt.grtMainManage.grtContract.updateGrtContractTbGrtGuarantybasic.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        contentType:'text/json',
		        success: function (text) {
		        	if(text.msg){
		        		nui.alert(text.msg);
		        	} else {
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
		 * 共有人选择
		 */
		function publicChange(){
			if(nui.get("tbGrtGuarantybasic.ifOtherCommon").getValue()=="0"){
				var json = nui.encode({"tbGrtGuarantypublic":{"suretyId":"<%=request.getParameter("suretyId") %>"}});
	 			git.mask();
	 			$.ajax({
	        		url: "com.bos.grt.manage.TbGrtGuarantypublic.getTbGrtGuarantypublicList.biz.ext",
	        		type: 'POST',
	        		data: json,
	        		cache: false,
	        		contentType:'text/json',
	        		success: function (text) {
	        			if(text.tbGrtGuarantypublics.length>0){
	        				nui.alert("存在共有人信息！");
	        				nui.get("tbGrtGuarantybasic.ifOtherCommon").setValue("1");
	        			}
	        			git.unmask();
	        		},
	        		error: function (jqXHR, textStatus, errorThrown) {
	            		nui.alert(jqXHR.responseText);
	        		}
				});
			}	
		}
		
		/*
		 *点击窗口中的关闭按钮
		 */
		function CloseWindow(action) {            
			window.CloseOwnerWindow("ok");
		}
		
		//取押品的合格性默认字段，如果有默认值，文本框成灰色，不允许选择
		function setQualifiedObjectEnabled(){
		   var sortType="<%=request.getParameter("sortType")%>";
		   if(sortType==""&&sortType==null&&sortType=="null") return;
		   var o = {"sortType":sortType};
		   var json=nui.encode(o);
		   git.mask();
			$.ajax({
		        url: "com.bos.grtpublic.getGrtQualified.getGrtQualifiedDefaultValue.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        contentType:'text/json',
		        success: function (text) {
		            if(text.defaultValueMap.GUARANTY_IF_FLAW){
		                nui.get("guarantyIfFlaw").setValue(text.defaultValueMap.GUARANTY_IF_FLAW);
		            	nui.get("guarantyIfFlaw").setEnabled(false);
		            }
		            if(text.defaultValueMap.IS_REGISTER){
		                nui.get("tbGrtGuarantybasic.isRegister").setValue(text.defaultValueMap.IS_REGISTER);
		            	nui.get("tbGrtGuarantybasic.isRegister").setEnabled(false);
		            }
		            if(text.defaultValueMap.IF_ENOUGH_INSURE){
		                nui.get("tbGrtGuarantybasic.ifEnoughInsure").setValue(text.defaultValueMap.IF_ENOUGH_INSURE);
		                nui.get("tbGrtGuarantybasic.ifEnoughInsure").setEnabled(false);
		            }
		            if(text.defaultValueMap.GUARANTY_IF_MY_ONE){
		                nui.get("tbGrtGuarantybasic.guarantyIfMyOne").setValue(text.defaultValueMap.GUARANTY_IF_MY_ONE);
		                nui.get("tbGrtGuarantybasic.guarantyIfMyOne").setEnabled(false);
		            }
		            if(text.defaultValueMap.GUARANTY_MOBILITY){
		                nui.get("tbGrtGuarantybasic.guarantyMobility").setValue(text.defaultValueMap.GUARANTY_MOBILITY);
		                nui.get("tbGrtGuarantybasic.guarantyMobility").setEnabled(false);
		            }
		            if(text.defaultValueMap.IS_RELATED){
		                nui.get("tbGrtGuarantybasic.isRelated").setValue(text.defaultValueMap.IS_RELATED);
		                nui.get("tbGrtGuarantybasic.isRelated").setEnabled(false);
		            }
		        	git.unmask();
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		            nui.alert(jqXHR.responseText);
		        }
			});
		}
		
	</script>
</body>
</html>