package com.gadgetmart.config;

import java.sql.Connection;
import com.gadgetmart.model.UserModel;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import com.gadgetmart.util.StringUtil;
import com.gadgetmart.model.CartModel;
import com.gadgetmart.model.CustomerInquiryModel;
import com.gadgetmart.model.CustomerOrderModel;
import com.gadgetmart.model.CustomerTransaction;
import com.gadgetmart.model.InquiryModel;
import com.gadgetmart.model.MemberModel;
import com.gadgetmart.model.OrdersModel;
import com.gadgetmart.model.PasswordEncryptionWithAes;
import com.gadgetmart.model.ProductModel;
import com.gadgetmart.model.Product_OrderModel;

public class DatabaseConfig {

	public Connection getConnection() throws SQLException, ClassNotFoundException {
		// Load the JDBC driver class
		Class.forName(StringUtil.DB_DRIVER);
		// Create a connection to the database

		return DriverManager.getConnection(StringUtil.DB_URL, StringUtil.DB_USERNAME, StringUtil.DB_PASSWORD); // return
																												// connection
																												// object

	}

	
}
