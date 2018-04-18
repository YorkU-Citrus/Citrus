package ctrl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.BookBean;
import bean.MonthlyBean;
import bean.OrderBean;
import bean.UserBean;
import core.User;
import dao.BookDAO;
import dao.MonthlyDAO;
import dao.OrderDAO;
import exception.CitrusFormException;

/**
 * Servlet implementation class UserManagePage
 */
@WebServlet("/manage")
public class UserManagePage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserManagePage() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserBean user = (UserBean) request.getSession().getAttribute("user");
		if (user.getRole().equals("MANAGER")) {
			request.setAttribute("manager", "manager");
		}
		
		if (request.getParameter("type") != null) {
			if (request.getParameter("type").equals("shipping")) {
				functionShipping(request, response);
				return;
			} else if (request.getParameter("type").equals("history")) {
				functionHistory(request, response);
				return;
			} else if (request.getParameter("type").equals("analytics")) {
				functionAnalytics(request, response);
				return;
			} else if (request.getParameter("type").equals("products")) {
				functionProducts(request, response);
				return;
			} else if (request.getParameter("type").equals("orders")) {
				functionOrders(request, response);
				return;
			} else if (request.getParameter("type").equals("comments")) {
				functionComments(request, response);
				return;
			} else if (request.getParameter("type").equals("signout")) {
				request.getSession().setAttribute("user", null);
				response.sendRedirect(request.getContextPath());
				return;
			}
		}
		// Default use Billing address
		functionBilling(request, response);
	}

	protected void functionBilling(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("billingactive", "active");
		try {
			//
			if (request.getParameter("formtype") != null) {
				User.updateBillingInformation(request.getParameter("firstname"), request.getParameter("lastname"),
						request.getParameter("creditcard"), request.getParameter("creditcard-password"),
						request.getParameter("address"), request.getParameter("province"),
						request.getParameter("country"), request.getParameter("pcode"), request);
				request.setAttribute("success", "Your information has been updated.");
			}
			User.loadBillingInformation(request);
		} catch (CitrusFormException e) {
			request.setAttribute("error", e.getMessage());
		}
		request.getRequestDispatcher("/WEB-INF/page-manage-billing.jsp").forward(request, response);
	}
	

	protected void functionShipping(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("shippingactive", "active");
		try {
			if (request.getParameter("formtype") != null) {
				
				User.updateShippingInformation(request.getParameter("firstname"), request.getParameter("lastname"), 
						request.getParameter("address"), request.getParameter("province"), 
						request.getParameter("country"), request.getParameter("pcode"), request);
					request.setAttribute("success", "Your shipping information has been updated.");
			}
			User.loadShippingInformation(request);
			
		} catch (CitrusFormException e) {
			request.setAttribute("error", e.getMessage());
		}
		request.getRequestDispatcher("/WEB-INF/page-manage-shipping.jsp").forward(request, response);
	}
	

	protected void functionHistory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("historyactive", "active");
		try {
			UserBean user = (UserBean) request.getSession().getAttribute("user");
			List<OrderBean> list = OrderDAO.getInstance().getOrdersByUser(user.getUid());
			request.setAttribute("order_list", list);	
			request.setAttribute("message", String.format("You have submitted %d order(s).",list.size()));
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/page-error.jsp").forward(request, response);
			return;
		}
		request.getRequestDispatcher("/WEB-INF/page-manage-history.jsp").forward(request, response);
	}
	

	protected void functionAnalytics(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("analyticsactive", "active");
		try {
			List<MonthlyBean> monthlyList = MonthlyDAO.getInstance().getSalesReport();
			request.setAttribute("monthly_list", monthlyList);
			List<BookBean> topList = BookDAO.getInstance().getMostPopularBooks(10);
			request.setAttribute("top_list", topList);	
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/page-error.jsp").forward(request, response);
			return;
		}
		request.getRequestDispatcher("/WEB-INF/page-manage-analytics.jsp").forward(request, response);
	}
	


	protected void functionProducts(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("productsactive", "active");
		request.getRequestDispatcher("/WEB-INF/page-manage-products.jsp").forward(request, response);
	}
	

	protected void functionOrders(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("ordersactive", "active");
		request.getRequestDispatcher("/WEB-INF/page-manage-orders.jsp").forward(request, response);
	}

	protected void functionComments(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("commentsactive", "active");
		request.getRequestDispatcher("/WEB-INF/page-manage-comments.jsp").forward(request, response);
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
