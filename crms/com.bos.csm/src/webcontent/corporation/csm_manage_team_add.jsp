<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 夏群
  - Date: 2013-12-10 11:31:35
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<center>
<div id="form1" style="width:99.5%;height:auto;overflow:hidden;">
<input type="hidden" name="item._entity" value="com.bos.dataset.csm.TbCsmManagementTeam" class="nui-hidden" />
	<input name="item.partyId" id="item.partyId" class="nui-hidden" />
		<div class="nui-dynpanel" columns="4">
			<label>客户经理名称</label>
			<input id="item.userNum" name="item.userNum"  allowInput="false" class="nui-buttonEdit" 
				onbuttonclick="selectCustManeger" required="true" dictTypeId="user" requiredErrorText="客户经理名称不能为空"/>
			
			<label>所在机构名称</label>
			<input id="item.orgName" class="nui-text nui-form-input" name="item.orgName" dictTypeId="org" enabled="true"/>
			
			<label>所在机构编号</label>
			<input id="item.orgNum" class="nui-text nui-form-input" name="item.orgNum"   enabled="true"/>
			<%--
			<label>岗位名称</label>
			<input id="item.post" class="nui-text nui-form-input" name="item.post"   />--%>
			
			<label>管理类型</label>
			<input id="item.userPlacingCd" name="item.userPlacingCd"  valueField="dictID" textField="dictName" validateOnChanged="false"
				required="true" class="nui-dictcombobox nui-form-input"  dictTypeId="XD_KHCD0187" requiredErrorText="管理类型必选"
				/>
		</div>
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

		function save() {
     		
      		
      		//判断管护权的唯一性
		    if(checkMajorRightUnique()=="false"){
	            return;
	        }
	   	
	    
           form.validate();
			if (form.isValid() == false) {
				alert("请将信息填写完整");
				return;
			}
			git.mask("form1");
			var o=form.getData();
			var json=nui.encode(o);
			//nui.alert(json);return;
			$.ajax({
		            url: "com.bos.csm.pub.crudCustInfo.insertItem.biz.ext",
		            type: 'POST',
		            data: json,
		            cache: false,	
		            contentType:'text/json',
		            success: function (text) {
		            	git.unmask("form1");
		            	if(text.msg){
		            		alert(text.msg);
		            	} else {
		            		alert("保存成功!");
		            		CloseWindow("ok");
		            	}
		            },
		            error: function (jqXHR, textStatus, errorThrown) {
		                git.unmask("form1");
		                nui.alert(jqXHR.responseText);
		            }
			});
		}
		
		function selectCustManeger(e) {
           //var btnEdit = this;
        nui.open({
            url: nui.context + "/csm/corporation/csm_manage_team_queryCust.jsp",
            showMaxButton: true,
            title: "选择客户经理",
            width: 800,
            height: 500,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.getData();
                    data = nui.clone(data);
                    if (data) {
                    	nui.get("item.userNum").setValue(data.userId);
                    	nui.get("item.orgName").setValue(data.orgCode);
                    	nui.get("item.orgNum").setValue(data.orgCode);
                    	
                    }
                }
            }
        });   
        }
        
        //校验管户权客户经理只能一个
        function checkMajorRightUnique(e){
            var userPlacingCd= nui.get("item.userPlacingCd").value;
            var userNum = nui.get("item.userNum").value;
	 		var partyId = nui.get("item.partyId").getValue();
	 		var result = "true";
	 		//验证选择的用户是否已经存在于管理团队中了
	 		var json = {"partyId":partyId,"userNum":userNum};
	 		$.ajax({
   	      	  	url:"com.bos.csm.pub.pubMethod.checkManagementTeamUnique.biz.ext",
   	      	  	type: 'POST',
   	      	  	data: json,
   	      	  	cache: false,
   	      	  	async: false,
   	         	success: function(text){
   	          	 	if(text.msg){
   	          	 	//已经存在
   	          			result = "false";
   	          			alert(text.msg);
   	          			return;
   	          	 	}else{
   	          	 		result = "true";
   	          	 		return;
   	          	 	}
   	          	},
   	            error:function(jqXHR, textStatus, errorThrown){
   	          		nui.alert(jqXHR.responseText);
   	          }
   	     });
   	     
   	      if(userPlacingCd=="01"){
   	      	 json = {"partyId":partyId};
   	      	$.ajax({
   	      	  	url:"com.bos.csm.pub.pubMethod.checkMajorRightUnique.biz.ext",
   	      	  	type: 'POST',
   	      	  	data: json,
   	      	  	cache: false,
   	      	  	async: false,
   	         	success: function(text){
   	          	 	if(text.msg){
   	          			var userPlacingCd = nui.get("item.userPlacingCd").getValue();
   	          			if(!userPlacingCd){
   	          			result = "true";
   	          			}else{
   	          			alert(text.msg);
   	          			result = "false";
   	          			}
   	          			
   	          	 	}
   	          	},
   	            error:function(jqXHR, textStatus, errorThrown){
   	          		nui.alert(jqXHR.responseText);
   	          }
   	     });
   	    } 
   	        return result; 
   	  }
   	  
   	 
	
</script>

</body>
</html>