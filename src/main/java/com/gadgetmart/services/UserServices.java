package com.gadgetmart.services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import  com.gadgetmart.config.DatabaseConfig;
import com.gadgetmart.model.PasswordEncryptionWithAes;
import com.gadgetmart.model.UserModel;
import com.gadgetmart.util.StringUtil;

public class UserServices {
	
	private final DatabaseConfig dbconfig = new DatabaseConfig();
	
	// for adding user to database
		public int addUser(UserModel usermodel) {

			try (Connection conn = dbconfig.getConnection()) {

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
		// public boolean checkRole(String userId)
		public boolean checkDuplicacy(String columnValue, String dbStatement) {
			try (Connection conn =dbconfig.getConnection()) {
				PreparedStatement st = conn.prepareStatement(dbStatement);
				st.setString(1, columnValue);
				ResultSet rs = st.executeQuery();
				return rs.next(); // If rs.next() returns true, a duplicate exists
			} catch (SQLException | ClassNotFoundException ex) {
				ex.printStackTrace(); // Log the exception for debugging
				return false;
			}
		}
		
		// for getting the user login info from the database
		public int getUserLoginInfo(String userId, String password) {
			try (Connection conn = dbconfig.getConnection();

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
		
		//for profile
		public ArrayList<UserModel> getProfileInfo(String userId) {
			try {
				PreparedStatement st = dbconfig.getConnection().prepareStatement(StringUtil.USER_QUERY);

				st.setString(1, userId);

				ResultSet result = st.executeQuery();

				ArrayList<UserModel> usersDetails = new ArrayList<>();

				while (result.next()) {
					UserModel user = new UserModel();

					user.setFullName(result.getString("Full_Name"));
					user.setEmail(result.getString("Email"));
					user.setPhoneNumber(result.getString("Phone_Number"));
					user.setFullAddress(result.getString("User_Address"));
					user.setUserId(result.getString("User_Id"));
					usersDetails.add(user);
				}
				for (UserModel user : usersDetails) {
					System.out.println("Full Name: " + user.getFullName());
					System.out.println("Email: " + user.getEmail());
					System.out.println("Phone Number: " + user.getPhoneNumber());
					System.out.println("Full Address: " + user.getFullAddress());
					System.out.println();
				}
				return usersDetails;

			} catch (SQLException | ClassNotFoundException ex) {
				ex.printStackTrace();
				return null;
			}
		}
		
		//get photo link
		public String getPhotoLink(String userId) {
			try {
				PreparedStatement st = dbconfig.getConnection().prepareStatement(StringUtil.IMG_RETRIVAL_QUERY);

				st.setString(1, userId);

				ResultSet result = st.executeQuery();

				String link = null;
				while (result.next()) {
					link = result.getString("Image_link");
				}

				return link;
			} catch (SQLException | ClassNotFoundException ex) {
				ex.printStackTrace();
				// Handle the exception appropriately, e.g., logging or throwing a custom
				// exception
				return null; // Return null or handle the error differently based on your requirement
			}
		}
		
		//update profile image
		public int addusersimg(UserModel userModel) {

			try (Connection conn = dbconfig.getConnection()) {

				System.out.println("test1:" + userModel.getImgLink());

				System.out.println("test2:" + userModel.getUserId());

				PreparedStatement st = conn.prepareStatement(StringUtil.UPDATE_USER_IMG);

				st.setString(1, userModel.getUserId());

				st.setString(2, userModel.getImgLink());

				int result = st.executeUpdate();

				return result > 0 ? 1 : 0;

			} catch (SQLException | ClassNotFoundException ex) {

				ex.printStackTrace();

				return -1;

			}

		}
		
		//user profile update
		public int userProfileUpdate(UserModel user) {

			try (Connection conn = dbconfig.getConnection()) {

				PreparedStatement st = conn.prepareStatement(StringUtil.UPDATE_USER);

				st.setString(1, user.getFullName());

				st.setString(2, user.getFullAddress());

				st.setString(3, user.getUserId());
				

				int result = st.executeUpdate();

				return result > 0 ? 1 : 0;

			} catch (SQLException | ClassNotFoundException ex) {

				ex.printStackTrace();

				return -1;

			}

		}
		
		//to change the login info (password)
		public int changeLoginInfo(String userId, String curpassword, String newpassword) {
			try (Connection conn = dbconfig.getConnection();
					PreparedStatement st = conn.prepareStatement("SELECT User_Id, Password FROM users WHERE User_Id = ?")) {

				// Set parameters for the prepared statement
				System.out.println("check 1 " + userId);
				System.out.println("check 2 " + curpassword);
				System.out.println("check 3 " + newpassword);
				st.setString(1, userId);

				// Execute the query
				try (ResultSet rs = st.executeQuery()) {
					if (rs.next()) {
						String userDb = rs.getString("User_Id");
						System.out.println("check 1 " + userDb);
						String passwordDb = rs.getString("Password");
						System.out.println("check 2 " + passwordDb);

						String decryptedPwd = PasswordEncryptionWithAes.decrypt(passwordDb, userDb);
						System.out.println("check 3 " + decryptedPwd);

						// Check if the provided credentials match the database records
						if (decryptedPwd != null && decryptedPwd.equals(curpassword) && userDb.equals(userId)) {

							// Prepare the update statement
							PreparedStatement st1 = conn
									.prepareStatement("UPDATE users SET Password = ? WHERE User_Id = ?");
							st1.setString(1, PasswordEncryptionWithAes.encrypt(userId, newpassword)); // Set new encrypted
																										// password
							st1.setString(2, userId); // Set user ID for WHERE clause

							// Execute the update statement
							int rowsUpdated = st1.executeUpdate();

							if (rowsUpdated > 0) {
								return 1; // Successful password update
							} else {
								return -1; // Update failed
							}

						} else {
							return 4; // Incorrect current password
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
