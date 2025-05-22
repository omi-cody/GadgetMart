package com.gadgetmart.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gadgetmart.config.DatabaseConfig;
import com.gadgetmart.model.CartModel;
import com.gadgetmart.util.StringUtil;

public class CartServices {
	private final DatabaseConfig dbconfig = new DatabaseConfig();
	
	//add product to cart
		public int addProductsCart(CartModel cart) {

			try (Connection conn = dbconfig.getConnection()) {

				PreparedStatement st = conn.prepareStatement(StringUtil.ADD_CART);

				st.setString(1, cart.getCartId());

				st.setString(2, cart.getUserId());

				st.setString(3, cart.getProductId());

				st.setString(4, cart.getQuantity());

				int result = st.executeUpdate();

				return result > 0 ? 1 : 0;

			} catch (SQLException | ClassNotFoundException ex) {

				ex.printStackTrace();

				return -1;

			}

		}
		
		//check duplicate of product in same cart
		public boolean checkDuplicateCartProduct(String columnValue, String columnValue2, String dbStatement) {
			try (Connection conn = dbconfig.getConnection()) {

				PreparedStatement st = conn.prepareStatement(dbStatement);
				st.setString(1, columnValue);
				st.setString(2, columnValue2);
				ResultSet rs = st.executeQuery();
				return rs.next(); // If rs.next() returns true, a duplicate exists
			} catch (SQLException | ClassNotFoundException ex) {
				ex.printStackTrace(); // Log the exception for debugging
				return false;
			}
		}

		//cart item count
		public int getCartItemCount(String userId) {
			int count = 0;
			try (Connection con = dbconfig.getConnection()) {
				PreparedStatement st = con
						.prepareStatement("SELECT COUNT(*) AS cartItemCount FROM carts WHERE User_Id = ?");
				st.setString(1, userId); // Set the user ID parameter
				ResultSet rs = st.executeQuery();
				if (rs.next()) {
					count = rs.getInt("cartItemCount"); // Retrieve the count from the "cartItemCount" column
				}
			} catch (SQLException | ClassNotFoundException ex) {
				ex.printStackTrace(); // Log the exception for debugging
			}
			return count;
		}
		
		
		//get cart Product
		public ArrayList<CartModel> getCartProduct(String userId) {
			try {
				PreparedStatement st = dbconfig.getConnection().prepareStatement(StringUtil.GET_CART);
				st.setString(1, userId);

				ResultSet result = st.executeQuery();

				ArrayList<CartModel> cartProducts_ = new ArrayList<>();

				while (result.next()) {
					CartModel cart = new CartModel();

					cart.setCartId(result.getString("Cart_Id"));
					cart.setProductId(result.getString("Product_Id"));
					cart.setQuantity(result.getString("Quantity"));
					cart.setUserId(result.getString("User_Id"));

					cartProducts_.add(cart);
				}

				return cartProducts_;

			} catch (SQLException | ClassNotFoundException ex) {
				ex.printStackTrace();
				return null;
			}
		}
		//get cart detail
		public List<CartModel> getCartDetail(String cart_id) {
			List<CartModel> cartDetails = new ArrayList<>();
			try (Connection con = dbconfig.getConnection()) {
				PreparedStatement st = con.prepareStatement("SELECT * FROM carts WHERE Cart_Id = ?");
				st.setString(1, cart_id); // Set the cart ID parameter
				ResultSet rs = st.executeQuery();
				while (rs.next()) {
					String productId = rs.getString("Product_Id");
					String quantity = rs.getString("Quantity");

					// Create a new CartsModel object with retrieved data
					CartModel cartItem = new CartModel();
					cartItem.setProductId(productId);
					cartItem.setQuantity(quantity);

					// Add the cart item to the list
					cartDetails.add(cartItem);
				}
			} catch (SQLException | ClassNotFoundException ex) {
				ex.printStackTrace(); // Log the exception for debugging
			}
			return cartDetails;
		}
		
		//delete product from cart
		public int deletecart(String productID) {
			try (Connection con = dbconfig.getConnection()) {
				PreparedStatement st = con.prepareStatement("DELETE FROM carts WHERE Product_Id = ?");
				st.setString(1, productID);
				return st.executeUpdate();
			} catch (SQLException | ClassNotFoundException ex) {
				ex.printStackTrace(); // Log the exception for debugging
				return -1;
			}
		}
		
}
