package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.BookBean;
import bean.CategoryBean;

public class BookDAO {

	// Singleton
	/**
	 * Only one object per program.
	 */
	private static BookDAO instance = null;
	private static Connection connection = null;

	public static synchronized BookDAO getInstance()
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (instance == null) {
			instance = new BookDAO();
		}
		return instance;
	}

	// Implementation
	// Constructor
	private PreparedStatement insertBookStatement;
	private PreparedStatement getBookByIdStatement;
	private PreparedStatement getBookByCategoryStatement;
	private PreparedStatement getBookBySearchStatement;
	private PreparedStatement getBookStatement;
	private PreparedStatement getMostPopularBooksStatement;

	protected BookDAO() {
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
		

		this.insertBookStatement = connection.prepareStatement("INSERT "
				+ "INTO `citrus_db`.`citrus_book` (`bid`, `btitle`, `bprice`, `bcategory`, `bisbn`, `bdescription`, `bamount`, `bimage`) "
				+ "VALUES (NULL, ?, ?, ?, ?, ?, ?, ?);");
		this.getBookByIdStatement = connection
				.prepareStatement("SELECT " + "`bid` as 'id', `btitle` as 'title', `bprice` as 'price', "
						+ "`cid` as 'category_id', `ctitle` as 'category_title', `bisbn` as 'isbn', "
						+ "`bdescription` as 'description', `bamount` as 'amount', `bimage` as 'image', "
						+ "AVG(`cmtrate`) as 'rating', COUNT(`cmtid`) as 'number_comment' FROM `citrus_book`  "
						+ "LEFT JOIN `citrus_category` ON `citrus_book`.`bcategory` = `citrus_category`.`cid` "
						+ "LEFT JOIN `citrus_comment` ON `citrus_book`.`bid` = `citrus_comment`.`cmtbid` "
						+ "WHERE `bid` = ? GROUP BY `bid` LIMIT 1;");
		this.getBookByCategoryStatement = connection
				.prepareStatement("SELECT " + "`bid` as 'id', `btitle` as 'title', `bprice` as 'price', "
						+ "`cid` as 'category_id', `ctitle` as 'category_title', `bisbn` as 'isbn', "
						+ "`bdescription` as 'description', `bamount` as 'amount', `bimage` as 'image', "
						+ "AVG(`cmtrate`) as 'rating', COUNT(`cmtid`) as 'number_comment' FROM `citrus_book`  "
						+ "LEFT JOIN `citrus_category` ON `citrus_book`.`bcategory` = `citrus_category`.`cid` "
						+ "LEFT JOIN `citrus_comment` ON `citrus_book`.`bid` = `citrus_comment`.`cmtbid` "
						+ "WHERE `bcategory` = ? GROUP BY `bid` ORDER BY `citrus_book`.`bid` DESC LIMIT ?,?;");
		this.getBookBySearchStatement = connection
				.prepareStatement("SELECT " + "`bid` as 'id', `btitle` as 'title', `bprice` as 'price', "
						+ "`cid` as 'category_id', `ctitle` as 'category_title', `bisbn` as 'isbn', "
						+ "`bdescription` as 'description', `bamount` as 'amount', `bimage` as 'image', "
						+ "AVG(`cmtrate`) as 'rating', COUNT(`cmtid`) as 'number_comment' FROM `citrus_book`  "
						+ "LEFT JOIN `citrus_category` ON `citrus_book`.`bcategory` = `citrus_category`.`cid` "
						+ "LEFT JOIN `citrus_comment` ON `citrus_book`.`bid` = `citrus_comment`.`cmtbid` "
						+ "WHERE `btitle` LIKE ? GROUP BY `bid` ORDER BY `citrus_book`.`bid` DESC LIMIT ?,?;");
		this.getBookStatement = connection
				.prepareStatement("SELECT " + "`bid` as 'id', `btitle` as 'title', `bprice` as 'price', "
						+ "`cid` as 'category_id', `ctitle` as 'category_title', `bisbn` as 'isbn', "
						+ "`bdescription` as 'description', `bamount` as 'amount', `bimage` as 'image', "
						+ "AVG(`cmtrate`) as 'rating', COUNT(`cmtid`) as 'number_comment' FROM `citrus_book`  "
						+ "LEFT JOIN `citrus_category` ON `citrus_book`.`bcategory` = `citrus_category`.`cid` "
						+ "LEFT JOIN `citrus_comment` ON `citrus_book`.`bid` = `citrus_comment`.`cmtbid` "
						+ "GROUP BY `bid` ORDER BY `bid` DESC LIMIT ?,?;");

		this.getMostPopularBooksStatement = connection
				.prepareStatement("SELECT " + "`bid` as 'id', `btitle` as 'title', `bprice` as 'price', "
						+ "`cid` as 'category_id', `ctitle` as 'category_title', `bisbn` as 'isbn', "
						+ "`bdescription` as 'description', `bamount` as 'amount', `bimage` as 'image', "
						+ "AVG(`cmtrate`) as 'rating', COUNT(distinct `cmtid`) as 'number_comment', "
						+ "SUM(oiamount) as order_amount " + "FROM citrus_book "
						+ "LEFT JOIN citrus_category ON citrus_book.bcategory = citrus_category.cid "
						+ "LEFT JOIN citrus_comment ON citrus_book.bid = citrus_comment.cmtbid, " + "citrus_order_item "
						+ "Where citrus_book.bid=citrus_order_item.oibid " + "GROUP BY citrus_book.bid "
						+ "ORDER BY order_amount DESC, bprice DESC LIMIT ?");

	}

	/**
	 * Get the TOP #'top' popular books 
	 * @param top
	 * @return a list of BookBean, representing the most popular books
	 * @throws SQLException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public synchronized List<BookBean> getMostPopularBooks(int top) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		checkConnection();
		
		getMostPopularBooksStatement.setInt(1, top);

		ResultSet result = getMostPopularBooksStatement.executeQuery();
		List<BookBean> resultList = new ArrayList<BookBean>();
		int rank = 1;
		while (result.next()) {
			resultList.add(new BookBean(result.getInt("id"), result.getString("title"), result.getInt("price"),
					result.getInt("category_id"), result.getString("category_title"), result.getString("isbn"),
					result.getString("description"), result.getInt("amount"), result.getString("image"),
					result.getDouble("rating"), result.getInt("number_comment"), result.getInt("order_amount"),
					rank++));
		}
		result.close();

		return resultList;
	}

	
	/**
	 * Add a book into the database
	 * @param book
	 * the BookBean to be added
	 * @return number of books added
	 * @throws SQLException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public synchronized int addBook(BookBean book) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		checkConnection();
		
		// Execute
		insertBookStatement.setString(1, book.getTitle());
		insertBookStatement.setInt(2, book.getPrice());
		insertBookStatement.setInt(3, book.getCategory());
		insertBookStatement.setString(4, book.getIsbn());
		insertBookStatement.setString(5, book.getDescription());
		insertBookStatement.setInt(6, book.getAmount());
		insertBookStatement.setString(7, book.getImage());
		return insertBookStatement.executeUpdate();
	}

	/**
	 * Get the book with given id 
	 * @param bid
	 * the book id 
	 * @return the BookBean
	 * @throws SQLException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public synchronized BookBean getBookByID(int bid) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		checkConnection();
		

		getBookByIdStatement.setInt(1, bid); // set the place holder
		ResultSet result = getBookByIdStatement.executeQuery();

		if (result.next()) {

			return new BookBean(result.getInt("id"), result.getString("title"), result.getInt("price"),
					result.getInt("category_id"), result.getString("category_title"), result.getString("isbn"),
					result.getString("description"), result.getInt("amount"), result.getString("image"),
					result.getDouble("rating"), result.getInt("number_comment"));

		} else {
			return null;
		}
	}

	/**
	 * Get books in given category
	 * @param category
	 * the category id
	 * @param offset
	 *            the first index is ZERO
	 * @param limit
	 * number of books wanted
	 * @return a list of BookBean in that category
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public synchronized List<BookBean> getBooksByCategory(int category, int offset, int limit) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		checkConnection();
		

		// Execute
		getBookByCategoryStatement.setInt(1, category);
		getBookByCategoryStatement.setInt(2, offset);
		getBookByCategoryStatement.setInt(3, limit);

		// Get Result
		ResultSet result = getBookByCategoryStatement.executeQuery();
		List<BookBean> resultList = new ArrayList<BookBean>();
		while (result.next()) {
			resultList.add(new BookBean(result.getInt("id"), result.getString("title"), result.getInt("price"),
					result.getInt("category_id"), result.getString("category_title"), result.getString("isbn"),
					result.getString("description"), result.getInt("amount"), result.getString("image"),
					result.getDouble("rating"), result.getInt("number_comment")));
		}
		result.close();

		return resultList;
	}

	public List<BookBean> getBooksByCategory(CategoryBean category, int offset, int limit) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		checkConnection();
		
		return getBooksByCategory(category.getCid(), offset, limit);
	}

	/**
	 * Search books with keyword
	 * @param keyword
	 * @param offset
	 * number of first set of books to be ignored 
	 * @param limit
	 * number of books wanted
	 * @return a list of BookBean relevant to the keyword
	 * @throws SQLException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public synchronized List<BookBean> getBooksBySearch(String keyword, int offset, int limit) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		checkConnection();
		
		// Execute
		getBookBySearchStatement.setString(1, "%" + keyword + "%");
		getBookBySearchStatement.setInt(2, offset);
		getBookBySearchStatement.setInt(3, limit);

		// Get Result
		ResultSet result = getBookBySearchStatement.executeQuery();
		List<BookBean> resultList = new ArrayList<BookBean>();
		while (result.next()) {
			resultList.add(new BookBean(result.getInt("id"), result.getString("title"), result.getInt("price"),
					result.getInt("category_id"), result.getString("category_title"), result.getString("isbn"),
					result.getString("description"), result.getInt("amount"), result.getString("image"),
					result.getDouble("rating"), result.getInt("number_comment")));
		}
		result.close();

		return resultList;
	}

	/**
	 * Get books in database
	 * @param offset
	 * @param limit
	 *            the first index is ZERO
	 *            number of books wanted
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public synchronized List<BookBean> getBook(int offset, int limit) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		checkConnection();
		

		// Execute
		getBookStatement.setInt(1, offset);
		getBookStatement.setInt(2, limit);

		// Get Result
		ResultSet result = getBookStatement.executeQuery();
		List<BookBean> resultList = new ArrayList<BookBean>();

		while (result.next()) {
			resultList.add(new BookBean(result.getInt("id"), result.getString("title"), result.getInt("price"),
					result.getInt("category_id"), result.getString("category_title"), result.getString("isbn"),
					result.getString("description"), result.getInt("amount"), result.getString("image"),
					result.getDouble("rating"), result.getInt("number_comment")));
		}
		result.close();

		return resultList;
	}

	public static void main(String[] args) {
		
		try {
			// Test

			BookDAO test = BookDAO.getInstance();
			/*
			 * test.addBook(new BookBean("CLR22S", 666, 2, "978-0-262-03384-8",
			 * "Introduction to Algorithm2", 666, "eb4961b8-65de-4ac7-a9dc-fbaaa4d8972c"));
			 * 
			 * BookBean book = test.getBookByID(2); System.out.println(book);
			 * 
			 * List<BookBean> categoryList = test.getBooksByCategory(new CategoryBean(1), 0,
			 * 2); System.out.println(categoryList);
			 * 
			 * List<BookBean> allList = test.getBook(0, 4); System.out.println(allList);
			 */
			 List<BookBean> topList = test.getMostPopularBooks(4);
			 System.out.println(topList.size());
			 System.out.println(topList);

			// List<BookBean> monthList = test.getBookSoldInMonth(2018, 3);
			// System.out.println(monthList.size());
			// for(BookBean book: monthList) {
			// System.out.println(book);
			// }

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
