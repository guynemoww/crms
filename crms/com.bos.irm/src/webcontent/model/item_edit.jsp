<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-04-12

  - Description:TB_IRM_RATING_MODEL, com.bos.dataset.irm.TbIrmRatingModel-->
<head>
<title>模型信息</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;" >
	<%--   --%><input type="hidden" name="tbIrmRatingModel" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">
		<label>模型类型：</label>
		<input name="tbIrmRatingModel.modelTypeCd" id="modelTypeCd" required="true" class="nui-dictcombobox nui-form-input" dictTypeId="XD_PJCD0011" vtype="maxLength:20" />
		
		<label>模型版本：</label>
		<input id ="ratingModelVer" name="tbIrmRatingModel.ratingModelVer" required="true" class="nui-textbox nui-form-input" vtype="maxLength:6" />

		<label>模型名称：</label>
		<input name="tbIrmRatingModel.modelName" id="tbIrmRatingModel.modelName" required="true"  class="nui-textbox nui-form-input"  vtype="maxLength:40" />
	<!--	<label>适用规模：</label>
		<input name="tbIrmRatingModel.modelSuitScale" required="false" class="nui-dictcombobox nui-form-input" dictTypeId="ABF_YESORNO" vtype="maxLength:20" />-->
		



	</div>
</div>
	<div class="nui-toolbar" id="nui-toolbar" style="text-align:right;padding-right:20px;border:0;">
		<a class="nui-button" iconCls="icon-save" onclick="save()" id="btnSave">保存</a>

	</div>
				
	    
			
    <script type="text/javascript">
 	nui.parse();
 	git.mask();
    var form = new nui.Form("#form1");
	if ("<%=request.getParameter("view") %>"=="1") {
		//form.setEnabled(false);
		nui.get("modelTypeCd").setReadOnly(true);
		nui.get("ratingModelVer").setReadOnly(true);
		nui.get("tbIrmRatingModel.modelName").setReadOnly(true);
		nui.get("nui-toolbar").hide();
	}

function initForm() {

	var json=nui.encode({"tbIrmRatingModel":
		{"modelId":
		"<%=request.getParameter("modelId") %>"}});
		
	$.ajax({
        url: "com.bos.irm.model.getTbIrmRatingModel.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.msg){
        		nui.alert(text.msg);
        	} else {
        		form.setData(text);
        	}
			 git.unmask(); 
        	
        },
        error: function (jqXHR, textStatus, errorThrown) {
            nui.alert(jqXHR.responseText);
            git.unmask();
         
        }
	});
}
initForm();

function save() {
 	git.mask();
	form.validate();
	if (form.isValid() == false) {
		nui.alert("请将信息填写完整");
		git.unmask();
		return;
	}
	var data=form.getData();
	data.tbIrmRatingModel.modelNum= data.tbIrmRatingModel.modelTypeCd;
	var json=nui.encode(data);
	//nui.alert(json);return;
	$.ajax({
        url: "com.bos.irm.model.updateTbIrmRatingModel.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.msg){
        		nui.alert(text.msg);
        	}
        	git.unmask();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            nui.alert(jqXHR.responseText);
            git.unmask();
        }
	});
}
      	//通用获取业务字典方法
	//param1.dictId:业务字典ID; 
	//param2.type:过滤类型(str:指定字符id过滤(多id以","隔开);sub:获取指定字符串子集;top:获取顶级业务字典)
	//param3.指定的字符串(type为top时可以为空,不做处理)
	
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
	nui.get("modelTypeCd").setData(getDictData("XD_PJCD0011","str","M1,M2,M3,M4,M5,P1,P2,S1,S2,S3,S4,S5,C1,T1,P3"));
	</script>
</body>
</html>
