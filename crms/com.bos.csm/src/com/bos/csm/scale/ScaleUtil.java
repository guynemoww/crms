/**
 * 
 */
package com.bos.csm.scale;

import com.eos.system.annotation.Bizlet;

/**
 * @author 陈川
 * @date 2016-03-31 14:48:46
 * 
 */
@Bizlet("企业规模计算公共类")
public class ScaleUtil {

	@Bizlet("查询企业客户的企业规模类型")
	public String getScaleTypeCd(String industrialTypeSamllCd) {
		String industrialTypeCd = industrialTypeSamllCd.substring(0, 1);
		String industrialTypeBigCd = industrialTypeSamllCd.substring(0, 3);
		String industrialTypeMidCd = industrialTypeSamllCd.substring(0, 4);
		if ("A".equals(industrialTypeCd)) {// 农林牧渔业与      行方提供的A农林牧渔业
			return "A0000";
		}else if ("B".equals(industrialTypeCd)) {// 工业企业(复杂)   行方提供的B其他未列明行业 
			return "Z0000";
		} /*else if ("B,C,D".contains(industrialTypeCd)) {// 工业企业(复杂)
			return "C0000,B0000,D0000";
		}*/ 
		else if ("C".equals(industrialTypeCd)) {// 工业企业(复杂)   行方提供的	C 工业
			return "C0000,B0000,D0000";
		}
		else if ("D".equals(industrialTypeCd)) {// 工业企业(复杂)   行方提供的D其他未列明行业 
			return "Z0000";
		}
		else if ("E".equals(industrialTypeCd)) {// 建筑业                   行方提供的E  建筑业
			return "E0000";
		} 
		else if ("F".equals(industrialTypeCd)) {// 零售/批发       
			if ("F51".equals(industrialTypeBigCd)) {// 批发业企业  行方提供的F	51  批发业
				return "F5100";
			} else {// 零售业企业                                                                                     行方提供的F52  零售
				return "F5200";
			}
			
		} 
		else if ("G".equals(industrialTypeCd)) {// 交通运输、仓储和邮政业
			if ("G59".equals(industrialTypeBigCd)) {// 仓储企业  行方提供的G59  仓储业
				return "G5900";
			} else if ("G60".equals(industrialTypeBigCd)) {// 邮政业企业  行方提供的G60   邮政业
				return "G6000";
	/*		} else if ("G53".equals(industrialTypeBigCd)) {// 铁路运输业企业(其他)
				return "Z0000";*/
			} else {// 交通运输业企业
				return "G0000"; //行方提供的G53   G54  G55 G56 G57 G58 交通运输业        
			}
		} 
		else if ("H".equals(industrialTypeCd)) {// 住宿业/餐饮业
			if ("H61".equals(industrialTypeBigCd)) {// 住宿业   方提供的 H61 住宿业
				return "H6100";
			} else {// 餐饮业(默认)   方提供的 H62 餐饮业
				return "H6200";
			}
		} 
		else if ("I".equals(industrialTypeCd)) {// 信息传输、软件和信息技术服务业
			if ("I65".equals(industrialTypeBigCd)) {// 软件和信息技术服务业 方提供的 I65 住宿业 软件和信息技术服务业
				return "I6500";
			} else {// 信息传输企业(默认)  方提供的 I64 	I63信息传输业
				return "I0000";
			}
		} 
		else if ("J".equals(industrialTypeCd)) {// 金融业   方提供的J 其他未列明行业
/*			if ("J67".equals(industrialTypeBigCd)) {// 证券业金融机构J6700
				return "J6700";
			} else if ("J68".equals(industrialTypeBigCd)) {// 保险业金融机构J6800
				return "J6800";
			} else if ("J6610,J6631,J6639,J6640".contains(industrialTypeSamllCd)) {// 银行业非存款类金融机构J6600
				return "J6600";
			} else if ("J6620".equals(industrialTypeSamllCd)) {// 银行业存款类金融机构J6601
				return "J6601";
			} else if ("J6633".equals(industrialTypeSamllCd)) {// 贷款公司、小额贷款公司及典当行J6602
				return "J6602";
			} else if ("J6910".equals(industrialTypeSamllCd)) {// 信托公司J6901
				return "J6901";
			} else if ("J6920".equals(industrialTypeSamllCd)) {// 金融控股公司J6902
				return "J6902";
			} else if ("J6930,J6940,J6990".contains(industrialTypeSamllCd)) {// 除贷款公司、小额贷款公司、典当行以外的其他金融机构J6903
				return "J6903";
			}*/
			return "Z0000";
		} 
		else if ("K".equals(industrialTypeCd)) {// 房地产业
			if ("K702".equals(industrialTypeMidCd)) {// 物业管理     方提供的 K702 物业管理
				return "K7020";
			} else if ("K701".equals(industrialTypeMidCd)) {// 房地产开发经营  方提供的 K701 房地产开发经营
				return "K7010";
			} else {// 其他  方提供的 K703 K704 K709 房地产开发经营
				return "Z0000";
			}
		} 
		else if ("L".equals(industrialTypeCd)) {// 租赁和商务服务业   方提供的L71  L72 租赁和商务服务业
			return "L0000";
		} else {
			return "Z0000";  // M N O P Q R S T 方提供的租赁和商务服务业
		}


	}

	public static void main(String[] args) {
		String aString = new ScaleUtil().getScaleTypeCd("J6729");
		System.out.println(aString);
	}
}
