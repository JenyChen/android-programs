package com.pot.gathering.Bean;

public class ContactBean {

	private boolean isTitle;
	
	private String name;// 姓名
	private int type;// 0表示闺蜜、死党，1表示其他
	
	// 其他附属信息
	private String phoneNum;// 手机号
	private String nickName;// 昵称
	private String imgUrl;
	private int sex;// 性别: 0表示男，1表示女
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public boolean isTitle() {
		return isTitle;
	}
	public void setTitle(boolean isTitle) {
		this.isTitle = isTitle;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
}
