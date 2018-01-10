package com.bos.csm.enterpriseSize;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.Request;

import com.eos.data.datacontext.UserObject;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.system.annotation.Bizlet;
import com.sybase.jdbc2.jdbc.DateObject;

import commonj.sdo.DataObject;

@Bizlet("UpdateEnterpriseSize")
public class UpdateEnterpriseSize {
	@Bizlet("")
	public int updateSize() {// 四部委企业规模计算
		
		// 公司客户集合
		DataObject corplist[] = null;
		// 查询模板（公司表）
		DataObject PartyIdForCorp = DataObjectUtil
				.createDataObject("com.bos.csm.corporation.corporation.GetPartyIdForCorp");
		//Timer timer = new Timer();//定时器，清理提示使用
		// 根据特定条件查询，可以有多个条件
		// corplist[i].set("partyId", "11111");
		// 查询出所有公司客户
		corplist = DatabaseUtil.queryEntitiesByTemplate("default", PartyIdForCorp);
		
		//System.out.println("开始计算");
		
		String msg = null;//提示消息
		//时间转换工具类
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		DataObject party = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");//参与人
		for(int i=0;i<corplist.length;i++){
			
		
			DataObject bridlist[] = null;
			DataObject brid = DataObjectUtil.createDataObject("com.bos.dataset.acc.TbAccCustomerFinance");
			
			// 设置查询参数
			brid.set("partyId", corplist[i].get("partyId"));
			brid.set("financeStatusCd","02");
			brid.set("financeTypeCd","1");
			bridlist = DatabaseUtil.queryEntitiesByTemplate("default", brid);
			if (bridlist.length > 0 && bridlist != null) {
				int index = -1;// 记录需要获取的对象下标
				Date briddDate = null;// 年报时间

				for (int j = 0; j < bridlist.length; j++) {// 判断取哪条财报数据
						try {
								if (briddDate == null) {
									
									briddDate = sdf.parse(bridlist[j].get("financeDeadline").toString());
									
									index = j;// 暂时需要此数据
								} else {
									if (briddDate.before(sdf.parse(bridlist[j].get("financeDeadline").toString()))) {// 比较时间（如果比A新）
										
										briddDate = sdf.parse(bridlist[j].get("financeDeadline").toString());// 记录最新的时间
										
										index = j;// 暂时需要此数据
									}
								}
						} catch (ParseException e) {
							// TODO 自动生成 catch 块
							System.out.println("数据转换异常");
							e.printStackTrace();
						}
				}
				
				
				if(index!=-1){
//					 执行到此处 说明已经从财报中获取到需要的数据
					int employeesNumber = 0; // 从业人数
					String industrialTypeCd = null; // 国标行业大类
					BigDecimal businessIncome = null;//营业收入
					BigDecimal TotalAssets =  null;//资产总额
					String fourzEnterpriseSizeCd = corplist[i].getString("fourzEnterpriseSizeCd");//企业规模
					boolean nlFlag = false;//是否有字段为空 
					
					party.set("partyId",corplist[i].get("partyId"));
					DatabaseUtil.expandEntity("default", party);//查询客户信息
					DataObject corp = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmCorporation");
					corp.set("partyId",corplist[i].get("partyId"));
					corp.set("employeesNumber",corplist[i].get("employeesNumber"));
					corp.set("industrialTypeCd",corplist[i].get("industrialTypeCd"));
					//DataObjectUtil.convertDataObject(corp, "com.bos.dataset.csm.TbCsmCorporation", false);//转换数据类型
					
					
					//获取index_data 值
					DataObject FinanceIndexData = DataObjectUtil.createDataObject("com.bos.dataset.acc.TbAccFinanceIndexData");
					
					FinanceIndexData.set("financeId",bridlist[index].get("financeId"));//对应报表主键
					FinanceIndexData.set("indexCd","b059");//对应科目代码(营业收入)
					DatabaseUtil.expandEntityByTemplate("default", FinanceIndexData, FinanceIndexData);
					
					
					
					
					if(FinanceIndexData.get("indexValueDataType")!=null){
						businessIncome = BigDecimal.valueOf(Double.parseDouble((FinanceIndexData.get("indexValueDataType").toString())));// //营业收入
						corplist[i].set("business_Income",businessIncome.intValue());
					}
					
					FinanceIndexData.set("financeId",bridlist[index].get("financeId"));//对应报表主键
					FinanceIndexData.set("indexCd","b026");//对应科目代码(营业收入)
					DatabaseUtil.expandEntityByTemplate("default", FinanceIndexData, FinanceIndexData);
					
					
					if(FinanceIndexData.get("indexValueDataType")!=null){
						TotalAssets = BigDecimal.valueOf(Double.parseDouble((FinanceIndexData.get("indexValueDataType").toString())));// 资产总额
						corplist[i].set("total_Assets",TotalAssets.intValue());
					}
					
					
					
					if (businessIncome == null ) {
						corp.set("fourzEnterpriseSizeCd", "2");// 默认中型
						nlFlag = true;
					}
					if (TotalAssets ==null ) {
						corp.set("fourzEnterpriseSizeCd", "2");// 默认中型
						nlFlag = true;
					}
					if (corp.get("employeesNumber") != null) {
						employeesNumber = Integer.parseInt(corp.get("employeesNumber").toString());
					} else {
						employeesNumber = 0;
					}
					if (corp.get("industrialTypeCd") != null) {
						industrialTypeCd = corp.get("industrialTypeCd").toString();
					} else {
						corp.set("fourzEnterpriseSizeCd", "2");// 默认中型
						nlFlag = true;
					}
					
					if(nlFlag==true){
						DatabaseUtil.updateEntity("default", corp);
						msg = party.getString("partyNum")+","+party.getString("partyName")+","+bridlist.length+","+businessIncome+","+TotalAssets+","+industrialTypeCd+","+employeesNumber+","+corp.get("fourzEnterpriseSizeCd")+","+fourzEnterpriseSizeCd;
						System.out.println(msg);
						continue;
					}
					
					//接收新企业规模
					fourzEnterpriseSizeCd = CalculateSize(businessIncome.intValue()/10000, employeesNumber, TotalAssets.intValue()/10000,industrialTypeCd);
					
					corp.set("fourzEnterpriseSizeCd",fourzEnterpriseSizeCd);//设置新的企业规模

					//String syso = "主键："+corp.get("partyId")+"获取到财报数量"+bridlist.length+"选中下标为："+index+",已经执行到第"+(i+1)+"笔数据,"+fourzEnterpriseSizeCd;
					
										
					
					msg = party.getString("partyNum")+","+party.getString("partyName")+","+bridlist.length+","+businessIncome+","+TotalAssets+","+industrialTypeCd+","+employeesNumber+","+corp.get("fourzEnterpriseSizeCd")+","+fourzEnterpriseSizeCd;
					
					
					
					DatabaseUtil.updateEntity("default", corp);
					System.out.println(msg);
				}
			}else{
				System.out.println("主键："+corplist[i].get("partyId")+"无报表");
			}
		}
			System.out.println(msg);
			return corplist.length;
	}
	

	@Bizlet("")
	public String CalculateSize(int incomeSum, int employees,
			int totalAssets, String sIndustryTypeNew) {
		String fourzEnterpriseSizeCd = null;
		// 农、林、牧、渔业
		if (sIndustryTypeNew.substring(0, 1).equals("A")) {
			if (incomeSum < 20000) {
				if (incomeSum < 50) {
					fourzEnterpriseSizeCd = "4";
					fourzEnterpriseSizeCd = "4";
				}
				if (incomeSum >= 50) {
					fourzEnterpriseSizeCd = "3";
				}
				if (incomeSum >= 500) {
					fourzEnterpriseSizeCd = "2";
				}
			} else {
				fourzEnterpriseSizeCd = "1";
			}
		}

		// 工业
		else if (sIndustryTypeNew.substring(0, 1).equals("B")
				|| sIndustryTypeNew.substring(0, 1).equals("C")
				|| sIndustryTypeNew.substring(0, 1).equals("D")){
			if (employees < 1000 || incomeSum < 40000) {
				if (incomeSum < 300 || employees < 20) {
					fourzEnterpriseSizeCd = "4";
				}
				if ((incomeSum >= 300) && (employees >= 20)) {
					fourzEnterpriseSizeCd = "3";
				}
				if ((incomeSum >= 2000) && (employees >= 300)) {
					fourzEnterpriseSizeCd = "2";
				}
			} else {
				fourzEnterpriseSizeCd = "1";
			}
		}

		// 建筑业
		else if (sIndustryTypeNew.substring(0, 1).equals("E")) {
			if (incomeSum < 80000 || totalAssets < 80000) {
				if (incomeSum < 300 || totalAssets < 300) {
					fourzEnterpriseSizeCd = "4";
				}
				if ((incomeSum >= 300) && (totalAssets >= 300)) {
					fourzEnterpriseSizeCd = "3";
				}
				if ((incomeSum >= 6000) && (totalAssets >= 5000)) {
					fourzEnterpriseSizeCd = "2";
				}
			} else {
				fourzEnterpriseSizeCd = "1";
			}
		}

		// 批发业
		else if (sIndustryTypeNew.substring(0, 3).equals("F51")) {
			if (incomeSum < 40000 || employees < 200) {
				if (incomeSum < 1000 || employees < 5) {
					fourzEnterpriseSizeCd = "4";
				}
				if ((incomeSum >= 1000) && (employees >= 5)) {
					fourzEnterpriseSizeCd = "3";
				}
				if ((incomeSum >= 5000) && (employees >= 20)) {
					fourzEnterpriseSizeCd = "2";
				}
			} else {
				fourzEnterpriseSizeCd = "1";
			}
		}

		// 零售业
		else if (sIndustryTypeNew.substring(0, 3).equals("F52")) {
			if (incomeSum < 20000 || employees < 300) {
				if (incomeSum < 100 || employees < 10) {
					fourzEnterpriseSizeCd = "4";
				}
				if ((incomeSum >= 100) && (employees >= 10)) {
					fourzEnterpriseSizeCd = "3";
				}
				if ((incomeSum >= 500) && (employees >= 50)) {
					fourzEnterpriseSizeCd = "2";
				}
			} else {
				fourzEnterpriseSizeCd = "1";
			}
		}

		// 交通运输业
		else if (sIndustryTypeNew.substring(0, 3).equals("G54")
				|| sIndustryTypeNew.substring(0, 3).equals("G55")
				|| sIndustryTypeNew.substring(0, 3).equals("G56")
				|| sIndustryTypeNew.substring(0, 3).equals("G57")
				|| sIndustryTypeNew.substring(0, 3).equals("G58")) {
			if (incomeSum < 30000 || employees < 1000) {
				if (incomeSum < 200 || employees < 20) {
					fourzEnterpriseSizeCd = "4";
				}
				if ((incomeSum >= 200) && (employees >= 20)) {
					fourzEnterpriseSizeCd = "3";
				}
				if ((incomeSum >= 3000) && (employees >= 300)) {
					fourzEnterpriseSizeCd = "2";
				}
			} else {
				fourzEnterpriseSizeCd = "1";
			}
		}

		// 仓储业
		else if (sIndustryTypeNew.substring(0, 3).equals("G59")) {
			if (incomeSum < 30000 || employees < 200) {
				if (incomeSum < 100 || employees < 20) {
					fourzEnterpriseSizeCd = "4";
				}
				if ((incomeSum >= 100) && (employees >= 20)) {
					fourzEnterpriseSizeCd = "3";
				}
				if ((incomeSum >= 1000) && (employees >= 100)) {
					fourzEnterpriseSizeCd = "2";
				}
			} else {
				fourzEnterpriseSizeCd = "1";
			}
		}

		// 邮政业
		else if (sIndustryTypeNew.substring(0, 3).equals("G60")) {
			if (incomeSum < 30000 || employees < 1000) {
				if (incomeSum < 100 || employees < 20) {
					fourzEnterpriseSizeCd = "4";
				}
				if ((incomeSum >= 100) && (employees >= 20)) {
					fourzEnterpriseSizeCd = "3";
				}
				if ((incomeSum >= 2000) && (employees >= 300)) {
					fourzEnterpriseSizeCd = "2";
				}
			} else {
				fourzEnterpriseSizeCd = "1";
			}
		}

		// 住宿业
		else if (sIndustryTypeNew.substring(0, 3).equals("H61")) {
			if (incomeSum < 10000 || employees < 300) {
				if (incomeSum < 100 || employees < 10) {
					fourzEnterpriseSizeCd = "4";
				}
				if ((incomeSum >= 100) && (employees >= 10)) {
					fourzEnterpriseSizeCd = "3";
				}
				if ((incomeSum >= 2000) && (employees >= 100)) {
					fourzEnterpriseSizeCd = "2";
				}
			} else {
				fourzEnterpriseSizeCd = "1";
			}
		}

		// 餐饮业
		else if (sIndustryTypeNew.substring(0, 3).equals("H62")) {
			if (incomeSum < 10000 || employees < 300) {
				if (incomeSum < 100 || employees < 10) {
					fourzEnterpriseSizeCd = "4";
				}
				if ((incomeSum >= 100) && (employees >= 10)) {
					fourzEnterpriseSizeCd = "3";
				}
				if ((incomeSum >= 2000) && (employees >= 100)) {
					fourzEnterpriseSizeCd = "2";
				}
			} else {
				fourzEnterpriseSizeCd = "1";
			}
		}

		// 信息传输业
		else if (sIndustryTypeNew.substring(0, 3).equals("I63")
				|| sIndustryTypeNew.substring(0, 3).equals("I64")) {
			if (incomeSum < 100000 || employees < 2000) {
				if (incomeSum < 100 || employees < 10) {
					fourzEnterpriseSizeCd = "4";
				}
				if ((incomeSum >= 100) && (employees >= 10)) {
					fourzEnterpriseSizeCd = "3";
				}
				if ((incomeSum >= 1000) && (employees >= 100)) {
					fourzEnterpriseSizeCd = "2";
				}
			} else {
				fourzEnterpriseSizeCd = "1";
			}
		}

		// 软件和信息技术服务业
		else if (sIndustryTypeNew.substring(0, 3).equals("I65")) {
			if (incomeSum < 10000 || employees < 300) {
				if (incomeSum < 50 || employees < 10) {
					fourzEnterpriseSizeCd = "4";
				}
				if ((incomeSum >= 50) && (employees >= 10)) {
					fourzEnterpriseSizeCd = "3";
				}
				if ((incomeSum >= 1000) && (employees >= 100)) {
					fourzEnterpriseSizeCd = "2";
				}
			} else {
				fourzEnterpriseSizeCd = "1";
			}
		}

		// 房地产开发经营业
		else if (sIndustryTypeNew.substring(0, 4).equals("K701")) {
			if (incomeSum < 200000 || totalAssets < 10000) {
				if (incomeSum < 100 || totalAssets < 2000) {
					fourzEnterpriseSizeCd = "4";
				}
				if ((incomeSum >= 100) && (totalAssets >= 2000)) {
					fourzEnterpriseSizeCd = "3";
				}
				if ((incomeSum >= 1000) && (totalAssets >= 5000)) {
					fourzEnterpriseSizeCd = "2";
				}
			} else {
				fourzEnterpriseSizeCd = "1";
			}
		}

		// 物业管理
		else if (sIndustryTypeNew.substring(0, 4).equals("K702")) {
			if (incomeSum < 5000 || employees < 1000) {
				if (incomeSum < 500 || employees < 100) {
					fourzEnterpriseSizeCd = "4";
				}
				if ((incomeSum >= 500) && (employees >= 100)) {
					fourzEnterpriseSizeCd = "3";
				}
				if ((incomeSum >= 1000) && (employees >= 300)) {
					fourzEnterpriseSizeCd = "2";
				}
			} else {
				fourzEnterpriseSizeCd = "1";
			}
		}

		// 租赁和商务服务业
		else if (sIndustryTypeNew.substring(0, 1).equals("L")) {
			if (totalAssets < 120000 || employees < 300) {
				if (totalAssets < 100 || employees < 10) {
					fourzEnterpriseSizeCd = "4";
				}
				if ((totalAssets >= 100) && (employees >= 10)) {
					fourzEnterpriseSizeCd = "3";
				}
				if ((totalAssets >= 8000) && (employees >= 100)) {
					fourzEnterpriseSizeCd = "2";
				}

			} else {
				fourzEnterpriseSizeCd = "1";
			}
		}

		// 其他行业
		else {
			if (employees < 300) {
				if (employees < 10) {
					fourzEnterpriseSizeCd = "4";
				}
				if (employees >= 10) {
					fourzEnterpriseSizeCd = "3";
				}
				if (employees >= 100) {
					fourzEnterpriseSizeCd = "2";
				}
			} else {
				fourzEnterpriseSizeCd = "1";
			}
		}
		/*
		 *  1 大型企业
			2 中型企业
			3 小型企业
			4 微型企业
			9 其他
		 */
		return fourzEnterpriseSizeCd;
	}
}
