package service;

import java.sql.SQLException;

import bean.BookBean;
import dao.BookDAO;

public class ProductCatalog {
	
	public BookBean getProductInfo(int productId) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return BookDAO.getInstance().getBookByID(productId);
	}
	
}
