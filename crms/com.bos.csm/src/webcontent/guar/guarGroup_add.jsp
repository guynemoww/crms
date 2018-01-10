<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): chenchuan
  - Date: 2016-5-08 13:04:42
  - Description:
-->
<head>
<title>新增联保客户</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="form1"  style="width:99.5%;height:auto;overflow:hidden;" >
	<fieldset> 
	  <legend>
	    <span>联保新增</span>
	   </legend>
	   	<div class="nui-dynpanel" columns="4">
			<label>联保小组类型：</label>
			<input id="guarGroup.jointGuaranteeType" class="nui-dictcombobox nui-form-input" name="guarGroup.jointGuaranteeType"  
			 required="true" dictTypeId="XD_KHCD4001" requiredErrorText="联保小组类型不能为空" />
			<label>联保客户名称：</label>
			<input id="party.partyName" class="nui-textbox nui-form-input" name="party.partyName"  vtype="string;minLength:4;maxLength:64" required="true"  requiredErrorText="名称不能为空"/>
		</div>
	</fieldset>
<div class="nui-toolbar" style="text-align:right;border:0;padding-right:20px;" >
    <a id="btnSave" class="nui-button"  iconCls="icon-save" onclick="add">确定</a>
</div>
</div>
	
	
<script type="text/javascript">
	nui.parse();
	var legorg = "<%=com.bos.pub.GitUtil.getLegorg()%>";
	var form = new nui.Form("#form1");
		var arr = git.getDictDataFilter("XD_KHCD4001",'02');
		nui.get("guarGroup.jointGuaranteeType").setData(arr);
		nui.get("guarGroup.jointGuaranteeType").setValue("02");
	function add(){
		//校验
		form.validate();
        if (form.isValid()==false) return;
        git.mask("form1");
        var o = form.getData();
        var json = nui.encode(o);
        var jointGuaranteeType = nui.get("guarGroup.jointGuaranteeType").getValue();
        var partyName = nui.get("party.partyName").getValue();
        	   var jsonEY = {"partyName":partyName,"jointGuaranteeType":jointGuaranteeType,"legorg":legorg};
        	   debugger;
	   	      var	tmsg = exeRule("RCSM_1005","1",jsonEY);
	   	      	if(null != tmsg && '' != tmsg){
		   	    
	              	      nui.alert(tmsg);
		               git.unmask("form1");
	   	      	}else{
         /*   var json1 = nui.encode({"partyName":o.party.partyName,"groupType":"512"});
        			        $.ajax({
		            url: "com.bos.csm.company.company.addCuustEcif.biz.ext",
		            type: 'POST',
		            data: json1,
		            cache: false,
		            async: false,
		            contentType:'text/json',
		            success: function (text) {
		             if("AAAAAAA"!=text.map.msg){
		             	nui.alert("调用ECIF接口失败:"+text.map.msgg);
		               git.unmask("form1");
		             
		             	   return ;
		             }else{ */
		           var json2 = nui.encode({"party":o.party,"guarGroup":o.guarGroup});
 		        $.ajax({
            url: "com.bos.csm.guar.guarGroup.addGuarGroup.biz.ext",
            type: 'POST',
            data: json2,
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
		    /* 
		             }
		            },
		            error: function (jqXHR, textStatus, errorThrown) {
		                nui.alert(jqXHR.responseText);
		                 return ;
		            }
		        });  */
       }
       
	}
	
	function onChineseAndEnglishValidation(e) {
            if (e.isValid) {
                if (isChineseAndEnglish(e.value) == false) {
                    e.errorText = "必须输入中文或者英文";
                    e.isValid = false;
                }
            }
     }
	
	
	 /* 是否汉字 */
        function isChineseAndEnglish(v) {
            var re = new RegExp("^[\u4e00-\u9fa5A-Za-z\_]+$");
            if (re.test(v)) return true;
            return false;
        }
        
		
		//弹出审批意见页面
	function openSubmitView(response){
		var url = nui.context + "/csm/guar/guarGroup_tree.jsp?partyId="
					+ response.bizId +"&partyNum="+response.partyNum+"&qote=0&processInstId="+response.processInstId+"&isSrc=2&wflow=2";
		nui.open({
	               url: url,
	               title: "新增联保客户", 
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