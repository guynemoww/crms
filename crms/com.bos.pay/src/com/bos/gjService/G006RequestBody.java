package com.bos.gjService;

import java.io.Serializable;
import java.util.List;

/**
 * 汇率推送---请求对象
 * @author shendl
 *
 */
public class G006RequestBody implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -939813181970842307L;
	//private int recNum;//记录数
	private List<G006RequestBodyStub>  g006RequestBodyStubList;
//	public int getRecNum() {
//		return recNum;
//	}
//	public void setRecNum(int recNum) {
//		this.recNum = recNum;
//	}
	public List<G006RequestBodyStub> getG006RequestBodyStubList() {
		return g006RequestBodyStubList;
	}
	public void setG006RequestBodyStubList(List<G006RequestBodyStub> g006RequestBodyStubList) {
		this.g006RequestBodyStubList = g006RequestBodyStubList;
	}
}
