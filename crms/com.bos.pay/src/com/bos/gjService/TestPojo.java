package com.bos.gjService;

public class TestPojo {
	private String num;
	private TestVo testVo;
	
	
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public TestVo getTestVo() {
		return testVo;
	}
	public void setTestVo(TestVo testVo) {
		this.testVo = testVo;
	}
	@Override
	public String toString() {
		return "TestPojo [num=" + num + ", testVo=" + testVo + "]";
	}

	
}

