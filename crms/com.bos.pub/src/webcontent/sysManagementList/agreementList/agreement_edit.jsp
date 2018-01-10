<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): lujinbin
  - Date: 2014-05-04

  - Description:TB_SYS_AGREEMENT_LIST, com.bos.pub.sys.TbSysAgreementList-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<%--   --%><input type="hidden" name="tbSysAgreementList" class="nui-hidden" />
		<%--   --%><input type="hidden" name="tbSysAgreementList.id" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">
		<label>客户名称：</label>
		<input id="cus" name="tbSysAgreementList.custName" required="true" class="nui-buttonedit nui-form-input" onbuttonclick="onButtonEdit" vtype="maxLength:200" enabled="false"/>

		<label>授权机构：</label>
		<input name="tbSysAgreementList.grantOrgCode" required="true" class="nui-buttonEdit" vtype="maxLength:10" onbuttonclick="selectEmpOrg" dictTypeId="org"/>

		<label>证件编码：</label>
		<input id="orgn" name="tbSysAgreementList.orgCode" required="false" class="nui-textbox nui-form-input" vtype="maxLength:10" onvalidation="checkOrgnNum" enabled="false"/>
		<label>证件类型：</label>
		<input id="codeType" name="tbSysAgreementList.codeType" required="false" class="nui-dictcombobox nui-form-input"   dictTypeId="CDKH0002" enabled="false"/>
	</div>
</div>
				
<div class="nui-toolbar" style="border-bottom:0;padding-right:20px;text-align:right;">
	<a class="nui-button" iconCls="icon-save" onclick="save()" id="btnSave">保存</a>
	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
</div>
	    
			
    <script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1");
	if ("<%=request.getParameter("view") %>"=="1") {
		form.setEnabled(false);
		nui.get("btnSave").hide();
	}

function initForm() {
	var json=nui.encode({"tbSysAgreementList":
		{"id":
		"<%=request.getParameter("id") %>"}});
	$.ajax({
        url: "com.bos.pub.sysManagementList.getTbSysAgreementList.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.msg){
        		nui.alert(text.msg);
        	} else {
        		form.setData(text);
        		nui.get("cus").setText(text.tbSysAgreementList.custName);
        	}
        },
        error: function (jqXHR, textStatus, errorThrown) {
            nui.alert(jqXHR.responseText);
        }
	});
}
initForm();

function save() {
	form.validate();
	if (form.isValid() == false) {
		nui.alert("请将信息填写完整");
		return;
	}
	var o=form.getData();
	var json=nui.encode(o);
	//nui.alert(json);return;
	$.ajax({
        url: "com.bos.pub.sysManagementList.updateTbSysAgreementList.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.msg){
        		nui.alert(text.msg);
        	} else {
        		CloseWindow("ok");
        	}
        },
        error: function (jqXHR, textStatus, errorThrown) {
            nui.alert(jqXHR.responseText);
        }
	});
}
// 担保方式代码
function selectCodeList(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/dict/code_tree.jsp?dicttypeid=XD_SXCD1020",
            title: "选择字典项",
           width: 200,
            height: 500,
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
    //选择机构
    function selectEmpOrg(e) {
        var btnEdit = this;
        nui.open({
            url:  nui.context + "/pub/sys/select_org_tree.jsp",
            showMaxButton: false,
            title: "选择机构",
            width: 350,
            height: 450,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.GetData();
                    data = nui.clone(data);
                    if (data) {
	                    	if(data.orgid=="10001"){
	                     		 btnEdit.setValue("BS001");
		                    }else{
		                   		 btnEdit.setValue(data.orgcode);
		                    }
	                        	btnEdit.setText(data.orgname);
                }
            }
            }
        });            
    }
    //选择客户
    function onButtonEdit(e){
             var btnEdit = this;
	        nui.open({
	            url: nui.context + "/pub/sysManagementList/cusMsg/cusMsg.jsp",
	            showMaxButton: true,
	            title: "选择客户",
	            width: 800,
	            height: 500,
	            ondestroy: function (action) {            
	                if (action == "ok") {
	                    var iframe = this.getIFrameEl();
	                    var data = iframe.contentWindow.getData();
	                    data = nui.clone(data);
	                    if (data) {
	                    	 btnEdit.setValue(data.partyName);// 客户id
	                         btnEdit.setText(data.partyName); // 客户名称
	                         nui.get("orgn").setValue(data.certificateCode);// 组织机构代码
	                         var orgn=  nui.get("orgn");
	                         orgn.setEnabled(false);
	                    }
	                }
	            }
	        });   
    }
    //效验组织机构代码
  function checkOrgnNum(e){
        var nowData;
        nowData=nui.get("orgn").getValue();
                     if(!isValidCompID()){
	                    e.isValid = false;
						}	                
  }
  //效验组织机构代码格式
  function isValidCompID() { 
  		var sCompID = nui.get("orgn").getValue();
		if(sCompID.length!=10) { 
			alert("组织机构代码总长度不符"); 
			return false; 
		} 
		var sItems = sCompID.split("-"); 
		iW = new Array(3,7,9,10,5,8,4,2); 
		if (sItems.length!=2){
			alert("识别用分隔符不正确"); 
			return false; 
		} 
		if(sItems[0].length!=8){
			alert("－前长度不足"); 
			return false; 
		} 
		if (sItems[1].length!=1){ 
			alert("－后长度不足");
			return false; 
		} 
		var iCheck; 
		var cCheck = sItems[1].charAt(0); 
		if( cCheck =='X' ) // X 
			iCheck = 10; 
		else if( isNaN(iCheck) ) //0-9 
			iCheck = parseInt(cCheck); 
		else { 
			alert("校验位字符不合法"+cCheck+"||"+iCheck); 
			return false; 
		}
		
		iSum = 0; 
		for( i=0;i<8;i++) { 
			iC = sItems[0].charAt(i); 
			if(isNaN(iC)){
				iVal = sItems[0].charCodeAt(i) - 55; 
				if( iVal < 10 || iVal > 35 ) {
					//A-Z begin10  
					alert("第"+(i+1)+"位本体字符不合法"); 
					return false; 
				}
			}else 
				iVal = parseInt(iC);
			iSum += iVal * iW[i]; 
		} 
		iChk = 11 - iSum % 11; 
		
		if( iChk == 11 ) 
			iChk = 0; 
		
		if(iCheck != iChk ){ 
			alert("校验位不符"); 
			return false; 
		} 
		return true; 
	}                                            
	</script>
</body>
</html>
