<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Prodcut Detail view</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Css/admin.css" />
</head>
<body>
<section class="api-bodyeel">
    <div class="api-container">
        <div class="api-product-detail">
            <div class="api-product-image">
    <img src="${selectedProducts[0].getImageUrl()}" alt="Product Image">
            </div>
            <div class="api-product-info">
                <h3><span class="api-hig-span-p">Product Name: </span>${selectedProducts[0].getProductName()}</h3>
                <hr style="margin-top: 15px; height: 2px; border: none; background-color: #000000; margin-bottom: 25px;">
                <p class="api-price"><span class="api-hig-span-ps">Price: </span>RS ${selectedProducts[0].getPrice()}</p>
                <ul class="api-specs">
                    <li><strong>Screen Size:</strong> ${selectedProducts[0].getScreenSize()} inches</li>
                    <li><strong>RAM:</strong> ${selectedProducts[0].getRam()} GB</li>
                    <li><strong>Processor:</strong> ${selectedProducts[0].getProcessor()}</li>
                    <li><strong>Storage:</strong> ${selectedProducts[0].getStorage()} </li>
                    <li><strong>Warranty:</strong> ${selectedProducts[0].getWarranty()} </li>
                    <li><strong>Camera:</strong> ${selectedProducts[0].getCamera()} </li>
                    <li><strong>Discount:</strong> ${selectedProducts[0].getDiscount()}%</li>
                </ul>
                
                <div class="api-features">
                    <hr style="margin-top: 15px; height: 2.5px; border: none; background-color: #000000; margin-bottom: 30px;">
                    <h3 class="api-hig-span-p">Features:</h3>
                    <p>${selectedProducts[0].getFeatures()}</p>
                </div>
            </div>
        </div>
        <a href="/GadgetMart/FetchProductForAdmin"><button class="api-arb-back-pm">Back</button></a>
    </div>
</section>
</body>
</html>