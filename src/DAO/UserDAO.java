package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

import bean.UserBean;
import bean.bookBean;
import sun.security.util.Password;

public class UserDAO extends CitrusDAO{

	public UserDAO(){
		super();
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
			querySt = conDB.prepareStatement(queryText);
		}catch(SQLException e){
			System.out.println("getUserByID failed in preparation.");
			System.out.println(e.toString());
			System.exit(0);
		}
		
		// execute the query
		try{
			querySt.setInt(1, uid); // set the place holder
			results = querySt.executeQuery();
		}catch(SQLException e){
			System.out.println("getUserByID failed in execute.");
			System.out.println(e.toString());
			System.exit(0);
		}
		
		// any results?
		try{
			while(results.next()){
				Integer id = results.getInt("uid");
				String name = results.getString("uname");
				String password = results.getString("upassword");
				Timestamp lastactive = results.getTimestamp("ulastactive");
				
				user = new UserBean(id, name, password, lastactive);
			}
			
			
		}catch(SQLException e){
			System.out.println("getUserByID failed in cursor.");
			System.out.println(e.toString());
			System.exit(0);
		}
		
		// close the cursor
		try{
			results.close();
		}catch(SQLException e){
			System.out.println("getUserByID failed in closing cursor.");
			System.out.println(e.toString());
			System.exit(0);
		}
		
		// close the handle
		try{
			querySt.close();
		}catch(SQLException e){
			System.out.println("getUserByID failed in closing the handle.");
			System.out.println(e.toString());
			System.exit(0);
		}
		
		return user;
		
	}
	
	public Map<Integer, UserBean> retrieveAllUsers(){
		String queryText = ""; //SQL TEXT
		PreparedStatement querySt = null; // the query handle
		ResultSet results = null; // a cursor
		Map<Integer, UserBean> resultMap = new HashMap<Integer, UserBean>();
		
		queryText = 
				"Select * "
			+   "From citrus_user "
			;
		
		//prepare the query
		try{
			querySt = conDB.prepareStatement(queryText);
		}catch(SQLException e){
			System.out.println("User retrieveAll() failed in preparation.");
			System.out.println(e.toString());
			System.exit(0);
		}
		
		// execute the query
		try{
			results = querySt.executeQuery();
		}catch(SQLException e){
			System.out.println("User retrieveAll() failed in execute.");
			System.out.println(e.toString());
			System.exit(0);
		}
		
		// any results?
		try{
			while(results.next()){
				Integer id = results.getInt("uid");
				String name = results.getString("uname");
				String password = results.getString("upassword");
				Timestamp lastactive = results.getTimestamp("ulastactive");
				
				UserBean uBean = new UserBean(id, name, password, lastactive);
				
				resultMap.put(id, uBean);
				
			}
			
			
		}catch(SQLException e){
			System.out.println("User retrieveAll() failed in cursor.");
			System.out.println(e.toString());
			System.exit(0);
		}
		
		// close the cursor
		try{
			results.close();
		}catch(SQLException e){
			System.out.println("User retrieveAll() failed in closing cursor.");
			System.out.println(e.toString());
			System.exit(0);
		}
		
		// close the handle
		try{
			querySt.close();
		}catch(SQLException e){
			System.out.println("User retrieveAll() failed in closing the handle.");
			System.out.println(e.toString());
			System.exit(0);
		}
		
		
		return resultMap;
	}
		
		
}
