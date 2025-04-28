package com.gadgetmart.util;

import com.mysql.cj.util.StringUtils;

public class ValidationUtil {
	
	//validate the user_id field
	public static boolean isUserIDValid(String user_id) {
		 return user_id.matches("[a-zA-Z0-9]+"); // Match letters and digits only
		 
	}
	
	//validate the fullname and its field
	public static boolean isText(String fullname) {
		return fullname.matches("[a-zA-Z\\s]+"); //matches letter only
	}
	
	//validate the email and its field
	public static boolean isEmail(String email) {
		return email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
		
	}
	
	
	//validate the password and its field
	public static boolean isPasswordValid(String password) {
		return password.matches("^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[A-Z]).*$");
	}
	
	
	//validate the gender
	public static boolean isGenderValid(String gender) {
		return gender != null && (gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female"));
	}
	

}
