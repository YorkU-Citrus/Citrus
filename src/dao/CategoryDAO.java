package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.CategoryBean;


public class CategoryDAO {

	private static CategoryDAO instance = null;
	private static Connection connection = null;
	
	public static synchronized CategoryDAO getInstance()
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (instance == null) {
			instance = new CategoryDAO();
		}
		return instance;
	}
	
	private PreparedStatement getCategoryByIdStatement;
	private PreparedStatement getCategoryStatement;
	
	protected CategoryDAO() {}
	
	public void checkConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		if (connection == null || (!connection.isValid(0))) {
			connection = CitrusDAO.getInstance().getConnection();
		}
		
		this.getCategoryByIdStatement = connection.prepareStatement("SELECT * FROM citrus_category WHERE cid=?; ");
		this.getCategoryStatement = connection.prepareStatement("SELECT * FROM citrus_category; ");
	}
	
	public synchronized CategoryBean getCategoryById(int cid) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		checkConnection();
		
		getCategoryByIdStatement.setInt(1, cid);
		ResultSet result = getCategoryByIdStatement.executeQuery();
		
		if (result.next()) {
			return new CategoryBean(
					result.getInt("cid"), 
					result.getString("ctitle")
					);
		}
		else {
			return null;
		}
		
	}
	
	public synchronized List<CategoryBean> getCategory() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		checkConnection();
		
		ResultSet resultSet = getCategoryStatement.executeQuery();
		List<CategoryBean> list = new ArrayList<CategoryBean>();
		while(resultSet.next()) {
			list.add(new CategoryBean(
					resultSet.getInt("cid"), 
					resultSet.getString("ctitle")
					)
				);
		}
		
		resultSet.close();
		return list;
	}
	
	public static void main(String[] args) {
		try {
			CategoryDAO test = CategoryDAO.getInstance();
			System.out.println("By Id: ");
			System.out.println(test.getCategoryById(1));
			System.out.println("All: ");
			System.out.println(test.getCategory());
			
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

}
