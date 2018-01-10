<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<script type="text/javascript" src="<%=contextPath%>/csm/js/commValidate.js"></script>
<html>
<!-- 
  - Author(s): cp
  - Date: 2017-07-16 10:23:12
  - Description:
-->
<head>
<title>新增机构下的公积金</title>
</head>
<body>
<div id="form1" style="width:99.5%;height:99.5%;overflow:hidden;">
	<fieldset>
  	<legend>
    	<span>公积金账号信息</span>
    </legend>
		<div class="nui-dynpanel" id="table1" columns="4" >
		
			<label>机构名称：</label>
			<input id="orgEntrustAccount.orgCode" name="orgEntrustAccount.orgCode" required=true class="nui-buttonEdit" allowInput="false" onbuttonclick="selectEmpOrg" dictTypeId="org"/>
		
			<label>委托存款账号：</label>
			<input id="orgEntrustAccount.entrustAcc" name="orgEntrustAccount.entrustAcc" required="true"  class="nui-textbox nui-form-input" vtype="maxLength:32"  />
		
			<!-- <label>委托贷款收息账号：</label>
			<input id="orgEntrustAccount.entrustReturnAcc" name="orgEntrustAccount.entrustReturnAcc" vtype="maxLength:100" required="true"  class="nui-textbox nui-form-input"  /> -->
			
			<label>委托人收本账号：</label>
			<input id="orgEntrustAccount.entrustReturnPrincipalAcc" name="orgEntrustAccount.entrustReturnPrincipalAcc" required="true" class="nui-textbox nui-form-input" />
				
			<label>委托人收息账号：</label>
			<input id="orgEntrustAccount.entrustReturnInterestAcc" name="orgEntrustAccount.entrustReturnInterestAcc" required="true"  class="nui-textbox nui-form-input"/>
		</div>
	</fieldset>
	<div class="nui-toolbar" style="border:0;text-align:right;padding-right:20px;">
	    <a id = "btnSave" class="nui-button" style="margin-right:5px;"  iconCls="icon-save" onclick="add">保存</a>
    	<a id = "btnClose" class="nui-button" iconCls="icon-close"  onclick="CloseWindow('ok')">关闭</a>
	</div>
</div>
	<script type="text/javascript">
    	nui.parse();
    	var form = new nui.Form("#form1");
    	function add(){
    	  var o = form.getData();
	        if (form.isValid() == false){
	         	nui.alert("请按规则填写信息");
	         	return;
	        }
    		var json = nui.encode({"orgEntrustAccount":o.orgEntrustAccount});
	        $.ajax({
	            url: "com.bos.utp.rights.funds.fundadd.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            async: false,
	            contentType:'text/json',
	            success: function (text) {
	            	if(text.msg !='111111'){
	            		nui.alert("新增成功!");
	            		CloseWindow('ok')
	            	}else{
						nui.alert("新增失败!");	            	
	            	}
	            }
	            });
    	}
		 function selectEmpOrg(e) {
	        var btnEdit = this;
	        nui.open({
	            url: nui.context + "/utp/org/employee/select_all_org_tree.jsp",
	            showMaxButton: true,
	            title: "选择机构",
	            width: 800,
	            height: 500,
	            ondestroy: function (action) {            
	                if (action == "ok") {
	                    var iframe = this.getIFrameEl();
	                    var data = iframe.contentWindow.GetData();
	                    data = nui.clone(data);
	                    
	                    if (data) {
	                    
	                    	if(data.auditbankno==null || data.auditbankno=='' || data.auditbankno == 'null'){
	                    		nui.alert("该机构无对应核算机构!");
	                    	}
	                    	
	                    	self.orglevel=data.orglevel;
	                        btnEdit.setValue(data.orgcode);
	                        btnEdit.setText(data.orgname);
	                    }
	                }
	            }
	        });            
	    }
    </script>
</body>
</html>