package com.gadgetmart.util;

public class StringUtil {
	// start query
	public static final String INSERT_USER = "INSERT INTO users ("
			+ "User_Id,Full_Name,Email,Password,Phone_Number,Gender,Role) " + "VALUES (?, ?, ?, ?, ?, ?, ?)";
	public static final String GET_USER_INFO = "SELECT User_Id, Password, Role FROM users WHERE User_Id= ?";
	public static final String ADD_PRODUCTS = "INSERT INTO products (Product_Id, Product_Name, Screen_Size, Processor, Ram, Features,Discount, Storage, Camera, Warranty,Price, Discount_Amount, Image) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
	public static final String ID_PRODUCTS = "SELECT * FROM products where Product_Id = ? ";
	public static final String SEARCH_PRODUCTS = "SELECT * FROM products WHERE Product_Name LIKE ? ";
	public static final String CATAGORY_PRODUCTS = "SELECT * FROM products WHERE Product_Id LIKE ? ";
	public static final String TOP_PRODUCTS = "SELECT * FROM products ORDER BY Discount DESC, Price DESC LIMIT 4";

	public static final String CHECK_DUP_CART = "SELECT * from carts WHERE User_Id = ? and Product_Id= ?";
	public static final String ADD_CART = "INSERT INTO carts (Cart_Id,User_Id,Product_Id,Quantity) VALUES (?, ?, ?, ?)";
	public static final String GET_CART = "SELECT * from carts Where User_Id = ?";

	public static final String LIST_ORDERS = "SELECT o.User_Id AS Id, u.Full_Name AS Customer_Name, oi.Product_Id AS Product_Id, p.Product_Name, o.Total_Amount AS Amount, o.Status,o.Order_Id ,u.Image_Link FROM orders o JOIN order_producrs oi ON o.Order_Id = oi.Order_Id JOIN products p ON oi.Product_Id = p.Product_Id JOIN users u ON o.User_Id = u.User_Id";

	public static final String USER_QUERY = "SELECT User_Id,Full_Name,Email,Phone_Number,User_Address from users where  User_Id= ?";
	public static final String IMG_RETRIVAL_QUERY = "SELECT Image_link from users where  User_Id= ?";
	public static final String UPDATE_USER_IMG = "UPDATE users SET Image_link = ? WHERE User_Id = ?";
	public static final String UPDATE_USER = "UPDATE users SET Full_Name = ?, User_address = ? WHERE User_Id = ?";
	public static final String UPDATE_PRODUCT_ADMIN = "UPDATE products SET Product_Name = ?, Screen_Size = ?, Processor = ?, Ram = ?, Features = ?,"
			+ " Storage = ?, Camera = ?, Price = ? ,Warranty = ?,Discount = ?," + " Image = ? WHERE Product_Id = ?";
	public static final String QUERY_DELETE_PRODUCT = "DELETE FROM products WHERE Product_Id = ?";

	public static final String IMAGE_ROOT_PATH = "MySecondTeacher\\Year-2\\4th-Sem\\Advance Programming and Technologies\\Coursework Content\\Coursework\\Project\\GadgetMart\\src\\main\\webapp\\resources\\images\\";
	public static final String IMAGE_DIR_PRODUCT = "D:/" + IMAGE_ROOT_PATH + "product_images/";
	public static final String IMAGE_DIR_USERS = "D:/" + IMAGE_ROOT_PATH + "users/";

	// end query

	// data duplicacy check start
	public static final String CHECK_DUP_ID = "SELECT * from users where User_ID = ? ";
	public static final String CHECK_DUP_EMAIL = "SELECT * from users where Email = ? ";
	public static final String CHECK_DUP_NUMBER = "SELECT * from users where Phone_Number = ? ";
	// data duplicacy check end

	// Start: Servlet url Route
	public static final String SERVLET_URL_HOME = "/home";
	public static final String SERVLET_URL_SHOP = "/ShopServlet";
	public static final String SERVLET_URL_ORDER = "/FetchCustomerOrder";
	public static final String SERVLET_URL_REGISTER = "/register";
	public static final String SERVLET_URL_LOGIN = "/login";

	// END: Servlet url Route

	// Start: Database Connection
	public static final String DB_URL = "jdbc:mysql://localhost:3306/GadgetMart";
	public static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String DB_USERNAME = "root";
	public static final String DB_PASSWORD = "";

	// End: Database Connection

	// For login servlet starts
	public static final String LOGIN_USER = "login_username";
	public static final String LOGIN_PASSWORD = "login_password";
	public static final String ERROR = "ERROR_MESSAGE";
	public static final String SUCCESS = "SUCCESS_MESSAGE";
	public static final String ROLE_USER_NOT = "The User ID for the currently selected role is not available.";
	public static final String PASS_NOT = " Username or Passwords do not match";
	public static final String ACCOUNT_NOT = "No matching record found. Create a New Account !!!";
	public static final String SERVER_NOT = "Oops! There is a server problem.";

	// for login servlet ends

	// for register servlet start

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
	// for register servlet ends

	// redirection links starts
	public static final String ADMIN_HOME = "/WEB-INF/Pages/Admin/adminOrder_list.jsp";
	public static final String ADMIN_PRODUCT = "/WEB-INF/Pages/Admin/adminProduct_View.jsp";
	public static final String USER_HOME = "/WEB-INF/Pages/User/home.jsp";
	public static final String LOGIN_PAGE = "/WEB-INF/Pages/User/login.jsp";
	public static final String REGISTER_PAGE = "/WEB-INF/Pages/User/register.jsp";
	// redirection links ends

	// login data starts
	public static final String USER = "User_ID";
	public static final String USER_ROLE = "Role";
	public static final String USER_CREDENTIALS = "Password";
	// login data ends

	// roles start

	public static final String Role1 = "admin";
	public static final String Role2 = "user";

	// roles end

	// authentication filter starts
	public static final String COOKIE_USER = "user";
	public static final String SESSION_IMG = "img";
	public static final String SESSION_DATA = "userID";
	public static final String ISADMIN = "isAdmin";
	public static final String LOGIN_ENDS = "/login.jsp";
	public static final String LOGIN_SERVLET_ENDS = "/login";
	public static final String REGISTER_ENDS = "/register.jsp";
	public static final String REGISTER_SERVLET_ENDS = "/register";
	public static final String LOGOUT_SERVLET_ENDS = "/LogoutServlet";
	public static final String ADDPRODUCT_SERVLET_ENDS = "/AddProductServlet";
	public static final String CART_SERVLET_ENDS = "/CartServlet";
	public static final String CATEGORY_SERVLET_ENDS = "/CategoryServlet";
	public static final String DELETEINQUIRYADMIN_SERVLET_END = "/DeleteInquiryByAdmin";
	public static final String DELETEPRODUCTADMIN_SERVLET_END = "/DeleteProductByAdmin";
	public static final String DISPLAYCART_SERVLET_END = "/DisplayCart";
	public static final String CUSTOMERORDER_SERVLET_END = "/FetchCustomerOrder";
	public static final String PRODUCTFORADMIN_SERVLET_END = "/FetchProductForAdmin";
	public static final String INQUIRYDETAIL_SERVLET_END = "/InquiryDetailServlet";
	public static final String INQUIRYLIST_SERVLET_END = "/InquiryListServlet";
	public static final String INQUIRY_SERVLET_END = "/InquiryServlet";
	public static final String MEMBER_SERVLET_END = "/MemberServlet";
	public static final String ORDER_SERVLET_END = "/OrderServlet";
	public static final String PRODUCTDETAILADMIN_SERVLET_END = "/ProductDetailAdmin";
	public static final String PRODUCTDETAIL_SERVLET_END = "/ProductDetailServlet";
	public static final String DELETEPRODFROMCART_SERVLET_END = "/RemoveFromCartServlet";
	public static final String SEARCH_SERVLET_END = "/SearchServlet";
	public static final String SHOP_SERVLET_END = "/ShopServlet";
	public static final String UPDATEPHOTO_SERVLET_END = "/UpdatePhoto";
	public static final String PROFILE_ENDS = "/ProfileServlet";
	public static final String ORDER_LIST_ENDS = "/OrderlistServlet";
	public static final String DASHBOARD_ENDS = "/DashboardServlet";
	public static final String UPDATEPASS_ENDS = "/UpdatePasswordServlet";
	public static final String UPDATEPRODUCT_ENDS = "/UpdateProductServlet";
	public static final String UPDATEPROFILE_ENDS = "/UpdateProfileServlet";
	public static final String UPDATEORDER_ENDS = "/UpdateOrder";
	public static final String CHECKOUT_ENDS = "/CheckoutServlet";

	// authentication filter ends

	// to add product start
	public static final String PID = "pID";
	public static final String PNAME = "pname";
	public static final String PPRICE = "pprice";
	public static final String PSIZE = "psize";
	public static final String PRAM = "pram";
	public static final String PPROC = "pproc";
	public static final String PBAT = "pbat";
	public static final String PSTOR = "pstor";
	public static final String PWAR = "pwar";
	public static final String PCAM = "pcam";
	public static final String PDIS = "pdis";
	public static final String PFEAT = "pfeat";
	public static final String PIMG = "pimage";

	public static final String ADD_PRODUCT_ERROR = "The Product Already Exists!";
	public static final String ADD_PRODUCT_SUCCESS = "The Product Added Successfully!";
	public static final String UPDTAE_PRODUCT_SUCCESS = "Product updated successfully.";
	public static final String UPDATE_PRODUCT_ERROR = "Failed to update product.";

	// to add product end

	// add to cart start
	public static final String PROD_ID_CART = "product_id_cart";
	public static final String CARD_ID = "CRD001";
	public static final String QUANT = "quant";
	public static final String CART_ERROR_MSG = "Product already\r\n" + "	exists in cart!\r\n" + "	Go To CartPage";
	public static final String SINGLE_PAGE_PROD = "/Pages/shop.jsp";
	public static final String FETCH_PRODUCT_SERVLET = "/ShopServlet";
	public static final String REDIRECT_SERVLET = "/ProductDetailServlet?product_=\" + \"productID";
	public static final String CART_VIEW_PAGE = "/WEB-INF/Pages/User/cart_list.jsp";

	// add to cart ends

	// checkout
	public static final String ORDER_CONFIRM = "/WEB-INF/Pages/User/order_confirm.jsp";

	// Message
	public static final String MESSAGE_SUCCESS = "successMessage";
	public static final String MESSAGE_ERROR = "errorMessage";

	// admin message
	public static final String MESSAGE_SUCCESS_DELETE = "Successfully Deleted!";
	public static final String MESSAGE_ERROR_DELETE = "Cannot delete the product!";

	public static final String ADMIN_FETCH_PROD = "/FetchProductForAdmin";
	public static final String PRODUCT_VIEW_PAGE = "/WEB-INF/Pages/Admin/adminProduct_view.jsp";
	

}
