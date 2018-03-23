package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;

import bean.*;

public class CommentDAO extends CitrusDAO {
	/*
	 * private UserBean user;
	private int rating;
	private Timestamp timestamp;
	private int status;
	private String comment;
	 */
	
	public CommentDAO(){
		super();
	}
	
	//update
	public void removeComment(int cmtid) {
		String queryText = ""; 
		PreparedStatement querySt = null; 
		
		queryText = "Update citrus_comment "
				+ "Set cmtstatus = ? "
				+ "Where cmtid = ? ";
		
		try {
			//prepare
			querySt = conDB.prepareStatement(queryText);
			
			//execute
			querySt.setString(1, "REMOVE");
			querySt.setInt(2, cmtid);
			querySt.executeUpdate();
			
			//close
			querySt.close();
			
		} catch (SQLException e) {
			System.out.println("CommentDAO removeComment failed.");	
			System.out.println(e.toString());
		}
	}
	
	//update
	public void publishComment(int cmtid) {
		String queryText = ""; 
		PreparedStatement querySt = null; 
		
		queryText = "Update citrus_comment "
				+ "Set cmtstatus = ? "
				+ "Where cmtid = ? ";
		
		try {
			//prepare
			querySt = conDB.prepareStatement(queryText);
			
			//execute
			querySt.setString(1, "PUBLISH");
			querySt.setInt(2, cmtid);
			querySt.executeUpdate();
			
			//close
			querySt.close();
			
		} catch (SQLException e) {
			System.out.println("CommentDAO publishComment failed.");	
			System.out.println(e.toString());
		}
	}
	
	public void addComment(CommentBean cb) {
		String queryText = ""; 
		PreparedStatement querySt = null; 
		
		queryText = "Insert "
				+ "into citrus_comment "
				+ "value( ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			//prepare
			querySt = conDB.prepareStatement(queryText);
			
			//execute
			// 1 cmtid AUTO_INCREMENT
			querySt.setInt(1, cb.getCmtid()); 
			querySt.setInt(2, cb.getUser().getUid()); 
			querySt.setInt(3, cb.getBookid());
			querySt.setTimestamp(4, cb.getTimestamp());
			querySt.setInt(5, cb.getRating());
			querySt.setString(6, cb.getContent());
			querySt.setString(7, cb.getStatus());
			
			querySt.executeUpdate();
			
			//close
			querySt.close();
			
		} catch (SQLException e) {
			System.out.println("CommentDAO addComment failed.");	
			System.out.println(e.toString());
		}
	}
	
	public List<CommentBean> getCommentsForBook(Integer bid){
		List<CommentBean> list = new ArrayList<CommentBean>();
		String queryText = ""; //SQL TEXT
		PreparedStatement querySt = null; // the query handle
		ResultSet results = null; // a cursor
		
		queryText = 
				"Select * "
			+   "From citrus_comment, citrus_user "
			+	"Where citrus_comment.cmtbid = ? And citrus_user.uid = citrus_comment.cmtuid "
			;
		
		//prepare the query
		try{
			querySt = conDB.prepareStatement(queryText);
		}catch(SQLException e){
			System.out.println("Cmt getCommentsForItem( bid) failed in preparation.");
			System.out.println(e.toString());
			
		}
		
		// execute the query
		try{
			querySt.setInt(1, bid); // set the place holder
			results = querySt.executeQuery();
		}catch(SQLException e){
			System.out.println("Cmt getCommentsForItem( bid) failed in execute.");
			System.out.println(e.toString());
			
		}
		
		// any results?
		try{
			while(results.next()){
				
				Integer cmtid = results.getInt("cmtid");
				Integer uid = results.getInt("cmtuid");
				Timestamp ts = results.getTimestamp("cmttime");
				Integer rate = results.getInt("cmtrate");
				String content = results.getString("cmtcontent");
				String status = results.getString("cmtstatus");
				
				String name = results.getString("uname");
				//String password = results.getString("upassword");
				Timestamp lastactive = results.getTimestamp("ulastactive");
				
				UserBean user = new UserBean(uid, name, lastactive);
				
				CommentBean cmtBean = new CommentBean(cmtid, user, bid, ts, rate, status, content);
				
				list.add(cmtBean);
				
			}
			
			
		}catch(SQLException e){
			System.out.println("Cmt getCommentsForItem( bid) failed in cursor.");
			System.out.println(e.toString());
			
		}
		
		// close the cursor
		try{
			results.close();
		}catch(SQLException e){
			System.out.println("Cmt getCommentsForItem( bid) failed in closing cursor.");
			System.out.println(e.toString());
			
		}
		
		// close the handle
		try{
			querySt.close();
		}catch(SQLException e){
			System.out.println("Cmt getCommentsForItem( bid) failed in closing the handle.");
			System.out.println(e.toString());
			
		}
		
		
		return list;
	}
	
	public static void main(String[] args) {
		UserDAO uDao = new UserDAO();
		CommentDAO cDao = new CommentDAO();
		
		UserBean user1 = uDao.getUserByID(1);
		CommentBean cb = new CommentBean(0, user1, 2, new Timestamp(new Date().getTime()), 3, "I dont like it ", "PENDING");
		//cDao.addComment(cb);
		cDao.publishComment(1);
		cDao.removeComment(2);
	}
	
}
