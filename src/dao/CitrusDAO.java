package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

public class CitrusDAO {

	// Singleton
	/**
	 * Only one object per program.
	 */
	private static CitrusDAO instance = null;

	/**
	 * Prevents duplicate model.
	 * 
	 * @return singleton object of this model
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws Exception 
	 */
	public static synchronized CitrusDAO getInstance() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (instance == null) {
			instance = new CitrusDAO();
		}
		return instance;
	}

	// Implementation

	// Connection to the database system.
	Connection db_connection;
	// Database location
	public static final String url = "jdbc:mariadb://yukikaze.yuri.moe:3366/citrus_db";

	// Constructor
	protected CitrusDAO() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		// Set up the DB connection.
		// Register the driver with DriverManager.
		Class.forName("org.mariadb.jdbc.Driver").newInstance();

		// initialize the connection
		db_connection = DriverManager.getConnection(url, "citrus_db", "PGDHXSYjY2CMhDAh");

		// turn on auto commit
		db_connection.setAutoCommit(true);
	}

	/**
	 * Execute a database query on citrus_db Return a list, each element in the list
	 * is a hashMap representing one row in the query results Entry in HashMap:
	 * <String, Object>
	 * THIS METHOD SHOULD BE YOUR LAST CHOICE!
	 * 
	 * @param sql
	 *            - The SQL text you want to execute
	 */
	public List<Map<String, Object>> citrusQuery(String queryText) throws SQLException {

		PreparedStatement preparedStatement = db_connection.prepareStatement(queryText);
		ResultSet results = preparedStatement.executeQuery();
		ResultSetMetaData metaData = results.getMetaData();
		int columnCount = metaData.getColumnCount();
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

		while (results.next()) {
			Map<String, Object> rowData = new HashMap<String, Object>();
			for (int i = 1; i <= columnCount; i++) {
				rowData.put(metaData.getColumnName(i), results.getObject(i));
			}
			result.add(rowData);
		}

		results.close();
		preparedStatement.close();

		return result;
	}

	/**
	 * Execute a database update on citrus_db
	 * THIS METHOD SHOULD BE YOUR LAST CHOICE!
	 * 
	 * @param sql
	 *            - The SQL text you want to execute
	 */
	public void citrusUpdate(String queryText) throws SQLException {
		
		PreparedStatement preparedStatement = db_connection.prepareStatement(queryText);
		preparedStatement.executeUpdate();
		preparedStatement.close();
	}
}
