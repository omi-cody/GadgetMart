package com.gadgetmart.model;

public class CustomerInquiryModel {
	
	
	private String id;
	private String inquiryId;
	private String customerName;
	private String customerEmail;
	private String customerAddress;
	private String customerPhoneNumber;
	private String customerImg;
	private String customerSubject;
	private String customerMessage;
	private String createdDate;
	
	public CustomerInquiryModel(String id, String inquiryId, String customerName, String customerEmail,
			String customerAddress, String customerPhoneNumber, String customerImg, String customerSubject,
			String customerMessage, String createdDate) {
		super();
		this.id = id;
		this.inquiryId = inquiryId;
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.customerAddress = customerAddress;
		this.customerPhoneNumber = customerPhoneNumber;
		this.customerImg = customerImg;
		this.customerSubject = customerSubject;
		this.customerMessage = customerMessage;
		this.createdDate = createdDate;
	}
	
	

	public CustomerInquiryModel() {

	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInquiryId() {
		return inquiryId;
	}

	public void setInquiryId(String inquiryId) {
		this.inquiryId = inquiryId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}

	public void setCustomerPhoneNumber(String customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}

	public String getCustomerImg() {
		return customerImg;
	}

	public void setCustomerImg(String customerImg) {
		this.customerImg = customerImg;
	}

	public String getCustomerSubject() {
		return customerSubject;
	}

	public void setCustomerSubject(String customerSubject) {
		this.customerSubject = customerSubject;
	}

	public String getCustomerMessage() {
		return customerMessage;
	}

	public void setCustomerMessage(String customerMessage) {
		this.customerMessage = customerMessage;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	
	
	
	

	
	
	

}
