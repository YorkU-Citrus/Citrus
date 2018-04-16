package core;

import javax.json.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;

import bean.UserBean;
import dao.UserDAO;
import exception.CitrusFormException;

@Path("user")
public class User {
    
	@POST
	@Path("/register/")
	@Produces("application/json")
	public String RestRegisterUser(@FormParam("username") String username, @FormParam("password") String password, @FormParam("repassword") String repassword, @Context HttpServletRequest request) {
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
	public String RestUserLogin(@FormParam("username") String username, @FormParam("password") String password, @Context HttpServletRequest request) {
		try {
			userLogin(username, password, request);
			return Json.createObjectBuilder().add("success", username).build().toString();
		} catch (Exception e) {
			return Json.createObjectBuilder().add("error", e.getMessage()).build().toString();
		}	
	}
	
	public static void registerUser(String username, String password, String repassword, HttpServletRequest request) throws CitrusFormException {
		if (username == null) {
			throw new CitrusFormException("User name cannot be empty!");
		}
		if (password == null) {
			throw new CitrusFormException("Password cannot be empty!");
		}
		if (repassword == null) {
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
		        HttpSession session= request.getSession(true);
		        session.setAttribute("user", user);				
			}else {
				// User Exist
				throw new CitrusFormException("User name [" + username + "] is occupied!");
			}

		} catch (CitrusFormException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new CitrusFormException("Undefined Error: " +  e.getMessage());
		}	
	}
	
	public static void userLogin(String username, String password, HttpServletRequest request) throws CitrusFormException {
		if (username == null) {
			throw new CitrusFormException("User name cannot be empty!");
		}
		if (password == null) {
			throw new CitrusFormException("Password cannot be empty!");
		}
		try {
			UserDAO dataSource = UserDAO.getInstance();
			UserBean user = dataSource.getUserByName(username);
			if (user == null) {
				// User does not exist
				throw new CitrusFormException("User name [" + username + "] does not exist!" );
			}else {
				// User Exist
				if (user.verifyPassword(password)) {
			        HttpSession session= request.getSession(true);
			        session.setAttribute("user", user);
				}else {
					throw new CitrusFormException("Incorrect Password!");
				}
			}
		} catch (CitrusFormException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new CitrusFormException("Undefined Error: " +  e.getMessage());
		}
	}

	public static void main(String[] args) {
		//JsonObject result = Json.createObjectBuilder().add("firstName", "John").build();
		//System.out.println(result.toString());
	}
}
