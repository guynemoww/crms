/**
 * 
 */
package com.bos.comm.pub;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.bos.pub.GitUtil;
import com.bos.pub.exception.ParamEmptyException;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.system.annotation.Bizlet;
import com.informix.util.stringUtil;
import commonj.sdo.DataObject;

/**
 * @author lujinbin
 * @date 2014-07-01 10:27:56
 * 
 */
@Bizlet("")
public class CheckRemote {
	@Bizlet("校验银监局标准是否异地")
	public String cRemote(String orgId, String creditCrd) {
		String cardNumTwo = "";
		String cardNumFour = "";
		String errMsg = "判断错误";
		String orgSeq = null;
		DataObject orgO = null;
		String[] arr = null;
		List list = null;
		boolean flag = false;
		boolean flag1 = false;
		String[] shSeq = { "10007", "10010", "10008", "10009", "10011",
				"10012", "10013", "100131", "100132", "10014", "10309","10118","10199","10046","10347" };
		DataObject sdo = DataObjectUtil
				.createDataObject("com.bos.utp.dataset.organization.OmOrganization");
		sdo.setString("orgid", orgId);
		try {
			orgO = GitUtil.queryEntityByTemplate(sdo);
			if (orgO == null) {

			} else {
				orgSeq = orgO.getString("orgseq");
				arr = orgSeq.toString().substring(1, orgSeq.length()).replace(
						".", "-").split("-");
				list = Arrays.asList(arr);

			}
		} catch (ParamEmptyException e) {
			e.printStackTrace();
		}

		if (orgId != "" && orgId != null && creditCrd != ""
				&& creditCrd != null && orgSeq != "" && orgSeq != null) {
			cardNumTwo = creditCrd.substring(0, 2);// 贷款卡号前2位
			cardNumFour = creditCrd.substring(0, 4);// 贷款卡号前4位
			for (int j = 0; j < shSeq.length; j++) {
				flag1 = list.contains(shSeq[j]);
				if (flag1 == true) {// 存在
					break;
				}
			}
			if ((flag1 == true || orgId.equals("10000")) && !cardNumTwo.equals("31")) {
				return "省外异地";
			} else if (list.contains("10356")) {
				if (!cardNumTwo.equals("33")) {
					return "省外异地";
				} else {
					if (!cardNumFour.equals("3302")) {
						return "省内异地";
					} else {
						return "非异地";
					}
				}
			} else if (list.contains("10438")) {
				if (!cardNumTwo.equals("33")) {
					return "省外异地";
				} else {
					if (!cardNumFour.equals("3302")) {
						return "省内异地";
					} else {
						return "非异地";
					}
				}
			} else if (list.contains("10490")) {
				if (!cardNumTwo.equals("12")) {
					return "省外异地";
				} else {
					return "非异地";
				}
			} else if (list.contains("10541")) {
				if (!cardNumTwo.equals("51")) {
					return "省外异地";
				} else {
					if (!cardNumFour.equals("5101")) {
						return "省内异地";
					} else {
						return "非异地";
					}
				}
			} else if (list.contains("10575")) {
				if (!cardNumTwo.equals("44")) {
					return "省外异地";
				} else {
					if (!cardNumFour.equals("4403")) {
						return "省内异地";
					} else {
						return "非异地";
					}
				}
			} else if (list.contains("10628")) {
				if (!cardNumTwo.equals("11")) {
					return "省外异地";
				} else {
					return "非异地";
				}
			} else if (list.contains("10389")) {
				if (!cardNumTwo.equals("32")) {
					return "省外异地";
				} else {
					if (!cardNumFour.equals("3201")) {
						return "省内异地";
					} else {
						return "非异地";
					}
				}
			} else if (list.contains("10656")) {
				if (!cardNumTwo.equals("32")) {
					return "省外异地";
				} else {
					if (!cardNumFour.equals("3205")) {
						return "省内异地";
					} else {
						return "非异地";
					}
				}
			} else if (list.contains("10424")) {
				if (!cardNumTwo.equals("32")) {
					return "省外异地";
				} else {
					if (!cardNumFour.equals("3202")) {
						return "省内异地";
					} else {
						return "非异地";
					}
				}
			} 
			else if (list.contains("10476")) {
				if (!cardNumTwo.equals("33")) {
					return "省外异地";
				} else {
					if (!cardNumFour.equals("3202")) {
						return "省内异地";
					} else {
						return "非异地";
					}
				}
			}else {
				return "非异地";
			}

		} else {
			return "条件不能为空";
		}
	}
	@Bizlet("校验行内标准是否异地")
	public String cBankRemote(String orgId, String creditCrd) {
		String cardNumTwo = "";
		String cardNumFour = "";
		String errMsg = "判断错误";
		String orgSeq = null;
		DataObject orgO = null;
		String[] arr = null;
		List list = null;
		boolean flag = false;
		boolean flag1 = false;
		String[] shSeq = { "10007", "10010", "10008", "10009", "10011",
				"10012", "10013", "100131", "100132", "10014", "10309","10118","10199","10046","10347" };
		DataObject sdo = DataObjectUtil
				.createDataObject("com.bos.utp.dataset.organization.OmOrganization");
		sdo.setString("orgid", orgId);
		try {
			orgO = GitUtil.queryEntityByTemplate(sdo);
			if (orgO == null) {

			} else {
				orgSeq = orgO.getString("orgseq");
				arr = orgSeq.toString().substring(1, orgSeq.length()).replace(
						".", "-").split("-");
				list = Arrays.asList(arr);

			}
		} catch (ParamEmptyException e) {
			e.printStackTrace();
		}

		if (orgId != "" && orgId != null && creditCrd != ""
				&& creditCrd != null && orgSeq != "" && orgSeq != null) {
			cardNumTwo = creditCrd.substring(0, 2);// 贷款卡号前2位
			cardNumFour = creditCrd.substring(0, 4);// 贷款卡号前4位
			for (int j = 0; j < shSeq.length; j++) {
				flag1 = list.contains(shSeq[j]);
				if (flag1 == true) {// 存在
					break;
				}
			}
			if ((flag1 == true || orgId.equals("10000")) && !cardNumTwo.equals("31")) {
				return "省外异地";
			} else if (list.contains("10356")) {
					if (!cardNumFour.equals("3302")) {
						return "异地";
					} else {
						return "非异地";
					}
			} else if (list.contains("10438")) {
				if (!cardNumTwo.equals("33")) {
					return "异地";
				} else {
					if (cardNumFour.equals("3302")||cardNumFour.equals("3306")) {
						return "异地";
					} else {
						return "非异地";
					}
				}
			} else if (list.contains("10490")) {
				if (!cardNumTwo.equals("12")) {
					return "异地";
				} else {
					return "非异地";
				}
			} else if (list.contains("10541")) {
				if (!cardNumTwo.equals("51")) {
					return "异地";
				} else {
						return "非异地";
				}
			} else if (list.contains("10575")) {
				if (!cardNumTwo.equals("44")) {
					return "异地";
				} else {
						return "非异地";
				}
			} else if (list.contains("10628")) {
				if (!cardNumTwo.equals("11")) {
					return "异地";
				} else {
					return "非异地";
				}
			} else if (list.contains("10389")) {
				if (!cardNumTwo.equals("32")) {
					return "异地";
				} else {
					if (cardNumFour.equals("3202")||cardNumFour.equals("3205")) {
						return "异地";
					} else {
						return "非异地";
					}
				}
			} else if (list.contains("10656")) {
					if (!cardNumFour.equals("3205")) {
						return "异地";
					} else {
						return "非异地";
					}
			} else if (list.contains("10424")) {
			 
					if (!cardNumFour.equals("3202")) {
						return "异地";
					} else {
						return "非异地";
					}
			} 
			else if (list.contains("10476")) {
					if (!cardNumFour.equals("3206")) {
						return "异地";
					} else {
						return "非异地";
					}
			}else {
				return "非异地";
			}

		} else {
			return "条件不能为空";
		}
	}
}