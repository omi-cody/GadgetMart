package com.gadgetmart.model;

public class CustomerOrderModel {
	private String orderId;
	private String productName;
	private String productImg;
	private String productPrice;
	private String productDiscount;
	private String status;
	
	
	public CustomerOrderModel(String orderId, String productName, String productImg, String productPrice,
			String productDiscount, String status) {
		super();
		this.orderId = orderId;
		this.productName = productName;
		this.productImg = productImg;
		this.productPrice = productPrice;
		this.productDiscount = productDiscount;
		this.status = status;
	}


	public CustomerOrderModel() {
	}


	public String getOrderId() {
		return orderId;
	}


	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getProductImg() {
		return productImg;
	}


	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}


	public String getProductPrice() {
		return productPrice;
	}


	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}


	public String getProductDiscount() {
		return productDiscount;
	}


	public void setProductDiscount(String productDiscount) {
		this.productDiscount = productDiscount;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	
	
	
}
