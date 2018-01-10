<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@page import="com.bos.pub.GitUtil"%>
<html>
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2013-11-28

  - Description:TB_CSM_ADDRESS, com.bos.dataset.csm.TbCsmAddress-->
<head>
<%@include file="/common/nui/common.jsp" %>
<script type="text/javascript" src="<%=contextPath%>/csm/js/csmValidate.js"></script>
</head>
<body>
<center>
<div id="form1" style="width:99.5%;height:auto;overflow:hidden;">
	<input name="item.partyId" id="item.partyId" class="nui-hidden" />
	<input name="item.certificateId" id="item.certificateId" class="nui-hidden" />
	<fieldset>
	  <legend>
	    <span>证件信息</span>
	   </legend>
	<div class="nui-dynpanel" columns="4">
			<label>证件类型：</label>
			<input id="item.certificateTypeCd" Enabled="false" name="item.certificateTypeCd" textField="dictname" valueField="dictid"  required="true" 
			 class="nui-dictcombobox nui-form-input" dictTypeId="CDKH0002"  />
	
			<label>证件号码：</label>
			<input id="item.certificateCode"  name="item.certificateCode" required="true" class="nui-textbox nui-form-input" onvalidation="onValidateCertificateCode" />
	
			<label>签发日期：</label>
			<input id="item.signDate"  name="item.signDate" required="true" class="nui-datepicker nui-form-input" format="yyyy-MM-dd" />
	
			<label>到期日期：</label>
			<input id="item.endDate" name="item.endDate" required="true"class="nui-datepicker nui-form-input" format="yyyy-MM-dd" />
		</div>
    </fieldset>
	<div class="nui-toolbar" style="border:0;text-align:right;padding-right:20px;">
		<a id="btnSave" class="nui-button" iconCls="icon-save" onclick="save()">保存</a>
		<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
	</div>
</div>
</center>				

	    
			
<script type="text/javascript">
 	nui.parse();
 	git.mask("form1");
    var form = new nui.Form("#form1");
	if ("<%=request.getParameter("view") %>"=="1") {
		form.setEnabled(false);
		nui.get("btnSave").hide();
	}
	var certificateTypeCd = "<%=request.getParameter("certificateTypeCd") %>";
	//初始化证件类型
	 function init(){
	 		git.mask("form1");
	 		var cType = "<%=request.getParameter("cType") %>";
	 		var json = null;
	 		if("1"==cType){
	 		   json = nui.encode({parentId:"10000"});
	 		}else{
	 		   json = nui.encode({parentId:"20000"});
	 		}
	        
	         $.ajax({
	            url: "com.bos.csm.pub.getDict.getCertTypeDict.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	                git.unmask("form1");
	                nui.get("item.certificateTypeCd").setData(text.levels);
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                git.unmask("form1");
	                nui.alert(jqXHR.responseText);
	            }
		     });
	      }
     init();

	function initForm() {
		var json=nui.encode({"certificateId":"<%=request.getParameter("itemId") %>"});
		$.ajax({
	            url: "com.bos.csm.common.certInfo.getCert.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            git.unmask("form1");
	            	if(text.msg){
	            		nui.alert(text.msg);
	            	} else {
	            		form.setData(text);
	            		window.form1Data=form.getData();
	            		nui.get("item.certificateTypeCd").value=text.item.certificateTypeCd;
	            	}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	            git.unmask("form1");
	                nui.alert(jqXHR.responseText);
	            }
		});
	}
	initForm();
	
	var data;
	function save() {
		var curDate="<%=GitUtil.getBusiDateStr()%>";
        var signDate = nui.get("item.signDate").value;
        var signDateStr=null;
        if(null != signDate){
        	signDateStr = nui.formatDate(signDate, "yyyy-MM-dd");
	        if(signDateStr>curDate){
		        alert("证件签发日期不能大于当前日期！");
		        return;
		    } 
        }
	    var endDate = nui.get("item.endDate").value;
	    if(signDate>endDate){
	        alert("证件签发日期不能大于到期日期！");
	        return;
	    } 
	
		form.validate();
		if (form.isValid() == false) {
			nui.alert("请将信息填写完整");
			return;
		}
		//git.mask("form1");
		var o=form.getData();
		var json=nui.encode(o);
		//nui.alert(json);return;
		//需要走流程，所以注释掉，不允许修改
		//此处影响到营业执照号码的修改，需要判断修改组织机构代码时才走流程，其他情况不走流程
		if('20001'==certificateTypeCd){
			
		}else{
			$.ajax({
	            url: "com.bos.csm.common.certInfo.saveCert.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            git.unmask("form1");
	            	if(text.msg){
	            		nui.alert(text.msg);
	            	} else {
	            	if(certificateTypeCd!="null"){
							 
	            	}
	            		CloseWindow("ok");
	            	}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                git.unmask("form1");
	                nui.alert(jqXHR.responseText);
	            }
		});
		}
		data = form.getData();
		CloseWindow("ok");
	}
	
	//证件验证
	function onValidateCertificateCode(e){
		var o2 = nui.get("item.certificateCode").getValue();  //修改后的证件号码
		if(window.form1Data==null || window.form1Data=="") {
			return;
		}
        var o1=window.form1Data.item["certificateCode"];  //原始数据
        if(o1==o2){
        }else{
			var certificateTypeCdStr = nui.get("item.certificateTypeCd").getValue();
			var certificateCodeStr = nui.get("item.certificateCode").getValue();
			CsmValidateobj.onValidateCertificateCode(certificateTypeCdStr, certificateCodeStr, e);
		}
	}
	

</script>
</body>
</html>
