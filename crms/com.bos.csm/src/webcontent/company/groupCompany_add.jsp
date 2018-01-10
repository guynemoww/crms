<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 陈川
  - Date: 2016-5-10 13:04:42
  - Description:
-->
<head>
<title>新增集团客户</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>

<div id="form1"  style="width:99.5%;height:auto;overflow:hidden;" >
	<fieldset> 
	  <legend>
	    <span>集团新增</span>
	   </legend>
	   	<div class="nui-dynpanel" columns="2">
			<label>集团客户名称：</label>
			<input id="party.partyName" class="nui-textbox nui-form-input" name="party.partyName"  vtype="maxLength:64" required="true"  requiredErrorText="集团名称不能为空"/>
		</div>
	</fieldset>
<div class="nui-toolbar" style="text-align:right;border:0;padding-right:20px;" >
    <a id="btnSave" class="nui-button"  iconCls="icon-save" onclick="add">确定</a>
</div>
</div>
	
	
<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form1");
	function add(){
		//校验
		form.validate();
        if (form.isValid()==false) return;
        git.mask("form1");
       var o = form.getData();
        var json = nui.encode(o);
        debugger;
    /*     var json1 = nui.encode({"partyName":o.party.partyName,"groupType":"511"});
        			        $.ajax({
		            url: "com.bos.csm.company.company.addCuustEcif.biz.ext",
		            type: 'POST',
		            data: json1,
		            cache: false,
		            async: false,
		            contentType:'text/json',
		            success: function (text) {
		             if("AAAAAAA"!=text.map.msg){
		             debugger;
		             	nui.alert("调用ECIF接口失败:"+text.map.msgg);
		               git.unmask("form1");
		             
		             	   return ;
		             }else{ */
		            /*      var json2 = nui.encode({"party":o.party,"groupCustNo":text.map.groupCustNo}); */
		                  var json21 = nui.encode({"party":o.party});
 		       $.ajax({
            url: "com.bos.csm.company.company.addCompany.biz.ext",//groupCustNo
            type: 'POST',
            data: json21,
            cache: false,
            contentType:'text/json',
            success: function (text) {
				git.unmask("form1");
            	if(text.msg){
            		nui.alert(text.msg);
            	} else {
            		openSubmitView(text.response);
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
            	git.unmask("form1");
                nui.alert(jqXHR.responseText);
            }
        });
		        
/* 		             }
		            },
		            error: function (jqXHR, textStatus, errorThrown) {
		                nui.alert(jqXHR.responseText);
		                 return ;
		            }
		        });  */
       
	}
	
	//弹出审批意见页面
	function openSubmitView(response){
		var url = nui.context + "/csm/company/groupCompany_tree.jsp?partyId="
					+ response.bizId +"&partyNum="+response.partyNum+"&qote=2&processInstId="+response.processInstId+"&isSrc=2&wflow=2";
		nui.open({
	               url: url,
	               title: "新增集团客户", 
	               width: 550, 
	               height: 260,
	               state:"max",
	               onload: function () {
	               },
	               ondestroy: function (action) {
	               		CloseWindow('ok');
	               }
	           });
	}
   
</script>

	
</body>
</html>