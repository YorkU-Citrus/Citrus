package ctrl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.BookBean;
import bean.CommentBean;
import dao.BookDAO;
import dao.CommentDAO;
import exception.CitrusFormException;

/**
 * Servlet implementation class BookItemPage
 */
@WebServlet("/item")
public class BookItemPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookItemPage() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if (request.getParameter("id") == null) {
				throw new CitrusFormException("Cannot find Book ID.");
			}
			int bid;
			try {
				bid = Integer.parseInt(request.getParameter("id"));
			}catch(Exception e) {
				throw new CitrusFormException("Incorrect Book ID.");
			}
			
			BookBean data = BookDAO.getInstance().getBookByID(bid);
			if (data == null) {
				throw new CitrusFormException("No Book Found.");
			}
			
			request.setAttribute("book", data);
			
			List<CommentBean> commentList = CommentDAO.getInstance().getCommentByBookId(data.getBookId(), "PUBLISH", 0, data.getNumberOfComment());
			request.setAttribute("comment_list", commentList);

			request.getRequestDispatcher("/WEB-INF/page-item.jsp").forward(request, response);
		} catch (CitrusFormException e) {
			e.printStackTrace();
			request.getRequestDispatcher("/WEB-INF/page-error.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
