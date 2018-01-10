//校验统一社会信用代码
function isValidUnifyNum(e) {
	var code = e.value;
	if (code.length != 18) {
		e.errorText = "统一社会信用代码长度错误";
		e.isValid = false;
	}
	var reg = /^([0-9ABCDEFGHJKLMNPQRTUWXY]{2})([0-9]{6})([0-9ABCDEFGHJKLMNPQRTUWXY]{9})([0-9ABCDEFGHJKLMNPQRTUWXY])$/;
	if (!reg.test(code)) {
		e.errorText ="统一社会信用代码校验错误！";
		e.isValid = false;
	}
	// 字符序列，对应的值为0-30
	var char_seq = '0123456789ABCDEFGHJKLMNPQRTUWXY';
	// 各位置的加权因子
	var weighting_factor = [ 1, 3, 9, 27, 19, 26, 16, 17, 20, 29, 25, 13, 8, 24, 10, 30, 28 ];
	var ci;
	var sum = 0;
	for (var i = 0; i < 17; i++) {
		// 取第i位字符
		ci = code.substr(i, 1);
		sum += char_seq.indexOf(ci) * weighting_factor[i];
	}
	// 校验码字符值
	var v18 = 31 - (sum % 31);
	// 取得第18位字符
	var c18 = code.substr(17, 1);
	// 比较第18位字符对应的值=？计算所得校验值
	if (v18 != char_seq.indexOf(c18)) {
		e.errorText ="统一社会信用代码有误！";
		e.isValid = false;
	}
}
// 效验组织机构代码
function isValidCompID(e) {
	var sCompID = e.value;
	if (null != sCompID && '' != sCompID) {
		if (e.isValid) {

			if (sCompID.length != 10) {
				e.errorText = "组织机构代码总长度不符";
				e.isValid = false;
			}

			if (sCompID.indexOf("-") == -1) {
				e.errorText = "组织机构代码校验不合法";
				e.isValid = false;
			}
			var sItems = sCompID.split("-");
			iW = new Array(3, 7, 9, 10, 5, 8, 4, 2);
			if (sItems.length != 2) {
				e.errorText = "不符合校验规则";
				e.isValid = false;
			}
			if (sItems[0].length != 8) {
				e.errorText = "－前长度不足";
				e.isValid = false;
			}
			if (sItems[1].length != 1) {
				e.errorText = "－后长度不足";
				e.isValid = false;
			}
			var iCheck;
			var cCheck = sItems[1].charAt(0);
			if (cCheck == 'X') // X
				iCheck = 10;
			else if (isNaN(iCheck)) // 0-9
				iCheck = parseInt(cCheck);
			else {
				e.errorText = "校验位字符不合法" + cCheck + "||" + iCheck;
				e.isValid = false;
			}

			iSum = 0;
			for (i = 0; i < 8; i++) {
				iC = sItems[0].charAt(i);
				if (isNaN(iC)) {
					iVal = sItems[0].charCodeAt(i) - 55;
					if (iVal < 10 || iVal > 35) {
						// A-Z begin10
						e.errorText = "第" + (i + 1) + "位本体字符不合法";
						e.isValid = false;
					}
				} else
					iVal = parseInt(iC);
				iSum += iVal * iW[i];
			}
			iChk = 11 - iSum % 11;

			if (iChk == 11)
				iChk = 0;

			if (iCheck != iChk) {
				e.errorText = "校验位不符";
				e.isValid = false;
			}
		}
	}
}
// 贷款卡的校验规则
function checkDKK(loanCardNum) {

	var financecode = loanCardNum.trim();
	var code = financecode;

	if (code.length != 0) {
		if (code.length != 16) {
			if (code.length != 18) {
				alert("贷款卡号必须16-18位数");
				return "false";
			}
		}
	} else {
		return "true";
	}

	if (code.match(/[A-Z0-9]{16}/) == null) {
		alert("贷款卡号不符合规则");
		return "false";
	}

	var w_i = new Array(14);
	var c_i = new Array(14);
	var j, s = 0;
	var checkid = 0;
	var c, i;

	w_i[0] = 1;
	w_i[1] = 3;
	w_i[2] = 5;
	w_i[3] = 7;
	w_i[4] = 11;
	w_i[5] = 2;
	w_i[6] = 13;
	w_i[7] = 1;
	w_i[8] = 1;
	w_i[9] = 17;
	w_i[10] = 19;
	w_i[11] = 97;
	w_i[12] = 23;
	w_i[13] = 29;

	for (j = 0; j < 14; j++) {
		if (financecode.charAt(j) >= '0' && financecode.charAt(j) <= '9') {
			c_i[j] = financecode.charCodeAt(j) - '0'.charCodeAt(0);
		} else if (financecode.charAt(j) >= 'A' && financecode.charAt(j) <= 'Z') {
			c_i[j] = financecode.charCodeAt(j) - 'A'.charCodeAt(0) + 10;
		} else {
			alert("贷款卡号不符合规则");
			return "false";
		}
		s = s + w_i[j] * c_i[j];
	}

	c = 1 + (s % 97);
	checkid = (financecode.charCodeAt(14) - '0'.charCodeAt(0)) * 10 + financecode.charCodeAt(15) - '0'.charCodeAt(0);

	if (c != checkid) {
		alert("贷款卡号不符合规则");
		return "false";
	}
	return "true";
}

// 工商行政管理注册号的校验规则
function ChecLicenseNo(loanCardCode) {
	if (loanCardCode.length != 15) {
		return false;
	}
	var financecode = new Array();
	var financevalue = new Array();

	for (i = 0; i < loanCardCode.length; i++) {
		financecode[i] = loanCardCode.charAt(i);
		if (i > 15) {
			return false;
		}
	}
	var checkValue = new Array(14);
	var initialValue = 10;
	var totalValue = 0;
	var c = 0;

	for (j = 0; j < 14; j++) {
		if (j == 0) {
			checkValue[j] = parseInt(initialValue, 10) + parseInt(financecode[j], 10);
		} else {
			checkValue[j] = parseInt(financevalue[j - 1], 10) + parseInt(financecode[j], 10);
		}

		if (checkValue[j] == 10) {
			financevalue[j] = (10 * 2) % 11;
		} else {
			financevalue[j] = ((checkValue[j] % 10) * 2) % 11;
		}
	}
	val = (parseInt(financevalue[13], 10) + parseInt(financecode[14], 10)) % 10;
	return val == 1;
}

// 四部委计算
function getType() {
	var IncomeSum = nui.get("item.businessIncome").value; // 营业收入
	var Employees = nui.get("item.employeesNumber").value; // 从业人数
	var TotalAssets = nui.get("item.totalAssets").value; // 资产总额
	var sIndustryTypeNew = nui.get("item.industrialTypeCd").value; // 国标行业大类
	if (!IncomeSum || IncomeSum == 0) {
		nui.get("item.fourzEnterpriseSizeCd").setValue("2");
		return;
	}
	if (!sIndustryTypeNew == "") {
		// 农、林、牧、渔业
		if (sIndustryTypeNew.substring(0, 1) == "A") {
			if (IncomeSum < 20000) {
				if (IncomeSum < 50) {
					nui.get("item.fourzEnterpriseSizeCd").setValue("4");
				}
				if (IncomeSum >= 50) {
					nui.get("item.fourzEnterpriseSizeCd").setValue("3");
				}
				if (IncomeSum >= 500) {
					nui.get("item.fourzEnterpriseSizeCd").setValue("2");
				}
			} else {
				nui.get("item.fourzEnterpriseSizeCd").setValue("1");
			}

		}

		// 工业
		else if (sIndustryTypeNew.substring(0, 1) == "B" || sIndustryTypeNew.substring(0, 1) == "C"
				|| sIndustryTypeNew.substring(0, 1) == "D") {
			if (Employees < 1000 || IncomeSum < 40000) {
				if (IncomeSum < 300 || Employees < 20) {
					nui.get("item.fourzEnterpriseSizeCd").setValue("4");
				}
				if ((IncomeSum >= 300) && (Employees >= 20)) {
					nui.get("item.fourzEnterpriseSizeCd").setValue("3");
				}
				if ((IncomeSum >= 2000) && (Employees >= 300)) {
					nui.get("item.fourzEnterpriseSizeCd").setValue("2");
				}
			} else {
				nui.get("item.fourzEnterpriseSizeCd").setValue("1");
			}
		}

		// 建筑业
		else if (sIndustryTypeNew.substring(0, 1) == "E") {
			if (IncomeSum < 80000 || TotalAssets < 80000) {
				if (IncomeSum < 300 || TotalAssets < 300) {
					nui.get("item.fourzEnterpriseSizeCd").setValue("4");
				}
				if ((IncomeSum >= 300) && (TotalAssets >= 300)) {
					nui.get("item.fourzEnterpriseSizeCd").setValue("3");
				}
				if ((IncomeSum >= 6000) && (TotalAssets >= 5000)) {
					nui.get("item.fourzEnterpriseSizeCd").setValue("2");
				}

			} else {
				nui.get("item.fourzEnterpriseSizeCd").setValue("1");
			}
		}
		// 批发业
		else if (sIndustryTypeNew.substring(0, 3) == "F51") {
			if (IncomeSum < 40000 || Employees < 200) {
				console.log("A");
				if (IncomeSum < 1000 || Employees < 5) {
					console.log(1);
					nui.get("item.fourzEnterpriseSizeCd").setValue("4");
				}
				if ((IncomeSum >= 1000) && (Employees >= 5)) {
					console.log(2);
					nui.get("item.fourzEnterpriseSizeCd").setValue("3");
				}
				if ((IncomeSum >= 5000) && (Employees >= 20)) {
					console.log(3);
					nui.get("item.fourzEnterpriseSizeCd").setValue("2");
				}
			} else {
				nui.get("item.fourzEnterpriseSizeCd").setValue("1");
			}
		}

		// 零售业
		else if (sIndustryTypeNew.substring(0, 3) == "F52") {
			if (IncomeSum < 20000 || Employees < 300) {
				if (IncomeSum < 100 || Employees < 10) {
					nui.get("item.fourzEnterpriseSizeCd").setValue("4");
				}
				if ((IncomeSum >= 100) && (Employees >= 10)) {
					nui.get("item.fourzEnterpriseSizeCd").setValue("3");
				}
				if ((IncomeSum >= 500) && (Employees >= 50)) {
					nui.get("item.fourzEnterpriseSizeCd").setValue("2");
				}
			} else {
				nui.get("item.fourzEnterpriseSizeCd").setValue("1");
			}
		}

		// 交通运输业sIndustryTypeNew.substring(0,3)=="G53")
		else if (sIndustryTypeNew.substring(0, 3) == "G54" || sIndustryTypeNew.substring(0, 3) == "G55"
				|| sIndustryTypeNew.substring(0, 3) == "G56" || sIndustryTypeNew.substring(0, 3) == "G57"
				|| sIndustryTypeNew.substring(0, 3) == "G58") {
			if (IncomeSum < 30000 || Employees < 1000) {
				if (IncomeSum < 200 || Employees < 20) {
					nui.get("item.fourzEnterpriseSizeCd").setValue("4");
				}
				if ((IncomeSum >= 200) && (Employees >= 20)) {
					nui.get("item.fourzEnterpriseSizeCd").setValue("3");
				}
				if ((IncomeSum >= 3000) && (Employees >= 300)) {
					nui.get("item.fourzEnterpriseSizeCd").setValue("2");
				}
			} else {
				nui.get("item.fourzEnterpriseSizeCd").setValue("1");
			}
		}

		// 仓储业
		else if (sIndustryTypeNew.substring(0, 3) == "G59") {
			if (IncomeSum < 30000 || Employees < 200) {
				if (IncomeSum < 100 || Employees < 20) {
					nui.get("item.fourzEnterpriseSizeCd").setValue("4");
				}
				if ((IncomeSum >= 100) && (Employees >= 20)) {
					nui.get("item.fourzEnterpriseSizeCd").setValue("3");
				}
				if ((IncomeSum >= 1000) && (Employees >= 100)) {
					nui.get("item.fourzEnterpriseSizeCd").setValue("2");
				}
			} else {
				nui.get("item.fourzEnterpriseSizeCd").setValue("1");
			}
		}

		// 邮政业
		else if (sIndustryTypeNew.substring(0, 3) == "G60") {
			if (IncomeSum < 30000 || Employees < 1000) {
				if (IncomeSum < 100 || Employees < 20) {
					nui.get("item.fourzEnterpriseSizeCd").setValue("4");
				}
				if ((IncomeSum >= 100) && (Employees >= 20)) {
					nui.get("item.fourzEnterpriseSizeCd").setValue("3");
				}
				if ((IncomeSum >= 2000) && (Employees >= 300)) {
					nui.get("item.fourzEnterpriseSizeCd").setValue("2");
				}
			} else {
				nui.get("item.fourzEnterpriseSizeCd").setValue("1");
			}
		}

		// 住宿业
		else if (sIndustryTypeNew.substring(0, 3) == "H61") {
			if (IncomeSum < 10000 || Employees < 300) {
				if (IncomeSum < 100 || Employees < 10) {
					nui.get("item.fourzEnterpriseSizeCd").setValue("4");
				}
				if ((IncomeSum >= 100) && (Employees >= 10)) {
					nui.get("item.fourzEnterpriseSizeCd").setValue("3");
				}
				if ((IncomeSum >= 2000) && (Employees >= 100)) {
					nui.get("item.fourzEnterpriseSizeCd").setValue("2");
				}
			} else {
				nui.get("item.fourzEnterpriseSizeCd").setValue("1");
			}
		}

		// 餐饮业
		else if (sIndustryTypeNew.substring(0, 3) == "H62") {
			if (IncomeSum < 10000 || Employees < 300) {
				if (IncomeSum < 100 || Employees < 10) {
					nui.get("item.fourzEnterpriseSizeCd").setValue("4");
				}
				if ((IncomeSum >= 100) && (Employees >= 10)) {
					nui.get("item.fourzEnterpriseSizeCd").setValue("3");
				}
				if ((IncomeSum >= 2000) && (Employees >= 100)) {
					nui.get("item.fourzEnterpriseSizeCd").setValue("2");
				}
			} else {
				nui.get("item.fourzEnterpriseSizeCd").setValue("1");
			}
		}

		// 信息传输业
		else if (sIndustryTypeNew.substring(0, 3) == "I63" || sIndustryTypeNew.substring(0, 3) == "I64") {
			if (IncomeSum < 100000 || Employees < 2000) {
				if (IncomeSum < 100 || Employees < 10) {
					nui.get("item.fourzEnterpriseSizeCd").setValue("4");
				}
				if ((IncomeSum >= 100) && (Employees >= 10)) {
					nui.get("item.fourzEnterpriseSizeCd").setValue("3");
				}
				if ((IncomeSum >= 1000) && (Employees >= 100)) {
					nui.get("item.fourzEnterpriseSizeCd").setValue("2");
				}
			} else {
				nui.get("item.fourzEnterpriseSizeCd").setValue("1");
			}
		}

		// 软件和信息技术服务业
		else if (sIndustryTypeNew.substring(0, 3) == "I65") {
			if (IncomeSum < 10000 || Employees < 300) {
				if (IncomeSum < 50 || Employees < 10) {
					nui.get("item.fourzEnterpriseSizeCd").setValue("4");
				}
				if ((IncomeSum >= 50) && (Employees >= 10)) {
					nui.get("item.fourzEnterpriseSizeCd").setValue("3");
				}
				if ((IncomeSum >= 1000) && (Employees >= 100)) {
					nui.get("item.fourzEnterpriseSizeCd").setValue("2");
				}
			} else {
				nui.get("item.fourzEnterpriseSizeCd").setValue("1");
			}
		}

		// 房地产开发经营业
		else if (sIndustryTypeNew.substring(0, 4) == "K701") {
			if (IncomeSum < 200000 || TotalAssets < 10000) {
				if (IncomeSum < 100 || TotalAssets < 2000) {
					nui.get("item.fourzEnterpriseSizeCd").setValue("4");
				}
				if ((IncomeSum >= 100) && (TotalAssets >= 2000)) {
					nui.get("item.fourzEnterpriseSizeCd").setValue("3");
				}
				if ((IncomeSum >= 1000) && (TotalAssets >= 5000)) {
					nui.get("item.fourzEnterpriseSizeCd").setValue("2");
				}
			} else {
				nui.get("item.fourzEnterpriseSizeCd").setValue("1");
			}
		}
		// 物业管理
		else if (sIndustryTypeNew.substring(0, 4) == "K702") {
			if (IncomeSum < 5000 || Employees < 1000) {
				if (IncomeSum < 500 || Employees < 100) {
					nui.get("item.fourzEnterpriseSizeCd").setValue("4");
				}
				if ((IncomeSum >= 500) && (Employees >= 100)) {
					nui.get("item.fourzEnterpriseSizeCd").setValue("3");
				}
				if ((IncomeSum >= 1000) && (Employees >= 300)) {
					nui.get("item.fourzEnterpriseSizeCd").setValue("2");
				}
			} else {
				nui.get("item.fourzEnterpriseSizeCd").setValue("1");
			}
		}

		// 租赁和商务服务业
		else if (sIndustryTypeNew.substring(0, 1) == "L") {
			if (TotalAssets < 120000 || Employees < 300) {
				if (TotalAssets < 100 || Employees < 10) {
					nui.get("item.fourzEnterpriseSizeCd").setValue("4");
				}
				if ((TotalAssets >= 100) && (Employees >= 10)) {
					nui.get("item.fourzEnterpriseSizeCd").setValue("3");
				}
				if ((TotalAssets >= 8000) && (Employees >= 100)) {
					nui.get("item.fourzEnterpriseSizeCd").setValue("2");
				}

			} else {
				nui.get("item.fourzEnterpriseSizeCd").setValue("1");
			}
		}

		// 其他行业
		else {
			if (Employees < 300) {
				if (Employees < 10) {
					nui.get("item.fourzEnterpriseSizeCd").setValue("4");
				}
				if (Employees >= 10) {
					nui.get("item.fourzEnterpriseSizeCd").setValue("3");
				}
				if (Employees >= 100) {
					nui.get("item.fourzEnterpriseSizeCd").setValue("2");
				}
			} else {
				nui.get("item.fourzEnterpriseSizeCd").setValue("1");
			}
		}

	} else if (sIndustryTypeNew == "") {
		nui.get("item.fourzEnterpriseSizeCd").setValue("5");
	}
}

function checkEnglish(code) {
	var re = new RegExp("^[a-zA-Z\_]+$");
	if (code.value != "") {
		if (!re.test(code.value)) {
			code.errorText = '只能输入英文';
			code.isValid = false;
			return;
		}
	}
}

function checkChinese(code) {
	var re = new RegExp("^[\u4e00-\u9fa5]+$");
	if (code.value != "") {
		if (!re.test(code.value)) {
			code.errorText = '只能输入中文';
			code.isValid = false;
			return;
		}
	}
}