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
	<title>新增抵质押品(抵质押合同)</title>
</head>
<script type="text/javascript" src="<%=contextPath%>/grt/grtJS/grt.js"></script>
<body>
	<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;height:94%;" onbeforeactivechanged="tabChange">
		<div title="押品概况信息" id="basicTab" name="basicTab" style="width:90%;height:550px">
			<div id="form1" style="width:100%;height:auto;overflow:hidden;">
				<input name="tbGrtGuarantybasic.parentSortType" required="true" class="nui-hidden" vtype="maxLength:30" id="tbGrtGuarantybasic.parentSortType" enabled="false" />
				<input name="tbGrtGuarantybasic.suretyId" required="true" class="nui-hidden" vtype="maxLength:50" id="tbGrtGuarantybasic.suretyId" enabled="false" />
				<input name="tbGrtGuarantybasic.assessorgPartyId" required="true" class="nui-hidden" vtype="maxLength:50" id="tbGrtGuarantybasic.assessorgPartyId" enabled="false" />
				<!-- 合同关联关系信息 -->
				<input name="tbConSubGrtRelation.subcontractId" required="true" class="nui-hidden" vtype="maxLength:50" id="tbConSubGrtRelation.subcontractId" enabled="false" />
				<input name="tbConSubGrtRelation.guarantyId" required="true" class="nui-hidden" vtype="maxLength:50" id="tbConSubGrtRelation.guarantyId" enabled="false" />
				<input name="tbConSubGrtRelation.relationId" required="true" class="nui-hidden" vtype="maxLength:50" id="tbConSubGrtRelation.relationId" enabled="false"/>
				<div id="panel1" class="nui-dynpanel" columns="4">
					<label>抵质押编号：</label>
					<input name="tbGrtGuarantybasic.suretyNum" required="true" class="nui-textbox nui-form-input" vtype="maxLength:60" id="suretyNum" enabled="false" />
					
					<label>抵质押人名称：</label>
					<input name="tbGrtGuarantybasic.partyName" required="true" class="nui-buttonEdit nui-form-input" vtype="maxLength:60" id="partyName" onbuttonclick="chooiseParty" enabled="false" allowinput="false"/>
					
					<label>抵质押人客户编号：</label>
					<input name="tbGrtGuarantybasic.partyNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:20" id="partyNum" enabled="false" />
					
					<label>抵质押人证件类型：</label>
					<input name="tbGrtGuarantybasic.certificateTypeCd" required="false" class="nui-dictcombobox nui-form-input" id="certificateTypeCd" dictTypeId="CDKH0002" enabled="false" vtype="maxLength:5"  />
					
					<label>抵质押人证件号码：</label>
					<input name="tbGrtGuarantybasic.certificateCode" required="false" class="nui-textbox nui-form-input" vtype="maxLength:50" id="certificateCode" enabled="false" />
					
					<label>是否存在共有人：</label>
					<input name="tbGrtGuarantybasic.ifOtherCommon" required="true" class="nui-dictcombobox nui-form-input" id="tbGrtGuarantybasic.ifOtherCommon" dictTypeId="XD_0002" onItemclick="publicChange" vtype="maxLength:1" />
					
					<label>抵质押物类型：</label>
					<input name="tbGrtGuarantybasic.sortType" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:9" id="sortType" enabled="false" dictTypeId="XD_DBCD4002" />
					
					<label>抵质押类型：</label>
					<input name="tbGrtGuarantybasic.collType" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:100" id="tbGrtGuarantybasic.collType" enabled="false" dictTypeId="CDZC0005" />
					
					<label>评估方式：</label>
					<input name="tbGrtGuarantybasic.assessType" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:2" id="assessType" dictTypeId="YP_GLCD0010" onValueChanged="chooiseValuationForm"/>
						
					<label>评估日期：</label>
					<input name="tbGrtGuarantybasic.assessDate" required="true" class="nui-datepicker nui-form-input" vtype="maxLength:10" id="assessDate" format="yyyy-MM-dd" />
				    
					<label>评估方法：</label>
					<input name="tbGrtGuarantybasic.valuationForm" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:4" id="valuationForm" dicttypeid="YP_GLCD0011"  />
					
					<label>评估机构名称：</label>
					<input name="tbGrtGuarantybasic.assessOrg" required="false" class="nui-buttonEdit nui-form-input" vtype="maxLength:300" id="assessOrg" onbuttonclick="chooiseAssessOrg" allowinput="false"/>
					
					<label>评估到期日：</label>
					<input name="tbGrtGuarantybasic.assessEndDate" required="false" class="nui-datepicker nui-form-input" vtype="maxLength:10" id="assessEndDate" format="yyyy-MM-dd" />
						
					<label>币种（评估价值）：</label>
					<input name="tbGrtGuarantybasic.currencyCd" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:3" dictTypeId="CD000001" />
					
					<label>评估价值：</label>
					<input name="tbGrtGuarantybasic.assessCost" required="true" class="nui-textbox nui-form-input" vtype="float;maxLength:26;" id="assessCost" dataType="currency" />
					
					<label>担保债权金额:</label>
					<input name="tbConSubGrtRelation.guaranteeRightMoney" required="true" class="nui-textbox nui-form-input" vtype="float;maxLength:26;" id="guaranteeRightMoney" dataType="currency" />
						
					<label>我行已设定担保额：</label>
					<input name="tbGrtGuarantybasic.mybankSetAmt" id="mybankSetAmt" dataType="currency" required="true" class="nui-textbox nui-form-input" value="0" vtype="float;maxLength:26;" />	
					
					<label>他行已设定担保额：</label>
					<input name="tbGrtGuarantybasic.otherbankSetAmt" id="otherbankSetAmt" dataType="currency" required="true" class="nui-textbox nui-form-input" value="0" vtype="float;maxLength:26;" />
					
					<label>抵质押率(%)：</label>
					<input name="tbGrtGuarantybasic.mortgageRate" required="true" class="nui-textbox nui-form-input" vtype="float;range:0,100;maxLength:8" />
					
					<label colspan="2"></label>
					
					<label>创建人：</label>
					<input name="tbGrtGuarantybasic.userNum" required="false" class="nui-text nui-form-input" vtype="maxLength:50"
						id="tbGrtGuarantybasic.userNum" value="<%=com.bos.pub.GitUtil.getCurrentUserId()%>" dictTypeId="user" enabled="false" />
					
					<label>更新人：</label>
					<input name="tbGrtGuarantybasic.updateUser" required="false" class="nui-text nui-form-input" vtype="maxLength:50"
						id="tbGrtGuarantybasic.updateUser" value="<%=com.bos.pub.GitUtil.getCurrentUserId()%>" dictTypeId="user" enabled="false" />
					
					<label>创建机构：</label>
					<input name="tbGrtGuarantybasic.orgNum" required="false" class="nui-text nui-form-input" vtype="maxLength:20"
						id="tbGrtGuarantybasic.orgNum" value="<%=com.bos.pub.GitUtil.getCurrentOrgCd()%>" dictTypeId="org" enabled="false"  />
					
					<label>更新机构：</label>
					<input name="tbGrtGuarantybasic.updateOrg" required="false" class="nui-text nui-form-input" vtype="maxLength:20"
						id="tbGrtGuarantybasic.updateOrg" value="<%=com.bos.pub.GitUtil.getCurrentOrgCd()%>" dictTypeId="org" enabled="false" />
					
					<label>创建日期：</label>
					<input name="tbGrtGuarantybasic.createTime" required="false" class="nui-textbox nui-form-input" vtype="maxLength:10" 
						id="tbGrtGuarantybasic.createTime" value="<%=com.bos.pub.GitUtil.getBusiDate()%>" enabled="false" />
	
					<label>更新日期：</label>
					<input name="tbGrtGuarantybasic.updateTime" required="false" class="nui-textbox nui-form-input" vtype="maxLength:10"
						id="tbGrtGuarantybasic.updateTime" value="<%=com.bos.pub.GitUtil.getBusiDate()%>" enabled="false" />
				</div>
			</div>
			<div class="nui-toolbar" style="border-bottom:0;text-align:center;">
				<a class="nui-button" iconCls="icon-save" onclick="save()" id="save1">保存</a>
			</div>	
		</div>
		
		<div title="押品其他附属信息">
	        <div id="form2" style="width:100%;height:auto;overflow:hidden;">
				<label class="nui-hidden">担保品ID：</label>
				<input name="tbGrtGuarantybasic.suretyId" required="true" class="nui-hidden" vtype="maxLength:32" 
					id="suretyId2" />
				<span>其他信息</span>
				<div class="nui-dynpanel" columns="4">
					<label>抵押物状态：</label>
					<input name="tbGrtGuarantybasic.guarantyStatus" required="true" class="nui-dictcombobox nui-form-input" id="tbGrtGuarantybasic.guarantyStatus" dictTypeId="YP_GLCD0013" vtype="maxLength:2" />
						
					<label>法律规定禁止流通财产：</label>
					<input name="tbGrtGuarantybasic.ifLawBanAssets" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002" vtype="maxLength:1" />
					
					<label>是否被查封、扣押或监管：</label>
					<input name="tbGrtGuarantybasic.ifSealSeize" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:1" dictTypeId="XD_0002" />
						
					<label>是否有强行执行条款：</label>
					<input name="tbGrtGuarantybasic.isExecute" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:1" dictTypeId="XD_0002" />	
							
				</div>
				<span>合格信息</span>
				<div class="nui-dynpanel" columns="4">
				    <label>是否权属清晰：</label>
					<input name="tbGrtGuarantybasic.guarantyIfFlaw" required="true" class="nui-dictcombobox nui-form-input" id="guarantyIfFlaw" dictTypeId="XD_0002" vtype="maxLength:1"  />
				
				    <label>是否办理登记：</label>
					<input name="tbGrtGuarantybasic.isRegister" id="tbGrtGuarantybasic.isRegister" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:2" dictTypeId="YP_GLCD0145" />
				
					<label>是否办理足额保险：</label>
					<input name="tbGrtGuarantybasic.ifEnoughInsure" required="true" class="nui-dictcombobox nui-form-input" id="tbGrtGuarantybasic.ifEnoughInsure" vtype="maxLength:1" dictTypeId="XD_0002"/>
			
					<label>我行债权是否优先受偿：</label>
					<input name="tbGrtGuarantybasic.guarantyIfMyOne" required="true" class="nui-dictcombobox nui-form-input" id="tbGrtGuarantybasic.guarantyIfMyOne" vtype="maxLength:1" dictTypeId="XD_0002" />
				    
				    <label>前手债权金额：</label>
					<input name="tbGrtGuarantybasic.agoHandMoney" required="false" class="nui-textbox nui-form-input" id="tbGrtGuarantybasic.agoHandMoney" vtype="float;maxLength:26;" dataType="currency" />
					
					<label>是否具有较高的市场流动性：</label>
					<input name="tbGrtGuarantybasic.guarantyMobility" id="tbGrtGuarantybasic.guarantyMobility" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="YP_GLCD0016" vtype="maxLength:2" />
					
					<label>与债务人之间是否存在正相关关系：</label>
					<input name="tbGrtGuarantybasic.isRelated" id="tbGrtGuarantybasic.isRelated" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:1" dictTypeId="XD_0002" />
				
				</div>
			</div>
			<div class="nui-toolbar" style="border-bottom:0;text-align:center;">
				<a class="nui-button" iconCls="icon-save" onclick="save2()" id="save2">保存</a>
			</div>
		</div>
	</div>   

	<script type="text/javascript">
		nui.parse();
		var form = new nui.Form("#form1");
		var form2 = new nui.Form("#form2");
		
		//设置抵质押类型的选择值
	    nui.get("tbGrtGuarantybasic.collType").setData(getDictData('CDZC0005','str','020101,020201'));
		var sortType = "<%=request.getParameter("sortType") %>";
		var parentSortType = "<%=request.getParameter("parentSortType") %>";
		var partyId = "<%=request.getParameter("partyId")%>";
		//调用抵质押编号生成JS方法生成抵质押编号。
		var suretyNum = getSuretyNum(sortType);
		nui.get("suretyNum").setValue(suretyNum);
		
		/**基础信息的初始化form表单方法**/
		function initForm2(suretyId) {
			var json=nui.encode({"tbGrtGuarantybasic":{"suretyId":suretyId}});	
			git.mask();
			$.ajax({
	        	url: "com.bos.grt.grtMainManage.grtOuter.getOuterTbGrtGuarantybasic.biz.ext",
	        	type: 'POST',
	        	data: json,
	        	cache: false,
	        	contentType:'text/json',
	        	success: function (text) {
	           		form2.setData(text);
	           		setQualifiedObjectEnabled();
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
			form.validate();
			if (form.isValid() == false) {
				nui.alert("请将信息填写完整");
				return;
			}
			var subcontractId = "<%=request.getParameter("subcontractId") %>";
			var o=form.getData();
			var json=nui.encode(o)+nui.encode({"subcontractId":subcontractId})+nui.encode({"partyId":partyId});
			git.mask();
			$.ajax({
	        	url: "com.bos.grt.grtMainManage.grtContract.addGrtContractDYZY.biz.ext",
	        	type: 'POST',
	        	data: json,
	        	cache: false,
	        	contentType:'text/json',
	        	success: function (text) {
	        		nui.alert(text.msg);
	        		if("保存成功！"==text.msg){
	        			var suretyId = text.tbGrtGuarantybasic.suretyId;
	        			nui.get("tbGrtGuarantybasic.suretyId").setValue(suretyId);
	        			nui.get("tbConSubGrtRelation.relationId").setValue(text.tbConSubGrtRelation.relationId);
	        			nui.get("suretyId2").setValue(suretyId);
	        			initForm2(suretyId);
	        		} else {
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
		function initCreateValue(){
			nui.get("sortType").setValue(sortType);
			nui.get("tbGrtGuarantybasic.collType").setValue("<%=request.getParameter("collType") %>");
			//押品父类型编号
			nui.get("tbGrtGuarantybasic.parentSortType").setValue(parentSortType);
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
		    		addTab(typeUrl);//根据查询出的url加载tab标签
		    		//登记信息
					addTab3();
		    		var tabs = nui.get("tabs1");
		    		//如果共有人不为是，删除共有人页签
		    		if(nui.get("tbGrtGuarantybasic.ifOtherCommon").getValue()!="1"){
	 					var publicInfo = tabs.getTab("publicInfo");
	 					tabs.removeTab(publicInfo);//删除publicInfo
	 				}else{
	 					addTab5();
	 				}
	 				//抵质押人信息赋值
	 				if("<%=request.getParameter("partyId") %>"=="" ||"<%=request.getParameter("partyId") %>"=="null" ||"<%=request.getParameter("partyId") %>"==null){
	 				   nui.alert("抵质押人的partyId为空!请先完善担保合同中的抵质押人信息!");
	 				   nui.get("partyName").enabled=true;
	 				   git.unmask();
	 				   return;
	 				   
	 				}
	 				var json2 = nui.encode({"party":{"partyId":"<%=request.getParameter("partyId") %>"}});
	 				git.mask();
					$.ajax({
		        		url: "com.bos.csm.pub.crudCustInfo.getCustByIdCardOrName.biz.ext",
		        		type:'POST',
		        		data: json2,
			    		cache: false,
			   		    contentType:'text/json',
			   		    success: function (text) {
			    			nui.get("partyNum").setValue(text.items[0].partyNum);
							nui.get("certificateTypeCd").setValue(text.items[0].certificateTypeCd);
							nui.get("certificateCode").setValue(text.items[0].certificateCode);
							nui.get("partyName").setValue(text.items[0].partyName);
							nui.get("partyName").setText(text.items[0].partyName);
							git.unmask();
			    		},
			            error: function () {
			            	nui.alert("操作失败！");
			            }
		        	});
		        	nui.get("tbConSubGrtRelation.subcontractId").setValue("<%=request.getParameter("subcontractId") %>");
					git.unmask();
				},
				error: function () {
					nui.alert("操作失败！");
				}
			});
		}
		initCreateValue();		
			
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
				url: nui.context+"/grt/grtMainManage/grtCus_chooise.jsp",
				title: "选择抵质押人", 
				width: 800, 
				height: 600,
				allowResize:false,
		        allowDrag: false,
				showMaxButton: false,
				ondestroy: function (action) {
				    partyId = action[0];
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
	 		var collType=nui.get("tbGrtGuarantybasic.collType").value;
	 		if(suretyId!="" && suretyId!=null){
	 			//押品详细信息
	 			var tabs = nui.get("tabs1");
	 			var collInfo = tabs.getTab("collInfo");
	 			var typeUrl = collInfo.url;
	 			typeUrl = typeUrl+"?suretyId="+suretyId+"&sortType="+sortType+"&collType="+collType+"&parentSortType="+parentSortType;
	 			collInfo.url = typeUrl;
	 			tabs.updateTab(collInfo);//更新tab标签页
  	 			//押品保管信息
	 			var tabs = nui.get("tabs1");
	 			var regInfo = tabs.getTab("regInfo");
	 			var regTypeUrl = regInfo.url;
	 			regTypeUrl = regTypeUrl+"?suretyId="+suretyId+"&sortType="+sortType+"&collType="+collType;
	 			regInfo.url = regTypeUrl;
	 			tabs.updateTab(regInfo);//更新tab标签页
	 			if(nui.get("tbGrtGuarantybasic.ifOtherCommon").getValue()=="1"){
	 				//共有人信息
	 				var tabs = nui.get("tabs1");
	 				var publicInfo = tabs.getTab("publicInfo");
	 				var publicTypeUrl = publicInfo.url;
	 				publicTypeUrl = publicTypeUrl+"?suretyId="+suretyId+"&sortType="+sortType+"&collType="+collType;
	 				publicInfo.url = publicTypeUrl;
	 				tabs.updateTab(publicInfo);//更新tab标签页
	 			}
	 		}else{
	 			alert("请先保存基本信息");
	 			e.cancel=true;
	 		}
	 	}
	 	
		//-------------------------------------------------------其他附属信息----------------------------------------------------------
		var form2 = new nui.Form("#form2");
		/**
		 * 保存其他附属信息
		 */
		function save2() {
			var suretyId = nui.get("tbGrtGuarantybasic.suretyId").getValue();
			var sortType = nui.get("sortType").getValue();
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
			var suretyId = nui.get("tbGrtGuarantybasic.suretyId").value;
			if(nui.get("tbGrtGuarantybasic.ifOtherCommon").getValue()=="2"){
				var json = nui.encode({"tbGrtGuarantypublic":{"suretyId":suretyId}});
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
		            	nui.get("guarantyIfFlaw").setEnabled(false);
		            }
		            if(text.defaultValueMap.IS_REGISTER){
		            	nui.get("tbGrtGuarantybasic.isRegister").setEnabled(false);
		            }
		            if(text.defaultValueMap.IF_ENOUGH_INSURE){
		                nui.get("tbGrtGuarantybasic.ifEnoughInsure").setEnabled(false);
		            }
		            if(text.defaultValueMap.GUARANTY_IF_MY_ONE){
		                nui.get("tbGrtGuarantybasic.guarantyIfMyOne").setEnabled(false);
		            }
		            if(text.defaultValueMap.GUARANTY_MOBILITY){
		                nui.get("tbGrtGuarantybasic.guarantyMobility").setEnabled(false);
		            }
		            if(text.defaultValueMap.IS_RELATED){
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