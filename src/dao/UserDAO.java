package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.catalina.User;

import bean.UserBean;

public class UserDAO{
	// Singleton
	/**
	 * Only one object per program.
	 */
	private static UserDAO instance = null;

	public static synchronized UserDAO getInstance()
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (instance == null) {
			instance = new UserDAO();
		}
		return instance;
	}
	
	// Implementation
	// Constructor
	private PreparedStatement insertUserStatement;
	private PreparedStatement getUserByNameStatement;
	private PreparedStatement signInStatement;
	
	protected UserDAO() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Connection connection = CitrusDAO.getInstance().getConnection();
		this.insertUserStatement = connection.prepareStatement("INSERT "
				+ "INTO `citrus_db`.`citrus_user` (`uid`, `uname`, `upassword`, `ulastactive`) "
				+ "VALUES (NULL, ?, ?, CURRENT_TIMESTAMP);");
		
		this.getUserByNameStatement = connection.prepareStatement("SELECT `uid` as 'id', `uname` as 'name', `upassword` as 'password', `ulastactive` as 'lastactive' "
				+ "FROM `citrus_db`.`citrus_user` "
				+ "WHERE `uname` = ?; ");
		
		this.signInStatement = connection.prepareStatement("SELECT * "
				+ "From `citrus_db`.`citrus_user` "
				+ "WHERE `uname`= ? AND `upassword`=?; ");
		
	}
	
	public boolean signIn(UserBean user)  throws SQLException{
		signInStatement.setString(1, user.getUname());
		signInStatement.setString(2, user.getHashedPassword());
		ResultSet result = signInStatement.executeQuery();
		
		if(result.next()) {
			return true;
		}
		
		return false;
	}
	
	//Insert user
	public int addUser(UserBean user) throws SQLException{
		
		insertUserStatement.setString(1, user.getUname());
		insertUserStatement.setString(2, user.getHashedPassword());
		
		return insertUserStatement.executeUpdate();
		
	}
	
	public UserBean getUserByName(String name) throws SQLException{
		getUserByNameStatement.setString(1, name);
		ResultSet result = getUserByNameStatement.executeQuery();
		
		if (result.next()) {

			return new UserBean(
					result.getInt("id"), 
					result.getString("name"),
					result.getString("password"),
					result.getTimestamp("lastactive")
			);
			
		} else {
			return null;
		}
		
	
	}
	
	public static void main(String[] args) {

		try {
			UserDAO test = UserDAO.getInstance();
			
			System.out.println("add test_u3");
			test.addUser(new UserBean("test_u3", "test_u3pwd".hashCode()+"",new Timestamp(new Date().getTime())));
			UserBean user = test.getUserByName("test_u3");
			System.out.println(user);
			
			UserBean t1 = new UserBean("test_u3", "test_u3pwd".hashCode()+"", new Timestamp(new Date().getTime()));
			UserBean t2 = new UserBean("test_u3", "wrong_u3pwd".hashCode()+"", new Timestamp(new Date().getTime()));
			System.out.println("sign in with name=test_u3 and password=test_u3pwd: " + test.signIn(t1));
			System.out.println("sign in with name=test_u3 and password=wrong_u3pwd: " + test.signIn(t2));


		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	public UserDAO(){
		
	}
	
	//update user info,change password
	public void changePassword(String uname, String password){
		String queryText = ""; 
		PreparedStatement querySt = null; 
		
		queryText = "Update citrus_user "
				+ "Set upassword = ? "
				+ "Where uname = ?";
		
		try {
			//prepare
			querySt = db_connection.prepareStatement(queryText);
			
			//execute
			querySt.setString(1, password);
			querySt.setString(3, uname); 
			querySt.executeUpdate();
			
			//close
			querySt.close();
			
		} catch (SQLException e) {
			System.out.println("UserDAO changePassword failed.");	
			System.out.println(e.toString());
		}
	}
	
	
	//add user
	public void signUp(UserBean ub, String password){
		String queryText = ""; 
		PreparedStatement querySt = null; 
		
		queryText = "Insert "
				+ "into citrus_user "
				+ "value( ? , ?, ?, ?)";
		
		try {
			//prepare
			querySt = db_connection.prepareStatement(queryText);
			
			//execute
			querySt.setInt(1, ub.getUid()); 
			querySt.setString(2, ub.getUname()); 
			querySt.setString(3, password);
			querySt.setTimestamp(4, ub.getUlastactive());
			querySt.executeUpdate();
			
			//close
			querySt.close();
			
		} catch (SQLException e) {
			System.out.println("UserDAO signUp failed.");	
			System.out.println(e.toString());
		}
	}
	
	//login
	public boolean signIn(String uname, String upassword){
		String queryText = ""; //SQL TEXT
		PreparedStatement querySt = null; // the query handle
		ResultSet results = null; // a cursor
		boolean success = false;
		
		queryText = 
				"Select * "
			+   "From citrus_user "
			+	"Where uname = ? And upassword = ? ";
		
		try {
			//prepare
			querySt = db_connection.prepareStatement(queryText);
			
			//execute
			querySt.setString(1, uname); 
			querySt.setString(2, upassword); 
			results = querySt.executeQuery();
			
			//check results
			if(results.next()){
				success = true;
			}
			
			//close
			results.close();
			querySt.close();
			
		} catch (SQLException e) {
			System.out.println("UserDAO signIn failed.");	
			System.out.println(e.toString());
		}
		
		return success;
	}
	
	
	//check if uname is taken
	public boolean checkUname(String uname){
		String queryText = ""; //SQL TEXT
		PreparedStatement querySt = null; // the query handle
		ResultSet results = null; // a cursor
		boolean userExist = false;
		
		queryText = 
				"Select * "
			+   "From citrus_user "
			+	"Where uname = ?";
		
		//prepare the query
		try{
			querySt = db_connection.prepareStatement(queryText);
		}catch(SQLException e){
			System.out.println("checkUname failed in preparation.");
			System.out.println(e.toString());
		}
		
		// execute the query
		try{
			querySt.setString(1, uname); 
			results = querySt.executeQuery();
		}catch(SQLException e){
			System.out.println("checkUname failed in execute.");
			System.out.println(e.toString());
		}
		
		// any results?
		try{
			if(results.next()){
				userExist = true;
			}
			
			
		}catch(SQLException e){
			System.out.println("checkUname failed in cursor.");
			System.out.println(e.toString());
		}
		
		// close the cursor
		try{
			results.close();
		}catch(SQLException e){
			System.out.println("checkUname failed in closing cursor.");
			System.out.println(e.toString());
		}
		
		// close the handle
		try{
			querySt.close();
		}catch(SQLException e){
			System.out.println("checkUname failed in closing the handle.");
			System.out.println(e.toString());
		}

		return userExist;
	}
	
	public UserBean getUsreByName(String uname){
		UserBean userBean = null;
		String queryText = ""; 
		PreparedStatement querySt = null; 
		ResultSet results = null; 
		
		queryText = 
				"Select * "
			+   "From citrus_user "
			+	"Where uname = ?";
		
		try {
			//prepare
			querySt = db_connection.prepareStatement(queryText);
			
			//execute
			querySt.setString(1, uname); 
			results = querySt.executeQuery();
			
			//check results
			if(results.next()){
				Integer id = results.getInt("uid");
				String name = results.getString("uname");
				Timestamp lastactive = results.getTimestamp("ulastactive");
				
				userBean = new UserBean(id, name, lastactive);
			}
			
			//close
			results.close();
			querySt.close();
			
		} catch (SQLException e) {
			System.out.println("UserDAO getUsreByName failed.");	
			System.out.println(e.toString());
		}
		
		
		return userBean;
	}
	
	public UserBean getUserByID(int uid){
		UserBean user = null;
		String queryText = ""; //SQL TEXT
		PreparedStatement querySt = null; // the query handle
		ResultSet results = null; // a cursor
		
		queryText = 
				"Select * "
			+   "From citrus_user "
			+	"Where uid = ?";
		
		//prepare the query
		try{
			querySt = db_connection.prepareStatement(queryText);
		}catch(SQLException e){
			System.out.println("getUserByID failed in preparation.");
			System.out.println(e.toString());
			
		}
		
		// execute the query
		try{
			querySt.setInt(1, uid); // set the place holder
			results = querySt.executeQuery();
		}catch(SQLException e){
			System.out.println("getUserByID failed in execute.");
			System.out.println(e.toString());
			
		}
		
		// any results?
		try{
			while(results.next()){
				Integer id = results.getInt("uid");
				String name = results.getString("uname");
				//String password = results.getString("upassword");
				Timestamp lastactive = results.getTimestamp("ulastactive");
				
				user = new UserBean(id, name, lastactive);
			}
			
			
		}catch(SQLException e){
			System.out.println("getUserByID failed in cursor.");
			System.out.println(e.toString());
			
		}
		
		// close the cursor
		try{
			results.close();
		}catch(SQLException e){
			System.out.println("getUserByID failed in closing cursor.");
			System.out.println(e.toString());
			
		}
		
		// close the handle
		try{
			querySt.close();
		}catch(SQLException e){
			System.out.println("getUserByID failed in closing the handle.");
			System.out.println(e.toString());
			
		}
		
		return user;
		
	}
	
	public List<UserBean> retrieveAllUsers(){
		String queryText = ""; //SQL TEXT
		PreparedStatement querySt = null; // the query handle
		ResultSet results = null; // a cursor
		List<UserBean> list = new ArrayList<UserBean>();
		
		queryText = 
				"Select * "
			+   "From citrus_user "
			;
		
		//prepare the query
		try{
			querySt = db_connection.prepareStatement(queryText);
		}catch(SQLException e){
			System.out.println("User retrieveAll() failed in preparation.");
			System.out.println(e.toString());
			
		}
		
		// execute the query
		try{
			results = querySt.executeQuery();
		}catch(SQLException e){
			System.out.println("User retrieveAll() failed in execute.");
			System.out.println(e.toString());
			
		}
		
		// any results?
		try{
			while(results.next()){
				Integer id = results.getInt("uid");
				String name = results.getString("uname");
				//String password = results.getString("upassword");
				Timestamp lastactive = results.getTimestamp("ulastactive");
				
				UserBean uBean = new UserBean(id, name, lastactive);
				
				list.add(uBean);
				
			}
			
			
		}catch(SQLException e){
			System.out.println("User retrieveAll() failed in cursor.");
			System.out.println(e.toString());
			
		}
		
		// close the cursor
		try{
			results.close();
		}catch(SQLException e){
			System.out.println("User retrieveAll() failed in closing cursor.");
			System.out.println(e.toString());
			
		}
		
		// close the handle
		try{
			querySt.close();
		}catch(SQLException e){
			System.out.println("User retrieveAll() failed in closing the handle.");
			System.out.println(e.toString());
			
		}
		
		
		return list;
	}
		
	public static void main(String[] args) {
		UserDAO uDao = new UserDAO();
		//uDao.signUp(new UserBean(1, "test_u1", new Timestamp(new Date().getTime())), "test_u1pwd");
		System.out.println(uDao.signIn("test_u1", "test_u1pwd"));
	}
	
	*/
		
}
