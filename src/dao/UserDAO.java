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
	private PreparedStatement updateUserStatement;
	
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
		
		this.updateUserStatement = connection.prepareStatement("UPDATE `citrus_db`.`citrus_user` "
				+ "SET `upassword`=?, `ulastactive`=? "
				+ "WHERE `uname`=?; ");
		
	}
	
	//change password, refresh last active timestamp
	public int updateUser(UserBean user) throws SQLException{
		updateUserStatement.setString(1, user.getHashedPassword());
		updateUserStatement.setTimestamp(2, user.getUlastactive());
		updateUserStatement.setString(3, user.getUname());
		
		
		return updateUserStatement.executeUpdate();
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
			
			//System.out.println("add test_u3");
			//test.addUser(new UserBean("test_u3", "test_u3pwd".hashCode()+"",new Timestamp(new Date().getTime())));
			UserBean user3 = test.getUserByName("test_u3");
			System.out.println("User3: " + user3);
			
			UserBean t1 = new UserBean("test_u3", "test_u3pwd".hashCode()+"", new Timestamp(new Date().getTime()));
			UserBean t2 = new UserBean("test_u3", "wrong_u3pwd".hashCode()+"", new Timestamp(new Date().getTime()));
			System.out.println("sign in with name=test_u3 and password=test_u3pwd: " + test.signIn(t1));
			System.out.println("sign in with name=test_u3 and password=wrong_u3pwd: " + test.signIn(t2));
			
			UserBean newUser3 = new UserBean(user3.getUname(), "new_u3pwd".hashCode()+"", new Timestamp(new Date().getTime()));
			test.updateUser(newUser3);
			System.out.println("Updated User3: " + test.getUserByName("test_u3"));


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
