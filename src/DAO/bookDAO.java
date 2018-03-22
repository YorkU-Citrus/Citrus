package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import bean.bookBean;

public class bookDAO extends CitrusDAO {

	public bookDAO(){
		super();
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
	
	
	
	public Map<Integer, bookBean> retrieveAllBooks(){
		String queryText = ""; //SQL TEXT
		PreparedStatement querySt = null; // the query handle
		ResultSet results = null; // a cursor
		Map<Integer, bookBean> resultMap = new HashMap<Integer, bookBean>();
		
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
				
				resultMap.put(bid, bBean);
				
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
		
		
		return resultMap;
	}
}
