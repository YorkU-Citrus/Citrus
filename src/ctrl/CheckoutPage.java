package ctrl;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
import dao.AddressDAO;
import dao.BookDAO;
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
			HttpSession session = request.getSession(true);
			UserBean user = (UserBean) session.getAttribute("user");
			if (request.getParameter("checkoutform") != null) {
				//Process order.
				
				OrderBean order = (OrderBean) session.getAttribute("order");
				if (order == null) {
					
				}
				request.getRequestDispatcher("/WEB-INF/page-checkout-stage3.jsp").forward(request,response);
				// hard code three time error
				if (request.getParameter("checkoutform") == "123") {
					throw new CitrusFormException("This is a hard core(coded) error!");
				}
				session.removeAttribute("order");
			}else {
				// Update session shopping cart
				if (request.getParameter("cart") != null) {
					System.out.println(request.getParameter("cart"));
					session.setAttribute("cart", request.getParameter("cart"));
				}
				
				if (user != null) {
					// Verify billing and shipping addresses
					
					// load billing address
					BillingAddressBean billingAddressData = AddressDAO.getInstance().getBillingAddressByUser(user.getUid());
					session.setAttribute("billing", billingAddressData);
					
					// load shipping address
					AddressBean shippingAddressData = AddressDAO.getInstance().getAddressByUser(user.getUid());	
					session.setAttribute("shipping", shippingAddressData);	
					
					// Print out shipping cart for confirm
					String bill = "";
					JsonReader jsonReader = Json.createReader(new StringReader((String) session.getAttribute("cart")));
					JsonObject object = jsonReader.readObject();
					
					List<OrderItemBean> shoppingCart = new ArrayList<OrderItemBean>();
					int sum = 0;
					for (String k : object.keySet()) {
						BookBean book = BookDAO.getInstance().getBookByID(Integer.parseInt(k));
						bill += book.getTitle() + "\n" + String.format("%.2f",book.getPrice()/100.0) + " CAD x " +  object.getInt(k) + "\n\n";
						shoppingCart.add(new OrderItemBean(Integer.parseInt(k),object.getInt(k)));
						sum += book.getPrice() * object.getInt(k);						
					}
					
					jsonReader.close();
					bill += "Subtotal: " + String.format("%.2f",sum/100.0) + " CAD\n";
					bill += "HST: " + String.format("%.2f",sum*0.13/100.0) + " CAD\n";
					bill += "\n";
					bill += "Total: " + String.format("%.2f",sum*1.13/100.0) + " CAD\n";					
					
					session.setAttribute("order", new OrderBean(user.getUid(), (int) Math.round(sum*1.13)));					
					request.setAttribute("total", String.format("%.2f",sum*1.13/100.0));
					request.setAttribute("bill", bill);
					
					request.getRequestDispatcher("/WEB-INF/page-checkout-stage2.jsp").forward(request,response);
				}else {
					// Ask them to login or sign up
					request.getRequestDispatcher("/WEB-INF/page-checkout-stage1.jsp").forward(request,response);
				}
			}
		} catch (CitrusFormException e) {
			e.printStackTrace();
		} catch (Exception e) {
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
