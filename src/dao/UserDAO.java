package dao;

public class UserDAO{

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
