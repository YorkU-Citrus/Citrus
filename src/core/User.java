package core;

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
import security.DataFilter;

@Path("user")
public class User {

	@POST
	@Path("/register/")
	@Produces("application/json")
	public String RestRegisterUser(@FormParam("username") String username, @FormParam("password") String password,
			@FormParam("repassword") String repassword, @Context HttpServletRequest request) {
		try {
			registerUser(username,password,repassword, request);
			return Json.createObjectBuilder().add("success", DataFilter.removeHTMLTags(username)).build().toString();
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
			login(username,password, request);
			return Json.createObjectBuilder().add("success", DataFilter.removeHTMLTags(username)).build().toString();
		} catch (Exception e) {
			return Json.createObjectBuilder().add("error", e.getMessage()).build().toString();
		}
	}
	
	
	public static void registerUser(String username, String password, String repassword, HttpServletRequest request)
			throws CitrusFormException {
		if ((username == null) || (username == "")) {
			throw new CitrusFormException("User name cannot be empty!");
		}
		
		if (!username.matches("^[a-z0-9\\-\\_]{3,20}$")) {
			throw new CitrusFormException("User name should only consist of lowercase characters and numbers! <br/>Length should between 3 and 20.");
		} 

		if ((password == null) || (password == "")) {
			throw new CitrusFormException("Password cannot be empty!");
		}

		if (!password.matches("^[a-zA-Z0-9\\-\\_\\.\\,]{6,20}$")) {
			throw new CitrusFormException("You can only use characters, number and -_., for your password. <br/>Length should between 6 and 20.");
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

		if (!username.matches("^[a-z0-9\\-\\_]{3,20}$")) {
			throw new CitrusFormException("User name should only consist of lowercase characters and numbers! <br/>Length should between 3 and 20.");
		} 
		
		if ((password == null) || (password == "")) {
			throw new CitrusFormException("Password cannot be empty!");
		}

		if (!password.matches("^[a-zA-Z0-9\\-\\_\\.\\,]{6,20}$")) {
			throw new CitrusFormException("You can only use characters, number and -_., for your password. <br/>Length should between 6 and 20.");
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

	public static void updateBillingInformation(String firstName, String lastName, 
			String address, String province, String country, String postalCode, HttpServletRequest request)
			throws CitrusFormException {
		if ((firstName == null) || (firstName == "")) {
			throw new CitrusFormException("First name cannot be empty!");
		}
		if (!firstName.matches("^[a-zA-Z\\'\\-\\_\\s]{2,20}$")) {
			throw new CitrusFormException("Please enter a valid first name.");
		} 
		if ((lastName == null) || (lastName == "")) {
			throw new CitrusFormException("Last name cannot be empty!");
		}
		if (!lastName.matches("^[a-zA-Z\\'\\-\\_\\s]{2,20}$")) {
			throw new CitrusFormException("Please enter a valid last name.");
		} 
		if ((address == null) || (address == "")) {
			throw new CitrusFormException("Address cannot be empty!");
		}
		if (!address.matches("^[a-zA-Z0-9\\(\\)\\'\\-\\_\\s]{2,20}$")) {
			throw new CitrusFormException("Please enter a valid address.");
		} 
		if ((province == null) || (province == "")) {
			throw new CitrusFormException("Provience cannot be empty!");
		}
		if (!province.matches("^[a-zA-Z0-9\\(\\)\\'\\-\\_\\s]{2,20}$")) {
			throw new CitrusFormException("Please enter a valid province.");
		} 
		if ((country == null) || (country == "")) {
			throw new CitrusFormException("country cannot be empty!");
		}
		if (!country.matches("^[a-zA-Z0-9\\(\\)\\'\\-\\_\\s]{2,20}$")) {
			throw new CitrusFormException("Please enter a valid country.");
		} 
		if ((postalCode == null) || (postalCode == "")) {
			throw new CitrusFormException("Postal code cannot be empty!");
		}
		if (!province.matches("^[a-zA-Z0-9\\(\\)\\'\\-\\_\\s]{2,20}$")) {
			throw new CitrusFormException("Please enter a valid postal code.");
		} 
		try {
			AddressDAO dataSource = AddressDAO.getInstance();
			HttpSession session = request.getSession(true);
			UserBean user = (UserBean) session.getAttribute("user");
			if (user == null) {
				throw new CitrusFormException("Please login!");
			}

			BillingAddressBean data = new BillingAddressBean(user.getUid(), firstName, lastName,
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
		if (!firstName.matches("^[a-zA-Z\\'\\-\\_\\s]{2,20}$")) {
			throw new CitrusFormException("Please enter a valid first name.");
		} 
		if ((lastName == null) || (lastName == "")) {
			throw new CitrusFormException("Last name cannot be empty!");
		}
		if (!lastName.matches("^[a-zA-Z\\'\\-\\_\\s]{2,20}$")) {
			throw new CitrusFormException("Please enter a valid last name.");
		} 
		if ((address == null) || (address == "")) {
			throw new CitrusFormException("Address cannot be empty!");
		}
		if (!address.matches("^[a-zA-Z0-9\\(\\)\\'\\-\\_\\s]{2,20}$")) {
			throw new CitrusFormException("Please enter a valid address.");
		} 
		if ((province == null) || (province == "")) {
			throw new CitrusFormException("Provience cannot be empty!");
		}
		if (!province.matches("^[a-zA-Z0-9\\(\\)\\'\\-\\_\\s]{2,20}$")) {
			throw new CitrusFormException("Please enter a valid province.");
		} 
		if ((country == null) || (country == "")) {
			throw new CitrusFormException("country cannot be empty!");
		}
		if (!country.matches("^[a-zA-Z0-9\\(\\)\\'\\-\\_\\s]{2,20}$")) {
			throw new CitrusFormException("Please enter a valid country.");
		} 
		if ((postalCode == null) || (postalCode == "")) {
			throw new CitrusFormException("Postal code cannot be empty!");
		}
		if (!province.matches("^[a-zA-Z0-9\\(\\)\\'\\-\\_\\s]{2,20}$")) {
			throw new CitrusFormException("Please enter a valid postal code.");
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
