package com.bos.utp.dict.util;

public class DictResponse {
	/**
	 * 状态返回对象
	 * @author wangbing (mailto:wangbing@primeton.com)
	 */
		//标识符，true:成功，false:失败
		private boolean flag;
		
		//提示信息
		private String message;

		public DictResponse() {
			super();
		}
		
		public DictResponse(String message) {
			super();
			this.message = message;
		}
		
		public DictResponse(boolean flag, String message) {
			super();
			this.flag = flag;
			this.message = message;
		}

		public boolean isFlag() {
			return flag;
		}

		public void setFlag(boolean flag) {
			this.flag = flag;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
		
		

}
