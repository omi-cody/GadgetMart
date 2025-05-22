package com.gadgetmart.model;

public class MemberModel {
 private String memberFName;
 private String memberLName;
 private String memberType;
 private String memberInfo;
 private String memberIntro;
 private String memberImage;
 
 


public MemberModel(String memberFName, String memberLName, String memberType, String memberInfo, String memberIntro,
		String memberImage) {
	super();
	this.memberFName = memberFName;
	this.memberLName = memberLName;
	this.memberType = memberType;
	this.memberInfo = memberInfo;
	this.memberIntro = memberIntro;
	this.memberImage = memberImage;
}


public MemberModel() {
}



public String getMemberFName() {
	return memberFName;
}


public void setMemberFName(String memberFName) {
	this.memberFName = memberFName;
}


public String getMemberLName() {
	return memberLName;
}


public void setMemberLName(String memberLName) {
	this.memberLName = memberLName;
}


public String getMemberType() {
	return memberType;
}


public void setMemberType(String memberType) {
	this.memberType = memberType;
}


public String getMemberInfo() {
	return memberInfo;
}


public void setMemberInfo(String memberInfo) {
	this.memberInfo = memberInfo;
}


public String getMemberIntro() {
	return memberIntro;
}


public void setMemberIntro(String memberIntro) {
	this.memberIntro = memberIntro;
}


public String getMemberImage() {
	return memberImage;
}


public void setMemberImage(String memberImage) {
	this.memberImage = memberImage;
}
 

 
 
 
}
