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
		this.insertBookStatement = connection.prepareStatement(
				"INSERT INTO `citrus_db`.`citrus_book` (`bid`, `btitle`, `bprice`, `bcategory`, `bisbn`, `bdescription`, `bamount`, `bimage`) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?);");
		this.getBookByIdStatement = connection.prepareStatement("SELECT * FROM `citrus_book` WHERE `bid` = ? LIMIT 1;");
		this.getBookByCategoryStatement = connection.prepareStatement(
				"SELECT * FROM `citrus_book` WHERE `bcategory` = ? ORDER BY `citrus_book`.`bid` DESC LIMIT ?,?;");
		this.getBookStatement = connection
				.prepareStatement("SELECT * FROM `citrus_book`ORDER BY `citrus_book`.`bid` DESC LIMIT ?,?;");
	}

	// Insert new book
	public void addBook(BookBean book) throws SQLException {
		// Execute
		insertBookStatement.setString(1, book.getTitle());
		insertBookStatement.setInt(2, book.getPrice());
		insertBookStatement.setInt(3, book.getCategory());
		insertBookStatement.setString(4, book.getIsbn());
		insertBookStatement.setString(5, book.getDescription());
		insertBookStatement.setInt(6, book.getAmount());
		insertBookStatement.setString(7, book.getImage());
		insertBookStatement.executeUpdate();
	}

	public BookBean getBookByID(int bid) throws SQLException {

		getBookByIdStatement.setInt(1, bid); // set the place holder
		ResultSet result = getBookByIdStatement.executeQuery();

		if (result.next()) {
			Integer bookid = result.getInt("bid");
			String title = result.getString("btitle");
			Integer price = result.getInt("bprice");
			Integer bcat = result.getInt("bcategory");
			String isbn = result.getString("bisbn");
			String dscpt = result.getString("bdescription");
			Integer amount = result.getInt("bamount");
			String image = result.getString("bimage");

			return new BookBean(bookid, title, price, bcat, isbn, dscpt, amount, image);
		} else {
			return null;
		}
	}

	/**
	 * @param category
	 * @param offset the first index is ZERO
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
			Integer bookid = result.getInt("bid");
			String title = result.getString("btitle");
			Integer price = result.getInt("bprice");
			Integer bcat = result.getInt("bcategory");
			String isbn = result.getString("bisbn");
			String dscpt = result.getString("bdescription");
			Integer amount = result.getInt("bamount");
			String image = result.getString("bimage");

			resultList.add(new BookBean(bookid, title, price, bcat, isbn, dscpt, amount, image));
		}
		result.close();

		return resultList;
	}
	
	public List<BookBean> getBooksByCategory(CategoryBean category, int offset, int limit) throws SQLException {
		return getBooksByCategory(category.getCid(), offset, limit);
	}

	/**
	 * @param offset
	 * @param limit the first index is ZERO
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
			Integer bid = result.getInt("bid");
			String title = result.getString("btitle");
			Integer price = result.getInt("bprice");
			Integer bcat = result.getInt("bcategory");
			String isbn = result.getString("bisbn");
			String dscpt = result.getString("bdescription");
			Integer amount = result.getInt("bamount");
			String image = result.getString("bimage");

			resultList.add(new BookBean(bid, title, price, bcat, isbn, dscpt, amount, image));
		}
		result.close();

		return resultList;
	}

	public static void main(String[] args) {
		
		try {
			// Test
			BookDAO test = BookDAO.getInstance();
			//test.addBook(new BookBean("CLRS", 666, 2, "978-0-262-03384-8", "Introduction to Algorithm", 666, "eb4961b8-65de-4ac7-a9dc-fbaaa4d8972c"));
			
			BookBean book = test.getBookByID(2);
			System.out.println(book);
			
			List<BookBean> categoryList = test.getBooksByCategory(new CategoryBean(1), 3, 2);
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
