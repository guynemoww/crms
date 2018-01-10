<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 陈川
  - Date: 2016-5-10 16:17:00
  - Description:
-->
<head>
<title>引入成员客户</title>
<%@include file="/common/nui/common.jsp"%>
<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
</head>
<body>
<div id="form1" style="width:99.5%;height:auto;overflow:hidden;" >
		<input id="mem.groupPartyId" name="mem.groupPartyId"   class="nui-hidden"  />
		<fieldset>
		  <legend>
		    <span>成员信息</span>
		  </legend>
		  <div class="nui-dynpanel" columns="2">
			<label>成员名称：</label>
			<input id="corp.partyName" required="ture" name="corp.partyName"   allowInput="false" class="nui-buttonEdit" onbuttonclick="selectCust" />

			<label>是否集团客户：</label> 
			<input id="mem.isGroup" required="ture"name="mem.isGroup"  class="nui-dictcombobox nui-form-input"  dictTypeId="YesOrNo" />

			<label>成员类别：</label> 
			<input id="mem.memberTypeCd" required="ture"  name="mem.memberTypeCd" class="nui-dictcombobox nui-form-input"  dictTypeId="XD_KHCD0142" />
			
			<label>关联类型：</label>
			<input id="mem.groupRelTypeCd" required="ture" name="mem.groupRelTypeCd" 
				class="nui-dictcombobox nui-form-input"  dictTypeId="XD_KHCD0207" onvaluechanged="groupRelTypeCd" />
			
			<label>关联关系说明：</label>
			<input id="mem.relationshipState" required="false" name="mem.relationshipState" vtype="maxLength:1000" class="nui-textarea nui-form-input" />
			
			<input id="mem.corporationPartyId" name="mem.corporationPartyId"   class="nui-hidden"  />
			<input id="processInstId" name="processInstId" value="<%=request.getParameter("processInstId") %>" class="nui-hidden"  />
		</div>
		</fieldset>
		<div id="ifOK" class="nui-toolbar" style="border:0;text-align:right;padding-right:20px;">
			<a class="nui-button" iconCls="icon-save" onclick="save()">新增</a>
			<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
		</div>
</div>


<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form1");
	
    var partyId = "<%=request.getParameter("partyId") %>";
    var processInstId = "<%=request.getParameter("processInstId") %>";//mem.memberTypeCd
    var str ='01,02';
  		var arr = git.getDictDataFilter("XD_KHCD0142",str);
		nui.get("mem.memberTypeCd").setData(arr);
		nui.get("mem.memberTypeCd").setValue("02");
	if (partyId) {
			nui.get("mem.groupPartyId").setValue(partyId); 
		}
		
    function query(){
        form.validate();
        if (form.isValid()==false) return;
        var o = form.getData();
    }
    
    
    function save() {
			form.validate();
			if (form.isValid() == false) {
				alert("请将信息填写完整");
				return;
			}
			//校验是否有客户的权限
			var partyId=nui.get('mem.corporationPartyId').getValue();
			var userNum="<%=((UserObject) session.getAttribute("userObject")).getUserId()%>";
			var orgNum="<%=((UserObject) session.getAttribute("userObject")).getAttributes().get("orgcode")%>";
			var json1 = {"partyId":partyId,"orgNum":orgNum,"userNum":userNum};
	   	    	msg = exeRule("PUB_0015","1",json1);
	   	    	if(null != msg && '' != msg){
	   	    		return alert("客户经理没有该客户的管户权或业务权");
	   	    }
	   	    
			//校验客户是在其他集团客户中
			var json = {"partyId":partyId};
			var msg = exeRule("RCSM_0901","1",json);
	   	      	if(null != msg && '' != msg){
		   	      return alert("该客户【"+nui.get("corp.partyName").getText()+"】已经与其他集团客户关联");
	   	    }
			git.mask("form1");
			var o=form.getData();
			o.processInstId=processInstId;
			var json=nui.encode(o);
            $.ajax({
		            url: "com.bos.csm.company.member.insertMember.biz.ext",//insertMemberEcif
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
		            		CloseWindow("ok");
		            	}
		            },
		            error: function (jqXHR, textStatus, errorThrown) {
		 				git.unmask("form1");
		                nui.alert(jqXHR.responseText);
		            }
			});
		}
		
	function selectCust(e) {
        nui.open({
            url: nui.context + "/csm/company/groupCompany_member_queryCorporationAndNatural.jsp",
            showMaxButton: true,
            title: "选择成员客户",
            width: 900,
            height: 500,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.getData();
                    data = nui.clone(data);
                    if (data) {
                    	nui.get("mem.corporationPartyId").setValue(data.partyId);
                    	nui.get("corp.partyName").setText(data.partyName);
                    	nui.get("corp.partyName").setValue(data.partyName);
                    	//nui.get("corp.partyNum").setValue(data.partyNum);
                    	//nui.get("corp.ecifPartyNum").setValue(data.ecifPartyNum);
                    }
                }
            }
        });   
        }
        
        function groupRelTypeCd(e){
        	var groupRelTypeCd = e.value;
        	if(groupRelTypeCd=='04'){
        		nui.get('mem.relationshipState').setRequired(true);
        		form.validate();
        	}else{
        		nui.get('mem.relationshipState').setRequired(false);
        		form.validate();
        	}
        }
    
  </script>
  
  
</body>
</html>