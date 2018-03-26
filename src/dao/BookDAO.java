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
	private PreparedStatement getBookStatement;

	protected BookDAO() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Connection connection = CitrusDAO.getInstance().getConnection();
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
		this.getBookStatement = connection
				.prepareStatement("SELECT " + "`bid` as 'id', `btitle` as 'title', `bprice` as 'price', "
						+ "`cid` as 'category_id', `ctitle` as 'category_title', `bisbn` as 'isbn', "
						+ "`bdescription` as 'description', `bamount` as 'amount', `bimage` as 'image', "
						+ "AVG(`cmtrate`) as 'rating', COUNT(`cmtid`) as 'number_comment' FROM `citrus_book`  "
						+ "LEFT JOIN `citrus_category` ON `citrus_book`.`bcategory` = `citrus_category`.`cid` "
						+ "LEFT JOIN `citrus_comment` ON `citrus_book`.`bid` = `citrus_comment`.`cmtbid` "
						+ "GROUP BY `bid` ORDER BY `bid` DESC LIMIT ?,?;");
	}

	// Insert new book
	public int addBook(BookBean book) throws SQLException {
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

	public BookBean getBookByID(int bid) throws SQLException {

		getBookByIdStatement.setInt(1, bid); // set the place holder
		ResultSet result = getBookByIdStatement.executeQuery();

		if (result.next()) {

			return new BookBean(
					result.getInt("id"), 
					result.getString("title"), 
					result.getInt("price"),
					result.getInt("category_id"), 
					result.getString("category_title"),
					result.getString("isbn"), 
					result.getString("description"),
					result.getInt("amount"), 
					result.getString("image"),
					result.getDouble("rating"),
					result.getInt("number_comment")
			);
			
		} else {
			return null;
		}
	}

	/**
	 * @param category
	 * @param offset
	 *            the first index is ZERO
	 * @param limit
	 * @return
	 * @throws SQLException
	 */
	public List<BookBean> getBooksByCategory(int category, int offset, int limit) throws SQLException {

		// Execute
		getBookByCategoryStatement.setInt(1, category);
		getBookByCategoryStatement.setInt(2, offset);
		getBookByCategoryStatement.setInt(3, limit);

		// Get Result
		ResultSet result = getBookByCategoryStatement.executeQuery();
		List<BookBean> resultList = new ArrayList<BookBean>();
		while (result.next()) {
			resultList.add(new BookBean(
					result.getInt("id"), 
					result.getString("title"), 
					result.getInt("price"),
					result.getInt("category_id"), 
					result.getString("category_title"),
					result.getString("isbn"), 
					result.getString("description"),
					result.getInt("amount"), 
					result.getString("image"),
					result.getDouble("rating"),
					result.getInt("number_comment")
			));
		}
		result.close();

		return resultList;
	}

	public List<BookBean> getBooksByCategory(CategoryBean category, int offset, int limit) throws SQLException {
		return getBooksByCategory(category.getCid(), offset, limit);
	}

	/**
	 * @param offset
	 * @param limit
	 *            the first index is ZERO
	 * @return
	 * @throws SQLException
	 */
	public List<BookBean> getBook(int offset, int limit) throws SQLException {

		// Execute
		getBookStatement.setInt(1, offset);
		getBookStatement.setInt(2, limit);

		// Get Result
		ResultSet result = getBookStatement.executeQuery();
		List<BookBean> resultList = new ArrayList<BookBean>();

		while (result.next()) {
			resultList.add(new BookBean(
					result.getInt("id"), 
					result.getString("title"), 
					result.getInt("price"),
					result.getInt("category_id"), 
					result.getString("category_title"),
					result.getString("isbn"), 
					result.getString("description"),
					result.getInt("amount"), 
					result.getString("image"),
					result.getDouble("rating"),
					result.getInt("number_comment")
			));
		}
		result.close();

		return resultList;
	}

	public static void main(String[] args) {

		try {
			// Test
			BookDAO test = BookDAO.getInstance();
			test.addBook(new BookBean("CLR22S", 666, 2, "978-0-262-03384-8", "Introduction to Algorithm2", 666, "eb4961b8-65de-4ac7-a9dc-fbaaa4d8972c"));

			BookBean book = test.getBookByID(2);
			System.out.println(book);

			List<BookBean> categoryList = test.getBooksByCategory(new CategoryBean(1), 0, 2);
			System.out.println(categoryList);

			List<BookBean> allList = test.getBook(0, 4);
			System.out.println(allList);

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