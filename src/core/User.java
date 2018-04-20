3package core;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import javax.json.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import bean.AddressBean;
import bean.BillingAddressBean;
import bean.UserBean;
import dao.AddressDAO;
import dao.UserDAO;
import exception.CitrusFormException;

@Path("user")
public class User {
	@POST
	@Path("/register/")
	@Produces("application/json")
	public String RestRegisterUser(@FormParam("username") String username, @FormParam("password") String password,
			@FormParam("repassword") String repassword, @Context HttpServletRequest request) {
		try {
			registerUser(username, password, repassword, request);
			return Json.createObjectBuilder().add("success", username).build().toString();
		} catch (Exception e) {
			return Json.createObjectBuilder().add("error", e.getMessage()).build().toString();
		}
	}

	@POST
	@Path("/login/")
	@Produces("application/json")
	public String RestUserLogin(@FormParam("username") String username, @FormParam("password") String password,
			@Context HttpServletRequest request) {
		try {
			login(username, password, request);
			return Json.createObjectBuilder().add("success", username).build().toString();
		} catch (Exception e) {
			return Json.createObjectBuilder().add("error", e.getMessage()).build().toString();
		}
	}

	public static void registerUser(String username, String password, String repassword, HttpServletRequest request)
			throws CitrusFormException {
		if ((username == null) || (username == "")) {
			throw new CitrusFormException("User name cannot be empty!");
		}
		if ((password == null) || (password == "")) {
			throw new CitrusFormException("Password cannot be empty!");
		}
		if ((repassword == null) || (repassword == "")) {
			throw new CitrusFormException("Please retype password!");
		}
		if (!repassword.equals(password)) {
			throw new CitrusFormException("Two passwords are not the same!");
		}
		try {
			UserDAO dataSource = UserDAO.getInstance();
			if (dataSource.getUserByName(username) == null) {
				// Add User
				dataSource.addUser(new UserBean(username, password));
				UserBean user = dataSource.getUserByName(username);
				HttpSession session = request.getSession(true);
				session.setAttribute("user", user);
			} else {
				// User Exist
				throw new CitrusFormException("User name [" + username + "] is occupied!");
			}

		} catch (CitrusFormException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new CitrusFormException("Undefined Error: " + e.getMessage());
		}
	}

	public static void login(String username, String password, HttpServletRequest request) throws CitrusFormException {
		if ((username == null) || (username == "")) {
			throw new CitrusFormException("User name cannot be empty!");
		}
		if ((password == null) || (password == "")) {
			throw new CitrusFormException("Password cannot be empty!");
		}
		try {
			UserDAO dataSource = UserDAO.getInstance();
			UserBean user = dataSource.getUserByName(username);
			if (user == null) {
				// User does not exist
				throw new CitrusFormException("User name [" + username + "] does not exist!");
			} else {
				// User Exist
				if (user.verifyPassword(password)) {
					HttpSession session = request.getSession(true);
					session.setAttribute("user", user);
				} else {
					throw new CitrusFormException("Incorrect Password!");
				}
			}
		} catch (CitrusFormException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new CitrusFormException("Undefined Error: " + e.getMessage());
		}
	}

	public static void updateBillingInformation(String firstName, String lastName, String cardNumber, String cvv,
			String address, String province, String country, String postalCode, HttpServletRequest request)
			throws CitrusFormException {
		if ((firstName == null) || (firstName == "")) {
			throw new CitrusFormException("First name cannot be empty!");
		}
		if ((lastName == null) || (lastName == "")) {
			throw new CitrusFormException("Last name cannot be empty!");
		}
		
		if ((cardNumber == null) || (cardNumber == "")) {
			throw new CitrusFormException("Credit card number cannot be empty!");
		}
		if ((cardNumber.length() != 14) && !cardNumber.chars().allMatch(Character::isDigit)) {
			throw new CitrusFormException("Credit card number should be of 14 digits and be of Type Number");
		}
		
		if ((cvv == null) || (cvv == "")) {
			throw new CitrusFormException("CVV cannot be empty!");
		}
		if((cvv.length() != 3) && !(cvv.chars().allMatch(Character::isDigit))) {
			throw new CitrusFormException("CVV should be of 3 digits and be of Type Number");
		}
		
		if ((address == null) || (address == "")) {
			throw new CitrusFormException("Address cannot be empty!");
		}
		if ((province == null) || (province == "")) {
			throw new CitrusFormException("Provience cannot be empty!");
		}
		
		if ((country == null) || (country == "")) {
			throw new CitrusFormException("Country cannot be empty!");
		}
		if ((postalCode == null) || (postalCode == "")) {
			throw new CitrusFormException("Postal code cannot be empty!");
		}
		try {
			AddressDAO dataSource = AddressDAO.getInstance();
			HttpSession session = request.getSession(true);
			UserBean user = (UserBean) session.getAttribute("user");
			if (user == null) {
				throw new CitrusFormException("Please login!");
			}

			BillingAddressBean data = new BillingAddressBean(user.getUid(), firstName, lastName, cardNumber, cvv,
					address, province, country, postalCode);
			dataSource.addBillingAddress(data);
			
		} catch (CitrusFormException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new CitrusFormException("Undefined Error: " + e.getMessage());
		}
	}

	public static void loadBillingInformation(HttpServletRequest request) throws CitrusFormException {
		try {
			AddressDAO dataSource = AddressDAO.getInstance();
			HttpSession session = request.getSession(true);
			UserBean user = (UserBean) session.getAttribute("user");
			if (user == null) {
				throw new CitrusFormException("Please login!");
			}
			BillingAddressBean data = dataSource.getBillingAddressByUser(user.getUid());
			session.setAttribute("billing", data);		
		} catch (CitrusFormException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new CitrusFormException("Undefined Error: " + e.getMessage());
		}
	}
	
	public static void loadShippingInformation(HttpServletRequest request) throws CitrusFormException {
		try {
			AddressDAO dataSource = AddressDAO.getInstance();
			HttpSession session = request.getSession(true);
			UserBean user = (UserBean) session.getAttribute("user");
			if (user == null) {
				throw new CitrusFormException("Please login!");
			}
			AddressBean data = dataSource.getAddressByUser(user.getUid());
			session.setAttribute("shipping", data);		
		} catch (CitrusFormException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new CitrusFormException("Undefined Error: " + e.getMessage());
		}
		
	}
	
	public static void updateShippingInformation(String firstName, String lastName, String address, String province
			, String country, String postalCode, HttpServletRequest request)
			throws CitrusFormException {
		
		if ((firstName == null) || (firstName == "")) {
			throw new CitrusFormException("First name cannot be empty!");
		}
		if ((lastName == null) || (lastName == "")) {
			throw new CitrusFormException("Last name cannot be empty!");
		}
		if ((address == null) || (address == "")) {
			throw new CitrusFormException("Address/street cannot be empty!");
		}
		if ((province == null) || (province == "")) {
			throw new CitrusFormException("Province cannot be empty!");
		}
		if ((country == null) || (country == "")) {
			throw new CitrusFormException("Country cannot be empty!");
		}
		if ((postalCode == null) || (postalCode == "")) {
			throw new CitrusFormException("Postal code cannot be empty!");
		}
		
		try {
			AddressDAO dataSource = AddressDAO.getInstance();
			HttpSession session = request.getSession(true);
			UserBean user = (UserBean) session.getAttribute("user");
			if (user == null) {
				throw new CitrusFormException("Please login!");
			}

			AddressBean data = new AddressBean(user.getUid(), firstName, lastName,
					address, province, country, postalCode);
			dataSource.addAddress(data);
			
		} catch (CitrusFormException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new CitrusFormException("Undefined Error: " + e.getMessage());
		}
		
	}
	
	public static void main(String[] args) {
		// JsonObject result = Json.createObjectBuilder().add("firstName",
		// "John").build();
		// System.out.println(result.toString());
	}
}
