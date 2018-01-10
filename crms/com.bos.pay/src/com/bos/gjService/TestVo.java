package com.bos.gjService;

public class TestVo {
	private String id;
	private Integer age;
	private String add;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getAdd() {
		return add;
	}
	public void setAdd(String add) {
		this.add = add;
	}
	@Override
	public String toString() {
		return "TestVo [id=" + id + ", age=" + age + ", add=" + add + "]";
	}
	
}
