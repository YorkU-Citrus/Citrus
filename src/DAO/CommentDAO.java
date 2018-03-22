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
	
	public ArrayList<CommentBean> getCommentsForBook(Integer bid){
		ArrayList<CommentBean> list = new ArrayList<CommentBean>();
		String queryText = ""; //SQL TEXT
		PreparedStatement querySt = null; // the query handle
		ResultSet results = null; // a cursor
		
		queryText = 
				"Select * "
			+   "From citrus_comment "
			+	"Where cmtbid = ?"
			;
		
		//prepare the query
		try{
			querySt = conDB.prepareStatement(queryText);
		}catch(SQLException e){
			System.out.println("Cmt getCommentsForItem( bid) failed in preparation.");
			System.out.println(e.toString());
			System.exit(0);
		}
		
		// execute the query
		try{
			querySt.setInt(1, bid); // set the place holder
			results = querySt.executeQuery();
		}catch(SQLException e){
			System.out.println("Cmt getCommentsForItem( bid) failed in execute.");
			System.out.println(e.toString());
			System.exit(0);
		}
		
		// any results?
		try{
			while(results.next()){
				Integer id = results.getInt("cmtid");
				Integer uid = results.getInt("cmtuid");
				Integer bookid = results.getInt("cmtbid");
				Timestamp time = results.getTimestamp("cmttime");
				Integer rate = results.getInt("cmtrate");
				String content = results.getString("cmtcontent");
				String status = results.getString("cmtstatus");
				
				CommentBean cmtBean = new CommentBean(id, uid, bookid, time, rate, content, status);
				
				list.add(cmtBean);
				
			}
			
			
		}catch(SQLException e){
			System.out.println("Cmt getCommentsForItem( bid) failed in cursor.");
			System.out.println(e.toString());
			System.exit(0);
		}
		
		// close the cursor
		try{
			results.close();
		}catch(SQLException e){
			System.out.println("Cmt getCommentsForItem( bid) failed in closing cursor.");
			System.out.println(e.toString());
			System.exit(0);
		}
		
		// close the handle
		try{
			querySt.close();
		}catch(SQLException e){
			System.out.println("Cmt getCommentsForItem( bid) failed in closing the handle.");
			System.out.println(e.toString());
			System.exit(0);
		}
		
		
		return list;
	}
	
	
}
