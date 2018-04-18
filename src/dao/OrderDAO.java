package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.OrderBean;
import bean.OrderItemBean;

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
	private PreparedStatement getItemsInOrderStatement;
	private PreparedStatement updateOrderStatement;
	private PreparedStatement placeOrderStatment;
	private PreparedStatement addOrderItemStatement;
	
	private final int batchSize = 1000;
	
	protected OrderDAO() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Connection connection = CitrusDAO.getInstance().getConnection();
		
		this.getOrderByUserStatement = connection.prepareStatement("SELECT * FROM citrus_order WHERE ouid=? ");
		this.getOrderByIdStatement = connection.prepareStatement("SELECT * FROM citrus_order WHERE oid=? ");
		this.updateOrderStatement = connection.prepareStatement("UPDATE citrus_order SET ostatus=? WHERE oid=? ");
		this.placeOrderStatment = connection.prepareStatement("INSERT INTO `citrus_order`(`oid`, `ouid`, `otime`, `ostatus`, `oprice`) "
				+ "VALUES (NULL,?,CURRENT_TIMESTAMP,'ORDERED',?)" , Statement.RETURN_GENERATED_KEYS);
		this.addOrderItemStatement = connection.prepareStatement("INSERT INTO `citrus_order_item`(`oiid`, `oioid`, `oibid`, `oiamount`) "
				+ "VALUES (NULL, ?, ?, ?)");
		this.getItemsInOrderStatement = connection.prepareStatement("SELECT * FROM citrus_order_item WHERE oioid=? ");
	}
	
	public synchronized List<OrderItemBean> getItemsInOrder(int orderId) throws SQLException	{
		getItemsInOrderStatement.setInt(1, orderId);
		ResultSet resultSet = getItemsInOrderStatement.executeQuery();
		List<OrderItemBean> list = new ArrayList<OrderItemBean>();
		
		while(resultSet.next()) {
			list.add(new OrderItemBean(resultSet.getInt("oioid"), resultSet.getInt("oibid"), resultSet.getInt("oiamount")));
		}
		
		return list;
	}
	
	
	//called by placeOrder()
	private synchronized int addOrderItems(List<OrderItemBean> itemList) throws SQLException	{
		int numItems = 0;
		
		for(OrderItemBean item: itemList) {
			addOrderItemStatement.setInt(1, item.getOrderId());
			addOrderItemStatement.setInt(2, item.getBookId());
			addOrderItemStatement.setInt(3, item.getAmount());

			addOrderItemStatement.addBatch();
			
			if(++numItems % batchSize == 0) { // avoid OutOfMemory Error
				addOrderItemStatement.executeBatch();
			}
		}
		
		addOrderItemStatement.executeBatch(); // insert remaining items
		
		return numItems;
	}
	
	public synchronized void placeOrder(OrderBean order, List<OrderItemBean> itemList) throws SQLException{
		placeOrderStatment.setInt(1, order.getUserId());
		placeOrderStatment.setInt(2, order.getTotalPrice());
		
		placeOrderStatment.executeUpdate();
		
		ResultSet orderResult = placeOrderStatment.getGeneratedKeys(); // get AUTO_INCREMENT key
		if(orderResult.next()) {
			int oid = orderResult.getInt(1);
			order.setId(oid);
			
			for(OrderItemBean item: itemList) {
				item.setOrderId(oid);
			}
			
			addOrderItems(itemList);
		}
	}
	
	public synchronized int updateOrderStatus(int orderId, String status) throws SQLException{
		updateOrderStatement.setString(1, status);
		updateOrderStatement.setInt(2, orderId);
		
		return updateOrderStatement.executeUpdate();
	}
	
	
	public synchronized OrderBean getOrderById(int orderId) throws SQLException{
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
	
	public synchronized List<OrderBean> getOrdersByUser(int userId) throws SQLException {
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
		try {
			
			
			//Delete entries in DB then try
			OrderDAO testOrder = OrderDAO.getInstance();
			List<OrderItemBean> list = new ArrayList<OrderItemBean>();
			
			OrderBean order1 = new OrderBean(3, 12354);
			OrderItemBean item1 = new OrderItemBean(1, 123);
			OrderItemBean item2 = new OrderItemBean(2, 50);
			OrderItemBean item3 = new OrderItemBean(4, 4);
			list.add(item1); list.add(item2); list.add(item3);
			
			testOrder.placeOrder(order1, list);
			
			System.out.println(testOrder.getOrdersByUser(3));
			System.out.println(testOrder.getItemsInOrder(order1.getId()));
			
			
			
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
