package filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import bean.UserBean;

/**
 * Servlet Filter implementation class ManagerPageFilter
 */
@WebFilter(dispatcherTypes = {DispatcherType.REQUEST }, urlPatterns  = {"/manage/*"})
public class ManagerPageFilter implements Filter {

    /**
     * Default constructor. 
     */
    public ManagerPageFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		
		try {
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			UserBean user = (UserBean) httpRequest.getSession().getAttribute("user");
			if (user == null) {
				request.setAttribute("error", "Please login!");
				request.getRequestDispatcher("/WEB-INF/page-error.jsp").forward(request,response);
				return;
			}
			
			if (user.getRole() != "MANAGER") {
				//System.out.println(request.getParameter("type"));
				if (
						(request.getParameter("type") == null)||
						(request.getParameter("type").equals("billing"))||
						(request.getParameter("type").equals("shipping"))||
						(request.getParameter("type").equals("history"))||
						(request.getParameter("type").equals("signout"))
					) {
					// no problem
				}else {
					request.setAttribute("error", "You do not have permission.");
					request.getRequestDispatcher("/WEB-INF/page-error.jsp").forward(request,response);
					return;
				}
			}
		}catch(Exception e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/page-error.jsp").forward(request,response);
			return;
		}
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
