package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.OrderBean;

public class OrderDAO {
	private static OrderDAO instance = null;
	
	public static synchronized OrderDAO getInstance()
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (instance == null) {
			instance = new OrderDAO();
		}
		return instance;
	}
	
	private PreparedStatement getOrderByUserStatement;
	private PreparedStatement getOrderByIdStatement;
	private PreparedStatement updateOrderStatement;
	private PreparedStatement placeOrderStatment;
	
	protected OrderDAO() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Connection connection = CitrusDAO.getInstance().getConnection();
		
		this.getOrderByUserStatement = connection.prepareStatement("SELECT * FROM citrus_order WHERE ouid=? ;");
		this.getOrderByIdStatement = connection.prepareStatement("SELECT * FROM citrus_order WHERE oid=? ;");
		this.updateOrderStatement = connection.prepareStatement("UPDATE citrus_order SET ostatus=? WHERE oid=?; ");
		this.placeOrderStatment = connection.prepareStatement("INSERT INTO `citrus_order`(`oid`, `ouid`, `otime`, `ostatus`, `oprice`) "
				+ "VALUES (NULL,?,CURRENT_TIMESTAMP,'PROCESSED',?)");
	}
	
	public int placeOrder(OrderBean order) throws SQLException{
		placeOrderStatment.setInt(1, order.getUserId());
		placeOrderStatment.setInt(2, order.getTotalPrice());
		
		return placeOrderStatment.executeUpdate();
	}
	
	public int updateOrderStatus(int orderId, String status) throws SQLException{
		updateOrderStatement.setString(1, status);
		updateOrderStatement.setInt(2, orderId);
		
		return updateOrderStatement.executeUpdate();
	}
	
	
	public OrderBean getOrderById(int orderId) throws SQLException{
		getOrderByIdStatement.setInt(1, orderId);
		ResultSet result = getOrderByIdStatement.executeQuery();
		
		if(result.next()) {
			return new OrderBean(
					result.getInt("oid"), 
					result.getInt("ouid"), 
					result.getTimestamp("otime"), 
					result.getString("ostatus"), 
					result.getInt("oprice"));
		}
		else {
			return null;
		}
	}
	
	public List<OrderBean> getOrdersByUser(int userId) throws SQLException {
		getOrderByUserStatement.setInt(1, userId);
		List<OrderBean> list = new ArrayList<OrderBean>();
		ResultSet resultSet = getOrderByUserStatement.executeQuery();
		
		while(resultSet.next()) {
			list.add(new OrderBean(
					resultSet.getInt("oid"), 
					resultSet.getInt("ouid"), 
					resultSet.getTimestamp("otime"), 
					resultSet.getString("ostatus"), 
					resultSet.getInt("oprice")));
		}
		
		resultSet.close();
		return list;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
