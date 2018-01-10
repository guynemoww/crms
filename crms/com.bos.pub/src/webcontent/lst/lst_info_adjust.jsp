<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 甘泉
  - Date: 2015-05-20 11:01:34
  - Description:
-->
<head>
<title>名单制管理调整</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
		<div id="form1" style="width: 99.5%; height: auto; overflow: hidden;">
			<fieldset>
				<legend>
					<span>基本信息</span>
				</legend>
				<input id="info.partyId" class="nui-hidden nui-form-input" name="info.partyId" value="<%=request.getParameter("partyId")%>" />
				<div class="nui-dynpanel" columns="4">
					<label class="nui-form-label">客户名称：</label>
					<input id="party.partyName" class="nui-text nui-form-input" readonly="true"enabled="false" name="party.partyName" /> 
					<label>证件类型：</label> 
					<input id="result.certType"  name="result.certType" class="nui-textbox nui-form-input" enabled="false"/>
					<label>证件号码：</label> 
					<input id="result.certNum"  name="result.certNum" class="nui-textbox nui-form-input" enabled="false"/>
					<label>中征码：</label> 
					<input id="result.middleCode"  name="result.middleCode" class="nui-textbox nui-form-input" enabled="false"/>
					<label class="nui-form-label">监控状态：</label>
					<input id="info.listStatus" class="nui-dictcombobox nui-form-input" dictTypeId="XD_MDCD0001" readonly="true"
						Enabled="false" name="info.listStatus" /> 
					<label class="nui-form-label">申请调整为：</label>
					<input id="info.newStatus" name="info.newStatus" class="nui-dictcombobox nui-form-input" dictTypeId="XD_MDCD0001" required="true" /> 
					<label class="nui-form-label">调整依据：</label>
					<input id="info.pdYj" name="info.pdYj"  vtype="maxLength:100" class=nui-textbox required="true" /> 
				</div>
			</fieldset>
			
			<div class="nui-toolbar"  style="text-align: right; padding-right: 20px; border: 0">
				<a id="btnSave" class="nui-button" iconCls="icon-save"onclick="update">保存</a>
			</div>
		</div>

		<script type="text/javascript">
	var oldData;
	var newData;
    nui.parse();
    git.mask("form1");
    
    var form = new nui.Form("#form1");
    var qote = "<%=request.getParameter("qote")%>" ;
    var partyId = "<%=request.getParameter("partyId")%>";
    var newStatus = "<%=request.getParameter("newStatus") %>";//获取是否黑名单客户跳转的页面
    
    //根据newStatus状态设置调整状态的值(0不是黑名单 1是黑名单)
	if(''!=newStatus||null!=newStatus){
		if('0'==newStatus){
			nui.get("info.newStatus").setData(getDictData("XD_MDCD0001","str","1,2,3"));	
		}else{
			nui.get("info.newStatus").setData(getDictData("XD_MDCD0001","str","1,3"));
		}
	}
	
	if(qote=="1"){
	   form.setEnabled(false);
	   nui.get("btnSave").hide();
	}	
    <%--  数据初始化--%>
	function initForm(){
	  var json = nui.encode({"info":{"partyId":"<%=request.getParameter("partyId")%>"}});
	  $.ajax({
			  url: "com.bos.pub.lst.lst.getInfo.biz.ext",
			  type: 'POST',
			  data: json,
			  cache: false,
			  contentType: 'text/json',
			  success: function (mydata) {
	          		 git.unmask("form1");
	                 var o = nui.decode(mydata);
	                 form.setData(o);
	                 nui.get('result.certType').setValue(nui.getDictText('CDKH0002',o.result.certType));
	            }, 
	            error: function (jqXHR, textStatus, errorThrown) {
	            	git.unmask("form1");
	                nui.alert(jqXHR.responseText);
	            }     
	  });
	 }
	initForm();
	
	function update(){
		//校验
		form.validate();
        if (form.isValid()==false) {
        	nui.alert("请输入必填项。");
        	return;   
        }  
        var o = form.getData();
        var json = nui.encode(o);
         $.ajax({
		            url: "com.bos.pub.lst.lst.saveLstInfo.biz.ext",
		            type: 'POST',
		            data: json,
		            cache: false,
		            contentType:'text/json',
		            success: function (text) {
		            	git.unmask("form1");
		            	if(text.msg){
		            		nui.alert(text.msg);
		            	}else{
		            		nui.alert("保存成功！");
		            	}
		            },
		            error: function (jqXHR, textStatus, errorThrown) {
		    			git.unmask("form1");
		                nui.alert(jqXHR.responseText);
		            }
		        });
        
		}

		//隐藏下来框中的某个值(getDictData(),contactStr())
		function getDictData(dictId,type,str){
		var dictData = nui.getDictData(dictId);//获取业务字典的数据
		var arr = nui.encode(dictData).split("},");//业务字典数据字符串化，方便处理
		var strArr = new Array();
		//将字符串存入数组
		if(str.indexOf(",") != -1){
			strArr = str.split(",");
		}else{
			strArr.push(str);
		}
		var dictStr = "";//拼接业务字典字符串
		if(type == "str"){//如果是指定字符串过滤
			for(var i = 0;i<arr.length;i++){
				for(var n = 0;n<strArr.length;n++){
					var flag = arr[i].indexOf('"dictID":"'+strArr[n]+'"')!="-1";//如果包含指定的字符串
					if(flag){
						dictStr = contactStr(i,dictStr,arr);
					}
				}
			}
		}else if(type == "sub"){//如果是只获取指定字符串子集
			for(var i = 0;i<arr.length;i++){
				for(var n = 0;n<strArr.length;n++){
					var s = strArr[n];
					//var flag = arr[i].indexOf('"dictID":"'+s)!="-1";//必须为指定字符串及其子项
					//var flag1 = arr[i].indexOf('"dictID":"'+s+'"')=="-1";//不能为父项
					var flag2 = arr[i].indexOf('"parentid":"'+s+'"')!="-1";//必须为子项（不包含子项的子项）
					if(flag2){
						dictStr = contactStr(i,dictStr,arr);
					}
				}
			}
		}else if(type == "top"){//如果是只获取最顶级业务字典
			for(var i = 0;i<arr.length;i++){
				var flag = arr[i].indexOf('"parentid":"null"')!="-1";//必须为顶级业务字典
				if(flag){
					dictStr = contactStr(i,dictStr,arr);
				}
			}
		}
		//如果最后一个字典项不符合条件，则增加结束标识符号“}]”
		if(dictStr.charAt(dictStr.length-1) != "]"){
			dictStr = dictStr + "}]";
		}
		var dict = nui.decode(dictStr);
		return dict;
	}
	
	//根据索引值，字符串和数组值拼接(用于过滤业务字典-getDictData)
	function contactStr(index,str,arr){
		if(index == 0){
			str = str + arr[index];
		}else if(index != (arr.length)){
			if(str == ""){
				str = "[" + arr[index];
			}else{
				str = str + "}," + arr[index];
			}
		}
		return str;
	}
	
</script>
</body>
</html>