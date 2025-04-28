<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/Css/add_product.css">
</head>
<body>
 <section class="adp_container_section">
    
        <div class="adp_container">
          
          <div class="adp_company_logo">
              <img src="${pageContext.request.contextPath}/resources/images/system/GadgetMartLogo.png" alt="Company Logo">
          </div>
          <div class="adp-form-container">
              <form action="#" method="post" enctype="multipart/form-data">
                  <div class="adp-form-grid">
                  
                      <div>
                          <label for="pID" class="adp-label">Product ID:</label>
                          <input type="text" id="pID" class="adp-input" placeholder="Enter Product ID"  name="pID" required>
                      </div>
                      
                      <div>
                          <label for="pname" class="adp-label">Product Name:</label>
                          <input type="text" id="pname" class="adp-input" placeholder="Enter Product Name"  name="pname" required>
                      </div>
                      <div>
                          <label for="pprice" class="adp-label">Price:</label>
                          <input type="text" id="pprice" class="adp-input" placeholder="Enter Price"  name="pprice" required>
                      </div>
                      <div>
                          <label for="psize" class="adp-label">Screen Size:</label>
                          <input type="text" id="psize" class="adp-input" placeholder="Enter Screen Size" name="psize" required>
                      </div>
                      <div>
                          <label for="pram" class="adp-label">Ram:</label>
                          <input type="text" id="pram" class="adp-input" placeholder="Enter RAM" name="pram" required>
                      </div>
                      <div>
                          <label for="pproc" class="adp-label">Processor:</label>
                          <input type="text" id="pproc" class="adp-input" placeholder="Enter Processor"  name="pproc" required>
                      </div>
      
                      <div>
                          <label for="pimage" class="adp-label" >Product Image:</label>
                          <input type="file" id="pimage" name="pimage" accept="image/*" class="arb-conmir-upload"  name="pimage" required>
                      </div>
      
                      <div>
                          <label for="pbat" class="adp-label">Battery:</label>
                          <input type="text" id="pbat" class="adp-input" placeholder="Enter Battery"  name="pbat" required>
                      </div>
      
                      <div>
                          <label for="pstor" class="adp-label">Storage:</label>
                          <input type="text" id="pstor" class="adp-input" placeholder="Enter Phone Storage" name ="pstor" required>
                      </div>
      
                      <div>
                          <label for="pwar" class="adp-label">Warrenty:</label>
                          <input type="text" id="pwar" class="adp-input" placeholder="Is Warrenty Available?" name="pwar" required>
                      </div>
      
                      <div>
                          <label for="pcam" class="adp-label">Camera:</label>
                          <input type="text" id="pcam" class="adp-input" placeholder="Enter Camera Details" name="pcam" required>
                      </div>
      
                      <div>
                          <label for="pdis" class="adp-label">Discount %</label>
                          <input type="text" id="pdis" class="adp-input" placeholder="Enter Discount" name="pdis" required>
                      </div>
      
                      <div>
                          <label for="pfeat" class="adp-label">Features:</label>
                          <textarea id="pfeat" class="adp-textarea" rows="10" placeholder="Enter Phone Features" style="resize:none;" name="pfeat" required></textarea>
                      </div>
                      
                  </div>
                  <button type="submit" class="adp-confirm-button">ADD</button>
              </form>
          </div>
          <a href="#"><button class="adp_back_btn">Back</button></a>
        </div>
      </section>
</body>
</html>