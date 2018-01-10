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
	<%
		String urlPath ="/grt/manage/twouse/twouse_list.jsp";
	%>
	<title>新增押品</title>
</head>
	<script type="text/javascript" src="<%=contextPath%>/grt/grtJS/grt.js"></script>
<body>
	<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;height:110%;" onbeforeactivechanged="tabChange">
		<div title="抵押物信息" id="basicTab" name="basicTab" style="width:90%;height:auto;">
		<div class="nui-dynpanel" columns="1" id="table2"  style="width:99.5%;">
			<fieldset>
			  	<legend>
			    	<span>概况信息</span>
			    </legend>
				<div id="form1" style="width:100%;height:auto;overflow:hidden;">
					<input name="tbGrtGuarantybasic.parentSortType" required="true" class="nui-hidden" vtype="maxLength:30" id="tbGrtGuarantybasic.parentSortType" enabled="false" />
					<input name="tbGrtGuarantybasic.suretyId" required="true" class="nui-hidden" vtype="maxLength:50" id="tbGrtGuarantybasic.suretyId" enabled="false" />
					<input name="tbGrtSurety.partyId" required="true" class="nui-hidden" vtype="maxLength:50" id="tbGrtSurety.partyId" enabled="false" />
					<input name="tbGrtGuarantybasic.partyId" required="true" class="nui-hidden" vtype="maxLength:50" id="tbGrtGuarantybasic.partyId" enabled="false" />	
					<input name="tbGrtGuarantybasic.assessorgPartyId" required="true" class="nui-hidden" vtype="maxLength:50" id="tbGrtGuarantybasic.assessorgPartyId" enabled="false" />
					<div id="panel1" class="nui-dynpanel" columns="4">
						<label>抵质押编号：</label>
						<input name="tbGrtGuarantybasic.suretyNo" required="true" class="nui-textbox nui-form-input" vtype="maxLength:60" id="suretyNum" enabled="false" />
						
						<label>抵质押人名称：</label>
						<input name="tbGrtGuarantybasic.partyName" required="true" class="nui-buttonEdit nui-form-input" vtype="maxLength:300" id="partyName"  allowinput="false" enabled="false"/>
						
						<label>抵质押人客户编号：</label>
						<input name="tbGrtGuarantybasic.partyNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:20" id="partyNum" enabled="false" />
						
						<label>抵质押人证件类型：</label>
						<input name="tbGrtGuarantybasic.certificateTypeCd" required="false" class="nui-buttonEdit nui-form-input" id="certificateTypeCd" vtype="maxLength:5" dictTypeId="CDKH0002" enabled="false" />
						
						<label>抵质押人证件号码：</label>
						<input name="tbGrtGuarantybasic.certificateCode" required="false" class="nui-textbox nui-form-input" vtype="maxLength:50" id="certificateCode" enabled="false" />
						
						<label>抵质押物分类：</label>
					<input name="tbGrtGuarantybasic.collType" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:100" id="tbGrtGuarantybasic.collType" enabled="true" dictTypeId="CDZC0005" />
							
					<label>抵质押类型：</label>
					<input name="tbGrtGuarantybasic.sortType" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:30" id="tbGrtGuarantybasic.sortType"  dictTypeId="XD_YWDB02" enabled="false"/>
					
					<label>评估方式：</label>
					<input name="tbGrtGuarantybasic.assessForm" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:2" id="assessForm" dictTypeId="YP_GLCD0010" />
					
					<label>评估日期：</label>
					<input name="tbGrtGuarantybasic.assessDate" required="true" class="nui-datepicker nui-form-input" vtype="maxLength:10" id="assessDate" format="yyyy-MM-dd" />
					
					<label>评估价值：</label>
					<input name="tbGrtGuarantybasic.assessValue" required="true" class="nui-textbox nui-form-input" vtype="float;maxLength:26;" id="assessValue" dataType="currency" />
					
					<label>币种：</label>
					<input name="tbGrtGuarantybasic.currencyCd" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:3" dictTypeId="CD000001" />
					
					<label>我行确认价值：</label>
					<input name="tbGrtGuarantybasic.mybankAffirmValue" id="mybankAffirmValue"  required="true" class="nui-textbox nui-form-input" vtype="float;maxLength:26;" dataType="currency" />
					
					<label>权利价值：</label>
					<input name="tbGrtGuarantybasic.mortgageValue" id="mortgageValue" dataType="currency" required="true" class="nui-textbox nui-form-input"  vtype="float;maxLength:26;" />
					
					<label>我行已设定担保额：</label>
					<input name="tbGrtGuarantybasic.mybankSetValue" id="mybankSetValue" dataType="currency" required="true" class="nui-textbox nui-form-input"  vtype="float;maxLength:26;" onblur="setRate"/>	
					
					<label>本次担保金额：</label>
					<input name="tbGrtGuarantybasic.suretyAmt" id="suretyAmt" dataType="currency" required="true" class="nui-textbox nui-form-input" vtype="float;maxLength:26;" onblur="setRate"/>	
					
					<label>抵押物状态：</label>
					<input name="tbGrtGuarantybasic.mortgageStatus" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:4" id="valuationForm" dicttypeid="XD_YWDB0132"  />
					
					<label>抵质押率(%)：</label>
					<input name="tbGrtGuarantybasic.mortgageRate" id="mortgageRate" required="true" class="nui-textbox nui-form-input" vtype="float;range:0,100;maxLength:8" enabled="false"/> 
					
					<!-- <label>是否存在共有人：</label>
					<input name="tbGrtGuarantybasic.ifOtherCommon" required="true" class="nui-dictcombobox nui-form-input" id="tbGrtGuarantybasic.ifOtherCommon" vtype="maxLength:1" dictTypeId="XD_0002" onItemclick="publicChange" /> -->
						
					<label>经办机构：</label>
					<input name="tbGrtGuarantybasic.orgNum" required="false" enabled="false" class="nui-text nui-form-input" vtype="maxLength:20" 
						value="<%=com.bos.pub.GitUtil.getCurrentOrgCd()%>" dictTypeId="org" />
							
					<label>经办人：</label>
					<input name="tbGrtGuarantybasic.userNum" required="false" enabled="false" class="nui-text nui-form-input" vtype="maxLength:50" 
						value="<%=com.bos.pub.GitUtil.getCurrentUserId()%>" dictTypeId="user"/>
					
					<label>创建日期：</label>
					<input name="tbGrtGuarantybasic.createTime" required="false" class="nui-textbox nui-form-input" vtype="maxLength:10" id="tbGrtGuarantybasic.createTime" 
						value="<%=com.bos.pub.GitUtil.getBusiDate() %>"  enabled="false" />
			
					<label>更新日期：</label>
					<input name="tbGrtGuarantybasic.updateTime" required="false" class="nui-textbox nui-form-input" vtype="maxLength:10" id="tbGrtGuarantybasic.updateTime" 
						value="<%=com.bos.pub.GitUtil.getBusiDate()%>"  enabled="false" />
						
					<!-- <label>评估方法：</label>
					<input name="tbGrtGuarantybasic.valuationForm" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:4" id="valuationForm" dicttypeid="YP_GLCD0011"  />
							
					<label>评估到期日：</label>
					<input name="tbGrtGuarantybasic.assessEndDate" required="false" class="nui-datepicker nui-form-input" vtype="maxLength:10" id="assessEndDate" format="yyyy-MM-dd" />
							
					-->
					</div>
					<div  style="border-bottom:0;width:99.5%; padding-right:30px;">
						<a class="nui-button" iconCls="icon-save" onclick="save()">保存</a>
					</div>
				</div>
			</fieldset>
		</div>
			<div class="nui-dynpanel" columns="1" id="table1" style="width:99.5%;">
				<fieldset>
				  	<legend>
				    	<span>抵押物详细信息</span>
				    </legend>
				    	<jsp:include page="<%=urlPath %>"></jsp:include>
				 </fieldset>
			 </div>
		</div>
	
		<div title="评估信息">
	        <div id="form2" style="width:100%;height:auto;overflow:hidden;">
				<label class="nui-hidden">担保品ID：</label>
				<input name="tbGrtGuarantybasic.suretyId" required="true" class="nui-hidden" vtype="maxLength:32"
					id="suretyId2"/>
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
					<input name="tbGrtGuarantybasic.guarantyIfFlaw" required="true" class="nui-dictcombobox nui-form-input"  id="guarantyIfFlaw" vtype="maxLength:2" dictTypeId="XD_0002" />
					
					<label>是否办理登记：</label>
					<input name="tbGrtGuarantybasic.isRegister" id="tbGrtGuarantybasic.isRegister" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:2" dictTypeId="YP_GLCD0145" />
				
					<label>是否办理足额保险：</label>
					<input name="tbGrtGuarantybasic.ifEnoughInsure" required="true" class="nui-dictcombobox nui-form-input" id="tbGrtGuarantybasic.ifEnoughInsure" vtype="maxLength:1" dictTypeId="XD_0002"/>
					
					<label>我行债权是否优先受偿：</label>
					<input name="tbGrtGuarantybasic.guarantyIfMyOne" required="true" class="nui-dictcombobox nui-form-input" id="tbGrtGuarantybasic.guarantyIfMyOne" vtype="maxLength:1" dictTypeId="XD_0002" onItemclick="guarantyIfMyOneChange"/>
				    
				    <label>前手债权金额：</label>
					<input name="tbGrtGuarantybasic.agoHandMoney" required="false" class="nui-textbox nui-form-input" id="tbGrtGuarantybasic.agoHandMoney" vtype="float;maxLength:26;" dataType="currency" />
			
					<label>是否具有较高的市场流动性：</label>
					<input name="tbGrtGuarantybasic.guarantyMobility" id="tbGrtGuarantybasic.guarantyMobility" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="YP_GLCD0016" vtype="maxLength:2" />
					
					
					<label>与债务人之间是否存在正相关关系：</label>
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
		var form2 = new nui.Form("#form2");
		//设置抵质押类型的选择值
		nui.get("tbGrtGuarantybasic.collType").setData(getDictData('CDZC0005','str','020101,020201'));
		//押品分类
		var sortType = "<%=request.getParameter("sortType")%>";
		//押品父类
		var parentSortType = "<%=request.getParameter("parentSortType")%>";
		//抵质押类型
		var collType = "<%=request.getParameter("collType")%>";
		//客户ID
		var partyId	="<%=request.getParameter("partyId")%>";
		
		//客户编号
		var partyNum	="<%=request.getParameter("partyNum")%>";
		//客户名称
		var partyName	="<%=request.getParameter("partyName")%>";
		//抵质押人证件类型
		var certType	="<%=request.getParameter("certType")%>";
		//抵质押人证件号码
		var certCode	="<%=request.getParameter("certCode")%>";
		
		
		//调用抵质押编号生成JS方法生成抵质押编号。
		git.mask();
		var suretyNum = getSuretyNum(sortType);
		nui.get("suretyNum").setValue(suretyNum);
		git.unmask();
	
		/**基础信息的初始化form表单方法**/
		function initForm2(suretyId) {
			var json=nui.encode({"tbGrtGuarantybasic":{"suretyId":suretyId}});	
			$.ajax({
	        	url: "com.bos.grt.grtMainManage.grtOuter.getOuterTbGrtGuarantybasic.biz.ext",
	        	type: 'POST',
	        	data: json,
	        	cache: false,
	        	contentType:'text/json',
	        	success: function (text) {
	           		form2.setData(text);
	           		//setQualifiedObjectEnabled();
	        	},
	        	error: function (jqXHR, textStatus, errorThrown) {
	            	nui.alert(jqXHR.responseText);
	        	}
	        });
	       }
	        	
		/**
		 * 保存基本信息
		 */
		function save() {
		    var suretyNum = nui.get("suretyNum");
		    if(suretyNum==""){
		    	nui.alert("抵质押品编号正在生成,请稍后");
		    	return;
		    }
			form.validate();
			if (form.isValid() == false) {
				nui.alert("请将信息填写完整");
				return;
			}
			var o=form.getData();
			var json=nui.encode(o);
			git.mask();
			$.ajax({
	        	url: "com.bos.grt.grtMainManage.grtOuter.addOuterTbGrtGuarantybasic.biz.ext",
	        	type: 'POST',
	        	data: json,
	        	cache: false,
	        	contentType:'text/json',
	        	success: function (text) {
	        		nui.alert(text.msg);
	        		if("保存成功！"==text.msg){
	        			var suretyId = text.tbGrtGuarantybasic.suretyId;
	        			//担保品ID
	        			nui.get("tbGrtGuarantybasic.suretyId").setValue(suretyId);
	        			nui.get("suretyId2").setValue(suretyId);
	        			initForm2(suretyId);
	        		} else {
	        			CloseWindow("ok");
	        		}
	        		/* var tabs = nui.get("tabs1");
		    		if(nui.get("tbGrtGuarantybasic.ifOtherCommon").getValue()=="1"){
	 					//共有人信息
	 					var publicInfo = tabs.getTab("publicInfo");
	 					tabs.removeTab(publicInfo);//删除publicInfo
	 					addTab5();
	 					var publicInfo = tabs.getTab("publicInfo");
	 					var publicTypeUrl = publicInfo.url.split("?")[0];
	 					publicTypeUrl = publicTypeUrl+"?suretyId="+suretyId +"&sortType=" + sortType;
	 					publicInfo.url = publicTypeUrl;
	 					tabs.updateTab(publicInfo);//更新tab标签页
	 				}else{
	 					var publicInfo = tabs.getTab("publicInfo");
	 					tabs.removeTab(publicInfo);//删除publicInfo
	 				} */
	        		git.unmask();
	        	},
	        	error: function (jqXHR, textStatus, errorThrown) {
	            	nui.alert(jqXHR.responseText);
	        	}
			});
		}
		
		
		/**
		 * 计算抵质押率
		 */
		function setRate(){
			 //本次担保金额
			var suretyAmt = nui.get("suretyAmt").getValue();
			//我行已设定担保金额			
			var mybankSetValue = nui.get("mybankSetValue").getValue();
			if(mybankSetValue!=""&&suretyAmt!=""){
				if(parseFloat(mybankSetValue)>=parseFloat(suretyAmt)){
					var rate= Math.round(suretyAmt/mybankSetValue*10000)/100;
					nui.get("mortgageRate").setValue(rate);
				}else{
					nui.get("suretyAmt").setValue("");
					nui.get("mybankSetValue").setValue("");
					nui.get("mortgageRate").setValue("");
					alert("本次担保金额不能大于我行设定担保额!");
				}
			} 
		}
		
		/**
		 * 初始化值
		 */
		function initCreateValue(){
			nui.get("tbGrtGuarantybasic.sortType").setValue(sortType);
			nui.get("tbGrtGuarantybasic.collType").setValue(collType);
			nui.get("partyNum").setValue(partyNum);
			nui.get("partyName").setText(partyName);
			nui.get("certificateTypeCd").setValue(certType);
			nui.get("certificateCode").setValue(certCode);
			nui.get("tbGrtGuarantybasic.partyId").setValue(partyId);
			
			
			//押品父类型编号
			nui.get("tbGrtGuarantybasic.parentSortType").setValue(parentSortType);
			//根据押品类型显示抵押物页面
			var json = nui.encode({"sortArgument":{"sortType":sortType}});
			git.mask();
			$.ajax({
				url: "com.bos.grt.grtMainManage.grtOuter.getOuterCollUrlBySortType.biz.ext",
				type:'POST',
				data: json,
				cache: false,
				contentType:'text/json',
				success: function (text) {
		    		var typeUrl = text.typeUrl;
		    		//addTab(typeUrl);//根据查询出的url加载tab标签
		    		//登记信息
					addTab3();
		    		var tabs = nui.get("tabs1");
		    		//如果共有人不为是，删除共有人页签
		    		/* if(nui.get("tbGrtGuarantybasic.ifOtherCommon").getValue()!="1"){
	 					var publicInfo = tabs.getTab("publicInfo");
	 					tabs.removeTab(publicInfo);//删除publicInfo
	 				}else{
	 					addTab5();
	 				} */
					git.unmask();
				},
				error: function () {
					nui.alert("操作失败！");
				}
			});
		}
		initCreateValue();		
		
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
	        	//nui.get("assessOrg").setRequired(true);
	        	nui.get("assessEndDate").setRequired(true);
	        }else{
	            //nui.get("assessOrg").setValue("");
	            //nui.get("assessOrg").setText("");
	            nui.get("assessEndDate").setValue("");
	            nui.get("assessEndDate").setText("");
	        	//nui.get("assessOrg").setRequired(false);
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
		 * 动态加载tab标签页:动态加载押品分类的页面
		 */
		 //押品详细信息和押品概括信息合并在押品概况信息页面----2015/06/04 By:钟辉
		/* function addTab(typeUrl) {
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
		} */
		
		/**
		 * 保管信息页签
		 */
		function addTab3() {
			var tabs = nui.get("tabs1");
			var tab = {title: "登记权证信息",name: "regInfo",url: nui.context+"/grt/manage/TbGrtRegcardinfo/TbGrtRegcardinfo_list.jsp"};
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
	 	 * 切换tab时
	 	 */
	 	function tabChange(e){
	 		var suretyId = nui.get("tbGrtGuarantybasic.suretyId").getValue();
	 		var collType=nui.get("tbGrtGuarantybasic.collType").getValue();
	 		
	 	/* 	var tabs = nui.get("tabs1");
	 			var collInfo = tabs.getTab("collInfo");
	 			var typeUrl = collInfo.url.split("?")[0];
	 			typeUrl = typeUrl+"?suretyId="+suretyId+"&sortType="+sortType+"&parentSortType="+parentSortType+"&collType="+collType;
	 			collInfo.url = typeUrl;
	 			tabs.updateTab(collInfo);//更新tab标签页 */
	 			
	 			//押品保管信息
	 			var tabs = nui.get("tabs1");
	 			var regInfo = tabs.getTab("regInfo");
	 			var regTypeUrl = regInfo.url.split("?")[0];
	 			regTypeUrl = regTypeUrl+"?suretyId="+suretyId+"&sortType="+sortType+"&collType="+collType;
	 			regInfo.url = regTypeUrl;
	 			tabs.updateTab(regInfo);//更新tab标签页
	 			
	 			if(nui.get("tbGrtGuarantybasic.ifOtherCommon").getValue()=="1"){
	 				//共有人信息
	 				var tabs = nui.get("tabs1");
	 				var publicInfo = tabs.getTab("publicInfo");
	 				var publicTypeUrl = publicInfo.url.split("?")[0];
	 				publicTypeUrl = publicTypeUrl+"?suretyId="+suretyId +"&sortType="+sortType+"&collType="+collType;
	 				publicInfo.url = publicTypeUrl;
	 				tabs.updateTab(publicInfo);//更新tab标签页
	 			}
	 		
	 		
	 	if(suretyId!="" && suretyId!=null){
	 			//押品详细信息
	 			/* var tabs = nui.get("tabs1");
	 			var collInfo = tabs.getTab("collInfo");
	 			var typeUrl = collInfo.url.split("?")[0];
	 			typeUrl = typeUrl+"?suretyId="+suretyId+"&sortType="+sortType+"&parentSortType="+parentSortType+"&collType="+collType;
	 			collInfo.url = typeUrl;
	 			tabs.updateTab(collInfo);//更新tab标签页 */
	 			
	 			//押品保管信息
	 			var tabs = nui.get("tabs1");
	 			var regInfo = tabs.getTab("regInfo");
	 			var regTypeUrl = regInfo.url.split("?")[0];
	 			regTypeUrl = regTypeUrl+"?suretyId="+suretyId+"&sortType="+sortType+"&collType="+collType;
	 			regInfo.url = regTypeUrl;
	 			tabs.updateTab(regInfo);//更新tab标签页
	 			
	 			if(nui.get("tbGrtGuarantybasic.ifOtherCommon").getValue()=="1"){
	 				//共有人信息
	 				var tabs = nui.get("tabs1");
	 				var publicInfo = tabs.getTab("publicInfo");
	 				var publicTypeUrl = publicInfo.url.split("?")[0];
	 				publicTypeUrl = publicTypeUrl+"?suretyId="+suretyId +"&sortType="+sortType+"&collType="+collType;
	 				publicInfo.url = publicTypeUrl;
	 				tabs.updateTab(publicInfo);//更新tab标签页
	 			}
	 		}else{
	 			alert("请先保存基本信息");
	 			e.cancel=true;
	 		}  
	 		
	 	/* 	var tabs = nui.get("tabs1");
	 			var collInfo = tabs.getTab("collInfo");
	 			var typeUrl = collInfo.url.split("?")[0];
	 			typeUrl = typeUrl+"?suretyId="+suretyId+"&sortType="+sortType+"&parentSortType="+parentSortType+"&collType="+collType;
	 			collInfo.url = typeUrl;
	 			tabs.updateTab(collInfo);//更新tab标签页 */
	 	}
		
		//-------------------------------------------------------其他附属信息----------------------------------------------------------
		var form2 = new nui.Form("#form2");
		
		/**
		 * 保存基本信息中的其他附属信息
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
		        url: "com.bos.grt.grtMainManage.grtOuter.updateOuterTbGrtGuarantybasic.biz.ext",
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
			var suretyId = nui.get("tbGrtGuarantybasic.suretyId").getValue();
			if(suretyId == "" || suretyId == null)return;
			if(nui.get("tbGrtGuarantybasic.ifOtherCommon").getValue()=="0"){
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
	        				nui.alert("存在共有人信息,不允许修改!");
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
		
		/**
		 * 【是否我行第一顺位】选择【否】时，需要填写前手债权金额
		 */
		function guarantyIfMyOneChange(){
			if(nui.get("tbGrtGuarantybasic.guarantyIfMyOne").getValue()=="0"){
				nui.get("tbGrtGuarantybasic.agoHandMoney").setRequired(true);
			}else{
				nui.get("tbGrtGuarantybasic.agoHandMoney").setValue("");
				nui.get("tbGrtGuarantybasic.agoHandMoney").required=false;
			}
		}
		
		/*
		 *点击窗口中的关闭按钮
		 */
		function CloseWindow(action) {            
			window.CloseOwnerWindow("ok");
		}
		
		
		//==============================================================================================
	    var form3 = new nui.Form("#form3"); 
		var grid = nui.get("grid3");
		if("1" == "<%=request.getParameter("view") %>"){
			nui.get("add").hide();
			nui.get("edit0").hide();
			nui.get("remove").hide();
		}
	    function search() {
			var suretyId = nui.get("tbGrtGuarantybasic.suretyId").getValue();
	        grid.load({"tbGrtHouse":{"suretyId":suretyId}});
	    }
	    search();
				
	    function reset(){
			form3.reset();
		}
		
	    function add() {
	    	var suretyId = nui.get("tbGrtGuarantybasic.suretyId").getValue();
	    	if(suretyId==""){
	    		nui.alert("请先保存概况信息!");
	    	}else{
	    		var suretyId=nui.get("tbGrtGuarantybasic.suretyId").getValue();
	    		nui.open({
	            url: nui.context+"/grt/manage/twouse/twouse_add.jsp?sortType="+"<%=request.getParameter("sortType")%>"+"&parentSortType="+"<%=request.getParameter("parentSortType")%>"+"&suretyId="+suretyId,
	            title: "新增", 
	            width: 800, 
	        	height: 600,
	        	allowResize: false,
	        	showMaxButton: true,
	            ondestroy: function (action) {
	                if(action=="ok"){
	                    search();
	                }
	            }
	        	}); 
	    	}
	    }
	    
	    function edit(v) {
	        var row = grid.getSelected();
	        var title1;
	        if(v == "0"){
				title1 = "编辑";        	
	        }else if(v == "1"){
	        	title1 = "查看";
	        }
	        if (row) {
	            nui.open({
	                url: nui.context+"/grt/manage/twouse/twouse_edit.jsp?suretyKeyId="+row.suretyKeyId+"&view="+v+"&suretyId="+row.suretyId,
	                title: title1, 
	                width: 800,
	        		height: 600,
	                allowResize: false,
	        		showMaxButton: true,
	                onload: function () {
	                    var iframe = this.getIFrameEl();
	                    var data = row;
	                    //iframe.contentWindow.SetData(data);
	                },
	                ondestroy: function (action) {
						search();
	                }
	            });
	        } else {
	            alert("请选中一条记录");
	        }
	        
	    }
	    
	    function remove() {
	        var row = grid.getSelected();
	        if (row) {
	        	nui.confirm("确定删除吗？","确认",function(action){
	            	if(action!="ok") return;
	            	var json=nui.encode({"tbGrtHouse":{"suretyKeyId":
	            		row.suretyKeyId,version:row.version}});
	            	git.mask();
	                $.ajax({
	                     url: "com.bos.grt.manage.house.delTbGrtHouse.biz.ext",
		                type: 'POST',
		                data: json,
		                cache: false,
		                contentType:'text/json',
	                    success: function (text) {
	                    	if (text.msg) {
	                    		nui.alert(text.msg);
	                    	}
	                        search();
	                        git.unmask();
	                    },
	                    error: function () {
	                    	nui.alert("操作失败！");
	                    }
	                });
	            }); 
	        } else {
	            alert("请选中一条记录");
	        }
	    }
	    
	    /**
	     * 导入
	     */
	    function showInput(){
			nui.open({
				url: nui.context+"/grt/grtImportExcel/import_TwoHouse.jsp?sortType=<%=request.getParameter("sortType")%>&suretyId=<%=request.getParameter("suretyId")%>&parentSortType=<%=request.getParameter("parentSortType")%>",
				title: "导入商住两用房", 
				width: 800, 
				height: 500,
				allowResize:false,
				showMaxButton: true,
				ondestroy: function (action) {
					if(action=="ok"){
						search();
					}
		  		}
			});
		}
    
	</script>
</body>
</html>