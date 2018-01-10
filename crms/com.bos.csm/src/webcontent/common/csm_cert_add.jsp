<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@page import="com.bos.pub.GitUtil"%>
<html>
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2013-11-28
  - Description:TB_CSM_ADDRESS, com.bos.dataset.csm.TbCsmAddress
-->
<head>
<%@include file="/common/nui/common.jsp" %>
<script type="text/javascript" src="<%=contextPath%>/csm/js/csmValidate.js"></script>
</head>
<body>
<center>
<div id="form1" style="width:99%;height:auto;overflow:hidden;">
	<input name="item.partyId" id="item.partyId" class="nui-hidden" />
	<fieldset>
	  <legend>
	    <span>证件信息</span>
	   </legend>
    	<div class="nui-dynpanel" columns="4">
			<label>证件类型：</label>
			<input id="item.certificateTypeCd" name="item.certificateTypeCd" textField="dictname" valueField="dictid"  required="true" 
			 class="nui-dictcombobox nui-form-input"   />
	
			<label>证件号码：</label>
			<input id="item.certificateCode"  name="item.certificateCode" required="true" class="nui-textbox nui-form-input" onvalidation="onValidateCertificateCode" />
	
			<label>签发日期：</label>
			<input id="item.signDate"  name="item.signDate" required="true" class="nui-datepicker nui-form-input" format="yyyy-MM-dd" />
	
			<label>到期日期：</label>
			<input id="item.endDate" name="item.endDate" required="true"class="nui-datepicker nui-form-input" format="yyyy-MM-dd" />
		</div>
    </fieldset>
    <div class="nui-toolbar" style="border:0;text-align:right;padding-right:20px;">
		<a class="nui-button" iconCls="icon-save" onclick="save()">保存</a>
		<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
	</div>
</div>
</center>				

	    
			
<script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1");
	var partyId = "<%=request.getParameter("partyId") %>";
	if (partyId) {
		nui.get("item.partyId").setValue(partyId); 
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
	                if(certificateTypeCd!="null"){
				   		nui.get("item.certificateTypeCd").setValue(certificateTypeCd);
				   		nui.get("item.certificateTypeCd").setEnabled(false);
				   	}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                git.unmask("form1");
	                nui.alert(jqXHR.responseText);
	            }
		     });
	      }
     init();
     
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
		git.mask("form1");
		var o=form.getData();
		var json=nui.encode(o);
		$.ajax({
	            url: "com.bos.csm.common.certInfo.insertCert.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	           		git.unmask("form1");
	            	if(text.msg){
	            		alert(text.msg);
	            		return;
	            	} else {
	            		alert("保存成功");
	            		if(certificateTypeCd!="null"){
							data = form.getData();	 
							data.item.certificateId = text.response.certificateId;
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
	
	
	
	
	//证件验证
	function onValidateCertificateCode(e){
		var certificateTypeCdStr = nui.get("item.certificateTypeCd").getValue();
		var certificateCodeStr = nui.get("item.certificateCode").getValue();
		CsmValidateobj.onValidateCertificateCode(certificateTypeCdStr, certificateCodeStr, e);
	}
	
	
	
	<%--function selectCertList(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/dict/code_tree.jsp?dicttypeid=CDKH0002",
            title: "选择字典项",
            width: 800,
            height: 450,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.currentNode;
                    data = nui.clone(data);
                    if (data) {
                        btnEdit.setValue(data.dictid);
                        btnEdit.setText(data.dictname);
                    }
                }
                if (action == "clear") { //清空选择的内容
                	btnEdit.setValue("");
                	btnEdit.setText("");
            	}
        	}
        });            
	}
   	--%>
</script>
</body>
</html>
