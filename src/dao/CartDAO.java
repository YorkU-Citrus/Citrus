package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.CartItemBean;

public class CartDAO{
	private static CartDAO instance = null;

	public static synchronized CartDAO getInstance()
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (instance == null) {
			instance = new CartDAO();
		}
		return instance;
	}
	
	private PreparedStatement getCartByUserStatement;
	private PreparedStatement addToCartStatement; //TODO
	
	protected CartDAO() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Connection connection = CitrusDAO.getInstance().getConnection();
		
		this.getCartByUserStatement = connection.prepareStatement("SELECT * FROM citrus_shopping_cart WHERE scuid=? AND scamount>0; ");
		this.addToCartStatement = connection.prepareStatement("INSERT INTO `citrus_shopping_cart`(`scid`, `scuid`, `scbid`, `scamount`, `scprice`) "
				+ "VALUES (NULL,?,?,?,?)");
	}
	
	public List<CartItemBean> getCartByUser(int userId){
		return null;//TODO
	}
	
	public int addToCart(CartItemBean item) throws SQLException{
		addToCartStatement.setInt(1, item.getUserId());
		addToCartStatement.setInt(2, item.getBookId());
		addToCartStatement.setInt(3, item.getAmount());
		addToCartStatement.setInt(4, item.getPrice());
		
		return addToCartStatement.executeUpdate();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
