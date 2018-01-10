	//param1.dictId:业务字典ID; 
	//param2.type:过滤类型(str:指定字符id过滤(多id以","隔开);sub:获取指定字符串子集;top:获取顶级业务字典)
	//param3.指定的字符串(type为top时可以为空,不做处理)

	function getDictData(dictId,type,str){
		var dictData = null;//获取业务字典的数据
		var intervalTemp = setInterval(function(){
			dictData = nui.getDictData(dictId);
			if(dictData){
				clearInterval(intervalTemp);
				return transDictData(dictData,dictId,type,str);
			}
		},200);
		
		setTimeout(function(){
			clearInterval(intervalTemp);
		}, 5000);
	}
	
	function transDictData(dictData,dictId,type,str){
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
CsmValidateobj={
    //校验组织机构或者营业执照号码是否唯一
	checkOrgNumUnique:function(e,certificateTypeCdStr,certificateCodeStr,unique){
		if(certificateCodeStr){
		    git.mask();
   	      	var json = {"certificateCode":certificateCodeStr,"certificateTypeCd":certificateTypeCdStr};
   	      	$.ajax({
   	      	  	url:"com.bos.csm.pub.pubMethod.checkOrgnNum.biz.ext",
   	      	  	type: 'POST',
   	      	  	data: json,
   	      	  	cache: false,
   	      	  	async: false,
   	         	success: function(text){
   	         	    git.unmask();
   	          	 	if(text.errMsg){
   	          	 	    e.isValid = false;
   	          	 	    if(unique=="1"){
   	          	 	    	e.isValid = true;
   	          	 	    	return null;
   	          	 	    }else{
   	          	 	    	 if(certificateTypeCdStr=="20001"){
	   	          	 	    	e.errorText = "组织机构代码已经存在!";
	   	          				return "组织机构代码已经存在!";
	   	          	 	    }else{
	   	          	 	    	e.errorText = "营业执照号已经存在!";
	   	          				return "营业执照号已经存在!";
	   	          	 	    }
   	          	 	    }
   	          	 	   
						
   	          	 	}else{
   	          	 		return null;
   	          	 	}
   	          	},
   	            error:function(jqXHR, textStatus, errorThrown){
   	                git.unmask();
   	          		nui.alert(jqXHR.responseText);
   	          		return "";
   	          }
   	     });
   	    } 
	},
	
	//效验组织机构代码
	validCompID:function(sCompID){
	    if(null==sCompID){
	    	return "组织机构代码不能为空";
	    }
		if(sCompID.length!=10) { 
			return "组织机构代码总长度不符"; 
		} 
		var sItems = sCompID.split("-"); 
		iW = new Array(3,7,9,10,5,8,4,2); 
		if (sItems.length!=2){
			return "不符合校验规则"; 
		} 
		if(sItems[0].length!=8){
			alert("－前长度不足"); 
			return false; 
		} 
		if (sItems[1].length!=1){ 
			return "－后长度不足"; 
		} 
		var iCheck; 
		var cCheck = sItems[1].charAt(0); 
		if( cCheck =='X' ) // X 
			iCheck = 10; 
		else if( isNaN(iCheck) ) //0-9 
			iCheck = parseInt(cCheck); 
		else { 
			return "校验位字符不合法"+cCheck+"||"+iCheck; 
		}
		
		iSum = 0; 
		for( i=0;i<8;i++) { 
			iC = sItems[0].charAt(i); 
			if(isNaN(iC)){
				iVal = sItems[0].charCodeAt(i) - 55; 
				if( iVal < 10 || iVal > 35 ) {
					//A-Z begin10  
					return "第"+(i+1)+"位本体字符不合法"; 
				}
			}else 
				iVal = parseInt(iC);
			iSum += iVal * iW[i]; 
		} 
		iChk = 11 - iSum % 11; 
		
		if( iChk == 11 ) 
			iChk = 0; 
		
		if(iCheck != iChk ){ 
			return "校验位不符"; 
		}else{
			return null; 
		}
	},
	
	
	//工商行政管理注册号的校验规则
	checLicenseNo:function(loanCardCode) {
		
		var financecode = new Array();
		var financevalue = new Array();
		
		if(loanCardCode.length!=15){
			return "企业营业执照长度为15位";
		}
		for (i=0;i<loanCardCode.length;i++){
		 	financecode[i]= loanCardCode.charAt(i);
		 	if(i>15){
		 		return "企业营业执照长度不能大于15位";
		 	}
		}
		var checkValue = new Array(14);
		var initialValue = 10;
		var totalValue = 0;
		var c = 0;
		
		for ( j = 0; j < 14; j++) {
			if (j==0){
				checkValue[j] = parseInt(initialValue,10) + parseInt(financecode[j],10);
			}else{
				checkValue[j] = parseInt(financevalue[j-1],10) + parseInt(financecode[j],10);
			}
			
			if(checkValue[j]==10){
				financevalue[j]=(10*2)%11;
			}else{
				financevalue[j]=((checkValue[j]%10)*2)%11; 
			}
		}
		val = (parseInt(financevalue[13],10)+parseInt(financecode[14],10))%10;
		if(val == 1){
			return null;
		}else{
			return "企业营业执格式错误!";
		}
	},
	
	//身份证号码验证，支持15位，18位大陆身份证号验证，支持HKG、MAC、TWN身份证号码验证
	//idCard 身份证号码; countryCode 国别代码
	validateIDCard:function(idCard, countryCode){
		
		
		if(null == idCard || '' == idCard.trim()){
			return "身份证号码不能为空!";
		}
		//	idCard=idCard.trim();
	    //如果国别为空，默认为中国大陆CHN
	    if(null == countryCode || '' == countryCode.trim()){
	    	countryCode = 'CHN';
	    }
	    countryCode = countryCode.toUpperCase();
	    
	    if('CHN' == countryCode){
	    	var idCardLength=idCard.length;
	    	if(15 == idCardLength){
	    		var regTest15 =  /^\d{15}$/g;
	    		if (!regTest15.test(idCard)){
	    			return '15位身份证号码有非法字符!';
	    		}
	    		//前两位  省/直辖市的行政区划代码 判定
	    		var validateIdCardCityCodeErrMsg =this.validateIdCardCityCode(idCard);
	    		if(null!=validateIdCardCityCodeErrMsg){
	    			return validateIdCardCityCodeErrMsg;
	    		}
	    		
	    		return null;
	    	}else if (18 == idCardLength){
	    		var regTest18 =  /^\d{17}[\dx]$/gi;
	    		if(!regTest18.test(idCard)){
	    			return '18位身份证号码有非法字符!';
	    		}
	    		
	    		//前两位  省/直辖市的行政区划代码 判定
	    		var validateIdCardCityCodeErrMsg =this.validateIdCardCityCode(idCard);
	    		if(null!=validateIdCardCityCodeErrMsg){
	    			return validateIdCardCityCodeErrMsg;
	    		}
	    		
	    		//年月的字段 判定
	    		var validate18IdCardBirthDayErrMsg =this.validate18IdCardBirthDay(idCard);
	    		if(null!=validate18IdCardBirthDayErrMsg){
	    			return validate18IdCardBirthDayErrMsg;
	    		}
	    		
	    		//中国大陆18位居民身份证号码校验位 判定
				var validate18IdCardRuleErrMsg = this.validate18IdCardISO7064(idCard);
				if(null != validate18IdCardRuleErrMsg){
					return validate18IdCardRuleErrMsg;
				}
					    		
	    		return null;
	    	}else{
	    		return '身份证号码长度不正确!';
	    	}
	    }else if('HKG' == countryCode || 'MAC' == countryCode || 'TWN' == countryCode){
	    	var idCardLength=idCard.length;
	    	if(10 != idCardLength && 11 != idCardLength){
	    		return 'HKG、MAC、TWN身份证号码长度必须是10或者11!';
	    	}
	    	var regTest=/^[\w\(\)#%\*-<>&\./\\]{10,11}$/g;
	    	if(!regTest.test(idCard)){
	    		return 'HKG、MAC、TWN身份证号码有非法字符!';
	    	}
	    	return null;
	    }else{
	    	return "不支持的国别码"+countryCode+"!";
	    }
	},
	
	//18位身份证校验位ISO7064验证
	validate18IdCardISO7064 : function(idCard){
		if(null == idCard || '' == idCard.trim() || 18 != idCard.trim().length){
		 	return '身份证号码长度不正确';
		}
		idCard=idCard.trim();
	
		var powerArray =[7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2];
		var validateCodeArray = ['1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'];
		
		var sumTmp = 0;
		for(i = 0; i < 17; i ++) {
			sumTmp += idCard.substr(i, 1) * powerArray[i];
		}
		var result = validateCodeArray[sumTmp % 11];
		if (result != idCard.substr(17, 1)){
			return '18位身份证的校验码不正确！';
		}
		
		return null;
	},
	
	//18位身份证生日验证
	validate18IdCardBirthDay:function(idCard){
		if(null == idCard || '' == idCard.trim() || 18 != idCard.trim().length){
		 	return '身份证号码长度不正确';
		}
		idCard=idCard.trim();
		
		var birthYearsStr=idCard.substr(6,4);
		var birthMonthStr=idCard.substr(10,2);
		var birthDateStr=idCard.substr(12,2);
		
		var birthStr=birthYearsStr + "/" + birthMonthStr + "/" + birthDateStr;
		var dtmBirth = new Date(birthStr);
		
		var blYear=(dtmBirth.getFullYear() == Number(birthYearsStr));
		var blMonth=((dtmBirth.getMonth() + 1) == Number(birthMonthStr));
		var blDate= (dtmBirth.getDate() == Number(birthDateStr));
		
		var blResult= blYear && blMonth && blDate;
		if(!blResult){
			return '18位身份证号码出生日期格式不正确!';
		}
		return null;
	},
	
	//身份证前两位行政地区码验证
	validateIdCardCityCode:function(idCard){
		if(null == idCard || '' == idCard.trim()){
			return "身份证号码不能为空!";
		}
		idCard=idCard.trim();
		var aCity={11:'北京',12:'天津',13:'河北',14:'山西',15:'内蒙古',
		     	   21:'辽宁',22:'吉林',23:'黑龙江 ',31:'上海',32:'江苏',
		     	   33:'浙江',34:'安徽',35:'福建',36:'江西',37:'山东',
		     	   41:'河南',42:'湖北 ',43:'湖南',44:'广东',45:'广西',
		     	   46:'海南',50:'重庆',51:'四川',52:'贵州',53:'云南',
		     	   54:'西藏 ',61:'陕西',62:'甘肃',63:'青海',64:'宁夏',
		     	   65:'新疆',71:'台湾',81:'香港',82:'澳门',91:'国外'
		};
		if(aCity[parseInt(idCard.substr(0,2))]==null){
			return "身份证前两位地区码非法!";
		}
		return null;
	},
	
	//验证各种证件号码
	onValidateCertificateCode:function(typeCd, code, e, countryCode,unique){
		
		if(null==typeCd || ''==typeCd.trim()){
			alert('证件类型不能为空');
			return;
		}
		if(null==code || ''==code.trim()){
			alert('证件号码不能为空');
			return;
		}
		if(null==e){
			alert('js调用出错，请检查输入参数e');
			return;
		}
		if(null==countryCode){
			countryCode='CHN';
		}
		
		if('10100'==typeCd){
			var validateMsg=this.validateIDCard(code, countryCode);
			if(null != validateMsg){
			    //验证身份证号码
				e.isValid = false;
				e.errorText = validateMsg;
			}
		}else if('20001'==typeCd){
		    var validateMsg=this.validCompID(code);
			if(null!=validateMsg){
			    //验证组织机构代码证,规则验证
				e.isValid = false;
				e.errorText = validateMsg;
			}else{
				//验证组织机构代码证,唯一性验证
				this.checkOrgNumUnique(e,typeCd,code,unique);
			}
		}else if('20100'==typeCd||'20101'==typeCd||'20102'==typeCd){
		   //校验企业营业执照的唯一性
			 var uniqueMsg = this.checkOrgNumUnique(e,typeCd,code,unique);
			 if(null!=uniqueMsg){
			 	e.isValid = false;
				e.errorText = uniqueMsg;
			 }else{
			 	var validateMsg=this.checLicenseNo(code);
			 	if(validateMsg){
			 		alert(validateMsg);
			 	}
		   	 	
		    	//return null;
			 }
		    //验证企业营业执照,规则验证
		    //var validateMsg=this.checLicenseNo(code);
		    //alert(validateMsg);
		    //return null;
			//if(null!=validateMsg){
			//	e.isValid = false;
			//	e.errorText = validateMsg;
			//}else{
			//	//验证企业营业执照,唯一性验证
			//	this.checkOrgNumUnique(e,typeCd,code,unique);
			//}
		}else if('10400'==typeCd){
			//验证护照，规则验证
			 var validateMsg=this.onValidatePassport(code);
			if(null!=validateMsg){
				e.isValid = false;
				e.errorText = validateMsg;
			}
		}
	},
	//验证护照
	onValidatePassport:function(code){
		if(code.length<=2){
			return '护照证件号长度不符合规则';
		}
		var regTest = /[/W\.]/g;
		if(!regTest.test(idCard)){
	    	return '护照编号不符合规则';
	    }
	
	},
	
	//国税地税号   去除证件号长度校验  add by zhangfahui
	onValidateTax:function(code){
//		if(code.value.length!=20&&code.value.length!=15){
//			code.errorText = '证件号长度不符合规则';
//			code.isValid = false;
//			return ;
//		}
		
		var regTest = /^([\w]{15}|[\w]{20})$/g;
		if(!regTest.test(code.value)){
			code.errorText = '证件号不符合规则,只能是15或者20位编号';
			code.isValid = false;
			return ;
	    }
	
	},
	//机构信用代码
	onValidatexingyong:function(code){
		if(code.value!=""){
			if(code.value.length!=18){
				code.errorText = '证件号长度不符合规则';
				code.isValid = false;
				return ;
			}
			var regTest = /^[\w]*$/g;
			if(!regTest.test(code.value)){
				code.errorText = '证件号不符合规则';
				code.isValid = false;
				return ;
		    }
		}
		
	
	},
	onValidateChinese:function(code){
		var re = new RegExp("^[\u4e00-\u9fa5]+$");
        if (!re.test(code.value)) {
        	code.errorText = '只能输入中文';
			code.isValid = false;
        	return;
        }
	}
};

