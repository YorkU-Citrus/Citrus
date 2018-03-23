package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;
import org.mariadb.jdbc.Driver;

public class CitrusDAO {
	Connection conDB;    // Connection to the database system.
	private String url;  // URL: Which database?
	
	
	//Constructor
	public CitrusDAO(){
		// Set up the DB connection.
		try {
		    // Register the driver with DriverManager.
			Class.forName("org.mariadb.jdbc.Driver").newInstance();
		} catch (ClassNotFoundException e) {
			System.out.println("What happened, cant find DriverManager");
			e.printStackTrace();
			
		} catch (InstantiationException e) {
			e.printStackTrace();
			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			
		}
				
		// url: which database
		url = "jdbc:mariadb://yukikaze.yuri.moe:3366/citrus_db";
				
		// initialize the connection
		try {
			// Connect with a fall-thru id & password
			conDB = DriverManager.getConnection(url, "citrus_db", "PGDHXSYjY2CMhDAh");
		} catch(SQLException e) {
			System.out.print("\nSQL: database connection error.\n");
		    System.out.println(e.toString());
			
		}   
				
				
		// turn on auto commit
		try{
			conDB.setAutoCommit(true);
		}catch(SQLException e){
			System.out.print("\nFailed trying to turn autocommit on.\n");
			e.printStackTrace();
			
		}
		
		
	}
	
	/**
	 * Execute a database query on citrus_db
	 * Return a list, each element in the list is a hashMap representing one row in the query results
	 * Entry in HashMap: <String, Object>
	 * THIS METHOD SHOULD BE YOUR LAST CHOICE!
	 * @param sql - The sql text you want to execute
	 */
	public List<Object> citrusQuery(String sql){
		String queryText = sql; // query text
		PreparedStatement querySt = null; // the query handle
		ResultSet results = null; // a cursor
		ResultSetMetaData md = null;
		int columnCount = -1 ;
		List list = new ArrayList();
		
		
		//prepare the query
		try{
			querySt = conDB.prepareStatement(queryText);
		}catch(SQLException e){
			System.out.println("SQL#1 failed in preparation.");
			System.out.println(e.toString());
			
		}
		
		// execute the query
		try{
			results = querySt.executeQuery();
			md = results.getMetaData();
			columnCount = md.getColumnCount(); // get # of columns
		}catch(SQLException e){
			System.out.println("citrusQuery failed in execute.");
			System.out.println(e.toString());
			
		}
		
		// any results?
		try{
			
			while(results.next()){
				Map rowData = new HashMap();
				for (int i = 1; i <= columnCount; i++) {
					rowData.put(md.getColumnName(i), results.getObject(i));
				}
				
				list.add(rowData);
			}

		}catch(SQLException e){
			System.out.println("citrusQuery failed in cursor.");
			System.out.println(e.toString());
			
		}
		
		// close the cursor
		try{
			results.close();
		}catch(SQLException e){
			System.out.println("citrusQuery failed in closing cursor.");
			System.out.println(e.toString());
			
		}
		
		// close the handle
		try{
			querySt.close();
		}catch(SQLException e){
			System.out.println("citrusQuery failed in closing the handle.");
			System.out.println(e.toString());
			
		}
		
		return list;
	}
	
	/**
	 * Execute a database update on citrus_db
	 * @param sql - The sql text you want to execute
	 */
	public void citrusUpdate(String sql){
		String queryText = sql; // query text
		PreparedStatement querySt = null; // the query handle
		
		//prepare the query
		try{
			querySt = conDB.prepareStatement(queryText);
		}catch(SQLException e){
			System.out.println("citrusUpdate failed in preparation.");
			System.out.println(e.toString());
			
		}
				
		// execute the query
		try{		
			querySt.executeUpdate();
			//conDB.commit();   // commit
			//System.out.println("update committed");
					
		}catch(SQLException e){
			System.out.println("citrusUpdate failed in execute.");
			System.out.println(e.toString());
			
		}
				
		// close the handle
		try{
			querySt.close();
		}catch(SQLException e){
			System.out.println("citrusUpdate failed in closing the handle.");
			System.out.println(e.toString());
			
		}
	}
}
