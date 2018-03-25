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
	public static synchronized CitrusDAO getInstance()
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (instance == null) {
			instance = new CitrusDAO();
		}
		return instance;
	}

	// Implementation

	// Connection to the database system.
	private Connection db_connection;
	
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

	// Methods
	/**
	 * Execute a database query on citrus_db Return a list, each element in the list
	 * is a hashMap representing one row in the query results Entry in HashMap:
	 * <String, Object> THIS METHOD SHOULD BE YOUR LAST CHOICE!
	 * 
	 * @param sql
	 *            - The SQL text you want to execute
	 */
	public List<Map<String, Object>> citrusQuery(String queryText) throws SQLException {
		// Query
		PreparedStatement preparedStatement = db_connection.prepareStatement(queryText);
		ResultSet results = preparedStatement.executeQuery();

		// Table Titles
		ResultSetMetaData metaData = results.getMetaData();
		int columnCount = metaData.getColumnCount();
		String[] columnLabel = new String[columnCount];
		for (int i = 1; i <= columnCount; i++) {
			columnLabel[i - 1] = metaData.getColumnLabel(i);
		}

		// Table Content
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		while (results.next()) {
			Map<String, Object> rowData = new HashMap<String, Object>();
			for (int i = 1; i <= columnCount; i++) {
				rowData.put(columnLabel[i - 1], results.getObject(i));
			}
			result.add(rowData);
		}

		// Terminate
		results.close();
		preparedStatement.close();

		return result;
	}

	/**
	 * Execute a database update on citrus_db THIS METHOD SHOULD BE YOUR LAST
	 * CHOICE!
	 * 
	 * @param sql
	 *            - The SQL text you want to execute
	 */
	public int citrusUpdate(String queryText) throws SQLException {
		// Query
		PreparedStatement preparedStatement = db_connection.prepareStatement(queryText);
		int effectedRow = preparedStatement.executeUpdate();
		
		// Terminate
		preparedStatement.close();
		
		return effectedRow;
	}
	
	public Connection getConnection() {
		return this.db_connection;
	}

	public static void main(String[] args) {
		try {
			// Test
			CitrusDAO test = CitrusDAO.getInstance();
			List<Map<String, Object>> result = test.citrusQuery("show tables;");
			System.out.println(result);

			test.citrusUpdate("INSERT INTO `citrus_db`.`citrus_comment` (`cmtid`, `cmtuid`, `cmtbid`, `cmttime`, `cmtrate`, `cmtcontent`, `cmtstatus`) VALUES (NULL, '1', '2', CURRENT_TIMESTAMP, '2', 'sdfgsdfg', 'PENDING');");
			
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
