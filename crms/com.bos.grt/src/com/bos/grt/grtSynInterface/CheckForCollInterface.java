/**
 * 
 */
package com.bos.grt.grtSynInterface;

import org.osoa.sca.annotations.Remotable;

/**
 * @author lenovo
 * 针对押品系统需要的零星校验，开发此接口，根据 目标值判断 属于哪种校验。校验结果
 *
 */
@Remotable
public interface CheckForCollInterface {

	public String checkForColl(String inputInfoJsonStr);
}
