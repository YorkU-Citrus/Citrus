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
				if (billingAddressData != null) {
					session.setAttribute("billing", billingAddressData);
					session.setAttribute("b_firstname", billingAddressData.getFirstName());
					session.setAttribute("b_lastname", billingAddressData.getLastName());
					session.setAttribute("b_address", billingAddressData.getStreet());
					session.setAttribute("b_provience", billingAddressData.getProvince());
					session.setAttribute("b_country", billingAddressData.getCountry());
					session.setAttribute("b_postal", billingAddressData.getZip());
				}
				
				// load shipping address
				AddressBean shippingAddressData = AddressDAO.getInstance().getAddressByUser(user.getUid());
				if (shippingAddressData != null) {
					session.setAttribute("shipping", shippingAddressData);	
					session.setAttribute("s_firstname", shippingAddressData.getFirstName());
					session.setAttribute("s_lastname", shippingAddressData.getLastName());
					session.setAttribute("s_address", shippingAddressData.getStreet());
					session.setAttribute("s_provience", shippingAddressData.getProvince());
					session.setAttribute("s_country", shippingAddressData.getCountry());
					session.setAttribute("s_postal", shippingAddressData.getZip());
				}
				
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

				// Keep the form data although it is not correct.
				if (request.getParameter("firstname") != null) session.setAttribute("b_firstname", request.getParameter("firstname"));
				if (request.getParameter("lastname") != null) session.setAttribute("b_lastname", request.getParameter("lastname"));
				if (request.getParameter("addr1") != null) session.setAttribute("b_address", request.getParameter("addr1"));
				if (request.getParameter("province") != null) session.setAttribute("b_provience", request.getParameter("province"));
				if (request.getParameter("country") != null) session.setAttribute("b_country", request.getParameter("country"));
				if (request.getParameter("pcode") != null) session.setAttribute("b_postal", request.getParameter("pcode"));
				
				if (request.getParameter("firstname-ship") != null) session.setAttribute("s_firstname", request.getParameter("firstname-ship"));
				if (request.getParameter("lastname-ship") != null) session.setAttribute("s_lastname", request.getParameter("lastname-ship"));
				if (request.getParameter("addr1-ship") != null) session.setAttribute("s_address", request.getParameter("addr1-ship"));
				if (request.getParameter("province-ship") != null) session.setAttribute("s_provience", request.getParameter("province-ship"));
				if (request.getParameter("country-ship") != null) session.setAttribute("s_country", request.getParameter("country-ship"));
				if (request.getParameter("pcode-ship") != null) session.setAttribute("s_postal", request.getParameter("pcode-ship"));
				
				// Update billing and shipping address
				try {
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
				
				// Check Credit Card
				// IMPLEMENTED 3rd request denied

				try {
					String creditcard = request.getParameter("creditcard");
					String cvvcode = request.getParameter("creditcard-password");
					if ((creditcard == null) || (creditcard == "")) {
						throw new CitrusFormException("Please enter your credit card number");
					}
					if (!creditcard.matches("^[0-9]{16}$")) {
						throw new CitrusFormException("Please enter a valid credit card number");
					}
					if ((cvvcode == null) || (cvvcode == "")) {
						throw new CitrusFormException("Please enter your CVV number");
					}
					if (!cvvcode.matches("^[0-9]{3,4}$")) {
						throw new CitrusFormException("Please enter a valid CVV number");
					}
				}catch(CitrusFormException e) {		
					request.setAttribute("total", String.format("%.2f",order.getTotalPrice()/100.0));
					request.setAttribute("bill", order.receipt());
					request.setAttribute("billing_error", e.getMessage());
					request.getRequestDispatcher("/WEB-INF/page-checkout-stage2.jsp").forward(request,response);
					return;
				}
				
				if (session.getAttribute("requirementG") == null) {
					session.setAttribute("requirementG", 0);
				}
				session.setAttribute("requirementG", (int)session.getAttribute("requirementG") + 1);
				if ((int)session.getAttribute("requirementG") % 3 == 0) {
					request.setAttribute("billing_error", "Credit Card Authorization Faild");
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
			e.printStackTrace();			
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
