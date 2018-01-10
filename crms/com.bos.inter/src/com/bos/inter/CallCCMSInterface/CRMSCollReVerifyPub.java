package com.bos.inter.CallCCMSInterface;

import javax.xml.bind.annotation.XmlElement;

public class CRMSCollReVerifyPub {
	/**
	 * 房地产：02000000、02010000、02010101、02010102、02010103、02010199 在建工程：02010800
	 * 期房：02010900 土地使用权：02020000、02020100、02020200、02020300、02020400、02029900
	 * 机器设备：04010000、04010100、04010200 仓单：04030100、04030101、04030102 提单：04030200
	 * 其他交通工具-船舶：04020301
	 * 应收账款：03010000、03010100、03010200、03010300、03010400、03019900
	 * 债券：01020200、01020201、01020202、01020203、01020204、01020205、01020206、01020207、01020208、01020209、01020210、01020211、01020212、01020213、01020214、
	 * 01020215、01020216、01020217、01020218、01020219、01020220、01020299
	 * 存单：01010200、01010201、01010202、01010203、01010204
	 * 不动产收益权：03020000、03020100、03020200、03020300、03020400、03020500、03020600、03020700、03020800、03020900、03029900
	 */

	// 房地产（TB_GRT_HOUSE）：房地产号
	@XmlElement(name = "housePropertyNum")
	public String housePropertyNum;

	// 在建工程（TB_GRT_PROJECTUNDER）：开发建设许可证号
	@XmlElement(name = "exploitPermitNum")
	public String exploitPermitNum;

	// 期房（TB_GRT_EXPECTHOUSE）：商品房销售合同号
	@XmlElement(name = "housesellsContractNum")
	public String housesellsContractNum;

	// 土地使用权（TB_GRT_LANDUSE）：土地使用权证号
	@XmlElement(name = "landUseNum")
	public String landUseNum;

	// 机器设备（TB_GRT_EQUIPMENT）：设备购置发票号
	@XmlElement(name = "purchaseInvoicenum")
	public String purchaseInvoicenum;

	// 仓单（TB_GRT_DEPOT）：仓单号
	@XmlElement(name = "depotNum")
	public String depotNum;

	// 提单（TB_GRT_LADBILL）：提单号
	@XmlElement(name = "ladBillNum")
	public String ladBillNum;

	// 其他交通工具-船舶（TB_GRT_RECEIVABLE 04020301）：船舶牌号
	@XmlElement(name = "shipNum")
	public String shipNum;

	// 应收账款（TB_GRT_RECEIVABLE）：销售合同号
	@XmlElement(name = "sellsContractNum")
	public String sellsContractNum;

	// 债券（TB_GRT_BOND）：债券号
	@XmlElement(name = "bondId")
	public String bondId;

	// 存单（TB_GRT_DEPOSIT）：存单号
	@XmlElement(name = "depositReceiptNum")
	public String depositReceiptNum;

	// 不动产收益权（TB_GRT_RECEIVABLE）：收费许可证明号
	@XmlElement(name = "tollProveNum")
	public String tollProveNum;

	// 应收账款（TB_GRT_RECEIVABLE）：卖方企业名称
	@XmlElement(name = "purchaseEnterprisename")
	public String purchaseEnterprisename;

	public void setHousePropertyNum(String housePropertyNum) {
		this.housePropertyNum = housePropertyNum;
	}

	public void setExploitPermitNum(String exploitPermitNum) {
		this.exploitPermitNum = exploitPermitNum;
	}

	public void setHousesellsContractNum(String housesellsContractNum) {
		this.housesellsContractNum = housesellsContractNum;
	}

	public void setLandUseNum(String landUseNum) {
		this.landUseNum = landUseNum;
	}

	public void setPurchaseInvoicenum(String purchaseInvoicenum) {
		this.purchaseInvoicenum = purchaseInvoicenum;
	}

	public void setDepotNum(String depotNum) {
		this.depotNum = depotNum;
	}

	public void setLadBillNum(String ladBillNum) {
		this.ladBillNum = ladBillNum;
	}

	public void setShipNum(String shipNum) {
		this.shipNum = shipNum;
	}

	public void setSellsContractNum(String sellsContractNum) {
		this.sellsContractNum = sellsContractNum;
	}

	public void setBondId(String bondId) {
		this.bondId = bondId;
	}

	public void setDepositReceiptNum(String depositReceiptNum) {
		this.depositReceiptNum = depositReceiptNum;
	}

	public void setTollProveNum(String tollProveNum) {
		this.tollProveNum = tollProveNum;
	}

	public void setPurchaseEnterprisename(String purchaseEnterprisename) {
		this.purchaseEnterprisename = purchaseEnterprisename;
	}

	@Override
	public String toString() {
		return "CRMSCollReVerifyPub [HousePropertyNum=" + housePropertyNum
				+ ",ExploitPermitNum=" + exploitPermitNum
				+ ",HousesellsContractNum=" + housesellsContractNum
				+ ",LandUseNum=" + landUseNum + ",PurchaseInvoicenum="
				+ purchaseInvoicenum + ",DepotNum=" + depotNum + ",LadBillNum="
				+ ladBillNum + ",ShipNum=" + shipNum + ",SellsContractNum="
				+ sellsContractNum + ",BondId=" + bondId
				+ ",DepositReceiptNum=" + depositReceiptNum + ",TollProveNum="
				+ tollProveNum + ",PurchaseEnterprisename="
				+ purchaseEnterprisename + "]";
	}
}
