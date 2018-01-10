<%@page pageEncoding="UTF-8"%>
<%@page import="com.bos.pub.GitUtil"%>
<html>
<!-- 
  - Author(s): 王艳丽
  - Date: 2013-11-11 12:42:24
  - Description:
-->
<head>
<title>新增外部评级信息</title>
<%@include file="/common/nui/common.jsp"%>

</head>
<body>
	<div id="form1" style="width:100%;height:auto;overflow:hidden;">
		<input name="item.partyId" id="item.partyId" class="nui-hidden" />
		<input name="item.userNum" id="item.userNum" value="<%=((UserObject)session.getAttribute("userObject")).getUserId() %>" class="nui-hidden" />
		<input name="item.orgNum" id="item.orgNum" value="<%=((UserObject)session.getAttribute("userObject")).getAttributes().get("orgcode") %>"  class="nui-hidden" />
		<input name="item._entity" value="com.bos.dataset.csm.TbCsmExternalEvalResult" class="nui-hidden" />
		<fieldset>
	  <legend>
	    评级信息
	  </legend>
    <div class="nui-dynpanel" columns="4">
    		<%--<label>是否需要评级：</label>
			<input id="item.whetherEval" name="item.whetherEval" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002"  onvaluechanged="selectWhetherEval"  />
			--%>
			<label>评级机构：</label>
			<input id="item.resultFromOrg" name="item.resultFromOrg" required="true" class="nui-textbox nui-form-input" vtype="maxLength:100" />
			
		    <label>外部评级结果：</label>
			<input id="item.outEvalResult" name="item.outEvalResult" required="true" class="nui-textbox nui-form-input"  vtype="maxLength:50" />
			
			<!-- 
			<label>评级期次：</label>
			<input name="item.evalPeriods" required="false" class="nui-textbox nui-form-input" vtype="int;maxLength:2" />
			 -->
			<%--<label>评级类型：</label>
			<input name="item.evalTypeCd" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_KHCD0111"  />
			--%>
			
			<label>评级日期：</label>
			<input name="item.evalDate" required="true" class="nui-datepicker nui-form-input" maxDate="<%=GitUtil.getBusiDateStr()%>" format="yyyy-MM-dd"/>
			
			<label>起始日期：</label>
			<input id="item.startDate" name="item.startDate" required="true" maxDate="<%=GitUtil.getBusiDateStr()%>" class="nui-datepicker nui-form-input" format="yyyy-MM-dd"/>
			
			<label>到期日期：</label>
			<input id="item.endDate" name="item.endDate" required="true" minDate="<%=GitUtil.getBusiDateStr()%>" class="nui-datepicker nui-form-input" format="yyyy-MM-dd"/>
			
	</div>
    </fieldset>
		<div class="nui-toolbar" style="text-align:right;padding-right:20px;border:0"> 
		    <a class="nui-button"  iconCls="icon-save" onclick="add">保存</a>
		    <a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
		</div>
	</div>

<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form1");

	var partyId = "<%=request.getParameter("partyId") %>";
	if (partyId) {
		nui.get("item.partyId").setValue(partyId); 
	}
		
	function add() {
	    var startDate = nui.get("item.startDate").value;
	    var endDate = nui.get("item.endDate").value;
	     if(endDate){
	    	if(startDate>endDate){
		        alert("起始日期不能大于终止日期！");
		        return;
		    }
	    }
		form.validate();
		if (form.isValid() == false) {
			nui.alert("请将信息填写完整");
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
	            		nui.alert(text.msg);
	            		return;
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
	
	//取得机构下的评级信息
	function getLevel(e){
		 git.mask("form1");
	     var parentId = e.value.substring(0,2);
	     if(parentId==""){
	     	 alert("请先选择评级来源机构");
	     	 git.unmask("form1");
             return;
	     }else{
	         var json = nui.encode({parentId:parentId});
	         $.ajax({
	            url: "com.bos.csm.pub.getDict.getLevelDict.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            	git.unmask("form1");
	            	nui.get("item.outEvalResult").setData(text.levels);
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                git.unmask("form1");
	                nui.alert(jqXHR.responseText);
	            }
		     });
	      }
     }
	 
	 //初始化评级机构
	 function init(){
	 		git.mask("form1");
	        var json = nui.encode({levelOrg:"1"});
	         $.ajax({
	            url: "com.bos.csm.pub.getDict.getLevelDict.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	                git.unmask("form1");
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                git.unmask("form1");
	                nui.alert(jqXHR.responseText);
	            }
		     });
	      }
     init();
     
function onDayValidate(e){
	 if (e.isValid){
	 	if(null != e.value && "" != e.value){
	 		var curDate="<%=GitUtil.getBusiDateStr()%> 00:00:00";
	 		if(e.value > curDate){
	 			e.errorText = "不能大于当前日期<%=GitUtil.getBusiDateStr()%>";
                e.isValid = false;
	 		}
	 	}
	 }
}

function selectWhetherEval(){
	var whetherEval = nui.get("item.whetherEval").getValue();
	if(whetherEval=="0"){
		nui.get("item.resultFromOrg").setRequired(false);
		nui.get("item.outEvalResult").setRequired(false);
		<%--nui.get("item.startDate").setRequired(false);
		nui.get("item.endDate").setRequired(false);--%>
	}else{
		nui.get("item.resultFromOrg").setRequired(true);
		nui.get("item.outEvalResult").setRequired(true);
		<%--nui.get("item.startDate").setRequired(true);
		nui.get("item.endDate").setRequired(true);--%>
	}
}


</script>
</body>
</html>