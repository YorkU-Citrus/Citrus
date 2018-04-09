package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;



import bean.UserBean;
import security.Encryption;

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
	private PreparedStatement updateUserStatement;
	
	protected UserDAO() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Connection connection = CitrusDAO.getInstance().getConnection();
		
		//updated, add salt
		this.insertUserStatement = connection.prepareStatement("INSERT "
				+ "INTO `citrus_db`.`citrus_user` (`uid`, `uname`, `upassword`, `usalt`, `ulastactive`) "
				+ "VALUES (NULL, ?, ?, ?, CURRENT_TIMESTAMP);");
		
		//updated, add salt
		this.getUserByNameStatement = connection.prepareStatement("SELECT `uid` as 'id', `uname` as 'name', `upassword` as 'password',  `usalt` as 'salt', `ulastactive` as 'lastactive' "
				+ "FROM `citrus_db`.`citrus_user` "
				+ "WHERE `uname` = ?; ");
		
		//updated, get back salt from database, then verify
		this.signInStatement = connection.prepareStatement("SELECT * "
				+ "From `citrus_db`.`citrus_user` "
				+ "WHERE `uname`= ? ");
		
		//updated, add salt
		this.updateUserStatement = connection.prepareStatement("UPDATE `citrus_db`.`citrus_user` "
				+ "SET `upassword`=?,  'usalt'=?, `ulastactive`=? "
				+ "WHERE `uname`=?; ");
		
	}
	
	//change password, refresh last active timestamp
	public int updateUser(UserBean user) throws SQLException{
		updateUserStatement.setString(1, user.getHashedPassword());
		updateUserStatement.setString(2, user.getSalt());
		updateUserStatement.setTimestamp(3, user.getUlastactive());
		updateUserStatement.setString(4, user.getUname());
		
		
		return updateUserStatement.executeUpdate();
	}
	
	//get back salt from database, then verify
	public boolean signIn(String userName, String userPassword)  throws SQLException{
		signInStatement.setString(1, userName);
		//signInStatement.setString(2, user.getHashedPassword());
		ResultSet result = signInStatement.executeQuery();
		
		if(result.next()) {
			
			return result.getString("upassword").equals(
					Encryption.getHashedPassword(userPassword, result.getString("usalt"))
					);
			
		}
		
		return false;
	}
	
	//Insert user
	public int addUser(UserBean user) throws SQLException{
		
		insertUserStatement.setString(1, user.getUname());
		insertUserStatement.setString(2, user.getHashedPassword());
		insertUserStatement.setString(3, user.getSalt());
		
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
					result.getString("salt"),
					result.getTimestamp("lastactive")
			);
			
		} else {
			return null;
		}
		
	
	}
	
	public static void main(String[] args) {

		try {
			UserDAO test = UserDAO.getInstance();
			
			//System.out.println("add test_u3");
			//test.addUser(new UserBean("test_u3", "test_u3pwd".hashCode()+"",new Timestamp(new Date().getTime())));
			//UserBean user3 = test.getUserByName("test_u3");
			//System.out.println("User3: " + user3);
			
			UserBean t3 = new UserBean("test_u3", "test_u3pwd");
			UserBean t4 = new UserBean("test_u4", "test_u4pwd");
			
			System.out.println("add user3: " + test.addUser(t3));
			System.out.println("add user4: " + test.addUser(t4));
			
			System.out.println("sign in with name=test_u3 and password=test_u3pwd: " + test.signIn("test_u3", "test_u3pwd"));
			System.out.println("sign in with name=test_u3 and password=wrong_u3pwd: " + test.signIn("test_u3", "wrong_u3pwd"));
			
			
			
			//System.out.println("Updated User3: " + test.getUserByName("test_u3"));


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
		
}
