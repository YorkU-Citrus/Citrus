package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.MonthlyBean;

public class MonthlyDAO {
	private static MonthlyDAO instance = null;
	private static Connection connection = null;
	
	public static synchronized MonthlyDAO getInstance() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (instance == null) {
			instance = new MonthlyDAO();
		}
		return instance;
	}
	
	private PreparedStatement getSalesInMonthStatement;
	private PreparedStatement getSalesReportStatement;
	
	protected MonthlyDAO() {}
	
	public void checkConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		if (connection == null || (!connection.isValid(0))) {
			connection = CitrusDAO.getInstance().getConnection();
		}
		this.getSalesInMonthStatement = connection.prepareStatement("SELECT YEAR(otime) as year, MONTH(otime) as month, SUM(oiamount) as sales "
				+ "FROM citrus_order, citrus_order_item "
				+ "WHERE citrus_order.oid=citrus_order_item.oioid "
				+ "AND YEAR(otime)=? "
				+ "and MONTH(otime)=? "
				+ "GROUP BY YEAR(otime), MONTH(otime) "
				);
		
		this.getSalesReportStatement = connection.prepareStatement("SELECT YEAR(otime) as year, MONTH(otime) as month, SUM(oiamount) as sales "
				+ "FROM citrus_order, citrus_order_item "
				+ "WHERE citrus_order.oid=citrus_order_item.oioid "
				+ "GROUP BY YEAR(otime), MONTH(otime) "
				);
	}
	
	public synchronized List<MonthlyBean> getSalesReport() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		checkConnection();
		
		ResultSet results = getSalesReportStatement.executeQuery();
		List<MonthlyBean> list = new ArrayList<MonthlyBean>();
		while(results.next()) {
			list.add( new MonthlyBean(results.getInt("year"), results.getInt("month"), results.getInt("sales")));
		}
		
		
		return list;
	}
	
	public synchronized MonthlyBean getSalesInMonth(int year, int month) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		checkConnection();
		
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
			for(MonthlyBean m: test.getSalesReport()) {
				System.out.println(m);
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
