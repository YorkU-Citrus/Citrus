package DAO;

import java.sql.*;

public class CitrusDAO {
	private Connection conDB;    // Connection to the database system.
	private String url;          // URL: Which database?
	
	
	//Constructor
	public CitrusDAO(){
		// Set up the DB connection.
		try {
		    // Register the driver with DriverManager.
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (ClassNotFoundException e) {
			System.out.println("What happened, cant find DriverManager");
			e.printStackTrace();
			System.exit(0);
		} catch (InstantiationException e) {
			e.printStackTrace();
			System.exit(0);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			System.exit(0);
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
			System.exit(0);
		}   
				
				
		// turn off auto commit
		try{
			conDB.setAutoCommit(false);
		}catch(SQLException e){
			System.out.print("\nFailed trying to turn autocommit off.\n");
			e.printStackTrace();
			System.exit(0);
		}
		
		
	}
}
