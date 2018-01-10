<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-04-29

  - Description:TB_GRT_GENERAL_APPINFO, com.bos.dataset.grt.TbGrtGeneralAppinfo-->
<head>
	<%@include file="/common/nui/common.jsp" %>
	<title>编辑业务申请下的质押品--保证金</title>
</head>
<script type="text/javascript" src="<%=contextPath%>/grt/grtJS/grt.js"></script>
<body>
	<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;height:94%;">
		<div title="押品概况信息" id="basicTab" name="basicTab" style="width:90%;height:550px">   <!-- tabs start -->
			<div id="form1" style="width:100%;height:auto;overflow:hidden;">
				<input name="tbGrtGuarantybasic.parentSortType" required="true" class="nui-hidden" vtype="maxLength:30"
					id="tbGrtGuarantybasic.parentSortType" enabled="false" />
				<input name="tbGrtGuarantybasic.suretyId" required="true" class="nui-hidden" vtype="maxLength:50" 
					id="tbGrtGuarantybasic.suretyId" enabled="false"/>
				<div id="panel1"  class="nui-dynpanel" columns="4">
					<label>抵质押编号：</label>
					<input name="tbGrtGuarantybasic.suretyNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:60" 
						id="suretyNum" enabled="false" />
					
					<label>抵质押人名称：</label>
					<input name="tbGrtGuarantybasic.partyName" required="false" class="nui-textbox nui-form-input" vtype="maxLength:300" 
						id="partyName" enabled="false" allowinput="false"/>
					
					<label>抵质押人客户编号：</label>
					<input name="tbGrtGuarantybasic.partyNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:20" 
						id="partyNum" enabled="false" />
					
					<label>抵质押人证件类型：</label>
					<input name="tbGrtGuarantybasic.certificateTypeCd" required="false" class="nui-dictcombobox nui-form-input" 
						id="certificateTypeCd" dictTypeId="CDKH0002" vtype="maxLength:5" enabled="false" />
					
					<label>抵质押人证件号码：</label>
					<input name="tbGrtGuarantybasic.certificateCode" required="false" class="nui-textbox nui-form-input" vtype="maxLength:50" 
						id="certificateCode" enabled="false" />
					
					<label>抵质押物类型：</label>
					<input name="tbGrtGuarantybasic.sortType" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:9" 
						id="sortType"  enabled="false" dictTypeId="XD_DBCD4002" />
					
					<label>抵质押类型：</label>
					<input name="tbGrtGuarantybasic.collType" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:100" 
						id="tbGrtGuarantybasic.collType" dictTypeId="CDZC0005" enabled="false" />
				
					<label>保证金比例(%)：</label>
					<input name="guaMoneyProportion" required="true" class="nui-textbox nui-form-input" vtype="float;maxLength:26;range:0,100;"
						id="guaMoneyProportion" />
					
					<label>创建人：</label>
					<input name="tbGrtGuarantybasic.userNum" required="false" class="nui-text nui-form-input" vtype="maxLength:50" enabled="false" 
						dictTypeId="user" />
					
					<label>更新人：</label>
					<input name="tbGrtGuarantybasic.updateUser" required="false" class="nui-text nui-form-input" vtype="maxLength:50" 
						enabled="false" dictTypeId="user" />
						
					<label>创建机构：</label>
					<input name="tbGrtGuarantybasic.orgNum" required="false" class="nui-text nui-form-input" vtype="maxLength:20" 
						enabled="false" dictTypeId="org" />
						
					<label>更新机构：</label>
					<input name="tbGrtGuarantybasic.updateOrg" required="false" class="nui-text nui-form-input" vtype="maxLength:10" 
						enabled="false" dictTypeId="org" />
					
					<label>创建日期：</label>
					<input name="tbGrtGuarantybasic.createTime" required="false" class="nui-textbox nui-form-input" vtype="maxLength:10" 
						id="tbGrtGuarantybasic.createTime" dateformat="yyyy-MM-dd" enabled="false" />
					
					<label>更新日期：</label>
					<input name="tbGrtGuarantybasic.updateTime" required="false" class="nui-textbox nui-form-input" vtype="maxLength:10" 
						id="tbGrtGuarantybasic.updateTime" dateformat="yyyy-MM-dd" enabled="false" />
				</div>
			</div>
			<div class="nui-toolbar" style="border-bottom:0;text-align:center;">
				<a class="nui-button" iconCls="icon-save" onclick="save()" id="save1">保存</a>
			</div>	
		</div>
	</div>	
			
    <script type="text/javascript">
	 	nui.parse();
	    var form = new nui.Form("#form1");
	    var view = "<%=request.getParameter("view")%>";
		if (view=="1") {
			form.setEnabled(false);
			nui.get("save1").hide();
		}
		//设置抵质押类型的选择值
	    nui.get("tbGrtGuarantybasic.collType").setData(getDictData('CDZC0005','str','020101,020201'));
		var sortType;
	
		/**
		 * 基础信息的初始化form表单方法
		 */
		function initForm() {
			var json=nui.encode({"tbGrtGuarantybasic":{"suretyId":"<%=request.getParameter("suretyId") %>"}});
			git.mask();
			$.ajax({
		        url: "com.bos.grt.grtMainMargin.grtApply.getMarginApplyTbGrtGuarantybasic.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        contentType:'text/json',
		        success: function (text) {
		        	if(text.msg){
		        		nui.alert(text.msg);
		        	} else {
		        		form.setData(text);
		        		sortType=text.tbGrtGuarantybasic.sortType;
		        		//申请传递的本次担保债权金额
		        		nui.get("guaMoneyProportion").setValue("<%=request.getParameter("guaMoneyProportion") %>");
		        		//根据押品类型显示抵押物页面
			        	var json  = nui.encode({"sortArgument":{"sortType":sortType}});
			        	
			        	$.ajax({
			        		url: "com.bos.grt.grtMainManage.grtApply.getApplyCollUrlBySortType.biz.ext",
			        		type:'POST',
			        		data: json,
				    		cache: false,
				   		    contentType:'text/json',
				   		    success: function (text) {
				    			var typeUrl = text.typeUrl;
				    			var suretyId = "<%=request.getParameter("suretyId") %>"
				    			typeUrl = typeUrl+"?suretyId="+suretyId +"&sortType=" + sortType+"&view="+view;
				    			addTab(typeUrl);//根据查询出的url加载tab标签
				    			
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
			var json=nui.encode(o);
			git.mask();
			$.ajax({
		        url: "com.bos.grt.grtMainMargin.grtApply.addMarginApplyTbGrtGuarantybasic.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        contentType:'text/json',
		        success: function (text) {
		        	if(text.msg){
		        		sendToApp();
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
	 	 * 向申请更新
	 	 */
	 	function sendToApp(){
	 		var reType = "<%=request.getParameter("reType") %>";
	 		var guaMoneyProportion = nui.get("guaMoneyProportion").value;
	 		var json = nui.encode({"relation":{"guaMoneyProportion":guaMoneyProportion,
	 			"relationId":"<%=request.getParameter("relationId") %>"},"bizCustType":"<%=request.getParameter("bizCustType") %>"});
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