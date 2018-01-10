package com.bos.mq.server;

/**
 * 服务端错误信息定义，错误码为6位字符，前两位代表系统编号，后四位为错误码
 * 系统编号如下：00-公用消息,01-T24,02-SCF,03-CCMS,04-ECIF,05-BMS
 * @author 武立松
 */
public enum CrmsMsg {
	/**************公用***************/
    //交易成功
    _SUCCESS("000000"),
    _SUCCESS_MSG("交易成功"),
    //交易失败
    _EXCEPTION("009999"),
    _EXCEPTION_MSG("CRMS返回未知异常"),
	//**************T24***************//
    _T24_ISNULL("0100010"),
    _T24_ISNULL_MSG("NOT FOUND DATA"),
	//**************SCF***************//
    _SCF_ISNULL("020010"),
    _SCF_ISNULL_MSG("NOT FOUND DATA"),
	//**************CCMS***************//
	//**************ECIF***************//
    //**************BMS***************//
    _BMS_ISNULL("050010"),
    _BMS_ISNULL_MSG("NOT FOUND DATA"),
  
    _OTHER("999999"),
    _OTHER_MSG("OTHER ERROR");
    
    public static CrmsMsg valueAs(String value) {
        for (CrmsMsg eNum : CrmsMsg.values()) {
            if (eNum.value.equalsIgnoreCase(value)) {
                return eNum;
            }
        }
        return null;
    }
    
    public String toString() {
        return this.value;
    }

    private String value;

    CrmsMsg(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
