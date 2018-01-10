/**   
* @Title: EncryptUtil.java 
* @Package cn.com.git.easy_framework.common.util.encrypt 
* @Description: TODO(用一句话描述该文件做什么) 
* @author GIT-Sunny
* @date 2013-1-14 下午11:25:54 
* @version V1.0   
*/
package com.bos.utp.tools;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.Key;
import java.security.MessageDigest;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

import com.bos.utp.tools.encrypt.BASE64Decoder;
import com.bos.utp.tools.encrypt.BASE64Encoder;
import com.eos.system.annotation.Bizlet;

/** 
 * @ClassName: EncryptUtil 
 * @Description: 加密码解密工具 
 * @author GIT-Sunny
 * @date 2013-1-14 下午11:25:54 
 *  
 */
public class EncryptUtil {
	public static final String KEY_SHA = "SHA";  
    public static final String KEY_MD5 = "MD5";  
  
    /** 
     * MAC算法可选以下多种算法 
     *  
     * <pre> 
     * HmacMD5  
     * HmacSHA1  
     * HmacSHA256  
     * HmacSHA384  
     * HmacSHA512 
     * </pre> 
     */  
    public static final String KEY_MAC = "HmacMD5";  
  
    /** 
     * BASE64解密 
     *  
     * @param key 
     * @return 
     * @throws Exception 
     */  
    public static byte[] decryptBASE64(String key) throws Exception {  
        return (new BASE64Decoder()).decodeBuffer(key);  
    }  
  
    /** 
     * BASE64加密 
     *  
     * @param key 
     * @return 
     * @throws Exception 
     */  
    public static String encryptBASE64(byte[] key) throws Exception {  
        return (new BASE64Encoder()).encodeBuffer(key);  
    }  
  
    
    /** 
     * MD5加密 
     *  
     * @param data 
     * @return 
     * @throws Exception 
     */  
    public static byte[] encryptMD5(byte[] data) throws Exception {  
  
        MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);  
        md5.update(data);  
  
        return md5.digest();  
  
    }  
  
    /** 
     * SHA加密 
     *  
     * @param data 
     * @return 
     * @throws Exception 
     */  
    public static byte[] encryptSHA(byte[] data) throws Exception {  
  
        MessageDigest sha = MessageDigest.getInstance(KEY_SHA);  
        sha.update(data);  
  
        return sha.digest();  
  
    }  
  
    /** 
     * 初始化HMAC密钥 
     *  
     * @return 
     * @throws Exception 
     */  
    public static String initMacKey() throws Exception {  
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_MAC);  
  
        SecretKey secretKey = keyGenerator.generateKey();  
        return encryptBASE64(secretKey.getEncoded());  
    }  
  
    /** 
     * HMAC加密 
     *  
     * @param data 
     * @param key 
     * @return 
     * @throws Exception 
     */  
    public static byte[] encryptHMAC(byte[] data, String key) throws Exception {  
  
        SecretKey secretKey = new SecretKeySpec(decryptBASE64(key), KEY_MAC);  
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());  
        mac.init(secretKey);  
  
        return mac.doFinal(data);  
  
    }  
    
    
    /**
     * DES 算法 <br>
     * 可替换为以下任意一种算法，同时key值的size相应改变。
     * 
     * <pre>
     * DES                  key size must be equal to 56 
     * DESede(TripleDES)    key size must be equal to 112 or 168 
     * AES                  key size must be equal to 128, 192 or 256,but 192 and 256 bits may not be available 
     * Blowfish             key size must be multiple of 8, and can only range from 32 to 448 (inclusive) 
     * RC2                  key size must be between 40 and 1024 bits 
     * RC4(ARCFOUR)         key size must be between 40 and 1024 bits 
     * </pre>
*/
    public static final String ALGORITHM = "DES";

    /**
     * DES 算法转换密钥<br>
     * 
     * @param key
     * @return
     * @throws Exception
*/
    private static Key toKey(byte[] key) throws Exception {
        SecretKey secretKey = null;
        if (ALGORITHM.equals("DES") || ALGORITHM.equals("DESede")) {
            DESKeySpec dks = new DESKeySpec(key);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
            secretKey = keyFactory.generateSecret(dks);
        } else {
            // 当使用其他对称加密算法时，如AES、Blowfish等算法时，用下述代码替换上述三行代码
            secretKey = new SecretKeySpec(key, ALGORITHM);
        }
        return secretKey;
    }

    /**
     * DES 算法解密
     * 
     * @param data
     * @param key
     * @return
     * @throws Exception
*/
    @Bizlet("DES 算法解密") 
    public static byte[] decrypt(byte[] data, String key) throws Exception {
        Key k = toKey(decryptBASE64(key));
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, k);
        return cipher.doFinal(data);
    }

    /**
     * DES 算法加密
     * 
     * @param data
     * @param key
     * @return
     * @throws Exception
*/
    @Bizlet("DES 算法加密")
    public static byte[] encrypt(byte[] data, String key) throws Exception {
        Key k = toKey(decryptBASE64(key));
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, k);
        return cipher.doFinal(data);
    }

    /**
     * DES 算法生成密钥
     * 
     * @return
     * @throws Exception
     */
    @Bizlet("DES 算法生成密钥")
    public static String initKey() throws Exception {
        return initKey(null);
    }

    /**
     * DES 算法生成密钥
     * 
     * @param seed
     * @return
     * @throws Exception
     */
    public static String initKey(String seed) throws Exception {
        SecureRandom secureRandom = null;
        if (seed != null) {
            secureRandom = new SecureRandom(decryptBASE64(seed));
        } else {
            secureRandom = new SecureRandom();
        }
        KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM);
        kg.init(secureRandom);
        SecretKey secretKey = kg.generateKey();
        return encryptBASE64(secretKey.getEncoded());
    }
    
    /**
     * 16进制数字字符集
    */
    private static String hexString = "0123456789ABCDEF";
    /**
     * 将字符串编码成16进制数字,适用于所有字符（包括中文）
    * @param str 字符串
    * @return 返回16进制字符串
    */
    public static String strToHexStr(String str) {
    	 // 根据默认编码获取字节数组
    	byte[] bytes = str.getBytes();
    	 StringBuilder sb = new StringBuilder(bytes.length * 2);
    	 // 将字节数组中每个字节拆解成2位16进制整数
    	for (int i = 0; i < bytes.length; i++) {
    	 sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
    	 sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));
    	 }
    	 return sb.toString();
    	}
    	/**
    	 * 将16进制数字解码成字符串,适用于所有字符（包括中文）
    	* @param hexStr 16进制字符串
    	* @return 返回字符串
    	*/
    	public static String hexStrToStr(String hexStr) {
    	 ByteArrayOutputStream baos = new ByteArrayOutputStream(
    	 hexStr.length() / 2);
    	 // 将每2位16进制整数组装成一个字节
    	for (int i = 0; i < hexStr.length(); i += 2)
    	 baos.write((hexString.indexOf(hexStr.charAt(i)) << 4 | hexString
    	 .indexOf(hexStr.charAt(i + 1))));
    	 return new String(baos.toByteArray());
    	}
    
    public static void main(String[] args) {
        try {
            String s = "creditFlag=1&userId=1167";
            String b = encryptBASE64(s.getBytes("UTF-8"));
            System.out.println("BASE64加密后:" + b);
            byte[] c = decryptBASE64(b);
            System.out.println("BASE64解密后:" + new String(c, "UTF-8"));

            c = encryptMD5(s.getBytes());
            System.out.println("MD5   加密后:" + new BigInteger(c).toString(16));

            c = encryptSHA(s.getBytes());
            System.out.println("SHA   加密后:" + new BigInteger(c).toString(16));

            String key = initMacKey();
            System.out.println("HMAC密匙:" + key);
            c = encryptHMAC(s.getBytes(), key);
            System.out.println("HMAC  加密后:" + new BigInteger(c).toString(16));

            key = initKey();
            System.out.println(ALGORITHM + "密钥:\t" + key);
            c = encrypt(s.getBytes("UTF-8"), key);
            //System.out.println(ALGORITHM + "   加密后:" + new BigInteger(c).toString(16));
            
            //ceshi
            String str = new String(c, "iso-8859-1");
            System.out.println("加密后字符串:"+str);
            str = strToHexStr(str);
            System.out.println("转换后16进制字符串:"+str);
            
            str = hexStrToStr(str);
            System.out.println("加密后字符串:"+str);
            c = str.getBytes("iso-8859-1");
            
            c = decrypt(c, key);
            System.out.println(ALGORITHM + "   解密后:" + new String(c, "UTF-8"));
            
            		
            String ceshiString = "creditFlag=1&userId=1167";
            ceshiString = strToHexStr(ceshiString);
            System.out.println("ceshiString:"+ceshiString);
            ceshiString = hexStrToStr(ceshiString);
            System.out.println("ceshiString:"+ceshiString);
            		
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    //从十六进制字符串到字节数组转换  
 	public static byte[] HexString2Bytes(String hexstr) {  
 	    byte[] b = new byte[hexstr.length() / 2];  
 	    int j = 0;  
 	    for (int i = 0; i < b.length; i++) {  
 	        char c0 = hexstr.charAt(j++);  
 	        char c1 = hexstr.charAt(j++);  
 	        b[i] = (byte) ((parse(c0) << 4) | parse(c1));  
 	    }  
 	    return b;  
 	}

    private static int parse(char c) {  
        if (c >= 'a')  
            return (c - 'a' + 10) & 0x0f;  
        if (c >= 'A')  
            return (c - 'A' + 10) & 0x0f;  
        return (c - '0') & 0x0f;  
    }  
    
 	public static byte[] hexStrToByteArray(String str)
 	{
 	    if (str == null) {
 	        return null;
 	    }
 	    if (str.length() == 0) {
 	        return new byte[0];
 	    }
 	    byte[] byteArray = new byte[str.length() / 2];
 	    for (int i = 0; i < byteArray.length; i++){
 	        String subStr = str.substring(2 * i, 2 * i + 2);
 	        byteArray[i] = ((byte)Integer.parseInt(subStr, 16));
 	    }
 	    return byteArray;
 	}

}
