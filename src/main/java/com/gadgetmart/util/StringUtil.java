package com.gadgetmart.util;

public class StringUtil {
	//start query
	public static final String INSERT_USER = "INSERT INTO users ("
			+ "User_Id,Full_Name,Email,Password,Phone_Number,Gender,Role) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
	public static final String GET_USER_INFO = "SELECT User_ID, Password, Role FROM users WHERE User_ID = ?";
	

	
	//end query
	
	
	//Start: Servlet url Route
	public static final String SERVLET_URL_HOME = "/home";
	public static final String SERVLET_URL_REGISTER = "/register";
	public static final String SERVLET_URL_LOGIN = "/login";
	
	//END: Servlet url Route
	
	
	//Start: Database Connection
	public static final String DB_URL ="jdbc:mysql://localhost:3306/GadgetMart";
	public static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String DB_USERNAME = "root";
	public static final String DB_PASSWORD = "";
	
	//End: Database Connection
	
	//For login servlet starts
	public static final String LOGIN_USER = "login_username";
	public static final String LOGIN_PASSWORD = "login_password";
	public static final String ERROR = "ERROR_MESSAGE";
	public static final String ROLE_USER_NOT = "The User ID for the currently selected role is not available.";
	public static final String PASS_NOT = " Username or Passwords do not match";
	public static final String ACCOUNT_NOT = "No matching record found. Create a New Account !!!";
	public static final String SERVER_NOT = "Oops! There is a server problem.";
	
	//for login servlet ends

	//for register servlet start

		public static final String ERROR_VAL = "valError";
		public static final String userFIELD = "username";
		public static final String FULLNAME_FIELD = "fullName";
		public static final String EMAIL_FIELD = "email";
		public static final String PHONE_FIELD = "phoneNumber";
		public static final String PASSW_FIELD = "password";
		public static final String REPASSW_FIELD = "confirmPassword";
		public static final String GENDER_FIELD = "gender";
		public static final String ROLE_FIELD = "user";

		public static final String USER_ID_NON = "User Name must be at least 6 characters long.";
		public static final String FULL_NAME_NON = "Invalid full name. Please enter a valid name.";
		public static final String EMAIL_NON = "Invalid email address. Please enter a valid email.";
		public static final String PHONE_NON = "Phone number musts have a total of 10 characters.";
		public static final String PASSW_NON = "Password must be at least 8 characters long and contain Uppercase ,LowerCase, numbers and Sybols";
		public static final String PASSW_NON_NOT = "Password did not match";
		public static final String DUPLICACY_NON = "Check UserId, Email, or PhoneNumber which already exists!";
	//for register servlet ends
		
		//redirection links starts
		public static final String USER_HOME = "/Pages/home.jsp";
		public static final String LOGIN_PAGE = "/Pages/login.jsp";
		public static final String REGISTER_PAGE = "/Pages/register.jsp";
	// redirection links ends
		
		// login data starts
		public static final String USER = "User_ID";
		public static final String USER_ROLE = "Role";
		public static final String USER_CREDENTIALS = "Password";
	// login data ends

	//roles start

		public static final String Role1 = "admin";
		public static final String Role2 = "user";

	//roles end
		
	//authentication filter starts
		public static final String SESSION_DATA = "userID";

		public static final String ISADMIN = "isAdmin";
	//auhentication filter ends
}
