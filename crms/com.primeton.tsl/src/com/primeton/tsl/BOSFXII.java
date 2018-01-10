package com.primeton.tsl;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

import com.primeton.plus.BusiOrgSplitRq;
import com.primeton.plus.ChangeInterControlRq;
import com.primeton.plus.ChangeIntrRq;
import com.primeton.plus.CrePayQueryRq;
import com.primeton.plus.CredMenu;
import com.primeton.plus.CredPeriodChangeRq;
import com.primeton.plus.DelayTime;
import com.primeton.plus.DiscountBackRq;
import com.primeton.plus.DiscountStopRq;
import com.primeton.plus.ExtendTimeAppRq;
import com.primeton.plus.FirstAjustPeriodRq;
import com.primeton.plus.ImpproviMenu;
import com.primeton.plus.InterSettMenu;
import com.primeton.plus.LoanCancelControlRq;
import com.primeton.plus.LoanCancelRq;
import com.primeton.plus.OffCaculate;
import com.primeton.plus.PayInterMenu;
import com.primeton.plus.PayOutRq;
import com.primeton.plus.PayVerifBackControlRq;
import com.primeton.plus.PayVerifControlRq;
import com.primeton.plus.QueryCredPayPlanRq;
import com.primeton.plus.RepayAccChangeRq;
import com.primeton.plus.RepayCancel;
import com.primeton.plus.RepayControlCancel;
import com.primeton.plus.RepayMenu;
import com.primeton.plus.RepayPlanChangeRq;
import com.primeton.plus.RepayWayChangeRq;
import com.primeton.plus.RepaymentRq;
import com.primeton.plus.SettleMenu;
import com.primeton.plus.StopControlRq;
import com.primeton.plus.StopItrRq;
import com.primeton.plus.StopStopControlRq;
import com.primeton.plus.StopStopItrRq;
import com.primeton.plus.VerificationBackRq;
import com.primeton.plus.VerificationRq;
import com.primeton.plus.VouComMenu;

@XmlRootElement(name = "BOSFXII")
public class BOSFXII implements Serializable {

	private static final long serialVersionUID = -7885264454080737832L;
	private String statusCode;
	private String message;
	
	public String getStatusCode() {
		return statusCode;
	}

	@XmlElement(name = "StatusCode")
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}


	public String getMessage() {
		return message;
	}

	@XmlElement(name = "Message")
	public void setMessage(String message) {
		this.message = message;
	}


	/*
	 * 声明T24请求报文体，每一个交易都用@XmlElement声明，中间用“,”分割，
	 * 属性name为交易名称，type为交易对应的javabean
	 * 注：交易名称和javabean不能重复，否则无法识别需要解析为什么形式
	 * */
	@XmlElements(value={@XmlElement(name="PayOutRq", type= PayOutRq.class),//放款接口
			            @XmlElement(name="OffCaculate", type= OffCaculate.class),//结清试算
			            @XmlElement(name="RepayAccChangeRq", type= RepayAccChangeRq.class),//还款账号变更
			            @XmlElement(name="RepaymentRq", type= RepaymentRq.class),//还款
			            @XmlElement(name="CrePayQueryRq", type= CrePayQueryRq.class),//贷款本息查询
			            @XmlElement(name="RepayPlanChangeRq", type= RepayPlanChangeRq.class),//还本计划变更控制信息 
			            @XmlElement(name="DiscountStopRq", type= DiscountStopRq.class),//暂停贴息
			            @XmlElement(name="DiscountBackRq", type= DiscountBackRq.class),//恢复贴息
			            @XmlElement(name="StopControlRq", type= StopControlRq.class),//停息控制信息
			            @XmlElement(name="StopItrRq", type= StopItrRq.class),//停息
			            @XmlElement(name="StopStopControlRq", type= StopStopControlRq.class),//终止停息控制信息
			            @XmlElement(name="StopStopItrRq", type= StopStopItrRq.class),//终止停息
			            @XmlElement(name="ExtendTimeAppRq", type= ExtendTimeAppRq.class),//展期申请控制信息
			            @XmlElement(name="CredPeriodChangeRq", type= CredPeriodChangeRq.class),//贷款期限变更
			            @XmlElement(name="ChangeInterControlRq", type= ChangeInterControlRq.class),//调整利息控制信息
			            @XmlElement(name="RepayWayChangeRq", type= RepayWayChangeRq.class),//还款方式变更
			            @XmlElement(name="PayVerifControlRq", type= PayVerifControlRq.class),//核销控制信息
			            @XmlElement(name="VerificationRq", type= VerificationRq.class),//核销
			            @XmlElement(name="PayVerifBackControlRq", type= PayVerifBackControlRq.class),//核销收回控制信息
			            @XmlElement(name="VerificationBackRq", type= VerificationBackRq.class),//核销收回
			            @XmlElement(name="FirstAjustPeriodRq", type= FirstAjustPeriodRq.class),//调整阶段性首次还本期数
			            @XmlElement(name="ChangeIntrRq", type= ChangeIntrRq.class),//调整利息
			            @XmlElement(name="LoanCancelControlRq", type= LoanCancelControlRq.class),//撤销控制信息
			            @XmlElement(name="LoanCancelRq", type= LoanCancelRq.class),//撤销
			            @XmlElement(name="QueryCredPayPlanRq", type= QueryCredPayPlanRq.class),//查询贷款还款计划表
			            @XmlElement(name="CredMenu", type= CredMenu.class),//放款清单
			            @XmlElement(name="RepayMenu", type= RepayMenu.class),//还款清单
			            @XmlElement(name="InterSettMenu", type= InterSettMenu.class),//结息清单
			            @XmlElement(name="SettleMenu", type= SettleMenu.class),//结清清单
			            @XmlElement(name="ImpproviMenu", type= ImpproviMenu.class),//计提减值清单
			            @XmlElement(name="PayInterMenu", type= PayInterMenu.class),//贴息清单
			            @XmlElement(name="VouComMenu", type= VouComMenu.class),//凭证补丁
			            @XmlElement(name="RepayCancel", type= RepayCancel.class),//放款撤销
			            @XmlElement(name="RepayControlCancel", type= RepayControlCancel.class),//还款控制信息撤销
			            @XmlElement(name="BusiOrgSplitRq", type= BusiOrgSplitRq.class),//业务移交
			            @XmlElement(name="DelayTime", type= DelayTime.class),//逾期展期
			            
			            
			            
			            
			            
			            
	})
	public SuperBosfxRq bosfxRq;
	
	/*
	 * 声明T24相应报文体，每一个交易都用@XmlElement声明，中间用“,”分割，
	 * 属性name为交易名称，type为交易对应的javabean
	 * 注：交易名称和javabean不能重复，否则无法识别需要解析为什么形式
	 * */
	@XmlElements(value = {
		@XmlElement(name = "PayOutRs", type = PayOutVo.class),//法人按揭贷款发放    
	})
	public SuperBosfxRs bosfxRs;
	

	@Override
	public String toString() {
		return "BOSFXII [bosfxRq=" + bosfxRq + ",bosfxRs="+bosfxRs+"]";
	}
}