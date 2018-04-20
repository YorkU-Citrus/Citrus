package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.UserBean;

public class UserDAO{
	// Singleton
	/**
	 * Only one object per program.
	 */
	private static UserDAO instance = null;
	private static Connection connection = null;

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
	private PreparedStatement updateUserStatement;
	private PreparedStatement checkUserOrderBookStatement;
	
	protected UserDAO(){}
	
	public void checkConnection()  throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		if (connection == null || (!connection.isValid(0))) {
			connection = CitrusDAO.getInstance().getConnection();
		}
		
		//updated, add salt
		this.insertUserStatement = connection.prepareStatement("INSERT "
				+ "INTO `citrus_db`.`citrus_user` (`uid`, `uname`, `upassword`, `usalt`, `ulastactive`) "
				+ "VALUES (NULL, ?, ?, ?, CURRENT_TIMESTAMP);");
		
		//updated, add salt
		this.getUserByNameStatement = connection.prepareStatement("SELECT `uid` as 'id', `uname` as 'name', `upassword` as 'password',  `usalt` as 'salt', `ulastactive` as 'lastactive', `urole` as 'role' "
				+ "FROM `citrus_db`.`citrus_user` "
				+ "WHERE `uname` = ? LIMIT 1; ");
				
		//updated, add salt
		this.updateUserStatement = connection.prepareStatement("UPDATE `citrus_db`.`citrus_user` "
				+ "SET `upassword`=?,  'usalt'=?, `ulastactive`=CURRENT_TIMESTAMP "
				+ "WHERE `uname`=?; ");
		
		this.checkUserOrderBookStatement = connection.prepareStatement("SELECT * FROM `citrus_order` LEFT JOIN `citrus_order_item` ON `oid` = `oioid` WHERE `ouid` = ? AND `ostatus` = 'COMPLETED' AND `oibid` = ? LIMIT 1;");
		
	}
	
	//change password, refresh last active time stamp
	public synchronized int updateUser(UserBean user) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		checkConnection();
		
		updateUserStatement.setString(1, user.getHashedPassword());
		updateUserStatement.setString(2, user.getSalt());
		updateUserStatement.setString(3, user.getUserName());
		
		
		return updateUserStatement.executeUpdate();
	}
		
	//Insert user
	public synchronized int addUser(UserBean user) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		checkConnection();
		
		
		insertUserStatement.setString(1, user.getUserName());
		insertUserStatement.setString(2, user.getHashedPassword());
		insertUserStatement.setString(3, user.getSalt());
		
		return insertUserStatement.executeUpdate();
		
	}
	
	public synchronized UserBean getUserByName(String name) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		checkConnection();
		
		getUserByNameStatement.setString(1, name);
		ResultSet result = getUserByNameStatement.executeQuery();
		
		if (result.next()) {

			return new UserBean(
					result.getInt("id"), 
					result.getString("name"),
					result.getString("password"),
					result.getString("salt"),
					result.getTimestamp("lastactive"),
					result.getString("role")
			);
			
		} else {
			return null;
		}
		
	
	}
	
	public synchronized boolean checkUserOrderBook(int userId, int bookId) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		checkConnection();
		
		checkUserOrderBookStatement.setInt(1, userId);
		checkUserOrderBookStatement.setInt(2, bookId);
		
		ResultSet result = checkUserOrderBookStatement.executeQuery();
		if (result.next()) {
			return true;
		}else {
			return false;
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
			
			//System.out.println("sign in with name=test_u3 and password=test_u3pwd: " + test.signIn("test_u3", "test_u3pwd"));
			//System.out.println("sign in with name=test_u3 and password=wrong_u3pwd: " + test.signIn("test_u3", "wrong_u3pwd"));
			
			
			
			//System.out.println("Updated User3: " + test.getUserByName("test_u3"));


		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
		
}
