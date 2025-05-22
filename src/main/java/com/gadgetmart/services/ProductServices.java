package com.gadgetmart.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.gadgetmart.config.DatabaseConfig;
import com.gadgetmart.model.ProductModel;
import com.gadgetmart.util.StringUtil;

public class ProductServices {
	
	private final DatabaseConfig dbconfig = new DatabaseConfig();
	// to add product in database
		public int addProducts(ProductModel productModel) {

			try (Connection conn = dbconfig.getConnection()) {

				PreparedStatement st = conn.prepareStatement(StringUtil.ADD_PRODUCTS);

				st.setString(1, productModel.getProductID());
				st.setString(2, productModel.getProductName());
				st.setString(3, productModel.getScreenSize());
				st.setString(4, productModel.getProcessor());
				st.setString(5, productModel.getRam());
				st.setString(6, productModel.getFeatures());
				st.setBigDecimal(7, productModel.getDiscount());
				st.setString(8, productModel.getStorage());
				st.setString(9, productModel.getCamera());
				st.setString(10, productModel.getWarranty());
				st.setBigDecimal(11, productModel.getPrice());
				st.setBigDecimal(12, productModel.getDiscountAmount());
				st.setString(13, productModel.getImageUrl());
				
				

				int result = st.executeUpdate();

				return result > 0 ? 1 : 0;

			} catch (SQLException | ClassNotFoundException ex) {

				ex.printStackTrace();

				return -1;

			}

		}
		
		//store the all the product information in ArrayList.
		public ArrayList<ProductModel> getAllProductInfo() {
		      try {
		         PreparedStatement st = this.dbconfig.getConnection().prepareStatement("SELECT * from products");
		         ResultSet result = st.executeQuery();
		         ArrayList<ProductModel> products_ = new ArrayList();

		         while(result.next()) {
		            ProductModel product = new ProductModel();
		            product.setProductID(result.getString("Product_Id"));
		            product.setProductName(result.getString("Product_Name"));
		            product.setScreenSize(result.getString("Screen_Size"));
		            product.setProcessor(result.getString("Processor"));
		            product.setRam(result.getString("Ram"));
		            product.setFeatures(result.getString("Features"));
		            product.setDiscount(result.getBigDecimal("Discount"));
		            product.setStorage(result.getString("Storage"));
		            product.setCamera(result.getString("Camera"));
		            product.setWarranty(result.getString("Warranty"));
		            product.setPrice(result.getBigDecimal("Price"));
		            product.setImageUrl(result.getString("Image"));
		            product.setDiscountAmount(result.getBigDecimal("Discount_Amount"));
		            products_.add(product);
		            
		         }

		         return products_;
		      } catch (ClassNotFoundException | SQLException var5) {
		         var5.printStackTrace();
		         return null;
		      }
		   }
		
		//get product by search
		public ArrayList<ProductModel> getProductbySearch(String SearchItem) {
			try {
				PreparedStatement st = dbconfig.getConnection().prepareStatement(StringUtil.SEARCH_PRODUCTS);
				st.setString(1, SearchItem);

				ResultSet result = st.executeQuery();

				ArrayList<ProductModel> searched_products = new ArrayList<>();

				while (result.next()) {
					ProductModel product = new ProductModel();
					product.setProductID(result.getString("Product_ID"));
					product.setProductName(result.getString("Product_Name"));
					product.setScreenSize(result.getString("Screen_Size"));
					product.setProcessor(result.getString("Processor"));
					product.setRam(result.getString("RAM"));
					product.setFeatures(result.getString("Features"));
					product.setDiscount(result.getBigDecimal("Discount"));
					product.setStorage(result.getString("Storage"));
					product.setCamera(result.getString("Camera"));
					product.setWarranty(result.getString("Warranty"));
					product.setPrice(result.getBigDecimal("Price"));
					product.setImageUrl(result.getString("Image"));
					searched_products.add(product);

				}

				return searched_products;

			} catch (SQLException | ClassNotFoundException ex) {
				ex.printStackTrace();
				return null; // Return an empty list if an error occurs
			}
		}
		
		//category product
			public ArrayList<ProductModel> getCategoryProduct(String categoryItem) {
				try {
					PreparedStatement st = dbconfig.getConnection().prepareStatement(StringUtil.CATAGORY_PRODUCTS);
					st.setString(1, categoryItem);

					ResultSet result = st.executeQuery();

					ArrayList<ProductModel> category_products = new ArrayList<>();

					while (result.next()) {
						ProductModel product = new ProductModel();
						product.setProductID(result.getString("Product_ID"));
						product.setProductName(result.getString("Product_Name"));
						product.setScreenSize(result.getString("Screen_Size"));
						product.setProcessor(result.getString("Processor"));
						product.setRam(result.getString("RAM"));
						product.setFeatures(result.getString("Features"));
						product.setDiscount(result.getBigDecimal("Discount"));
						product.setStorage(result.getString("Storage"));
						product.setCamera(result.getString("Camera"));
						product.setWarranty(result.getString("Warranty"));
						product.setPrice(result.getBigDecimal("Price"));
						product.setImageUrl(result.getString("Image"));
						category_products.add(product);

					}

					return category_products;

				} catch (SQLException | ClassNotFoundException ex) {
					ex.printStackTrace();
					return null; // Return an empty list if an error occurs
				}
			}
			
			//get top trending product
			public ArrayList<ProductModel> getTopProduct(){
				try {
					PreparedStatement st = dbconfig.getConnection().prepareStatement(StringUtil.TOP_PRODUCTS); 
					ResultSet result = st.executeQuery();
					
					ArrayList<ProductModel> topProducts = new ArrayList<>();
					
					while(result.next()) {
						ProductModel product = new ProductModel();
						product.setProductID(result.getString("Product_ID"));
						product.setProductName(result.getString("Product_Name"));
						product.setScreenSize(result.getString("Screen_Size"));
						product.setProcessor(result.getString("Processor"));
						product.setRam(result.getString("RAM"));
						product.setFeatures(result.getString("Features"));
						product.setDiscount(result.getBigDecimal("Discount"));
						product.setStorage(result.getString("Storage"));
						product.setCamera(result.getString("Camera"));
						product.setWarranty(result.getString("Warranty"));
						product.setPrice(result.getBigDecimal("Price"));
						product.setImageUrl(result.getString("Image"));
						topProducts.add(product);
					}
					return topProducts;
				}catch(SQLException | ClassNotFoundException ex){
					ex.printStackTrace();
					return null; 
					
				}
				
				
				
			}
		
		
		
		//update product by id
		public int updateProductById(String ProductID, ProductModel updatedProduct) {

			try (Connection conn = dbconfig.getConnection()) {
				System.out.println("product id: " + ProductID);

				PreparedStatement statement = conn.prepareStatement(StringUtil.UPDATE_PRODUCT_ADMIN);

				statement.setString(1, updatedProduct.getProductName());
				statement.setString(2, updatedProduct.getScreenSize());
				statement.setString(3, updatedProduct.getProcessor());
				statement.setString(4, updatedProduct.getRam());
				statement.setString(5, updatedProduct.getFeatures());
				statement.setString(6, updatedProduct.getStorage());
				statement.setString(7, updatedProduct.getCamera());
				statement.setBigDecimal(8, updatedProduct.getPrice());
				statement.setString(9, updatedProduct.getWarranty());
				statement.setBigDecimal(10, updatedProduct.getDiscount());
				statement.setString(11, updatedProduct.getImageUrl());
				statement.setString(12, updatedProduct.getProductID());

				int rowsUpdated = statement.executeUpdate();

				return rowsUpdated > 0 ? 1 : 0;
			} catch (SQLException | ClassNotFoundException e) {

				e.printStackTrace();
				return 0;

			}

		}
		
		//get product by id
		public ArrayList<ProductModel> getProductbyID(String ProductID) {
			try {
				PreparedStatement st = dbconfig.getConnection().prepareStatement(StringUtil.ID_PRODUCTS);
				st.setString(1, ProductID);
				ResultSet result = st.executeQuery();

				ArrayList<ProductModel> selected_products = new ArrayList<>();

				while (result.next()) {
					ProductModel product = new ProductModel();
					product.setProductID(result.getString("Product_ID"));
					product.setProductName(result.getString("Product_Name"));
					product.setScreenSize(result.getString("Screen_Size"));
					product.setProcessor(result.getString("Processor"));
					product.setRam(result.getString("RAM"));
					product.setFeatures(result.getString("Features"));
					product.setDiscount(result.getBigDecimal("Discount"));
					product.setStorage(result.getString("Storage"));
					product.setCamera(result.getString("Camera"));
					product.setWarranty(result.getString("Warranty"));
					product.setPrice(result.getBigDecimal("Price"));
					product.setImageUrl(result.getString("Image"));
					product.setDiscountAmount(result.getBigDecimal("Discount_Amount"));
					selected_products.add(product);
				}

				return selected_products;

			} catch (SQLException | ClassNotFoundException ex) {
				ex.printStackTrace();
				return null; // Return an empty list if an error occurs
			}
		}
		
		//delete product by admin
		public int deleteProductInfo(String productId) {
			try (Connection con = dbconfig.getConnection()) {
				PreparedStatement st = con.prepareStatement(StringUtil.QUERY_DELETE_PRODUCT);
				st.setString(1, productId);
				return st.executeUpdate();
			} catch (SQLException | ClassNotFoundException ex) {
				ex.printStackTrace(); // Log the exception for debugging
				return -1;
			}
		}
}
