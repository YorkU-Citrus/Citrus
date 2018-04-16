package rest;

import javax.json.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;

import bean.UserBean;
import dao.UserDAO;

@Path("user")
public class User {
    
	@GET
	@Path("/register/")
	@Produces("application/json")
	public String registerUser(@QueryParam("username") String username, @QueryParam("password") String password) {
		if (username == null) {
			return Json.createObjectBuilder().add("error", "User name cannot be empty!").build().toString();
		}
		if (password == null) {
			return Json.createObjectBuilder().add("error", "Password cannot be empty!").build().toString();
		}
		try {
			UserDAO dataSource = UserDAO.getInstance();
			if (dataSource.getUserByName(username) == null) {
				// Add User
				dataSource.addUser(new UserBean(username, password));
				return Json.createObjectBuilder().add("success", username).build().toString();
			}else {
				// User Exist
				return Json.createObjectBuilder().add("error", "User name [" + username + "] is occupied!" ).build().toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Json.createObjectBuilder().add("error", e.getMessage()).build().toString();
		}		
	}
	

	@GET
	@Path("/login/")
	@Produces("application/json")
	public String userLogin(@QueryParam("username") String username, @QueryParam("password") String password, @Context HttpServletRequest request) {
		if (username == null) {
			return Json.createObjectBuilder().add("error", "User name cannot be empty!").build().toString();
		}
		if (password == null) {
			return Json.createObjectBuilder().add("error", "Password cannot be empty!").build().toString();
		}
		try {
			UserDAO dataSource = UserDAO.getInstance();
			UserBean user = dataSource.getUserByName(username);
			if (user == null) {
				// User does not exist
				return Json.createObjectBuilder().add("error", "User name [" + username + "] does not exist!" ).build().toString();
			}else {
				// User Exist
				if (user.verifyPassword(password)) {
			        HttpSession session= request.getSession(true);
			        session.setAttribute("user", user);
					return Json.createObjectBuilder().add("success", username).build().toString();
				}else {
					return Json.createObjectBuilder().add("error", "Incorrect Password!" ).build().toString();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Json.createObjectBuilder().add("error", e.getMessage()).build().toString();
		}		
	}
	

	public static void main(String[] args) {
		//JsonObject result = Json.createObjectBuilder().add("firstName", "John").build();
		//System.out.println(result.toString());
	}
}
