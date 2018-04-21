package ctrl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.BookBean;
import bean.CommentBean;
import bean.UserBean;
import dao.BookDAO;
import dao.CommentDAO;
import dao.UserDAO;
import exception.CitrusFormException;
import security.DataFilter;

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
			
			//If it is a submission of a comment
			if (request.getParameter("cmt-textarea") != null) {
				UserBean user = (UserBean) request.getSession().getAttribute("user");
				if (user == null) {
					request.setAttribute("error", "Please <a href='login'>login</a> to continue!");
					request.getRequestDispatcher("/WEB-INF/page-error.jsp").forward(request, response);
					return;
				}
				CommentDAO.getInstance().addComment(new CommentBean(user.getUid(),data.getBookId(),Integer.parseInt(request.getParameter("rate")),DataFilter.removeHTMLTags(request.getParameter("cmt-textarea")), "PUBLISH"));

				request.setAttribute("information", "Your comment has been posted!");
				request.setAttribute("information-detail", "You will see you comment soon.");
				request.getRequestDispatcher("/WEB-INF/page-success.jsp").forward(request, response);
				return;
			}
			
			// Book Information
			request.setAttribute("book", data);
			
			// List of comments
			List<CommentBean> commentList = CommentDAO.getInstance().getCommentByBookId(data.getBookId(), "PUBLISH", 0, data.getNumberOfComment());
			request.setAttribute("comment_list", commentList);
			
			// Permission to post comment
			UserBean user = (UserBean) request.getSession().getAttribute("user");
			if (user == null) {
				request.setAttribute("no_comment_permission", "Please <a href='login'>login</a> to post the comment.");
			}else if (!UserDAO.getInstance().checkUserOrderBook(user.getUid(), data.getBookId())) {
				request.setAttribute("no_comment_permission", "You can post your comment only after you have received the book (books in fulfilled orders).");
			}else {
				
			}

			request.getRequestDispatcher("/WEB-INF/page-item.jsp").forward(request, response);
		} catch (CitrusFormException e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/page-error.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/page-error.jsp").forward(request, response);
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
