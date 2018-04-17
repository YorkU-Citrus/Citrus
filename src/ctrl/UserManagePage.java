package ctrl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.User;
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
		if (request.getParameter("type") != null) {
			if (request.getParameter("type").equals("shipping")) {
				functionShipping(request, response);
				return;
			} else if (request.getParameter("type").equals("history")) {

			} else if (request.getParameter("type").equals("analytic")) {

			} else if (request.getParameter("type").equals("shipping")) {

			} else if (request.getParameter("type").equals("products")) {

			} else if (request.getParameter("type").equals("orders")) {

			} else if (request.getParameter("type").equals("comments")) {

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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
