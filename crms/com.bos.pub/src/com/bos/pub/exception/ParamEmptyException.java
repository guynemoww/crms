package com.bos.pub.exception;

/**
 * @author 王世春 参数为空异常
 * 
 */
public class ParamEmptyException extends Exception {

	private static final long serialVersionUID = -4071259950143747687L;

	public ParamEmptyException(String msg) {
		super(msg);
	}

	public ParamEmptyException() {
		super();
	}

	public ParamEmptyException(Throwable e) {
		super(e);
	}

	public ParamEmptyException(String msg, Throwable e) {
		super(msg, e);
	}
}
