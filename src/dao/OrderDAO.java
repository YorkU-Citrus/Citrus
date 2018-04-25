package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.BookBean;
import bean.OrderBean;
import bean.OrderItemBean;
import bean.UserStatisticBean;

/**
 * @author lover
 *
 */
public class OrderDAO {
	private static OrderDAO instance = null;
	private static Connection connection = null;

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
	private PreparedStatement getBookSoldInMonthStatement;
	private PreparedStatement getStatisticStatement;
	private PreparedStatement getAllOrdersStatement;
	private PreparedStatement getOrderByBookStatement;

	private final int batchSize = 1000;

	protected OrderDAO() {
	}

	/**
	 * Check if the connection is valid; if not, get a new connection
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void checkConnection()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		if (connection == null || (!connection.isValid(0))) {
			connection = CitrusDAO.getInstance().getConnection();
		}
		this.getOrderByUserStatement = connection.prepareStatement("SELECT * FROM citrus_order WHERE ouid=? ");
		this.getOrderByIdStatement = connection.prepareStatement("SELECT * FROM citrus_order WHERE oid=? ");
		this.updateOrderStatement = connection.prepareStatement("UPDATE citrus_order SET ostatus=? WHERE oid=? ");
		this.placeOrderStatment = connection
				.prepareStatement("INSERT INTO `citrus_order`(`oid`, `ouid`, `otime`, `ostatus`, `oprice`) "
						+ "VALUES (NULL,?,CURRENT_TIMESTAMP,'ORDERED',?)", Statement.RETURN_GENERATED_KEYS);
		this.addOrderItemStatement = connection.prepareStatement(
				"INSERT INTO `citrus_order_item`(`oiid`, `oioid`, `oibid`, `oiamount`) " + "VALUES (NULL, ?, ?, ?)");
		this.getItemsInOrderStatement = connection.prepareStatement("SELECT * FROM citrus_order_item WHERE oioid=? ");

		this.getBookSoldInMonthStatement = connection.prepareStatement("SELECT "
				+ "`bid` as 'id', `btitle` as 'title', `bprice` as 'price', "
				+ "`cid` as 'category_id', `ctitle` as 'category_title', `bisbn` as 'isbn', "
				+ "`bdescription` as 'description', `bamount` as 'amount', `bimage` as 'image', "
				+ "AVG(`cmtrate`) as 'rating', COUNT(`cmtid`) as 'number_comment', " + "SUM(oiamount) as order_amount, "
				+ "YEAR(citrus_order.otime) as year, " + "MONTH(citrus_order.otime) as month " + "FROM citrus_book "
				+ "LEFT JOIN citrus_category ON citrus_book.bcategory = citrus_category.cid "
				+ "LEFT JOIN citrus_comment ON citrus_book.bid = citrus_comment.cmtbid, "
				+ "citrus_order_item, citrus_order " + "Where citrus_book.bid=citrus_order_item.oibid "
				+ "AND citrus_order.oid=citrus_order_item.oioid " + "AND YEAR(citrus_order.otime)=? "
				+ "AND MONTH(citrus_order.otime)=? "
				+ "GROUP BY citrus_book.bid, YEAR(citrus_order.otime), MONTH(citrus_order.otime) ");

		this.getStatisticStatement = connection.prepareStatement(
				"SELECT uname, temp.sazip as zip, COUNT(oid) as order_amount, SUM(oprice) as total_consumption "
						+ "FROM citrus_user, citrus_order, citrus_shipping_address, "
						+ "(SELECT SA.sauid , SA.sazip  FROM citrus_shipping_address SA WHERE SA.satime >= "
						+ "(SELECT MAX(SA2.satime) FROM citrus_shipping_address SA2 WHERE SA.sauid=SA2.sauid)) temp "
						+ "WHERE citrus_user.uid=temp.sauid " + "AND citrus_user.uid=citrus_order.ouid "
						+ "AND citrus_shipping_address.sauid=citrus_user.uid " + "GROUP BY uid, uname, temp.sazip ");
		this.getAllOrdersStatement = connection.prepareStatement("SELECT * FROM "
				+ "(SELECT * FROM `citrus_order` ORDER BY `oid` DESC LIMIT ?,?) as subq "
				+ "LEFT JOIN `citrus_order_item` ON `oid` = `oioid` " + "LEFT JOIN `citrus_book` ON `oibid` = `bid` "
				+ "LEFT JOIN `citrus_category` ON `bcategory` = `cid` " + "ORDER BY `oid` DESC " + "");
		
		this.getOrderByBookStatement = connection.prepareStatement("SELECT * "
				+ "FROM citrus_order, citrus_order_item "
				+ "WHERE citrus_order.oid=citrus_order_item.oioid AND citrus_order_item.oibid=? "
				+ "GROUP BY citrus_order.oid, citrus_order.ouid, citrus_order.otime, citrus_order.ostatus, citrus_order.oprice "
				+ "ORDER BY otime DESC "
				+ "LIMIT ?,? ");
	}

	
	
	/**
	 * Get the buyer statistics for analysis
	 * @return a list of UserStatisticBean 
	 * @throws SQLException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public synchronized List<UserStatisticBean> getBuyerStatistic()
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		checkConnection();

		ResultSet results = getStatisticStatement.executeQuery();
		List<UserStatisticBean> list = new ArrayList<UserStatisticBean>();

		while (results.next()) {
			list.add(new UserStatisticBean(results.getString("uname"), results.getString("zip"),
					results.getInt("order_amount"), results.getInt("total_consumption")));
		}

		return list;
	}

	
	/**
	 * Get the books sold in given month
	 * @param year 
	 * @param month
	 * @return a list of books sold in given month
	 * @throws SQLException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public synchronized List<BookBean> getBookSoldInMonth(int year, int month)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		checkConnection();

		getBookSoldInMonthStatement.setInt(1, year);
		getBookSoldInMonthStatement.setInt(2, month);

		ResultSet result = getBookSoldInMonthStatement.executeQuery();
		List<BookBean> resultList = new ArrayList<BookBean>();
		while (result.next()) {
			resultList.add(new BookBean(result.getInt("id"), result.getString("title"), result.getInt("price"),
					result.getInt("category_id"), result.getString("category_title"), result.getString("isbn"),
					result.getString("description"), result.getInt("amount"), result.getString("image"),
					result.getDouble("rating"), result.getInt("number_comment"), result.getInt("order_amount")));
		}
		result.close();

		return resultList;
	}

	/**
	 * Get all the books in an order
	 * @param orderId
	 * @return a list of OrderItemBean
	 * @throws SQLException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public synchronized List<OrderItemBean> getItemsInOrder(int orderId)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		checkConnection();

		getItemsInOrderStatement.setInt(1, orderId);
		ResultSet resultSet = getItemsInOrderStatement.executeQuery();
		List<OrderItemBean> list = new ArrayList<OrderItemBean>();

		while (resultSet.next()) {
			list.add(new OrderItemBean(resultSet.getInt("oioid"), resultSet.getInt("oibid"),
					resultSet.getInt("oiamount")));
		}

		return list;
	}

	// called by placeOrder()
	private synchronized int addOrderItems(List<OrderItemBean> itemList)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		checkConnection();

		int numItems = 0;

		for (OrderItemBean item : itemList) {
			addOrderItemStatement.setInt(1, item.getOrderId());
			addOrderItemStatement.setInt(2, item.getBookId());
			addOrderItemStatement.setInt(3, item.getAmount());

			addOrderItemStatement.addBatch();

			if (++numItems % batchSize == 0) { // avoid OutOfMemory Error
				addOrderItemStatement.executeBatch();
			}
		}

		addOrderItemStatement.executeBatch(); // insert remaining items

		return numItems;
	}

	/**
	 * Place an order, status = "ordered"
	 * @param order
	 * an OrderBean to be added into database
	 * @param itemList
	 * a list of OrderItemBean
	 * @throws SQLException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public synchronized void placeOrder(OrderBean order, List<OrderItemBean> itemList)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		checkConnection();

		placeOrderStatment.setInt(1, order.getUserId());
		placeOrderStatment.setInt(2, order.getTotalPrice());

		placeOrderStatment.executeUpdate();

		ResultSet orderResult = placeOrderStatment.getGeneratedKeys(); // get AUTO_INCREMENT key
		if (orderResult.next()) {
			int oid = orderResult.getInt(1);
			order.setId(oid);

			for (OrderItemBean item : itemList) {
				if (item.getAmount() == 0) {
					continue;
				}
				item.setOrderId(oid);
			}

			addOrderItems(itemList);
		}
	}

	/**
	 * Update order status
	 * @param orderId
	 * @param status
	 * the new status of the order
	 * @return number of updated orders
	 * @throws SQLException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public synchronized int updateOrderStatus(int orderId, String status)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		checkConnection();

		updateOrderStatement.setString(1, status);
		updateOrderStatement.setInt(2, orderId);

		return updateOrderStatement.executeUpdate();
	}

	/**
	 * Get the order with given order id
	 * @param orderId
	 * @return the OrderBean
	 * @throws SQLException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public synchronized OrderBean getOrderById(int orderId)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		checkConnection();

		getOrderByIdStatement.setInt(1, orderId);
		ResultSet result = getOrderByIdStatement.executeQuery();

		if (result.next()) {
			return new OrderBean(result.getInt("oid"), result.getInt("ouid"), result.getTimestamp("otime"),
					result.getString("ostatus"), result.getInt("oprice"));
		} else {
			return null;
		}
	}

	/**
	 * Get all the orders made by the given user
	 * @param userId
	 * @return a list of OrderBean
	 * @throws SQLException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public synchronized List<OrderBean> getOrdersByUser(int userId)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		checkConnection();

		getOrderByUserStatement.setInt(1, userId);
		List<OrderBean> list = new ArrayList<OrderBean>();
		ResultSet resultSet = getOrderByUserStatement.executeQuery();

		while (resultSet.next()) {
			list.add(new OrderBean(resultSet.getInt("oid"), resultSet.getInt("ouid"), resultSet.getTimestamp("otime"),
					resultSet.getString("ostatus"), resultSet.getInt("oprice")));
		}

		resultSet.close();
		return list;
	}
	
	/**
	 * Get 'limit' number of orders with 'offset' , ordered by time 
	 * @param offset
	 * number of orders to be ignored
	 * @param limit
	 * number of orders wanted
	 * @return a list of OrderBean
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public synchronized List<OrderBean> getAllOrders(long offset, long limit) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		checkConnection();
		
		getAllOrdersStatement.setLong(1, offset);
		getAllOrdersStatement.setLong(2, limit);
		
		List<OrderBean> list = new ArrayList<OrderBean>();
		
		ResultSet resultSet= getAllOrdersStatement.executeQuery();
		
		Integer previousId = null;
		OrderBean oBean = null;
		while (resultSet.next()) {
			if ((previousId == null) || (resultSet.getInt("oid") != previousId)) {
				previousId = resultSet.getInt("oid");
				if (oBean != null) {
					list.add(oBean);
				}
				oBean = new OrderBean(
						resultSet.getInt("oid"), 
						resultSet.getInt("ouid"), 
						resultSet.getTimestamp("otime"), 
						resultSet.getString("ostatus"), 
						resultSet.getInt("oprice"));
			}

			BookBean bb = new BookBean(
					resultSet.getInt("bid"), 
					resultSet.getString("btitle"), 
					resultSet.getInt("bprice"),
					resultSet.getString("bisbn")
					);
			OrderItemBean oib = new OrderItemBean(bb, resultSet.getInt("oiamount"));
			oBean.appendList(oib);
		}
		resultSet.close();
		return list;
	}
	
	/**
	 * Get all the orders containing a certain book
	 * @param bookId
	 * @param offset
	 * number of orders to be ignored
	 * @param limit
	 * number of orders wanted
	 * @return a list of OrderBean
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public synchronized List<OrderBean> getOrderByBookId(int bookId, long offset, long limit) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		checkConnection();
		
		List<OrderBean> list = new ArrayList<OrderBean>();
		
		getOrderByBookStatement.setInt(1, bookId);
		getOrderByBookStatement.setLong(2, offset);
		getOrderByBookStatement.setLong(3, limit);
		
		ResultSet results = getOrderByBookStatement.executeQuery();
		
		while(results.next()) {
			list.add(new OrderBean(results.getInt("oid"), results.getInt("ouid"), results.getTimestamp("otime"),
					results.getString("ostatus"), results.getInt("oprice")));
		}
		results.close();
		return list;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {

			// Delete entries in DB then try
			OrderDAO testOrder = OrderDAO.getInstance();
			/*
			 * List<OrderItemBean> list = new ArrayList<OrderItemBean>();
			 * 
			 * OrderBean order1 = new OrderBean(3, 12354); OrderItemBean item1 = new
			 * OrderItemBean(1, 123); OrderItemBean item2 = new OrderItemBean(2, 50);
			 * OrderItemBean item3 = new OrderItemBean(4, 4); list.add(item1);
			 * list.add(item2); list.add(item3);
			 * 
			 * testOrder.placeOrder(order1, list);
			 * 
			 * //System.out.println(testOrder.getOrdersByUser(3));
			 * //System.out.println(testOrder.getItemsInOrder(order1.getId()));
			 * System.out.println(); List<BookBean> monthList =
			 * testOrder.getBookSoldInMonth(2018, 4); System.out.println(monthList.size());
			 * for(BookBean book: monthList) { System.out.println(book); }
			 */

			List<OrderBean> list = testOrder.getOrderByBookId(18, 1, 3);
			System.out.println(list.size());
			for (OrderBean u : list) {
				System.out.println(u);
			}

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
