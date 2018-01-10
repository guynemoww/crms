<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): menglei@git.com.cn
  - Date: 2014-04-29 onbeforeactivechanged=""
  - Description:TB_GRT_GENERAL_APPINFO, com.bos.dataset.grt.tbGrtGuarantybasic
-->
<head>
	<%@include file="/common/nui/common.jsp" %>
	<title>新增业务申请下的质押品--保证金</title>
</head>
<script type="text/javascript" src="<%=contextPath%>/grt/grtJS/grt.js"></script>
<body>
	<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;height:94%;" onbeforeactivechanged="tabChange">
		<div title="押品概况信息" id="basicTab" name="basicTab" style="width:90%;height:550px">   <!-- tabs start -->
			<div id="form1" style="width:100%;height:auto;overflow:hidden;">
				<input name="tbGrtGuarantybasic.parentSortType" required="true" class="nui-hidden" vtype="maxLength:30"
					id="tbGrtGuarantybasic.parentSortType" enabled="false" />
				<input name="tbGrtGuarantybasic.suretyId" required="true" class="nui-hidden" vtype="maxLength:50" 
					id="tbGrtGuarantybasic.suretyId" enabled="false"/>
				<input name="tbGrtSurety.partyId" required="true" class="nui-hidden" vtype="maxLength:50" 
					id="tbGrtSurety.partyId" enabled="false"/>
				<!-- 业务申请信息 -->
				<input id="bizCustType" name="bizCustType" required="false" value="<%=request.getParameter("bizCustType") %>" class="nui-hidden" />
				<input name="tbBizRelation.applyId" value="<%=request.getParameter("applyId") %>" required="false" class="nui-hidden" />
				<input name="tbBizRelation.reType" required="false" class="nui-hidden" value="<%=request.getParameter("reType") %>"
					id="tbBizRelation.reType"  />	  
				<input name="tbBizRelation.relationId" required="false" class="nui-hidden" id="tbBizRelation.relationId" /> 	   		
				<div id="panel1"  class="nui-dynpanel" columns="4">
					<label>抵质押编号：</label>
					<input name="tbGrtGuarantybasic.suretyNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:60" 
						id="suretyNum" enabled="false" />
					
					<label>抵质押人名称：</label>
					<input name="tbGrtGuarantybasic.partyName" required="true" class="nui-buttonEdit nui-form-input" 
						id="partyName" onbuttonclick="chooiseParty" vtype="maxLength:300" allowinput="false"/>
					
					<label>抵质押人客户编号：</label>
					<input name="tbGrtGuarantybasic.partyNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:20" 
						id="partyNum" enabled="false" />
					
					<label>抵质押人证件类型：</label>
					<input name="tbGrtGuarantybasic.certificateTypeCd" required="false" class="nui-dictcombobox nui-form-input" 
						id="certificateTypeCd" dictTypeId="CDKH0002" vtype="maxLength:5" enabled="false" />
					
					<label>抵质押人证件号码：</label>
					<input name="tbGrtGuarantybasic.certificateCode" required="false" class="nui-textbox nui-form-input" 
						id="certificateCode" enabled="false" vtype="maxLength:50" />
					
					<label>抵质押物类型：</label>
					<input name="tbGrtGuarantybasic.sortType" required="false" class="nui-dictcombobox nui-form-input" 
						id="sortType" enabled="false" dictTypeId="XD_DBCD4002" vtype="maxLength:9" />
					
					<label>抵质押类型：</label>
					<input name="tbGrtGuarantybasic.collType" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:100"
						id="tbGrtGuarantybasic.collType" dictTypeId="CDZC0005" enabled="false" />
				
					<label>保证金比例(%)：</label>
					<input name="tbGrtGuarantybasic.guaMoneyProportion" required="true" class="nui-textbox nui-form-input" vtype="float;maxLength:26;range:0,100;" 
						id="guaMoneyProportion" />
					
					<label>创建人：</label>
					<input name="tbGrtGuarantybasic.userNum" required="false" class="nui-text nui-form-input" vtype="maxLength:50" 
						value="<%=com.bos.pub.GitUtil.getCurrentUserId()%>" dictTypeId="user" enabled="false" />
					
					<label>更新人：</label>
					<input name="tbGrtGuarantybasic.updateUser" required="false" class="nui-text nui-form-input" vtype="maxLength:50" 
						value="<%=com.bos.pub.GitUtil.getCurrentUserId()%>" dictTypeId="user" enabled="false" />
						
					<label>创建机构：</label>
					<input name="tbGrtGuarantybasic.orgNum" required="false" class="nui-text nui-form-input" vtype="maxLength:20" 
						value="<%=com.bos.pub.GitUtil.getCurrentOrgCd()%>" dictTypeId="org" enabled="false" />
						
					<label>更新机构：</label>
					<input name="tbGrtGuarantybasic.updateOrg" required="false" class="nui-text nui-form-input" vtype="maxLength:10" 
						value="<%=com.bos.pub.GitUtil.getCurrentOrgCd()%>" dictTypeId="org" enabled="false" />
						
					<label>创建日期：</label>
					<input name="tbGrtGuarantybasic.createTime" required="false" class="nui-textbox nui-form-input" vtype="maxLength:10"
						id="tbGrtGuarantybasic.createTime" value="<%=com.bos.pub.GitUtil.getBusiDate() %>"  enabled="false" />
					
					<label>更新日期：</label>
					<input name="tbGrtGuarantybasic.updateTime" required="false" class="nui-textbox nui-form-input" vtype="maxLength:10" 
						id="tbGrtGuarantybasic.updateTime" value="<%=com.bos.pub.GitUtil.getBusiDate()%>" enabled="false"  />
				</div>
			</div>
			<div class="nui-toolbar" style="border-bottom:0;text-align:center;">
				<a class="nui-button" iconCls="icon-save" onclick="save()" id="save">保存</a>
			</div>	
		</div>
	</div>	    

	<script type="text/javascript">
	 	nui.parse();
	    var form = new nui.Form("#form1");
	    //设置抵质押类型的选择值
	    nui.get("tbGrtGuarantybasic.collType").setData(getDictData('CDZC0005','str','020101,020201'));
	    var sortType = "<%=request.getParameter("sortType")%>";
	    var parentSortType = "<%=request.getParameter("parentSortType")%>";
	    var partyId = "<%=request.getParameter("partyId")%>";
	    var	suretyId ;
	    var relationId;
	    //调用抵质押编号生成JS方法生成抵质押编号。
	    git.mask();
		var suretyNum = getSuretyNum(sortType);
		nui.get("suretyNum").setValue(suretyNum);
		git.unmask();
		
		/**
	     * 新增保存
	     */
		function save() {
		    var suretyNum = nui.get("suretyNum").getValue();
		    if(suretyNum==""){
		       nui.alert("抵质押品编号正在生成,请稍后");
		       return;
		    }
			form.validate();
			if (form.isValid() == false) {
				nui.alert("请将信息填写完整");
				return;
			}
			if(relationId == "" || relationId == null){
				var o=form.getData();
				var relationIdjsp = nui.get("tbBizRelation.relationId").getValue();
				var json=nui.encode(o);
				git.mask();
				$.ajax({
			        url: "com.bos.grt.grtMainMargin.grtApply.addMarginApplyTbGrtGuarantybasic.biz.ext",
			        type: 'POST',
			        data: json,
			        cache: false,
			        contentType:'text/json',
			        success: function (text) {
			        	if("保存成功！"==text.msg){
			        		var suretyId = text.tbGrtGuarantybasic.suretyId;
			        		nui.get("tbGrtGuarantybasic.suretyId").setValue(suretyId);
			        		relationId = text.relationId[0];
			        		nui.get("tbBizRelation.relationId").setValue(relationId);
			        		if(relationId != "" || relationId != null){
			        			updateAppRelationInfo(relationId);//更新
			        		}else{
			        			sendToApp();//插入
			        		}
			        	} else {
			        		CloseWindow("ok");
			        	}
			        	git.unmask();
			        },
			        error: function (jqXHR, textStatus, errorThrown) {
			            nui.alert(jqXHR.responseText);
			        }
				});
			}else{
				var o=form.getData();
				var relationIdjsp = nui.get("tbBizRelation.relationId").getValue();
				var guaMoneyProportion = nui.get("guaMoneyProportion").value;
				var json=nui.encode(o)+nui.encode({"relation":{"guaMoneyProportion":guaMoneyProportion,"relationId":relationId},
		 			"bizCustType":"<%=request.getParameter("bizCustType") %>"});
				git.mask();
				$.ajax({
			        url: "com.bos.grt.grtMainMargin.grtApply.addMarginApplyTbGrtGuarantybasic2.biz.ext",
			        type: 'POST',
			        data: json,
			        cache: false,
			        contentType:'text/json',
			        success: function (text) {
			        	if("保存成功！"==text.msg){
			        		var suretyId = text.tbGrtGuarantybasic.suretyId;
			        		nui.get("tbGrtGuarantybasic.suretyId").setValue(suretyId);
			        		relationId = text.relationId;
			        		nui.get("tbBizRelation.relationId").setValue(relationId);
			        		if(relationId != "" || relationId != null){
			        			updateAppRelationInfo(relationId);//更新
			        		}else{
			        			sendToApp();//插入
			        		}
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
		}
		
		/**
		 * 初始化值
		 */
		function initCreateValue(){
			nui.get("sortType").setValue(sortType);
			//默认显示贷款申请人为保证金提供者
			getCusInfo();
			var collType = "<%=request.getParameter("collType") %>";
			nui.get("tbGrtGuarantybasic.collType").setValue(collType);
			//押品父类型编号
			nui.get("tbGrtGuarantybasic.parentSortType").setValue("<%=request.getParameter("parentSortType")%>");
			nui.get("tbGrtSurety.partyId").setValue("<%=request.getParameter("partyId")%>");
			//根据押品类型显示抵押物页面
        	var json  = nui.encode({"sortArgument":{"sortType":"<%=request.getParameter("sortType") %>"}});
        	git.mask();
        	$.ajax({
        		url: "com.bos.grt.grtMainMargin.grtApply.getApplyCollUrlBySortType.biz.ext",
        		type:'POST',
        		data: json,
	    		cache: false,
	   		    contentType:'text/json',
	   		    success: function (text) {
	    			var typeUrl = text.typeUrl;
	    			addTab(typeUrl);//根据查询出的url加载tab标签
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
                //如果禁止销毁的时候，自动active一个新table.autoActive = false;
            }
 			tabs.addTab(tab);
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
					if(action != "ok"){
						nui.get("tbGrtSurety.partyId").setValue(action[0]);
						nui.get("partyNum").setValue(action[1]);
						nui.get("certificateTypeCd").setValue(action[3]);
						nui.get("certificateCode").setValue(action[4]);
						nui.get("partyName").setValue(action[2]);
						nui.get("partyName").setText(action[2]);
					}
				}
			}); 
		}
	
		function getCusInfo(){
			var json = nui.encode({"party":{"partyId":partyId}});
			if(partyId==null || partyId=="" || partyId=="null"){
				return;
			}
			git.mask();
			$.ajax({
        		url: "com.bos.csm.pub.crudCustInfo.getCustByIdCardOrName.biz.ext",
        		type:'POST',
        		data: json,
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
		}
 	
	 	/**
	 	 * 切换tab时
	 	 */
	 	function tabChange(e){
	 		var suretyId = nui.get("tbGrtGuarantybasic.suretyId").value;
	 		if(suretyId!="" && suretyId!=null){
	 			var tabs = nui.get("tabs1");
	 			var collInfo = tabs.getTab("collInfo")
	 			var typeUrl = collInfo.url;
	 			typeUrl = typeUrl+"?suretyId="+suretyId +"&sortType=" + sortType;
	 			collInfo.url = typeUrl;
	 			tabs.updateTab(collInfo);//更新tab标签页
	 		}else{
	 			nui.alert("请先保存基本信息");
	 			e.cancel=true;
	 		}
	 	}
 	
	 	/**
	 	 * 向申请插入
	 	 */
	 	function sendToApp(){
	 		var reType = "<%=request.getParameter("reType") %>";
	 		var guaMoneyProportion = nui.get("guaMoneyProportion").value;//保证金比例
	 		var json = nui.encode({"rows":[{"suretyId":nui.get("tbGrtGuarantybasic.suretyId").value,
	 		"guaMoneyProportion":guaMoneyProportion}],"reType":reType,"bizCustType":"<%=request.getParameter("bizCustType") %>",
	 		"applyId":"<%=request.getParameter("applyId") %>"});
			git.mask();
			$.ajax({
	       		url: "com.bos.comm.biz.Collateral.insertCollateralOfGuaranty.biz.ext",
	       		type: 'POST',
	       		data: json,
	       		cache: false,
	       		contentType:'text/json',
	       		success: function (text) {
	       			nui.alert(text.msg);
	       			git.unmask();
	       		},
	       		error: function (jqXHR, textStatus, errorThrown) {
					nui.alert(jqXHR.responseText);
	       		}
			});
	 	}
 	
	 	/**
	 	 * 更新申请关联信息
	 	 */
	 	function updateAppRelationInfo(relationId){
	 		var reType = "<%=request.getParameter("reType") %>";
	 		var guaMoneyProportion = nui.get("guaMoneyProportion").value;
	 		var json = nui.encode({"relation":{"guaMoneyProportion":guaMoneyProportion,"relationId":relationId},
	 			"bizCustType":"<%=request.getParameter("bizCustType") %>"});
	 		git.mask();
			$.ajax({
	       		url: "com.bos.comm.biz.Collateral.updateCollateral.biz.ext",
	       		type: 'POST',
	       		data: json,
	       		cache: false,
	       		contentType:'text/json',
	       		success: function (text) {
	       			nui.alert(text.msg);
	       			git.unmask();
	       		},
	       		error: function (jqXHR, textStatus, errorThrown) {
					nui.alert(jqXHR.responseText);
	       		}
			});
	 	}
 	
		/**
		 * 点击关闭按钮，关闭窗口	
		 */
		function CloseWindow(action) {            
			window.CloseOwnerWindow("ok");
		}
		
	</script>
</body>
</html>