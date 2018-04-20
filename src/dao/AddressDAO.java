package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import bean.AddressBean;
import bean.BillingAddressBean;

public class AddressDAO {
	private static AddressDAO instance = null;
	private static Connection connection = null;
	
	public static synchronized AddressDAO getInstance()
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (instance == null) {
			instance = new AddressDAO();
		}
		return instance;
	}
	
	private PreparedStatement getShippingAddressByUserStatement;
	private PreparedStatement addShippingAddressStatement;
	private PreparedStatement getBillingAddressByUserStatement;
	private PreparedStatement addBillingAddressStatement;
	
	protected AddressDAO() {}
	
	public void checkConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		if (connection == null || (!connection.isValid(0))) {
			connection = CitrusDAO.getInstance().getConnection();
		}
		this.getShippingAddressByUserStatement = connection.prepareStatement("SELECT *  FROM `citrus_shipping_address` WHERE `sauid` = ? ORDER BY `satime` DESC LIMIT 1");
		
		this.addShippingAddressStatement = connection.prepareStatement("INSERT INTO `citrus_shipping_address`(`said`, `sauid`, `satime`, `safirst`, `salast`, `sastreet`, `saprovince`, `sacountry`, `sazip`) "
				+ "VALUES (NULL,?,CURRENT_TIMESTAMP,?,?,?,?,?,?)");
		
		this.getBillingAddressByUserStatement = connection.prepareStatement("SELECT *  FROM `citrus_billing_address` WHERE `bauid` = ? ORDER BY `batime` DESC LIMIT 1");
		
		this.addBillingAddressStatement = connection.prepareStatement("INSERT INTO `citrus_billing_address`"
				+ "(`baid`, `bauid`, `batime`, `bafirst`, `balast`, `bastreet`, `baprovince`, `bacountry`, `bazip`) "
				+ "VALUES (NULL, ?, CURRENT_TIMESTAMP, ?, ?, ?, ?, ?, ?)");
	}
	
	public synchronized int addBillingAddress(BillingAddressBean billing) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		checkConnection();
		
		addBillingAddressStatement.setInt(1, billing.getUserId());
		addBillingAddressStatement.setString(2, billing.getFirstName());
		addBillingAddressStatement.setString(3, billing.getLastName());
		addBillingAddressStatement.setString(4, billing.getStreet());
		addBillingAddressStatement.setString(5, billing.getProvince());
		addBillingAddressStatement.setString(6, billing.getCountry());
		addBillingAddressStatement.setString(7, billing.getZip());
		
		
		return addBillingAddressStatement.executeUpdate();
	}
	
	
	
	//choose the latest address
	public synchronized BillingAddressBean getBillingAddressByUser(int userId) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		checkConnection();
		
		getBillingAddressByUserStatement.setInt(1, userId);		
		ResultSet result = getBillingAddressByUserStatement.executeQuery();
		
		if(result.next()) {
			return new BillingAddressBean(result.getInt("bauid"), result.getTimestamp("batime"), 
					result.getString("bafirst"), 
					result.getString("balast"), 
					result.getString("bastreet"), 
					result.getString("baprovince"), 
					result.getString("bacountry"), 
					result.getString("bazip"));
		}
		else {
			return null;
		}
	}
	
	
	//choose the lastest address
	public synchronized AddressBean getAddressByUser(int userId) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		checkConnection();
		
		getShippingAddressByUserStatement.setInt(1, userId);
		ResultSet result = getShippingAddressByUserStatement.executeQuery();
		
		if(result.next()) {
			return new AddressBean(
					//result.getInt("said"), 
					result.getInt("sauid"), 
					result.getString("safirst"), 
					result.getString("salast"), 
					result.getString("sastreet"), 
					result.getString("saprovince"), 
					result.getString("sacountry"), 
					result.getString("sazip"));
		}
		else {
			return null;
		}
	}
	
	//add or update
	public synchronized int addAddress(AddressBean address) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		checkConnection();
		
		addShippingAddressStatement.setInt(1, address.getUserId());
		addShippingAddressStatement.setString(2, address.getFirstName());
		addShippingAddressStatement.setString(3, address.getLastName());
		addShippingAddressStatement.setString(4, address.getStreet());
		addShippingAddressStatement.setString(5, address.getProvince());
		addShippingAddressStatement.setString(6, address.getCountry());
		addShippingAddressStatement.setString(7, address.getZip());
		
		return addShippingAddressStatement.executeUpdate();
	}
	
	
	public static void main(String[] args) {
		try {
			AddressDAO test = AddressDAO.getInstance();
			//AddressBean a1 = new AddressBean (1, "u1_first", "u1_last", "younge", "ON", "Canada", "M3J 1P3");
			//AddressBean a2 = new AddressBean(1, "u1_first", "u1_last", "finch", "ON", "Canada", "M3K 4Q5");
			//AddressBean a3 = new AddressBean(2, "u2_first", "u2_last", "Byng", "ON", "Canada", "M4O UFO");
			
			
			//System.out.println(test.addAddress(a1));
			//System.out.println(test.addAddress(a2));
			//System.out.println(test.addAddress(a3));
			
			//System.out.println(test.getAddressByUser(1));
			
			BillingAddressBean ba1 = new BillingAddressBean(3, new Timestamp(new Date().getTime()), " My dad ", "pays for me", "Jane", "AN", "Wakada", "666 666");
			//System.out.println(ba1);
			
			test.addBillingAddress(ba1);
			//System.out.println(test.getBillingAddressByUser(3));
			
			
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
