package com.gadgetmart.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import com.gadgetmart.config.DatabaseConfig;
import com.gadgetmart.model.CustomerOrderModel;
import com.gadgetmart.model.CustomerTransaction;
import com.gadgetmart.model.OrdersModel;
import com.gadgetmart.model.Product_OrderModel;
import com.gadgetmart.util.StringUtil;

public class OrderServices {
	private final DatabaseConfig dbconfig = new DatabaseConfig();
	
	//add order 
		public int addOrder(OrdersModel ordermodel) {
			try (Connection conn = dbconfig.getConnection()) {
				// Prepare the SQL statement
				String query = "INSERT INTO orders (Order_Id, User_Id, Total_Amount, Status, City, Address, Payment) VALUES (?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement statement = conn.prepareStatement(query);

				// Set parameters for the statement
				statement.setString(1, ordermodel.getOrderId());
				statement.setString(2, ordermodel.getUserId());
				statement.setString(3, ordermodel.getTotalAmount());
				statement.setString(4, ordermodel.getStatus());
				statement.setString(5, ordermodel.getCity());
				statement.setString(6, ordermodel.getOrder_address());
				statement.setString(7, ordermodel.getPayment());

				// Execute the statement
				return statement.executeUpdate(); // Returns the number of rows affected
			} catch (SQLException | ClassNotFoundException ex) {
				ex.printStackTrace(); // Log the exception for debugging
				return -1; // Return -1 to indicate failure
			}
		}
		
		

		//add order line item
		public int addOrderLineItem(Product_OrderModel ordersprod) {
			try (Connection conn = dbconfig.getConnection()) {
				// Prepare the SQL statement
				String query = "INSERT INTO order_producrs (Order_Id, Product_Id, Line_Quantity) VALUES (?, ?, ?)";
				PreparedStatement statement = conn.prepareStatement(query);

				// Set parameters for the statement
				statement.setString(1, ordersprod.getOrderID());
				statement.setString(2, ordersprod.getProductID());
				statement.setString(3, ordersprod.getLineQuantity());

				// Execute the statement
				return statement.executeUpdate(); // Returns the number of rows affected
			} catch (SQLIntegrityConstraintViolationException ex) {
				System.out.println("Duplicate entry detected for Order_ID: " + ordersprod.getOrderID() + ", Product_ID: "
						+ ordersprod.getProductID());
				ex.printStackTrace(); // Log the exception for debugging
				return -1; // Return -1 to indicate failure
			} catch (SQLException | ClassNotFoundException ex) {
				ex.printStackTrace(); // Log the exception for debugging
				return -1; // Return -1 to indicate failure
			}
		}
		
		//get the order list
		public List<CustomerTransaction> getAllOrdersDetails() {
			List<CustomerTransaction> ordersCum = new ArrayList<>();
			try {
				PreparedStatement st = dbconfig.getConnection().prepareStatement(StringUtil.LIST_ORDERS);
				ResultSet result = st.executeQuery();

				while (result.next()) {

					CustomerTransaction orderdetails = new CustomerTransaction();

					orderdetails.setId(result.getString("Id"));
					orderdetails.setCustomerName(result.getString("Customer_Name"));
					orderdetails.setProductId(result.getString("Product_Id"));
					orderdetails.setProductName(result.getString("Product_Name"));
					orderdetails.setAmount(result.getString("Amount"));
					orderdetails.setStatus(result.getString("Status"));
					orderdetails.setImageLink(result.getString("Image_Link"));
					orderdetails.setOrderId(result.getString("Order_Id"));

					ordersCum.add(orderdetails);
				}
			} catch (SQLException | ClassNotFoundException ex) {
				ex.printStackTrace();
			}
			return ordersCum;
		}
		
		//update order
		public int updateOrder(String orderId, OrdersModel orderm) {

			try (Connection conn = dbconfig.getConnection()) {
				System.out.println("order id: " +  orderId);
				System.out.println("Status:"+orderm.getStatus());

				PreparedStatement statement = conn.prepareStatement("UPDATE orders  SET Status = ? WHERE Order_Id = ?");
				
				statement.setString(1, orderm.getStatus());
				statement.setString(2, orderId);
				
				

				int rowsUpdated = statement.executeUpdate();

				return rowsUpdated > 0 ? 1 : 0;
			} catch (SQLException | ClassNotFoundException e) {

				e.printStackTrace();
				return 0;

			}

		}
		
		//get customer order
		public ArrayList<CustomerOrderModel> getCustomerOrder(String userId) {
			try {
				PreparedStatement st = dbconfig.getConnection().prepareStatement("SELECT o.Order_Id,p.Product_Name,p.Image,p.Price,p.Discount_Amount,o.Status FROM orders o JOIN order_producrs oi ON o.Order_Id = oi.Order_Id JOIN products p ON p.Product_Id = oi.Product_Id WHERE o.User_Id =?");
				st.setString(1, userId);

				ResultSet result = st.executeQuery();

				ArrayList<CustomerOrderModel> cusOrder = new ArrayList<>();

				while (result.next()) {
					CustomerOrderModel order = new CustomerOrderModel();

					order.setOrderId(result.getString("Order_Id"));
					order.setProductName(result.getString("Product_Name"));
					order.setProductImg(result.getString("Image"));
					order.setProductPrice(result.getString("Price"));
					order.setProductDiscount(result.getString("Discount_Amount"));
					order.setStatus(result.getString("Status"));

					cusOrder.add(order);
				}

				return cusOrder;

			} catch (SQLException | ClassNotFoundException ex) {
				ex.printStackTrace();
				return null;
			}
		}
	
}
