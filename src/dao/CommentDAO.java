package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.CommentBean;

public class CommentDAO {

	// Singleton
	/**
	 * Only one object per program.
	 */
	private static CommentDAO instance = null;
	private static Connection connection = null;

	public static synchronized CommentDAO getInstance()
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (instance == null) {
			instance = new CommentDAO();
		}
		return instance;
	}

	// Implementation
	// Constructor
	private PreparedStatement insertCommentStatement;
	private PreparedStatement updateCommentStatusStatement;
	private PreparedStatement getCommentsByBookId;

	protected CommentDAO() {}
	
	/**
	 * Check if the connection is valid; if not, get a new connection
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void checkConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		if (connection == null || (!connection.isValid(0))) {
			connection = CitrusDAO.getInstance().getConnection();
		}
		this.insertCommentStatement = connection.prepareStatement("INSERT "
				+ "INTO `citrus_db`.`citrus_comment` (`cmtid`, `cmtuid`, `cmtbid`, `cmttime`, `cmtrate`, `cmtcontent`, `cmtstatus`) "
				+ "VALUES (NULL, ?, ?, CURRENT_TIMESTAMP, ?, ?, ?);");
		this.updateCommentStatusStatement = connection.prepareStatement("UPDATE `citrus_db`.`citrus_comment` "
				+ "SET `cmtstatus` = ? WHERE `citrus_comment`.`cmtid` = ?;");
		this.getCommentsByBookId = connection.prepareStatement("SELECT `cmtid`, `cmtuid`, `cmttime`, `cmtrate`, `cmtcontent`, `cmtstatus`, `uname` FROM `citrus_comment` LEFT JOIN `citrus_user` ON `citrus_comment`.`cmtuid` = `citrus_user`.`uid` WHERE `cmtbid` = ? AND `cmtstatus` = ? ORDER BY `cmttime` DESC LIMIT ?,?;");

	}

	
	/**
	 * Post a comment
	 * @param comment
	 * the CommentBean representing the comment to be posted
	 * @return number of comments posted
	 * @throws SQLException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public synchronized int addComment(CommentBean comment) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		checkConnection();
		
		// Execute
		insertCommentStatement.setInt(1, comment.getUserId());
		insertCommentStatement.setInt(2, comment.getBookId());
		if (comment.getRating() == 0) {
			insertCommentStatement.setNull(3, java.sql.Types.INTEGER);
		}else {
			insertCommentStatement.setInt(3, comment.getRating());	
		}
		insertCommentStatement.setString(4, comment.getContent());
		insertCommentStatement.setString(5, comment.getStatus());
		return insertCommentStatement.executeUpdate();
	}
	
	
	/**
	 * Update the status of the comment
	 * @param commentId
	 * @param status
	 * the new status of the comment
	 * @return number of comments updated
	 * @throws SQLException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public synchronized int updateCommentStatus(int commentId, String status) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		checkConnection();
		
		// Execute
		updateCommentStatusStatement.setString(1, status);
		updateCommentStatusStatement.setInt(2, commentId);
		return updateCommentStatusStatement.executeUpdate();		
	}
	

	/**
	 * Get 'limit' number of comments with 'offset' which status='status' for a book, ordered by time 
	 * @param bookId
	 * @param status
	 * @param offset
	 * number of first set of comments to be ignored
	 * @param limit
	 * number of comments wanted
	 * @return a list of CommentBean
	 * @throws SQLException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public synchronized List<CommentBean> getCommentByBookId(int bookId, String status, int offset, int limit) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		checkConnection();
		
		// Execute
		getCommentsByBookId.setInt(1, bookId);
		getCommentsByBookId.setString(2, status);
		getCommentsByBookId.setInt(3, offset);
		getCommentsByBookId.setInt(4, limit);
		
		// Get Result
		ResultSet result = getCommentsByBookId.executeQuery();
		List<CommentBean> resultList = new ArrayList<CommentBean>();
		
		while (result.next()) {
			resultList.add(new CommentBean(
					result.getInt("cmtid"), 
					result.getInt("cmtuid"), 
					result.getString("uname"),
					bookId, 
					result.getTimestamp("cmttime"), 
					result.getInt("cmtrate"), 
					result.getString("cmtcontent"), 
					result.getString("cmtstatus")
					));
		}
		result.close();
		
		return resultList;
	}


	public static void main(String[] args) {
		// Test
		try {
			CommentDAO test = CommentDAO.getInstance();
			// test.addComment(new CommentBean(1, 15, null, 5, "hello world", "PENDING"));
			
			System.out.println(test.updateCommentStatus(5, "PUBLISH"));
			
			List<CommentBean> list = test.getCommentByBookId(2, "PUBLISH", 0, 3);
			System.out.println(list);
			
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
