package com.gadgetmart.config;

import java.sql.Connection;
import com.gadgetmart.model.UserModel;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gadgetmart.util.StringUtil;

import com.gadgetmart.model.PasswordEncryptionWithAes;

public class DatabaseConfig {

	
	
	public Connection getConnection() throws SQLException, ClassNotFoundException {
		//Load the JDBC driver class
		System.out.println("here operate");
		Class.forName(StringUtil.DB_DRIVER);
		//Create a connection to the database 
		
		return DriverManager.getConnection(StringUtil.DB_URL,StringUtil.DB_USERNAME,StringUtil.DB_PASSWORD); // return connection object
		
	}
	
	// for adding user to database
		public int addUser(UserModel usermodel) {

			try (Connection conn = getConnection()) {
	

				PreparedStatement st = conn.prepareStatement(StringUtil.INSERT_USER);
				

				// insert the user detail in the database in same order as from database.
				st.setString(1, usermodel.getUserId());
				st.setString(2, usermodel.getFullName());
				st.setString(3, usermodel.getEmail());
	
				st.setString(4, PasswordEncryptionWithAes.encrypt(usermodel.getUserId(), usermodel.getPassword()));
	
				st.setString(5, usermodel.getPhoneNumber());
				st.setString(6, usermodel.getGender());
				st.setString(7, usermodel.getRole());
		

				int result = st.executeUpdate();
				return result > 0 ? 1 : 0;

			} catch (SQLException | ClassNotFoundException ex) {
				ex.printStackTrace();
				return -1;
			}

		}
		public int getUserLoginInfo(String userId, String password) {
			try (Connection conn = getConnection();

					PreparedStatement st = conn.prepareStatement(StringUtil.GET_USER_INFO)) {

				// Set parameters for the prepared statement
				st.setString(1, userId);

				// Execute the query
				try (ResultSet rs = st.executeQuery()) {
					if (rs.next()) {
						String userDb = rs.getString(StringUtil.USER);
						String roleDb = rs.getString(StringUtil.USER_ROLE);
						String passwordDb = rs.getString(StringUtil.USER_CREDENTIALS);
						String decryptedPwd = PasswordEncryptionWithAes.decrypt(passwordDb, userDb);

						// Check if the provided credentials match the database records
						if (decryptedPwd != null && decryptedPwd.equals(password) && userDb.equals(userId)) {

							if (roleDb.equalsIgnoreCase(StringUtil.Role1) || roleDb.equalsIgnoreCase(StringUtil.Role2)) {

								return roleDb.equalsIgnoreCase(StringUtil.Role1) ? 1 : 2; // 1 for admin, 2 for regular
																							// user
							} else {

								return 3; // Role does not match

							}

						} else {

							return 4; // Username and/or Passwords do not match
						}

					} else {

						return 0; // No matching record found for the provided userId
					}
				}
			} catch (SQLException | ClassNotFoundException ex) {
				ex.printStackTrace(); // Log the exception for debugging
				return -1; // Return -1 to indicate an error occurred
			}
		}

}
