<%@page pageEncoding="UTF-8"%>
<%@include file="/common/skins/skin0/component.jsp" %>
<html>
<!-- 
  - Author(s): 李建飞
  - Date: 2013-10-25 15:44:50
  - Description:
-->
<head>
<title>新增黑名单</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
</center>
<div id="form1" style="width:99%;height:auto;overflow:hidden;">
    <input name="action" id="action" class="nui-hidden" />
    <input name="blacklist.partyId" id="blacklist.partyId" class="nui-hidden"/>
    <fieldset>
	  <legend>
	    <span>黑名单信息</span>
	  </legend>
      <div class="nui-dynpanel">
		<label>客户名称：</label>
		<input id="item.partyName" name="item.partyName"  class="nui-buttonEdit" onbuttonclick="selectCrop" />
	
		<label>CRMS客户编号：</label>
		<input id="item.partyNum" name="item.partyNum"  class="nui-text nui-form-input" />
	
		<label>ECIF客户编号：</label>
		<input id="item.EcifPartyNum" name="item.EcifPartyNum" class="nui-text nui-form-input"/>
	
		<label>组织机构代码：</label>
		<input id="item.orgnNum" name="item.orgnNum"  class="nui-text nui-form-input"/>
	
		<label>黑名单进入原因：</label>
		<input id="blacklist.blackListReasonCd" name="blacklist.blackListReasonCd"  class="nui-dictcombobox" data="data" valueField="dictID" textField="dictName" 
		  dictTypeId="XD_KHCD0010" required="true"/> 
		  
		<label>黑名单进入原因说明：</label>
		<input id="blacklist.blackReasonExplain" name="blacklist.blackReasonExplain"  class="nui-textbox nui-form-input"/>
	
		<label>黑名单数据来源：</label>
		<input id="blacklist.blackListSourceCd" name="blacklist.blackListSourceCd"  class="nui-dictcombobox" data="data" valueField="dictID" textField="dictName" 
		  dictTypeId="XD_KHCD0138" required="true"/> 
		  
		<label>黑名单转入日期：</label>
		<input id="blacklist.rollInDate" name="blacklist.rollInDate"  class="nui-datepicker nui-form-input"  format="yyyy-MM-dd"/>
	</div>
    
    </fieldset>
    
		<div style="text-align:right;padding-left:25px;padding:10px;">               
	        <a class="nui-button" onclick="onOk" style="width:60px;margin-right:20px;">保存</a>       
	        <a class="nui-button" onclick="onCancel" style="width:60px;">取消</a>       
	    </div>  
		
   
          
</div>  
</center>
<script type="text/javascript">
    
        
        nui.parse();
 
        var form = new nui.Form("form1");
        
 		
        function CloseWindow(action) {            
            if (action == "close" && form.isChanged()) {
                if (confirm("数据被修改了，是否先保存？")) {
                    return false;
                }
            }
            if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
            else window.close();            
        }
        
        function onOk(e) {
        
            var o = form.getData();            
            form.validate();
            if (form.isValid() == false) return;
            git.mask("form1");
            var json = nui.encode(o);
            nui.ajax({
                url: "com.bos.csm.blacklist.blacklist.saveBlackList.biz.ext",
                data: json,
                type: 'POST',
                cache: false,
                contentType: 'text/json',
                success: function (text) {
                    git.unmask("form1");
                    if(text.msg){
		            		alert(text.msg);
		            		return;
		            	} else {
		            		var node = text.node;
							openSubmitView(node);
		            		//CloseWindow("ok");
		            	}
                },
                error: function (jqXHR, textStatus, errorThrown) {
                 	git.unmask("form1");
                    alert(jqXHR.responseText);
                    CloseWindow();
                }
            });
        }
        function onCancel(e) {
            CloseWindow("cancel");
        }
        
 		//选择客户
        function selectCrop(e) {
            nui.open({
                url:nui.context + "/csm/group/groupCust_member_queryCorp.jsp",
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
	                            nui.get("blacklist.partyId").setValue(data.partyId);
	                            nui.get("item.partyName").setText(data.partyName);
	                            nui.get("item.partyName").setValue(data.partyName);
		                    	nui.get("item.partyNum").setValue(data.partyNum);
		                    	nui.get("item.EcifPartyNum").setValue(data.EcifPartyNum);
		                    	nui.get("item.orgnNum").setValue(data.orgnNum);
		                    	
	                        }
	                    }
 
                }
            });            
        }   
      
             
</script>
</body>
</html>