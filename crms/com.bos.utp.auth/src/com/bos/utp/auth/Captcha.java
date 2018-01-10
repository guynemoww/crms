package com.bos.utp.auth;

import java.util.Random;

//import com.bos.git.crm.app.realtime.message.meta.CaptchaMessageVo;
//import com.bos.git.crm.app.realtime.socket.RealTimeSocketMessageSend;
import com.eos.system.annotation.Bizlet;

/**
 * 生成登录手机验证码 并发送短信
 * @author shaoyan
 * @since 2013-7-15
 */
public class Captcha {
	
	//校验码字符集
	private static char[] code={'0','1','2','3','4','5','6','7','8','9'};
	
	/**
	 *  生成登录手机验证码 并发送短信
	 * @param phoneNumber 手机号码
	 * @return 所生成的手机校验码
	 */
	@Bizlet
	public String sendCaptcha(String phoneNumber){
		if(!checkPhoneNumber(phoneNumber)){
			return "wrongPhone";
		}
		int temp=0;
		Random ran=new Random();
		StringBuilder returnCode=new StringBuilder();
		for(int i=0;i<6;i++){
			temp=ran.nextInt(10);
			returnCode.append(code[temp]);
		}
		String message="短信验证码："+returnCode.toString()+"（5分钟内有效）。您正在登录客户关系管理系统";
		/**
		 * 短信发送
		 * 01 为发送成功
		 */
		/**try {
			CaptchaMessageVo vo = new CaptchaMessageVo();
			vo.setTelphone(phoneNumber);
			vo.setCaptchaCode(message);
			RealTimeSocketMessageSend s = new RealTimeSocketMessageSend();
			String result = s.send(vo);
			if(result!=null&&!result.equals("01")){
				return "false";
			}
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			return "false";
		}	**/	
		return returnCode.toString();	
	}
	/**
	 * 手机号码验证
	 * @param phone 手机号码
	 * @return
	 */
	public static boolean checkPhoneNumber(String phone){
		if(phone!=null&&phone.matches("[0-9]{11}")){
			return true;
		}
		return false;	
	}
	
	public static void main(String[] args){
		String ph="13456789012";
		System.out.println(checkPhoneNumber(ph));
	}

}
