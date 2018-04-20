package ctrl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.User;
import exception.CitrusFormException;
import security.DataFilter;

/**
 * Servlet implementation class UserRegisterPage
 */
@WebServlet("/register")
public class UserRegisterPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegisterPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("formtype") != null) {
			try {		
				// Removed the Possible injection
				User.registerUser(DataFilter.removeHTMLTags(request.getParameter("username")), DataFilter.removeHTMLTags(request.getParameter("password")),  DataFilter.removeHTMLTags(request.getParameter("repassword")), request);
				if (request.getParameter("checkout") != null) {
					response.sendRedirect(request.getContextPath() + "/checkout");
				}else {
					response.sendRedirect(request.getContextPath());
				}
				return;
			} catch (CitrusFormException e) {
				request.setAttribute("error", e.getMessage());
			}			
		}		
		request.getRequestDispatcher("/WEB-INF/page-register.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
