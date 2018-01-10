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
</head>
<script type="text/javascript" src="<%=contextPath%>/grt/grtJS/grt.js"></script>
<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;height:94%;" onbeforeactivechanged="tabChange">
	<div title="押品概况信息" id="basicTab" name="basicTab" style="width:90%;height:550px">   <!-- tabs start -->
		<div id="form1" style="width:100%;height:auto;overflow:hidden;">
				<input name="tbGrtGuarantybasic.userNum" required="true" class="nui-hidden" vtype="maxLength:50" 
					   id="tbGrtGuarantybasic.userNum"  width="200px" enabled="false" dictTypeId="user"  style="width:200px;"/>
				<input name="tbGrtGuarantybasic.updateUser" required="true" class="nui-hidden" vtype="maxLength:50"
					   id="tbGrtGuarantybasic.updateUser"  width="200px" enabled="false"  style="width:200px;"/>
				<input name="tbGrtGuarantybasic.updateOrg" required="true" class="nui-hidden" vtype="maxLength:10" 
						id="tbGrtGuarantybasic.updateOrg" width="200px" enabled="false"  style="width:200px;"/>
				<input name="tbGrtGuarantybasic.orgNum" required="true" class="nui-hidden" vtype="maxLength:30"
						id="tbGrtGuarantybasic.orgNum" width="200px" enabled="false"  style="width:200px;"/>
				<input name="tbGrtGuarantybasic.parentSortType" required="true" class="nui-hidden" vtype="maxLength:30"
						id="tbGrtGuarantybasic.parentSortType" width="200px" enabled="false"  style="width:200px;"/>
				<input name="tbGrtGuarantybasic.suretyId" required="true" class="nui-hidden" vtype="maxLength:50" 
					   id="tbGrtGuarantybasic.suretyId"  width="200px" enabled="false" dictTypeId="user"  style="width:200px;"/>
					   		
			<div id="panel1"  class="nui-dynpanel" columns="4">
				<label>抵质押编号：</label>
				<input name="tbGrtGuarantybasic.suretyNum" id="suretyNum" required="false" enabled="false" class="nui-textbox nui-form-input" vtype="maxLength:60" />
				
				<label>抵质押人客户编号：</label>
				<input name="tbGrtGuarantybasic.partyNum" id="partyNum" required="false" enabled="false" class="nui-textbox nui-form-input" vtype="maxLength:20" />
				
				<label>抵质押人证件类型：</label>
				<input name="tbGrtGuarantybasic.certificateTypeCd" id="certificateTypeCd" required="false" class="nui-dictcombobox nui-form-input" 
						dictTypeId="CDKH0002" vtype="maxLength:5" enabled="false" />
				
				<label>抵质押人证件号码：</label>
				<input name="tbGrtGuarantybasic.certificateCode" id="certificateCode" required="false" class="nui-textbox nui-form-input" enabled="false" vtype="maxLength:50" />
				
				<label>抵质押人名称：</label>
				<input name="tbGrtGuarantybasic.partyName" id="partyName" required="false" class="nui-buttonEdit nui-form-input" onbuttonclick="chooiseParty" vtype="maxLength:300" allowinput="false"/>
				
				<label>抵质押物类型：</label>
				<input name="tbGrtGuarantybasic.sortType" id="sortType" required="false" class="nui-dictcombobox nui-form-input" dictTypeId="XD_DBCD4002"
					 enabled="false" vtype="maxLength:9" />
				
				<label>抵质押类型：</label>
				<input name="tbGrtGuarantybasic.collType" id="tbGrtGuarantybasic.collType" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:100" enabled="false"
					dictTypeId="CDZC0005" />
				
				<label>创建日期：</label>
				<input name="tbGrtGuarantybasic.createTime" id="tbGrtGuarantybasic.createTime" required="false" enabled="false" 
					class="nui-textbox nui-form-input" format="yyyy-MM-dd"   vtype="maxLength:10" />
				
				<label>创建机构：</label>
				<input  required="false" enabled="false" class="nui-textbox nui-form-input" value="<%=userObject.getUserOrgName() %>" vtype="maxLength:20" />
					
				<label>创建人：</label>
				<input  required="false" enabled="false" class="nui-textbox nui-form-input" value="<%=userObject.getUserName() %>" vtype="maxLength:50" />
				
				<label>更新日期：</label>
				<input name="tbGrtGuarantybasic.updateTime" id="tbGrtGuarantybasic.updateTime" required="false" enabled="false" 
					class="nui-textbox nui-form-input" format="yyyy-MM-dd"   vtype="maxLength:10" />
				
				<label>更新机构：</label>
				<input required="false" enabled="false" value="<%=userObject.getUserOrgName() %>" class="nui-textbox nui-form-input" vtype="maxLength:10" />
				
				<label>更新人：</label>
				<input  required="false" enabled="false" class="nui-textbox nui-form-input" value="<%=userObject.getUserName() %>" vtype="maxLength:50" />
				
			</div>
		</div>
		<div class="nui-toolbar" style="border-bottom:0;text-align:center;">
			<a class="nui-button" iconCls="icon-save" onclick="save()">保存</a>
		</div>	
	</div>
</div>	    
<div class="nui-toolbar" style="border-bottom:0;text-align:center;">
	<a class="nui-button" iconCls="icon-close" onclick="closeok">关闭</a>
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
		var suretyNum = getSuretyNum(sortType);
		nui.get("suretyNum").setValue(suretyNum);
		
function save() {
	form.validate();
	if (form.isValid() == false) {
		nui.alert("请将信息填写完整");
		return;
	}
	
	var o=form.getData();
	var json=nui.encode(o);

	$.ajax({
        url: "com.bos.grt.grtMainManage.grtOuter.addOuterTbGrtGuarantybasic.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if("保存成功！"==text.msg){
        		var suretyId = text.tbGrtGuarantybasic.suretyId;
        		nui.get("tbGrtGuarantybasic.suretyId").setValue(suretyId);
        	} else {
        		CloseWindow("ok");
        	}
        },
        error: function (jqXHR, textStatus, errorThrown) {
            nui.alert(jqXHR.responseText);
        }
	});
}		
		//初始化值
		function initCreateValue(){
			nui.get("tbGrtGuarantybasic.updateTime").setValue("<%=com.bos.pub.GitUtil.getBusiDate()%>");
			nui.get("tbGrtGuarantybasic.updateOrg").setValue("<%=com.bos.pub.GitUtil.getCurrentOrgCd()%>");
			nui.get("tbGrtGuarantybasic.updateUser").setValue("<%=com.bos.pub.GitUtil.getCurrentUserId()%>");
			nui.get("sortType").setValue(sortType);
			nui.get("tbGrtGuarantybasic.orgNum").setValue("<%=com.bos.pub.GitUtil.getCurrentOrgCd()%>");
			nui.get("tbGrtGuarantybasic.userNum").setValue("<%=com.bos.pub.GitUtil.getCurrentUserId()%>");
			nui.get("tbGrtGuarantybasic.createTime").setValue("<%=com.bos.pub.GitUtil.getBusiDate()%>");
			
			//默认显示贷款申请人为保证金提供者
			getCusInfo();
			
			var collType = "<%=request.getParameter("collType") %>";
			nui.get("tbGrtGuarantybasic.collType").setValue(collType);
			//押品父类型编号
			nui.get("tbGrtGuarantybasic.parentSortType").setValue("<%=request.getParameter("parentSortType")%>");
        		
			//根据押品类型显示抵押物页面
        	var json  = nui.encode({"sortArgument":{"sortType":"<%=request.getParameter("sortType") %>"}});
        	$.ajax({
        		url: "com.bos.grt.grtMainManage.grtApply.getApplyCollUrlBySortType.biz.ext",
        		type:'POST',
        		data: json,
	    		cache: false,
	   		    contentType:'text/json',
	   		    success: function (text) {
	    			var typeUrl = text.typeUrl;
	    			addTab(typeUrl);//根据查询出的url加载tab标签
	    			
	    		},
	            error: function () {
	            	nui.alert("操作失败！");
	            }
        	});
        	
		}
       initCreateValue();		
		
 		//动态加载tab标签页
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
	 * 抵质押人
	 */
	function chooiseParty(){
		nui.open({
			url: nui.context+"/grt/grtMainCustomer/grtCus_chooise.jsp",
			title: "选择抵质押人", 
			width: 800, 
			height: 600,
			allowResize:false,
	        allowDrag: false,
			showMaxButton: false,
			ondestroy: function (action) {
				//客户主键		partyId
				//客户编号		partyNum
				//客户名称		partyName
				//定义客户证件类型	certificateTypeCd
				//定义客户证件号码	certificateCode	
				//var str = [partyId,partyNum,partyName,certificateTypeCd,certificateCode,partyTypeCd];
				//nui.get("tbGrtSurety.partyId").setValue(action[0]);
				nui.get("partyNum").setValue(action[1]);
				nui.get("certificateTypeCd").setValue(action[3]);
				nui.get("certificateCode").setValue(action[4]);
				nui.get("partyName").setValue(action[2]);
				nui.get("partyName").setText(action[2]);
			}
		}); 
	}
	
	function getCusInfo(){
		var json = nui.encode({"party":{"partyId":partyId}});
		if(partyId==null || partyId=="" ||partyId=="null"){
			return;
		}
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
	    		},
	            error: function () {
	            	nui.alert("操作失败！");
	            }
        	});
	}
 	
 	//切换tab时
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
 			alert("请先保存基本信息");
 			e.cancel=true;
 		}
 	}
 	
 	  /**
	 * 点击关闭按钮，关闭窗口	
	 */
	function closeok(){
		CloseWindow("ok");
	}
	
	function CloseWindow(action) {            
		window.CloseOwnerWindow("ok");
	}
	
	</script>
</body>
</html>