package ctrl;

import java.io.IOException;
import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.AddressBean;
import bean.BillingAddressBean;
import bean.BookBean;
import bean.OrderBean;
import bean.OrderItemBean;
import bean.UserBean;
import core.User;
import dao.AddressDAO;
import dao.BookDAO;
import dao.OrderDAO;
import exception.CitrusFormException;

/**
 * Servlet implementation class CheckoutPage
 */
@WebServlet("/checkout")
public class CheckoutPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckoutPage() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if(request.getParameter("thankyou") != null) {
				request.getRequestDispatcher("/WEB-INF/page-checkout-stage3.jsp").forward(request,response);
				return;
			}
			
			////////////////////////////////////////////////////////			
			HttpSession session = request.getSession(true);
			UserBean user = (UserBean) session.getAttribute("user");

			// POST["cart"] -> SESSION["cart"]
			if (request.getParameter("cart") != null) {
				//System.out.println(request.getParameter("cart"));
				session.setAttribute("cart", request.getParameter("cart"));
				session.setAttribute("order", null);
			}
			
			if (user == null) {
				request.getRequestDispatcher("/WEB-INF/page-checkout-stage1.jsp").forward(request,response);
				return;
			}		
			
			if (session.getAttribute("cart") != null) {
				// Verify billing and shipping addresses
				
				// load billing address
				BillingAddressBean billingAddressData = AddressDAO.getInstance().getBillingAddressByUser(user.getUid());
				session.setAttribute("billing", billingAddressData);
				
				// load shipping address
				AddressBean shippingAddressData = AddressDAO.getInstance().getAddressByUser(user.getUid());	
				session.setAttribute("shipping", shippingAddressData);	
				
				// Print out shipping cart for confirm
				if (session.getAttribute("order") == null) {
					JsonReader jsonReader = Json.createReader(new StringReader((String) session.getAttribute("cart")));
					JsonObject object = jsonReader.readObject();
					
					OrderBean order = new OrderBean(user.getUid());
					order.setTaxRate(13);
					for (String k : object.keySet()) {
						BookBean book = BookDAO.getInstance().getBookByID(Integer.parseInt(k));
						if (book != null) {
							order.appendList(new OrderItemBean(book,object.getInt(k)));
						}					
					}
					
					jsonReader.close();
					session.setAttribute("cart", null);	
					session.setAttribute("order", order);	
				}
				
				OrderBean order = (OrderBean) session.getAttribute("order");
				request.setAttribute("total", String.format("%.2f",order.getTotalPrice()/100.0));
				request.setAttribute("bill", order.receipt());
				
				request.getRequestDispatcher("/WEB-INF/page-checkout-stage2.jsp").forward(request,response);
				return;
			}
						
			
			// SESSION["cart"] -> SESSION["order"]
			if ((session.getAttribute("order") != null) && (request.getParameter("checkoutform") != null)) {
				OrderBean order = (OrderBean) session.getAttribute("order");
				//System.out.println(order.receipt());				

				// Update billing and shipping address
				try {
					//request.getParameter("creditcard"), request.getParameter("creditcard-password"),
					User.updateBillingInformation(request.getParameter("firstname"), request.getParameter("lastname"),
							request.getParameter("addr1"), request.getParameter("province"),
							request.getParameter("country"), request.getParameter("pcode"), request);
				}catch(CitrusFormException e) {		
					request.setAttribute("total", String.format("%.2f",order.getTotalPrice()/100.0));
					request.setAttribute("bill", order.receipt());
					request.setAttribute("billing_error", e.getMessage());
					request.getRequestDispatcher("/WEB-INF/page-checkout-stage2.jsp").forward(request,response);
					return;	
				}
				try {
					User.updateShippingInformation(request.getParameter("firstname-ship"), request.getParameter("lastname-ship"), 
							request.getParameter("addr1-ship"), request.getParameter("province-ship"), 
							request.getParameter("country-ship"), request.getParameter("pcode-ship"), request);
				}catch(CitrusFormException e) {		
					request.setAttribute("total", String.format("%.2f",order.getTotalPrice()/100.0));
					request.setAttribute("bill", order.receipt());
					request.setAttribute("shipping_error", e.getMessage());
					request.getRequestDispatcher("/WEB-INF/page-checkout-stage2.jsp").forward(request,response);
					return;
				}
				
				// process order
				OrderDAO.getInstance().placeOrder(order, order.getOderList());
				
				session.setAttribute("cart", null);
				session.setAttribute("order", null);
				response.sendRedirect("checkout?thankyou");				
				return;
			}
			
			
			
			throw new Exception("You cannot visit this page.");
			
		}catch (Exception e) {
			//e.printStackTrace();
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/page-error.jsp").forward(request,response);
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
