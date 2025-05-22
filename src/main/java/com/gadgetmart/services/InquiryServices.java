package com.gadgetmart.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gadgetmart.config.DatabaseConfig;
import com.gadgetmart.model.CustomerInquiryModel;
import com.gadgetmart.model.InquiryModel;

public class InquiryServices {
	private final DatabaseConfig dbconfig = new DatabaseConfig();
	
	//inquiry form
		public int addUserInquiry(InquiryModel inqury) {

			try (Connection conn = dbconfig.getConnection()) {

				PreparedStatement st = conn.prepareStatement("INSERT INTO inquiry (Inquiry_Id, User_Id, Subject, Created_At, Message) VALUES (?, ?, ?,?, ?)"
						+ "");

				// insert the inquiry detail in database
				st.setString(1, inqury.getInquiryID());
				st.setString(2, inqury.getUserID());
				st.setString(3, inqury.getSubject());
				st.setString(4, inqury.getCreatedAt());
				st.setString(5, inqury.getMessage());

				int result = st.executeUpdate();
				return result > 0 ? 1 : 0;

			} catch (SQLException | ClassNotFoundException ex) {
				ex.printStackTrace();
				return -1;
			}

		}
		
		//Fetch inquiry details
		public List<CustomerInquiryModel> getAllInquiryDetails() {
			List<CustomerInquiryModel> InquiryCus = new ArrayList<>();
			try {
				PreparedStatement st = dbconfig.getConnection().prepareStatement("SELECT i.User_Id AS Id, u.Full_Name AS Customer_Name,i.Inquiry_Id,i.Message AS Message,i.Created_At AS Created_Date ,u.Image_Link,u.Email AS Customer_Email FROM inquiry i JOIN users u ON i.User_Id = u.User_Id");
				ResultSet result = st.executeQuery();

				while (result.next()) {

					CustomerInquiryModel inquiryDetails = new CustomerInquiryModel();

					inquiryDetails.setId(result.getString("Id"));
					inquiryDetails.setInquiryId(result.getString("Inquiry_Id"));
					inquiryDetails.setCustomerName(result.getString("Customer_Name"));
					inquiryDetails.setCustomerEmail(result.getString("Customer_Email"));
					inquiryDetails.setCustomerMessage(result.getString("Message"));
					inquiryDetails.setCreatedDate(result.getString("Created_Date"));
					inquiryDetails.setCustomerImg(result.getString("Image_Link"));
					InquiryCus.add(inquiryDetails);
				}
			} catch (SQLException | ClassNotFoundException ex) {
				ex.printStackTrace();
			}
			return InquiryCus;
		}
		
		//get Inquiry detail by id
		public ArrayList<CustomerInquiryModel> getInquirybyID(String InquiryID) {
			try {
				PreparedStatement st = dbconfig.getConnection().prepareStatement("SELECT u.Full_Name AS Customer_Name,u.Email AS Customer_Email, u.User_Address AS Customer_Address, u.Phone_Number AS Customer_PhoneNumber, u.Image_Link AS Image_Link, i.Subject, i.Message AS Customer_Message, i.Created_At AS Create_Date FROM inquiry i JOIN users u ON i.User_Id = u.User_Id WHERE i.Inquiry_Id = ? ");
				st.setString(1, InquiryID);
				ResultSet result = st.executeQuery();

				ArrayList<CustomerInquiryModel> selected_inquiry= new ArrayList<>();

				while (result.next()) {
					CustomerInquiryModel customerInquiry = new CustomerInquiryModel();
					customerInquiry.setCustomerName(result.getString("Customer_Name"));
					customerInquiry.setCustomerEmail(result.getString("Customer_Email"));
					customerInquiry.setCustomerAddress(result.getString("Customer_Address"));
					customerInquiry.setCustomerPhoneNumber(result.getString("Customer_PhoneNumber"));
					customerInquiry.setCustomerSubject(result.getString("Subject"));
					customerInquiry.setCustomerMessage(result.getString("Customer_Message"));
					customerInquiry.setCreatedDate(result.getString("Create_Date"));
					customerInquiry.setCustomerImg(result.getString("Image_Link"));
					selected_inquiry.add(customerInquiry);
				}

				return selected_inquiry;

			} catch (SQLException | ClassNotFoundException ex) {
				ex.printStackTrace();
				return null; // Return an empty list if an error occurs
			}
		}
		
		//Delete Inquiry
		public int deleteInquiry(String inquiryId) {
			try (Connection con = dbconfig.getConnection()) {
				PreparedStatement st = con.prepareStatement("DELETE FROM inquiry WHERE Inquiry_Id = ?");
				st.setString(1, inquiryId);
				return st.executeUpdate();
			} catch (SQLException | ClassNotFoundException ex) {
				ex.printStackTrace(); // Log the exception for debugging
				return -1;
			}
		}
		
}
