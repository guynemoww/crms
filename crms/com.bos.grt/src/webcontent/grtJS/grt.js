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
	
	//得到CRMS系统的押品编号
	function getSuretyNum(sortType){
		debugger;
	    var suretyNum = "";
		if(sortType == "" || sortType == "null" || sortType == null){
		   nui.alert("押品分类编号为空");
		   return suretyNum;
		}else{
			var o = {"sortType":sortType};
			var json=nui.encode(o);
			git.mask();
			$.ajax({
	        	url: "com.bos.grtpublic.getSuretyNum.getCsmSuretyNum.biz.ext",
	        	type: 'POST',
	        	data: json,
	        	cache: false,
	        	async: false,//非异步，即同步
	        	contentType:'text/json',
	        	success: function (text) {
	        		suretyNum = text.suretyNumMap.suretyNum;
	        		git.unmask();
	        		return suretyNum;
	        	},
	        	error: function (jqXHR, textStatus, errorThrown) {
	            	nui.alert(jqXHR.responseText);
	        	}
			});
			return suretyNum;
		}
	}