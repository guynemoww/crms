<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): luchengchen@git.com.cn
  - Date: 2013-12-30
  - Description:TB_SYS_WARN_RESCRIPTION, com.bos.dataset.sys.TbSysWarnRescription
-->
<html>
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input type="hidden" name="item._entity" value="com.bos.dataset.sys.TbSysWarnRescription" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">
		<label>预警指标类型：</label>
		<input id="rescriptType" required="true" name="item.rescriptType" 
			class="nui-dictcombobox nui-form-input" vtype="maxLength:40" dictTypeId="earlyWarningType"  />

		<label>预警指标编码：</label>
		<input name="item.rescriptCode" required="true" class="nui-textbox nui-form-input" vtype="maxLength:40" />

		<label>预警指标名称：</label>
		<input name="item.rescriptName" required="true" class="nui-textbox nui-form-input" vtype="maxLength:20" />

		<label>预警阀值：</label>
		<input id="rescriptValue" name="item.rescriptValue" required="true" class="nui-textbox nui-form-input" vtype="maxLength:40" onvaluechanged="checkOrgnUnique(this.value)"/>
		
		<label>预警阀值类型：</label>
		<input name="item.rescriptvalueType" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:40" dictTypeId="earlyWarningType"/>
		
		<label>预警阀值单位：</label>
		<input name="item.rescriptvalueUnit" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:40" dictTypeId="earlyWarningType"/>
		
		<label>预警信号接收人：</label>
		<input name="item.rescriptReceiver" id="item.rescriptReceiver" allowInput="false" required="true" class="nui-buttonEdit" dictTypeId="user"  
		onbuttonclick="selectCustManeger" requiredErrorText="预警信号接收人不能为空"/>
		
		<label>预警信号等级：</label>
		<input name="item.rescriptLevel" required="true" class="nui-dictcombobox nui-form-input" vtype="maxLength:40" dictTypeId="XD_DHCD4006"/>
		
		
		<label>生效日期：</label>
		<input name="item.rescriptEffectDate" required="true" class="nui-datepicker nui-form-input" format="yyyy-MM-dd" vtype="maxLength:7" />
		<%--<label>经办机构：</label>
		<input name="item.handlingOrgidId" required="true" class="nui-textbox nui-form-input"  vtype="maxLength:10" />

		<label>经办人员名称：</label>
		<input name="item.handlingUserdId" required="true" class="nui-textbox nui-form-input"  vtype="maxLength:10" />

		<label>经办日期：</label>
		<input name="item.handlingDate" required="true" class="nui-datepicker nui-form-input" format="yyyy-MM-dd" vtype="maxLength:7" />--%>
      	
      	<label>预警指标描述：</label>
		<input name="item.rescriptDesc" required="true" class="nui-textarea nui-form-input" vtype="maxLength:1000" />
	</div>
</div>
				
<div class="nui-toolbar" style="border-bottom:0;text-align:center;">
	<a class="nui-button" iconCls="icon-save" onclick="save()">保存</a>
	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
</div>
	    
			
<script type="text/javascript">
	 	nui.parse();
	    var form = new nui.Form("#form1");
	    
function save() {
	form.validate();
	if (form.isValid() == false) {
		nui.alert("请将信息填写完整");
		return;
	}
	var o=form.getData();
	var json=nui.encode(o);
	
	$.ajax({
        url: "com.bos.pub.earlyWarning.rescription.addItem.biz.ext",
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
function selectCustManeger(e) {
          
        nui.open({
            url: nui.context + "/csm/group/groupCust_teamInfo_queryCust.jsp",
            showMaxButton: true,
            title: "选择预警接收人",
            width: 800,
            height: 500,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.getData();
                    data = nui.clone(data);
                    if (data) {
                    	nui.get("item.rescriptReceiver").setValue(data.userId);
     <%--              nui.get("item.handlingOrgId").setValue(data.orgId);
                   	nui.get("item.orgId").setValue(data.orgId);
                    	nui.get("item.post").setValue(data.post);--%>
                    
                       
                    }
                }
            }
        });   
        }
        
           //校验预警阀值是否唯一
        function checkOrgnUnique(obj){
         			var rescriptType=nui.get("rescriptType").getValue();
		        if(rescriptType==""){
		           alert("预警指标类型不能为空");
		           return;
		        }
       				 var json=nui.encode({"item":{"rescriptType":
            		 nui.get("rescriptType").getValue(),"rescriptValue":obj,"_entity":"com.bos.dataset.sys.TbSysWarnRescription"}});
                $.ajax({
                     url: "com.bos.utp.tools.CommonUtil.checkUniqueValue.biz.ext",
	                type: 'POST',
	                data: json,
	                cache: false,
	                contentType:'text/json',
                    success: function (text) {
                    	if(text.res==1){
                    	  alert("同一指标的预警阀值不能相同!");
                    	  nui.get("rescriptValue").setValue();
                    	  return;
                    	}
                    },
                    error: function () {
                    	nui.alert("操作失败！");
                    }
                });
            
        }
   <%--  
   //校验预警阀值是否唯一
	function checkOrgnUnique(obj) {
		if(!obj.value){
			return;
		}
		
    	var myAjax = new Ajax("com.bos.utp.tools.CommonUtil.uniqueValidate.biz");
		myAjax.addParam("dataType","com.bos.dataset.sys.TbSysWarnRescription");
		myAjax.addParam("property","rescriptValue");
		myAjax.addParam("validateValue",obj.value);
		myAjax.submit();
		
		var rtn = myAjax.getValue("root/data/isUnique");
		if(rtn=="0"){
			var el = document.getElementById("rescriptValue");
            el.innerHTML = "已存在!";
            obj.isValid = false;
		}else if(rtn=='-1'){
			nui.alert("查询异常");
		}else{
			var el = document.getElementById("rescriptValue");
			el.innerHTML = "";
		}
    } --%>
</script>
</body>
</html>
