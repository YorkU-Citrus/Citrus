package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.MonthlyBean;

public class MonthlyDAO {
	private static MonthlyDAO instance = null;
	
	public static synchronized MonthlyDAO getInstance() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (instance == null) {
			instance = new MonthlyDAO();
		}
		return instance;
	}
	
	private PreparedStatement getSalesInMonthStatement;
	
	protected MonthlyDAO() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Connection connection = CitrusDAO.getInstance().getConnection();
		
		this.getSalesInMonthStatement = connection.prepareStatement("SELECT YEAR(otime) as year, MONTH(otime) as month, SUM(oiamount) as sales "
				+ "FROM citrus_order, citrus_order_item "
				+ "WHERE citrus_order.oid=citrus_order_item.oioid "
				+ "AND YEAR(otime)=? "
				+ "and MONTH(otime)=? "
				+ "GROUP BY YEAR(otime), MONTH(otime) "
				);
	}
	
	public synchronized MonthlyBean getSalesInMonth(int year, int month) throws SQLException{
		getSalesInMonthStatement.setInt(1, year);
		getSalesInMonthStatement.setInt(2, month);
		
		ResultSet result = getSalesInMonthStatement.executeQuery();
		if(result.next()) {
			return new MonthlyBean(result.getInt("year"), result.getInt("month"), result.getInt("sales"));
		}
		
		return null;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MonthlyDAO test;
		try {
			test = MonthlyDAO.getInstance();
			System.out.println(test.getSalesInMonth(2018, 3));
			System.out.println(test.getSalesInMonth(2018, 4));
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
