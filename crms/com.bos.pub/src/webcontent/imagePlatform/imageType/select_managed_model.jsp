<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<!-- 
  - Author(s): yangyong
  - Date: 2013-02-28 10:14:50
  - Description:
-->
<head>
<title>选择适用岗位</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@include file="/common/nui/common.jsp" %>
</head>
<body>

	<div id="listbox1" class="nui-listbox" url="com.bos.pub.image.getPositionByOrgLevel.biz.ext" style="width:388px;height:100%;"
          multiSelect="true"  textField="posiname" idField="posicode"
       	dataField="omPosition" showCheckBox="true">
     	<div property="columns">
           <div header="可选岗位" field="posiname"></div>
        </div>
    </div>

<div id="toolBar" class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;visibility:hidden;" 
    borderStyle="border:0;">
    <a class="nui-button"  iconCls="icon-ok" onclick="ok">确定</a>
    <span style="display:inline-block;width:25px;"></span>
    <a class="nui-button" id="cancelBtn_01" iconCls="icon-cancel"  onclick="cancel">关闭</a>
</div>
<script type="text/javascript">
	 nui.parse();
	
	 var listbox1 = nui.get("listbox1");
 
 
    $(function(){
		if(window.parent.getCurrentNode){ // 从Tab页进入时
			var node = window.parent.getCurrentNode();
			var parentNode = node;
			window['parentNode'] = parentNode;
		}
	});
	
	// 弹出窗口时调用
	function SetData(data){
		// 显示关闭按钮
		$("#toolBar").css("visibility", "visible");
		data = nui.clone(data);
		if(data && data.parentNode){
			window['parentNode'] = data.parentNode;
		}
		
		
		if(data.ids == "") return;
		var ids = data.ids.split(",");
        if(ids!=""){
	       for(var i=0;i<ids.length;i++){
	          var id = ids[i].split(":");
	          var listdata = listbox1.getData();
	          for(var j=0,len=listdata.length;j<len;j++){
	              if(listdata[j].posicode==id[0]){
	                  listbox1.select(listdata[j]);
	                  break;
	              }
	          }
	       }
        }
	}
	
	function GetData() {
		var items = listbox1.getSelecteds();
		var ids = [];
		var texts = [];
		for(var i=0;i<items.length;i++){
			ids.push(items[i].posicode);
			texts.push(items[i].posiname);
		}
        return {id:ids.join(","),text:texts.join(",")};
    }
    
	
	
	function CloseWindow(action) {            
        if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
        else window.close();            
    }
    
    function ok(){
 	   
        CloseWindow("ok");
    }
    
    function cancel() {
    	CloseWindow("cancel");
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
	
</script>
</body>
</html>