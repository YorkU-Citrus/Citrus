package ctrl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CategoryBean;
import dao.CategoryDAO;
import exception.CitrusFormException;

/**
 * Servlet implementation class BookListPage
 */
@WebServlet({ "/list" })
public class BookListPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookListPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("js_search", "");
			request.setAttribute("js_cat_id", "");
			
			List<CategoryBean> categoryList = CategoryDAO.getInstance().getCategory();
			request.setAttribute("category_list", categoryList);
		
			if (request.getParameter("category") != null) {
				CategoryBean category = CategoryDAO.getInstance().getCategoryById(Integer.parseInt(request.getParameter("category")));
				if (category == null) {
					throw new CitrusFormException("No such a category ID.");
				}
				request.setAttribute("note", String.format("Books in category [ <strong>%s</strong> ].", category.getCtitle()));
				request.setAttribute("js_cat_id", Integer.parseInt(request.getParameter("category")));				
			}else if (request.getParameter("search") != null) {
				// TODO: Need to filter out search keyword escaping HTML
				String search_keyword = request.getParameter("search");
				request.setAttribute("note", String.format("Search using keyword [ <strong>%s</strong> ].", search_keyword));
				request.setAttribute("js_search", search_keyword);
			}
			
					
			request.getRequestDispatcher("/WEB-INF/page-list.jsp").forward(request,response);

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException | CitrusFormException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
