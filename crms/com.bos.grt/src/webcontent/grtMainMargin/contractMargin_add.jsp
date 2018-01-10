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
	<title>新增保证金(质押合同)</title>
</head>
<script type="text/javascript" src="<%=contextPath%>/grt/grtJS/grt.js"></script>
<body>
	<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;height:94%;" onbeforeactivechanged="tabChange">
		<div title="押品概况信息" id="basicTab" name="basicTab" style="width:90%;height:550px">
			<div id="form1" style="width:100%;height:auto;overflow:hidden;">
				<input name="tbGrtGuarantybasic.parentSortType" required="true" class="nui-hidden" vtype="maxLength:30"
					id="tbGrtGuarantybasic.parentSortType" enabled="false" />
				<input name="tbGrtGuarantybasic.suretyId" required="true" class="nui-hidden" vtype="maxLength:50" 
					id="tbGrtGuarantybasic.suretyId" enabled="false" />
				<input name="tbGrtGuarantee.suretyKeyId" required="true" class="nui-hidden" vtype="maxLength:50" 
					id="tbGrtGuarantee.suretyKeyId" enabled="false" />
				<!-- 合同关联关系信息 -->
				<input name="tbConSubGrtRelation.subcontractId" required="true" class="nui-hidden" vtype="maxLength:50" 
					id="tbConSubGrtRelation.subcontractId"enabled="false" />
				<input name="tbConSubGrtRelation.guarantyId" required="true" class="nui-hidden" vtype="maxLength:50" 
					id="tbConSubGrtRelation.guarantyId"enabled="false" />
				<input name="tbConSubGrtRelation.relationId" required="true" class="nui-hidden" vtype="maxLength:50" 
					id="tbConSubGrtRelation.relationId" enabled="false"/>
				<div id="panel1" class="nui-dynpanel" columns="4">
					<label>抵质押编号：</label>
					<input name="tbGrtGuarantybasic.suretyNum" required="true" class="nui-textbox nui-form-input" vtype="maxLength:60"
						id="suretyNum" enabled="false" />
					
					<label>抵质押人名称：</label>
					<input name="tbGrtGuarantybasic.partyName" required="true" class="nui-buttonEdit nui-form-input" vtype="maxLength:60" 
						id="partyName"  allowinput="false" onbuttonclick="chooiseParty" enabled="false"/>
					
					<label>抵质押人客户编号：</label>
					<input name="tbGrtGuarantybasic.partyNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:20" 
						id="partyNum" enabled="false" />
					
					<label>抵质押人证件类型：</label>
					<input name="tbGrtGuarantybasic.certificateTypeCd" required="false" class="nui-dictcombobox nui-form-input" 
						id="certificateTypeCd" dictTypeId="CDKH0002" enabled="false" vtype="maxLength:5" />
					
					<label>抵质押人证件号码：</label>
					<input name="tbGrtGuarantybasic.certificateCode" required="false" class="nui-textbox nui-form-input" vtype="maxLength:50" 
						id="certificateCode" enabled="false" />
					
					<label>抵质押物类型：</label>
					<input name="tbGrtGuarantybasic.sortType" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:9"
						 id="sortType" enabled="false" dictTypeId="XD_DBCD4002" />
					
					<label>抵质押类型：</label>
					<input name="tbGrtGuarantybasic.collType" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:100" 
						id="tbGrtGuarantybasic.collType" enabled="false" dictTypeId="CDZC0005" />
					
					<label>担保债权金额:</label>
					<input name="tbConSubGrtRelation.guaranteeRightMoney" required="true" class="nui-textbox nui-form-input" 
						id="guaranteeRightMoney" vtype="float;maxLength:26;" dataType="currency" />
						
					<label>币种（评估价值）：</label>
					<input name="tbGrtGuarantybasic.currencyCd" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:3"
						dictTypeId="CD000001" />
					
					<label>评估价值：</label>
					<input name="tbGrtGuarantybasic.assessCost" required="true" class="nui-textbox nui-form-input" 
						id="assessCost" vtype="float;maxLength:26;" dataType="currency" />
					
					<label>创建人：</label>
					<input name="tbGrtGuarantybasic.userNum" required="false" class="nui-text nui-form-input" vtype="maxLength:50"
						id="tbGrtGuarantybasic.userNum" value="<%=com.bos.pub.GitUtil.getCurrentUserId()%>"  dictTypeId="user" enabled="false" />
					
					<label>更新人：</label>
					<input name="tbGrtGuarantybasic.updateUser" required="false" class="nui-text nui-form-input" vtype="maxLength:50"
						id="tbGrtGuarantybasic.updateUser" value="<%=com.bos.pub.GitUtil.getCurrentUserId()%>" dictTypeId="user" enabled="false" />
					
					<label>创建机构：</label>
					<input name="tbGrtGuarantybasic.orgNum" required="false" class="nui-text nui-form-input" vtype="maxLength:20"
						id="tbGrtGuarantybasic.orgNum" value="<%=com.bos.pub.GitUtil.getCurrentOrgCd()%>" dictTypeId="org" enabled="false" />
					
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
				<input name="tbGrtGuarantybasic.suretyId" required="true" class="nui-hidden" vtype="maxLength:32"/>
				<span>其他信息</span>
				<div class="nui-dynpanel" columns="4">
					<label>抵押物状态：</label>
					<input name="tbGrtGuarantybasic.guarantyStatus" id="tbGrtGuarantybasic.guarantyStatus"  required="true" class="nui-dictcombobox nui-form-input" dictTypeId="YP_GLCD0013" vtype="maxLength:2" />
						
					<label>法律规定禁止流通财产：</label>
					<input name="tbGrtGuarantybasic.ifLawBanAssets" id="tbGrtGuarantybasic.ifLawBanAssets" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:1" dictTypeId="XD_0002" />
					
					<label>是否被查封、扣押或监管：</label>
					<input name="tbGrtGuarantybasic.ifSealSeize" id="tbGrtGuarantybasic.ifSealSeize" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:1" dictTypeId="XD_0002" />
					
					<label>是否有强行执行条款：</label>
					<input name="tbGrtGuarantybasic.isExecute" id="tbGrtGuarantybasic.isExecute" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:1" dictTypeId="XD_0002" />
					
				</div>
				<span>合格信息</span>
				<div class="nui-dynpanel" columns="4">
				    <label>是否权属清晰：</label>
					<input name="tbGrtGuarantybasic.guarantyIfFlaw" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:2" id="guarantyIfFlaw" dictTypeId="XD_0002" />
					
					<label>是否办理登记：</label>
					<input name="tbGrtGuarantybasic.isRegister" id="tbGrtGuarantybasic.isRegister" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:2" dictTypeId="YP_GLCD0145" />
				
					<label>是否办理足额保险：</label>
					<input name="tbGrtGuarantybasic.ifEnoughInsure" id="tbGrtGuarantybasic.ifEnoughInsure" required="true" class="nui-dictcombobox nui-form-input" id="tbGrtGuarantybasic.ifEnoughInsure" vtype="maxLength:1" dictTypeId="XD_0002"/>
					
					<label>我行债权是否优先受偿：</label>
					<input name="tbGrtGuarantybasic.guarantyIfMyOne" id="tbGrtGuarantybasic.guarantyIfMyOne" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:1" dictTypeId="XD_0002" />
									    
				    <label>前手债权金额：</label>
					<input name="tbGrtGuarantybasic.agoHandMoney" id="agoHandMoney" required="false" class="nui-textbox nui-form-input" vtype="float;maxLength:26;" dataType="currency" />
			
					<label>是否具有较高的市场流动性：</label>
					<input name="tbGrtGuarantybasic.guarantyMobility" id="tbGrtGuarantybasic.guarantyMobility" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:2" dictTypeId="YP_GLCD0016" />
				
					<label>与债务人之间的是否存在正相关关系：</label>
					<input name="tbGrtGuarantybasic.isRelated" id="tbGrtGuarantybasic.isRelated" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:1" dictTypeId="XD_0002" />
				
				</div>
			</div>
			<div class="nui-toolbar" style="border-bottom:0;text-align:center;">
				<a class="nui-button" iconCls="icon-save" onclick="save2()">保存</a>
			</div>
		</div>
	</div>   

	<script type="text/javascript">
		nui.parse();
		var form = new nui.Form("#form1");
		//设置抵质押类型的选择值
	    nui.get("tbGrtGuarantybasic.collType").setData(getDictData('CDZC0005','str','020101,020201'));
		var sortType = "<%=request.getParameter("sortType") %>";
		var parentSortType = "<%=request.getParameter("parentSortType") %>";
		var partyId = "<%=request.getParameter("partyId")%>";
		var subcontractId = "<%=request.getParameter("subcontractId") %>";
		
		//调用抵质押编号生成JS方法生成抵质押编号。
		var suretyNum = getSuretyNum(sortType);
		nui.get("suretyNum").setValue(suretyNum);
		
		/**
		 * 保证金账号必须为5开头
		 */
		function marginAccountBlur(){
			var marginAccount = nui.get("tbGrtGuarantee.marginAccount").getValue();
			var tempmarginAccount = marginAccount.substring(0,1);
			if(tempmarginAccount != "5"){
				nui.alert("保证金账号必须为5开头！");
				nui.get("save1").setEnabled(false);
				return;
			}else{
				nui.get("save1").setEnabled(true);
				checkMarinAccount(marginAccount);
			}
		}
		
		/**
		 * 验证保证金账号
		 */
		function checkMarinAccount(marginAccount){
			var json=nui.encode({"AcctNo":marginAccount});
			git.mask();
			$.ajax({
				url: "com.bos.inter.CallT24Interface.T24Maintain.TDpAcctAllInq.biz.ext",
				type: 'POST',
				data: json,
				cache: false,
				contentType:'text/json',
				success: function (text) {
					//如果返回的数据没有保证金账号，则为不存在的保证金账号！
					if(text.accRs.AgreeMentAcctNo == null){
						nui.alert("账号不存在！");
						nui.get("save1").setEnabled(false);
					}else{//否则是存在的保证金账号
						nui.get("save1").setEnabled(true);
						//如果选择的类型为定期保证金
						if("01010102" == nui.get("sortType").getValue()){
							if(text.accRs.MaturityDate == "" || text.accRs.MaturityDate == null){//输入的账号是活期保证金
								nui.alert("输入的账号是活期保证金！不符合要求！");
								nui.get("save1").setEnabled(false);
							}else{//输入的账号也是定期期保证金
								nui.alert("符合要求！");
								nui.get("save1").setEnabled(true);
							}
						}else if("01010101" == nui.get("sortType").getValue()){//选择是活期保证金
							if(text.accRs.MaturityDate == "" || text.accRs.MaturityDate == null){//输入与的账号也是活期保证金
								nui.alert("符合要求！");
								nui.get("save1").setEnabled(true);
							}else{//输入的账号是定期保证金
								nui.alert("输入的账号是定期保证金！不符合要求！");
								nui.get("save1").setEnabled(false);
							}
						} 
					}
					git.unmask();
				},
				error: function (jqXHR, textStatus, errorThrown) {
					nui.alert(jqXHR.responseText);
				}
			});
		}
		
		/**
		 * 保存保证金
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
				url: "com.bos.grt.grtMainMargin.grtContract.addGrtContractMargin.biz.ext",
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
						nui.get("tbGrtGuarantee.suretyKeyId").setValue(text.tbGrtGuarantee.suretyKeyId);
						initForm2();
					} else {
						CloseWindow("ok");
					}
					git.unmask();
				},
				error: function (jqXHR, textStatus, errorThrown) {
					nui.alert(jqXHR.responseText);
				}
			});
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
		
	 	//切换tab时
	 	function tabChange(e){
	 		var suretyId = nui.get("tbGrtGuarantybasic.suretyId").value;
	 		var collType=nui.get("tbGrtGuarantybasic.collType").value;
	 		if(suretyId!="" && suretyId!=null){
	 			//押品详细信息
	 			var tabs = nui.get("tabs1");
	 			var collInfo = tabs.getTab("collInfo");
	 			var typeUrl = collInfo.url.split("?")[0];
	 			typeUrl = typeUrl+"?suretyId="+suretyId +"&sortType=" + sortType + "&collType=" + collType+"&subcontractId="+subcontractId;
	 			collInfo.url = typeUrl;
	 			tabs.updateTab(collInfo);//更新tab标签页
	 		}else{
	 			alert("请先保存基本信息");
	 			e.cancel=true;
	 		}
	 	}
	 	
		//-------------------------------------------------------其他附属信息----------------------------------------------------------
		var form2 = new nui.Form("#form2");
		/**
		 * 其他附属信息初始化
		 */
		function initForm2() {
			var suretyId = nui.get("tbGrtGuarantybasic.suretyId").value;
			var json=nui.encode({"tbGrtGuarantybasic":{"suretyId": suretyId}});
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
		
		/**
		 * 保存其他附属信息
		 */
		function save2() {
			var suretyId = nui.get("tbGrtGuarantybasic.suretyId").value;
			var sortType = nui.get("sortType").value;
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
		 * 点击关闭窗口	
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