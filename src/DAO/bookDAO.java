package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import java.util.List;

import bean.bookBean;

public class bookDAO extends CitrusDAO {

	/*
	 * bookBean:
	 *  private int bid;
		private String title;
		private int price;
		private int category;
		private String isbn; 
		private String description;
		private int amount;
		private String image; // url
	 */
	public bookDAO(){
		super();
	}
	
	//insert new book
	public void addBook(bookBean bBean) {
		String queryText = ""; 
		PreparedStatement querySt = null; 
		
		queryText = "Insert "
				+ "into citrus_book "
				+ "value( ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			//prepare
			querySt = conDB.prepareStatement(queryText);
			
			//execute
			querySt.setInt(1, bBean.getBid()); 
			querySt.setString(2, bBean.getTitle()); 
			querySt.setInt(3, bBean.getPrice()); 
			querySt.setInt(4, bBean.getCategory());
			querySt.setString(5, bBean.getIsbn()); 
			querySt.setString(6, bBean.getDescription()); 
			querySt.setInt(7, bBean.getAmount());
			querySt.setString(8, bBean.getImage()); 
			querySt.executeUpdate();
			
			//close
			querySt.close();
			
		} catch (SQLException e) {
			System.out.println("bookDAO addBook failed.");	
			System.out.println(e.toString());
		}
	}
	
	public List<bookBean> getBooksByCategory(int catid){
		String queryText = ""; //SQL TEXT
		PreparedStatement querySt = null; // the query handle
		ResultSet results = null; // a cursor
		bookBean book = null;
		List<bookBean> list = new ArrayList<bookBean>();
		
		queryText = 
				"Select * "
			+   "From citrus_book "
			+	"Where bcategory = ? ";
		
		try {
			//prepare
			querySt = conDB.prepareStatement(queryText);
			
			//execute
			querySt.setInt(1, catid); 
			results = querySt.executeQuery();
			
			//check results
			while(results.next()){
				Integer bookid = results.getInt("bid");
				String title = results.getString("btitle");
				Integer price = results.getInt("bprice");
				Integer bcat = results.getInt("bcategory");
				String isbn = results.getString("bisbn");
				String dscpt = results.getString("bdescription");
				Integer amount = results.getInt("bamount");
				String image = results.getString("bimage");
				
				book = new bookBean(bookid, title, price, bcat, isbn, dscpt, amount, image);
				list.add(book);
			}
			
			//close
			results.close();
			querySt.close();
			
		} catch (SQLException e) {
			System.out.println("UserDAO signIn failed.");	
			System.out.println(e.toString());
		}
		
		return list;	
	}
	
	
	public bookBean getBookByID(int bid){
		bookBean book = null;
		String queryText = ""; //SQL TEXT
		PreparedStatement querySt = null; // the query handle
		ResultSet results = null; // a cursor
		
		queryText = 
				"Select * "
			+   "From citrus_book "
			+	"Where bid = ?";
		
		//prepare the query
		try{
			querySt = conDB.prepareStatement(queryText);
		}catch(SQLException e){
			System.out.println("getBookByID failed in preparation.");
			System.out.println(e.toString());
			
		}
		
		// execute the query
		try{
			querySt.setInt(1, bid); // set the place holder
			results = querySt.executeQuery();
		}catch(SQLException e){
			System.out.println("getBookByID failed in execute.");
			System.out.println(e.toString());
			
		}
		
		// any results?
		try{
			while(results.next()){
				Integer bookid = results.getInt("bid");
				String title = results.getString("btitle");
				Integer price = results.getInt("bprice");
				Integer bcat = results.getInt("bcategory");
				String isbn = results.getString("bisbn");
				String dscpt = results.getString("bdescription");
				Integer amount = results.getInt("bamount");
				String image = results.getString("bimage");
				
				book = new bookBean(bid, title, price, bcat, isbn, dscpt, amount, image);
			}
			
			
		}catch(SQLException e){
			System.out.println("getBookByID failed in cursor.");
			System.out.println(e.toString());
			
		}
		
		// close the cursor
		try{
			results.close();
		}catch(SQLException e){
			System.out.println("getBookByID failed in closing cursor.");
			System.out.println(e.toString());
			
		}
		
		// close the handle
		try{
			querySt.close();
		}catch(SQLException e){
			System.out.println("getBookByID failed in closing the handle.");
			System.out.println(e.toString());
			
		}
		
		return book;
	}
	
	
	public List<bookBean> retrieveAllBooks(){
		String queryText = ""; //SQL TEXT
		PreparedStatement querySt = null; // the query handle
		ResultSet results = null; // a cursor
		List<bookBean> list = new ArrayList<bookBean>();
		
		queryText = 
				"Select * "
			+   "From citrus_book "
			;
		
		//prepare the query
		try{
			querySt = conDB.prepareStatement(queryText);
		}catch(SQLException e){
			System.out.println("retrieveAllBooks() failed in preparation.");
			System.out.println(e.toString());
			
		}
		
		// execute the query
		try{
			results = querySt.executeQuery();
		}catch(SQLException e){
			System.out.println("retrieveAllBooks() failed in execute.");
			System.out.println(e.toString());
			
		}
		
		// any results?
		try{
			while(results.next()){
				Integer bid = results.getInt("bid");
				String title = results.getString("btitle");
				Integer price = results.getInt("bprice");
				Integer bcat = results.getInt("bcategory");
				String isbn = results.getString("bisbn");
				String dscpt = results.getString("bdescription");
				Integer amount = results.getInt("bamount");
				String image = results.getString("bimage");
				
				bookBean bBean = new bookBean(bid, title, price, bcat, isbn, dscpt, amount, image);
				
				list.add(bBean);
				
			}
			
			
		}catch(SQLException e){
			System.out.println("retrieveAllBooks() failed in cursor.");
			System.out.println(e.toString());
			
		}
		
		// close the cursor
		try{
			results.close();
		}catch(SQLException e){
			System.out.println("retrieveAllBooks() failed in closing cursor.");
			System.out.println(e.toString());
			
		}
		
		// close the handle
		try{
			querySt.close();
		}catch(SQLException e){
			System.out.println("retrieveAllBooks() failed in closing the handle.");
			System.out.println(e.toString());
			
		}
		
		
		return list;
	}
	
	public static void main(String[] args) {
		bookDAO bDao = new bookDAO();
		bookBean book1 = new bookBean(1, "test_book1", 10, 1, "isbn1", "book1", 32, "url1");
		bookBean book2 = new bookBean(2, "test_book2", 111, 10, "isbn2", "book2", 32, "url2");
		bookBean book3 = new bookBean(0, "test_book3", 1, 110, "isbn3", "book3", 3, "url3");
		//bDao.addBook(book1);
		//bDao.addBook(book2);
		//bDao.addBook(book3);
		System.out.println(bDao.retrieveAllBooks().size());
	}
}
